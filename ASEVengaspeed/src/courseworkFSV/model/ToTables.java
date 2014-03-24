package courseworkFSV.model;

import courseworkFSV.controller.RestaurantController;

public class ToTables implements Runnable {

	private Hatch hatch;
	private Tables tables;

	public ToTables(final Hatch hatch, final Tables tables) {
		this.hatch = hatch;
		this.tables = tables;
	}

	public void run() {
		while (!hatch.getFinished() || !hatch.isEmpty()) {
			if (!hatch.isEmpty()) {
				Order currentOrder = hatch.get(0);

				int sec = 1 + (int) (Math.random() * 5);
				try {
					Thread.sleep(sec * 1000);
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
				}
				
				synchronized (hatch) {
					hatch.remove(0);
					hatch.notifyAll();
				}
				
				tables.addAnOrder(currentOrder.getTableId(), currentOrder);
			}
		}	
	}
}
