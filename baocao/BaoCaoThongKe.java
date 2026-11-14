package baocao;

import datvevathanhtoan.DanhSachHoaDon;
import datvevathanhtoan.DanhSachVe;
import datvevathanhtoan.HoaDon;
import datvevathanhtoan.Ve;
import dulieu.DocGhiFile;
import kho.KhoDuLieu;
import nguoidung.DanhSachHanhKhach;
import nguoidung.DanhSachNhanVien;
import nguoidung.HanhKhach;
import nguoidung.NhanVien;
import tochuctour.DanhSachKeHoachTour;
import tochuctour.KeHoachTour;
import tour.DanhSachTour;
import tour.Tour;
import tour.TourTrongNuoc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Module bao cao va thong ke cho he thong quan ly tour du lich.
 * Moi loai thong ke la mot phuong thuc doc lap (public static void)
 * trinh bay theo phong cach bang khung Unicode.
 */
public class BaoCaoThongKe {
    private static final DateTimeFormatter DINH_DANG_ISO = DateTimeFormatter.ISO_LOCAL_DATE;
    private static final DateTimeFormatter DINH_DANG_VN = DateTimeFormatter.ofPattern("dd/MM/yyyy");

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
     * 0. Thong ke tong quan quy mo he thong, ve va hoa don.
     */
    public static void thongKeTongQuanHeThong() {
        damBaoDuLieu();

        DanhSachTour dsTour = KhoDuLieu.getDanhSachTour();
        DanhSachKeHoachTour dsKeHoach = KhoDuLieu.getDanhSachKeHoachTour();
        DanhSachHanhKhach dsHanhKhach = KhoDuLieu.getDanhSachHanhKhach();
        DanhSachNhanVien dsNhanVien = KhoDuLieu.getDanhSachNhanVien();
        DanhSachVe dsVe = KhoDuLieu.getDanhSachVe();
        DanhSachHoaDon dsHoaDon = KhoDuLieu.getDanhSachHoaDon();

        int tourTrongNuoc = 0;
        int tourNuocNgoai = 0;
        for (int i = 0; i < dsTour.getSoLuong(); i++) {
            Tour t = dsTour.getDanhSach()[i];
            if (t instanceof TourTrongNuoc) {
                tourTrongNuoc++;
            } else if (t != null) {
                tourNuocNgoai++;
            }
        }

        int veThanhToan = 0;
        int veDaDat = 0;
        int veDaHuy = 0;
        for (int i = 0; i < dsVe.getSoLuong(); i++) {
            Ve v = dsVe.getDanhSach()[i];
            if (v == null) {
                continue;
            }
            if (v.getTrangThai() == Ve.DA_THANH_TOAN) {
                veThanhToan++;
            } else if (v.getTrangThai() == Ve.DA_DAT) {
                veDaDat++;
            } else if (v.getTrangThai() == Ve.DA_HUY) {
                veDaHuy++;
            }
        }

        int hdThuDu = 0;
        int hdChuaDu = 0;
        for (int i = 0; i < dsHoaDon.getSoLuong(); i++) {
            HoaDon hd = dsHoaDon.getDanhSach()[i];
            if (hd == null) {
                continue;
            }
            if (hd.daThanhToanDu()) {
                hdThuDu++;
            } else {
                hdChuaDu++;
            }
        }

        int[] cot = {28, 12, 50};
        inTieuDeBang("THONG KE TONG QUAN HE THONG", cot);
        System.out.printf("║ %-26s │ %-10s │ %-48s ║%n", "Danh Muc", "So Luong", "Chi Tiet Phan Loai");
        inDuongPhanCach(cot, '╠', '╬', '╣');

        inDongTongQuan("Tour du lich", dsTour.getSoLuong(),
                String.format("Trong nuoc: %d | Nuoc ngoai: %d", tourTrongNuoc, tourNuocNgoai));
        inDongTongQuan("Ke hoach tour", dsKeHoach.getSoLuong(), "Lich trinh khoi hanh hien co");
        inDongTongQuan("Hanh khach", dsHanhKhach.getSoLuong(), "Ho so khach hang trong he thong");
        inDongTongQuan("Nhan vien", dsNhanVien.getSoLuong(), "Nhan su phu trach tour & hoa don");
        inDongTongQuan("Phuong tien", KhoDuLieu.getDanhSachPhuongTien().getSoLuong(), "Danh muc phuong tien di chuyen");
        inDongTongQuan("Khach san", KhoDuLieu.getDanhSachKhachSan().getSoLuong(), "Danh muc co so luu tru");
        inDongTongQuan("Ve da phat hanh", dsVe.getSoLuong(),
                String.format("Da TT: %d | Da dat: %d | Da huy: %d", veThanhToan, veDaDat, veDaHuy));
        inDongTongQuan("Hoa don", dsHoaDon.getSoLuong(),
                String.format("Da thu du: %d | Chua thu du: %d", hdThuDu, hdChuaDu));

        inDuongPhanCach(cot, '╚', '╩', '╝');
    }

