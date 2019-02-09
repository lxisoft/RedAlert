package com.lxisoft.redalert.client.crimestopper.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * UserResponseDTO
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-02-09T12:49:50.445+05:30[Asia/Calcutta]")

public class UserResponseDTO   {
  @JsonProperty("commentId")
  private Long commentId = null;

  @JsonProperty("complaintId")
  private Long complaintId = null;

  /**
   * Gets or Sets flag
   */
  public enum FlagEnum {
    LIKE("LIKE"),
    
    DISLIKE("DISLIKE");

    private String value;

    FlagEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static FlagEnum fromValue(String text) {
      for (FlagEnum b : FlagEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("flag")
  private FlagEnum flag = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("replyId")
  private Long replyId = null;

  @JsonProperty("userId")
  private Long userId = null;

  public UserResponseDTO commentId(Long commentId) {
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

  public UserResponseDTO complaintId(Long complaintId) {
    this.complaintId = complaintId;
    return this;
  }

  /**
   * Get complaintId
   * @return complaintId
  **/
  @ApiModelProperty(value = "")


  public Long getComplaintId() {
    return complaintId;
  }

  public void setComplaintId(Long complaintId) {
    this.complaintId = complaintId;
  }

  public UserResponseDTO flag(FlagEnum flag) {
    this.flag = flag;
    return this;
  }

  /**
   * Get flag
   * @return flag
  **/
  @ApiModelProperty(value = "")


  public FlagEnum getFlag() {
    return flag;
  }

  public void setFlag(FlagEnum flag) {
    this.flag = flag;
  }

  public UserResponseDTO id(Long id) {
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

  public UserResponseDTO replyId(Long replyId) {
    this.replyId = replyId;
    return this;
  }

  /**
   * Get replyId
   * @return replyId
  **/
  @ApiModelProperty(value = "")


  public Long getReplyId() {
    return replyId;
  }

  public void setReplyId(Long replyId) {
    this.replyId = replyId;
  }

  public UserResponseDTO userId(Long userId) {
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
    UserResponseDTO userResponseDTO = (UserResponseDTO) o;
    return Objects.equals(this.commentId, userResponseDTO.commentId) &&
        Objects.equals(this.complaintId, userResponseDTO.complaintId) &&
        Objects.equals(this.flag, userResponseDTO.flag) &&
        Objects.equals(this.id, userResponseDTO.id) &&
        Objects.equals(this.replyId, userResponseDTO.replyId) &&
        Objects.equals(this.userId, userResponseDTO.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(commentId, complaintId, flag, id, replyId, userId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserResponseDTO {\n");
    
    sb.append("    commentId: ").append(toIndentedString(commentId)).append("\n");
    sb.append("    complaintId: ").append(toIndentedString(complaintId)).append("\n");
    sb.append("    flag: ").append(toIndentedString(flag)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    replyId: ").append(toIndentedString(replyId)).append("\n");
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

