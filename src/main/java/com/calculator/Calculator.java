package com.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {

    private JTextField display;
    private String currentInput = "";
    private double firstNumber = 0;
    private String operator = "";

    public Calculator() {
        // Configurar ventana
        setTitle("Calculadora");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar
        setLayout(new BorderLayout());

        // Display
        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 20));
        add(display, BorderLayout.NORTH);

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 5, 5));

        // Botones
        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.matches("\\d")) { // Si es un número
            currentInput += cmd;
            display.setText(currentInput);
        } else if (cmd.equals("C")) { // Limpiar
            currentInput = "";
            firstNumber = 0;
            operator = "";
            display.setText("");
        } else if (cmd.equals("=")) { // Calcular
            if (!currentInput.isEmpty() && !operator.isEmpty()) {
                double secondNumber = Double.parseDouble(currentInput);
                double result = 0;

                switch (operator) {
                    case "+" -> result = firstNumber + secondNumber;
                    case "-" -> result = firstNumber - secondNumber;
                    case "*" -> result = firstNumber * secondNumber;
                    case "/" -> result = secondNumber != 0 ? firstNumber / secondNumber : Double.NaN;
                }

                display.setText(String.valueOf(result));
                currentInput = "";
                operator = "";
                firstNumber = result; // permite seguir operando con el resultado
            }
        } else { // Operador
            if (!currentInput.isEmpty()) {
                firstNumber = Double.parseDouble(currentInput);
                operator = cmd;
                currentInput = "";
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculator calc = new Calculator();
            calc.setVisible(true);
        });
    }
}
