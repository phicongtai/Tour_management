package nguoidung;

public class NhanVien {
    private int maNhanVien;
    private String hoTen;
    private String ngaySinh;
    private int gioiTinh;
    private String soDienThoai;
    public NhanVien() {
    }
    public NhanVien(int maNhanVien, String hoTen, String ngaySinh, int gioiTinh, String soDienThoai) {
        this.maNhanVien = maNhanVien;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.soDienThoai = soDienThoai;
    }
    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        if (hoTen != null) {
            this.hoTen = hoTen.trim();// trim dùng để xóa khoảng trắng cuối và đầu chuỗi
        }
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        if (soDienThoai != null) {
            this.soDienThoai = soDienThoai.trim();
        }
    }
    public boolean capNhatThongTin(String hoTen,String ngaySinh,int gioiTinh,String soDienThoai){
        if(hoTen==null||hoTen.trim().isEmpty()||soDienThoai==null||soDienThoai.trim().isEmpty()){
            return false;
        }
        if(gioiTinh < 0 || gioiTinh >2){
            return false;
        }
        this.hoTen = hoTen.trim();
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.soDienThoai = soDienThoai.trim();
        return true;
    }
}
