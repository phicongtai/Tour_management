package danhsach;

import java.nio.file.Path;

public interface DanhSach<T> {
    // Thêm phần tử (có tham số và không có tham số)
    boolean them(T phanTu);
    boolean them();

    // Xuất và nhập
    void xuat();
    void nhap();

    // Xóa phần tử theo mã
    boolean xoaTheoMa(int ma);

    // Sửa / Cập nhật phần tử
    boolean capNhat(T phanTu);
    boolean suaTheoMa(int ma);

    // Tìm kiếm và lấy danh sách
    T timTheoMa(int ma);
    T[] layTatCa();

    // Đọc và ghi file (chỉ khai báo, không có thân hàm)
    boolean docFile(Path path);
    boolean ghiFile(Path path);
}
