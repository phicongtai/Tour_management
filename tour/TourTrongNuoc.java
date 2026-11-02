package tour;

public class TourTrongNuoc extends Tour {
    private String vungMien;
    private long phuPhi;

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
    public boolean capNhatThongTinRieng(String vungMien, long phuPhi) {
    if (vungMien == null || vungMien.trim().isEmpty() || phuPhi < 0) {
        return false;
    }

    this.vungMien = vungMien;
    this.phuPhi = phuPhi;

    return true;
}

@Override
public long tinhGiaVe() {
    return getDonGiaCoBan() + phuPhi;
}
}
