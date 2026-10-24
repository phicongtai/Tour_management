package nguoidung;

import nhapxuatfile.CoTheNhapXuatTxt;
import java.util.Scanner;
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
    public String chuyenThanhDong(){
        return "NHANVIEN|"+maNhanVien+"|"+hoTen+"|"+ngaySinh+
        "|"+gioiTinh+"|"+soDienThoai;
    }
    public boolean docTuDong(String dong){
        if(dong==null||!dong.startsWith("NHANVIEN|")){//kiểm tra xem dòng dữ liệu có đúng định dạng bắt đầu bằng tiền tố quy định hay không
            return false;
        }
        String[] parts=dong.split("\\|");//Cắt chuỗi lớn thành nhiều chuỗi nhỏ hơn bởi biểu thức quy tắc(regex)
        if(parts.length!=6){//lấy ra số lượng phần tử của mảng
            return false;
        }
        try {
            int ma = Integer.parseInt(parts[1]);
            if (ma <= 0) return false;
            int g = Integer.parseInt(parts[4]);
            if (g < 0 || g > 2) return false;
            if (parts[2].trim().isEmpty() || parts[5].trim().isEmpty()) {
                return false;
            }
            this.maNhanVien=ma;
            this.hoTen=parts[2].trim();
            this.ngaySinh=parts[3].trim();
            this.gioiTinh=g;
            this.soDienThoai=parts[5].trim();
            return true;
        }
        catch(NumberFormatException e){
            return true;
        }
    }
}
