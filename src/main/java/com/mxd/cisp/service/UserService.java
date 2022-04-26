package com.mxd.cisp.service;

import com.mxd.cisp.domain.User;

import java.util.List;
/**
 * @Author mxd
 * @Date 2021/12/2 20:30
 */
public interface UserService {

  /**
   * 登录校验，判断用户是否存在且密码正确
   * @param user
   * @return
   */
  User loginCheck(User user);

  /**
   * 用户注册
   * @param user
   */
  void register(User user);

  /**
   * 根据用户判断用户是否存在
   * @param username
   * @return
   */
  Boolean isUsernameExist(String username);

  /**
   * 根据用户名获取用户
   * @param username
   * @return
   */
  User getUserByUsername(String username);

  /**
   * 根据 search 查询用户数量
   * @param search
   * @return
   */
  int countAll(String search);

  /**
   * 根据search 模糊获取用户
   * @param search
   * @return
   */
  List<User> getUsers(String search);

  /**
   * 修改用户线信息
   * @param user
   */
  void updateUser(User user);

  /**
   * 根据id获取用户
   * @param id
   * @return
   */
  User getUserById(Integer id);

  /**
   * 根据id删除用户
   * @param id
   */
  void deleteById(Integer id);
}
