package Lab4;

public class SpaceStation implements RemoteControllable{
	private String Planet="";
	private String currMission="";
	
	public SpaceStation(String planet) {
		Planet = planet;		
	}
	public void updateMission(String mission) {
		currMission = mission;
	}
	public String getLocation() {
		return "The space station floats around the planet "+Planet;
	}
	public String getStatusReport() {
		return getLocation()+"\nThe station is on a mission to: "+currMission;
	}
}
