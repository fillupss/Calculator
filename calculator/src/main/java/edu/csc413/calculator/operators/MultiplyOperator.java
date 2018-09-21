package edu.csc413.calculator.operators;
import edu.csc413.calculator.evaluator.Operand;

public class MultiplyOperator extends Operator{

    public MultiplyOperator(){
        op = "*";
    }

    @Override
    public int priority() {
        return 2;
    }

    @Override
    public Operand execute(Operand op1, Operand op2) {
        int pro = op1.getValue() * op2.getValue();
        Operand op3 = new Operand(pro);
        return op3;
    }

}
