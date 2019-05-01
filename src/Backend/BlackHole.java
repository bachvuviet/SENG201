package Backend;

import java.util.ArrayList;
import java.util.Arrays;

import SpaceVessel.Spaceship;

/**
 * A type of entity
 * @author Bach Vu, Linh Luu
 * @version 0.30
 */
public class BlackHole extends Entity implements CelestialBody {
	public Galaxy DestinationGalaxy;
	
	public BlackHole(int x, int y, int w, int h, String Name, Galaxy gala) {
		super(x, y, w, h, Name, "", new ArrayList<String>(Arrays.asList("/Hole1.png","/Hole2.png")));
		DestinationGalaxy = gala;
	}

	public Galaxy JumptoGalaxy(Spaceship ship) {
		DestinationGalaxy.updateShip(ship);
		return DestinationGalaxy;
	}
	
	@Override
	public String toString() {
		return "Ready to jump to "+DestinationGalaxy.getGalaxyName()+"!";
	}

}
