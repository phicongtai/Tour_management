package tochuctour;

import java.nio.file.Path;
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
        if(maKhachSan <= 0){
            return null;
        }

        for(int i = 0 ; i < this.soLuong ; i++){
            if(this.danhSach[i].getMaKhachSan() == maKhachSan){
                return this.danhSach[i];
            }
        }

        return null;
    }

    public KhachSan[] timTheoTen(String tenKhachSan) {
        if(this.danhSach == null||tenKhachSan.isBlank()){
            return new KhachSan[0];
        }

        int tempsize = 0;
        KhachSan[] temp = new KhachSan[0];

        for(int i = 0 ; i < this.soLuong ; i++){
            if(this.danhSach[i].getTenKhachSan().equals(tenKhachSan)){
            temp = Arrays.copyOf(temp, tempsize + 1);
            temp[tempsize] = this.danhSach[i];
            tempsize++;
            }
    }
    return temp;
}

    @Override
    public boolean capNhat(KhachSan khachSan) {
        if(this.danhSach == null || khachSan == null){
            return false;
        }
        for(int i = 0 ; i < this.soLuong ; i++){
            if(this.danhSach[i].getMaKhachSan() == khachSan.getMaKhachSan()){
                this.danhSach[i] = khachSan;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean xoaTheoMa(int maKhachSan) {
        if(this.danhSach == null || maKhachSan <= 0){
            return false;
        }

        boolean check = false;
        int point = 0;
        for(int i = 0 ; i < this.soLuong ; i++){
            if(this.danhSach[i].getMaKhachSan() == maKhachSan){
                point = i;
                check = true;
                break;
            }
        }

        if(!check){
            return  false;
        }

        for(int i = point ; i < this.soLuong - 1 ;i++){
            this.danhSach[i] = this.danhSach[i+1];
        }

        this.soLuong--;
        return true;
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
