package com.lxisoft.redalert.client.crimestopper.model;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-02-10T01:11:22.577+05:30[Asia/Calcutta]")
public class UserDTOElasticSearch {

	@JsonProperty("id")
    private Long id;
	@JsonProperty("firstName")
    private String firstName;
	@JsonProperty("lastName")
    private String lastName;
	@JsonProperty("email")
    private String email;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
    
    
	
}
