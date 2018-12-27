package com.lxisoft.redalert.client.crime_stopper.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.lxisoft.redalert.client.crime_stopper.model.DepartmentDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ComplaintDTO
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-12-27T09:54:08.957000800+05:30[Asia/Calcutta]")

public class ComplaintDTO   {
  @JsonProperty("departments")
  @Valid
  private List<DepartmentDTO> departments = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("locationId")
  private Long locationId = null;

  @JsonProperty("media")
  private byte[] media = null;

  @JsonProperty("mediaContentType")
  private String mediaContentType = null;

  @JsonProperty("name")
  private String name = null;

  /**
   * Gets or Sets status
   */
  public enum StatusEnum {
    SEEN("SEEN"),
    
    ACTION_TAKEN("ACTION_TAKEN");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String text) {
      for (StatusEnum b : StatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("status")
  private StatusEnum status = null;

  @JsonProperty("time")
  private OffsetDateTime time = null;

  @JsonProperty("timeInString")
  private String timeInString = null;

  @JsonProperty("timeOfIncident")
  private OffsetDateTime timeOfIncident = null;

  @JsonProperty("userId")
  private Long userId = null;

  @JsonProperty("votes")
  private Long votes = null;

  public ComplaintDTO departments(List<DepartmentDTO> departments) {
    this.departments = departments;
    return this;
  }

  public ComplaintDTO addDepartmentsItem(DepartmentDTO departmentsItem) {
    if (this.departments == null) {
      this.departments = new ArrayList<DepartmentDTO>();
    }
    this.departments.add(departmentsItem);
    return this;
  }

  /**
   * Get departments
   * @return departments
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<DepartmentDTO> getDepartments() {
    return departments;
  }

  public void setDepartments(List<DepartmentDTO> departments) {
    this.departments = departments;
  }

  public ComplaintDTO description(String description) {
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

  public ComplaintDTO id(Long id) {
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

  public ComplaintDTO locationId(Long locationId) {
    this.locationId = locationId;
    return this;
  }

  /**
   * Get locationId
   * @return locationId
  **/
  @ApiModelProperty(value = "")


  public Long getLocationId() {
    return locationId;
  }

  public void setLocationId(Long locationId) {
    this.locationId = locationId;
  }

  public ComplaintDTO media(byte[] media) {
    this.media = media;
    return this;
  }

  /**
   * Get media
   * @return media
  **/
  @ApiModelProperty(value = "")

@Pattern(regexp="^(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=)?$") 
  public byte[] getMedia() {
    return media;
  }

  public void setMedia(byte[] media) {
    this.media = media;
  }

  public ComplaintDTO mediaContentType(String mediaContentType) {
    this.mediaContentType = mediaContentType;
    return this;
  }

  /**
   * Get mediaContentType
   * @return mediaContentType
  **/
  @ApiModelProperty(value = "")


  public String getMediaContentType() {
    return mediaContentType;
  }

  public void setMediaContentType(String mediaContentType) {
    this.mediaContentType = mediaContentType;
  }

  public ComplaintDTO name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(value = "")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ComplaintDTO status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  **/
  @ApiModelProperty(value = "")


  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public ComplaintDTO time(OffsetDateTime time) {
    this.time = time;
    return this;
  }

  /**
   * Get time
   * @return time
  **/
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getTime() {
    return time;
  }

  public void setTime(OffsetDateTime time) {
    this.time = time;
  }

  public ComplaintDTO timeInString(String timeInString) {
    this.timeInString = timeInString;
    return this;
  }

  /**
   * Get timeInString
   * @return timeInString
  **/
  @ApiModelProperty(value = "")


  public String getTimeInString() {
    return timeInString;
  }

  public void setTimeInString(String timeInString) {
    this.timeInString = timeInString;
  }

  public ComplaintDTO timeOfIncident(OffsetDateTime timeOfIncident) {
    this.timeOfIncident = timeOfIncident;
    return this;
  }

  /**
   * Get timeOfIncident
   * @return timeOfIncident
  **/
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getTimeOfIncident() {
    return timeOfIncident;
  }

  public void setTimeOfIncident(OffsetDateTime timeOfIncident) {
    this.timeOfIncident = timeOfIncident;
  }

  public ComplaintDTO userId(Long userId) {
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

  public ComplaintDTO votes(Long votes) {
    this.votes = votes;
    return this;
  }

  /**
   * Get votes
   * @return votes
  **/
  @ApiModelProperty(value = "")


  public Long getVotes() {
    return votes;
  }

  public void setVotes(Long votes) {
    this.votes = votes;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ComplaintDTO complaintDTO = (ComplaintDTO) o;
    return Objects.equals(this.departments, complaintDTO.departments) &&
        Objects.equals(this.description, complaintDTO.description) &&
        Objects.equals(this.id, complaintDTO.id) &&
        Objects.equals(this.locationId, complaintDTO.locationId) &&
        Objects.equals(this.media, complaintDTO.media) &&
        Objects.equals(this.mediaContentType, complaintDTO.mediaContentType) &&
        Objects.equals(this.name, complaintDTO.name) &&
        Objects.equals(this.status, complaintDTO.status) &&
        Objects.equals(this.time, complaintDTO.time) &&
        Objects.equals(this.timeInString, complaintDTO.timeInString) &&
        Objects.equals(this.timeOfIncident, complaintDTO.timeOfIncident) &&
        Objects.equals(this.userId, complaintDTO.userId) &&
        Objects.equals(this.votes, complaintDTO.votes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(departments, description, id, locationId, media, mediaContentType, name, status, time, timeInString, timeOfIncident, userId, votes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ComplaintDTO {\n");
    
    sb.append("    departments: ").append(toIndentedString(departments)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    locationId: ").append(toIndentedString(locationId)).append("\n");
    sb.append("    media: ").append(toIndentedString(media)).append("\n");
    sb.append("    mediaContentType: ").append(toIndentedString(mediaContentType)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    time: ").append(toIndentedString(time)).append("\n");
    sb.append("    timeInString: ").append(toIndentedString(timeInString)).append("\n");
    sb.append("    timeOfIncident: ").append(toIndentedString(timeOfIncident)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    votes: ").append(toIndentedString(votes)).append("\n");
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

