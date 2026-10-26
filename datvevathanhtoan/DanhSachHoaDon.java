package datvevathanhtoan;

import danhsach.DanhSach;

public class DanhSachHoaDon implements DanhSach<HoaDon> {
    private HoaDon[] danhSach;
    private int soLuong;
    private HoaDon[] danhSachTam;
    private int soLuongTam;

    public DanhSachHoaDon() {
    }

    public DanhSachHoaDon(HoaDon[] danhSach, int soLuong, HoaDon[] danhSachTam, int soLuongTam) {
        this.danhSach = danhSach;
        this.soLuong = soLuong;
        this.danhSachTam = danhSachTam;
        this.soLuongTam = soLuongTam;
    }

    @Override
    public boolean them(HoaDon hoaDon) {
        return false;
    }

    @Override
    public boolean xoa(int ma) {
        return false;
    }

    public HoaDon[] getDanhSach() {
        return danhSach;
    }

    public void setDanhSach(HoaDon[] danhSach) {
        this.danhSach = danhSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public HoaDon[] getDanhSachTam() {
        return danhSachTam;
    }

    public void setDanhSachTam(HoaDon[] danhSachTam) {
        this.danhSachTam = danhSachTam;
    }

    public int getSoLuongTam() {
        return soLuongTam;
    }

    public void setSoLuongTam(int soLuongTam) {
        this.soLuongTam = soLuongTam;
    }
}
