import java.security.KeyPair;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class LiteralsArray {
    ArrayList<Literal> allWords = new ArrayList<>();

    public LiteralsArray(ArrayList<Literal> allWords) {
        this.allWords = allWords;
    }

    public LiteralsArray() {
    }

    public void add(Literal literal) {
        allWords.add(literal);
    }

    public void show() {
        for (Literal literal : allWords)
            System.out.println(literal.toString());
    }

    class ComparatorByGroup implements Comparator<Literal> {
        @Override
        public int compare(Literal o1, Literal o2) {
            if (o1.getGroupNumber() != o2.getGroupNumber())
                return o1.getGroupNumber() - o2.getGroupNumber();
            if (o1.getGroupNumber() == 1)
                return Integer.parseInt(o2.getLiteral()) - Integer.parseInt(o1.getLiteral());
            if (o1.getGroupNumber() == 2)
                return Integer.parseInt(o2.getLiteral(), 16) - Integer.parseInt(o1.getLiteral(), 16);
            if (o1.getGroupNumber() == 3 || o1.getGroupNumber() == 4) {
                if (Double.parseDouble(o1.getLiteral()) == Double.parseDouble(o2.getLiteral()))
                    return 0;
                if (Double.parseDouble(o1.getLiteral()) < Double.parseDouble(o2.getLiteral()))
                    return -1;
                if (Double.parseDouble(o1.getLiteral()) > Double.parseDouble(o2.getLiteral()))
                    return 1;
            }
            //for text
            return o2.getLiteral().compareTo(o1.getLiteral());
        }
    }

    public void sort() {
        allWords.sort(new ComparatorByGroup());
    }

    public void showAll() {
        System.out.println("____ Groups (1)-(5) _____");
        for (Literal literal:allWords)
            System.out.println(literal.toString());
        System.out.println("__________________");
    }

}
