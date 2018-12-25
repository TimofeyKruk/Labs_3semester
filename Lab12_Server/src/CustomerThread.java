import Support.ProjectValidator;
import Support.SupportFiles;
import Support.SupportXML;

import java.io.*;
import java.net.Socket;

public class CustomerThread implements Runnable {

    Socket client;
    ServerReply serverReply;

    public CustomerThread(Socket client) {
        this.client = client;
    }


    //--------------------------RUN------------------------
    @Override
    public void run() {
        try {
            String DTD_CLIENT;
            String SCHEMA_CLIENT;
            String DTD_SERVER = "ServerReply.dtd";
            String SCHEMA_SERVER = "ServerReply.xsd";

            serverReply = new ServerReply();
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            DataInputStream in = new DataInputStream(client.getInputStream());
            //System.out.println("In&Out streams were created for client!");

            // +++++++++ Support Files +++++++++++
            SupportFiles supportFiles = new SupportFiles(in, out);

            //___sending files___
            supportFiles.sendFile(DTD_SERVER);
            supportFiles.sendFile(SCHEMA_SERVER);

            //___Exchanging dtd and schema files____
            DTD_CLIENT = supportFiles.receiveFile();
            SCHEMA_CLIENT = supportFiles.receiveFile();

            ProjectValidator serverValidator = new ProjectValidator(DTD_CLIENT, SCHEMA_CLIENT);


            String introduction = "**** Welcome to dentist appointments service!****\nEnter your name: ";
            out.writeUTF(introduction);
            out.flush();
            //Waiting for reply from client
            String customerName = in.readUTF();
            System.out.println(customerName + " initialized!");
            Customer customer = new Customer(customerName);


            //-------INTRO-------
            StringBuffer title = new StringBuffer();
            title.append("___" + customer.getCustomerName() + ", what would you like to do?___\n");
            title.append("Write \"make\" to make an appointment.\n");
            title.append("Write \"my\" to look at your appointments.\n");
            title.append("Write \"deny\" to deny your appointment.\n");
            title.append("Write \"quit\" to disconnect.\n");


            SupportXML supportXML = new SupportXML(serverValidator);

            //Making and sending intro as XML
            serverReply.setReply(title.toString());
            supportFiles.sendFile(supportXML.makeXMLFile(serverReply));

            //------------Main part------------------
            ClientRequest clientRequest;
            while (!client.isClosed()) {
                //Receiving and reading XML from client
                clientRequest = (ClientRequest) supportXML.readFromXMLFile(supportFiles.receiveFile());
                String request = clientRequest.getRequest();

                //Status
                System.out.println(clientRequest.getClientName() + " requested: " + request);

                switch (request) {
                    case "make":

                        //Making and sending as XML
                        serverReply.setReply(ServerDentistXML.dentalDB.showAppointments() + '\n' + "Write in format \"dd.MM.yyyy HH:mm\"\n");
                        supportFiles.sendFile(supportXML.makeXMLFile(serverReply));

                        //Receiving and reading XML from client
                        clientRequest = (ClientRequest) supportXML.readFromXMLFile(supportFiles.receiveFile());
                        String dateAppointment = clientRequest.getRequest();

                        if (ServerDentistXML.dentalDB.addAppointment(DentalDB.toDate(dateAppointment), customer)) {
                            serverReply.setReply("You are assigned to the specified time!\n\n" + title.toString());
                            System.out.println(customer.getCustomerName() + " appointed for: " + dateAppointment);
                        } else {
                            System.out.println(customer.getCustomerName() + " failed to make an appointment for: " + dateAppointment);
                            serverReply.setReply("Sorry, this time is already assigned! Try another free time!\n\n" + title.toString());
                        }
                        supportFiles.sendFile(supportXML.makeXMLFile(serverReply));

                        break;

                    case "my":
                        System.out.println(customer.getCustomerName() + " requested the list of his/her appointments!");
                        //Making and sending as XML
                        serverReply.setReply(customer.appointmentsToString() + "\n\n" + title.toString());
                        supportFiles.sendFile(supportXML.makeXMLFile(serverReply));
                        break;

                    case "deny":
                        //Making and sending as XML
                        serverReply.setReply("Enter time you want to deny. Write in format \"dd.MM.yyyy HH:mm\"\n");
                        supportFiles.sendFile(supportXML.makeXMLFile(serverReply));

                        //Receiving and reading XML from client
                        clientRequest = (ClientRequest) supportXML.readFromXMLFile(supportFiles.receiveFile());
                        dateAppointment = clientRequest.getRequest();

                        String denied = ServerDentistXML.dentalDB.denyAppointment(DentalDB.toDate(dateAppointment), customer);

                        //Making and sending as XML
                        serverReply.setReply(denied + '\n' + title.toString());
                        supportFiles.sendFile(supportXML.makeXMLFile(serverReply));

                        System.out.println(customer.getCustomerName() + " tries to deny: " + dateAppointment);
                        System.out.println(customer.getCustomerName() + " got in result: " + denied);
                        break;

                    case "quit":
                        //Making and sending as XML
                        serverReply.setReply("You have disconnected! We hope to see you soon!");
                        supportFiles.sendFile(supportXML.makeXMLFile(serverReply));

                        System.out.println("_!!!!!!! " + customer.getCustomerName() + " has disconnected!!!!!!!_\n");
                        in.close();
                        out.close();
                        client.close();
                        break;
                    default:
                        //Making and sending intro as XML
                        serverReply.setReply(title.toString());
                        supportFiles.sendFile(supportXML.makeXMLFile(serverReply));
                        break;

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
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
