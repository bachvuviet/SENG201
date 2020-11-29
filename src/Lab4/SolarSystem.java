package Lab4;

import java.util.ArrayList;

public class SolarSystem {
	//Class at higher level
	private ArrayList<Planet> solarList = new ArrayList<Planet>();
	
	public void addPlanet(Planet planet) {
		solarList.add(planet);
	}
	
	public int getPlanetCount() {
		return solarList.size();
	}
	
	public void printAllPlanets() {
		for (Planet planet:solarList) {
			System.out.println(planet);
		}
	}
}
