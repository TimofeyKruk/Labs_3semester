import java.io.*;
import java.text.ParseException;
import java.util.Scanner;

public class FileSupport {
    private String fileNameDat;

    //  Methods

    public FileSupport(String fileNameDat) {
        this.fileNameDat = fileNameDat;
    }

    public String getFileNameDat() {
        return fileNameDat;
    }

    public void setFileNameDat(String fileNameDat) {
        this.fileNameDat = fileNameDat;
    }

    public void deleteFile() {
        File file = new File(fileNameDat);
        file.delete();
    }

    public void appendToFile(Serializable object) throws FileNotFoundException, IOException {
        try (RandomAccessFile raf = new RandomAccessFile(this.fileNameDat, "rw")) {
            Buffer.writeObject(raf, object);
        }
    }

    public void printFile() throws IOException, ClassNotFoundException {
        System.out.println("_____   File: " + fileNameDat + "   _____");
        try (RandomAccessFile raf = new RandomAccessFile(this.fileNameDat, "r")) {
            long position = 0;
            while ((position = raf.getFilePointer()) < raf.length()) {
                UtilityBill bill = (UtilityBill) Buffer.readObject(raf, position);
                System.out.println("* position:  " + bill.toString());
            }
            if (position == 0) System.out.println("The file is empty!");
        }
        System.out.println("_____________________________________");
    }

    public void appendToFileFromTXTFile(String fileNameTXT) throws IOException, FileNotFoundException, ParseException {
        Scanner in = new Scanner(new File(fileNameTXT));

        while (in.hasNextLine()) {
            String str = in.nextLine();
            appendToFile(new UtilityBill(str));
        }
    }
}
