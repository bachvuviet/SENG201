package GUI;

import SpaceVessel.*;
import Backend.*;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Backend.Galaxy;
import CustomUIELmt.StaticObjects;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;

/** 
 * This is the pause frame, if player press ESC at anytime.
 * Game environment is disabled (no control until resume).
 * User can view ship status and give crew order within this frame, since both action must be performed when the game is paused.
 * @author Bach Vu, Linh Luu
 * @version 0.30
 */
public class PauseFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	public JFrame frame;
	private JTextField txtShipname;

	JPanel panelCrew = new JPanel();
	
	/**
	 * Load all data of ship: ship status, stock, crew and modules
	 * @param parent Galaxy to be paused (disable control in game environment)
	 * @param MyShip ship Current ship
	 */
	public PauseFrame(JFrame parent, Spaceship MyShip, Galaxy currGala) {
		frame = new JFrame();
		frame.setBounds(100, 100, 770, 620);
		//frame.setAlwaysOnTop(true);

		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		frame.setContentPane(contentPane);
		
		JPanel panelBottom = new JPanel();
		contentPane.add(panelBottom, BorderLayout.SOUTH);
		panelBottom.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.setPreferredSize(new Dimension(150, 30));
		btnQuit.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.dispose();
				frame.dispose();
			}
		});
		panelBottom.add(btnQuit);
		
		JButton btnSave = new JButton("Save");
		btnSave.setPreferredSize(new Dimension(150, 30));
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Galaxy> gala = new ArrayList<Galaxy>();
				gala.add(currGala);
				for (Entity en:currGala.getSpaceObjects()) {
					if (en instanceof BlackHole) {
						gala.add(((BlackHole) en).Destination);
					}
				}
				
				try {
					System.out.println("here");
					//String path = "B:\\Saved\\lol.ser";//this.getClass().getResource("/Saved/lol.ser").toString();
			        FileOutputStream fileOut = new FileOutputStream(this.getClass().getResource("/Saved/lol.ser").toString());
			        System.out.println("here1");
			        ObjectOutputStream out = new ObjectOutputStream(fileOut);
			        System.out.println("here2");
			        out.writeObject(currGala);
			        out.close();
			        fileOut.close();
			        StaticObjects.MessBox("Game saved", "Save success", "Info");
			      } catch (IOException ex) {
			    	  ex.printStackTrace();
			    	  StaticObjects.MessBox(ex.getMessage(),"Save Error", "Error");
			      }
			}
		});
		panelBottom.add(btnSave);
		
		JButton btnResume = new JButton("Resume");
		btnResume.setPreferredSize(new Dimension(150, 30));
		btnResume.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnResume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.setFocusable(true);
				parent.setEnabled(true);
				frame.dispose();
			}
		});
		panelBottom.add(btnResume);
		
		JLabel lblPauseTitle = new JLabel("Game Paused");
		lblPauseTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblPauseTitle.setFont(new Font("Tahoma", Font.BOLD, 27));
		contentPane.add(lblPauseTitle, BorderLayout.NORTH);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panelStatus = new JPanel();
		tabbedPane.addTab("Ship Status", null, panelStatus, null);
		panelStatus.setLayout(null);
		
		txtShipname = new JTextField();
		txtShipname.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtShipname.setBounds(10, 11, 210, 30);
		txtShipname.setText(MyShip.getVesselName());
		panelStatus.add(txtShipname);
		txtShipname.setColumns(10);
		
		JLabel lblShipvisiual = new JLabel("");
		lblShipvisiual.setOpaque(true);
		lblShipvisiual.setBackground(new Color(0, 0, 128));
		lblShipvisiual.setBounds(230, 11, 257, 482);
		Image IMG = StaticObjects.SelfResizeImage("/Ship/spaceshipOrg.png", this, lblShipvisiual.getWidth(), lblShipvisiual.getHeight());	
		lblShipvisiual.setIcon(new ImageIcon(IMG));
		panelStatus.add(lblShipvisiual);		
		
		JLabel lblShipStatus = new JLabel("Ship Status");
		lblShipStatus.setText(MyShip.ShipStatus());
		lblShipStatus.setVerticalAlignment(SwingConstants.TOP);
		lblShipStatus.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblShipStatus.setBounds(10, 48, 210, 174);
		panelStatus.add(lblShipStatus);
		
		JLabel lblModule = new JLabel("Module");
		lblModule.setVerticalAlignment(SwingConstants.TOP);
		lblModule.setText(MyShip.getModules());
		lblModule.setVerticalAlignment(SwingConstants.TOP);
		lblModule.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblModule.setBounds(10, 233, 210, 223);
		panelStatus.add(lblModule);
		
		JLabel lblInventory = new JLabel("Inventory");
		lblInventory.setText(MyShip.ListofStock());
		lblInventory.setVerticalAlignment(SwingConstants.TOP);
		lblInventory.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblInventory.setBounds(497, 11, 241, 445);
		panelStatus.add(lblInventory);		
		
		panelCrew.setBackground(Color.YELLOW);
		panelCrew.setLayout(null);
		
		int halfX = (int) 760/2;
		int halfY = (int) 530/2;
		//System.out.println(halfX + " " + halfY);
		
		tabbedPane.addTab("Crew Status", null, panelCrew, null);
		
		ArrayList<Crew> crew = MyShip.getCrewList();
		for (int i=0; i < 2; i++) {
			CrewPanel crew1 = new CrewPanel(i*halfX, i*halfY, halfX, halfY, crew.get(2*i), MyShip);
			panelCrew.add(crew1.contentPan);
			
			CrewPanel crew2 = new CrewPanel(i*halfX, (1-i)*halfY, halfX, halfY, crew.get(i*2+1), MyShip);
			panelCrew.add(crew2.contentPan);
		}
		
		JPanel panelLog = new JPanel();
		tabbedPane.addTab("Mission Log", null, panelLog, null);
	}
}

