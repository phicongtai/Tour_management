package datvevathanhtoan;

import java.util.Locale;
import java.util.Scanner;

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
    if (ve == null) {
        return false;
    }
    if (ve.getMaDonDatTour() != maDonDatTour){
        return false;
    }
    if (chiTiet != null && chiTiet.coChuaVe(maVe)) {
        return false;
    }
    if (chiTiet == null) {
        chiTiet = new ChiTietHoaDon(maHoaDon);
    }
    if (!chiTiet.themVe(maVe)) {
        return false;
    }
    if (!chiTiet.capNhatTongTien(dsVe)) {
        return false;
    }
    return true;
}

public int[] layDanhSachMaVe() {
    if (chiTiet == null) {
        return new int[0];
    }
    int[] danhSachMaVe = chiTiet.layDanhSachMaVe();
    if (danhSachMaVe == null) {
        return new int[0];
    }
        return danhSachMaVe;
}

public long tinhTongTien() {
        if (chiTiet == null) {
            return 0;
        }
        return chiTiet.layTongTien();
    }

public long tinhSoTienConLai() {
    if (soTienDaThanhToan < 0) {
        return 0;
    }
    long tongTien = tinhTongTien();
        if (tongTien <= 0) {
        return 0;
    }
    if (soTienDaThanhToan > tongTien) {
        return 0;
    }
    return tongTien - soTienDaThanhToan;
}

    public boolean ghiNhanThanhToan(long soTien, DanhSachVe dsVe) {
        if (soTien <= 0 || dsVe == null || chiTiet == null) {
            return false;
        }
        if (soTienDaThanhToan < 0) {
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
            for (int i = 0; i < cacVe.length; i++) {
                if (cacVe[i].getTrangThai() == Ve.DA_DAT) {
                    cacVe[i].capNhatTrangThai(Ve.DA_THANH_TOAN);
                }
            }
        }
        return true;
    }
    public boolean daThanhToanDu() {
        if (soTienDaThanhToan < 0) {
            return false;
        }
        long tongTien = tinhTongTien();
        if (tongTien <= 0) {
            return false;
        }
        return soTienDaThanhToan >= tongTien;
    }

    public void nhap(){
        Scanner in = KhoDuLieu.getScanner();
        this.maHoaDon = KhoDuLieu.getDanhSachHoaDon().getmamoi();
        System.out.println("Ma hoa don (tu dong): " + this.maHoaDon);
        System.out.print("Nhap ma don dat tour: ");
        this.maDonDatTour = in.nextInt();
        in.nextLine();
        System.out.print("Nhap ma nhan vien: ");
        this.maNhanVien = in.nextInt();
        in.nextLine();
        System.out.print("Nhap ngay lap: ");
        this.ngayLap = in.nextLine();
    }

    public void xuat() {
        System.out.printf("%-6d | %-8d | %-6d | %-12s | %-14s | %-14s | %-14s | %-12s%n",
                this.maHoaDon,
                this.maDonDatTour,
                this.maNhanVien,
                this.ngayLap,
                String.format(Locale.US, "%,d", tinhTongTien()),
                String.format(Locale.US, "%,d", this.soTienDaThanhToan),
                String.format(Locale.US, "%,d", tinhSoTienConLai()),
                daThanhToanDu() ? "Da du" : "Chua du");
    }
}
