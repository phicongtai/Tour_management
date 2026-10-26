package datvevathanhtoan;

public class DonDatTour {
    public static final int DA_DAT = 0;
    public static final int DA_HUY = 1;

    private int maDonDatTour;
    private int maNguoiDat;
    private int maKeHoachTour;
    private String ngayDat;
    private int trangThai;
    private double tongTien;

    public DonDatTour() {
    }

    public DonDatTour(int maDonDatTour, int maNguoiDat, int maKeHoachTour, String ngayDat, int trangThai, double tongTien) {
        this.maDonDatTour = maDonDatTour;
        this.maNguoiDat = maNguoiDat;
        this.maKeHoachTour = maKeHoachTour;
        this.ngayDat = ngayDat;
        this.trangThai = trangThai;
        this.tongTien = tongTien;
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

    public String getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(String ngayDat) {
        this.ngayDat = ngayDat;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }
}
