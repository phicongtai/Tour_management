package nguoidung;

import java.util.Scanner;

public class NhanVien {
    private int maNhanVien;
    private String hoTen;
    private String ngaySinh;
    private int gioiTinh;
    private String soDienThoai;
    private Scanner sc = new Scanner(System.in);

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
            this.hoTen = hoTen.trim();
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
        if (gioiTinh == 0 || gioiTinh == 1) {
            this.gioiTinh = gioiTinh;
        }
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        if (soDienThoai != null) {
            this.soDienThoai = soDienThoai.trim();
        }
    }

    public boolean capNhatThongTin(String hoTen, String ngaySinh, int gioiTinh, String soDienThoai) {
        if (hoTen == null || hoTen.isBlank() || soDienThoai == null || soDienThoai.isBlank()) {
            return false;
        }
        if (gioiTinh != 0 && gioiTinh != 1) {
            return false;
        }
        this.hoTen = hoTen.trim();
        this.ngaySinh = ngaySinh.trim();
        this.gioiTinh = gioiTinh;
        this.soDienThoai = soDienThoai.trim();
        return true;
    }
    public void nhap() {
        this.maNhanVien = kho.KhoDuLieu.getDanhSachNhanVien().getMaMoi();
        System.out.println("Ma nhan vien (tu dong): " + this.maNhanVien);
        
        System.out.print("Nhap ho va ten: ");
        this.hoTen = sc.nextLine().trim();
        
        System.out.print("Nhap ngay sinh (vd: 2001-08-25): ");
        this.ngaySinh = sc.nextLine().trim();
        
        System.out.print("Nhap gioi tinh (0: Nu, 1: Nam): ");
        this.gioiTinh = Integer.parseInt(sc.nextLine().trim());
        
        System.out.print("Nhap so dien thoai: ");
        this.soDienThoai = sc.nextLine().trim();
    }
    public void xuat() {
        System.out.println(maNhanVien + "|" + hoTen + "|" + ngaySinh + "|" + gioiTinh + "|" + soDienThoai);
    }
}