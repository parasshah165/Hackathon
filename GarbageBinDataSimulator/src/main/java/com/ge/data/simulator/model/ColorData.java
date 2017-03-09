package com.ge.data.simulator.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class ColorData {

	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("heightCovered")
	@Expose
	private String colorValue;

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
	public String getHeightCovered() {
		return colorValue;
	}

	/**
	 * 
	 * @param heightCovered
	 *            The heightCovered
	 */
	public void setHeightCovered(String colorValue) {
		this.colorValue = colorValue;
	}

}