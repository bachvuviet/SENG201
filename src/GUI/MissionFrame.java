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

import Backend.Galaxy;
import SpaceVessel.*;
import CustomUIELmt.StaticObjects;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.FlowLayout;
import javax.swing.JTextField;

/** 
 * This is the second frame, if player choose new game.
 * It ask for user input to make a new spaceship and new journey.
 * @author Bach Vu, Linh Luu
 * @version 0.30
 */

public class MissionFrame {
	public JFrame frame; 
	/** Slider to select day of journeys get value in other support method of class.*/
	private JSlider slideJourneyDay = new JSlider(3, 10, 6);//min, max, initVal
	/** Variable to support get slideJourneyDay value.*/
	private int Days = slideJourneyDay.getValue();
	/** This label tell user how many crew they have selected.*/
	JLabel lblTotal = new JLabel("");
	
	/**
	 * Initialize the contents of the frame.
	 * Initialize Game environment
	 * Initialize Modules and crew of Spaceship
	 * Initialize new Spaceship
	 */
	public MissionFrame() {		
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
		slideJourneyDay.setSize(221, 43);
		slideJourneyDay.setLocation(928, 88);
		slideJourneyDay.setMajorTickSpacing(3);
		slideJourneyDay.setMinorTickSpacing(1);
		slideJourneyDay.setPaintTicks(true);
		panelMid.add(slideJourneyDay);
		
		//Crew Selection
		JLabel lblChoosingCrewMembers = new JLabel("");
		lblChoosingCrewMembers.setText("<html><p>CHOOSING 4 CREW MEMBERS<p>(or right-click to undo)</html>");
		lblChoosingCrewMembers.setVerticalAlignment(SwingConstants.TOP);
		lblChoosingCrewMembers.setForeground(Color.WHITE);
		lblChoosingCrewMembers.setHorizontalAlignment(SwingConstants.CENTER);
		lblChoosingCrewMembers.setFont(new Font("Cambria", Font.BOLD, 18));
		lblChoosingCrewMembers.setBounds(481, 115, 400, 68);
		panelMid.add(lblChoosingCrewMembers);		
		
		String[] TooltipArr = {"<html><h2><center>Captain</center><h4>Boost base morale of all crew by 25</html>",
								"<html><h2><center>Doctor</center><h4>Boost base health of all crew by 25</html>",
								"<html><h2><center>Helms</center><h4><center>He/She boosts your spaceship fuel by 200</center></html>",
								"<html><h2><center>Mechanic</center><h4>He/She fixes your ship faster</html>",
								"<html><h2><center>Scientist</center><h4>He/She increase Ship HP by 50</html>",
								"<html><h2><center>Chef</center><h4>Boost base hunger of all crew by 25</html>",};
		CrewRank RankArr[] = CrewRank.values();
				
		for (int i=0; i<6; i++) {
			Image IMG = StaticObjects.SelfResizeImage("/Crew"+AvatarArr[i], this, 174, 207);
			Arr[i] = AvatarArr[i];
			JButton btnCrewMember = new JButton(new ImageIcon(IMG));
			btnCrewMember.setText(Integer.toString(i));
			btnCrewMember.setToolTipText(TooltipArr[i]);
			btnCrewMember.addMouseListener(new MouseListener() {
				@Override
				public void mousePressed(MouseEvent e) {
					int index = Integer.parseInt(btnCrewMember.getText());
					if (SwingUtilities.isLeftMouseButton(e))
						addCrew(RankArr[index], btnCrewMember, 1);
					else if (SwingUtilities.isRightMouseButton(e))
						addCrew(RankArr[index], btnCrewMember, -1);
				}

				public void mouseReleased(MouseEvent e) {}
				public void mouseExited(MouseEvent e) {}
				public void mouseEntered(MouseEvent e) {}
				public void mouseClicked(MouseEvent e) {}
			});
			btnCrewMember.setBounds(37+i*221, 194, 174, 207);
			panelMid.add(btnCrewMember);
		}

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
				Days = slideJourneyDay.getValue();
				if (Days >= 3  && Days <= 10) {
				    InitGame(Days, txtShipName.getText());					
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
		Image IMG = StaticObjects.SelfResizeImage("/Background/Mission.jpg", this, 1366, 768);
		Background.setVerticalAlignment(SwingConstants.BOTTOM);
		Background.setIcon(new ImageIcon(IMG));
		frame.getContentPane().add(Background);
	}
	
	void InitGame(int Days, String name) {
		int DaysOnMission = Days;
		if (name == "")
			name = "KMS Gneisenau";
		if (tempCrew.size() < 4) {
			StaticObjects.MessBox("You haven't got enough crew, make more friends captain", "Not enough crew", "Info");
			return;
		}
		
		//SpaceShip
		Galaxy.maxFuel = 800;
		Galaxy.maxHull = 100;
		Galaxy.maxTurn = DaysOnMission;
		Galaxy.Prestige = 0;
		
		Spaceship SpaceShip = new Spaceship(300, 300, name, tempCrew);	
		LoadingFrame game = new LoadingFrame(SpaceShip);
		game.frame.setVisible(true);
		game.frame.setLocationRelativeTo(null);
		game.frame.setFocusable(true);
		frame.dispose();
		
		Timer timer = new Timer(true);
		TimerTask updateIncomingMessage = new TimerTask() {
			@Override
			public void run() {						
				game.NewMission();

				timer.cancel();
			}
	    };
	    
	    timer.scheduleAtFixedRate(updateIncomingMessage, 20, 10);//delay, period
		
	}
	
	/** Crew list contain crew via button choose crew*/
	ArrayList<Crew> tempCrew = new ArrayList<Crew>();
	/** Check amount of total crew in spaceship and amount of each type of crew*/
    int[] Array = {0, 0, 0, 0, 0, 0};
    String[] Arr = {"","","","","",""};
    int avaIndex = 5;
    String[] AvatarArr = {"/Kirk.png","/DoctorStrange.png","/StarLord.png","/Spock.png","/Uhura.png",
			"/Gamora.png","/Groot.png","/someone.png","/thangaodo.png","/thangaovang.png"};

	void addCrew(CrewRank Rank, JButton btn, int mode) {
		if (mode == 1) {
			int total = 0;
			for (int num:Array) {
				total += num;
			}
			if (total >= 4) {
				StaticObjects.MessBox("You can have maximum of 4 crew", "Enough Crew", "");
				return;
			}
		}
		
		int index = 0;
		switch (Rank) {
		case CAPTAIN:
			index = 0;
			break;
		case DOCTOR:
			index = 1;
			break;
		case HELMS_MAN:
			index = 2;
			break;
		case MECHANIC:
			index = 3;
			break;
		case SCIENTIST:
			index = 4;
			break;
		case CHEF:
			index = 5;
			break;
		default:
			break;
		}
		
		if (mode == 1) {
			Array[index] += 1;
			Image IMG1 = StaticObjects.SelfResizeImage("/Crew"+Arr[index], this, 174, 207);
			Crew newCrew = new Crew("Rename here", Rank, new ImageIcon(IMG1));
			tempCrew.add(newCrew);
			
			avaIndex += 1;
			Arr[index] = AvatarArr[avaIndex];
			Image IMG2 = StaticObjects.SelfResizeImage("/Crew"+AvatarArr[avaIndex], this, 174, 207);
			btn.setIcon(new ImageIcon(IMG2));
		} else if (mode == -1) {
			Array[index] -= 1;
			
			for (Crew cr:tempCrew) {
				if (cr.getRank() == Rank) {
					tempCrew.remove(cr);
					break;
				}
			}
			avaIndex -= 1;
			Image IMG = StaticObjects.SelfResizeImage("/Crew"+AvatarArr[index], this, 174, 207);
			btn.setIcon(new ImageIcon(IMG));
		}

		updateCrewlabel();		
	}	
	
	void updateCrewlabel() {
		String[] Rank = {"Captain", "Doctor", "Helms", "Mechanic", "Scientist", "Chef"};
		String mystr = "";
		for (int i=0; i < 6; i++) {
			if (Array[i] != 0) {
				mystr += Rank[i] + ": " + Array[i] + " ";
			}
		}
		lblTotal.setText("<html><h2>Your selected Crew(s):<p>" + mystr + "</html>");
	}
}

	