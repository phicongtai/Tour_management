package giaodien;

import kho.KhoDuLieu;
import tochuctour.PhuongTien;

import java.util.Scanner;

public class Quanlyphuongtien {
    public static void mainQuanlyphuongtien() {
        Scanner in = KhoDuLieu.getScanner();
        int chose;
        do {
            System.out.println("\n================ QUAN LY PHUONG TIEN ================");
            System.out.println("1. Xem danh sach\n2. Them\n3. Tim theo ma\n4. Tim theo ten");
            System.out.println("5. Sua\n6. Xoa\n7. Nhap nhieu");
            System.out.println("0. Quay lai");
            System.out.println("=====================================================");
            System.out.print("Chon: ");
            chose = in.nextInt();
            in.nextLine();

            switch (chose) {
                case 1:
                    System.out.println("\n-------- DANH SACH TAT CA PHUONG TIEN --------");
                    TieuDeBang.inTieuDePhuongTien();
                    KhoDuLieu.getDanhSachPhuongTien().xuat();
                    TieuDeBang.inChanBangPhuongTien();
                    System.out.println("\n>> Nhan Enter de tiep tuc...");
                    in.nextLine();
                    break;
                case 2:
                    System.out.println("\n-------- THEM PHUONG TIEN --------");
                    int viTriMoi = KhoDuLieu.getDanhSachPhuongTien().getSoLuong();
                    KhoDuLieu.getDanhSachPhuongTien().them();
                    System.out.println("DA THEM PHUONG TIEN:");
                    TieuDeBang.inTieuDePhuongTien();
                    KhoDuLieu.getDanhSachPhuongTien().getDanhSach()[viTriMoi].xuat();
                    System.out.println("\n>> Nhan Enter de tiep tuc...");
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
                    TieuDeBang.inTieuDePhuongTien();
                    temp1.xuat();
                    System.out.println("\n>> Nhan Enter de tiep tuc...");
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
                    TieuDeBang.inTieuDePhuongTien();
                    for (PhuongTien pt : dsKetQua) {
                        pt.xuat();
                    }
                    System.out.println("\n>> Nhan Enter de tiep tuc...");
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
                    TieuDeBang.inTieuDePhuongTien();
                    temp2.xuat();
                    System.out.println("\n-------- CHINH SUA THONG TIN --------");
                    KhoDuLieu.getDanhSachPhuongTien().suaTheoMa(ma1);
                    System.out.println("\n-------- THONG TIN SAU KHI SUA --------");
                    TieuDeBang.inTieuDePhuongTien();
                    temp2.xuat();
                    System.out.println("\n>> Nhan Enter de tiep tuc...");
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
                    System.out.println("\n>> Nhan Enter de tiep tuc...");
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
                    TieuDeBang.inTieuDePhuongTien();
                    PhuongTien[] dsPt = KhoDuLieu.getDanhSachPhuongTien().getDanhSach();
                    for (int i = viTriBatDau; i < KhoDuLieu.getDanhSachPhuongTien().getSoLuong(); i++) {
                        if (dsPt[i] != null) {
                            dsPt[i].xuat();
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
