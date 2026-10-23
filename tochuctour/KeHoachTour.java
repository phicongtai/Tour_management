package tochuctour;

import tour.Tour;

public class KeHoachTour {

    private String maKeHoachTour;
    private String ngayDi;
    private String ngayVe;
    private int soLuongVeDaBan;
    private double tongThu;
    private double tongChi;
    private Tour tour;

    public KeHoachTour() {}

    public KeHoachTour(String maKeHoachTour, String ngayDi, String ngayVe, int soLuongVeDaBan, double tongThu, double tongChi, Tour tour) {
        this.maKeHoachTour = maKeHoachTour;
        this.ngayDi = ngayDi;
        this.ngayVe = ngayVe;
        this.soLuongVeDaBan = soLuongVeDaBan;
        this.tongThu = tongThu;
        this.tongChi = tongChi;
        this.tour = tour;
    }

    public String getmaKeHoachTour() {
        return this.maKeHoachTour;
    }
    public String getngayDi() {
        return this.ngayDi;
    }
    public String getngayVe() {
        return this.ngayVe;
    }
    public int getsoLuongVeDaBan() {
        return this.soLuongVeDaBan;
    }
    public double gettongThu() {
        return this.tongThu;
    }
    public double gettongChi() {
        return this.tongChi;
    }

    public void setmaKeHoachTour(String maKeHoachTour) {
        this.maKeHoachTour = maKeHoachTour;
    }

    public void setngayDi(String ngayDi) {
        this.ngayDi = ngayDi;
    }

    public void setngayVe(String ngayVe) {
        this.ngayVe = ngayVe;
    }

    public void setsoLuongVeDaBan(int soLuongVeDaBan) {
        this.soLuongVeDaBan = soLuongVeDaBan;
    }

    public void settongThu(double tongThu) {
        this.tongThu = tongThu;
    }

    public void settongChi(double tongChi) {
        this.tongChi = tongChi;
    }

    public int getSoLuongConLai() {
        return this.tour.soLuongHanhKhacToiDa - this.soLuongVeDaBan;
    }
}
