package nguoidung;

import danhsach.DanhSach;

public class DanhSachNhanVien implements DanhSach<NhanVien> {
    private NhanVien[] danhSach;
    private int soLuong;

    public DanhSachNhanVien() {
    }

    public DanhSachNhanVien(NhanVien[] danhSach, int soLuong) {
        this.danhSach = danhSach;
        this.soLuong = soLuong;
    }

    @Override
    public boolean them(NhanVien nhanVien) {
        return false;
    }

    @Override
    public NhanVien[] layTatCa() {
        return danhSach;
    }

    public NhanVien[] getDanhSach() {
        return danhSach;
    }

    public void setDanhSach(NhanVien[] danhSach) {
        this.danhSach = danhSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
