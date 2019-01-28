package com.lxisoft.redalert.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;
import com.lxisoft.redalert.domain.enumeration.Alert;

/**
 * A DTO for the Post entity.
 */
public class PostDTO implements Serializable {

    private Long id;

    private Alert alertLevel;

    private String description;

    private Instant createdOn;

    private Boolean active;

    private String latitude;

    private String longitude;

    private Long userRegistrationId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Alert getAlertLevel() {
        return alertLevel;
    }

    public void setAlertLevel(Alert alertLevel) {
        this.alertLevel = alertLevel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Long getUserRegistrationId() {
        return userRegistrationId;
    }

    public void setUserRegistrationId(Long userRegistrationId) {
        this.userRegistrationId = userRegistrationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PostDTO postDTO = (PostDTO) o;
        if (postDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), postDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PostDTO{" +
            "id=" + getId() +
            ", alertLevel='" + getAlertLevel() + "'" +
            ", description='" + getDescription() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", active='" + isActive() + "'" +
            ", latitude='" + getLatitude() + "'" +
            ", longitude='" + getLongitude() + "'" +
            ", userRegistration=" + getUserRegistrationId() +
            "}";
    }
}
