package xmu.crms.vo;

public class GPSDTO {
	String longitude;
    String latitude;
    String elevation;
	public GPSDTO(String longitude, String latitude, String elevation) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
		this.elevation = elevation;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getElevation() {
		return elevation;
	}
	public void setElevation(String elevation) {
		this.elevation = elevation;
	}

}
