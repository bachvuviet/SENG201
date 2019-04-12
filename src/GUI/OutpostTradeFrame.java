package GUI;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import SpaceVessel.*;

import java.awt.Font;

public class OutpostTradeFrame {

	public JFrame frame;

	/**
	 * Create the application.
	 */
	public OutpostTradeFrame(Outpost outpost) {
		frame = new JFrame();
		frame.setBounds(200, 300, 450, 300);
		frame.setUndecorated(true);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLabel lblOutpost = new JLabel("Outpost");
		lblOutpost.setText(outpost.toString());
		lblOutpost.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblOutpost.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblOutpost, BorderLayout.NORTH);
	}

}
