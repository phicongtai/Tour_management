package datvevathanhtoan;

public class ChiTietHoaDon {
    private int maHoaDon;
    private int[] danhSachMaVe;
    private int soLuongVe;
    private long tongTien;

    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public ChiTietHoaDon(int maHoaDon, int[] danhSachMaVe, int soLuongVe, long tongTien) {
        this.maHoaDon = maHoaDon;
        this.danhSachMaVe = danhSachMaVe;
        this.soLuongVe = soLuongVe;
        this.tongTien = tongTien;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int[] getDanhSachMaVe() {
        return danhSachMaVe;
    }

    public void setDanhSachMaVe(int[] danhSachMaVe) {
        this.danhSachMaVe = danhSachMaVe;
    }

    public int getSoLuongVe() {
        return soLuongVe;
    }

    public void setSoLuongVe(int soLuongVe) {
        this.soLuongVe = soLuongVe;
    }

    public long getTongTien() {
        return tongTien;
    }

    public void setTongTien(long tongTien) {
        this.tongTien = tongTien;
    }
}

