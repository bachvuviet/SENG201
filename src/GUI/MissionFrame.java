package GUI;

import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import SpaceVessel.*;
import CustomUIELmt.StaticObjects;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Color;
import java.awt.Image;
import java.awt.FlowLayout;
import javax.swing.JTextField;

public class MissionFrame {
	public JFrame frame; 
	private JSlider slider = new JSlider(3, 10, 6);//min, max, initVal
	private int Days = slider.getValue();
	/**
	 * Initialize the contents of the frame.
	 */
	public MissionFrame() {		
		GraphicsDevice gd[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
		int width = gd[0].getDisplayMode().getWidth();
		int height = gd[0].getDisplayMode().getHeight();
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(0, 0, 1366, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);	
		
		JLabel lblMission = new JLabel("");
		lblMission.setForeground(new Color(0, 255, 0));
		lblMission.setBounds(0, 0, 1360, 201);
		lblMission.setVerticalAlignment(SwingConstants.TOP);
		lblMission.setHorizontalAlignment(SwingConstants.LEFT);
		lblMission.setFont(new Font("Cambria", Font.BOLD, 18));
		lblMission.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 25));
		frame.getContentPane().add(lblMission);
		
		//Middle
		JPanel panelMid = new JPanel();
		panelMid.setBounds(0, 200, 1366, 516);
		panelMid.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
		panelMid.setOpaque(true);
		panelMid.setLayout(null);
		frame.getContentPane().add(panelMid);
		
		JLabel lblCreate = new JLabel("ENTER SHIP NAME");
		lblCreate.setForeground(Color.WHITE);
		lblCreate.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreate.setBounds(211, 52, 221, 22);
		panelMid.add(lblCreate);
		lblCreate.setFont(new Font("Cambria", Font.BOLD, 18));	

		JTextField txtShipName;
		txtShipName = new JTextField();
		txtShipName.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtShipName.setText("KMS Gneisenau");
		txtShipName.setColumns(10);
		txtShipName.setBounds(211, 85, 221, 20);
		panelMid.add(txtShipName);
				
		JLabel lblEnterDays = new JLabel("CHOOSE 3-10 DAYS");
		lblEnterDays.setForeground(Color.WHITE);
		lblEnterDays.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterDays.setFont(new Font("Cambria", Font.BOLD, 18));
		lblEnterDays.setBounds(928, 52, 221, 22);
		panelMid.add(lblEnterDays);
		
		//Experiment Slider	
		slider.setSize(221, 43);
		slider.setLocation(928, 88);
		slider.setMajorTickSpacing(3);
		slider.setMinorTickSpacing(1);
		slider.setPaintTicks(true);
		panelMid.add(slider);
		
		JLabel lblChoosingCrewMembers = new JLabel("");
		lblChoosingCrewMembers.setText("<html><p>CHOOSING 4 CREW MEMBERS<p>(or right-click to undo choice)</html>");
		lblChoosingCrewMembers.setVerticalAlignment(SwingConstants.TOP);
		lblChoosingCrewMembers.setForeground(Color.WHITE);
		lblChoosingCrewMembers.setHorizontalAlignment(SwingConstants.CENTER);
		lblChoosingCrewMembers.setFont(new Font("Cambria", Font.BOLD, 18));
		lblChoosingCrewMembers.setBounds(481, 115, 400, 68);
		panelMid.add(lblChoosingCrewMembers);
		
		JLabel lblTotal = new JLabel("");
		Image IMG = StaticObjects.SelfResizeImage("../Doctor Strange.png", this, 174, 207);
		JButton btnCrewMember1 = new JButton(new ImageIcon(IMG));
		btnCrewMember1.setToolTipText("<html><h2><center>Doctor</center><h4>He/She heals your health</html>");
		btnCrewMember1.addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if (SwingUtilities.isLeftMouseButton(e))
					addCrew(CrewRank.DOCTOR, lblTotal);
				else if (SwingUtilities.isRightMouseButton(e))
					removeCrew(CrewRank.DOCTOR, lblTotal);
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
		btnCrewMember1.setBounds(37, 194, 174, 207);
		panelMid.add(btnCrewMember1);
		
