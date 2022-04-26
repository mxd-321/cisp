package com.mxd.cisp.service;

import com.mxd.cisp.domain.Comment;
import com.mxd.cisp.vo.CommentVO;

import java.util.List;

/**
 * @Author mxd
 * @Date 2021/12/2 20:30
 */
public interface CommentService {

  /**
   * 创建评论
   * @param comment
   */
  void create(Comment comment);

  /**
   * 根据帖子id获取帖子评论数
   * @param articleId
   * @return
   */
  List<CommentVO> getCommentByArticleId(Integer articleId);

  /**
   * 查询评论数量
   * @return
   */
  int countAllComment();

  /**
   * 删除评论
   * @param comment
   */
  void delete(Comment comment);
}
