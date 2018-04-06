import org.junit.Test;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class TicketTest {

    @Test
    public void calculateDueDateTestTurnaroundIsOneHour() {
        Ticket ticket = new Ticket(1, new Date(2018, Calendar.APRIL, 06, 9, 00));
        ticket.calculateDueDate();
        Date result = new Date(2018, Calendar.APRIL, 06, 10, 00);
        assertEquals(result, ticket.dueDate);
    }
}
