package Backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import SpaceVessel.Spaceship;

public class Galaxy implements Serializable {
	private static final long serialVersionUID = 1L;
	public static int maxTurn;
	public static int maxHull;
	public static int maxFuel;
	public static int totalCrew;
	public static int Prestige;//Point
	public static int currTutorial;
	
	public static ArrayList<Tutorial> Tutorial = new ArrayList<Tutorial>(Arrays.asList(
			new Tutorial("Leutenent Linh, tip1", "Captain, our ship is severely damaged from last battle and now lost contact with the fleet. We must find parts on surrounding planet to replace damaged modules before regroups with battlefleet Silver Dawn. Admiral Venesca Catallia is wounded, you will take chare of the whole ship.", "Press ESC to assess ship situation"),
			new Tutorial("Leutenent Linh, tip2", "As you can see, some of the modules is not active. Also, inventory is low on stock after the long journey. Come back to this panel anytime you wish to review the latest ship Status.", "Now select Crew tab to visit our crew."),
			new Tutorial("Leutenent Linh, tip3", "Crews are awakened after the warp jump. You can see individual crew’s stat here. Let’s put them to work. Use the dropdown and click “Give order” to confirm. We must have two crews Pilot the ship at all time.", "Order two crews to Pilot ship."),
			new Tutorial("Leutenent Linh, tip4", "Good work. Time to learn to control our ship. Use: W to go forward, S to reverse, A to steer left, D to steer right. Remember, you cannot control the ship when Plasma fuel ran out (display in bottom-right corner) and at least 2 crews to Pilot the ship.", "Move close to a SpaceMarine Station (your 9 o’clock)"),
			new Tutorial("Leutenent Linh, tip5", "The Space Marine, our ally, are willing to support us. We need to buy some supply to replenish our inventory. Once you get close to the space station, give them a warm hail.", "Press X to enter spacestation"),
			new Tutorial("Leutenent Linh, tip6", "We have some Universal gold that can be use to trade with the spacemarine. Hover mouse on an item on the right-hand-side to view details of the item. Right click to buy, Left-click to sell.", "Perform some trading action"),
			new Tutorial("Leutenent Linh, tip7", "Let’s get back to crew panel. Press ESC then select crew panel. Now you can order crew to use supplement from inventory to increase their health, hunger and morale.", "Order a pilot to eat something (Supply box below avatar)"),
			new Tutorial("Leutenent Linh, tip8", "Just a quick tips about crew actions:<br>Sleep: +30 Morale, -20 Hunger<br>Pilot: -30Morale, -30 Hunger<br>Repair: -30Morale, -30 Hunger<br>Use Supplement: Increase HP, Hunger or morale (depends on item)", ""),
			new Tutorial("Leutenent Linh, tip9", "Well done, our crew are taken care of and our ship inventory is temporary solved. Let’s get started on finding parts to repair our ship. Move your ship close to a planet and Scan", "Scan a planet for possible transporter. Press X"),
			new Tutorial("Leutenent Linh, tip10", "Yes, we found something. Some planet may have no transporter, some does containing parts to repair our ship and some contain stock.", "Check Ship status now (ESC) to view latest Inventory and Modules status."),
			new Tutorial("Leutenent Linh, tip11", "Now let’s head to other planet to find more parts. Remember, you cannot win the game unless all parts are found. If you ran out of Fuel, press Enter to have a rest and the crew will wake you up next day.", "Press Enter to refuel and reset crew action."),
			new Tutorial("Leutenent Linh, tip12", "Each crew has 2 tasks to perform per day. Utilise them.<br>Enter the black hole to move to the next galaxy once you have scanned all planets.<br>Space events occur randomly. Keep close eyes to ship stat and crew.<br>If a crew dies, you have less person to perform the action.", "Repair your ship and get back to the fleet.")
			));
	
	private int[] ShipStatic = {0, 0, 0, 0, 0};
	
	public void Save() {
		ShipStatic[0] = maxTurn;
		ShipStatic[1] = maxHull;
		ShipStatic[2] = maxFuel;
		ShipStatic[3] = totalCrew;
		ShipStatic[4] = Prestige;
	}
	public void Load() {
		maxTurn = ShipStatic[0];
		maxHull = ShipStatic[1];
		maxFuel = ShipStatic[2];
		totalCrew = ShipStatic[3];
		Prestige = ShipStatic[4];
	}
	public int[] TestShipStatic() {
		return ShipStatic;
	}
	
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
