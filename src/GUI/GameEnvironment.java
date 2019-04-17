package GUI;

import Backend.*;
import SpaceVessel.*;
import CustomUIELmt.StaticObjects;

import java.awt.Image;
import java.util.ArrayList;
//import java.util.Timer;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.Font;

/** Class Intro:
 * @author Bach Vu, Linh Luu
 * @category SpaceShip game
 * @version 0.30
 * @location Lab133
 * This is the third frame, player actually start their game 
 * Storyline and HowToPlay added here
 * 
 * Start Game in a strange Galaxy, has multiple Galaxy panel overlaps 
 * Only generate Graphics and Space Objects, don't process user input
 */
public class GameEnvironment {
	public JFrame frame;
	public JProgressBar Fuel;
	public JProgressBar Hull;
	public JLabel lblDay = new JLabel("");
	
	/** All Space objects (from Backend package) is stored here*/
	public ArrayList<Entity> SpaceObjects = new ArrayList<Entity>();
	/** All Galaxy Panel is stored here*/
	public ArrayList<GalaxyPanel> Galaxy = new ArrayList<GalaxyPanel>();
	/** Spaceship generated from MissionFrame*/
	public Spaceship SpaceShip;

	/**
	 * Initialize Gaming environment
	 * Initialize default stock for Spaceship
	 * Initialize default Space objects
	 * 
	 * @param x Width of the frame (always fullscreen)
	 * @param y Height of the frame (always fullscreen)
	 * @param ship Receive Spaceship object pass from Mission Frame
	 */
	public GameEnvironment(int x, int y, Spaceship ship) {
		frame = new JFrame();
		frame.setBounds(0, 0, x, y);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setUndecorated(true);
		UIManager.put("ProgressBar.selectionForeground", Color.YELLOW);
	    UIManager.put("ProgressBar.selectionBackground", Color.BLUE);
		
		//Ship Default Inventory
		SpaceShip = ship;
		for(Stock st:generateStock()) {
			SpaceShip.addStock(st);
		}
		makeSpaceObjects();
		
		
		//Control Panel
		JPanel controlPan = new JPanel();
		controlPan.setBounds(x-350, y-150, 350, 150);
		controlPan.setBackground(new Color(255, 255, 255));
		controlPan.setLayout(null);
		
		JLabel lblFuel = new JLabel("FUEL Left:");
		lblFuel.setBounds(10, 10, 90, 20);
		lblFuel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblFuel.setFont(new Font("Cambria", Font.BOLD, 14));
		
		Fuel = new JProgressBar(0, y/2);
		Fuel.setBounds(100, 10, 240, 20);
		Fuel.setValue(y/2);
		Fuel.setStringPainted(true);
		
		JLabel lblHull = new JLabel("Ship Hull:");
		lblHull.setBounds(10, 40, 90, 20);
		lblHull.setVerticalAlignment(SwingConstants.BOTTOM);
		lblHull.setFont(new Font("Cambria", Font.BOLD, 14));
		
		Hull = new JProgressBar(0, SpaceShip.getHull());
		Hull.setBounds(100, 40, 240, 20);
		Hull.setValue(SpaceShip.getHull());
		Hull.setStringPainted(true);
		Hull.setToolTipText("");
		Hull.setForeground(Color.RED);
		
		lblDay.setText("Day on mission: 1/"+SpaceShip.daysOnMission);
		lblDay.setBounds(10, 70, 330, 20);
		lblDay.setVerticalAlignment(SwingConstants.BOTTOM);
		lblDay.setFont(new Font("Cambria", Font.BOLD, 14));
				
		JButton btnEndTurn = new JButton("Next Day (Enter)");
		btnEndTurn.setBounds(10, 110, 330, 30);
		btnEndTurn.setEnabled(false);
		
		controlPan.add(lblFuel);
		controlPan.add(Fuel);
		controlPan.add(lblHull);
		controlPan.add(Hull);
		controlPan.add(lblDay);
		controlPan.add(btnEndTurn);
		
		//Galaxy
		GalaxyPanel galaxy = new GalaxyPanel(x, y, frame, this);		
		galaxy.setBounds(0, 0, x ,y);
		galaxy.setOpaque(false);

		frame.getContentPane().add(galaxy);
		frame.getContentPane().add(controlPan);	
		
		for (Entity en:SpaceObjects) {
			JLabel lblTemp = new JLabel(new ImageIcon(en.getImage()));
			lblTemp.setBounds(en.getX(), en.getY(), en.getWidth(), en.getHeight());
			frame.getContentPane().add(lblTemp);
		}
		
		//Background
		JLabel galaBack = new JLabel("");
		galaBack.setBounds(0, 0, x, y);
		galaBack.setVerticalAlignment(SwingConstants.BOTTOM);
		Image IMG = StaticObjects.SelfResizeImage("../giphy.gif", this, x, y);	
		galaBack.setIcon(new ImageIcon(IMG));
		frame.getContentPane().add(galaBack);
	}
	
