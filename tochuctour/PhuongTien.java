package tochuctour;

import java.util.Scanner;
import kho.KhoDuLieu;

public class PhuongTien {
    private int maPhuongTien;
    private String tenPhuongTien;
    private int soChoToiDa;
    private long giaMoiNguoiMoiNgay;

    public PhuongTien() {
    }

    public PhuongTien(int maPhuongTien, String tenPhuongTien, int soChoToiDa, long giaMoiNguoiMoiNgay) {
        this.maPhuongTien = maPhuongTien;
        this.tenPhuongTien = tenPhuongTien;
        this.soChoToiDa = soChoToiDa;
        this.giaMoiNguoiMoiNgay = giaMoiNguoiMoiNgay;
    }

    public int getMaPhuongTien() {
        return maPhuongTien;
    }

    public void setMaPhuongTien(int maPhuongTien) {
        this.maPhuongTien = maPhuongTien;
    }

    public String getTenPhuongTien() {
        return tenPhuongTien;
    }

    public void setTenPhuongTien(String tenPhuongTien) {
        this.tenPhuongTien = tenPhuongTien;
    }

    public int getSoChoToiDa() {
        return soChoToiDa;
    }

    public void setSoChoToiDa(int soChoToiDa) {
        this.soChoToiDa = soChoToiDa;
    }

    public long getGiaMoiNguoiMoiNgay() {
        return giaMoiNguoiMoiNgay;
    }

    public void setGiaMoiNguoiMoiNgay(long giaMoiNguoiMoiNgay) {
        this.giaMoiNguoiMoiNgay = giaMoiNguoiMoiNgay;
    }

    public void xuat() {
        System.out.println(this.maPhuongTien + "|" + this.tenPhuongTien + "|" + this.soChoToiDa + "|" + this.giaMoiNguoiMoiNgay);
    }

    public void nhap() {
        Scanner in = KhoDuLieu.getScanner();
        System.out.print("Nhập mã phương tiện: ");
        this.maPhuongTien = in.nextInt();
        in.nextLine();
        System.out.print("Nhập tên phương tiện: ");
        this.tenPhuongTien = in.nextLine();
        System.out.print("Nhập số chỗ tối đa: ");
        this.soChoToiDa = in.nextInt();
        in.nextLine();
        System.out.print("Nhập giá mỗi người mỗi ngày: ");
        this.giaMoiNguoiMoiNgay = in.nextLong();
        in.nextLine();
    }
}
