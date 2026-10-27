package tour;

public abstract class Tour {
    private int maTour;
    private String tenTour;
    private String diemKhoiHanh;
    private String diemDen;
    private int soNgay;
    private long donGiaCoBan;

    public Tour() {
    }

    public Tour(int maTour, String tenTour, String diemKhoiHanh, String diemDen, int soNgay, long donGiaCoBan) {
        this.maTour = maTour;
        this.tenTour = tenTour;
        this.diemKhoiHanh = diemKhoiHanh;
        this.diemDen = diemDen;
        this.soNgay = soNgay;
        this.donGiaCoBan = donGiaCoBan;
    }

    public int getMaTour() {
        return maTour;
    }

    public void setMaTour(int maTour) {
        this.maTour = maTour;
    }

    public String getTenTour() {
        return tenTour;
    }

    public void setTenTour(String tenTour) {
        this.tenTour = tenTour;
    }

    public String getDiemKhoiHanh() {
        return diemKhoiHanh;
    }

    public void setDiemKhoiHanh(String diemKhoiHanh) {
        this.diemKhoiHanh = diemKhoiHanh;
    }

    public String getDiemDen() {
        return diemDen;
    }

    public void setDiemDen(String diemDen) {
        this.diemDen = diemDen;
    }

    public int getSoNgay() {
        return soNgay;
    }

    public void setSoNgay(int soNgay) {
        this.soNgay = soNgay;
    }

    public long getDonGiaCoBan() {
        return donGiaCoBan;
    }

    public void setDonGiaCoBan(long donGiaCoBan) {
        this.donGiaCoBan = donGiaCoBan;
    }
}
