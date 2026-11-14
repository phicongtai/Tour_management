package giaodien;

import kho.KhoDuLieu;
import tochuctour.KeHoachTour;

import java.util.Scanner;

public class Quanlykehoachtour {
    private static void inTieuDeBangKeHoach() {
        System.out.printf("%-18s | %-26s | %-18s | %-12s | %-12s | %-18s%n",
                "Ma Ke Hoach Tour", "Ten Tour", "Nhan Vien", "Ngay Di", "Ngay Ve", "So Luong Ve Toi Da");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
    }

    public static void mainQuanlykehoachtour() {
        Scanner in = KhoDuLieu.getScanner();
        int chose;
        do {
            System.out.println("\n================ QUẢN LÝ KẾ HOẠCH TOUR ================");
            System.out.println("1. Xem danh sách\n2. Thêm\n3. Tìm theo mã\n4. Tìm theo mã tour");
            System.out.println("5. Sửa\n6. Xóa\n7. Nhập nhiều");
            System.out.println("8. Quản lý lịch trình ngày\n9. Xem chỗ còn\n10. Tính chi phí");
            System.out.println("0. Quay lại");
            System.out.println("=======================================================");
            System.out.print("Chọn: ");
            chose = in.nextInt();
            in.nextLine();

            switch (chose) {
                case 1:
                    System.out.println("\n-------- DANH SÁCH TẤT CẢ KẾ HOẠCH TOUR --------");
                    inTieuDeBangKeHoach();
                    KhoDuLieu.getDanhSachKeHoachTour().xuat();
                    System.out.println("=======================================================================================================================");
                    System.out.println("\n>> Nhấn Enter để tiếp tục...");
                    in.nextLine();
                    break;
                case 2:
                    System.out.println("\n-------- THEM KE HOACH TOUR --------");
                    int viTriMoi = KhoDuLieu.getDanhSachKeHoachTour().getSoLuong();
                    KhoDuLieu.getDanhSachKeHoachTour().them();
                    System.out.println("ĐÃ THÊM KẾ HOẠCH TOUR:");
                    inTieuDeBangKeHoach();
                    KhoDuLieu.getDanhSachKeHoachTour().getDanhSach()[viTriMoi].xuat();
                    System.out.println("\n>> Nhấn Enter để tiếp tục...");
                    in.nextLine();
                    break;
                case 3:
                    System.out.println("\n-------- TIM THEO MA KE HOACH --------");
                    int ma;
                    KeHoachTour temp1;
                    do {
                        System.out.print("Vui long nhap ma ke hoach can tim: ");
                        ma = in.nextInt();
                        in.nextLine();
                        temp1 = KhoDuLieu.getDanhSachKeHoachTour().timTheoMa(ma);
                        if (temp1 == null) {
                            System.out.println("Ma khong ton tai vui long nhap lai!");
                        }
                    } while (temp1 == null);
                    System.out.println("\n-------- KET QUA TIM KIEM --------");
                    inTieuDeBangKeHoach();
                    temp1.xuat();
                    System.out.println("\n>> Nhấn Enter để tiếp tục...");
                    in.nextLine();
                    break;
                case 4:
                    System.out.println("\n-------- TIM THEO MA TOUR --------");
                    int maTour;
                    KeHoachTour[] dsKetQua;
                    do {
                        System.out.print("Vui long nhap ma tour can tim: ");
                        maTour = in.nextInt();
                        in.nextLine();
                        dsKetQua = KhoDuLieu.getDanhSachKeHoachTour().timTheoMaTour(maTour);
                        if (dsKetQua.length == 0) {
                            System.out.println("Khong co ke hoach nao cho ma tour nay, vui long nhap lai!");
                        }
                    } while (dsKetQua.length == 0);
                    System.out.println("\n-------- KET QUA TIM KIEM --------");
                    inTieuDeBangKeHoach();
                    for (KeHoachTour kh : dsKetQua) {
                        if (kh != null) {
                            kh.xuat();
                        }
                    }
                    System.out.println("\n>> Nhấn Enter để tiếp tục...");
                    in.nextLine();
                    break;
                case 5:
                    System.out.println("\n-------- SUA KE HOACH TOUR --------");
                    int ma1;
                    KeHoachTour temp2;
                    do {
                        System.out.print("Vui long nhap ma ke hoach can chinh sua: ");
                        ma1 = in.nextInt();
                        in.nextLine();
                        temp2 = KhoDuLieu.getDanhSachKeHoachTour().timTheoMa(ma1);
                        if (temp2 == null) {
                            System.out.println("Ma khong ton tai vui long nhap lai!");
                        }
                    } while (temp2 == null);
                    System.out.println("\n-------- THONG TIN BAN DAU --------");
                    inTieuDeBangKeHoach();
                    temp2.xuat();
                    System.out.println("\n-------- CHINH SUA THONG TIN --------");
                    KhoDuLieu.getDanhSachKeHoachTour().suaTheoMa(ma1);
                    System.out.println("\n-------- THONG TIN SAU KHI SUA --------");
                    inTieuDeBangKeHoach();
                    temp2.xuat();
                    System.out.println("\n>> Nhấn Enter để tiếp tục...");
                    in.nextLine();
                    break;
                case 6:
                    System.out.println("\n-------- XOA THEO MA --------");
                    int ma2;
                    KeHoachTour temp3;
                    do {
                        System.out.print("Vui long nhap ma can xoa: ");
                        ma2 = in.nextInt();
                        in.nextLine();
                        temp3 = KhoDuLieu.getDanhSachKeHoachTour().timTheoMa(ma2);
                        if (temp3 == null) {
                            System.out.println("Ma khong ton tai vui long nhap lai!");
                        }
                    } while (temp3 == null);
                    KhoDuLieu.getDanhSachKeHoachTour().xoaTheoMa(ma2);
                    System.out.println("XOA THANH CONG!");
                    System.out.println("\n>> Nhấn Enter để tiếp tục...");
                    in.nextLine();
                    break;
                case 7:
                    System.out.println("\n-------- NHAP NHIEU KE HOACH TOUR --------");
                    System.out.print("Nhap so luong ke hoach can them: ");
                    int soLuongThem = in.nextInt();
                    in.nextLine();
                    if (soLuongThem <= 0) {
                        System.out.println("So luong phai lon hon 0!");
                        break;
                    }
                    int viTriBatDau = KhoDuLieu.getDanhSachKeHoachTour().getSoLuong();
                    for (int i = 0; i < soLuongThem; i++) {
                        System.out.println("\n--- Nhap ke hoach thu " + (i + 1) + "/" + soLuongThem + " ---");
                        KhoDuLieu.getDanhSachKeHoachTour().them();
                    }
                    System.out.println("\nDA THEM THANH CONG " + soLuongThem + " KE HOACH TOUR:");
                    inTieuDeBangKeHoach();
                    KeHoachTour[] dsKh = KhoDuLieu.getDanhSachKeHoachTour().getDanhSach();
                    for (int i = viTriBatDau; i < KhoDuLieu.getDanhSachKeHoachTour().getSoLuong(); i++) {
                        if (dsKh[i] != null) {
                            dsKh[i].xuat();
                        }
                    }
                    System.out.println("\n>> Nhấn Enter để tiếp tục...");
                    in.nextLine();
                    break;
                case 8:
                    // TODO: Quản lý lịch trình ngày
                    break;
                case 9:
                    // TODO: Xem chỗ còn
                    break;
                case 10:
                    // TODO: Tính chi phí
                    break;
                case 0:
                    System.out.println("Quay lại menu quản lý...");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ! Vui lòng chọn lại.");
                    break;
            }
        } while (chose != 0);
    }
}
