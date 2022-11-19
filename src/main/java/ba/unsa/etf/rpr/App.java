package ba.unsa.etf.rpr;

import java.util.Scanner;


/**
 * Class which contains main method and parses String from args paramether using evaluate method from ExpressionEvaluator class
 */
public class App 
{

    public static void main(String[] args) {
        if(args.length==0) {
            String function;
            System.out.println("NOTE \nPlease make sure that each expression is entered within \nparentheses and that the parentheses are perfectly balanced.");
            System.out.println("Enter a function: ");
            Scanner reader = new Scanner(System.in);
            function = reader.nextLine();
            ExpressionEvaluator al = new ExpressionEvaluator();
            try {
                System.out.println(al.evaluate(function));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        else {
            try {
                double result = (new ExpressionEvaluator()).evaluate(args[0]);
                System.out.println(result);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
