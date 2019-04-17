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

import SpaceVessel.*;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

/** Class Intro:
 * @author Linh Luu
 * @category SpaceShip game
 * @version 0.30
 * @location Lab133
 * This class make instances of JPanel, which is used to create 4 instances (for each crew) in the pause frame, Crew tab.
 * User can give crew order individually, and view crew status.
 */
public class CrewPanel {
	public JPanel contentPan;

    JTextField txtName = new JTextField();
	JComboBox<String> cboAction1;
	JComboBox<String> cboAction2;
	JComboBox<Stock> cboSupply;
	JProgressBar progHealth = new JProgressBar();
	JProgressBar progHunger = new JProgressBar();
	JProgressBar progMorale = new JProgressBar();

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
        
	    JLabel lblAvatar = new JLabel(crew.getAvatar());
	    Border border = BorderFactory.createLineBorder(Color.BLACK, 3);
	    lblAvatar.setBorder(border);
        lblAvatar.setBackground(Color.GRAY);
        lblAvatar.setForeground(Color.RED);
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
        
        progHunger.setBounds(188, 67, 160, 15);
        progHunger.setValue(crew.getHunger());
        progHunger.setForeground(Color.BLUE);
        contentPan.add(progHunger);
        
        progHealth.setBounds(188, 43, 160, 15);
        progHealth.setValue(crew.getHealth());
        progHealth.setForeground(Color.RED);
        contentPan.add(progHealth);   
        
        progMorale.setBounds(188, 90, 160, 15);
        progMorale.setValue(crew.getMorale());
        progMorale.setForeground(Color.YELLOW);
        contentPan.add(progMorale);

        String[] ActionSet = {"", "Sleep", "Repair", "Pilot", "Use Supplement"};
        cboAction1 = new JComboBox<String>(ActionSet);
        cboAction1.setBounds(188, 131, 160, 19);
        cboAction1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				String choice = "";
				if (cboAction1.getItemCount() > 0)
        			choice = cboAction1.getSelectedItem().toString();
				
				cboAction2.removeAllItems();
				for (String str:ActionSet) {
					if (!choice.equals(str))
						cboAction2.addItem(str);
				}
				cboAction2.setEnabled(true);

				if (choice.equals(ActionSet[4])) {
					cboSupply.setVisible(true);
				} else {
					cboSupply.setVisible(false);
				}
			}
		});
        contentPan.add(cboAction1);
        
        cboAction2 = new JComboBox<String>(ActionSet);
        cboAction2.setEnabled(false);
        cboAction2.setBounds(188, 162, 160, 19);
        cboAction2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String choice = "";
        		if (cboAction2.getItemCount() > 0)
        			choice = cboAction2.getSelectedItem().toString();
				if (choice.equals(ActionSet[4])) {
					cboSupply.setVisible(true);
				} else {
					cboSupply.setVisible(false);
				}
				cboAction1.setEnabled(false);
			}
		});
        contentPan.add(cboAction2);
        
        ArrayList<Stock> stock = ship.getInventory();
        cboSupply = new JComboBox<Stock>(stock.toArray(new Stock[stock.size()]));
        cboSupply.setBounds(12, 200, 111, 20);        
        cboSupply.setVisible(false);
        contentPan.add(cboSupply);
        
        JButton btnGiveOrder = new JButton("Give Order");
        btnGiveOrder.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {        		
        		GiveOrder(crew, ship);
        	}
        });
        
        btnGiveOrder.setBounds(138, 197, 210, 25);
        contentPan.add(btnGiveOrder);
        
        contentPan.revalidate();
        contentPan.repaint();
	}
	
	/**
	 * Called when btn GiveOrder is clicked
	 * @param crew The crew whom the panel show status
	 * @param ship current ship
	 */
	void GiveOrder(Crew crew, Spaceship ship) {
		ArrayList<Stock> stock = ship.getInventory();
		//Action1
		switch (cboAction1.getSelectedItem().toString()) {
		case "Sleep":
			crew.sleep();
			break;
		case "Repair":
			crew.repair(ship);
			break;
		case "Pilot":
			crew.pilotShip();
			break;
		case "Use Supplement":
			for (Stock st: stock) {
				if (cboSupply.getSelectedItem().equals(st)) {
					crew.useSupply(1, st);
		        } else {
			        System.out.println("false");
		        }
			}
			break;	
		default:
			break;
		}
		
		//Action2
		switch (cboAction2.getSelectedItem().toString()) {
		case "Sleep":
			crew.sleep();
			break;
		case "Repair":
			crew.repair(ship);
			break;
		case "Pilot":
			crew.pilotShip();
			break;
		case "Use Supplement":
			for (Stock st: stock) {
				if (cboSupply.getSelectedItem().equals(st)) {
					crew.useSupply(1, st);
		        } else {
			        System.out.println("false");
		        }
			}
			break;	
		default:
			break;
		}

	    crew.setName(txtName.getText());
	    
		progHealth.setValue(crew.getHealth());
	    progHunger.setValue(crew.getHunger());
	    progMorale.setValue(crew.getMorale());
	    txtName.setText(crew.getName());
	}
}
