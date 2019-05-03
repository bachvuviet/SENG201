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
		super(x, y, r, Name, new ArrayList<String>(Arrays.asList("/Celestial/Colorful1.png","/Celestial/Colorful2.png","/Celestial/Colorful3.png","/Celestial/RandomPlanet1.png")));
		backgroundPath = "/Background/ColorfulBack.png";
	}
	
}
