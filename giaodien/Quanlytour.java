package giaodien;

import kho.KhoDuLieu;
import tour.Tour;

import java.util.Scanner;

public class Quanlytour {
    public static void mainQuanlytour() {
        Scanner in = KhoDuLieu.getScanner();
        int chose;
        do {
            System.out.println("\n================ QUAN LY TOUR ================");
            System.out.println("1. Xem danh sach\n2.Them\n3. Tim theo ma\n4. Tim theo ten");
            System.out.println("5. Sua\n6. Xoa\n7. Nhap nhieu");
            System.out.println("0. Quay lai");
            System.out.println("==============================================");
            System.out.print("Chon: ");
            chose = in.nextInt();
            in.nextLine();

            switch (chose) {
                case 1:
                    System.out.println("\n-------- DANH SACH TAT CA TOUR --------");
                    TieuDeBang.inTieuDeTour();
                    KhoDuLieu.getDanhSachTour().xuat();
                    TieuDeBang.inChanBangTour();
                    System.out.println("\n>> Nhan Enter de tiep tuc...");
                    in.nextLine();
                    break;
                case 2:
                    System.out.println("\n-------- THEM TOUR --------");
                    if (KhoDuLieu.getDanhSachTour().them()) {
                        int viTriMoi = KhoDuLieu.getDanhSachTour().getSoLuong() - 1;
                        System.out.println("DA THEM TOUR:");
                        TieuDeBang.inTieuDeTour();
                        KhoDuLieu.getDanhSachTour().getDanhSach()[viTriMoi].xuat();
                    }
                    System.out.println("\n>> Nhan Enter de tiep tuc...");
                    in.nextLine();
                    break;
                case 3:
                    System.out.println("\n-------- TIM THEO MA --------");
                    int ma;
                    Tour temp1;
                    do{
                    System.out.print("Vui long nhap ma cam tim: ");
                    ma = in.nextInt();
                    in.nextLine();
                    temp1 = kho.KhoDuLieu.getDanhSachTour().timTheoMa(ma);
                    if(temp1 == null){
                        System.out.println("Ma khong ton tai vui long nhap lai!");
                    }
                }while(temp1 == null);
                    System.out.println("\n-------- KET QUA TIM KIEM --------");
                    TieuDeBang.inTieuDeTour();
                    temp1.xuat();
                    System.out.println("\n>> Nhan Enter de tiep tuc...");
                    in.nextLine();
                    break;
                case 4:
                    System.out.println("\n-------- TIM THEO TEN --------");
                    String ten;
                    Tour[] dsKetQua;
                    do {
                        System.out.print("Vui long nhap ten tour can tim: ");
                        ten = in.nextLine();
                        dsKetQua = KhoDuLieu.getDanhSachTour().timTheoTen(ten);
                        if (dsKetQua.length == 0) {
                            System.out.println("Ten tour khong ton tai vui long nhap lai!");
                        }
                    } while (dsKetQua.length == 0);
                    System.out.println("\n-------- KET QUA TIM KIEM --------");
                    TieuDeBang.inTieuDeTour();
                    for (Tour t : dsKetQua) {
                        t.xuat();
                    }
                    System.out.println("\n>> Nhan Enter de tiep tuc...");
                    in.nextLine();
                    break;
                case 5:
                    System.out.println("\n-------- TIM THEO MA --------");
                    int ma1;
                    Tour temp2;
                    do{
                    System.out.print("Vui long nhap ma tour can chinh sua: ");
                    ma1 = in.nextInt();
                    in.nextLine();
                    temp2 = kho.KhoDuLieu.getDanhSachTour().timTheoMa(ma1);
                    if(temp2 == null){
                        System.out.println("Ma khong ton tai vui long nhap lai!");
                    }
                }while(temp2 == null);
                    System.out.println("\n-------- THONG TIN BAN DAU --------");
                    TieuDeBang.inTieuDeTour();
                    temp2.xuat();
                    System.out.println("\n-------- CHINH SUA THONG TIN --------");
                    kho.KhoDuLieu.getDanhSachTour().suaTheoMa(ma1);
                    System.out.println("\n-------- THONG TIN SAU KHI SUA --------");
                    TieuDeBang.inTieuDeTour();
                    temp2.xuat();
                    System.out.println("\n>> Nhan Enter de tiep tuc...");
                    in.nextLine();
                    break;
                case 6:
                    System.out.println("\n-------- XOA THEO MA --------");
                    int ma2;
                    Tour temp3;
                    do{
                    System.out.print("Vui long nhap ma cam xoa: ");
                    ma2 = in.nextInt();
                    in.nextLine();
                    temp3 = kho.KhoDuLieu.getDanhSachTour().timTheoMa(ma2);
                    if(temp3 == null){
                        System.out.println("Ma khong ton tai vui long nhap lai!");
                    }
                }while(temp3 == null);
                    kho.KhoDuLieu.getDanhSachTour().xoaTheoMa(ma2);
                    System.out.println("XOA THANH CONG!");
                    System.out.println("\n>> Nhan Enter de tiep tuc...");
                    in.nextLine();
                    break;
                case 7:
                    System.out.println("\n-------- NHAP NHIEU TOUR --------");
                    System.out.print("Nhap so luong tour can them: ");
                    int soLuongThem = in.nextInt();
                    in.nextLine();
                    if (soLuongThem <= 0) {
                        System.out.println("So luong phai lon hon 0!");
                        break;
                    }
                    int viTriBatDau = KhoDuLieu.getDanhSachTour().getSoLuong();
                    for (int i = 0; i < soLuongThem; i++) {
                        System.out.println("\n--- Nhap tour thu " + (i + 1) + "/" + soLuongThem + " ---");
                        if (!KhoDuLieu.getDanhSachTour().them()) {
                            i--;
                        }
                    }
                    System.out.println("\nDA THEM THANH CONG " + soLuongThem + " TOUR:");
                    TieuDeBang.inTieuDeTour();
                    Tour[] dsTour = KhoDuLieu.getDanhSachTour().getDanhSach();
                    for (int i = viTriBatDau; i < KhoDuLieu.getDanhSachTour().getSoLuong(); i++) {
                        if (dsTour[i] != null) {
                            dsTour[i].xuat();
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