	/**
	 * Default stock list generate for ship
	 * @return ArrayList<Stock> before adding the whole list to ship
	 */
	private ArrayList<Stock> generateStock(){
		Stock food1 = new Stock_Food("Burger", 10, "../burger.png");
		Stock food2 = new Stock_Food("Bread", 10, "../bread.png");
		Stock food3 = new Stock_Food("Pizza", 10, "../pizza.png");
		Stock food4 = new Stock_Food("Chicken", 10, "../Chicken.png");
		Stock food5 = new Stock_Food("Steak", 10, "../steak.png");
		Stock food6 = new Stock_Food("Sushi", 10, "../sushi.png");
		
		Stock medi1 = new Stock_Medicine("Healing Potion", "Heart", 10, "../healpotion.png");
		Stock medi2 = new Stock_Medicine("Pain Killer", "Morale", 10, "../painkiller.png");
		Stock medi3 = new Stock_Medicine("Syringe", "Any disease", 10, "../syringe.png");
		
		ArrayList<Stock> STOCK = new ArrayList<Stock>();
		STOCK.add(food1);STOCK.add(food2);
		STOCK.add(food3);STOCK.add(food4);
		STOCK.add(food5);STOCK.add(food6);
		STOCK.add(medi1);STOCK.add(medi2);
		STOCK.add(medi3);
		
		return STOCK;
	}
	
	/**
	 * Add Celestial bodies and Outpost to SpaceObjects
	 */
	private void makeSpaceObjects() {		
		//Space objects
		Outpost post1 = new Outpost(200, 300, 100, 100, "Eldar TradePost", "../EldarSpaceStation.png");
		SpaceObjects.add(post1);
		Outpost post2 = new Outpost(700, 800, 100, 100, "SpaceMarine Shipyard", "../SpaceMarineStation.png");
		SpaceObjects.add(post2);
		BlackHole hole1 = new BlackHole(20, 30, 150, 150, "MegaBlack", "../BlueHole.png");
		SpaceObjects.add(hole1);
		BlackHole hole2 = new BlackHole(1450, 700, 200, 200, "MediumBlue", "../smallBlueHole.png");
		SpaceObjects.add(hole2);
		Planet earth = new Planet_Terrestrial(1000, 1000, 400, "Earth", "../Earth.png");
		SpaceObjects.add(earth);
		Planet oceanPlanet = new Planet_Ocean(600, 500, 250, "Ocean", "../Ocean Planet.png");
		SpaceObjects.add(oceanPlanet);
		Planet colorfulPlanet = new Planet_ColorfulDrawf(1800, 300, 300, "ODBE-35X","../Colorful Planet1.png");
		SpaceObjects.add(colorfulPlanet);
		Planet saturn = new Planet_GasGiant(200, 180, 350, "Saturn", "../saturn.png");
		SpaceObjects.add(saturn);
	}
}