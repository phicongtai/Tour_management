package nguoidung;
import java.util.Arrays;
import danhsach.DanhSach;

public class DanhSachNhanVien implements DanhSach<NhanVien> {
    private NhanVien[] danhSach;
    private int soLuong;

    public DanhSachNhanVien() {
        danhSach = new NhanVien[100];
        soLuong = 0;
    }
    @Override 
    public boolean them(NhanVien nhanVien) {
        if(nhanVien==null)return false;
        if(danhSach==null){
        danhSach=new NhanVien[0];
        soLuong=0;
        }
        for(int i=0;i<soLuong;i++){
            if(danhSach[i]!=null && danhSach[i].getMaNhanVien()==nhanVien.getMaNhanVien()){
                return false;
            }
        }
        danhSach=Arrays.copyOf(danhSach,soLuong+1);
        danhSach[soLuong]=nhanVien;
        soLuong++;
        return true;
    }
    @Override 
    public NhanVien timTheoMa(int maNhanVien) {
        if(danhSach==null || soLuong <= 0 || maNhanVien <= 0) {
            return null;
        }
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i]!=null && danhSach[i].getMaNhanVien() == maNhanVien)return danhSach[i];
        }
        return null;
    }
    public NhanVien[] timTheoTen(String hoTen) {
        if (danhSach == null || soLuong <= 0 || hoTen.isBlank())return new NhanVien[0];
        NhanVien[] ketQua = new NhanVien[0];
        int index = 0;
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i]!=null && danhSach[i].getHoTen().equalsIgnoreCase(hoTen.trim())) {
                ketQua = Arrays.copyOf(ketQua, index + 1);
                ketQua[index] = danhSach[i];
                index++;
            }
        }
        return ketQua;
    }
    @Override 
    public boolean capNhat(NhanVien nhanVien) {
        if (danhSach == null || soLuong <= 0 || nhanVien == null || nhanVien.getMaNhanVien() <= 0) return false;
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i]!=null && danhSach[i].getMaNhanVien() == nhanVien.getMaNhanVien()) {
                danhSach[i] = nhanVien;
                return true;
            }
        }
        return false;
    }
    @Override 
    public boolean xoaTheoMa(int maNhanVien) {
        if (danhSach == null || soLuong <= 0 || maNhanVien <= 0) return false;
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i]!=null && danhSach[i].getMaNhanVien() == maNhanVien) {
                // Dồn mảng
                for (int j = i; j < soLuong - 1; j++) {
                    danhSach[j] = danhSach[j + 1];
                }
                danhSach[--soLuong] = null;
                return true;
            }
        }
        return false;
    }
    @Override 
    public NhanVien[] layTatCa() {
        NhanVien[] kq = new NhanVien[soLuong];
        System.arraycopy(danhSach, 0, kq, 0, soLuong);
        return kq;
    }
    @Override 
    public String[] xuatDuLieu() {
        String[] dongTXT = new String[soLuong];
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i]!=null) {
                dongTXT[i] = danhSach[i].toString(); // Giả định lớp NhanVien có phương thức toString() định dạng chuỗi lưu file
            }
        }
        return dongTXT;
    }
    @Override
    public boolean nhapDuLieu(String[] dongDuLieu) {
        if (dongDuLieu == null) return false;
        NhanVien[] temp = new NhanVien[dongDuLieu.length];
        int count = 0;
        for (String line : dongDuLieu) {
            // Giả sử mỗi dòng trong file txt phân cách bằng dấu phẩy: ma,hoTen,ngaySinh,gioiTinh,soDienThoai
            String[] parts = line.split(",");
            if (parts.length < 5) return false; // Nếu thiếu dữ liệu thì xem như lỗi, dừng lại
            try {
                int maNhanVien = Integer.parseInt(parts[0].trim());
                String hoTen = parts[1].trim();
                String ngaySinh = parts[2].trim();
                int gioiTinh = Integer.parseInt(parts[3].trim());
                String soDienThoai = parts[4].trim();
                NhanVien nv = new NhanVien(maNhanVien, hoTen, ngaySinh, gioiTinh, soDienThoai);
                temp[count++] = nv;
            } catch (Exception e) {
                return false; // Dòng nào lỗi định dạng (ví dụ mã/giới tính không phải số) thì trả về false
            }
        }
        danhSach = temp;
        soLuong = count;
        return true;
    }
}
