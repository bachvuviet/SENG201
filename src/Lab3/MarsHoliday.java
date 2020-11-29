package Lab3;

public class MarsHoliday {
	public void activityChooser(MarsActivities activity) {
		switch (activity) {
        case MOUNTAINS:
            System.out.println("The air is no different up here than on the ground");
            break;
        case VALLEYS:
            System.out.println("Did I just see a Martian?");
            break;
        case ICE_CAPS:
            System.out.println("Maybe I can drink this");
            break;
        case CURIOSITY:
            System.out.println("Lets pat Curiosity");
            patCuriosity();
            break;
        case RED_SAND:
            System.out.println("That's hot!");
            break;
        default:
            break;
		}
	}
	
	public void patCuriosity() {
		System.out.println("Pat pat pat\nCuriosity seems happy");
	}
}
