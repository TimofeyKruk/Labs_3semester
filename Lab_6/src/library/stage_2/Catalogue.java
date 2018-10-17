package library.stage_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Catalogue implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<Book> bookCatalogue = new ArrayList<Book>();

    //_____Methods______
    public Catalogue() {
    }

    public Catalogue(ArrayList<Book> bookCatalogue) {
        this.bookCatalogue = bookCatalogue;
    }

    public Catalogue(String fileName) {
        try {
            Scanner in = new Scanner(new File(fileName));

            while (in.hasNextLine()) {
                StringTokenizer str = new StringTokenizer(in.nextLine(), " ");
                String name = str.nextToken();
                String author = str.nextToken();

                for (int quantity = Integer.parseInt(str.nextToken()); quantity > 0; --quantity)
                    bookCatalogue.add(new Book(name, author, 0, true, null));
            }

            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Book> getBookCatalogue() {
        return bookCatalogue;
    }

    public void addBookToCatalogue(Book book) {
        bookCatalogue.add(book);
    }

    public int getBookCatalogueSize() {
        return bookCatalogue.size();
    }

    public void showBookCatalogue() {
        System.out.println("_______________" + AppLocale.getString(AppLocale.bookCatalogue) + ":_______________");
        for (Book book : bookCatalogue) {
            System.out.println(book.toString());
        }
        System.out.println("_____________________________________________\n");
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("_____" + AppLocale.getString(AppLocale.bookCatalogue) + ":_____\n");
        for (Book book : bookCatalogue) {
            result.append(book);
            result.append('\n');
        }
        return result.toString();
    }

    public Book getBookByIndex(int index) {
        return bookCatalogue.get(index);
    }

    public void deleteBookByIndex(int index) {
        bookCatalogue.remove(index);
    }
}
