package com.lxisoft.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Reply entity.
 */
public class ReplyDTO implements Serializable {

    private Long id;

    private Long commentId;

    private String description;

    private Long noOfLikes;

    private Long noOfDislikes;

    private Long commentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getNoOfLikes() {
        return noOfLikes;
    }

    public void setNoOfLikes(Long noOfLikes) {
        this.noOfLikes = noOfLikes;
    }

    public Long getNoOfDislikes() {
        return noOfDislikes;
    }

    public void setNoOfDislikes(Long noOfDislikes) {
        this.noOfDislikes = noOfDislikes;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ReplyDTO replyDTO = (ReplyDTO) o;
        if (replyDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), replyDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ReplyDTO{" +
            "id=" + getId() +
            ", commentId=" + getCommentId() +
            ", description='" + getDescription() + "'" +
            ", noOfLikes=" + getNoOfLikes() +
            ", noOfDislikes=" + getNoOfDislikes() +
            ", comment=" + getCommentId() +
            "}";
    }
}
