package model;

import java.util.Map;

public class ResponseJSON {
	private int assetsid;

    private Double lat;
    private Double longitude;
    private String assetid;
    private String type;
    private String status;
    

    private Map<String,Object> params;


	public int getAssetsid() {
		return assetsid;
	}


	public void setAssetsid(int assetsid) {
		this.assetsid = assetsid;
	}


	public Double getLat() {
		return lat;
	}


	public void setLat(Double lat) {
		this.lat = lat;
	}


	public Double getLongitude() {
		return longitude;
	}


	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}


	public String getAssetid() {
		return assetid;
	}


	public void setAssetid(String assetid) {
		this.assetid = assetid;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Map<String, Object> getParams() {
		return params;
	}


	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
    
    
}