    private static void inDongTongQuan(String danhMuc, int soLuong, String ghiChu) {
        System.out.printf("║ %-26s │ %10s │ %-48s ║%n",
                catChuoi(danhMuc, 26),
                dinhDangSo(soLuong),
                catChuoi(ghiChu, 48));
    }

    /**
     * 1. Tinh tong tien hoa don, thuc thu, cong no, tong chi va loi nhuan tu ngay a den ngay b.
     * Ho tro ca dinh dang yyyy-MM-dd lan dd/MM/yyyy.
     */
    public static void thongKeTheoKhoang(String tuNgay, String denNgay) {
        damBaoDuLieu();

        LocalDate ngayBatDau = parseNgay(tuNgay);
        LocalDate ngayKetThuc = parseNgay(denNgay);
        if (ngayBatDau != null && ngayKetThuc != null && ngayBatDau.isAfter(ngayKetThuc)) {
            LocalDate tmp = ngayBatDau;
            ngayBatDau = ngayKetThuc;
            ngayKetThuc = tmp;
        }

        KetQuaTaiChinh kq = tinhTaiChinhTheoKhoang(ngayBatDau, ngayKetThuc);

        int[] cot = {8, 17, 17, 17, 17, 18};
        String tieuDe = "THONG KE TONG TIEN HOA DON TU " + tuNgay + " DEN " + denNgay;
        inTieuDeBang(tieuDe, cot);
        System.out.printf("║ %-6s │ %-15s │ %-15s │ %-15s │ %-15s │ %-16s ║%n",
                "So HD", "Tg Tien HD", "Thuc Thu", "Cong No", "Tg Chi", "Loi Nhuan");
        inDuongPhanCach(cot, '╠', '╬', '╣');
        System.out.printf("║ %6s │ %15s │ %15s │ %15s │ %15s │ %16s ║%n",
                dinhDangSo(kq.soHoaDon),
                dinhDangSo(kq.tongTienHd),
                dinhDangSo(kq.thucThu),
                dinhDangSo(kq.congNo),
                dinhDangSo(kq.tongChi),
                dinhDangSo(kq.loiNhuanThucThu()));
        inDuongPhanCach(cot, '╚', '╩', '╝');
    }

