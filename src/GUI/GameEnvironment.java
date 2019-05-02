package GUI;

import Backend.*;
import SpaceVessel.*;
import CustomUIELmt.StaticObjects;

import java.awt.Image;

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
	
	/** Spaceship generated from MissionFrame*/

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
	public GameEnvironment(int x, int y, Galaxy currGala) {
		frame = new JFrame();
		frame.setBounds(0, 0, x, y);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setUndecorated(true);
		UIManager.put("ProgressBar.selectionForeground", Color.YELLOW);
	    UIManager.put("ProgressBar.selectionBackground", Color.BLUE);
		
		Spaceship SpaceShip = currGala.getShip();
	    
		//Control Panel
		JPanel controlPan = new JPanel();
		controlPan.setBounds(x-350, y-150, 350, 150);
		controlPan.setBackground(new Color(255, 255, 255));
		controlPan.setLayout(null);
		
		JLabel lblFuel = new JLabel("FUEL Left:");
		lblFuel.setBounds(10, 10, 90, 20);
		lblFuel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblFuel.setFont(new Font("Cambria", Font.BOLD, 14));
		
		Fuel = new JProgressBar(0, Galaxy.maxFuel);
		Fuel.setBounds(100, 10, 240, 20);
		Fuel.setValue(SpaceShip.getFuel());
		Fuel.setToolTipText(Fuel.getValue()+"/"+Fuel.getMaximum());
		Fuel.setStringPainted(true);
		
		JLabel lblHull = new JLabel("Ship Hull:");
		lblHull.setBounds(10, 40, 90, 20);
		lblHull.setVerticalAlignment(SwingConstants.BOTTOM);
		lblHull.setFont(new Font("Cambria", Font.BOLD, 14));
		
		Hull = new JProgressBar(0, Galaxy.maxHull);
		Hull.setBounds(100, 40, 240, 20);
		Hull.setValue(SpaceShip.getHull());
		Hull.setStringPainted(true);
		Hull.setToolTipText(Hull.getValue()+"/"+Hull.getMaximum());
		Hull.setForeground(Color.RED);
		
		lblDay.setText("Day on mission: "+SpaceShip.getTurn()+"/"+Galaxy.maxTurn);
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
		SpaceshipController controller = new SpaceshipController(x, y, frame, currGala, this);		
		controller.setBounds(0, 0, x ,y);
		controller.setOpaque(false);

		frame.getContentPane().add(controller);
		frame.getContentPane().add(controlPan);

		for (Entity en:currGala.getSpaceObjects()) {
			JLabel lblTemp = new JLabel(new ImageIcon(en.getImage()));
			lblTemp.setBounds(en.getX(), en.getY(), en.getWidth(), en.getHeight());
			frame.getContentPane().add(lblTemp);			
		}		
		
		JLabel galaBack = new JLabel("");
		galaBack.setBounds(0, 0, x, y);
		galaBack.setVerticalAlignment(SwingConstants.BOTTOM);		
		Image IMG = StaticObjects.SelfResizeImage(currGala.getBackgroundPath(), this, x, y);	
		galaBack.setIcon(new ImageIcon(IMG));
		frame.getContentPane().add(galaBack);
	}	
}