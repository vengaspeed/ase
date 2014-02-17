package courseworkFSV.exception;

public class NoMatchingDishException extends Exception {
	public NoMatchingDishException(String name) {
		super("No dish " + name.toUpperCase() + " found.");
	}
}
