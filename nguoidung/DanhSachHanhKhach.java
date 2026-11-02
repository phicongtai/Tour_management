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
        if (danhSach==null || soLuong <= 0 || maHanhKhach <= 0) {
            return null;
        }
        for(int i=0;i<soLuong;i++){
            if(danhSach[i]!=null && danhSach[i].getMaHanhKhach()==maHanhKhach)return danhSach[i];
        }
        return null;
    }
    public HanhKhach[] timTheoTen(String hoTen){
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
    @Override 
    public boolean capNhat(HanhKhach hanhKhach){
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
    public boolean xoaTheoMa(int maHanhKhach){
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
    public HanhKhach[] layTatCa(){
        HanhKhach[] kq=new HanhKhach[soLuong];
        System.arraycopy(danhSach,0,kq,0,soLuong);
        return kq;
    }
    @Override 
    public String[] xuatDuLieu(){
        String[] dongTXT=new String[soLuong];
        for(int i=0;i<soLuong;i++){
            if(danhSach[i]!=null){
                dongTXT[i]=danhSach[i].toString();
            }
        }
        return dongTXT;
    }
    @Override 
    public boolean nhapDuLieu(String[] dongDuLieu ){
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
                HanhKhach hk=new HanhKhach(maHanhKhach,hoTen,ngaySinh,gioiTinh,soDienThoai);
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