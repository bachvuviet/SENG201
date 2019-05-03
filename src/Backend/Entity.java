package Backend;

import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import CustomUIELmt.StaticObjects;

/**
 * Entity means everything graphical show in screen with (x,y) coordinate in this game.
 * @author Bach Vu, Linh Luu
 * @version 0.30
 */
public abstract class Entity implements Serializable {
	private static final long serialVersionUID = 1L;

	protected String Name;	

	protected double x, y, Width, Height;
	protected transient Image visual;
	protected String DefaultPath = "";
	
	public Entity() {}
	//Resized
	public Entity(int x, int y, int width, int height, String Name, String defaultPath, ArrayList<String> imagePack) {
		this.x = x;	this.Width = width;
		this.y = y; this.Height = height;
		this.Name = Name;
		this.DefaultPath = defaultPath;
		if (imagePack != null) {
			DefaultPath = randomPath(imagePack);
		}
	}
	
	private String randomPath(ArrayList<String> lol) {
		Collections.shuffle(lol);
		return lol.get(0);
	}
	
	/**
	 * Get Image of object
	 * @return Image of Label Image Icon
	 */
	public Image getImage() {
		visual = StaticObjects.SelfResizeImage(DefaultPath, this, (int) Width, (int) Height);
		return visual;
	}

	/**
	 * get horizontal pixel for label
	 * @return x
	 */
	public int getX() {
		return (int) x;
	}

	/**
	 * get vertical pixel for label
	 * @return y
	 */
	public int getY() {
		return (int) y;
	}
	
	/**
	 * Get Center of scan, horizontal pixel
	 * @return x + radius
	 */
	public int getcenterX() {
		return (int) (x + Width/2);
	}

	/**
	 * Get Center of scan, vertical pixel
	 * @return y + height/2
	 */
	public int getcenterY() {
		return (int) (y + Height/2);
	}

	/**
	 * Get Width
	 * @return width
	 */
	public int getWidth() {
		return (int) Width;
	}
	
	/**
	 * Get Height
	 * @return height
	 */
	public int getHeight() {
		return (int) Height;
	}
	
	/**
	 * Get radius, return height or width depends which is bigger
	 * @return radius of scan
	 */
	public int getScanRadius() {
		if (Height < Width)
			return (int) Width/2;
		else
			return (int) Height/2;
	}
	
	/**
	 * a string representation of the object.
	 */
	public abstract String toString();
}
