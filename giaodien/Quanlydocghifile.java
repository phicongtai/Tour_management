package giaodien;

import dulieu.DocGhiFile;
import kho.KhoDuLieu;

import java.util.Scanner;

public class Quanlydocghifile {
    private static final Scanner in = new Scanner(System.in);
    public static void mainQuanlydocghifile() {
        DocGhiFile docGhi = new DocGhiFile();
        int chose;
        do {
            System.out.println("\n════════ DOC / GHI FILE DU LIEU ════════");
            System.out.println("1. Doc tat ca du lieu tu file");
            System.out.println("2. Ghi tat ca du lieu ra file");
            System.out.println("0. Quay lai");
            System.out.println("========================================");
            System.out.print("Chon: ");
            chose = in.nextInt();
            in.nextLine();

            switch (chose) {
                case 1:
                    if (docGhi.docTatCa()) {
                        System.out.println("Da doc toan bo du lieu tu File_txt/ thanh cong!");
                        System.out.println("- Tour: " + KhoDuLieu.getDanhSachTour().getSoLuong()
                                + " | Ke hoach tour: " + KhoDuLieu.getDanhSachKeHoachTour().getSoLuong()
                                + " | Hanh khach: " + KhoDuLieu.getDanhSachHanhKhach().getSoLuong()
                                + " | Nhan vien: " + KhoDuLieu.getDanhSachNhanVien().getSoLuong());
                        System.out.println("- Phuong tien: " + KhoDuLieu.getDanhSachPhuongTien().getSoLuong()
                                + " | Khach san: " + KhoDuLieu.getDanhSachKhachSan().getSoLuong()
                                + " | Ve: " + KhoDuLieu.getDanhSachVe().getSoLuong()
                                + " | Hoa don: " + KhoDuLieu.getDanhSachHoaDon().getSoLuong());
                    } else {
                        System.out.println("Doc du lieu tu file that bai!");
                    }
                    System.out.println("\n>> Nhan Enter de tiep tuc...");
                    in.nextLine();
                    break;

                case 2:
                    if (docGhi.ghiTatCa()) {
                        System.out.println("Da ghi toan bo du lieu ra File_txt/ thanh cong!");
                    } else {
                        System.out.println("Ghi du lieu ra file that bai!");
                    }
                    System.out.println("\n>> Nhan Enter de tiep tuc...");
                    in.nextLine();
                    break;

                case 0:
                    System.out.println("Quay lai menu quan ly...");
                    break;

                default:
                    System.out.println("Lua chon khong hop le! Vui long chon lai.");
                    break;
            }
        } while (chose != 0);
    }
}