    /**
     * 2a. Thong ke tong tien hoa don, chi phi va loi nhuan theo 12 thang trong nam.
     */
    public static void thongKeTheoThang(int nam) {
        damBaoDuLieu();

        int[] cot = {10, 8, 17, 17, 17, 17, 18};
        inTieuDeBang("THONG KE TONG TIEN HOA DON THEO THANG - NAM " + nam, cot);
        System.out.printf("║ %-8s │ %-6s │ %-15s │ %-15s │ %-15s │ %-15s │ %-16s ║%n",
                "Thang", "So HD", "Tg Tien HD", "Thuc Thu", "Cong No", "Tg Chi", "Loi Nhuan");
        inDuongPhanCach(cot, '╠', '╬', '╣');

        int tongHd = 0;
        long tongTienHd = 0;
        long tongThucThu = 0;
        long tongCongNo = 0;
        long tongChi = 0;

        for (int thang = 1; thang <= 12; thang++) {
            LocalDate dauThang = LocalDate.of(nam, thang, 1);
            LocalDate cuoiThang = dauThang.plusMonths(1).minusDays(1);
            KetQuaTaiChinh kq = tinhTaiChinhTheoKhoang(dauThang, cuoiThang);

            tongHd += kq.soHoaDon;
            tongTienHd += kq.tongTienHd;
            tongThucThu += kq.thucThu;
            tongCongNo += kq.congNo;
            tongChi += kq.tongChi;

            System.out.printf("║ %-8s │ %6s │ %15s │ %15s │ %15s │ %15s │ %16s ║%n",
                    "Thang " + thang,
                    dinhDangSo(kq.soHoaDon),
                    dinhDangSo(kq.tongTienHd),
                    dinhDangSo(kq.thucThu),
                    dinhDangSo(kq.congNo),
                    dinhDangSo(kq.tongChi),
                    dinhDangSo(kq.loiNhuanThucThu()));
        }

        inDuongPhanCach(cot, '╠', '╬', '╣');
        System.out.printf("║ %-8s │ %6s │ %15s │ %15s │ %15s │ %15s │ %16s ║%n",
                "TC",
                dinhDangSo(tongHd),
                dinhDangSo(tongTienHd),
                dinhDangSo(tongThucThu),
                dinhDangSo(tongCongNo),
                dinhDangSo(tongChi),
                dinhDangSo(tongThucThu - tongChi));
        inDuongPhanCach(cot, '╚', '╩', '╝');
    }

