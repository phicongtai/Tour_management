package baocao;

import datvevathanhtoan.DanhSachHoaDon;
import datvevathanhtoan.DanhSachVe;
import datvevathanhtoan.HoaDon;
import datvevathanhtoan.Ve;
import dulieu.DocGhiFile;
import kho.KhoDuLieu;
import nguoidung.DanhSachNhanVien;
import nguoidung.NhanVien;
import tochuctour.DanhSachKeHoachTour;
import tochuctour.KeHoachTour;
import tour.DanhSachTour;
import tour.Tour;

/**
 * Module bao cao va thong ke cho he thong quan ly tour du lich.
 * Moi loai thong ke la mot phuong thuc doc lap (public static void).
 */
public class BaoCaoThongKe {

    private static final class KetQuaTaiChinh {
        int soHoaDon;
        long tongTienHd;
        long thucThu;
        long congNo;
        long tongChi;

        long loiNhuanThucThu() {
            return thucThu - tongChi;
        }
    }

    public BaoCaoThongKe() {
    }

    /**
     * 1. Tinh tong tien hoa don, thuc thu, cong no, tong chi va loi nhuan tu ngay a den ngay b (yyyy-MM-dd).
     */
    public static void thongKeTheoKhoang(String tuNgay, String denNgay) {
        damBaoDuLieu(); // nó la kiểm tra toàn bộ xem coi có danh sách nào có kích thước = 0 hay không nếu có văng

        String ngayBatDau = tuNgay == null ? "" : tuNgay.trim();
        String ngayKetThuc = denNgay == null ? "" : denNgay.trim();
        if (!ngayBatDau.isEmpty() && !ngayKetThuc.isEmpty() && ngayBatDau.compareTo(ngayKetThuc) > 0) {
            String tmp = ngayBatDau;
            ngayBatDau = ngayKetThuc;
            ngayKetThuc = tmp;
        }

        KetQuaTaiChinh kq = tinhTaiChinhTheoKhoang(ngayBatDau, ngayKetThuc);

        System.out.println("\n--- THONG KE TONG TIEN HOA DON TU " + tuNgay + " DEN " + denNgay + " ---");
        System.out.println("So hoa don  : " + kq.soHoaDon);
        System.out.println("Tong tien HD: " + kq.tongTienHd);
        System.out.println("Thuc thu    : " + kq.thucThu);
        System.out.println("Cong no     : " + kq.congNo);
        System.out.println("Tong chi    : " + kq.tongChi);
        System.out.println("Loi nhuan   : " + kq.loiNhuanThucThu());
    }

    /**
     * 2b. Thong ke theo Quy trong nam:
     * Cot: Nam YYYY | Q1 | Q2 | Q3 | Q4 | TC
     * Dong: Tg Thu (HD), Thuc Thu, Tg Chi, Loi Nhuan.
     */
    public static void thongKeTheoQuy(int nam) {
        damBaoDuLieu();

        long[] hdQuy = new long[4];
        long[] thucThuQuy = new long[4];
        long[] chiQuy = new long[4];
        long[] loiNhuanQuy = new long[4];

        long tcHd = 0;
        long tcThucThu = 0;
        long tcChi = 0;
        long tcLoiNhuan = 0;

        String[] dauQuyStr = {"-01-01", "-04-01", "-07-01", "-10-01"};
        String[] cuoiQuyStr = {"-03-31", "-06-30", "-09-30", "-12-31"};
        for (int q = 0; q < 4; q++) {
            KetQuaTaiChinh kq = tinhTaiChinhTheoKhoang(nam + dauQuyStr[q], nam + cuoiQuyStr[q]);

            hdQuy[q] = kq.tongTienHd;
            thucThuQuy[q] = kq.thucThu;
            chiQuy[q] = kq.tongChi;
            loiNhuanQuy[q] = kq.loiNhuanThucThu();

            tcHd += hdQuy[q];
            tcThucThu += thucThuQuy[q];
            tcChi += chiQuy[q];
            tcLoiNhuan += loiNhuanQuy[q];
        }

        System.out.println("\n--- THONG KE THU - CHI - LOI NHUAN THEO QUY (NAM " + nam + ") ---");
        System.out.printf("%-14s | %-15s | %-15s | %-15s | %-15s | %-16s%n",
                "Nam " + nam, "Q1", "Q2", "Q3", "Q4", "TC");
        System.out.println("---------------------------------------------------------------------------------------------------------");

        inDongBangQuy("Tg Thu (HD)", hdQuy, tcHd);
        inDongBangQuy("Thuc Thu", thucThuQuy, tcThucThu);
        inDongBangQuy("Tg Chi", chiQuy, tcChi);
        System.out.println("---------------------------------------------------------------------------------------------------------");
        inDongBangQuy("Loi Nhuan", loiNhuanQuy, tcLoiNhuan);
    }

