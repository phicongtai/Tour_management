package kho;

import java.util.Scanner;
import danhsach.DanhSach;
import datvevathanhtoan.DanhSachHoaDon;
import datvevathanhtoan.DanhSachVe;
import nguoidung.DanhSachHanhKhach;
import nguoidung.DanhSachNhanVien;
import tochuctour.DanhSachKeHoachTour;
import tochuctour.DanhSachKhachSan;
import tochuctour.DanhSachPhuongTien;
import tour.DanhSachTour;

public final class KhoDuLieu {
    private static final Scanner scanner = new Scanner(System.in);

    // 8 danh sach tinh dung chung toan he thong
    private static final DanhSachTour danhSachTour = new DanhSachTour();
    private static final DanhSachNhanVien danhSachNhanVien = new DanhSachNhanVien();
    private static final DanhSachHanhKhach danhSachHanhKhach = new DanhSachHanhKhach();
    private static final DanhSachKeHoachTour danhSachKeHoachTour = new DanhSachKeHoachTour();
    private static final DanhSachPhuongTien danhSachPhuongTien = new DanhSachPhuongTien();
    private static final DanhSachKhachSan danhSachKhachSan = new DanhSachKhachSan();
    private static final DanhSachVe danhSachVe = new DanhSachVe();
    private static final DanhSachHoaDon danhSachHoaDon = new DanhSachHoaDon();

    // Private constructor để ngăn tạo đối tượng bên ngoài
    private KhoDuLieu() {
    }

    public static Scanner getScanner() {
        return scanner;
    }

    public static void dongScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }

    // Getters tĩnh cho từng danh sách
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

    // Trả về toàn bộ 8 danh sách theo thứ tự chuẩn để phục vụ đọc/ghi file
    public static DanhSach<?>[] layTatCaDanhSach() {
        return new DanhSach<?>[] {
            danhSachTour,
            danhSachNhanVien,
            danhSachHanhKhach,
            danhSachPhuongTien,
            danhSachKhachSan,
            danhSachKeHoachTour,
            danhSachVe,
            danhSachHoaDon
        };
    }
}
