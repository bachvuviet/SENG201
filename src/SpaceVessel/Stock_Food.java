package SpaceVessel;

public class Stock_Food implements Stock{
	private String Name;
	private final String CATEGORY = "FOOD";
	private int boostValue;
	public int amount = 20;
	//private String allergyCategory;
	
	public Stock_Food(String name, int boost) {
		this.Name = name;
		this.boostValue = boost;
	}
	
	public int use(int amount) {
        return amount*boostValue;
	}
	
	public String toString() {
		return amount +"x " +Name + " (" + CATEGORY + "):<p> --> Effect: Crew Hunger +" +boostValue;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		this.Name = name;
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