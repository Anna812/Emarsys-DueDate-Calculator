import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Ticket {
    public LocalDateTime timeOfReport;
    public long turnaroundTime;
    public LocalDateTime dueDate;

    public Ticket(long turnaroundTime) throws Exception {
        if(checkIfWorktime(LocalDateTime.now()) && !checkIfWeekend(LocalDateTime.now())) {
            timeOfReport = LocalDateTime.now();
            this.turnaroundTime = turnaroundTime;
        } else {
            throw new Exception("You cannot report new bug outside working hours");
        }
    }

    public Ticket(long turnaroundTime, LocalDateTime timeOfReport) {
        this.turnaroundTime = turnaroundTime;
        this.timeOfReport = timeOfReport;
    }

    private boolean checkIfWorktime(LocalDateTime localDateTime) {
        int nowInHour = localDateTime.getHour();
        return nowInHour < 17 && nowInHour > 9;
    }

    private boolean checkIfWeekend(LocalDateTime localDateTime){
        DayOfWeek nowNameOfDay = localDateTime.getDayOfWeek();
        return nowNameOfDay.equals(DayOfWeek.SATURDAY) || nowNameOfDay.equals(DayOfWeek.SUNDAY);
    }

    public void calculateDueDate() {
        int modulus = (int)turnaroundTime % 8;
        if(modulus == 0) {
            dueDate = timeOfReport.plusDays(turnaroundTime/8);
        } else {
            dueDate = timeOfReport.plusDays(turnaroundTime/8).plusHours(modulus);
        }
        while(checkIfWeekend(dueDate)) {
            dueDate = dueDate.plusDays(1);
        }
    }
}
