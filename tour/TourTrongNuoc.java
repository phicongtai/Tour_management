package tour;

/** Tour trong nước: giá vé = đơn giá cơ bản + phụ phí. */
public class TourTrongNuoc extends Tour {
    private String vungMien;
    private double phuPhi;

    public TourTrongNuoc() {
    }

    public TourTrongNuoc(int maTour, String tenTour, String diemKhoiHanh,
                         String diemDen, int soNgay, double donGiaCoBan,
                         String vungMien, double phuPhi) {
        super(maTour, tenTour, diemKhoiHanh, diemDen, soNgay, donGiaCoBan);
        setVungMien(vungMien);
        setPhuPhi(phuPhi);
    }

    public String getVungMien() { return vungMien; }
    public void setVungMien(String vungMien) {
        this.vungMien = batBuoc(vungMien, "Vùng miền");
    }

    public double getPhuPhi() { return phuPhi; }
    public void setPhuPhi(double phuPhi) {
        if (!Double.isFinite(phuPhi) || phuPhi < 0)
            throw new IllegalArgumentException("Phụ phí phải là số hữu hạn và không âm.");
        this.phuPhi = phuPhi;
    }

    @Override
    public double tinhGiaVe() {
        return getDonGiaCoBan() + phuPhi;
    }

    /** TXT: 6 trường chung + vungMien + phuPhi. */
    @Override
    public String chuyenThanhDong() {
        return super.chuyenThanhDong() + "|" + vungMien + "|" + phuPhi;
    }

    @Override
    public boolean taoTuDong(String dong) {
        String[] fields = tachDong(dong, 8);
        if (fields == null) return false;
        try {
            String mien = fields[6].trim();
            double phi = Double.parseDouble(fields[7].trim());
            if (mien.isEmpty() || !Double.isFinite(phi) || phi < 0) return false;
            // Chỉ cập nhật đối tượng sau khi toàn bộ dòng đã được kiểm tra hợp lệ.
            if (!napThongTinChung(fields)) return false;
            this.vungMien = mien;
            this.phuPhi = phi;
            return true;
        } catch (RuntimeException ex) {
            return false;
        }
    }

    @Override
    public String toString() {
        return "[Tour trong nước] " + super.toString()
                + ", vùng miền: " + vungMien + ", phụ phí: " + phuPhi;
    }
}
