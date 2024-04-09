package edu.sc.bse3211.meetingplanner;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class CalendarTest {
	// Add test methods here.
	// You are not required to write tests for all classes.

	@Test
	public void testAddMeeting_holiday() {
		// Create Janan Luwum holiday
		Calendar calendar = new Calendar();
		// Add to calendar object.
		try {
			Meeting janan = new Meeting(2, 16, "Janan Luwum");
			calendar.addMeeting(janan);
			// Verify that it was added.
			Boolean added = calendar.isBusy(2, 16, 0, 23);
			assertTrue("Janan Luwum Day should be marked as busy on the calendar", added);
		} catch (TimeConflictException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testAddDifferentMeetings() {
		// Inititalization
		Calendar calendar = new Calendar();
		Meeting firstMeeting = new Meeting(2, 16, "first meeting");
		Meeting secondMeeting = new Meeting(3, 12, "second meeting");
		try {
			// Test steps
			calendar.addMeeting(firstMeeting);
			calendar.addMeeting(secondMeeting);
		} catch (TimeConflictException e) { // Test oracle
			assertTrue(false);
		}
	}

	@Test
	public void testAddConflictingMeetings() {
		// Inititalization
		Calendar calendar = new Calendar();
		Meeting firstMeeting = new Meeting(2, 16, "first meeting");
		Meeting secondMeeting = new Meeting(2, 16, "second meeting");
		try {
			// Test steps
			calendar.addMeeting(firstMeeting);
			calendar.addMeeting(secondMeeting);
			fail("Expected TimeConflictException to be thrown");
		} catch (TimeConflictException e) { // Test oracle
			assertTrue(true);
		}
	}

	@Test
	public void testAddMeetingWithWrongMonth() {
		try {
			Calendar calendar = new Calendar();
			Meeting meeting = new Meeting(15, 3, "This is a meeting with wrong input");
			calendar.addMeeting(meeting);
			fail("Added a wrong month");
		} catch (TimeConflictException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testAddMeetingWithWrongDay() {
		try {
			Calendar calendar = new Calendar();
			Meeting meeting = new Meeting(5, 36, "This is a meeting with wrong input");
			calendar.addMeeting(meeting);
			fail("Added a wrong day");
		} catch (TimeConflictException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testAddMeetingWithStartGreaterThanEnd() {
		try {
			Calendar calendar = new Calendar();
			Meeting meeting = new Meeting(1, 3, 14, 10);
			calendar.addMeeting(meeting);
			fail("Added a wrong time frame");
		} catch (TimeConflictException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testAddMeetingWithAttendeesAndRoom() {
		// Initialization
		var calendar = new Calendar();
		var organization = new Organization();
		var attendees = new ArrayList<Person>();

		try {
			// Test steps
			var room = organization.getRoom("LAB2");
			attendees.add(organization.getEmployee("Kukunda Lynn"));
			attendees.add(organization.getEmployee("Namugga Martha"));
			var meeting = new Meeting(4, 6, 12, 13, attendees, room, "This is a meeting");
			calendar.addMeeting(meeting);
			boolean booked = calendar.isBusy(4, 6, 12, 13);
			// Test oracle
			assertTrue("Meeting not added to calendar", booked);
		} catch (Exception e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testCheckTimesValidInput() {
		try {
			Calendar.checkTimes(3, 2, 10, 12);
		} catch (TimeConflictException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testCheckTimesInvalidInput() {
		try {
			Calendar.checkTimes(34, 200, -12, 60);
			fail("Should throw an exception");
		} catch (TimeConflictException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testCheckTimesWithDec() {
		try {
			Calendar.checkTimes(12, 2, 12, 16);
			fail("Should throw an exception");
		} catch (TimeConflictException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testBookHolidayOnNov30() {
		Calendar calendar = new Calendar();
		Meeting meeting = new Meeting(11, 30, "This is a holiday");
		try {
			calendar.addMeeting(meeting);
		} catch (TimeConflictException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testCheckTimeWithZeroMonth() {
		try {
			Calendar.checkTimes(0, 2, 12, 16);
			fail("Should throw an exception");
		} catch (TimeConflictException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testCheckTimesWithSameHour() {
		try {
			Calendar.checkTimes(10, 2, 12, 12);
			fail("Should throw an exception");
		} catch (TimeConflictException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testGetMeetingWithWrongDate() {
		Calendar calendar = new Calendar();
		Meeting meeting = new Meeting(40, 78, 34, 60);
		try {
			calendar.addMeeting(meeting);
		} catch (TimeConflictException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testPrintEmptyAgenda() {
		try {
			var calendar = new Calendar();
			assertEquals("Agenda for " + "3" + ":\n", calendar.printAgenda(3));
		} catch (Exception e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testPrintAgendaWithWrongMonth() {
		var calendar = new Calendar();
		try {
			calendar.printAgenda(21);
			fail("Agenda has wrong month");
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	public void testPrintAgendaWithCorrectMonth() {
		try {
			var calendar = new Calendar();
			var attendees = new ArrayList<Person>();
			var room = new Room("LAB2");
			var meeting = new Meeting(3, 2, 12, 13, attendees, room, "This is a meeting");
			calendar.addMeeting(meeting);
			assertNotEquals("Agenda for " + "3" + ":\n", calendar.printAgenda(3));
		} catch (Exception e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testPrintAgendaForADay() {
		Calendar calendar = new Calendar();
		ArrayList<Person> attendees = new ArrayList<>();
		Room room = new Room("LAB2");
		Meeting meeting = new Meeting(3, 4, 12, 13, attendees, room, "This is a meeting");
		String expected = "Agenda for " + "3" + "/" + "4" + ":\n" + meeting.toString() + "\n";
		try {
			calendar.addMeeting(meeting);
			assertEquals(expected, calendar.printAgenda(3, 4));
		} catch (TimeConflictException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testGetExistingMeeting() {
		Calendar calendar = new Calendar();
		Meeting meeting = new Meeting(3, 2, 12, 13);
		try {
			calendar.addMeeting(meeting);
			assertSame(meeting, calendar.getMeeting(3, 2, 0));
		} catch (TimeConflictException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testGetNonExistingMeeting() {
		Calendar calendar = new Calendar();
		try {
			calendar.getMeeting(3, 2, 0);
			fail("Meeting not supposed to exist");
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	public void testRemoveExistingMeeting() {
		Calendar calendar = new Calendar();
		Meeting meeting = new Meeting(3, 2, 12, 13);
		try {
			calendar.addMeeting(meeting);
			calendar.removeMeeting(3, 2, 0);
			assertFalse(calendar.isBusy(3, 2, 12, 13));
		} catch (TimeConflictException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testRemoveNonExistingMeeting() {
		Calendar calendar = new Calendar();
		try {
			calendar.removeMeeting(3, 2, 0);
			fail("Meeting not supposed to exist");
		} catch (Exception e) {
			assertTrue(true);
		}
	}
}
