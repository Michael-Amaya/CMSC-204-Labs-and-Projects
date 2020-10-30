import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CourseDBManager_STUDENT_Test {
	
	CourseDBManager testManager1;
	CourseDBManager testManager2;
	File input1;
	File input2;
	
	String courseID1;
	int courseCRN1;
	int credits1;
	String roomNumber1;
	String instructor1;
	
	String courseID2;
	int courseCRN2;
	int credits2;
	String roomNumber2;
	String instructor2;
	
	String courseID3;
	int courseCRN3;
	int credits3;
	String roomNumber3;
	String instructor3;
	
	String courseID4;
	int courseCRN4;
	int credits4;
	String roomNumber4;
	String instructor4;
	
	String courseID5;
	int courseCRN5;
	int credits5;
	String roomNumber5;
	String instructor5;

	@Before
	public void setUp() throws Exception {
		testManager1 = new CourseDBManager();
		testManager2 = new CourseDBManager();
		input1 = new File("test1.txt");
		input2 = new File("test2.txt");
		
		courseID1 = "MATH182";
		courseCRN1 = 1234;
		credits1 = 4;
		roomNumber1 = "SC100";
		instructor1 = "Somebody";
		
		courseID2 = "CMSC204";
		courseCRN2 = 5678;
		credits2 = 4;
		roomNumber2 = "SC400";
		instructor2 = "Once";
		
		courseID3 = "HLTH130";
		courseCRN3 = 2468;
		credits3 = 3;
		roomNumber3 = "HU100";
		instructor3 = "Told";
		
		courseID4 = "ENGL101";
		courseCRN4 = 1357;
		credits4 = 3;
		roomNumber4 = "HU200";
		instructor4 = "Me";
		
		courseID5 = "ART100";
		courseCRN5 = 9876;
		credits5 = 3;
		roomNumber5 = "HU300";
		instructor5 = "The";
	}

	@After
	public void tearDown() throws Exception {
		testManager1 = testManager2 = null;
		input1.delete();
		input2.delete();
		input1 = input2 = null;
	}
	
	@Test
	public void testAdd() {
		testManager1.add(courseID1, courseCRN1, credits1, roomNumber1, instructor1);
		testManager1.add(courseID3, courseCRN3, credits3, roomNumber3, instructor3);
		testManager1.add(courseID5, courseCRN5, credits5, roomNumber5, instructor5);
		
		assertTrue("\nCourse:ART100 CRN:9876 Credits:3 Instructor:The Room:HU300".equals(testManager1.showAll().get(0)));
		assertTrue("\nCourse:HLTH130 CRN:2468 Credits:3 Instructor:Told Room:HU100".equals(testManager1.showAll().get(1)));
		assertTrue("\nCourse:MATH182 CRN:1234 Credits:4 Instructor:Somebody Room:SC100".equals(testManager1.showAll().get(2)));
		
		testManager2.add(courseID2, courseCRN2, credits2, roomNumber2, instructor2);
		testManager2.add(courseID3, courseCRN3, credits3, roomNumber3, instructor3);
		testManager2.add(courseID4, courseCRN4, credits4, roomNumber4, instructor4);
		
		assertTrue("\nCourse:CMSC204 CRN:5678 Credits:4 Instructor:Once Room:SC400".equals(testManager2.showAll().get(0)));
		assertTrue("\nCourse:HLTH130 CRN:2468 Credits:3 Instructor:Told Room:HU100".equals(testManager2.showAll().get(1)));
		assertTrue("\nCourse:ENGL101 CRN:1357 Credits:3 Instructor:Me Room:HU200".equals(testManager2.showAll().get(2)));
		
		try {
			testManager1.add(courseID2, courseCRN2, credits2, roomNumber2, instructor2);
			testManager2.add(courseID5, courseCRN5, credits5, roomNumber5, instructor5);
			assertTrue("No Exception occured when adding", true);
		} catch (Exception e) {
			assertTrue("Exception thrown when it shouldn't have!", false);
		}
	}

	@Test
	public void testGet() {
		testManager1.add(courseID1, courseCRN2, credits3, roomNumber4, instructor5);
		testManager1.add(courseID4, courseCRN3, credits2, roomNumber1, instructor4);
		testManager1.add(courseID5, courseCRN5, credits5, roomNumber2, instructor3);
		
		assertTrue(courseID1.equals(testManager1.get(courseCRN2).getCourseID()));
		assertEquals(credits3, testManager1.get(courseCRN2).getNumberOfCredits());
		assertTrue(roomNumber4.equals(testManager1.get(courseCRN2).getRoomNumber()));
		assertTrue(instructor5.equals(testManager1.get(courseCRN2).getInstructor()));
		
		assertTrue(courseID4.equals(testManager1.get(courseCRN3).getCourseID()));
		assertEquals(credits2, testManager1.get(courseCRN3).getNumberOfCredits());
		assertTrue(roomNumber1.equals(testManager1.get(courseCRN3).getRoomNumber()));
		assertTrue(instructor4.equals(testManager1.get(courseCRN3).getInstructor()));
		
		assertTrue(courseID5.equals(testManager1.get(courseCRN5).getCourseID()));
		assertEquals(credits5, testManager1.get(courseCRN5).getNumberOfCredits());
		assertTrue(roomNumber2.equals(testManager1.get(courseCRN5).getRoomNumber()));
		assertTrue(instructor3.equals(testManager1.get(courseCRN5).getInstructor()));
		
		
		testManager2.add(courseID2, courseCRN1, credits4, roomNumber5, instructor2);
		testManager2.add(courseID1, courseCRN4, credits3, roomNumber2, instructor3);
		testManager2.add(courseID3, courseCRN2, credits1, roomNumber1, instructor1);
		
		assertTrue(courseID2.equals(testManager2.get(courseCRN1).getCourseID()));
		assertEquals(credits4, testManager2.get(courseCRN1).getNumberOfCredits());
		assertTrue(roomNumber5.equals(testManager2.get(courseCRN1).getRoomNumber()));
		assertTrue(instructor2.equals(testManager2.get(courseCRN1).getInstructor()));
		
		assertTrue(courseID1.equals(testManager2.get(courseCRN4).getCourseID()));
		assertEquals(credits3, testManager2.get(courseCRN4).getNumberOfCredits());
		assertTrue(roomNumber2.equals(testManager2.get(courseCRN4).getRoomNumber()));
		assertTrue(instructor3.equals(testManager2.get(courseCRN4).getInstructor()));
		
		assertTrue(courseID3.equals(testManager2.get(courseCRN2).getCourseID()));
		assertEquals(credits1, testManager2.get(courseCRN2).getNumberOfCredits());
		assertTrue(roomNumber1.equals(testManager2.get(courseCRN2).getRoomNumber()));
		assertTrue(instructor1.equals(testManager2.get(courseCRN2).getInstructor()));
		
		assertTrue("".equals(testManager1.get(courseCRN1).getCourseID()));
		assertEquals(0, testManager1.get(courseCRN1).getNumberOfCredits());
		assertTrue("".equals(testManager1.get(courseCRN1).getRoomNumber()));
		assertTrue("".equals(testManager1.get(courseCRN1).getInstructor()));
		
		assertTrue("".equals(testManager2.get(courseCRN3).getCourseID()));
		assertEquals(0, testManager2.get(courseCRN3).getNumberOfCredits());
		assertTrue("".equals(testManager2.get(courseCRN3).getRoomNumber()));
		assertTrue("".equals(testManager2.get(courseCRN3).getInstructor()));
		
		try {
			testManager2.get(courseCRN5).getCourseID();
			testManager1.get(courseCRN4).getCourseID();
			assertTrue("No exception was thrown!", true);
		} catch (Exception e) {
			assertTrue("Should not have thrown an exception!", false);
		}
	}
	
	@Test
	public void testReadFile() {
		try {
			PrintWriter inFile1 = new PrintWriter(input1);
			PrintWriter inFile2 = new PrintWriter(input2);
			
			inFile1.println(courseID1 + " " + courseCRN1 + " " + credits2 + " " + roomNumber4 + " " + instructor2);
			inFile1.println(courseID3 + " " + courseCRN2 + " " + credits4 + " " + roomNumber2 + " " + instructor3);
			inFile1.println(courseID5 + " " + courseCRN3 + " " + credits3 + " " + roomNumber3 + " " + instructor4);

			inFile1.close();
			
			inFile2.println(courseID2 + " " + courseCRN3 + " " + credits3 + " " + roomNumber3 + " " + instructor1);
			inFile2.println(courseID4 + " " + courseCRN4 + " " + credits5 + " " + roomNumber1 + " " + instructor5);
			inFile2.println(courseID1 + " " + courseCRN5 + " " + credits1 + " " + roomNumber2 + " " + instructor2);
			
			inFile2.close();
			
			testManager1.readFile(input1);
			testManager2.readFile(input2);
			
			assertTrue("\nCourse:HLTH130 CRN:5678 Credits:3 Instructor:Told Room:SC400".equals(testManager1.showAll().get(0)));
			assertTrue("\nCourse:ART100 CRN:2468 Credits:3 Instructor:Me Room:HU100".equals(testManager1.showAll().get(1)));
			assertTrue("\nCourse:MATH182 CRN:1234 Credits:4 Instructor:Once Room:HU200".equals(testManager1.showAll().get(2)));

			assertTrue("\nCourse:MATH182 CRN:9876 Credits:4 Instructor:Once Room:SC400".equals(testManager2.showAll().get(0)));
			assertTrue("\nCourse:CMSC204 CRN:2468 Credits:3 Instructor:Somebody Room:HU100".equals(testManager2.showAll().get(1)));
			assertTrue("\nCourse:ENGL101 CRN:1357 Credits:3 Instructor:The Room:SC100".equals(testManager2.showAll().get(2)));
		} catch (FileNotFoundException e) {
			assertTrue("Should not have thrown an exception", false);
		}
		
	}
	
	@Test
	public void showAll() {
		testManager1.add(courseID4, courseCRN1, credits5, roomNumber3, instructor1);
		testManager1.add(courseID2, courseCRN3, credits3, roomNumber5, instructor3);
		testManager1.add(courseID1, courseCRN2, credits1, roomNumber1, instructor2);
		
		assertTrue("\nCourse:MATH182 CRN:5678 Credits:4 Instructor:Once Room:SC100".equals(testManager1.showAll().get(0)));
		assertTrue("\nCourse:CMSC204 CRN:2468 Credits:3 Instructor:Told Room:HU300".equals(testManager1.showAll().get(1)));
		assertTrue("\nCourse:ENGL101 CRN:1234 Credits:3 Instructor:Somebody Room:HU100".equals(testManager1.showAll().get(2)));
		
		testManager2.add(courseID5, courseCRN2, credits4, roomNumber2, instructor2);
		testManager2.add(courseID1, courseCRN4, credits2, roomNumber1, instructor4);
		testManager2.add(courseID3, courseCRN1, credits3, roomNumber4, instructor5);
		
		assertTrue("\nCourse:ART100 CRN:5678 Credits:3 Instructor:Once Room:SC400".equals(testManager2.showAll().get(0)));
		assertTrue("\nCourse:HLTH130 CRN:1234 Credits:3 Instructor:The Room:HU200".equals(testManager2.showAll().get(1)));
		assertTrue("\nCourse:MATH182 CRN:1357 Credits:4 Instructor:Me Room:SC100".equals(testManager2.showAll().get(2)));
	}
}
