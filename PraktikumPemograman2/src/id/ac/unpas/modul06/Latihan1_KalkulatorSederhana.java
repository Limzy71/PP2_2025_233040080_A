package id.ac.unpas.modul06;

import java.awt.*;
import javax.swing.*;

public class Latihan1_KalkulatorSederhana {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Kalkulator Sederhana");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());

        JTextField layar = new JTextField();
        layar.setHorizontalAlignment(JTextField.RIGHT);
        layar.setBackground(Color.lightGray);
        layar.setEditable(false);
        layar.setPreferredSize(new Dimension(300, 150));

        frame.add(layar, BorderLayout.NORTH);

        JPanel panelTombol = new JPanel();
        panelTombol.setLayout(new GridLayout(4, 4, 5, 5));
        panelTombol.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JButton[] tombolAngka = new JButton[10];
        JButton[] tombolOperator = new JButton[6];

        for (int i = 0; i < 10; i++) {
            tombolAngka[i] = new JButton(String.valueOf(i));
            tombolAngka[i].setFont(new Font("Arial", Font.BOLD, 20));
        }

        tombolOperator[0] = new JButton("+");
        tombolOperator[1] = new JButton("-");
        tombolOperator[2] = new JButton("*");
        tombolOperator[3] = new JButton("/");
        tombolOperator[4] = new JButton("=");
        tombolOperator[5] = new JButton("C");

        for (int i = 0; i < 6; i++) {
            tombolOperator[i].setFont(new Font("Arial", Font.BOLD, 20));
        }

        panelTombol.add(tombolAngka[7]);
        panelTombol.add(tombolAngka[8]);
        panelTombol.add(tombolAngka[9]);
        panelTombol.add(tombolOperator[3]);

        panelTombol.add(tombolAngka[4]);
        panelTombol.add(tombolAngka[5]);
        panelTombol.add(tombolAngka[6]);
        panelTombol.add(tombolOperator[2]);

        panelTombol.add(tombolAngka[1]);
        panelTombol.add(tombolAngka[2]);
        panelTombol.add(tombolAngka[3]);
        panelTombol.add(tombolOperator[1]);

        panelTombol.add(tombolOperator[5]);
        panelTombol.add(tombolAngka[0]);
        panelTombol.add(tombolOperator[4]);
        panelTombol.add(tombolOperator[0]);

        frame.add(panelTombol, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }
}
