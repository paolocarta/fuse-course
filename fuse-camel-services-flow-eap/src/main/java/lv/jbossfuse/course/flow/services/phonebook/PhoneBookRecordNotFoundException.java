package lv.jbossfuse.course.flow.services.phonebook;

public class PhoneBookRecordNotFoundException extends Exception {

	private static final long serialVersionUID = 2519722516532158663L;

	public PhoneBookRecordNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public PhoneBookRecordNotFoundException(String message) {
		super(message);
	}		
}
