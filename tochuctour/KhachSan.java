package tochuctour;

public class KhachSan {
    private int maKhachSan;
    private String tenKhachSan;
    private long giaOMoiNguoiMoiDem;
    private long giaAnMoiNguoiMoiNgay;

    public KhachSan() {
    }

    public KhachSan(int maKhachSan, String tenKhachSan, long giaOMoiNguoiMoiDem, long giaAnMoiNguoiMoiNgay) {
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

    public long getGiaOMoiNguoiMoiDem() {
        return giaOMoiNguoiMoiDem;
    }

    public void setGiaOMoiNguoiMoiDem(long giaOMoiNguoiMoiDem) {
        this.giaOMoiNguoiMoiDem = giaOMoiNguoiMoiDem;
    }

    public long getGiaAnMoiNguoiMoiNgay() {
        return giaAnMoiNguoiMoiNgay;
    }

    public void setGiaAnMoiNguoiMoiNgay(long giaAnMoiNguoiMoiNgay) {
        this.giaAnMoiNguoiMoiNgay = giaAnMoiNguoiMoiNgay;
    }

    public boolean capNhatThongTin(String tenKhachSan, long giaOMoiNguoiMoiDem, long giaAnMoiNguoiMoiNgay) {
    // 1. Kiểm tra điều kiện đầu vào
    if (tenKhachSan == null || tenKhachSan.trim().isBlank() 
            || giaOMoiNguoiMoiDem < 0 || giaAnMoiNguoiMoiNgay < 0) {
        return false;
    }

    // 2. Gán dữ liệu mới (giữ nguyên maKhachSan)
    this.tenKhachSan = tenKhachSan.trim();
    this.giaOMoiNguoiMoiDem = giaOMoiNguoiMoiDem;
    this.giaAnMoiNguoiMoiNgay = giaAnMoiNguoiMoiNgay;

    return true;
}
}