    private static void inDongBangQuy(String nhan, long[] quy, long tongCong) {
        System.out.printf("%-14s | %-15d | %-15d | %-15d | %-15d | %-16d%n",
                nhan,
                quy[0],
                quy[1],
                quy[2],
                quy[3],
                tongCong);
    }

    /**
     * 2c. Thong ke tong tien hoa don, chi phi va loi nhuan theo tung Nam.
     */
    public static void thongKeTheoNam() {
        damBaoDuLieu();

        int[] dsNam = layDanhSachNamHoaDon();
        System.out.println("\n--- THONG KE TONG TIEN HOA DON THEO NAM ---");

        if (dsNam.length == 0) {
            System.out.println("Chua co du lieu hoa don hop le de thong ke.");
            return;
        }

        System.out.printf("%-6s | %-6s | %-15s | %-15s | %-15s | %-15s | %-16s%n",
                "Nam", "So HD", "Tg Tien HD", "Thuc Thu", "Cong No", "Tg Chi", "Loi Nhuan");
        System.out.println("--------------------------------------------------------------------------------------------------------------");

        int tongHd = 0;
        long tongTien = 0;
        long tongThu = 0;
        long tongNo = 0;
        long tongChi = 0;

        for (int nam : dsNam) {
            KetQuaTaiChinh kq = tinhTaiChinhTheoKhoang(nam + "-01-01", nam + "-12-31");

            tongHd += kq.soHoaDon;
            tongTien += kq.tongTienHd;
            tongThu += kq.thucThu;
            tongNo += kq.congNo;
            tongChi += kq.tongChi;

            System.out.printf("%-6d | %-6d | %-15d | %-15d | %-15d | %-15d | %-16d%n",
                    nam,
                    kq.soHoaDon,
                    kq.tongTienHd,
                    kq.thucThu,
                    kq.congNo,
                    kq.tongChi,
                    kq.loiNhuanThucThu());
        }

        System.out.println("--------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-6s | %-6d | %-15d | %-15d | %-15d | %-15d | %-16d%n",
                "TC",
                tongHd,
                tongTien,
                tongThu,
                tongNo,
                tongChi,
                tongThu - tongChi);
    }

    /**
     * 3a. Thong ke tong tien hoa don theo Nhan vien va Quy trong mot nam.
     */
    public static void thongKeNhanVienTheoQuy(int nam) {
        damBaoDuLieu();

        DanhSachNhanVien dsNhanVien = KhoDuLieu.getDanhSachNhanVien();
        DanhSachHoaDon dsHoaDon = KhoDuLieu.getDanhSachHoaDon();

        int soNv = dsNhanVien.getSoLuong();
        boolean coHdChuaXacDinh = coHoaDonNhanVienLa(dsHoaDon, dsNhanVien);
        int tongDong = soNv + (coHdChuaXacDinh ? 1 : 0);

        int[] dsMaNv = new int[tongDong];
        String[] dsTenNv = new String[tongDong];
        int idx = 0;
        for (int i = 0; i < soNv; i++) {
            NhanVien nv = dsNhanVien.getDanhSach()[i];
            if (nv != null) {
                dsMaNv[idx] = nv.getMaNhanVien();
                dsTenNv[idx] = nv.getMaNhanVien() + " - " + nv.getHoTen();
                idx++;
            }
        }
        if (coHdChuaXacDinh) {
            dsMaNv[idx] = -1;
            dsTenNv[idx] = "Chua xac dinh";
            idx++;
        }

        long[][] maTran = new long[idx][4];
        for (int i = 0; i < dsHoaDon.getSoLuong(); i++) {
            HoaDon hd = dsHoaDon.getDanhSach()[i];
            if (hd == null) {
                continue;
            }
            String ngay = hd.getNgayLap();
            if (ngay == null || ngay.length() < 7 || Integer.parseInt(ngay.substring(0, 4)) != nam) {
                continue;
            }
            int q = (Integer.parseInt(ngay.substring(5, 7)) - 1) / 3;
            int viTri = timViTriMa(dsMaNv, idx, hd.getMaNhanVien());
            if (viTri == -1 && coHdChuaXacDinh) {
                viTri = idx - 1;
            }
            if (viTri >= 0) {
                maTran[viTri][q] += hd.tinhTongTien();
            }
        }

        String[] nhanThucTe = new String[idx];
        System.arraycopy(dsTenNv, 0, nhanThucTe, 0, idx);
        inBangMaTranQuy(
                "THONG KE TONG TIEN HOA DON THEO NHAN VIEN VA QUY - NAM " + nam,
                "Nhan Vien (Nam " + nam + ")",
                nhanThucTe,
                maTran);
    }

