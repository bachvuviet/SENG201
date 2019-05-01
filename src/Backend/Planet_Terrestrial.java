 package Backend;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A type of planet
 * @author Bach Vu, Linh Luu
 * @version 0.30
 */
public class Planet_Terrestrial extends Planet implements CelestialBody {

	public Planet_Terrestrial(int x, int y, int r, String Name) {
		super(x, y, r, Name, new ArrayList<String>(Arrays.asList("/Earth1.png","/Earth2.png","/Earth3.png","/RandomPlanet2.png")));
		backgroundPath = "/Mission.jpg";
	}
}
