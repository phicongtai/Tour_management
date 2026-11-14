package giaodien;

import datvevathanhtoan.DanhSachHoaDon;
import datvevathanhtoan.DanhSachVe;
import datvevathanhtoan.HoaDon;
import datvevathanhtoan.Ve;
import java.util.Scanner;
import kho.KhoDuLieu;
import nguoidung.HanhKhach;

public class NguoiDungTraCuuVe {
    public static void xuLy() {
        Scanner in = KhoDuLieu.getScanner();
        HanhKhach nguoidung = MenuNguoiDung.yeuCauDangNhap();
        if (nguoidung == null) {
            return;
        }

        DanhSachVe dsVe = KhoDuLieu.getDanhSachVe();
        DanhSachHoaDon dsHD = KhoDuLieu.getDanhSachHoaDon();
        int chose;
        do {
            System.out.println("\n════════ TRA CUU NHOM VE ════════");
            System.out.println("1. Tra theo ma nhom ve");
            System.out.println("2. Tra theo ma ve");
            System.out.println("3. Xem cac nhom ve cua toi");
            System.out.println("0. Quay lai");
            System.out.println("=================================");
            do {
                System.out.print("Vui long chon: ");
                chose = in.nextInt();
                in.nextLine();
                if (chose < 0 || chose > 3) {
                    System.out.println(">> Lua chon khong hop le, vui long chon lai!");
                }
            } while (chose < 0 || chose > 3);

            switch (chose) {
                case 1:
                    System.out.println("\n-------- TRA THEO MA NHOM VE --------");
                    System.out.print("Vui long nhap ma nhom ve: ");
                    int maDon = in.nextInt();
                    in.nextLine();
                    Ve[] dsNhom = dsVe.timTheoMaDonDatTour(maDon);
                    if (dsNhom.length == 0) {
                        System.out.println(">> Khong tim thay nhom ve co ma: " + maDon);
                        break;
                    }
                    System.out.println("\n-------- NHOM VE #" + maDon + " (" + dsVe.layTrangThaiNhom(maDon) + ") --------");
                    MenuNguoiDung.inBangVeNguoiDung(dsNhom);
                    inHoaDonDiKem(dsHD.timTheoMaDonDatTour(maDon));
                    break;

                case 2:
                    System.out.println("\n-------- TRA THEO MA VE --------");
                    System.out.print("Vui long nhap ma ve: ");
                    int maVe = in.nextInt();
                    in.nextLine();
                    Ve ve = dsVe.timTheoMa(maVe);
                    if (ve == null) {
                        System.out.println(">> Khong tim thay ve co ma: " + maVe);
                        break;
                    }
                    MenuNguoiDung.inBangVeNguoiDung(new Ve[] { ve });
                    break;

                case 3:
                    System.out.println("\n-------- CAC NHOM VE CUA TOI --------");
                    int[] dsMaDon = dsVe.layDanhSachMaDonTheoNguoiDat(nguoidung.getMaHanhKhach());
                    if (dsMaDon.length == 0) {
                        System.out.println(">> Ban chua co nhom ve nao!");
                        break;
                    }
                    for (int maDonItem : dsMaDon) {
                        Ve[] nhom = dsVe.timTheoMaDonDatTour(maDonItem);
                        System.out.println("\n-------- NHOM VE #" + maDonItem + " (" + dsVe.layTrangThaiNhom(maDonItem) + ") --------");
                        MenuNguoiDung.inBangVeNguoiDung(nhom);
                        inHoaDonDiKem(dsHD.timTheoMaDonDatTour(maDonItem));
                    }
                    break;

                case 0:
                    System.out.println(">> Dang quay lai menu khach hang...");
                    return;
            }
        } while (chose != 0);
    }

    private static void inHoaDonDiKem(HoaDon[] dsHD) {
        if (dsHD.length == 0) {
            System.out.println(">> Nhom ve nay chua co hoa don.");
            return;
        }
        System.out.println("\n-------- HOA DON DI KEM --------");
        TieuDeBang.inTieuDeHoaDon();
        for (HoaDon hd : dsHD) {
            if (hd != null) {
                hd.xuat();
            }
        }
        TieuDeBang.inChanBangHoaDon();
    }
}
