package Lab4;

public class Planet {
	//Super class
	private String Name;
	private int Order;
	private String Temp;
	
	//public Planet() {}
	public Planet(String name, int order, String temperature){
		Name = name;
		Order = order;
		Temp = temperature;
	}
	public String getName() {
		return Name;
	}
	public String getTemp() {
		return Temp;
	}
	
	public String orderFromSun() {
		return String.format("%s is number %d from the Sun", Name, Order);
	}
	
	public String toString() {
		return orderFromSun() + " and is " + getTemp();
	}
}
