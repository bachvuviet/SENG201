package Backend;

import SpaceVessel.Spaceship;

/**
 * A type of entity
 * @author Bach Vu, Linh Luu
 * @version 0.30
 */
public class BlackHole extends Entity implements CelestialBody {
	public Galaxy DestinationGalaxy;
	
	public BlackHole(int x, int y, int w, int h, String Name, String path, Galaxy gala) {
		super(x, y, w, h, Name, path);
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
