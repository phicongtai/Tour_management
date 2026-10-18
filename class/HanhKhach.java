import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class HanhKhach {
    private String maHanhKhach;
    private String hoTen;
    private Date ngaySinh;
    private String gioiTinh;
    private String canCuocCongDan;
    private String soDienThoai;
    private String diaChi;
    private String email;

    public HanhKhach() {}
    public HanhKhach(String maHanhKhach, String hoTen, Date ngaySinh, String gioiTinh, String canCuocCongDan, String soDienThoai, String diaChi, String email) {
        this.maHanhKhach = maHanhKhach;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.canCuocCongDan = canCuocCongDan;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.email = email;
    }

    public String getMaHanhKhach() { return maHanhKhach; }
    public void setMaHanhKhach(String maHanhKhach) { this.maHanhKhach = maHanhKhach; }

    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }

    public Date getNgaySinh() { return ngaySinh; }
    public void setNgaySinh(Date ngaySinh) { this.ngaySinh = ngaySinh; }

    public String getGioiTinh() { return gioiTinh; }
    public void setGioiTinh(String gioiTinh) { this.gioiTinh = gioiTinh; }

    public String getCanCuocCongDan() { return canCuocCongDan; }
    public void setCanCuocCongDan(String canCuocCongDan) { this.canCuocCongDan = canCuocCongDan; }

    public String getSoDienThoai() { return soDienThoai; }
    public void setSoDienThoai(String soDienThoai) { this.soDienThoai = soDienThoai; }

    public String getDiaChi() { return diaChi; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public void nhap() {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.print("Nhap ma hanh khach: ");
        maHanhKhach = scanner.nextLine();
        System.out.print("Nhap ho ten: ");
        hoTen = scanner.nextLine();
        System.out.print("Nhap ngay sinh (dd/MM/yyyy): ");
        try {
            ngaySinh = sdf.parse(scanner.nextLine());
        } catch (Exception e) {
            ngaySinh = new Date();
        }
        System.out.print("Nhap gioi tinh: ");
        gioiTinh = scanner.nextLine();
        System.out.print("Nhap CCCD: ");
        canCuocCongDan = scanner.nextLine();
        System.out.print("Nhap so dien thoai: ");
        soDienThoai = scanner.nextLine();
        System.out.print("Nhap dia chi: ");
        diaChi = scanner.nextLine();
        System.out.print("Nhap email: ");
        email = scanner.nextLine();
    }

    public void hienThiThongTin() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Ma HK: " + maHanhKhach + 
                           "\nHo ten: " + hoTen + 
                           "\nNgay sinh: " + (ngaySinh != null ? sdf.format(ngaySinh) : "") + 
                           "\nGioi tinh: " + gioiTinh + "\nCCCD: " + canCuocCongDan + 
                           "\nSDT: " + soDienThoai + "\nDia chi: " + diaChi + "\nEmail: " + email);
    }
    import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class HanhKhach {
    private String maHanhKhach;
    private String hoTen;
    private Date ngaySinh;
    private String gioiTinh;
    private String canCuocCongDan;
    private String soDienThoai;
    private String diaChi;
    private String email;

    public HanhKhach() {}
    public HanhKhach(String maHanhKhach, String hoTen, Date ngaySinh, String gioiTinh, String canCuocCongDan, String soDienThoai, String diaChi, String email) {
        this.maHanhKhach = maHanhKhach;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.canCuocCongDan = canCuocCongDan;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.email = email;
    }

    public String getMaHanhKhach() { return maHanhKhach; }
    public void setMaHanhKhach(String maHanhKhach) { this.maHanhKhach = maHanhKhach; }

    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }

    public Date getNgaySinh() { return ngaySinh; }
    public void setNgaySinh(Date ngaySinh) { this.ngaySinh = ngaySinh; }

    public String getGioiTinh() { return gioiTinh; }
    public void setGioiTinh(String gioiTinh) { this.gioiTinh = gioiTinh; }

    public String getCanCuocCongDan() { return canCuocCongDan; }
    public void setCanCuocCongDan(String canCuocCongDan) { this.canCuocCongDan = canCuocCongDan; }

    public String getSoDienThoai() { return soDienThoai; }
    public void setSoDienThoai(String soDienThoai) { this.soDienThoai = soDienThoai; }

    public String getDiaChi() { return diaChi; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public void nhap() {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.print("Nhap ma hanh khach: ");
        maHanhKhach = scanner.nextLine();
        System.out.print("Nhap ho ten: ");
        hoTen = scanner.nextLine();
        System.out.print("Nhap ngay sinh (dd/MM/yyyy): ");
        try {
            ngaySinh = sdf.parse(scanner.nextLine());
        } catch (Exception e) {
            ngaySinh = new Date();
        }
        System.out.print("Nhap gioi tinh: ");
        gioiTinh = scanner.nextLine();
        System.out.print("Nhap CCCD: ");
        canCuocCongDan = scanner.nextLine();
        System.out.print("Nhap so dien thoai: ");
        soDienThoai = scanner.nextLine();
        System.out.print("Nhap dia chi: ");
        diaChi = scanner.nextLine();
        System.out.print("Nhap email: ");
        email = scanner.nextLine();
    }

    public void hienThiThongTin() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Ma HK: " + maHanhKhach + 
                           "\nHo ten: " + hoTen + 
                           "\nNgay sinh: " + (ngaySinh != null ? sdf.format(ngaySinh) : "") + 
                           "\nGioi tinh: " + gioiTinh + "\nCCCD: " + canCuocCongDan + 
                           "\nSDT: " + soDienThoai + "\nDia chi: " + diaChi + "\nEmail: " + email);
    }

    public Ve datVe(KeHoachTour keHoachTour) {
        System.out.println("Hanh khach " + this.hoTen + " dang thuc hien dat ve.");
        return new Ve(); 
    }
}