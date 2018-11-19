import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

final class DateCompare implements Comparator<Appointment> {
    @Override
    public int compare(Appointment o1, Appointment o2) {
        return o1.getDate().compareTo(o2.getDate());
    }
}

public class DentalDB {
    private ArrayList<Appointment> appointments = new ArrayList<>();
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    protected static SimpleDateFormat dayFormat = new SimpleDateFormat("dd.MM.yyyy");
    protected static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

    public DentalDB() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(ServerDentist.currentDate);
        for (int i = 0; i < 7; ++i) {
            calendar.add(Calendar.DATE, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 8);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            calendar.set(Calendar.SECOND, 0);
            for (int j = 1; j < ServerDentist.MAX_APPOINTMENTS; ++j) {
                calendar.add(Calendar.MINUTE, 20);
                appointments.add(new Appointment(calendar.getTime()));
            }
        }
    }

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(ArrayList<Appointment> appointments) {
        this.appointments = appointments;
    }

    public static Date toDate(String str)
    {
        try {
            return dateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String denyAppointment(Date date, Customer customer)
    {

        for (Appointment appointment : appointments) {
            if (appointment.getDate().equals(date))
                if (!this.isBooked(appointment))
                return "This time is free!";
                else
                    if(appointment.getClientName().equals(customer.getCustomerName()))
                {
                    appointment.setClientName(null);
                    customer.customerAppointments.remove(appointment.getDate());
                    appointment.setBooked(false);
                    return "Time was denied successfully!";
                }
                else
                    return "This time doesn't belong to you. You can't deny this!";

        }

        return "Error! You can't deny this time!";
    }

    public boolean addAppointment(Date date, Customer customer) {
        for (Appointment appointment : appointments) {
            if (appointment.getDate().equals(date))
                if (!this.isBooked(appointment)) {
                    appointment.setClientName(customer.getCustomerName());
                    customer.addAppointment(appointment.getDate());
                    appointment.setBooked(true);
                    return true;
                }

        }
        return false;
    }

    public boolean isBooked(Appointment appointment) {
        return appointment.isBooked();
    }

    public String showAppointments() {
        Collections.sort(appointments, new DateCompare());
        int tempDay = 0;
        int day;
        StringBuffer str = new StringBuffer();
        Calendar calendar = Calendar.getInstance();
        for (Appointment appointment : appointments) {
            calendar.setTime(appointment.getDate());
            day = calendar.get(Calendar.DAY_OF_MONTH);

            if (!appointment.isBooked())
                if (day != tempDay) {
                    tempDay = day;
                    str.append('\n');
                    str.append(dayFormat.format(appointment.getDate()) + "  ----  | " + timeFormat.format(appointment.getDate()) + " | ");
                } else
                    str.append(timeFormat.format(appointment.getDate()) + " | ");


        }
        return str.toString();
    }


}
