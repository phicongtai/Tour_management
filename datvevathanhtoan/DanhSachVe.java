package datvevathanhtoan;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;
import danhsach.DanhSach;
import kho.KhoDuLieu;
import nguoidung.DanhSachHanhKhach;
import nguoidung.HanhKhach;
import tochuctour.DanhSachKeHoachTour;
import tochuctour.KeHoachTour;

public class DanhSachVe implements DanhSach<Ve> {
    private Ve[] danhSach;
    private int soLuong;

    // Constructors
    public DanhSachVe() {
    }

    public DanhSachVe(Ve[] danhSach, int soLuong) {
        this.danhSach = danhSach;
        this.soLuong = soLuong;
    }

    // Getters / Setters
    public Ve[] getDanhSach() {
        return danhSach;
    }

    public void setDanhSach(Ve[] danhSach) {
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
    public boolean them(Ve ve) {
        if (ve == null){
            return false;
        }
        if(danhSach == null){
            this.danhSach = new Ve[0];
            this.soLuong = 0;
        }
        
        for (int i = 0 ; i < this.soLuong; i++){
            if(this.danhSach[i].getMaVe() == ve.getMaVe()){
                return false;
            }
        }

        this.danhSach = Arrays.copyOf(this.danhSach, soLuong + 1);
        this.danhSach[this.soLuong] = ve;
        this.soLuong++;

        return true;
    }

    @Override
    public boolean them() {
        if(this.danhSach == null){
            this.danhSach = new Ve[0];
            this.soLuong = 0;
        }

        this.danhSach = Arrays.copyOf(this.danhSach, this.soLuong + 1);
        this.danhSach[this.soLuong] = new Ve();
        this.danhSach[this.soLuong].nhap();
        this.soLuong++;
        
        return true;
    }

    @Override
    public void xuat() {
        if(danhSach == null){
            return;
        }
        System.out.println("Danh sach ve: ");
        for(int i = 0 ; i < this.soLuong; i++){
            this.danhSach[i].xuat();
        }
    }

    @Override
    public void nhap() {
        Scanner in = KhoDuLieu.getScanner();
        System.out.print("Nhap so luong ve: ");
        this.soLuong = in.nextInt();
        in.nextLine();
        for(int i = 0 ; i < this.soLuong; i++){
            System.out.println("Nhap ve thu: " + (i + 1));
            this.danhSach[i] = new Ve();
            this.danhSach[i].nhap();
        }
    }

    @Override
    public boolean xoaTheoMa(int maVe) {
        if(this.danhSach == null || maVe <= 0){
            return false;
        }
        boolean check = false;
        int checkpoint = 0;
        for(int i = 0 ; i < this.soLuong - 1; i++){
            if(this.danhSach[i].getMaVe() == maVe){
                check = true;
                checkpoint = i;
                break;
            }
        }

        if(!check){
            return false;
        }
        for (int i = checkpoint; i < this.soLuong; i++){
            this.danhSach[i] = this.danhSach[i+1];
        }
        this.soLuong--;
        return true;
    }

    @Override
    public boolean suaTheoMa(int ma) {
        Scanner in = KhoDuLieu.getScanner();
        if(this.danhSach == null){
            return false;
        }

        Ve temp = timTheoMa(ma);
        if(temp == null ){
            return false;
        }
        System.out.println("Thông tin vé là");
        temp.xuat();
        
        System.out.print("Sửa ngày đặt: ");
        String ngaydat = in.nextLine();
        if(!ngaydat.isBlank()){
        temp.setNgayDat(ngaydat);
        }

        System.out.print("Sữa đơn giá: ");
        long dongia = in.nextLong();
        if(dongia > 0){
        temp.setDonGia(dongia);
        }

        System.out.print("Sua trang thai: ");
        int trangThai = in.nextInt();
        if(trangThai > 0){
        temp.setTrangThai(trangThai);
        }
        return false;
    }
    @Override
    public Ve timTheoMa(int maVe) {
        if (maVe <= 0 || danhSach == null) {
            return null;
        }
        for (int i = 0; i < this.soLuong; i++) {
            if (maVe == this.danhSach[i].getMaVe()) {
                return this.danhSach[i];
            }
        }
        return null;
    }
    @Override
    public Ve[] layTatCa() {
        return danhSach;
    }

    @Override
    public boolean docFile(String duongDan) {
        this.danhSach = new Ve[0];
        this.soLuong = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(duongDan))) {
            String temp;
            while ((temp = reader.readLine()) != null) {
                if (temp.trim().isEmpty()) continue;
                String[] parts = temp.split("\\|");
                if (parts.length >= 8) {
                    Ve ve = new Ve(
                        Integer.parseInt(parts[0].trim()),
                        Integer.parseInt(parts[1].trim()),
                        Integer.parseInt(parts[2].trim()),
                        Integer.parseInt(parts[3].trim()),
                        Integer.parseInt(parts[4].trim()),
                        parts[5].trim(),
                        Long.parseLong(parts[6].trim()),
                        Integer.parseInt(parts[7].trim())
                    );
                    this.danhSach = Arrays.copyOf(this.danhSach, this.soLuong + 1);
                    this.danhSach[this.soLuong++] = ve;
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
                temp += this.danhSach[i].getMaVe();
                temp += "|";
                temp += this.danhSach[i].getMaDonDatTour();
                temp += "|";
                temp += this.danhSach[i].getMaNguoiDat();
                temp += "|";
                temp += this.danhSach[i].getMaKeHoachTour();
                temp += "|";
                temp += this.danhSach[i].getMaHanhKhach();
                temp += "|";
                temp += this.danhSach[i].getNgayDat();
                temp += "|";
                temp += this.danhSach[i].getDonGia();
                temp += "|";
                temp += this.danhSach[i].getTrangThai();
                writer.write(temp);
                writer.newLine();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public Ve[] timTheoMaDonDatTour(int maDonDatTour) {
        if (maDonDatTour <= 0 || danhSach == null) {
            return new Ve[0];
        }
        Ve[] temp = new Ve[0];
        int tempsize = 0 ;
        for(int i = 0 ; i < this.soLuong ; i++){
            if(maDonDatTour == this.danhSach[i].getMaDonDatTour()){
                temp = Arrays.copyOf(temp,tempsize + 1);
                temp[tempsize] = this.danhSach[i];
                tempsize++;
            }
        }
        return temp;
    }

    public int[] layDanhSachMaDonDatTour() {
    if (danhSach == null || soLuong <= 0) {
        return new int[0];
    }
    int[] tam = new int[soLuong];
    int dem = 0;
    for (int i = 0; i < soLuong; i++) {
        if (danhSach[i] == null) {
            continue;
        }
        int maDonDatTour = danhSach[i].getMaDonDatTour();
        boolean daCo = false;
        for (int j = 0; j < dem; j++) {
            if (tam[j] == maDonDatTour) {
                daCo = true;
                break;
            }
        }
        if (!daCo) {
            tam[dem] = maDonDatTour;
            dem++;
        }
    }
    int[] ketQua = new int[dem];
    for (int i = 0; i < dem; i++) {
        ketQua[i] = tam[i];
    }
    return ketQua;
}
    public Ve[] timTheoTrangThai(int trangThai) {
        if (soLuong == 0) {
            return new Ve[0];
        }
        Ve[] ketQua = new Ve[0];
        int dem = 0;
        for (int i = 0; i < this.soLuong; i++) {
            if (danhSach[i] != null && danhSach[i].getTrangThai() == trangThai) {
                ketQua[dem++] = danhSach[i];
            }
        }
        return Arrays.copyOf(ketQua, dem);
    }

    public int demVeChiemChoTheoKeHoach(int maKeHoachTour) {
        if (maKeHoachTour <= 0) {
            return 0;
        }
        int dem = 0;
        for (int i = 0; i < this.soLuong; i++) {
            if (danhSach[i] != null && danhSach[i].getMaKeHoachTour() == maKeHoachTour
                    && danhSach[i].chiemCho()) {
                dem++;
            }
        }
        return dem;
    }
    public boolean daCoVeHopLe(int maKeHoachTour, int maHanhKhach) {
        if (maKeHoachTour <= 0 || maHanhKhach <= 0) {
            return false;
        }
        for (int i = 0; i < this.soLuong; i++) {
            if (danhSach[i] != null && danhSach[i].getMaKeHoachTour() == maKeHoachTour
                    && danhSach[i].getMaHanhKhach() == maHanhKhach && danhSach[i].chiemCho()) {
                return true;
            }
        }
        return false;
    }

    public int getmamoi(){
        int temp = 0;
        if(this.danhSach != null){
            for(int i = 0 ; i < this.soLuong; i++){
                if(this.danhSach[i] != null && temp < this.danhSach[i].getMaVe()){
                    temp = this.danhSach[i].getMaVe();
                }
            }
        }
        return temp + 1;
    }

    public int getmadondattourmoi(){
        int temp = 0;
        if(this.danhSach != null){
            for(int i = 0 ; i < this.soLuong ; i++){
                if(this.danhSach[i] != null && this.danhSach[i].getMaDonDatTour() > temp){
                    temp = this.danhSach[i].getMaDonDatTour();
                }
            }
        }
        return temp + 1;
    }
}
