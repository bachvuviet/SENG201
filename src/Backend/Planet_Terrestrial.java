package Backend;

import SpaceVessel.Stock;

public class Planet_Terrestrial extends Planet implements CelestialBody {

	public Planet_Terrestrial(int x, int y, int r, String Name, String path) {
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
