import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class Ve {
    protected String maVe;
    protected double donGia;
    protected Date ngayDat;
    protected String trangThai;

    public Ve() {}
    public Ve(String maVe, double donGia, Date ngayDat, String trangThai) {
        this.maVe = maVe;
        this.donGia = donGia;
        this.ngayDat = ngayDat;
        this.trangThai = trangThai;
    }

    public String getMaVe() { return maVe; }
    public void setMaVe(String maVe) { this.maVe = maVe; }

    public double getDonGia() { return donGia; }
    public void setDonGia(double donGia) { this.donGia = donGia; }

    public Date getNgayDat() { return ngayDat; }
    public void setNgayDat(Date ngayDat) { this.ngayDat = ngayDat; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

    public void nhap() {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.print("Nhap ma ve: ");
        maVe = scanner.nextLine();
        System.out.print("Nhap don gia: ");
        try {
            donGia = Double.parseDouble(scanner.nextLine());
        } catch (Exception e) {
            donGia = 0.0;
        }
        System.out.print("Nhap ngay dat (dd/MM/yyyy): ");
        try {
            ngayDat = sdf.parse(scanner.nextLine());
        } catch (Exception e) {
            ngayDat = new Date();
        }
        System.out.print("Nhap trang thai: ");
        trangThai = scanner.nextLine();
    }

    public void capNhatTrangThai(String trangThaiMoi) {
        this.trangThai = trangThaiMoi;
        System.out.println("Da cap nhat trang thai ve " + maVe + " thanh: " + trangThaiMoi);
    }

    public void huyVe() {
        this.trangThai = "Da huy";
        System.out.println("Ve co ma " + maVe + " da duoc huy thanh cong.");
    }
    
    public void hienThiThongTin() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Ma ve: " + maVe + "\nDon gia: " + donGia + 
                           "\nNgay dat: " + (ngayDat != null ? sdf.format(ngayDat) : "") + 
                           "\nTrang thai: " + trangThai);
    }
}

