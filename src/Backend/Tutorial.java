package Backend;

public class Tutorial {
	private String Title;
	private String Message;
	private String PS;
	
	public Tutorial(String title, String mess, String ps) {
		Title = title;
		Message = mess;
		PS = ps;
	}
	
	public String getTitle() {
		return Title;
	}
	
	public String getMessage() {
		return Message;
	}
	
	public String getPS() {
		return PS;
	}
	
	public String toString() {
		return "";
	}
}
