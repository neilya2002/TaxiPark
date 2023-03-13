package com.example.TaxiPark.model;

public class CommentDto {

  private String comment;
  private String userName;

  public CommentDto(String comment, String userName) {
    this.comment = comment;
    this.userName = userName;
  }

  public CommentDto() {
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }
}
