package com.company;

public class Main {
    public static void main(String[] argc){
        double x = Double.parseDouble(argc[0]);
        int eps_n = Integer.parseInt(argc[1]);
        double true_result = Math.exp(x);
        System.out.printf("Result by Math: %." + eps_n +"f\n", true_result);

        double eps = Math.pow(10, -eps_n);
        double a = 1;
        double res = 1;
        for(int n = 1; Math.abs(a) >= eps; n++){
            a *= x / n;
            res += a;
        }
        System.out.printf("\nResult by Taylor: %." + eps_n + "f\n", res);
    }

}