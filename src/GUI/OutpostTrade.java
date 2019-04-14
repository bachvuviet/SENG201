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
	
	private ArrayList<Stock> StockList;
	private ArrayList<JTextField> textFieldList;

	public OutpostTrade(JFrame parent, Outpost outpost, Outpost ship) {
		StockList = ship.getInventory();
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
		
		int y = 0; int row = 0;
		for(int i=1; i <= StockList.size(); i++) {
			Stock st = StockList.get(i-1);
			JLabel lblFood = new JLabel("");
			lblFood.setBorder(new LineBorder(new Color(0, 0, 0)));
			lblFood.setDoubleBuffered(true);
			lblFood.setBounds(33 + (i-1-row)*86, 73+y*91, 50, 50);
			lblFood.setToolTipText(st.getName());
			panelLeft.add(lblFood);
			
			JTextField txtFood = new JTextField();
			txtFood.setEnabled(false);
			txtFood.setHorizontalAlignment(SwingConstants.CENTER);
			txtFood.setBounds(33 + (i-1-row)*86, 121+y*91, 50, 20);
			txtFood.setColumns(10);
			txtFood.setText(Integer.toString(st.getAmount()));
			panelLeft.add(txtFood);
			textFieldList.add(txtFood);
			
			if (i%3 == 0) {
				row += 3;
				y += 1;
			}
		}
        
		JButton btnFood1 = new JButton();
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
