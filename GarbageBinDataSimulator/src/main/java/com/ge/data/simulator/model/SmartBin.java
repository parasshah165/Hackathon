package com.ge.data.simulator.model;

public enum SmartBin {

	SMARTBIN_1(37.763227, -121.942073, 10.0,30.0,50.0,50.0,100.0,400.0),
	SMARTBIN_2(37.763195, -121.951387, 10.0,30.0,50.0,50.0,100.0,400.0),
	SMARTBIN_3(37.778289, -121.978122, 10.0,30.0,50.0,50.0,100.0,400.0),
	SMARTBIN_4(37.750198, -121.964217, 10.0,30.0,50.0,50.0,100.0,400.0),
	SMARTBIN_5(37.779510, -121.936666, 10.0,30.0,50.0,50.0,100.0,400.0);
	

	private final double latitude;
	private final double longitude;
	private final double redThreshold;
	private final double yellowThreshold;
	private final double greenThreshold;
	private final double height;
	private final double airQualityThreshold1;
	private final double airQualityThreshold2;




	private SmartBin(double latitude, double longitude, double redThreshold,
			double yellowThreshold, double greenThreshold, double height, double airQualityThreshold1,
			double airQualityThreshold2) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.redThreshold = redThreshold;
		this.yellowThreshold = yellowThreshold;
		this.greenThreshold = greenThreshold;
		this.height = height;
		this.airQualityThreshold1 = airQualityThreshold1;
		this.airQualityThreshold2 = airQualityThreshold2;
	}



	public double getAirQualityThreshold1() {
		return airQualityThreshold1;
	}



	public double getAirQualityThreshold2() {
		return airQualityThreshold2;
	}



	public double getRedThreshold() {
		return redThreshold;
	}

	

	public double getYellowThreshold() {
		return yellowThreshold;
	}

	public double getGreenThreshold() {
		return greenThreshold;
	}

	public double getHeight() {
		return height;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	
}
