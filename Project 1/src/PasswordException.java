/** This exception isn't directly called, but is a superclass
 *  of all other exceptions in this package. It's just to be able
 *  to catch this exception instead of catching a bunch of different
 *  exceptions when you want to check for all password exceptions
 * 
 * @author Michael Amaya
 *
 */
public class PasswordException extends Exception {

	/** Default constructor that shoudln't be used, provides a default exception message */
	public PasswordException() {
		this("There was an issue with the password");
	}

	/** Constructor that shows a custom message
	 *  This should be used for all subclasses as a blank
	 *  exception isn't specific at all
	 * 
	 * @param msg The custom message to be shown when exception is thrown
	 */
	public PasswordException(String msg) {
		super(msg);
	}
	
}
