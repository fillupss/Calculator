package edu.csc413.calculator.evaluator;

import edu.csc413.calculator.operators.Operator;
import edu.csc413.calculator.operators.SubtractOperator;

import java.util.Stack;
import java.util.StringTokenizer;


public class Evaluator {
  private Stack<Operand> operandStack;
  private Stack<Operator> operatorStack;
  private StringTokenizer tokenizer;
  private static final String DELIMITERS = "+-*^/()";


  public Evaluator() {
    operandStack = new Stack<>();
    operatorStack = new Stack<>();
  }

  // this method will calculate two operands given an operator when necessary
  public void process(){
    Operand second = operandStack.pop();
    Operand first = operandStack.pop();
    Operator sign = operatorStack.pop();
    Operand result = sign.execute(first, second);
    // a subtraction is equivalent to adding a negative number or vice versa for addition
    if(!operatorStack.empty() && operatorStack.peek().getSign().equals("-")){
      operatorStack.pop();
      operatorStack.push(Operator.tokenCheck("+"));
      result.setNegative();
    }
    operandStack.push(result);
  }

  public int eval(String expression) {
    String token;

    // The 3rd argument is true to indicate that the delimiters should be used
    // as tokens, too. But, we'll need to remember to filter out spaces.
    this.tokenizer = new StringTokenizer(expression, DELIMITERS, true);

    // initialize operator stack - necessary with operator priority schema
    // the priority of any operator in the operator stack other than
    // the usual mathematical operators - "+-*/" - should be less than the priority
    // of the usual operators


    while (this.tokenizer.hasMoreTokens()) {
      // filter out spaces
      if (!(token = this.tokenizer.nextToken()).equals(" ")) {
        // check if token is an operand
        if (Operand.check(token)) {
          Operand a = new Operand(token);
          //this will set operand to negative


          // uncomment if statement if u want to try negative operands (only tested few expressions)
          // from lines 61 - 84
          /*if(operatorStack.size()>0){
            if(operatorStack.peek().getSign().equals("-") && (operatorStack.size() > operandStack.size())){
              Operator temp = operatorStack.pop();
              if(operatorStack.isEmpty()){
                a.setNegative();
              }
              if(operatorStack.size() - operandStack.size() == 0){
                operatorStack.push(temp);
              }
              else if(operatorStack.peek().getSign().equals("(")){
                Operator temp1 = operatorStack.pop();
                if(operatorStack.isEmpty() || (operatorStack.peek().getSign().equals("(") && operatorStack.size() > operandStack.size())){
                  a.setNegative();
                  operatorStack.push(temp1);
                }
                else{
                  operatorStack.push(temp1);
                  operatorStack.push(temp);
                }
              }
              //a.setNegative();
              //operatorStack.pop();
            }
          }*/
          // uncomment ends here


          operandStack.push(a);
        } else {
          if (!Operator.check(token)) {
            System.out.println("*****invalid token******");
            throw new RuntimeException("*****invalid token******");
          }


          // TODO Operator is abstract - these two lines will need to be fixed:
          // The Operator class should contain an instance of a HashMap,
          // and values will be instances of the Operators.  See Operator class
          // skeleton for an example.

          // This is only dealing with priorities NOT the total calculation

          // need to add one more algorithm where parantheses acts as a multiplier when it is being scanned

          if(!token.equals(")")) {
            Operator newOperator = Operator.tokenCheck(token);
            if (!operatorStack.empty()) {
              // we want to check if the top of stack is a parenthesis which has a priority of 4
              // if so then we would not process
              // if the top of stack is not a parenthesis then we can proceed to calculate
              // assuming the conditions are met
              if ((operatorStack.peek().priority() >= newOperator.priority()) && operatorStack.peek().priority() != 4) {
                process();
              }
            }
            operatorStack.push(newOperator);
          }
          if (token.equals(")")) {
            // this loop is to calculate the expression that is inside the parenthesis
            while (operatorStack.peek().priority() != 4) {
              process();
            }
            // if the operator stack is less then the operand stack, it is safe to assume that "(" acts as a multiplier also
            // if condition is not met then that "(" will act as an enclosure only
            // ex. 3+(4*3) => 3 operators and 3 operands, "(" is in operator stack
            // ex. 3(4*3) => 2 operators and 3 operands

            // lines 128 - 132 is the part where the parantheses acts as a multipler
            /*if (operatorStack.size() < operandStack.size()) {
              process();
            }
            else {*/
              operatorStack.pop();
            //}
          }

        }
      }
    }
    // Control gets here when we've picked up all of the tokens; you must add
    // code to complete the evaluation - consider how the code given here
    // will evaluate the expression 1+2*3
    // When we have no more tokens to scan, the operand stack will contain 1 2
    // and the operator stack will have + * with 2 and * on the top;
    // In order to complete the evaluation we must empty the stacks (except
    // the init operator on the operator stack); that is, we should keep
    // evaluating the operator stack until it only contains the init operator;
    // Suggestion: create a method that takes an operator as argument and
    // then executes the while loop.

    // after working with priorities, we can just keep repeating process() until operator stack is out
    // we check the condition of operator stack since this stack typically has less elements inside then operands
    while(!operatorStack.empty()){
      process();

    }
    return operandStack.pop().getValue();
  }

}
