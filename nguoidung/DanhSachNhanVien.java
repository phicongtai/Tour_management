package nguoidung;

import java.util.Arrays;
import danhsach.DanhSach;
import java.util.Arrays;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.List;

public class DanhSachNhanVien implements DanhSach<NhanVien> {
    private NhanVien[] danhSach;
    private int soLuong;
    private Scanner sc = new Scanner(System.in);

    public DanhSachNhanVien() {
        danhSach = new NhanVien[0];
        soLuong = 0;
    }

    @Override 
    public boolean them(NhanVien nhanVien) {
        if (nhanVien == null) return false;
        if (danhSach == null) {
            danhSach = new NhanVien[0];
            soLuong = 0;
        }
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i] != null && danhSach[i].getMaNhanVien() == nhanVien.getMaNhanVien()) 
                return false;
        }
        danhSach = Arrays.copyOf(danhSach, soLuong + 1);
        danhSach[soLuong] = nhanVien;
        soLuong++;
        return true;
    }

    @Override
    public boolean them() {
        try {
            System.out.print("Nhap ma nhan vien: ");
            int ma = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Nhap ho va ten: ");
            String hoTen = sc.nextLine().trim();
            System.out.print("Nhap ngay sinh: ");
            String ngaySinh = sc.nextLine().trim();
            System.out.print("Nhap gioi tinh (1: Nu, 2: Nam, 0: Gioi tinh khac): ");
            int gioiTinh = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Nhap so dien thoại: ");
            String soDienThoai = sc.nextLine().trim();
            NhanVien nv = new NhanVien(ma, hoTen, ngaySinh, gioiTinh, soDienThoai);
            return them(nv);
        } catch (Exception e) {
            System.out.println("Loi nhap thong tin nhan vien: " + e.getMessage());
            return false;
        }
    }

    @Override
    public void nhap() {
        System.out.print("Nhap so luong nhan vien can them: ");
        try {
            int n = Integer.parseInt(sc.nextLine().trim());
            for (int i = 0; i < n; i++) {
                System.out.println("Nhap thong tin cho nhan vien thu " + (i + 1) + ":");
                boolean thanhCong = them();
                if (!thanhCong) {
                    System.out.println("Them that bai (trung ma hoac du lieu khong hop le). Vui long nhap lai.");
                    i--;
                }
            }
        } catch (Exception e) {
            System.out.println("Loi nhap du lieu danh sach!");
        }
    }

    @Override
    public void xuat() {
        if (soLuong == 0) {
            System.out.println("Danh sach nhan vien trong!");
            return;
        }
        System.out.println("--- DANH SACH NHAN VIEN ---");
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i] != null) {
                System.out.println(danhSach[i].toString());
            }
        }
    }

    @Override
    public boolean suaTheoMa(int ma) {
        NhanVien nv = timTheoMa(ma);
        if (nv == null) {
            System.out.println("Khong tim thay nhan vien co ma: " + ma);
            return false;
        }
        try {
            System.out.print("Nhap ho ten moi: ");
            String s = sc.nextLine().trim(); if (!s.isEmpty()) nv.setHoTen(s);
            System.out.print("Nhap ngay sinh moi: ");
            s = sc.nextLine().trim(); if (!s.isEmpty()) nv.setNgaySinh(s);
            System.out.print("Nhap gioi tinh moi (1:Nu, 2:Nam, 0:Khac): ");
            s = sc.nextLine().trim(); if (!s.isEmpty()) nv.setGioiTinh(Integer.parseInt(s));
            System.out.print("Nhap so dien thoai moi: ");
            s = sc.nextLine().trim(); if (!s.isEmpty()) nv.setSoDienThoai(s);
            return capNhat(nv);
        } catch (Exception e) {
            System.out.println("Loi cap nhat thong tin: " + e.getMessage());
            return false;
        }
    }

    @Override
    public NhanVien timTheoMa(int maNhanVien) {
        if (danhSach == null || soLuong <= 0 || maNhanVien <= 0) return null;
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i] != null && danhSach[i].getMaNhanVien() == maNhanVien)
                return danhSach[i];
        }
        return null;
    }

    public NhanVien[] timTheoTen(String hoTen) {
        if (danhSach == null || soLuong <= 0 || hoTen == null || hoTen.isBlank()) return new NhanVien[0];
        NhanVien[] ketQua = new NhanVien[0];
        int index = 0;
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i] != null && danhSach[i].getHoTen() != null && danhSach[i].getHoTen().equalsIgnoreCase(hoTen)) {
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
            if (danhSach[i] != null && danhSach[i].getMaNhanVien() == nhanVien.getMaNhanVien()) {
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
            if (danhSach[i] != null && danhSach[i].getMaNhanVien() == maNhanVien) {
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

    public String[] xuatDuLieu() {
        String[] dongTXT = new String[soLuong];
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i] != null) {
                dongTXT[i] = danhSach[i].toString();
            }
        }
        return dongTXT;
    }

    public boolean nhapDuLieu(String[] dongDuLieu) {
        if (dongDuLieu == null) return false;
        NhanVien[] temp = new NhanVien[dongDuLieu.length];
        int count = 0;
        for (String line : dongDuLieu) {
            String[] parts = line.split(",");
            if (parts.length < 5) return false;
            try {
                int maNhanVien = Integer.parseInt(parts[0].trim());
                String hoTen = parts[1].trim();
                String ngaySinh = parts[2].trim();
                int gioiTinh = Integer.parseInt(parts[3].trim());
                String soDienThoai = parts[4].trim();
                NhanVien nv = new NhanVien(maNhanVien, hoTen, ngaySinh, gioiTinh, soDienThoai);
                temp[count] = nv;
                count++;
            } catch (Exception e) {
                return false;
            }
        }
        danhSach = temp;
        soLuong = count;
        return true;
    }

    @Override
    public boolean docFile(String duongDan) {
        return false;
    }

    @Override
    public boolean ghiFile(String duongDan) {
        return false;
    }

    // Methods còn lại
}