package UnitTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Backend.*;
import SpaceVessel.*;

class SpaceObjects {
	Spaceship testShip;
	Galaxy currGalaxy;
	ArrayList<Galaxy> testGalaList = new ArrayList<Galaxy>();
	
	@BeforeEach
	void init() {
		//Galaxy
		testShip = new Spaceship(200,200, "KMS Tirpitz", null);
		currGalaxy = new Galaxy("Cadian Galaxy", testShip, "/Background/CadianGalaxy.gif");
		Galaxy Gala1 = new Galaxy("Orion Galaxy" , null, "/Background/OrionGalaxy.jpg");
		testGalaList.add(currGalaxy);
		testGalaList.add(Gala1);
		
		//Space objects
		Planet terrestrial1 = new Planet_Terrestrial(1000, 1000, 400, "Earth");
		Planet ocean1 = new Planet_Ocean(600, 500, 250, "Ocean");
		currGalaxy.addSpaceObjects(terrestrial1);
		currGalaxy.addSpaceObjects(ocean1);
		
		Planet terrestrial2 = new Planet_Terrestrial(800, 0, 200, "Earth");
		Planet ocean2 = new Planet_Ocean(950, 700, 250, "Ocean");
		Gala1.addSpaceObjects(terrestrial2);
		Gala1.addSpaceObjects(ocean2);
		
		Outpost post1 = new Outpost(200, 300, 100, 100, "Eldar TradePost", "/Ship/EldarSpaceStation.png");
		
		BlackHole hole1 = new BlackHole(20, 30, 150, 150, "MegaBlack", Gala1);	
		BlackHole hole2 = new BlackHole(1450, 100, 200, 200, "MediumBlue", currGalaxy);
		
		currGalaxy.addSpaceObjects(0, post1);
		
		currGalaxy.addSpaceObjects(1, hole1);
		Gala1.addSpaceObjects(1, hole2);
	}
	
	
	@Test
	void testJumpGalaxy() {
		Entity en = currGalaxy.getSpaceObjects().get(1);
		Galaxy gala = ((BlackHole) en).JumptoGalaxy(testShip);
		currGalaxy.deleteShip();
		
		assertEquals(null, currGalaxy.getShip());
		assertNotNull(gala.getShip());
	}

}
