package tochuctour;

public class KeHoachTour {
    private int maKeHoachTour;
    private int maTour;
    private int maNhanVien;
    private String ngayDi;
    private String ngayVe;
    private int soLuongVeToiDa;
    private ChiTietKeHoachTour[] danhSachChiTiet;
    private int soLuongChiTiet;

    public KeHoachTour() {
    }

    public KeHoachTour(int maKeHoachTour, int maTour, int maNhanVien, String ngayDi, String ngayVe, int soLuongVeToiDa) {
        this.maKeHoachTour = maKeHoachTour;
        this.maTour = maTour;
        this.maNhanVien = maNhanVien;
        this.ngayDi = ngayDi;
        this.ngayVe = ngayVe;
        this.soLuongVeToiDa = soLuongVeToiDa;
    }

    public KeHoachTour(int maKeHoachTour, int maTour, int maNhanVien, String ngayDi, String ngayVe, int soLuongVeToiDa, ChiTietKeHoachTour[] danhSachChiTiet, int soLuongChiTiet) {
        this.maKeHoachTour = maKeHoachTour;
        this.maTour = maTour;
        this.maNhanVien = maNhanVien;
        this.ngayDi = ngayDi;
        this.ngayVe = ngayVe;
        this.soLuongVeToiDa = soLuongVeToiDa;
        this.danhSachChiTiet = danhSachChiTiet;
        this.soLuongChiTiet = soLuongChiTiet;
    }

    public int getMaKeHoachTour() {
        return maKeHoachTour;
    }

    public void setMaKeHoachTour(int maKeHoachTour) {
        this.maKeHoachTour = maKeHoachTour;
    }

    public int getMaTour() {
        return maTour;
    }

    public void setMaTour(int maTour) {
        this.maTour = maTour;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getNgayDi() {
        return ngayDi;
    }

    public void setNgayDi(String ngayDi) {
        this.ngayDi = ngayDi;
    }

    public String getNgayVe() {
        return ngayVe;
    }

    public void setNgayVe(String ngayVe) {
        this.ngayVe = ngayVe;
    }

    public int getSoLuongVeToiDa() {
        return soLuongVeToiDa;
    }

    public void setSoLuongVeToiDa(int soLuongVeToiDa) {
        this.soLuongVeToiDa = soLuongVeToiDa;
    }

    public ChiTietKeHoachTour[] getDanhSachChiTiet() {
        return danhSachChiTiet;
    }

    public void setDanhSachChiTiet(ChiTietKeHoachTour[] danhSachChiTiet) {
        this.danhSachChiTiet = danhSachChiTiet;
    }

    public int getSoLuongChiTiet() {
        return soLuongChiTiet;
    }

    public void setSoLuongChiTiet(int soLuongChiTiet) {
        this.soLuongChiTiet = soLuongChiTiet;
    }
}
