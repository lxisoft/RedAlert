package com.lxisoft.redalert.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A LeaderBoard.
 */
@Entity
@Table(name = "leader_board")
public class LeaderBoard implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "score")
    private Integer score;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public LeaderBoard userName(String userName) {
        this.userName = userName;
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getScore() {
        return score;
    }

    public LeaderBoard score(Integer score) {
        this.score = score;
        return this;
    }

    public void setScore(Integer score) {
        this.score = score;
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
        LeaderBoard leaderBoard = (LeaderBoard) o;
        if (leaderBoard.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), leaderBoard.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LeaderBoard{" +
            "id=" + getId() +
            ", userName='" + getUserName() + "'" +
            ", score=" + getScore() +
            "}";
    }
}
