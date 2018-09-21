package edu.csc413.calculator.evaluator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EvaluatorUI extends JFrame implements ActionListener {

    private TextField txField = new TextField();
    private Panel buttonPanel = new Panel();
    private static String sum = "";

    // total of 20 buttons on the calculator,
    // numbered from left to right, top to bottom
    // bText[] array contains the text for corresponding buttons
    private static final String[] bText = {
            "7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3",
            "*", "0", "^", "=", "/", "(", ")", "C", "CE"
    };

    /**
     * C  is for clear, clears entire expression
     * CE is for clear expression, clears last entry up until the last operator.
     */
    private Button[] buttons = new Button[bText.length];

    // this function will mainly be used if an operator button has been clicked in the previous event
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

    public static void main(String argv[]) {
        EvaluatorUI calc = new EvaluatorUI();
    }

    public EvaluatorUI() {
        setLayout(new BorderLayout());
        this.txField.setPreferredSize(new Dimension(600, 50));
        this.txField.setFont(new Font("Courier", Font.BOLD, 28));

        add(txField, BorderLayout.NORTH);
        txField.setEditable(false);

        add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.setLayout(new GridLayout(5, 4));

        //create 20 buttons with corresponding text in bText[] array
        Button bt;
        for (int i = 0; i < EvaluatorUI.bText.length; i++) {
            bt = new Button(bText[i]);
            bt.setFont(new Font("Courier", Font.BOLD, 28));
            buttons[i] = bt;
        }

        //add buttons to button panel
        for (int i = 0; i < EvaluatorUI.bText.length; i++) {
            buttonPanel.add(buttons[i]);
        }

        //set up buttons to listen for mouse input
        for (int i = 0; i < EvaluatorUI.bText.length; i++) {
            buttons[i].addActionListener(this);
        }

        setTitle("Calculator");
        setSize(400, 400);
        setLocationByPlatform(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent arg0) {
        Evaluator ev = new Evaluator();
        // arg0.getSource() will check which object has been interacted with
        // this if statement is to check the operands
        if (arg0.getSource() == buttons[0] || arg0.getSource() == buttons[1] || arg0.getSource() == buttons[2] || arg0.getSource() == buttons[4] || arg0.getSource() == buttons[5] || arg0.getSource() == buttons[6] ||
                arg0.getSource() == buttons[8] || arg0.getSource() == buttons[9] || arg0.getSource() == buttons[10] || arg0.getSource() == buttons[12]) {
            if(check(txField.getText())){
                if(check(sum.substring(sum.length()-1)) && txField.getText().equals("-")){
                }
                else{
                    this.txField.setText(""); // clear the text field in calc
                }
            }
            // arg0.getActionCommand() will return a string that corresponds to the object being interacted
            sum = sum + arg0.getActionCommand();
            // this line will allow us to continue to display strings continuously
            this.txField.setText(txField.getText() + arg0.getActionCommand());
        }
        // this if statement checks the operators
        else if (arg0.getSource() == buttons[3] || arg0.getSource() == buttons[7] ||arg0.getSource() == buttons[11] ||arg0.getSource() == buttons[13] ||
                arg0.getSource() == buttons[15] || arg0.getSource() == buttons[16] || arg0.getSource() == buttons[17]) {
            sum = sum + arg0.getActionCommand();
            // this line will only allow us to display one string then it will clear if another string is to display
            this.txField.setText(arg0.getActionCommand());
        }
        // this operator is to check for the "CE" operator
        else if (arg0.getSource() == buttons[19] || arg0.getSource() == buttons[18]) { // need to finish 'C'
            // clear the text field and expression
            this.txField.setText("");
            sum = "";

        }
        // this button is the "=" and will compute the expression that is represented in "sum"
        if (arg0.getSource() == buttons[14]) {
            int res = ev.eval(sum);
            // changes from int to string so it can represent in text field of calc.
            sum = String.valueOf(res);
            this.txField.setText(sum);
        }
    }
}



