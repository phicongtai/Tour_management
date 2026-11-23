package giaodien;

import datvevathanhtoan.HoaDon;
import kho.KhoDuLieu;

import java.util.Scanner;

public class Quanlyhoadon {
    private static final Scanner in = new Scanner(System.in);
    public static void mainQuanlyhoadon() {
        int chose;
        do {
            System.out.println("\n════════ HOA DON VA THANH TOAN ════════");
            System.out.println("1. Xem danh sach hoa don");
            System.out.println("2. Tim hoa don theo ma hoa don");
            System.out.println("3. Tim hoa don theo ma nhom ve");
            System.out.println("4. Ghi nhan thanh toan");
            System.out.println("5. Hoa don chua thanh toan du");
            System.out.println("6. Xoa hoa don");
            System.out.println("0. Quay lai");
            System.out.println("========================================");
            System.out.print("Chon: ");
            chose = in.nextInt();
            in.nextLine();

            switch (chose) {
                case 1:
                    System.out.println("\n-------- DANH SACH HOA DON --------");
                    int soLuong = KhoDuLieu.getDanhSachHoaDon().getSoLuong();
                    if (soLuong == 0) {
                        System.out.println("Chua co hoa don nao.");
                    } else {
                        TieuDeBang.inTieuDeHoaDon();
                        KhoDuLieu.getDanhSachHoaDon().xuat();
                        TieuDeBang.inChanBangHoaDon();
                        System.out.println("Tong so hoa don: " + soLuong);
                    }
                    System.out.println("\n>> Nhan Enter de tiep tuc...");
                    in.nextLine();
                    break;

                case 2:
                    System.out.println("\n-------- TIM HOA DON THEO MA HOA DON --------");
                    int maHD;
                    HoaDon hdTim;
                    do {
                        System.out.print("Nhap ma hoa don can tim: ");
                        maHD = in.nextInt();
                        in.nextLine();
                        hdTim = KhoDuLieu.getDanhSachHoaDon().timTheoMa(maHD);
                        if (hdTim == null) {
                            System.out.println("Khong tim thay hoa don voi ma " + maHD + "! Vui long nhap lai.");
                        }
                    } while (hdTim == null);
                    TieuDeBang.inTieuDeHoaDon();
                    hdTim.xuat();
                    System.out.println("\n>> Nhan Enter de tiep tuc...");
                    in.nextLine();
                    break;

                case 3:
                    System.out.println("\n-------- TIM HOA DON THEO MA NHOM VE --------");
                    int maNhom;
                    HoaDon[] dsKetQua;
                    do {
                        System.out.print("Nhap ma nhom ve can tim: ");
                        maNhom = in.nextInt();
                        in.nextLine();
                        dsKetQua = KhoDuLieu.getDanhSachHoaDon().timTheoMaDonDatTour(maNhom);
                        if (dsKetQua.length == 0) {
                            System.out.println("Khong tim thay hoa don thuoc nhom ve " + maNhom + "! Vui long nhap lai.");
                        }
                    } while (dsKetQua.length == 0);
                    TieuDeBang.inTieuDeHoaDon();
                    for (int i = 0; i < dsKetQua.length; i++) {
                        dsKetQua[i].xuat();
                    }
                    System.out.println("\n>> Nhan Enter de tiep tuc...");
                    in.nextLine();
                    break;

                case 4:
                    System.out.println("\n-------- GHI NHAN THANH TOAN --------");
                    int maHDTT;
                    HoaDon hdTT;
                    do {
                        System.out.print("Nhap ma hoa don can thanh toan: ");
                        maHDTT = in.nextInt();
                        in.nextLine();
                        hdTT = KhoDuLieu.getDanhSachHoaDon().timTheoMa(maHDTT);
                        if (hdTT == null) {
                            System.out.println("Khong tim thay hoa don voi ma " + maHDTT + "! Vui long nhap lai.");
                        }
                    } while (hdTT == null);

                    TieuDeBang.inTieuDeHoaDon();
                    hdTT.xuat();

                    if (hdTT.daThanhToanDu()) {
                        System.out.println("Hoa don nay da thanh toan du!");
                    } else {
                        boolean ok;
                        do {
                            System.out.print("Nhap so tien thanh toan (toi da " + hdTT.tinhSoTienConLai() + "): ");
                            long soTien = in.nextLong();
                            in.nextLine();
                            ok = hdTT.ghiNhanThanhToan(soTien, KhoDuLieu.getDanhSachVe());
                            if (!ok) {
                                System.out.println("So tien khong hop le (phai > 0 va <= " + hdTT.tinhSoTienConLai() + ")! Vui long nhap lai.");
                            }
                        } while (!ok);

                        System.out.println("Da ghi nhan thanh toan thanh cong!");
                        TieuDeBang.inTieuDeHoaDon();
                        hdTT.xuat();
                    }
                    System.out.println("\n>> Nhan Enter de tiep tuc...");
                    in.nextLine();
                    break;

                case 5:
                    System.out.println("\n-------- HOA DON CHUA THANH TOAN DU --------");
                    HoaDon[] dsChuaDu = KhoDuLieu.getDanhSachHoaDon().timChuaThanhToanDu(KhoDuLieu.getDanhSachVe());
                    if (dsChuaDu.length == 0) {
                        System.out.println("Tat ca hoa don deu da thanh toan du.");
                    } else {
                        TieuDeBang.inTieuDeHoaDon();
                        for (int i = 0; i < dsChuaDu.length; i++) {
                            dsChuaDu[i].xuat();
                        }
                        System.out.println("Tong so hoa don chua thanh toan du: " + dsChuaDu.length);
                    }
                    System.out.println("\n>> Nhan Enter de tiep tuc...");
                    in.nextLine();
                    break;

                case 6:
                    System.out.println("\n-------- XOA HOA DON --------");
                    int maHDXoa;
                    HoaDon hdXoa;
                    do {
                        System.out.print("Nhap ma hoa don can xoa: ");
                        maHDXoa = in.nextInt();
                        in.nextLine();
                        hdXoa = KhoDuLieu.getDanhSachHoaDon().timTheoMa(maHDXoa);
                        if (hdXoa == null) {
                            System.out.println("Khong tim thay hoa don voi ma " + maHDXoa + "! Vui long nhap lai.");
                        }
                    } while (hdXoa == null);

                    TieuDeBang.inTieuDeHoaDon();
                    hdXoa.xuat();
                    System.out.print("Xac nhan xoa hoa don nay? (y/n): ");
                    String xacNhan = in.nextLine().trim();
                    if (xacNhan.equalsIgnoreCase("y")) {
                        if (KhoDuLieu.getDanhSachHoaDon().xoaTheoMa(maHDXoa)) {
                            System.out.println("Da xoa hoa don ma " + maHDXoa + " thanh cong!");
                        } else {
                            System.out.println("Xoa hoa don that bai!");
                        }
                    } else {
                        System.out.println("Da huy thao tac.");
                    }
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
