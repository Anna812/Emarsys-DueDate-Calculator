import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.*;

public class TicketTest {

    @Test
    public void checkTicketConstructorValidationTestOutofWorkingHours(){
        try {
            new Ticket(1L);
            fail();
        } catch (Exception e) {
            assertEquals("You cannot report new bug outside working hours", e.getMessage());
        }
    }

    @Test
    public void checkTicketConstructorValidationTestWeekend(){
        try {
            new Ticket(1L);
            fail("exception is expected if test runs on weekend");
        } catch (Exception e) {
            assertEquals("You cannot report new bug outside working hours", e.getMessage());
        }
    }

    @Test
    public void calculateDueDateTestTurnaroundIsOneHour() {
        Ticket ticket = new Ticket(1, LocalDateTime.of(2018, Month.APRIL, 6, 9, 0));
        ticket.calculateDueDate();
        LocalDateTime result = LocalDateTime.of(2018, Month.APRIL, 6, 10, 0);
        assertEquals(result, ticket.dueDate);
    }

    @Test
    public void calculateDueDateTestTurnaroundIsEightHours() {
        Ticket ticket = new Ticket(8, LocalDateTime.of(2018, Month.APRIL, 4, 9, 0));
        ticket.calculateDueDate();
        LocalDateTime result = LocalDateTime.of(2018, Month.APRIL, 5, 9, 0);
        assertEquals(result, ticket.dueDate);
    }

    @Test
    public void calculateDueDateTestTurnaroundIsEightHoursMidDay() {
        Ticket ticket = new Ticket(8, LocalDateTime.of(2018, Month.APRIL, 4, 10, 0));
        ticket.calculateDueDate();
        LocalDateTime result = LocalDateTime.of(2018, Month.APRIL, 5, 10, 0);
        assertEquals(result, ticket.dueDate);
    }

    @Test
    public void calculateDueDateTestTurnaroundIsNineHours() {
        Ticket ticket = new Ticket(9, LocalDateTime.of(2018, Month.APRIL, 4, 9, 0));
        ticket.calculateDueDate();
        LocalDateTime result = LocalDateTime.of(2018, Month.APRIL, 5, 10, 0);
        assertEquals(result, ticket.dueDate);
    }

    @Test
    public void calculateDueDateTestTurnaroundIsNineHoursMidDay() {
        Ticket ticket = new Ticket(9, LocalDateTime.of(2018, Month.APRIL, 4, 10, 0));
        ticket.calculateDueDate();
        LocalDateTime result = LocalDateTime.of(2018, Month.APRIL, 5, 11, 0);
        assertEquals(result, ticket.dueDate);
    }

    @Test
    public void calculateDueDateTestTurnaroundIsNineHoursSaturday() {
        Ticket ticket = new Ticket(9, LocalDateTime.of(2018, Month.APRIL, 6, 10, 00));
        ticket.calculateDueDate();
        LocalDateTime result = LocalDateTime.of(2018, Month.APRIL, 9, 11, 00);
        assertEquals(result, ticket.dueDate);
    }

    @Test
    public void calculateDueDateTestNewMonth() {
        Ticket ticket = new Ticket(9, LocalDateTime.of(2017, Month.DECEMBER, 29, 10, 00));
        ticket.calculateDueDate();
        LocalDateTime result = LocalDateTime.of(2018, Month.JANUARY, 1, 11, 00);
        assertEquals(result, ticket.dueDate);
    }
}
