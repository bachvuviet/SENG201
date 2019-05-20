package GUI;

import java.awt.Color;
import java.awt.Component;
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

import CustomUIELmt.StaticObjects;
import SpaceVessel.*;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

/** 
 * This class make instances of JPanel, which is used to create 4 instances (for each crew) in the pause frame, Crew tab.
 * User can give crew order individually, and view crew status.
 * @author Linh Luu
 * @version 0.30
 */
public class CrewPanel {
	public JPanel contentPan;

    JTextField txtName = new JTextField();
	JComboBox<String> cboAction1;
	JComboBox<String> cboAction2;
	JComboBox<Stock> cboSupply;
	JProgressBar progHealth;
	JProgressBar progHunger;
	JProgressBar progMorale;

	/**
	 * Create a crew panel before added to crew tab in Pause frame (4 in total)
	 * @param x Horizontal location of the panel (relative to tabcontrol in Pause frame, not to the entire frame)
	 * @param y Vertical location of the panel (relative to tabcontrol in Pause frame, not to the entire frame)
	 * @param w Panel width, half of screen by default
	 * @param h Panel height, half of screen by default
	 * @param crew Individual crew's data to be display by panel
	 * @param ship Current ship to get crew list and inventory if needed
	 */
	public CrewPanel(int x, int y, int w, int h, Crew crew, Spaceship ship) {
		contentPan = new JPanel();
		contentPan.setBounds(x, y, w, h);
		contentPan.setLayout(null);
		contentPan.setVisible(true);
		if (crew.isSick()) {
			contentPan.setBackground(new Color(255,102,102));
			contentPan.setToolTipText("This Crew is sick.");
		}
		else {
			contentPan.setBackground(new Color(240, 240, 240));
			contentPan.setToolTipText("");
		}
        
	    JLabel lblAvatar = new JLabel(crew.getAvatar());
	    Border border = BorderFactory.createLineBorder(Color.BLACK, 3);
	    lblAvatar.setBorder(border);
        lblAvatar.setBackground(Color.WHITE);
        lblAvatar.setOpaque(true);
        lblAvatar.setHorizontalAlignment(SwingConstants.CENTER);
        lblAvatar.setBounds(12, 12, 111, 169);
        contentPan.add(lblAvatar);
        
        txtName.setText(crew.getName());        
        txtName.setHorizontalAlignment(SwingConstants.CENTER);
        txtName.setBounds(138, 12, 100, 19);
        txtName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (txtName.getText().length() >= 15 ) // limit to 15 characters
                    e.consume();
            }
        });
        contentPan.add(txtName);
        
        JLabel lblRank = new JLabel(crew.getRank().name());
        lblRank.setFont(new Font("Dialog", Font.BOLD, 12));
        lblRank.setBounds(248, 12, 110, 15);
        contentPan.add(lblRank);
        
        JLabel lblHealth = new JLabel("Health");
        lblHealth.setFont(new Font("Dialog", Font.BOLD, 11));
        lblHealth.setBounds(137, 43, 49, 15);
        contentPan.add(lblHealth);
        
        JLabel lblHunger = new JLabel("Hunger");
        lblHunger.setFont(new Font("Dialog", Font.BOLD, 11));
        lblHunger.setBounds(137, 67, 49, 15);
        contentPan.add(lblHunger);
        
        JLabel lblMorale = new JLabel("Morale");
        lblMorale.setFont(new Font("Dialog", Font.BOLD, 11));
        lblMorale.setBounds(137, 90, 49, 15);
        contentPan.add(lblMorale);
        
        JLabel lblActions = new JLabel("Action");
        lblActions.setFont(new Font("Dialog", Font.BOLD, 13));
        lblActions.setBounds(137, 131, 51, 50);
        contentPan.add(lblActions);

        progHealth = new JProgressBar(0, Spaceship.maxCrewHealth);
        progHealth.setBounds(188, 43, 160, 15);
        progHealth.setValue(crew.getHealth());
        progHealth.setToolTipText(crew.getHealth()+"/"+progHealth.getMaximum());
        progHealth.setForeground(Color.RED);
        contentPan.add(progHealth); 
        
        progHunger = new JProgressBar(0, Spaceship.maxCrewHunger);
        progHunger.setBounds(188, 67, 160, 15);
        progHunger.setToolTipText(crew.getHunger()+"/"+progHunger.getMaximum());
        progHunger.setValue(crew.getHunger());
        progHunger.setForeground(Color.BLUE);
        contentPan.add(progHunger);
        
        progMorale = new JProgressBar(0, Spaceship.maxCrewMorale);
        progMorale.setBounds(188, 90, 160, 15);
        progMorale.setValue(crew.getMorale());
        progMorale.setToolTipText(crew.getMorale()+"/"+progMorale.getMaximum());
        progMorale.setForeground(Color.YELLOW);
        contentPan.add(progMorale);

        cboAction1 = new JComboBox<String>(ActionSet);
        cboAction1.setBounds(188, 131, 160, 19);
        cboAction1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				UpdateCbo(cboAction1, cboAction2, ship);
				check = true;
			}
		});
        contentPan.add(cboAction1);
        
        cboAction2 = new JComboBox<String>(ActionSet);
        cboAction2.setBounds(188, 162, 160, 19);
        cboAction2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		UpdateCbo(cboAction2, cboAction1, ship);
				check = true;
			}
		});
        contentPan.add(cboAction2);
        
        cboSupply = new JComboBox<Stock>();
        cboSupply.setBounds(12, 200, 111, 20);        
        cboSupply.setVisible(false);
        contentPan.add(cboSupply);
        
        JButton btnGiveOrder = new JButton("Give Order");
        btnGiveOrder.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		GiveOrder(cboAction1, crew, ship);
        		GiveOrder(cboAction2, crew, ship);
        	}
        });
        
        btnGiveOrder.setBounds(138, 197, 210, 25);
        contentPan.add(btnGiveOrder);

        if (crew.getCrewActivity().size() >= 1) {
        	cboAction1.setEnabled(false);
        	cboAction1.setSelectedItem(crew.getCrewActivity().get(0));
        }
        if (crew.getCrewActivity().size() == 2) {
        	cboAction2.setEnabled(false);
        	cboAction2.setSelectedItem(crew.getCrewActivity().get(1));
        }
		
		if (!crew.Real) {
			contentPan.setToolTipText("This Crew is dead.");
			for (Component comp:contentPan.getComponents()) {
				comp.setEnabled(false);
			}
		}
        
        contentPan.revalidate();
        contentPan.repaint();
	}

	boolean check = true;
    String[] ActionSet = {"", "Sleep", "Repair", "Pilot", "Use Supplement (x2)"};
    
    /**
     * Update JComboBox when actions have been chosen 
     * @param cboSelect
     * @param cboUpdate
     * @param ship
     */
	void UpdateCbo(JComboBox<String> cboSelect, JComboBox<String> cboUpdate, Spaceship ship) {
		String choice = "";
		String choice2 = "";
		if (cboSelect.getItemCount() > 0 && check) {
			check = false;
			choice = cboSelect.getSelectedItem().toString();
			choice2 = cboUpdate.getSelectedItem().toString();
		} else if (cboSelect.getItemCount() > 0) {
			check = true;
			return;
		}
		else {
			return;
		}
		
		cboUpdate.removeAllItems();
		for (String str:ActionSet) {
			if (!choice.equals(str))
				cboUpdate.addItem(str);
		}
		cboUpdate.setSelectedItem(choice2);
		
		//call cboSupply when action[4] (Use Supplement (x2)) has been chosen
		if ((choice.equals(ActionSet[4]) && cboSelect.isEnabled())||(choice2.equals(ActionSet[4]) && cboUpdate.isEnabled())) {
			if (!cboSupply.isVisible()) {
				cboSupply.setVisible(true);
				cboSupply.removeAllItems();
				for (Stock st: ship.getInventory()) {
					cboSupply.addItem(st);
				}				
			}
		} else {
			if (cboSupply.isVisible())
				cboSupply.setVisible(false);
		}
	}
	
	/**
	 * Called when btn GiveOrder is clicked
	 * @param crew The crew whom the panel show status
	 * @param ship current ship
	 */
	void GiveOrder(JComboBox<String> cboAction, Crew crew, Spaceship ship) {
		if (!cboAction.isEnabled())
			return;
		
		boolean check = true;
		switch (cboAction.getSelectedItem().toString()) {
		case "Sleep":
			crew.sleep();
			break;
		case "Repair":
			if(!crew.repair(ship)) {
	    		StaticObjects.MessBox("Crew is too hungry/tired to do this. Sleep or use supplement.", "Cannot perform order", "Error");
	    		check = false;
			}
			break;
		case "Pilot":
			if(!crew.pilotShip()) {
	    		StaticObjects.MessBox("Crew is too hungry/tired to do this. Sleep or use supplement.", "Cannot perform order", "Error");
	    		check = false;
			}
			break;
		case "Use Supplement (x2)": 
			for (Stock st:ship.getInventory()) {
				if (cboSupply.getSelectedItem().equals(st)) {
					if(!crew.useSupply(2, st)) {
						StaticObjects.MessBox("Have less than 2x "+st.getName(), "Not enough Stock", "Error");
						check = false;
					} else
						cboSupply.setVisible(false);
				}
			}
			break;	
		default:
			check = false;
			break;
		}

	    crew.setName(txtName.getText());
	    txtName.setText(crew.getName());
	    if (check) {	    	
	    	cboAction.setEnabled(false);
			if (!crew.isSick())
				contentPan.setBackground(new Color(240, 240, 240));
			
			progHealth.setValue(crew.getHealth());
		    progHunger.setValue(crew.getHunger());
		    progMorale.setValue(crew.getMorale());
		    progHealth.setToolTipText(crew.getHealth()+"/"+progHealth.getMaximum());
		    progHunger.setToolTipText(crew.getHunger()+"/"+progHunger.getMaximum());
		    progMorale.setToolTipText(crew.getMorale()+"/"+progMorale.getMaximum());
	    }
	}
}
