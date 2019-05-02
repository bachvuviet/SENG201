package SpaceVessel;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import Backend.Galaxy;

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
	private int daysOnMission = 1;
	private final Faction shipFaction = Faction.IMPERIAL_NAVY;
	
	//Default
	private int Fuel;
	private int HullStrength;
	private int Money = 100;
	
	//Indexed data	
	private ArrayList<ShipModule> MODULES = new ArrayList<ShipModule>();
	private ArrayList<Crew> CREW = new ArrayList<Crew>();
	
	//Constructor
	public Spaceship(double x, double y, String name, ArrayList<Crew> crew) {
		super((int) x, (int) y, 40, 70, name, "/spaceshipUp.png");
		CREW = crew;
		HullStrength = Galaxy.maxHull;
		Fuel = Galaxy.maxFuel;
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
			mystr += "<p>" + module.modStatus();
		}
		
		mystr = "<html><h1>Modules: " + active + "/" + MODULES.size() + mystr + "</html>";
		return mystr;
	}
	public ArrayList<ShipModule> getModuleList() {
		return MODULES;
	}
	public ShipModule getModule(ShipModule mod) {
		ShipModule module = new ShipModule();
		for (ShipModule st: MODULES) {
			if ((st.getName()).equals(mod.getName())) {
				module = st;
				break;
			}
		}
		return module;
	}
	public void CheckModule() {
		
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
		return Fuel;
	}
	/**
	 * Get Name of Ship
	 * @return Ship Name
	 */
	public String getVesselName() {
		return Name;
	}
	/**
	 * Get Space money of player
	 * @return Space money
	 */
	public int getMoney() {
		return Money;
	}
	
	//setter
	/**
	 * Add max fuel
	 * @param amount boost fuel by helmsman
	 */
	public void setModule(ArrayList<ShipModule> mod) {
		MODULES = mod;
	}
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
	/**
	 * Add/Minus certain amount of money via sell/buy item
	 * @param amount money to be added/minus
	 */
	public boolean changeMoney(int amount, Stock st) {
		if (amount < 0 && Money >= -amount) {
			Money += amount;
			return true;
		} else if (amount > 0 && st.getAmount() >= 5) {
			Money += amount;
			return true;
		} else
			return false;
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

	public void CheckCrew() {
		for (Crew crew:CREW) {
			switch (crew.getRank()) {
			case SCIENTIST://Increase Hull
				Galaxy.maxHull += 50;
				HullStrength = Galaxy.maxHull;
				break;
			case MECHANIC://Repair Hull faster
				break;
			case CAPTAIN://Increase all morale
				for (Crew cr:CREW) {
					cr.setMaxMorale(25);
				}
				break;
			case DOCTOR://Increase all health
				for (Crew cr:CREW) {
					cr.setMaxHealth(25);
				}
				break;
			case CHEF://Increase all hunger
				for (Crew cr:CREW) {
					cr.setMaxHunger(25);
				}
				break;
			case HELMS_MAN://Increase fuel
				Galaxy.maxFuel += 200;
				Fuel = Galaxy.maxFuel;
				break;
			default:
				break;
			}
		}
		
		for (Crew cr:CREW) {
			cr.MaxStat();
		}
	}

	public int getTurn() {
		return daysOnMission;
	}
	public void nextTurn() {
		daysOnMission += 1;
	}

	public void setFuel(int value) {
		Fuel = value;
	}
}