import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Utility with many static methods to check for 
 *  password validity or password weakness. Can also
 *  Check for a list of passwords and return the invalid 
 *  passwords.
 *  
 *  @author Michael Amaya
 *  
 */
public class PasswordCheckerUtility {
	
	/** This method checks for password validity with the following Tests:
	 *  Checks the length, Checks for digits, Checks for an uppercase letter,
	 *  Checks for a lowercase letter, checks for a special symbol,
	 *  Checks for an invalid sequence, in that order.
	 *  If any of the tests fail, an approraite exception is thrown
	 * 
	 *  @param passwordString	The password to check for validity
	 *  @return 				Returns true if the password is valid, never returns false, exceptions are thrown instead
	 *  @throws LengthException				Throws this when the password's length is less than 6 characters
	 *  @throws NoDigitException			Throws this if the password does not contain a digit
	 *  @throws NoUpperAlphaException		Throws this if the password does not contain any uppercase letters
	 *  @throws NoLowerAlphaException		Throws this if the password does not contain any lowercase letters
	 *  @throws NoSpecialSymbolException	Throws this if the password does not contain any special symbols
	 *  @throws InvalidSequenceException	Throws this if the password contains more than two of the same character in a sequence
	 *  
	 */
	public static boolean isValidPassword(String passwordString) throws PasswordException {
		char[] passwordArray = passwordString.toCharArray();
		boolean containsNumber = false;
		boolean containsUpperAlpha = false;
		boolean containsLowerAlpha = false;
		int foundTheSame = 1;
		char lastCharacter = 0;
		
		// Check length of array / String
		if (passwordArray.length < 6)
			throw new LengthException();
		
		// Check if password contains a number
		for (char character : passwordArray)
			switch(character) {
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
				case '8':
				case '9':
				case '0':
					containsNumber = true;
					break;
			}
		
		// If the password contained a number, then this will fail and
		// not throw an exception
		if (!containsNumber)
			throw new NoDigitException();
		
		// Check if password contains an uppercase letter
		for (char character : passwordArray)
			switch (character) {
				case 'A':
				case 'B':
				case 'C':
				case 'D':
				case 'E':
				case 'F':
				case 'G':
				case 'H':
				case 'I':
				case 'J':
				case 'K':
				case 'L':
				case 'M':
				case 'N':
				case 'O':
				case 'P':
				case 'Q':
				case 'R':
				case 'S':
				case 'T':
				case 'U':
				case 'V':
				case 'W':
				case 'X':
				case 'Y':
				case 'Z':
					containsUpperAlpha = true;
					break;
			}
		
		// If the password contains an uppercase letter, this test
		// will fail and not throw an exception
		if (!containsUpperAlpha)
			throw new NoUpperAlphaException();
		
		// Check if password contains a lowercase letter
		for (char character : passwordArray)
			switch (character) {
				case 'a':
				case 'b':
				case 'c':
				case 'd':
				case 'e':
				case 'f':
				case 'g':
				case 'h':
				case 'i':
				case 'j':
				case 'k':
				case 'l':
				case 'm':
				case 'n':
				case 'o':
				case 'p':
				case 'q':
				case 'r':
				case 's':
				case 't':
				case 'u':
				case 'v':
				case 'w':
				case 'x':
				case 'y':
				case 'z':
					containsLowerAlpha = true;
					break;
			}
		
		// If the password contains a lowercase letter,
		// this test will fail and not throw an exception
		if (!containsLowerAlpha)
			throw new NoLowerAlphaException();
		
		// Check for special characters
		// Here is the actual regex pattern:
		// ^.*[\!\@\#\$\%\^\&\*\(\)\{\}\;\:\'\"\,\<\.\>\/\?\\\`\~\-\_\=\+\|].*$
		// So the list of allowed symbols are !@#$%^&*()-=_+`~[]\{};:'",<.>/?|
		Pattern regExPattern = Pattern.compile("^.*[\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\{\\}\\;\\:\\'\\\"\\,\\<\\.\\>\\/\\?\\\\\\`\\~\\-\\_\\=\\+\\|].*$");
		Matcher regExMatcher = regExPattern.matcher(passwordString);
		
		if (!regExMatcher.matches()) {
			throw new NoSpecialSymbolException();
		}
		
		// Check for an invalid sequence
		for (char character : passwordArray) {
			// Check if the current character is the same as the last character
			// If it is, add 1 to the foundTheSame
			// If it's not, set to 1 as that character would be the first
			// In a sequence
			if (character == lastCharacter)
				foundTheSame++;
			else
				foundTheSame = 1;
			
			// Set the last character to the current character
			lastCharacter = character;
			
			// Check if you found three or more characters in a sequence
			// And if you did, throw an exception. This should realistically never
			// reach over three, but we'll check if anyways just in case.
			if (foundTheSame >= 3)
				throw new InvalidSequenceException();
		}
		
		// Return true as all tests need to pass to get to this point.
		// If a test fails, then an exception will be thrown and the method
		// will never get to this point
		return true;
	}
	
	/** Checks if a password is weak by checking its length
	 *  
	 *  @param passwordString The password to check
	 *  @return True or false depending if the password is weak or not
	 *  
	 */
	public static boolean isWeakPassword(String passwordString) {
		// Check if the length of the password is less than 10.
		// If it is, return true, if not return false
		if(passwordString.length() < 10)
			return true;
		
		return false;
	}
	
	/** Checks a String ArrayList of passwords and says which passwords
	 *  are weak
	 *  
	 *  @param passwords The String ArrayList to check for
	 *  @return A String ArrayList of invalid passwords
	 *  
	 */
	public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords){
		// Create an arraylist for invalid passwords
		ArrayList<String> invalidPasswords = new ArrayList<>();
		
		// Loop through all passwords
		for(String password : passwords)
		
			// Try to check for invalid passwords, if 
			// An exception is thrown, then it is considered invalid
			try {
				isValidPassword(password);
			} catch (PasswordException pe) {
				invalidPasswords.add(password + " " + pe.getMessage());
			}
		
		// Need to add something or else the program will error out if the file is empty.
		invalidPasswords.add("");
		
		// Return the list of invalid passwords
		return invalidPasswords;
	}
}
