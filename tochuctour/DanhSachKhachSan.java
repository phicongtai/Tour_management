package tochuctour;

import java.util.Arrays;

import danhsach.DanhSach;

public class DanhSachKhachSan implements DanhSach<KhachSan> {
    private KhachSan[] danhSach;
    private int soLuong;

    public DanhSachKhachSan() {
    }

    public DanhSachKhachSan(KhachSan[] danhSach, int soLuong) {
        this.danhSach = danhSach;
        this.soLuong = soLuong;
    }

    @Override
    public boolean them(KhachSan khachSan) {
        if(khachSan == null){
            return false;
        }

        if(this.danhSach == null){
            this.danhSach = new KhachSan[0];
            this.soLuong = 0;
        }

        for(int i = 0 ; i < this.soLuong ; i++){
            if(this.danhSach[i].getMaKhachSan() == khachSan.getMaKhachSan()){
                return false;
            }
        }

        this.danhSach = Arrays.copyOf(this.danhSach, soLuong + 1);
        this.danhSach[this.soLuong] = khachSan;
        this.soLuong++;
        return true;
    }

    @Override
    public KhachSan[] layTatCa() {
        return danhSach;
    }

    public KhachSan[] getDanhSach() {
        return danhSach;
    }

    public void setDanhSach(KhachSan[] danhSach) {
        this.danhSach = danhSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    @Override
    public KhachSan timTheoMa(int maKhachSan) {
        return null;
    }

    public KhachSan[] timTheoTen(String tenKhachSan) {
        return new KhachSan[0];
    }

    @Override
    public boolean capNhat(KhachSan khachSan) {
        return false;
    }

    @Override
    public boolean xoaTheoMa(int maKhachSan) {
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
