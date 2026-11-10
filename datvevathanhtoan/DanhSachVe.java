package datvevathanhtoan;

import java.util.Arrays;
import danhsach.DanhSach;
import kho.KhoDuLieu;
import nguoidung.DanhSachHanhKhach;
import nguoidung.HanhKhach;

public class DanhSachVe implements DanhSach<Ve> {
    private Ve[] danhSach;
    private int soLuong;

    // Constructors
    public DanhSachVe() {
        this(new Ve[0], 0);
    }

    public DanhSachVe(Ve[] danhSach, int soLuong) {
        this.danhSach = danhSach == null ? new Ve[0]
                : Arrays.copyOf(danhSach, Math.min(Math.max(soLuong, 0), danhSach.length));
        this.soLuong = this.danhSach.length;
    }

    // Getters / Setters
    public Ve[] getDanhSach() {
        return danhSach;
    }

    public void setDanhSach(Ve[] danhSach) {
        this.danhSach = danhSach;
        soLuong = danhSach == null ? 0 : Math.min(soLuong, danhSach.length);
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = danhSach == null ? 0 : Math.min(Math.max(soLuong, 0), danhSach.length);
    }

    // Methods @Override
    @Override
    public boolean them(Ve ve) {
        if (ve == null || ve.getMaVe() <= 0 || danhSach == null || soLuong < 0
                || soLuong > danhSach.length || timTheoMa(ve.getMaVe()) != null) {
            return false;
        }
        danhSach = Arrays.copyOf(danhSach, soLuong + 1);
        danhSach[soLuong++] = ve;
        return true;
    }

    @Override
    public boolean them() {
        return false;
    }

    @Override
    public void xuat() {
    }

    @Override
    public void nhap() {
    }

    @Override
    public boolean xoaTheoMa(int maVe) {
        if (maVe <= 0 || danhSach == null) {
            return false;
        }
        for (int i = 0; i < gioiHan(); i++) {
            if (danhSach[i] != null && danhSach[i].getMaVe() == maVe) {
                System.arraycopy(danhSach, i + 1, danhSach, i, gioiHan() - i - 1);
                danhSach[--soLuong] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean capNhat(Ve ve) {
        if (ve == null || ve.getMaVe() <= 0 || danhSach == null) {
            return false;
        }
        for (int i = 0; i < gioiHan(); i++) {
            if (danhSach[i] != null && danhSach[i].getMaVe() == ve.getMaVe()) {
                danhSach[i] = ve;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean suaTheoMa(int ma) {
        return false;
    }

    @Override
    public Ve timTheoMa(int maVe) {
        if (maVe <= 0 || danhSach == null) {
            return null;
        }
        for (int i = 0; i < gioiHan(); i++) {
            if (danhSach[i] != null && danhSach[i].getMaVe() == maVe) {
                return danhSach[i];
            }
        }
        return null;
    }

    @Override
    public Ve[] layTatCa() {
        return danhSach == null ? new Ve[0] : Arrays.copyOf(danhSach, gioiHan());
    }

    @Override
    public boolean docFile(String duongDan) {
        return false;
    }

    @Override
    public boolean ghiFile(String duongDan) {
        return false;
    }

    // Methods còn lại
    public Ve[] timTheoTen(String hoTen) {
        if (hoTen == null || hoTen.trim().isEmpty() || danhSach == null) {
            return new Ve[0];
        }
        DanhSachHanhKhach dsHanhKhach = KhoDuLieu.getDanhSachHanhKhach();
        if (dsHanhKhach == null) {
            return new Ve[0];
        }
        HanhKhach[] hanhKhachs = dsHanhKhach.timTheoTen(hoTen);
        if (hanhKhachs == null || hanhKhachs.length == 0) {
            return new Ve[0];
        }

        Ve[] ketQua = new Ve[gioiHan()];
        int dem = 0;
        for (int i = 0; i < gioiHan(); i++) {
            Ve ve = danhSach[i];
            if (ve != null && thuocHanhKhach(ve, hanhKhachs)) {
                ketQua[dem++] = ve;
            }
        }
        return Arrays.copyOf(ketQua, dem);
    }

    public Ve[] timTheoMaDonDatTour(int maDonDatTour) {
        if (maDonDatTour <= 0 || danhSach == null) {
            return new Ve[0];
        }
        Ve[] ketQua = new Ve[gioiHan()];
        int dem = 0;
        for (int i = 0; i < gioiHan(); i++) {
            if (danhSach[i] != null && danhSach[i].getMaDonDatTour() == maDonDatTour) {
                ketQua[dem++] = danhSach[i];
            }
        }
        return Arrays.copyOf(ketQua, dem);
    }

    public int[] layDanhSachMaDonDatTour() {
        if (gioiHan() == 0) {
            return new int[0];
        }
        int[] ketQua = new int[gioiHan()];
        int dem = 0;
        for (int i = 0; i < gioiHan(); i++) {
            if (danhSach[i] == null) {
                continue;
            }
            int maDon = danhSach[i].getMaDonDatTour();
            boolean daCo = false;
            for (int j = 0; j < dem; j++) {
                if (ketQua[j] == maDon) {
                    daCo = true;
                    break;
                }
            }
            if (!daCo) {
                ketQua[dem++] = maDon;
            }
        }
        return Arrays.copyOf(ketQua, dem);
    }

    public Ve[] timTheoTrangThai(int trangThai) {
        if (gioiHan() == 0) {
            return new Ve[0];
        }
        Ve[] ketQua = new Ve[gioiHan()];
        int dem = 0;
        for (int i = 0; i < gioiHan(); i++) {
            if (danhSach[i] != null && danhSach[i].getTrangThai() == trangThai) {
                ketQua[dem++] = danhSach[i];
            }
        }
        return Arrays.copyOf(ketQua, dem);
    }

    public int demVeChiemChoTheoKeHoach(int maKeHoachTour) {
        if (maKeHoachTour <= 0) {
            return 0;
        }
        int dem = 0;
        for (int i = 0; i < gioiHan(); i++) {
            if (danhSach[i] != null && danhSach[i].getMaKeHoachTour() == maKeHoachTour
                    && danhSach[i].chiemCho()) {
                dem++;
            }
        }
        return dem;
    }

    public boolean daCoVeHopLe(int maKeHoachTour, int maHanhKhach) {
        if (maKeHoachTour <= 0 || maHanhKhach <= 0) {
            return false;
        }
        for (int i = 0; i < gioiHan(); i++) {
            if (danhSach[i] != null && danhSach[i].getMaKeHoachTour() == maKeHoachTour
                    && danhSach[i].getMaHanhKhach() == maHanhKhach && danhSach[i].chiemCho()) {
                return true;
            }
        }
        return false;
    }

    // Helper methods
    private int gioiHan() {
        return danhSach == null ? 0 : Math.min(soLuong, danhSach.length);
    }

    private boolean thuocHanhKhach(Ve ve, HanhKhach[] hanhKhachs) {
        for (HanhKhach hanhKhach : hanhKhachs) {
            if (hanhKhach != null && hanhKhach.getMaHanhKhach() == ve.getMaHanhKhach()) {
                return true;
            }
        }
        return false;
    }
}
