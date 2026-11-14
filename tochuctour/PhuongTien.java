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
        System.out.printf("%-15s | %-25s | %-15s | %-20s%n",
                this.maPhuongTien, this.tenPhuongTien, this.soChoToiDa, this.giaMoiNguoiMoiNgay);
    }

    public void nhap() {
        Scanner in = KhoDuLieu.getScanner();
        this.maPhuongTien = KhoDuLieu.getDanhSachPhuongTien().getMaMoi();
        System.out.println("Ma phuong tien (tu dong): " + this.maPhuongTien);
        System.out.print("Nhap ten phuong tien: ");
        this.tenPhuongTien = in.nextLine();
        System.out.print("Nhap so cho toi da: ");
        this.soChoToiDa = in.nextInt();
        in.nextLine();
        System.out.print("Nhap gia moi nguoi moi ngay: ");
        this.giaMoiNguoiMoiNgay = in.nextLong();
        in.nextLine();
    }
}
