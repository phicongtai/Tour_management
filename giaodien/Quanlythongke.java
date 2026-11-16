package giaodien;

import baocao.BaoCaoThongKe;
import kho.KhoDuLieu;

import java.util.Scanner;

public class Quanlythongke {
    public static void mainQuanlythongke() {
        Scanner in = KhoDuLieu.getScanner();
        int chose;
        do {
            System.out.println("\n════════ BAO CAO VA THONG KE ════════");
            System.out.println("1. Thong ke hoa don theo khoang ngay");
            System.out.println("2. Thong ke thu - chi - loi nhuan theo quy");
            System.out.println("3. Thong ke hoa don theo nam");
            System.out.println("4. Thong ke hoa don theo nhan vien va quy");
            System.out.println("5. Thong ke hoa don theo tour va quy");
            System.out.println("0. Quay lai");
            System.out.println("=====================================");
            System.out.print("Chon: ");
            chose = in.nextInt();
            in.nextLine();

            switch (chose) {
                case 1:
                    System.out.println("\n-------- THONG KE THEO KHOANG NGAY --------");
                    System.out.print("Nhap tu ngay (yyyy-MM-dd): ");
                    String tuNgay = in.nextLine().trim();
                    System.out.print("Nhap den ngay (yyyy-MM-dd): ");
                    String denNgay = in.nextLine().trim();
                    BaoCaoThongKe.thongKeTheoKhoang(tuNgay, denNgay);
                    System.out.println("\n>> Nhan Enter de tiep tuc...");
                    in.nextLine();
                    break;

                case 2:
                    System.out.print("Nhap nam can thong ke: ");
                    int namQuy = in.nextInt();
                    in.nextLine();
                    BaoCaoThongKe.thongKeTheoQuy(namQuy);
                    System.out.println("\n>> Nhan Enter de tiep tuc...");
                    in.nextLine();
                    break;

                case 3:
                    BaoCaoThongKe.thongKeTheoNam();
                    System.out.println("\n>> Nhan Enter de tiep tuc...");
                    in.nextLine();
                    break;

                case 4:
                    System.out.print("Nhap nam can thong ke: ");
                    int namNV = in.nextInt();
                    in.nextLine();
                    BaoCaoThongKe.thongKeNhanVienTheoQuy(namNV);
                    System.out.println("\n>> Nhan Enter de tiep tuc...");
                    in.nextLine();
                    break;

                case 5:
                    System.out.print("Nhap nam can thong ke: ");
                    int namTour = in.nextInt();
                    in.nextLine();
                    BaoCaoThongKe.thongKeTourTheoQuy(namTour);
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
