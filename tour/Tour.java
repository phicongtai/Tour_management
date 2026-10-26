package tour;


/**
 * Lớp cơ sở trừu tượng cho các loại tour.
 * Định dạng TXT: maTour|tenTour|diemKhoiHanh|diemDen|soNgay|donGiaCoBan
 */
public abstract class Tour {
    private int maTour;
    private String tenTour;
    private String diemKhoiHanh;
    private String diemDen;
    private int soNgay;
    private double donGiaCoBan;

    protected Tour() {
    }

    protected Tour(int maTour, String tenTour, String diemKhoiHanh,
                   String diemDen, int soNgay, double donGiaCoBan) {
        setMaTour(maTour);
        setTenTour(tenTour);
        setDiemKhoiHanh(diemKhoiHanh);
        setDiemDen(diemDen);
        setSoNgay(soNgay);
        setDonGiaCoBan(donGiaCoBan);
    }

    public abstract double tinhGiaVe();

    public int getMaTour() { return maTour; }
    public void setMaTour(int maTour) {
        if (maTour <= 0) throw new IllegalArgumentException("Mã tour phải lớn hơn 0.");
        this.maTour = maTour;
    }

    public String getTenTour() { return tenTour; }
    public void setTenTour(String tenTour) { this.tenTour = batBuoc(tenTour, "Tên tour"); }

    public String getDiemKhoiHanh() { return diemKhoiHanh; }
    public void setDiemKhoiHanh(String diemKhoiHanh) {
        this.diemKhoiHanh = batBuoc(diemKhoiHanh, "Điểm khởi hành");
    }

    public String getDiemDen() { return diemDen; }
    public void setDiemDen(String diemDen) { this.diemDen = batBuoc(diemDen, "Điểm đến"); }

    public int getSoNgay() { return soNgay; }
    public void setSoNgay(int soNgay) {
        if (soNgay <= 0) throw new IllegalArgumentException("Số ngày phải lớn hơn 0.");
        this.soNgay = soNgay;
    }

    public double getDonGiaCoBan() { return donGiaCoBan; }
    public void setDonGiaCoBan(double donGiaCoBan) {
        if (!Double.isFinite(donGiaCoBan) || donGiaCoBan < 0)
            throw new IllegalArgumentException("Đơn giá cơ bản phải là số hữu hạn và không âm.");
        this.donGiaCoBan = donGiaCoBan;
    }

    protected static String batBuoc(String value, String tenTruong) {
        if (value == null || value.trim().isEmpty())
            throw new IllegalArgumentException(tenTruong + " không được để trống.");
        return value.trim();
    }

    protected static String[] tachDong(String dong, int soTruong) {
        if (dong == null) return null;
        String[] fields = dong.split("\\|", -1); // -1 để không bỏ mất trường rỗng cuối dòng
        return fields.length == soTruong ? fields : null;
    }

    public String chuyenThanhDong() {
        return maTour + "|" + tenTour + "|" + diemKhoiHanh + "|" + diemDen
                + "|" + soNgay + "|" + donGiaCoBan;
    }

    /** Nạp phần thuộc tính chung từ dòng TXT; lớp con kiểm tra thêm các trường riêng. */
    protected boolean napThongTinChung(String[] fields) {
        try {
            if (fields == null || fields.length < 6) return false;
            int ma = Integer.parseInt(fields[0].trim());
            String ten = fields[1].trim();
            String khoiHanh = fields[2].trim();
            String den = fields[3].trim();
            int ngay = Integer.parseInt(fields[4].trim());
            double gia = Double.parseDouble(fields[5].trim());
            if (ma <= 0 || ten.isEmpty() || khoiHanh.isEmpty() || den.isEmpty()
                    || ngay <= 0 || !Double.isFinite(gia) || gia < 0) return false;
            this.maTour = ma;
            this.tenTour = ten;
            this.diemKhoiHanh = khoiHanh;
            this.diemDen = den;
            this.soNgay = ngay;
            this.donGiaCoBan = gia;
            return true;
        } catch (RuntimeException ex) {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Mã tour: " + maTour + ", tên: " + tenTour
                + ", khởi hành: " + diemKhoiHanh + ", điểm đến: " + diemDen
                + ", số ngày: " + soNgay + ", giá cơ bản: " + donGiaCoBan
                + ", giá vé: " + tinhGiaVe();
    }
}
