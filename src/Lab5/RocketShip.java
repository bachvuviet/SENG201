package Lab5;

public class RocketShip {

    int MAX_FUEL_LEVEL = 100;

    int fuelLevel;
    int currentHeight;

    public RocketShip(int fuelLevel) {
        this.fuelLevel = fuelLevel;
        this.currentHeight = 0;
    }

    public int getFuelLevel() {
        return fuelLevel;
    }

    public int getCurrentHeight() {
        return currentHeight;
    }

    public void fuelUp(int fuelAmount) {
		fuelLevel += fuelAmount;
    }

    public void takeOff(){
    	if (fuelLevel >= 20) {
            fuelLevel -= 20;
            currentHeight += 20;
    	}
    	else
    		throw new IllegalStateException("Not enough fuel!");
    }

    public void goHigher() {
        fuelLevel -= 10;
        currentHeight += 50;
    }

    public void goLower() {
        currentHeight -= 50;
    }

    public void land() {
    	if (currentHeight > 20)
    		throw new LandingException("Too high to land!");
    	else
    		currentHeight = 0;
    }
    
    public void testFlight() {
    	try {
        	takeOff();
        	goHigher();
        	goLower();
        	land();    		
    	}
    	catch (IllegalStateException ex) {
    		System.out.println(ex.getMessage());
    	}
    	finally {
    		System.out.println("Cleaning up launch pad");
    	}
    }
}

class LandingException extends IllegalStateException {
    public LandingException() {}
    public LandingException(String message) {
        super(message);
    }
}

class InsufficientFuelException extends IllegalStateException {
    public InsufficientFuelException() {}
    public InsufficientFuelException(String message) {
        super(message);
    }
}
