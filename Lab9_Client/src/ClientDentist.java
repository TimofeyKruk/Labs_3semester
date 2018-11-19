import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientDentist {

    public static void main(String[] args) {
        try {
            Socket client=new Socket("localhost",4500);
            DataOutputStream out =new DataOutputStream(client.getOutputStream());
            DataInputStream in=new DataInputStream(client.getInputStream());

            //Introduction
            System.out.println(in.readUTF());
            Scanner consoleIn=new Scanner(System.in);
            String clientName=consoleIn.nextLine();

            if(clientName.isEmpty())
                clientName="NO NAME";

            out.writeUTF(clientName);
            out.flush();


            while(!client.isClosed())
            {
                System.out.println(in.readUTF());

                String input=consoleIn.nextLine();
                out.writeUTF(input);
                out.flush();
                //let server think and reply
                Thread.sleep(300);
                // TODO: check for quit
            }

            System.out.println(in.readUTF());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
