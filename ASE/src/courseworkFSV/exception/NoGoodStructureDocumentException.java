package courseworkFSV.exception;

public class NoGoodStructureDocumentException extends Exception{
	public  NoGoodStructureDocumentException(String file) {
		super("The structure of the document "+file+" is not good.");
	}
}
