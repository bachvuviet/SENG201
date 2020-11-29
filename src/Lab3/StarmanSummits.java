package Lab3;

public class StarmanSummits {
	public void climbMountain(double height, double dash, double slide) {
		double currHeight = 0;
		int attempt=0;
		while (currHeight < height) {
			currHeight+=dash;
			attempt++;
			if (currHeight < height)
				currHeight-=slide;
		}
		
		System.out.println("Starman needs to dash "+attempt+" times before he reaches the top of the mountain");
	}
}
