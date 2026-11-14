package tour;

import java.util.Scanner;

import kho.KhoDuLieu;

public abstract class Tour {
    private int maTour;
    private String tenTour;
    private String diemKhoiHanh;
    private String diemDen;
    private int soNgay;
    private long donGiaCoBan;

    public Tour() {
    }

    public Tour(int maTour, String tenTour, String diemKhoiHanh, String diemDen, int soNgay, long donGiaCoBan) {
        this.maTour = maTour;
        this.tenTour = tenTour;
        this.diemKhoiHanh = diemKhoiHanh;
        this.diemDen = diemDen;
        this.soNgay = soNgay;
        this.donGiaCoBan = donGiaCoBan;
    }

    public int getMaTour() {
        return maTour;
    }

    public void setMaTour(int maTour) {
        this.maTour = maTour;
    }

    public String getTenTour() {
        return tenTour;
    }

    public void setTenTour(String tenTour) {
        this.tenTour = tenTour;
    }

    public String getDiemKhoiHanh() {
        return diemKhoiHanh;
    }

    public void setDiemKhoiHanh(String diemKhoiHanh) {
        this.diemKhoiHanh = diemKhoiHanh;
    }

    public String getDiemDen() {
        return diemDen;
    }

    public void setDiemDen(String diemDen) {
        this.diemDen = diemDen;
    }

    public int getSoNgay() {
        return soNgay;
    }

    public void setSoNgay(int soNgay) {
        this.soNgay = soNgay;
    }

    public long getDonGiaCoBan() {
        return donGiaCoBan;
    }

    public void setDonGiaCoBan(long donGiaCoBan) {
        this.donGiaCoBan = donGiaCoBan;
    }

    public long tinhGiaVe(){
    return donGiaCoBan;
    }
    public void nhap(){
        Scanner in = KhoDuLieu.getScanner();
        setMaTour(KhoDuLieu.getDanhSachTour().getMaMoi());
        System.out.println("Ma tour (tu dong): " + getMaTour());
        System.out.println("Nhap ten tour:");
        setTenTour(in.nextLine());
        System.out.println("Nhap diem khoi hanh:");
        setDiemKhoiHanh(in.nextLine());
        System.out.println("Nhap diem den:");
        setDiemDen(in.nextLine());
        System.out.println("Nhap so ngay:");
        setSoNgay(in.nextInt());
        System.out.println("Nhap don gia co ban:");
        setDonGiaCoBan(in.nextLong());
        in.nextLine();
    }
    public void xuat(){
        System.out.print(this.maTour +"|"+ this.tenTour +"|"+ this.diemKhoiHanh +"|"+ this.diemDen +"|"+ this.soNgay +"|"+ this.donGiaCoBan+"|");
    }
}
