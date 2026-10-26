package tochuctour;

import danhsach.DanhSach;

public class DanhSachKhachSan implements DanhSach<KhachSan> {
    private KhachSan[] danhSach;
    private int soLuong;
    private KhachSan[] danhSachTam;
    private int soLuongTam;

    public DanhSachKhachSan() {
    }

    public DanhSachKhachSan(KhachSan[] danhSach, int soLuong, KhachSan[] danhSachTam, int soLuongTam) {
        this.danhSach = danhSach;
        this.soLuong = soLuong;
        this.danhSachTam = danhSachTam;
        this.soLuongTam = soLuongTam;
    }

    @Override
    public boolean them(KhachSan khachSan) {
        return false;
    }

    @Override
    public boolean xoa(int ma) {
        return false;
    }

    public KhachSan[] getDanhSach() {
        return danhSach;
    }

    public void setDanhSach(KhachSan[] danhSach) {
        this.danhSach = danhSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public KhachSan[] getDanhSachTam() {
        return danhSachTam;
    }

    public void setDanhSachTam(KhachSan[] danhSachTam) {
        this.danhSachTam = danhSachTam;
    }

    public int getSoLuongTam() {
        return soLuongTam;
    }

    public void setSoLuongTam(int soLuongTam) {
        this.soLuongTam = soLuongTam;
    }
}
