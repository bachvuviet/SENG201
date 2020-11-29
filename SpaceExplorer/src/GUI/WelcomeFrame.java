/**
 * GUI package contain all Graphical Implement of the game, upgrade from commandline game
 */
package GUI;

import CustomUIELmt.*;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Image;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import Backend.Galaxy;

import java.awt.Color;

/**
 * Class Intro:
 * This is the First and Begin Frame, player choose New game, Load game or view Credits.
 * This take user to different frames.
 * @author Bach Vu
 * @version 0.30
 */

public class WelcomeFrame {
	/** Field Intro:
	 * First frame
	 */
	public JFrame frame;

	/** Method Intro:
	 * Create controls in the frame
	 */
	public WelcomeFrame() {
		frame = new JFrame();
		frame.setTitle("SENG");
		frame.setResizable(false);
		frame.getContentPane().setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 19));
		frame.setBounds(100, 100, 635, 414);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(StaticObjects.SelfResizeImage("/Celestial/Earth1.png", this, 40, 40));
		frame.getContentPane().setLayout(null);
		
		JLabel lblWelcome = new JLabel("IMPERIAL BATTLEFLEET HEADQUARTER");
		lblWelcome.setBackground(new Color(75, 0, 130));
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setToolTipText("");
		lblWelcome.setFont(new Font("Cambria Math", lblWelcome.getFont().getStyle() | Font.BOLD, 21));
		lblWelcome.setBounds(0, 0, 631, 72);
		frame.getContentPane().add(lblWelcome);
		
		JButton btnCreateSpaceship = new TranslucentButton("Receive new Mission");
		btnCreateSpaceship.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnCreateSpaceship.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				//newGame.frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 				
				MissionFrame newGame = new MissionFrame();
				newGame.frame.setUndecorated(true);//no control box
				newGame.frame.setVisible(true);
				newGame.frame.setLocationRelativeTo(null);

				frame.dispose();
			}
		});
		btnCreateSpaceship.setBounds(180, 174, 270, 57);
		frame.getContentPane().add(btnCreateSpaceship);
		
		JButton btnLoadMission = new TranslucentButton("Load Mission");
		btnLoadMission.setAlignmentX(.5f);
		btnLoadMission.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnLoadMission.setBounds(180, 241, 270, 57);
		btnLoadMission.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				Galaxy gala;
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setAcceptAllFileFilterUsed(false);;
				fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("SpaceExplorer Saves (.starman)", "starman"));
				
				if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
					try {
						FileInputStream fileInputStream = new FileInputStream(fileChooser.getSelectedFile());
					    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
					    gala = (Galaxy) objectInputStream.readObject();
					    objectInputStream.close(); 	
					    
					    //newGame.frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 				
						LoadingFrame loadGame = new LoadingFrame(gala, true);
						loadGame.frame.setUndecorated(true);//no control box
						loadGame.frame.setVisible(true);
						loadGame.frame.setLocationRelativeTo(null);
	
						frame.dispose();
					} catch(Exception ex) {
						StaticObjects.MessBox(ex.getMessage(),"Can't load selected save", "Open Error");
						ex.printStackTrace();
						return;
					}
				} else {
					
				}
			}
		});
		frame.getContentPane().add(btnLoadMission);
		
		JButton btnCredits = new TranslucentButton("Credits");
		String Credits = "<html>"
				+ "<p> Developers: Bach and Linh"
				+ "<p> Animation: Bach and Linh"
				+ "<p> Graphics: Bach and Linh"
				+ "<p> Soundtrack: Bach and Linh"
				+ "<p> Design: Bach and Linh"
				+ "<p> Storyline: Bach and Linh"
				+ "<p> Testers: Bach and Linh"
				+ "</html>";
		btnCredits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaticObjects.MessBox(Credits, "Game Credits", "");
			}
		});
		btnCredits.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnCredits.setBounds(180, 308, 270, 57);
		frame.getContentPane().add(btnCredits);	
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setVerticalAlignment(SwingConstants.BOTTOM);
		Image backImg = new ImageIcon(this.getClass().getResource("/Background/Welcome.jpg")).getImage();
		lblBackground.setBounds(0, 0, 631, 386);
		lblBackground.setIcon(new ImageIcon(backImg));
		frame.getContentPane().add(lblBackground);
	}
	
	/**
	 * Game Entry point, create a Welcome page which is the first page of the game
	 * @param args Initial argument (potential save)
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomeFrame window = new WelcomeFrame();
					window.frame.setLocationRelativeTo(null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
