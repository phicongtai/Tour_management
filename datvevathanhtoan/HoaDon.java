package datvevathanhtoan;

import kho.KhoDuLieu;

public class HoaDon {
    private int maHoaDon;
    private int maDonDatTour;
    private int maNhanVien;
    private String ngayLap;
    private long soTienDaThanhToan;
    private ChiTietHoaDon chiTiet;

    public HoaDon() {
    }

    public HoaDon(int maHoaDon, int maDonDatTour, int maNhanVien, String ngayLap) {
        this.maHoaDon = maHoaDon;
        this.maDonDatTour = maDonDatTour;
        this.maNhanVien = maNhanVien;
        this.ngayLap = ngayLap;
        this.chiTiet = new ChiTietHoaDon(maHoaDon);
    }

    public HoaDon(int maHoaDon, int maDonDatTour, int maNhanVien, String ngayLap,
                  long soTienDaThanhToan, ChiTietHoaDon chiTiet) {
        this.maHoaDon = maHoaDon;
        this.maDonDatTour = maDonDatTour;
        this.maNhanVien = maNhanVien;
        this.ngayLap = ngayLap;
        this.soTienDaThanhToan = soTienDaThanhToan;
        this.chiTiet = chiTiet;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getMaDonDatTour() {
        return maDonDatTour;
    }

    public void setMaDonDatTour(int maDonDatTour) {
        this.maDonDatTour = maDonDatTour;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(String ngayLap) {
        this.ngayLap = ngayLap;
    }

    public long getSoTienDaThanhToan() {
        return soTienDaThanhToan;
    }

    public void setSoTienDaThanhToan(long soTienDaThanhToan) {
        this.soTienDaThanhToan = soTienDaThanhToan;
    }

    public ChiTietHoaDon getChiTiet() {
        return chiTiet;
    }

    public void setChiTiet(ChiTietHoaDon chiTiet) {
        this.chiTiet = chiTiet;
    }

    public boolean themVeVaoChiTiet(int maVe, DanhSachVe dsVe) {
        // 1. Kiểm tra tham số: dsVe khác null, maVe hợp lệ
        if (dsVe == null || maVe <= 0) {
            return false;
        }

        // 2. Tra vé theo mã, không tìm thấy thì từ chối
        Ve ve = dsVe.timTheoMa(maVe);
        if (ve == null) {
            return false;
        }

        // 3. Vé phải thuộc đúng đơn đặt tour của hóa đơn
        if (ve.getMaDonDatTour() != maDonDatTour) {
            return false;
        }

        // 4. Vé chưa có trong chi tiết và chưa được gán vào hóa đơn khác
        if (chiTiet != null && chiTiet.coChuaVe(maVe)) {
            return false;
        }
        if (daThuocHoaDonKhac(maVe)) {
            return false;
        }

        // 5. Thêm vé vào chi tiết rồi cập nhật tổng tiền
        if (chiTiet == null) {
            chiTiet = new ChiTietHoaDon(maHoaDon);
        }
        if (!chiTiet.themVe(maVe)) {
            return false;
        }
        if (!chiTiet.capNhatTongTien(dsVe)) {
            return false;
        }

        // 6. Thêm thành công khi mọi bước đều hợp lệ
        return true;
    }

    public int[] layDanhSachMaVe() {
        // Chưa có chi tiết thì trả mảng rỗng
        if (chiTiet == null) {
            return new int[0];
        }

        // ChiTietHoaDon.layDanhSachMaVe() đã trả bản sao các mã vé đang dùng
        int[] danhSachMaVe = chiTiet.layDanhSachMaVe();
        if (danhSachMaVe == null) {
            return new int[0];
        }
        return danhSachMaVe;
    }

    public long tinhTongTien() {
        // Tổng tiền là dữ liệu lịch sử đã chốt trong chi tiết; không tính lại theo giá vé hiện tại
        if (chiTiet == null) {
            return 0;
        }
        return chiTiet.layTongTien();
    }

    public long tinhSoTienConLai() {
        // 1. Kiểm tra điều kiện: số tiền đã thanh toán không được âm
        if (soTienDaThanhToan < 0) {
            return 0;
        }

        // 2. Lấy tổng tiền của hóa đơn, tổng tiền không hợp lệ thì từ chối
        long tongTien = tinhTongTien();
        if (tongTien <= 0) {
            return 0;
        }

        // 3. Đã thanh toán vượt tổng tiền thì số còn lại sẽ âm, từ chối
        if (soTienDaThanhToan > tongTien) {
            return 0;
        }

        // 4. Chỉ đọc dữ liệu, trả về số tiền còn phải thanh toán
        return tongTien - soTienDaThanhToan;
    }

    public boolean ghiNhanThanhToan(long soTien, DanhSachVe dsVe) {
        // 1. Kiểm tra tham số: số tiền dương, có danh sách vé và có chi tiết hóa đơn
        if (soTien <= 0 || dsVe == null || chiTiet == null) {
            return false;
        }

        // 2. Số tiền đã ghi nhận trước đó phải hợp lệ
        if (soTienDaThanhToan < 0) {
            return false;
        }

        // 3. Hóa đơn phải có tổng tiền hợp lệ
        long tongTien = chiTiet.layTongTien();
        if (tongTien <= 0) {
            return false;
        }

        // 4. Không nhận thanh toán vượt quá số tiền còn lại
        if (soTienDaThanhToan > tongTien || soTien > tongTien - soTienDaThanhToan) {
            return false;
        }

        // 5. Kiểm tra trước mọi vé của hóa đơn còn tra được, tránh ghi dở dang
        int[] danhSachMaVe = chiTiet.layDanhSachMaVe();
        Ve[] cacVe = new Ve[danhSachMaVe.length];
        for (int i = 0; i < danhSachMaVe.length; i++) {
            cacVe[i] = dsVe.timTheoMa(danhSachMaVe[i]);
            if (cacVe[i] == null) {
                return false;
            }
        }

        // 6. Ghi nhận số tiền đã thanh toán
        this.soTienDaThanhToan += soTien;

        // 7. Trả đủ thì chuyển các vé chưa hủy sang trạng thái đã thanh toán
        if (this.soTienDaThanhToan >= tongTien) {
            for (int i = 0; i < cacVe.length; i++) {
                if (cacVe[i].getTrangThai() == Ve.DA_DAT) {
                    cacVe[i].capNhatTrangThai(Ve.DA_THANH_TOAN);
                }
            }
        }

        return true;
    }

    // Đã thanh toán đủ khi số tiền đã trả không nhỏ hơn tổng tiền của hóa đơn
    public boolean daThanhToanDu() {
        // Điều kiện đầu vào: số tiền đã thanh toán không âm
        if (soTienDaThanhToan < 0) {
            return false;
        }

        // Hóa đơn chưa có tổng tiền hợp lệ thì coi như chưa thanh toán đủ
        long tongTien = tinhTongTien();
        if (tongTien <= 0) {
            return false;
        }

        // Chỉ đọc dữ liệu, không ghi
        return soTienDaThanhToan >= tongTien;
    }

    // Kiểm tra vé đã nằm trong chi tiết của một hóa đơn khác hay chưa
    private boolean daThuocHoaDonKhac(int maVe) {
        DanhSachHoaDon danhSachHoaDon = KhoDuLieu.getDanhSachHoaDon();
        if (danhSachHoaDon == null) {
            return false;
        }

        HoaDon[] tatCa = danhSachHoaDon.layTatCa();
        if (tatCa == null) {
            return false;
        }
        for (int i = 0; i < tatCa.length; i++) {
            HoaDon hoaDonKhac = tatCa[i];
            if (hoaDonKhac == null || hoaDonKhac == this) {
                continue;
            }
            ChiTietHoaDon chiTietKhac = hoaDonKhac.getChiTiet();
            if (chiTietKhac != null && chiTietKhac.coChuaVe(maVe)) {
                return true;
            }
        }
        return false;
    }
}
