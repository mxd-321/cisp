package com.mxd.cisp.service;

import com.mxd.cisp.domain.Article;
import com.mxd.cisp.vo.ArticleVO;

import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @Author mxd
 * @Date 2021/12/2 20:30
 */
@Service
public interface ArticleService {

  /**
   * 新建帖子
   * @param article
   */
  void create(Article article);

  /**
   * 获取第 page 页的帖子
   * @param page
   * @return
   */
  List<ArticleVO> getIndexArticles(Integer page);

  /**
   * 获取帖子总数
   * @return
   */
  int countIndexArticles();

  /**
   * 根据id获取帖子详情
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
   * 获取某一类别的帖子
   * @param category
   * @param page
   * @return
   */
  List<ArticleVO> getArticlesByCategoryId(Integer category, Integer page);

  /**
   * 获取某一类别下的帖子数量
   * @return
   */
  int countArticleByCateId();

  /**
   * 查询帖子数量
   * @return
   */
  int countAllArticle();

  /**
   * 修改帖子
   * @param article
   */
  void update(Article article);

  /**
   * 根据id删除帖子
   * @param id
   */
  void delete(Integer id);

  /**
   * 根据id置顶帖子
   * @param id
   */
  void setTopStatus(Integer id);

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
   * @return
   */
  List<Article> getHotArticles();
}
