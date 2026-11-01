package datvevathanhtoan;

import danhsach.DanhSach;

public class DanhSachHoaDon implements DanhSach<HoaDon> {
    private HoaDon[] danhSach;
    private int soLuong;

    public DanhSachHoaDon() {
    }

    public DanhSachHoaDon(HoaDon[] danhSach, int soLuong) {
        this.danhSach = danhSach;
        this.soLuong = soLuong;
    }

    @Override
    public boolean them(HoaDon hoaDon) {
        // 1. Kiểm tra tham số: hóa đơn khác null
        if (hoaDon == null) {
            return false;
        }

        // 2. Mảng chưa được cấp phát hoặc số lượng không hợp lệ thì từ chối
        if (danhSach == null || soLuong < 0) {
            return false;
        }

        // 3. Duyệt mảng kiểm tra trùng mã hóa đơn, trùng thì từ chối
        for (int i = 0; i < soLuong && i < danhSach.length; i++) {
            HoaDon hoaDonCu = danhSach[i];
            if (hoaDonCu != null && hoaDonCu.getMaHoaDon() == hoaDon.getMaHoaDon()) {
                return false;
            }
        }

        // 4. Kiểm tra còn chỗ trống trong mảng
        if (soLuong >= danhSach.length) {
            return false;
        }

        // 5. Ghi vào cuối mảng rồi tăng số lượng
        danhSach[soLuong] = hoaDon;
        soLuong++;

        // 6. Thêm thành công khi mọi bước đều hợp lệ
        return true;
    }

    @Override
    public HoaDon timTheoMa(int maHoaDon) {
        // Tham số ngoài miền giá trị hoặc danh sách chưa có dữ liệu thì không tìm được
        if (maHoaDon <= 0 || danhSach == null) {
            return null;
        }

        // Duyệt danhSach[0..soLuong-1] để tìm hóa đơn có mã trùng khớp
        for (int i = 0; i < soLuong && i < danhSach.length; i++) {
            HoaDon hoaDon = danhSach[i];
            if (hoaDon != null && hoaDon.getMaHoaDon() == maHoaDon) {
                return hoaDon;
            }
        }

        // Không tìm thấy thì trả null
        return null;
    }

    public HoaDon[] timTheoMaDonDatTour(int maDonDatTour) {
        // 1. Chuẩn hóa tham số: mã đơn đặt tour phải là số dương
        if (maDonDatTour <= 0) {
            return new HoaDon[0];
        }

        // Danh sách chưa có dữ liệu thì trả mảng rỗng
        if (danhSach == null) {
            return new HoaDon[0];
        }

        // 2. Đếm số hóa đơn khớp để cấp phát mảng kết quả vừa đủ
        int gioiHan = Math.min(soLuong, danhSach.length);
        int dem = 0;
        for (int i = 0; i < gioiHan; i++) {
            HoaDon hoaDon = danhSach[i];
            if (hoaDon != null && hoaDon.getMaDonDatTour() == maDonDatTour) {
                dem++;
            }
        }

        // 3. Gom các hóa đơn khớp vào mảng kết quả mới
        HoaDon[] ketQua = new HoaDon[dem];
        int index = 0;
        for (int i = 0; i < gioiHan; i++) {
            HoaDon hoaDon = danhSach[i];
            if (hoaDon != null && hoaDon.getMaDonDatTour() == maDonDatTour) {
                ketQua[index++] = hoaDon;
            }
        }

        // 4. Trả mảng (rỗng nếu không khớp); danh sách gốc không đổi
        return ketQua;
    }

    public HoaDon[] timChuaThanhToanDu(DanhSachVe dsVe) {
        // 1. Tham số không hợp lệ thì từ chối, trả mảng rỗng
        if (dsVe == null || danhSach == null) {
            return new HoaDon[0];
        }

        // Chỉ duyệt các hóa đơn đang dùng: danhSach[0..soLuong-1]
        int gioiHan = Math.min(soLuong, danhSach.length);

        // 2. Đếm hóa đơn còn nợ để cấp phát mảng kết quả vừa đủ
        int dem = 0;
        for (int i = 0; i < gioiHan; i++) {
            if (laHoaDonConNo(danhSach[i], dsVe)) {
                dem++;
            }
        }

        // 3. Gom hóa đơn còn nợ vào mảng kết quả mới
        HoaDon[] ketQua = new HoaDon[dem];
        int index = 0;
        for (int i = 0; i < gioiHan; i++) {
            if (laHoaDonConNo(danhSach[i], dsVe)) {
                ketQua[index++] = danhSach[i];
            }
        }

        // 4. Trả mảng (rỗng nếu không có); danh sách gốc không đổi
        return ketQua;
    }

    // Hóa đơn còn nợ: chưa thanh toán đủ và không phải mọi vé đều đã bị hủy
    private boolean laHoaDonConNo(HoaDon hoaDon, DanhSachVe dsVe) {
        // Bỏ qua phần tử null
        if (hoaDon == null) {
            return false;
        }

        // Bỏ hóa đơn đã thanh toán đủ
        if (hoaDon.daThanhToan()) {
            return false;
        }

        // Tra trạng thái từng vé bằng dsVe; hóa đơn chưa có vé vẫn tính là còn nợ
        int[] danhSachMaVe = hoaDon.layDanhSachMaVe();
        if (danhSachMaVe == null || danhSachMaVe.length == 0) {
            return true;
        }

        boolean tatCaDaHuy = true;
        for (int i = 0; i < danhSachMaVe.length; i++) {
            Ve ve = dsVe.timTheoMa(danhSachMaVe[i]);
            // Vé không tra được thì chưa thể kết luận là đã hủy
            if (ve == null || ve.getTrangThai() != Ve.DA_HUY) {
                tatCaDaHuy = false;
                break;
            }
        }

        // Nhóm đã hủy toàn bộ vé thì không đưa vào kết quả
        return !tatCaDaHuy;
    }

    @Override
    public HoaDon[] layTatCa() {
        return danhSach;
    }

    public HoaDon[] getDanhSach() {
        return danhSach;
    }

    public void setDanhSach(HoaDon[] danhSach) {
        this.danhSach = danhSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    @Override
    public HoaDon timTheoMa(int maHoaDon) {
        return null;
    }

    public HoaDon timTheoMaDonDatTour(int maDonDatTour) {
        return null;
    }

    public HoaDon[] timChuaThanhToanDu(DanhSachVe dsVe) {
        return new HoaDon[0];
    }

    @Override
    public boolean capNhat(HoaDon hoaDon) {
        return false;
    }

    @Override
    public boolean xoaTheoMa(int maHoaDon) {
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
