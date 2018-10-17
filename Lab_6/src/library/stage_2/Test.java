package library.stage_2;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        System.out.println("Enter your region(RU, BY, GB):");
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();

//        ResourceBundle resourceBundle=ResourceBundle.getBundle("Msg");
//        System.out.println(resourceBundle.getString("Book"));
//        Locale locale = new Locale("be","BY");
//        Locale.setDefault(locale);
//        AppLocale.set(locale);
//        System.out.println(AppLocale.getString(AppLocale.book));

        Locale locale;
        switch (s) {
            case "BY":
                locale = new Locale("be", "BY");
                Locale.setDefault(locale);
                AppLocale.set(locale);
                break;
            case "RU":
                locale = new Locale("ru", "RU");
                Locale.setDefault(locale);
                AppLocale.set(locale);
                break;
            case "GB":
                locale = new Locale("en", "GB");
                Locale.setDefault(locale);
                AppLocale.set(locale);
                break;
            default:
                System.out.println("Incorrect input! Locale is set as default(en_GB)!");
                locale = new Locale("en", "GB");
                Locale.setDefault(locale);
                AppLocale.set(locale);
                break;
        }

        System.out.println("* * * * * * * * * * * * * * * * * * * * * *");
        System.out.println(AppLocale.getString(AppLocale.Library));
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * \n");

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
            System.out.println(AppLocale.getString(AppLocale.testingRead));
            Catalogue readCatalogue = myConnector.read();
            readCatalogue.showBookCatalogue();

            //Testing librarian
            Librarian librarian = new Librarian("BookCatalogue.txt");
            System.out.println(librarian.toString());

            librarian.addDays(6);
            System.out.println(librarian.getCreationDate());

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
            System.err.println(e);
        }
    }


}
