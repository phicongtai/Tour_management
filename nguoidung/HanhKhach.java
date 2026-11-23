package nguoidung;

import java.util.Scanner;

public class HanhKhach {
    private int maHanhKhach;
    private String ho;
    private String ten;
    private String ngaySinh;
    private int gioiTinh;
    private String soDienThoai;
    private static final Scanner sc = new Scanner(System.in);

    public HanhKhach() {
    }

    public HanhKhach(int maHanhKhach, String ho, String ten, String ngaySinh, int gioiTinh, String soDienThoai) {
        this.maHanhKhach = maHanhKhach;
        setHo(ho);
        setTen(ten);
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

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        if (ho != null) {
            this.ho = ho.trim();
        }
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        if (ten != null) {
            this.ten = ten.trim();
        }
    }

    // Ho ten day du chi de hien thi, du lieu luu tru van tach rieng ho va ten
    public String getHoTen() {
        String hoDayDu = (ho == null) ? "" : ho;
        String tenDayDu = (ten == null) ? "" : ten;
        return (hoDayDu + " " + tenDayDu).trim();
    }

    public void setHoTen(String hoTen) {
        if (hoTen == null) return;
        String[] parts = hoTen.trim().split("\\s+", 2);
        this.ho = parts[0];
        this.ten = (parts.length > 1) ? parts[1] : "";
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
        
        System.out.print("Nhap ho: ");
        this.ho = sc.nextLine().trim();
        
        System.out.print("Nhap ten: ");
        this.ten = sc.nextLine().trim();
        
        System.out.print("Nhap ngay sinh (vd: 2001-08-25): ");
        this.ngaySinh = sc.nextLine().trim();
        
        System.out.print("Nhap gioi tinh (0: Nu, 1: Nam): ");
        this.gioiTinh = Integer.parseInt(sc.nextLine().trim());
        
        System.out.print("Nhap so dien thoai: ");
        this.soDienThoai = sc.nextLine().trim();
    }

    public void xuat() {
        System.out.printf("%-6s | %-12s | %-20s | %-12s | %-10s | %-14s%n",
                maHanhKhach, ho, ten, ngaySinh, (gioiTinh == 1 ? "Nam" : "Nu"), soDienThoai);
    }
}
