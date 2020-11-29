package Lab7;

public class Starman implements Observer {
	  
	  public void startObserving(GalaxyWeather galaxyWeather) {
	      galaxyWeather.addObserver(this);
	  }
	  
	  public void stopObserving(GalaxyWeather galaxyWeather) {
	      galaxyWeather.deleteObserver(this);
	  }
	  
	  @Override
	  public void update(Observable o, Object arg) {
		  if (o instanceof GalaxyWeather) {
		      if (((GalaxyWeather)o).getIsSolarFlare()) {
		    	  System.out.println("Sigh... no adventuring today");
		      } else if (((GalaxyWeather)o).getIsComets()) {
		    	  System.out.println("I'll show those comets who's boss!");
		      }
		  }
	  }
}