package Lab4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

public class GreenAlienTransporter {
	private ArrayList<GreenAlien> passengers;
	private String nameTransport;

    public GreenAlienTransporter(String name) {
		// TODO Auto-generated constructor stub
    	passengers = new ArrayList<GreenAlien>();
    	nameTransport = name;
	}
    
	public boolean addPassenger(GreenAlien newPassenger) {
		if (!passengers.contains(newPassenger)) {
			return passengers.add(newPassenger);			
		}
		else{
			return false;
		}
	}	
	public boolean removePassenger(GreenAlien toRemove) {
		return passengers.remove(toRemove);
	}	
	public int howManyPassengers() {
		return passengers.size();
	}
	
	public void listPassengers() {
		String returnString = "The passengers on "+nameTransport+" are:\n"; 
		for (GreenAlien passenger: passengers) {
			returnString += passenger.getName();
			returnString += ", ";
		}
		System.out.println(returnString);
	}
	
	public int countEyes() {
		int eyes = 0;
		for (GreenAlien passenger: passengers) {
			eyes += passenger.eyeCount;
		}
		return eyes;
	}
	
	public ArrayList<String> shoppingList(){
		ArrayList<String> shoppingList = new ArrayList<String>();
		for (GreenAlien passenger: passengers) {
			shoppingList.add(passenger.getCan());
		}
		return shoppingList;
	}
	
	public void printDetails() {
		listPassengers();
		System.out.println("The number of eyes on this transporter is: "+countEyes());

		ArrayList<String> shoppingList = shoppingList();
		TreeSet<String> mySet = new TreeSet<String>(shoppingList);//Set sort and has un-duplicate values
		String prtstr = "The favourites of this group are:\n";
		for (String candy:mySet) {
			prtstr += candy + "(" + Collections.frequency(shoppingList,candy) + ")";
			prtstr += ", ";
		}
		System.out.print(prtstr);				
	}
}
