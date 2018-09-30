package src4;

public class Date {

    private int day;
    private int month;
    private int year;

    //Constructors
    public Date() {
        day = 0;
        month = 0;
        year = 0;
    }

    public Date(int d, int m, int y) {
        setDate(d, m, y);
    }

    //SET METHOD
    public void setDate(int d, int m, int y) {
        assert (d > 0 && d <= 31);
        assert (m > 0 && m <= 12);
        assert (year >= 0);
        day = d;
        month = m;
        year = y;
    }

    //SHOW METHOD
    public void showDate() {
        StringBuilder result = new StringBuilder();

        if (day < 10) result.append(0);
        result.append(day);
        result.append('.');
        if (month < 10) result.append(0);
        result.append(month);
        result.append('.');
        result.append(year % 100);

        System.out.println(result);
    }


}
