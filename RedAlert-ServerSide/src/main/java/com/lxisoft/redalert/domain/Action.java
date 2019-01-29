package com.lxisoft.redalert.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.lxisoft.redalert.domain.enumeration.Reaction;

/**
 * A Action.
 */
@Entity
@Table(name = "action")
public class Action implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "description")
    private String description;

    @Column(name = "taken_on")
    private Instant takenOn;

    @Enumerated(EnumType.STRING)
    @Column(name = "reaction")
    private Reaction reaction;

    @Column(name = "approval")
    private Boolean approval;

    @ManyToOne
    @JsonIgnoreProperties("actions")
    private Post post;

    @OneToMany(mappedBy = "action")
    private Set<Report> reports = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public Action userId(String userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public Action userName(String userName) {
        this.userName = userName;
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDescription() {
        return description;
    }

    public Action description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getTakenOn() {
        return takenOn;
    }

    public Action takenOn(Instant takenOn) {
        this.takenOn = takenOn;
        return this;
    }

    public void setTakenOn(Instant takenOn) {
        this.takenOn = takenOn;
    }

    public Reaction getReaction() {
        return reaction;
    }

    public Action reaction(Reaction reaction) {
        this.reaction = reaction;
        return this;
    }

    public void setReaction(Reaction reaction) {
        this.reaction = reaction;
    }

    public Boolean isApproval() {
        return approval;
    }

    public Action approval(Boolean approval) {
        this.approval = approval;
        return this;
    }

    public void setApproval(Boolean approval) {
        this.approval = approval;
    }

    public Post getPost() {
        return post;
    }

    public Action post(Post post) {
        this.post = post;
        return this;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Set<Report> getReports() {
        return reports;
    }

    public Action reports(Set<Report> reports) {
        this.reports = reports;
        return this;
    }

    public Action addReports(Report report) {
        this.reports.add(report);
        report.setAction(this);
        return this;
    }

    public Action removeReports(Report report) {
        this.reports.remove(report);
        report.setAction(null);
        return this;
    }

    public void setReports(Set<Report> reports) {
        this.reports = reports;
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
        Action action = (Action) o;
        if (action.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), action.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Action{" +
            "id=" + getId() +
            ", userId='" + getUserId() + "'" +
            ", userName='" + getUserName() + "'" +
            ", description='" + getDescription() + "'" +
            ", takenOn='" + getTakenOn() + "'" +
            ", reaction='" + getReaction() + "'" +
            ", approval='" + isApproval() + "'" +
            "}";
    }
}
