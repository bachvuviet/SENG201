package GUI;

import Backend.*;
import SpaceVessel.*;
import CustomUIELmt.StaticObjects;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
//import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;


import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class GameEnvironment {
	/**
	 * 
	 */
	public JFrame frame;
	public ArrayList<Entity> SpaceObjects = new ArrayList<Entity>();

	public Spaceship SpaceShip;
	public JProgressBar Fuel;
	public JProgressBar Hull;
	public JLabel lblDay = new JLabel("");

	public GameEnvironment(int x, int y, Spaceship ship) {
		/**
		 * This class generate game environment from MissionFrame input
		 * Only generate Graphics and Objects, don't process user input
		 */
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
		Galaxy galaxy = new Galaxy(x, y, frame, this);		
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
	
	private ArrayList<Stock> generateStock(){
		Stock food1 = new Stock_Food("Burger", 10);
		Stock food2 = new Stock_Food("Chips", 10);
		Stock food3 = new Stock_Food("Potato", 10);
		Stock food4 = new Stock_Food("Chicken", 10);
		Stock food5 = new Stock_Food("Beef", 10);
		
		Stock medi1 = new Stock_Medicine("Paracetamon", "Headache", 10);
		Stock medi2 = new Stock_Medicine("dau bung", "Stomachache", 10);
		Stock medi3 = new Stock_Medicine("thuoc ngu", "Sleepless", 10);
		
		ArrayList<Stock> STOCK = new ArrayList<Stock>();
		STOCK.add(food1);STOCK.add(food2);
		STOCK.add(food3);STOCK.add(food4);STOCK.add(food5);
		STOCK.add(medi1);STOCK.add(medi2);
		STOCK.add(medi3);
		
		return STOCK;
	}
	
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

class Galaxy extends JPanel implements KeyListener{
	private static final long serialVersionUID = 1L;
	/**
	 * This class contains Game components and controls
	 * Keyevents to allow player interact
	 */
	private JFrame frame;//just to control focus
	private JPanel contentPane;	
	private ArrayList<Entity> SpaceObjects; 
	private Spaceship SpaceShip;
	
	//private Timer mainTimer;
	private int height;
	private int width;
	private double x, y;
	private int direction = 0;
	
	private JProgressBar Fuel;
	private JProgressBar Hull;
	private JLabel Days;
	private int currDay = 1;
	
	public Galaxy(int width, int height, JFrame frame, GameEnvironment game) {
		this.frame = frame;
		this.width = width;
		this.height = height;
		this.Fuel = game.Fuel;
		this.Hull = game.Hull;
		this.Days = game.lblDay;
		this.SpaceObjects = game.SpaceObjects;
		
		//Spaceship
		x = width/2; y = height/2;
		setFocusable(true);
		setBounds(0, 0, width, height);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		addKeyListener(this);
		this.SpaceShip = game.SpaceShip;
		Fuel.setToolTipText(Fuel.getMaximum() + "/" + Fuel.getMaximum());
		Hull.setToolTipText(SpaceShip.getHull() + "/" + SpaceShip.getHull());
	}
	
	//KeyListener Action
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);		

		SpaceShip.render(g);		
		x = SpaceShip.getcenterX();
		y = SpaceShip.getcenterY();
		direction = SpaceShip.getDirect();
	}
	
	int speed = 4;
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		//Ship control
		boolean check;
		if (key == KeyEvent.VK_W && Fuel.getValue() > 0) {
			if (y < 0 & direction == 0)
				check = false;
			else if (x < 0 && direction == 1)
				check = false;
			else if (y > (height-85) && direction == 2)
				check = false;
			else if (x > (width-85) && direction == 3)
				check = false;
			else
				check = true;
			
			if (check) {
				SpaceShip.forward();
				Fuel.setValue(Fuel.getValue() - 5);
				Fuel.setToolTipText(Fuel.getValue()+"/"+Fuel.getMaximum());
			}
		}
		else if (key == KeyEvent.VK_S && Fuel.getValue() > 0) {
			if (y < 0 && direction == 2)
				check = false;
			else if (x < 0 && direction == 3)
				check = false;
			else if (y > (height-85) && direction == 0)
				check = false;
			else if (x > (width-85) && direction == 1)
				check = false;
			else
				check = true;
			
			if (check) {
				SpaceShip.reverse();
				Fuel.setValue(Fuel.getValue() - 2);
				Fuel.setToolTipText(Fuel.getValue()+"/"+Fuel.getMaximum());
			}				
		}
				
		if (key == KeyEvent.VK_A)
			SpaceShip.toPort();
		else if (key == KeyEvent.VK_D)
			SpaceShip.toStarBoard();
		else
			SpaceShip.stop();
		
		repaint();
		
		//Pause menu - ESC
		if (key == KeyEvent.VK_ESCAPE) {
			//Pause
			frame.setFocusable(false);
			frame.setEnabled(false);
			PauseFrame pause = new PauseFrame(frame, SpaceShip);
			pause.frame.setUndecorated(true);
			pause.frame.setVisible(true);
			pause.frame.setLocationRelativeTo(null);
			
		}
		else if (key == KeyEvent.VK_ENTER) {
			EndTurn();
		}
		
		if (key == KeyEvent.VK_X) {
			found: {//for else loop in python
				for (Entity en:SpaceObjects) {
					int scanRad = en.getScanRadius();
					boolean X = Math.abs(en.getcenterX() - x) <= scanRad;
					boolean Y = Math.abs(en.getcenterY() - y) <= scanRad;
					//System.out.println(X +" "+ Y + " "+en+" "+en.getcenterX()+":"+x+":"+scanRad);
					//System.out.println(X +" "+ Y + " "+en+" "+en.getcenterY()+":"+y+":"+scanRad);
					
					if (X && Y) {
						if (en instanceof Outpost) {
							frame.setFocusable(false);
							frame.setEnabled(false);
							OutpostTrade trade = new OutpostTrade(frame, (Outpost) en, (Outpost) SpaceShip);
							trade.frame.setVisible(true);
							trade.frame.setLocationRelativeTo(null);
						}
						else if (en instanceof Planet) {
							StaticObjects.MessBox("Found "+en, "Scan Success", "");
							
						} else if (en instanceof CelestialBody) {
							StaticObjects.MessBox(en.toString(), "Move Galaxy", "Warning");
							
						}
						break found;
					}
				}
				StaticObjects.MessBox("Scan failed, not close enough!", "Scan Failed", "Warning");
			} 
		}
	}
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		SpaceShip.turning = false;
		
		if (key == KeyEvent.VK_W)
			SpaceShip.stop();
		else if (key == KeyEvent.VK_S)
			SpaceShip.stop();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}	
	
	private void EndTurn() {
		if (currDay < SpaceShip.daysOnMission) {
			//Update Control Panel
			UIManager.put("ProgressBar.selectionForeground", Color.YELLOW);
		    UIManager.put("ProgressBar.selectionBackground", Color.BLUE);
			Fuel.setValue(Fuel.getMaximum());
			Fuel.setToolTipText(Fuel.getValue()+"/"+Fuel.getMaximum());
			
			currDay += 1;
			Days.setText("Days on mission:"+currDay+"/"+SpaceShip.daysOnMission);
			
			
			//
			
			StaticObjects.MessBox("Loading Next day", "End Turn", "");
		} else {
			StaticObjects.MessBox("MissionFail", "Time Elapsed", "");
		}
	}
}