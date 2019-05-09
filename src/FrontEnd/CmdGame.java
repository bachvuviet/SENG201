package FrontEnd;

import SpaceVessel.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import Backend.Galaxy;
public class CmdGame {
	private static Spaceship ship;
	ArrayList<Stock> STOCK = new ArrayList<Stock>();
	ArrayList<Crew> crew = new ArrayList<Crew>();
	int day;
	int action1;
	int action2;
	int supply;
	//int action2;
	
	public CmdGame() {
		checkShip();
	}
	
	public void checkShip() {
        //Crew
		Galaxy.maxFuel = 0;
		Galaxy.maxHull = 100;
		Galaxy.maxTurn = 10;
		Crew newCrew1 = new Crew("Kirk", CrewRank.CAPTAIN, null);
		Crew newCrew2 = new Crew("Spock", CrewRank.MECHANIC, null);
		Crew newCrew3 = new Crew("Dr.Strange", CrewRank.DOCTOR, null);
		Crew newCrew4 = new Crew("Groot", CrewRank.CHEF, null);
	
		crew.add(newCrew1);
		crew.add(newCrew2);
		crew.add(newCrew3);
		crew.add(newCrew4);
		
		//Ship
		ship = new Spaceship(200,200, "KMS Tirpitz", crew);
		getModules();
		generateModules();
		
		//Stock
		Stock food1 = new Stock_Food("Burger", 5, 2, "/Stock/burger.png");
		Stock food2 = new Stock_Food("Bread", 10, 4, "/Stock/bread.png");
		Stock food3 = new Stock_Food("Pizza", 15, 6, "/Stock/pizza.png");
		Stock food4 = new Stock_Food("Chicken", 20, 7, "/Stock/Chicken.png");
		Stock food5 = new Stock_Food("Steak", 25, 8, "/Stock/steak.png");
		Stock food6 = new Stock_Food("Sushi", 30, 9, "/Stock/sushi.png");
		
		Stock medi1 = new Stock_Medicine("Healing Potion", "Heart", 20, 10, "/Stock/healpotion.png");
		Stock medi2 = new Stock_Medicine("Pain Killer", "Morale", 20, 10, "/Stock/painkiller.png");
		Stock medi3 = new Stock_Medicine("Syringe", "Any disease", 10, 10, "/Stock/syringe.png");
		
		STOCK.add(food1);STOCK.add(food2);
		STOCK.add(food3);STOCK.add(food4);
		STOCK.add(food5);STOCK.add(food6);
		STOCK.add(medi1);STOCK.add(medi2);
		STOCK.add(medi3);
		for(Stock st:STOCK) {
			ship.addStock(st);
	    }
	}
	
	public String getModules() {
		int active = 0;
		String mystr = "";
		for(ShipModule module:ship.getModuleList()) {
			if (module.isActive()) {
				active += 1;
			}
			mystr += module.modStatus() + "\n";
		}
		
		mystr = "\n" + "Modules: " + active  + "/" + ship.getModuleList().size()  + "\n" + mystr;
		return mystr;
	}
	
	private void generateModules() {
		ShipModule thrust1 = new ShipModule("Engine-Left", "Speed", 50);
		ShipModule thrust2 = new ShipModule("Engine-Right", "Speed", 50);
		ShipModule thrust3 = new ShipModule("Engine-Center", "Speed", 100);
		ShipModule engine1 = new ShipModule("Generator-Main", "Speed", 200);
		ShipModule engine2 = new ShipModule("Generator-Support", "Speed", 100);
		ShipModule ammour = new ShipModule("Amour Belt", "Health", 200);
		ShipModule bridge = new ShipModule("Bridge", "CrewHealth", 50);
		ShipModule fusioner = new ShipModule("Fusion3D-printer", "CrewHunger", 50);
		ShipModule warp = new ShipModule("Warp-Engine", "Complete", 0);
		warp.setActive(false);
		ArrayList<ShipModule> CoreMod = new ArrayList<ShipModule>(Arrays.asList(thrust3, ammour, bridge));//Core
		ArrayList<ShipModule> SupMod = new ArrayList<ShipModule>(Arrays.asList(thrust1, thrust2, engine1, engine2, fusioner));
		Collections.shuffle(SupMod);
		for (int i=0; i < (int) 2* Galaxy.maxTurn/3-1; i++) {
			SupMod.get(SupMod.size() -1 -i).setActive(false);
		}
		for (ShipModule mod:SupMod) {
			CoreMod.add(mod);
		}
		CoreMod.add(warp);
		ship.setModule(CoreMod);
	}
	
