package tochuctour;

import danhsach.DanhSach;

public class DanhSachPhuongTien implements DanhSach<PhuongTien> {
    private PhuongTien[] danhSach;
    private int soLuong;
    private PhuongTien[] danhSachTam;
    private int soLuongTam;

    public DanhSachPhuongTien() {
    }

    public DanhSachPhuongTien(PhuongTien[] danhSach, int soLuong, PhuongTien[] danhSachTam, int soLuongTam) {
        this.danhSach = danhSach;
        this.soLuong = soLuong;
        this.danhSachTam = danhSachTam;
        this.soLuongTam = soLuongTam;
    }

    @Override
    public boolean them(PhuongTien phuongTien) {
        return false;
    }

    @Override
    public boolean xoa(int ma) {
        return false;
    }

    public PhuongTien[] getDanhSach() {
        return danhSach;
    }

    public void setDanhSach(PhuongTien[] danhSach) {
        this.danhSach = danhSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public PhuongTien[] getDanhSachTam() {
        return danhSachTam;
    }

    public void setDanhSachTam(PhuongTien[] danhSachTam) {
        this.danhSachTam = danhSachTam;
    }

    public int getSoLuongTam() {
        return soLuongTam;
    }

    public void setSoLuongTam(int soLuongTam) {
        this.soLuongTam = soLuongTam;
    }
}
