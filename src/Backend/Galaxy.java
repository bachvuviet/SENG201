package Backend;

import java.util.ArrayList;
import java.util.Collections;

import SpaceVessel.Spaceship;

public class Galaxy {
	public static int maxTurn;
	public static int maxHull;
	public static int maxFuel;
	public static int Prestige;//Point
	
	private String Name;
	private Spaceship SpaceShip;
	private String imagePath;
	private ArrayList<Entity> SpaceObjects = new ArrayList<Entity>();
	
	public Galaxy(String name, Spaceship ship, String backGroundPath) {
		Name = name;
		SpaceShip = ship;
		imagePath = backGroundPath;
	}
	
	public String getGalaxyName() {
		return Name;
	}
	
	public String getBackgroundPath() {
		return imagePath;
	}
	
	public Spaceship getShip() {
		return SpaceShip;
	}
	
	public ArrayList<Entity> getSpaceObjects(){
		return SpaceObjects;
	}
	
	public void addSpaceObjects(Entity en) {
		SpaceObjects.add(en);
	}
	public void addSpaceObjects(int index, Entity en) {
		SpaceObjects.add(index, en);
	}
	
	public void shuffleSpaceObjects() {
		Collections.shuffle(SpaceObjects);
	}

	public void deleteShip() {
		SpaceShip = null;		
	}

	public void updateShip(Spaceship ship) {
		SpaceShip = ship;
	}
}
