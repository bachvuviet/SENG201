package SpaceVessel;

public interface Stock {
    public static String CATERGORY="";
    
    public int use(int amount);
    public int getAmount();
    public void setAmount(int amount);
    public String getStockName();
    public String toString();
}
