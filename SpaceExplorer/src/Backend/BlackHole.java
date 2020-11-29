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
	private static final long serialVersionUID = 1L;
	public Galaxy Destination;
	
	public BlackHole(int x, int y, int w, int h, String Name, Galaxy gala) {
		super(x, y, w, h, Name, "", new ArrayList<String>(Arrays.asList("/Celestial/Hole1.png","/Celestial/Hole2.png")));
		Destination = gala;
	}
	
	public Galaxy JumptoGalaxy(Spaceship ship) {
		Destination.updateShip(ship);
		return Destination;
	}
	/**
	 * Display the destination of the galaxy the ship supposed to jump
	 * @return Formated String
	 */
	@Override
	public String toString() {
		return "Ready to jump to "+Destination.getGalaxyName()+"!";
	}

}
