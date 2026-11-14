package giaodien;

import kho.KhoDuLieu;
import nguoidung.HanhKhach;

import java.util.Scanner;

public class Quanlyhanhkhach {
    public static void mainQuanlyhanhkhach() {
        Scanner in = KhoDuLieu.getScanner();
        int chose;
        do {
            System.out.println("\n================ QUAN LY HANH KHACH ================");
            System.out.println("1. Xem danh sach\n2. Them\n3. Tim theo ma\n4. Tim theo ten");
            System.out.println("5. Sua\n6. Xoa\n7. Nhap nhieu");
            System.out.println("0. Quay lai");
            System.out.println("====================================================");
            System.out.print("Chon: ");
            chose = in.nextInt();
            in.nextLine();

            switch (chose) {
                case 1:
                    System.out.println("\n-------- DANH SACH TAT CA HANH KHACH --------");
                    TieuDeBang.inTieuDeHanhKhach();
                    KhoDuLieu.getDanhSachHanhKhach().xuat();
                    TieuDeBang.inChanBangHanhKhach();
                    System.out.println("\n>> Nhan Enter de tiep tuc...");
                    in.nextLine();
                    break;
                case 2:
                    System.out.println("\n-------- THEM HANH KHACH --------");
                    int viTriMoi = KhoDuLieu.getDanhSachHanhKhach().getSoLuong();
                    KhoDuLieu.getDanhSachHanhKhach().them();
                    System.out.println("DA THEM HANH KHACH:");
                    TieuDeBang.inTieuDeHanhKhach();
                    KhoDuLieu.getDanhSachHanhKhach().getDanhSach()[viTriMoi].xuat();
                    System.out.println("\n>> Nhan Enter de tiep tuc...");
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
                    TieuDeBang.inTieuDeHanhKhach();
                    temp1.xuat();
                    System.out.println("\n>> Nhan Enter de tiep tuc...");
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
                    TieuDeBang.inTieuDeHanhKhach();
                    for (HanhKhach hk : dsKetQua) {
                        hk.xuat();
                    }
                    System.out.println("\n>> Nhan Enter de tiep tuc...");
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
                    TieuDeBang.inTieuDeHanhKhach();
                    temp2.xuat();
                    System.out.println("\n-------- CHINH SUA THONG TIN --------");
                    KhoDuLieu.getDanhSachHanhKhach().suaTheoMa(ma1);
                    System.out.println("\n-------- THONG TIN SAU KHI SUA --------");
                    TieuDeBang.inTieuDeHanhKhach();
                    temp2.xuat();
                    System.out.println("\n>> Nhan Enter de tiep tuc...");
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
                    System.out.println("\n>> Nhan Enter de tiep tuc...");
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
                    TieuDeBang.inTieuDeHanhKhach();
                    HanhKhach[] dsHk = KhoDuLieu.getDanhSachHanhKhach().getDanhSach();
                    for (int i = viTriBatDau; i < KhoDuLieu.getDanhSachHanhKhach().getSoLuong(); i++) {
                        if (dsHk[i] != null) {
                            dsHk[i].xuat();
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
