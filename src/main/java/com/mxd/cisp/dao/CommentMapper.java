package com.mxd.cisp.dao;

import com.mxd.cisp.domain.Comment;
import com.mxd.cisp.vo.CommentVO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author mxd
 * @Date 2021/12/2 20:30
 */
@Repository
@Mapper
public interface CommentMapper {

  /**
   * 创建评论
   * @param comment
   */
  void create(Comment comment);

  /**
   * 根据帖子id获取帖子的评论数
   * @param articleId
   * @return
   */
  List<CommentVO> getCommentByArticleId(Integer articleId);

  void delete(Comment comment);

  Comment getById(Integer id);

  /**
   * 根据帖子id删除评论
   * @param articleId
   */
  void deleteByArticleId(Integer articleId);
}
