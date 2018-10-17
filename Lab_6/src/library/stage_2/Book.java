package library.stage_2;

import java.io.Serializable;

public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    //_____Fields______
    protected String bookName;
    protected String bookAuthor;
    protected int daysLeft;
    protected boolean isBookInLibrary;
    protected Reader reader;
    private int bookID;
    private static int counter = 0;

    //_____Methods______


    public Book(String bookName, String bookAuthor, int daysLeft, boolean isBookInLibrary, Reader reader) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.daysLeft = daysLeft;
        this.isBookInLibrary = isBookInLibrary;
        this.reader = reader;
        this.bookID = counter++;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public int getDaysLeft() {
        return daysLeft;
    }

    public void setDaysLeft(int daysLeft) {
        this.daysLeft = daysLeft;
    }

    public int getBookID() {
        return bookID;
    }

    public boolean isBookInLibrary() {
        return isBookInLibrary;
    }

    public void setBookInLibrary(boolean bookInLibrary) {
        isBookInLibrary = bookInLibrary;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    @Override
    public String toString() {
        return AppLocale.getString(AppLocale.book) + "{ ID= " + bookID +
                ", " + AppLocale.getString(AppLocale.bookName) + "='" + bookName + '\'' +
                ", " + AppLocale.getString(AppLocale.bookAuthor) + "='" + bookAuthor + '\'' +
                ", " + AppLocale.getString(AppLocale.daysLeft) + "=" + daysLeft +
                ", " + AppLocale.getString(AppLocale.isBookInLibrary) + "=" + isBookInLibrary +
                ", " + AppLocale.getString(AppLocale.reader) + "=" + reader + '}';
    }

}
