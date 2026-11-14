package giaodien;

import kho.KhoDuLieu;
import tochuctour.PhuongTien;

import java.util.Scanner;

public class Quanlyphuongtien {
    private static void inTieuDeBangPhuongTien() {
        System.out.printf("%-15s | %-25s | %-15s | %-20s%n",
                "Ma Phuong Tien", "Ten Phuong Tien", "So Cho Toi Da", "Gia Moi Nguoi/Ngay");
        System.out.println("------------------------------------------------------------------------------------");
    }

    public static void mainQuanlyphuongtien() {
        Scanner in = KhoDuLieu.getScanner();
        int chose;
        do {
            System.out.println("\n================ QUẢN LÝ PHƯƠNG TIỆN ================");
            System.out.println("1. Xem danh sách\n2. Thêm\n3. Tìm theo mã\n4. Tìm theo tên");
            System.out.println("5. Sửa\n6. Xóa\n7. Nhập nhiều");
            System.out.println("0. Quay lại");
            System.out.println("=====================================================");
            System.out.print("Chọn: ");
            chose = in.nextInt();
            in.nextLine();

            switch (chose) {
                case 1:
                    System.out.println("\n-------- DANH SÁCH TẤT CẢ PHƯƠNG TIỆN --------");
                    inTieuDeBangPhuongTien();
                    KhoDuLieu.getDanhSachPhuongTien().xuat();
                    System.out.println("====================================================================================");
                    System.out.println("\n>> Nhấn Enter để tiếp tục...");
                    in.nextLine();
                    break;
                case 2:
                    System.out.println("\n-------- THEM PHUONG TIEN --------");
                    int viTriMoi = KhoDuLieu.getDanhSachPhuongTien().getSoLuong();
                    KhoDuLieu.getDanhSachPhuongTien().them();
                    System.out.println("ĐÃ THÊM PHƯƠNG TIỆN:");
                    inTieuDeBangPhuongTien();
                    KhoDuLieu.getDanhSachPhuongTien().getDanhSach()[viTriMoi].xuat();
                    System.out.println("\n>> Nhấn Enter để tiếp tục...");
                    in.nextLine();
                    break;
                case 3:
                    System.out.println("\n-------- TIM THEO MA --------");
                    int ma;
                    PhuongTien temp1;
                    do {
                        System.out.print("Vui long nhap ma can tim: ");
                        ma = in.nextInt();
                        in.nextLine();
                        temp1 = KhoDuLieu.getDanhSachPhuongTien().timTheoMa(ma);
                        if (temp1 == null) {
                            System.out.println("Ma khong ton tai vui long nhap lai!");
                        }
                    } while (temp1 == null);
                    System.out.println("\n-------- KET QUA TIM KIEM --------");
                    inTieuDeBangPhuongTien();
                    temp1.xuat();
                    System.out.println("\n>> Nhấn Enter để tiếp tục...");
                    in.nextLine();
                    break;
                case 4:
                    System.out.println("\n-------- TIM THEO TEN --------");
                    String ten;
                    PhuongTien[] dsKetQua;
                    do {
                        System.out.print("Vui long nhap ten phuong tien can tim: ");
                        ten = in.nextLine();
                        dsKetQua = KhoDuLieu.getDanhSachPhuongTien().timTheoTen(ten);
                        if (dsKetQua.length == 0) {
                            System.out.println("Ten phuong tien khong ton tai vui long nhap lai!");
                        }
                    } while (dsKetQua.length == 0);
                    System.out.println("\n-------- KET QUA TIM KIEM --------");
                    inTieuDeBangPhuongTien();
                    for (PhuongTien pt : dsKetQua) {
                        pt.xuat();
                    }
                    System.out.println("\n>> Nhấn Enter để tiếp tục...");
                    in.nextLine();
                    break;
                case 5:
                    System.out.println("\n-------- SUA PHUONG TIEN --------");
                    int ma1;
                    PhuongTien temp2;
                    do {
                        System.out.print("Vui long nhap ma phuong tien can chinh sua: ");
                        ma1 = in.nextInt();
                        in.nextLine();
                        temp2 = KhoDuLieu.getDanhSachPhuongTien().timTheoMa(ma1);
                        if (temp2 == null) {
                            System.out.println("Ma khong ton tai vui long nhap lai!");
                        }
                    } while (temp2 == null);
                    System.out.println("\n-------- THONG TIN BAN DAU --------");
                    inTieuDeBangPhuongTien();
                    temp2.xuat();
                    System.out.println("\n-------- CHINH SUA THONG TIN --------");
                    KhoDuLieu.getDanhSachPhuongTien().suaTheoMa(ma1);
                    System.out.println("\n-------- THONG TIN SAU KHI SUA --------");
                    inTieuDeBangPhuongTien();
                    temp2.xuat();
                    System.out.println("\n>> Nhấn Enter để tiếp tục...");
                    in.nextLine();
                    break;
                case 6:
                    System.out.println("\n-------- XOA THEO MA --------");
                    int ma2;
                    PhuongTien temp3;
                    do {
                        System.out.print("Vui long nhap ma can xoa: ");
                        ma2 = in.nextInt();
                        in.nextLine();
                        temp3 = KhoDuLieu.getDanhSachPhuongTien().timTheoMa(ma2);
                        if (temp3 == null) {
                            System.out.println("Ma khong ton tai vui long nhap lai!");
                        }
                    } while (temp3 == null);
                    KhoDuLieu.getDanhSachPhuongTien().xoaTheoMa(ma2);
                    System.out.println("XOA THANH CONG!");
                    System.out.println("\n>> Nhấn Enter để tiếp tục...");
                    in.nextLine();
                    break;
                case 7:
                    System.out.println("\n-------- NHAP NHIEU PHUONG TIEN --------");
                    System.out.print("Nhap so luong phuong tien can them: ");
                    int soLuongThem = in.nextInt();
                    in.nextLine();
                    if (soLuongThem <= 0) {
                        System.out.println("So luong phai lon hon 0!");
                        break;
                    }
                    int viTriBatDau = KhoDuLieu.getDanhSachPhuongTien().getSoLuong();
                    for (int i = 0; i < soLuongThem; i++) {
                        System.out.println("\n--- Nhap phuong tien thu " + (i + 1) + "/" + soLuongThem + " ---");
                        KhoDuLieu.getDanhSachPhuongTien().them();
                    }
                    System.out.println("\nDA THEM THANH CONG " + soLuongThem + " PHUONG TIEN:");
                    inTieuDeBangPhuongTien();
                    PhuongTien[] dsPt = KhoDuLieu.getDanhSachPhuongTien().getDanhSach();
                    for (int i = viTriBatDau; i < KhoDuLieu.getDanhSachPhuongTien().getSoLuong(); i++) {
                        if (dsPt[i] != null) {
                            dsPt[i].xuat();
                        }
                    }
                    System.out.println("\n>> Nhấn Enter để tiếp tục...");
                    in.nextLine();
                    break;
                case 0:
                    System.out.println("Quay lại menu quản lý...");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ! Vui lòng chọn lại.");
                    break;
            }
        } while (chose != 0);
    }
}
