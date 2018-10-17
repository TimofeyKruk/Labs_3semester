package library.stage_2;

import java.util.*;

public class AppLocale {
    private static final String strMsg = "Msg";
    private static Locale loc = Locale.getDefault();
    private static ResourceBundle res =
            ResourceBundle.getBundle(AppLocale.strMsg, AppLocale.loc);

    static Locale get() {
        return AppLocale.loc;
    }

    static void set(Locale loc) {
        AppLocale.loc = loc;
        res = ResourceBundle.getBundle(AppLocale.strMsg, AppLocale.loc);
    }

    static ResourceBundle getBundle() {
        return AppLocale.res;
    }

    static String getString(String key) {
        return AppLocale.res.getString(key);
    }

    // Resource keys:
    public static final String book = "Book";
    public static final String bookName = "bookName";
    public static final String bookAuthor = "bookAuthor";
    public static final String daysLeft = "daysLeft";
    public static final String isBookInLibrary = "isBookInLibrary";
    public static final String reader = "reader";
    public static final String blackList = "blackList";
    public static final String Librarian = "Librarian";
    public static final String Library = "Library";
    public static final String libraryCatalogue = "libraryCatalogue";
    public static final String libraryAdministrator = "libraryAdministrator";
    public static final String date = "date";
    public static final String name = "name";
    public static final String mobileNumber = "mobileNumber";
    public static final String Administrator = "Administrator";
    public static final String testingRead = "testingRead";
    public static final String bookCatalogue = "bookCatalogue";
}
