package com.lxisoft.redalert.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.lxisoft.redalert.domain.enumeration.Gender;

import com.lxisoft.redalert.domain.enumeration.BloodGroup;

import com.lxisoft.redalert.domain.enumeration.Alert;

/**
 * A UserRegistration.
 */
@Entity
@Table(name = "user_registration")
public class UserRegistration implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "jhi_password")
    private String password;

    @Lob
    @Column(name = "profile_pic")
    private byte[] profilePic;

    @Column(name = "profile_pic_content_type")
    private String profilePicContentType;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "blood_group")
    private BloodGroup bloodGroup;

    @Column(name = "contact")
    private Long contact;

    @Column(name = "email")
    private String email;
    
    @Column(name = "user_id")
    private String userId;

    @Column(name = "dob")
    private Instant dob;

    @Column(name = "score")
    private Integer score;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Alert status;

    @Column(name = "created_on")
    private Instant createdOn;

    @OneToMany(mappedBy = "userRegistration")
    private Set<Post> users = new HashSet<>();
    @ManyToMany
    @JoinTable(name = "user_registration_friends",
               joinColumns = @JoinColumn(name = "user_registrations_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "friends_id", referencedColumnName = "id"))
    private Set<UserRegistration> friends = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public UserRegistration userName(String userName) {
        this.userName = userName;
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public UserRegistration password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getProfilePic() {
        return profilePic;
    }

    public UserRegistration profilePic(byte[] profilePic) {
        this.profilePic = profilePic;
        return this;
    }

    public void setProfilePic(byte[] profilePic) {
        this.profilePic = profilePic;
    }

    public String getProfilePicContentType() {
        return profilePicContentType;
    }

    public UserRegistration profilePicContentType(String profilePicContentType) {
        this.profilePicContentType = profilePicContentType;
        return this;
    }

    public void setProfilePicContentType(String profilePicContentType) {
        this.profilePicContentType = profilePicContentType;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserRegistration firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegistration lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public UserRegistration gender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public UserRegistration bloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
        return this;
    }

    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public Long getContact() {
        return contact;
    }

    public UserRegistration contact(Long contact) {
        this.contact = contact;
        return this;
    }

    public void setContact(Long contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public UserRegistration email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Instant getDob() {
        return dob;
    }

    public UserRegistration dob(Instant dob) {
        this.dob = dob;
        return this;
    }

    public void setDob(Instant dob) {
        this.dob = dob;
    }

    public Integer getScore() {
        return score;
    }

    public UserRegistration score(Integer score) {
        this.score = score;
        return this;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Alert getStatus() {
        return status;
    }

    public UserRegistration status(Alert status) {
        this.status = status;
        return this;
    }

    public void setStatus(Alert status) {
        this.status = status;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public UserRegistration createdOn(Instant createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }

    public Set<Post> getUsers() {
        return users;
    }

    public UserRegistration users(Set<Post> posts) {
        this.users = posts;
        return this;
    }

    public UserRegistration addUsers(Post post) {
        this.users.add(post);
        post.setUserRegistration(this);
        return this;
    }

    public UserRegistration removeUsers(Post post) {
        this.users.remove(post);
        post.setUserRegistration(null);
        return this;
    }

    public void setUsers(Set<Post> posts) {
        this.users = posts;
    }

    public Set<UserRegistration> getFriends() {
        return friends;
    }

    public UserRegistration friends(Set<UserRegistration> userRegistrations) {
        this.friends = userRegistrations;
        return this;
    }

    public UserRegistration addFriends(UserRegistration userRegistration) {
        this.friends.add(userRegistration);
        return this;
    }

    public UserRegistration removeFriends(UserRegistration userRegistration) {
        this.friends.remove(userRegistration);
        return this;
    }

    public void setFriends(Set<UserRegistration> userRegistrations) {
        this.friends = userRegistrations;
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
        UserRegistration userRegistration = (UserRegistration) o;
        if (userRegistration.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userRegistration.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserRegistration{" +
            "id=" + getId() +
            ", userName='" + getUserName() + "'" +
            ", userId='" + getUserId() + "'" +
            ", password='" + getPassword() + "'" +
            ", profilePic='" + getProfilePic() + "'" +
            ", profilePicContentType='" + getProfilePicContentType() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", gender='" + getGender() + "'" +
            ", bloodGroup='" + getBloodGroup() + "'" +
            ", contact=" + getContact() +
            ", email='" + getEmail() + "'" +
            ", dob='" + getDob() + "'" +
            ", score=" + getScore() +
            ", status='" + getStatus() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            "}";
    }
}
