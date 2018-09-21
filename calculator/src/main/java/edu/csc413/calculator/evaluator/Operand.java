package edu.csc413.calculator.evaluator;
/**
 * Operand class used to represent an operand
 * in a valid mathmetical expression.
 */
public class Operand {

  private int num;

  /**
   * construct operand from string token.
   */
  public Operand( String token ) {
    try{
      num = Integer.parseInt(token);
    }catch(NumberFormatException e){
      System.out.println(e.getMessage());
    }
  }
  /**
   * construct operand from integer
   */
  public Operand( int value ) {

    num = value;
  }
  /**
   * return value of opernad
   */
  public int getValue() {

    return this.num;
  }

  public void setNegative(){
    this.num = num*(-1);
  }

  /**
   * Check to see if given token is a valid
   * operand.
   */
  public static boolean check( String token ) {
    char c;
    if(!token.equals(null)){
      // go through the token character by character to see if any character cannot represent as an integer
      for(int i = 0; i < token.length(); i++){
        c = token.charAt(i);
        if(c < '0' || c > '9'){
          return false;
        }
      }
    }
    return true;

  }
}
