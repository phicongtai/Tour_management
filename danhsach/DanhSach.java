package danhsach;

public interface DanhSach<T> {
    // Them phan tu (co tham so va khong co tham so)
    boolean them(T phanTu);
    boolean them();

    // Xuat va nhap
    void xuat();
    void nhap();

    // Xoa phan tu theo ma
    boolean xoaTheoMa(int ma);

    // Sua / Cap nhat phan tu
    boolean suaTheoMa(int ma);

    // Tim kiem va lay danh sach
    T timTheoMa(int ma);
    T[] layTatCa();

    // Doc va ghi file (chi khai bao, khong co than ham)
    boolean docFile(String duongDan);
    boolean ghiFile(String duongDan);
}
