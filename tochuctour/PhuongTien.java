package tochuctour;

public class PhuongTien {
    private int maPhuongTien;
    private String tenPhuongTien;
    private int soChoToiDa;
    private double giaMoiNguoiMoiNgay;

    public PhuongTien() {
    }

    public PhuongTien(int maPhuongTien, String tenPhuongTien, int soChoToiDa, double giaMoiNguoiMoiNgay) {
        this.maPhuongTien = maPhuongTien;
        this.tenPhuongTien = tenPhuongTien;
        this.soChoToiDa = soChoToiDa;
        this.giaMoiNguoiMoiNgay = giaMoiNguoiMoiNgay;
    }

    public int getMaPhuongTien() {
        return maPhuongTien;
    }

    public void setMaPhuongTien(int maPhuongTien) {
        this.maPhuongTien = maPhuongTien;
    }

    public String getTenPhuongTien() {
        return tenPhuongTien;
    }

    public void setTenPhuongTien(String tenPhuongTien) {
        this.tenPhuongTien = tenPhuongTien;
    }

    public int getSoChoToiDa() {
        return soChoToiDa;
    }

    public void setSoChoToiDa(int soChoToiDa) {
        this.soChoToiDa = soChoToiDa;
    }

    public double getGiaMoiNguoiMoiNgay() {
        return giaMoiNguoiMoiNgay;
    }

    public void setGiaMoiNguoiMoiNgay(double giaMoiNguoiMoiNgay) {
        this.giaMoiNguoiMoiNgay = giaMoiNguoiMoiNgay;
    }
}
