package tour;
import java.util.Scanner;
import kho.KhoDuLieu;
public class TourTrongNuoc extends Tour {
    private String vungMien;
    private long phuPhi;

    // Constructors
    public TourTrongNuoc() {
    }

    public TourTrongNuoc(String vungMien, long phuPhi) {
        this.vungMien = vungMien;
        this.phuPhi = phuPhi;
    }

    public TourTrongNuoc(int maTour, String tenTour, String diemKhoiHanh, String diemDen, int soNgay, long donGiaCoBan, String vungMien, long phuPhi) {
        super(maTour, tenTour, diemKhoiHanh, diemDen, soNgay, donGiaCoBan);
        this.vungMien = vungMien;
        this.phuPhi = phuPhi;
    }

    // Getters / Setters
    public String getVungMien() {
        return vungMien;
    }

    public void setVungMien(String vungMien) {
        this.vungMien = vungMien;
    }

    public long getPhuPhi() {
        return phuPhi;
    }

    public void setPhuPhi(long phuPhi) {
        this.phuPhi = phuPhi;
    }

    // Methods @Override
    @Override
    public long tinhGiaVe() {
        return getDonGiaCoBan() + getPhuPhi();
    }
    @Override
    public void xuat() {
        super.xuat();
        System.out.println(this.vungMien +"|"+ this.phuPhi);
    }
    @Override 
    public void nhap(){
        super.nhap();
        Scanner in = KhoDuLieu.getScanner();
        System.out.println("Nhap vung mien:");
        this.vungMien=in.nextLine();
        System.out.println("Nhap phu phi:");
        this.phuPhi=in.nextLong();
        in.nextLine();
    }
}
