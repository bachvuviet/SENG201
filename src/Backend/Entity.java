package Backend;

import java.awt.Graphics;
import java.awt.Image;

import CustomUIELmt.StaticObjects;

public abstract class Entity {
	protected String Name;	

	protected double x, y, Width, Height;
	protected Image visual;
	protected String DefaultPath = "";
	
	//Size of Image
	public Entity(double x, double y, String Name, String defaultPath) {
		this.x = x;
		this.y = y;
		this.Name = Name;
		this.DefaultPath = defaultPath;

		visual = StaticObjects.SelfResizeImage(DefaultPath, this, 50, 70);
	}
	
	//Resized
	public Entity(int x, int y, int width, int height, String Name, String defaultPath) {
		this.x = x;	this.Width = width;
		this.y = y; this.Height = height;
		this.Name = Name;
		this.DefaultPath = defaultPath;

		visual = StaticObjects.SelfResizeImage(DefaultPath, this, (int) Width, (int) Height);
	}
	
	public void UpdateLocation(double velX, double velY) {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(visual, (int) x, (int) y, null);
		//System.out.println("Speed: " +x+" - "+  y);
	}
	
	public Image getImage() {
		return visual;
	}

	public int getX() {
		return (int) x;
	}

	public int getY() {
		return (int) y;
	}
	
	public int getcenterX() {
		return (int) (x + Width/2);
	}

	public int getcenterY() {
		return (int) (y + Height/2);
	}

	public int getWidth() {
		return (int) Width;
	}
	
	public int getHeight() {
		return (int) Height;
	}
	
	public int getScanRadius() {
		if (Height < Width)
			return (int) Width/2;
		else
			return (int) Height/2;
	}
	
	public abstract String toString();
}
