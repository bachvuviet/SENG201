package SpaceVessel;

//import javax.swing.ImageIcon;
import java.util.ArrayList;

public class Crew {
    public int ID;
    public boolean Real;
    
    private String Name;
    private CrewRank Rank;
    private int Health;
    private int Hunger;
    private int Morale;
    
    private ArrayList<String> crewAction = new ArrayList<String>();
    //private ImageIcon avatar;
    
    public Crew() { Real = false; }
    public Crew(String name, CrewRank rank, int health, int hunger, int morale) {
    	this.ID = 0;
    	this.Name = name;
    	this.Rank = rank;
    	this.Health = health;
    	this.Hunger = hunger;
    	this.Morale = morale;
    	Real = true;
    }
    
    //getter
    public CrewRank getRank() {
    	return Rank;
    }
    public int getHealth() {
    	return Health;
    }
    public int getMorale() {
    	return Morale;
    }
    public int getHunger() {
    	return Hunger;
    }
    
    
	//Crew Action
    public void useSupply(int amount, Stock stock) {
    	if (stock.getAmount() > 0) { //&& currShip.getInventory().contains(stock)) {
    		stock.setAmount(stock.getAmount()-amount);
    		if (stock instanceof Stock_Food)
    			Hunger += stock.use(amount);
    		else if (stock instanceof Stock_Medicine)
    			Health += stock.use(amount);
    		crewAction.add("Eating");
    	} else {
    		
    	}    	
    }
    public void sleep() {
    	Health += 25;
    	Hunger += 25;
    	crewAction.add("Sleeping");
    }
    public void repair(Spaceship Ship) {
    	Hunger -= 20;
    	crewAction.add("Repairing Ship");
    	if (Rank == CrewRank.MECHANIC)
    		Ship.setHull(Ship.getHull() + 25);
    	else
    		Ship.setHull(Ship.getHull() + 15);
    }
    public void pilotShip() {
    	crewAction.add("Driving Ship");
    }
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
