package UnitTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Backend.Galaxy;
import SpaceVessel.*;

//import SpaceVessel.*;

class EndTurn {
	Spaceship testShip;
	ArrayList<Stock> STOCK = new ArrayList<Stock>();
	ArrayList<Crew> tempCrew = new ArrayList<Crew>();
	@BeforeEach
	void init() {
		//Galaxy
		Galaxy.maxTurn = 4;
		testShip = new Spaceship(200,200, "KMS Tirpitz", null);
		Crew newCrew1 = new Crew("Kirk", CrewRank.CAPTAIN, null);
		Crew newCrew2 = new Crew("Spock", CrewRank.MECHANIC, null);
		Crew newCrew3 = new Crew("DR.Strange", CrewRank.DOCTOR, null);
		Crew newCrew4 = new Crew("Dan", CrewRank.CHEF, null);
		
		tempCrew.add(newCrew1);
		tempCrew.add(newCrew2);
		tempCrew.add(newCrew3);
		tempCrew.add(newCrew4);
		
		//Ship
		testShip = new Spaceship(200,200, "KMS Tirpitz", tempCrew);

		//Stock
		Stock food1 = new Stock_Food("Burger", 5, 2, 5, "/Stock/burger.png");
		Stock food2 = new Stock_Food("Bread", 10, 4, 5, "/Stock/bread.png");
		Stock food3 = new Stock_Food("Pizza", 15, 6, 5, "/Stock/pizza.png");
		Stock food4 = new Stock_Food("Chicken", 20, 7, 5, "/Stock/Chicken.png");
		Stock food5 = new Stock_Food("Steak", 25, 8, 5, "/Stock/steak.png");
		Stock food6 = new Stock_Food("Sushi", 30, 9, 5, "/Stock/sushi.png");
		
		Stock medi1 = new Stock_Medicine("Healing Potion", "Health", 20, 10, 5, "/Stock/healpotion.png");
		Stock medi2 = new Stock_Medicine("Pain Killer", "Morale", 20, 10, 5, "/Stock/painkiller.png");
		Stock medi3 = new Stock_Medicine("Syringe", "Any disease", 10, 10, 5, "/Stock/syringe.png");
		
		STOCK.add(food1);STOCK.add(food2);
		STOCK.add(food3);STOCK.add(food4);
		STOCK.add(food5);STOCK.add(food6);
		STOCK.add(medi1);STOCK.add(medi2);
		STOCK.add(medi3);
		for(Stock st:STOCK) {
			testShip.addStock(st);
		}
	}	

	@Test
	void testEvent() {
		
	}

	@Test
	void testTurn(){
		testShip.EndTurn();
		assertEquals(2, Spaceship.daysOnMission);
		
		testShip.EndTurn();
		assertEquals(3, Spaceship.daysOnMission);
		
		testShip.EndTurn();
		assertEquals(4, Spaceship.daysOnMission);
		
		//Can't move on next day
		testShip.EndTurn();
		assertEquals(4, Spaceship.daysOnMission);
	}
	
	@Test
	void testEffect() {
		
	}
}
