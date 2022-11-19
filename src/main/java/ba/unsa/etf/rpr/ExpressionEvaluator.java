package ba.unsa.etf.rpr;

import java.text.DecimalFormat;
import java.util.Stack;

/**
 *  Starting with public class ExpressionEvaluator
 */
public class ExpressionEvaluator {


    private Stack<String> operators;
    private Stack<Double> values;

    public ExpressionEvaluator() {
        this.operators = new Stack<String>();
        this.values = new Stack<Double>();
    }

    /**
     * Method that calculates the given String
     * @param function
     * @return
     * @throws Exception
     */
    public Double evaluate(String function) throws Exception {
        function+=" ";
        String toEvaluate = "";
        for(int i=0; i<function.length(); i++) {
            boolean done = false;
            if(function.charAt(i) != ' ') {
                toEvaluate += function.charAt(i);
            } else {
                done = true;
            }
            if(done) {
                if(check(toEvaluate).equals("double")) {
                    this.values.push(Double.parseDouble(toEvaluate));
                } else if (check(toEvaluate).equals("operator")) {
                    this.operators.push(toEvaluate);
                } else if(check(toEvaluate).equals("execute")) {
                    double value1 = this.values.pop();
                    double value2 = this.values.pop();
                    double result = 0;
                    String operator = this.operators.pop();
                    switch(operator) {
                        case "+":
                            result = value2 + value1;
                            break;
                        case "-":
                            result = value2 - value1;
                            break;
                        case "*":
                            result = value2 * value1;
                            break;
                        case "/":
                            result = value2 / value1;
                            break;
                        case "%":
                            result = value2 % value1;
                            break;
                        default:
                            break;
                    }
                    this.values.push(result);
                } else {
                    toEvaluate = "";
                    continue;
                }
                toEvaluate = "";
            }
        }
        DecimalFormat df = new DecimalFormat("###.##");
        Double broj = Double.parseDouble(df.format(this.values.pop()));
        return broj;
    }

    /**
     * method used in evalute method
     * @param character
     * @return
     * @throws Exception
     */
    public String check(String character) throws Exception {
        if(character.matches("(([0-9]+)\\.([0-9]+))|([0-9]+)")) {
            return "double";
        } else if (character.matches("[%|*|+|[-]|/]")){
            return "operator";
        } else if (character.matches("[)]")){
            return "execute";
        } else if (character.matches("[(]")){
            return "";
        } else {
            throw new RuntimeException("Function not entered correctly");
        }
    }


}




