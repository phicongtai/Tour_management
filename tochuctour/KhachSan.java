package tochuctour;

public class KhachSan {
    private int maKhachSan;
    private String tenKhachSan;
    private double giaOMoiNguoiMoiDem;
    private double giaAnMoiNguoiMoiNgay;

    public KhachSan() {
    }

    public KhachSan(int maKhachSan, String tenKhachSan, double giaOMoiNguoiMoiDem, double giaAnMoiNguoiMoiNgay) {
        this.maKhachSan = maKhachSan;
        this.tenKhachSan = tenKhachSan;
        this.giaOMoiNguoiMoiDem = giaOMoiNguoiMoiDem;
        this.giaAnMoiNguoiMoiNgay = giaAnMoiNguoiMoiNgay;
    }

    public int getMaKhachSan() {
        return maKhachSan;
    }

    public void setMaKhachSan(int maKhachSan) {
        this.maKhachSan = maKhachSan;
    }

    public String getTenKhachSan() {
        return tenKhachSan;
    }

    public void setTenKhachSan(String tenKhachSan) {
        this.tenKhachSan = tenKhachSan;
    }

    public double getGiaOMoiNguoiMoiDem() {
        return giaOMoiNguoiMoiDem;
    }

    public void setGiaOMoiNguoiMoiDem(double giaOMoiNguoiMoiDem) {
        this.giaOMoiNguoiMoiDem = giaOMoiNguoiMoiDem;
    }

    public double getGiaAnMoiNguoiMoiNgay() {
        return giaAnMoiNguoiMoiNgay;
    }

    public void setGiaAnMoiNguoiMoiNgay(double giaAnMoiNguoiMoiNgay) {
        this.giaAnMoiNguoiMoiNgay = giaAnMoiNguoiMoiNgay;
    }
}
