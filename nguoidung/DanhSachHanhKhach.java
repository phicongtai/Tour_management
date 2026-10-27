package nguoidung;

import danhsach.DanhSach;

public class DanhSachHanhKhach implements DanhSach<HanhKhach> {
    private HanhKhach[] danhSach;
    private int soLuong;

    public DanhSachHanhKhach() {
    }

    public DanhSachHanhKhach(HanhKhach[] danhSach, int soLuong) {
        this.danhSach = danhSach;
        this.soLuong = soLuong;
    }

    @Override
    public boolean them(HanhKhach hanhKhach) {
        return false;
    }

    @Override
    public HanhKhach[] layTatCa() {
        return danhSach;
    }

    public HanhKhach[] getDanhSach() {
        return danhSach;
    }

    public void setDanhSach(HanhKhach[] danhSach) {
        this.danhSach = danhSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
