package datvevathanhtoan;

public class HoaDon {
    private int maHoaDon;
    private int maNhanVien;
    private int maDonDatTour;
    private String ngayLap;
    private double tongTien;
    private double soTienDaThanhToan;

    public HoaDon() {
    }

    public HoaDon(int maHoaDon, int maNhanVien, int maDonDatTour, String ngayLap, double tongTien, double soTienDaThanhToan) {
        this.maHoaDon = maHoaDon;
        this.maNhanVien = maNhanVien;
        this.maDonDatTour = maDonDatTour;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
        this.soTienDaThanhToan = soTienDaThanhToan;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public int getMaDonDatTour() {
        return maDonDatTour;
    }

    public void setMaDonDatTour(int maDonDatTour) {
        this.maDonDatTour = maDonDatTour;
    }

    public String getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(String ngayLap) {
        this.ngayLap = ngayLap;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public double getSoTienDaThanhToan() {
        return soTienDaThanhToan;
    }

    public void setSoTienDaThanhToan(double soTienDaThanhToan) {
        this.soTienDaThanhToan = soTienDaThanhToan;
    }
}
