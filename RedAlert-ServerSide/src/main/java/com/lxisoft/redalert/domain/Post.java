package com.lxisoft.redalert.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.lxisoft.redalert.domain.enumeration.Alert;

/**
 * A Post.
 */
@Entity
@Table(name = "post")
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "alert_level")
    private Alert alertLevel;

    @Column(name = "description")
    private String description;

    @Column(name = "created_on")
    private Instant createdOn;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @ManyToOne
    @JsonIgnoreProperties("users")
    private UserRegistration userRegistration;

    @OneToMany(mappedBy = "post")
    private Set<Action> actions = new HashSet<>();
    @OneToMany(mappedBy = "post")
    private Set<Report> posts = new HashSet<>();
    @OneToMany(mappedBy = "post")
    private Set<Media> attatchments = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Alert getAlertLevel() {
        return alertLevel;
    }

    public Post alertLevel(Alert alertLevel) {
        this.alertLevel = alertLevel;
        return this;
    }

    public void setAlertLevel(Alert alertLevel) {
        this.alertLevel = alertLevel;
    }

    public String getDescription() {
        return description;
    }

    public Post description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public Post createdOn(Instant createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }

    public Boolean isActive() {
        return active;
    }

    public Post active(Boolean active) {
        this.active = active;
        return this;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getLatitude() {
        return latitude;
    }

    public Post latitude(String latitude) {
        this.latitude = latitude;
        return this;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public Post longitude(String longitude) {
        this.longitude = longitude;
        return this;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public UserRegistration getUserRegistration() {
        return userRegistration;
    }

    public Post userRegistration(UserRegistration userRegistration) {
        this.userRegistration = userRegistration;
        return this;
    }

    public void setUserRegistration(UserRegistration userRegistration) {
        this.userRegistration = userRegistration;
    }

    public Set<Action> getActions() {
        return actions;
    }

    public Post actions(Set<Action> actions) {
        this.actions = actions;
        return this;
    }

    public Post addActions(Action action) {
        this.actions.add(action);
        action.setPost(this);
        return this;
    }

    public Post removeActions(Action action) {
        this.actions.remove(action);
        action.setPost(null);
        return this;
    }

    public void setActions(Set<Action> actions) {
        this.actions = actions;
    }

    public Set<Report> getPosts() {
        return posts;
    }

    public Post posts(Set<Report> reports) {
        this.posts = reports;
        return this;
    }

    public Post addPosts(Report report) {
        this.posts.add(report);
        report.setPost(this);
        return this;
    }

    public Post removePosts(Report report) {
        this.posts.remove(report);
        report.setPost(null);
        return this;
    }

    public void setPosts(Set<Report> reports) {
        this.posts = reports;
    }

    public Set<Media> getAttatchments() {
        return attatchments;
    }

    public Post attatchments(Set<Media> media) {
        this.attatchments = media;
        return this;
    }

    public Post addAttatchments(Media media) {
        this.attatchments.add(media);
        media.setPost(this);
        return this;
    }

    public Post removeAttatchments(Media media) {
        this.attatchments.remove(media);
        media.setPost(null);
        return this;
    }

    public void setAttatchments(Set<Media> media) {
        this.attatchments = media;
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
        Post post = (Post) o;
        if (post.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), post.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Post{" +
            "id=" + getId() +
            ", alertLevel='" + getAlertLevel() + "'" +
            ", description='" + getDescription() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", active='" + isActive() + "'" +
            ", latitude='" + getLatitude() + "'" +
            ", longitude='" + getLongitude() + "'" +
            "}";
    }
}
