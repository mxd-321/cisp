package com.mxd.cisp.dao;

import com.mxd.cisp.domain.Article;
import com.mxd.cisp.vo.ArticleVO;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author mxd
 * @Date 2021/12/2 20:30
 */
@Repository
@Mapper
public interface ArticleMapper {

  /**
   * 插入一个帖子
   * @param article
   */
  void insert(Article article);

  /**
   * 获取第 page 页的帖子
   * @param page
   * @return
   */
  List<ArticleVO> getIndexArticles(@Param("page") Integer page);

  /**
   * 获取帖子总数
   * @return
   */
  int countIndexArticles();

  /**
   * 根据id获取帖子
   * @param id
   * @return
   */
  ArticleVO getArticleById(Integer id);

  /**
   * 根据用户id获取该用户发表的帖子
   * @param userId
   * @return
   */
  List<ArticleVO> getArticlesByUserId(Integer userId);

  /**
   * 查询帖子数量
   * @return
   */
  int countAllArticle();

  /**
   * 获取某一类别的帖子
   * @param categoryId
   * @param page
   * @return
   */
  List<ArticleVO> getArticleByCategoryId(Integer categoryId, Integer page);

  /**
   * 获取某一类别下的帖子数量
   * @return
   */
  int countArticleByCateId();

  /**
   * 修改帖子
   * @param article
   */
  void updateById(Article article);

  /**
   * 根据id删除帖子
   * @param id
   */
  void deleteById(Integer id);

  /**
   * 修改帖子状态，是否置顶
   * @param id
   * @param top
   */
  void updateTopStatus(Integer id, String top);

  /**
   * 获取顶置帖子
   * @return
   */
  List<ArticleVO> getTopArticle();

  /**
   * 根据类别获取帖子
   * @param categoryId
   * @param startTime
   * @param endTime
   * @param keyword
   * @return
   */
  List<ArticleVO> searchArticle(Integer categoryId, String startTime, String endTime,
    String keyword);

  /**
   * 获取热帖
   * @param startDate
   * @param endDate
   * @return
   */
  List<Article> getHotArticles(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

  /**
   * 使评论数加 1
   * @param id
   */
  void addComment(Integer id);

  /**
   * 使评论数减 1
   * @param id
   */
  void deleteComment(Integer id);
}
