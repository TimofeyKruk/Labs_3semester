package src4;

public class Test {
    public static void main(String[] args) {
        //  TIME
        System.out.println("---------Class TIME testing----------");
        System.out.println("Constructor + show: ");
        Time time1 = new Time();
        time1.showTime();
        System.out.println("Set + show: ");
        time1.setTime(5, 5, 31);
        time1.showTime();
        System.out.println("Constructor with param + show: ");
        Time time2 = new Time(3, 4, 13);
        time2.showTime();

        //  src.Date
        System.out.println("---------Class DATE testing----------");
        System.out.println("Constructor + show: ");
        Date date1 = new Date();
        date1.showDate();
        System.out.println("Set + show: ");
        date1.setDate(25, 9, 2018);
        date1.showDate();
        System.out.println("Constructor with param + show: ");
        Date date2 = new Date(6, 6, 1941);
        date2.showDate();
    }
}
