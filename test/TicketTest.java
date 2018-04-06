import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class TicketTest {

    Ticket ticketA;

    @Before
    public void setUp() throws Exception {
       ticketA = new Ticket(1);
    }

    @Test
    public void calculateDueDateTestTurnaroundIsOneHour() {
        Date result = new Date(2018, Calendar.APRIL, 06, 10, 00);
        ticketA.timeOfReport = new Date(2018, Calendar.APRIL, 06, 9, 00);
        ticketA.calculateDueDate();
        assertEquals(result, ticketA.dueDate);
    }
}
