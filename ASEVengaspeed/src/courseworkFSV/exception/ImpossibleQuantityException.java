package courseworkFSV.exception;

public class ImpossibleQuantityException extends Exception {
	public ImpossibleQuantityException(int quantity) {
		super("The quantity can't be negatif : " + quantity + ".");
	}
}
