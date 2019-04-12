package SpaceVessel;

public class Stock_Medicine implements Stock{
    private String Name; 
    private String healCategory;
    private int boostValue;
    public int amount = 30;
    
    public Stock_Medicine(String name, String healCategory, int boost) { 
        this.setName(name);
        this.setHealCategory(healCategory);
        this.boostValue = boost;

    }
    
    public int use(int amount) {
        return boostValue*amount;
    }
    
    public String toString() {
		return amount +"x "+ Name + " (MED for " + healCategory +"):<p> --> Effect: Crew HP +" +boostValue;
    }

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		this.Name = name;
	}

	public String getHealCategory() {
		return healCategory;
	}

	public void setHealCategory(String healCategory) {
		this.healCategory = healCategory;
	}

	@Override
	public int getAmount() {
		// TODO Auto-generated method stub
		return amount;
	}

	@Override
	public void setAmount(int amount) {
		// TODO Auto-generated method stub
		this.amount = amount;
	}
}