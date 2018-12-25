package com.lxisoft.crimestopper.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;
import com.lxisoft.crimestopper.domain.enumeration.Status;

/**
 * A DTO for the Complaint entity.
 */
public class ComplaintDTO implements Serializable {

    private Long id;

    private Long userId;

    private String name;

    private String description;

    private Instant time;

    private Instant timeOfIncident;

    @Lob
    private byte[] media;
    private String mediaContentType;

    private Status status;

    private Long votes;

    private Long locationId;

    private Set<DepartmentDTO> departments = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public Instant getTimeOfIncident() {
        return timeOfIncident;
    }

    public void setTimeOfIncident(Instant timeOfIncident) {
        this.timeOfIncident = timeOfIncident;
    }

    public byte[] getMedia() {
        return media;
    }

    public void setMedia(byte[] media) {
        this.media = media;
    }

    public String getMediaContentType() {
        return mediaContentType;
    }

    public void setMediaContentType(String mediaContentType) {
        this.mediaContentType = mediaContentType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getVotes() {
        return votes;
    }

    public void setVotes(Long votes) {
        this.votes = votes;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Set<DepartmentDTO> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<DepartmentDTO> departments) {
        this.departments = departments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ComplaintDTO complaintDTO = (ComplaintDTO) o;
        if (complaintDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), complaintDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ComplaintDTO{" +
            "id=" + getId() +
            ", userId=" + getUserId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", time='" + getTime() + "'" +
            ", timeOfIncident='" + getTimeOfIncident() + "'" +
            ", media='" + getMedia() + "'" +
            ", status='" + getStatus() + "'" +
            ", votes=" + getVotes() +
            ", location=" + getLocationId() +
            "}";
    }
}
