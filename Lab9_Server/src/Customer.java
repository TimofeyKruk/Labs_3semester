import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Customer implements Serializable {
    public String customerName;
    ArrayList<Date> customerAppointments = new ArrayList<>();

    //Methods
    public Customer() {
    }

    public Customer(String customerName) {
        this.customerName = customerName;
        for (Appointment appointment : ServerDentist.dentalDB.getAppointments()) {
            if (appointment.getClientName() != null)
                if (appointment.getClientName().equals(this.customerName))
                    this.addAppointment(appointment.getDate());

        }
    }

    public Customer(String customerName, ArrayList<Date> customerAppointments) {
        this.customerName = customerName;
        this.customerAppointments = customerAppointments;
    }


    public void addAppointment(Date date) {
        this.customerAppointments.add(date);
    }

    public String appointmentsToString() {
        StringBuffer str = new StringBuffer();
        str.append("_____Appointments of client: " + this.getCustomerName() + " _____\n");

        if (customerAppointments.size() == 0)
            return "You have no appointments!";
        customerAppointments.sort((Date o1, Date o2) -> o1.compareTo(o2));//?????
        for (Date date : customerAppointments) {
            str.append(DentalDB.dateFormat.format(date));
            str.append('\n');
        }
        str.append("____________________________________________");

        return str.toString();
    }


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public ArrayList<Date> getCustomerAppointments() {
        return customerAppointments;
    }

    public void setCustomerAppointments(ArrayList<Date> customerAppointments) {
        this.customerAppointments = customerAppointments;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerName='" + customerName + "\'\n" +
                ", customerAppointments=" + customerAppointments +
                '}';
    }
}
