import javax.sound.midi.Soundbank;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerDentist {
    public static Date currentDate = new Date();
    private static int SERVER_PORT = 4500;
    private static int MAX_CLIENTS = 15;
    public static int MAX_APPOINTMENTS = 15;
    //******* DB *******
    public static DentalDB dentalDB = new DentalDB();


    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("#####  Server is working! Waiting for clients!  #####");

            //Current Date
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy  HH:mm");
            System.out.println("The current date: " + dateFormat.format(currentDate));
            System.out.println("-----------------------------------------------------------\n");


            // CREATING NEW THREADS FOR CLIENTS
            ExecutorService executorService = Executors.newFixedThreadPool(MAX_CLIENTS);


            while (!serverSocket.isClosed()) {
                Socket client = serverSocket.accept();
                executorService.execute(new CustomerThread(client));
                System.out.println("A new client connected! ");


            }


            executorService.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
