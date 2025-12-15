package id.ac.unpas.modul10.model;

import id.ac.unpas.modul10.KoneksiDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MahasiswaModel {

    // 1. CREATE
    public void addMahasiswa(Mahasiswa mhs) throws SQLException {
        String sql = "INSERT INTO mahasiswa (nama, nim, jurusan) VALUES (?, ?, ?)";
        Connection conn = KoneksiDB.configDB();
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, mhs.getNama());
        pst.setString(2, mhs.getNim());
        pst.setString(3, mhs.getJurusan());
        pst.execute();
    }

    // 2. READ
    public List<Mahasiswa> getMahasiswaList() throws SQLException {
        List<Mahasiswa> list = new ArrayList<>();
        Connection conn = KoneksiDB.configDB();
        Statement stm = conn.createStatement();
        ResultSet res = stm.executeQuery("SELECT * FROM mahasiswa");
        while (res.next()) {
            list.add(new Mahasiswa(res.getString("nama"), res.getString("nim"), res.getString("jurusan")));
        }
        return list;
    }

    // 3. Update
    public void updateMahasiswa(Mahasiswa mhs) throws SQLException {
        // SQL ini sesuai modul: NIM hanya sebagai WHERE (kunci), bukan untuk diubah
        String sql = "UPDATE mahasiswa SET nama = ?, jurusan = ? WHERE nim = ?";
        Connection conn = KoneksiDB.configDB();
        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setString(1, mhs.getNama());
        pst.setString(2, mhs.getJurusan());
        pst.setString(3, mhs.getNim()); // NIM digunakan untuk mencari data mana yang diedit

        pst.executeUpdate();
    }

    // 4. DELETE
    public void deleteMahasiswa(String nim) throws SQLException {
        String sql = "DELETE FROM mahasiswa WHERE nim = ?";
        Connection conn = KoneksiDB.configDB();
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, nim);
        pst.execute();
    }

    // 5. CEK NIM
    public boolean isNimExists(String nim) throws SQLException {
        String sql = "SELECT COUNT(*) FROM mahasiswa WHERE nim = ?";
        Connection conn = KoneksiDB.configDB();
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, nim);
        ResultSet res = pst.executeQuery();
        if (res.next()) {
            return res.getInt(1) > 0;
        }
        return false;
    }

    // 6. Search
    public List<Mahasiswa> searchMahasiswa(String keyword) throws SQLException {
        List<Mahasiswa> list = new ArrayList<>();
        String sql = "SELECT * FROM mahasiswa WHERE nama LIKE ?";
        Connection conn = KoneksiDB.configDB();
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, "%" + keyword + "%"); // Pakai wildcard %

        ResultSet res = pst.executeQuery();
        while (res.next()) {
            list.add(new Mahasiswa(res.getString("nama"), res.getString("nim"), res.getString("jurusan")));
        }
        return list;
    }
}