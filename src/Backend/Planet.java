package Backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import SpaceVessel.Stock;
import SpaceVessel.Stock_Food;
import SpaceVessel.Stock_Medicine;



/**
 * This class contain only static methods to support gaming experience
 * @author Bach Vu
 * @version 1.0
 */
public class Planet extends Entity implements CelestialBody {
	protected int radius;
    protected Stock hiddenStock;
    protected boolean check=false;
    protected Random num = new Random();
	
	public Planet (int x, int y, int r, String Name, String path) {
		super(x, y, r, r, Name, path);
	}
	
	public int getRadius() {
		return radius/2;
	}
	
	public void setHiddenTreasure() {
		for (int i=1; i <= 20; i++) {
			int val = num.nextInt(i);
			//"lol"
		Stock food1 = new Stock_Food("Burger", val, "/burger.png");
		Stock food2 = new Stock_Food("Bread", val, "/bread.png");
		Stock food3 = new Stock_Food("Pizza", val, "/pizza.png");
		Stock food4 = new Stock_Food("Chicken", val, "/Chicken.png");
		Stock food5 = new Stock_Food("Steak", val, "/steak.png");
		Stock food6 = new Stock_Food("Sushi", val, "/sushi.png");
		
		Stock medi1 = new Stock_Medicine("Healing Potion", "Heart", val, "/healpotion.png");
		Stock medi2 = new Stock_Medicine("Pain Killer", "Morale", val, "/painkiller.png");
		Stock medi3 = new Stock_Medicine("Syringe", "Any disease", val, "/syringe.png");
		
		ArrayList<Stock> STOCK = new ArrayList<Stock>();
		STOCK.add(food1);STOCK.add(food2);
		STOCK.add(food3);STOCK.add(food4);
		STOCK.add(food5);STOCK.add(food6);
		STOCK.add(medi1);STOCK.add(medi2);
		STOCK.add(medi3);
		
		Collections.shuffle(STOCK);
		for (Stock st: STOCK) {
			hiddenStock = st;
		}
	}

		//System.out.println(STOCK);
		//System.out.println(val);
	}
	
	public boolean getScan() {
		return check;
	}
	
	public Stock getHiddenTreasure() {
		setHiddenTreasure();
		check = true;
		return hiddenStock;
	}
	
	public String toString() {
		return Name + " Planet";
	}
}

