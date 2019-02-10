package com.lxisoft.redalert.client.crimestopper.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ReplyDTO
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-02-10T01:11:22.577+05:30[Asia/Calcutta]")

public class ReplyDTO   {
  @JsonProperty("commentId")
  private Long commentId = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("noOfDislikes")
  private Long noOfDislikes = null;

  @JsonProperty("noOfLikes")
  private Long noOfLikes = null;

  @JsonProperty("userId")
  private Long userId = null;

  public ReplyDTO commentId(Long commentId) {
    this.commentId = commentId;
    return this;
  }

  /**
   * Get commentId
   * @return commentId
  **/
  @ApiModelProperty(value = "")


  public Long getCommentId() {
    return commentId;
  }

  public void setCommentId(Long commentId) {
    this.commentId = commentId;
  }

  public ReplyDTO id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ReplyDTO noOfDislikes(Long noOfDislikes) {
    this.noOfDislikes = noOfDislikes;
    return this;
  }

  /**
   * Get noOfDislikes
   * @return noOfDislikes
  **/
  @ApiModelProperty(value = "")


  public Long getNoOfDislikes() {
    return noOfDislikes;
  }

  public void setNoOfDislikes(Long noOfDislikes) {
    this.noOfDislikes = noOfDislikes;
  }

  public ReplyDTO noOfLikes(Long noOfLikes) {
    this.noOfLikes = noOfLikes;
    return this;
  }

  /**
   * Get noOfLikes
   * @return noOfLikes
  **/
  @ApiModelProperty(value = "")


  public Long getNoOfLikes() {
    return noOfLikes;
  }

  public void setNoOfLikes(Long noOfLikes) {
    this.noOfLikes = noOfLikes;
  }

  public ReplyDTO userId(Long userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
  **/
  @ApiModelProperty(value = "")


  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReplyDTO replyDTO = (ReplyDTO) o;
    return Objects.equals(this.commentId, replyDTO.commentId) &&
        Objects.equals(this.id, replyDTO.id) &&
        Objects.equals(this.noOfDislikes, replyDTO.noOfDislikes) &&
        Objects.equals(this.noOfLikes, replyDTO.noOfLikes) &&
        Objects.equals(this.userId, replyDTO.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(commentId, id, noOfDislikes, noOfLikes, userId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReplyDTO {\n");
    
    sb.append("    commentId: ").append(toIndentedString(commentId)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    noOfDislikes: ").append(toIndentedString(noOfDislikes)).append("\n");
    sb.append("    noOfLikes: ").append(toIndentedString(noOfLikes)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

