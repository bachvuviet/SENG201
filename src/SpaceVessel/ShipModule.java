package SpaceVessel;

public class ShipModule implements Stock {
	private String Name;
    private String Boost;
    private int boostValue;
    private boolean Active = true;
    
    
    public ShipModule(String name, String boost, int boostVar) {
    	Name = name;
    	Boost = boost;//Boost Health, Energy, CrewHealth, CrewHunger, Complete
    	boostValue = boostVar;
    }
        
    public int use() {
    	return boostValue;
    }
    
    //Getter
	public String getName() {
		return Name;
	}
	public String getBoost() {
		return Boost;
	}
	public boolean isActive() {
		return Active;
	}	
	public String toString() {
		if (Active)
			return Name + ": Active";
		else
			return Name + ": Broken";
    }

	//Setter
	public void setActive(boolean active) {
		Active = active;
	}

	@Override
	public int use(int amount) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getAmount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setAmount(int amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getStockName() {
		// TODO Auto-generated method stub
		return null;
	}
}
