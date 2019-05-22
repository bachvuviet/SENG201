package SpaceVessel;

import javax.swing.ImageIcon;

import CustomUIELmt.StaticObjects;

/**
 * Food improve crew hunger
 * @author Bach Vu, Linh Luu
 * @version 0.30
 */
public class Stock_Food implements Stock, Cloneable {
	private static final long serialVersionUID = 1L;
	private String Name;
	private int boostValue;
	private String imagePath;
	private int amount;
	private int Price;
	
	public Stock_Food(String name, int boost, int price, int amount, String path) {
		this.Name = name;
		this.boostValue = boost;
		this.Price = price;
		this.amount = amount;
		this.imagePath = path;
	}
	
	//getter
	public String getName() {
		return Name;
	}
	public int getAmount() {
		return amount;
	}
	public ImageIcon getImage() {
		ImageIcon Image = new ImageIcon(StaticObjects.SelfResizeImage(imagePath, this, 50, 50));
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
    	if (amount <= this.amount) {
    		this.amount -= amount;
    		return boostValue*amount;    		
    	} else
    		return 0;    	
    }	
	
	//toStrings
	public String getStockStatus() {
		return amount +"x " +Name + " (Food):<p> --> Effect: Crew Hunger +" +boostValue;
	}
	public String toStringPrice() {
		return Name + ": "+ Price + "$ each";
	}
	public String toString() {
		return Name +" x"+ amount;
	}
	
	public Stock clone() throws CloneNotSupportedException {
		return (Stock) super.clone();
	}
}