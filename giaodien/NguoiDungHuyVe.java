package giaodien;

import datvevathanhtoan.DanhSachHoaDon;
import datvevathanhtoan.DanhSachVe;
import datvevathanhtoan.Ve;
import java.util.Scanner;
import kho.KhoDuLieu;
import nguoidung.HanhKhach;

public class NguoiDungHuyVe {
    private static final Scanner in = new Scanner(System.in);
    public static void xuLy() {
        System.out.println("\n════════ HUY NHOM VE ════════");
        HanhKhach nguoidung = MenuNguoiDung.yeuCauDangNhap();
        if (nguoidung == null) {
            return;
        }

        DanhSachVe dsVe = KhoDuLieu.getDanhSachVe();
        DanhSachHoaDon dsHD = KhoDuLieu.getDanhSachHoaDon();

        System.out.print("Nhap ma nhom ve: ");
        int maDon = in.nextInt();
        in.nextLine();

        Ve[] dsNhom = dsVe.timTheoMaDonDatTour(maDon);
        if (dsNhom.length == 0) {
            System.out.println(">> Khong tim thay nhom ve co ma: " + maDon);
            return;
        }

        boolean thuocNguoiDung = false;
        for (Ve v : dsNhom) {
            if (v != null && v.getMaNguoiDat() == nguoidung.getMaHanhKhach()) {
                thuocNguoiDung = true;
                break;
            }
        }
        if (!thuocNguoiDung) {
            System.out.println(">> Ban khong co quyen huy nhom ve nay (nhom ve thuoc ve tai khoan khac)!");
            return;
        }

        System.out.println("\n-------- NHOM VE #" + maDon + " (" + dsVe.layTrangThaiNhom(maDon) + ") --------");
        MenuNguoiDung.inBangVeNguoiDung(dsNhom);

        if (dsHD.donDaThuTien(maDon)) {
            System.out.println(">> Khong the huy: hoa don da thu tien!");
            return;
        }

        boolean coVeDuocHuy = false;
        for (Ve v : dsNhom) {
            if (v != null && v.duocHuy()) {
                coVeDuocHuy = true;
                break;
            }
        }
        if (!coVeDuocHuy) {
            System.out.println(">> Nhom ve nay khong co ve nao co the huy!");
            return;
        }

        System.out.print("Huy ca nhom? (Y/N): ");
        String xacNhan = in.nextLine().trim();
        if (!xacNhan.equalsIgnoreCase("Y")) {
            System.out.println(">> Da giu nguyen nhom ve.");
            return;
        }

        int soVeDaHuy = dsVe.huyNhomVe(maDon);
        System.out.println(">> Da huy " + soVeDaHuy + " ve.");

        System.out.println("\n-------- NHOM VE #" + maDon + " SAU KHI HUY (" + dsVe.layTrangThaiNhom(maDon) + ") --------");
        MenuNguoiDung.inBangVeNguoiDung(dsNhom);
    }
}
