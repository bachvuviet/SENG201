package Backend;

import SpaceVessel.Stock;

public class Planet_GasGiant extends Planet implements CelestialBody {

	public Planet_GasGiant(int x, int y, int r, String Name, String path) {
		super(x, y, r, Name, path);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getRadius() {
		return radius;
		// TODO Auto-generated method stub
		
	}

	@Override
	public Stock getHiddenTreasure() {
		return hiddenStock;
		// TODO Auto-generated method stub
		
	}

}
