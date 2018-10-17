package library.stage_1;

import java.io.Serializable;

public class Reader implements Serializable {
    private static final long serialVersionUID = 1L;
    //_____Fields______
    protected String name;
    protected int mobileNumber;
    protected Catalogue readerBooks = new Catalogue();


    //_____Methods______
    public Reader() {
        this.name = null;
        this.mobileNumber = 0;
    }


    public Reader(String name, int mobileNumber) {
        this.name = name;
        this.mobileNumber = mobileNumber;
    }

    public void addBook(Book book) {
        readerBooks.addBookToCatalogue(book);
    }

    //createRequest
    public Request createRequest(String bookName, String bookAuthor) {
        return new Request(bookName, bookAuthor);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(int mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public String toString() {
        return "Reader{" +
                "name='" + name + '\'' +
                ", mobileNumber=" + mobileNumber + '}';
    }

    public void showReaderBooks() {
        System.out.println(this.toString());
        this.readerBooks.showBookCatalogue();
    }

    public boolean returnBookByIndex(int bookIndex) {
        Book book = this.readerBooks.getBookByIndex(bookIndex);
        if (book != null) {
            book.isBookInLibrary = true;
            book.setReader(null);
            book.setDaysLeft(0);
            this.readerBooks.deleteBookByIndex(bookIndex);
            return true;
        }
        return false;
    }
}
