package tour;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;
import tour.*;
import danhsach.DanhSach;
import kho.KhoDuLieu;
import tochuctour.KeHoachTour;

public class DanhSachTour implements DanhSach<Tour> {
    private Tour[] danhSach;
    private int soLuong;

    // Constructors
    public DanhSachTour() {
    }

    public DanhSachTour(Tour[] danhSach, int soLuong) {
        this.danhSach = danhSach;
        this.soLuong = soLuong;
    }

    // Getters / Setters
    public Tour[] getDanhSach() {
        return danhSach;
    }

    public void setDanhSach(Tour[] danhSach) {
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
    public boolean them(Tour tour) {
        if(tour == null ) {
            return false;
        }
        if(danhSach == null){
            this.danhSach = new Tour[0];
            this.soLuong = 0;
        }
        for(int i = 0 ; i < this.soLuong ; i++){
            if(this.danhSach[i].getMaTour() == tour.getMaTour() ){
            return false;
            }
        }
        this.danhSach = Arrays.copyOf(this.danhSach, this.soLuong + 1);
        this.danhSach[this.soLuong] = tour;
        this.soLuong++;
        return true;
    }

    @Override
    public boolean them() {
        Scanner in = KhoDuLieu.getScanner();
        System.out.println("Nhap loai tour (1: Trong nuoc, 2: Nuoc ngoai):");
        int loaiTour=in.nextInt();
        in.nextLine();
        Tour tour;
        if(loaiTour==1){
            tour=new TourTrongNuoc();
        }
        else if(loaiTour==2){
            tour=new TourNuocNgoai();
        }
        else{
            System.out.println("Loai tour khong hop le!");
            return false;
        }
        tour.nhap();
        if(this.danhSach == null){
            this.danhSach = new Tour[0];
            this.soLuong = 0;
        }
        this.danhSach = Arrays.copyOf(this.danhSach, this.soLuong + 1);
        this.danhSach[this.soLuong] = tour;
        this.soLuong++;
        return true;
    }

    @Override
    public void xuat() {
        if(this.danhSach==null||this.soLuong==0){
            System.out.println("Danh sach tour rong!");
            return;
        }
        for(int i=0;i<this.soLuong;i++){
            this.danhSach[i].xuat();
        }
    }

    @Override
    public void nhap() {
        Scanner in=KhoDuLieu.getScanner();
        System.out.println("Nhap so luong tour:");
        this.soLuong=in.nextInt();
        in.nextLine();
        this.danhSach = new Tour[this.soLuong];
        for(int i=0;i<this.soLuong;i++){
            System.out.println("Nhap tour thu: "+ (i+1));
            System.out.println("Nhap loai tour (1: Trong nuoc, 2: Nuoc ngoai):");
            int loaiTour=in.nextInt();
            if(loaiTour==1){
                this.danhSach[i]=new TourTrongNuoc();
            }
            else if(loaiTour==2){
                this.danhSach[i]=new TourNuocNgoai();
            }
            this.danhSach[i].nhap();
        }
    }

    @Override
    public boolean xoaTheoMa(int maTour) {
        if (this.danhSach == null) return false;

        for (int i = 0; i < this.soLuong; i++) {
            if (this.danhSach[i] != null
                    && this.danhSach[i].getMaTour() == maTour) {
                for (int j = i; j < this.soLuong - 1; j++)
                    this.danhSach[j] = this.danhSach[j + 1];

                this.danhSach[--this.soLuong] = null;
                return true;
            }
        }
        return false;
    }      

    @Override
    public boolean suaTheoMa(int ma) {
        Scanner in = KhoDuLieu.getScanner();
        if(this.danhSach == null){
            return false;
        }
        Tour tour = timTheoMa(ma);
        if(tour == null){
            return false;
        }
        System.out.println("Thong tin tour goc la: " + tour);
        tour.xuat();
        System.out.println("Chinh sua ten tour: ");
        String tenTour=in.nextLine();
        if(!tenTour.isBlank()){
            tour.setTenTour(tenTour);
        }
        System.out.println("Chinh sua diem khoi hanh: ");
        String diemKhoiHanh=in.nextLine();
        if(!diemKhoiHanh.isBlank()){
            tour.setDiemKhoiHanh(diemKhoiHanh);
        }
        System.out.println("Chinh sua diem den: ");
        String diemDen=in.nextLine();
        if(!diemDen.isBlank()){
            tour.setDiemDen(diemDen);
        }
        System.out.println("Chinh sua so ngay: ");
        String soNgay=in.nextLine();
        if(!soNgay.isBlank()){
            tour.setSoNgay(Integer.parseInt(soNgay));
        }
        System.out.println("Chinh sua don gia co ban: ");
        String donGiaCoBan=in.nextLine();
        if(!donGiaCoBan.isBlank()){
            tour.setDonGiaCoBan(Long.parseLong(donGiaCoBan));
        }
        if(tour instanceof TourTrongNuoc){
            TourTrongNuoc tourTrongNuoc=(TourTrongNuoc) tour;
            System.out.println("Thong tin goc cua tour trong nuoc la: ");
            tourTrongNuoc.xuat();
            System.out.println("Chinh sua vung mien: ");
            String vungMien=in.nextLine();
            if(!vungMien.isBlank()){
                tourTrongNuoc.setVungMien(vungMien);
            }
            System.out.println("Chinh sua phu phi: ");
            String phuPhi=in.nextLine();
            if(!phuPhi.isBlank()){
                tourTrongNuoc.setPhuPhi(Long.parseLong(phuPhi));
            }
        }
    else if(tour instanceof TourNuocNgoai){
            TourNuocNgoai tourNuocNgoai=(TourNuocNgoai) tour;
            System.out.println("Thong tin goc cua tour nuoc ngoai la: ");
            tourNuocNgoai.xuat();
            System.out.println("Chinh sua quoc gia: ");
            String quocGia=in.nextLine();
            if(!quocGia.isBlank()){
                tourNuocNgoai.setQuocGia(quocGia);
            }
            System.out.println("Chinh sua can visa: ");
            String canVisa=in.nextLine();
            if(!canVisa.isBlank()){
                tourNuocNgoai.setCanVisa(Boolean.parseBoolean(canVisa));
            }
            System.out.println("Chinh sua phi visa: ");
            String phiVisa=in.nextLine();
            if(!phiVisa.isBlank()){
                tourNuocNgoai.setPhiVisa(Long.parseLong(phiVisa));
            }
        }
        System.out.println("Chinh sua thanh cong!");
        return true;
    }

    @Override
    public Tour timTheoMa(int maTour) {
        if (this.danhSach == null) return null;

        for (int i = 0; i < this.soLuong; i++)
            if (this.danhSach[i] != null
                    && this.danhSach[i].getMaTour() == maTour)
                return this.danhSach[i];

        return null;
    }

    @Override
    public Tour[] layTatCa() {
        return this.danhSach == null ? new Tour[0]
                : Arrays.copyOf(this.danhSach, this.soLuong);
    }

    @Override
    public boolean docFile(String duongDan) {
        this.danhSach = new Tour[0];
        this.soLuong = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(duongDan))) {
            String temp;
            while ((temp = reader.readLine()) != null) {
                if (temp.trim().isEmpty()) continue;
                String[] parts = temp.split("\\|");
                if (parts[0].trim().equalsIgnoreCase("TRONG_NUOC") && parts.length >= 9) {
                    TourTrongNuoc t = new TourTrongNuoc(
                        Integer.parseInt(parts[1].trim()),
                        parts[2].trim(),
                        parts[3].trim(),
                        parts[4].trim(),
                        Integer.parseInt(parts[5].trim()),
                        Long.parseLong(parts[6].trim()),
                        parts[7].trim(),
                        Long.parseLong(parts[8].trim())
                    );
                    this.danhSach = Arrays.copyOf(this.danhSach, this.soLuong + 1);
                    this.danhSach[this.soLuong++] = t;
                } else if (parts[0].trim().equalsIgnoreCase("NUOC_NGOAI") && parts.length >= 10) {
                    TourNuocNgoai t = new TourNuocNgoai(
                        Integer.parseInt(parts[1].trim()),
                        parts[2].trim(),
                        parts[3].trim(),
                        parts[4].trim(),
                        Integer.parseInt(parts[5].trim()),
                        Long.parseLong(parts[6].trim()),
                        parts[7].trim(),
                        Boolean.parseBoolean(parts[8].trim()),
                        Long.parseLong(parts[9].trim())
                    );
                    this.danhSach = Arrays.copyOf(this.danhSach, this.soLuong + 1);
                    this.danhSach[this.soLuong++] = t;
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
                if (this.danhSach[i] instanceof TourTrongNuoc) {
                    TourTrongNuoc t = (TourTrongNuoc) this.danhSach[i];
                    temp += "TRONG_NUOC|";
                    temp += t.getMaTour() + "|";
                    temp += t.getTenTour() + "|";
                    temp += t.getDiemKhoiHanh() + "|";
                    temp += t.getDiemDen() + "|";
                    temp += t.getSoNgay() + "|";
                    temp += t.getDonGiaCoBan() + "|";
                    temp += t.getVungMien() + "|";
                    temp += t.getPhuPhi();
                } else if (this.danhSach[i] instanceof TourNuocNgoai) {
                    TourNuocNgoai t = (TourNuocNgoai) this.danhSach[i];
                    temp += "NUOC_NGOAI|";
                    temp += t.getMaTour() + "|";
                    temp += t.getTenTour() + "|";
                    temp += t.getDiemKhoiHanh() + "|";
                    temp += t.getDiemDen() + "|";
                    temp += t.getSoNgay() + "|";
                    temp += t.getDonGiaCoBan() + "|";
                    temp += t.getQuocGia() + "|";
                    temp += t.isCanVisa() + "|";
                    temp += t.getPhiVisa();
                }
                writer.write(temp);
                writer.newLine();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Methods còn lại
    public Tour[] timTheoTen(String tenTour) {
        if (this.danhSach == null || tenTour == null || tenTour.trim().isEmpty())
            return new Tour[0];

        String tuKhoa = tenTour.trim().toLowerCase();
        Tour[] ketQua = new Tour[0];
        int index = 0;
        for (int i = 0; i < this.soLuong; i++) {
            if (this.danhSach[i] != null && this.danhSach[i].getTenTour() != null
                    && this.danhSach[i].getTenTour().toLowerCase().contains(tuKhoa))
            {
                ketQua = Arrays.copyOf(ketQua, index + 1);
                ketQua[index] = this.danhSach[i];
                index++;
            }
        }
        return ketQua;
    }

    public int getMaMoi() {
        int max = 0;
        for (int i = 0; i < this.soLuong; i++) {
            if (this.danhSach[i] != null && this.danhSach[i].getMaTour() > max) {
                max = this.danhSach[i].getMaTour();
            }
        }
        return max + 1;
    }
}
