package giaodien;

import java.util.Scanner;
import kho.KhoDuLieu;
import nguoidung.HanhKhach;

public class NguoiDungTaiKhoan {
    public static HanhKhach xuLy(HanhKhach nguoidung) {
        Scanner in = KhoDuLieu.getScanner();
        HanhKhach temp1 = null;
        int chose;
        do {
            System.out.println("\n════════ TAI KHOAN HANH KHACH ════════");
            System.out.println("1. Dang nhap bang ma hanh khach");
            System.out.println("2. Dang ky moi");
            System.out.println("3. Cap nhat thong tin ca nhan");
            System.out.println("0. Quay lai");
            System.out.println("========================================");
            System.out.print("Chon: ");
            chose = in.nextInt();
            in.nextLine();
            if (chose < 0 || chose > 3) {
                System.out.println("\n>> Vui long chon lai!");
            }
        } while (chose < 0 || chose > 3);

        switch (chose) {
            case 1:
                int chose1;
                System.out.println("\n-------- DANG NHAP --------");
                System.out.print("Vui long nhap ma khach hang: ");
                chose1 = in.nextInt();
                in.nextLine();
                temp1 = KhoDuLieu.getDanhSachHanhKhach().timTheoMa(chose1);
                if (temp1 != null) {
                    System.out.println(">> Dang nhap thanh cong! Xin chao " + temp1.getHoTen());
                    TieuDeBang.inTieuDeHanhKhach();
                    temp1.xuat();
                    TieuDeBang.inChanBangHanhKhach();
                    return temp1;
                } else {
                    System.out.println(">> Khong tim thay khach hang co ma: " + chose1);
                    return xuLy(nguoidung);
                }

            case 2:
                System.out.println("\n-------- DANG KY TAI KHOAN MOI --------");
                temp1 = new HanhKhach();
                temp1.nhap();
                KhoDuLieu.getDanhSachHanhKhach().them(temp1);
                System.out.println(">> Dang ky thanh cong! Xin chao " + temp1.getHoTen());
                TieuDeBang.inTieuDeHanhKhach();
                temp1.xuat();
                TieuDeBang.inChanBangHanhKhach();
                return temp1;

            case 3:
                int chose3;
                if (nguoidung == null) {
                    System.out.println(">> Ban chua dang nhap! Vui long chon 1 de dang nhap truoc khi cap nhat.");
                    return null;
                }
                System.out.println("\n-------- CAP NHAT THONG TIN CA NHAN --------");
                chose3 = nguoidung.getMaHanhKhach();
                KhoDuLieu.getDanhSachHanhKhach().suaTheoMa(chose3);
                temp1 = KhoDuLieu.getDanhSachHanhKhach().timTheoMa(chose3);
                if (temp1 != null) {
                    System.out.println("\n>> Thong tin ca nhan hien tai:");
                    TieuDeBang.inTieuDeHanhKhach();
                    temp1.xuat();
                    TieuDeBang.inChanBangHanhKhach();
                }
                return temp1;

            case 0:
                System.out.println(">> Dang quay lai menu chinh...");
                return null;

            default:
                return null;
        }
    }
}
