package tp1.p2.control.exceptions;

public class RecordException extends Exception {

	public RecordException(String recordError) {
		super(recordError);
	}

	public RecordException(String recordError, Exception exception) {
		super(recordError, exception);
		}

}
