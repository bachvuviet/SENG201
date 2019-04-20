package SpaceVessel;

import Backend.*;
import java.util.ArrayList;

/**
 * Outpost are space object thats created by human.Super class of spaceship, which has Inventory and methods related to Stock as in Spaceship
 * @author Bach Vu, Linh Luu
 * @version 0.30
 */
public class Outpost extends Entity {
	protected int inventorySize = 100;
	protected int baseSpeed; //temporary
    protected int baseShield; // temporary
    protected int shield;
    protected int energy;
	protected ArrayList<Stock> INVENTORY = new ArrayList<Stock>();
    
	/**
	 * Create Spaceship
	 * @param x Horizontal pixel location
	 * @param y Vertical pixel location
	 * @param Name Ship name
	 * @param path path to spaceship Icon
	 */
    public Outpost(double x, double y, String Name, String path) {
		super(x, y, Name, path);
	}
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
		super(x, y, w, h, Name, path);
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
		// TODO Auto-generated method stub
		for (Stock st: INVENTORY) {
			if ((st.getName()).equals(stock.getName())) {
				st.setAmount(amount + st.getAmount());
			break;
			}
		}
	}
}
