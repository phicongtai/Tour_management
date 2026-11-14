package giaodien;

import kho.KhoDuLieu;

import java.util.Scanner;

public class MenuQuanLy {
    public static void menuquanly() {
        Scanner in = KhoDuLieu.getScanner();
        int chose;
        do {
            System.out.println("\n================ MENU QUAN LY ================");
            System.out.println("1. Quan ly tour");
            System.out.println("2. Quan ly nhan vien");
            System.out.println("3. Quan ly hanh khach");
            System.out.println("4. Quan ly phuong tien");
            System.out.println("5. Quan ly khach san");
            System.out.println("6. Quan ly ke hoach tour");
            System.out.println("7. Quan ly ve");
            System.out.println("8. Quan ly hoa don va thanh toan");
            System.out.println("9. Bao cao va thong ke");
            System.out.println("10. Doc / Ghi file");
            System.out.println("0. Quay lai");
            System.out.println("==============================================");
            System.out.print("Chon: ");
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
                case 6:
                    Quanlykehoachtour.mainQuanlykehoachtour();
                    break;
                case 7:
                    Quanlyve.mainQuanlyve();
                    break;
                case 8:
                    Quanlyhoadon.mainQuanlyhoadon();
                    break;
                case 9:
                    Quanlythongke.mainQuanlythongke();
                    break;
                case 10:
                    Quanlydocghifile.mainQuanlydocghifile();
                    break;
                case 0:
                    System.out.println("Quay lai menu chinh...");
                    break;
                default:
                    System.out.println("Lua chon khong hop le! Vui long chon lai.");
                    break;
            }
        } while (chose != 0);
    }
}
