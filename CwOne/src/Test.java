import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Test {
    public static void main(String[] args) {
        //Scanner in =new Scanner(System.in);
        try {
            Scanner in=new Scanner(new File("input.txt"));

            int stringNumber=0;
            LiteralsArray myWords=new LiteralsArray();
            StringTokenizer str;
            String token;
            while(in.hasNextLine())
            {
                ++stringNumber;
                str=new StringTokenizer(in.nextLine()," \r\n");

                while (str.hasMoreTokens())
                {
                         token=str.nextToken();
                    System.out.println(token+"  :"+Literal.whatGroup(token));
                    myWords.add(new Literal(token,stringNumber));
                }

            }
            myWords.sort();
            myWords.show();

            myWords.showAll();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
