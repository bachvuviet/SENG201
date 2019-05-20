package Backend;

public class Tutorial {
	private String Title;
	private String Message;
	private String PS;
	
	//Constructor
	public Tutorial(String title, String mess, String ps) {
		Title = title;
		Message = mess;
		PS = ps;
	}
	/**
	 * get the title of the tutorial
	 * @return title
	 */
	public String getTitle() {
		return Title;
	}
	/**
	 * get the tutorial message
	 * @return Message
	 */
	public String getMessage() {
		return Message;
	}
	/**
	 * get the tutorial prestige
	 * @return PS
	 */
	public String getPS() {
		return PS;
	}
	/**
	 * @return formated string
	 */
	public String toString() {
		return "";
	}
}
