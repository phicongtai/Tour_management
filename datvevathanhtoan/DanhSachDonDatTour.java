package datvevathanhtoan;

import danhsach.DanhSach;

public class DanhSachDonDatTour implements DanhSach<DonDatTour> {
    private DonDatTour[] danhSach;
    private int soLuong;
    private DonDatTour[] danhSachTam;
    private int soLuongTam;

    public DanhSachDonDatTour() {
    }

    public DanhSachDonDatTour(DonDatTour[] danhSach, int soLuong, DonDatTour[] danhSachTam, int soLuongTam) {
        this.danhSach = danhSach;
        this.soLuong = soLuong;
        this.danhSachTam = danhSachTam;
        this.soLuongTam = soLuongTam;
    }

    @Override
    public boolean them(DonDatTour don) {
        return false;
    }

    @Override
    public boolean xoa(int ma) {
        return false;
    }

    public DonDatTour[] getDanhSach() {
        return danhSach;
    }

    public void setDanhSach(DonDatTour[] danhSach) {
        this.danhSach = danhSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public DonDatTour[] getDanhSachTam() {
        return danhSachTam;
    }

    public void setDanhSachTam(DonDatTour[] danhSachTam) {
        this.danhSachTam = danhSachTam;
    }

    public int getSoLuongTam() {
        return soLuongTam;
    }

    public void setSoLuongTam(int soLuongTam) {
        this.soLuongTam = soLuongTam;
    }
}
