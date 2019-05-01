package GUI;

import Backend.*;
import CustomUIELmt.StaticObjects;
import SpaceVessel.Outpost;
import SpaceVessel.ShipModule;
import SpaceVessel.Spaceship;
import SpaceVessel.Stock;
import SpaceVessel.Stock_Food;
import SpaceVessel.Stock_Medicine;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class LoadingFrame {
	public JFrame frame;

	Spaceship SpaceShip;
    protected Random random = new Random();
    
	Galaxy currGalaxy;
	Galaxy Gala1;
	Galaxy Gala2;
    
	void Initialize(int x, int y) {
		frame = new JFrame();
		frame.setBounds(0, 0, x, y);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setUndecorated(true);
		
		JLabel loadingScreen = new JLabel("");
		loadingScreen.setBounds(0, 0, x, y);
		loadingScreen.setVerticalAlignment(SwingConstants.BOTTOM);		
		Image IMG = StaticObjects.SelfResizeImage("/Loading.gif", this, x, y);	
		loadingScreen.setIcon(new ImageIcon(IMG));
		frame.getContentPane().add(loadingScreen);
	}
	
	//Change galaxy
	public LoadingFrame(int width, int height, Galaxy gala) {
		Initialize(width, height);
		currGalaxy = gala;
		CompleteLoading(width, height);
	}
	
	//New game
	public LoadingFrame(int width, int height, int days, Spaceship ship) {
		Initialize(width, height);
		SpaceShip = ship;
		SpaceShip.CheckCrew();

		//Ship Default Inventory
		ArrayList<Stock> modelSTOCK = generateStock();	
		for(Stock st:modelSTOCK) {
			SpaceShip.addStock(st);
		}
		
		//Ship Module
		generateModules(days);		
		
		//Generate Galaxy and Entity within them
		makePlanets();
	    try {
			hideStock(modelSTOCK);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	    makeSpaceStations();
	    CompleteLoading(width, height);
	}
	
	void CompleteLoading(int x, int y) {		
		GameEnvironment game = new GameEnvironment(x, y, currGalaxy);		
		Timer timer = new Timer(true);
		TimerTask updateIncomingMessage = new TimerTask() {
			@Override
			public void run() {				
				game.frame.setVisible(true);
				game.frame.setLocationRelativeTo(null);	

				frame.dispose();
				timer.cancel();
			}
	    };
	    
	    timer.scheduleAtFixedRate(updateIncomingMessage, 2000, 10);//delay, period	
	}
	
	private void generateModules(int days) {
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
		for (int i=0; i < (int) 2*days/3-1; i++) {
			SupMod.get(SupMod.size() -1 -i).setActive(false);
		}
		for (ShipModule mod:SupMod) {
			CoreMod.add(mod);
		}
		CoreMod.add(warp);
		SpaceShip.setModule(CoreMod);
	}
	
	private ArrayList<Stock> generateStock() {
		Stock food1 = new Stock_Food("Burger", 5, 2, "/burger.png");
		Stock food2 = new Stock_Food("Bread", 10, 4, "/bread.png");
		Stock food3 = new Stock_Food("Pizza", 15, 6, "/pizza.png");
		Stock food4 = new Stock_Food("Chicken", 20, 7, "/Chicken.png");
		Stock food5 = new Stock_Food("Steak", 25, 8, "/steak.png");
		Stock food6 = new Stock_Food("Sushi", 30, 9, "/sushi.png");
		
		Stock medi1 = new Stock_Medicine("Healing Potion", "Heart", 20, 10, "/healpotion.png");
		Stock medi2 = new Stock_Medicine("Pain Killer", "Morale", 20, 10, "/painkiller.png");
		Stock medi3 = new Stock_Medicine("Syringe", "Any disease", 10, 10, "/syringe.png");
		
		ArrayList<Stock> STOCK = new ArrayList<Stock>();
		STOCK.add(food1);STOCK.add(food2);
		STOCK.add(food3);STOCK.add(food4);
		STOCK.add(food5);STOCK.add(food6);
		STOCK.add(medi1);STOCK.add(medi2);
		STOCK.add(medi3);
		
		return STOCK;
	}
	
	private void makePlanets() {		
		//Galaxy
		currGalaxy = new Galaxy("Cadian Galaxy", SpaceShip, "/CadianGalaxy.gif");
		Gala1 = new Galaxy("Orion Galaxy" , null, "/OrionGalaxy.jpg");
		Gala2 = new Galaxy("Nemesis Tessera", null, "/NemesisGalaxy.png");
		
		//Space objects
		Planet terrestrial1 = new Planet_Terrestrial(1000, 1000, 400, "Earth", "/Earth.png");
		Planet ocean1 = new Planet_Ocean(600, 500, 250, "Ocean", "/OceanPlanet.png");
		Planet colorful1 = new Planet_ColorfulDrawf(1800, 300, 300, "ODBE-35X","/ColorfulPlanet1.png");
		Planet gasgiant1 = new Planet_GasGiant(200, 180, 350, "Saturn", "/saturn.png");
		currGalaxy.addSpaceObjects(terrestrial1);
		currGalaxy.addSpaceObjects(ocean1);
		currGalaxy.addSpaceObjects(colorful1);
		currGalaxy.addSpaceObjects(gasgiant1);
		
		Planet terrestrial2 = new Planet_Terrestrial(1000, 1000, 400, "Earth", "/Earth.png");
		Planet ocean2 = new Planet_Ocean(600, 500, 250, "Ocean", "/OceanPlanet.png");
		Planet colorful2 = new Planet_ColorfulDrawf(1800, 300, 300, "ODBE-35X","/ColorfulPlanet1.png");
		Planet gasgiant2 = new Planet_GasGiant(200, 180, 350, "Saturn", "/saturn.png");
		Gala1.addSpaceObjects(terrestrial2);
		Gala1.addSpaceObjects(ocean2);
		Gala1.addSpaceObjects(colorful2);
		Gala1.addSpaceObjects(gasgiant2);
		
		Planet terrestrial3 = new Planet_Terrestrial(1000, 1000, 400, "Earth", "/Earth.png");
		Planet ocean3 = new Planet_Ocean(600, 500, 250, "Ocean", "/OceanPlanet.png");
		Planet colorful3 = new Planet_ColorfulDrawf(1800, 300, 300, "ODBE-35X","/ColorfulPlanet1.png");
		Planet gasgiant3 = new Planet_GasGiant(200, 180, 350, "Saturn", "/saturn.png");
		Gala2.addSpaceObjects(terrestrial3);
		Gala2.addSpaceObjects(ocean3);
		Gala2.addSpaceObjects(colorful3);
		Gala2.addSpaceObjects(gasgiant3);
	}
	
	private void makeSpaceStations() {
		Outpost post1 = new Outpost(200, 300, 100, 100, "Eldar TradePost", "/EldarSpaceStation.png");
		Outpost post2 = new Outpost(700, 800, 100, 100, "SpaceMarine Shipyard", "/SpaceMarineStation.png");
		
		BlackHole hole1a = new BlackHole(20, 30, 150, 150, "MegaBlack", "/BlueHole.png", Gala1);
		BlackHole hole1b = new BlackHole(20, 30, 150, 150, "MegaBlack", "/BlueHole.png", currGalaxy);
		
		BlackHole hole2a = new BlackHole(1450, 700, 200, 200, "MediumBlue", "/smallBlueHole.png", Gala2);
		BlackHole hole2b = new BlackHole(1450, 700, 200, 200, "MediumBlue", "/smallBlueHole.png", Gala1);
		
		BlackHole hole3a = new BlackHole(1450, 100, 200, 200, "MediumBlue", "/smallBlueHole.png", currGalaxy);
		BlackHole hole3b = new BlackHole(1450, 100, 200, 200, "MediumBlue", "/smallBlueHole.png", Gala2);
		
		currGalaxy.addSpaceObjects(0, post1);
		Gala1.addSpaceObjects(0, post2);
		
		currGalaxy.addSpaceObjects(1, hole1a);
		Gala1.addSpaceObjects(1, hole1b);
		
		Gala1.addSpaceObjects(1, hole2a);
		Gala2.addSpaceObjects(1, hole2b);
		
		Gala2.addSpaceObjects(1, hole3a);
		currGalaxy.addSpaceObjects(1, hole3b);
	}

	private void hideStock(ArrayList<Stock> modelSTOCK) throws CloneNotSupportedException {
		//Hide modules of ship
		ArrayList<ShipModule> missingModule = new ArrayList<ShipModule>();
		for (ShipModule mod:SpaceShip.getModuleList()) {
			if (!mod.isActive())
			missingModule.add(mod);
		}
		
		ArrayList<Entity> SpaceObjects = new ArrayList<Entity>();
		
		for (Entity en:currGalaxy.getSpaceObjects()) {
			SpaceObjects.add(en);
		}
		for (Entity en:Gala1.getSpaceObjects()) {
			SpaceObjects.add(en);
		}
		for (Entity en:Gala2.getSpaceObjects()) {
			SpaceObjects.add(en);
		}
		
		
		for (Entity en:SpaceObjects) {
			if (en instanceof Planet) {
				if (missingModule.size() > 0) {
					((Planet) en).setHiddenTreasure(missingModule.get(0));
					missingModule.remove(0);
				} else {
					int index = random.nextInt(8);
					int randomInt = random.nextInt(15);
					Stock st = modelSTOCK.get(index);
					Stock cloneST = new Stock_Food("", 0, 0, "/bread.png");
					if (st instanceof Stock_Medicine) {
						cloneST = ((Stock_Medicine) st).clone();
						cloneST.setAmount(randomInt);
					} else if (st instanceof Stock_Food) {
						cloneST = ((Stock_Food) st).clone();
						cloneST.setAmount(randomInt);
					}
				    ((Planet) en).setHiddenTreasure(cloneST);
				}				
			}
		}
	}
}
