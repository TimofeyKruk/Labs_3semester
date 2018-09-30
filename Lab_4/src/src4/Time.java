package src4;

public class Time {
    private int hours;
    private int minutes;
    private int seconds;

    //Constructors
    public Time() {
        hours = 0;
        minutes = 0;
        seconds = 0;
    }

    public Time(int h, int m, int sec) {
        setTime(h, m, sec);
    }

    //SET METHOD
    public void setTime(int h, int m, int sec) {
        assert (h >= 0 && h < 24);
        assert (m >= 0 && m < 60);
        assert (sec >= 0 && sec < 60);
        hours = h;
        minutes = m;
        seconds = sec;
    }

    //SHOW METHOD
    public void showTime() {
        System.out.printf("%d : %d : %d\n", hours, minutes, seconds);
    }

}
