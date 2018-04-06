import java.util.Date;

public class Ticket {
    public Date timeOfReport;
    public int turnaroundTime;
    public Date dueDate;

    public Ticket(int turnaroundTime) {
        timeOfReport = new Date();
        this.turnaroundTime = turnaroundTime;
    }

    public void calculateDueDate() {
        
    }
}
