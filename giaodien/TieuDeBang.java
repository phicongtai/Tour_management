package giaodien;

public class TieuDeBang {
    public static void inTieuDeTour() {
        System.out.printf("%-7s | %-26s | %-14s | %-12s | %-7s | %-14s | %-12s | %-12s | %-10s | %-10s | %-10s%n",
                "Ma Tour", "Ten Tour", "Diem Khoi Hanh", "Diem Den", "So Ngay", "Don Gia Co Ban",
                "Loai Tour", "Vung/QuocGia", "Phu Phi", "Can Visa", "Phi Visa");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public static void inChanBangTour() {
        System.out.println("====================================================================================================================================================================");
    }

    public static void inTieuDeKeHoach() {
        System.out.printf("%-18s | %-26s | %-24s | %-12s | %-12s | %-18s%n",
                "Ma Ke Hoach Tour", "Ten Tour", "Nhan Vien", "Ngay Di", "Ngay Ve", "So Luong Ve Toi Da");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
    }

    public static void inChanBangKeHoach() {
        System.out.println("=============================================================================================================================");
    }

    public static void inTieuDeChiTietKeHoach() {
        System.out.printf("%-12s | %-25s | %-30s | %-12s | %-12s | %-12s%n",
                "Ngay", "Phuong Tien", "Khach San", "Tien An", "Tien O", "Tien Di Lai");
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
    }

    public static void inTieuDeNhanVien() {
        System.out.printf("%-6s | %-12s | %-20s | %-12s | %-10s | %-14s%n",
                "Ma NV", "Ho", "Ten", "Ngay Sinh", "Gioi Tinh", "So Dien Thoai");
        System.out.println("-----------------------------------------------------------------------------------------");
    }

    public static void inChanBangNhanVien() {
        System.out.println("=========================================================================================");
    }

    public static void inTieuDeHanhKhach() {
        System.out.printf("%-6s | %-12s | %-20s | %-12s | %-10s | %-14s%n",
                "Ma HK", "Ho", "Ten", "Ngay Sinh", "Gioi Tinh", "So Dien Thoai");
        System.out.println("-----------------------------------------------------------------------------------------");
    }

    public static void inChanBangHanhKhach() {
        System.out.println("=========================================================================================");
    }

    public static void inTieuDePhuongTien() {
        System.out.printf("%-15s | %-25s | %-15s | %-20s%n",
                "Ma Phuong Tien", "Ten Phuong Tien", "So Cho Toi Da", "Gia Moi Nguoi/Ngay");
        System.out.println("------------------------------------------------------------------------------------");
    }

    public static void inChanBangPhuongTien() {
        System.out.println("====================================================================================");
    }

    public static void inTieuDeKhachSan() {
        System.out.printf("%-15s | %-30s | %-18s | %-18s%n",
                "Ma Khach San", "Ten Khach San", "Gia O/Dem", "Gia An/Ngay");
        System.out.println("------------------------------------------------------------------------------------------");
    }

    public static void inChanBangKhachSan() {
        System.out.println("==========================================================================================");
    }

    public static void inTieuDeVe() {
        System.out.printf("%-6s | %-8s | %-12s | %-12s | %-13s | %-12s | %-12s | %-15s%n",
                "Ma Ve", "Ma Nhom", "Ma Nguoi Dat", "Ma Ke Hoach", "Ma Hanh Khach", "Ngay Dat", "Don Gia", "Trang Thai");
        System.out.println("---------------------------------------------------------------------------------------------------------------");
    }

    public static void inChanBangVe() {
        System.out.println("===============================================================================================================");
    }

    public static void inTieuDeNhomDat() {
        System.out.printf("%-10s | %-24s | %-26s | %-12s | %-8s | %-15s | %-25s%n",
                "Ma nhom", "Nguoi dat", "Tour", "Ngay dat", "So ve", "Tong tien", "Trang thai");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public static void inTieuDeVeNguoiDung() {
        System.out.printf("%-6s | %-8s | %-8s | %-24s | %-12s | %-12s | %-15s%n",
                "Ma Ve", "Ma KH", "Ma HK", "Ten Hanh Khach", "Ngay Dat", "Don Gia", "Trang Thai");
        System.out.println("-------------------------------------------------------------------------------------------------------");
    }

    public static void inChanBangVeNguoiDung() {
        System.out.println("=======================================================================================================");
    }

    public static void inTieuDeHoaDon() {
        System.out.printf("%-6s | %-8s | %-6s | %-12s | %-14s | %-14s | %-14s | %-12s%n",
                "Ma HD", "Ma Nhom", "Ma NV", "Ngay Lap", "Tong Tien", "Da Thanh Toan", "Con Lai", "Trang Thai");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
    }

    public static void inChanBangHoaDon() {
        System.out.println("===========================================================================================================");
    }
}
