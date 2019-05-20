package SpaceVessel;

import javax.swing.ImageIcon;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Crews are your friend, they carry out your order. Each Ship as a crew of 4.
 * @author Bach Vu, Linh Luu
 * @version 0.30
 */
public class Crew implements Serializable {
	private static final long serialVersionUID = 1L;
    /** Check if the crew was instantiated from MissionFrame. If false, cannot perform action.*/
    public boolean Real;
    
    /** Default: Rename here. Player can rename crew from PauseFrame, crew tab*/
    private String Name;
    /** Rank of Crew (1 of 6). Different Rank (crew type) has different bonus*/
    private CrewRank Rank;
    /** If Health drop to 0, this crew will die and removed from ship's crew. You have less friend to help you carry orders.*/
    private int Health;
    /** Hunger is needed to perform your order. Feed them well after assign task to them.*/
    private int Hunger;
    /** Morale is needed to perform your order. Let crew sleep to relax, don't force them work too hard*/
    private int Morale;
    private boolean Sick = false;
    
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
    	this.Name = name;
    	this.Rank = rank;
    	this.avatar = img;
    	Real = true;
    	MaxStat();
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
    /** Get crew's morale
     * @return Morale point*/
    public int getMorale() {
    	return Morale;
    }
    /** Get crew's hunger
     * @return Hunger point*/
    public int getHunger() {
    	return Hunger;
    }
    /** Get crew's image, to display as label icon
     * @return Crew Avatar*/
    public ImageIcon getAvatar() {
    	return avatar;
    }
    
    public boolean isSick() {
    	return Sick;
    }
    
    //setter
    /** Set crew's name in crew panel txtbox
     * @param name New name for crew
     * */
    public void setName(String name) {
    	Name = name;
    }
    
    public void Sick(boolean sick) {
    	Sick = sick;
    }
    
    public void MaxStat() {
    	Health = Spaceship.maxCrewHealth;
    	Hunger = Spaceship.maxCrewHunger;
    	Morale = Spaceship.maxCrewMorale;
    }
    
	//Crew Action
    private boolean checkCrewStat(int hunger, int morale, String act) {
    	//String errMess = "Cannot perform action "+act+".";
    	boolean err = false;
    	if (Hunger < hunger) {
    		//errMess += "\nCrew is too hungry to do this. (Sleep or use supplement)";
    		err = true;
    	}
    	if (Morale < morale) {
    		//errMess += "\nCrew is too tired to do this. (Sleep or use supplement)";
    		err = true;
    	}
    	return err;
    }
    
    /** 
     * Order crew to use stock (food, medicine, portion) from Ship Inventory to increase HP, Hunger and morale
     * @param amount amount of stock to use
     * @param stock what stock to use
     */
    public boolean useSupply(int amount, Stock stock) {
    	int boost = stock.use(amount);
    	if (boost <= 0) {
    		return false;
    	}

		if (stock instanceof Stock_Food) {
			Hunger = SupplyEffect(Hunger, boost, Spaceship.maxCrewHunger);
		}
		else if (stock instanceof Stock_Medicine) {
			if (((Stock_Medicine) stock).getHealCategory() == "Health")
				Health = SupplyEffect(Health, boost, Spaceship.maxCrewHealth);
			
			else if (((Stock_Medicine) stock).getHealCategory() == "Morale") 
				Morale = SupplyEffect(Morale, boost, Spaceship.maxCrewMorale);
			
			else {
				Morale = SupplyEffect(Morale, boost, Spaceship.maxCrewMorale);
				Health = SupplyEffect(Health, boost, Spaceship.maxCrewHealth);
				Sick = false;
			}
		}
		crewAction.add("Use Supplement (x2)");  
		return true;
    }
    
    private int SupplyEffect(int initial, int boost, int max) {
    	if (initial + boost <= max)
			initial += boost;
		else
			initial = max;
    	
    	return initial;
    }
    /**
     * Let Crew sleep to increase Health and Morale, but hungry when wake up
     */
    public void sleep() {
    	Health += 5;
    	Morale += 25;
    	Hunger -= 20;
    	crewAction.add("Sleep");
    }
    /**
     * Order crew to repair ship
     * @param Ship Ship of the crew
     */
    public boolean repair(Spaceship Ship) {
    	if (!checkCrewStat(20, 15, "Repair Ship")) {
        	Hunger -= 20;
        	Morale -= 15;
        	crewAction.add("Repair");
        	if (Rank == CrewRank.MECHANIC)
        		Ship.increaseHull(25);
        	else
        		Ship.increaseHull(15);
        	return true;
    	} else
    		return false;
    }
    /**
     * crew must have action to pilot ship at anytime, otherwise ship cannot move
     */
    public boolean pilotShip() {
    	if (!checkCrewStat(30, 30, "Pilot Ship")) {
	    	Morale -= 30;
	    	Hunger -= 30;
	    	crewAction.add("Pilot");
	    	return true;
    	} else
    		return false;
    }
    /**
     * Get Assigned order of each crew
     * @return Crew current task
     */
    public ArrayList<String> getCrewActivity(){
    	return crewAction;
    }
	public void clearActivity() {
		crewAction.clear();
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
    
	public void minusHealth(int amount) {
		Health -= amount;
	}
	
	public void dead() {
		Real = false;		
	}
}
