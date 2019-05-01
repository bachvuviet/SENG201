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
		super(x, y, r, Name, new ArrayList<String>(Arrays.asList("/Ocean1.png","/Ocean2.png","/Ocean3.png")));
		backgroundPath = "/OceanBack.jpg";
	}
}
