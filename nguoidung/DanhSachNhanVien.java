package nguoidung;

import danhsach.DanhSach;
import java.util.Scanner;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class DanhSachNhanVien implements DanhSach<NhanVien> {
    private NhanVien[] danhSach;
    private int soLuong;
    private static final Scanner sc = new Scanner(System.in);

    public DanhSachNhanVien() {
        this.danhSach = new NhanVien[0];
        this.soLuong = 0;
    }

    public NhanVien[] getDanhSach() { 
        return this.danhSach; 
    }
    
    public void setDanhSach(NhanVien[] danhSach) { 
        this.danhSach = danhSach; 
    }
    
    public int getSoLuong() { 
        return this.soLuong; 
    }
    
    public void setSoLuong(int soLuong) { 
        this.soLuong = soLuong; 
    }

    @Override 
    public boolean them(NhanVien nhanVien) {
        if (nhanVien == null) return false;
        if (this.danhSach == null) {
            this.danhSach = new NhanVien[0];
            this.soLuong = 0;
        }
        for (int i = 0; i < this.soLuong; i++) {
            if (this.danhSach[i] != null && this.danhSach[i].getMaNhanVien() == nhanVien.getMaNhanVien()) {
                return false;
            }
        }
        this.danhSach = Arrays.copyOf(this.danhSach, this.soLuong + 1);
        this.danhSach[this.soLuong] = nhanVien;
        this.soLuong++;
        return true;
    }

    @Override 
    public boolean them() {
        if(this.danhSach == null){
            this.danhSach = new NhanVien[0];
            this.soLuong = 0;
        }

        this.danhSach = Arrays.copyOf(this.danhSach, this.soLuong + 1);
        this.danhSach[this.soLuong] = new NhanVien();
        this.danhSach[this.soLuong].nhap();
        this.soLuong++;
        return true;
    }

    @Override
    public void nhap() {
        System.out.print("Nhap vao so luong nhan vien: ");
        this.soLuong = this.sc.nextInt();
        this.sc.nextLine(); 
        this.danhSach = new NhanVien[this.soLuong];
        for (int i = 0; i < this.soLuong; i++) {
            System.out.println("Nhap thong tin nhan vien thu " + (i + 1) + ":");
            this.danhSach[i] = new NhanVien(); 
            this.danhSach[i].nhap();
        }
    }

    @Override
    public void xuat() {
        if (this.danhSach == null || this.soLuong == 0) {
            System.out.println("Danh sach nhan vien trong!");
            return;
        }
        for (int i = 0; i < this.soLuong; i++) {
            if (this.danhSach[i] != null) {
                this.danhSach[i].xuat();
            }
        }
    }

    @Override 
    public NhanVien timTheoMa(int maNhanVien) {
        if (this.danhSach == null || this.soLuong <= 0 || maNhanVien <= 0) return null;
        for (int i = 0; i < this.soLuong; i++) {
            if (this.danhSach[i] != null && this.danhSach[i].getMaNhanVien() == maNhanVien) {
                return this.danhSach[i];
            }
        }
        return null;
    }

    @Override 
    public boolean suaTheoMa(int maNhanVien) {
        NhanVien nv = this.timTheoMa(maNhanVien);
        if (nv == null) {
            System.out.println("Khong tim thay nhan vien co ma: " + maNhanVien);
            return false;
        }
        System.out.println("Thong tin nhan vien hien tai: ");
        nv.xuat();
        
        System.out.print("Chinh Ho: ");
        String ho = this.sc.nextLine();
        if (!ho.isBlank()) { 
            nv.setHo(ho); 
        }
        
        System.out.print("Chinh Ten: ");
        String ten = this.sc.nextLine();
        if (!ten.isBlank()) { 
            nv.setTen(ten); 
        }
        
        System.out.print("Chinh Ngay sinh (vd:2007-11-20): ");
        String ngaySinh = this.sc.nextLine();
        if (!ngaySinh.isBlank()) { 
            nv.setNgaySinh(ngaySinh); 
        }
        
        System.out.print("Chinh Gioi tinh (0: Nu, 1: Nam): ");
        String gioiTinhStr = this.sc.nextLine();
        if (!gioiTinhStr.isBlank()) {
            int gioiTinh = Integer.parseInt(gioiTinhStr.trim());
            nv.setGioiTinh(gioiTinh);
        }
        
        System.out.print("Chinh So dien thoai: ");
        String soDienThoai = this.sc.nextLine();
        if (!soDienThoai.isBlank()) { 
            nv.setSoDienThoai(soDienThoai); 
        }
        
        System.out.println("Thong tin nhan vien sau khi cap nhat: ");
        nv.xuat();
        return true;
    }

    @Override
    public boolean xoaTheoMa(int maNhanVien) {
        if (this.danhSach == null || this.soLuong <= 0 || maNhanVien <= 0) {
            return false;
        }
        int index = -1;
        for (int i = 0; i < this.soLuong; i++) {
            if (this.danhSach[i] != null && this.danhSach[i].getMaNhanVien() == maNhanVien) {
                index = i;
                break;
            }
        }
        if(index==-1)return false;
        for (int i = index; i < this.soLuong - 1; i++) {
            this.danhSach[i] = this.danhSach[i + 1];
        }
        this.danhSach = Arrays.copyOf(this.danhSach, this.soLuong - 1);
        this.soLuong--;
        return true;
    }

    @Override
    public NhanVien[] layTatCa() {
        NhanVien[] kq = new NhanVien[this.soLuong];
        System.arraycopy(this.danhSach, 0, kq, 0, this.soLuong);
        return kq;
    }

    @Override 
    public boolean docFile(String duongDan) {
        this.danhSach = new NhanVien[0];
        this.soLuong = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(duongDan))) {
            String temp;
            while ((temp = reader.readLine()) != null) {
                if (temp.isBlank()) continue;
                String[] parts = temp.split("\\|");
                if (parts.length >= 6) {
                    NhanVien nv = new NhanVien(
                        Integer.parseInt(parts[0].trim()),
                        parts[1].trim(),
                        parts[2].trim(),
                        parts[3].trim(),
                        Integer.parseInt(parts[4].trim()),
                        parts[5].trim()
                    );
                    this.them(nv);
                } else if (parts.length >= 5) {
                    // Ho tro doc file cu (chua tach ho va ten)
                    NhanVien nv = new NhanVien();
                    nv.setMaNhanVien(Integer.parseInt(parts[0].trim()));
                    nv.setHoTen(parts[1].trim());
                    nv.setNgaySinh(parts[2].trim());
                    nv.setGioiTinh(Integer.parseInt(parts[3].trim()));
                    nv.setSoDienThoai(parts[4].trim());
                    this.them(nv);
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override 
    public boolean ghiFile(String duongDan) {
        if (this.danhSach == null) return false;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(duongDan))) {
            for (int i = 0; i < this.soLuong; i++) {
                if (this.danhSach[i] == null) continue;
                String temp = this.danhSach[i].getMaNhanVien() + "|" +
                              this.danhSach[i].getHo() + "|" +
                              this.danhSach[i].getTen() + "|" +
                              this.danhSach[i].getNgaySinh() + "|" +
                              this.danhSach[i].getGioiTinh() + "|" +
                              this.danhSach[i].getSoDienThoai();
                writer.write(temp);
                writer.newLine();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public NhanVien[] timTheoTen(String tenCanTim) {
        if (this.danhSach == null || this.soLuong <= 0 || tenCanTim == null || tenCanTim.isBlank()) {
            return new NhanVien[0];
        }
        String khoa = tenCanTim.trim();
        NhanVien[] ketQua = new NhanVien[0];
        int index = 0;
        for (int i = 0; i < this.soLuong; i++) {
            if (this.danhSach[i] != null && this.danhSach[i].getTen() != null
                    && (this.danhSach[i].getTen().equalsIgnoreCase(khoa)
                        || this.danhSach[i].getHoTen().equalsIgnoreCase(khoa))) {
                ketQua = Arrays.copyOf(ketQua, index + 1);
                ketQua[index] = this.danhSach[i];
                index++;
            }
        }
        return ketQua;
    }

    public int getMaMoi() {
        int max = 0;
        for (int i = 0; i < this.soLuong; i++) {
            if (this.danhSach[i] != null && this.danhSach[i].getMaNhanVien() > max) {
                max = this.danhSach[i].getMaNhanVien();
            }
        }
        return max + 1;
    }
}