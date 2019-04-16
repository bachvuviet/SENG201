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

import SpaceVessel.Crew;
import SpaceVessel.Stock;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class CrewPanel {
	public JPanel contentPan;
	JComboBox<String> cboAction1 = new JComboBox<String>();
	JComboBox<String> cboAction2 = new JComboBox<String>();
	JComboBox<String> useSupplyAction = new JComboBox<String>();
	JProgressBar progHealth = new JProgressBar();
	JProgressBar progHunger = new JProgressBar();
	JProgressBar progMorale = new JProgressBar();
	
	public CrewPanel(int x, int y, int w, int h, Crew crew, ArrayList<Stock> stock) {
		contentPan = new JPanel();
		contentPan.setBounds(x, y, w, h);
		contentPan.setLayout(null);
		contentPan.setVisible(true);
        
	    JLabel lblAvatar = new JLabel(crew.getAvatar());
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
        
       
        cboAction1.setBounds(188, 131, 160, 19);
        cboAction1.addItem("");
        cboAction1.addItem("Sleep");
        cboAction1.addItem("Repair");
        cboAction1.addItem("Pilot");
        cboAction1.addItem("Use stock");
        cboAction1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (cboAction1.getSelectedItem().equals("Use stock")) {
					useSupplyAction.setVisible(true);
				} else {
					useSupplyAction.setVisible(false);
				}
			}
		});
        contentPan.add(cboAction1);
        
        cboAction2.setBounds(188, 162, 160, 19);
        cboAction2.addItem("");
        cboAction2.addItem("Sleep");
        cboAction2.addItem("Repair");
        cboAction2.addItem("Pilot");
        cboAction2.addItem("Use stock");
        cboAction2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (cboAction2.getSelectedItem().equals("Use stock")) {
					useSupplyAction.setVisible(true);
				} else {
					useSupplyAction.setVisible(false);
				}
			}
		});
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
        
       
        progHunger.setBounds(188, 67, 160, 15);
        contentPan.add(progHunger);
        progHunger.setValue(crew.getHunger());
        progHunger.setForeground(Color.BLUE);
        
        
        progHealth.setBounds(188, 43, 160, 15);
        contentPan.add(progHealth);
        progHealth.setValue(crew.getHealth());
        progHealth.setForeground(Color.RED);   
        
       
        progMorale.setBounds(188, 90, 160, 15);
        contentPan.add(progMorale);
        progMorale.setValue(crew.getMorale());
        progMorale.setForeground(Color.YELLOW);
        
        //useSupplyAction.setFont(new Font("Dialog", Font.BOLD, 10));
        useSupplyAction.setBounds(12, 200, 111, 20);
        for (Stock i: stock) {
        	useSupplyAction.addItem(i.getName());
        };
        useSupplyAction.setVisible(false);
        contentPan.add(useSupplyAction);
        
        
        
        JButton btnGiveOrder = new JButton("Give Order");
        btnGiveOrder.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	    GiveOrder(crew);
        	}
        });
        
        btnGiveOrder.setBounds(138, 197, 210, 25);
        contentPan.add(btnGiveOrder);
        
        contentPan.revalidate();
        contentPan.repaint();
	}
	
	void GiveOrder(Crew crew) {
		// TODO Auto-generated method stub
		if (cboAction1.getSelectedItem().equals("Sleep") || cboAction2.getSelectedItem().equals("Sleep")) {
		    crew.sleep();
		    progHealth.setValue(crew.getHealth());
		    progHunger.setValue(crew.getHunger());
		
		}
		else if (cboAction1.getSelectedItem().equals("Repair") || cboAction2.getSelectedItem().equals("Repair")) {
			crew.pilotShip();
			progMorale.setValue(crew.getMorale());
		}
	}
	
	
}
