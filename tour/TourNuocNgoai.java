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
        System.out.println(getMaTour() +"|"+ getTenTour() +"|"+ getDiemKhoiHanh() +"|"+ getDiemDen() +"|"+ getSoNgay() +"|"+ getDonGiaCoBan() +"|"+ this.quocGia +"|"+ this.canVisa +"|"+ this.phiVisa);
    }
    @Override
    public void nhap() {
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
        System.out.println("Nhap quoc gia:");
        this.quocGia = in.nextLine();
        System.out.println("Nhap can visa (true/false):");
        this.canVisa = in.nextBoolean();
        in.nextLine();
        if (this.canVisa) {
            System.out.println("Nhap phi visa:");
            this.phiVisa = in.nextLong();
            in.nextLine();
        }
    } 
}
