package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

public class PowerOperator extends Operator {
    public PowerOperator(){
        op = "^";
    }

    @Override
    public int priority() {
        return 3;
    }

    @Override
    public Operand execute(Operand op1, Operand op2) {
        int power = (int) Math.pow((double) op1.getValue(), op2.getValue());
        Operand op3 = new Operand(power);
        return op3;
    }

}
