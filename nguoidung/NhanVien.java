package nguoidung;

import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import datvevathanhtoan.HoaDon;

public class NhanVien {
    private String maNhanVien;
    private String hoTen;
    private Date ngaySinh;
    private String gioiTinh;
    private String soDienThoai;
    private String diaChi;
    private String email;
    private String chucVu;

    public NhanVien() {}

    public NhanVien(String maNhanVien, String hoTen, Date ngaySinh, String gioiTinh, 
        String soDienThoai, String diaChi, String email, String chucVu) {
        this.maNhanVien = maNhanVien;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.email = email;
        this.chucVu = chucVu;
    }

    public String getMaNhanVien() { 
        return maNhanVien; 
    }
    public void setMaNhanVien(String maNhanVien) { 
        this.maNhanVien = maNhanVien; 
    }

    public String getHoTen() { 
        return hoTen; 
    }
    public void setHoTen(String hoTen) { 
        this.hoTen = hoTen; 
    }

    public Date getNgaySinh() { 
        return ngaySinh; 
    }
    public void setNgaySinh(Date ngaySinh) { 
        this.ngaySinh = ngaySinh; 
    }

    public String getGioiTinh() { 
        return gioiTinh; 
    }
    public void setGioiTinh(String gioiTinh) { 
        this.gioiTinh = gioiTinh; 
    }

    public String getSoDienThoai() { 
        return soDienThoai;
    }
    public void setSoDienThoai(String soDienThoai) { 
        this.soDienThoai = soDienThoai; 
    }

    public String getDiaChi() { 
        return diaChi; 
    }
    public void setDiaChi(String diaChi) { 
        this.diaChi = diaChi; 
    }

    public String getEmail() { 
        return email; 
    }
    public void setEmail(String email) { 
        this.email = email; 
    }

    public String getChucVu() { 
        return chucVu; 
    }
    public void setChucVu(String chucVu) { 
        this.chucVu = chucVu; 
    }
    public void nhap() {
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.print("Nhap ma nhan vien: ");
        maNhanVien = sc.nextLine();
        System.out.print("Nhap ho ten: ");
        hoTen = sc.nextLine();

        System.out.print("Nhap ngay sinh (dd/MM/yyyy): ");
        try {
            ngaySinh = sdf.parse(sc.nextLine());
        } catch (Exception e) {
            ngaySinh = new Date(); 
        }

        System.out.print("Nhap gioi tinh: ");
        gioiTinh = sc.nextLine();
        System.out.print("Nhap so dien thoai: ");
        soDienThoai = sc.nextLine();
        System.out.print("Nhap dia chi: ");
        diaChi = sc.nextLine();
        System.out.print("Nhap email: ");
        email = sc.nextLine();
        System.out.print("Nhap chuc vu: ");
        chucVu = sc.nextLine();
    }

    public void hienThiThongTin() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String strNgaySinh = (ngaySinh != null) ? sdf.format(ngaySinh) : "";

        System.out.println("Ma NV: " + maNhanVien + 
                        " \nHo ten: " + hoTen + 
                        " \nNgay sinh: " + strNgaySinh + 
                        " \nGioi tinh: " + gioiTinh + 
                        " \nSDT: " + soDienThoai + 
                        " \nDia chi: " + diaChi + 
                        " \nEmail: " + email + 
                        " \nChuc vu: " + chucVu);
    }
    public HoaDon lapHoaDon(HanhKhach hanhKhach) {
        System.out.println("Nhan vien " + this.hoTen + " dang lap hoa don cho hanh khach: " + hanhKhach.getHoTen());
        return new HoaDon(); 
    }
}
