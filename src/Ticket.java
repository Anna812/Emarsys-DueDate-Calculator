import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class Ticket {
    private LocalDateTime timeOfReport;
    private int turnaroundTime;
    LocalDateTime dueDate;
    private final int workDayStart = 9;
    private final int workDayEnd = 17;

    public Ticket(int turnaroundTime, LocalDateTime timeOfReport) {
        if(turnaroundTime > 0) {
            this.turnaroundTime = turnaroundTime;
        } else {
            throw new IllegalArgumentException();
        }

        if(checkIfWorktime(timeOfReport) && !checkIfWeekend(timeOfReport)) {
            this.timeOfReport = timeOfReport;
        } else {
            throw new IllegalArgumentException();
        }
    }

    private boolean checkIfWorktime(LocalDateTime localDateTime) {
        int nowInHour = localDateTime.getHour();
        return nowInHour <= workDayEnd && nowInHour >= workDayStart;
    }

    private boolean checkIfWeekend(LocalDateTime localDateTime){
        DayOfWeek nowNameOfDay = localDateTime.getDayOfWeek();
        return nowNameOfDay.equals(DayOfWeek.SATURDAY) || nowNameOfDay.equals(DayOfWeek.SUNDAY);
    }

    public void calculateDueDate() {
        dueDate = timeOfReport;
        int workDayLength = workDayEnd - workDayStart;
        int leftoverTime = turnaroundTime % workDayLength;

        calculateDate(turnaroundTime / workDayLength);
        
        if(leftoverTime != 0) {
            calculateTime(leftoverTime);
        }
    }

    private void calculateTime(int leftoverTime) {
        dueDate = dueDate.plusHours(leftoverTime);

        if(!checkIfWorktime(dueDate)) {
            dueDate = dueDate.plusHours(24 -  workDayEnd + workDayStart);
            stepOverWeekend();
        }
    }

    private void calculateDate(int limit) {
        for(int i = 0; i < limit; i++) {
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
