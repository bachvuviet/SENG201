package SpaceVessel;

import java.util.ArrayList;
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
	private static final long serialVersionUID = 1L;
	public static int maxCrewHealth = 100;
	public static int maxCrewHunger = 100;
	public static int maxCrewMorale = 100;
	public static int daysOnMission = 1;
	private int[] ShipStatic = {0, 0, 0, 0};
	
	public void Save() {
		ShipStatic[0] = maxCrewHealth;
		ShipStatic[1] = maxCrewHunger;
		ShipStatic[2] = maxCrewMorale;
		ShipStatic[3] = daysOnMission;
	}
	public void Load() {
		maxCrewHealth = ShipStatic[0];
		maxCrewHunger = ShipStatic[1];
		maxCrewMorale = ShipStatic[2];
		daysOnMission = ShipStatic[3];
	}
	
	//Attributes by User
	private final Faction shipFaction = Faction.IMPERIAL_NAVY;
	
	//Default
	private int Fuel;
	private int HullStrength;
	private int Money = 100;
	
	//Indexed data	
	private ArrayList<ShipModule> MODULES = new ArrayList<ShipModule>();
	private ArrayList<Crew> CREW = new ArrayList<Crew>();
	
	//Constructor
	public Spaceship(int x, int y, String name, ArrayList<Crew> crew) {
		super(x, y, 40, 70, name, "/spaceshipUp.png");
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
				maxCrewMorale += 25;
				break;
			case DOCTOR://Increase all health
				maxCrewHealth += 25;
				break;
			case CHEF://Increase all hunger
				maxCrewHunger += 25;
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

	public void setFuel(int value) {
		Fuel = value;
	}
}