import java.io.Serializable;
import java.util.Date;

public class Appointment implements Serializable {
    private Date date;
    private boolean isBooked=false;
    private String clientName;

    public Appointment(Date date) {
        this.date = date;
        clientName=null;
    }

    public Appointment(Date date, boolean isBooked, String clientName) {
        this.date = date;
        this.isBooked = isBooked;
        this.clientName = clientName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}
