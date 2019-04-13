package SpaceVessel;

import Backend.*;
import java.util.ArrayList;

public class Outpost extends Entity {
	protected int inventorySize = 100;
	protected int baseSpeed; //temporary
    protected int baseShield; // temporary
    protected int shield;
    protected int energy;
	protected ArrayList<Stock> INVENTORY = new ArrayList<Stock>();
    
	
    public Outpost(double x, double y, String Name, String path) {
		super(x, y, Name, path);
	}
    public Outpost(int x, int y, int w, int h, String Name, String path) {
		super(x, y, w, h, Name, path);
	}
    
    public void addStock(Stock st) {
    	INVENTORY.add(st);
    }
    public String ListofStock() {
    	String mystr = "";
    	int totalStock = 0;
    	for (Stock stock:INVENTORY) {
    		mystr += "<p>"+ stock.toString();
    		totalStock += stock.getAmount();
    	}
    	
    	mystr = "<html><h2>Inventory: " + totalStock + "/" + inventorySize
				+ mystr + "</html>";
    	return mystr;
    }
    public ArrayList<Stock> getInventory(){
    	return INVENTORY;
    }
    public Stock[] getItemList(){
    	int size = INVENTORY.size();
    	Stock[] arr = new Stock[size];
    	for (int i = 0; i < size; i++) {
			arr[i] = INVENTORY.get(i);
		} 
    	return arr;
    }
    
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Welcome to " + Name;
	}
}
