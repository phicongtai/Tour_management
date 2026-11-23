package giaodien;

import datvevathanhtoan.DanhSachHoaDon;
import datvevathanhtoan.DanhSachVe;
import datvevathanhtoan.HoaDon;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import kho.KhoDuLieu;
import nguoidung.DanhSachHanhKhach;
import nguoidung.HanhKhach;
import tochuctour.ChiTietKeHoachTour;
import tochuctour.KeHoachTour;
import tour.Tour;

public class NguoiDungDatVe {
    private static final Scanner in = new Scanner(System.in);
    public static void xuLy() {
        int matour;
        int chosekh;
        Tour tourDaChon;
        KeHoachTour keHoachDaChon;

        System.out.println("\n════════ DAT VE TOUR ════════");
        HanhKhach nguoidung = MenuNguoiDung.yeuCauDangNhap();
        if (nguoidung == null) {
            return;
        }

        // Xuat danh sach tour va chon tour muon dat
        System.out.println("\n-------- DANH SACH TOUR --------");
        TieuDeBang.inTieuDeTour();
        KhoDuLieu.getDanhSachTour().xuat();
        TieuDeBang.inChanBangTour();

        do {
            System.out.print("Chon tour (nhap ma tour): ");
            matour = in.nextInt();
            in.nextLine();
            tourDaChon = KhoDuLieu.getDanhSachTour().timTheoMa(matour);
            if (tourDaChon == null) {
                System.out.println(">> Ma tour khong ton tai, vui long chon lai!");
            }
        } while (tourDaChon == null);

        KeHoachTour[] dskh = KhoDuLieu.getDanhSachKeHoachTour().timTheoMaTour(matour);
        if (dskh.length == 0) {
            System.out.println(">> Tour nay chua co ke hoach, vui long chon tour khac!");
            return;
        }

        // Xuat tat ca ke hoach tour va chon ke hoach muon di
        System.out.println("\n-------- DANH SACH KE HOACH TOUR --------");
        for (KeHoachTour temp : dskh) {
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
            }
            TieuDeBang.inChanBangKeHoach();
        }

        do {
            System.out.print("Chon ke hoach tour (nhap ma ke hoach): ");
            chosekh = in.nextInt();
            in.nextLine();
            keHoachDaChon = KhoDuLieu.getDanhSachKeHoachTour().timTheoMaVaTour(chosekh, matour);
            if (keHoachDaChon == null) {
                System.out.println(">> Khong ton tai ke hoach tour nay thuoc tour da chon!");
            }
        } while (keHoachDaChon == null);

        // Kiem tra nguoi dung da dat ve cho ke hoach nay chua
        DanhSachVe dsVe = KhoDuLieu.getDanhSachVe();
        if (dsVe.daCoVeHopLe(keHoachDaChon.getMaKeHoachTour(), nguoidung.getMaHanhKhach())) {
            System.out.println("\n>> Ban da dat ve cho ke hoach tour nay roi!");
            return;
        }

        // Kiem tra so cho con lai
        int soVeDaDat = dsVe.demVeChiemChoTheoKeHoach(keHoachDaChon.getMaKeHoachTour());
        int soluongvecon = keHoachDaChon.soChoConLai(soVeDaDat);
        if (soluongvecon <= 0) {
            System.out.println("\n>> Ke hoach tour nay da het cho, vui long chon ke hoach hoac tour khac!");
            return;
        }

        int chosesoluongve;
        System.out.println();
        do {
            System.out.print("Chon so luong ve (con " + soluongvecon + " cho): ");
            chosesoluongve = in.nextInt();
            in.nextLine();
            if (!keHoachDaChon.conDuCho(soVeDaDat, chosesoluongve)) {
                System.out.println(">> So luong khong hop le! Chi con " + soluongvecon + " cho.");
            }
        } while (!keHoachDaChon.conDuCho(soVeDaDat, chosesoluongve));

