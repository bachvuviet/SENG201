package Lab4;

public interface RemoteControllable {
	public static final String DOMAIN = "You can have a static attribute within an interface (final means cannot be override)";
	
    public String getStatusReport();
    public void updateMission(String newMission);
}
