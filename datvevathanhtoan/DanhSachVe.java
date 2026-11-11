package datvevathanhtoan;

import java.util.Arrays;
import java.util.Scanner;
import danhsach.DanhSach;
import kho.KhoDuLieu;
import nguoidung.DanhSachHanhKhach;
import nguoidung.HanhKhach;
import tochuctour.DanhSachKeHoachTour;
import tochuctour.KeHoachTour;

public class DanhSachVe implements DanhSach<Ve> {
    private Ve[] danhSach;
    private int soLuong;

    // Constructors
    public DanhSachVe() {
        this(new Ve[0], 0);
    }

    public DanhSachVe(Ve[] danhSach, int soLuong) {
        this.danhSach = danhSach == null ? new Ve[0]
                : Arrays.copyOf(danhSach, Math.min(Math.max(soLuong, 0), danhSach.length));
        this.soLuong = this.danhSach.length;
    }

    // Getters / Setters
    public Ve[] getDanhSach() {
        return danhSach;
    }

    public void setDanhSach(Ve[] danhSach) {
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
    public boolean them(Ve ve) {
        if (ve == null || ve.getMaVe() <= 0 || danhSach == null || soLuong < 0
                || soLuong > danhSach.length || timTheoMa(ve.getMaVe()) != null) {
            return false;
        }
        danhSach = Arrays.copyOf(danhSach, soLuong + 1);
        danhSach[soLuong++] = ve;
        return true;
    }

    @Override
    public boolean them() {
        if(this.danhSach == null){
            this.danhSach = new Ve[0];
            this.soLuong = 0;
        }

        this.danhSach = Arrays.copyOf(this.danhSach, this.soLuong + 1);
        this.danhSach[this.soLuong] = new Ve();
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
        this.danhSach = new Ve[this.soLuong];
        for(int i = 0 ; i < this.soLuong ; i++){
            System.out.println("Nhập vé thứ " + (i + 1));
            this.danhSach[i] = new Ve();
            this.danhSach[i].nhap();
        }
    }

    @Override
    public boolean xoaTheoMa(int maVe) {
        if(this.danhSach == null || maVe <= 0 ){
            return false;
        }
        boolean check = false;
        int checkpoint = 0;
        for(int i = 0 ; i < this.soLuong ; i++){
            if(this.danhSach[i].getMaVe() == maVe){
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

        Ve temp = timTheoMa(ma);
        if(temp == null ){
            return false;
        }
        System.out.println("Thông tin vé là");
        temp.xuat();
        
        System.out.print("Sửa ngày đặt: ");
        String ngaydat = in.nextLine();
        if(!ngaydat.isBlank()){
        temp.setNgayDat(ngaydat);
        }

        System.out.print("Sữa đơn giá: ");
        long dongia = in.nextLong();
        if(dongia > 0){
        temp.setDonGia(dongia);
        }

        System.out.print("Sua trang thai: ");
        int trangThai = in.nextInt();
        if(trangThai > 0){
        temp.setTrangThai(trangThai);
        }
        return false;
    }

    @Override
    public Ve timTheoMa(int maVe) {
        if (maVe <= 0 || danhSach == null) {
            return null;
        }
        for (int i = 0; i < gioiHan(); i++) {
            if (danhSach[i] != null && danhSach[i].getMaVe() == maVe) {
                return danhSach[i];
            }
        }
        return null;
    }

    @Override
    public Ve[] layTatCa() {
        return danhSach == null ? new Ve[0] : Arrays.copyOf(danhSach, gioiHan());
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
    public Ve[] timTheoTen(String hoTen) {
        if (hoTen == null || hoTen.trim().isEmpty() || danhSach == null) {
            return new Ve[0];
        }
        DanhSachHanhKhach dsHanhKhach = KhoDuLieu.getDanhSachHanhKhach();
        if (dsHanhKhach == null) {
            return new Ve[0];
        }
        HanhKhach[] hanhKhachs = dsHanhKhach.timTheoTen(hoTen);
        if (hanhKhachs == null || hanhKhachs.length == 0) {
            return new Ve[0];
        }

        Ve[] ketQua = new Ve[gioiHan()];
        int dem = 0;
        for (int i = 0; i < gioiHan(); i++) {
            Ve ve = danhSach[i];
            if (ve != null && thuocHanhKhach(ve, hanhKhachs)) {
                ketQua[dem++] = ve;
            }
        }
        return Arrays.copyOf(ketQua, dem);
    }

    public Ve[] timTheoMaDonDatTour(int maDonDatTour) {
        if (maDonDatTour <= 0 || danhSach == null) {
            return new Ve[0];
        }
        Ve[] ketQua = new Ve[gioiHan()];
        int dem = 0;
        for (int i = 0; i < gioiHan(); i++) {
            if (danhSach[i] != null && danhSach[i].getMaDonDatTour() == maDonDatTour) {
                ketQua[dem++] = danhSach[i];
            }
        }
        return Arrays.copyOf(ketQua, dem);
    }

    public int[] layDanhSachMaDonDatTour() {
        if (gioiHan() == 0) {
            return new int[0];
        }
        int[] ketQua = new int[gioiHan()];
        int dem = 0;
        for (int i = 0; i < gioiHan(); i++) {
            if (danhSach[i] == null) {
                continue;
            }
            int maDon = danhSach[i].getMaDonDatTour();
            boolean daCo = false;
            for (int j = 0; j < dem; j++) {
                if (ketQua[j] == maDon) {
                    daCo = true;
                    break;
                }
            }
            if (!daCo) {
                ketQua[dem++] = maDon;
            }
        }
        return Arrays.copyOf(ketQua, dem);
    }

    public Ve[] timTheoTrangThai(int trangThai) {
        if (gioiHan() == 0) {
            return new Ve[0];
        }
        Ve[] ketQua = new Ve[gioiHan()];
        int dem = 0;
        for (int i = 0; i < gioiHan(); i++) {
            if (danhSach[i] != null && danhSach[i].getTrangThai() == trangThai) {
                ketQua[dem++] = danhSach[i];
            }
        }
        return Arrays.copyOf(ketQua, dem);
    }

    public int demVeChiemChoTheoKeHoach(int maKeHoachTour) {
        if (maKeHoachTour <= 0) {
            return 0;
        }
        int dem = 0;
        for (int i = 0; i < gioiHan(); i++) {
            if (danhSach[i] != null && danhSach[i].getMaKeHoachTour() == maKeHoachTour
                    && danhSach[i].chiemCho()) {
                dem++;
            }
        }
        return dem;
    }

    public boolean daCoVeHopLe(int maKeHoachTour, int maHanhKhach) {
        if (maKeHoachTour <= 0 || maHanhKhach <= 0) {
            return false;
        }
        for (int i = 0; i < gioiHan(); i++) {
            if (danhSach[i] != null && danhSach[i].getMaKeHoachTour() == maKeHoachTour
                    && danhSach[i].getMaHanhKhach() == maHanhKhach && danhSach[i].chiemCho()) {
                return true;
            }
        }
        return false;
    }

    // Helper methods
    private int gioiHan() {
        return danhSach == null ? 0 : Math.min(soLuong, danhSach.length);
    }

    private boolean thuocHanhKhach(Ve ve, HanhKhach[] hanhKhachs) {
        for (HanhKhach hanhKhach : hanhKhachs) {
            if (hanhKhach != null && hanhKhach.getMaHanhKhach() == ve.getMaHanhKhach()) {
                return true;
            }
        }
        return false;
    }
}
