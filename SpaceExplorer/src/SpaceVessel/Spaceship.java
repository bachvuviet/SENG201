package SpaceVessel;

import java.util.ArrayList;
import java.util.Random;

import Backend.Galaxy;
import CustomUIELmt.StaticObjects;

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
	public int[] TestCrewStatic() {
		return ShipStatic;
	}
	
	//Attributes by User
	private final Faction shipFaction = Faction.IMPERIAL_NAVY;
	
	//Default
	private int HullStrength;
	private int Money = 70;
	
	//Indexed data	
	private ArrayList<ShipModule> MODULES = new ArrayList<ShipModule>();
	private ArrayList<Crew> CREW = new ArrayList<Crew>();
	
	//Constructor
	public Spaceship(int x, int y, String name, ArrayList<Crew> crew) {
		super(x, y, 40, 70, name, "/spaceshipUp.png");
		CREW = crew;
		
		if (crew != null)
			CheckCrew();
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
	/**
	 * Quick display of Ship modules list
	 * @return List of modules
	 */
	public ArrayList<ShipModule> getModuleList() {
	
		return MODULES;
	}
	/**
	 * Display the Ship modules
	 * @param mod Module to be found
	 * @return Ship modules
	 */
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
	
	//Supply Ship Methods - getter
	/**
	 * Get Health of Ship
	 * @return Ship HP
	 */
	public int getHull() {
		return HullStrength;
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
	 * @param mod Set Initial Module list
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
	 * @param st Stock to be purchase/Sell
	 * @return True if transaction success
	 */
	public boolean changeMoney(int amount, Stock st) {
		if (amount < 0 && Money >= -amount) {//Buy
			Money += amount;
			return true;
		} else if (amount > 0 && st.getAmount() >= 1) {//Sell
			Money += amount;
			return true;
		} else
			return false;
	}
	
	
	//Crew Related
	/**
	 * Get entire list of crew in ship
	 * @return Entire Crew list
	 */
	public ArrayList<Crew> getCrewList(){
		return CREW;
	}
	
	/**
	 * Get bonus for chosen special crew(s)
	 */

	public void CheckCrew() {
		Galaxy.maxHull = 100;
		Galaxy.maxFuel = 800;
		maxCrewHealth = 100;
		maxCrewHunger = 100;
		maxCrewMorale = 100;
		
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
	
	public void CalculatePrestige() {		
		//Plus all alive crews' stat
		for (Crew cr:CREW) {
			if (cr.Real) {
				Galaxy.Prestige += cr.getHealth();
				Galaxy.Prestige += cr.getHunger();
				Galaxy.Prestige += cr.getMorale();				
			}
		}
		
		//Plus values of stock
		for (Stock st:INVENTORY) {
			Galaxy.Prestige += st.getPrice();
		}
		
		//Plus 100 point for each active modules
		for (int i=0; i<MODULES.size(); i++) {
			Galaxy.Prestige += 100;
		}
		
	}
	
	/**
	 * Run random keyEvents and count a new day
	 */
	Random random = new Random();
	/**
	 * Calculate new Point.
	 * <br>Do Space Events at random.
	 * <br>Update Crew stat at turn end, reset crew action.
	 * Do none of above if turn exceed
	 */
	public void EndTurn() {	
		if (daysOnMission == Galaxy.maxTurn)
			return;
		
		CalculatePrestige();
		
		String[] eventArr = {"Alien Pirates", "Space Plague", "Space Debris/Asteroid belt", "No problem"};
		String Message = "";
		int index = random .nextInt(4);
		switch (index) {
		case 0:
			Message = AlienPirates();
			break;
		case 1:
			Message = SpacePlague();
			break;
		case 2:
			Message = AsteroidBelt();
			break;
		default://No event
			Message = "Nothing to worry about!";
			break;
		}
		StaticObjects.MessBox(Message+"\nNew Turn begins", eventArr[index], "Warning");

		daysOnMission += 1;
		setFuel(Galaxy.maxFuel);
		for (Crew cr:CREW) {
			if (!cr.Real)
				continue;
			
			cr.clearActivity();
			if (cr.isSick())
				cr.minusHealth(50);
			if (cr.getHealth() <= 0)
				cr.dead();
		}
	}
	/**
	 * Check the Ship modules
	 * @return True if all modules are active and win the game
	 */
	public boolean CheckModule() {
		for (ShipModule mod:MODULES) {
			if (!mod.isActive())
				return false;
		}
		return true;
	}
	
	/**
	 * Display the AlienPrirates event
	 * @return Formated String
	 */
	String AlienPirates() {
		String mess = "Alien pirates boarded our ship and stole all of your ";
		int index = random .nextInt(9);
		Stock st = INVENTORY.get(index);
		st.setAmount(0);
		
		mess += st.getName();
		return mess;
	}
	
	/**
	 * Display the SpacePlague event
	 * @return Formated String
	 */
	String SpacePlague() {
		String mess = "A strange plague has not been quarantine. Following crew(s) are currently sick:\n\n";
		int numSick = random .nextInt(2);
		for (int i=0; i<=numSick; i++) {
			int index = random .nextInt(4);
			Crew cr = CREW.get(index);
			if (!cr.isSick()) {
				cr.Sick(true);
			}
		}
		
		for (Crew cr:CREW) {
			if (cr.isSick())
				mess += cr.getName() + " ("+cr.getRank()+"): -15HP, -20Morale per turn\n";
		}
		mess += "Sick crew(s) are colored Red in Crew Manaagement.\n";
		
		return mess;
	}
	
	/**
	 * Display the AsteroidBelt event
	 * @return Formated String
	 */
	String AsteroidBelt() {
		int amount = (int) HullStrength/2;
		HullStrength -= amount;
		return "Blame the pilot dude for Sleeping and driving at same time. Ship's hull damaged by "+amount+" points";
	}
	
	/** 
	 * Check the number of pilots if greater or equal to 2
	 * @return true if pilotNum greater or equal otherwise false
	 */
	public boolean enoughPilot() {
		int pilotNum = 0;
		
		for (Crew cr:CREW) {
			if(cr.getCrewActivity().contains("Pilot")) {
				pilotNum += 1;
			}
		}
		
		return pilotNum >= 2;
	}
	
	/**
	 * Set crew's name
	 * @param name new Ship name
	 */
	public void setName(String name) {
		Name = name;
	}
}