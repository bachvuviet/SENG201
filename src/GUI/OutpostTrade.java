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
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;

import CustomUIELmt.StaticObjects;

import java.awt.Color;
import javax.swing.JTextField;

/**
 * This is the trade frame, if player's spaceship get close to one of the outpost and interact (X).
 * Player can buy more ship supply here.
 * @author Bach Vu, Linh Luu
 * @version 0.30
 */
public class OutpostTrade {
	public JFrame frame;
	JLabel lblInventory;
	
	/** Ship current stock*/
	private ArrayList<Stock> StockList;
	private ArrayList<JTextField> textFieldList = new ArrayList<JTextField>();
	private Spaceship ship;

	/**
	 * Make Trade frame with stock load from ship
	 * @param parent Galaxy to be paused (disable control in game environment)
	 * @param outpost Outpost interacted (press X)
	 * @param ship Current ship
	 */
	public OutpostTrade(JFrame parent, Outpost outpost, Spaceship ship) {
		frame = new JFrame();
		//frame.setAlwaysOnTop(true);
		frame.setBounds(200, 300, 501, 447);
		frame.setUndecorated(true);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		this.ship = ship;
		StockList = ship.getInventory();
		
		JLabel lblOutpost = new JLabel(outpost.toString());
		lblOutpost.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblOutpost.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblOutpost, BorderLayout.NORTH);
		
		JPanel panelBottom = new JPanel();
		frame.getContentPane().add(panelBottom, BorderLayout.SOUTH);
		
		JButton btnResume = new JButton("Complete Trade");
		btnResume.setPreferredSize(new Dimension(300, 30));
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
		
		lblInventory = new JLabel("You have "+ship.getMoney()+"$");
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
		
		int col = 0; int row = 0;
		for(int i=1; i <= StockList.size(); i++) {
			int index=i-1;
			Stock st = StockList.get(index);
			JLabel lblFood = new JLabel(st.getImage());
			lblFood.setBorder(new LineBorder(new Color(0, 0, 0)));
			lblFood.setDoubleBuffered(true);
			lblFood.setBounds(33 + (i-1-col)*86, 73+row*91, 50, 50);
			lblFood.setToolTipText(st.getName());
			panelLeft.add(lblFood);
			
			JTextField txtFood = new JTextField();
			txtFood.setEnabled(false);
			txtFood.setHorizontalAlignment(SwingConstants.CENTER);
			txtFood.setBounds(33 + (i-1-col)*86, 121+row*91, 50, 20);
			txtFood.setColumns(10);
			txtFood.setText(Integer.toString(st.getAmount()));
			textFieldList.add(txtFood);
			panelLeft.add(txtFood);
			
			JButton btnFood = new JButton(st.getImage());
			btnFood.setToolTipText(st.toStringPrice());
			btnFood.setBounds(20 + (i-1-col)*60, 73+row*91, 50, 50);
			btnFood.addMouseListener(new MouseListener() {
				@Override
				public void mousePressed(MouseEvent e) {
					if (SwingUtilities.isLeftMouseButton(e))
						updateStockAmount(st, index, 1);
					else if (SwingUtilities.isRightMouseButton(e))
						updateStockAmount(st, index, -1);
				}

				public void mouseReleased(MouseEvent e) {}
				public void mouseExited(MouseEvent e) {}
				public void mouseEntered(MouseEvent e) {}
				public void mouseClicked(MouseEvent e) {}
			});
			panelRight.add(btnFood);
			
			if (i%3 == 0) {
				col += 3;
				row += 1;
			}
		}
	}
	
	/**
	 * Update visual amount of ship current stock
	 * Called when user buy any of the stock in the spacestation
	 * @param st Stock user buy
	 * @param index Index of stock, which can be used to find what textbox to be updated
	 * @param mode 1 is buy, -1 is sell
	 */
	void updateStockAmount(Stock st, int index, int mode) {
		int amountToTrade = 1; //buy or sell
		if (ship.changeMoney(-st.getPrice() * (amountToTrade * mode), st)) {
			st.setAmount(st.getAmount()+amountToTrade * mode);
			lblInventory.setText("You have "+ship.getMoney()+"$"); 
			textFieldList.get(index).setText(Integer.toString(st.getAmount()));
		} else {
			if (mode == 1)
				StaticObjects.MessBox("You don't have enough money to buy " + st.getName() + " x"+ amountToTrade,
					"Not enough money", "Error");
			else if (mode == -1)
				StaticObjects.MessBox("You don't have enough "+ amountToTrade+ "x "+ st.getName()+" to sell",
						"Not enough item", "Error");
		}
		
	}
}
