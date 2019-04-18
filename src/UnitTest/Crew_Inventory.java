package UnitTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SpaceVessel.*;

class Crew_Inventory {

	private Spaceship testShip;
	private Outpost tradePost;
		
	@BeforeEach
	public void init() {
		//Crew
		Crew newCrew1 = new Crew("Kirk", CrewRank.CAPTAIN, 100, 100, 100, null);
		Crew newCrew2 = new Crew("Spock", CrewRank.MECHANIC, 100, 100, 100, null);
		Crew newCrew3 = new Crew("DR.Strange", CrewRank.DOCTOR, 100, 100, 100, null);
		ArrayList<Crew> tempCrew = new ArrayList<Crew>();
		tempCrew.add(newCrew1);
		tempCrew.add(newCrew2);
		tempCrew.add(newCrew3);
		
		//Ship
		testShip = new Spaceship(200,200, "KMS Tirpitz", 0, tempCrew, null);
		tradePost = new Outpost(500, 500, 10, 10, "Eldar Corsair", "/bread.png");

		//Stock
		Stock food1 = new Stock_Food("Burger", 10, "/bread.png");
		Stock food2 = new Stock_Food("Bread", 10, "/bread.png");
		Stock food3 = new Stock_Food("Pizza", 10, "/bread.png");
		Stock food4 = new Stock_Food("Chicken", 10, "/bread.png");
		Stock food5 = new Stock_Food("Steak", 10, "/bread.png");
		Stock food6 = new Stock_Food("Sushi", 10, "/bread.png");
		
		Stock medi1 = new Stock_Medicine("Healing Potion", "Heart", 10, "/bread.png");
		Stock medi2 = new Stock_Medicine("Pain Killer", "Morale", 10, "/bread.png");
		Stock medi3 = new Stock_Medicine("Syringe", "Any disease", 10, "/bread.png");
		
		ArrayList<Stock> STOCK = new ArrayList<Stock>();
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
	public void testGetCrewofShip() {
		assertEquals(3, testShip.getCrewList().size());
		assertEquals("Kirk", testShip.getCrew(0).getName());
	}
	
	@Test
	public void shipMovement() {	    
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
	}
	
	@Test
	public void CrewAction() {
		assertEquals(9, testShip.getInventory().size());
		Crew cr = testShip.getCrew(0);
		ArrayList<Stock> Stock = testShip.getInventory();
		
		cr.useSupply(5, Stock.get(0));//eat
		assertEquals(150, cr.getHunger());
		assertEquals(100, cr.getHealth());
		assertEquals(100, cr.getMorale());
		
		cr.useSupply(4, Stock.get(8));//heal
		assertEquals(140, cr.getHealth());
		assertEquals(150, cr.getHunger());
		assertEquals(100, cr.getMorale());
		
		cr.sleep();
		assertEquals(125, cr.getHunger());
		assertEquals(125, cr.getMorale());
		assertEquals(165, cr.getHealth());
		
		cr.repair(testShip);
		assertEquals(105, cr.getHunger());
		assertEquals(125, cr.getMorale());
		assertEquals(165, cr.getHealth());
		assertEquals(115, testShip.getHull());
		
		cr.pilotShip();
		assertEquals(5, cr.getCrewActivity().size());
		assertEquals("Eating", cr.getCrewActivity().get(0));
		assertEquals("Healing", cr.getCrewActivity().get(1));
		assertEquals("Sleeping", cr.getCrewActivity().get(2));
		assertEquals("Repairing Ship", cr.getCrewActivity().get(3));
		assertEquals("Driving Ship", cr.getCrewActivity().get(4));
	}
	
	@Test
	public void Trade() {
		
	}
}

