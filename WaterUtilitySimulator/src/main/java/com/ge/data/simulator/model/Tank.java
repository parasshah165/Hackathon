package com.ge.data.simulator.model;

public enum Tank {
	
	WATERTANK_001(37.696877, -121.886764),
	WATERTANK_002(37.763195, -121.926280),
	WATERTANK_003(37.732008, -121.926280),
	WATERTANK_004(37.760191, -121.964907),
	WATERTANK_005(37.779510, -121.936666);
	

	private final double latitude;
	private final double longitude;
//	private final double redThreshold;
//	private final double yellowThreshold;
//	private final double greenThreshold;
//	private final double ph;
//	private final double turbidity;
//	private final double chlorine;
//	private final double lead;

	
	private Tank(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
				
	}

//	private Tank(double latitude, double longitude, double redThreshold,
//			double yellowThreshold, double greenThreshold, double ph, double turbidity, double chlorine, double lead) {
//		this.latitude = latitude;
//		this.longitude = longitude;
//		this.redThreshold = redThreshold;
//		this.yellowThreshold = yellowThreshold;
//		this.greenThreshold = greenThreshold;
//		this.ph = ph;
//		this.chlorine = chlorine;
//		this.lead = lead;
//		this.turbidity = turbidity;
//		
//	}



	public double getLatitude() {
		return latitude;
	}



	public double getLongitude() {
		return longitude;
	}



//	public double getRedThreshold() {
//		return redThreshold;
//	}
//
//
//
//	public double getYellowThreshold() {
//		return yellowThreshold;
//	}
//
//
//
//	public double getGreenThreshold() {
//		return greenThreshold;
//	}
//
//
//
//	public double getPh() {
//		return ph;
//	}
//
//
//
//	public double getTurbidity() {
//		return turbidity;
//	}
//
//
//
//	public double getChlorine() {
//		return chlorine;
//	}
//
//
//
//	public double getLead() {
//		return lead;
//	}
//	
	


}
