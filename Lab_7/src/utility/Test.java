package utility;

import java.io.File;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {

        try {
            System.out.println("+++     UTILITY   BILL    +++\n");

            //  Creating .dat file and writing some objects from Input.txt
            FileSupport datFile = new FileSupport("UtilityBill.dat");
            datFile.appendToFileFromTXTFile("Input.txt");


            System.out.println("___What do you want to do?___");
            System.out.println("1. Append bill to file.");
            System.out.println("2. Print file.");
            System.out.println("3. Delete file.");
            System.out.println("_____________________________");
            System.out.print("Your choice: ");

            Scanner input = new Scanner(System.in);
            int userChoice = input.nextInt();
            System.out.println();

            switch (userChoice) {
                case 1:
                    System.out.println("Enter Utility Bill:  ");
                    Scanner in=new Scanner(System.in);
                    String s = in.nextLine();
                    datFile.appendToFile(new UtilityBill(s));

                    System.out.println("After appending, .dat file: ");
                    datFile.printFile();
                    break;
                case 2:
                    datFile.printFile();
                    break;
                case 3:
                    datFile.deleteFile();
                    System.out.println("File was deleted!");
                    break;
                default:
                    System.out.println("Incorrect input!");
                    break;
            }


            System.out.println("\n\nThe programme worked correctly!");
        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }

    }
}
