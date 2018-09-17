/*
Kruk Timofey 10 group
variant 7
sin(x)/x

x  args[0]  (-inf;+inf)
k  args[1]  (1;+inf)
*/

public class Main
{
    public static void main(String[] args)
    {
        //input
        double x;
        x=5.6;
        //x = Double.parseDouble(args[0]);
        int k;
        //k=Integer.parseInt(args[1]);
        k=5;
        //Checking for correctness
        if(k<=1)
        {
            System.err.println("Parameter k can't be <=1, your k= "+k+"\n");
            System.exit(1);
        }
        if(x==0)
        {
            System.err.println("Parameter x can't be equal to 0! Division by 0!\n");
            System.exit(1);
        }

        Teilor teilor = new Teilor(k, x);

        //output
        System.out.println("----------------------");
        String format="%12."+k+"f\n";
        System.out.printf("By Teilor:"+format,teilor.solve());
        System.out.printf("By Math:  "+format,Math.sin(x)/x);
        System.out.println("----------------------");

        //exit
        System.exit(0);
    }
}
