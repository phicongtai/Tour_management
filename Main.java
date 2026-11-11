import dulieu.DocGhiFile;
import kho.KhoDuLieu;

public class Main {
    public static void main(String[] a){
        DocGhiFile docGhi = new DocGhiFile();
        docGhi.docTatCa();
        System.out.println("So tour: " + KhoDuLieu.getDanhSachTour().getSoLuong());
        System.out.println("So nhan vien: " + KhoDuLieu.getDanhSachNhanVien().getSoLuong());
        System.out.println("So hanh khach: " + KhoDuLieu.getDanhSachHanhKhach().getSoLuong());
        System.out.println("So phuong tien: " + KhoDuLieu.getDanhSachPhuongTien().getSoLuong());
        System.out.println("So khach san: " + KhoDuLieu.getDanhSachKhachSan().getSoLuong());
        System.out.println("So ke hoach tour: " + KhoDuLieu.getDanhSachKeHoachTour().getSoLuong());
        System.out.println("So ve: " + KhoDuLieu.getDanhSachVe().getSoLuong());
        System.out.println("So hoa don: " + KhoDuLieu.getDanhSachHoaDon().getSoLuong());
    }
}
