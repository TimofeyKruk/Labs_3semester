package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.print("Enter n: ");
        Scanner in= new Scanner(System.in);
        int n;
        while(true)
        {
            n=in.nextInt();
            if(n>0)
                break;
            else
                System.err.println("Invalid n, it must be > 0! Try again");
        }

        Random rnd=new Random();
        rnd.setSeed(System.currentTimeMillis());

        System.out.println("Generated matrix:\n--------------------------------------");
        double[] average=new double [n];
        double[][] array = new double[n][n];
        for(int i=0; i < n; ++i)
        {
            average[i]=0;
            for(int j = 0; j < n; ++j)
            {
                array[i][j]=rnd.nextInt(n);
                System.out.printf("%7d",(int)array[i][j]);
                average[i]+=array[i][j];
            }

            average[i]=average[i]/n;
            System.out.printf(" |%8.2f\n",average[i]);

        }

        System.out.println("Changed matrix:\n--------------------------------------");
        for(int i=0; i < n; ++i)
        {
            for(int j = 0; j < n; ++j)
            {
                array[i][j]-=average[i];
                System.out.printf("%7.2f",array[i][j]);
            }
            System.out.println();
        }


    }
}
