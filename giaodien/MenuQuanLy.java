package giaodien;

import kho.KhoDuLieu;

import java.util.Scanner;

public class MenuQuanLy {
    public static void menuquanly() {
        Scanner in = KhoDuLieu.getScanner();
        int chose;
        do {
            System.out.println("\n================ MENU QUẢN LÝ ================");
            System.out.println("1. Quản lý tour");
            System.out.println("2. Quản lý nhân viên");
            System.out.println("3. Quản lý hành khách");
            System.out.println("4. Quản lý phương tiện");
            System.out.println("5. Quản lý khách sạn");
            System.out.println("0. Quay lại");
            System.out.println("==============================================");
            System.out.print("Chọn: ");
            chose = in.nextInt();
            in.nextLine();

            switch (chose) {
                case 1:
                    Quanlytour.mainQuanlytour();
                    break;
                case 2:
                    Quanlynhanvien.mainQuanlynhanvien();
                    break;
                case 3:
                    Quanlyhanhkhach.mainQuanlyhanhkhach();
                    break;
                case 4:
                    Quanlyphuongtien.mainQuanlyphuongtien();
                    break;
                case 5:
                    Quanlykhachsan.mainQuanlykhachsan();
                    break;
                case 0:
                    System.out.println("Quay lại menu chính...");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ! Vui lòng chọn lại.");
                    break;
            }
        } while (chose != 0);
    }
}
