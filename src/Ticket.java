import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class Ticket {
    public LocalDateTime timeOfReport;
    public long turnaroundTime;
    public LocalDateTime dueDate;

    public Ticket(long turnaroundTime) throws Exception {
        if(checkIfWorktime() && !checkIfWeekend()) {
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

    private boolean checkIfWorktime() {
        int nowInHour = LocalDateTime.now().getHour();
        return nowInHour < 17 && nowInHour > 9;
    }

    private boolean checkIfWeekend(){
        DayOfWeek nowNameOfDay = LocalDateTime.now().getDayOfWeek();
        return nowNameOfDay.equals("SATURDAY") || nowNameOfDay.equals("SUNDAY");
    }

    public void calculateDueDate() {
        dueDate = timeOfReport.plusHours(turnaroundTime);
    }
}
