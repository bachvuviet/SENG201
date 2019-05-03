package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import Backend.*;
import CustomUIELmt.StaticObjects;
import SpaceVessel.*;
/**
 * This class make instances of JPanel, which Game Environment can initiate multiple galaxies.
 * Process User input and update controls in ControlPanel (bottom right) of Game environment
 * @author Bach Vu
 * @version 0.30
 */
public class SpaceshipController extends JPanel implements KeyListener{
	private static final long serialVersionUID = 1L;
	
	/** Game environment frame, control focus when pause, trade or scan*/
	private JFrame frame;
	/** JPanel contain paintable graphics and update when user interact Keypress events*/
	private JPanel contentPane;	
	/** A small fraction of SpaceObjects in game environment which belongs to this (current panel) galaxy*/
	private ArrayList<Entity> SpaceObjects; 
	/** Same Spaceship generated from MissionFrame*/
	private Spaceship SpaceShip;
	
	//private Timer mainTimer;
	private int height;
	private int width;
	private double x, y;
	private int direction = 0;
	
	/** Fuel level of ship*/
	private JProgressBar Fuel;
	/** Ship's hull strength*/
	private JProgressBar Hull;
	/** Current turn display in control Panel*/
	private JLabel Days;
	/** Current turn to display via Jlabel Days*/
	Galaxy currGalaxy;
	
	/**
	 * 
	 * @param width Same width as frame
	 * @param height Same height as frame
	 * @param frame GameEnvironment frame
	 * @param game GameEnvironment object to access fields
	 */
	public SpaceshipController(int width, int height, JFrame frame, Galaxy currGalaxy, GameEnvironment game) {
		this.frame = frame;
		this.width = width;
		this.height = height;
		
		this.Fuel = game.Fuel;
		this.Hull = game.Hull;
		this.Days = game.lblDay;
		this.currGalaxy = currGalaxy;
		this.SpaceObjects = currGalaxy.getSpaceObjects();
		
		//Spaceship
		x = width/2; y = height/2;
		setFocusable(true);
		setBounds(0, 0, width, height);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		addKeyListener(this);
		this.SpaceShip = currGalaxy.getShip();
		Fuel.setToolTipText(Fuel.getMaximum() + "/" + Fuel.getMaximum());
		Hull.setToolTipText(SpaceShip.getHull() + "/" + SpaceShip.getHull());
	}
	
	//KeyListener Action
	/**
	 * Called via repaint() method of JPanel, update graphics location
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);		

		SpaceShip.render(g);		
		x = SpaceShip.getcenterX();
		y = SpaceShip.getcenterY();
		direction = SpaceShip.getDirect();
	}
	
	int speed = 4;
	/**
	 * Keypress event
	 * WSAD for ship's movement
	 * ESC to pause, view ship and crew status
	 * X to Scan, trade, change universe
	 * Enter: move to next turn
	 */
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
				Fuel.setValue(Fuel.getValue() - 5);
				Fuel.setToolTipText(Fuel.getValue()+"/"+Fuel.getMaximum());
				SpaceShip.forward();
				SpaceShip.setFuel(Fuel.getValue());
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
				Fuel.setValue(Fuel.getValue() - 2);
				Fuel.setToolTipText(Fuel.getValue()+"/"+Fuel.getMaximum());
				SpaceShip.reverse();
				SpaceShip.setFuel(Fuel.getValue());
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
			PauseFrame pause = new PauseFrame(frame, SpaceShip, currGalaxy);
			pause.frame.setUndecorated(true);
			pause.frame.setVisible(true);
			pause.frame.setLocationRelativeTo(null);
			
		}
		else if (key == KeyEvent.VK_ENTER) {
			EndTurn();
		}
		
		if (key == KeyEvent.VK_X) {
			Interact();
		}
	}
	
	/**
	 * Pause the ship when key is released
	 */
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
	
	/**
	 * Press Enter
	 * Update Control panel, summary of turn and move to next day, reset fuel and SpaceEvent
	 */
	private void EndTurn() {
		if (Spaceship.daysOnMission < Galaxy.maxTurn) {
			//Update Control Panel
			UIManager.put("ProgressBar.selectionForeground", Color.YELLOW);
		    UIManager.put("ProgressBar.selectionBackground", Color.BLUE);
			Fuel.setValue(Fuel.getMaximum());
			Fuel.setToolTipText(Fuel.getValue()+"/"+Fuel.getMaximum());
			
			Spaceship.daysOnMission += 1;
			Days.setText("Days on mission:"+Spaceship.daysOnMission+"/"+Galaxy.maxTurn);
			
			
			//
			
			StaticObjects.MessBox("Loading Next day", "End Turn", "");
		} else {
			StaticObjects.MessBox("MissionFail", "Time Elapsed", "");
		}
	}
	
	/**
	 * Press X
	 * Conduct Scan planets, trade, or Enter Warp holes
	 */
	private void Interact() {
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
					OutpostTrade trade = new OutpostTrade(frame, (Outpost) en, SpaceShip);
					trade.frame.setVisible(true);
					trade.frame.setLocationRelativeTo(null);
				}
				else if (en instanceof Planet) {
					if (!((Planet) en).getScan()) {
						frame.setFocusable(false);
						frame.setEnabled(false);
						Stock st = ((Planet) en).getTreasure();
						ScanPlanetFrame window = new ScanPlanetFrame("Found "+ st, ((Planet) en).getBackgroundPath(), frame);
						window.frame.setLocationRelativeTo(null);
						window.frame.setVisible(true);
						
						if (st instanceof ShipModule)
							SpaceShip.getModule((ShipModule) st).setActive(true);
						else
							SpaceShip.changeStockAmount(st.getAmount(), st);
					} else {
						StaticObjects.MessBox(en.toString()+" has no more stock.", "Scanned "+en.toString(), "");
					}
					
				}
				else if (en instanceof BlackHole) {
					StaticObjects.MessBox(en.toString(), "Move Galaxy", "Warning");
					currGalaxy.deleteShip();
					LoadingFrame game = new LoadingFrame(width, height, ((BlackHole) en).JumptoGalaxy(SpaceShip));
					
					Timer timer = new Timer(true);
					TimerTask updateIncomingMessage = new TimerTask() {
						@Override
						public void run() {							
							game.frame.setVisible(true);
							game.frame.setLocationRelativeTo(null);	

							timer.cancel();
						}
				    };
				    
				    timer.scheduleAtFixedRate(updateIncomingMessage, 20, 10);//delay, period
					frame.dispose();
				}
				break found;
			}
		}
		StaticObjects.MessBox("Scan failed, not close enough!", "Scan Failed", "Warning");
		}
	}

}