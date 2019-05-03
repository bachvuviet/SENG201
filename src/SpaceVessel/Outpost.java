package SpaceVessel;

import Backend.*;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;

/**
 * Outpost are space object thats created by human.Super class of spaceship, which has Inventory and methods related to Stock as in Spaceship
 * @author Bach Vu, Linh Luu
 * @version 0.30
 */
public class Outpost extends Entity {
	protected int inventorySize = 100;
	protected ArrayList<Stock> INVENTORY = new ArrayList<Stock>();
    
    /**
     * Create Trade outpost/Space station
	 * @param x Horizontal pixel location
	 * @param y Vertical pixel location
	 * @param w Width of Outpost image
	 * @param h Height of Outpost image
	 * @param Name Ship name
	 * @param path path to spaceship Icon
     */
    public Outpost(int x, int y, int w, int h, String Name, String path) {
		super(x, y, w, h, Name, path, null);
	}
    
    /**
     * Add Stock (object) to outpost Inventory at the start of the game
     * @param st Stock adding
     */
    public void addStock(Stock st) {
    	INVENTORY.add(st);
    }

    /**
     * @return Inventory of out post
     */
    public ArrayList<Stock> getInventory(){
    	return INVENTORY;
    }
    /**
     * Produce formated string represent all stock status in the inventory
     * @return Formated string
     */
    public String ListofStock() {
    	String mystr = "";
    	int totalStock = 0;
    	for (Stock stock:INVENTORY) {
    		mystr += "<p>"+ stock.getStockStatus();
    		totalStock += stock.getAmount();
    	}
    	
    	mystr = "<html><h2>Inventory: " + totalStock + "/" + inventorySize
				+ mystr + "</html>";
    	return mystr;
    }
    
	@Override
	/**
	 * @return String representqtion of outpost
	 */
	public String toString() {
		return "Welcome to " + Name;
	}
	
	public void changeStockAmount(int amount, Stock stock) {
		for (Stock st: INVENTORY) {
			if ((st.getName()).equals(stock.getName())) {
				st.setAmount(amount + st.getAmount());
				break;
			}
		}
	}
	
	//Object movement
	double velocity;
	int direction = 0;
	public boolean turning = false;
	
	public void render(Graphics g) {
		switch (direction) {
		case 1: 
			visual = new ImageIcon(this.getClass().getResource("/Ship/spaceshipLeft.png")).getImage();
			break;
		case 2:
			visual = new ImageIcon(this.getClass().getResource("/Ship/spaceshipDown.png")).getImage();
			break;
		case 3:
			visual = new ImageIcon(this.getClass().getResource("/Ship/spaceshipRight.png")).getImage();
			break;
		default:
			visual = new ImageIcon(this.getClass().getResource("/Ship/spaceshipUp.png")).getImage();
			break;
		}
		g.drawImage(visual, (int) x, (int) y, null);
	}
	
	//Controls Ship Methods
	/**
	 * Get new location of Spaceship on screen
	 */
	public void UpdateLocation() {
		switch (direction) {
			case 0:
				y -= velocity;
				break;
			case 1: 
				x -= velocity;
				break;
			case 2:
				y += velocity;
				break;
			case 3:
				x += velocity;
				break;
			default:
				break;
		}
	}
	/** Go straight*/
	public void forward() {
		velocity = 5;
		UpdateLocation();
	}
	/** Turn left*/
	public void toPort() {
		if (!turning) {
			if (direction < 3)
				direction += 1;
			else
				direction = 0;
			turning = true; 			
		}
	}
	/** Turn right*/
	public void toStarBoard() {
		if (!turning) {
			if (direction == 0)
				direction = 3;
			else
				direction -= 1;
			turning = true; 
		}
	}
	/** Reverse*/
	public void reverse() {
		velocity = -2;
		UpdateLocation();
	}
	/** Stop the Ship*/
	public void stop() {
		velocity = 0;
		UpdateLocation();
	}
	/**
	 * get current direction of Ship
	 * @return WASD = 0123
	 */
	public int getDirect() {
		return direction;
	}
}
