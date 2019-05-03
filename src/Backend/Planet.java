package Backend;


import java.util.ArrayList;

import SpaceVessel.Stock;

/**
 * This class contain only static methods to support gaming experience
 * @author Bach Vu
 * @version 1.0
 */
public class Planet extends Entity implements CelestialBody {
	private static final long serialVersionUID = 1L;
	protected Stock hiddenStock;
    protected boolean Scanned = false;
    protected String backgroundPath;
    
	public Planet() {}
	public Planet (int x, int y, int r, String Name, ArrayList<String> imagePack) {
		super(x, y, r, r, Name, "", imagePack);
	}
	
	public String getBackgroundPath() {
		return backgroundPath;
	}
	
	public boolean getScan() {
		return Scanned;
	}
	public void setTreasure(Stock st) {
	    hiddenStock = st;
	}
	
	public Stock getTreasure() {
		Scanned = true;
		return hiddenStock;
	}
	
	public String toString() {
		return Name + " Planet";
	}
}

