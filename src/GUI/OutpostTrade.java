package GUI;

import SpaceVessel.*;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;


import javax.swing.ImageIcon;

//import SpaceVessel.Stock;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;

import SpaceVessel.Outpost;

import java.awt.Color;
import javax.swing.JTextField;


public class OutpostTrade {

	public JFrame frame;
	
	private JTextField txtFood1;
	private JTextField txtFood2;
	private JTextField txtFood3;
	private JTextField txtFood4;
	private JTextField txtFood5;
	private JTextField txtFood6;
	private JTextField txMed1;
	private JTextField txtMed2;
	private JTextField txtMed3;
	
	private ArrayList<Stock> StockList;

	public OutpostTrade(JFrame parent, Outpost outpost, Outpost ship) {
		StockList
		frame = new JFrame();
		frame.setAlwaysOnTop(true);
		frame.setBounds(200, 300, 501, 447);
		frame.setUndecorated(true);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLabel lblOutpost = new JLabel(outpost.toString());
		lblOutpost.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblOutpost.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblOutpost, BorderLayout.NORTH);
		
		JPanel panelBottom = new JPanel();
		frame.getContentPane().add(panelBottom, BorderLayout.SOUTH);
		
		JButton btnResume = new JButton("Resume");
		btnResume.setPreferredSize(new Dimension(150, 30));
		btnResume.setFont(new Font("Tahoma", Font.BOLD, 20));
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
		
