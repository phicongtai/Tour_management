package datvevathanhtoan;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
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
    }

    public DanhSachHoaDon(HoaDon[] danhSach, int soLuong) {
        this.danhSach = danhSach;
        this.soLuong = soLuong;
    }

    // Getters / Setters
    public HoaDon[] getDanhSach() {
        return danhSach;
    }

    public void setDanhSach(HoaDon[] danhSach) {
        this.danhSach = danhSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
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
        this.danhSach = new HoaDon[0];
        this.soLuong = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(duongDan))) {
            String temp;
            while ((temp = reader.readLine()) != null) {
                if (temp.trim().isEmpty()) continue;
                String[] parts = temp.split("\\|");
                if (parts.length >= 7) {
                    int maHoaDon = Integer.parseInt(parts[0].trim());
                    int maDonDatTour = Integer.parseInt(parts[1].trim());
                    int maNhanVien = Integer.parseInt(parts[2].trim());
                    String ngayLap = parts[3].trim();
                    long soTienDaThanhToan = Long.parseLong(parts[4].trim());

                    int[] dsMaVe = new int[0];
                    String veStr = parts[5].trim();
                    if (!veStr.isEmpty()) {
                        String[] maVeStrs = veStr.split(",");
                        dsMaVe = new int[maVeStrs.length];
                        for (int j = 0; j < maVeStrs.length; j++) {
                            dsMaVe[j] = Integer.parseInt(maVeStrs[j].trim());
                        }
                    }
                    long tongTien = Long.parseLong(parts[6].trim());
                    ChiTietHoaDon chiTiet = new ChiTietHoaDon(maHoaDon, dsMaVe, dsMaVe.length, tongTien);

                    HoaDon hd = new HoaDon(maHoaDon, maDonDatTour, maNhanVien, ngayLap, soTienDaThanhToan, chiTiet);
                    this.danhSach = Arrays.copyOf(this.danhSach, this.soLuong + 1);
                    this.danhSach[this.soLuong++] = hd;
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean ghiFile(String duongDan) {
        if (this.danhSach == null) {
            return false;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(duongDan))) {
            for (int i = 0; i < this.soLuong; i++) {
                HoaDon hd = this.danhSach[i];
                if (hd == null) continue;
                String temp = "";
                temp += hd.getMaHoaDon() + "|";
                temp += hd.getMaDonDatTour() + "|";
                temp += hd.getMaNhanVien() + "|";
                temp += hd.getNgayLap() + "|";
                temp += hd.getSoTienDaThanhToan() + "|";

                ChiTietHoaDon ct = hd.getChiTiet();
                String dsVeStr = "";
                if (ct != null && ct.getDanhSachMaVe() != null) {
                    int[] maVes = ct.getDanhSachMaVe();
                    for (int j = 0; j < maVes.length; j++) {
                        dsVeStr += maVes[j];
                        if (j < maVes.length - 1) {
                            dsVeStr += ",";
                        }
                    }
                }
                temp += dsVeStr + "|";
                temp += (ct != null ? ct.getTongTien() : 0);
                writer.write(temp);
                writer.newLine();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
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
