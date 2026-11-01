package tochuctour;

import danhsach.DanhSach;

public class DanhSachPhuongTien implements DanhSach<PhuongTien> {
    private PhuongTien[] danhSach;
    private int soLuong;

    public DanhSachPhuongTien() {
    }

    public DanhSachPhuongTien(PhuongTien[] danhSach, int soLuong) {
        this.danhSach = danhSach;
        this.soLuong = soLuong;
    }

    @Override
    public boolean them(PhuongTien phuongTien) {
        return false;
    }

    @Override
    public PhuongTien[] layTatCa() {
        return danhSach;
    }

    public PhuongTien[] getDanhSach() {
        return danhSach;
    }

    public void setDanhSach(PhuongTien[] danhSach) {
        this.danhSach = danhSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    @Override
    public PhuongTien timTheoMa(int maPhuongTien) {
        return null;
    }

    public PhuongTien[] timTheoTen(String tenPhuongTien) {
        return new PhuongTien[0];
    }

    @Override
    public boolean capNhat(PhuongTien phuongTien) {
        return false;
    }

    @Override
    public boolean xoaTheoMa(int maPhuongTien) {
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
