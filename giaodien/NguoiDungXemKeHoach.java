package giaodien;

import java.util.Scanner;
import kho.KhoDuLieu;
import tochuctour.ChiTietKeHoachTour;
import tochuctour.KeHoachTour;
import tour.Tour;

public class NguoiDungXemKeHoach {
    private static final Scanner in = new Scanner(System.in);
    public static void xuLy() {
        System.out.println("\n════════ XEM KE HOACH VA LICH TRINH ════════");
        System.out.print("Vui long nhap ma tour: ");
        int matour = in.nextInt();
        in.nextLine();
        Tour t = KhoDuLieu.getDanhSachTour().timTheoMa(matour);
        if (t == null) {
            System.out.println(">> Khong tim thay tour co ma: " + matour);
            return;
        }
        System.out.println("\n-------- THONG TIN TOUR --------");
        TieuDeBang.inTieuDeTour();
        t.xuat();
        System.out.println();
        KeHoachTour[] listkehoachtour = KhoDuLieu.getDanhSachKeHoachTour().timTheoMaTour(matour);
        if (listkehoachtour.length == 0) {
            System.out.println(">> Tour nay hien chua co ke hoach tour nao!");
            return;
        }
        System.out.println("-------- DANH SACH KE HOACH TOUR --------");
        for (KeHoachTour temp : listkehoachtour) {
            if (temp == null) {
                continue;
            }
            TieuDeBang.inTieuDeKeHoach();
            temp.xuat();
            if (temp.getChiTietLichTrinh() != null && temp.getSoLuongChiTiet() > 0) {
                TieuDeBang.inTieuDeChiTietKeHoach();
                for (ChiTietKeHoachTour temp2 : temp.getChiTietLichTrinh()) {
                    if (temp2 != null) {
                        temp2.xuat();
                    }
                }
            } else {
                System.out.println(">> Chua co chi tiet lich trinh cho ke hoach nay.");
            }
            TieuDeBang.inChanBangKeHoach();
        }
    }
}
