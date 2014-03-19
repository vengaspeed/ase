package courseworkFSV.model;

import courseworkFSV.controller.RestaurantController;



public class ToTables implements Runnable {

	private Kitchen kitchen;
	private Tables tables;
	//a reference to controller
	private RestaurantController controller;
		
	public ToTables(final Kitchen kitchen, final Tables tables, final RestaurantController controller) {
		this.kitchen = kitchen;
		this.tables = tables;
		this.controller = controller;
	}

	public void run() {

		while (!kitchen.getFinished()) {
			if (!kitchen.isEmpty()) {
				Order currentOrder = kitchen.get(0);

				int sec = 1 + (int) (Math.random() * 5);
				try {
					Thread.sleep(sec * 1000);
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
				}
				controller.addOrderToTable(currentOrder.getTableId(), currentOrder);
				kitchen.remove(0);
			}
		}
	}
}
