package com.mxd.cisp.dao;

import com.mxd.cisp.domain.User;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author mxd
 * @Date 2021/12/2 20:30
 */
@Mapper
@Repository
public interface UserMapper {

  /**
   * 根据用户名获取用户
   * @param username
   * @return
   */
  User selectByUsername(String username);

  /**
   * 插入一个用户
   * @param user
   * @return
   */
  int insert(User user);

  /**
   * 根据 search 查询用户数量
   * @param search
   * @return
   */
  int countAll(@Param("search") String search);

  /**
   * 根据search 模糊获取用户
   * @param search
   * @return
   */
  List<User> getUsers(@Param("search") String search);

  /**
   * 修改用户信息
   * @param user
   */
  void updateUser(User user);

  /**
   * 根据id获取用户
   * @param id
   * @return
   */
  User getUserById(Integer id);

  void deleteById(Integer id);
}
