package danhsach;

public interface DanhSach<T> {
    // Thêm phần tử mới
    boolean them(T phanTu);

    // Tìm kiếm theo mã (dùng chung kiểu int cho mã nhân viên / hành khách)
    T timTheoMa(int ma);

    // Cập nhật thông tin phần tử
    boolean capNhat(T phanTu);

    // Xóa phần tử theo mã
    boolean xoaTheoMa(int ma);

    // Lấy tất cả phần tử đang dùng
    T[] layTatCa();

    // Xuất dữ liệu ra các dòng TXT
    default String[] xuatDuLieu() {
        return new String[0];
    }

    // Nhập dữ liệu từ các dòng TXT
    default boolean nhapDuLieu(String[] dongDuLieu) {
        return false;
    }
}