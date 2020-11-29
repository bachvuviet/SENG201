package GlobalProg;

//import Lab3.*;
//import Lab4.*;
//import Lab5.*;
import Lab7.*;

public class Program {
	public static void main_basic(String[] args) {
		/*Loop basic
		StarmanFixes wheelnut = new StarmanFixes();
		
		wheelnut.setOneTurn(1);
		wheelnut.tightenQuarter();
		wheelnut.tightenHalf();
		wheelnut.tightenFull();
		System.out.println(wheelnut.getTightenAmount());*/
		
		
		/*Override method
		SpaceStation station = new SpaceStation();
		station.dock();
		station.dock("    Red Tesla Roadster");
		station.dock("Red Tesla Roadster", "Space Oddity");
		*/
		
		/*Basic condition, notice .equal() method
		LiftoffWatch launch = new LiftoffWatch();

		launch.setTemp(27.0);
		launch.setWeather("Sunny");
		launch.setWind(53);
		System.out.println(launch.canWeLaunch()); 		 
		*/
		
		/*enum and switch
		MarsHoliday holiday = new MarsHoliday();
		holiday.activityChooser(MarsActivities.MOUNTAINS);
		holiday.activityChooser(MarsActivities.VALLEYS);
		holiday.activityChooser(MarsActivities.ICE_CAPS);
		holiday.activityChooser(MarsActivities.CURIOSITY);
		holiday.activityChooser(MarsActivities.RED_SAND);
		*/
		
		/*Time in a day, String.format() sometimes work
		LocationBeacon beacon = new LocationBeacon();
		beacon.heartBeat(23, 28, 630000, 10000, 90);
		*/
		
		/*while loop
	 	StarmanSummits starman = new StarmanSummits();
		starman.climbMountain(21287.4, 16.4, 4.3);
		*/
		
		/*Override toString()
		GreenAlien gwerp = new GreenAlien();
		gwerp.name = "Gwerp";
		gwerp.eyeCount = 4;
		gwerp.favouriteCandy = "Marshmellows";

		System.out.println(gwerp);
		*/
		
		/*Array list and TreeSet basic
		GreenAlienTransporter transporter = new GreenAlienTransporter("Fun Club");

		GreenAlien kloup = new GreenAlien("Kloup", 9, "Biscuits");
		GreenAlien gwerp = new GreenAlien("Gwerp", 4, "Marshmellows");
		GreenAlien blarg = new GreenAlien("Blarg", 3, "Pop Rocks");
		GreenAlien lesap = new GreenAlien("Lesap", 5, "Chocolate");
		GreenAlien hugso = new GreenAlien("Hugso", 2, "Pop Rocks");

		transporter.addPassenger(kloup);
		transporter.addPassenger(gwerp);
		transporter.addPassenger(blarg);
		transporter.addPassenger(lesap);
		transporter.addPassenger(hugso);

		transporter.printDetails();
		*/
		
		/*//Interface basic
		SpaceStation station = new SpaceStation("Mars");
		System.out.println(station instanceof RemoteControllable);
		station.updateMission("Store food rations for hungry space travelers");
		System.out.println(station.getStatusReport());
		Rover rover = new Rover();
		System.out.println(rover instanceof RemoteControllable);
		rover.setLocation(100.0, 42.0);
		rover.updateMission("Find water");
		System.out.println(rover.getStatusReport());
		
		//object implement an interface has same name of methods,
		 *  so dont need to know the type of object while using InterfaceObj.method()
		 *  can make a list<Interface> to store mix of object implement same interface
		 *  instanceof keyword can check for superclass/interface
		SpaceStation station = new SpaceStation("Mars");
		station.updateMission("Store food rations for hungry space travelers");

		Rover rover = new Rover();
		rover.setLocation(100.0, 42.0);
		rover.updateMission("Find water");

		StarmanOrganises organise = new StarmanOrganises();
		organise.addControllable(station);
		organise.addControllable(rover);
		organise.getAllStatusReports();*/
		
		//Inheritance class (NOT Interface)
		//Earth earth = new Earth();
		//System.out.println(earth instanceof Planet);
		//System.out.println(earth);
		//System.out.println(earth.home());
		/*
		Planet earth = new Earth();
		Planet mercury = new Mercury();
		Planet venus = new Venus();
		Planet saturn = new Saturn();

		SolarSystem solarsystem = new SolarSystem();
		solarsystem.addPlanet(mercury);
		solarsystem.addPlanet(venus);
		solarsystem.addPlanet(earth);
		solarsystem.addPlanet(saturn);

		solarsystem.printAllPlanets();
		System.out.println(solarsystem.getPlanetCount());
		*/
	}
	public static void main(String[] args) {
		/*
		//Modify exception (un-catched)
		RocketShip testRocketShip = new RocketShip(50);
		try {
		    testRocketShip.takeOff();
		    testRocketShip.goHigher();
		    testRocketShip.goHigher();
		    testRocketShip.goHigher();
		    testRocketShip.land();
		} catch (IllegalStateException e) {
		    System.out.println(e.getMessage());
		}
		
		//try catch
		RocketShip testRocketShip1 = new RocketShip(90);
		testRocketShip1.testFlight();

		System.out.println(testRocketShip1.getCurrentHeight());
		System.out.println(testRocketShip1.getFuelLevel());
		*/
	 	
		/*
		RocketShip testRocketShip = new RocketShip(0);
		try {
		    testRocketShip.testFlight();
		    System.out.println(testRocketShip.getCurrentHeight());
		} catch (InsufficientFuelException e) {
		    System.out.println("Oops! You didn't catch the exception");
		}*/
//		String s1 = "seng201";
//		String s2 = "seng202";
//		s2 = s1;
//		String s3 = "seng";
//		s3 += "201";
		
		
		GalaxyWeather weather = new GalaxyWeather();
		Starman spacey = new Starman();
		weather.addObserver(spacey);
		weather.setIsSolarFlare(true);
		
//		GalaxyWeather weather = new GalaxyWeather();
//		SpaceMan spacey = new SpaceMan();
//		weather.addObserver(spacey);
//		weather.setIsSpaceStorm(false);
	}
}
