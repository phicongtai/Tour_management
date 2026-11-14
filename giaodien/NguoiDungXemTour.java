package giaodien;

import java.util.Scanner;
import kho.KhoDuLieu;
import tour.Tour;

public class NguoiDungXemTour {
    public static void xuLy() {
        Scanner in = KhoDuLieu.getScanner();
        int chose;
        do {
            System.out.println("\n════════ TIM VA XEM TOUR ════════");
            System.out.println("1. Xem tat ca tour");
            System.out.println("2. Tim theo ma");
            System.out.println("3. Tim theo ten");
            System.out.println("0. Quay lai");
            System.out.println("=================================");
            System.out.print("Chon: ");
            chose = in.nextInt();
            in.nextLine();
            if (chose < 0 || chose > 3) {
                System.out.println("\n>> Vui long chon lai!");
            }
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
                    int ma;
                    System.out.println("\n-------- TIM TOUR THEO MA --------");
                    System.out.print("Vui long nhap ma tour: ");
                    ma = in.nextInt();
                    in.nextLine();
                    Tour temp = KhoDuLieu.getDanhSachTour().timTheoMa(ma);
                    System.out.println();
                    if (temp != null) {
                        TieuDeBang.inTieuDeTour();
                        temp.xuat();
                        TieuDeBang.inChanBangTour();
                    } else {
                        System.out.println(">> Khong tim thay tour!");
                    }
                    break;
                case 3:
                    String ten;
                    System.out.println("\n-------- TIM TOUR THEO TEN --------");
                    System.out.print("Vui long nhap ten: ");
                    ten = in.nextLine();
                    Tour[] danhsach = KhoDuLieu.getDanhSachTour().timTheoTen(ten);
                    if (danhsach != null && danhsach.length > 0 && danhsach[0] != null) {
                        TieuDeBang.inTieuDeTour();
                        for (Tour temptour : danhsach) {
                            if (temptour != null) {
                                temptour.xuat();
                            }
                        }
                        TieuDeBang.inChanBangTour();
                    } else {
                        System.out.println(">> Khong ton tai!");
                    }
                    break;
                case 0:
                    System.out.println(">> Dang quay lai menu chinh...");
                    return;
                default:
                    break;
            }
        } while (chose != 0);
    }
}
