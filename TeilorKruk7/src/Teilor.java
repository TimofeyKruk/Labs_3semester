public class Teilor {

    private double value;
    private int precision;

    Teilor(int precision, double value) {
        this.precision = precision;
        this.value = value;
    }

    public double solve() {
        //extra parameters
        double eps = 1 / Math.pow(10, precision + 1);
        double result = 1;
        double temp = (-1) * value * value / 2 / 3;
        int n = 4;

        //counting
        while (Math.abs(temp) >= eps) {
            result += temp;
            temp = temp * (-1) * value * value / n / (n + 1);
            n += 2;
        }
        return result;
    }
}
