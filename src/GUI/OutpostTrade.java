package GUI;

import SpaceVessel.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;

import SpaceVessel.Outpost;

import java.awt.Color;
import javax.swing.JTextField;

/** Class Intro:
 * @author Bach Vu, Linh Luu
 * @category SpaceShip game
 * @version 0.30
 * @location Lab133
 * This is the trade frame, if player's spaceship get close to one of the outpost and interact (X)
 * Player can buy more ship supply here
 */
public class OutpostTrade {
	public JFrame frame;
	
	/** Ship current stock*/
	private ArrayList<Stock> StockList;
	private ArrayList<JTextField> textFieldList = new ArrayList<JTextField>();

	/**
	 * Make Trade frame with stock load from ship
	 * @param parent Galaxy to be paused (disable control in game environment)
	 * @param outpost Outpost interacted (press X)
	 * @param ship Current ship
	 */
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
			int index=i-1;
			Stock st = StockList.get(index);
			JLabel lblFood = new JLabel(st.getImage());
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
			textFieldList.add(txtFood);
			panelLeft.add(txtFood);
			
			JButton btnFood = new JButton(st.getImage());
			btnFood.setToolTipText(st.getName());
			btnFood.setBounds(20 + (i-1-row)*60, 73+y*91, 50, 50);
			btnFood.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					updateStockAmount(st, index);
				}
			});
			panelRight.add(btnFood);
			
			if (i%3 == 0) {
				row += 3;
				y += 1;
			}
		}
	}
	
	/**
	 * Update visual amount of ship current stock
	 * Called when user buy any of the stock in the spacestation
	 * @param st Stock user buy
	 * @param index Index of stock, which can be used to find what textbox to be updated
	 */
	void updateStockAmount(Stock st, int index) {
		st.setAmount(st.getAmount()+1);
		textFieldList.get(index).setText(Integer.toString(st.getAmount()));
	}
}
