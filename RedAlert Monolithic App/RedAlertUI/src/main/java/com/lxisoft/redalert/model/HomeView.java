package com.lxisoft.redalert.model;

import java.util.HashSet;
import java.util.List;

import com.lxisoft.redalert.client.crimestopper.model.ComplaintDTO;

public class HomeView {

	private List<ComplaintDTO> complaints;
	
	public HomeView(List<ComplaintDTO> complaints) {
		
		this.complaints=complaints;
	}

	public List<ComplaintDTO> getComplaints() {
		return complaints;
	}

	public void setComplaints(List<ComplaintDTO> complaints) {
		this.complaints = complaints;
	}

	
	
	
	
	
	
}
