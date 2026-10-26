package tochuctour;

public class ChiTietKeHoachTour {
    private int maKeHoachTour;
    private String ngay;
    private int maPhuongTien;
    private int maKhachSan;

    public ChiTietKeHoachTour() {
    }

    public ChiTietKeHoachTour(int maKeHoachTour, String ngay, int maPhuongTien, int maKhachSan) {
        this.maKeHoachTour = maKeHoachTour;
        this.ngay = ngay;
        this.maPhuongTien = maPhuongTien;
        this.maKhachSan = maKhachSan;
    }

    public int getMaKeHoachTour() {
        return maKeHoachTour;
    }

    public void setMaKeHoachTour(int maKeHoachTour) {
        this.maKeHoachTour = maKeHoachTour;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public int getMaPhuongTien() {
        return maPhuongTien;
    }

    public void setMaPhuongTien(int maPhuongTien) {
        this.maPhuongTien = maPhuongTien;
    }

    public int getMaKhachSan() {
        return maKhachSan;
    }

    public void setMaKhachSan(int maKhachSan) {
        this.maKhachSan = maKhachSan;
    }
}
