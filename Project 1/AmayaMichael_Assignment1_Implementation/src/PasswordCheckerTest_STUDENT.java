
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author Michael Amaya
 *
 */
public class PasswordCheckerTest_STUDENT {

	String[] shortPasswords = {"ol3$E", "1r*$R", "3edf", "t$e", "j#ec"};
	String[] noUpperCasePasswords = {"rAndompassword3#", "whateversomethiNg4%", "okokokdrenF1$", "sdfWeb$e3@", "iamNotverycreative34!"};
	String[] noLowerCasePasswords = {"STILLNOTCREATIVE33!", "CANNOTTHINKOFANYMORE233!", "42#LETSCHANGEITUP", "WOWOWOWR#4", "MICHAELAMAYA34#"};
	String[] noSpecialSymbolPasswords = {"WaitA1mostForgotAboutThis", "ThisTestWasnt1ncludedByDefault", "gottaMAkeAMethodNow3", "EeEeEeEe32", "Short1Pass"};
	
	String[] validButWeakPasswords = {"tHis3@", "A!bert2", "Ye!!0w", "P@55w0rd", "P4b!03"};
	String[] validAndNotWeakPasswords = {"Str0ngP@ssw0rd", "K0d4kB!ack25", "1HeartJesusChri$t", "!AmTerrib1ePassw0rdMaker", "Buffa10!3e5"};
	
	String[] invalidSequencePasswords = {"SomethingAboutEpsteeen4#", "Calculus222IsDifficult?", "Yes,,,IndeedIt1s", "ThereAre[[[50]]]PasswordsHere", "HopeThisFailsThisTesttt43@"};
	String[] noDigitPasswords = {"ThersNoDigitsHEre#", "PleaseFailMe$", "ThisBetterPassIGotThingsToDo#", "$AReYouHavingFunReadingThese?", "IsThisHundredPercentWork?"};
	
	String[] validPasswords = {"N0tTh1$Again43", "Sh0rt!", "K4tchup%", "{V4lid}", "_-_a_B-3_-", "1@3%$2dRE5J24&$'"};
	
	// String[] randomInvalidPasswords = {"HeeeloWorld4%2", "failingpassword4", "EverybodyNoticeMe5", "SuperLongPasswordIAmWritingWhileListeningToMusic34", "ImInLondonGotMyBeatsFromLondon2", "D4ys$"};
	
	ArrayList<String> randomPasswords = new ArrayList<>(Arrays.asList("SomethingAboutEpsteeen4", "_-_a_B-3_-", "P4b!02", "RAND", "P@55w0rd", "randomPasswordBad1", "ImInLondonGotMyBeatsFromLondon2", "D4ys$", "3verybodyNoticeMe5@", "tHis3@"));
	// Invalid Passwords: SomethingAboutEpsteeen4, RAND, randomPasswordBad1, ImInLondonGotMyBeatsFromLondon2, D4ys$
	// Valid Passwords: _-_a_B-3_-, P4b!02, P@55w0rd, 3verybodyNoticeMe5@, tHis3@
	
