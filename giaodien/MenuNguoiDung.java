package giaodien;

import datvevathanhtoan.Ve;
import java.util.Scanner;
import kho.KhoDuLieu;
import nguoidung.HanhKhach;

public class MenuNguoiDung {
    private static final Scanner in = new Scanner(System.in);
    private static HanhKhach nguoidung;

    public static HanhKhach yeuCauDangNhap() {
        if (nguoidung == null) {
            System.out.println(">> Ban chua dang nhap! Vui long dang nhap hoac dang ky.");
            nguoidung = NguoiDungTaiKhoan.xuLy(nguoidung);
        }
        return nguoidung;
    }

    static void inBangVeNguoiDung(Ve[] dsNhom) {
        TieuDeBang.inTieuDeVeNguoiDung();
        for (Ve v : dsNhom) {
            if (v != null) {
                HanhKhach hk = KhoDuLieu.getDanhSachHanhKhach().timTheoMa(v.getMaHanhKhach());
                String tenHK = (hk != null) ? hk.getHoTen() : "Khong ro";
                System.out.printf("%-6d | %-8d | %-8d | %-24s | %-12s | %-12d | %-15s%n",
                        v.getMaVe(),
                        v.getMaKeHoachTour(),
                        v.getMaHanhKhach(),
                        tenHK,
                        v.getNgayDat(),
                        v.getDonGia(),
                        v.layTenTrangThai());
            }
        }
        TieuDeBang.inChanBangVeNguoiDung();
    }

    public static void menunguoidung() {
        int chose;
        do {
            System.out.println("════════ KHACH HANG ════════");
            if (nguoidung == null) {
                System.out.println("Tai khoan: [Chua dang nhap]");
            } else {
                System.out.println("Tai khoan: " + nguoidung.getHoTen());
            }
            System.out.println("1. Tai khoan (dang nhap / dang ky / cap nhat)");
            System.out.println("2. Tim va xem tour");
            System.out.println("3. Xem ke hoach va lich trinh");
            System.out.println("4. Dat ve");
            System.out.println("5. Tra cuu nhom ve");
            System.out.println("6. Huy nhom ve");
            System.out.println("0. Quay lai");
            System.out.print("Chon: ");
            chose = in.nextInt();
            in.nextLine();

            switch (chose) {
                case 0:
                    break;
                case 1:
                    HanhKhach kh = NguoiDungTaiKhoan.xuLy(nguoidung);
                    if (kh != null) {
                        nguoidung = kh;
                    }
                    break;
                case 2:
                    NguoiDungXemTour.xuLy();
                    break;
                case 3:
                    NguoiDungXemKeHoach.xuLy();
                    break;
                case 4:
                    NguoiDungDatVe.xuLy();
                    break;
                case 5:
                    NguoiDungTraCuuVe.xuLy();
                    break;
                case 6:
                    NguoiDungHuyVe.xuLy();
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (chose != 0);
    }
}
