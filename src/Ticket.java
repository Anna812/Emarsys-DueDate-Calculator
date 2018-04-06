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
        return nowInHour <= 17 && nowInHour >= 9;
    }

    private boolean checkIfWeekend(LocalDateTime localDateTime){
        DayOfWeek nowNameOfDay = localDateTime.getDayOfWeek();
        return nowNameOfDay.equals(DayOfWeek.SATURDAY) || nowNameOfDay.equals(DayOfWeek.SUNDAY);
    }

    public void calculateDueDate() throws Exception {
        if(timeOfReport == null) {
            throw new NullPointerException();
        } else if (turnaroundTime <= 0){
            throw new Exception("You need to have a valid turn around time.");
        }
        
        dueDate = timeOfReport;
        int modulus = turnaroundTime % 8;

        calculateDate(turnaroundTime / 8);
        
        if(modulus != 0) {
            calculateTime(modulus);
        }
    }

    private void calculateTime(int modulus) {
        dueDate = dueDate.plusHours(modulus);

        if(!checkIfWorktime(dueDate)) {
            dueDate = dueDate.plusHours(16);
            stepOverWeekend();
        }
    }

    private void calculateDate(int result) {
        for(int i = 0; i < result; i++) {
            dueDate = dueDate.plusDays(1);
            stepOverWeekend();
        }
    }

    private void stepOverWeekend() {
        if(checkIfWeekend(dueDate)) {
            dueDate = dueDate.plusDays(2);
        }
    }
}
