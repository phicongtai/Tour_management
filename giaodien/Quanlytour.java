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
            System.out.println("5. Sửa\n6. Xóa\n7. Nhập nhiều");
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
                    in.nextLine();
                    temp1 = kho.KhoDuLieu.getDanhSachTour().timTheoMa(ma);
                    if(temp1 == null){
                        System.out.println("Ma khong ton tai vui long nhap lai!");
                    }
                }while(temp1 == null);
                    System.out.println("\n-------- KET QUA TIM KIEM --------");
                    inTieuDeBangTour();
                    temp1.xuat();
                    System.out.println("\n>> Nhấn Enter để tiếp tục...");
                    in.nextLine();
                    break;
                case 4:
                    System.out.println("\n-------- TIM THEO TEN --------");
                    String ten;
                    Tour[] dsKetQua;
                    do {
                        System.out.print("Vui long nhap ten tour can tim: ");
                        ten = in.nextLine();
                        dsKetQua = KhoDuLieu.getDanhSachTour().timTheoTen(ten);
                        if (dsKetQua.length == 0) {
                            System.out.println("Ten tour khong ton tai vui long nhap lai!");
                        }
                    } while (dsKetQua.length == 0);
                    System.out.println("\n-------- KET QUA TIM KIEM --------");
                    inTieuDeBangTour();
                    for (Tour t : dsKetQua) {
                        t.xuat();
                    }
                    System.out.println("\n>> Nhấn Enter để tiếp tục...");
                    in.nextLine();
                    break;
                case 5:
                    System.out.println("\n-------- TIM THEO MA --------");
                    int ma1;
                    Tour temp2;
                    do{
                    System.out.print("Vui long nhap ma tour can chinh sua: ");
                    ma1 = in.nextInt();
                    in.nextLine();
                    temp2 = kho.KhoDuLieu.getDanhSachTour().timTheoMa(ma1);
                    if(temp2 == null){
                        System.out.println("Ma khong ton tai vui long nhap lai!");
                    }
                }while(temp2 == null);
                    System.out.println("\n-------- THONG TIN BAN DAU --------");
                    inTieuDeBangTour();
                    temp2.xuat();
                    System.out.println("\n-------- CHINH SUA THONG TIN --------");
                    kho.KhoDuLieu.getDanhSachTour().suaTheoMa(ma1);
                    System.out.println("\n-------- THONG TIN SAU KHI SUA --------");
                    inTieuDeBangTour();
                    temp2.xuat();
                    System.out.println("\n>> Nhấn Enter để tiếp tục...");
                    in.nextLine();
                    break;
                case 6:
                    System.out.println("\n-------- XOA THEO MA --------");
                    int ma2;
                    Tour temp3;
                    do{
                    System.out.print("Vui long nhap ma cam xoa: ");
                    ma2 = in.nextInt();
                    in.nextLine();
                    temp3 = kho.KhoDuLieu.getDanhSachTour().timTheoMa(ma2);
                    if(temp3 == null){
                        System.out.println("Ma khong ton tai vui long nhap lai!");
                    }
                }while(temp3 == null);
                    kho.KhoDuLieu.getDanhSachTour().xoaTheoMa(ma2);
                    System.out.println("XOA THANH CONG!");
                    System.out.println("\n>> Nhấn Enter để tiếp tục...");
                    in.nextLine();
                    break;
                case 7:
                    System.out.println("\n-------- NHAP NHIEU TOUR --------");
                    System.out.print("Nhap so luong tour can them: ");
                    int soLuongThem = in.nextInt();
                    in.nextLine();
                    if (soLuongThem <= 0) {
                        System.out.println("So luong phai lon hon 0!");
                        break;
                    }
                    int viTriBatDau = KhoDuLieu.getDanhSachTour().getSoLuong();
                    for (int i = 0; i < soLuongThem; i++) {
                        System.out.println("\n--- Nhap tour thu " + (i + 1) + "/" + soLuongThem + " ---");
                        if (!KhoDuLieu.getDanhSachTour().them()) {
                            i--;
                        }
                    }
                    System.out.println("\nDA THEM THANH CONG " + soLuongThem + " TOUR:");
                    inTieuDeBangTour();
                    Tour[] dsTour = KhoDuLieu.getDanhSachTour().getDanhSach();
                    for (int i = viTriBatDau; i < KhoDuLieu.getDanhSachTour().getSoLuong(); i++) {
                        if (dsTour[i] != null) {
                            dsTour[i].xuat();
                        }
                    }
                    System.out.println("\n>> Nhấn Enter để tiếp tục...");
                    in.nextLine();
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
