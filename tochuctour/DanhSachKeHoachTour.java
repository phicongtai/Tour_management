package tochuctour;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;

import danhsach.DanhSach;

public class DanhSachKeHoachTour implements DanhSach<KeHoachTour> {
    private KeHoachTour[] danhSach;
    private int soLuong;

    // Constructors
    public DanhSachKeHoachTour() {
    }

    public DanhSachKeHoachTour(KeHoachTour[] danhSach, int soLuong) {
        this.danhSach = new KeHoachTour[10];
        this.soLuong = 0;
    }

    // Getters / Setters
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

    // Methods @Override
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
    public boolean them() {
        return false;
    }

    @Override
    public void xuat() {
    }

    @Override
    public void nhap() {
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
    public boolean suaTheoMa(int ma) {
        return false;
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

    @Override
    public KeHoachTour[] layTatCa() {
        return danhSach;
    }

// maKeHoachTour|maTour|maNhanVien|ngayDi|ngayVe|soLuongVeToiDa
@Override
public boolean docFile(String duongDan) {
    this.danhSach = new KeHoachTour[0];
    this.soLuong = 0;
    try (BufferedReader reader = new BufferedReader(new FileReader(duongDan))) {
        String temp;
        while ((temp = reader.readLine()) != null) {
            if (temp.trim().isEmpty()) continue;
            String[] parts = temp.split("\\|"); //dùng cái nay để tách 1 chuổi thành 1 mạng phga6n tách bằng dấu |
            if (parts.length >= 6) { // số 6 này biệu thì cho số lượng thuộc tính trong class kế hoạch tour 
                KeHoachTour kh = new KeHoachTour(
                    Integer.parseInt(parts[0].trim()),
                    Integer.parseInt(parts[1].trim()),
                    Integer.parseInt(parts[2].trim()),
                    parts[3].trim(),
                    parts[4].trim(),
                    Integer.parseInt(parts[5].trim())
                );
                this.danhSach = Arrays.copyOf(this.danhSach, this.soLuong + 1);
                this.danhSach[this.soLuong++] = kh;
            }
        }
        return true;
    } catch (Exception e) {
        return false;
    }
}

    @Override
    public boolean ghiFile(String duongDan) {
        if(this.danhSach == null){
            return false;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(duongDan))) {
            for(int i = 0 ; i < this.soLuong ; i++){
                String temp = "";
                temp += this.danhSach[i].getMaKeHoachTour();
                temp += "|";
                temp += this.danhSach[i].getMaTour();
                temp += "|";
                temp += this.danhSach[i].getMaNhanVien();
                temp += "|";
                temp += this.danhSach[i].getNgayDi();
                temp += "|";
                temp += this.danhSach[i].getNgayVe();
                temp += "|";
                temp += this.danhSach[i].getSoLuongVeToiDa();
                writer.write(temp);
                writer.newLine();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Methods còn lại
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
}
