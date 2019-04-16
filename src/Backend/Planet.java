package Backend;

import SpaceVessel.Stock;
import SpaceVessel.Stock_Food;

public class Planet extends Entity implements CelestialBody {
	protected int radius;
    protected Stock hiddenStock;
	
	public Planet (int x, int y, int r, String Name, String path) {
		super(x, y, r, r, Name, path);
		hiddenStock = new Stock_Food("Chips",10,"../sushi.png");
	}
	
	public void getRadius() {
		
	}
	public Stock getHiddenTreasure() {
		return hiddenStock;
	}
	
	public String toString() {
		return Name + " Planet";
	}
}
