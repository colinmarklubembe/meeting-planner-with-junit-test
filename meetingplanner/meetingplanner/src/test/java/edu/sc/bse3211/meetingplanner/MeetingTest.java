package edu.sc.bse3211.meetingplanner;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class MeetingTest {
    // Add test methods here.
    // You are not required to write tests for all classes.
    @Test
    public void testConstructor() {
        ArrayList<Person> attendees = new ArrayList<Person>();
        attendees.add(new Person("Kato Trevor"));
        Room room = new Room("LAB2");
        Meeting meeting = new Meeting(1, 1, 10, 12, attendees, room, "This is a meeting");

        // Test getters
        assertEquals(1, meeting.getMonth());
        assertEquals(1, meeting.getDay());
        assertEquals(10, meeting.getStartTime());
        assertEquals(12, meeting.getEndTime());
        assertFalse(meeting.getAttendees().isEmpty());
        assertEquals(room, meeting.getRoom());
        assertSame("This is a meeting", meeting.getDescription());
    }

    @Test
    public void testSetters() {
        var meeting = new Meeting();
        // Test setters
        meeting.setMonth(2);
        assertEquals(2, meeting.getMonth());
        meeting.setDay(2);
        assertEquals(2, meeting.getDay());
        meeting.setStartTime(14);
        assertEquals(14, meeting.getStartTime());
        meeting.setEndTime(16);
        assertEquals(16, meeting.getEndTime());
        Room newRoom = new Room();
        meeting.setRoom(newRoom);
        assertEquals(newRoom, meeting.getRoom());
        meeting.setDescription("New Test Meeting");
    }

    @Test
    public void testGetEmployees() {
        var org = new Organization();
        try {
            Person employee = org.getEmployee("Namugga Martha");
            assertEquals("Namugga Martha", employee.getName());
        } catch (Exception e) {
            fail("Exception should not have been thrown.");
        }
    }

    @Test
    public void getNonExistingEmployee() {
        var org = new Organization();
        try {
            org.getEmployee("Non-existent Employee");
            fail("Exception should have been thrown.");
        } catch (Exception e) {
            assertEquals("Requested employee does not exist", e.getMessage());
        }
    }

    @Test
    public void testRemoveAttendee() {
        // Initialization
        ArrayList<Person> attendees = new ArrayList<Person>();
        Person person = new Person("Kato Trevor");
        Room room = new Room("LAB2");
        // Test steps
        attendees.add(person);
        Meeting meeting = new Meeting(2, 3, 12, 13, attendees, room, "This is a meeting");
        meeting.removeAttendee(person);
        // Test Oracle
        assertEquals(0, meeting.getAttendees().size());
    }

    @Test
    public void testRemoveNonExistingAttendee() {
        // Initialization
        ArrayList<Person> attendees = new ArrayList<Person>();
        Person person = new Person("Kato Trevor");
        Person otherPerson = new Person("Thomas");
        Room room = new Room("LAB2");
        try {
            // Test steps
            attendees.add(person);
            Meeting meeting = new Meeting(2, 3, 12, 13, attendees, room, "This is a meeting");
            meeting.removeAttendee(otherPerson);
            // Test Oracle
            assertEquals(1, meeting.getAttendees().size());
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void testGetRoom(){
        var org = new Organization();
         try {
            Room room = org.getRoom("LLT6A");
            assertEquals("LLT6A", room.getID());
        } catch (Exception e) {
            fail("Exception should not have been thrown.");
        }
    }

    @Test
    public void testGetNonExistingRoom(){
        var org = new Organization();
        try {
            org.getRoom("Non-existent Room");
            fail("Exception should have been thrown.");
        } catch (Exception e) {
            assertEquals("Requested room does not exist", e.getMessage());
        }
    }
}