	public void shipParts() {
		System.out.println(getModules());
	}
	
    public void scanDays() {
		@SuppressWarnings("resource")
		Scanner myObj = new Scanner(System.in);
		boolean blah = true;
		while (blah) {
			System.out.print("Enter 3-10 day(s): ");
		    day = myObj.nextInt();
		    if (day < 3 || day > 10) {
			    System.out.println("Invalid!");
			    blah = true;
		    } else {
		      blah = false;
		    }
		}
		
	}
    
    
    public void printStr(int count) {
    	String str = "";
    	    //str += "\n";
    		str += "Day " + count + "\n";
    		str += "Choose 2 actions via Sleep, choose 1" + "\n";
    		str += "1.Sleep \t 2.Repair \t 3.Pilot \t 4.Use supplements" + "\n";
    		System.out.println(str);
    	
    	
    }
    
    public void scanFoodList() {
    	String str = "";
    	str += "List of supplements \n";
    	str += "Food:     {0.Burger, 1.Bread, 2.Pizza, 3.Chicken, 4.Steak, 5.Sushi} \n";
    	str += "Medicine: {6.Healing Potion, 7. Pain Killer, 8.Syringe}";
    	System.out.println(str);
    	@SuppressWarnings("resource")
		Scanner scan2 = new Scanner(System.in);
    	System.out.print("Enter supplement: ");
    	supply = scan2.nextInt();
    }
    
    
    public void scanActions(Crew action, Spaceship ship) {
    	
    	@SuppressWarnings("resource")
		Scanner scan1 = new Scanner(System.in);
    	@SuppressWarnings("resource")
		Scanner scan3 = new Scanner(System.in);
    	for (Crew cr: crew) {
    		for (int count=1; count <= day; count ++) {
        		System.out.println("Crew " + cr.getRank() + " +1:");
        		printStr(count);
        		System.out.print("Action1: ");
        		action1 = scan1.nextInt();
        		System.out.print("\nAction2: ");
        		action2 = scan3.nextInt();
        		if (action1 == 4 || action2 == 4) {
        			scanFoodList();
        		}
        		
        		
        	    crewActions(action, ship);
        	    
        	}
    	}
    	
    	
    }

    
    public void crewActions(Crew action, Spaceship ship) {
    	//Action1
    	ship.CheckCrew();
    	
		switch(action1) {
		case 1:
			action.sleep();
			break;
		case 2:
			action.repair(ship);
			break;
		case 3:
			action.pilotShip();
			break;
		case 4:
			for (Crew cr: crew) {
				cr.useSupply(5, ship.getInventory().get(supply));//eat
			}
			break;
		default:
			break;
	    }
		
		//Action2
		switch(action2) {
		case 1:
			action.sleep();
			break;
		case 2:
			action.repair(ship);
			break;
		case 3:
			action.pilotShip();
			break;
		case 4:
			for (Crew cr: crew) {
				cr.useSupply(5, ship.getInventory().get(supply));//eat
			}
			break;
		default:
			break;
	    }

		System.out.println("health: " + action.getHealth());
		System.out.println("hunger: " + action.getHunger());
		System.out.println("morale: " + action.getMorale());
		System.out.println(action.getCrewActivity());
    }
    
    public static void main(String[] args) {
    	CmdGame st = new CmdGame();
    	Crew crew = ship.getCrewList().get(0);//new Crew();
    	st.checkShip();
		st.scanDays();
		st.shipParts();
		st.scanActions(crew, ship);
	}
    
}
