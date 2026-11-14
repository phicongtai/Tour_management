package giaodien;

import kho.KhoDuLieu;
import nguoidung.HanhKhach;

import java.util.Scanner;

public class Quanlyhanhkhach {
    private static void inTieuDeBangHanhKhach() {
        System.out.println("Ma HK | Ho Ten | Ngay Sinh | Gioi Tinh (0:Nu, 1:Nam) | So Dien Thoai");
        System.out.println("---------------------------------------------------------------------");
    }

    public static void mainQuanlyhanhkhach() {
        Scanner in = KhoDuLieu.getScanner();
        int chose;
        do {
            System.out.println("\n================ QUẢN LÝ HÀNH KHÁCH ================");
            System.out.println("1. Xem danh sách\n2. Thêm\n3. Tìm theo mã\n4. Tìm theo tên");
            System.out.println("5. Sửa\n6. Xóa\n7. Nhập nhiều");
            System.out.println("0. Quay lại");
            System.out.println("====================================================");
            System.out.print("Chọn: ");
            chose = in.nextInt();
            in.nextLine();

            switch (chose) {
                case 1:
                    System.out.println("\n-------- DANH SÁCH TẤT CẢ HÀNH KHÁCH --------");
                    inTieuDeBangHanhKhach();
                    KhoDuLieu.getDanhSachHanhKhach().xuat();
                    System.out.println("=====================================================================");
                    System.out.println("\n>> Nhấn Enter để tiếp tục...");
                    in.nextLine();
                    break;
                case 2:
                    System.out.println("\n-------- THEM HANH KHACH --------");
                    int viTriMoi = KhoDuLieu.getDanhSachHanhKhach().getSoLuong();
                    KhoDuLieu.getDanhSachHanhKhach().them();
                    System.out.println("ĐÃ THÊM HÀNH KHÁCH:");
                    inTieuDeBangHanhKhach();
                    KhoDuLieu.getDanhSachHanhKhach().getDanhSach()[viTriMoi].xuat();
                    System.out.println("\n>> Nhấn Enter để tiếp tục...");
                    in.nextLine();
                    break;
                case 3:
                    System.out.println("\n-------- TIM THEO MA --------");
                    int ma;
                    HanhKhach temp1;
                    do {
                        System.out.print("Vui long nhap ma can tim: ");
                        ma = in.nextInt();
                        in.nextLine();
                        temp1 = KhoDuLieu.getDanhSachHanhKhach().timTheoMa(ma);
                        if (temp1 == null) {
                            System.out.println("Ma khong ton tai vui long nhap lai!");
                        }
                    } while (temp1 == null);
                    System.out.println("\n-------- KET QUA TIM KIEM --------");
                    inTieuDeBangHanhKhach();
                    temp1.xuat();
                    System.out.println("\n>> Nhấn Enter để tiếp tục...");
                    in.nextLine();
                    break;
                case 4:
                    System.out.println("\n-------- TIM THEO TEN --------");
                    String ten;
                    HanhKhach[] dsKetQua;
                    do {
                        System.out.print("Vui long nhap ten hanh khach can tim: ");
                        ten = in.nextLine();
                        dsKetQua = KhoDuLieu.getDanhSachHanhKhach().timTheoTen(ten);
                        if (dsKetQua.length == 0) {
                            System.out.println("Ten hanh khach khong ton tai vui long nhap lai!");
                        }
                    } while (dsKetQua.length == 0);
                    System.out.println("\n-------- KET QUA TIM KIEM --------");
                    inTieuDeBangHanhKhach();
                    for (HanhKhach hk : dsKetQua) {
                        hk.xuat();
                    }
                    System.out.println("\n>> Nhấn Enter để tiếp tục...");
                    in.nextLine();
                    break;
                case 5:
                    System.out.println("\n-------- SUA HANH KHACH --------");
                    int ma1;
                    HanhKhach temp2;
                    do {
                        System.out.print("Vui long nhap ma hanh khach can chinh sua: ");
                        ma1 = in.nextInt();
                        in.nextLine();
                        temp2 = KhoDuLieu.getDanhSachHanhKhach().timTheoMa(ma1);
                        if (temp2 == null) {
                            System.out.println("Ma khong ton tai vui long nhap lai!");
                        }
                    } while (temp2 == null);
                    System.out.println("\n-------- THONG TIN BAN DAU --------");
                    inTieuDeBangHanhKhach();
                    temp2.xuat();
                    System.out.println("\n-------- CHINH SUA THONG TIN --------");
                    KhoDuLieu.getDanhSachHanhKhach().suaTheoMa(ma1);
                    System.out.println("\n-------- THONG TIN SAU KHI SUA --------");
                    inTieuDeBangHanhKhach();
                    temp2.xuat();
                    System.out.println("\n>> Nhấn Enter để tiếp tục...");
                    in.nextLine();
                    break;
                case 6:
                    System.out.println("\n-------- XOA THEO MA --------");
                    int ma2;
                    HanhKhach temp3;
                    do {
                        System.out.print("Vui long nhap ma can xoa: ");
                        ma2 = in.nextInt();
                        in.nextLine();
                        temp3 = KhoDuLieu.getDanhSachHanhKhach().timTheoMa(ma2);
                        if (temp3 == null) {
                            System.out.println("Ma khong ton tai vui long nhap lai!");
                        }
                    } while (temp3 == null);
                    KhoDuLieu.getDanhSachHanhKhach().xoaTheoMa(ma2);
                    System.out.println("XOA THANH CONG!");
                    System.out.println("\n>> Nhấn Enter để tiếp tục...");
                    in.nextLine();
                    break;
                case 7:
                    System.out.println("\n-------- NHAP NHIEU HANH KHACH --------");
                    System.out.print("Nhap so luong hanh khach can them: ");
                    int soLuongThem = in.nextInt();
                    in.nextLine();
                    if (soLuongThem <= 0) {
                        System.out.println("So luong phai lon hon 0!");
                        break;
                    }
                    int viTriBatDau = KhoDuLieu.getDanhSachHanhKhach().getSoLuong();
                    for (int i = 0; i < soLuongThem; i++) {
                        System.out.println("\n--- Nhap hanh khach thu " + (i + 1) + "/" + soLuongThem + " ---");
                        KhoDuLieu.getDanhSachHanhKhach().them();
                    }
                    System.out.println("\nDA THEM THANH CONG " + soLuongThem + " HANH KHACH:");
                    inTieuDeBangHanhKhach();
                    HanhKhach[] dsHk = KhoDuLieu.getDanhSachHanhKhach().getDanhSach();
                    for (int i = viTriBatDau; i < KhoDuLieu.getDanhSachHanhKhach().getSoLuong(); i++) {
                        if (dsHk[i] != null) {
                            dsHk[i].xuat();
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