    /**
     * 2b. Thong ke theo Quy trong nam dung bo cuc bang mau:
     * Cot: Nam YYYY | Q1 | Q2 | Q3 | Q4 | TC
     * Dong: Tg Tien HD, Thuc Thu, Tg Chi, Loi nhuan.
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

        for (int q = 0; q < 4; q++) {
            int thangDau = q * 3 + 1;
            LocalDate dauQuy = LocalDate.of(nam, thangDau, 1);
            LocalDate cuoiQuy = dauQuy.plusMonths(3).minusDays(1);
            KetQuaTaiChinh kq = tinhTaiChinhTheoKhoang(dauQuy, cuoiQuy);

            hdQuy[q] = kq.tongTienHd;
            thucThuQuy[q] = kq.thucThu;
            chiQuy[q] = kq.tongChi;
            loiNhuanQuy[q] = kq.loiNhuanThucThu();

            tcHd += hdQuy[q];
            tcThucThu += thucThuQuy[q];
            tcChi += chiQuy[q];
            tcLoiNhuan += loiNhuanQuy[q];
        }

        int[] cot = {16, 17, 17, 17, 17, 18};
        inTieuDeBang("THONG KE THU - CHI - LOI NHUAN THEO QUY (NAM " + nam + ")", cot);
        System.out.printf("║ %-14s │ %15s │ %15s │ %15s │ %15s │ %16s ║%n",
                "Nam " + nam, "Q1", "Q2", "Q3", "Q4", "TC");
        inDuongPhanCach(cot, '╠', '╬', '╣');

        inDongBangQuy("Tg Thu (HD)", hdQuy, tcHd);
        inDongBangQuy("Thuc Thu", thucThuQuy, tcThucThu);
        inDongBangQuy("Tg Chi", chiQuy, tcChi);
        inDuongPhanCach(cot, '╠', '╬', '╣');
        inDongBangQuy("Loi Nhuan", loiNhuanQuy, tcLoiNhuan);
        inDuongPhanCach(cot, '╚', '╩', '╝');
    }

    private static void inDongBangQuy(String nhan, long[] quy, long tongCong) {
        System.out.printf("║ %-14s │ %15s │ %15s │ %15s │ %15s │ %16s ║%n",
                nhan,
                dinhDangSo(quy[0]),
                dinhDangSo(quy[1]),
                dinhDangSo(quy[2]),
                dinhDangSo(quy[3]),
                dinhDangSo(tongCong));
    }

    /**
     * 2c. Thong ke tong tien hoa don, chi phi va loi nhuan theo tung Nam.
     */
    public static void thongKeTheoNam() {
        damBaoDuLieu();

        int[] dsNam = layDanhSachNamHoaDon();
        int[] cot = {8, 8, 17, 17, 17, 17, 18};
        inTieuDeBang("THONG KE TONG TIEN HOA DON THEO NAM", cot);

        if (dsNam.length == 0) {
            System.out.printf("║ %-106s ║%n", "Chua co du lieu hoa don hop le de thong ke.");
            inDuongPhanCach(cot, '╚', '╩', '╝');
            return;
        }

        System.out.printf("║ %-6s │ %-6s │ %-15s │ %-15s │ %-15s │ %-15s │ %-16s ║%n",
                "Nam", "So HD", "Tg Tien HD", "Thuc Thu", "Cong No", "Tg Chi", "Loi Nhuan");
        inDuongPhanCach(cot, '╠', '╬', '╣');

        int tongHd = 0;
        long tongTien = 0;
        long tongThu = 0;
        long tongNo = 0;
        long tongChi = 0;

        for (int nam : dsNam) {
            LocalDate dauNam = LocalDate.of(nam, 1, 1);
            LocalDate cuoiNam = LocalDate.of(nam, 12, 31);
            KetQuaTaiChinh kq = tinhTaiChinhTheoKhoang(dauNam, cuoiNam);

            tongHd += kq.soHoaDon;
            tongTien += kq.tongTienHd;
            tongThu += kq.thucThu;
            tongNo += kq.congNo;
            tongChi += kq.tongChi;

            System.out.printf("║ %6d │ %6s │ %15s │ %15s │ %15s │ %15s │ %16s ║%n",
                    nam,
                    dinhDangSo(kq.soHoaDon),
                    dinhDangSo(kq.tongTienHd),
                    dinhDangSo(kq.thucThu),
                    dinhDangSo(kq.congNo),
                    dinhDangSo(kq.tongChi),
                    dinhDangSo(kq.loiNhuanThucThu()));
        }

        inDuongPhanCach(cot, '╠', '╬', '╣');
        System.out.printf("║ %-6s │ %6s │ %15s │ %15s │ %15s │ %15s │ %16s ║%n",
                "TC",
                dinhDangSo(tongHd),
                dinhDangSo(tongTien),
                dinhDangSo(tongThu),
                dinhDangSo(tongNo),
                dinhDangSo(tongChi),
                dinhDangSo(tongThu - tongChi));
        inDuongPhanCach(cot, '╚', '╩', '╝');
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
            LocalDate ngay = parseNgay(hd.getNgayLap());
            if (ngay == null || ngay.getYear() != nam) {
                continue;
            }
            int q = (ngay.getMonthValue() - 1) / 3;
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
     * 4a. Thong ke tong tien hoa don theo Khach hang va Quy trong mot nam.
     */
    public static void thongKeKhachHangTheoQuy(int nam) {
        damBaoDuLieu();

        DanhSachHoaDon dsHoaDon = KhoDuLieu.getDanhSachHoaDon();
        int[] dsMaKH = layDanhSachMaKhachDatHoaDon();
        if (dsMaKH.length == 0) {
            inBangThongBaoTrong("THONG KE TONG TIEN HOA DON THEO KHACH HANG VA QUY - NAM " + nam);
            return;
        }

        String[] dsTenKH = new String[dsMaKH.length];
        for (int i = 0; i < dsMaKH.length; i++) {
            dsTenKH[i] = dinhDangTenKhachHang(dsMaKH[i]);
        }

        long[][] maTran = new long[dsMaKH.length][4];
        for (int i = 0; i < dsHoaDon.getSoLuong(); i++) {
            HoaDon hd = dsHoaDon.getDanhSach()[i];
            if (hd == null) {
                continue;
            }
            LocalDate ngay = parseNgay(hd.getNgayLap());
            if (ngay == null || ngay.getYear() != nam) {
                continue;
            }
            int maKH = timMaNguoiDatTheoHoaDon(hd);
            int viTri = timViTriMa(dsMaKH, dsMaKH.length, maKH);
            if (viTri >= 0) {
                int q = (ngay.getMonthValue() - 1) / 3;
                maTran[viTri][q] += hd.tinhTongTien();
            }
        }

        inBangMaTranQuy(
                "THONG KE TONG TIEN HOA DON THEO KHACH HANG VA QUY - NAM " + nam,
                "Khach Hang (Nam " + nam + ")",
                dsTenKH,
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
            inBangThongBaoTrong("THONG KE TONG TIEN HOA DON THEO TOUR (SP) VA QUY - NAM " + nam);
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
            LocalDate ngay = parseNgay(hd.getNgayLap());
            if (ngay == null || ngay.getYear() != nam) {
                continue;
            }
            int q = (ngay.getMonthValue() - 1) / 3;
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
        int[] cot = {28, 17, 17, 17, 17, 19};
        inTieuDeBang(tieuDe, cot);
        System.out.printf("║ %-26s │ %15s │ %15s │ %15s │ %15s │ %17s ║%n",
                catChuoi(nhanCotDau, 26), "Q1", "Q2", "Q3", "Q4", "TC");
        inDuongPhanCach(cot, '╠', '╬', '╣');

        long[] tongQuy = new long[4];
        long tongToanBo = 0;

        for (int i = 0; i < dsNhanHang.length; i++) {
            long tongHang = 0;
            for (int q = 0; q < 4; q++) {
                tongHang += maTran[i][q];
                tongQuy[q] += maTran[i][q];
            }
            tongToanBo += tongHang;
            System.out.printf("║ %-26s │ %15s │ %15s │ %15s │ %15s │ %17s ║%n",
                    catChuoi(dsNhanHang[i], 26),
                    dinhDangSo(maTran[i][0]),
                    dinhDangSo(maTran[i][1]),
                    dinhDangSo(maTran[i][2]),
                    dinhDangSo(maTran[i][3]),
                    dinhDangSo(tongHang));
        }

        inDuongPhanCach(cot, '╠', '╬', '╣');
        System.out.printf("║ %-26s │ %15s │ %15s │ %15s │ %15s │ %17s ║%n",
                "TC",
                dinhDangSo(tongQuy[0]),
                dinhDangSo(tongQuy[1]),
                dinhDangSo(tongQuy[2]),
                dinhDangSo(tongQuy[3]),
                dinhDangSo(tongToanBo));
        inDuongPhanCach(cot, '╚', '╩', '╝');
    }

    private static void inBangThongBaoTrong(String tieuDe) {
        int[] cot = {66};
        inTieuDeBang(tieuDe, cot);
        System.out.printf("║ %-64s ║%n", "Chua co du lieu hoa don hop le de thong ke.");
        inDuongPhanCach(cot, '╚', '╩', '╝');
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

    private static KetQuaTaiChinh tinhTaiChinhTheoKhoang(LocalDate ngayBatDau, LocalDate ngayKetThuc) {
        KetQuaTaiChinh kq = new KetQuaTaiChinh();
        if (ngayBatDau == null || ngayKetThuc == null) {
            return kq;
        }

        DanhSachHoaDon dsHoaDon = KhoDuLieu.getDanhSachHoaDon();
        for (int i = 0; i < dsHoaDon.getSoLuong(); i++) {
            HoaDon hd = dsHoaDon.getDanhSach()[i];
            if (hd == null) {
                continue;
            }
            LocalDate ngayLap = parseNgay(hd.getNgayLap());
            if (ngayLap == null || ngayLap.isBefore(ngayBatDau) || ngayLap.isAfter(ngayKetThuc)) {
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
            LocalDate ngay = parseNgay(hd.getNgayLap());
            if (ngay == null) {
                continue;
            }
            int nam = ngay.getYear();
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

    private static int[] layDanhSachMaKhachDatHoaDon() {
        DanhSachHoaDon dsHoaDon = KhoDuLieu.getDanhSachHoaDon();
        int[] tam = new int[dsHoaDon.getSoLuong()];
        int dem = 0;

        for (int i = 0; i < dsHoaDon.getSoLuong(); i++) {
            HoaDon hd = dsHoaDon.getDanhSach()[i];
            if (hd == null) {
                continue;
            }
            int maKH = timMaNguoiDatTheoHoaDon(hd);
            if (timViTriMa(tam, dem, maKH) < 0) {
                tam[dem++] = maKH;
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

    private static int timMaNguoiDatTheoHoaDon(HoaDon hd) {
        if (hd == null) {
            return 0;
        }
        DanhSachVe dsVe = KhoDuLieu.getDanhSachVe();
        int[] dsMaVe = hd.layDanhSachMaVe();
        for (int maVe : dsMaVe) {
            Ve ve = dsVe.timTheoMa(maVe);
            if (ve != null && ve.getMaNguoiDat() > 0) {
                return ve.getMaNguoiDat();
            }
        }
        Ve[] veTheoDon = dsVe.timTheoMaDonDatTour(hd.getMaDonDatTour());
        for (Ve ve : veTheoDon) {
            if (ve != null && ve.getMaNguoiDat() > 0) {
                return ve.getMaNguoiDat();
            }
        }
        return 0;
    }

    private static String dinhDangTenKhachHang(int maKH) {
        if (maKH <= 0) {
            return "0 - Khach le";
        }
        HanhKhach hk = KhoDuLieu.getDanhSachHanhKhach().timTheoMa(maKH);
        if (hk == null || hk.getHoTen() == null || hk.getHoTen().trim().isEmpty()) {
            return maKH + " - (Khong ro)";
        }
        return maKH + " - " + hk.getHoTen().trim();
    }

    private static LocalDate parseNgay(String chuoiNgay) {
        if (chuoiNgay == null) {
            return null;
        }
        String s = chuoiNgay.trim();
        if (s.isEmpty() || "N/A".equalsIgnoreCase(s)) {
            return null;
        }
        try {
            return LocalDate.parse(s, DINH_DANG_ISO);
        } catch (Exception ignored) {
        }
        try {
            return LocalDate.parse(s, DINH_DANG_VN);
        } catch (Exception ignored) {
        }
        return null;
    }

    private static String dinhDangSo(long giaTri) {
        return String.format(Locale.US, "%,d", giaTri);
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

    private static void inTieuDeBang(String tieuDe, int[] doRongCot) {
        int tongNoiDung = doRongCot.length - 1;
        for (int w : doRongCot) {
            tongNoiDung += w;
        }
        System.out.println();
        System.out.println("╔" + lapKyTu('═', tongNoiDung) + "╗");
        System.out.println("║" + canGiua(tieuDe, tongNoiDung) + "║");
        inDuongPhanCach(doRongCot, '╠', '╦', '╣');
    }

    private static void inDuongPhanCach(int[] doRongCot, char trai, char giua, char phai) {
        StringBuilder sb = new StringBuilder();
        sb.append(trai);
        for (int i = 0; i < doRongCot.length; i++) {
            sb.append(lapKyTu('═', doRongCot[i]));
            sb.append(i < doRongCot.length - 1 ? giua : phai);
        }
        System.out.println(sb.toString());
    }

    private static String canGiua(String text, int width) {
        if (text == null) {
            text = "";
        }
        if (text.length() >= width) {
            return text.substring(0, width);
        }
        int padTrai = (width - text.length()) / 2;
        int padPhai = width - text.length() - padTrai;
        return lapKyTu(' ', padTrai) + text + lapKyTu(' ', padPhai);
    }

    private static String lapKyTu(char ch, int count) {
        if (count <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(count);
        for (int i = 0; i < count; i++) {
            sb.append(ch);
        }
        return sb.toString();
    }
}
