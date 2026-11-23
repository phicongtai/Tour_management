package tochuctour;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;

import danhsach.DanhSach;

public class DanhSachKhachSan implements DanhSach<KhachSan> {
    private static final Scanner in = new Scanner(System.in);
    private KhachSan[] danhSach;
    private int soLuong;

    // Constructors
    public DanhSachKhachSan() {
    }

    public DanhSachKhachSan(KhachSan[] danhSach, int soLuong) {
        this.danhSach = danhSach;
        this.soLuong = soLuong;
    }

    // Getters / Setters
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

    // Methods @Override
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
    public boolean them() {
        if(this.danhSach == null){
            this.danhSach = new KhachSan[0];
            this.soLuong = 0;
        }

        this.danhSach = Arrays.copyOf(this.danhSach, this.soLuong + 1);
        this.danhSach[this.soLuong] = new KhachSan();
        this.danhSach[this.soLuong].nhap();
        this.soLuong++;
        return true;
    }

    @Override
    public void xuat() {
        if(this.danhSach == null || this.soLuong == 0){
            System.out.println("Danh sach khach san trong!");
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
        this.danhSach = new KhachSan[this.soLuong];
        for(int i = 0 ; i < this.soLuong ; i++){
            System.out.println("Nhap khach san thu " + (i + 1));
            this.danhSach[i] = new KhachSan();
            this.danhSach[i].nhap();
        }
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
    public boolean suaTheoMa(int ma) {
        if(this.danhSach == null){
            return false;
        }
        KhachSan temp = timTheoMa(ma);
        if(temp == null){
            return false;
        }

        System.out.println("Thong tin khach san goc la:");
        temp.xuat();

        System.out.println("Chinh sua thong tin");

        System.out.print("Chinh sua ten khach san: ");
        String tenkhachsan = in.nextLine();
        if(!tenkhachsan.isBlank()){
            temp.setTenKhachSan(tenkhachsan);
        }

        System.out.print("Chinh sua gia o moi dem: ");
        long giaODem = in.nextLong();
        in.nextLine();
        if(giaODem > 0){
            temp.setGiaOMoiNguoiMoiDem(giaODem); 
        }

        System.out.print("Chinh sua gia an moi ngay: ");
        long giaanmoingay = in.nextLong();
        in.nextLine();
        if(giaanmoingay > 0){
            temp.setGiaAnMoiNguoiMoiNgay(giaanmoingay); 
        }
        return true;
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

    @Override
    public KhachSan[] layTatCa() {
        return danhSach;
    }

    // maKhachSan|tenKhachSan|giaOMoiNguoiMoiDem|giaAnMoiNguoiMoiNgay
    @Override
    public boolean docFile(String duongDan) {
        this.danhSach = new KhachSan[0];
        this.soLuong = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(duongDan))) {
            String temp;
            while ((temp = reader.readLine()) != null) {
                if (temp.trim().isEmpty()) continue;
                String[] parts = temp.split("\\|");
                if (parts.length >= 4) {
                    KhachSan ks = new KhachSan(
                        Integer.parseInt(parts[0].trim()),
                        parts[1].trim(),
                        Long.parseLong(parts[2].trim()),
                        Long.parseLong(parts[3].trim())
                    );
                    this.danhSach = Arrays.copyOf(this.danhSach, this.soLuong + 1);
                    this.danhSach[this.soLuong++] = ks;
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
                temp += this.danhSach[i].getMaKhachSan();
                temp += "|";
                temp += this.danhSach[i].getTenKhachSan();
                temp += "|";
                temp += this.danhSach[i].getGiaOMoiNguoiMoiDem();
                temp += "|";
                temp += this.danhSach[i].getGiaAnMoiNguoiMoiNgay();
                writer.write(temp);
                writer.newLine();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Methods con lai
    public KhachSan[] timTheoTen(String tenKhachSan) {
        if(this.danhSach == null||tenKhachSan.isBlank()){
            return new KhachSan[0];
        }

        int tempsize = 0;
        KhachSan[] temp = new KhachSan[0];

        for(int i = 0 ; i < this.soLuong ; i++){
            if(this.danhSach[i].getTenKhachSan().equalsIgnoreCase(tenKhachSan)){
            temp = Arrays.copyOf(temp, tempsize + 1);
            temp[tempsize] = this.danhSach[i];
            tempsize++;
            }
        }
        return temp;
    }

    public int getMaMoi() {
        int max = 0;
        for (int i = 0; i < this.soLuong; i++) {
            if (this.danhSach[i] != null && this.danhSach[i].getMaKhachSan() > max) {
                max = this.danhSach[i].getMaKhachSan();
            }
        }
        return max + 1;
    }
}



