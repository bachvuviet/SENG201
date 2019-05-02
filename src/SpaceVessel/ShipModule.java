package SpaceVessel;

import javax.swing.ImageIcon;

/**
 * Ship modules are part of space ship. If active, they have boost value tp your ship
 * @author Bach Vu, Linh Luu
 * @version 0.30
 */
public class ShipModule implements Stock {
	private String Name;
    private String Boost;
    private int boostValue;
    private boolean Active = true;
    
    public ShipModule() {}
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
    /**
     * Get boost value of a module
     * @return boost to ship's stat
     */
	public String getBoost() {
		return Boost;
	}
	/**
	 * All module must be active to win the game
	 * @return is Broken or not
	 */
	public boolean isActive() {
		return Active;
	}	
	public String modStatus() {
		if (Active)
			return Name + ": Active";
		else
			return Name + ": Broken";
    }
	public String toString() {
		return Name + ". Press ESC to check ship modules.";
	}
	public ImageIcon getImage() {
		return null;
	}

	//Setter
	/**
	 * When found a module, set active to true
	 * @param active true when found a module
	 */
	public void setActive(boolean active) {
		Active = active;
	}

	public int use(int amount) {
		return 0;
	}

	public int getAmount() {
		return 0;
	}

	@Override
	public void setAmount(int amount) {
		
	}

	@Override
	public String getStockStatus() {
		return null;
	}

	@Override
	public int getPrice() {
		return 0;
	}

	@Override
	public String toStringPrice() {
		return null;
	}
}
