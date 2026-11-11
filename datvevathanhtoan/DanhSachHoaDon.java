package datvevathanhtoan;

import java.util.Arrays;
import java.util.Scanner;

import danhsach.DanhSach;
import kho.KhoDuLieu;
import nguoidung.DanhSachNhanVien;
import nguoidung.NhanVien;

public class DanhSachHoaDon implements DanhSach<HoaDon> {
    private HoaDon[] danhSach;
    private int soLuong;

    // Constructors
    public DanhSachHoaDon() {
        this(new HoaDon[0], 0);
    }

    public DanhSachHoaDon(HoaDon[] danhSach, int soLuong) {
        this.danhSach = danhSach == null ? new HoaDon[0]
                : Arrays.copyOf(danhSach, Math.min(Math.max(soLuong, 0), danhSach.length));
        this.soLuong = this.danhSach.length;
    }

    // Getters / Setters
    public HoaDon[] getDanhSach() {
        return danhSach;
    }

    public void setDanhSach(HoaDon[] danhSach) {
        this.danhSach = danhSach;
        soLuong = danhSach == null ? 0 : Math.min(soLuong, danhSach.length);
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = danhSach == null ? 0 : Math.min(Math.max(soLuong, 0), danhSach.length);
    }

    // Methods @Override
    @Override
    public boolean them(HoaDon hoaDon) {
        if (hoaDon == null || hoaDon.getMaHoaDon() <= 0 || danhSach == null || soLuong < 0
                || soLuong > danhSach.length || timTheoMa(hoaDon.getMaHoaDon()) != null) {
            return false;
        }
        danhSach = Arrays.copyOf(danhSach, soLuong + 1);
        danhSach[soLuong++] = hoaDon;
        return true;
    }

    @Override
    public boolean them() {
        if(this.danhSach == null){
            this.danhSach = new HoaDon[0];
            this.soLuong = 0;
        }

        this.danhSach = Arrays.copyOf(this.danhSach, this.soLuong + 1);
        this.danhSach[this.soLuong] = new HoaDon();
        this.danhSach[this.soLuong].nhap();
        this.soLuong++;

        return true;
    }

    @Override
    public void xuat() {
        if(this.danhSach == null){
            return;
        }
        System.out.println("Danh sách vé: ");
        for (int i = 0 ; i < this.soLuong ; i++){
            this.danhSach[i].xuat();
        }
    }

    @Override
    public void nhap() {
        Scanner in = new Scanner(System.in);
        System.out.print("Nhập số lượng vé: ");
        this.soLuong = in.nextInt();
        in.nextLine();
        this.danhSach = new HoaDon[this.soLuong];
        for(int i = 0 ; i < this.soLuong ; i++){
            System.out.println("Nhập vé thứ " + (i + 1));
            this.danhSach[i] = new HoaDon();
            this.danhSach[i].nhap();
        }
    }

    @Override
    public boolean xoaTheoMa(int maHoaDon) {
         if(this.danhSach == null || maHoaDon <= 0 ){
            return false;
        }
        boolean check = false;
        int checkpoint = 0;
        for(int i = 0 ; i < this.soLuong ; i++){
            if(this.danhSach[i].getMaHoaDon() == maHoaDon){
                check = true;
                checkpoint = i;
                break;
            }
        }

        if(!check){
            return false;
        }
        for(int i = checkpoint ; i < this.soLuong-1 ; i++){
            this.danhSach[i] = this.danhSach[i+1];
        }
        this.soLuong--;
        return true;
    }

    @Override
    public boolean suaTheoMa(int ma) {
        Scanner in = new Scanner(System.in);
        if(this.danhSach == null){
            return false;
        }

        HoaDon temp = timTheoMa(ma);
        if(temp == null ){
            return false;
        }
        System.out.println("Thông tin hóa đơn là");
        temp.xuat();
        
        System.out.print("Sửa ngày lập: ");
        String ngaylap = in.nextLine();
        if(!ngaylap.isBlank()){
        temp.setNgayLap(ngaylap);
        }

        System.out.print("Sữa số tiền đã thanh toán: ");
        long soTienDaThanhToan = in.nextInt();
        if(soTienDaThanhToan > 0){
        temp.setSoTienDaThanhToan(soTienDaThanhToan);
        }

        return false;
    }

    @Override
    public HoaDon timTheoMa(int maHoaDon) {
        if (maHoaDon <= 0 || danhSach == null) {
            return null;
        }
        for (int i = 0; i < gioiHan(); i++) {
            if (danhSach[i] != null && danhSach[i].getMaHoaDon() == maHoaDon) {
                return danhSach[i];
            }
        }
        return null;
    }

    @Override
    public HoaDon[] layTatCa() {
        return danhSach == null ? new HoaDon[0] : Arrays.copyOf(danhSach, gioiHan());
    }

    @Override
    public boolean docFile(String duongDan) {
        return false;
    }

    @Override
    public boolean ghiFile(String duongDan) {
        return false;
    }

    // Methods còn lại
    public HoaDon[] timTheoTen(String hoTen) {
        if (hoTen == null || hoTen.trim().isEmpty() || danhSach == null) {
            return new HoaDon[0];
        }
        DanhSachNhanVien dsNhanVien = KhoDuLieu.getDanhSachNhanVien();
        if (dsNhanVien == null) {
            return new HoaDon[0];
        }
        NhanVien[] nhanViens = dsNhanVien.timTheoTen(hoTen);
        if (nhanViens == null || nhanViens.length == 0) {
            return new HoaDon[0];
        }

        HoaDon[] ketQua = new HoaDon[gioiHan()];
        int dem = 0;
        for (int i = 0; i < gioiHan(); i++) {
            HoaDon hoaDon = danhSach[i];
            if (hoaDon != null && thuocNhanVien(hoaDon, nhanViens)) {
                ketQua[dem++] = hoaDon;
            }
        }
        return Arrays.copyOf(ketQua, dem);
    }

    public HoaDon[] timTheoMaDonDatTour(int maDonDatTour) {
        if (maDonDatTour <= 0 || danhSach == null) {
            return new HoaDon[0];
        }
        HoaDon[] ketQua = new HoaDon[gioiHan()];
        int dem = 0;
        for (int i = 0; i < gioiHan(); i++) {
            if (danhSach[i] != null && danhSach[i].getMaDonDatTour() == maDonDatTour) {
                ketQua[dem++] = danhSach[i];
            }
        }
        return Arrays.copyOf(ketQua, dem);
    }

    public HoaDon[] timChuaThanhToanDu(DanhSachVe dsVe) {
        HoaDon[] ketQua = new HoaDon[gioiHan()];
        int dem = 0;
        for (int i = 0; i < gioiHan(); i++) {
            HoaDon hoaDon = danhSach[i];
            if (hoaDon == null) {
                continue;
            }
            if (dsVe != null && hoaDon.getChiTiet() != null && hoaDon.tinhTongTien() == 0) {
                hoaDon.getChiTiet().capNhatTongTien(dsVe);
            }
            if (!hoaDon.daThanhToanDu()) {
                ketQua[dem++] = hoaDon;
            }
        }
        return Arrays.copyOf(ketQua, dem);
    }

    // Helper methods
    private int gioiHan() {
        return danhSach == null ? 0 : Math.min(soLuong, danhSach.length);
    }

    private boolean thuocNhanVien(HoaDon hoaDon, NhanVien[] nhanViens) {
        for (NhanVien nhanVien : nhanViens) {
            if (nhanVien != null && nhanVien.getMaNhanVien() == hoaDon.getMaNhanVien()) {
                return true;
            }
        }
        return false;
    }
}
