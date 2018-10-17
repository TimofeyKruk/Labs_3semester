package library.stage_1;

import java.io.Serializable;

public class Request implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String bookName;
    protected String bookAuthor;

    //  Methods
    public Request() {
        this.bookName = null;
        this.bookAuthor = null;
    }

    public Request(String bookName, String bookAuthor) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
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

}
