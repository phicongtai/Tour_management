package tour;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import danhsach.DanhSach;

public class DanhSachTour implements DanhSach<Tour> {
    private Tour[] danhSach;
    private int soLuong;

    // Constructors
    public DanhSachTour() {
    }

    public DanhSachTour(Tour[] danhSach, int soLuong) {
        this.danhSach = danhSach;
        this.soLuong = soLuong;
    }

    // Getters / Setters
    public Tour[] getDanhSach() {
        return danhSach;
    }

    public void setDanhSach(Tour[] danhSach) {
        this.danhSach = danhSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    // Methods @Override
    @Override
    public boolean them(Tour tour) {
        if (tour == null || timTheoMa(tour.getMaTour()) != null)
            return false;

        if (danhSach == null) danhSach = new Tour[10];
        if (soLuong >= danhSach.length)
            danhSach = Arrays.copyOf(danhSach,
                    danhSach.length == 0 ? 10 : danhSach.length * 2);

        danhSach[soLuong++] = tour;
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
    public boolean xoaTheoMa(int maTour) {
        if (danhSach == null) return false;

        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i] != null
                    && danhSach[i].getMaTour() == maTour) {
                for (int j = i; j < soLuong - 1; j++)
                    danhSach[j] = danhSach[j + 1];

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
    public Tour timTheoMa(int maTour) {
        if (danhSach == null) return null;

        for (int i = 0; i < soLuong; i++)
            if (danhSach[i] != null
                    && danhSach[i].getMaTour() == maTour)
                return danhSach[i];

        return null;
    }

    @Override
    public Tour[] layTatCa() {
        return danhSach == null ? new Tour[0]
                : Arrays.copyOf(danhSach, soLuong);
    }

    @Override
    public boolean docFile(String duongDan) {
        this.danhSach = new Tour[0];
        this.soLuong = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(duongDan))) {
            String temp;
            while ((temp = reader.readLine()) != null) {
                if (temp.trim().isEmpty()) continue;
                String[] parts = temp.split("\\|");
                if (parts[0].trim().equalsIgnoreCase("TRONG_NUOC") && parts.length >= 9) {
                    TourTrongNuoc t = new TourTrongNuoc(
                        Integer.parseInt(parts[1].trim()),
                        parts[2].trim(),
                        parts[3].trim(),
                        parts[4].trim(),
                        Integer.parseInt(parts[5].trim()),
                        Long.parseLong(parts[6].trim()),
                        parts[7].trim(),
                        Long.parseLong(parts[8].trim())
                    );
                    this.danhSach = Arrays.copyOf(this.danhSach, this.soLuong + 1);
                    this.danhSach[this.soLuong++] = t;
                } else if (parts[0].trim().equalsIgnoreCase("NUOC_NGOAI") && parts.length >= 10) {
                    TourNuocNgoai t = new TourNuocNgoai(
                        Integer.parseInt(parts[1].trim()),
                        parts[2].trim(),
                        parts[3].trim(),
                        parts[4].trim(),
                        Integer.parseInt(parts[5].trim()),
                        Long.parseLong(parts[6].trim()),
                        parts[7].trim(),
                        Boolean.parseBoolean(parts[8].trim()),
                        Long.parseLong(parts[9].trim())
                    );
                    this.danhSach = Arrays.copyOf(this.danhSach, this.soLuong + 1);
                    this.danhSach[this.soLuong++] = t;
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
                if (this.danhSach[i] instanceof TourTrongNuoc) {
                    TourTrongNuoc t = (TourTrongNuoc) this.danhSach[i];
                    temp += "TRONG_NUOC|";
                    temp += t.getMaTour() + "|";
                    temp += t.getTenTour() + "|";
                    temp += t.getDiemKhoiHanh() + "|";
                    temp += t.getDiemDen() + "|";
                    temp += t.getSoNgay() + "|";
                    temp += t.getDonGiaCoBan() + "|";
                    temp += t.getVungMien() + "|";
                    temp += t.getPhuPhi();
                } else if (this.danhSach[i] instanceof TourNuocNgoai) {
                    TourNuocNgoai t = (TourNuocNgoai) this.danhSach[i];
                    temp += "NUOC_NGOAI|";
                    temp += t.getMaTour() + "|";
                    temp += t.getTenTour() + "|";
                    temp += t.getDiemKhoiHanh() + "|";
                    temp += t.getDiemDen() + "|";
                    temp += t.getSoNgay() + "|";
                    temp += t.getDonGiaCoBan() + "|";
                    temp += t.getQuocGia() + "|";
                    temp += t.isCanVisa() + "|";
                    temp += t.getPhiVisa();
                }
                writer.write(temp);
                writer.newLine();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Methods còn lại
    public Tour[] timTheoTen(String tenTour) {
        if (danhSach == null || tenTour == null)
            return new Tour[0];

        Tour[] ketQua = new Tour[soLuong];
        int index = 0;
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i] != null && danhSach[i].getTenTour() != null
                    && (danhSach[i].getTenTour().equalsIgnoreCase(tenTour)
                    || danhSach[i].getTenTour().toLowerCase().contains(tenTour.toLowerCase()))) {
                ketQua = Arrays.copyOf(ketQua, index + 1);
                ketQua[index] = danhSach[i];
                index++;
            }
        }
        return ketQua;
    }
}
