package Lab3;

public class StarmanFixes {
	private double oneTurn = 0.0;
	private double tightenAmount = 0.0;
	
	public void setOneTurn(double amount) {
		oneTurn = amount;
	}
	public double getTightenAmount() {
		return tightenAmount;
	}
	public void tightenQuarter() {
		tightenAmount += 0.25*oneTurn;
		System.out.println("Starman tightens the nut one quarter of a turn");
	}
	public void tightenHalf() {
		for (int i=0; i<2;i++) {
			tightenQuarter();
		}
		System.out.println("The nut has been tightened half a turn");
	}
	public void tightenFull() {
		for (int i=0; i<2;i++) {
			tightenHalf();
		}
		System.out.println("The nut has been tightened a full turn");
	}
}
