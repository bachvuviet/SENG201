package CustomUIELmt;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * This class contain only static methods to support gaming experience
 * @author Bach Vu
 * @version 1.0
 */
public class StaticObjects {
	
	/**
	 * Quick way to use pop up messbox, only show details, no decision process
	 * @param infoMessage String to display as message
	 * @param titleBar Title of box
	 * @param type Default blank, INFO, Warning, Error
	 */
    public static void MessBox(String infoMessage, String titleBar, String type)
    {
    	switch(type) {
	    	case "Warning":
	    		JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.WARNING_MESSAGE);
	    		break;
	    	case "Error":
	    		JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.ERROR_MESSAGE);
	    		break;
	    	case "Info":
	    		JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
	    		break;
	    	default:
	    		//default title and icon
	            JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.PLAIN_MESSAGE);	    		
    	}
    }
    
    /**
     * Not Implemented
	 * @param infoMessage String to display as message
	 * @param titleBar Title of box
	 * @param type Default blank, INFO, Warning, Error
     */
    public static void OptionBox(String infoMessage, String titleBar, String type)
    {
    	
    }
    
    /**
     * Quick way to resize background imag, avatar crew, stock icon ...
     * @param path path to Image (../sth.png)
     * @param obj Current class using this static method (normally send this)
     * @param x width
     * @param y height
     * @return Resized Image
     */
    public static Image SelfResizeImage(String path, Object obj, int x, int y) {
    	BufferedImage img = null;
    	if (path!="") {
        	try {
    			img = ImageIO.read(obj.getClass().getResource(path));
    		} catch (IOException e1) {
    			e1.printStackTrace();
    		}
    		Image IMG = img.getScaledInstance(x , y, Image.SCALE_SMOOTH);
    		return IMG;
    	} else {
    		return null;
    	}
    }
    
    /**
     * Running message, for game tutorial and incoming message of storyline
     * @param heading Header of message
     * @param message Message
     * @param PS Regards tip of message
     * @param messLabel The label use to display the message
     */
	public static void IncomingMessage(String heading, String message, String PS, JLabel messLabel) {
		//String OutputMessage = String.format("<html><h1> %s:</h1><p>	Greeting Captain,<p>%s<p>%s</html>", heading, message, PS);
		
		Timer timer = new Timer(true);
		TimerTask updateIncomingMessage = new TimerTask() {
			boolean CompleteMessage = false;
			
        	String currHead = "";
        	String tobeHead = heading;
        	String currMess = "";
        	String tobeMess = message;
        	String currPS = "";
        	String tobePS = PS;
	        public void run() {
	        	if (!tobeHead.equals("")) {
	        		currHead += tobeHead.substring(0, 1);
		        	tobeHead = tobeHead.substring(1);
		        	messLabel.setText(String.format("<html><h1> %s</h1><p>	Greeting Captain,<p>%s<p>%s</html>", currHead, "", ""));
	        	}
	        	else if (!tobeMess.equals("")) {
		        	currMess += tobeMess.substring(0, 1);
		        	tobeMess = tobeMess.substring(1);
		        	messLabel.setText(String.format("<html><h1> %s</h1><p>	Greeting Captain,<p>%s<p>%s</html>", currHead, currMess, ""));
	        	}
	        	else if (!tobePS.equals("")) {
		        	currPS += tobePS.substring(0, 1);
		        	tobePS = tobePS.substring(1);
		        	messLabel.setText(String.format("<html><h1> %s</h1><p>	Greeting Captain,<p>%s<p>%s</html>", currHead, currMess, currPS));
	        	}
	        	else
	        		CompleteMessage = true;
	        	
	        	if(CompleteMessage) {
	    			timer.cancel();
	    		}
	        }
	    };
	    
	    timer.scheduleAtFixedRate(updateIncomingMessage, 0, 20);//delay, period	
	}
}