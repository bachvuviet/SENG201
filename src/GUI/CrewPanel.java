package GUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import com.sun.javadoc.ProgramElementDoc;

import SpaceVessel.Crew;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CrewPanel {
	public JPanel contentPan;

	public CrewPanel(int x, int y, int w, int h, Crew crew) {
		contentPan = new JPanel();
		contentPan.setBounds(x, y, w, h);
		contentPan.setLayout(null);
		contentPan.setVisible(true);
        
	    JLabel lblAvatar = new JLabel();
	    Border border = BorderFactory.createLineBorder(Color.BLACK, 3);
	    lblAvatar.setBorder(border);
        lblAvatar.setBackground(Color.GRAY);
        lblAvatar.setForeground(Color.RED);
        lblAvatar.setHorizontalAlignment(SwingConstants.CENTER);
        lblAvatar.setBounds(12, 12, 111, 169);
        contentPan.add(lblAvatar);
        
        JTextField txtName = new JTextField();
        //txtName.setText(crew.getName());
        txtName.setText(crew.getName());
        
        txtName.setHorizontalAlignment(SwingConstants.CENTER);
        txtName.setBounds(138, 12, 210, 19);
        contentPan.add(txtName);
        txtName.setColumns(10);
        
        JComboBox<String> cboAction1 = new JComboBox<String>();
        cboAction1.setBounds(188, 131, 160, 19);
        contentPan.add(cboAction1);
        
        JComboBox<String> cboAction2 = new JComboBox<String>();
        cboAction2.setBounds(188, 162, 160, 19);
        contentPan.add(cboAction2);
        
        JLabel lblActions = new JLabel("Action");
        lblActions.setFont(new Font("Dialog", Font.BOLD, 10));
        lblActions.setBounds(137, 131, 51, 50);
        contentPan.add(lblActions);
        
        JLabel lblHealth1 = new JLabel("Health");
        lblHealth1.setFont(new Font("Dialog", Font.BOLD, 11));
        lblHealth1.setBounds(137, 43, 49, 15);
        contentPan.add(lblHealth1);
        
        JLabel lblHunger1 = new JLabel("Hunger");
        lblHunger1.setFont(new Font("Dialog", Font.BOLD, 11));
        lblHunger1.setBounds(137, 67, 49, 15);
        contentPan.add(lblHunger1);
        
        JLabel lblMorale = new JLabel("Morale");
        lblMorale.setFont(new Font("Dialog", Font.BOLD, 11));
        lblMorale.setBounds(137, 90, 49, 15);
        contentPan.add(lblMorale);
        
        JProgressBar progHunger = new JProgressBar();
        progHunger.setBounds(188, 67, 160, 15);
        contentPan.add(progHunger);
        progHunger.setValue(crew.getHunger());
        progHunger.setForeground(Color.BLUE);
        
        JProgressBar progHealth = new JProgressBar();
        progHealth.setBounds(188, 43, 160, 15);
        contentPan.add(progHealth);
        progHealth.setValue(crew.getHealth());
        progHealth.setForeground(Color.RED);   
        
        JProgressBar progMorale = new JProgressBar();
        progMorale.setBounds(188, 90, 160, 15);
        contentPan.add(progMorale);
        progMorale.setValue(crew.getMorale());
        progMorale.setForeground(Color.YELLOW);
        
        JButton btnGiveOrder = new JButton("Give Order");
        btnGiveOrder.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		GiveOrder();
        	}
        });
        
        btnGiveOrder.setBounds(138, 197, 210, 25);
        contentPan.add(btnGiveOrder);
        
        contentPan.revalidate();
        contentPan.repaint();
	}
	
	void GiveOrder(int index) {
		System.out.println("Crew "+index+" received order!");
	}
}
