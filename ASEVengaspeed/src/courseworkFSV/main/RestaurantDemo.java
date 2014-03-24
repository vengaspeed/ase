package courseworkFSV.main;

import courseworkFSV.model.Restaurant;
import courseworkFSV.view.RestaurantGUI;

import courseworkFSV.controller.RestaurantController;

public class RestaurantDemo {

	public static void main(String[] args) {
		Restaurant restaurant = Restaurant
				.getInstance("menu.txt", "orders.txt");

		// test threads
		RestaurantGUI restaurantGUI = new RestaurantGUI(restaurant);

		RestaurantController controller = new RestaurantController(
				restaurantGUI, restaurant);

		controller.start();

		// System.out.println("Finish.");

		// RestaurantInterface c = new RestaurantInterface(restaurant);
		// c.run();
	}
}
