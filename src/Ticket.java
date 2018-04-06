import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class Ticket {
    private LocalDateTime timeOfReport;
    private int turnaroundTime;
    LocalDateTime dueDate;

    public Ticket(int turnaroundTime) throws Exception {
        if(checkIfWorktime(LocalDateTime.now()) && !checkIfWeekend(LocalDateTime.now())) {
            timeOfReport = LocalDateTime.now();
            this.turnaroundTime = turnaroundTime;
        } else {
            throw new Exception("You cannot report new bug outside working hours");
        }
    }

    // this constructor is only used to be able to create test Tickets after working hours and on weekends too
    Ticket(int turnaroundTime, LocalDateTime timeOfReport) {
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
        int modulus = turnaroundTime % 8;
        int result = turnaroundTime / 8;
        dueDate = timeOfReport;

        for(int i = 0; i < result; i++) {
            dueDate = dueDate.plusDays(1);
            while(checkIfWeekend(dueDate)) {
                dueDate = dueDate.plusDays(1);
            }
        }

        if(modulus != 0) {
            dueDate = dueDate.plusHours(modulus);
        }
    }
}
