package tour;

import danhsach.DanhSach;

public class DanhSachTour implements DanhSach<Tour> {
    private Tour[] danhSach;
    private int soLuong;
    private Tour[] danhSachTam;
    private int soLuongTam;

    public DanhSachTour() {
    }

    public DanhSachTour(Tour[] danhSach, int soLuong, Tour[] danhSachTam, int soLuongTam) {
        this.danhSach = danhSach;
        this.soLuong = soLuong;
        this.danhSachTam = danhSachTam;
        this.soLuongTam = soLuongTam;
    }

    @Override
    public boolean them(Tour tour) {
        return false;
    }

    @Override
    public boolean xoa(int ma) {
        return false;
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

    public Tour[] getDanhSachTam() {
        return danhSachTam;
    }

    public void setDanhSachTam(Tour[] danhSachTam) {
        this.danhSachTam = danhSachTam;
    }

    public int getSoLuongTam() {
        return soLuongTam;
    }

    public void setSoLuongTam(int soLuongTam) {
        this.soLuongTam = soLuongTam;
    }
}
