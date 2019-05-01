package UnitTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SpaceVessel.*;

class Crew_Inventory {

	private Spaceship testShip;
	private Outpost tradePost;

	ArrayList<Stock> STOCK = new ArrayList<Stock>();
	ArrayList<Crew> tempCrew = new ArrayList<Crew>();
	@BeforeEach
	public void init() {
		//Crew
		Crew newCrew1 = new Crew("Kirk", CrewRank.CAPTAIN, null);
		Crew newCrew2 = new Crew("Spock", CrewRank.MECHANIC, null);
		Crew newCrew3 = new Crew("DR.Strange", CrewRank.DOCTOR, null);
		
		tempCrew.add(newCrew1);
		tempCrew.add(newCrew2);
		tempCrew.add(newCrew3);
		
		//Ship
		testShip = new Spaceship(200,200, "KMS Tirpitz", 0, 100, tempCrew);
		tradePost = new Outpost(500, 500, 10, 10, "Eldar Corsair", "/bread.png");

		//Stock
		Stock food1 = new Stock_Food("Burger", 5, 2, "/burger.png");
		Stock food2 = new Stock_Food("Bread", 10, 4, "/bread.png");
		Stock food3 = new Stock_Food("Pizza", 15, 6, "/pizza.png");
		Stock food4 = new Stock_Food("Chicken", 20, 7, "/Chicken.png");
		Stock food5 = new Stock_Food("Steak", 25, 8, "/steak.png");
		Stock food6 = new Stock_Food("Sushi", 30, 9, "/sushi.png");
		
		Stock medi1 = new Stock_Medicine("Healing Potion", "Heart", 20, 10, "/healpotion.png");
		Stock medi2 = new Stock_Medicine("Pain Killer", "Morale", 20, 10, "/painkiller.png");
		Stock medi3 = new Stock_Medicine("Syringe", "Any disease", 10, 10, "/syringe.png");
		
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
		assertEquals(125, cr.getHunger());
		assertEquals(100, cr.getHealth());
		assertEquals(100, cr.getMorale());
		
		cr.useSupply(4, Stock.get(8));//heal
		assertEquals(125, cr.getHunger());
		assertEquals(140, cr.getHealth());
		assertEquals(100, cr.getMorale());
		
		cr.sleep();
		assertEquals(105, cr.getHunger());
		assertEquals(125, cr.getMorale());
		assertEquals(165, cr.getHealth());
		
		cr.repair(testShip);
		assertEquals(85, cr.getHunger());
		assertEquals(110, cr.getMorale());
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
		testShip.changeStockAmount(10, STOCK.get(5));
		assertEquals(20, testShip.getInventory().get(5).getAmount());
		testShip.changeStockAmount(-10, STOCK.get(4));
		assertEquals(0, testShip.getInventory().get(4).getAmount());
		testShip.changeStockAmount(-5, STOCK.get(7));
		assertEquals(10, testShip.getInventory().get(7).getAmount());
	}
	
	@Test
	public void SpecialCrews() {
		Crew newCrew4 = new Crew("Blah", CrewRank.DOCTOR, null);
		tempCrew.add(newCrew4);
		testShip.CheckCrew();
		assertEquals(100, testShip.getHull());
		assertEquals(100, testShip.getFuel());
	}
}
