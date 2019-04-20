package SpaceVessel;

import javax.swing.ImageIcon;

import CustomUIELmt.StaticObjects;

/**
 * Medicine Improve health of crews
 * @author Bach Vu, Linh Luu
 * @version 0.30
 */
public class Stock_Medicine implements Stock, Cloneable {
	/** Name of medicine*/
    private String Name; 
    /** Desease heal*/
    private String healCategory;
    /** amount of Health recover*/
    private int boostValue;
    /** image of medicine*/
    private ImageIcon Image;
    /** Amount current have in ship's inventory*/
    private int amount = 15;
    private int Price;
    
    public Stock_Medicine(String name, String healCategory, int boost, int price, String path) { 
        this.Name = name;
        this.healCategory = healCategory;
        this.boostValue = boost;
        this.Price = price;
        this.Image = new ImageIcon(StaticObjects.SelfResizeImage(path, this, 50, 50));
    }
    
    //getter
	public String getName() {
		return Name;
	}
	public String getHealCategory() {
		return healCategory;
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
        return boostValue*amount;
    }
    
    //toStrings
	public String getStockStatus() {
		return amount +"x "+ Name + " (MED for " + healCategory +"):<p> --> Effect: Crew HP +" +boostValue;
	}
	public String toStringPrice() {
		return Name + " x5: "+ Price + "$ each.";
	}
	public String toString() {
		return Name + " x"+ amount;
	}
	
	public Stock clone() throws CloneNotSupportedException {
		return (Stock) super.clone();
	}
}