package tour;
import java.util.Scanner;
import kho.KhoDuLieu;
public class TourNuocNgoai extends Tour {
    private String quocGia;
    private boolean canVisa;
    private long phiVisa;

    // Constructors
    public TourNuocNgoai() {
    }

    public TourNuocNgoai(String quocGia, boolean canVisa, long phiVisa) {
        this.quocGia = quocGia;
        this.canVisa = canVisa;
        this.phiVisa = phiVisa;
    }

    public TourNuocNgoai(int maTour, String tenTour, String diemKhoiHanh, String diemDen, int soNgay, long donGiaCoBan, String quocGia, boolean canVisa, long phiVisa) {
        super(maTour, tenTour, diemKhoiHanh, diemDen, soNgay, donGiaCoBan);
        this.quocGia = quocGia;
        this.canVisa = canVisa;
        this.phiVisa = phiVisa;
    }

    // Getters / Setters
    public String getQuocGia() {
        return quocGia;
    }

    public void setQuocGia(String quocGia) {
        this.quocGia = quocGia;
    }

    public boolean isCanVisa() {
        return canVisa;
    }

    public boolean getCanVisa() {
        return canVisa;
    }

    public void setCanVisa(boolean canVisa) {
        this.canVisa = canVisa;
    }

    public long getPhiVisa() {
        return phiVisa;
    }

    public void setPhiVisa(long phiVisa) {
        this.phiVisa = phiVisa;
    }

    // Methods @Override
    @Override
    public long tinhGiaVe() {
        if (canVisa) {
            return getDonGiaCoBan() + phiVisa;
        }

        return getDonGiaCoBan();
    }
    @Override
    public void xuat() {
        super.xuat();
        System.out.printf(" | %-12s | %-12s | %-10s | %-10s | %-10s%n",
                "Nuoc ngoai", quocGia, "-", canVisa, phiVisa);
    }
    @Override
    public void nhap() {
        super.nhap();
        Scanner in = new Scanner(System.in);
        System.out.println("Nhap quoc gia:");
        this.quocGia = in.nextLine();
        System.out.println("Nhap can visa (true/false):");
        this.canVisa = in.nextBoolean();
        if (this.canVisa) {
            System.out.println("Nhap phi visa:");
            this.phiVisa = in.nextLong();
        }
    } 
}