        // Nhap danh sach hanh khach di cung
        System.out.println("\n-------- NHAP HANH KHACH DI CUNG --------");
        int[] dsmakh = new int[chosesoluongve];
        dsmakh[0] = nguoidung.getMaHanhKhach();
        System.out.println(">> Da luu ma hanh khach cua ban (" + nguoidung.getMaHanhKhach() + " - " + nguoidung.getHoTen() + ").");

        DanhSachHanhKhach dsHK = KhoDuLieu.getDanhSachHanhKhach();
        for (int i = 1; i < chosesoluongve; i++) {
            int makh;
            int kq;
            do {
                System.out.print("Nhap ma hanh khach thu " + (i + 1) + ": ");
                makh = in.nextInt();
                in.nextLine();
                kq = dsVe.kiemTraHanhKhachHopLe(keHoachDaChon.getMaKeHoachTour(), makh, dsmakh, i, dsHK);
                if (kq == 1) {
                    System.out.println(">> Ma hanh khach khong ton tai, vui long nhap lai!");
                } else if (kq == 2) {
                    System.out.println(">> Ma hanh khach bi trung trong don, vui long nhap lai!");
                } else if (kq == 3) {
                    System.out.println(">> Hanh khach nay da co ve cho ke hoach tour nay!");
                }
            } while (kq != 0);
            dsmakh[i] = makh;
        }

        // Tinh gia ve va in tom tat dat ve
        long giaTour = tourDaChon.tinhGiaVe();
        long chiphikehoac = keHoachDaChon.tinhTongChiPhi(1);
        long dongia = keHoachDaChon.tinhDonGiaVe(tourDaChon);
        long tongTien = dongia * chosesoluongve;

        System.out.println("\n================ TOM TAT DAT VE ================");
        TieuDeBang.inTieuDeTour();
        tourDaChon.xuat();
        System.out.println();

        TieuDeBang.inTieuDeKeHoach();
        keHoachDaChon.xuat();
        if (keHoachDaChon.getChiTietLichTrinh() != null && keHoachDaChon.getSoLuongChiTiet() > 0) {
            System.out.println();
            TieuDeBang.inTieuDeChiTietKeHoach();
            for (ChiTietKeHoachTour temp : keHoachDaChon.getChiTietLichTrinh()) {
                if (temp != null) {
                    temp.xuat();
                }
            }
        }
        System.out.println("------------------------------------------------");
        System.out.println("Gia tour: " + giaTour);
        System.out.println("Chi phi lich trinh (1 khach): " + chiphikehoac);
        System.out.println("Don gia 1 ve: " + dongia);
        System.out.println("So luong ve: " + chosesoluongve);
        System.out.println("Tong tien: " + tongTien);
        System.out.println("================================================");

        int chose;
        System.out.println("Xac nhan dat ve (1. Dong y / 0. Huy)");
        System.out.print("Vui long chon: ");
        chose = in.nextInt();
        in.nextLine();
        if (chose != 1) {
            System.out.println(">> Da huy dat ve.");
            return;
        }

        DanhSachHoaDon dsHD = KhoDuLieu.getDanhSachHoaDon();
        int maDonDatTourMoi = dsVe.getmadondattourmoi();
        System.out.print("Nhap ngay hom nay (vd: 2026-11-13): ");
        String ngayHienTai = in.nextLine().trim();

        int[] dsMaVeMoi = dsVe.taoNhieuVe(
                maDonDatTourMoi,
                nguoidung.getMaHanhKhach(),
                keHoachDaChon.getMaKeHoachTour(),
                dsmakh,
                ngayHienTai,
                dongia);

        HoaDon hdMoi = dsHD.taoHoaDonChoDon(
                maDonDatTourMoi,
                keHoachDaChon.getMaNhanVien(),
                ngayHienTai,
                dsMaVeMoi,
                dsVe);

