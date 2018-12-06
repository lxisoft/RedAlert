package com.lxisoft.redalert.model;

import java.util.ArrayList;
import java.util.List;

import com.lxisoft.redalert.client.red_alert.model.*;

public class NewsFeedViewModel {
	private UserRegistrationDTO userRegistationDTO;
	private PostDTO postDTO;
	private ActionDTO newActionDTO;
	private List<ActionDTO> actionDTOList=new ArrayList<ActionDTO>();
	private List<MediaDTO> mediaDTOList=new ArrayList<MediaDTO>();
	private List<ReportDTO> reportDTOList=new ArrayList<ReportDTO>();
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
	
	public ActionDTO getNewActionDTO() {
		return newActionDTO;
	}
	public void setNewActionDTO(ActionDTO newActionDTO) {
		this.newActionDTO = newActionDTO;
	}
	public List<ActionDTO> getActionDTOList() {
		return actionDTOList;
	}
	public void setActionDTOList(List<ActionDTO> actionDTOList) {
		this.actionDTOList = actionDTOList;
	}
	public List<MediaDTO> getMediaDTOList() {
		return mediaDTOList;
	}
	public void setMediaDTOList(List<MediaDTO> mediaDTOList) {
		this.mediaDTOList = mediaDTOList;
	}
	public List<ReportDTO> getReportDTOList() {
		return reportDTOList;
	}
	public void setReportDTOList(List<ReportDTO> reportDTOList) {
		this.reportDTOList = reportDTOList;
	}
	public void setReportDTOList(ArrayList<ReportDTO> reportDTOList) {
		this.reportDTOList = reportDTOList;
	}
	
	
	
	
}
