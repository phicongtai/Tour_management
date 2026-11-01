package datvevathanhtoan;

import danhsach.DanhSach;

public class DanhSachHoaDon implements DanhSach<HoaDon> {
    private HoaDon[] danhSach;
    private int soLuong;

    public DanhSachHoaDon() {
    }

    public DanhSachHoaDon(HoaDon[] danhSach, int soLuong) {
        this.danhSach = danhSach;
        this.soLuong = soLuong;
    }

    @Override
    public boolean them(HoaDon hoaDon) {
        return false;
    }

    @Override
    public HoaDon[] layTatCa() {
        return danhSach;
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

    @Override
    public HoaDon timTheoMa(int maHoaDon) {
        return null;
    }

    public HoaDon timTheoMaDonDatTour(int maDonDatTour) {
        return null;
    }

    public HoaDon[] timChuaThanhToanDu(DanhSachVe dsVe) {
        return new HoaDon[0];
    }

    @Override
    public boolean capNhat(HoaDon hoaDon) {
        return false;
    }

    @Override
    public boolean xoaTheoMa(int maHoaDon) {
        return false;
    }

    @Override
    public String[] xuatDuLieu() {
        return new String[0];
    }

    @Override
    public boolean nhapDuLieu(String[] dongDuLieu) {
        return false;
    }
}
