import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.*;

public class TicketTest {

    @Test
    public void checkTicketConstructorValidationTestOutofWorkingHours(){
        try {
            new Ticket(1);
            fail();
        } catch (Exception e) {
            assertEquals("You cannot report new bug outside working hours.", e.getMessage());
        }
    }

    @Test
    public void checkTicketConstructorValidationTestWeekend(){
        try {
            new Ticket(1);
            fail("Exception is expected if test runs on weekend");
        } catch (Exception e) {
            assertEquals("You cannot report new bug outside working hours.", e.getMessage());
        }
    }

    @Test
    public void calculateDueDateTestInvalidTurnaroundValue(){
        try {
            new Ticket(-1);
            fail("Exception is expected if turnaround value is invalid");
        } catch (Exception e) {
            assertEquals("You need to have a valid turn around time.", e.getMessage());
        }
    }

    @Test
    public void calculateDueDateTestTurnaroundIsOneHour() {
        Ticket ticket = new Ticket(1, LocalDateTime.of(2018, Month.APRIL, 6, 9, 0));
        try {
            ticket.calculateDueDate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        LocalDateTime result = LocalDateTime.of(2018, Month.APRIL, 6, 10, 0);
        assertEquals(result, ticket.dueDate);
    }

    @Test
    public void calculateDueDateTestTurnaroundIsEightHours() {
        Ticket ticket = new Ticket(8, LocalDateTime.of(2018, Month.APRIL, 4, 9, 0));
        try {
            ticket.calculateDueDate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        LocalDateTime result = LocalDateTime.of(2018, Month.APRIL, 5, 9, 0);
        assertEquals(result, ticket.dueDate);
    }

    @Test
    public void calculateDueDateTestTurnaroundIsEightHoursMidDay() {
        Ticket ticket = new Ticket(8, LocalDateTime.of(2018, Month.APRIL, 4, 10, 0));
        try {
            ticket.calculateDueDate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        LocalDateTime result = LocalDateTime.of(2018, Month.APRIL, 5, 10, 0);
        assertEquals(result, ticket.dueDate);
    }

    @Test
    public void calculateDueDateTestTurnaroundIsNineHours() {
        Ticket ticket = new Ticket(9, LocalDateTime.of(2018, Month.APRIL, 4, 9, 0));
        try {
            ticket.calculateDueDate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        LocalDateTime result = LocalDateTime.of(2018, Month.APRIL, 5, 10, 0);
        assertEquals(result, ticket.dueDate);
    }

    @Test
    public void calculateDueDateTestTurnaroundIsNineHoursMidDay() {
        Ticket ticket = new Ticket(9, LocalDateTime.of(2018, Month.APRIL, 4, 10, 0));
        try {
            ticket.calculateDueDate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        LocalDateTime result = LocalDateTime.of(2018, Month.APRIL, 5, 11, 0);
        assertEquals(result, ticket.dueDate);
    }

    @Test
    public void calculateDueDateTestTurnaroundIsNineHoursSaturday() {
        Ticket ticket = new Ticket(9, LocalDateTime.of(2018, Month.APRIL, 6, 10, 0));
        try {
            ticket.calculateDueDate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        LocalDateTime result = LocalDateTime.of(2018, Month.APRIL, 9, 11, 0);
        assertEquals(result, ticket.dueDate);
    }

    @Test
    public void calculateDueDateTestNewMonth() {
        Ticket ticket = new Ticket(9, LocalDateTime.of(2017, Month.DECEMBER, 29, 10, 0));
        try {
            ticket.calculateDueDate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        LocalDateTime result = LocalDateTime.of(2018, Month.JANUARY, 1, 11, 0);
        assertEquals(result, ticket.dueDate);
    }

    @Test
    public void calculateDueDateTestLongTurnaround() {
        Ticket ticket = new Ticket(45, LocalDateTime.of(2018, Month.APRIL, 10, 10, 0));
        try {
            ticket.calculateDueDate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        LocalDateTime result = LocalDateTime.of(2018, Month.APRIL, 17, 15, 0);
        assertEquals(result, ticket.dueDate);
    }

    @Test
    public void calculateDueDateTestReportedEndOfDay() {
        Ticket ticket = new Ticket(2, LocalDateTime.of(2018, Month.APRIL, 10, 16, 0));
        try {
            ticket.calculateDueDate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        LocalDateTime result = LocalDateTime.of(2018, Month.APRIL, 11, 10, 0);
        assertEquals(result, ticket.dueDate);
    }

    @Test
    public void calculateDueDateTestReportedEndOfFriday() {
        Ticket ticket = new Ticket(2, LocalDateTime.of(2018, Month.APRIL, 6, 16, 0));
        try {
            ticket.calculateDueDate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        LocalDateTime result = LocalDateTime.of(2018, Month.APRIL, 9, 10, 0);
        assertEquals(result, ticket.dueDate);
    }

    @Test
    public void calculateDueDateTestReportedEndOfFridayNineHoursTurnaround() {
        Ticket ticket = new Ticket(9, LocalDateTime.of(2018, Month.APRIL, 6, 16, 0));
        try {
            ticket.calculateDueDate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        LocalDateTime result = LocalDateTime.of(2018, Month.APRIL, 9, 17, 0);
        assertEquals(result, ticket.dueDate);
    }
}
