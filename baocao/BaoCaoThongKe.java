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
        inTieuDeBang("THỐNG KÊ TỔNG QUAN HỆ THỐNG", cot);
        System.out.printf("║ %-26s │ %-10s │ %-48s ║%n", "Danh Mục", "Số Lượng", "Chi Tiết Phân Loại");
        inDuongPhanCach(cot, '╠', '╬', '╣');

        inDongTongQuan("Tour du lịch", dsTour.getSoLuong(),
                String.format("Trong nước: %d | Nước ngoài: %d", tourTrongNuoc, tourNuocNgoai));
        inDongTongQuan("Kế hoạch tour", dsKeHoach.getSoLuong(), "Lịch trình khởi hành hiện có");
        inDongTongQuan("Hành khách", dsHanhKhach.getSoLuong(), "Hồ sơ khách hàng trong hệ thống");
        inDongTongQuan("Nhân viên", dsNhanVien.getSoLuong(), "Nhân sự phụ trách tour & hóa đơn");
        inDongTongQuan("Phương tiện", KhoDuLieu.getDanhSachPhuongTien().getSoLuong(), "Danh mục phương tiện di chuyển");
        inDongTongQuan("Khách sạn", KhoDuLieu.getDanhSachKhachSan().getSoLuong(), "Danh mục cơ sở lưu trú");
        inDongTongQuan("Vé đã phát hành", dsVe.getSoLuong(),
                String.format("Đã TT: %d | Đã đặt: %d | Đã hủy: %d", veThanhToan, veDaDat, veDaHuy));
        inDongTongQuan("Hóa đơn", dsHoaDon.getSoLuong(),
                String.format("Đã thu đủ: %d | Chưa thu đủ: %d", hdThuDu, hdChuaDu));

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
        String tieuDe = "THỐNG KÊ TỔNG TIỀN HÓA ĐƠN TỪ " + tuNgay + " ĐẾN " + denNgay;
        inTieuDeBang(tieuDe, cot);
        System.out.printf("║ %-6s │ %-15s │ %-15s │ %-15s │ %-15s │ %-16s ║%n",
                "Số HĐ", "Tg Tiền HĐ", "Thực Thu", "Công Nợ", "Tg Chi", "Lợi Nhuận");
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
        inTieuDeBang("THỐNG KÊ TỔNG TIỀN HÓA ĐƠN THEO THÁNG - NĂM " + nam, cot);
        System.out.printf("║ %-8s │ %-6s │ %-15s │ %-15s │ %-15s │ %-15s │ %-16s ║%n",
                "Tháng", "Số HĐ", "Tg Tiền HĐ", "Thực Thu", "Công Nợ", "Tg Chi", "Lợi Nhuận");
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
                    "Tháng " + thang,
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
        inTieuDeBang("THỐNG KÊ THU - CHI - LỢI NHUẬN THEO QUÝ (NĂM " + nam + ")", cot);
        System.out.printf("║ %-14s │ %15s │ %15s │ %15s │ %15s │ %16s ║%n",
                "Năm " + nam, "Q1", "Q2", "Q3", "Q4", "TC");
        inDuongPhanCach(cot, '╠', '╬', '╣');

        inDongBangQuy("Tg Thu (HĐ)", hdQuy, tcHd);
        inDongBangQuy("Thực Thu", thucThuQuy, tcThucThu);
        inDongBangQuy("Tg Chi", chiQuy, tcChi);
        inDuongPhanCach(cot, '╠', '╬', '╣');
        inDongBangQuy("Lợi Nhuận", loiNhuanQuy, tcLoiNhuan);
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
        inTieuDeBang("THỐNG KÊ TỔNG TIỀN HÓA ĐƠN THEO NĂM", cot);

        if (dsNam.length == 0) {
            System.out.printf("║ %-106s ║%n", "Chưa có dữ liệu hóa đơn hợp lệ để thống kê.");
            inDuongPhanCach(cot, '╚', '╩', '╝');
            return;
        }

        System.out.printf("║ %-6s │ %-6s │ %-15s │ %-15s │ %-15s │ %-15s │ %-16s ║%n",
                "Năm", "Số HĐ", "Tg Tiền HĐ", "Thực Thu", "Công Nợ", "Tg Chi", "Lợi Nhuận");
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
            dsTenNv[idx] = "Chưa xác định";
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
                "THỐNG KÊ TỔNG TIỀN HÓA ĐƠN THEO NHÂN VIÊN VÀ QUÝ - NĂM " + nam,
                "Nhân Viên (Năm " + nam + ")",
                nhanThucTe,
                maTran);
    }

    /**
     * 3b. Thong ke tong tien hoa don theo Nhan vien va cac Nam.
     */
    public static void thongKeNhanVienTheoNam() {
        damBaoDuLieu();

        int[] dsNam = layDanhSachNamHoaDon();
        if (dsNam.length == 0) {
            inBangThongBaoTrong("THỐNG KÊ TỔNG TIỀN HÓA ĐƠN THEO NHÂN VIÊN VÀ NĂM");
            return;
        }

        DanhSachNhanVien dsNhanVien = KhoDuLieu.getDanhSachNhanVien();
        DanhSachHoaDon dsHoaDon = KhoDuLieu.getDanhSachHoaDon();
        boolean coHdChuaXacDinh = coHoaDonNhanVienLa(dsHoaDon, dsNhanVien);
        int tongDong = dsNhanVien.getSoLuong() + (coHdChuaXacDinh ? 1 : 0);

        int[] dsMaNv = new int[tongDong];
        String[] dsTenNv = new String[tongDong];
        int idx = 0;
        for (int i = 0; i < dsNhanVien.getSoLuong(); i++) {
            NhanVien nv = dsNhanVien.getDanhSach()[i];
            if (nv != null) {
                dsMaNv[idx] = nv.getMaNhanVien();
                dsTenNv[idx] = nv.getMaNhanVien() + " - " + nv.getHoTen();
                idx++;
            }
        }
        if (coHdChuaXacDinh) {
            dsMaNv[idx] = -1;
            dsTenNv[idx] = "Chưa xác định";
            idx++;
        }

        long[][] maTran = new long[idx][dsNam.length];
        for (int i = 0; i < dsHoaDon.getSoLuong(); i++) {
            HoaDon hd = dsHoaDon.getDanhSach()[i];
            if (hd == null) {
                continue;
            }
            LocalDate ngay = parseNgay(hd.getNgayLap());
            if (ngay == null) {
                continue;
            }
            int cotNam = timViTriMa(dsNam, dsNam.length, ngay.getYear());
            if (cotNam < 0) {
                continue;
            }
            int viTri = timViTriMa(dsMaNv, idx, hd.getMaNhanVien());
            if (viTri == -1 && coHdChuaXacDinh) {
                viTri = idx - 1;
            }
            if (viTri >= 0) {
                maTran[viTri][cotNam] += hd.tinhTongTien();
            }
        }

        String[] nhanThucTe = new String[idx];
        System.arraycopy(dsTenNv, 0, nhanThucTe, 0, idx);
        inBangMaTranNam(
                "THỐNG KÊ TỔNG TIỀN HÓA ĐƠN THEO NHÂN VIÊN VÀ NĂM",
                "Nhân Viên (Mã - Tên)",
                dsNam,
                nhanThucTe,
                maTran);
    }

    /**
     * 3c (giu lai). Thong ke tong hop hoa don, thuc thu va cong no theo Nhan vien phu trach.
     */
    public static void thongKeTheoNhanVien() {
        damBaoDuLieu();

        DanhSachNhanVien dsNhanVien = KhoDuLieu.getDanhSachNhanVien();
        DanhSachHoaDon dsHoaDon = KhoDuLieu.getDanhSachHoaDon();

        int[] cot = {7, 26, 8, 17, 17, 17};
        inTieuDeBang("THỐNG KÊ HÓA ĐƠN THEO NHÂN VIÊN PHỤ TRÁCH", cot);
        System.out.printf("║ %-5s │ %-24s │ %-6s │ %-15s │ %-15s │ %-15s ║%n",
                "Mã NV", "Họ Tên Nhân Viên", "Số HĐ", "Tg Tiền HĐ", "Thực Thu", "Công Nợ");
        inDuongPhanCach(cot, '╠', '╬', '╣');

        int tongHdAll = 0;
        long tongTienAll = 0;
        long tongThuAll = 0;
        long tongNoAll = 0;

        for (int i = 0; i < dsNhanVien.getSoLuong(); i++) {
            NhanVien nv = dsNhanVien.getDanhSach()[i];
            if (nv == null) {
                continue;
            }
            int soHd = 0;
            long tongTien = 0;
            long thucThu = 0;
            long congNo = 0;

            for (int j = 0; j < dsHoaDon.getSoLuong(); j++) {
                HoaDon hd = dsHoaDon.getDanhSach()[j];
                if (hd != null && hd.getMaNhanVien() == nv.getMaNhanVien()) {
                    soHd++;
                    tongTien += hd.tinhTongTien();
                    thucThu += hd.getSoTienDaThanhToan();
                    congNo += hd.tinhSoTienConLai();
                }
            }

            tongHdAll += soHd;
            tongTienAll += tongTien;
            tongThuAll += thucThu;
            tongNoAll += congNo;

            System.out.printf("║ %5d │ %-24s │ %6d │ %15s │ %15s │ %15s ║%n",
                    nv.getMaNhanVien(),
                    catChuoi(nv.getHoTen(), 24),
                    soHd,
                    dinhDangSo(tongTien),
                    dinhDangSo(thucThu),
                    dinhDangSo(congNo));
        }

        int hdKhac = 0;
        long tienKhac = 0;
        long thuKhac = 0;
        long noKhac = 0;
        for (int j = 0; j < dsHoaDon.getSoLuong(); j++) {
            HoaDon hd = dsHoaDon.getDanhSach()[j];
            if (hd != null && dsNhanVien.timTheoMa(hd.getMaNhanVien()) == null) {
                hdKhac++;
                tienKhac += hd.tinhTongTien();
                thuKhac += hd.getSoTienDaThanhToan();
                noKhac += hd.tinhSoTienConLai();
            }
        }
        if (hdKhac > 0) {
            tongHdAll += hdKhac;
            tongTienAll += tienKhac;
            tongThuAll += thuKhac;
            tongNoAll += noKhac;
            System.out.printf("║ %-5s │ %-24s │ %6d │ %15s │ %15s │ %15s ║%n",
                    "-", "Chưa xác định", hdKhac,
                    dinhDangSo(tienKhac), dinhDangSo(thuKhac), dinhDangSo(noKhac));
        }

        inDuongPhanCach(cot, '╠', '╬', '╣');
        System.out.printf("║ %-5s │ %-24s │ %6d │ %15s │ %15s │ %15s ║%n",
                "TC",
                dsNhanVien.getSoLuong() + " nhân viên",
                tongHdAll,
                dinhDangSo(tongTienAll),
                dinhDangSo(tongThuAll),
                dinhDangSo(tongNoAll));
        inDuongPhanCach(cot, '╚', '╩', '╝');
    }

    /**
     * 4a. Thong ke tong tien hoa don theo Khach hang va Quy trong mot nam.
     */
    public static void thongKeKhachHangTheoQuy(int nam) {
        damBaoDuLieu();

        DanhSachHoaDon dsHoaDon = KhoDuLieu.getDanhSachHoaDon();
        int[] dsMaKH = layDanhSachMaKhachDatHoaDon();
        if (dsMaKH.length == 0) {
            inBangThongBaoTrong("THỐNG KÊ TỔNG TIỀN HÓA ĐƠN THEO KHÁCH HÀNG VÀ QUÝ - NĂM " + nam);
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
                "THỐNG KÊ TỔNG TIỀN HÓA ĐƠN THEO KHÁCH HÀNG VÀ QUÝ - NĂM " + nam,
                "Khách Hàng (Năm " + nam + ")",
                dsTenKH,
                maTran);
    }

    /**
     * 4b. Thong ke tong tien hoa don cua tung Khach hang theo Nam.
     */
    public static void thongKeTongHDTheoKHNam() {
        damBaoDuLieu();

        DanhSachHoaDon dsHoaDon = KhoDuLieu.getDanhSachHoaDon();
        int[] dsNam = layDanhSachNamHoaDon();
        int[] dsMaKH = layDanhSachMaKhachDatHoaDon();

        if (dsNam.length == 0 || dsMaKH.length == 0) {
            inBangThongBaoTrong("THỐNG KÊ TỔNG HÓA ĐƠN CỦA TỪNG KHÁCH HÀNG THEO NĂM");
            return;
        }

        String[] dsTenKH = new String[dsMaKH.length];
        long[][] maTran = new long[dsMaKH.length][dsNam.length];
        for (int i = 0; i < dsMaKH.length; i++) {
            dsTenKH[i] = dinhDangTenKhachHang(dsMaKH[i]);
            for (int j = 0; j < dsNam.length; j++) {
                maTran[i][j] = tinhTongHoaDonTheoKhachVaNam(dsHoaDon, dsMaKH[i], dsNam[j]);
            }
        }

        inBangMaTranNam(
                "THỐNG KÊ TỔNG TIỀN HÓA ĐƠN THEO KHÁCH HÀNG VÀ NĂM",
                "Khách Hàng (Mã - Tên)",
                dsNam,
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
            inBangThongBaoTrong("THỐNG KÊ TỔNG TIỀN HÓA ĐƠN THEO TOUR (SP) VÀ QUÝ - NĂM " + nam);
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
                "THỐNG KÊ TỔNG TIỀN HÓA ĐƠN THEO TOUR (SP) VÀ QUÝ - NĂM " + nam,
                "Năm " + nam + " (Tour)",
                nhanThucTe,
                maTran);
    }

    /**
     * 5b. Thong ke tong tien hoa don theo Tour (SP) va cac Nam.
     */
    public static void thongKeTourTheoNam() {
        damBaoDuLieu();

        int[] dsNam = layDanhSachNamHoaDon();
        DanhSachTour dsTour = KhoDuLieu.getDanhSachTour();
        if (dsNam.length == 0 || dsTour.getSoLuong() == 0) {
            inBangThongBaoTrong("THỐNG KÊ TỔNG TIỀN HÓA ĐƠN THEO TOUR (SP) VÀ NĂM");
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

        long[][] maTran = new long[idx][dsNam.length];
        DanhSachHoaDon dsHoaDon = KhoDuLieu.getDanhSachHoaDon();
        DanhSachVe dsVe = KhoDuLieu.getDanhSachVe();
        DanhSachKeHoachTour dsKeHoach = KhoDuLieu.getDanhSachKeHoachTour();

        for (int i = 0; i < dsHoaDon.getSoLuong(); i++) {
            HoaDon hd = dsHoaDon.getDanhSach()[i];
            if (hd == null) {
                continue;
            }
            LocalDate ngay = parseNgay(hd.getNgayLap());
            if (ngay == null) {
                continue;
            }
            int cotNam = timViTriMa(dsNam, dsNam.length, ngay.getYear());
            if (cotNam < 0) {
                continue;
            }
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
                    maTran[viTri][cotNam] += ve.getDonGia();
                }
            }
        }

        String[] nhanThucTe = new String[idx];
        System.arraycopy(dsTenTour, 0, nhanThucTe, 0, idx);
        inBangMaTranNam(
                "THỐNG KÊ TỔNG TIỀN HÓA ĐƠN THEO TOUR (SP) VÀ NĂM",
                "Tour (Mã - Tên)",
                dsNam,
                nhanThucTe,
                maTran);
    }

    /**
     * 5c (giu lai). Thong ke tinh hinh ban ve, ti le lap day, doanh thu va chi phi theo tung Tour.
     */
    public static void thongKeDoanhThuTheoTour() {
        damBaoDuLieu();

        DanhSachTour dsTour = KhoDuLieu.getDanhSachTour();
        DanhSachKeHoachTour dsKeHoach = KhoDuLieu.getDanhSachKeHoachTour();
        DanhSachVe dsVe = KhoDuLieu.getDanhSachVe();

        int[] cot = {6, 26, 13, 6, 16, 10, 17, 17, 17};
        inTieuDeBang("THỐNG KÊ HIỆU QUẢ KINH DOANH THEO TOUR", cot);
        System.out.printf("║ %-4s │ %-24s │ %-11s │ %-4s │ %-14s │ %-8s │ %-15s │ %-15s │ %-15s ║%n",
                "Mã", "Tên Tour", "Loại", "KH", "Vé(TT/Đặt/Hủy)", "Lấp Đầy", "Giá Trị Vé", "Chi Phí Tour", "LN Dự Kiến");
        inDuongPhanCach(cot, '╠', '╬', '╣');

        int tongKh = 0;
        int tongSucChuaAll = 0;
        int tongChiemChoAll = 0;
        int tongVeTTAll = 0;
        int tongVeDatAll = 0;
        int tongVeHuyAll = 0;
        long tongDoanhThuAll = 0;
        long tongChiPhiAll = 0;

        for (int i = 0; i < dsTour.getSoLuong(); i++) {
            Tour tour = dsTour.getDanhSach()[i];
            if (tour == null) {
                continue;
            }

            KeHoachTour[] keHoachCuaTour = dsKeHoach.timTheoMaTour(tour.getMaTour());
            int soKh = keHoachCuaTour.length;
            int sucChua = 0;
            int veTT = 0;
            int veDat = 0;
            int veHuy = 0;
            long doanhThuVe = 0;
            long chiPhiTour = 0;

            for (KeHoachTour kh : keHoachCuaTour) {
                if (kh == null) {
                    continue;
                }
                sucChua += kh.getSoLuongVeToiDa();
                long chiPhi1Khach = Math.max(0, kh.tinhTongChiPhi(1));

                for (int j = 0; j < dsVe.getSoLuong(); j++) {
                    Ve ve = dsVe.getDanhSach()[j];
                    if (ve == null || ve.getMaKeHoachTour() != kh.getMaKeHoachTour()) {
                        continue;
                    }
                    if (ve.getTrangThai() == Ve.DA_THANH_TOAN) {
                        veTT++;
                    } else if (ve.getTrangThai() == Ve.DA_DAT) {
                        veDat++;
                    } else if (ve.getTrangThai() == Ve.DA_HUY) {
                        veHuy++;
                    }

                    if (ve.chiemCho()) {
                        doanhThuVe += ve.getDonGia();
                        chiPhiTour += chiPhi1Khach;
                    }
                }
            }

            int veChiemCho = veTT + veDat;
            double tiLeLapDay = sucChua > 0 ? (veChiemCho * 100.0 / sucChua) : 0.0;
            String loaiTour = (tour instanceof TourTrongNuoc) ? "Trong nước" : "Nước ngoài";
            String chiTietVe = veTT + " / " + veDat + " / " + veHuy;

            tongKh += soKh;
            tongSucChuaAll += sucChua;
            tongChiemChoAll += veChiemCho;
            tongVeTTAll += veTT;
            tongVeDatAll += veDat;
            tongVeHuyAll += veHuy;
            tongDoanhThuAll += doanhThuVe;
            tongChiPhiAll += chiPhiTour;

            System.out.printf("║ %4d │ %-24s │ %-11s │ %4d │ %14s │ %7.1f%% │ %15s │ %15s │ %15s ║%n",
                    tour.getMaTour(),
                    catChuoi(tour.getTenTour(), 24),
                    loaiTour,
                    soKh,
                    chiTietVe,
                    tiLeLapDay,
                    dinhDangSo(doanhThuVe),
                    dinhDangSo(chiPhiTour),
                    dinhDangSo(doanhThuVe - chiPhiTour));
        }

        double tiLeTong = tongSucChuaAll > 0 ? (tongChiemChoAll * 100.0 / tongSucChuaAll) : 0.0;
        inDuongPhanCach(cot, '╠', '╬', '╣');
        System.out.printf("║ %-4s │ %-24s │ %-11s │ %4d │ %14s │ %7.1f%% │ %15s │ %15s │ %15s ║%n",
                "TC",
                dsTour.getSoLuong() + " tour",
                "-",
                tongKh,
                tongVeTTAll + " / " + tongVeDatAll + " / " + tongVeHuyAll,
                tiLeTong,
                dinhDangSo(tongDoanhThuAll),
                dinhDangSo(tongChiPhiAll),
                dinhDangSo(tongDoanhThuAll - tongChiPhiAll));
        inDuongPhanCach(cot, '╚', '╩', '╝');
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

    private static void inBangMaTranNam(String tieuDe, String nhanCotDau, int[] dsNam, String[] dsNhanHang, long[][] maTran) {
        int[] cot = new int[dsNam.length + 2];
        cot[0] = 28;
        for (int i = 0; i < dsNam.length; i++) {
            cot[i + 1] = 17;
        }
        cot[cot.length - 1] = 19;

        inTieuDeBang(tieuDe, cot);

        System.out.printf("║ %-26s ", catChuoi(nhanCotDau, 26));
        for (int nam : dsNam) {
            System.out.printf("│ %15d ", nam);
        }
        System.out.printf("│ %17s ║%n", "TC");

        inDuongPhanCach(cot, '╠', '╬', '╣');

        long[] tongTheoNam = new long[dsNam.length];
        long tongToanBo = 0;

        for (int i = 0; i < dsNhanHang.length; i++) {
            long tongHang = 0;
            System.out.printf("║ %-26s ", catChuoi(dsNhanHang[i], 26));
            for (int j = 0; j < dsNam.length; j++) {
                long giaTri = maTran[i][j];
                tongHang += giaTri;
                tongTheoNam[j] += giaTri;
                System.out.printf("│ %15s ", dinhDangSo(giaTri));
            }
            tongToanBo += tongHang;
            System.out.printf("│ %17s ║%n", dinhDangSo(tongHang));
        }

        inDuongPhanCach(cot, '╠', '╬', '╣');
        System.out.printf("║ %-26s ", "TC");
        for (long tongNam : tongTheoNam) {
            System.out.printf("│ %15s ", dinhDangSo(tongNam));
        }
        System.out.printf("│ %17s ║%n", dinhDangSo(tongToanBo));
        inDuongPhanCach(cot, '╚', '╩', '╝');
    }

    private static void inBangThongBaoTrong(String tieuDe) {
        int[] cot = {66};
        inTieuDeBang(tieuDe, cot);
        System.out.printf("║ %-64s ║%n", "Chưa có dữ liệu hóa đơn hợp lệ để thống kê.");
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

    private static long tinhTongHoaDonTheoKhachVaNam(DanhSachHoaDon dsHoaDon, int maKH, int nam) {
        long tong = 0;
        for (int i = 0; i < dsHoaDon.getSoLuong(); i++) {
            HoaDon hd = dsHoaDon.getDanhSach()[i];
            if (hd == null || timMaNguoiDatTheoHoaDon(hd) != maKH) {
                continue;
            }
            LocalDate ngay = parseNgay(hd.getNgayLap());
            if (ngay != null && ngay.getYear() == nam) {
                tong += hd.tinhTongTien();
            }
        }
        return tong;
    }

    private static String dinhDangTenKhachHang(int maKH) {
        if (maKH <= 0) {
            return "0 - Khách lẻ";
        }
        HanhKhach hk = KhoDuLieu.getDanhSachHanhKhach().timTheoMa(maKH);
        if (hk == null || hk.getHoTen() == null || hk.getHoTen().trim().isEmpty()) {
            return maKH + " - (Không rõ)";
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
