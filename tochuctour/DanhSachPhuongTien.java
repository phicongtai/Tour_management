package tochuctour;

import java.util.Arrays;

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
        if(phuongTien == null){
            return false;
        }

        if(this.danhSach == null){
            this.danhSach = new PhuongTien[0];
            this.soLuong = 0;
        }

        for(int i = 0 ; i < this.soLuong ;i++){
            if(this.danhSach[i].getMaPhuongTien() == phuongTien.getMaPhuongTien()){
                return false;
            }
        }

        this.danhSach = Arrays.copyOf(this.danhSach, soLuong + 1);
        this.danhSach[this.soLuong] = phuongTien;
        this.soLuong++;

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

        if(this.danhSach == null || maPhuongTien <= 0){
            return null;
        }

        for(int i = 0 ; i < this.soLuong ; i++){
            if(this.danhSach[i].getMaPhuongTien() == maPhuongTien){
                return this.danhSach[i];
            }
        }

        return null;
    }

    public PhuongTien[] timTheoTen(String tenPhuongTien) {
        if(this.danhSach == null || tenPhuongTien.isBlank()){
            return new PhuongTien[0];
        }

        PhuongTien[] temp = new PhuongTien[0];
        int tempsize = 0;

        for(int i = 0 ; i < this.soLuong ; i++){
            if(this.danhSach[i].getTenPhuongTien().equals(tenPhuongTien)){
                temp = Arrays.copyOf(temp,tempsize + 1);
                temp[tempsize] = this.danhSach[i];
                tempsize++;
            }
        }

        return temp;
    }

    @Override
    public boolean capNhat(PhuongTien phuongTien) {
        if(this.danhSach == null || phuongTien == null){
            return false;
        }
        for(int i = 0; i < this.soLuong ; i++){
            if(this.danhSach[i].getMaPhuongTien() == phuongTien.getMaPhuongTien()){
                this.danhSach[i] = phuongTien;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean xoaTheoMa(int maPhuongTien) {
        if(this.danhSach == null || maPhuongTien <= 0){
            return false;
        }
        int point = 0;
        for(int i = 0 ; i < this.soLuong ; i++){
            if(this.danhSach[i].getMaPhuongTien() == maPhuongTien){
                point = i;
                break;
            }
        }

        for(int i = point ; i < this.soLuong-1 ; i++){
            this.danhSach[i] = this.danhSach[i + 1];
        }

        this.soLuong--;
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
