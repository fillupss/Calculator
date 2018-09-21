package edu.csc413.calculator.operators;
import edu.csc413.calculator.evaluator.Operand;

public class SubtractOperator extends Operator {
    public SubtractOperator(){
        op = "-";
    }

    @Override
    public Operand execute(Operand op1, Operand op2) {
        int diff = op1.getValue() - op2.getValue();
        Operand op3 = new Operand(diff);
        return op3;
    }

    @Override
    public int priority() {
        return 1;
    }

}