	/**
	 * Test if the password is less than 8 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		try {
			for(String password : shortPasswords)
				PasswordCheckerUtility.isValidPassword(password);
		} catch(LengthException le) {
			assertTrue("LengthException was thrown", true);
		} catch(Exception e) {
			assertTrue("An Exception that isn't a LengthException was thrown: " + e, false);
		}
	}

	/**
	 * Test if the password contains at least one symbol
	 * This test should throw a NoSpecialSymbolException for each password
	 * This was almost forgotten..
	 */
	@Test
	public void testIsValidPasswordNoSymbol()
	{
		try {
			for (String password : noSpecialSymbolPasswords)
				PasswordCheckerUtility.isValidPassword(password);
		} catch (NoSpecialSymbolException nspe) {
			assertTrue("NoSpecialSymbolException was thrown!", true);
		} catch (Exception e) {
			assertTrue("Some other exception was thrown: " + e, false);
		}
	}
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha()
	{
		try {
			for(String password : noUpperCasePasswords)
				PasswordCheckerUtility.isValidPassword(password);
		} catch (NoUpperAlphaException nuae) {
			assertTrue("NoUpperAlphaException was thrown", true);
		} catch (Exception e) {
			assertTrue("An Exception that isn't a NoUpperAlphaException was thrown: " + e, false);
		}
	}
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha()
	{
		try {
			for(String password : noLowerCasePasswords)
				PasswordCheckerUtility.isValidPassword(password);
		} catch (NoLowerAlphaException nlae) {
			assertTrue("NoUpperAlphaException was thrown", true);
		} catch (Exception e) {
			assertTrue("An Exception that isn't a NoLowerAlphaException was thrown: " + e, false);
		}
	}
	/**
	 * Test if the password is weak, 
	 * Should test a bunch of weak and nonweak passwords
	 */
	@Test
	public void testIsWeakPassword()
	{
		try {
			for (String password : validButWeakPasswords) {
				assertTrue(PasswordCheckerUtility.isValidPassword(password));
				assertTrue(PasswordCheckerUtility.isWeakPassword(password));
			}
			
			for (String password : validAndNotWeakPasswords) {
				assertTrue(PasswordCheckerUtility.isValidPassword(password));
				assertFalse(PasswordCheckerUtility.isWeakPassword(password));
			}
		} catch (PasswordException pe) {
			assertTrue("Some subclass of PasswordException was thrown: " + pe, false);
		} catch (Exception e) {
			assertTrue("Some Other exception was thrown: " + e, false);
		}
	}
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()
	{
		try {
			for(String password : invalidSequencePasswords)
				PasswordCheckerUtility.isValidPassword(password);
		} catch (InvalidSequenceException ise) {
			assertTrue("InvalidSequenceException was thrown", true);
		} catch (Exception e) {
			assertTrue("An Exception that isn't a InvalidSequenceException was thrown: " + e, false);
		}
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit()
	{
		try {
			for(String password : noDigitPasswords)
				PasswordCheckerUtility.isValidPassword(password);
		} catch (NoDigitException nde) {
			assertTrue("NoDigitException was thrown", true);
		} catch (Exception e) {
			assertTrue("An Exception that isn't a NoDigitException was thrown: " + e, false);
		}
	}
	
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()
	{
		boolean testsPassed = true;
		
		try {
			for (String password : validPasswords)
				PasswordCheckerUtility.isValidPassword(password);
		} catch (PasswordException pe) {
			assertTrue("Something of Subclass PasswordException was thrown: " + pe, false);
			testsPassed = false;
		} catch (Exception e) {
			assertTrue("Something that isn't a Subclass of PasswordException was thrown: " + e, false);
			testsPassed = false;
		}
		
		assertTrue(testsPassed);
	}
	
	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	@Test
	public void testInvalidPasswords() {
		// Invalid Passwords: SomethingAboutEpsteeen4, RAND, randomPasswordBad1, ImInLondonGotMyBeatsFromLondon2, D4ys$
		// Valid Passwords: _-_a_B-3_-, P4b!02, P@55w0rd, 3verybodyNoticeMe5@, tHis3@
		
		boolean passedPassword1, passedPassword2, passedPassword3, passedPassword4, passedPassword5, passedPassword6, passedPassword7, passedPassword8, passedPassword9 ,passedPassword10;
		passedPassword1 =  passedPassword2 = passedPassword3 = passedPassword4 =  passedPassword5 = passedPassword6 = passedPassword7 = passedPassword8 = passedPassword9 = passedPassword10 = false;
		
		ArrayList<String> testInvalidPasswords = PasswordCheckerUtility.getInvalidPasswords(randomPasswords);
		
		for(String invalidPasswordAndError : testInvalidPasswords) {
			if (invalidPasswordAndError.contains("SomethingAboutEpsteeen4"))
				passedPassword1 = true;
			
			if (invalidPasswordAndError.contains("RAND"))
				passedPassword2 = true;
			
			if (invalidPasswordAndError.contains("randomPasswordBad1"))
				passedPassword3 = true;
			
			if (invalidPasswordAndError.contains("ImInLondonGotMyBeatsFromLondon2"))
				passedPassword4 = true;
			
			if (invalidPasswordAndError.contains("D4ys$"))
				passedPassword5 = true;
			
			if (!invalidPasswordAndError.contains("_-_a_B-3_-"))
				passedPassword6 = true;
			
			if (!invalidPasswordAndError.contains("P4b!02"))
				passedPassword7 = true;
			
			if (!invalidPasswordAndError.contains("P@55w0rd"))
				passedPassword8 = true;
			
			if (!invalidPasswordAndError.contains("3verybodyNoticeMe5@"))
				passedPassword9 = true;
			
			if (!invalidPasswordAndError.contains("tHis3@"))
				passedPassword10 = true;
		}
		
		assertTrue((passedPassword1 && passedPassword2 && passedPassword3 && passedPassword4 && passedPassword5 && passedPassword6 && passedPassword7 && passedPassword8 && passedPassword9 && passedPassword10));
	}
	
}
