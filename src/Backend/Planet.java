package Backend;


import SpaceVessel.Stock;

/**
 * This class contain only static methods to support gaming experience
 * @author Bach Vu
 * @version 1.0
 */
public class Planet extends Entity implements CelestialBody {
	protected int radius;
    protected Stock hiddenStock;
    protected boolean check=false;
	
	public Planet (int x, int y, int r, String Name, String path) {
		super(x, y, r, r, Name, path);
	}
	
	public int getRadius() {
		return radius/2;
	}
	
	public boolean getScan() {
		return check;
	}
	
	public void setHiddenTreasure(Stock st) {
	    hiddenStock = st;
	    
	}
	
	public Stock getHiddenTreasure() {
		check = true;
		return hiddenStock;
	}
	
	public String toString() {
		return Name + " Planet";
	}
}