        if (dsMaVeMoi.length == 0 || hdMoi == null) {
            System.out.println(">> Co loi xay ra khi tao ve hoac hoa don!");
            return;
        }

        int chonThanhToan;
        do {
            System.out.println("\n-------- THANH TOAN --------");
            System.out.println("1. Thanh toan het (" + hdMoi.tinhTongTien() + ")");
            System.out.println("2. Dat coc mot phan");
            System.out.println("0. Thanh toan sau");
            System.out.print("Chon: ");
            chonThanhToan = in.nextInt();
            in.nextLine();
            if (chonThanhToan < 0 || chonThanhToan > 2) {
                System.out.println(">> Vui long chon lai!");
            }
        } while (chonThanhToan < 0 || chonThanhToan > 2);

        if (chonThanhToan == 1) {
            hdMoi.ghiNhanThanhToan(hdMoi.tinhTongTien(), dsVe);
        } else if (chonThanhToan == 2) {
            boolean ok;
            do {
                System.out.print("Nhap so tien muon dat coc (toi da " + hdMoi.tinhTongTien() + "): ");
                long soTien = in.nextLong();
                in.nextLine();
                ok = hdMoi.ghiNhanThanhToan(soTien, dsVe);
                if (!ok) {
                    System.out.println(">> So tien khong hop le (phai > 0 va <= " + hdMoi.tinhTongTien() + ")!");
                }
            } while (!ok);
        }

        System.out.println("\n════════ DAT VE THANH CONG ════════");
        System.out.println("Ma don dat tour: " + maDonDatTourMoi);
        System.out.println("Ma hoa don: " + hdMoi.getMaHoaDon());
        System.out.print("Danh sach ma ve: ");
        for (int i = 0; i < dsMaVeMoi.length; i++) {
            System.out.print(dsMaVeMoi[i] + (i < dsMaVeMoi.length - 1 ? ", " : ""));
        }
        System.out.println();
        System.out.println("Tong tien: " + hdMoi.tinhTongTien());
        System.out.println("So tien da thanh toan: " + hdMoi.getSoTienDaThanhToan());
        System.out.println("So tien con lai: " + hdMoi.tinhSoTienConLai());
        System.out.println("====================================");

        String duongDanFileVe = "Ve_xuat/ve_don_" + maDonDatTourMoi + ".txt";
        if (xuatVeRaFile(duongDanFileVe, maDonDatTourMoi, hdMoi, dsMaVeMoi)) {
            System.out.println(">> Da xuat ve ra file: " + duongDanFileVe);
        } else {
            System.out.println(">> Xuat ve ra file that bai!");
        }
    }

    private static boolean xuatVeRaFile(String duongDan,int maDonDatTour,HoaDon hoaDon,int[] dsMaVe) {
        if (duongDan == null || hoaDon == null || dsMaVe == null) {
            return false;
        }
        try {
            File parent = new File(duongDan).getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
        } catch (Exception e) {
            return false;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(duongDan))) {
            writer.write("════════ DAT VE THANH CONG ════════");
            writer.newLine();
            writer.write("Ma don dat tour: " + maDonDatTour);
            writer.newLine();
            writer.write("Ma hoa don: " + hoaDon.getMaHoaDon());
            writer.newLine();
            String dsMaVeStr = "";
            for (int i = 0; i < dsMaVe.length; i++) {
                dsMaVeStr += dsMaVe[i] + (i < dsMaVe.length - 1 ? ", " : "");
            }
            writer.write("Danh sach ma ve: " + dsMaVeStr);
            writer.newLine();
            writer.write("Tong tien: " + hoaDon.tinhTongTien());
            writer.newLine();
            writer.write("So tien da thanh toan: " + hoaDon.getSoTienDaThanhToan());
            writer.newLine();
            writer.write("So tien con lai: " + hoaDon.tinhSoTienConLai());
            writer.newLine();
            writer.write("====================================");
            writer.newLine();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
