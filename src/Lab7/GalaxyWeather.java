package Lab7;

//one class needs to have a main() method
public class GalaxyWeather extends Observable
{
	private boolean isSolarFlare;
	private boolean isSpaceStorm;
	private boolean isComets;
	
	public GalaxyWeather() {
		this.isSolarFlare = false;
		this.isSpaceStorm = false;
		this.isComets = false;
	}

	public void setIsSolarFlare(boolean isSolarFlare) {
		if (this.isSolarFlare != isSolarFlare) {
		    this.isSolarFlare = isSolarFlare;
		 	setChanged();
		 	notifyObservers();
		}
	}
	
	public void setIsSpaceStorm(boolean isSpaceStorm) {
		if (this.isSpaceStorm != isSpaceStorm) {
		   	this.isSpaceStorm = isSpaceStorm;
		 	setChanged();
		 	notifyObservers();
		}
	}
	
	public void setIsComets(boolean isComets) {
		if (this.isComets != isComets) {
		   	this.isComets = isComets;
		 	setChanged();
		 	notifyObservers();
		}
	}
	
	public boolean getIsSolarFlare() {
	 	return isSolarFlare;
	}
	
	public boolean getIsSpaceStorm() {
	 	return isSpaceStorm;
	}
	
	public boolean getIsComets() {
	 	return isComets;
	}
}