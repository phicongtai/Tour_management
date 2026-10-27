package danhsach;

public interface DanhSach<T> {
    boolean them(T phanTu);

    T[] layTatCa();

    default String[] xuatDuLieu() {
        return new String[0];
    }

    default boolean nhapDuLieu(String[] dongDuLieu) {
        return false;
    }
}
