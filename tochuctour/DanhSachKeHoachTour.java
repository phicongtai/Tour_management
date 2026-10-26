package tochuctour;

import danhsach.DanhSach;

public class DanhSachKeHoachTour implements DanhSach<KeHoachTour> {
    private KeHoachTour[] danhSach;
    private int soLuong;
    private KeHoachTour[] danhSachTam;
    private int soLuongTam;

    public DanhSachKeHoachTour() {
    }

    public DanhSachKeHoachTour(KeHoachTour[] danhSach, int soLuong, KeHoachTour[] danhSachTam, int soLuongTam) {
        this.danhSach = danhSach;
        this.soLuong = soLuong;
        this.danhSachTam = danhSachTam;
        this.soLuongTam = soLuongTam;
    }

    @Override
    public boolean them(KeHoachTour keHoach) {
        return false;
    }

    @Override
    public boolean xoa(int ma) {
        return false;
    }

    public KeHoachTour[] getDanhSach() {
        return danhSach;
    }

    public void setDanhSach(KeHoachTour[] danhSach) {
        this.danhSach = danhSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public KeHoachTour[] getDanhSachTam() {
        return danhSachTam;
    }

    public void setDanhSachTam(KeHoachTour[] danhSachTam) {
        this.danhSachTam = danhSachTam;
    }

    public int getSoLuongTam() {
        return soLuongTam;
    }

    public void setSoLuongTam(int soLuongTam) {
        this.soLuongTam = soLuongTam;
    }
}
