package id.ac.unpas.modul08.model;

public class PersegiPanjangModel {
    private double panjang;
    private double lebar;
    private double luas;

    // Baru di tambahakan
    private double keliling;

    // Menghitung luas (Logika Bisnis)
    public void hitungLuas() {
        this.luas = this.panjang * this.lebar;
    }
    // Baru di tambahkan (hitung keliling)
    public void hitungKeliling() {
        this.keliling = 2 * (this.panjang + this.lebar);
    }

    // Getters dan Setters
    public void setPanjang(double panjang) {
        this.panjang = panjang;
    }

    public void setLebar(double lebar) {
        this.lebar = lebar;
    }

    public double getLuas() {
        return luas;
    }

    // Baru di tambahakan (getter Keliling)
    public double getKeliling() {
        return keliling;
    }
}
