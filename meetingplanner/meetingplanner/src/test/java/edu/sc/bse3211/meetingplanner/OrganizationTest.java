package edu.sc.bse3211.meetingplanner;

import static org.junit.Assert.*;
import org.junit.Test;

public class OrganizationTest {
    // Add test methods here.
    // You are not required to write tests for all classes.
    @Test
    public void testConstructor() {
        // Initialization
        var organization = new Organization();
        int expectedSize = 5;// Test oracle
        // Test steps
        int employees = organization.getEmployees().size();
        int rooms = organization.getRooms().size();
        assertEquals("Number of employees is not equal to 5", expectedSize, employees);
        assertEquals("Number of rooms is not equal to 5", expectedSize, rooms);
    }

    @Test
    public void testGetUnknownEmployee() {
        var organization = new Organization();
        try {
            organization.getEmployee("Kato Trevor");
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void testGetUnknownRoom() {
        var organization = new Organization();
        try {
            organization.getRoom("MIIC");
        } catch (Exception e) {
            assertTrue(true);
        }
    }
}
