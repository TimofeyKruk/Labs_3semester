package library.stage_1;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * Library * * * * * * * * * * * * * * * * * * * * * * \n");

//        ArrayList<String> mylist=new ArrayList<String>();
//        mylist.add("lalala");
//        System.out.println(mylist.get(0));

        try {
            Catalogue myCatalogue = new Catalogue("BookCatalogue.txt");
            myCatalogue.showBookCatalogue();

            //Connector.write
            Connector myConnector = new Connector("output.dat");
            myConnector.write(myCatalogue);

            //Connector.read
            System.out.println("TESTING READ:");
            Catalogue readedCatalogue = myConnector.read();
            readedCatalogue.showBookCatalogue();

            //Testing librarian
            Librarian librarian = new Librarian("BookCatalogue.txt");
            System.out.println(librarian.toString());

            librarian.addDays(6);
            System.out.println(librarian.getDate());

            Reader reader = new Reader("Timofey Kruk", 1680508);
            Request myRequest = reader.createRequest("Экономика", "Никитенко.А.Ф.");
            //System.out.println(librarian.findBookByRequest(myRequest));
            librarian.giveBookToReader(reader, myRequest);
            System.out.println(reader.toString());
            librarian.libraryCatalogue.showBookCatalogue();
            reader.showReaderBooks();
            librarian.addDays(20);
            librarian.getLibraryCatalogue().showBookCatalogue();
            reader.showReaderBooks();

            reader.returnBookByIndex(0);
            reader.showReaderBooks();

            System.out.println(librarian.getLibraryCatalogue().toString());

            librarian.showBlackList();

            myRequest = reader.createRequest("МатАнализ", "Наумович.А.С.");
            librarian.giveBookToReader(reader, myRequest);
            myRequest = reader.createRequest("Социология", "Шкурова.С.В.");
            librarian.giveBookToReader(reader, myRequest);

            System.out.println();
            reader.showReaderBooks();
            librarian.getLibraryCatalogue().showBookCatalogue();

            librarian.addDays(35);
            reader.showReaderBooks();
            librarian.getLibraryCatalogue().showBookCatalogue();
            librarian.showBlackList();

        } catch (Exception e) {
            System.err.println("ERROR! " + e);
        }
    }


}
