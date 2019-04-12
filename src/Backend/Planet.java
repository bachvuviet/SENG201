package Backend;

import SpaceVessel.Stock;

public class Planet extends Entity implements CelestialBody {
	protected int radius;
    protected Stock hiddenStock;
	
	public Planet (int x, int y, int r, String Name, String path) {
		super(x, y, r, r, Name, path);
	}
	
	public void getRadius() {
		
	}
	public void getHiddenTreasure() {
		
	}
	
	public String toString() {
		return Name + " Planet";
	}
}