    /**
     * 5a. Thong ke tong tien hoa don theo Tour (SP) va Quy trong nam dung bo cuc bang mau:
     * Cot: Nam YYYY | Q1 | Q2 | Q3 | Q4 | TC
     * Dong: Tung Tour + dong TC.
     */
    public static void thongKeTourTheoQuy(int nam) {
        damBaoDuLieu();

        DanhSachTour dsTour = KhoDuLieu.getDanhSachTour();
        if (dsTour.getSoLuong() == 0) {
            System.out.println("\n--- THONG KE TONG TIEN HOA DON THEO TOUR (SP) VA QUY - NAM " + nam + " ---");
            System.out.println("Chua co du lieu hoa don hop le de thong ke.");
            return;
        }

        int[] dsMaTour = new int[dsTour.getSoLuong()];
        String[] dsTenTour = new String[dsTour.getSoLuong()];
        int idx = 0;
        for (int i = 0; i < dsTour.getSoLuong(); i++) {
            Tour tour = dsTour.getDanhSach()[i];
            if (tour != null) {
                dsMaTour[idx] = tour.getMaTour();
                dsTenTour[idx] = tour.getMaTour() + " - " + tour.getTenTour();
                idx++;
            }
        }

        long[][] maTran = new long[idx][4];
        DanhSachHoaDon dsHoaDon = KhoDuLieu.getDanhSachHoaDon();
        DanhSachVe dsVe = KhoDuLieu.getDanhSachVe();
        DanhSachKeHoachTour dsKeHoach = KhoDuLieu.getDanhSachKeHoachTour();

        for (int i = 0; i < dsHoaDon.getSoLuong(); i++) {
            HoaDon hd = dsHoaDon.getDanhSach()[i];
            if (hd == null) {
                continue;
            }
            String ngay = hd.getNgayLap();
            if (ngay == null || ngay.length() < 7 || Integer.parseInt(ngay.substring(0, 4)) != nam) {
                continue;
            }
            int q = (Integer.parseInt(ngay.substring(5, 7)) - 1) / 3;
            for (int maVe : hd.layDanhSachMaVe()) {
                Ve ve = dsVe.timTheoMa(maVe);
                if (ve == null) {
                    continue;
                }
                KeHoachTour kh = dsKeHoach.timTheoMa(ve.getMaKeHoachTour());
                if (kh == null) {
                    continue;
                }
                int viTri = timViTriMa(dsMaTour, idx, kh.getMaTour());
                if (viTri >= 0) {
                    maTran[viTri][q] += ve.getDonGia();
                }
            }
        }

        String[] nhanThucTe = new String[idx];
        System.arraycopy(dsTenTour, 0, nhanThucTe, 0, idx);
        inBangMaTranQuy(
                "THONG KE TONG TIEN HOA DON THEO TOUR (SP) VA QUY - NAM " + nam,
                "Nam " + nam + " (Tour)",
                nhanThucTe,
                maTran);
    }

    // ==================== CAC HAM HO TRO TINH TOAN & DINH DANG ====================

