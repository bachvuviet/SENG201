package UnitTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Backend.Galaxy;
import SpaceVessel.*;

class Crew_Inventory {

	private Spaceship testShip;
	private Outpost tradePost;

	ArrayList<Stock> STOCK = new ArrayList<Stock>();
	ArrayList<Crew> tempCrew = new ArrayList<Crew>();
	@BeforeEach
	void init() {
		//Crew
		Galaxy.maxFuel = 800;
		Galaxy.maxHull = 100;
		Galaxy.maxTurn = 10;
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
		tradePost = new Outpost(500, 500, 10, 10, "Eldar Corsair", "/Stock/bread.png");

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
			tradePost.addStock(st);
		}
	}
	
	@Test
	void testGetCrewofShip() {
		assertEquals(4, testShip.getCrewList().size());
		assertEquals("Kirk", testShip.getCrewList().get(0).getName());
	}
	
	@Test
	void BoostFuel() {
		Crew newCrew4 = new Crew("Blah", CrewRank.HELMS_MAN, null);
		tempCrew.add(newCrew4);
		testShip.CheckCrew();
		assertEquals(1000, testShip.getFuel());
		assertEquals(100, testShip.getHull());
		assertEquals(5, testShip.getCrewList().size());
	}
	
	@Test
	void BoostHull() {	
		Crew newCrew5 = new Crew("Blah", CrewRank.SCIENTIST, null);
		tempCrew.add(newCrew5);
		testShip.CheckCrew();
		assertEquals(800, testShip.getFuel());
		assertEquals(150, testShip.getHull());
		assertEquals(5, testShip.getCrewList().size());
	}
	
	@Test
	void shipMovement() {	    
	    testShip.forward();
	    assertEquals(195, testShip.getY());
	    testShip.forward();
	    assertEquals(190, testShip.getY());
	    testShip.reverse();
	    testShip.reverse();
	    assertEquals(194, testShip.getY());
	    testShip.toPort();
	    testShip.turning = false;
	    testShip.forward();
	    assertEquals(194, testShip.getY());
	    assertEquals(195, testShip.getX());
	    testShip.toStarBoard();
	    testShip.turning = false;//Key release
	    testShip.toStarBoard();
	    testShip.turning = false;
	    assertEquals(194, testShip.getY());
	    assertEquals(195, testShip.getX());
	    assertEquals(3, testShip.getDirect());
	    testShip.forward();
	    testShip.forward();
	    assertEquals(194, testShip.getY());
	    assertEquals(205, testShip.getX());
	    
	    assertEquals(771, testShip.getFuel());
	}
	
	@Test
	void TestInventory() {
		assertEquals(9, testShip.getInventory().size());
	}
	
	@Test
	void CrewSleep() {
		Crew cr = testShip.getCrewList().get(0);
		assertEquals(125, cr.getHunger());
		assertEquals(125, cr.getMorale());
		assertEquals(125, cr.getHealth());
		
		//-20 Hunger, +25 HP, +25 Morale
		cr.sleep();
		assertEquals(105, cr.getHunger());
		assertEquals(150, cr.getMorale());
		assertEquals(130, cr.getHealth());
	}
	
	@Test
	void CrewEat() {
		Crew cr = testShip.getCrewList().get(0);
		cr.useSupply(5, testShip.getInventory().get(0));//eat
		assertEquals(125, cr.getHunger());//Eat when already full
		assertEquals(125, cr.getHealth());
		assertEquals(125, cr.getMorale());
		
		cr.sleep();
		cr.useSupply(2, testShip.getInventory().get(0));//eat
		assertEquals(105, cr.getHunger());//Eat when not full
		
	}
	
	@Test
	void CrewHeal() {
		Crew cr = testShip.getCrewList().get(0);
		cr.useSupply(5, testShip.getInventory().get(6));//heal
		assertEquals(125, cr.getHealth());//Not heal, max health
		
		cr.useSupply(2, testShip.getInventory().get(7));//morale
		assertEquals(125, cr.getHealth());//Not heal, max moral
		
		//Test minus action to increase after
		cr.pilotShip(); cr.pilotShip(); cr.pilotShip();
		assertEquals(35, cr.getMorale());
		
		cr.useSupply(2, testShip.getInventory().get(7));
		assertEquals(35, cr.getHunger());
		assertEquals(125, cr.getHealth());
		assertEquals(75, cr.getMorale());//Heal, not max morale
		
		cr.useSupply(2, testShip.getInventory().get(8));//desease cure
		assertEquals(35, cr.getHunger());
		assertEquals(125, cr.getHealth());//Not heal, max health
		assertEquals(95, cr.getMorale());
	}
	
	@Test
	void CrewRepair() {
		Crew cr = testShip.getCrewList().get(0);
		cr.repair(testShip);
		// -15 Morale, -20 Hunger
		
		assertEquals(105, cr.getHunger());
		assertEquals(110, cr.getMorale());
		assertEquals(125, cr.getHealth());
		assertEquals(115, testShip.getHull());
		
		Crew cre = testShip.getCrewList().get(1);
		cre.repair(testShip);

		assertEquals(140, testShip.getHull());
	}
	
	@Test
	void CrewPilot() {
		Crew cr = testShip.getCrewList().get(0);
		cr.pilotShip();
		assertEquals(1, cr.getCrewActivity().size());
		assertEquals("Pilot", cr.getCrewActivity().get(0));
	}
	
	@Test
	void Trade() {
		testShip.changeStockAmount(10, STOCK.get(5));
		assertEquals(15, testShip.getInventory().get(5).getAmount());
		testShip.changeStockAmount(-5, STOCK.get(4));
		assertEquals(0, testShip.getInventory().get(4).getAmount());
		testShip.changeStockAmount(5, STOCK.get(7));
		assertEquals(10, testShip.getInventory().get(7).getAmount());
	}
	
	@Test
	void ToStrigs() {
		assertEquals(false, testShip.enoughPilot());
		assertNotEquals("", testShip.getModules());
		assertNotEquals("", testShip.ShipStatus());

		Crew cr = testShip.getCrewList().get(0);
		assertNotEquals("", cr.toString());
		
		Stock st = testShip.getInventory().get(0);
		assertNotEquals("", st.toString());
		assertNotEquals("", st.toStringPrice());
		assertNotEquals("", st.getStockStatus());
		
	}
} 
