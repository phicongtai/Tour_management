package tochuctour;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;

import danhsach.DanhSach;

public class DanhSachPhuongTien implements DanhSach<PhuongTien> {
    private static final Scanner in = new Scanner(System.in);
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

        return true;
    }

    @Override
    public boolean them() {
        if(this.danhSach == null){
            this.danhSach = new PhuongTien[0];
            this.soLuong = 0;
        }

        this.danhSach = Arrays.copyOf(this.danhSach, this.soLuong + 1);
        this.danhSach[this.soLuong] = new PhuongTien();
        this.danhSach[this.soLuong].nhap();
        this.soLuong++;
        return true;
    }

    @Override
    public void xuat() {
        if(this.danhSach == null || this.soLuong == 0){
            System.out.println("Danh sach phuong tien trong!");
            return;
        }
        for(int i = 0 ; i < this.soLuong ; i++){
            if(this.danhSach[i] != null){
                this.danhSach[i].xuat();
            }
        }
    }

    @Override
    public void nhap() {
        System.out.print("Nhap vao so luong: ");
        this.soLuong = in.nextInt();
        in.nextLine();
        this.danhSach = new PhuongTien[this.soLuong];
        for(int i = 0 ; i < this.soLuong ; i++){
            System.out.println("Nhap phuong tien thu " + (i + 1));
            this.danhSach[i] = new PhuongTien();
            this.danhSach[i].nhap();
    }
    }

    @Override
    public boolean xoaTheoMa(int maPhuongTien) {
        if(this.danhSach == null || maPhuongTien <= 0){
            return false;
        }
        int point = 0;
        boolean check = false;
        for(int i = 0 ; i < this.soLuong ; i++){
            if(this.danhSach[i].getMaPhuongTien() == maPhuongTien){
                check = true;
                point = i;
                break;
            }
        }

        if(!check){
            return false;
        }

        for(int i = point ; i < this.soLuong-1 ; i++){
            this.danhSach[i] = this.danhSach[i + 1];
        }
        this.danhSach=Arrays.copyOf(this.danhSach,this.soLuong-1);
        this.soLuong--;
        return true;
    }

    @Override
    public boolean suaTheoMa(int ma) {
        if(this.danhSach == null){
            return false;
        }

        PhuongTien temp = timTheoMa(ma);
        if(temp == null){
            return false;
        }

        System.out.println("Thong tin phuong tien goc la:");
        temp.xuat();

        System.out.print("Chinh sua ten phuong tien: ");
        String tenphuongtien = in.nextLine();
        if(!tenphuongtien.isBlank()){
        temp.setTenPhuongTien(tenphuongtien);
        }

        System.out.print("chinh sua so cho: ");
        int socho = in.nextInt();
        in.nextLine();
        if(socho > 0){
        temp.setSoChoToiDa(socho);
        }

        System.out.print("chinh sua gia moi nguoi moi ngay: ");
        long gia = in.nextLong();
        in.nextLine();
        if(gia > 0){
        temp.setGiaMoiNguoiMoiNgay(gia);
        }
        return true;
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

    // Methods con lai
    public PhuongTien[] timTheoTen(String tenPhuongTien) {
        if(this.danhSach == null || tenPhuongTien.isBlank()){
            return new PhuongTien[0];
        }

        PhuongTien[] temp = new PhuongTien[0];
        int tempsize = 0;

        for(int i = 0 ; i < this.soLuong ; i++){
            if(this.danhSach[i].getTenPhuongTien().equalsIgnoreCase(tenPhuongTien)){
                temp = Arrays.copyOf(temp,tempsize + 1);
                temp[tempsize] = this.danhSach[i];
                tempsize++;
            }
        }

        return temp;
    }

    public int getMaMoi() {
        int max = 0;
        for (int i = 0; i < this.soLuong; i++) {
            if (this.danhSach[i] != null && this.danhSach[i].getMaPhuongTien() > max) {
                max = this.danhSach[i].getMaPhuongTien();
            }
        }
        return max + 1;
    }
}
