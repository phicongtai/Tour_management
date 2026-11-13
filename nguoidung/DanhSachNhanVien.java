package nguoidung;

import danhsach.DanhSach;
import kho.KhoDuLieu;
import java.util.Scanner;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class DanhSachNhanVien implements DanhSach<NhanVien> {
    private NhanVien[] danhSach;
    private int soLuong;
    private Scanner sc = KhoDuLieu.getScanner();

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
        NhanVien nv = new NhanVien();
        nv.nhap();
        return this.them(nv);
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
        System.out.println("Danh sach nhan vien:");
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
        
        System.out.print("Chinh Ho ten: ");
        String hoTen = this.sc.nextLine();
        if (!hoTen.isBlank()) { 
            nv.setHoTen(hoTen); 
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
                if (parts.length >= 5) {
                    NhanVien nv = new NhanVien(
                        Integer.parseInt(parts[0].trim()),
                        parts[1].trim(),
                        parts[2].trim(),
                        Integer.parseInt(parts[3].trim()),
                        parts[4].trim()
                    );
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
                              this.danhSach[i].getHoTen() + "|" +
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

    public NhanVien[] timTheoTen(String hoTen) {
        if (this.danhSach == null || this.soLuong <= 0 || hoTen == null || hoTen.isBlank()) {
            return new NhanVien[0];
        }
        NhanVien[] ketQua = new NhanVien[0];
        int index = 0;
        for (int i = 0; i < this.soLuong; i++) {
            if (this.danhSach[i] != null && this.danhSach[i].getHoTen() != null && this.danhSach[i].getHoTen().equalsIgnoreCase(hoTen)) {
                ketQua = Arrays.copyOf(ketQua, index + 1);
                ketQua[index] = this.danhSach[i];
                index++;
            }
        }
        return ketQua;
    }
}