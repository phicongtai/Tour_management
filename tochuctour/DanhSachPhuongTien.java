package tochuctour;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import danhsach.DanhSach;

public class DanhSachPhuongTien implements DanhSach<PhuongTien> {
    private PhuongTien[] danhSach;
    private int soLuong;

    // Constructors
    public DanhSachPhuongTien() {
    }

    public DanhSachPhuongTien(PhuongTien[] danhSach, int soLuong) {
        this.danhSach = danhSach;
        this.soLuong = soLuong;
    }

    // Getters / Setters
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

    // Methods @Override
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
    public boolean suaTheoMa(int ma) {
        return false;
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

    @Override
    public PhuongTien[] layTatCa() {
        return danhSach;
    }

    // maPhuongTien|tenPhuongTien|soChoToiDa|giaMoiNguoiMoiNgay
    @Override
    public boolean docFile(String duongDan) {
        this.danhSach = new PhuongTien[0];
        this.soLuong = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(duongDan))) {
            String temp;
            while ((temp = reader.readLine()) != null) {
                if (temp.trim().isEmpty()) continue;
                String[] parts = temp.split("\\|");
                if (parts.length >= 4) {
                    PhuongTien pt = new PhuongTien(
                        Integer.parseInt(parts[0].trim()),
                        parts[1].trim(),
                        Integer.parseInt(parts[2].trim()),
                        Long.parseLong(parts[3].trim())
                    );
                    this.danhSach = Arrays.copyOf(this.danhSach, this.soLuong + 1);
                    this.danhSach[this.soLuong++] = pt;
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean ghiFile(String duongDan) {
        if (this.danhSach == null) {
            return false;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(duongDan))) {
            for (int i = 0; i < this.soLuong; i++) {
                String temp = "";
                temp += this.danhSach[i].getMaPhuongTien();
                temp += "|";
                temp += this.danhSach[i].getTenPhuongTien();
                temp += "|";
                temp += this.danhSach[i].getSoChoToiDa();
                temp += "|";
                temp += this.danhSach[i].getGiaMoiNguoiMoiNgay();
                writer.write(temp);
                writer.newLine();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Methods còn lại
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
}
