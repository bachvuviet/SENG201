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
	/**
	 * get the scan of the planet
	 * @return scanned
	 */
	public boolean getScan() {
		return Scanned;
	}
	/**
	 * set the treasure found on the planet
	 * @param st
	 */
	public void setTreasure(Stock st) {
	    hiddenStock = st;
	}
	
	/**
	 * get the treasure found on the planet
	 * @return hiddenStock
	 */
	public Stock getTreasure() {
		Scanned = true;
		return hiddenStock;
	}
	/**
	 * @return name of the planet the ship lands on
	 */
	public String toString() {
		return Name + " Planet";
	}
}

