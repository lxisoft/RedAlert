package com.lxisoft.redalert.client.crime_stopper.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * LocationDTO
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-12-27T09:54:08.957000800+05:30[Asia/Calcutta]")

public class LocationDTO   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("latitude")
  private String latitude = null;

  @JsonProperty("longitutde")
  private String longitutde = null;

  @JsonProperty("name")
  private String name = null;

  public LocationDTO id(Long id) {
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

  public LocationDTO latitude(String latitude) {
    this.latitude = latitude;
    return this;
  }

  /**
   * Get latitude
   * @return latitude
  **/
  @ApiModelProperty(value = "")


  public String getLatitude() {
    return latitude;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  public LocationDTO longitutde(String longitutde) {
    this.longitutde = longitutde;
    return this;
  }

  /**
   * Get longitutde
   * @return longitutde
  **/
  @ApiModelProperty(value = "")


  public String getLongitutde() {
    return longitutde;
  }

  public void setLongitutde(String longitutde) {
    this.longitutde = longitutde;
  }

  public LocationDTO name(String name) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LocationDTO locationDTO = (LocationDTO) o;
    return Objects.equals(this.id, locationDTO.id) &&
        Objects.equals(this.latitude, locationDTO.latitude) &&
        Objects.equals(this.longitutde, locationDTO.longitutde) &&
        Objects.equals(this.name, locationDTO.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, latitude, longitutde, name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LocationDTO {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    latitude: ").append(toIndentedString(latitude)).append("\n");
    sb.append("    longitutde: ").append(toIndentedString(longitutde)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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

