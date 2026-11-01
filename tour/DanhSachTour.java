package tour;

import danhsach.DanhSach;

public class DanhSachTour implements DanhSach<Tour> {
    private Tour[] danhSach;
    private int soLuong;

    public DanhSachTour() {
    }

    public DanhSachTour(Tour[] danhSach, int soLuong) {
        this.danhSach = danhSach;
        this.soLuong = soLuong;
    }

    @Override
    public boolean them(Tour tour) {
        return false;
    }

    @Override
    public Tour[] layTatCa() {
        return danhSach;
    }

    public Tour[] getDanhSach() {
        return danhSach;
    }

    public void setDanhSach(Tour[] danhSach) {
        this.danhSach = danhSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    @Override
    public Tour timTheoMa(int maTour) {
        return null;
    }

    public Tour[] timTheoTen(String tenTour) {
        return new Tour[0];
    }

    @Override
    public boolean capNhat(Tour tour) {
        return false;
    }

    @Override
    public boolean xoaTheoMa(int maTour) {
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
