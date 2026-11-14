package giaodien;

import kho.KhoDuLieu;
import nguoidung.NhanVien;

import java.util.Scanner;

public class Quanlynhanvien {
    private static void inTieuDeBangNhanVien() {
        System.out.println("Ma NV | Ho Ten | Ngay Sinh | Gioi Tinh (0:Nu, 1:Nam) | So Dien Thoai");
        System.out.println("---------------------------------------------------------------------");
    }

    public static void mainQuanlynhanvien() {
        Scanner in = KhoDuLieu.getScanner();
        int chose;
        do {
            System.out.println("\n================ QUẢN LÝ NHÂN VIÊN ================");
            System.out.println("1. Xem danh sách\n2. Thêm\n3. Tìm theo mã\n4. Tìm theo tên");
            System.out.println("5. Sửa\n6. Xóa\n7. Nhập nhiều");
            System.out.println("0. Quay lại");
            System.out.println("===================================================");
            System.out.print("Chọn: ");
            chose = in.nextInt();
            in.nextLine();

            switch (chose) {
                case 1:
                    System.out.println("\n-------- DANH SÁCH TẤT CẢ NHÂN VIÊN --------");
                    inTieuDeBangNhanVien();
                    KhoDuLieu.getDanhSachNhanVien().xuat();
                    System.out.println("=====================================================================");
                    System.out.println("\n>> Nhấn Enter để tiếp tục...");
                    in.nextLine();
                    break;
                case 2:
                    System.out.println("\n-------- THEM NHAN VIEN --------");
                    int viTriMoi = KhoDuLieu.getDanhSachNhanVien().getSoLuong();
                    KhoDuLieu.getDanhSachNhanVien().them();
                    System.out.println("ĐÃ THÊM NHÂN VIÊN:");
                    inTieuDeBangNhanVien();
                    KhoDuLieu.getDanhSachNhanVien().getDanhSach()[viTriMoi].xuat();
                    System.out.println("\n>> Nhấn Enter để tiếp tục...");
                    in.nextLine();
                    break;
                case 3:
                    System.out.println("\n-------- TIM THEO MA --------");
                    int ma;
                    NhanVien temp1;
                    do {
                        System.out.print("Vui long nhap ma can tim: ");
                        ma = in.nextInt();
                        in.nextLine();
                        temp1 = KhoDuLieu.getDanhSachNhanVien().timTheoMa(ma);
                        if (temp1 == null) {
                            System.out.println("Ma khong ton tai vui long nhap lai!");
                        }
                    } while (temp1 == null);
                    System.out.println("\n-------- KET QUA TIM KIEM --------");
                    inTieuDeBangNhanVien();
                    temp1.xuat();
                    System.out.println("\n>> Nhấn Enter để tiếp tục...");
                    in.nextLine();
                    break;
                case 4:
                    System.out.println("\n-------- TIM THEO TEN --------");
                    String ten;
                    NhanVien[] dsKetQua;
                    do {
                        System.out.print("Vui long nhap ten nhan vien can tim: ");
                        ten = in.nextLine();
                        dsKetQua = KhoDuLieu.getDanhSachNhanVien().timTheoTen(ten);
                        if (dsKetQua.length == 0) {
                            System.out.println("Ten nhan vien khong ton tai vui long nhap lai!");
                        }
                    } while (dsKetQua.length == 0);
                    System.out.println("\n-------- KET QUA TIM KIEM --------");
                    inTieuDeBangNhanVien();
                    for (NhanVien nv : dsKetQua) {
                        nv.xuat();
                    }
                    System.out.println("\n>> Nhấn Enter để tiếp tục...");
                    in.nextLine();
                    break;
                case 5:
                    System.out.println("\n-------- SUA NHAN VIEN --------");
                    int ma1;
                    NhanVien temp2;
                    do {
                        System.out.print("Vui long nhap ma nhan vien can chinh sua: ");
                        ma1 = in.nextInt();
                        in.nextLine();
                        temp2 = KhoDuLieu.getDanhSachNhanVien().timTheoMa(ma1);
                        if (temp2 == null) {
                            System.out.println("Ma khong ton tai vui long nhap lai!");
                        }
                    } while (temp2 == null);
                    System.out.println("\n-------- THONG TIN BAN DAU --------");
                    inTieuDeBangNhanVien();
                    temp2.xuat();
                    System.out.println("\n-------- CHINH SUA THONG TIN --------");
                    KhoDuLieu.getDanhSachNhanVien().suaTheoMa(ma1);
                    System.out.println("\n-------- THONG TIN SAU KHI SUA --------");
                    inTieuDeBangNhanVien();
                    temp2.xuat();
                    System.out.println("\n>> Nhấn Enter để tiếp tục...");
                    in.nextLine();
                    break;
                case 6:
                    System.out.println("\n-------- XOA THEO MA --------");
                    int ma2;
                    NhanVien temp3;
                    do {
                        System.out.print("Vui long nhap ma can xoa: ");
                        ma2 = in.nextInt();
                        in.nextLine();
                        temp3 = KhoDuLieu.getDanhSachNhanVien().timTheoMa(ma2);
                        if (temp3 == null) {
                            System.out.println("Ma khong ton tai vui long nhap lai!");
                        }
                    } while (temp3 == null);
                    KhoDuLieu.getDanhSachNhanVien().xoaTheoMa(ma2);
                    System.out.println("XOA THANH CONG!");
                    System.out.println("\n>> Nhấn Enter để tiếp tục...");
                    in.nextLine();
                    break;
                case 7:
                    System.out.println("\n-------- NHAP NHIEU NHAN VIEN --------");
                    System.out.print("Nhap so luong nhan vien can them: ");
                    int soLuongThem = in.nextInt();
                    in.nextLine();
                    if (soLuongThem <= 0) {
                        System.out.println("So luong phai lon hon 0!");
                        break;
                    }
                    int viTriBatDau = KhoDuLieu.getDanhSachNhanVien().getSoLuong();
                    for (int i = 0; i < soLuongThem; i++) {
                        System.out.println("\n--- Nhap nhan vien thu " + (i + 1) + "/" + soLuongThem + " ---");
                        KhoDuLieu.getDanhSachNhanVien().them();
                    }
                    System.out.println("\nDA THEM THANH CONG " + soLuongThem + " NHAN VIEN:");
                    inTieuDeBangNhanVien();
                    NhanVien[] dsNv = KhoDuLieu.getDanhSachNhanVien().getDanhSach();
                    for (int i = viTriBatDau; i < KhoDuLieu.getDanhSachNhanVien().getSoLuong(); i++) {
                        if (dsNv[i] != null) {
                            dsNv[i].xuat();
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
