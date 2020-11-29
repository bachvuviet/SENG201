package Lab4;

public class Rover implements RemoteControllable{
	private double latitude;
	private double longitude;
	private String currMission;
	
	public void setLocation(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public void updateMission(String mission) {
		currMission = mission;
	}
	public String getLocation() {
		return String.format("The rover is located %.1f, %.1f on the planet.", latitude, longitude);
	}
	public String getStatusReport() {
		return getLocation()+"\nThe rover is driving to: "+currMission;
	}
}
