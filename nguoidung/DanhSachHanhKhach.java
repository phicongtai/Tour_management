package nguoidung;

import danhsach.DanhSach;

public class DanhSachHanhKhach implements DanhSach<HanhKhach>{
    private HanhKhach[] danhSach;
    private int soLuong;
    public DanhSachHanhKhach(){
        this.danhSach=new HanhKhach[100];
        this.soLuong=0;
    }
    @Override 
    public boolean them(HanhKhach hanhKhach){
        if(hanhKhach==null||soLuong>=danhSach.length)return false;
        if(timTheoMa(hanhKhach.getMaHanhKhach())!=null)return false;
        danhSach[soLuong++]=hanhKhach;
        return true;
    }
    @Override 
    public HanhKhach timTheoMa(int maHanhKhach){
        for(int i=0;i<soLuong;i++){
            if(danhSach[i].getMaHanhKhach()==maHanhKhach)return danhSach[i];
        }
        return null;
    }
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
        if(hanhKhach==null)return false;
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
    @Override 
    public HanhKhach[] layTatCa(){
        HanhKhach[] kq=new HanhKhach[soLuong];
        System.arraycopy(danhSach,0,kq,0,soLuong);
        return kq;
    }
    @Override 
    public String[] xuatDuLieu(){
        String[] dongTXT=new String[soLuong];
        for(int i=0;i<soLuong;i++){
            dongTXT[i]=danhSach[i].toString();
        }
        return dongTXT;
    }
    @Override 
    public boolean nhapDuLieu(String[] dongDuLieu) {
        if(dongDuLieu==null)return false;
        HanhKhach[] temp=new HanhKhach[dongDuLieu.length];
        int count=0;
        for(String line : dongDuLieu){
            String[] parts=line.split(",");
        if(parts.length<5)return false;
        try{
            int maHanhKhach=Integer.parseInt(parts[0].trim());
            String hoTen=parts[1].trim();
            String ngaySinh=parts[2].trim();
            int gioiTinh=Integer.parseInt(parts[3].trim());
            String soDienThoai=parts[4].trim();
            HanhKhach hk= new HanhKhach(maHanhKhach,hoTen,ngaySinh,gioiTinh,soDienThoai);
                temp[count++]=hk;
            }catch(Exception e){
                return false;
            }
        }
        danhSach=temp;
        soLuong=count;
        return true;
    }
}

