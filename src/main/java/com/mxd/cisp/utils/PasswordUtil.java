package com.mxd.cisp.utils;

import org.springframework.util.DigestUtils;
import java.nio.charset.StandardCharsets;

/**
 * @Author mxd
 * @Date 2021/12/2 20:30
 */
public class PasswordUtil {

  /**
   * 加密密码
   */
  public static String bryptPwd(String pwd) {
    return DigestUtils.md5DigestAsHex(pwd.getBytes(StandardCharsets.UTF_8));

  }

  /**
   * MD5和位运算移位加密
   * @param content
   * @return
   */
  public static String MD5(String content) {
    try {
      java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
      byte[] array = md.digest(content.getBytes());
      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < array.length; ++i) {
        sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100), 1, 3);
      }
      return sb.toString();
    } catch (java.security.NoSuchAlgorithmException e) {
    }
    return null;
  }
}
