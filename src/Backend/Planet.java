package Backend;


import java.util.ArrayList;

import SpaceVessel.Stock;

/**
 * This class contain only static methods to support gaming experience
 * @author Bach Vu
 * @version 1.0
 */
public class Planet extends Entity implements CelestialBody {
	protected int radius;
    protected Stock hiddenStock;
    protected boolean Scanned = false;
    
	public Planet() {}
	public Planet (int x, int y, int r, String Name, ArrayList<String> imagePack) {
		super(x, y, r, r, Name, "", imagePack);
	}
	
	public int getRadius() {
		return radius/2;
	}
	
	public boolean getScan() {
		return Scanned;
	}
	public void setHiddenTreasure(Stock st) {
	    hiddenStock = st;
	    
	}
	
	public Stock getHiddenTreasure() {
		Scanned = true;
		return hiddenStock;
	}
	
	public String toString() {
		return Name + " Planet";
	}
}

