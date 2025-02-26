package SpaceVessel;

import javax.swing.ImageIcon;

import CustomUIELmt.StaticObjects;

/**
 * Medicine Improve health of crews
 * @author Bach Vu, Linh Luu
 * @version 0.30
 */
public class Stock_Medicine implements Stock, Cloneable {
	private static final long serialVersionUID = 1L; 
    /** Desease heal*/
    public String healCategory;
	/** Name of medicine*/
    private String Name;
    /** amount of Health recover*/
    private int boostValue;
    /** image of medicine*/
    private String imagePath;
    /** Amount current have in ship's inventory*/
    private int amount;
    private int Price;
    
    public Stock_Medicine(String name, String healCategory, int boost, int price, int amount, String path) { 
        this.Name = name;
        this.healCategory = healCategory;
        this.boostValue = boost;
        this.Price = price;
        this.imagePath = path;
        this.amount = amount;
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
		return amount +"x "+ Name + " (MED for " + healCategory +"):<p> --> Effect: Crew HP +" +boostValue;
	}
	public String toStringPrice() {
		return Name + ": "+ Price + "$ each.";
	}
	public String toString() {
		return Name + " x"+ amount;
	}
	
	public Stock clone() throws CloneNotSupportedException {
		return (Stock) super.clone();
	}
}