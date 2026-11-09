package nguoidung;

import java.nio.file.Path;
import java.util.Arrays;
import danhsach.DanhSach;

public class DanhSachHanhKhach implements DanhSach<HanhKhach> {
    private HanhKhach[] danhSach;
    private int soLuong;

    // Constructors
    public DanhSachHanhKhach() {
        danhSach = new HanhKhach[100];
        soLuong = 0;
    }

    // Methods @Override
    @Override 
    public boolean them(HanhKhach hanhKhach) {
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
    public boolean xoaTheoMa(int maHanhKhach) {
        if(danhSach==null || soLuong <= 0 || maHanhKhach <= 0)return false;
        for(int i=0;i<soLuong;i++){
            if(danhSach[i]!=null && danhSach[i].getMaHanhKhach()==maHanhKhach){
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
    public boolean capNhat(HanhKhach hanhKhach) {
        if(danhSach==null || soLuong <= 0 || hanhKhach==null || hanhKhach.getMaHanhKhach() <= 0)return false;
        for(int i=0;i<soLuong;i++){
            if(danhSach[i]!=null && danhSach[i].getMaHanhKhach()==hanhKhach.getMaHanhKhach()){
                danhSach[i]=hanhKhach;
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
    public HanhKhach timTheoMa(int maHanhKhach) {
        if (danhSach==null || soLuong <= 0 || maHanhKhach <= 0) {
            return null;
        }
        for(int i=0;i<soLuong;i++){
            if(danhSach[i]!=null && danhSach[i].getMaHanhKhach()==maHanhKhach)return danhSach[i];
        }
        return null;
    }

    @Override 
    public HanhKhach[] layTatCa() {
        HanhKhach[] kq=new HanhKhach[soLuong];
        System.arraycopy(danhSach,0,kq,0,soLuong);
        return kq;
    }

    @Override
    public boolean docFile(Path path) {
        return false;
    }

    @Override
    public boolean ghiFile(Path path) {
        return false;
    }

    // Methods còn lại
    public HanhKhach[] timTheoTen(String hoTen) {
        if(danhSach==null || soLuong <= 0 || hoTen.isBlank())return new HanhKhach[0];
        HanhKhach[] ketQua=new HanhKhach[0];
        int index=0;
        for(int i=0;i<soLuong;i++){
            if(danhSach[i]!=null && danhSach[i].getHoTen().equalsIgnoreCase(hoTen)){
                ketQua = Arrays.copyOf(ketQua, index + 1);
                ketQua[index] = danhSach[i];
                index++;
            }
        }
        return ketQua;
    }
}
