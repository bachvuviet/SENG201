package Backend;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A type of planet
 * @author Bach Vu, Linh Luu
 * @version 0.30
 */
public class Planet_ColorfulDrawf extends Planet implements CelestialBody{

	public Planet_ColorfulDrawf(int x, int y, int r, String Name) {
		super(x, y, r, Name, new ArrayList<String>(Arrays.asList("/Colorful1.png","/Colorful2.png","/Colorful3.png","/RandomPlanet1.png")));
		backgroundPath = "/ColorfulBack.png";
	}
	
}
