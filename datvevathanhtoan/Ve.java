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

    public Ve(int maVe, int maDonDatTour, int maNguoiDat, int maKeHoachTour, int maHanhKhach, String ngayDat, long donGia, int trangThai) {
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
        this.ngayDat = ngayDat;
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
}
