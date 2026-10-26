package nguoidung;

import danhsach.DanhSach;

public class DanhSachHanhKhach implements DanhSach<HanhKhach> {
    private HanhKhach[] danhSach;
    private int soLuong;
    private HanhKhach[] danhSachTam;
    private int soLuongTam;

    public DanhSachHanhKhach() {
    }

    public DanhSachHanhKhach(HanhKhach[] danhSach, int soLuong, HanhKhach[] danhSachTam, int soLuongTam) {
        this.danhSach = danhSach;
        this.soLuong = soLuong;
        this.danhSachTam = danhSachTam;
        this.soLuongTam = soLuongTam;
    }

    @Override
    public boolean them(HanhKhach hanhKhach) {
        return false;
    }

    @Override
    public boolean xoa(int ma) {
        return false;
    }

    public HanhKhach[] getDanhSach() {
        return danhSach;
    }

    public void setDanhSach(HanhKhach[] danhSach) {
        this.danhSach = danhSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public HanhKhach[] getDanhSachTam() {
        return danhSachTam;
    }

    public void setDanhSachTam(HanhKhach[] danhSachTam) {
        this.danhSachTam = danhSachTam;
    }

    public int getSoLuongTam() {
        return soLuongTam;
    }

    public void setSoLuongTam(int soLuongTam) {
        this.soLuongTam = soLuongTam;
    }
}
