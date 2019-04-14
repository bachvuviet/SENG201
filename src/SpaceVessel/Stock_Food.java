package SpaceVessel;

public class Stock_Food implements Stock{
	private String Name;
	private final String CATEGORY = "FOOD";
	private int boostValue;
	public int amount = 20;
	
	public Stock_Food(String name, int boost) {
		this.Name = name;
		this.boostValue = boost;
	}
	
	//getter
	public String getName() {
		return Name;
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
        return amount*boostValue;
	}	
	
	//toStrings
	public String getStockStatus() {
		return amount +"x " +Name + " (" + CATEGORY + "):<p> --> Effect: Crew Hunger +" +boostValue;
	}
	public String toString() {
		return Name;
	}
}