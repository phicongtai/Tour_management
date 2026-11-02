package datvevathanhtoan;

import kho.KhoDuLieu;

public class HoaDon {
    private int maHoaDon;
    private int maDonDatTour;
    private int maNhanVien;
    private String ngayLap;
    private long soTienDaThanhToan;
    private ChiTietHoaDon chiTiet;

    public HoaDon() {
    }

    public HoaDon(int maHoaDon, int maDonDatTour, int maNhanVien, String ngayLap) {
        this.maHoaDon = maHoaDon;
        this.maDonDatTour = maDonDatTour;
        this.maNhanVien = maNhanVien;
        this.ngayLap = ngayLap;
        this.chiTiet = new ChiTietHoaDon(maHoaDon);
    }

    public HoaDon(int maHoaDon, int maDonDatTour, int maNhanVien, String ngayLap,
                  long soTienDaThanhToan, ChiTietHoaDon chiTiet) {
        this.maHoaDon = maHoaDon;
        this.maDonDatTour = maDonDatTour;
        this.maNhanVien = maNhanVien;
        this.ngayLap = ngayLap;
        this.soTienDaThanhToan = soTienDaThanhToan;
        this.chiTiet = chiTiet;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getMaDonDatTour() {
        return maDonDatTour;
    }

    public void setMaDonDatTour(int maDonDatTour) {
        this.maDonDatTour = maDonDatTour;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(String ngayLap) {
        this.ngayLap = ngayLap;
    }

    public long getSoTienDaThanhToan() {
        return soTienDaThanhToan;
    }

    public void setSoTienDaThanhToan(long soTienDaThanhToan) {
        this.soTienDaThanhToan = soTienDaThanhToan;
    }

    public ChiTietHoaDon getChiTiet() {
        return chiTiet;
    }

    public void setChiTiet(ChiTietHoaDon chiTiet) {
        this.chiTiet = chiTiet;
    }

    public boolean themVeVaoChiTiet(int maVe, DanhSachVe dsVe) {
        if (dsVe == null || maVe <= 0) {
            return false;
        }

        Ve ve = dsVe.timTheoMa(maVe);
        if (ve == null || ve.getMaDonDatTour() != maDonDatTour
                || (chiTiet != null && chiTiet.coChuaVe(maVe))
                || daThuocHoaDonKhac(maVe)) {
            return false;
        }

        if (chiTiet == null) {
            chiTiet = new ChiTietHoaDon(maHoaDon);
        }
        return chiTiet.themVe(maVe) && chiTiet.capNhatTongTien(dsVe);
    }

    public int[] layDanhSachMaVe() {
        return chiTiet == null ? new int[0] : chiTiet.layDanhSachMaVe();
    }

    public long tinhTongTien() {
        return chiTiet == null ? 0 : chiTiet.layTongTien();
    }

    public long tinhSoTienConLai() {
        long tongTien = tinhTongTien();
        if (soTienDaThanhToan < 0 || tongTien <= 0 || soTienDaThanhToan > tongTien) {
            return 0;
        }
        return tongTien - soTienDaThanhToan;
    }

    public boolean ghiNhanThanhToan(long soTien, DanhSachVe dsVe) {
        if (soTien <= 0 || dsVe == null || chiTiet == null || soTienDaThanhToan < 0) {
            return false;
        }
        long tongTien = chiTiet.layTongTien();
        if (tongTien <= 0) {
            return false;
        }
        if (soTienDaThanhToan > tongTien || soTien > tongTien - soTienDaThanhToan) {
            return false;
        }

        int[] danhSachMaVe = chiTiet.layDanhSachMaVe();
        Ve[] cacVe = new Ve[danhSachMaVe.length];
        for (int i = 0; i < danhSachMaVe.length; i++) {
            cacVe[i] = dsVe.timTheoMa(danhSachMaVe[i]);
            if (cacVe[i] == null) {
                return false;
            }
        }

        this.soTienDaThanhToan += soTien;
        if (this.soTienDaThanhToan >= tongTien) {
            for (Ve veTrongHoaDon : cacVe) {
                if (veTrongHoaDon.getTrangThai() == Ve.DA_DAT) {
                    veTrongHoaDon.capNhatTrangThai(Ve.DA_THANH_TOAN);
                }
            }
        }
        return true;
    }

    public boolean daThanhToanDu() {
        long tongTien = tinhTongTien();
        return soTienDaThanhToan >= 0 && tongTien > 0 && soTienDaThanhToan >= tongTien;
    }

    private boolean daThuocHoaDonKhac(int maVe) {
        DanhSachHoaDon danhSachHoaDon = KhoDuLieu.getDanhSachHoaDon();
        if (danhSachHoaDon == null) {
            return false;
        }

        HoaDon[] tatCa = danhSachHoaDon.layTatCa();
        if (tatCa == null) {
            return false;
        }
        for (HoaDon hoaDonKhac : tatCa) {
            if (hoaDonKhac == null || hoaDonKhac == this) {
                continue;
            }
            ChiTietHoaDon chiTietKhac = hoaDonKhac.getChiTiet();
            if (chiTietKhac != null && chiTietKhac.coChuaVe(maVe)) {
                return true;
            }
        }
        return false;
    }
}
