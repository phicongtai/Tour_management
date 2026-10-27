package kho;

import tour.DanhSachTour;
import nguoidung.DanhSachNhanVien;
import nguoidung.DanhSachHanhKhach;
import tochuctour.DanhSachKeHoachTour;
import tochuctour.DanhSachPhuongTien;
import tochuctour.DanhSachKhachSan;
import datvevathanhtoan.DanhSachVe;
import datvevathanhtoan.DanhSachHoaDon;
import danhsach.DanhSach;

public final class KhoDuLieu {

    private static final DanhSachTour danhSachTour = new DanhSachTour();
    private static final DanhSachNhanVien danhSachNhanVien = new DanhSachNhanVien();
    private static final DanhSachHanhKhach danhSachHanhKhach = new DanhSachHanhKhach();
    private static final DanhSachKeHoachTour danhSachKeHoachTour = new DanhSachKeHoachTour();
    private static final DanhSachPhuongTien danhSachPhuongTien = new DanhSachPhuongTien();
    private static final DanhSachKhachSan danhSachKhachSan = new DanhSachKhachSan();
    private static final DanhSachVe danhSachVe = new DanhSachVe();
    private static final DanhSachHoaDon danhSachHoaDon = new DanhSachHoaDon();

    private KhoDuLieu() {
    }

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

    public static DanhSach<?>[] layTatCaDanhSach() {
        return new DanhSach<?>[] {
            danhSachTour,
            danhSachNhanVien,
            danhSachHanhKhach,
            danhSachKeHoachTour,
            danhSachPhuongTien,
            danhSachKhachSan,
            danhSachVe,
            danhSachHoaDon
        };
    }
}

}