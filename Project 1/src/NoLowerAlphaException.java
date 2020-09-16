/** This exception should only occur when
 *  a string does not contain any lowercase
 *  letters.
 *  
 *  Example: HELLO, WORLD, RANDOM
 *  will all fail because they contain
 *  no lowercase letters
 *  
 *  @author Michael Amaya
 *  
 */ 
public class NoLowerAlphaException extends PasswordException {
	
	/** Constructor that gives a default message exception is thrown */
	public NoLowerAlphaException() {
		this("The password must contain at least one lowercase alphabetic character");
	}
	
	/** Constructor with a custom message to
	 *  be shown when the exception is throwed
	 *
	 *  @param msg The custom message to show
	 */
	public NoLowerAlphaException(String msg) {
		super(msg);
	}
}
