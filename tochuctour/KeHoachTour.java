package tochuctour;

import java.util.Arrays;
import java.util.Scanner;
import kho.KhoDuLieu;

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
        return chiTietLichTrinh == null ? new ChiTietKeHoachTour[0] : chiTietLichTrinh;
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

    public int soChoConLai(int soVeDaDat) {
        //sovedadat lấy ra từ danh sach ve và tin kiếm bãng mã kế hoạch tour
        if (soVeDaDat <= 0 || soVeDaDat > soLuongVeToiDa) {
            return -1;
        }
        int conLai = Math.abs(soLuongVeToiDa - soVeDaDat);
        return conLai;
    }

    public boolean conDuCho(int soVeDaDat, int soVeCanDat) {
        // mực dích là kiem tra xem số vé cần đát nó có lớn hơn số vé còn lại không sovedadat lấy ra từ danh sach ve và tin kiếm bãng mã kế hoạch tour
        if (soVeCanDat <= 0 || soVeDaDat > soLuongVeToiDa || soVeCanDat <= 0) {
            return false;
        }
        return soVeCanDat <= soLuongVeToiDa - soVeDaDat;
    }

    public boolean themChiTiet(ChiTietKeHoachTour chiTiet) {
        if (chiTiet == null || chiTiet.getNgay() == null || chiTiet.getNgay().trim().isBlank()) {
            return false;
        }

        if (this.chiTietLichTrinh == null) {
            this.chiTietLichTrinh = new ChiTietKeHoachTour[0];
            this.soLuongChiTiet = 0;
        }

        for (int i = 0; i < this.soLuongChiTiet; i++) {
            if (chiTiet.getNgay().equals(this.chiTietLichTrinh[i].getNgay())) {
                return false;
            }
        }

        this.chiTietLichTrinh = Arrays.copyOf(this.chiTietLichTrinh, this.soLuongChiTiet + 1);
        
        this.chiTietLichTrinh[this.soLuongChiTiet] = chiTiet;

        this.soLuongChiTiet++;

        return true;
    }

    public boolean doiNgayChiTiet(String ngayCu , String ngayMoi){
        if(ngayCu == null || ngayMoi == null ){
            return false;
        }
                // Kiểm tra xem ngày mới đã bị trùng với chi tiết nào khác chưa
        for (int i = 0 ; i < this.soLuongChiTiet ; i++){
            if(this.chiTietLichTrinh[i].getNgay().equals(ngayMoi)){
                return false;
            }
        }
        // Tìm ngày cũ để cập nhật sang ngày mới
        for (int i = 0 ; i < this.soLuongChiTiet ; i++){
            if(this.chiTietLichTrinh[i].getNgay().equals(ngayCu)){
                this.chiTietLichTrinh[i].setNgay(ngayMoi);
            }
        }
        return true;
    }

    public boolean xoaChiTiet(String ngay){
        if(ngay == null || this.chiTietLichTrinh == null){
            return false;
        }
        int point = -1;
        for(int i = 0 ; i < this.soLuongChiTiet ; i++){
            if(this.chiTietLichTrinh[i].getNgay().equals(ngay)){
                point = i;
                break;
            }
        }
        if(point == -1){
            return false;
        }
        for(int i = point ; i < this.soLuongChiTiet - 1; i++){
            this.chiTietLichTrinh[i] = this.chiTietLichTrinh[i+1];
        }

        this.chiTietLichTrinh=Arrays.copyOf(this.chiTietLichTrinh, this.soLuongChiTiet - 1);
        this.soLuongChiTiet--;
        return true;
    }

    public ChiTietKeHoachTour timChiTietTheoNgay(String ngay){
        if(ngay == null || this.chiTietLichTrinh == null){
            return null;
        }

        for(int i = 0 ; i < this.soLuongChiTiet ; i++){
            if(this.chiTietLichTrinh[i].getNgay().equals(ngay)){
                return this.chiTietLichTrinh[i];
            }
        }
        return null;
    }

    public long tinhTongChiPhi(int soKhach){
        if(soKhach <= 0){
            return -1;
        }

        long result = 0;

    for(int i = 0 ; i < soLuongChiTiet ; i++){
        result += soKhach * chiTietLichTrinh[i].tinhChiPhiNgay(soKhach);
        }

        return result;
    }

    public void xuat(){
        String tentour = kho.KhoDuLieu.getDanhSachTour().timTheoMa(maTour).getTenTour();
        String tenNhanvien = kho.KhoDuLieu.getDanhSachNhanVien().timTheoMa(maNhanVien).getHoTen();
        System.out.printf("%-18s | %-26s | %-18s | %-12s | %-12s | %-18s%n", this.maKeHoachTour, tentour, tenNhanvien, this.ngayDi, this.ngayVe, this.soLuongVeToiDa);
    }

    public void nhap(){
        Scanner in = KhoDuLieu.getScanner();
        System.out.print("Nhập mã kế hoạch tour: ");
        this.maKeHoachTour = in.nextInt();
        in.nextLine();
        System.out.print("Nhập mã tour: ");
        this.maTour = in.nextInt();
        in.nextLine();
        System.out.print("Nhập mã nhân viên: ");
        this.maNhanVien = in.nextInt();
        in.nextLine();
        System.out.print("Nhập Ngày đi (trình bày như sau 2026-11-23): ");
        this.ngayDi = in.nextLine();
        System.out.print("Nhập Ngày về (trình bày như sau 2026-11-23): ");
        this.ngayVe = in.nextLine();
        System.out.print("Nhập số lượng vé tối đa: ");
        this.soLuongVeToiDa = in.nextInt();
        in.nextLine();
    }
}
