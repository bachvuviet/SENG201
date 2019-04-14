package SpaceVessel;

public class Stock_Medicine implements Stock {
    private String Name; 
    private String healCategory;
    private int boostValue;
    public int amount = 30;
    
    public Stock_Medicine(String name, String healCategory, int boost) { 
        this.setName(name);
        this.healCategory = healCategory;
        this.boostValue = boost;

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
		return "";
	}
}