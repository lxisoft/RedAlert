package com.lxisoft.redalert.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;
import com.lxisoft.redalert.domain.enumeration.Gender;
import com.lxisoft.redalert.domain.enumeration.BloodGroup;
import com.lxisoft.redalert.domain.enumeration.Alert;

/**
 * A DTO for the UserRegistration entity.
 */
public class UserRegistrationDTO implements Serializable {

    private Long id;

    private String userName;

    private String password;

    @Lob
    private byte[] profilePic;
    private String profilePicContentType;

    private String firstName;

    private String lastName;

    private Gender gender;

    private BloodGroup bloodGroup;

    private Long contact;

    private String email;

    private Instant dob;

    private Integer score;

    private Alert status;

    private Instant createdOn;

    private Set<UserRegistrationDTO> friends = new HashSet<>();

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(byte[] profilePic) {
        this.profilePic = profilePic;
    }

    public String getProfilePicContentType() {
        return profilePicContentType;
    }

    public void setProfilePicContentType(String profilePicContentType) {
        this.profilePicContentType = profilePicContentType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public Long getContact() {
        return contact;
    }

    public void setContact(Long contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Instant getDob() {
        return dob;
    }

    public void setDob(Instant dob) {
        this.dob = dob;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Alert getStatus() {
        return status;
    }

    public void setStatus(Alert status) {
        this.status = status;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }
   

    public Set<UserRegistrationDTO> getFriends() {
        return friends;
    }

    public void setFriends(Set<UserRegistrationDTO> userRegistrations) {
        this.friends = userRegistrations;
    }
    

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserRegistrationDTO userRegistrationDTO = (UserRegistrationDTO) o;
        if (userRegistrationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userRegistrationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserRegistrationDTO{" +
            "id=" + getId() +
            ", userName='" + getUserName() + "'" +
            ", password='" + getPassword() + "'" +
            ", profilePic='" + getProfilePic() + "'" +
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
