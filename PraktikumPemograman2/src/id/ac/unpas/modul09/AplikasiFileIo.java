package id.ac.unpas.modul09;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class AplikasiFileIo extends JFrame {

    // Komponen UI
    private JTextArea textArea;
    private JButton btnOpenText, btnSaveText, btnAppendText;
    private JButton btnSaveBinary, btnLoadBinary;
    private JButton btnSaveObj, btnLoadObj;
    private JButton btnUbahFont;
    private JFileChooser fileChooser;

    public AplikasiFileIo() {
        super("Tutorial File IO & Exception Handling");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inisialisasi Komponen
        textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        fileChooser = new JFileChooser();

        // Panel Tombol
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 4, 5, 5));

        btnOpenText = new JButton("Buka Text");
        btnSaveText = new JButton("Simpan Text");
        btnAppendText = new JButton("Tambah (Append)");
        btnSaveBinary = new JButton("Simpan Config (Binary)");
        btnLoadBinary = new JButton("Muat Config (Binary)");
        btnSaveObj = new JButton("Simpan Objek");
        btnLoadObj = new JButton("Muat Objek");
        btnUbahFont = new JButton("Ubah Font Size");

        buttonPanel.add(btnOpenText);
        buttonPanel.add(btnSaveText);
        buttonPanel.add(btnAppendText);
        buttonPanel.add(btnSaveBinary);
        buttonPanel.add(btnLoadBinary);
        buttonPanel.add(btnSaveObj);
        buttonPanel.add(btnLoadObj);
        buttonPanel.add(btnUbahFont);

        // Layout
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        muatCatatanTerakhir();

        // --- Event Handling ---

        // 1. MEMBACA FILE TEKS (Text Stream)
        btnOpenText.addActionListener(e -> bukaFileTeks());

        btnAppendText.addActionListener(e -> tambahFileTeks());

        // 2. MENULIS FILE TEKS (Text Stream)
        btnSaveText.addActionListener(e -> simpanFileTeks());

        // 3. MENULIS FILE BINARY (Byte Stream)
        btnSaveBinary.addActionListener(e -> simpanConfigBinary());

        // 4. MEMBACA FILE BINARY (Byte Stream)
        btnLoadBinary.addActionListener(e -> muatConfigBinary());

        btnSaveObj.addActionListener(e -> simpanUserConfig());
        btnLoadObj.addActionListener(e -> muatUserConfig());
        btnUbahFont.addActionListener(e -> ubahUkuranFont());
    }

    // Contoh: Membaca File Teks dengan Try-Catch-Finally Konvensional
    private void bukaFileTeks() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            BufferedReader reader = null; // Deklarasi di luar try agar bisa diakses di finally

            try {
                // Membuka stream
                reader = new BufferedReader(new FileReader(file));
                textArea.setText(""); // Kosongkan area

                String line;
                // Baca baris demi baris
                while ((line = reader.readLine()) != null) {
                    textArea.append(line + "\n");
                }

                JOptionPane.showMessageDialog(this, "File berhasil dimuat!");

            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "File tidak ditemukan: " + ex.getMessage());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Gagal membaca file: " + ex.getMessage());
            } finally {
                // Blok Finally: Selalu dijalankan untuk menutup resource
                try {
                    if (reader != null) {
                        reader.close(); // PENTING: Menutup stream
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    // Contoh: Menulis File Teks menggunakan Try-With-Resources (Lebih Modern)
    private void simpanFileTeks() {
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            // Try-with-resources otomatis menutup stream tanpa blok finally manual
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(textArea.getText());
                JOptionPane.showMessageDialog(this, "File berhasil disimpan!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Gagal menyimpan file: " + ex.getMessage());
            }
        }
    }

    // Contoh: Menulis Binary (Menyimpan ukuran font saat ini ke file .bin)
    private void simpanConfigBinary() {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("config.bin"))) {
            // Kita simpan ukuran font saat ini (Integer)
            int fontSize = textArea.getFont().getSize();
            dos.writeInt(fontSize);

            JOptionPane.showMessageDialog(this, "Ukuran font (" + fontSize + ") disimpan ke config.bin");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan binary: " + ex.getMessage());
        }
    }

    // Contoh: Membaca Binary (Mengambil ukuran font dari file .bin)
    private void muatConfigBinary() {
        try (DataInputStream dis = new DataInputStream(new FileInputStream("config.bin"))) {
            // Membaca data Integer mentah
            int fontSize = dis.readInt();

            // Terapkan ke aplikasi
            textArea.setFont(new Font("Monospaced", Font.PLAIN, fontSize));
            JOptionPane.showMessageDialog(this, "Font diubah menjadi ukuran: " + fontSize);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "File config.bin belum dibuat!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Gagal membaca binary: " + ex.getMessage());
        }
    }

    private void muatCatatanTerakhir() {
        File file = new File("last_notes.txt");

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                textArea.setText("");

                while ((line = reader.readLine()) != null) {
                    textArea.append(line + "\n");
                }
            } catch (IOException e) {
            }
        }
    }

    private void ubahUkuranFont() {
        String input = JOptionPane.showInputDialog(this, "Masukkan ukuran font baru (contoh: 20):");
        if (input != null && !input.isEmpty()) {
            try {
                int size = Integer.parseInt(input);
                textArea.setFont(new Font("Monospaced", Font.PLAIN, size));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Masukkan angka yang benar!");
            }
        }
    }

    private void simpanUserConfig() {
        // 1. Ambil data dari UI
        String username = JOptionPane.showInputDialog(this, "Masukkan Username:");
        int fontSize = textArea.getFont().getSize();

        // 2. Buat Objek UserConfig
        UserConfig config = new UserConfig();
        config.setUsername(username);
        config.setFontsize(fontSize);

        // 3. Simpan Objek ke File menggunakan ObjectOutputStream
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("user.cfg"))) {
            oos.writeObject(config); // Menulis seluruh objek sekaligus!
            JOptionPane.showMessageDialog(this, "Objek UserConfig berhasil disimpan!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan objek: " + ex.getMessage());
        }
    }

    private void muatUserConfig() {
        // Gunakan ObjectInputStream untuk membaca
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("user.cfg"))) {

            // 1. Baca data dan ubah (Cast) menjadi UserConfig
            UserConfig config = (UserConfig) ois.readObject();

            // 2. Tampilkan datanya
            textArea.setFont(new Font("Monospaced", Font.PLAIN, config.getFontsize()));
            JOptionPane.showMessageDialog(this,
                    "Config dimuat!\nUsername: " + config.getUsername() +
                            "\nFont Size: " + config.getFontsize());

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "File user.cfg belum dibuat!");
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Gagal membaca objek: " + ex.getMessage());
        }
    }

    private void tambahFileTeks() {
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.newLine();
                writer.write(textArea.getText());
                JOptionPane.showMessageDialog(this, "Teks berhasil ditambahkan di bawah!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Gagal menambahkan file: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AplikasiFileIo().setVisible(true);
        });
    }
}