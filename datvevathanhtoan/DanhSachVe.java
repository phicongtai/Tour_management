package datvevathanhtoan;

import danhsach.DanhSach;

public class DanhSachVe implements DanhSach<Ve> {
    private Ve[] danhSach;
    private int soLuong;

    public DanhSachVe() {
    }

    public DanhSachVe(Ve[] danhSach, int soLuong) {
        this.danhSach = danhSach;
        this.soLuong = soLuong;
    }

    @Override
    public boolean them(Ve ve) {
        // 1. Kiểm tra null
        if (ve == null || danhSach == null) {
            return false;
        }

        // 2. Duyệt mảng kiểm tra trùng mã vé
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i] != null && danhSach[i].getMaVe() == ve.getMaVe()) {
                return false;
            }
        }

        // 3. Kiểm tra còn chỗ trống
        if (soLuong >= danhSach.length) {
            return false;
        }

        // 4. Gán vào cuối mảng và tăng số lượng
        danhSach[soLuong] = ve;
        soLuong++;

        // 5. Thêm thành công
        return true;
    }

    @Override
    public Ve timTheoMa(int maVe) {
        // Duyệt danhSach[0..soLuong-1] để tìm vé có mã trùng khớp
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i] != null && danhSach[i].getMaVe() == maVe) {
                return danhSach[i];
            }
        }
        // Không tìm thấy thì trả null
        return null;
    }

    public Ve[] timTheoMaDonDatTour(int maDonDatTour) {
        // Danh sách chưa có dữ liệu thì trả mảng rỗng
        if (danhSach == null) {
            return new Ve[0];
        }

        // 1. Đếm số vé khớp để cấp phát mảng kết quả vừa đủ
        int dem = 0;
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i] != null && danhSach[i].getMaDonDatTour() == maDonDatTour) {
                dem++;
            }
        }

        // 2. Gom các vé khớp vào mảng kết quả mới
        Ve[] ketQua = new Ve[dem];
        int index = 0;
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i] != null && danhSach[i].getMaDonDatTour() == maDonDatTour) {
                ketQua[index++] = danhSach[i];
            }
        }

        // 3. Trả mảng (rỗng nếu không khớp); danh sách gốc không đổi
        return ketQua;
    }

    @Override
    public Ve[] layTatCa() {
        return danhSach;
    }

    public Ve[] getDanhSach() {
        return danhSach;
    }

    public void setDanhSach(Ve[] danhSach) {
        this.danhSach = danhSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    @Override
    public Ve timTheoMa(int maVe) {
        return null;
    }

    public Ve[] timTheoMaDonDatTour(int maDonDatTour) {
        return new Ve[0];
    }

    public int[] layDanhSachMaDonDatTour() {
        return new int[0];
    }

    public Ve[] timTheoTrangThai(int trangThai) {
        return new Ve[0];
    }

    public int demVeChiemChoTheoKeHoach(int maKeHoachTour) {
        return 0;
    }

    public boolean daCoVeHopLe(int maKeHoachTour, int maHanhKhach) {
        return false;
    }

    @Override
    public boolean capNhat(Ve ve) {
        return false;
    }

    @Override
    public boolean xoaTheoMa(int maVe) {
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
