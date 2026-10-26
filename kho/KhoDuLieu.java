package kho;

import datvevathanhtoan.DanhSachDonDatTour;
import datvevathanhtoan.DanhSachHoaDon;
import datvevathanhtoan.DanhSachVe;

import nguoidung.DanhSachHanhKhach;
import nguoidung.DanhSachNhanVien;

import tochuctour.DanhSachKeHoachTour;
import tochuctour.DanhSachKhachSan;
import tochuctour.DanhSachPhuongTien;

import tour.DanhSachTour;

import danhsach.DanhSach;

public final class KhoDuLieu {

    // 1. Nhóm quản lý tour
    private static final DanhSachTour danhSachTour =
            new DanhSachTour();

    // 2. Nhóm người dùng
    private static final DanhSachNhanVien danhSachNhanVien =
            new DanhSachNhanVien();

    private static final DanhSachHanhKhach danhSachHanhKhach =
            new DanhSachHanhKhach();

    // 3. Nhóm tổ chức tour
    private static final DanhSachKeHoachTour danhSachKeHoachTour =
            new DanhSachKeHoachTour();

    private static final DanhSachPhuongTien danhSachPhuongTien =
            new DanhSachPhuongTien();

    private static final DanhSachKhachSan danhSachKhachSan =
            new DanhSachKhachSan();

    // 4. Nhóm đặt vé và thanh toán
    private static final DanhSachDonDatTour danhSachDonDatTour =
            new DanhSachDonDatTour();

    private static final DanhSachVe danhSachVe =
            new DanhSachVe();

    private static final DanhSachHoaDon danhSachHoaDon =
            new DanhSachHoaDon();

    // Không cho phép tạo đối tượng KhoDuLieu bên ngoài.
    private KhoDuLieu() {
    }

    // ==================== GETTER ====================

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

    public static DanhSachDonDatTour getDanhSachDonDatTour() {
        return danhSachDonDatTour;
    }

    public static DanhSachVe getDanhSachVe() {
        return danhSachVe;
    }

    public static DanhSachHoaDon getDanhSachHoaDon() {
        return danhSachHoaDon;
    }

    // Trả về toàn bộ danh sách.
    public static DanhSach<?>[] layTatCaDanhSach() {
        return new DanhSach<?>[] {
            danhSachTour,
            danhSachNhanVien,
            danhSachHanhKhach,
            danhSachKeHoachTour,
            danhSachPhuongTien,
            danhSachKhachSan,
            danhSachDonDatTour,
            danhSachVe,
            danhSachHoaDon
        };
    }
}