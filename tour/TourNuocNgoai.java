package tour;

public class TourNuocNgoai extends Tour {
    private String quocGia;
    private boolean canVisa;
    private long phiVisa;

    public TourNuocNgoai() {
    }

    public TourNuocNgoai(int maTour, String tenTour, String diemKhoiHanh, String diemDen, int soNgay, long donGiaCoBan, String quocGia, boolean canVisa, long phiVisa) {
        super(maTour, tenTour, diemKhoiHanh, diemDen, soNgay, donGiaCoBan);
        this.quocGia = quocGia;
        this.canVisa = canVisa;
        this.phiVisa = phiVisa;
    }

    public String getQuocGia() {
        return quocGia;
    }

    public void setQuocGia(String quocGia) {
        this.quocGia = quocGia;
    }

    public boolean isCanVisa() {
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
}
