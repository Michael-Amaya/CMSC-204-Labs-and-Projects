/** This Exception should only occur when a String
 *  Contains no special symbols. The special symbols that
 *  should be accepted are:
 *  !@#$%^&*()-=_+`~[]\{};:'",<.>/?|
 *  
 *  Example: HeLlo, MiKe, WoTlFD
 *  should all fail because they don't
 *  contain any symbols
 *  
 *  @author Michael Amaya
 *  
 */
public class NoSpecialSymbolException extends PasswordException {
	
	/** Constructor that provides a default message when excpetion is thrown */
	public NoSpecialSymbolException() {
		this("The password must contain at least one special character");
	}

	/** Contructor that accepts a custom message
	 *  when the exception is thrown
	 * 
	 * @param msg The custom message to show when exception is thrown
	 */
	public NoSpecialSymbolException(String msg) {
		super(msg);
	}
}
