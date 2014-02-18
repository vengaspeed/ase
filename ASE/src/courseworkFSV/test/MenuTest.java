package courseworkFSV.test;

import static org.junit.Assert.*;

import org.junit.Test;

import courseworkFSV.Category;
import courseworkFSV.Menu;
import courseworkFSV.MenuItem;
import courseworkFSV.exception.ImpossiblePriceException;
import courseworkFSV.exception.NoMatchingDishException;

public class MenuTest {

	/*
	 * tests that an exception is thrown when a dish doesn't exist in the menu 
	 */
	@Test
	public  void invalidDishName() {
		Menu menu = new Menu();
		try {
			menu.add(new MenuItem("A",3.00,Category.STARTER));
			menu.add(new MenuItem("B",3.00,Category.MAIN));
			menu.add(new MenuItem("Z",3.00,Category.STARTER));
			
			menu.foundByName("NAME");
			fail("Negatif price supplied - should throw exception");
		}
		catch (ImpossiblePriceException e) {
			// Do nothing
		}
		catch (NoMatchingDishException nmde) {
			assertTrue(nmde.getMessage().contains("NAME"));
		}
	}

}
