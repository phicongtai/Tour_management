package datvevathanhtoan;

import java.util.Arrays;

public class ChiTietHoaDon {
    private int maHoaDon;
    private int[] danhSachMaVe;
    private int soLuongVe;
    private long tongTien;

    public ChiTietHoaDon() {
        this(0);
    }

    public ChiTietHoaDon(int maHoaDon) {
        this(maHoaDon, null, 0, 0);
    }

    public ChiTietHoaDon(int maHoaDon, int[] danhSachMaVe, int soLuongVe, long tongTien) {
        this.maHoaDon = maHoaDon;
        if (danhSachMaVe == null) {
            danhSachMaVe = new int[0];
        }
        int soLuongHopLe = Math.min(Math.max(soLuongVe, 0), danhSachMaVe.length);
        this.danhSachMaVe = Arrays.copyOf(danhSachMaVe, soLuongHopLe);
        this.soLuongVe = this.danhSachMaVe.length;
        this.tongTien = tongTien;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int[] getDanhSachMaVe() {
        return layDanhSachMaVe();
    }

    public int[] layDanhSachMaVe() {
        if (danhSachMaVe == null || soLuongVe <= 0) {
            return new int[0];
        }
        return Arrays.copyOf(danhSachMaVe, Math.min(soLuongVe, danhSachMaVe.length));
    }

    public void setDanhSachMaVe(int[] danhSachMaVe) {
        this.danhSachMaVe = danhSachMaVe == null ? new int[0] : Arrays.copyOf(danhSachMaVe, danhSachMaVe.length);
        this.soLuongVe = this.danhSachMaVe.length;
    }

    public int getSoLuongVe() {
        return soLuongVe;
    }

    public void setSoLuongVe(int soLuongVe) {
        if (danhSachMaVe == null) {
            danhSachMaVe = new int[0];
        }
        this.soLuongVe = Math.min(Math.max(soLuongVe, 0), danhSachMaVe.length);
    }

    public long getTongTien() {
        return layTongTien();
    }

    public long layTongTien() {
        return Math.max(tongTien, 0);
    }

    public void setTongTien(long tongTien) {
        this.tongTien = tongTien;
    }

    public boolean themVe(int maVe) {
        if (maVe <= 0 || coChuaVe(maVe) || soLuongVe < 0
                || (danhSachMaVe != null && soLuongVe > danhSachMaVe.length)) {
            return false;
        }

        if (danhSachMaVe == null) {
            danhSachMaVe = new int[0];
        }
        danhSachMaVe = Arrays.copyOf(danhSachMaVe, soLuongVe + 1);
        danhSachMaVe[soLuongVe++] = maVe;
        return true;
    }

    public boolean coChuaVe(int maVe) {
        if (maVe <= 0 || danhSachMaVe == null || soLuongVe <= 0) {
            return false;
        }
        int gioiHan = Math.min(soLuongVe, danhSachMaVe.length);
        for (int i = 0; i < gioiHan; i++) {
            if (danhSachMaVe[i] == maVe) {
                return true;
            }
        }
        return false;
    }

    public boolean capNhatTongTien(DanhSachVe dsVe) {
        if (dsVe == null || danhSachMaVe == null || soLuongVe < 0 || soLuongVe > danhSachMaVe.length) {
            return false;
        }

        long tongTienTam = 0;
        for (int i = 0; i < soLuongVe; i++) {
            Ve ve = dsVe.timTheoMa(danhSachMaVe[i]);
            if (ve == null || ve.getDonGia() < 0 || Long.MAX_VALUE - tongTienTam < ve.getDonGia()) {
                return false;
            }
            tongTienTam += ve.getDonGia();
        }

        tongTien = tongTienTam;
        return true;
    }
}
