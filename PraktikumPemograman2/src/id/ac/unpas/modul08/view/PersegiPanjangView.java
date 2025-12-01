package id.ac.unpas.modul08.view;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class PersegiPanjangView extends JFrame {
    // Komponen UI sebagai atribut
    private JTextField txtPanjang = new JTextField(10);
    private JTextField txtLebar = new JTextField(10);
    private JLabel lblHasil = new JLabel("-");

    // Baru di tambahakan (label output keliling)
    private JLabel lblHasilKeliling = new JLabel("-");

    private JButton btnHitung = new JButton("Hitung Luas dan Keliling");

    // Baru di tambahakan (buat tombol reset)
    private JButton btnReset = new JButton("Reset");

    public PersegiPanjangView() {
        // Inisialisasi UI
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(350, 250); // Memperbesar ukuran yang tadinya 300 x 200 menjadi 350 x 250
        this.setLayout(new GridLayout(5, 2, 10, 10)); // Grid 4 baris : jadi 5 baris
        this.setTitle("MVC Kalkulator");

        this.add(new JLabel("Panjang:"));
        this.add(txtPanjang);
        this.add(new JLabel("Lebar:"));
        this.add(txtLebar);
        this.add(new JLabel("Hasil Luas:"));
        this.add(lblHasil);

        // Baru di tambahakan (menambahkan komponen keliling ke layar)
        this.add(new JLabel("Hasil Keliling:"));
        this.add(lblHasilKeliling);

        // Baru di tambahakan (menambahkan tombol reset)
        this.add(btnReset);

        this.add(btnHitung);
    }

    // Mengambil nilai panjang dari TextField
    public double getPanjang() {
        return Double.parseDouble(txtPanjang.getText());
    }

    // Mengambil nilai lebar dari TextField
    public double getLebar() {
        return Double.parseDouble(txtLebar.getText());
    }

    // Menampilkan hasil ke Label
    public void setHasil(double hasil) {
        lblHasil.setText(String.valueOf(hasil));
    }

    // Baru di tambahakan (menampilkan hasil keliling ke label)
    public void setHasilKeliling(double hasil) {
        lblHasilKeliling.setText(String.valueOf(hasil));
    }

    // Baru di tambahakan (fitur reset)
    public void clear() {
        txtPanjang.setText("");
        txtLebar.setText("");
        lblHasil.setText("-");
        lblHasilKeliling.setText("-");
    }

    // Menampilkan pesan error (jika input bukan angka)
    public void tampilkanPesanError(String pesan) {
        JOptionPane.showMessageDialog(this, pesan);
    }

    // Mendaftarkan Listener untuk tombol (Controller yang akan memberikan aksinya)
    public void addHitungListener(ActionListener listener) {
        btnHitung.addActionListener(listener);
    }

    // Baru di tambahakan (mendaftarkan listener untuk tombol reset)
    public void addResetListener(ActionListener listener) {
        btnReset.addActionListener(listener);
    }
}
