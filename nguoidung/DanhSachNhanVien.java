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
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i].getMaNhanVien() == maNhanVien) {
                return danhSach[i];
            }
        }
        return null;
    }

    @Override
    public NhanVien[] timTheoTen(String hoTen) {
        // Đếm số lượng tìm thấy trước
        int dem = 0;
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i].getHoTen().equalsIgnoreCase(hoTen.trim())) {
                dem++;
            }
        }
        
        NhanVien[] ketQua = new NhanVien[dem];
        int index = 0;
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i].getHoTen().equalsIgnoreCase(hoTen.trim())) {
                ketQua[index++] = danhSach[i];
            }
        }
        return ketQua;
    }

    @Override
    public boolean capNhat(NhanVien nhanVien) {
        if (nhanVien == null) return false;
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i].getMaNhanVien() == nhanVien.getMaNhanVien()) {
                danhSach[i] = nhanVien;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean xoaTheoMa(int maNhanVien) {
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i].getMaNhanVien() == maNhanVien) {
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
            dongTXT[i] = danhSach[i].toString(); // Giả định lớp NhanVien có phương thức toString() định dạng chuỗi lưu file
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
                
                // Gọi thẳng constructor 5 tham số của lớp NhanVien
                NhanVien nv = new NhanVien(maNhanVien, hoTen, ngaySinh, gioiTinh, soDienThoai);
                temp[count++] = nv;
            } catch (Exception e) {
                return false; // Dòng nào lỗi định dạng (ví dụ mã/giới tính không phải số) thì trả về false
            }
        }
        
        // Mọi dòng hợp lệ mới thay thế nội dung danh sách
        danhSach = temp;
        soLuong = count;
        return true;
    }
}
