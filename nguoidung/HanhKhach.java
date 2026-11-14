package nguoidung;

import java.util.Scanner;

public class HanhKhach {
    private int maHanhKhach;
    private String hoTen;
    private String ngaySinh;
    private int gioiTinh;
    private String soDienThoai;
    private Scanner sc = kho.KhoDuLieu.getScanner();

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

    public void nhap() {
        this.maHanhKhach = kho.KhoDuLieu.getDanhSachHanhKhach().getMaMoi();
        System.out.println("Ma hanh khach (tu dong): " + this.maHanhKhach);
        
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
        System.out.printf("%-6s | %-24s | %-12s | %-10s | %-14s%n",
                maHanhKhach, hoTen, ngaySinh, (gioiTinh == 1 ? "Nam" : "Nu"), soDienThoai);
    }
}
