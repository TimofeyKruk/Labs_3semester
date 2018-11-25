import java.util.Comparator;

public class CompareByHumidity implements Comparator<Pair> {
    @Override
    public int compare(Pair o1, Pair o2) {
        double x1=Double.parseDouble(o1.getHumidity());
        double x2=Double.parseDouble(o2.getHumidity());
        if(x1<x2) return -1;
        if(x1==x2) return 0;
        if(x1>x2) return 1;
        return 0;
    }
}
