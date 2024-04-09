package edu.sc.bse3211.meetingplanner;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class RoomTest {
    // Add test methods here.
    // You are not required to write tests for all classes.
    @Test
    public void testConstructor() {
        Room room = new Room("LAB2");
        assertEquals("LAB2", room.getID());
    }

    @Test
    public void testAddMeeting() {
        Room room = new Room("LAB2");
        ArrayList<Person> attendees = new ArrayList<>();
        Meeting meeting = new Meeting(3, 4, 5, 6, attendees, room, "This is a meeting");
        try {
            room.addMeeting(meeting);
            assertTrue(room.isBusy(3,4,5,6));
        } catch (Exception e) {
            fail("Should not throw exception: " + e.getMessage());
        }
    }
}
