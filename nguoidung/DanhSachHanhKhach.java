package nguoidung;
import danhsach.DanhSach;
<<<<<<< HEAD
=======
import kho.KhoDuLieu;
import java.util.Scanner;
>>>>>>> b47a561c57a7eaeec0e2494d9a547643aaa4900a
import java.util.Arrays;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
public class DanhSachHanhKhach implements DanhSach<HanhKhach>{
    private HanhKhach[] danhSach;
    private int soLuong;
<<<<<<< HEAD
    private Scanner sc = new Scanner(System.in);
=======
    private Scanner sc = KhoDuLieu.getScanner();

>>>>>>> b47a561c57a7eaeec0e2494d9a547643aaa4900a
    public DanhSachHanhKhach(){
        danhSach = new HanhKhach[0];
        soLuong = 0;
    }
    public HanhKhach[] getDanhSach(){ return danhSach; }
    public void setDanhSach(HanhKhach[] danhSach){ this.danhSach = danhSach; }
    public int getSoLuong(){ return soLuong; }
    public void setSoLuong(int soLuong){ this.soLuong = soLuong; }
    @Override 
    public boolean them(HanhKhach hanhKhach){
        if(hanhKhach == null) return false;
        if(danhSach == null){
            danhSach = new HanhKhach[0];
            soLuong = 0;
        }
        for(int i = 0; i < soLuong; i++){
            if(danhSach[i] != null && danhSach[i].getMaHanhKhach() == hanhKhach.getMaHanhKhach()) return false;
        }
        danhSach = Arrays.copyOf(danhSach, soLuong + 1);
        danhSach[soLuong] = hanhKhach;
        soLuong++;
        return true;
    }
    @Override 
    public boolean them(){
        HanhKhach hk = new HanhKhach();
        hk.nhap();
        return them(hk);
    }
    @Override
    public void nhap() {
        System.out.print("Nhap vao so luong hanh khach: ");
        this.soLuong = sc.nextInt();
        sc.nextLine(); 
        this.danhSach = new HanhKhach[this.soLuong];
        for (int i = 0; i < this.soLuong; i++) {
            System.out.println("Nhap thong tin hanh khach thu " + (i + 1) + ":");
            this.danhSach[i] = new HanhKhach(); 
            this.danhSach[i].nhap();
        }
    }
    @Override
    public void xuat() {
        if (this.danhSach == null) {
            return;
        }
        System.out.println("Danh sach hanh khach:");
        for (int i = 0; i < this.soLuong; i++) {
            this.danhSach[i].xuat();
        }
    }
    @Override 
    public HanhKhach timTheoMa(int maHanhKhach){
        if(danhSach == null || soLuong <= 0 || maHanhKhach <= 0) return null;
        for(int i = 0; i < soLuong; i++){
            if(danhSach[i] != null && danhSach[i].getMaHanhKhach() == maHanhKhach){
                return danhSach[i];
            }
        }
        return null;
    }
    @Override 
    public boolean suaTheoMa(int maHanhKhach){
        HanhKhach hk = timTheoMa(maHanhKhach);
        if(hk == null){
            System.out.println("Khong tim thay hanh khach co ma: " + maHanhKhach);
            return false;
        }
        System.out.println("Thong tin hanh khach hien tai: ");
        hk.xuat();
        System.out.print("Chinh Ho ten: ");
        String hoTen = sc.nextLine();
        if(!hoTen.isBlank()){ hk.setHoTen(hoTen); }
        System.out.print("Chinh Ngay sinh (vd:2007-11-20): ");
        String ngaySinh = sc.nextLine();
        if(!ngaySinh.isBlank()){ hk.setNgaySinh(ngaySinh); }
        System.out.print("Chinh Gioi tinh (0: Nu, 1: Nam): ");
        String gioiTinhStr = sc.nextLine();
        if(!gioiTinhStr.isBlank()){
            int gioiTinh = Integer.parseInt(gioiTinhStr.trim());
            hk.setGioiTinh(gioiTinh);
        }
        System.out.print("Chinh So dien thoai: ");
        String soDienThoai = sc.nextLine();
        if(!soDienThoai.isBlank()){ hk.setSoDienThoai(soDienThoai); }
        System.out.println("Thong tin hanh khach sau khi cap nhat: ");
        hk.xuat();
        return true;
    }
    @Override 
    public boolean xoaTheoMa(int maHanhKhach){
        if(danhSach == null || soLuong <= 0 || maHanhKhach <= 0) return false;
        for(int i = 0; i < soLuong; i++){
            if(danhSach[i] != null && danhSach[i].getMaHanhKhach() == maHanhKhach){
                for(int j = i; j < soLuong - 1; j++){
                    danhSach[j] = danhSach[j+1];
                }
                danhSach[--soLuong] = null;
                return true;
            }
        }
        return false;
    }
    @Override
    public HanhKhach[] layTatCa(){
        HanhKhach[] kq = new HanhKhach[soLuong];
        System.arraycopy(danhSach, 0, kq, 0, soLuong);
        return kq;
    }
    @Override 
    public boolean docFile(String duongDan){
        danhSach = new HanhKhach[0];
        soLuong = 0;
        try(BufferedReader reader = new BufferedReader(new FileReader(duongDan))){
            String temp;
            while((temp = reader.readLine()) != null){
                if(temp.isBlank()) continue;
                String[] parts = temp.split("\\|");
                if(parts.length >= 5){
                    HanhKhach hk = new HanhKhach(
                        Integer.parseInt(parts[0].trim()),
                        parts[1].trim(),
                        parts[2].trim(),
                        Integer.parseInt(parts[3].trim()),
                        parts[4].trim()
                    );
                    them(hk);
                }
            }
            return true;
        }catch(Exception e){
            return false;
        }
    }
    @Override 
    public boolean ghiFile(String duongDan){
        if(danhSach == null) return false;
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(duongDan))){
            for(int i = 0; i < soLuong; i++){
                if(danhSach[i] == null) continue;
                String temp = danhSach[i].getMaHanhKhach() + "|" +
                danhSach[i].getHoTen() + "|" +
                danhSach[i].getNgaySinh() + "|" +
                danhSach[i].getGioiTinh() + "|" +
                danhSach[i].getSoDienThoai();
                writer.write(temp);
                writer.newLine();
            }
            return true;
        }catch(Exception e){
            return false;
        }
    }
    public HanhKhach[] timTheoTen(String hoTen){
        if(danhSach == null || soLuong <= 0 || hoTen == null || hoTen.isBlank()) return new HanhKhach[0];
        HanhKhach[] ketQua = new HanhKhach[0];
        int index = 0;
        for(int i = 0; i < soLuong; i++){
            if(danhSach[i] != null && danhSach[i].getHoTen() != null && danhSach[i].getHoTen().equalsIgnoreCase(hoTen)){
                ketQua = Arrays.copyOf(ketQua, index + 1);
                ketQua[index] = danhSach[i];
                index++;
            }
        }
        return ketQua;
    }
}