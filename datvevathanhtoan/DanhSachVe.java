package datvevathanhtoan;

import java.util.Arrays;

import danhsach.DanhSach;
import kho.KhoDuLieu;
import nguoidung.DanhSachHanhKhach;
import nguoidung.HanhKhach;

public class DanhSachVe implements DanhSach<Ve> {
    private Ve[] danhSach;
    private int soLuong;

    public DanhSachVe() {
        this.danhSach = new Ve[0];
    }

    public DanhSachVe(Ve[] danhSach, int soLuong) {
        int gioiHan = danhSach == null ? 0 : Math.min(Math.max(soLuong, 0), danhSach.length);
        this.danhSach = danhSach == null ? new Ve[0] : Arrays.copyOf(danhSach, gioiHan);
        this.soLuong = gioiHan;
    }

    @Override
    public boolean them(Ve ve) {
        if (ve == null || ve.getMaVe() <= 0 || danhSach == null
                || soLuong < 0 || soLuong > danhSach.length
                || timTheoMa(ve.getMaVe()) != null) {
            return false;
        }

        danhSach = Arrays.copyOf(danhSach, soLuong + 1);
        danhSach[soLuong++] = ve;
        return true;
    }

    @Override
    public Ve timTheoMa(int maVe) {
        if (maVe <= 0 || danhSach == null || soLuong <= 0) {
            return null;
        }
        int gioiHan = Math.min(soLuong, danhSach.length);
        for (int i = 0; i < gioiHan; i++) {
            if (danhSach[i] != null && danhSach[i].getMaVe() == maVe) {
                return danhSach[i];
            }
        }
        return null;
    }

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

        int gioiHan = Math.min(soLuong, danhSach.length);
        Ve[] ketQua = new Ve[gioiHan];
        int dem = 0;
        for (int i = 0; i < gioiHan; i++) {
            Ve ve = danhSach[i];
            if (ve != null && thuocHanhKhach(ve, hanhKhachs)) {
                ketQua[dem++] = ve;
            }
        }
        return Arrays.copyOf(ketQua, dem);
    }

    @Override
    public boolean capNhat(Ve ve) {
        if (ve == null || ve.getMaVe() <= 0 || danhSach == null) {
            return false;
        }
        int gioiHan = Math.min(soLuong, danhSach.length);
        for (int i = 0; i < gioiHan; i++) {
            if (danhSach[i] != null && danhSach[i].getMaVe() == ve.getMaVe()) {
                danhSach[i] = ve;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean xoaTheoMa(int maVe) {
        if (maVe <= 0 || danhSach == null) {
            return false;
        }
        int gioiHan = Math.min(soLuong, danhSach.length);
        for (int i = 0; i < gioiHan; i++) {
            if (danhSach[i] != null && danhSach[i].getMaVe() == maVe) {
                System.arraycopy(danhSach, i + 1, danhSach, i, gioiHan - i - 1);
                danhSach[--soLuong] = null;
                return true;
            }
        }
        return false;
    }

    public Ve[] timTheoMaDonDatTour(int maDonDatTour) {
        if (maDonDatTour <= 0 || danhSach == null) {
            return new Ve[0];
        }
        int gioiHan = Math.min(soLuong, danhSach.length);
        Ve[] ketQua = new Ve[gioiHan];
        int dem = 0;
        for (int i = 0; i < gioiHan; i++) {
            if (danhSach[i] != null && danhSach[i].getMaDonDatTour() == maDonDatTour) {
                ketQua[dem++] = danhSach[i];
            }
        }
        return Arrays.copyOf(ketQua, dem);
    }

    @Override
    public Ve[] layTatCa() {
        if (danhSach == null || soLuong <= 0) {
            return new Ve[0];
        }
        return Arrays.copyOf(danhSach, Math.min(soLuong, danhSach.length));
    }

    public Ve[] getDanhSach() {
        return danhSach;
    }

    public void setDanhSach(Ve[] danhSach) {
        this.danhSach = danhSach;
        if (danhSach == null) {
            soLuong = 0;
        } else {
            soLuong = Math.min(soLuong, danhSach.length);
        }
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = danhSach == null ? 0 : Math.min(Math.max(soLuong, 0), danhSach.length);
    }

    private boolean thuocHanhKhach(Ve ve, HanhKhach[] hanhKhachs) {
        for (int i = 0; i < hanhKhachs.length; i++) {
            if (hanhKhachs[i] != null && hanhKhachs[i].getMaHanhKhach() == ve.getMaHanhKhach()) {
                return true;
            }
        }
        return false;
    }

    public int[] layDanhSachMaDonDatTour() {
        if (danhSach == null || soLuong <= 0) {
            return new int[0];
        }
        int gioiHan = Math.min(soLuong, danhSach.length);
        int[] temp = new int[gioiHan];
        int dem = 0;
        for (int i = 0; i < gioiHan; i++) {
            if (danhSach[i] != null) {
                int maDon = danhSach[i].getMaDonDatTour();
                boolean exists = false;
                for (int j = 0; j < dem; j++) {
                    if (temp[j] == maDon) {
                        exists = true;
                        break;
                    }
                }
                if (!exists) {
                    temp[dem++] = maDon;
                }
            }
        }
        return Arrays.copyOf(temp, dem);
    }

    public Ve[] timTheoTrangThai(int trangThai) {
        if (danhSach == null || soLuong <= 0) {
            return new Ve[0];
        }
        int gioiHan = Math.min(soLuong, danhSach.length);
        Ve[] ketQua = new Ve[gioiHan];
        int dem = 0;
        for (int i = 0; i < gioiHan; i++) {
            if (danhSach[i] != null && danhSach[i].getTrangThai() == trangThai) {
                ketQua[dem++] = danhSach[i];
            }
        }
        return Arrays.copyOf(ketQua, dem);
    }

    public int demVeChiemChoTheoKeHoach(int maKeHoachTour) {
        if (maKeHoachTour <= 0 || danhSach == null || soLuong <= 0) {
            return 0;
        }
        int gioiHan = Math.min(soLuong, danhSach.length);
        int dem = 0;
        for (int i = 0; i < gioiHan; i++) {
            if (danhSach[i] != null && danhSach[i].getMaKeHoachTour() == maKeHoachTour && danhSach[i].chiemCho()) {
                dem++;
            }
        }
        return dem;
    }

    public boolean daCoVeHopLe(int maKeHoachTour, int maHanhKhach) {
        if (maKeHoachTour <= 0 || maHanhKhach <= 0 || danhSach == null || soLuong <= 0) {
            return false;
        }
        int gioiHan = Math.min(soLuong, danhSach.length);
        for (int i = 0; i < gioiHan; i++) {
            if (danhSach[i] != null && danhSach[i].getMaKeHoachTour() == maKeHoachTour
                    && danhSach[i].getMaHanhKhach() == maHanhKhach && danhSach[i].chiemCho()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String[] xuatDuLieu() {
        return new String[0];
    }

    @Override
    public boolean nhapDuLieu(String[] dongDuLieu) {
        return false;
    }
}
