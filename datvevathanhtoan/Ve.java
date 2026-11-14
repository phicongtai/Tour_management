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
        this.maVe = KhoDuLieu.getDanhSachVe().getmamoi();
        System.out.println("Ma ve (tu dong): " + this.maVe);
        System.out.print("Nhap ma don dat tour: ");
        this.maDonDatTour = in.nextInt();
        in.nextLine();
        System.out.print("Nhap ma nguoi dat: ");
        this.maNguoiDat = in.nextInt();
        in.nextLine();
        System.out.print("Nhap ma ke hoach tour: ");
        this.maKeHoachTour = in.nextInt();
        in.nextLine();
        System.out.print("Nhap ma hanh khach: ");
        this.maHanhKhach = in.nextInt();
        in.nextLine();
        System.out.print("Nhap ngay dat: ");
        this.ngayDat = in.nextLine();
        System.out.print("Nhap don gia: ");
        this.donGia = in.nextInt();
        in.nextLine();
        System.out.print("Nhap trang thai: ");
        this.trangThai = in.nextInt();
        in.nextLine();
    }

    public void xuat() {
        System.out.printf("%-6s | %-8s | %-12s | %-12s | %-13s | %-12s | %-12s | %-15s%n",
                this.maVe, this.maDonDatTour, this.maNguoiDat, this.maKeHoachTour,
                this.maHanhKhach, this.ngayDat, this.donGia, layTenTrangThai());
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

    public String layTenTrangThai() {
        if (this.trangThai == DA_DAT) {
            return "Da dat";
        }
        if (this.trangThai == DA_THANH_TOAN) {
            return "Da thanh toan";
        }
        if (this.trangThai == DA_HUY) {
            return "Da huy";
        }
        return "Khac";
    }
}