    private static void inBangMaTranQuy(String tieuDe, String nhanCotDau, String[] dsNhanHang, long[][] maTran) {
        System.out.println("\n--- " + tieuDe + " ---");
        System.out.printf("%-26s | %-15s | %-15s | %-15s | %-15s | %-17s%n",
                catChuoi(nhanCotDau, 26), "Q1", "Q2", "Q3", "Q4", "TC");
        System.out.println("-------------------------------------------------------------------------------------------------------------------");

        long[] tongQuy = new long[4];
        long tongToanBo = 0;

        for (int i = 0; i < dsNhanHang.length; i++) {
            long tongHang = 0;
            for (int q = 0; q < 4; q++) {
                tongHang += maTran[i][q];
                tongQuy[q] += maTran[i][q];
            }
            tongToanBo += tongHang;
            System.out.printf("%-26s | %-15d | %-15d | %-15d | %-15d | %-17d%n",
                    catChuoi(dsNhanHang[i], 26),
                    maTran[i][0],
                    maTran[i][1],
                    maTran[i][2],
                    maTran[i][3],
                    tongHang);
        }

        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-26s | %-15d | %-15d | %-15d | %-15d | %-17d%n",
                "TC",
                tongQuy[0],
                tongQuy[1],
                tongQuy[2],
                tongQuy[3],
                tongToanBo);
    }

    private static void damBaoDuLieu() {
        boolean trong = KhoDuLieu.getDanhSachTour().getSoLuong() == 0
                && KhoDuLieu.getDanhSachHoaDon().getSoLuong() == 0
                && KhoDuLieu.getDanhSachVe().getSoLuong() == 0
                && KhoDuLieu.getDanhSachHanhKhach().getSoLuong() == 0;
        if (trong) {
            new DocGhiFile().docTatCa();
        }
    }

    private static KetQuaTaiChinh tinhTaiChinhTheoKhoang(String ngayBatDau, String ngayKetThuc) {
        KetQuaTaiChinh kq = new KetQuaTaiChinh();
        if (ngayBatDau == null || ngayKetThuc == null || ngayBatDau.isEmpty() || ngayKetThuc.isEmpty()) {
            return kq;
        }

        DanhSachHoaDon dsHoaDon = KhoDuLieu.getDanhSachHoaDon();
        for (int i = 0; i < dsHoaDon.getSoLuong(); i++) {
            HoaDon hd = dsHoaDon.getDanhSach()[i];
            if (hd == null) {
                continue;
            }
            String ngayLap = hd.getNgayLap();
            if (ngayLap == null || ngayLap.isEmpty() || ngayLap.compareTo(ngayBatDau) < 0 || ngayLap.compareTo(ngayKetThuc) > 0) {
                continue;
            }
            kq.soHoaDon++;
            kq.tongTienHd += hd.tinhTongTien();
            kq.thucThu += hd.getSoTienDaThanhToan();
            kq.congNo += hd.tinhSoTienConLai();
            kq.tongChi += tinhChiPhiHoaDon(hd);
        }
        return kq;
    }

    private static long tinhChiPhiHoaDon(HoaDon hd) {
        if (hd == null) {
            return 0;
        }
        DanhSachVe dsVe = KhoDuLieu.getDanhSachVe();
        DanhSachKeHoachTour dsKeHoach = KhoDuLieu.getDanhSachKeHoachTour();
        int[] dsMaVe = hd.layDanhSachMaVe();

        long tongChiPhi = 0;
        for (int maVe : dsMaVe) {
            Ve ve = dsVe.timTheoMa(maVe);
            if (ve == null || !ve.chiemCho()) {
                continue;
            }
            KeHoachTour kh = dsKeHoach.timTheoMa(ve.getMaKeHoachTour());
            if (kh != null) {
                long chiPhi1Khach = kh.tinhTongChiPhi(1);
                if (chiPhi1Khach > 0) {
                    tongChiPhi += chiPhi1Khach;
                }
            }
        }
        return tongChiPhi;
    }

    private static boolean coHoaDonNhanVienLa(DanhSachHoaDon dsHoaDon, DanhSachNhanVien dsNhanVien) {
        for (int i = 0; i < dsHoaDon.getSoLuong(); i++) {
            HoaDon hd = dsHoaDon.getDanhSach()[i];
            if (hd != null && dsNhanVien.timTheoMa(hd.getMaNhanVien()) == null) {
                return true;
            }
        }
        return false;
    }

    private static int timViTriMa(int[] mang, int soPhanTu, int giaTri) {
        for (int i = 0; i < soPhanTu; i++) {
            if (mang[i] == giaTri) {
                return i;
            }
        }
        return -1;
    }

    private static int[] layDanhSachNamHoaDon() {
        DanhSachHoaDon dsHoaDon = KhoDuLieu.getDanhSachHoaDon();
        int[] tam = new int[dsHoaDon.getSoLuong()];
        int dem = 0;

        for (int i = 0; i < dsHoaDon.getSoLuong(); i++) {
            HoaDon hd = dsHoaDon.getDanhSach()[i];
            if (hd == null) {
                continue;
            }
            String ngay = hd.getNgayLap();
            if (ngay == null || ngay.length() < 4) {
                continue;
            }
            int nam = Integer.parseInt(ngay.substring(0, 4));
            if (timViTriMa(tam, dem, nam) < 0) {
                tam[dem++] = nam;
            }
        }

        for (int i = 0; i < dem - 1; i++) {
            for (int j = i + 1; j < dem; j++) {
                if (tam[i] > tam[j]) {
                    int tmp = tam[i];
                    tam[i] = tam[j];
                    tam[j] = tmp;
                }
            }
        }

        int[] ketQua = new int[dem];
        System.arraycopy(tam, 0, ketQua, 0, dem);
        return ketQua;
    }

    private static String catChuoi(String s, int maxLen) {
        if (s == null) {
            return "";
        }
        String trimmed = s.trim();
        if (trimmed.length() <= maxLen) {
            return trimmed;
        }
        if (maxLen <= 3) {
            return trimmed.substring(0, maxLen);
        }
        return trimmed.substring(0, maxLen - 3) + "...";
    }
}
