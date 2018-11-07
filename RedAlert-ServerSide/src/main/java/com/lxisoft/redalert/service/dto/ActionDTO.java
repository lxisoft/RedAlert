package com.lxisoft.redalert.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;
import com.lxisoft.redalert.domain.enumeration.Reaction;

/**
 * A DTO for the Action entity.
 */
public class ActionDTO implements Serializable {

    private Long id;

    private Integer userId;

    private String userName;

    private String description;

    private Instant takenOn;

    private Reaction reaction;

    private Long postId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getTakenOn() {
        return takenOn;
    }

    public void setTakenOn(Instant takenOn) {
        this.takenOn = takenOn;
    }

    public Reaction getReaction() {
        return reaction;
    }

    public void setReaction(Reaction reaction) {
        this.reaction = reaction;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ActionDTO actionDTO = (ActionDTO) o;
        if (actionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), actionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ActionDTO{" +
            "id=" + getId() +
            ", userId=" + getUserId() +
            ", userName='" + getUserName() + "'" +
            ", description='" + getDescription() + "'" +
            ", takenOn='" + getTakenOn() + "'" +
            ", reaction='" + getReaction() + "'" +
            ", post=" + getPostId() +
            "}";
    }
}
