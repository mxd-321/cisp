package com.mxd.cisp.utils;

import com.jcraft.jsch.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author mxd
 * @Date 2021/12/2 20:30
 */
public class FileUtil {

  /**
   * sftp通道
   */
  private static ChannelSftp sftp = null;
  /**
   * ftp服务器ip地址
   */
  private static final String FTP_ADDRESS = "neteasemxd.top";
  /**
   * 端口号
   */
  private static final int FTP_PORT = 22;
  /**
   * 用户名
   */
  private static final String FTP_USERNAME = "root";
  /**
   *密码
   */
  private static final String FTP_PASSWORD = "mxd0321.";
  /**
   *图片路径
   */
  public static final String FTP_BASEPATH = "/images";
  /**
   * 访问路径
   */
  private static final String PATH_PREFIX = "https://neteasemxd.top/images/";

  /**
   * 上传文件
   * @param file 文件对象
   * @return 文件名
   * @throws IOException 异常
   */
  public static String upload(MultipartFile file) throws IOException {
    //获取到文件的文件名
    String fileName = file.getOriginalFilename();
    //根据文件名+UUID生产新的文件名
    String newFileName = UUID.randomUUID() + fileName;
    //从MultipartFile对象中获取流
    InputStream inputStream = file.getInputStream();
    //判断是否成功的boolean值
    boolean success = false;
    //返回值
    String path = "";
    //Session对象
    Session session = null;
    try {
      //创建JSch对象
      JSch jSch = new JSch();
      //调用JSch对象的getSession方法（参数是服务器的访问用户名,服务器访问路径,服务器的端口号）给session赋值
      session = jSch.getSession(FTP_USERNAME,FTP_ADDRESS,FTP_PORT);
      //给session对象设置密码参数也就是你的服务器访问的密码
      session.setPassword(FTP_PASSWORD);
      //创建参数
      Properties sshConfig = new Properties();
      //给参数对象赋值，这里解决
      sshConfig.put("StrictHostKeyChecking", "no");
      //这里设置参数给session主要解决把kerberos认证方式去掉，如果不写这一步走到这里你需要向控制台输入你的	    	    kerberos用户名和口令，如果你的项目环境没有涉及到kerberos应该是不用设置
      session.setConfig(
              "PreferredAuthentications",
              "publickey,keyboard-interactive,password");
      //把参数对象给session对象注入
      session.setConfig(sshConfig);
      //打开session连接
      session.connect(15000);
      //使用session对象连接服务器
      Channel channel = session.openChannel("sftp");
      channel.connect();
      sftp = (ChannelSftp) channel;
      //使用ChannelSftp对象进行使用命令
      //进入需要进入的路径
      sftp.cd(FTP_BASEPATH);
      //进行文件上传
      sftp.put(inputStream,newFileName);
      //设置上传成功
      success = true;
    } catch (JSchException e) {
      e.printStackTrace();
    } catch (SftpException e) {
      e.printStackTrace();
    } finally {
      //关闭连接
      if (sftp != null) {
        if (sftp.isConnected()) {
          sftp.disconnect();
        }
      }
      //关闭session
      if (session != null) {
        if (session.isConnected()) {
          session.disconnect();
        }
      }
    }
    //判断是否成功
    if(success){
      //设置返回路径为访问路径（你的服务器访问路径+新文件名）
      path = PATH_PREFIX +newFileName;
    }
    return path;
  }

}
