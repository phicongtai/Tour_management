package datvevathanhtoan;

import java.util.Scanner;
import kho.KhoDuLieu;
import tochuctour.DanhSachKeHoachTour;
import tochuctour.KeHoachTour;
import nguoidung.DanhSachHanhKhach;
import tour.DanhSachTour;
import tour.Tour;

public class Ve {
    public static final int DA_DAT = 0;
    public static final int DA_THANH_TOAN = 1;
    public static final int DA_HUY = 2;

    private int maVe;
    private int maDonDatTour;
    private int maNguoiDat;
    private int maKeHoachTour;
    private int maHanhKhach;
    private String ngayDat;
    private long donGia;
    private int trangThai;

    public Ve() {
    }

    public Ve(int maVe, int maDonDatTour, int maNguoiDat, int maKeHoachTour,
              int maHanhKhach, String ngayDat, long donGia, int trangThai) {
        this.maVe = maVe;
        this.maDonDatTour = maDonDatTour;
        this.maNguoiDat = maNguoiDat;
        this.maKeHoachTour = maKeHoachTour;
        this.maHanhKhach = maHanhKhach;
        this.ngayDat = ngayDat;
        this.donGia = donGia;
        this.trangThai = trangThai;
    }

    public int getMaVe() {
        return maVe;
    }

    public void setMaVe(int maVe) {
        this.maVe = maVe;
    }

    public int getMaDonDatTour() {
        return maDonDatTour;
    }

    public void setMaDonDatTour(int maDonDatTour) {
        this.maDonDatTour = maDonDatTour;
    }

    public int getMaNguoiDat() {
        return maNguoiDat;
    }

    public void setMaNguoiDat(int maNguoiDat) {
        this.maNguoiDat = maNguoiDat;
    }

    public int getMaKeHoachTour() {
        return maKeHoachTour;
    }

    public void setMaKeHoachTour(int maKeHoachTour) {
        this.maKeHoachTour = maKeHoachTour;
    }

    public int getMaHanhKhach() {
        return maHanhKhach;
    }

    public void setMaHanhKhach(int maHanhKhach) {
        this.maHanhKhach = maHanhKhach;
    }

    public String getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(String ngayDat) {
        if (ngayDat != null) {
            this.ngayDat = ngayDat.trim();
        }
    }

    public long getDonGia() {
        return donGia;
    }

    public void setDonGia(long donGia) {
        this.donGia = donGia;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public void nhap(){
        Scanner in = KhoDuLieu.getScanner();
        System.out.print("Nhập mã vé: ");
        this.maVe = in.nextInt();
        in.nextLine();
        System.out.print("Nhập mã đơn đặt tour: ");
        this.maDonDatTour = in.nextInt();
        in.nextLine();
        System.out.print("Nhập mã người đặt: ");
        this.maNguoiDat = in.nextInt();
        in.nextLine();
        System.out.print("Nhập mã kế hoạch tour: ");
        this.maKeHoachTour = in.nextInt();
        in.nextLine();
        System.out.print("Nhập mã hành khách: ");
        this.maHanhKhach = in.nextInt();
        in.nextLine();
        System.out.print("Nhập ngày đặt: ");
        this.ngayDat = in.nextLine();
        System.out.print("Nhập đơn giá: ");
        this.donGia = in.nextInt();
        in.nextLine();
        System.out.print("Nhập trạng thái: ");
        this.trangThai = in.nextInt();
        in.nextLine();
    }

        public void xuat(){
        System.out.println(this.maVe + "|" + this.maDonDatTour + "|" + this.maNguoiDat + "|" + this.maKeHoachTour + "|" + this.maHanhKhach + "|" + this.ngayDat + "|" + this.donGia + "|" + "|" + this.trangThai + "|");
    }

    public boolean chiemCho() {
        return trangThai == DA_DAT || trangThai == DA_THANH_TOAN;
    }

    public boolean duocHuy() {
        return trangThai == DA_DAT;
    }

    public boolean capNhatTrangThai(int trangThaiMoi) {
        if (trangThai != DA_DAT || (trangThaiMoi != DA_THANH_TOAN && trangThaiMoi != DA_HUY)) {
            return false;
        }

        this.trangThai = trangThaiMoi;
        return true;
    }
}
