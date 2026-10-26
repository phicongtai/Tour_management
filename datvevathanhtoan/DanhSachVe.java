package datvevathanhtoan;

import danhsach.DanhSach;

public class DanhSachVe implements DanhSach<Ve> {
    private Ve[] danhSach;
    private int soLuong;
    private Ve[] danhSachTam;
    private int soLuongTam;

    public DanhSachVe() {
    }

    public DanhSachVe(Ve[] danhSach, int soLuong, Ve[] danhSachTam, int soLuongTam) {
        this.danhSach = danhSach;
        this.soLuong = soLuong;
        this.danhSachTam = danhSachTam;
        this.soLuongTam = soLuongTam;
    }

    @Override
    public boolean them(Ve ve) {
        return false;
    }

    @Override
    public boolean xoa(int ma) {
        return false;
    }

    public Ve[] getDanhSach() {
        return danhSach;
    }

    public void setDanhSach(Ve[] danhSach) {
        this.danhSach = danhSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Ve[] getDanhSachTam() {
        return danhSachTam;
    }

    public void setDanhSachTam(Ve[] danhSachTam) {
        this.danhSachTam = danhSachTam;
    }

    public int getSoLuongTam() {
        return soLuongTam;
    }

    public void setSoLuongTam(int soLuongTam) {
        this.soLuongTam = soLuongTam;
    }
}
