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
public class DanhSachNhanVien implements DanhSach<NhanVien>{
    private NhanVien[] danhSach;
    private int soLuong;
<<<<<<< HEAD
    private Scanner sc=new Scanner(System.in);
    public DanhSachNhanVien(){
        danhSach=new NhanVien[0];
        soLuong=0;
=======
    private Scanner sc = KhoDuLieu.getScanner();

    public DanhSachNhanVien() {
        danhSach = new NhanVien[0];
        soLuong = 0;
>>>>>>> b47a561c57a7eaeec0e2494d9a547643aaa4900a
    }
    public NhanVien[] getDanhSach(){return danhSach;}
    public void setDanhSach(NhanVien[] danhSach){this.danhSach=danhSach;}
    public int getSoLuong(){return soLuong;}
    public void setSoLuong(int soLuong){this.soLuong=soLuong;}
    @Override 
    public boolean them(NhanVien nhanVien){
        if(nhanVien==null)return false;
        if(danhSach==null){
            danhSach=new NhanVien[0];
            soLuong=0;
        }
        for(int i=0;i<soLuong;i++){
            if(danhSach[i]!=null && danhSach[i].getMaNhanVien()==nhanVien.getMaNhanVien())return false;
        }
        danhSach=Arrays.copyOf(danhSach,soLuong+1);
        danhSach[soLuong]=nhanVien;
        soLuong++;
        return true;
    }
    @Override 
    public boolean them(){
        NhanVien nv = new NhanVien();
        nv.nhap();
        return them(nv);
    }
    @Override
    public void nhap() {
        System.out.print("Nhap vao so luong nhan vien: ");
        this.soLuong = sc.nextInt();
        sc.nextLine(); 
        
        this.danhSach = new NhanVien[this.soLuong];
        for (int i = 0; i < this.soLuong; i++) {
            System.out.println("Nhap thong tin nhan vien thu " + (i + 1) + ":");
            this.danhSach[i] = new NhanVien(); 
            this.danhSach[i].nhap();
        }
    }
    @Override
    public void xuat() {
        if (this.danhSach == null) {
            return;
        }
        System.out.println("Danh sach nhan vien:");
        for (int i = 0; i < this.soLuong; i++) {
                this.danhSach[i].xuat();
        }
    }
    @Override 
    public NhanVien timTheoMa(int maNhanVien){
        if(danhSach==null || soLuong<=0 || maNhanVien<=0)return null;
        for(int i=0;i<soLuong;i++){
            if(danhSach[i]!=null && danhSach[i].getMaNhanVien()==maNhanVien){
                return danhSach[i];
            }
        }
        return null;
    }
    @Override 
    public boolean suaTheoMa(int maNhanVien){
        NhanVien nv=timTheoMa(maNhanVien);
        if(nv==null){
            System.out.println("Khong tim thay nhan vien co ma: "+maNhanVien);
            return false;
        }
        System.out.println("Thong tin nhan vien hien tai: ");
        nv.xuat();
        System.out.print("Chinh Ho ten: ");
        String hoTen=sc.nextLine();
        if(!hoTen.isBlank()){nv.setHoTen(hoTen);}
        System.out.print("Chinh Ngay sinh (vd:2007-11-20): ");
        String ngaySinh=sc.nextLine();
        if(!ngaySinh.isBlank()){nv.setNgaySinh(ngaySinh);}
        System.out.print("Chinh Gioi tinh (0: Nu,1: Nam): ");
        String gioiTinhStr=sc.nextLine();
        if(!gioiTinhStr.isBlank()){
            int gioiTinh=Integer.parseInt(gioiTinhStr.trim());
            nv.setGioiTinh(gioiTinh);
        }
        System.out.print("Chinh So dien thoai: ");
        String soDienThoai=sc.nextLine();
        if(!soDienThoai.isBlank()){nv.setSoDienThoai(soDienThoai);}
        System.out.println("Thong tin nhan vien sau khi cap nhat: ");
        nv.xuat();
        return true;
    }
    @Override 
    public boolean xoaTheoMa(int maNhanVien){
        if(danhSach==null || soLuong<=0 || maNhanVien<=0)return false;
        for(int i=0;i<soLuong;i++){
            if(danhSach[i]!=null && danhSach[i].getMaNhanVien()==maNhanVien){
                for(int j=i;j<soLuong-1;j++){
                    danhSach[j]=danhSach[j+1];
                }
                danhSach[--soLuong]=null;
                return true;
            }
        }
        return false;
    }
    @Override
    public NhanVien[] layTatCa(){
        NhanVien[] kq=new NhanVien[soLuong];
        System.arraycopy(danhSach,0,kq,0,soLuong);
        return kq;
    }
    @Override 
    public boolean docFile(String duongDan){
        danhSach=new NhanVien[0];
        soLuong=0;
        try(BufferedReader reader=new BufferedReader(new FileReader(duongDan))){
            String temp;
            while((temp=reader.readLine())!=null){
                if(temp.isBlank())continue;
                String[] parts=temp.split("\\|");
                if(parts.length>=5){
                    NhanVien nv=new NhanVien(
                        Integer.parseInt(parts[0].trim()),
                        parts[1].trim(),
                        parts[2].trim(),
                        Integer.parseInt(parts[3].trim()),
                        parts[4].trim()
                    );
                    them(nv);
                }
            }
            return true;
        }catch(Exception e){
            return false;
        }
    }
    @Override 
    public boolean ghiFile(String duongDan){
        if(danhSach==null)return false;
        try(BufferedWriter writer=new BufferedWriter(new FileWriter(duongDan))){
            for(int i=0;i<soLuong;i++){
                if(danhSach[i]==null)continue;
                String temp=danhSach[i].getMaNhanVien()+"|"+
                danhSach[i].getHoTen()+"|"+
                danhSach[i].getNgaySinh()+"|"+
                danhSach[i].getGioiTinh()+"|"+
                danhSach[i].getSoDienThoai();
                writer.write(temp);
                writer.newLine();
            }
            return true;
        }catch(Exception e){
            return false;
        }
    }
    public NhanVien[] timTheoTen(String hoTen){
        if(danhSach==null || soLuong<=0 || hoTen==null || hoTen.isBlank())return new NhanVien[0];
        NhanVien[] ketQua=new NhanVien[0];
        int index=0;
        for(int i=0;i<soLuong;i++){
            if(danhSach[i]!=null && danhSach[i].getHoTen()!=null && danhSach[i].getHoTen().equalsIgnoreCase(hoTen)){
                ketQua=Arrays.copyOf(ketQua,index+1);
                ketQua[index]=danhSach[i];
                index++;
            }
        }
        return ketQua;
    }
}