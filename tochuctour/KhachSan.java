package tochuctour;

import java.util.Scanner;

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
        System.out.println(this.maKhachSan + "|" + this.tenKhachSan + "|" + this.giaOMoiNguoiMoiDem + "|" + this.giaAnMoiNguoiMoiNgay);
    }

    public void nhap() {
        Scanner in = new Scanner(System.in);
        System.out.print("Nhập mã khách sạn: ");
        this.maKhachSan = in.nextInt();
        in.nextLine();
        System.out.print("Nhập tên khách sạn: ");
        this.tenKhachSan = in.nextLine();
        System.out.print("Nhập giá ở mỗi người mỗi đêm: ");
        this.giaOMoiNguoiMoiDem = in.nextLong();
        in.nextLine();
        System.out.print("Nhập giá ăn mỗi người mỗi ngày: ");
        this.giaAnMoiNguoiMoiNgay = in.nextLong();
        in.nextLine();
    }
}
