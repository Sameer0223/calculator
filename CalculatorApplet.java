import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;


public class CalculatorApplet extends Applet implements ActionListener {
    TextField display;
    Button[] numButtons = new Button[10];
    Button addButton, subButton, mulButton, divButton, eqButton, clrButton, decButton,sqrt,pow;
    String operator = "";
    double num1, num2, result;

   
    public void init() {
        setLayout(new BorderLayout());
        // Set the size of the applet
        setSize(500, 500); 

        display = new TextField();
        display.setEditable(false);
        // Set preferred size for the text field
        display.setPreferredSize(new Dimension(400, 50)); 
        // Set font size for the text field
        display.setFont(new Font("SansSerif", Font.PLAIN, 24)); 
        add(display, BorderLayout.NORTH);

        Panel panel = new Panel();
        // Use GridBagLayout
        panel.setLayout(new GridBagLayout()); 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        // Add gaps between buttons
        gbc.insets = new Insets(5, 5, 5, 5); 

        for (int i = 0; i < 10; i++) {
            numButtons[i] = new Button(String.valueOf(i));
            numButtons[i].addActionListener(this);
        }

        decButton = new Button(".");
        addButton = new Button("+");
        subButton = new Button("-");
        mulButton = new Button("*");
        divButton = new Button("/");
        eqButton = new Button("=");
        sqrt = new Button("sqrt");
        pow = new Button("pow");
        clrButton = new Button("C");

        decButton.addActionListener(this);
        addButton.addActionListener(this);
        subButton.addActionListener(this);
        mulButton.addActionListener(this);
        divButton.addActionListener(this);
        eqButton.addActionListener(this);
        clrButton.addActionListener(this);
        sqrt.addActionListener(this);
        pow.addActionListener(this);

        // Add buttons to the panel with constraints
        gbc.weightx = 0.75;
        gbc.weighty = 1.0;

        addComponent(panel, numButtons[7], gbc, 0, 0);
        addComponent(panel, numButtons[8], gbc, 1, 0);
        addComponent(panel, numButtons[9], gbc, 2, 0);
        addComponent(panel, addButton,     gbc, 3, 0);
        addComponent(panel, numButtons[4], gbc, 0, 1);
        addComponent(panel, numButtons[5], gbc, 1, 1);
        addComponent(panel, numButtons[6], gbc, 2, 1);
        addComponent(panel, subButton,     gbc, 3, 1);
        addComponent(panel, numButtons[1], gbc, 0, 2);
        addComponent(panel, numButtons[2], gbc, 1, 2);
        addComponent(panel, numButtons[3], gbc, 2, 2);
        addComponent(panel, mulButton,     gbc, 3, 2);
        addComponent(panel, decButton,     gbc, 0, 3);
        addComponent(panel, numButtons[0], gbc, 1, 3);
        addComponent(panel, eqButton,      gbc, 2, 3);
        addComponent(panel, divButton,     gbc, 3, 3);
        addComponent(panel, sqrt,          gbc, 0, 4);
        addComponent(panel, pow,           gbc, 1, 4);
        // Add clear button spanning the entire row
        
        // Make the clear button span 2 columns
        gbc.gridwidth = 2; 
        addComponent(panel, clrButton, gbc, 2, 4);

        add(panel, BorderLayout.CENTER);
    }

    private void addComponent(Panel panel, Component comp, GridBagConstraints gbc, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        panel.add(comp, gbc);
    }


    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
            display.setText(display.getText() + command);
        } else if (command.equals(".")) {
            if (!display.getText().contains(".")) {
                display.setText(display.getText() + ".");
            }
        } else if (command.equals("C")) {
            display.setText("");
            num1 = num2 = result = 0;
            operator = "";
        } else if (command.equals("=")) {
            num2 = Double.parseDouble(display.getText());
            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    result = num1 / num2;
                    break;
                case "pow":
                    result = Math.pow(num1, num2);
                    break;
            }
            display.setText(String.valueOf(result));
            operator = "";
        } else if (command.equals("sqrt")) {
            double number = Double.parseDouble(display.getText());
            result = Math.sqrt(number);
            display.setText(String.valueOf(result));
        } else {
            if (!display.getText().isEmpty()) {
                num1 = Double.parseDouble(display.getText());
                operator = command;
                display.setText("");
            }
        }
    }
}
