package danhsach;

import java.nio.file.Path;

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

    default boolean docFile(Path path) {
        return false;
    }

    default boolean ghiFile(Path path) {
        return false;
    }
}
