package datvevathanhtoan;

import java.util.Arrays;

import danhsach.DanhSach;
import kho.KhoDuLieu;
import nguoidung.DanhSachNhanVien;
import nguoidung.NhanVien;

public class DanhSachHoaDon implements DanhSach<HoaDon> {
    private HoaDon[] danhSach;
    private int soLuong;

    public DanhSachHoaDon() {
        this.danhSach = new HoaDon[0];
    }

    public DanhSachHoaDon(HoaDon[] danhSach, int soLuong) {
        int gioiHan = danhSach == null ? 0 : Math.min(Math.max(soLuong, 0), danhSach.length);
        this.danhSach = danhSach == null ? new HoaDon[0] : Arrays.copyOf(danhSach, gioiHan);
        this.soLuong = gioiHan;
    }

    @Override
    public boolean them(HoaDon hoaDon) {
        if (hoaDon == null || hoaDon.getMaHoaDon() <= 0 || danhSach == null
                || soLuong < 0 || soLuong > danhSach.length
                || timTheoMa(hoaDon.getMaHoaDon()) != null) {
            return false;
        }

        danhSach = Arrays.copyOf(danhSach, soLuong + 1);
        danhSach[soLuong++] = hoaDon;
        return true;
    }

    @Override
    public HoaDon timTheoMa(int maHoaDon) {
        if (maHoaDon <= 0 || danhSach == null || soLuong <= 0) {
            return null;
        }
        int gioiHan = Math.min(soLuong, danhSach.length);
        for (int i = 0; i < gioiHan; i++) {
            if (danhSach[i] != null && danhSach[i].getMaHoaDon() == maHoaDon) {
                return danhSach[i];
            }
        }
        return null;
    }

    public HoaDon[] timTheoTen(String hoTen) {
        if (hoTen == null || hoTen.trim().isEmpty() || danhSach == null) {
            return new HoaDon[0];
        }

        DanhSachNhanVien dsNhanVien = KhoDuLieu.getDanhSachNhanVien();
        if (dsNhanVien == null) {
            return new HoaDon[0];
        }
        NhanVien[] nhanViens = dsNhanVien.timTheoTen(hoTen);
        if (nhanViens == null || nhanViens.length == 0) {
            return new HoaDon[0];
        }

        int gioiHan = Math.min(soLuong, danhSach.length);
        HoaDon[] ketQua = new HoaDon[gioiHan];
        int dem = 0;
        for (int i = 0; i < gioiHan; i++) {
            HoaDon hoaDon = danhSach[i];
            if (hoaDon != null && thuocNhanVien(hoaDon, nhanViens)) {
                ketQua[dem++] = hoaDon;
            }
        }
        return Arrays.copyOf(ketQua, dem);
    }

    @Override
    public boolean capNhat(HoaDon hoaDon) {
        if (hoaDon == null || hoaDon.getMaHoaDon() <= 0 || danhSach == null) {
            return false;
        }
        int gioiHan = Math.min(soLuong, danhSach.length);
        for (int i = 0; i < gioiHan; i++) {
            if (danhSach[i] != null && danhSach[i].getMaHoaDon() == hoaDon.getMaHoaDon()) {
                danhSach[i] = hoaDon;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean xoaTheoMa(int maHoaDon) {
        if (maHoaDon <= 0 || danhSach == null) {
            return false;
        }
        int gioiHan = Math.min(soLuong, danhSach.length);
        for (int i = 0; i < gioiHan; i++) {
            if (danhSach[i] != null && danhSach[i].getMaHoaDon() == maHoaDon) {
                System.arraycopy(danhSach, i + 1, danhSach, i, gioiHan - i - 1);
                danhSach[--soLuong] = null;
                return true;
            }
        }
        return false;
    }

    public HoaDon[] timTheoMaDonDatTour(int maDonDatTour) {
        if (maDonDatTour <= 0 || danhSach == null) {
            return new HoaDon[0];
        }
        int gioiHan = Math.min(soLuong, danhSach.length);
        HoaDon[] ketQua = new HoaDon[gioiHan];
        int dem = 0;
        for (int i = 0; i < gioiHan; i++) {
            if (danhSach[i] != null && danhSach[i].getMaDonDatTour() == maDonDatTour) {
                ketQua[dem++] = danhSach[i];
            }
        }
        return Arrays.copyOf(ketQua, dem);
    }

    @Override
    public HoaDon[] layTatCa() {
        if (danhSach == null || soLuong <= 0) {
            return new HoaDon[0];
        }
        return Arrays.copyOf(danhSach, Math.min(soLuong, danhSach.length));
    }

    public HoaDon[] getDanhSach() {
        return danhSach;
    }

    public void setDanhSach(HoaDon[] danhSach) {
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

    private boolean thuocNhanVien(HoaDon hoaDon, NhanVien[] nhanViens) {
        for (int i = 0; i < nhanViens.length; i++) {
            if (nhanViens[i] != null && nhanViens[i].getMaNhanVien() == hoaDon.getMaNhanVien()) {
                return true;
            }
        }
        return false;
    }

    public HoaDon[] timChuaThanhToanDu(DanhSachVe dsVe) {
        if (danhSach == null || soLuong <= 0) {
            return new HoaDon[0];
        }
        int gioiHan = Math.min(soLuong, danhSach.length);
        HoaDon[] ketQua = new HoaDon[gioiHan];
        int dem = 0;
        for (int i = 0; i < gioiHan; i++) {
            HoaDon hoaDon = danhSach[i];
            if (hoaDon != null) {
                if (dsVe != null && hoaDon.getChiTiet() != null && hoaDon.tinhTongTien() == 0) {
                    hoaDon.getChiTiet().capNhatTongTien(dsVe);
                }
                if (!hoaDon.daThanhToanDu()) {
                    ketQua[dem++] = hoaDon;
                }
            }
        }
        return Arrays.copyOf(ketQua, dem);
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