		JSplitPane splitPane = new JSplitPane();
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);
		splitPane.setDividerLocation(frame.getWidth()/7*4);
		splitPane.setEnabled(false);
		
		JPanel panelLeft = new JPanel();
		panelLeft.setBounds(0, 0, frame.getWidth()/2, frame.getHeight()/2);
		splitPane.setLeftComponent(panelLeft);
		panelLeft.setLayout(null);
		
		JLabel lblInventory = new JLabel("You have");
		lblInventory.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblInventory.setHorizontalAlignment(SwingConstants.CENTER);
		lblInventory.setBounds(10, 11, 265, 30);
		panelLeft.add(lblInventory);
		
		JLabel lblFood1 = new JLabel("");
		lblFood1.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblFood1.setDoubleBuffered(true);
		lblFood1.setBounds(33, 73, 50, 50);
		panelLeft.add(lblFood1);
		
		JLabel lblFood2 = new JLabel("");
		lblFood2.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblFood2.setBounds(119, 73, 50, 50);
		panelLeft.add(lblFood2);
		
		JLabel lblFood3 = new JLabel("");
		lblFood3.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblFood3.setBounds(203, 73, 50, 50);
		panelLeft.add(lblFood3);
		
		JLabel lblFood4 = new JLabel("");
		lblFood4.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblFood4.setBounds(33, 164, 50, 50);
		panelLeft.add(lblFood4);
		
		JLabel lblFood5 = new JLabel("");
		lblFood5.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblFood5.setBounds(119, 164, 50, 50);
		panelLeft.add(lblFood5);
		
		JLabel lblFood6 = new JLabel("");
		lblFood6.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblFood6.setBounds(203, 164, 50, 50);
		panelLeft.add(lblFood6);
		
		JLabel lblMed1 = new JLabel("");
		lblMed1.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblMed1.setBounds(33, 258, 50, 50);
		panelLeft.add(lblMed1);
		
		JLabel lblMed2 = new JLabel("");
		lblMed2.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblMed2.setBounds(119, 258, 50, 50);
		panelLeft.add(lblMed2);
		
		JLabel lblMed3 = new JLabel("");
		lblMed3.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblMed3.setBounds(203, 258, 50, 50);
		panelLeft.add(lblMed3);
		
		txtFood1 = new JTextField();
		txtFood1.setEnabled(false);
		txtFood1.setHorizontalAlignment(SwingConstants.CENTER);
		txtFood1.setBounds(33, 121, 50, 20);
		panelLeft.add(txtFood1);
		txtFood1.setColumns(10);
		
		txtFood2 = new JTextField();
		txtFood2.setEnabled(false);
		txtFood2.setHorizontalAlignment(SwingConstants.CENTER);
		txtFood2.setColumns(10);
		txtFood2.setBounds(119, 121, 50, 20);
		panelLeft.add(txtFood2);
		
		txtFood3 = new JTextField();
		txtFood3.setEnabled(false);
		txtFood3.setHorizontalAlignment(SwingConstants.CENTER);
		txtFood3.setColumns(10);
		txtFood3.setBounds(203, 121, 50, 20);
		panelLeft.add(txtFood3);
		
		txtFood4 = new JTextField();
		txtFood4.setEnabled(false);
		txtFood4.setHorizontalAlignment(SwingConstants.CENTER);
		txtFood4.setColumns(10);
		txtFood4.setBounds(33, 212, 50, 20);
		panelLeft.add(txtFood4);
		
		txtFood5 = new JTextField();
		txtFood5.setEnabled(false);
		txtFood5.setHorizontalAlignment(SwingConstants.CENTER);
		txtFood5.setColumns(10);
		txtFood5.setBounds(119, 212, 50, 20);
		panelLeft.add(txtFood5);
		
		txtFood6 = new JTextField();
		txtFood6.setEnabled(false);
		txtFood6.setHorizontalAlignment(SwingConstants.CENTER);
		txtFood6.setColumns(10);
		txtFood6.setBounds(203, 212, 50, 20);
		panelLeft.add(txtFood6);
		
		txMed1 = new JTextField();
		txMed1.setEnabled(false);
		txMed1.setHorizontalAlignment(SwingConstants.CENTER);
		txMed1.setColumns(10);
		txMed1.setBounds(33, 306, 50, 20);
		panelLeft.add(txMed1);
		
		txtMed2 = new JTextField();
		txtMed2.setEnabled(false);
		txtMed2.setHorizontalAlignment(SwingConstants.CENTER);
		txtMed2.setColumns(10);
		txtMed2.setBounds(119, 306, 50, 20);
		panelLeft.add(txtMed2);
		
		txtMed3 = new JTextField();
		txtMed3.setEnabled(false);
		txtMed3.setHorizontalAlignment(SwingConstants.CENTER);
		txtMed3.setColumns(10);
		txtMed3.setBounds(203, 306, 50, 20);
		panelLeft.add(txtMed3);

		
		JPanel panelRight = new JPanel();
		panelRight.setBorder(null);
		splitPane.setRightComponent(panelRight);
		panelRight.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("FOOD");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 46, 49, 14);
		panelRight.add(lblNewLabel);
		
		JLabel lblMedicine = new JLabel("MEDICINE");
		lblMedicine.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMedicine.setBounds(10, 235, 76, 14);
		panelRight.add(lblMedicine);
		
		//IMG = StaticObjects.SelfResizeImage("../Doctor Strange.png", this, 174, 207);
		//JButton btnEwButton = new JButton(new ImageIcon(IMG));
		//Image IMG = new ImageIcon(this.getClass().getResource("7a9496206821ca9.png")).getImage();
        
		JButton btnFood1 = new JButton(); 
		
		//btnEwButton.setIcon( new ImageIcon(OutpostTrade.class.getResource("/Image/7a9496206821ca9.png")));
		btnFood1.setToolTipText("Burger");
		btnFood1.setBounds(20, 71, 50, 50);
		panelRight.add(btnFood1);
		
		JButton btnFood2 = new JButton();
		btnFood2.setToolTipText("Burger");
		btnFood2.setBounds(80, 71, 50, 50);
		panelRight.add(btnFood2);
		
		JButton btnFood3 = new JButton();
		btnFood3.setToolTipText("Burger");
		btnFood3.setBounds(140, 71, 50, 50);
		panelRight.add(btnFood3);
		
		JButton btnFood4 = new JButton("");
		btnFood4.setToolTipText("Healing potion");
		btnFood4.setBounds(20, 164, 50, 50);
		panelRight.add(btnFood4);
		
		JButton btnFood5 = new JButton("");
		btnFood5.setToolTipText("Power-up syringe");
		btnFood5.setBounds(80, 164, 50, 50);
		panelRight.add(btnFood5);
		
		JButton btnFood6 = new JButton("");
		btnFood6.setToolTipText("Power-up syringe");
		btnFood6.setBounds(140, 164, 50, 50);
		panelRight.add(btnFood6);
		
		JButton btnMed1 = new JButton("");
		btnMed1.setToolTipText("Healing potion");
		btnMed1.setBounds(20, 260, 50, 50);
		panelRight.add(btnMed1);
		
		JButton btnMed2 = new JButton("");
		btnMed2.setToolTipText("Power-up syringe");
		btnMed2.setBounds(80, 260, 50, 50);
		panelRight.add(btnMed2);
		
		JButton btnMed3 = new JButton("");
		btnMed3.setToolTipText("Power-up syringe");
		btnMed3.setBounds(140, 260, 50, 50);
		panelRight.add(btnMed3);
	}
}
