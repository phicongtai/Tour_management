package giaodien;

import kho.KhoDuLieu;
import tochuctour.KhachSan;

import java.util.Scanner;

public class Quanlykhachsan {
    private static void inTieuDeBangKhachSan() {
        System.out.printf("%-15s | %-28s | %-18s | %-18s%n",
                "Ma Khach San", "Ten Khach San", "Gia O/Dem", "Gia An/Ngay");
        System.out.println("---------------------------------------------------------------------------------------");
    }

    public static void mainQuanlykhachsan() {
        Scanner in = KhoDuLieu.getScanner();
        int chose;
        do {
            System.out.println("\n================ QUẢN LÝ KHÁCH SẠN ================");
            System.out.println("1. Xem danh sách\n2. Thêm\n3. Tìm theo mã\n4. Tìm theo tên");
            System.out.println("5. Sửa\n6. Xóa\n7. Nhập nhiều");
            System.out.println("0. Quay lại");
            System.out.println("===================================================");
            System.out.print("Chọn: ");
            chose = in.nextInt();
            in.nextLine();

            switch (chose) {
                case 1:
                    System.out.println("\n-------- DANH SÁCH TẤT CẢ KHÁCH SẠN --------");
                    inTieuDeBangKhachSan();
                    KhoDuLieu.getDanhSachKhachSan().xuat();
                    System.out.println("=======================================================================================");
                    System.out.println("\n>> Nhấn Enter để tiếp tục...");
                    in.nextLine();
                    break;
                case 2:
                    System.out.println("\n-------- THEM KHACH SAN --------");
                    int viTriMoi = KhoDuLieu.getDanhSachKhachSan().getSoLuong();
                    KhoDuLieu.getDanhSachKhachSan().them();
                    System.out.println("ĐÃ THÊM KHÁCH SẠN:");
                    inTieuDeBangKhachSan();
                    KhoDuLieu.getDanhSachKhachSan().getDanhSach()[viTriMoi].xuat();
                    System.out.println("\n>> Nhấn Enter để tiếp tục...");
                    in.nextLine();
                    break;
                case 3:
                    System.out.println("\n-------- TIM THEO MA --------");
                    int ma;
                    KhachSan temp1;
                    do {
                        System.out.print("Vui long nhap ma can tim: ");
                        ma = in.nextInt();
                        in.nextLine();
                        temp1 = KhoDuLieu.getDanhSachKhachSan().timTheoMa(ma);
                        if (temp1 == null) {
                            System.out.println("Ma khong ton tai vui long nhap lai!");
                        }
                    } while (temp1 == null);
                    System.out.println("\n-------- KET QUA TIM KIEM --------");
                    inTieuDeBangKhachSan();
                    temp1.xuat();
                    System.out.println("\n>> Nhấn Enter để tiếp tục...");
                    in.nextLine();
                    break;
                case 4:
                    System.out.println("\n-------- TIM THEO TEN --------");
                    String ten;
                    KhachSan[] dsKetQua;
                    do {
                        System.out.print("Vui long nhap ten khach san can tim: ");
                        ten = in.nextLine();
                        dsKetQua = KhoDuLieu.getDanhSachKhachSan().timTheoTen(ten);
                        if (dsKetQua.length == 0) {
                            System.out.println("Ten khach san khong ton tai vui long nhap lai!");
                        }
                    } while (dsKetQua.length == 0);
                    System.out.println("\n-------- KET QUA TIM KIEM --------");
                    inTieuDeBangKhachSan();
                    for (KhachSan ks : dsKetQua) {
                        ks.xuat();
                    }
                    System.out.println("\n>> Nhấn Enter để tiếp tục...");
                    in.nextLine();
                    break;
                case 5:
                    System.out.println("\n-------- SUA KHACH SAN --------");
                    int ma1;
                    KhachSan temp2;
                    do {
                        System.out.print("Vui long nhap ma khach san can chinh sua: ");
                        ma1 = in.nextInt();
                        in.nextLine();
                        temp2 = KhoDuLieu.getDanhSachKhachSan().timTheoMa(ma1);
                        if (temp2 == null) {
                            System.out.println("Ma khong ton tai vui long nhap lai!");
                        }
                    } while (temp2 == null);
                    System.out.println("\n-------- THONG TIN BAN DAU --------");
                    inTieuDeBangKhachSan();
                    temp2.xuat();
                    System.out.println("\n-------- CHINH SUA THONG TIN --------");
                    KhoDuLieu.getDanhSachKhachSan().suaTheoMa(ma1);
                    System.out.println("\n-------- THONG TIN SAU KHI SUA --------");
                    inTieuDeBangKhachSan();
                    temp2.xuat();
                    System.out.println("\n>> Nhấn Enter để tiếp tục...");
                    in.nextLine();
                    break;
                case 6:
                    System.out.println("\n-------- XOA THEO MA --------");
                    int ma2;
                    KhachSan temp3;
                    do {
                        System.out.print("Vui long nhap ma can xoa: ");
                        ma2 = in.nextInt();
                        in.nextLine();
                        temp3 = KhoDuLieu.getDanhSachKhachSan().timTheoMa(ma2);
                        if (temp3 == null) {
                            System.out.println("Ma khong ton tai vui long nhap lai!");
                        }
                    } while (temp3 == null);
                    KhoDuLieu.getDanhSachKhachSan().xoaTheoMa(ma2);
                    System.out.println("XOA THANH CONG!");
                    System.out.println("\n>> Nhấn Enter để tiếp tục...");
                    in.nextLine();
                    break;
                case 7:
                    System.out.println("\n-------- NHAP NHIEU KHACH SAN --------");
                    System.out.print("Nhap so luong khach san can them: ");
                    int soLuongThem = in.nextInt();
                    in.nextLine();
                    if (soLuongThem <= 0) {
                        System.out.println("So luong phai lon hon 0!");
                        break;
                    }
                    int viTriBatDau = KhoDuLieu.getDanhSachKhachSan().getSoLuong();
                    for (int i = 0; i < soLuongThem; i++) {
                        System.out.println("\n--- Nhap khach san thu " + (i + 1) + "/" + soLuongThem + " ---");
                        KhoDuLieu.getDanhSachKhachSan().them();
                    }
                    System.out.println("\nDA THEM THANH CONG " + soLuongThem + " KHACH SAN:");
                    inTieuDeBangKhachSan();
                    KhachSan[] dsKs = KhoDuLieu.getDanhSachKhachSan().getDanhSach();
                    for (int i = viTriBatDau; i < KhoDuLieu.getDanhSachKhachSan().getSoLuong(); i++) {
                        if (dsKs[i] != null) {
                            dsKs[i].xuat();
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
