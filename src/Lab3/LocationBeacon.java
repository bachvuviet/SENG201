package Lab3;

public class LocationBeacon {
	private int hours=0;
	private int minutes=0;
	private int distance=0;
	
	public void heartBeat(int startHour, int startMinute, int startDistance, int speed, int duration) {
		hours = startHour;
		minutes = startMinute;
		distance = startDistance;
		
		for (int i = 0; i < duration/10; i++) {
			//System.out.println(minutes);
			
			if (minutes < 50) {
				minutes += 10;
				
			}
			else if (minutes >= 50) {
				minutes -= 50;
				if (hours < 23)
					hours +=1 ;
				else
					hours -= 23;
			}
			
			distance += speed/6;
			//String string = String.format("[%d:%d] Starman is %dkm away from Earth", hours, minutes, distance);
			//System.out.println(string);
			System.out.println("["+hours+":"+minutes+"] Starman is "+distance+"km away from Earth");
		}
	}
}
