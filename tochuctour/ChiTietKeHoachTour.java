package tochuctour;

public class ChiTietKeHoachTour {
    private String ngay;
    private int maPhuongTien;
    private int maKhachSan;
    private long tienAn;
    private long tienO;
    private long tienDiLai;

    public ChiTietKeHoachTour() {
    }

    public ChiTietKeHoachTour(String ngay, int maPhuongTien, int maKhachSan, long tienAn, long tienO, long tienDiLai) {
        this.ngay = ngay;
        this.maPhuongTien = maPhuongTien;
        this.maKhachSan = maKhachSan;
        this.tienAn = tienAn;
        this.tienO = tienO;
        this.tienDiLai = tienDiLai;
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

    public long getTienAn() {
        return tienAn;
    }

    public void setTienAn(long tienAn) {
        this.tienAn = tienAn;
    }

    public long getTienO() {
        return tienO;
    }

    public void setTienO(long tienO) {
        this.tienO = tienO;
    }

    public long getTienDiLai() {
        return tienDiLai;
    }

    public void setTienDiLai(long tienDiLai) {
        this.tienDiLai = tienDiLai;
    }

    public long tinhChiPhiNgay(int soKhach){
        if(soKhach <= 0){
            return -1;
        }
        return soKhach * (this.tienAn + this.tienDiLai + this.tienO);
    }
}
