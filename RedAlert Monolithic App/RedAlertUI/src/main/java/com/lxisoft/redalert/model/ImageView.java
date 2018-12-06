package com.lxisoft.redalert.model;

import java.util.ArrayList;

import com.lxisoft.redalert.client.red_alert.model.MediaDTO;
import com.lxisoft.redalert.client.red_alert.model.PostDTO;

/**
 * @author silpa
 *
 */
public class ImageView {
	private PostDTO post;
	private ArrayList<MediaDTO> media;
	private ArrayList<String> images;
	public PostDTO getPost() {
		return post;
	}
	public void setPost(PostDTO post) {
		this.post = post;
	}
	public ArrayList<MediaDTO> getMedia() {
		return media;
	}
	public void setMedia(ArrayList<MediaDTO> media) {
		this.media = media;
	}
	public ArrayList<String> getImages() {
		return images;
	}
	public void setImages(ArrayList<String> images) {
		this.images = images;
	}
	
	

}
