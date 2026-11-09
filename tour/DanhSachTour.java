
package tour;

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

        Tour[] ketQua = new Tour[soLuong];
        int index = 0;
        for(int i=0;i<soLuong;i++){
            if(danhSach[i]!=null && danhSach[i].getTenTour().equalsIgnoreCase(tenTour)){
                ketQua=Arrays.copyOf(ketQua,index+1);
                ketQua[index]=danhSach[i];
                index++;
            }
        }
        return ketQua;
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
    public String[] xuatDuLieu() {
        String[] kq = new String[soLuong];

        for (int i = 0; i < soLuong; i++) {
            Tour t = danhSach[i];
            String chung = t.getMaTour() + "|" + t.getTenTour()
                    + "|" + t.getDiemKhoiHanh() + "|" + t.getDiemDen()
                    + "|" + t.getSoNgay() + "|" + t.getDonGiaCoBan();

            if (t instanceof TourTrongNuoc) {
                TourTrongNuoc tn = (TourTrongNuoc) t;
                kq[i] = "TRONG_NUOC|" + chung + "|"
                        + tn.getVungMien() + "|" + tn.getPhuPhi();
            } else if (t instanceof TourNuocNgoai) {
                TourNuocNgoai tnn = (TourNuocNgoai) t;
                kq[i] = "NUOC_NGOAI|" + chung + "|"
                        + tnn.getQuocGia() + "|" + tnn.isCanVisa()
                        + "|" + tnn.getPhiVisa();
            }
        }
        return kq;
    }

    @Override
    public boolean nhapDuLieu(String[] dongDuLieu) {
        if (dongDuLieu == null) return false;

        Tour[] tam = new Tour[dongDuLieu.length];
        int dem = 0;

        try {
            for (String dong : dongDuLieu) {
                if (dong == null) return false;
                String[] p = dong.split("\\|", -1);
                Tour t;

                if (p[0].equals("TRONG_NUOC") && p.length == 9)
                    t = new TourTrongNuoc(
                            Integer.parseInt(p[1]), p[2], p[3], p[4],
                            Integer.parseInt(p[5]), Long.parseLong(p[6]),
                            p[7], Long.parseLong(p[8]));

                else if (p[0].equals("NUOC_NGOAI") && p.length == 10) {
                    if (!p[8].equalsIgnoreCase("true")
                            && !p[8].equalsIgnoreCase("false"))
                        return false;

                    t = new TourNuocNgoai(
                            Integer.parseInt(p[1]), p[2], p[3], p[4],
                            Integer.parseInt(p[5]), Long.parseLong(p[6]),
                            p[7], Boolean.parseBoolean(p[8]),
                            Long.parseLong(p[9]));
                } else return false;

                if (t.getMaTour() <= 0 || t.getTenTour().trim().isEmpty()
                        || t.getDiemKhoiHanh().trim().isEmpty()
                        || t.getDiemDen().trim().isEmpty()
                        || t.getSoNgay() <= 0 || t.getDonGiaCoBan() < 0)
                    return false;

                for (int i = 0; i < dem; i++)
                    if (tam[i].getMaTour() == t.getMaTour())
                        return false;

                if (t instanceof TourTrongNuoc
                        && ((TourTrongNuoc) t).getPhuPhi() < 0)
                    return false;

                if (t instanceof TourNuocNgoai
                        && ((TourNuocNgoai) t).getPhiVisa() < 0)
                    return false;

                tam[dem++] = t;
            }
        } catch (Exception e) {
            return false;
        }

        danhSach = tam;
        soLuong = dem;
        return true;
    }
}
