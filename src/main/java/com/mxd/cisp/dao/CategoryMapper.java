package com.mxd.cisp.dao;

import com.mxd.cisp.domain.Category;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author mxd
 * @Date 2021/12/2 20:30
 */
@Repository
@Mapper
public interface CategoryMapper {

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
   * 修改帖子的列表
   * @param category
   */
  void updateById(Category category);
}
