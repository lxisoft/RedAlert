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
 * CommentDTO
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-02-09T12:49:50.445+05:30[Asia/Calcutta]")

public class CommentDTO   {
  @JsonProperty("complaintId")
  private Long complaintId = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("noOfDislikes")
  private Long noOfDislikes = null;

  @JsonProperty("noOfLikes")
  private Long noOfLikes = null;

  @JsonProperty("noOfReplies")
  private Long noOfReplies = null;

  @JsonProperty("userId")
  private Long userId = null;

  public CommentDTO complaintId(Long complaintId) {
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

  public CommentDTO description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(value = "")


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public CommentDTO id(Long id) {
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

  public CommentDTO noOfDislikes(Long noOfDislikes) {
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

  public CommentDTO noOfLikes(Long noOfLikes) {
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

  public CommentDTO noOfReplies(Long noOfReplies) {
    this.noOfReplies = noOfReplies;
    return this;
  }

  /**
   * Get noOfReplies
   * @return noOfReplies
  **/
  @ApiModelProperty(value = "")


  public Long getNoOfReplies() {
    return noOfReplies;
  }

  public void setNoOfReplies(Long noOfReplies) {
    this.noOfReplies = noOfReplies;
  }

  public CommentDTO userId(Long userId) {
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
    CommentDTO commentDTO = (CommentDTO) o;
    return Objects.equals(this.complaintId, commentDTO.complaintId) &&
        Objects.equals(this.description, commentDTO.description) &&
        Objects.equals(this.id, commentDTO.id) &&
        Objects.equals(this.noOfDislikes, commentDTO.noOfDislikes) &&
        Objects.equals(this.noOfLikes, commentDTO.noOfLikes) &&
        Objects.equals(this.noOfReplies, commentDTO.noOfReplies) &&
        Objects.equals(this.userId, commentDTO.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(complaintId, description, id, noOfDislikes, noOfLikes, noOfReplies, userId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CommentDTO {\n");
    
    sb.append("    complaintId: ").append(toIndentedString(complaintId)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    noOfDislikes: ").append(toIndentedString(noOfDislikes)).append("\n");
    sb.append("    noOfLikes: ").append(toIndentedString(noOfLikes)).append("\n");
    sb.append("    noOfReplies: ").append(toIndentedString(noOfReplies)).append("\n");
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

