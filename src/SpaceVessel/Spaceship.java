package SpaceVessel;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;

enum Faction {
	CHAOS_FLEET, IMPERIAL_NAVY, ELDAR_CORSAIR, SPACE_PIRATE;
}

public class Spaceship extends Outpost {
	//Attributes by User
	public int daysOnMission;
	private final Faction shipFaction = Faction.IMPERIAL_NAVY;
	
	//Default
	private int baseSpeed = 100;
	private int baseFuel = 100;
	private int HullStrength = 100;	
	
	//Indexed data	
	private ArrayList<ShipModule> MODULES = new ArrayList<ShipModule>();
	private ArrayList<Crew> CREW = new ArrayList<Crew>();
	
	//Constructor
	public Spaceship(double x, double y, String name, int day, ArrayList<Crew> crew, ArrayList<ShipModule> mod) {
		super(x, y, name, "../spaceshipUp.png");
		Width = 40; Height = 70;
		daysOnMission = day;
		CREW = crew;
		MODULES = mod;
	}
	
	//Visual Object
	double velocity;
	int direction = 0;
	public boolean turning = false;
	
	public void render(Graphics g) {
		switch (direction) {
		case 1: 
			visual = new ImageIcon(this.getClass().getResource("../spaceshipLeft.png")).getImage();
			break;
		case 2:
			visual = new ImageIcon(this.getClass().getResource("../spaceshipDown.png")).getImage();
			break;
		case 3:
			visual = new ImageIcon(this.getClass().getResource("../spaceshipRight.png")).getImage();
			break;
		default:
			visual = new ImageIcon(this.getClass().getResource("../spaceshipUp.png")).getImage();
			break;
		}
		g.drawImage(visual, (int) x, (int) y, null);
	}
	
	//Controls Ship Methods
	public void UpdateLocation() {
		switch (direction) {
			case 0:
				y -= velocity;
				break;
			case 1: 
				x -= velocity;
				break;
			case 2:
				y += velocity;
				break;
			case 3:
				x += velocity;
				break;
			default:
				break;
		}
		//System.out.println("Speed: " +x+" - "+  y+"-"+velocity);
	}
	
	public void forward() {
		velocity = 5;
		UpdateLocation();
	}
	public void toPort() {
		if (!turning) {
			if (direction < 3)
				direction += 1;
			else
				direction = 0;
			turning = true; 			
		}
	}
	public void toStarBoard() {
		if (!turning) {
			if (direction == 0)
				direction = 3;
			else
				direction -= 1;
			turning = true; 
		}
	}
	public void reverse() {
		velocity = -2;
		UpdateLocation();
	}
	public void stop() {
		velocity = 0;
		UpdateLocation();
	}

	public int getDirect() {
		return direction;
	}
	
	//Supply Ship Methods - getter
	public int getHull() {
		return HullStrength;
	}
	public int getFuel() {
		return baseFuel;
	}
	public String ShipStatus() {
		return "<html>"
				+ "<h1>Ship Status:"
				+ "<p>Hull Strength:" + HullStrength
				+ "<p>Speed: " + baseSpeed
				+ "<p>"
				+ "<p>This vessel is registered for " + shipFaction
				+ "<p> Day on Mission: " + daysOnMission
				+ "</html>";
	}
	public String toString() {
		return "";
	}
	public String getVesselName() {
		return Name;
	}
	public String getModules() {
		int active = 0;
		String mystr = "";
		for(ShipModule module:MODULES) {
			if (module.isActive()) {
				active += 1;
			}
			mystr += "<p>" + module.toString();
		}
		
		mystr = "<html><h1>Modules: " + active + "/" + MODULES.size() + mystr + "</html>";
		return mystr;
	}
	
	//Supply Ship Methods - setter
	public void setHull(int value) {
		HullStrength += value;
	}
	public void RenameShip(String name) {
		Name = name;
	}
	
	//Crew Related
	public Crew getCrew(int ID) {
		Crew myCrew = new Crew();
		for (Crew cr:CREW) {
			if (cr.ID == ID) {
				myCrew = cr;
				break;
			}
		}
		return myCrew;
	}
	public ArrayList<Crew> getCrewList(){
		return CREW;
	}
}