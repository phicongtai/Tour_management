package tochuctour;

import java.util.Scanner;
import kho.KhoDuLieu;

public class ChiTietKeHoachTour {
    private static final Scanner in = new Scanner(System.in);
    private String ngay;
    private int maPhuongTien;
    private int maKhachSan;
    private long tienAn;
    private long tienO;
    private long tienDiLai;

    public ChiTietKeHoachTour() {
    }

    public ChiTietKeHoachTour(String ngay, int maPhuongTien, int maKhachSan, long tienAn, long tienO, long tienDiLai) {
        this.ngay = ngay;
        this.maPhuongTien = maPhuongTien;
        this.maKhachSan = maKhachSan;
        this.tienAn = tienAn;
        this.tienO = tienO; 
        this.tienDiLai = tienDiLai;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public int getMaPhuongTien() {
        return maPhuongTien;
    }

    public void setMaPhuongTien(int maPhuongTien) {
        this.maPhuongTien = maPhuongTien;
    }

    public int getMaKhachSan() {
        return maKhachSan;
    }

    public void setMaKhachSan(int maKhachSan) {
        this.maKhachSan = maKhachSan;
    }

    public long getTienAn() {
        return tienAn;
    }

    public void setTienAn(long tienAn) {
        this.tienAn = tienAn;
    }

    public long getTienO() {
        return tienO;
    }

    public void setTienO(long tienO) {
        this.tienO = tienO;
    }

    public long getTienDiLai() {
        return tienDiLai;
    }

    public void setTienDiLai(long tienDiLai) {
        this.tienDiLai = tienDiLai;
    }

    public long tinhChiPhiNgay(int soKhach){
        if(soKhach <= 0){
            return -1;
        }
        return soKhach * (this.tienAn + this.tienDiLai + this.tienO);
    }

    public void xuat() {
        PhuongTien phuongTienLienQuan = kho.KhoDuLieu.getDanhSachPhuongTien().timTheoMa(this.maPhuongTien);
        KhachSan khachSanLienQuan = kho.KhoDuLieu.getDanhSachKhachSan().timTheoMa(this.maKhachSan);
        String pt = (phuongTienLienQuan != null) ? phuongTienLienQuan.getTenPhuongTien() : ("PT #" + this.maPhuongTien);
        String ks = (khachSanLienQuan != null) ? khachSanLienQuan.getTenKhachSan() : ("KS #" + this.maKhachSan);
        System.out.printf("%-12s | %-25s | %-30s | %-12s | %-12s | %-12s%n", this.ngay, pt, ks, this.tienAn, this.tienO, this.tienDiLai);
    }

    public void nhap() {
        System.out.print("Nhap ngay (vd: 2026-11-20): ");
        this.ngay = in.nextLine();
        System.out.print("Nhap ma phuong tien: ");
        this.maPhuongTien = in.nextInt();
        in.nextLine();
        System.out.print("Nhap ma khach san: ");
        this.maKhachSan = in.nextInt();
        in.nextLine();
        System.out.print("Nhap tien an: ");
        this.tienAn = in.nextLong();
        in.nextLine();
        System.out.print("Nhap tien o: ");
        this.tienO = in.nextLong();
        in.nextLine();
        System.out.print("Nhap tien di lai: ");
        this.tienDiLai = in.nextLong();
        in.nextLine();
    }
}
