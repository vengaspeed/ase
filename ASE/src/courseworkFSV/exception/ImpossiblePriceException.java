package courseworkFSV.exception;

public class ImpossiblePriceException extends Exception {
	public ImpossiblePriceException(Double price) {
		super("Impossible price for menu item: " + String.valueOf(price)+".");
	}
}
