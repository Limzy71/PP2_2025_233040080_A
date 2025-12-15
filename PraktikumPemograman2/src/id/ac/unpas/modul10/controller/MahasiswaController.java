package id.ac.unpas.modul10.controller;

import id.ac.unpas.modul10.model.*;
import id.ac.unpas.modul10.view.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class MahasiswaController {
    private MahasiswaModel model;
    private MahasiswaView view;

    public MahasiswaController(MahasiswaModel model, MahasiswaView view) {
        this.model = model;
        this.view = view;

        // Listener Tombol
        view.btnSimpan.addActionListener(e -> simpanData());
        view.btnEdit.addActionListener(e -> editData());
        view.btnHapus.addActionListener(e -> hapusData());
        view.btnClear.addActionListener(e -> clearForm());
        view.btnCari.addActionListener(e -> cariData());

        // Listener Tabel
        view.tableMahasiswa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.tableMahasiswa.getSelectedRow();
                view.txtNama.setText(view.model.getValueAt(row, 1).toString());
                view.txtNIM.setText(view.model.getValueAt(row, 2).toString());
                view.txtJurusan.setText(view.model.getValueAt(row, 3).toString());
            }
        });

        loadData();
    }

    // --- LOGIKA SIMPAN ---
    private void simpanData() {
        String nama = view.txtNama.getText().trim();
        String nim = view.txtNIM.getText().trim();
        String jurusan = view.txtJurusan.getText().trim();

        if (nama.isEmpty() || nim.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Nama dan NIM tidak boleh kosong!");
            return;
        }

        try {
            if (model.isNimExists(nim)) {
                JOptionPane.showMessageDialog(view, "NIM Sudah Terdaftar!");
                return;
            }
            Mahasiswa mhs = new Mahasiswa(nama, nim, jurusan);
            model.addMahasiswa(mhs);
            JOptionPane.showMessageDialog(view, "Data Berhasil Disimpan");
            loadData();
            clearForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error: " + e.getMessage());
        }
    }

    // --- EDIT ---
    private void editData() {
        try {
            String nama = view.txtNama.getText();
            String nim = view.txtNIM.getText();
            String jurusan = view.txtJurusan.getText();

            Mahasiswa mhs = new Mahasiswa(nama, nim, jurusan);
            model.updateMahasiswa(mhs);

            JOptionPane.showMessageDialog(view, "Data Berhasil Diedit");
            loadData();
            clearForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error Edit: " + e.getMessage());
        }
    }

    // --- HAPUS ---
    private void hapusData() {
        try {
            String nim = view.txtNIM.getText();
            if (nim.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Pilih data di tabel dulu!");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(view,
                    "Yakin hapus data NIM " + nim + "?",
                    "Konfirmasi Hapus",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                model.deleteMahasiswa(nim);
                JOptionPane.showMessageDialog(view, "Data Berhasil Dihapus");
                loadData();
                clearForm();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error Hapus: " + e.getMessage());
        }
    }

    // --- Cari Data ---
    private void cariData() {
        try {
            String keyword = view.txtCari.getText();
            List<Mahasiswa> list = model.searchMahasiswa(keyword);

            view.model.setRowCount(0);
            int no = 1;
            for (Mahasiswa m : list) {
                view.model.addRow(new Object[]{no++, m.getNama(), m.getNim(), m.getJurusan()});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error Cari: " + e.getMessage());
        }
    }

    private void loadData() {
        try {
            view.model.setRowCount(0);
            List<Mahasiswa> list = model.getMahasiswaList();
            int no = 1;
            for (Mahasiswa m : list) {
                view.model.addRow(new Object[]{no++, m.getNama(), m.getNim(), m.getJurusan()});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearForm() {
        view.txtNama.setText("");
        view.txtNIM.setText("");
        view.txtJurusan.setText("");
        view.txtCari.setText("");
    }
}