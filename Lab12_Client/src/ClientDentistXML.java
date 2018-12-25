import Support.ProjectValidator;
import Support.SupportFiles;
import Support.SupportXML;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class ClientDentistXML {

    public static void main(String[] args) {
        try {
            String DTD_CLIENT = "ClientRequest.dtd";
            String SCHEMA_CLIENT = "ClientRequest.xsd";
            String DTD_SERVER;
            String SCHEMA_SERVER;

            //Connecting
            Socket client = new Socket("localhost", 4500);
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            DataInputStream in = new DataInputStream(client.getInputStream());

            // +++++++++ Support Files +++++++++++
            SupportFiles supportFiles = new SupportFiles(in, out);

            //___Exchanging dtd and schema files____
            DTD_SERVER = supportFiles.receiveFile();
            SCHEMA_SERVER = supportFiles.receiveFile();

            ProjectValidator clientValidator = new ProjectValidator(DTD_SERVER, SCHEMA_SERVER);

            //___sending files___
            supportFiles.sendFile(DTD_CLIENT);
            supportFiles.sendFile(SCHEMA_CLIENT);


            //Introduction
            //*************************************
            System.out.println(in.readUTF());
            Scanner consoleIn = new Scanner(System.in);
            //*************************************

            //Authorisation
            //*************************************
            String clientName = consoleIn.nextLine();

            if (clientName.isEmpty())
                clientName = "NO NAME";

            out.writeUTF(clientName);
            out.flush();
            //*************************************

            // +++++++++ Client Request +++++++++++
            ClientRequest clientRequest;
            clientRequest = new ClientRequest(clientName, "registration");


            SupportXML supportXML = new SupportXML(clientValidator);
            ServerReply serverReply;
            //Working with client
            while (!client.isClosed()) {
                //Receiving answer or/and intro by XML (receiving and reading)
                serverReply = (ServerReply) supportXML.readFromXMLFile(supportFiles.receiveFile());
                String reply = serverReply.getReply();

                System.out.println(reply);

                //Entering option what to do
                String input = consoleIn.nextLine();
                clientRequest.setRequest(input);

                //Making and sending XML to Server
                String fileXMLName = supportXML.makeXMLFile(clientRequest);
                supportFiles.sendFile(fileXMLName);

                //Checking for quit
                if (input.equalsIgnoreCase("quit")) {
                    Thread.sleep(100);

                    //Receiving answer or/and intro by XML (receiving and reading)
                    serverReply = (ServerReply) supportXML.readFromXMLFile(supportFiles.receiveFile());
                    System.out.println(serverReply.getReply());

                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
