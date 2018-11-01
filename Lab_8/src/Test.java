import java.io.File;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {

        try {
            System.out.println("+++     UTILITY   BILL    +++\n");

            //  Creating .dat file and writing some objects from Input.txt
            FileSupport datFile = new FileSupport("UtilityBill.dat");
            datFile.deleteFile();
            datFile.appendToFileFromTXTFile("Input.txt");
            datFile.printFile();

            //  MAP DEMONSTRATION
            MapUtility billMap = new MapUtility(datFile);
            billMap.showMap();


            //Testing readByKey
            System.out.println("*** Reading Bill by key: 13");
            System.out.println(billMap.readByKey(13));

            //Testing removeByKey
            billMap.removeByKey(3);
            System.out.println("\n*** Remove Bill by key:  3");
            billMap.showMap();

            datFile.printFile();

            //OutputAscending
            billMap.showObjectsAscending();
            //OutputDescending
            billMap.showObjectsDescending();

            //bigger than key 13
            billMap.showObjectBiggerThanKey(13);
            //bigger than key 33
            billMap.showObjectBiggerThanKey(33);
            //bigger than key 50
            billMap.showObjectBiggerThanKey(50);

            //Smaller than 10
            billMap.showObjectSmallerThanKey(10);
            //Smaller than 7
            billMap.showObjectSmallerThanKey(7);

            System.out.println("\n\nThe programme worked correctly!");
        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }

    }
}
