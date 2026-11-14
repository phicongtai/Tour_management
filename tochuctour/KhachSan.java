package tochuctour;

import java.util.Scanner;
import kho.KhoDuLieu;

public class KhachSan {
    private int maKhachSan;
    private String tenKhachSan;
    private long giaOMoiNguoiMoiDem;
    private long giaAnMoiNguoiMoiNgay;

    public KhachSan() {
    }

    public KhachSan(int maKhachSan, String tenKhachSan, long giaOMoiNguoiMoiDem, long giaAnMoiNguoiMoiNgay) {
        this.maKhachSan = maKhachSan;
        this.tenKhachSan = tenKhachSan;
        this.giaOMoiNguoiMoiDem = giaOMoiNguoiMoiDem;
        this.giaAnMoiNguoiMoiNgay = giaAnMoiNguoiMoiNgay;
    }

    public int getMaKhachSan() {
        return maKhachSan;
    }

    public void setMaKhachSan(int maKhachSan) {
        this.maKhachSan = maKhachSan;
    }

    public String getTenKhachSan() {
        return tenKhachSan;
    }

    public void setTenKhachSan(String tenKhachSan) {
        this.tenKhachSan = tenKhachSan;
    }

    public long getGiaOMoiNguoiMoiDem() {
        return giaOMoiNguoiMoiDem;
    }

    public void setGiaOMoiNguoiMoiDem(long giaOMoiNguoiMoiDem) {
        this.giaOMoiNguoiMoiDem = giaOMoiNguoiMoiDem;
    }

    public long getGiaAnMoiNguoiMoiNgay() {
        return giaAnMoiNguoiMoiNgay;
    }

    public void setGiaAnMoiNguoiMoiNgay(long giaAnMoiNguoiMoiNgay) {
        this.giaAnMoiNguoiMoiNgay = giaAnMoiNguoiMoiNgay;
    }

    public void xuat() {
        System.out.printf("%-15s | %-28s | %-18s | %-18s%n",
                this.maKhachSan, this.tenKhachSan, this.giaOMoiNguoiMoiDem, this.giaAnMoiNguoiMoiNgay);
    }

    public void nhap() {
        Scanner in = KhoDuLieu.getScanner();
        this.maKhachSan = KhoDuLieu.getDanhSachKhachSan().getMaMoi();
        System.out.println("Ma khach san (tu dong): " + this.maKhachSan);
        System.out.print("Nhap ten khach san: ");
        this.tenKhachSan = in.nextLine();
        System.out.print("Nhap gia o moi nguoi moi dem: ");
        this.giaOMoiNguoiMoiDem = in.nextLong();
        in.nextLine();
        System.out.print("Nhap gia an moi nguoi moi ngay: ");
        this.giaAnMoiNguoiMoiNgay = in.nextLong();
        in.nextLine();
    }
}
