package id.ac.unpas.Modul07;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class ManajemenNilaiSiswaApp extends JFrame {
    private JTextField txtNama;
    private JTextField txtNilai;
    private JComboBox<String> cmbMatkul;
    private JTable tableData;
    private DefaultTableModel tableModel;
    private JTabbedPane tabbedPane;

    // Method untuk membuat desain Tab Input
    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Komponen Nama
        panel.add(new JLabel("Nama Siswa:"));
        txtNama = new JTextField();
        panel.add(txtNama);

        // Komponen Mata Pelajaran (ComboBox)
        panel.add(new JLabel("Mata Pelajaran:"));
        String[] matkul = {"Matematika Dasar", "Bahasa Indonesia",
                "Algoritma dan Pemrograman I", "Praktikum Pemrograman II"};
        cmbMatkul = new JComboBox<>(matkul);
        panel.add(cmbMatkul);

        // Komponen Nilai
        panel.add(new JLabel("Nilai (0-100):"));
        txtNilai = new JTextField();
        panel.add(txtNilai);

        // Tombol Reset dan Simpan Data

        // 1. Tombol Reset
        JButton btnReset = new JButton("Reset Form");
        panel.add(btnReset);

        // 2. Tombol Simpan
        JButton btnSimpan = new JButton("Simpan Data");
        panel.add(btnSimpan);

        // Event Handling Tombol Reset
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtNama.setText("");
                txtNilai.setText("");
                cmbMatkul.setSelectedIndex(0);
                txtNama.requestFocus();
            }
        });

        // Event Handling Tombol Simpan
        btnSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prosesSimpan();
            }
        });

        return panel;
    }

    // Method untuk membuat desain Tab Tabel
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Setup Model Tabel (Kolom)
        String[] kolom = {"Nama Siswa", "Mata Pelajaran", "Nilai", "Grade"};
        tableModel = new DefaultTableModel(kolom, 0);
        tableData = new JTable(tableModel);

        // Membungkus tabel dengan ScrollPane (agar bisa di scroll jika data banyak)
        JScrollPane scrollPane = new JScrollPane(tableData);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Membuat Tombol Hapus
        JButton btnHapus = new JButton("Hapus Data");
        JPanel bottomPanel = new JPanel(new GridLayout(1, 1));
        bottomPanel.add(btnHapus);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        // Event Handling Tombol Hapus
        btnHapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableData.getSelectedRow();
                if (selectedRow > -1) {
                    int confirm = JOptionPane.showConfirmDialog(null, "Yakin hapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        tableModel.removeRow(selectedRow);
                        JOptionPane.showMessageDialog(null, "Data dihapus!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Pilih baris dulu!");
                }
            }
        });

        return panel;
    }

    // Logika Validasi dan Penyimpanan Data
    private void prosesSimpan() {
        // 1. Ambil Data dari input
        String nama = txtNama.getText();
        String matkul = (String) cmbMatkul.getSelectedItem();
        String strNilai = (String) txtNilai.getText();

        // 2. Validasi Input

        // Validasi 1: cek apakah nama kosong
        if (nama.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama tidak boleh kosong!", "Error Validasi", JOptionPane.ERROR_MESSAGE);
            return; // Hentikan proses
        }

        // Validasi untuk mengecek nama siswa harus minimal 3 karakter
        if (nama.trim().length() < 3) {
            JOptionPane.showMessageDialog(this, "Nama siswa minimal harus 3 karakter!", "Error Validasi", JOptionPane.ERROR_MESSAGE);
            return; // Hentikan proses
        }

        // Validasi 2: cek apakah nilai berupa angka dan dalam range valid
        int nilai;
        try {
            nilai = Integer.parseInt(strNilai);
            if (nilai < 0 || nilai > 100) {
                JOptionPane.showMessageDialog(this, "Nilai harus antara 0 - 100!", "Error Validasi", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Nilai harus berupa angka!", "Error Validasi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 3. Logika Bisnis (Menentukan Grade)
        String grade;
        switch (nilai / 10) {
            case 10: // Untuk nilai 100
            case 9:  // Untuk nilai 90-99
            case 8:  // Untuk nilai 80-89
                grade = "A";
                break;
            case 7:  // Untuk nilai 70-79
                grade = "AB";
                break;
            case 6:  // Untuk nilai 60-69
                grade = "B";
                break;
            case 5:  // Untuk nilai 50-59
                grade = "BC";
                break;
            case 4:  // Untuk nilai 40-49
                grade = "C";
                break;
            case 3:  // Untuk nilai 30-39
                grade = "D";
                break;
            default: // Untuk nilai 0-29
                grade = "E";
                break;
        }

        // 4. Masukkan ke Tabel (Update Model)
        Object[] dataBaris = {nama, matkul, nilai, grade};
        tableModel.addRow(dataBaris);

        // 5. Reset Form dan Pindah Tab
        txtNama.setText("");
        txtNilai.setText("");
        cmbMatkul.setSelectedIndex(0);

        JOptionPane.showMessageDialog(this, "Data Berhasil Disimpan!");
        tabbedPane.setSelectedIndex(1); // Otomatis pindah ke tab tabel
    }

    public ManajemenNilaiSiswaApp() {
        // 1. Konfigurasi Frame Utama
        setTitle("Aplikasi Manajemen Nilai Siswa");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Posisi di tengah layar

        // 2. Inisialisasi Tabbed Pane
        tabbedPane = new JTabbedPane();

        // 3. Membuat Panel untuk Tab 1 (Form Input)
        JPanel panelInput = createInputPanel();
        tabbedPane.addTab("Input Data", panelInput);

        // 4. Membuat Panel untuk Tab 2 (Tabel Data)
        JPanel panelTabel = createTablePanel();
        tabbedPane.addTab("Daftar Nilai", panelTabel);

        // Menambahkan TabbedPane ke Frame
        add(tabbedPane);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ManajemenNilaiSiswaApp().setVisible(true);
        });
    }
}