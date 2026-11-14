package giaodien;

import kho.KhoDuLieu;
import nguoidung.NhanVien;

import java.util.Scanner;

public class Quanlynhanvien {
    public static void mainQuanlynhanvien() {
        Scanner in = KhoDuLieu.getScanner();
        int chose;
        do {
            System.out.println("\n================ QUAN LY NHAN VIEN ================");
            System.out.println("1. Xem danh sach\n2. Them\n3. Tim theo ma\n4. Tim theo ten");
            System.out.println("5. Sua\n6. Xoa\n7. Nhap nhieu");
            System.out.println("0. Quay lai");
            System.out.println("===================================================");
            System.out.print("Chon: ");
            chose = in.nextInt();
            in.nextLine();

            switch (chose) {
                case 1:
                    System.out.println("\n-------- DANH SACH TAT CA NHAN VIEN --------");
                    TieuDeBang.inTieuDeNhanVien();
                    KhoDuLieu.getDanhSachNhanVien().xuat();
                    TieuDeBang.inChanBangNhanVien();
                    System.out.println("\n>> Nhan Enter de tiep tuc...");
                    in.nextLine();
                    break;
                case 2:
                    System.out.println("\n-------- THEM NHAN VIEN --------");
                    int viTriMoi = KhoDuLieu.getDanhSachNhanVien().getSoLuong();
                    KhoDuLieu.getDanhSachNhanVien().them();
                    System.out.println("DA THEM NHAN VIEN:");
                    TieuDeBang.inTieuDeNhanVien();
                    KhoDuLieu.getDanhSachNhanVien().getDanhSach()[viTriMoi].xuat();
                    System.out.println("\n>> Nhan Enter de tiep tuc...");
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
                    TieuDeBang.inTieuDeNhanVien();
                    temp1.xuat();
                    System.out.println("\n>> Nhan Enter de tiep tuc...");
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
                    TieuDeBang.inTieuDeNhanVien();
                    for (NhanVien nv : dsKetQua) {
                        nv.xuat();
                    }
                    System.out.println("\n>> Nhan Enter de tiep tuc...");
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
                    TieuDeBang.inTieuDeNhanVien();
                    temp2.xuat();
                    System.out.println("\n-------- CHINH SUA THONG TIN --------");
                    KhoDuLieu.getDanhSachNhanVien().suaTheoMa(ma1);
                    System.out.println("\n-------- THONG TIN SAU KHI SUA --------");
                    TieuDeBang.inTieuDeNhanVien();
                    temp2.xuat();
                    System.out.println("\n>> Nhan Enter de tiep tuc...");
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
                    System.out.println("\n>> Nhan Enter de tiep tuc...");
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
                    TieuDeBang.inTieuDeNhanVien();
                    NhanVien[] dsNv = KhoDuLieu.getDanhSachNhanVien().getDanhSach();
                    for (int i = viTriBatDau; i < KhoDuLieu.getDanhSachNhanVien().getSoLuong(); i++) {
                        if (dsNv[i] != null) {
                            dsNv[i].xuat();
                        }
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
