package edu.csc413.calculator.operators;



import edu.csc413.calculator.evaluator.Operand;

import java.util.HashMap;

public abstract class Operator {
    // The Operator class should contain an instance of a HashMap
    // This map will use keys as the tokens we're interested in,
    // and values will be instances of the Operators.
    // ALL subclasses of operator MUST be in their own file.
    // Example:
    // Where does this declaration go? What should its access level be?
    // Class or instance variable? Is this the right declaration?
    protected String op;
    private static HashMap<String, Operator> operators;

    // preload the hash map so that it can be accessed
    static {
        operators = new HashMap<>();
        operators.put("+", new AddOperator());
        operators.put("-", new SubtractOperator());
        operators.put("*", new MultiplyOperator());
        operators.put("/", new DivideOperator());
        operators.put("^", new PowerOperator());
        operators.put("(", new Parantheses());

    }

    public abstract int priority();
    public abstract Operand execute(Operand op1, Operand op2 );

    public String getSign(){
        return op;
    }


    /**
     * determines if a given token is a valid operator.
     * please do your best to avoid static checks
     */
    public static boolean check( String token ) {
        String delimiters = "+-*^/()";
        if(token.length()==1){
            for(int i = 0; i < delimiters.length(); i++){
                if(token.charAt(0) - delimiters.charAt(i) == 0){
                    return true;
                }
            }
        }
        return false;
    }

    // this method is an accessor for other classes/software to get the operator from Hash map
    // need to be static since it would be method for the class instead of method for the Object
    // do not need to create an Operator object to call static methods
    public static Operator tokenCheck(String token){

        return operators.get(token);
    }



}

