package nguoidung;
import java.util.Arrays;
import danhsach.DanhSach;
public class DanhSachHanhKhach implements DanhSach<HanhKhach>{
    private HanhKhach[] danhSach;
    private int soLuong;
    public DanhSachHanhKhach(){
        danhSach=new HanhKhach[100];
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
    public HanhKhach timTheoMa(int maHanhKhach){
        for(int i=0;i<soLuong;i++){
            if(danhSach[i].getMaHanhKhach()==maHanhKhach)
                return danhSach[i];
        }
        return null;
    }
    @Override 
    public HanhKhach[] timTheoTen(String hoTen){
        int dem=0;
        for(int i=0;i<soLuong;i++){
            if(danhSach[i].getHoTen().equalsIgnoreCase(hoTen.trim()))dem++;
        }
        HanhKhach[] ketQua=new HanhKhach[dem];
        int index=0;
        for(int i=0;i<soLuong;i++){
            if(danhSach[i].getHoTen().equalsIgnoreCase(hoTen.trim()))
                ketQua[index++]=danhSach[i];
        }
        return ketQua;
    }
    @Override 
    public boolean capNhat(HanhKhach hanhKhach){
        if(hanhKhach == null)return false;
        for(int i=0;i<soLuong;i++){
            if(danhSach[i].getMaHanhKhach()==hanhKhach.getMaHanhKhach()){
                danhSach[i]=hanhKhach;
                return true;
            }
        }
        return false;
    }
    @Override 
    public boolean xoaTheoMa(int maHanhKhach){
        for(int i=0;i<soLuong;i++){
            if(danhSach[i].getMaHanhKhach()==maHanhKhach){
                for(int j=i;j<soLuong-1;j++){
                    danhSach[j]=danhSach[j+1];
                }
                danhSach[--soLuong]=null;
                return true;
            }
        }
        return false;
    }
    
}