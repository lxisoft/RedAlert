package com.lxisoft.redalert.model;

import java.util.ArrayList;
import java.util.Objects;

import com.lxisoft.redalert.client.crimestopper.model.ComplaintDTO;

import io.swagger.annotations.ApiModelProperty;

public class CSHashtagView {
	private Long id = null;

	private String name = null;

	private ArrayList<ComplaintDTO> complaints = null;

	public CSHashtagView id(Long id) {
		this.id = id;
		return this;
	}

	public ArrayList<ComplaintDTO> getComplaints() {
		return complaints;
	}

	public void setComplaints(ArrayList<ComplaintDTO> complaints) {
		this.complaints = complaints;
	}

	/**
	 * Get id
	 * 
	 * @return id
	 **/
	@ApiModelProperty(value = "")

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CSHashtagView name(String name) {
		this.name = name;
		return this;
	}

	/**
	 * Get name
	 * 
	 * @return name
	 **/
	@ApiModelProperty(value = "")

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		CSHashtagView hashtagView = (CSHashtagView) o;
		return Objects.equals(this.id, hashtagView.id) && Objects.equals(this.name, hashtagView.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class CSHashtagView {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
