package GUI;

import Backend.*;
import SpaceVessel.*;
import CustomUIELmt.StaticObjects;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

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

/** 
 * This is the third frame, player actually start their game.
 * Storyline and HowToPlay added here.
 * <p>
 * Start Game in a strange Galaxy, has multiple Galaxy panel overlaps.
 * Only generate Graphics and Space Objects, don't process user input.
 * @author Bach Vu, Linh Luu
 * @version 0.30
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
    protected Random random = new Random();
	

	/**
	 * Initialize Gaming environment
	 * Initialize default stock for Spaceship
	 * Initialize default Space objects
	 * 
	 * @param x Width of the frame (always fullscreen)
	 * @param y Height of the frame (always fullscreen)
	 * @param ship Receive Spaceship object pass from Mission Frame
	 * @throws CloneNotSupportedException 
	 */
	public GameEnvironment(int x, int y, Spaceship ship) throws CloneNotSupportedException {
		frame = new JFrame();
		frame.setBounds(0, 0, x, y);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setUndecorated(true);
		UIManager.put("ProgressBar.selectionForeground", Color.YELLOW);
	    UIManager.put("ProgressBar.selectionBackground", Color.BLUE);
		
		//Ship Default Inventory
		SpaceShip = ship;
		CheckCrew();
		ArrayList<Stock> modelSTOCK = generateStock();
		for(Stock st:modelSTOCK) {
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
		
		Fuel = new JProgressBar(0, SpaceShip.getFuel());
		Fuel.setBounds(100, 10, 240, 20);
		Fuel.setValue(Fuel.getMaximum());
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
			if (en instanceof Planet) {
				int index = random.nextInt(8);
				int randomInt = random.nextInt(30);
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
			JLabel lblTemp = new JLabel(new ImageIcon(en.getImage()));
			lblTemp.setBounds(en.getX(), en.getY(), en.getWidth(), en.getHeight());
			frame.getContentPane().add(lblTemp);
		}
		
		//Background
		JLabel galaBack = new JLabel("");
		galaBack.setBounds(0, 0, x, y);
		galaBack.setVerticalAlignment(SwingConstants.BOTTOM);
		Image IMG = StaticObjects.SelfResizeImage("/giphy.gif", this, x, y);	
		galaBack.setIcon(new ImageIcon(IMG));
		frame.getContentPane().add(galaBack);
	}
	
	/**
	 * Default stock list generate for ship
	 * @return ArrayList<Stock> before adding the whole list to ship
	 */
	private ArrayList<Stock> generateStock(){
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
	
	/**
	 * Add Celestial bodies and Outpost to SpaceObjects
	 */
	private void makeSpaceObjects() {		
		//Space objects
		Outpost post1 = new Outpost(200, 300, 100, 100, "Eldar TradePost", "/EldarSpaceStation.png");
		SpaceObjects.add(post1);
		Outpost post2 = new Outpost(700, 800, 100, 100, "SpaceMarine Shipyard", "/SpaceMarineStation.png");
		SpaceObjects.add(post2);
		BlackHole hole1 = new BlackHole(20, 30, 150, 150, "MegaBlack", "/BlueHole.png");
		SpaceObjects.add(hole1);
		BlackHole hole2 = new BlackHole(1450, 700, 200, 200, "MediumBlue", "/smallBlueHole.png");
		SpaceObjects.add(hole2);
		Planet earth = new Planet_Terrestrial(1000, 1000, 400, "Earth", "/Earth.png");
		SpaceObjects.add(earth);
		Planet oceanPlanet = new Planet_Ocean(600, 500, 250, "Ocean", "/OceanPlanet.png");
		SpaceObjects.add(oceanPlanet);
		Planet colorfulPlanet = new Planet_ColorfulDrawf(1800, 300, 300, "ODBE-35X","/ColorfulPlanet1.png");
		SpaceObjects.add(colorfulPlanet);
		Planet saturn = new Planet_GasGiant(200, 180, 350, "Saturn", "/saturn.png");
		SpaceObjects.add(saturn);
	}
	
	void CheckCrew() {
		ArrayList<Crew> crews = SpaceShip.getCrewList();
		for (Crew crew:crews) {
			switch (crew.getRank()) {
			case SCIENTIST://Increase Hull
				SpaceShip.addMaxHull(50);
				break;
			case MECHANIC://Repair Hull faster
				break;
			case CAPTAIN://Increase all morale
				for (Crew cr:crews) {
					cr.setMaxMorale(25);
				}
				break;
			case DOCTOR://Increase all health
				for (Crew cr:crews) {
					cr.setMaxHealth(25);
				}
				break;
			case CHEF://Increase all hunger
				for (Crew cr:crews) {
					cr.setMaxHunger(25);
				}
				break;
			case HELMS_MAN://Increase fuel
				SpaceShip.addFuel(200);
				break;
			default:
				break;
			}
		}
		
		for (Crew cr:crews) {
			cr.MaxStat();
		}
	}
}