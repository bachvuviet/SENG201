package SpaceVessel;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;

enum Faction {
	CHAOS_FLEET, IMPERIAL_NAVY, ELDAR_CORSAIR, SPACE_PIRATE;
}

/**
 * Spaceship object generated from missionFrame and stay the same throughout the game.
 * <br> Its attribute change however
 * @author Bach Vu, Linh Luu
 * @version 0.30
 */
public class Spaceship extends Outpost {
	//Attributes by User
	/** current turn*/
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
		super(x, y, name, "/spaceshipUp.png");
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
			visual = new ImageIcon(this.getClass().getResource("/spaceshipLeft.png")).getImage();
			break;
		case 2:
			visual = new ImageIcon(this.getClass().getResource("/spaceshipDown.png")).getImage();
			break;
		case 3:
			visual = new ImageIcon(this.getClass().getResource("/spaceshipRight.png")).getImage();
			break;
		default:
			visual = new ImageIcon(this.getClass().getResource("/spaceshipUp.png")).getImage();
			break;
		}
		g.drawImage(visual, (int) x, (int) y, null);
	}
	
	//Controls Ship Methods
	/**
	 * Get new location of Spaceship on screen
	 */
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
	}
	
	public void forward() {
		velocity = 5;
		UpdateLocation();
	}
	/** Turn left*/
	public void toPort() {
		if (!turning) {
			if (direction < 3)
				direction += 1;
			else
				direction = 0;
			turning = true; 			
		}
	}
	/** Turn right*/
	public void toStarBoard() {
		if (!turning) {
			if (direction == 0)
				direction = 3;
			else
				direction -= 1;
			turning = true; 
		}
	}
	/** Reverse*/
	public void reverse() {
		velocity = -2;
		UpdateLocation();
	}
	/** Stop the Ship*/
	public void stop() {
		velocity = 0;
		UpdateLocation();
	}
	/**
	 * get current direction of Ship
	 * @return WASD = 0123
	 */
	public int getDirect() {
		return direction;
	}
	
	//tostrings
	/**
	 * Quick display of Ship stat for pause frame
	 * @return Formated String
	 */
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
	@Override
	public String toString() {
		return "";
	}
	/**
	 * Quick display of Ship modules for pause frame
	 * @return Formated String
	 */
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
	
	//Supply Ship Methods - getter
	/**
	 * Get Health of Ship
	 * @return Ship HP
	 */
	public int getHull() {
		return HullStrength;
	}
	/**
	 * Get Fuel left at any instance
	 * @return current fuel left
	 */
	public int getFuel() {
		return baseFuel;
	}
	/**
	 * Get Name of Ship
	 * @return Ship Name
	 */
	public String getVesselName() {
		return Name;
	}
	
	//setter
	/**
	 * Set new Ship Hull after repair (+ve value) or after collision/events (-ve value)
	 * @param value Amount to add to Ship HP
	 */
	public void increaseHull(int value) {
		HullStrength += value;
	}
	/**
	 * Rename Ship via this method
	 * @param name New name
	 */
	public void RenameShip(String name) {
		Name = name;
	}
	
	
	//Crew Related
	/**
	 * Get crew from crew list
	 * @param ID ID or index of crew
	 * @return Crew member at selected index
	 */
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
	/**
	 * Get entire list of crew in ship
	 * @return Entire Crew list
	 */
	public ArrayList<Crew> getCrewList(){
		return CREW;
	}
}