package nguoidung;
import danhsach.DanhSach;
import java.util.Arrays;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
public class DanhSachHanhKhach implements DanhSach<HanhKhach>{
    private HanhKhach[] danhSach;
    private int soLuong;
    private Scanner sc = new Scanner(System.in);
    public DanhSachHanhKhach(){
        danhSach=new HanhKhach[0];
        soLuong=0;
    }
    @Override 
    public boolean them(HanhKhach hanhKhach){
        if(hanhKhach==null)return false;
        if(danhSach==null){
            danhSach=new HanhKhach[0];
            soLuong=0;
        }
        for(int i=0;i<soLuong;i++){
            if(danhSach[i]!=null && danhSach[i].getMaHanhKhach()==hanhKhach.getMaHanhKhach())return false;
        }
        danhSach=Arrays.copyOf(danhSach,soLuong+1);
        danhSach[soLuong]=hanhKhach;
        soLuong++;
        return true;
    }
    @Override 
    public boolean them(){
        System.out.print("Nhap ma hanh khach: ");
        int maHanhKhach=Integer.parseInt(sc.nextLine().trim());
        System.out.print("Nhap ho va ten: ");
        String hoTen=sc.nextLine().trim();
        System.out.print("Nhap ngay sinh (vd:2001-08-25): ");
        String ngaySinh=sc.nextLine().trim();
        System.out.print("Nhap gioi tinh (0: Nu, 1: Nam): ");
        int gioiTinh=Integer.parseInt(sc.nextLine().trim());
        System.out.print("Nhap so dien thoai: ");
        String soDienThoai=sc.nextLine().trim();
        HanhKhach hk=new HanhKhach(maHanhKhach,hoTen,ngaySinh,gioiTinh,soDienThoai);
        return them(hk);
    }
    
}