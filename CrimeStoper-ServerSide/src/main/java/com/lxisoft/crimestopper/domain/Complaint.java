package com.lxisoft.crimestopper.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lxisoft.crimestopper.domain.enumeration.Status;

/**
 * A Complaint. 
 */
@Entity
@Table(name = "complaint")
public class Complaint implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "jhi_time")
    private Instant time;

    @Column(name = "time_of_incident")
    private Instant timeOfIncident;

    @Lob
    @Column(name = "media")
    private byte[] media;

    @Column(name = "media_content_type")
    private String mediaContentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "votes")
    private Long votes;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Location location;

    @ManyToMany
    @JoinTable(name = "complaint_department",
               joinColumns = @JoinColumn(name = "complaints_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "departments_id", referencedColumnName = "id"))
    private Set<Department> departments = new HashSet<>();
    
 

	// jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public Complaint userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public Complaint name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Complaint description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getTime() {
        return time;
    }

    public Complaint time(Instant time) {
        this.time = time;
        return this;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public Instant getTimeOfIncident() {
        return timeOfIncident;
    }

    public Complaint timeOfIncident(Instant timeOfIncident) {
        this.timeOfIncident = timeOfIncident;
        return this;
    }

    public void setTimeOfIncident(Instant timeOfIncident) {
        this.timeOfIncident = timeOfIncident;
    }

    public byte[] getMedia() {
        return media;
    }

    public Complaint media(byte[] media) {
        this.media = media;
        return this;
    }

    public void setMedia(byte[] media) {
        this.media = media;
    }

    public String getMediaContentType() {
        return mediaContentType;
    }

    public Complaint mediaContentType(String mediaContentType) {
        this.mediaContentType = mediaContentType;
        return this;
    }

    public void setMediaContentType(String mediaContentType) {
        this.mediaContentType = mediaContentType;
    }

    public Status getStatus() {
        return status;
    }

    public Complaint status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getVotes() {
        return votes;
    }

    public Complaint votes(Long votes) {
        this.votes = votes;
        return this;
    }

    public void setVotes(Long votes) {
        this.votes = votes;
    }

    public Location getLocation() {
        return location;
    }

    public Complaint location(Location location) {
        this.location = location;
        return this;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public Complaint departments(Set<Department> departments) {
        this.departments = departments;
        return this;
    }

    public Complaint addDepartment(Department department) {
        this.departments.add(department);
        department.getComplaints().add(this);
        return this;
    }

    public Complaint removeDepartment(Department department) {
        this.departments.remove(department);
        department.getComplaints().remove(this);
        return this;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Complaint complaint = (Complaint) o;
        if (complaint.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), complaint.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Complaint{" +
            "id=" + getId() +
            ", userId=" + getUserId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", time='" + getTime() + "'" +
            ", timeOfIncident='" + getTimeOfIncident() + "'" +
            ", media='" + getMedia() + "'" +
            ", mediaContentType='" + getMediaContentType() + "'" +
            ", status='" + getStatus() + "'" +
            ", votes=" + getVotes() +
            "}";
    }
}
