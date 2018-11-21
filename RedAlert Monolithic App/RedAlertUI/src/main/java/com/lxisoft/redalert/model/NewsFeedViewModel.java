package com.lxisoft.redalert.model;

import java.util.ArrayList;

import com.lxisoft.redalert.client.red_alert.model.*;

public class NewsFeedViewModel {
	private UserRegistrationDTO userRegistationDTO;
	private PostDTO postDTO;
	private ActionDTO newActionDTO;
	private ArrayList<ActionDTO> actionDTOList=new ArrayList<ActionDTO>();
	private ArrayList<MediaDTO> mediaDTOList=new ArrayList<MediaDTO>();
	private ArrayList<ReportDTO> reportDTOList=new ArrayList<ReportDTO>();
	public UserRegistrationDTO getUserRegistationDTO() {
		return userRegistationDTO;
	}
	public void setUserRegistationDTO(UserRegistrationDTO userRegistationDTO) {
		this.userRegistationDTO = userRegistationDTO;
	}
	public PostDTO getPostDTO() {
		return postDTO;
	}
	public void setPostDTO(PostDTO postDTO) {
		this.postDTO = postDTO;
	}
	public ArrayList<ActionDTO> getActionDTOList() {
		return actionDTOList;
	}
	public void setActionDTOList(ArrayList<ActionDTO> actionDTOList) {
		this.actionDTOList = actionDTOList;
	}
	public ArrayList<MediaDTO> getMediaDTOList() {
		return mediaDTOList;
	}
	public void setMediaDTOList(ArrayList<MediaDTO> mediaDTOList) {
		this.mediaDTOList = mediaDTOList;
	}
	public ArrayList<ReportDTO> getReportDTOList() {
		return reportDTOList;
	}
	public void setReportDTOList(ArrayList<ReportDTO> reportDTOList) {
		this.reportDTOList = reportDTOList;
	}
	
	
	
	
}
