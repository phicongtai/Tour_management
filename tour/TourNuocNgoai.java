package tour;

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

    // Methods còn lại
    public boolean capNhatThongTinRieng(String quocGia, boolean canVisa, long phiVisa) {
        if (quocGia == null || quocGia.trim().isEmpty() || phiVisa < 0) {
            return false;
        }

        this.quocGia = quocGia;
        this.canVisa = canVisa;
        this.phiVisa = phiVisa;

        return true;
    }
}
