package tochuctour;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;
import danhsach.DanhSach;
import kho.KhoDuLieu;

public class DanhSachKeHoachTour implements DanhSach<KeHoachTour> {
    private KeHoachTour[] danhSach;
    private int soLuong;

    // Constructors
    public DanhSachKeHoachTour() {
    }

    public DanhSachKeHoachTour(KeHoachTour[] danhSach, int soLuong) {
        this.danhSach = new KeHoachTour[10];
        this.soLuong = 0;
    }

    // Getters / Setters
    public KeHoachTour[] getDanhSach() {
        return danhSach;
    }

    public void setDanhSach(KeHoachTour[] danhSach) {
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
    public boolean them(KeHoachTour keHoach) {
        if(keHoach == null){
            return false;
        }
        if(danhSach == null){
            this.danhSach = new KeHoachTour[0];
            this.soLuong = 0;
        }
        for(int i = 0 ; i < this.soLuong ; i++){
            if(this.danhSach[i].getMaKeHoachTour() == keHoach.getMaKeHoachTour() ){;
            return false;
            }
        }

        this.danhSach = Arrays.copyOf(this.danhSach, soLuong + 1);
        this.danhSach[this.soLuong] = keHoach;
        this.soLuong++;
        return true;
    }

    @Override
    public boolean them() {
        if(this.danhSach == null){
            this.danhSach = new KeHoachTour[0];
            this.soLuong = 0;
        }

        this.danhSach = Arrays.copyOf(this.danhSach, this.soLuong + 1);
        this.danhSach[this.soLuong] = new KeHoachTour();
        this.danhSach[this.soLuong].nhap();
        this.soLuong++;
        return true;
    }

    @Override
    public void xuat() {
        if(this.danhSach == null){
            return;
        }
        System.out.println("Danh sách kế hoạch tour");
        for(int i = 0 ; i < this.soLuong ; i++){
            this.danhSach[i].xuat();
        }
    }

    @Override
    public void nhap() {
        Scanner in = KhoDuLieu.getScanner();
        System.out.print("Nhập vào số lượng: ");
        this.soLuong = in.nextInt();
        in.nextLine();
        this.danhSach = new KeHoachTour[this.soLuong];
        for(int i = 0 ; i < this.soLuong ; i++){
            System.out.println("Nhập kế hoạch tour thứ " + (i + 1));
            this.danhSach[i] = new KeHoachTour();
            this.danhSach[i].nhap();
        }
    }

    @Override
    public boolean xoaTheoMa(int maKeHoachTour) {
        if(this.danhSach == null || maKeHoachTour <= 0 ){
            return false;
        }
        boolean check = false;
        int checkpoint = 0;
        for(int i = 0 ; i < this.soLuong ; i++){
            if(this.danhSach[i].getMaKeHoachTour() == maKeHoachTour){
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
        Scanner in = KhoDuLieu.getScanner();
        if(this.danhSach == null){
            return false;
        }

        KeHoachTour temp = timTheoMa(ma);
        if(temp == null ){
            return false;
        }
        System.out.println("Thông tin gốc kế hoạch tour là");
        temp.xuat();
        
        System.out.print("Sữa ngày đi: ");
        String ngaydi = in.nextLine();
        if(!ngaydi.isBlank()){
        temp.setNgayDi(ngaydi);
        }

        System.out.print("Sửa này về: ");
        String ngayve = in.nextLine();
        if(!ngayve.isBlank()){
        temp.setNgayVe(ngaydi);
        }

        System.out.print("Sua so lượng vé tối đa: ");
        int soluong = in.nextInt();
        if(soluong > 0){
        temp.setSoLuongVeToiDa(soluong);
        }
        return false;
    }

    @Override
    public KeHoachTour timTheoMa(int maKeHoachTour) {
        if(maKeHoachTour <= 0 || this.danhSach == null){
            return null;
        }

        for(int i = 0 ; i < this.soLuong ; i++){
            if(maKeHoachTour == this.danhSach[i].getMaKeHoachTour()){
                return this.danhSach[i];
            }
        }
        return null;
    }

    @Override
    public KeHoachTour[] layTatCa() {
        return danhSach;
    }

// maKeHoachTour|maTour|maNhanVien|ngayDi|ngayVe|soLuongVeToiDa|chuoiChiTiet(optional)
@Override
public boolean docFile(String duongDan) {
    this.danhSach = new KeHoachTour[0];
    this.soLuong = 0;
    try (BufferedReader reader = new BufferedReader(new FileReader(duongDan))) {
        String temp;
        while ((temp = reader.readLine()) != null) {
            if (temp.trim().isEmpty()) continue;
            String[] parts = temp.split("\\|");
            if (parts.length >= 6) {
                KeHoachTour kh = new KeHoachTour(
                    Integer.parseInt(parts[0].trim()),
                    Integer.parseInt(parts[1].trim()),
                    Integer.parseInt(parts[2].trim()),
                    parts[3].trim(),
                    parts[4].trim(),
                    Integer.parseInt(parts[5].trim())
                );

                // Doc chi tiet lich trinh neu co (cot thu 7)
                if (parts.length >= 7 && !parts[6].trim().isEmpty() && !parts[6].trim().equalsIgnoreCase("none")) {
                    String[] dsChiTietStr = parts[6].trim().split(";");
                    for (String ctStr : dsChiTietStr) {
                        if (ctStr.trim().isEmpty()) continue;
                        String[] ctParts = ctStr.split(",");
                        if (ctParts.length >= 6) {
                            ChiTietKeHoachTour ct = new ChiTietKeHoachTour(
                                ctParts[0].trim(),
                                Integer.parseInt(ctParts[1].trim()),
                                Integer.parseInt(ctParts[2].trim()),
                                Long.parseLong(ctParts[3].trim()),
                                Long.parseLong(ctParts[4].trim()),
                                Long.parseLong(ctParts[5].trim())
                            );
                            kh.themChiTiet(ct);
                        }
                    }
                }

                this.danhSach = Arrays.copyOf(this.danhSach, this.soLuong + 1);
                this.danhSach[this.soLuong++] = kh;
            }
        }
        return true;
    } catch (Exception e) {
        return false;
    }
}

    @Override
    public boolean ghiFile(String duongDan) {
        if(this.danhSach == null){
            return false;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(duongDan))) {
            for(int i = 0 ; i < this.soLuong ; i++){
                KeHoachTour kh = this.danhSach[i];
                if (kh == null) continue;

                StringBuilder sb = new StringBuilder();
                sb.append(kh.getMaKeHoachTour()).append("|")
                  .append(kh.getMaTour()).append("|")
                  .append(kh.getMaNhanVien()).append("|")
                  .append(kh.getNgayDi()).append("|")
                  .append(kh.getNgayVe()).append("|")
                  .append(kh.getSoLuongVeToiDa()).append("|");

                ChiTietKeHoachTour[] listCT = kh.getChiTietLichTrinh();
                if (listCT != null && kh.getSoLuongChiTiet() > 0) {
                    for (int j = 0; j < kh.getSoLuongChiTiet(); j++) {
                        ChiTietKeHoachTour ct = listCT[j];
                        if (ct != null) {
                            sb.append(ct.getNgay()).append(",")
                              .append(ct.getMaPhuongTien()).append(",")
                              .append(ct.getMaKhachSan()).append(",")
                              .append(ct.getTienAn()).append(",")
                              .append(ct.getTienO()).append(",")
                              .append(ct.getTienDiLai());
                            if (j < kh.getSoLuongChiTiet() - 1) {
                                sb.append(";");
                            }
                        }
                    }
                } else {
                    sb.append("none");
                }

                writer.write(sb.toString());
                writer.newLine();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Methods còn lại
    public KeHoachTour[] timTheoMaTour(int maTour) {
        if(maTour <= 0 || this.danhSach == null){
            return new KeHoachTour[0];
        }

        KeHoachTour[] temp = new KeHoachTour[0];
        int tempsize = 0 ;
        
        for(int i = 0 ; i < this.soLuong ; i++){
            if(maTour == this.danhSach[i].getMaTour()){
                temp = Arrays.copyOf(temp,tempsize + 1);
                temp[tempsize] = this.danhSach[i];
                tempsize++;
            }
        }

        return temp;
    }
}
