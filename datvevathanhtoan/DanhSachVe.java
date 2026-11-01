package datvevathanhtoan;

import danhsach.DanhSach;

public class DanhSachVe implements DanhSach<Ve> {
    private Ve[] danhSach;
    private int soLuong;

    public DanhSachVe() {
    }

    public DanhSachVe(Ve[] danhSach, int soLuong) {
        this.danhSach = danhSach;
        this.soLuong = soLuong;
    }

    @Override
    public boolean them(Ve ve) {
        return false;
    }

    @Override
    public Ve[] layTatCa() {
        return danhSach;
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

    @Override
    public Ve timTheoMa(int maVe) {
        return null;
    }

    public Ve[] timTheoMaDonDatTour(int maDonDatTour) {
        return new Ve[0];
    }

    public int[] layDanhSachMaDonDatTour() {
        return new int[0];
    }

    public Ve[] timTheoTrangThai(int trangThai) {
        return new Ve[0];
    }

    public int demVeChiemChoTheoKeHoach(int maKeHoachTour) {
        return 0;
    }

    public boolean daCoVeHopLe(int maKeHoachTour, int maHanhKhach) {
        return false;
    }

    @Override
    public boolean capNhat(Ve ve) {
        return false;
    }

    @Override
    public boolean xoaTheoMa(int maVe) {
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
