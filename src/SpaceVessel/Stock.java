package SpaceVessel;

public interface Stock {
    public static String CATERGORY="";
    
    public String getName();
    public int getAmount();

    public String getStockStatus();
    public String toString();

    public int use(int amount);//decrease amount, return int
    public void setAmount(int amount);//increase amount
}
