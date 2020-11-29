package Lab3;

public class LiftoffWatch {
	private double temperature=0;
	private String weather="";
	private double wind=0;
	
	public void setTemp(double temp) {
		temperature=temp;
	}
	public void setWeather(String state) {
		weather = state.toLowerCase();
	}
	public void setWind(double speed) {
		wind=speed;
	}
	public boolean canWeLaunch() {
		boolean canLaunch = true;
		if (temperature > 34.0 || temperature < 16.5)
			canLaunch = false;
		
		if (canLaunch) {
			System.out.println(weather);
			System.out.println(wind);
			System.out.println(weather.equals("sunny"));
			if (weather == "sunny" && wind <= 60.0)			
				canLaunch = true;//do nothing
			else if (weather == "cloudy" && wind <= 45.0)
				canLaunch = true;//do nothing 
			else
				canLaunch = false;
		}
		
		return canLaunch;
	}	
}
