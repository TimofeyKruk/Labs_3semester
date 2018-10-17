package library.stage_1;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Librarian implements Serializable {
    private static final long serialVersionUID = 1L;
    //      Fields
    private static int readingTerm = 30;
    protected Catalogue libraryCatalogue;
    protected Administrator libraryAdministrator = new Administrator();
    protected Date date;

    //      Methods
    public Librarian(Catalogue libraryCatalogue, Administrator libraryAdministrator, Date date) {
        this.libraryCatalogue = libraryCatalogue;
        this.libraryAdministrator = libraryAdministrator;
        this.date = date;
    }

    public Librarian(String fileName) {
        this.libraryCatalogue = new Catalogue(fileName);
        this.libraryAdministrator = new Administrator();
        this.date = new Date();
    }

    public static int getReadingTerm() {
        return readingTerm;
    }

    public static void setReadingTerm(int readingTerm) {
        Librarian.readingTerm = readingTerm;
    }

    public Catalogue getLibraryCatalogue() {
        return libraryCatalogue;
    }

    public void setLibraryCatalogue(Catalogue libraryCatalogue) {
        this.libraryCatalogue = libraryCatalogue;
    }

    public Administrator getLibraryAdministrator() {
        return libraryAdministrator;
    }

    public void setLibraryAdministrator(Administrator libraryAdministrator) {
        this.libraryAdministrator = libraryAdministrator;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void addDays(int days) {
//        Calendar cal=Calendar.getInstance();
//        cal.setTime(this.date);
//        cal.add(Calendar.DATE,days);
//        date.setTime(Calendar.DATE);
        date.setTime(date.getTime() + days * 24 * 60 * 60 * 1000);
        //decreasing daysLeft
        for (Book book : libraryCatalogue.getBookCatalogue()) {
            if (!book.isBookInLibrary()) {
                book.daysLeft -= days;
                if (book.getDaysLeft() <= 0) {
                    libraryAdministrator.addToBlackList(book.reader);
                }

            }

        }
    }

    public Book findBookByRequest(Request request) {
        for (Book book : libraryCatalogue.getBookCatalogue()) {
            if (book.getBookAuthor().equals(request.getBookAuthor()))
                if (book.getBookName().equals(request.getBookName()))
                    if (book.isBookInLibrary())
                        return book;
        }
        return null;
    }

    //******WORKING HERE******************
    public boolean giveBookToReader(Reader reader, Request request) {
        if (libraryAdministrator.isReaderInBlackList(reader)) return false;
        Book bookToGive = this.findBookByRequest(request);
        if (bookToGive != null) {
            bookToGive.isBookInLibrary = false;
            bookToGive.setDaysLeft(readingTerm);
            bookToGive.setReader(reader);
            reader.addBook(bookToGive);
            return true;
        }
        return false;
    }

    public void showBlackList() {
        System.out.println("++++++++++++ The BLACK list +++++++++++");
        for (Reader reader : this.getLibraryAdministrator().getBlackList()) {
            System.out.println(reader.toString());
        }
        System.out.println("++++++++++++++++++++++++++++++++++++++");

    }

    @Override
    public String toString() {
        return "******  Librarian   ******\n" +
                "libraryCatalogue =  " + libraryCatalogue.toString() +
                "\nlibraryAdministrator =   " + libraryAdministrator.toString() +
                "\ndate =    " + date +
                "\n**************************\n";
    }
}
