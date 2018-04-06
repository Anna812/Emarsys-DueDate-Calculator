import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.*;

public class TicketTest {

    @Test
    public void checkTicketConstructorValidationTestOutofWorkingHours(){
        try {
            Ticket ticket = new Ticket(1L);
            fail();
        } catch (Exception e) {
            assertEquals("You cannot report new bug outside working hours", e.getMessage());
        }
    }

    @Test
    public void checkTicketConstructorValidationTestWeekend(){
        try {
            Ticket ticket = new Ticket(1L);
            fail("exception is expected if test runs on weekend");
        } catch (Exception e) {
            assertEquals("You cannot report new bug outside working hours", e.getMessage());
        }
    }

    @Test
    public void calculateDueDateTestTurnaroundIsOneHour() {
        Ticket ticket = new Ticket(1, LocalDateTime.of(2018, Month.APRIL, 06, 9, 00));
        ticket.calculateDueDate();
        LocalDateTime result = LocalDateTime.of(2018, Month.APRIL, 06, 10, 00);
        assertEquals(result, ticket.dueDate);
    }

    @Test
    public void calculateDueDateTestTurnaroundIsEightHours() {
        Ticket ticket = new Ticket(8, LocalDateTime.of(2018, Month.APRIL, 04, 9, 00));
        ticket.calculateDueDate();
        LocalDateTime result = LocalDateTime.of(2018, Month.APRIL, 05, 9, 00);
        assertEquals(result, ticket.dueDate);
    }

    @Test
    public void calculateDueDateTestTurnaroundIsEightHoursMidDay() {
        Ticket ticket = new Ticket(8, LocalDateTime.of(2018, Month.APRIL, 04, 10, 00));
        ticket.calculateDueDate();
        LocalDateTime result = LocalDateTime.of(2018, Month.APRIL, 05, 10, 00);
        assertEquals(result, ticket.dueDate);
    }

    @Test
    public void calculateDueDateTestTurnaroundIsNineHours() {
        Ticket ticket = new Ticket(9, LocalDateTime.of(2018, Month.APRIL, 04, 9, 00));
        ticket.calculateDueDate();
        LocalDateTime result = LocalDateTime.of(2018, Month.APRIL, 05, 10, 00);
        assertEquals(result, ticket.dueDate);
    }

    @Test
    public void calculateDueDateTestTurnaroundIsNineHoursMidDay() {
        Ticket ticket = new Ticket(9, LocalDateTime.of(2018, Month.APRIL, 04, 10, 00));
        ticket.calculateDueDate();
        LocalDateTime result = LocalDateTime.of(2018, Month.APRIL, 05, 11, 00);
        assertEquals(result, ticket.dueDate);
    }

    @Test
    public void calculateDueDateTestTurnaroundIsNineHoursSaturday() {
        Ticket ticket = new Ticket(9, LocalDateTime.of(2018, Month.APRIL, 06, 10, 00));
        ticket.calculateDueDate();
        LocalDateTime result = LocalDateTime.of(2018, Month.APRIL, 8, 11, 00);
        assertEquals(result, ticket.dueDate);
    }
}
