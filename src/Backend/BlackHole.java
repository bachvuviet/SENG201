package Backend;

/**
 * A type of entity
 * @author Bach Vu, Linh Luu
 * @version 0.30
 */
public class BlackHole extends Entity implements CelestialBody {

	public BlackHole(int x, int y, int w, int h, String Name, String path) {
		super(x, y, w, h, Name, path);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Ready to jump to new Galaxy?";
	}

}
