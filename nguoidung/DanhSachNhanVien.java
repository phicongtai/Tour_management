package nguoidung;

import danhsach.DanhSach;

public class DanhSachNhanVien implements DanhSach<NhanVien> {
    private NhanVien[] danhSach;
    private int soLuong;
    private NhanVien[] danhSachTam;
    private int soLuongTam;

    public DanhSachNhanVien() {
    }

    public DanhSachNhanVien(NhanVien[] danhSach, int soLuong, NhanVien[] danhSachTam, int soLuongTam) {
        this.danhSach = danhSach;
        this.soLuong = soLuong;
        this.danhSachTam = danhSachTam;
        this.soLuongTam = soLuongTam;
    }

    @Override
    public boolean them(NhanVien nhanVien) {
        return false;
    }

    @Override
    public boolean xoa(int ma) {
        return false;
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

    public NhanVien[] getDanhSachTam() {
        return danhSachTam;
    }

    public void setDanhSachTam(NhanVien[] danhSachTam) {
        this.danhSachTam = danhSachTam;
    }

    public int getSoLuongTam() {
        return soLuongTam;
    }

    public void setSoLuongTam(int soLuongTam) {
        this.soLuongTam = soLuongTam;
    }
}
