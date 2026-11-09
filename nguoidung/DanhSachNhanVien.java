package nguoidung;
import java.nio.file.Path;
import java.util.Arrays;
import danhsach.DanhSach;

public class DanhSachNhanVien implements DanhSach<NhanVien> {
    private NhanVien[] danhSach;
    private int soLuong;

    public DanhSachNhanVien() {
        danhSach = new NhanVien[100];
        soLuong = 0;
    }
    @Override 
    public boolean them(NhanVien nhanVien) {
        if(nhanVien==null)return false;
        if(danhSach==null){
        danhSach=new NhanVien[0];
        soLuong=0;
        }
        for(int i=0;i<soLuong;i++){
            if(danhSach[i]!=null && danhSach[i].getMaNhanVien()==nhanVien.getMaNhanVien()){
                return false;
            }
        }
        danhSach=Arrays.copyOf(danhSach,soLuong+1);
        danhSach[soLuong]=nhanVien;
        soLuong++;
        return true;
    }
    @Override 
    public NhanVien timTheoMa(int maNhanVien) {
        if(danhSach==null || soLuong <= 0 || maNhanVien <= 0) {
            return null;
        }
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i]!=null && danhSach[i].getMaNhanVien() == maNhanVien)return danhSach[i];
        }
        return null;
    }
    public NhanVien[] timTheoTen(String hoTen) {
        if (danhSach == null || soLuong <= 0 || hoTen.isBlank())return new NhanVien[0];
        NhanVien[] ketQua = new NhanVien[0];
        int index = 0;
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i]!=null && danhSach[i].getHoTen().equalsIgnoreCase(hoTen.trim())) {
                ketQua = Arrays.copyOf(ketQua, index + 1);
                ketQua[index] = danhSach[i];
                index++;
            }
        }
        return ketQua;
    }
    @Override 
    public boolean capNhat(NhanVien nhanVien) {
        if (danhSach == null || soLuong <= 0 || nhanVien == null || nhanVien.getMaNhanVien() <= 0) return false;
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i]!=null && danhSach[i].getMaNhanVien() == nhanVien.getMaNhanVien()) {
                danhSach[i] = nhanVien;
                return true;
            }
        }
        return false;
    }
    @Override 
    public boolean xoaTheoMa(int maNhanVien) {
        if (danhSach == null || soLuong <= 0 || maNhanVien <= 0) return false;
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i]!=null && danhSach[i].getMaNhanVien() == maNhanVien) {
                // Dồn mảng
                for (int j = i; j < soLuong - 1; j++) {
                    danhSach[j] = danhSach[j + 1];
                }
                danhSach[--soLuong] = null;
                return true;
            }
        }
        return false;
    }
    @Override 
    public NhanVien[] layTatCa() {
        NhanVien[] kq = new NhanVien[soLuong];
        System.arraycopy(danhSach, 0, kq, 0, soLuong);
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
}
