package courseworkFSV.test;

import static org.junit.Assert.*;

import org.junit.Test;

import courseworkFSV.Category;
import courseworkFSV.MenuItem;
import courseworkFSV.Order;
import courseworkFSV.exception.ImpossiblePriceException;

public class OrderTest {

	/*
	 * tests if when an order is created, its id is equals to the last id more one.
	 * Test all components diff, 2 same, all same
	 */
	@Test
	public void testID() {
		Order o = new Order(null,2);
		int id1 = o.getOrderId();
		Order o1 = new Order(null,2);
		int id2 = o1.getOrderId();
		assertTrue ((id1 + 1) == id2);
	}

}
