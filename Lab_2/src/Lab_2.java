import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Lab_2 {
    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(new File("my_text.txt"));
            String s;
            int i;
            int k;
            Stack<Character> operation_stack = new Stack<>();
            Stack<Double> number_stack = new Stack<>();
            int start,end;
            while(in.hasNextLine())
            {
                s=in.nextLine();
                System.out.print(s+" = ");
                s='('+s+')';
                k=0;

                //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                while(k < s.length())
                {
                    Character op='s';
                    start=k;
                    end=k;
                    for(; k<s.length();)
                    {
                        if(Character.isDigit(s.charAt(k)))
                        {
                            end++;
                            k++;
                            op='s';
                        }
                        else
                        {
                            op=s.charAt(k);
                            k++;
                            break;
                        }

                    }
                    //добавляем число в стек
                    if(start!=end)
                    {
                        number_stack.push(Double.parseDouble(s.substring(start,end)));
                    }
                    //***********FUNCTION********************
                    if(op!='s')
                    {
                        Character prev;
                        if(!operation_stack.empty())
                        {
                            prev = operation_stack.pop();
                            if(compare_operations(prev,op))
                            {
                                operation_stack.push(prev);
                                operation_stack.push(op);
                            }
                            else
                            {
                                double a,b;
                                b=number_stack.pop();
                                a=number_stack.pop();

                                number_stack.push(count(a,b,prev));

                                if(op==')')
                                {
                                    prev=operation_stack.pop();
                                    while(prev!='(')
                                    {
                                        b=number_stack.pop();
                                        a=number_stack.pop();
                                        number_stack.push(count(a,b,prev));
                                        prev=operation_stack.pop();
                                    }
                                }
                                else
                                    operation_stack.push(op);
                            }
                        }
                        else
                            operation_stack.push(op);
                    }
                    //***********FUNCTION********************

                }
                //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            //ВЫВОД РЕЗУЛЬТАТА
                System.out.println(number_stack.pop());

            }
        in.close();
        }

        catch (FileNotFoundException e ) {
            e.printStackTrace();
        }

    }

    //*******************************************
    static boolean compare_operations(Character a, Character b)
    {
        return ((a=='+' || a=='-')&&(b=='*' || b=='/'))  ||  ( b=='(' ) ||  ( a=='(' );
    }
    static double count(double a, double b, Character c)
    {
        double result=0;
        switch (c) {
            case '+': result=a+b;  break;
            case '-': result=a-b;  break;
            case '*': result=a*b;  break;
            case '/': if(b!=0)     result=a/b;     break;
            default: break;
        }
        return result;
    }
}

