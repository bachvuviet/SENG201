package SpaceVessel;

import javax.swing.ImageIcon;

import CustomUIELmt.StaticObjects;

/**
 * Food improve crew hunger
 * @author Bach Vu, Linh Luu
 * @version 0.30
 */
public class Stock_Food implements Stock, Cloneable {
	private String Name;
	private final String CATEGORY = "FOOD";
	private int boostValue;
	private ImageIcon Image;
	private int amount = 10;
	private int Price;
	
	public Stock_Food(String name, int boost, int price, String path) {
		this.Name = name;
		this.boostValue = boost;
		this.Price = price;
		this.Image = new ImageIcon(StaticObjects.SelfResizeImage(path, this, 50, 50));
	}
	
	//getter
	public String getName() {
		return Name;
	}
	public int getAmount() {
		return amount;
	}
	public ImageIcon getImage() {
		return Image;
	}
	public int getPrice() {
		return Price;
	}

	//setter
	public void setAmount(int amount) {
		this.amount = amount;
	}	
	public int use(int amount) {
        return amount*boostValue;
	}	
	
	//toStrings
	public String getStockStatus() {
		return amount +"x " +Name + " (" + CATEGORY + "):<p> --> Effect: Crew Hunger +" +boostValue;
	}
	public String toStringPrice() {
		return Name + " x5: "+ Price + "$ each";
	}
	public String toString() {
		return Name +" x"+ amount;
	}
	
	public Stock clone() throws CloneNotSupportedException {
		return (Stock) super.clone();
	}
}