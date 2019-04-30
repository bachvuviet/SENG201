package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import Backend.*;
import CustomUIELmt.StaticObjects;
import SpaceVessel.*;
/**
 * This class make instances of JPanel, which Game Environment can initiate multiple galaxies.
 * Process User input and update controls in ControlPanel (bottom right) of Game environment
 * @author Bach Vu
 * @version 0.30
 */
public class GalaxyPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	public JPanel galaxy;
	JLabel galaBack;
	
	public GalaxyPanel(int x, int y) {
		galaxy = new JPanel();
	}
	
	public void UpgradeBackground(int x, int y, String path) {
		//Background		
		Image IMG = StaticObjects.SelfResizeImage(path, this, x, y);	
		galaBack.setIcon(new ImageIcon(IMG));
		
		galaxy.revalidate();
		galaxy.repaint();
	}
}
