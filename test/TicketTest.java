import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.*;

public class TicketTest {

    @Test(expected = IllegalArgumentException.class)
    public void checkTicketConstructorValidationTestOutofWorkingHours(){
        new Ticket(1, LocalDateTime.of(2018, Month.APRIL, 6, 18, 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkTicketConstructorValidationTestWeekend(){
        new Ticket(1, LocalDateTime.of(2018, Month.APRIL, 7, 9, 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateDueDateTestInvalidTurnaroundValue(){
        new Ticket(-1, LocalDateTime.of(2018, Month.APRIL, 6, 16, 0));
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

    @Test
    public void calculateDueDateTestSeveralWeekendsIsWithinTurnaroundPeriod() {
        Ticket ticket = new Ticket(72, LocalDateTime.of(2018, Month.APRIL, 4, 12, 0));
        try {
            ticket.calculateDueDate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        LocalDateTime result = LocalDateTime.of(2018, Month.APRIL, 17, 12, 0);
        assertEquals(result, ticket.dueDate);
    }

    @Test
    public void calculateDueDateTestSeveralWeekendsIsWithinTurnaroundPeriodAndCannotBeDividedByEight() {
        Ticket ticket = new Ticket(78, LocalDateTime.of(2018, Month.APRIL, 4, 12, 0));
        try {
            ticket.calculateDueDate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        LocalDateTime result = LocalDateTime.of(2018, Month.APRIL, 18, 10, 0);
        assertEquals(result, ticket.dueDate);
    }
}
