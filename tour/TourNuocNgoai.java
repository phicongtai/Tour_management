package tour;

/** Tour nước ngoài: cộng phí visa khi tour yêu cầu visa. */
public class TourNuocNgoai extends Tour {
    private String quocGia;
    private boolean canVisa;
    private double phiVisa;

    public TourNuocNgoai() {
    }

    public TourNuocNgoai(int maTour, String tenTour, String diemKhoiHanh,
                         String diemDen, int soNgay, double donGiaCoBan,
                         String quocGia, boolean canVisa, double phiVisa) {
        super(maTour, tenTour, diemKhoiHanh, diemDen, soNgay, donGiaCoBan);
        setQuocGia(quocGia);
        setCanVisa(canVisa);
        setPhiVisa(phiVisa);
    }

    public String getQuocGia() { return quocGia; }
    public void setQuocGia(String quocGia) {
        this.quocGia = batBuoc(quocGia, "Quốc gia");
    }

    public boolean isCanVisa() { return canVisa; }
    public void setCanVisa(boolean canVisa) { this.canVisa = canVisa; }

    public double getPhiVisa() { return phiVisa; }
    public void setPhiVisa(double phiVisa) {
        if (!Double.isFinite(phiVisa) || phiVisa < 0)
            throw new IllegalArgumentException("Phí visa phải là số hữu hạn và không âm.");
        this.phiVisa = phiVisa;
    }

    @Override
    public double tinhGiaVe() {
        return getDonGiaCoBan() + (canVisa ? phiVisa : 0.0);
    }

    /** TXT: 6 trường chung + quocGia + canVisa + phiVisa. */
    @Override
    public String chuyenThanhDong() {
        return super.chuyenThanhDong() + "|" + quocGia + "|" + canVisa + "|" + phiVisa;
    }

    @Override
    public boolean taoTuDong(String dong) {
        String[] fields = tachDong(dong, 9);
        if (fields == null) return false;
        try {
            String quocGiaMoi = fields[6].trim();
            String visaText = fields[7].trim();
            double phiMoi = Double.parseDouble(fields[8].trim());
            if (quocGiaMoi.isEmpty()
                    || !(visaText.equalsIgnoreCase("true") || visaText.equalsIgnoreCase("false"))
                    || !Double.isFinite(phiMoi) || phiMoi < 0) return false;
            if (!napThongTinChung(fields)) return false;
            this.quocGia = quocGiaMoi;
            this.canVisa = Boolean.parseBoolean(visaText);
            this.phiVisa = phiMoi;
            return true;
        } catch (RuntimeException ex) {
            return false;
        }
    }

    @Override
    public String toString() {
        return "[Tour nước ngoài] " + super.toString()
                + ", quốc gia: " + quocGia + ", cần visa: " + canVisa
                + ", phí visa: " + phiVisa;
    }
}

