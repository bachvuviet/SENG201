package Backend;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A type of planet
 * @author Bach Vu, Linh Luu
 * @version 0.30
 */
public class Planet_GasGiant extends Planet implements CelestialBody {
	
	public Planet_GasGiant(int x, int y, int r, String Name) {		
		super(x, y, r, Name, new ArrayList<String>(Arrays.asList("/Celestial/Gas1.png","/Celestial/Gas2.png","/Celestial/Gas3.png")));
		backgroundPath = "/Background/GasBack.jpg";
	}
}
