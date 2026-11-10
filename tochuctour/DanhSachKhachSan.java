package tochuctour;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;

import danhsach.DanhSach;

public class DanhSachKhachSan implements DanhSach<KhachSan> {
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
        if(this.danhSach == null){
            return;
        }
        System.out.println("Danh sách khách sạn");
        for(int i = 0 ; i < this.soLuong ; i++){
            this.danhSach[i].xuat();
        }
    }

    @Override
    public void nhap() {
        Scanner in = new Scanner(System.in);
        System.out.print("Nhập vào số lượng: ");
        this.soLuong = in.nextInt();
        in.nextLine();
        this.danhSach = new KhachSan[this.soLuong];
        for(int i = 0 ; i < this.soLuong ; i++){
            System.out.println("Nhập khách sạn thứ " + (i + 1));
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
        Scanner in = new Scanner(System.in);
        if(this.danhSach == null){
            return false;
        }
        KhachSan temp = timTheoMa(ma);
        if(temp == null){
            return false;
        }

        System.out.println("Thông tin khách sạn gốc là:");
        temp.xuat();

        System.out.println("Chỉnh sửa thông tin");

        System.out.print("Chỉnh sửa tên khách sạn: ");
        String tenkhachsan = in.nextLine();
        if(!tenkhachsan.isBlank()){
            temp.setTenKhachSan(tenkhachsan);
        }

        System.out.print("Chỉnh sửa giá ở mỗi đêm: ");
        long giaODem = in.nextLong();
        if(giaODem > 0){
            temp.setGiaOMoiNguoiMoiDem(giaODem); 
        }

        System.out.print("Chỉnh sửa giá ăn mỗi ngày: ");
        long giaanmoingay = in.nextLong();
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

    // Methods còn lại
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
}
