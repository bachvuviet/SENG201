package GUI;

import Backend.*;
import CustomUIELmt.StaticObjects;
import SpaceVessel.Outpost;
import SpaceVessel.ShipModule;
import SpaceVessel.Spaceship;
import SpaceVessel.Stock;
import SpaceVessel.Stock_Food;
import SpaceVessel.Stock_Medicine;

import java.awt.Desktop;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.io.File;
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
	
	int width, height;
    
	/**
	 * Initialize the content of loading frame
	 */
	void Initialize() {
		GraphicsDevice gd[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
		width = gd[0].getDisplayMode().getWidth();
		height = gd[0].getDisplayMode().getHeight();
		
		frame = new JFrame();
		frame.setBounds(0, 0, width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setUndecorated(true);
			
		Image IMG = StaticObjects.SelfResizeImage("/Background/Loading.jpg", this, width, height);	
		JLabel loadingScreen = new JLabel(new ImageIcon(IMG));
		loadingScreen.setBounds(0, 0, width, height);
		loadingScreen.setVerticalAlignment(SwingConstants.BOTTOM);
		loadingScreen.setIcon(new ImageIcon(IMG));
		frame.getContentPane().add(loadingScreen);
	}
	
	/**
	 * Load game while galaxy has been changed
	 * @param gala Load status of current galaxy when player save before
	 * @param LoadGame Is loading game or start new game?
	 */
	//Change galaxy
	public LoadingFrame(Galaxy gala, boolean LoadGame) {
		Initialize();
		if (LoadGame) {
			gala.Load();
			gala.getShip().Load();
		}
		currGalaxy = gala;
		CompleteLoading();
	}
	
	//New game
	public LoadingFrame(Spaceship ship) {		
		Initialize();
		SpaceShip = ship;
	}
	/**
	 * Generate a new game
	 */
	public void NewMission() {
		//Ship Default Inventory
		ArrayList<Stock> modelSTOCK = generateStock();	
		for(Stock st:modelSTOCK) {
			SpaceShip.addStock(st);
		}
		
		//Ship Module
		generateModules();		
		
		//Generate Galaxy and Entity within them
		makePlanets();
	    try {
			hideStock(modelSTOCK);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();//bach
		}
	    makeSpaceStations();
	    try {
			PlayIntroVideo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	    CompleteLoading();
	}
	
	private void PlayIntroVideo() throws Exception {
		Desktop dt = Desktop.getDesktop();
    	File movie = new File(LoadingFrame.class.getProtectionDomain().getCodeSource().getLocation().getPath()+"/GameIntro.mp4");
		dt.open(movie);
		Thread.sleep(3000);
	}

	void CompleteLoading() {		
		GameEnvironment game = new GameEnvironment(width, height, currGalaxy);		
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
		SpaceShip.setModule(CoreMod);
	}
	
	private ArrayList<Stock> generateStock() {
		Stock food1 = new Stock_Food("Burger", 5, 2, 5, "/Stock/burger.png");
		Stock food2 = new Stock_Food("Bread", 10, 4, 2, "/Stock/bread.png");
		Stock food3 = new Stock_Food("Pizza", 15, 6, 0, "/Stock/pizza.png");
		Stock food4 = new Stock_Food("Chicken", 20, 7, 0, "/Stock/Chicken.png");
		Stock food5 = new Stock_Food("Steak", 25, 8, 0, "/Stock/steak.png");
		Stock food6 = new Stock_Food("Sushi", 30, 9, 0, "/Stock/sushi.png");
		
		Stock medi1 = new Stock_Medicine("Healing Potion", "Heart", 20, 10, 2, "/Stock/healpotion.png");
		Stock medi2 = new Stock_Medicine("Pain Killer", "Morale", 20, 10, 2, "/Stock/painkiller.png");
		Stock medi3 = new Stock_Medicine("Syringe", "Any disease", 10, 10, 2, "/Stock/syringe.png");
		
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
		currGalaxy = new Galaxy("Cadian Galaxy", SpaceShip, "/Background/CadianGalaxy.gif");
		Gala1 = new Galaxy("Orion Galaxy" , null, "/Background/OrionGalaxy.jpg");
		Gala2 = new Galaxy("Nemesis Tessera", null, "/Background/NemesisGalaxy.png");
		
		//Space objects
		Planet terrestrial1 = new Planet_Terrestrial(1000, 1000, 500, "Earth");
		Planet ocean1 = new Planet_Ocean(600, 500, 250, "Ocean");
		Planet colorful1 = new Planet_ColorfulDrawf(1800, 300, 300, "ODBE-35X");
		Planet gasgiant1 = new Planet_GasGiant(200, 180, 350, "Saturn");
		currGalaxy.addSpaceObjects(terrestrial1);
		currGalaxy.addSpaceObjects(ocean1);
		currGalaxy.addSpaceObjects(colorful1);
		currGalaxy.addSpaceObjects(gasgiant1);
		
		if (Galaxy.maxTurn/3 >= 2) {
			Planet terrestrial2 = new Planet_Terrestrial(800, 0, 200, "Earth");
			Planet ocean2 = new Planet_Ocean(950, 700, 250, "Ocean");
			Planet colorful2 = new Planet_ColorfulDrawf(1600, 600, 300, "ODBE-35X");
			Planet gasgiant2 = new Planet_GasGiant(300, 400, 250, "Saturn");
			Gala1.addSpaceObjects(terrestrial2);
			Gala1.addSpaceObjects(ocean2);
			Gala1.addSpaceObjects(colorful2);
			Gala1.addSpaceObjects(gasgiant2);
		}
		
		if (Galaxy.maxTurn/3 >= 3) {
			Planet terrestrial3 = new Planet_Terrestrial(100, 50, 300, "Earth");
			Planet ocean3 = new Planet_Ocean(500, 800, 400, "Ocean");
			Planet colorful3 = new Planet_ColorfulDrawf(1650, 300, 300, "ODBE-35X");
			Planet gasgiant3 = new Planet_GasGiant(1200, 380, 500, "Saturn");
			Gala2.addSpaceObjects(terrestrial3);
			Gala2.addSpaceObjects(ocean3);
			Gala2.addSpaceObjects(colorful3);
			Gala2.addSpaceObjects(gasgiant3);
		}
	}
	
	private void makeSpaceStations() {
		Outpost post1 = new Outpost(200, 300, 100, 100, "Eldar TradePost", "/Ship/EldarSpaceStation.png");
		Outpost post2 = new Outpost(700, 800, 100, 100, "SpaceMarine Shipyard", "/Ship/SpaceMarineStation.png");
		
		BlackHole hole1a = new BlackHole(1450, 500, 150, 150, "MegaBlack", Gala1);
		BlackHole hole1b = new BlackHole(1450, 500, 150, 150, "MegaBlack", currGalaxy);
		
		BlackHole hole2a = new BlackHole(50, 600, 200, 200, "MediumBlue", Gala2);
		BlackHole hole2b = new BlackHole(50, 600, 200, 200, "MediumBlue", Gala1);
		
		BlackHole hole3a = new BlackHole(1450, 100, 200, 200, "MediumBlue", currGalaxy);
		BlackHole hole3b = new BlackHole(1450, 100, 200, 200, "MediumBlue", Gala2);

		currGalaxy.addSpaceObjects(0, post1);
		Gala2.addSpaceObjects(0, post2);	
		
		if (Galaxy.maxTurn/3 >= 2) {
			currGalaxy.addSpaceObjects(1, hole1a);
			Gala1.addSpaceObjects(1, hole1b);
		}
		
		if ((Galaxy.maxTurn/3 >= 3)) {
			Gala1.addSpaceObjects(1, hole2a);
			Gala2.addSpaceObjects(1, hole2b);
			
			Gala2.addSpaceObjects(1, hole3a);
			currGalaxy.addSpaceObjects(1, hole3b);
		}	
	}

	/**
	 * 
	 * @param modelSTOCK
	 * @throws CloneNotSupportedException
	 */
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
		Collections.shuffle(SpaceObjects);
		
		for (Entity en:SpaceObjects) {
			if (en instanceof Planet) {
				if (missingModule.size() > 0) {
					((Planet) en).setTreasure(missingModule.get(0));
					missingModule.remove(0);
				} else {
					int index = random.nextInt(9);
					int randomInt = random.nextInt(11);
					Stock st = modelSTOCK.get(index);
					Stock cloneST = new Stock_Food("", 0, 0, 0, "/Stock/bread.png");
					if (st instanceof Stock_Medicine) {
						cloneST = ((Stock_Medicine) st).clone();
						cloneST.setAmount(randomInt);
					} else if (st instanceof Stock_Food) {
						cloneST = ((Stock_Food) st).clone();
						cloneST.setAmount(randomInt);
					}
				    ((Planet) en).setTreasure(cloneST);
				}				
			}
		}
	}
}
