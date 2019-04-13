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
import javax.swing.AbstractListModel;

public class OutpostTradeFrame {

	public JFrame frame;

	/**
	 * Create the application.
	 */
	public OutpostTradeFrame(JFrame parent, Outpost outpost) {
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
		
		JPanel panelLeft = new JPanel();
		panelLeft.setBounds(0, 0, frame.getWidth()/2, frame.getHeight()/2);
		splitPane.setLeftComponent(panelLeft);
		panelLeft.setLayout(null);
		
		JList list = new JList();
		list.setBounds(10, 11, 204, 196);
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Linh", "Lol", "Luu", "Lil"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		panelLeft.add(list);
		
		JPanel panelRight = new JPanel();
		splitPane.setRightComponent(panelRight);
		panelRight.setLayout(null);
		
		JList list_1 = new JList();
		list_1.setModel(new AbstractListModel() {
			String[] values = new String[] {"Big", "bang", "bomb"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list_1.setBounds(10, 11, 204, 196);
		panelRight.add(list_1);
	}
}
