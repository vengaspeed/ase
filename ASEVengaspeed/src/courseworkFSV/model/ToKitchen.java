package courseworkFSV.model;

import java.util.List;

public class ToKitchen implements Runnable {
	/** Kitchen to send the orders. */
	private Kitchen kitchen;
	/** Orders to send. */
	private List<Order> orders;

	/**
	 * Set up attributes
	 * 
	 * @param kitchen
	 *            Kitchen to send the orders.
	 * @param orders
	 *            Orders to send.
	 */
	public ToKitchen(final Kitchen kitchen, final List<Order> orders) {
		this.kitchen = kitchen;
		this.orders = orders;
	}

	/**
	 * Sends orders to kitchen.
	 */
	@Override
	public void run() {
		for (Order o : orders) {
			try {
				Thread.sleep(o.getItem().getCookingTime());
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
			
			synchronized (kitchen) {
				kitchen.add(o);
				kitchen.notifyAll();
			}	
			
		}
		kitchen.setFinished();
	}
}
