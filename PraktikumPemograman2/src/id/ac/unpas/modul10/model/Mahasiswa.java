package id.ac.unpas.modul10.model;

public class Mahasiswa {
    private String id;
    private String nama;
    private String nim;
    private String jurusan;

    // Constructor
    public Mahasiswa(String nama, String nim, String jurusan) {
        this.nama = nama;
        this.nim = nim;
        this.jurusan = jurusan;
    }

    // Getter
    public String getNama() { return nama; }
    public String getNim() { return nim; }
    public String getJurusan() { return jurusan; }
}