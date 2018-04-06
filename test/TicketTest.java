import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.*;

public class TicketTest {

    @Test
    public void checkTicketConstructorValidationTestOutofWorkingHours(){
        try {
            Ticket ticket = new Ticket(1);
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
}
