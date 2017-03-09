package com.ge.data.simulator.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class BinHeightData {

	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("heightCovered")
	@Expose
	private Double heightCovered;

	/**
	 * 
	 * @return The name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 *            The name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return The heightCovered
	 */
	public Double getHeightCovered() {
		return heightCovered;
	}

	/**
	 * 
	 * @param heightCovered
	 *            The heightCovered
	 */
	public void setHeightCovered(Double heightCovered) {
		this.heightCovered = heightCovered;
	}

}