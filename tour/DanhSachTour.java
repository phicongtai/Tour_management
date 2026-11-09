
package tour;
import java.nio.file.Path;

import java.util.Arrays;
import danhsach.DanhSach;

public class DanhSachTour implements DanhSach<Tour> {
    private Tour[] danhSach;
    private int soLuong;

    public DanhSachTour() {
    }

    public DanhSachTour(Tour[] danhSach, int soLuong) {
        this.danhSach = danhSach;
        this.soLuong = soLuong;
    }

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
    public Tour[] layTatCa() {
        return danhSach == null ? new Tour[0]
                : Arrays.copyOf(danhSach, soLuong);
    }

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

    @Override
    public Tour timTheoMa(int maTour) {
        if (danhSach == null) return null;

        for (int i = 0; i < soLuong; i++)
            if (danhSach[i] != null
                    && danhSach[i].getMaTour() == maTour)
                return danhSach[i];

        return null;
    }

    public Tour[] timTheoTen(String tenTour) {
        if (danhSach == null || tenTour == null)
            return new Tour[0];

        Tour[] kq = new Tour[soLuong];
        int dem = 0;

        for (int i = 0; i < soLuong; i++) {
            Tour t = danhSach[i];
            if (t != null && t.getTenTour() != null
                    && t.getTenTour().toLowerCase()
                    .contains(tenTour.toLowerCase()))
                kq[dem++] = t;
        }
        return Arrays.copyOf(kq, dem);
    }

    @Override
    public boolean capNhat(Tour tour) {
        if (tour == null || danhSach == null) return false;

        for (int i = 0; i < soLuong; i++)
            if (danhSach[i] != null
                    && danhSach[i].getMaTour() == tour.getMaTour()) {
                danhSach[i] = tour;
                return true;
            }
        return false;
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
    public boolean docFile(Path path) {
        return false;
    }

    @Override
    public boolean ghiFile(Path path) {
        return false;
    }
}
