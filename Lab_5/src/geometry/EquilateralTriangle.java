package geometry;

import java.util.Comparator;
import java.util.Iterator;

//----------EQUILATERAL--TRIANGLE-----------
public class EquilateralTriangle extends Triangle implements Comparable<EquilateralTriangle>, Iterator<Double>, Iterable<Double> {
    protected int iterator_idx = 0;

    //------COMPARATOR--classes-------------
    public static class CompareAngles implements Comparator<EquilateralTriangle> {
        @Override
        public int compare(EquilateralTriangle o1, EquilateralTriangle o2) {
            if (o1.angle < o2.angle) return -1;
            if (o1.angle > o2.angle) return 1;
            return 0;
        }
    }

    public static class CompareFirstSides implements Comparator<EquilateralTriangle> {
        @Override
        public int compare(EquilateralTriangle o1, EquilateralTriangle o2) {
            if (o1.firstSide < o2.firstSide) return -1;
            if (o1.firstSide > o2.firstSide) return 1;
            return 0;
        }
    }

    public static class CompareSecondSides implements Comparator<EquilateralTriangle> {
        @Override
        public int compare(EquilateralTriangle o1, EquilateralTriangle o2) {
            if (o1.secondSide < o2.secondSide) return -1;
            if (o1.secondSide > o2.secondSide) return 1;
            return 0;
        }
    }
    //------COMPARATOR--classes-------------

    public EquilateralTriangle(double side) {
        super(60, side, side);
    }

    public EquilateralTriangle(String s) {
        super(60, Double.parseDouble(s), Double.parseDouble(s));
    }

    @Override
    public double area() {
        return (firstSide * secondSide * Math.sin(Math.toRadians(angle)) / 2);
    }

    @Override
    public double perimeter() {
        return 3 * firstSide;
    }

    //____Iterator & Iterable____

    @Override
    public Iterator<Double> iterator() {
        reset();
        return this;
    }

    @Override
    public boolean hasNext() {
        return iterator_idx < 3;
    }

    @Override
    public Double next() {
        switch (iterator_idx) {
            case 0:
                iterator_idx++;
                return angle;
            case 1:
                iterator_idx++;
                return firstSide;
            case 2:
                iterator_idx++;
                return secondSide;
            default:
                reset();
                return null;
        }
    }

    public void reset() {
        iterator_idx = 0;
    }
    //____Iterator____

    @Override
    public String toString() {
        return "EquilateralTriangle{" +
                "angle=" + angle + "\u00B0" +
                ", firstSide=" + String.format("%7.2f", firstSide) +
                ", secondSide=" + String.format("%7.2f", secondSide) +
                '}';
    }

    @Override
    //compareTo compares sides
    public int compareTo(EquilateralTriangle o) {
        if (this.firstSide < o.firstSide) return -1;
        if (this.firstSide > o.firstSide) return 1;
        return 0;
    }

    //setFirstSide & setSecondSide RELOAD (in equilateral triangles sides must be equal)
    @Override
    public void setFirstSide(double firstSide) {
        super.setFirstSide(firstSide);
        super.setSecondSide(firstSide);
    }

    @Override
    public void setSecondSide(double secondSide) {
        super.setFirstSide(secondSide);
        super.setSecondSide(secondSide);
    }
}
