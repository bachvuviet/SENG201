package SpaceVessel;

import javax.swing.ImageIcon;

public interface Stock {
    public static String CATERGORY="";
    
    public String getName();
    public int getAmount();
    public ImageIcon getImage();

    public String getStockStatus();
    public String toString();

    public int use(int amount);//decrease amount, return int
    public void setAmount(int amount);//increase amount
}
