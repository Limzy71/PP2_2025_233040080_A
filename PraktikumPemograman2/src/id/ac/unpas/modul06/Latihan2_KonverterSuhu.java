package id.ac.unpas.modul06;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Latihan2_KonverterSuhu {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Konversi Suhu Celcius ke Fahrenheit");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel labelCelcius = new JLabel("Celcius:");
        labelCelcius.setFont(new Font("Arial", Font.PLAIN, 16));

        JTextField textFieldCelcius = new JTextField();
        textFieldCelcius.setFont(new Font("Arial", Font.PLAIN, 16));

        JButton buttonKonversi = new JButton("Konversi");
        buttonKonversi.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel labelKosong = new JLabel("");

        JLabel labelFahrenheit = new JLabel("Fahrenheit:");
        labelFahrenheit.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel labelHasil = new JLabel("");
        labelHasil.setFont(new Font("Arial", Font.BOLD, 16));
        labelHasil.setForeground(Color.BLUE);

        buttonKonversi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String inputCelcius = textFieldCelcius.getText();

                    double celcius = Double.parseDouble(inputCelcius);

                    double fahrenheit = (celcius * 9.0 / 5.0) + 32;

                    labelHasil.setText(String.format("%.2f °F", fahrenheit));

                } catch (NumberFormatException ex) {
                    labelHasil.setText("Input tidak valid!");
                    labelHasil.setForeground(Color.RED);

                    Timer timer = new Timer(2000, new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            labelHasil.setForeground(Color.BLUE);
                        }
                    });
                    timer.setRepeats(false);
                    timer.start();
                }
            }
        });

        frame.add(labelCelcius);
        frame.add(textFieldCelcius);
        frame.add(buttonKonversi);
        frame.add(labelKosong);
        frame.add(labelFahrenheit);
        frame.add(labelHasil);

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }
}