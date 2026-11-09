package tochuctour;

import java.nio.file.Path;

import java.util.Arrays;

import danhsach.DanhSach;

public class DanhSachKeHoachTour implements DanhSach<KeHoachTour> {
    private KeHoachTour[] danhSach;
    private int soLuong;

    public DanhSachKeHoachTour() {
    }

    public DanhSachKeHoachTour(KeHoachTour[] danhSach, int soLuong) {
        this.danhSach = new KeHoachTour[10];
        this.soLuong = 0;
    }

    @Override
    public boolean them(KeHoachTour keHoach) {
        if(keHoach == null){
            return false;
        }
        if(danhSach == null){
            this.danhSach = new KeHoachTour[0];
            this.soLuong = 0;
        }
        for(int i = 0 ; i < this.soLuong ; i++){
            if(this.danhSach[i].getMaKeHoachTour() == keHoach.getMaKeHoachTour() ){;
            return false;
            }
        }

        this.danhSach = Arrays.copyOf(this.danhSach, soLuong + 1);
        this.danhSach[this.soLuong] = keHoach;
        this.soLuong++;
        return true;
    }

    @Override
    public KeHoachTour[] layTatCa() {
        return danhSach;
    }

    public KeHoachTour[] getDanhSach() {
        return danhSach;
    }

    public void setDanhSach(KeHoachTour[] danhSach) {
        this.danhSach = danhSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    @Override
    public KeHoachTour timTheoMa(int maKeHoachTour) {
        if(maKeHoachTour <= 0 || this.danhSach == null){
            return null;
        }

        for(int i = 0 ; i < this.soLuong ; i++){
            if(maKeHoachTour == this.danhSach[i].getMaKeHoachTour()){
                return this.danhSach[i];
            }
        }
        return null;
    }

    public KeHoachTour[] timTheoMaTour(int maTour) {
        if(maTour <= 0 || this.danhSach == null){
            return new KeHoachTour[0];
        }

        KeHoachTour[] temp = new KeHoachTour[0];
        int tempsize = 0 ;
        
        for(int i = 0 ; i < this.soLuong ; i++){
            if(maTour == this.danhSach[i].getMaTour()){
                temp = Arrays.copyOf(temp,tempsize + 1);
                temp[tempsize] = this.danhSach[i];
                tempsize++;
            }
        }

        return temp;
    }

    @Override
    public boolean capNhat(KeHoachTour keHoach) {
        if(keHoach == null){
            return false;
        }

        for(int  i = 0 ; i < this.soLuong ; i++){
            if(this.danhSach[i].getMaKeHoachTour() == keHoach.getMaKeHoachTour()){
                this.danhSach[i] = keHoach;
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean xoaTheoMa(int maKeHoachTour) {
        if(this.danhSach == null || maKeHoachTour <= 0 ){
            return false;
        }
        boolean check = false;
        int checkpoint = 0;
        for(int i = 0 ; i < this.soLuong ; i++){
            if(this.danhSach[i].getMaKeHoachTour() == maKeHoachTour){
                check = true;
                checkpoint = i;
                break;
            }
        }

        if(!check){
            return false;
        }
        for(int i = checkpoint ; i < this.soLuong-1 ; i++){
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
