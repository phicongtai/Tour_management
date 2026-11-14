package giaodien;

import kho.KhoDuLieu;
import tour.Tour;
import tour.TourNuocNgoai;
import tour.TourTrongNuoc;

import java.util.Scanner;

public class Quanlytour {
    private static void inTieuDeBangTour() {
        System.out.printf("%-7s | %-26s | %-14s | %-12s | %-7s | %-14s | %-12s | %-12s | %-10s | %-10s | %-10s%n",
                "Ma Tour", "Ten Tour", "Diem Khoi Hanh", "Diem Den", "So Ngay", "Don Gia Co Ban",
                "Loai Tour", "Vung/QuocGia", "Phu Phi", "Can Visa", "Phi Visa");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public static void mainQuanlytour() {
        Scanner in = KhoDuLieu.getScanner();
        int chose;
        do {
            System.out.println("\n================ QUẢN LÝ TOUR ================");
            System.out.println("1. Xem danh sách\n2.Thêm\n3. Tìm theo mã\n4. Tìm theo tên");
            System.out.println("5. Sửa\n6. Xóa\n7. Nhập nhiều\n8. Xem giá vé");
            System.out.println("0. Quay lại");
            System.out.println("==============================================");
            System.out.print("Chọn: ");
            chose = in.nextInt();
            in.nextLine();

            switch (chose) {
                case 1:
                    System.out.println("\n-------- DANH SÁCH TẤT CẢ TOUR --------");
                    inTieuDeBangTour();
                    KhoDuLieu.getDanhSachTour().xuat();
                    System.out.println("===========================================================================================================================================================");
                    System.out.println("\n>> Nhấn Enter để tiếp tục...");
                    in.nextLine();
                    break;
                case 2:
                    System.out.println("\n-------- THEM TOUR --------");
                    System.out.println("Chọn loại tour: 1. Tour trong nước | 2. Tour nước ngoài");
                    System.out.print("Chọn: ");
                    int loai = in.nextInt();
                    in.nextLine();
                    Tour temp;
                    if (loai == 1) {
                        temp = new TourTrongNuoc();
                    } else if (loai == 2) {
                        temp = new TourNuocNgoai();
                    } else {
                        System.out.println("Loại tour không hợp lệ!");
                        break;
                    }
                    temp.nhap();
                    KhoDuLieu.getDanhSachTour().them(temp);
                    System.out.println("ĐÃ THÊM TOUR:");
                    inTieuDeBangTour();
                    temp.xuat();
                    System.out.println("\n>> Nhấn Enter để tiếp tục...");
                    in.nextLine();
                    break;
                case 3:
                    System.out.println("\n-------- TIM THEO MA --------");
                    int ma;
                    Tour temp1;
                    do{
                    System.out.print("Vui long nhap ma cam tim: ");
                    ma = in.nextInt();
                    if(kho.KhoDuLieu.getDanhSachTour().timTheoMa(ma) == null){
                        System.out.println("Ma khong ton tai vui long nhap lai!");
                    }else{
                        temp1 = kho.KhoDuLieu.getDanhSachTour().timTheoMa(ma);
                    }
                }while(kho.KhoDuLieu.getDanhSachTour().timTheoMa(ma) == null);
                    System.out.println("\n-------- TOUR --------");
                    temp1.xuat();
                    
                    break;
                case 4:
                    // TODO: Tìm tour theo tên
                    break;
                case 5:
                    // TODO: Sửa tour
                    break;
                case 6:
                    // TODO: Xóa tour
                    break;
                case 7:
                    // TODO: Nhập nhiều tour
                    break;
                case 8:
                    // TODO: Xem giá vé tour
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
