package giaodien;

import kho.KhoDuLieu;
import tochuctour.ChiTietKeHoachTour;
import tochuctour.KeHoachTour;
import tour.Tour;

import java.util.Scanner;

public class Quanlykehoachtour {
    private static final Scanner in = new Scanner(System.in);
    public static void mainQuanlykehoachtour() {
        int chose;
        do {
            System.out.println("\n================ QUAN LY KE HOACH TOUR ================");
            System.out.println("1. Xem danh sach\n2. Them\n3. Tim theo ma\n4. Tim theo ma tour");
            System.out.println("5. Sua\n6. Xoa\n7. Nhap nhieu");
            System.out.println("8. Tinh chi phi");
            System.out.println("0. Quay lai");
            System.out.println("=======================================================");
            System.out.print("Chon: ");
            chose = in.nextInt();
            in.nextLine();

            switch (chose) {
                case 1:
                    System.out.println("\n-------- DANH SACH TAT CA KE HOACH TOUR --------");
                    TieuDeBang.inTieuDeKeHoach();
                    KhoDuLieu.getDanhSachKeHoachTour().xuat();
                    TieuDeBang.inChanBangKeHoach();
                    System.out.println("\n>> Nhan Enter de tiep tuc...");
                    in.nextLine();
                    break;
                case 2:
                    System.out.println("\n-------- THEM KE HOACH TOUR --------");
                    int viTriMoi = KhoDuLieu.getDanhSachKeHoachTour().getSoLuong();
                    KhoDuLieu.getDanhSachKeHoachTour().them();
                    System.out.println("DA THEM KE HOACH TOUR:");
                    TieuDeBang.inTieuDeKeHoach();
                    KhoDuLieu.getDanhSachKeHoachTour().getDanhSach()[viTriMoi].xuat();
                    System.out.println("\n>> Nhan Enter de tiep tuc...");
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
                    TieuDeBang.inTieuDeKeHoach();
                    temp1.xuat();
                    System.out.println("\n>> Nhan Enter de tiep tuc...");
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
                    TieuDeBang.inTieuDeKeHoach();
                    for (KeHoachTour kh : dsKetQua) {
                        if (kh != null) {
                            kh.xuat();
                        }
                    }
                    System.out.println("\n>> Nhan Enter de tiep tuc...");
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
                    TieuDeBang.inTieuDeKeHoach();
                    temp2.xuat();
                    System.out.println("\n-------- CHINH SUA THONG TIN --------");
                    KhoDuLieu.getDanhSachKeHoachTour().suaTheoMa(ma1);
                    System.out.println("\n-------- THONG TIN SAU KHI SUA --------");
                    TieuDeBang.inTieuDeKeHoach();
                    temp2.xuat();
                    System.out.println("\n>> Nhan Enter de tiep tuc...");
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
                    System.out.println("\n>> Nhan Enter de tiep tuc...");
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
                    TieuDeBang.inTieuDeKeHoach();
                    KeHoachTour[] dsKh = KhoDuLieu.getDanhSachKeHoachTour().getDanhSach();
                    for (int i = viTriBatDau; i < KhoDuLieu.getDanhSachKeHoachTour().getSoLuong(); i++) {
                        if (dsKh[i] != null) {
                            dsKh[i].xuat();
                        }
                    }
                    System.out.println("\n>> Nhan Enter de tiep tuc...");
                    in.nextLine();
                    break;
                case 8:
                    System.out.println("\n-------- TINH CHI PHI KE HOACH TOUR --------");
                    int maKhChiPhi;
                    KeHoachTour khChiPhi;
                    do {
                        System.out.print("Vui long nhap ma ke hoach tour: ");
                        maKhChiPhi = in.nextInt();
                        in.nextLine();
                        khChiPhi = KhoDuLieu.getDanhSachKeHoachTour().timTheoMa(maKhChiPhi);
                        if (khChiPhi == null) {
                            System.out.println("Ma khong ton tai vui long nhap lai!");
                        }
                    } while (khChiPhi == null);

                    System.out.println("\n-------- THONG TIN KE HOACH TOUR --------");
                    TieuDeBang.inTieuDeKeHoach();
                    khChiPhi.xuat();

                    if (khChiPhi.getSoLuongChiTiet() > 0) {
                        System.out.println("\n-------- CHI TIET LICH TRINH NGAY --------");
                        TieuDeBang.inTieuDeChiTietKeHoach();
                        ChiTietKeHoachTour[] dsChiTiet = khChiPhi.getChiTietLichTrinh();
                        for (int i = 0; i < khChiPhi.getSoLuongChiTiet(); i++) {
                            if (dsChiTiet[i] != null) {
                                dsChiTiet[i].xuat();
                            }
                        }
                    } else {
                        System.out.println("\nKe hoach nay chua co chi tiet lich trinh (chi phi lich trinh = 0).");
                    }

                    long chiPhi1Khach = Math.max(0, khChiPhi.tinhTongChiPhi(1));
                    Tour tourLienQuan = KhoDuLieu.getDanhSachTour().timTheoMa(khChiPhi.getMaTour());

                    System.out.println("\n-------- KET QUA TINH CHI PHI (1 KHACH) --------");
                    System.out.printf("Chi phi lich trinh / 1 khach          : %d VND%n", chiPhi1Khach);
                    if (tourLienQuan != null) {
                        long donGiaVe = khChiPhi.tinhDonGiaVe(tourLienQuan);
                        System.out.printf("Don gia ve / 1 khach (Tour + Chi phi) : %d VND%n", donGiaVe);
                    }
                    System.out.println("================================================");
                    System.out.println("\n>> Nhan Enter de tiep tuc...");
                    in.nextLine();
                    break;
                case 0:
                    System.out.println("Quay lai menu quan ly...");
                    break;
                default:
                    System.out.println("Lua chon khong hop le! Vui long chon lai.");
                    break;
            }
        } while (chose != 0);
    }
}
