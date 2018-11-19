package com.lxisoft.redalert.model;

import com.lxisoft.redalert.client.red_alert.model.MediaDTO;
import com.lxisoft.redalert.client.red_alert.model.PostDTO;

public class View {
	private PostDTO postDTO;
	private MediaDTO mediaDTO;
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

}
