package edu.sc.bse3211.meetingplanner;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class PersonTest {
	// Add test methods here.
	// You are not required to write tests for all classes.
	@Test
	public void testConstructor() {
		Person person = new Person("Bradley");
		assertEquals("Bradley", person.getName());
	}

	@Test
	public void testAddMeeting() {
        Room room = new Room("LAB2");
		Person person = new Person("Kato Trevor");
        ArrayList<Person> attendees = new ArrayList<>();
        Meeting meeting; 
        try {
			attendees.add(person);
			meeting = new Meeting(3, 4, 5, 6, attendees, room, "This is a meeting");
            person.addMeeting(meeting);
            assertTrue(person.isBusy(3,4,5,6));
        } catch (Exception e) {
            fail("Should not throw exception: " + e.getMessage());
        }
    }

	@Test
    public void testGetMeeting() throws TimeConflictException {
		Room room = new Room("LAB2");
		Person person = new Person("Kato Trevor");
        ArrayList<Person> attendees = new ArrayList<>();
        Meeting meeting = new Meeting(4, 1, 10, 11, attendees, room, "Project meeting");
        person.addMeeting(meeting);
        assertEquals(meeting, person.getMeeting(4, 1, 0));
    }

	@Test
    public void testRemoveMeeting() throws TimeConflictException {
		Room room = new Room("LAB2");
		Person person = new Person("Kato Trevor");
        ArrayList<Person> attendees = new ArrayList<>();
        Meeting meeting = new Meeting(4, 1, 10, 11, attendees, room, "Project meeting");
        person.addMeeting(meeting);
        person.removeMeeting(4, 1, 0);
        assertFalse(person.isBusy(4, 1, 10, 11));
    }
}
