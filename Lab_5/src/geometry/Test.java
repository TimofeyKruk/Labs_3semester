package geometry;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());

        System.out.println("_____Triangles_____");
        EquilateralTriangle[] triangles = new EquilateralTriangle[8];
        for (int i = 0; i < 7; ++i) {
            triangles[i] = new EquilateralTriangle(rnd.nextDouble() * 40);
            System.out.println(triangles[i].toString());
        }
        System.out.println();

        //Testing setFirstSide
        System.out.println("_____triangles[1].setFirstSide(5)_____");
        triangles[1].setFirstSide(5);
        System.out.println(triangles[1]);

        //Testing constructor(String)
        triangles[7] = new EquilateralTriangle("255");
        System.out.println();

        System.out.println("_____Sorting by angles_____");
        Arrays.sort(triangles, new EquilateralTriangle.CompareAngles());
        //Output of triangles[]
        for (int i = 0; i < 8; ++i)
            System.out.println(triangles[i].toString());
        System.out.println("\n");

        //Testing compareTo
        System.out.println("_____compareTo_____");
        System.out.println(triangles[5].toString());
        System.out.println(triangles[6].toString());
        if (triangles[5].compareTo(triangles[6]) == -1)
            System.out.println("First is smaller!");
        else if (triangles[5].compareTo(triangles[6]) == 1)
            System.out.println("First is bigger!");
        else
            System.out.println("They are equal!");
        System.out.println();

        System.out.println("_____Sorting by firstSides_____");
        Arrays.sort(triangles, new EquilateralTriangle.CompareFirstSides());
        //Output of triangles[]
        for (int i = 0; i < 8; ++i)
            System.out.println(triangles[i].toString());
        System.out.println("\n");


        System.out.println("_____Area and perimeter_____");
        System.out.println("What to count: " + triangles[0].toString());
        System.out.print("Area: ");
        System.out.printf("%7.2f\n", triangles[0].area());
        System.out.print("Perimeter: ");
        System.out.printf("%7.2f", triangles[0].perimeter());
        System.out.println("\n");

        //Testing Iterator
        System.out.println("_____Iterator: next, hasNext_____");
        int count = 1;
        while (triangles[2].hasNext()) {
            System.out.printf("#%d field: %6.2f\n", count++, triangles[2].next());
        }

        System.out.println();
        System.out.println("_____Iterable: foreach_____");
        for (EquilateralTriangle triangle:triangles)
        {
            for(Double side : triangle) {
                System.out.println(side);
            }
        }
    }
}
