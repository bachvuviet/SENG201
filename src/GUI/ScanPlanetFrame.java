package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import CustomUIELmt.StaticObjects;

public class ScanPlanetFrame {
	public JFrame frame; 
	
	//Constructor
	/**
	 * Display a frame when the planet is scanned
	 * @param Message Scan success return stock status
	 * @param path Image path depends on planet type
	 * @param parent Game Environment is disable just like in pause frame
	 */
	public ScanPlanetFrame(String Message, String path, JFrame parent) {
		int width = 700; int height = 500;
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.getContentPane().setLayout(null);
		
		JPanel panelMid = new JPanel();
		panelMid.setBounds(0, 0, width, height);
		panelMid.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.3f));
		panelMid.setOpaque(true);
		panelMid.setLayout(null);
		frame.getContentPane().add(panelMid);
		
		JButton btnOK = new JButton("Accept");
		btnOK.setBounds(277, 462, 183, 27);
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.setFocusable(true);
				parent.setEnabled(true);				
				frame.dispose();
			}
		});
		btnOK.setFont(new Font("Times New Roman", Font.BOLD, 20));			
		panelMid.add(btnOK);		
		
		JLabel lblMessage = new JLabel(Message);
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblMessage.setBounds(28, 40, 651, 45);
		lblMessage.setForeground(Color.YELLOW);
		panelMid.add(lblMessage);
		
		frame.getRootPane().setDefaultButton(btnOK);
		btnOK.requestFocus();
		
		//Always put background at the END
		Image IMG = StaticObjects.SelfResizeImage(path, this, width, height);
		JLabel Background = new JLabel(new ImageIcon(IMG));
		Background.setBounds(0, 0, width, height);
		Background.setVerticalAlignment(SwingConstants.BOTTOM);
		Background.setIcon(new ImageIcon(IMG));
		frame.getContentPane().add(Background);
	}
}
