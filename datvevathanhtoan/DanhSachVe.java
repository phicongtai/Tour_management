package datvevathanhtoan;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
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
        this.danhSach = new Ve[0];
        this.soLuong = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(duongDan))) {
            String temp;
            while ((temp = reader.readLine()) != null) {
                if (temp.trim().isEmpty()) continue;
                String[] parts = temp.split("\\|");
                if (parts.length >= 8) {
                    Ve ve = new Ve(
                        Integer.parseInt(parts[0].trim()),
                        Integer.parseInt(parts[1].trim()),
                        Integer.parseInt(parts[2].trim()),
                        Integer.parseInt(parts[3].trim()),
                        Integer.parseInt(parts[4].trim()),
                        parts[5].trim(),
                        Long.parseLong(parts[6].trim()),
                        Integer.parseInt(parts[7].trim())
                    );
                    this.danhSach = Arrays.copyOf(this.danhSach, this.soLuong + 1);
                    this.danhSach[this.soLuong++] = ve;
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean ghiFile(String duongDan) {
        if (this.danhSach == null) {
            return false;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(duongDan))) {
            for (int i = 0; i < this.soLuong; i++) {
                String temp = "";
                temp += this.danhSach[i].getMaVe();
                temp += "|";
                temp += this.danhSach[i].getMaDonDatTour();
                temp += "|";
                temp += this.danhSach[i].getMaNguoiDat();
                temp += "|";
                temp += this.danhSach[i].getMaKeHoachTour();
                temp += "|";
                temp += this.danhSach[i].getMaHanhKhach();
                temp += "|";
                temp += this.danhSach[i].getNgayDat();
                temp += "|";
                temp += this.danhSach[i].getDonGia();
                temp += "|";
                temp += this.danhSach[i].getTrangThai();
                writer.write(temp);
                writer.newLine();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
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
