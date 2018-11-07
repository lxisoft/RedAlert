package com.lxisoft.redalert.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the LeaderBoard entity.
 */
public class LeaderBoardDTO implements Serializable {

    private Long id;

    private String userName;

    private Integer score;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LeaderBoardDTO leaderBoardDTO = (LeaderBoardDTO) o;
        if (leaderBoardDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), leaderBoardDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LeaderBoardDTO{" +
            "id=" + getId() +
            ", userName='" + getUserName() + "'" +
            ", score=" + getScore() +
            "}";
    }
}
