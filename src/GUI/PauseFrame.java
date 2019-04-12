package GUI;

import SpaceVessel.*;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import CustomUIELmt.StaticObjects;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;

public class PauseFrame {
	public JFrame frame;
	private JTextField txtShipname;

	/**
	 * Create the frame.
	 */
	public PauseFrame(JFrame parent, Spaceship MyShip) {
		frame = new JFrame();
		frame.setBounds(100, 100, 769, 617);
		frame.setAlwaysOnTop(true);

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
		Image IMG = StaticObjects.SelfResizeImage("../spaceshipOrg.png", this, lblShipvisiual.getWidth(), lblShipvisiual.getHeight());	
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
		
		JPanel panelCrew = new JPanel();
		tabbedPane.addTab("Crew Status", null, panelCrew, null);
		
		JLabel lblDemocrew = new JLabel("");
		String demo = "<html>";
		for (Crew cr:MyShip.getCrewList()) {
			demo += "<p>" + cr.toString();
		}
		demo += "</html>";
		lblDemocrew.setText(demo);
		panelCrew.add(lblDemocrew);
		
		
		JPanel panelLog = new JPanel();
		tabbedPane.addTab("Mission Log", null, panelLog, null);
		
	}
}
