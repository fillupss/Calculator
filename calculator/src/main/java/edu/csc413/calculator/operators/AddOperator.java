package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

public class AddOperator extends Operator {

    public AddOperator(){
        op = "+";
    }

    public int priority(){
        return 1;
    }

    public Operand execute(Operand op1, Operand op2){
        int sum = op1.getValue() + op2.getValue();
        Operand op3 = new Operand(sum);
        return op3;
    }



}
