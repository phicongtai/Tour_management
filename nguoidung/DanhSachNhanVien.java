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
        if (nhanVien == null || soLuong >= danhSach.length) return false;
        // Kiểm tra trùng mã
        if (timTheoMa(nhanVien.getMaNhanVien()) != null) return false;
        
        danhSach[soLuong++] = nhanVien;
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
    public NhanVien[] timTheoTen(String hoTen) {
        // Äáº¿m sá»‘ lÆ°á»£ng tÃ¬m tháº¥y trÆ°á»›c
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
                // Dá»“n máº£ng
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
            dongTXT[i] = danhSach[i].toString(); // Giáº£ Ä‘á»‹nh lá»›p NhanVien cÃ³ phÆ°Æ¡ng thá»©c toString() Ä‘á»‹nh dáº¡ng chuá»—i lÆ°u file
        }
        return dongTXT;
    }

    @Override
    public boolean nhapDuLieu(String[] dongDuLieu) {
        if (dongDuLieu == null) return false;
        NhanVien[] temp = new NhanVien[dongDuLieu.length];
        int count = 0;
        
        for (String line : dongDuLieu) {
            // Giáº£ sá»­ má»—i dÃ²ng trong file txt phÃ¢n cÃ¡ch báº±ng dáº¥u pháº©y: ma,hoTen,ngaySinh,gioiTinh,soDienThoai
            String[] parts = line.split(",");
            if (parts.length < 5) return false; // Náº¿u thiáº¿u dá»¯ liá»‡u thÃ¬ xem nhÆ° lá»—i, dá»«ng láº¡i
            
            try {
                int maNhanVien = Integer.parseInt(parts[0].trim());
                String hoTen = parts[1].trim();
                String ngaySinh = parts[2].trim();
                int gioiTinh = Integer.parseInt(parts[3].trim());
                String soDienThoai = parts[4].trim();
                
                // Gá»i tháº³ng constructor 5 tham sá»‘ cá»§a lá»›p NhanVien
                NhanVien nv = new NhanVien(maNhanVien, hoTen, ngaySinh, gioiTinh, soDienThoai);
                temp[count++] = nv;
            } catch (Exception e) {
                return false; // DÃ²ng nÃ o lá»—i Ä‘á»‹nh dáº¡ng (vÃ­ dá»¥ mÃ£/giá»›i tÃ­nh khÃ´ng pháº£i sá»‘) thÃ¬ tráº£ vá» false
            }
        }
        
        // Má»i dÃ²ng há»£p lá»‡ má»›i thay tháº¿ ná»™i dung danh sÃ¡ch
        danhSach = temp;
        soLuong = count;
        return true;
    }
}

