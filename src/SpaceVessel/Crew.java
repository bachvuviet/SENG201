package SpaceVessel;

import javax.swing.ImageIcon;

import java.util.ArrayList;

/**
 * Crews are your friend, they carry out your order. Each Ship as a crew of 4.
 * @author Bach Vu, Linh Luu
 * @version 0.30
 */
public class Crew {
	/** ID of Crew on ship (0-3) to support get Crew from ship's Crew list*/
    public int ID;
    /** Check if the crew was instantiated from MissionFrame. If false, cannot perform action.*/
    public boolean Real;
    
    /** Default: Rename here. Player can rename crew from PauseFrame, crew tab*/
    private String Name;
    /** Rank of Crew (1 of 6). Different Rank (crew type) has different bonus*/
    private CrewRank Rank;
    /** If Health drop to 0, this crew will die and removed from ship's crew. You have less friend to help you carry orders.*/
    private int MaxHealth;
    private int Health;
    /** Hunger is needed to perform your order. Feed them well after assign task to them.*/
    private int Hunger;
    private int MaxHunger;
    /** Morale is needed to perform your order. Let crew sleep to relax, don't force them work too hard*/
    private int Morale;
    private int MaxMorale;
    
    /** Maximum of 2 action per day/turn. Given order is added, so you can perform 2 actions at any time of a day.*/
    private ArrayList<String> crewAction = new ArrayList<String>();
    /** Selfie pics of crew, from the select*/
    private ImageIcon avatar;
    
    /** Empty object to search for crew*/
    public Crew() { Real = false; }
    /**
     * Actual constructor, only called in MissionFrame when player choose crew type
     * @param name Default by button, rename here
     * @param rank Selected by player via button on mission frame
     * @param health Depends on crew Rank, min 100
     * @param hunger Depends on crew Rank, min 100
     * @param morale Depends on crew Rank, min 100
     * @param img Avatar, depends on crew Rank
     */
    public Crew(String name, CrewRank rank, ImageIcon img) {
    	this.ID = 0;
    	this.Name = name;
    	this.Rank = rank;
    	this.MaxHealth = 100;
    	this.MaxHunger = 100;
    	this.MaxMorale = 100;
    	this.avatar = img;
    	Real = true;
    }
    
    //getter
    /** Get crew's name
     * @return Crew Name*/
    public String getName( ) {
    	return Name;
    }
    /** Get crew's rank
     * @return CrewRank*/
    public CrewRank getRank() {
    	return Rank;
    }
    /** Get crew's health
     * @return HP point*/
    public int getHealth() {
    	return Health;
    }
    /**
     * Initial health
     * @return Max health of crew
     */
	public int getMaxHealth() {
		return MaxHealth;
	}
    /** Get crew's morale
     * @return Morale point*/
    public int getMorale() {
    	return Morale;
    }
    /**
     * Initial morale
     * @return Max morale of crew
     */
	public int getMaxMorale() {
		return MaxMorale;
	}
    /** Get crew's hunger
     * @return Hunger point*/
    public int getHunger() {
    	return Hunger;
    }
    /**
     * Initial hunger
     * @return Max hunger of crew
     */
	public int getMaxHunger() {
		return MaxHunger;
	}
    /** Get crew's image, to display as label icon
     * @return Crew Avatar*/
    public ImageIcon getAvatar() {
    	return avatar;
    }
    
    //setter
    /** Set crew's name in crew penel txtbox
     * @param name New name for crew
     * */
    public void setName(String name) {
    	Name = name;
    }
    /**
     * Increase health of crew by some amount
     * @param amount HP increase
     */
    public void setMaxHealth(int amount) {
    	MaxHealth += amount;
    }
    /**
     * Increase hunger of crew by some amount
     * @param amount hunger increase
     */
    public void setMaxHunger(int amount) {
    	MaxHunger += amount;
    }
    /**
     * Increase morale of crew by some amount
     * @param amount morale increase
     */
    public void setMaxMorale(int amount) {
    	MaxMorale += amount;
    }
    
    public void MaxStat() {
    	Health = MaxHealth;
    	Hunger = MaxHunger;
    	Morale = MaxMorale;
    }
    
	//Crew Action
    /** 
     * Order crew to use stock (food, medicine, potion) from Ship Inventory to increase HP, Hunger and morale
     * @param amount amount of stock to use
     * @param stock what stock to use
     */
    public void useSupply(int amount, Stock stock) {
    	if (stock.getAmount() > 0) {
    		stock.setAmount(stock.getAmount()-amount);
    		if (stock instanceof Stock_Food) {
    			Hunger += stock.use(amount); 
        		crewAction.add("Eating");    			
    		}
    		else if (stock instanceof Stock_Medicine) {
    			Health += stock.use(amount);
        		crewAction.add("Healing");    	
    		}
    	}    	
    }
    /**
     * Let Crew sleep to increase Health and Morale, but hungry when wake up
     */
    public void sleep() {
    	Health += 25;
    	Morale += 25;
    	Hunger -= 20;
    	crewAction.add("Sleeping");
    }
    /**
     * Order crew to repair ship
     * @param Ship Ship of the crew
     */
    public void repair(Spaceship Ship) {
    	Hunger -= 20;
    	Morale -= 15;
    	crewAction.add("Repairing Ship");
    	if (Rank == CrewRank.MECHANIC)
    		Ship.increaseHull(25);
    	else
    		Ship.increaseHull(15);
    }
    /**
     * crew must have action to pilot ship at anytime, otherwise ship cannot move
     */
    public void pilotShip() {
    	Morale -= 25;
    	crewAction.add("Driving Ship");
    }
    /**
     * Get Assigned order of each crew
     * @return Crew current task
     */
    public ArrayList<String> getCrewActivity(){
    	return crewAction;
    }
    /**
     * Quick output to check Crew status when testing
     */
    public String toString() {
    	String mystr = "";
    	if (crewAction.size() == 0) {
    		mystr = Rank + Name + " is doing nothing";
    	} else {
	    	mystr = Rank + Name + " is " + crewAction.get(0);
	    	if (crewAction.size() > 0)
	    		mystr += " and " + crewAction.get(1);	    	
    	}
    	return mystr;
    }
}
