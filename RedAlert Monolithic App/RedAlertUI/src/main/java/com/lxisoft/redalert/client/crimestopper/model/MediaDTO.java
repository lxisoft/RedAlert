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
 * MediaDTO
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-02-10T01:11:22.577+05:30[Asia/Calcutta]")

public class MediaDTO   {
  @JsonProperty("complaintId")
  private Long complaintId = null;

  @JsonProperty("file")
  private byte[] file = null;

  @JsonProperty("fileContentType")
  private String fileContentType = null;

  @JsonProperty("id")
  private Long id = null;

  public MediaDTO complaintId(Long complaintId) {
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

  public MediaDTO file(byte[] file) {
    this.file = file;
    return this;
  }

  /**
   * Get file
   * @return file
  **/
  @ApiModelProperty(value = "")

@Pattern(regexp="^(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=)?$") 
  public byte[] getFile() {
    return file;
  }

  public void setFile(byte[] file) {
    this.file = file;
  }

  public MediaDTO fileContentType(String fileContentType) {
    this.fileContentType = fileContentType;
    return this;
  }

  /**
   * Get fileContentType
   * @return fileContentType
  **/
  @ApiModelProperty(value = "")


  public String getFileContentType() {
    return fileContentType;
  }

  public void setFileContentType(String fileContentType) {
    this.fileContentType = fileContentType;
  }

  public MediaDTO id(Long id) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MediaDTO mediaDTO = (MediaDTO) o;
    return Objects.equals(this.complaintId, mediaDTO.complaintId) &&
        Objects.equals(this.file, mediaDTO.file) &&
        Objects.equals(this.fileContentType, mediaDTO.fileContentType) &&
        Objects.equals(this.id, mediaDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(complaintId, file, fileContentType, id);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MediaDTO {\n");
    
    sb.append("    complaintId: ").append(toIndentedString(complaintId)).append("\n");
    sb.append("    file: ").append(toIndentedString(file)).append("\n");
    sb.append("    fileContentType: ").append(toIndentedString(fileContentType)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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

