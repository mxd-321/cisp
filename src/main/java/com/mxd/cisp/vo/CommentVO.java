package com.mxd.cisp.vo;

import com.mxd.cisp.domain.Comment;

/**
 * @Author mxd
 * @Date 2021/12/2 20:30
 */

public class CommentVO extends Comment {

  private String nickname;
  private String avatar;

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  @Override
  public String toString() {
    return "CommentVO{" +
            "nickname='" + nickname + '\'' +
            ", avatar='" + avatar + '\'' +
            '}';
  }
}