		IMG = StaticObjects.SelfResizeImage("../Gamora.png", this, 174, 207);
		JButton btnCrewMember2 = new JButton(new ImageIcon(IMG));
		btnCrewMember2.setToolTipText("<html><h2><center>Mechanic</center><h4>He/She fixes your ship</html>");		
		btnCrewMember2.addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if (SwingUtilities.isLeftMouseButton(e))
					addCrew(CrewRank.MECHANIC, lblTotal);
				else if (SwingUtilities.isRightMouseButton(e))
					removeCrew(CrewRank.MECHANIC, lblTotal);
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
		btnCrewMember2.setBounds(258, 194, 174, 207);
		panelMid.add(btnCrewMember2);
		
		IMG = StaticObjects.SelfResizeImage("../Groot.png", this, 174, 207);
		JButton btnCrewMember3 = new JButton(new ImageIcon(IMG));
		btnCrewMember3.setToolTipText("<html><h2><center>Captain</center><h4>He/She is ... </html>");
		btnCrewMember3.addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if (SwingUtilities.isLeftMouseButton(e))
					addCrew(CrewRank.CAPTAIN, lblTotal);
				else if (SwingUtilities.isRightMouseButton(e))
					removeCrew(CrewRank.CAPTAIN, lblTotal);
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
		btnCrewMember3.setBounds(481, 194, 174, 207);
		panelMid.add(btnCrewMember3);
		
		IMG = StaticObjects.SelfResizeImage("../Kirk.png", this, 174, 207);
		
		JButton btnCrewMember4 = new JButton(new ImageIcon(IMG));
		btnCrewMember4.setToolTipText("<html><h2><center>Scientist</center><h4>He/She researches stuff</html>");
		btnCrewMember4.addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if (SwingUtilities.isLeftMouseButton(e))
					addCrew(CrewRank.SCIENTIST, lblTotal);
				else if (SwingUtilities.isRightMouseButton(e))
					removeCrew(CrewRank.SCIENTIST, lblTotal);
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
		btnCrewMember4.setBounds(707, 194, 174, 207);
		panelMid.add(btnCrewMember4);
		
		IMG = StaticObjects.SelfResizeImage("../Spock.png", this, 174, 207);
		
		JButton btnCrewMember5 = new JButton(new ImageIcon(IMG));
		btnCrewMember5.setToolTipText("<html><h2><center>Chef</center><h4>He/She boosts your hunger</html>");
		btnCrewMember5.addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if (SwingUtilities.isLeftMouseButton(e))
					addCrew(CrewRank.CHEF, lblTotal);
				else if (SwingUtilities.isRightMouseButton(e))
					removeCrew(CrewRank.CHEF, lblTotal);
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
		btnCrewMember5.setBounds(928, 194, 174, 207);
		panelMid.add(btnCrewMember5);
		
		IMG = StaticObjects.SelfResizeImage("../Star Lord.png", this, 174, 207);
		JButton btnCrewMember6 = new JButton(new ImageIcon(IMG));
		btnCrewMember6.setToolTipText("<html><h2><center>Helms</center><h4><center>He/She boosts your spaceship fuel</center></html>");
		btnCrewMember6.addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if (SwingUtilities.isLeftMouseButton(e))
					addCrew(CrewRank.HELMS_MAN, lblTotal);
				else if (SwingUtilities.isRightMouseButton(e))
					removeCrew(CrewRank.HELMS_MAN, lblTotal);
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
		btnCrewMember6.setBounds(1149, 194, 174, 207);
		panelMid.add(btnCrewMember6);

		lblTotal.setForeground(Color.GREEN);
		lblTotal.setFont(new Font("Cambria", Font.BOLD, 18));
		lblTotal.setBounds(810, 412, 513, 101);
		lblTotal.setVerticalAlignment(SwingConstants.TOP);
		lblTotal.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		lblTotal.setBackground(new Color(0,0,0));
		lblTotal.setOpaque(true);
		panelMid.add(lblTotal);	
		
		//Bottom
		JPanel panelBottom = new JPanel();
		panelBottom.setBounds(0, 715, 1366, 53);
		panelBottom.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
		panelBottom.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		frame.getContentPane().add(panelBottom);
		
		JButton btnDeclineMission = new JButton("Decline Mission");
		btnDeclineMission.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WelcomeFrame window = new WelcomeFrame();
				window.frame.setLocationRelativeTo(null);
				window.frame.setVisible(true);
				
