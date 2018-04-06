import java.util.Date;

public class Ticket {
    public Date timeOfReport;
    public int turnaroundTime;
    public Date dueDate;

    public Ticket(int turnaroundTime) throws Exception {
        if(checkIfWorktime()) {
            timeOfReport = new Date();
            this.turnaroundTime = turnaroundTime;
        } else {
            throw new Exception("You cannot report new bug outside working hours");
        }
    }

    private boolean checkIfWorktime() {
        Date now = new Date();
        int nowHour = now.getHours();
        return (nowHour < 17 && nowHour > 9);
    }

    public void calculateDueDate() {
        
    }
}
