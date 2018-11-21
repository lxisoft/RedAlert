package com.lxisoft.redalert.model;

import com.lxisoft.redalert.client.red_alert.model.MediaDTO;
import com.lxisoft.redalert.client.red_alert.model.PostDTO;
import com.lxisoft.redalert.client.red_alert.model.PostDTO.AlertLevelEnum;
import com.lxisoft.redalert.client.red_alert.model.UserRegistrationDTO;

public class View {
	private PostDTO postDTO;
	private MediaDTO mediaDTO;
	private long userMediaId;
	private long userRegistrationId;
	private AlertLevelEnum userAlertLevel;
	public AlertLevelEnum getUserAlertLevel() {
		return userAlertLevel;
	}
	public void setUserAlertLevel(AlertLevelEnum userAlertLevel) {
		this.userAlertLevel = userAlertLevel;
	}
	public long getUserRegistrationId() {
		return userRegistrationId;
	}
	public void setUserRegistrationId(long userRegistrationId) {
		this.userRegistrationId = userRegistrationId;
	}
	private UserRegistrationDTO userRegistrationDTO;
	public UserRegistrationDTO getUserRegistrationDTO() {
		return userRegistrationDTO;
	}
	public void setUserRegistrationDTO(UserRegistrationDTO userRegistrationDTO) {
		this.userRegistrationDTO = userRegistrationDTO;
	}
	public PostDTO getPostDTO() {
		return postDTO;
	}
	public void setPostDTO(PostDTO postDTO) {
		this.postDTO = postDTO;
	}
	public MediaDTO getMediaDTO() {
		return mediaDTO;
	}
	public void setMediaDTO(MediaDTO mediaDTO) {
		this.mediaDTO = mediaDTO;
	}
	public long getUserMediaId() {
		return userMediaId;
	}
	public void setUserMediaId(long userMediaId) {
		this.userMediaId = userMediaId;
	}

}
