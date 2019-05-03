package Backend;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A type of planet
 * @author Bach Vu, Linh Luu
 * @version 0.30
 */
public class Planet_Ocean extends Planet implements CelestialBody {

	public Planet_Ocean(int x, int y, int r, String Name) {
		super(x, y, r, Name, new ArrayList<String>(Arrays.asList("/Celestial/Ocean1.png","/Celestial/Ocean2.png","/Celestial/Ocean3.png")));
		backgroundPath = "/Background/OceanBack.jpg";
	}
}
