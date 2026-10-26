package danhsach;

public interface DanhSach<T> {
    boolean them(T phanTu);

    boolean xoa(int ma);

    default boolean xoa(T phanTu) {
        return false;
    }

    default boolean xoaTheoMa(int ma) {
        return xoa(ma);
    }
}

