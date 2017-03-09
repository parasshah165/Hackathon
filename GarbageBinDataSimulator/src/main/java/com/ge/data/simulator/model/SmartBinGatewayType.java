package com.ge.data.simulator.model;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class SmartBinGatewayType {

	@SerializedName("binHeightData")
	@Expose
	private BinHeightData binHeightData;

	@SerializedName("currentTime")
	@Expose
	private Long currentTime;

	@SerializedName("colorData")
	@Expose
	private ColorData colorData;

	@SerializedName("garbageAirQuality")
	@Expose
	private GarbageAirQuality garbageAirQuality;

	@SerializedName("locdata")
	@Expose
	private LocationData locdata;

	/**
	 * 
	 * @return The binHeightData
	 */
	public BinHeightData getBinHeightData() {
		return binHeightData;
	}

	/**
	 * 
	 * @param binHeightData
	 *            The binHeightData
	 */
	public void setBinHeightData(BinHeightData rpmData) {
		this.binHeightData = rpmData;
	}

	/**
	 * 
	 * @return The currentTime
	 */
	public Long getCurrentTime() {
		return currentTime;
	}

	/**
	 * 
	 * @param currentTime
	 *            The currentTime
	 */
	public void setCurrentTime(Long currentTime) {
		this.currentTime = currentTime;
	}

	/**
	 * 
	 * @return The garbageAirQuality
	 */
	public GarbageAirQuality getGarbageAirQuality() {
		return garbageAirQuality;
	}

	/**
	 * 
	 * @param garbageAirQuality
	 *            The garbageAirQuality
	 */
	public void setGarbageAirQuality(GarbageAirQuality garbageAirQuality) {
		this.garbageAirQuality = garbageAirQuality;
	}

	/**
	 * 
	 * @return The locdata
	 */
	public LocationData getLocdata() {
		return locdata;
	}

	/**
	 * 
	 * @param locdata
	 *            The locdata
	 */
	public void setLocdata(LocationData locdata) {
		this.locdata = locdata;
	}

	public ColorData getColorData() {
		return colorData;
	}

	public void setColorData(ColorData colorData) {
		this.colorData = colorData;
	}

}
