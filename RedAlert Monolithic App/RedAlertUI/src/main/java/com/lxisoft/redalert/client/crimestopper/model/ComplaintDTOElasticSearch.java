package com.lxisoft.redalert.client.crimestopper.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ComplaintDTOElasticSearch{

	@JsonProperty("id")
    private Long id;
	@JsonProperty("subject")
    private String subject;
	@JsonProperty("description")
    private String description;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
    
 
}
