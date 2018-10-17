package library.stage_1;

import java.io.*;
import java.util.ArrayList;

public class Connector {
    private String fileName;

    //      Methods
    public Connector(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void write(Catalogue libraryCatalogue) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeInt(libraryCatalogue.getBookCatalogueSize());
            for (Book book : libraryCatalogue.getBookCatalogue()) {
                oos.writeObject(book);
            }
            oos.flush();
        }
    }

    public Catalogue read() throws IOException, ClassNotFoundException {
        FileInputStream fin = new FileInputStream(fileName);
        try (ObjectInputStream ois = new ObjectInputStream(fin)) {
            int size = ois.readInt();
            Catalogue result = new Catalogue();

            int counter = 1;
            while (counter <= size) {
                result.addBookToCatalogue((Book) ois.readObject());
                counter++;
            }
            return result;
        }
    }


}
