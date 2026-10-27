package tochuctour;

import java.lang.reflect.Array;
import java.util.Arrays;

public class KeHoachTour {
    private int maKeHoachTour;
    private int maTour;
    private int maNhanVien;
    private String ngayDi;
    private String ngayVe;
    private int soLuongVeToiDa;
    private ChiTietKeHoachTour[] chiTietLichTrinh;
    private int soLuongChiTiet;

    public KeHoachTour() {
    }

    public KeHoachTour(int maKeHoachTour, int maTour, int maNhanVien, String ngayDi, String ngayVe, int soLuongVeToiDa) {
        this.maKeHoachTour = maKeHoachTour;
        this.maTour = maTour;
        this.maNhanVien = maNhanVien;
        this.ngayDi = ngayDi;
        this.ngayVe = ngayVe;
        this.soLuongVeToiDa = soLuongVeToiDa;
    }

    public KeHoachTour(int maKeHoachTour, int maTour, int maNhanVien, String ngayDi, String ngayVe, int soLuongVeToiDa, ChiTietKeHoachTour[] chiTietLichTrinh, int soLuongChiTiet) {
        this.maKeHoachTour = maKeHoachTour;
        this.maTour = maTour;
        this.maNhanVien = maNhanVien;
        this.ngayDi = ngayDi;
        this.ngayVe = ngayVe;
        this.soLuongVeToiDa = soLuongVeToiDa;
        this.chiTietLichTrinh = chiTietLichTrinh;
        this.soLuongChiTiet = soLuongChiTiet;
    }

    public int getMaKeHoachTour() {
        return maKeHoachTour;
    }

    public void setMaKeHoachTour(int maKeHoachTour) {
        this.maKeHoachTour = maKeHoachTour;
    }

    public int getMaTour() {
        return maTour;
    }

    public void setMaTour(int maTour) {
        this.maTour = maTour;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getNgayDi() {
        return ngayDi;
    }

    public void setNgayDi(String ngayDi) {
        this.ngayDi = ngayDi;
    }

    public String getNgayVe() {
        return ngayVe;
    }

    public void setNgayVe(String ngayVe) {
        this.ngayVe = ngayVe;
    }

    public int getSoLuongVeToiDa() {
        return soLuongVeToiDa;
    }

    public void setSoLuongVeToiDa(int soLuongVeToiDa) {
        this.soLuongVeToiDa = soLuongVeToiDa;
    }

    public ChiTietKeHoachTour[] getChiTietLichTrinh() {
        return chiTietLichTrinh;
    }

    public void setChiTietLichTrinh(ChiTietKeHoachTour[] chiTietLichTrinh) {
        this.chiTietLichTrinh = chiTietLichTrinh;
    }

    public int getSoLuongChiTiet() {
        return soLuongChiTiet;
    }

    public void setSoLuongChiTiet(int soLuongChiTiet) {
        this.soLuongChiTiet = soLuongChiTiet;
    }

    boolean capNhatThongTin(int maNhanVien, String ngayDi, String ngayVe, int soLuongVeToiDa){
// Chức năng dùng để cập nhật thông tin của một chuyến tour
    if(
        maNhanVien <= 0 || ngayDi == null || ngayVe == null || soLuongVeToiDa <=0
    ){return false;}

    this.maNhanVien = maNhanVien;
    this.ngayDi = ngayDi;
    this.ngayVe = ngayVe;
    this.soLuongVeToiDa = soLuongVeToiDa;
    return true;
    }

    int soChoConLai(int soVeDaDat){
        //sovedadat lấy ra từ danh sach ve và tin kiếm bãng mã kế hoạch tour
        if(soVeDaDat <= 0 || soVeDaDat > soLuongVeToiDa){
            return -1;
        }
        int conLai = Math.abs(soLuongVeToiDa - soVeDaDat);
        return conLai;
    }

    boolean conDuCho(int soVeDaDat , int soVeCanDat){
        // mực dích là kiem tra xem số vé cần đát nó có lớn hơn số vé còn lại không sovedadat lấy ra từ danh sach ve và tin kiếm bãng mã kế hoạch tour
        if(soVeCanDat <=  0 || soVeDaDat > soLuongVeToiDa || soVeCanDat <= 0){
            return false;
        }
        return soVeCanDat <= soLuongVeToiDa - soVeDaDat;
    }

    boolean themChiTiet(ChiTietKeHoachTour chiTiet){
        if(chitiet == null || chiTiet.getNgay() == null || chiTiet.getNgay().trim().isBlank()){
            return false;
        }

        
        for(int i = 0 ; i < this.soLuongChiTiet ; i++){
            if(chiTiet.getNgay().equals(this.chiTietLichTrinh[i].getNgay())){
                return false;
            }
        }
        
        this.chiTietLichTrinh = Arrays.copyOf(this.chiTietLichTrinh, this.soLuongChiTiet + 1);

        this.chiTietLichTrinh[this.soLuongChiTiet] = chiTiet;

        this.soLuongChiTiet++;

        return true;
    }
}
