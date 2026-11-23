package kho;

import datvevathanhtoan.DanhSachHoaDon;
import datvevathanhtoan.DanhSachVe;
import nguoidung.DanhSachHanhKhach;
import nguoidung.DanhSachNhanVien;
import tochuctour.DanhSachKeHoachTour;
import tochuctour.DanhSachKhachSan;
import tochuctour.DanhSachPhuongTien;
import tour.DanhSachTour;

public final class KhoDuLieu {

    // 8 danh sach tinh dung chung toan he thong
    private static final DanhSachTour danhSachTour = new DanhSachTour();
    private static final DanhSachNhanVien danhSachNhanVien = new DanhSachNhanVien();
    private static final DanhSachHanhKhach danhSachHanhKhach = new DanhSachHanhKhach();
    private static final DanhSachKeHoachTour danhSachKeHoachTour = new DanhSachKeHoachTour();
    private static final DanhSachPhuongTien danhSachPhuongTien = new DanhSachPhuongTien();
    private static final DanhSachKhachSan danhSachKhachSan = new DanhSachKhachSan();
    private static final DanhSachVe danhSachVe = new DanhSachVe();
    private static final DanhSachHoaDon danhSachHoaDon = new DanhSachHoaDon();

    // Private constructor de ngan tao doi tuong ben ngoai
    private KhoDuLieu() {
    }

    // Getters tinh cho tung danh sach
    public static DanhSachTour getDanhSachTour() {
        return danhSachTour;
    }

    public static DanhSachNhanVien getDanhSachNhanVien() {
        return danhSachNhanVien;
    }

    public static DanhSachHanhKhach getDanhSachHanhKhach() {
        return danhSachHanhKhach;
    }

    public static DanhSachKeHoachTour getDanhSachKeHoachTour() {
        return danhSachKeHoachTour;
    }

    public static DanhSachPhuongTien getDanhSachPhuongTien() {
        return danhSachPhuongTien;
    }

    public static DanhSachKhachSan getDanhSachKhachSan() {
        return danhSachKhachSan;
    }

    public static DanhSachVe getDanhSachVe() {
        return danhSachVe;
    }

    public static DanhSachHoaDon getDanhSachHoaDon() {
        return danhSachHoaDon;
    }
}
