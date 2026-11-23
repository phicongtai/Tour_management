package tochuctour;

import java.util.Arrays;
import java.util.Scanner;
import kho.KhoDuLieu;
import tour.Tour;

public class KeHoachTour {
    private static final Scanner in = new Scanner(System.in);
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
        //sovedadat lay ra tu danh sach ve va tin kiem bang ma ke hoach tour
        if (soVeDaDat < 0 || soVeDaDat > soLuongVeToiDa) {
            return -1;
        }
        int conLai = Math.abs(this.soLuongVeToiDa - soVeDaDat);
        return conLai;
    }

    public boolean conDuCho(int soVeDaDat, int soVeCanDat) {
        // muc dich la kiem tra xem so ve can dat no co lon hon so ve con lai khong sovedadat lay ra tu danh sach ve va tin kiem bang ma ke hoach tour
        if (soVeCanDat <= 0 || soVeDaDat < 0 || soVeDaDat > this.soLuongVeToiDa) {
            return false;
        }
        return soVeCanDat <= this.soLuongVeToiDa - soVeDaDat;
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
                // Kiem tra xem ngay moi da bi trung voi chi tiet nao khac chua
        for (int i = 0 ; i < this.soLuongChiTiet ; i++){
            if(this.chiTietLichTrinh[i].getNgay().equals(ngayMoi)){
                return false;
            }
        }
        // Tim ngay cu de cap nhat sang ngay moi
        for (int i = 0 ; i < this.soLuongChiTiet ; i++){
            if(this.chiTietLichTrinh[i].getNgay().equals(ngayCu)){
                this.chiTietLichTrinh[i].setNgay(ngayMoi);
            }
        }
        return true;
    }

    public long tinhTongChiPhi(int soKhach){
        if(soKhach <= 0){
            return -1;
        }

        long result = 0;

    for(int i = 0 ; i < soLuongChiTiet ; i++){
        result += chiTietLichTrinh[i].tinhChiPhiNgay(soKhach);
        }

        return result;
    }

    public void xuat(){
        String tentour = kho.KhoDuLieu.getDanhSachTour().timTheoMa(maTour).getTenTour();
        String tenNhanvien = kho.KhoDuLieu.getDanhSachNhanVien().timTheoMa(maNhanVien).getHoTen();
        System.out.printf("%-18s | %-26s | %-24s | %-12s | %-12s | %-18s%n", this.maKeHoachTour, tentour, tenNhanvien, this.ngayDi, this.ngayVe, this.soLuongVeToiDa);
    }

    public void nhap(){
        this.maKeHoachTour = KhoDuLieu.getDanhSachKeHoachTour().getMaMoi();
        System.out.println("Ma ke hoach tour (tu dong): " + this.maKeHoachTour);
        System.out.print("Nhap ma tour: ");
        this.maTour = in.nextInt();
        in.nextLine();
        System.out.print("Nhap ma nhan vien: ");
        this.maNhanVien = in.nextInt();
        in.nextLine();
        System.out.print("Nhap Ngay di (trinh bay nhu sau 2026-11-23): ");
        this.ngayDi = in.nextLine();
        System.out.print("Nhap Ngay ve (trinh bay nhu sau 2026-11-23): ");
        this.ngayVe = in.nextLine();
        System.out.print("Nhap so luong ve toi da: ");
        this.soLuongVeToiDa = in.nextInt();
        in.nextLine();
    }

    public long tinhDonGiaVe(Tour tour){// tinh don gia theo tour tren 1 nguoi
        return tour.tinhGiaVe() + this.tinhTongChiPhi(1);
    }
}
