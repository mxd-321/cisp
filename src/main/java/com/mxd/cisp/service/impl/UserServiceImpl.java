package com.mxd.cisp.service.impl;

import com.mxd.cisp.dao.UserMapper;
import com.mxd.cisp.domain.User;
import com.mxd.cisp.utils.PasswordUtil;
import com.mxd.cisp.service.UserService;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @Author mxd
 * @Date 2021/12/2 20:30
 */
@Service
public class UserServiceImpl implements UserService {

  @Resource
  UserMapper userMapper;

  @Override
  public User loginCheck(User user) {
    // 根据用户名获取用户
    User tmpUser = userMapper.selectByUsername(user.getUsername());
    if (tmpUser == null) {
      return null;
    }
    boolean isLogin = PasswordUtil.bryptPwd(user.getPassword()).equals(tmpUser.getPassword());
    if (isLogin) {
      return tmpUser;
    } else {
      return null;
    }
  }

  @Override
  public void register(User user) {
    // 对密码进行加密
    user.setPassword(PasswordUtil.bryptPwd(user.getPassword()));
    userMapper.insert(user);
  }

  @Override
  public Boolean isUsernameExist(String username) {
    return userMapper.selectByUsername(username) != null;
  }

  @Override
  public User getUserByUsername(String username) {
    return userMapper.selectByUsername(username);
  }

  @Override
  public int countAll(String search) {
    return userMapper.countAll(search);
  }

  @Override
  public List<User> getUsers(String search) {
    return userMapper.getUsers(search);
  }

  @Override
  public void updateUser(User user) {
    userMapper.updateUser(user);
  }

  @Override
  public User getUserById(Integer id) {
    return userMapper.getUserById(id);
  }

  @Override
  public void deleteById(Integer id) {
    userMapper.deleteById(id);
  }
}
