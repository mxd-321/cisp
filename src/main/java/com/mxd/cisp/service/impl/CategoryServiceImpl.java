package com.mxd.cisp.service.impl;

import com.mxd.cisp.dao.CategoryMapper;
import com.mxd.cisp.domain.Category;
import com.mxd.cisp.service.CategoryService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @Author mxd
 * @Date 2021/12/2 20:30
 */
@Service
public class CategoryServiceImpl implements CategoryService {

  @Resource
  CategoryMapper categoryMapper;

  @Override
  public List<Category> getAllCategory() {
    return categoryMapper.getAllCategory();
  }

  @Override
  public void create(Category category) {
    categoryMapper.create(category);
  }

  @Override
  public void deleteById(Integer id) {
    categoryMapper.deleteById(id);
  }

  @Override
  public void updateById(Category category) {
    categoryMapper.updateById(category);
  }
}
