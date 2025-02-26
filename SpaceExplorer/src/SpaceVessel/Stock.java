package SpaceVessel;

import java.io.Serializable;

import javax.swing.ImageIcon;

/**
 * Interface stock. A ship has stock in 3 forms: food, medicine and modules.
 * <br> A planet has hidden stock in 3 forms: food, medicine and modules.
 * @author Bach Vu, Linh Luu
 * @version 0.30
 */
public interface Stock extends Serializable {
    public static String CATERGORY="";
    
    /** get name of stock to display at Trade post
     * @return Stock Name*/
    public String getName();
    /** get current amount of a specific stock in the inventory
     * @return amount left of this stock (min = 0)*/
    public int getAmount();
    /** get image to display at trade post
     * @return Image*/
    public ImageIcon getImage();
    /** get price of item to buy
     * @return Price of Stock*/
    public int getPrice();

    /** Quick String output of Stock's status
     * @return Formated String*/
    public String getStockStatus();
    /** String output, to display as tooltip in outpost
     * @return i.e. Chicken x5: 7$ each*/
    public String toStringPrice();
    /** String output, shorter version to be called implicitly in JControls
     * @return i.e. 30x Chicken*/
    public String toString();

    /**
     * decrease amount, return int
     * @param amount Amount of a selected stock to be used
     * @return increment value
     */
    public int use(int amount);
    /**
     * increase amount of a selected stock
     * @param amount new amount of selected stock
     */
    public void setAmount(int amount);
    /**
     * Clone a stock to get 2 exact same instance of Stock
     * @return Image of old Stock
     */
	//public Object clone();
}
