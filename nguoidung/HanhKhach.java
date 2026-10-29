package nguoidung;

public class HanhKhach {
    private int maHanhKhach;
    private String hoTen;
    private String ngaySinh;
    private int gioiTinh;
    private String soDienThoai;

    public HanhKhach() {
    }

    public HanhKhach(int maHanhKhach, String hoTen, String ngaySinh, int gioiTinh, String soDienThoai) {
        this.maHanhKhach = maHanhKhach;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.soDienThoai = soDienThoai;
    }

    public int getMaHanhKhach() {
        return maHanhKhach;
    }

    public void setMaHanhKhach(int maHanhKhach) {
        this.maHanhKhach = maHanhKhach;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        if(hoTen!=null){
           this.hoTen=hoTen.trim(); 
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
        if(soDienThoai!=null){
            this.soDienThoai=soDienThoai;
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
