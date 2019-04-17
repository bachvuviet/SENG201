package SpaceVessel;

import javax.swing.ImageIcon;

import CustomUIELmt.StaticObjects;

public class Stock_Medicine implements Stock {
    private String Name; 
    private String healCategory;
    private int boostValue;
    private ImageIcon Image;
    public int amount = 30;
    
    public Stock_Medicine(String name, String healCategory, int boost, String path) { 
        this.Name = name;
        this.healCategory = healCategory;
        this.boostValue = boost;
        this.Image = new ImageIcon(StaticObjects.SelfResizeImage(path, this, 50, 50));
    }
    
    //getter
    public String getStockName() {
    	return Name;
    }
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
	public void setName(String name) {
		this.Name = name;
	}
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