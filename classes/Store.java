package classes;

public class Store {
    // Integer ID and double sales private values
    /**
     * @param id    the integer id of the store
     * @param sales the double value of sales over the past month
     * @param alias
     */
    private int ID;
    private double sales;
    // constructor with integer ID parameter and a sales double parameter
    /**
     * 
     * @param ID
     * @param sales
     * @param alias
     */
    public Store(int ID, double sales) {
        this.ID = ID;
        this.sales = sales;
    }
    /**
     * get the sales double of the store
     * @return
     */
    public double getSales() {
        return sales;
    }
    /**
     * Set the sales double of the store
     * @param sales
     */
    public void setSales(double sales) {
        this.sales = sales;
    }

    /**
     * the integer ID of the store
     * @return  
     */
    public int getID() {
        return ID;
    }

    //toString override the default implementation that outputs "Store " + ID + ": " + sales 
        /**
     * 
     * @return
     */
    @Override
    public String toString() {
        return String.format("Store "+ ID + " sold $%,.2f.", getSales());
    }
}
