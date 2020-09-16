/** This exception should only occur when the String
 *  does not contain any uppercase letters.
 *  
 *  Example: hello, world, random
 *  should all fail because they don't
 *  contain any uppercase letters
 * 
 * @author Michael Amaya
 *
 */
public class NoUpperAlphaException extends PasswordException {
	
	/** Constructor that shows a default message when exception is thrown */
	public NoUpperAlphaException() {
		this("The password must contain at least one uppercase alphabetic character");
	}
	
	/** Constructor that shows a custom message from
	 *  the parameter provided
	 * 
	 *  @param msg The custom message to show when exception is thrown
	 */
	public NoUpperAlphaException(String msg) {
		super(msg);
	}
}
