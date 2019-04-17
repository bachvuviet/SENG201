package SpaceVessel;

import javax.swing.ImageIcon;

import CustomUIELmt.StaticObjects;

/**
 * Medicine Improve health of crews
 * @author Bach Vu, Linh Luu
 * @version 0.30
 */
public class Stock_Medicine implements Stock {
	/** Name of medicine*/
    private String Name; 
    /** Desease heal*/
    private String healCategory;
    /** amount of Health recover*/
    private int boostValue;
    /** image of medicine*/
    private ImageIcon Image;
    /** Amount current have in ship's inventory*/
    private int amount = 30;
    
    public Stock_Medicine(String name, String healCategory, int boost, String path) { 
        this.Name = name;
        this.healCategory = healCategory;
        this.boostValue = boost;
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
	public String toString() {
		return Name + " x"+ boostValue;
	}
}