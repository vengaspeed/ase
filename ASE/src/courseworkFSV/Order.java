package courseworkFSV;

/**
 * @author Noel Stephanie, Vasilis Tsifountidis, Florent Gonin
 * a simple class to contain and manage an order
 */

public class Order {
	
	/** The id of the order. */
	private int orderId;
	
	/** The menu item ordered. */
	private MenuItem item;
	
	/** The quantity ordered. */
	private int quantity;
	
	/** The last id assigned. */
	private static int maxID = 0;
	
    /**
     * Set up the order details. The id is assigned according to the last id assigned and this one is updated.
     * @param item The menu item ordered.
     * @param quantity The quantity ordered.
     */
	public Order(final MenuItem item, final int quantity){
		this.item = item;
		this.quantity = quantity;
		this.orderId = Order.maxID + 1;
		maxID = this.orderId;
	}
	
	/**
     * @return The id of the order.
     */
    public int getOrderId() {
		return orderId;
	}

	/**
     * @return A string containing all details of the order.
     */
	@Override
	public String toString(){
		return item.getName().toUpperCase() + "\t" + 
				String.valueOf(quantity) + " * " + 
				String.valueOf(item.getPrice()) + " = " 
				+ String.valueOf(quantity*item.getPrice());	
	}
	
    /**
     * @return the total cost the order
     */
	public double totalCost(){
		return quantity*item.getPrice();
	}

}
