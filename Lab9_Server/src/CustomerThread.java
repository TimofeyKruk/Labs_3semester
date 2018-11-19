import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class CustomerThread implements Runnable {

    Socket client;

    public CustomerThread(Socket client) {
        this.client = client;
    }


    //--------------------------RUN------------------------
    @Override
    public void run() {
        try {
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            DataInputStream in = new DataInputStream(client.getInputStream());
            //System.out.println("In&Out streams were created for client!");

            String introduction = "**** Welcome to dentist appointments service!****\nEnter your name: ";
            out.writeUTF(introduction);
            out.flush();
            //Waiting for reply from client
            String customerName = in.readUTF();
            System.out.println(customerName + " initialized!");
            Customer customer = new Customer(customerName);


            //------------Main part------------------
            StringBuffer title = new StringBuffer();
            title.append("___"+customer.getCustomerName() + ", what would you like to do?___\n");
            title.append("Write \"make\" to make an appointment.\n");
            title.append("Write \"my\" to look at your appointments.\n");
            title.append("Write \"deny\" to deny your appointment.\n");
            title.append("Write \"quit\" to disconnect.\n");

            out.writeUTF(title.toString());
            while (!client.isClosed()) {

                String request = in.readUTF();
                System.out.println(customer.getCustomerName() + " requested: " + request);

                switch (request) {
                    case "make":

                        out.writeUTF(ServerDentist.dentalDB.showAppointments() + '\n' + "Write in format \"dd.MM.yyyy HH:mm\"\n");
                        out.flush();

                        String dateAppointment = in.readUTF();

                        if (ServerDentist.dentalDB.addAppointment(DentalDB.toDate(dateAppointment), customer)) {
                            out.writeUTF("You are assigned to the specified time!\n\n" + title.toString());
                            System.out.println(customer.getCustomerName() + " appointed for: " + dateAppointment);
                        } else {
                            System.out.println(customer.getCustomerName() + " failed to make an appointment for: " + dateAppointment);
                            out.writeUTF("Sorry, this time is already assigned! Try another free time!\n\n" + title.toString());

                        }
                        break;

                    case "my":
                        System.out.println(customer.getCustomerName() + " requested the list of his/her appointments!");
                        out.writeUTF(customer.appointmentsToString() + "\n\n" + title.toString());
                        break;

                    case "deny":
                        out.writeUTF( "Enter time you want to deny. Write in format \"dd.MM.yyyy HH:mm\"\n");
                        out.flush();


                        dateAppointment = in.readUTF();
                        String denied=ServerDentist.dentalDB.denyAppointment(DentalDB.toDate(dateAppointment), customer);
                        out.writeUTF(denied+'\n'+title.toString());
                        System.out.println(customer.getCustomerName() + " tries to deny: "+dateAppointment);
                        System.out.println(customer.getCustomerName() + " got in result: "+denied);
                        break;

                    case "quit":
                        out.writeUTF("You have disconnected! We hope to see you soon!");
                        System.out.println("!!! "+customer.getCustomerName()+" has disconnected!!!\n");
                        in.close();
                        out.close();
                        client.close();
                        break;
                    default:
                        out.writeUTF(title.toString());
                        break;

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getClient() {
        return client;
    }

    public void setClient(Socket client) {
        this.client = client;
    }
}
