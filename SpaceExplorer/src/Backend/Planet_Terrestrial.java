 package Backend;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A type of planet
 * @author Bach Vu, Linh Luu
 * @version 0.30
 */
public class Planet_Terrestrial extends Planet implements CelestialBody {
	private static final long serialVersionUID = 1L;

	public Planet_Terrestrial(int x, int y, int r, String Name) {
		super(x, y, r, Name, new ArrayList<String>(Arrays.asList("/Celestial/Earth1.png","/Celestial/Earth2.png","/Celestial/Earth3.png","/Celestial/RandomPlanet2.png")));
		backgroundPath = "/Background/EarthBack.jpg";
	}
}
