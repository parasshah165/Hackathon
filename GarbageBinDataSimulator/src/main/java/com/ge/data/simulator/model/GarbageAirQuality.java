package com.ge.data.simulator.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class GarbageAirQuality {

	@SerializedName("airQuality")
	@Expose
	private Double airQuality;
	@SerializedName("name")
	@Expose
	private String name;

	/**
	 * 
	 * @return The Garbage bin airQuality
	 */
	public Double getAirQuality() {
		return airQuality;
	}

	/**
	 * 
	 * @param airQuality
	 *            The airQuality
	 */
	public void setAirQuality(Double airQuality) {
		this.airQuality = airQuality;
	}

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

}
