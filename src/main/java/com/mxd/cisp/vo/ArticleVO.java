package com.mxd.cisp.vo;

import com.mxd.cisp.domain.Article;

/**
 * @Author mxd
 * @Date 2021/12/2 20:30
 */

public class ArticleVO extends Article {

  private String nickname;
  private String category;
  private String signature;
  private Integer commentCount;
  private String avatar;

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getSignature() {
    return signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
  }

  @Override
  public Integer getCommentCount() {
    return commentCount;
  }

  @Override
  public void setCommentCount(Integer commentCount) {
    this.commentCount = commentCount;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  @Override
  public String toString() {
    return "ArticleVO{" +
            "nickname='" + nickname + '\'' +
            ", category='" + category + '\'' +
            ", signature='" + signature + '\'' +
            ", commentCount=" + commentCount +
            ", avatar='" + avatar + '\'' +
            '}';
  }
}
