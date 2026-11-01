package datvevathanhtoan;

public class Ve {
    public static final int DA_DAT = 0;
    public static final int DA_THANH_TOAN = 1;
    public static final int DA_HUY = 2;

    private int maVe;
    private int maDonDatTour;
    private int maNguoiDat;
    private int maKeHoachTour;
    private int maHanhKhach;
    private String ngayDat;
    private long donGia;
    private int trangThai;

    public Ve() {
    }

    public Ve(int maVe, int maDonDatTour, int maNguoiDat, int maKeHoachTour,
              int maHanhKhach, String ngayDat, long donGia, int trangThai) {
        this.maVe = maVe;
        this.maDonDatTour = maDonDatTour;
        this.maNguoiDat = maNguoiDat;
        this.maKeHoachTour = maKeHoachTour;
        this.maHanhKhach = maHanhKhach;
        this.ngayDat = ngayDat;
        this.donGia = donGia;
        this.trangThai = trangThai;
    }

    public int getMaVe() {
        return maVe;
    }

    public void setMaVe(int maVe) {
        this.maVe = maVe;
    }

    public int getMaDonDatTour() {
        return maDonDatTour;
    }

    public void setMaDonDatTour(int maDonDatTour) {
        this.maDonDatTour = maDonDatTour;
    }

    public int getMaNguoiDat() {
        return maNguoiDat;
    }

    public void setMaNguoiDat(int maNguoiDat) {
        this.maNguoiDat = maNguoiDat;
    }

    public int getMaKeHoachTour() {
        return maKeHoachTour;
    }

    public void setMaKeHoachTour(int maKeHoachTour) {
        this.maKeHoachTour = maKeHoachTour;
    }

    public int getMaHanhKhach() {
        return maHanhKhach;
    }

    public void setMaHanhKhach(int maHanhKhach) {
        this.maHanhKhach = maHanhKhach;
    }

    public String getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(String ngayDat) {
        if (ngayDat != null) {
            this.ngayDat = ngayDat.trim();
        }
    }

    public long getDonGia() {
        return donGia;
    }

    public void setDonGia(long donGia) {
        this.donGia = donGia;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    // Vé đã đặt hoặc đã thanh toán thì đang chiếm một chỗ của kế hoạch tour
    public boolean chiemCho() {
        return trangThai == DA_DAT || trangThai == DA_THANH_TOAN;
    }

    // Chỉ vé vừa đặt, chưa thanh toán và chưa hủy mới được hủy
    public boolean duocHuy() {
        return trangThai == DA_DAT;
    }

    public boolean capNhatTrangThai(int trangThaiMoi) {
        // 1. Trạng thái mới phải nằm trong miền giá trị
        if (trangThaiMoi != DA_DAT && trangThaiMoi != DA_THANH_TOAN && trangThaiMoi != DA_HUY) {
            return false;
        }

        // 2. Chỉ chuyển từ DA_DAT sang trạng thái khác; trạng thái cũ phải hợp lệ
        if (trangThai != DA_DAT || trangThaiMoi == DA_DAT) {
            return false;
        }

        // 3. Ghi trạng thái mới
        this.trangThai = trangThaiMoi;
        return true;
    }
}
