package Lab4;

public class GreenAlien {
	private String name="";
	public int eyeCount=0;
	private String favouriteCandy="";
	
	public GreenAlien() {
		name="Kloup";eyeCount=6;favouriteCandy="Lollypops";
	}
	public GreenAlien(String tempName, int tempEye, String tempCandy) {
		name=tempName;eyeCount=tempEye;favouriteCandy=tempCandy;
	}
	
	public String getName() {
		return name;
	}
	public String getCan() {
		return favouriteCandy;
	}
	
	public boolean equals(GreenAlien other) {
		if (this.name == other.name && this.eyeCount == other.eyeCount && this.favouriteCandy == other.favouriteCandy) {
		    
		    return true;
		}
		return false;
	}
	
	
	public String toString() {
		return "This Alien is called "+name+" and has "+eyeCount+" eyes. Gross. It seems to enjoy eating "+favouriteCandy;
	}
}
