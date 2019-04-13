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

public class CrewPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	public JPanel frame;
	
	//public CrewPanel() {}
	
	public CrewPanel() {
		JPanel frame = new JPanel();
        frame.setBounds(0, 0, 385, 235);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setOpaque(false);
        
	    JLabel lblAvatar = new JLabel();
	    Border border = BorderFactory.createLineBorder(Color.BLACK, 3);
	    lblAvatar.setBorder(border);
        //Image backImg = new ImageIcon(this.getClass().getResource("Kirk.png")).getImage();
        //lblPhoto1.setIcon(new ImageIcon(backImg));
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
	}
}
