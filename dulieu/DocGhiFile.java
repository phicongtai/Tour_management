package dulieu;

import danhsach.DanhSach;
import kho.KhoDuLieu;

public class DocGhiFile {
    private static final String FILE_TOUR = "File_txt/tour.txt";
    private static final String FILE_NHAN_VIEN = "File_txt/nhan_vien.txt";
    private static final String FILE_HANH_KHACH = "File_txt/hanh_khach.txt";
    private static final String FILE_PHUONG_TIEN = "File_txt/phuong_tien.txt";
    private static final String FILE_KHACH_SAN = "File_txt/khach_san.txt";
    private static final String FILE_KE_HOACH = "File_txt/ke_hoach_tour.txt";
    private static final String FILE_VE = "File_txt/ve.txt";
    private static final String FILE_HOA_DON = "File_txt/hoa_don.txt";

    public DocGhiFile() {
    }

    private boolean docTuFile(String duongDan, DanhSach<?> danhSach) {
        if (duongDan == null || danhSach == null) {
            return false;
        }
        return danhSach.docFile(duongDan);
    }

    private boolean ghiRaFile(String duongDan, DanhSach<?> danhSach) {
        if (duongDan == null || danhSach == null) {
            return false;
        }
        return danhSach.ghiFile(duongDan);
    }

    public boolean docTatCa() {
        docTuFile(FILE_TOUR, KhoDuLieu.getDanhSachTour());
        docTuFile(FILE_NHAN_VIEN, KhoDuLieu.getDanhSachNhanVien());
        docTuFile(FILE_HANH_KHACH, KhoDuLieu.getDanhSachHanhKhach());
        docTuFile(FILE_PHUONG_TIEN, KhoDuLieu.getDanhSachPhuongTien());
        docTuFile(FILE_KHACH_SAN, KhoDuLieu.getDanhSachKhachSan());
        docTuFile(FILE_KE_HOACH, KhoDuLieu.getDanhSachKeHoachTour());
        docTuFile(FILE_VE, KhoDuLieu.getDanhSachVe());
        docTuFile(FILE_HOA_DON, KhoDuLieu.getDanhSachHoaDon());

        return true;
    }

    public boolean ghiTatCa() {
        ghiRaFile(FILE_TOUR, KhoDuLieu.getDanhSachTour());
        ghiRaFile(FILE_NHAN_VIEN, KhoDuLieu.getDanhSachNhanVien());
        ghiRaFile(FILE_HANH_KHACH, KhoDuLieu.getDanhSachHanhKhach());
        ghiRaFile(FILE_PHUONG_TIEN, KhoDuLieu.getDanhSachPhuongTien());
        ghiRaFile(FILE_KHACH_SAN, KhoDuLieu.getDanhSachKhachSan());
        ghiRaFile(FILE_KE_HOACH, KhoDuLieu.getDanhSachKeHoachTour());
        ghiRaFile(FILE_VE, KhoDuLieu.getDanhSachVe());
        ghiRaFile(FILE_HOA_DON, KhoDuLieu.getDanhSachHoaDon());

        return true;
    }
}
