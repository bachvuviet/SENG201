package GUI;

import SpaceVessel.*;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.sun.jdi.event.EventQueue;

import CustomUIELmt.StaticObjects;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

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

public class PauseFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JFrame frame;
	private JTextField txtShipname;

	JPanel panelCrew = new JPanel();
	/**
	 * Create the frame.
	 */
	public PauseFrame(JFrame parent, Spaceship MyShip) {
		frame = new JFrame();
		frame.setBounds(100, 100, 770, 620);
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
		
		panelCrew.setBackground(Color.YELLOW);
		panelCrew.setOpaque(true);
		panelCrew.setLayout(null);
		
		JPanel crew = CrewPanel();
		panelCrew.add(crew);
		panelCrew.revalidate();
		panelCrew.repaint();

		tabbedPane.addTab("Crew Status", null, panelCrew, null);
		
		JPanel panelLog = new JPanel();
		tabbedPane.addTab("Mission Log", null, panelLog, null);
	}
	
	JPanel CrewPanel() {
		JPanel frame = new JPanel();
        frame.setBounds(0, 0, 385, 235);
        frame.setLayout(null);
        frame.setVisible(true);
        
	    JLabel lblAvatar = new JLabel();
	    Border border = BorderFactory.createLineBorder(Color.BLACK, 3);
	    lblAvatar.setBorder(border);
        lblAvatar.setBackground(Color.GRAY);
        lblAvatar.setForeground(Color.RED);
        lblAvatar.setHorizontalAlignment(SwingConstants.CENTER);
        lblAvatar.setBounds(12, 12, 111, 169);
        frame.add(lblAvatar);
        
        JTextField txtName = new JTextField();
        txtName.setHorizontalAlignment(SwingConstants.CENTER);
        txtName.setBounds(138, 12, 210, 19);
        frame.add(txtName);
        txtName.setColumns(10);
        
        JComboBox<String> cboAction1 = new JComboBox<String>();
        cboAction1.setBounds(188, 131, 160, 19);
        frame.add(cboAction1);
        
        JComboBox<String> cboAction2 = new JComboBox<String>();
        cboAction2.setBounds(188, 162, 160, 19);
        frame.add(cboAction2);
        
        JLabel lblActions = new JLabel("Action");
        lblActions.setFont(new Font("Dialog", Font.BOLD, 10));
        lblActions.setBounds(137, 131, 51, 50);
        frame.add(lblActions);
        
        JLabel lblHealth1 = new JLabel("Health");
        lblHealth1.setFont(new Font("Dialog", Font.BOLD, 11));
        lblHealth1.setBounds(137, 43, 49, 15);
        frame.add(lblHealth1);
        
        JLabel lblHunger1 = new JLabel("Hunger");
        lblHunger1.setFont(new Font("Dialog", Font.BOLD, 11));
        lblHunger1.setBounds(137, 67, 49, 15);
        frame.add(lblHunger1);
        
        JProgressBar progHunger = new JProgressBar();
        progHunger.setBounds(188, 67, 160, 15);
        frame.add(progHunger);
        
        JProgressBar progHealth = new JProgressBar();
        progHealth.setBounds(188, 43, 160, 15);
        frame.add(progHealth);
        
        JLabel lblMorale = new JLabel("Morale");
        lblMorale.setFont(new Font("Dialog", Font.BOLD, 11));
        lblMorale.setBounds(137, 90, 49, 15);
        frame.add(lblMorale);
        
        JProgressBar progMorale = new JProgressBar();
        progMorale.setBounds(188, 90, 160, 15);
        frame.add(progMorale);
        
        JButton btnGiveOrder = new JButton("Give Order");
        btnGiveOrder.setBounds(138, 197, 210, 25);
        frame.add(btnGiveOrder);
        
        return frame;
	}
}
