package datvevathanhtoan;

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

    public HoaDon(int maHoaDon, int maDonDatTour, int maNhanVien, String ngayLap, long soTienDaThanhToan, ChiTietHoaDon chiTiet) {
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
}
