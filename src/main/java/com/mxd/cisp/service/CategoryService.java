package com.mxd.cisp.service;

import com.mxd.cisp.domain.Category;

import java.util.List;

/**
 * @Author mxd
 * @Date 2021/12/2 20:30
 */
public interface CategoryService {

  /**
   * 获取帖子类型
   * @return
   */
  List<Category> getAllCategory();

  /**
   * 添加帖子的类别
   * @param category
   */
  void create(Category category);

  /**
   * 根据id删除帖子的类别
   * @param id
   */
  void deleteById(Integer id);

  /**
   * 修改帖子的类别
   * @param category
   */
  void updateById(Category category);
}