				frame.dispose();
			}
		});
		btnDeclineMission.setFont(new Font("Times New Roman", Font.BOLD, 30));			
		panelBottom.add(btnDeclineMission);				
		
		JButton btnAcceptStart = new JButton("Accept & Start");		
		btnAcceptStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Days = slider.getValue();
				if (Days >= 3  && Days <= 10) {
				    InitGame(width, height, Days, txtShipName.getText());					
				}
			}
		});
		btnAcceptStart.setFont(new Font("Times New Roman", Font.BOLD, 30));
		panelBottom.add(btnAcceptStart);
		
		StaticObjects.IncomingMessage("Incoming message from Admiral Spire:", "Your exceptional result in Imperium Naval Academy has made you the youngest but most experience Captain. " + 
				"Therefore, I need you to arrive Battlefleet Gothic ASAP, Sector 001-a, where I will personally promote you" + 
				" as a Captain of the brand new Battle Cruiser Gneisenau (BC-560). Until then, safe travel and beware that our WARP-tech is experiencing some jaming from the CHAOS Fleet.", 
				"Admiral Spire over!", lblMission);
		
		//Always put background at the END
		JLabel Background = new JLabel("");
		Background.setBounds(0, 0, 1366, 768);
		IMG = StaticObjects.SelfResizeImage("../Mission.jpg", this, 1366, 768);
		Background.setVerticalAlignment(SwingConstants.BOTTOM);
		Background.setIcon(new ImageIcon(IMG));
		frame.getContentPane().add(Background);
	}
	
	void InitGame(int width, int height, int Days, String name) {
		int DaysOnMission = Days;
		if (name == "")
			name = "KMS Gneisenau";
		if (tempCrew.size() < 4) {
			StaticObjects.MessBox("You haven't got enough crew, make more friends captain", "Not enough crew", "Info");
			return;
		}
			
		//Module
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
		for (int i=0; i < (int)DaysOnMission/3; i++) {
			SupMod.get(SupMod.size() -1 -i).setActive(false);
		}
		for (ShipModule mod:SupMod) {
			CoreMod.add(mod);
		}
		CoreMod.add(warp);
		
		//SpaceShip
		Spaceship SpaceShip = new Spaceship(width/2, height/2, name, DaysOnMission, tempCrew, CoreMod);				
		
		GameEnvironment game = new GameEnvironment(width, height, SpaceShip);
		game.frame.setVisible(true);
		game.frame.setLocationRelativeTo(null);
		
		frame.dispose();
	}
	
	ArrayList<Crew> tempCrew = new ArrayList<Crew>();
    int[] Array = {0, 0, 0, 0, 0, 0};
	void addCrew(CrewRank Rank, JLabel lbl) {
		int total = 0;
		for (int num:Array) {
			total += num;
		}
		if (total >= 4) {
			return;
		}
		switch (Rank) {
		case SCIENTIST:
			Array[0] += 1;
			break;
		case MECHANIC:
			Array[1] += 1;
			break;
		case CAPTAIN:
			Array[2] += 1;
			break;
		case DOCTOR:
			Array[3] += 1;
			break;
		case CHEF:
			Array[4] += 1;
			break;
		case HELMS_MAN:
			Array[5] += 1;
			break;
		default:
			break;
		}
		
		Crew newCrew = new Crew("Anything", Rank, 100, 100, 100);
		tempCrew.add(newCrew);
		updateCrewlabel(lbl);
	}
	void removeCrew(CrewRank Rank, JLabel lbl) {
		switch (Rank) {
		case SCIENTIST:
			if (Array[0] > 0)
				Array[0] -= 1;
			break;
		case MECHANIC:
			if (Array[1] > 0)
				Array[1] -= 1;
			break;
		case CAPTAIN:
			if (Array[2] > 0)
				Array[2] -= 1;
			break;
		case DOCTOR:
			if (Array[3] > 0)
				Array[3] -= 1;
			break;
		case CHEF:
			if (Array[4] > 0)
				Array[4] -= 1;
			break;
		case HELMS_MAN:
			if (Array[5] > 0)
				Array[5] -= 1;
			break;
		default:
			break;
		}
		
		for (Crew cr:tempCrew) {
			if (cr.getRank() == Rank) {
				tempCrew.remove(cr);
				break;
			}
		}
		updateCrewlabel(lbl);
	}
	void updateCrewlabel(JLabel lbl) {
		String[] Rank = {"Scientist", "Mechanic", "Captain", "Doctor", "Chef", "Helms"};
		String mystr = "";
		for (int i=0; i < 6; i++) {
			if (Array[i] != 0) {
				mystr += Rank[i] + ": " + Array[i] + " ";
			}
		}
		lbl.setText("<html><h2>Your selected Crew(s):<p>" + mystr + "</html>");
	}
}

	