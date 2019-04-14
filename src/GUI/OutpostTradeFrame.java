package GUI;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import SpaceVessel.*;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JList;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.GridLayout;

public class OutpostTradeFrame {

	public JFrame frame;

	/**
	 * Create the application.
	 */
	public OutpostTradeFrame(JFrame parent, Outpost outpost, Outpost ship) {
		frame = new JFrame();
		frame.setAlwaysOnTop(true);
		frame.setBounds(200, 300, 450, 300);
		frame.setUndecorated(true);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLabel lblOutpost = new JLabel("Outpost");
		lblOutpost.setText(outpost.toString());
		lblOutpost.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblOutpost.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblOutpost, BorderLayout.NORTH);
		
		JPanel panelBottom = new JPanel();
		frame.getContentPane().add(panelBottom, BorderLayout.SOUTH);
		
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
		
		JSplitPane splitPane = new JSplitPane();
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);
		splitPane.setDividerLocation(frame.getWidth()/2);
		splitPane.setEnabled(false);
		
		JPanel panelLeft = new JPanel();
		panelLeft.setBounds(0, 0, frame.getWidth()/2, frame.getHeight()/2);
		splitPane.setLeftComponent(panelLeft);
		panelLeft.setLayout(new GridLayout(1, 0, 0, 0));

		JList<Stock> listLeft = new JList<Stock>(ship.getItemList());
		listLeft.setBounds(10, 11, 204, 196);
		panelLeft.add(listLeft);
		
		JPanel panelRight = new JPanel();
		panelRight.setBorder(null);
		splitPane.setRightComponent(panelRight);
		panelRight.setLayout(null);
		
		//JList<Stock> listRight = new JList<Stock>(outpost.getItemList());
		//listRight.setBounds(10, 11, 204, 196);
		//panelRight.add(listRight);
	}
}
