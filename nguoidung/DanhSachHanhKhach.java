package nguoidung;

import danhsach.DanhSach;
import java.util.Scanner;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class DanhSachHanhKhach implements DanhSach<HanhKhach> {
    private HanhKhach[] danhSach;
    private int soLuong;
    private static final Scanner sc = new Scanner(System.in);

    public DanhSachHanhKhach() {
        this.danhSach = new HanhKhach[0];
        this.soLuong = 0;
    }

    public HanhKhach[] getDanhSach() { 
        return this.danhSach; 
    }
    
    public void setDanhSach(HanhKhach[] danhSach) { 
        this.danhSach = danhSach; 
    }
    
    public int getSoLuong() { 
        return this.soLuong; 
    }
    
    public void setSoLuong(int soLuong) { 
        this.soLuong = soLuong; 
    }

    @Override 
    public boolean them(HanhKhach hanhKhach) {
        if (hanhKhach == null) return false;
        if (this.danhSach == null) {
            this.danhSach = new HanhKhach[0];
            this.soLuong = 0;
        }
        for (int i = 0; i < this.soLuong; i++) {
            if (this.danhSach[i] != null && this.danhSach[i].getMaHanhKhach() == hanhKhach.getMaHanhKhach()) {
                return false;
            }
        }
        this.danhSach = Arrays.copyOf(this.danhSach, this.soLuong + 1);
        this.danhSach[this.soLuong] = hanhKhach;
        this.soLuong++;
        return true;
    }

    @Override 
    public boolean them() {
        if(this.danhSach == null){
            this.danhSach = new HanhKhach[0];
            this.soLuong = 0;
        }

        this.danhSach = Arrays.copyOf(this.danhSach, this.soLuong + 1);
        this.danhSach[this.soLuong] = new HanhKhach();
        this.danhSach[this.soLuong].nhap();
        this.soLuong++;
        return true;
    }

    @Override 
    public void nhap(){
        System.out.print("Nhap so luong hanh khach: ");
        this.soLuong=this.sc.nextInt();
        this.sc.nextLine();
        this.danhSach=new HanhKhach[this.soLuong];
        for(int i=0;i<this.soLuong;i++){
            System.out.println("Nhap thong tin hanh khach thu "+(i+1)+":");
            this.danhSach[i]=new HanhKhach();
            this.danhSach[i].nhap();
        }
    }
    @Override 
    public void xuat(){
        if(this.danhSach==null || this.soLuong==0){
            System.out.println("Danh sach hanh khach trong!");
            return;
        }
        for(int i=0;i<this.soLuong;i++){
            if(this.danhSach[i]!=null){
                this.danhSach[i].xuat();
            }
        }
    }
    @Override 
    public HanhKhach timTheoMa(int maHanhKhach) {
        if (this.danhSach == null || this.soLuong <= 0 || maHanhKhach <= 0) return null;
        for (int i = 0; i < this.soLuong; i++) {
            if (this.danhSach[i] != null && this.danhSach[i].getMaHanhKhach() == maHanhKhach) {
                return this.danhSach[i];
            }
        }
        return null;
    }

    @Override 
    public boolean suaTheoMa(int maHanhKhach) {
        HanhKhach hk = this.timTheoMa(maHanhKhach);
        if (hk == null) {
            System.out.println("Khong tim thay hanh khach co ma: " + maHanhKhach);
            return false;
        }
        System.out.println("Thong tin hanh khach hien tai: ");
        hk.xuat();
        
        System.out.print("Chinh Ho: ");
        String ho = this.sc.nextLine();
        if (!ho.isBlank()) { 
            hk.setHo(ho); 
        }
        
        System.out.print("Chinh Ten: ");
        String ten = this.sc.nextLine();
        if (!ten.isBlank()) { 
            hk.setTen(ten); 
        }
        
        System.out.print("Chinh Ngay sinh (vd:2007-11-20): ");
        String ngaySinh = this.sc.nextLine();
        if (!ngaySinh.isBlank()) { 
            hk.setNgaySinh(ngaySinh); 
        }
        
        System.out.print("Chinh Gioi tinh (0: Nu, 1: Nam): ");
        String gioiTinhStr = this.sc.nextLine();
        if (!gioiTinhStr.isBlank()) {
            int gioiTinh = Integer.parseInt(gioiTinhStr.trim());
            hk.setGioiTinh(gioiTinh);
        }
        
        System.out.print("Chinh So dien thoai: ");
        String soDienThoai = this.sc.nextLine();
        if (!soDienThoai.isBlank()) { 
            hk.setSoDienThoai(soDienThoai); 
        }
        
        System.out.println("Thong tin hanh khach sau khi cap nhat: ");
        hk.xuat();
        return true;
    }
    @Override 
    public boolean xoaTheoMa(int maHanhKhach){
        if(danhSach==null ||this.soLuong<=0 || maHanhKhach<=0)return false;
        int index=-1;
        for (int i = 0; i < this.soLuong; i++) {
            if (this.danhSach[i] != null && this.danhSach[i].getMaHanhKhach() == maHanhKhach) {
                index = i;
                break;
            }
        }
        if(index==-1)return false;
        for(int i=index;i<soLuong-1;i++){
            this.danhSach[i]=danhSach[i+1];
        }
        this.danhSach=Arrays.copyOf(danhSach,soLuong-1);
        soLuong--;
        return true;
    }

    @Override
    public HanhKhach[] layTatCa() {
        HanhKhach[] kq = new HanhKhach[this.soLuong];
        System.arraycopy(this.danhSach, 0, kq, 0, this.soLuong);
        return kq;
    }

    @Override 
    public boolean docFile(String duongDan){
        this.danhSach=new HanhKhach[0];
        this.soLuong=0;
        try(BufferedReader reader=new BufferedReader(new FileReader(duongDan))){
            String temp;
            while((temp=reader.readLine())!=null){
                if(temp.isBlank())continue;
                String[] parts=temp.split("\\|");
                if(parts.length>=6){
                    HanhKhach hk=new HanhKhach(
                        Integer.parseInt(parts[0].trim()),
                        parts[1].trim(),
                        parts[2].trim(),
                        parts[3].trim(),
                        Integer.parseInt(parts[4].trim()),
                        parts[5].trim()
                    );
                    this.them(hk);
                }else if(parts.length>=5){
                    // Ho tro doc file cu (chua tach ho va ten)
                    HanhKhach hk=new HanhKhach();
                    hk.setMaHanhKhach(Integer.parseInt(parts[0].trim()));
                    hk.setHoTen(parts[1].trim());
                    hk.setNgaySinh(parts[2].trim());
                    hk.setGioiTinh(Integer.parseInt(parts[3].trim()));
                    hk.setSoDienThoai(parts[4].trim());
                    this.them(hk);
                }
            }
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override 
    public boolean ghiFile(String duongDan) {
        if (this.danhSach == null) return false;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(duongDan))) {
            for (int i = 0; i < this.soLuong; i++) {
                if (this.danhSach[i] == null) continue;
                String temp = this.danhSach[i].getMaHanhKhach() + "|" +
                              this.danhSach[i].getHo() + "|" +
                              this.danhSach[i].getTen() + "|" +
                              this.danhSach[i].getNgaySinh() + "|" +
                              this.danhSach[i].getGioiTinh() + "|" +
                              this.danhSach[i].getSoDienThoai();
                writer.write(temp);
                writer.newLine();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public HanhKhach[] timTheoTen(String tenCanTim) {
        if (this.danhSach == null || this.soLuong <= 0 || tenCanTim == null || tenCanTim.isBlank()) {
            return new HanhKhach[0];
        }
        String khoa = tenCanTim.trim();
        HanhKhach[] ketQua = new HanhKhach[0];
        int index = 0;
        for (int i = 0; i < this.soLuong; i++) {
            if (this.danhSach[i] != null && this.danhSach[i].getTen() != null
                    && (this.danhSach[i].getTen().equalsIgnoreCase(khoa)
                        || this.danhSach[i].getHoTen().equalsIgnoreCase(khoa))) {
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
            if (this.danhSach[i] != null && this.danhSach[i].getMaHanhKhach() > max) {
                max = this.danhSach[i].getMaHanhKhach();
            }
        }
        return max + 1;
    }
}