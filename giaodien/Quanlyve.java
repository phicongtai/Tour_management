package giaodien;

import datvevathanhtoan.Ve;
import kho.KhoDuLieu;
import nguoidung.HanhKhach;
import tochuctour.KeHoachTour;
import tour.Tour;

import java.util.Scanner;

public class Quanlyve {
    public static void mainQuanlyve() {
        Scanner in = KhoDuLieu.getScanner();
        int chose;
        do {
            System.out.println("\n================ QUAN LY VE ================");
            System.out.println("1. Xem danh sach ve\n2. Tim theo ma ve\n3. Tim theo ma nhom ve");
            System.out.println("4. Sua ve\n5. Xoa ve\n6. Nhap nhieu");
            System.out.println("7. Danh sach cac nhom dat\n8. Loc ve theo trang thai\n9. Huy nhom ve");
            System.out.println("0. Quay lai");
            System.out.println("============================================");
            System.out.print("Chon: ");
            chose = in.nextInt();
            in.nextLine();

            switch (chose) {
                case 1:
                    System.out.println("\n-------- DANH SACH TAT CA VE --------");
                    TieuDeBang.inTieuDeVe();
                    KhoDuLieu.getDanhSachVe().xuat();
                    TieuDeBang.inChanBangVe();
                    System.out.println("\n>> Nhan Enter de tiep tuc...");
                    in.nextLine();
                    break;
                case 2:
                    System.out.println("\n-------- TIM THEO MA VE --------");
                    int ma;
                    Ve temp1;
                    do {
                        System.out.print("Vui long nhap ma ve can tim: ");
                        ma = in.nextInt();
                        in.nextLine();
                        temp1 = KhoDuLieu.getDanhSachVe().timTheoMa(ma);
                        if (temp1 == null) {
                            System.out.println("Ma ve khong ton tai vui long nhap lai!");
                        }
                    } while (temp1 == null);
                    System.out.println("\n-------- KET QUA TIM KIEM --------");
                    TieuDeBang.inTieuDeVe();
                    temp1.xuat();
                    System.out.println("\n>> Nhan Enter de tiep tuc...");
                    in.nextLine();
                    break;
                case 3:
                    System.out.println("\n-------- TIM THEO MA NHOM VE --------");
                    int maNhom;
                    Ve[] dsKetQua;
                    do {
                        System.out.print("Vui long nhap ma nhom ve can tim: ");
                        maNhom = in.nextInt();
                        in.nextLine();
                        dsKetQua = KhoDuLieu.getDanhSachVe().timTheoMaDonDatTour(maNhom);
                        if (dsKetQua.length == 0) {
                            System.out.println("Ma nhom ve khong ton tai vui long nhap lai!");
                        }
                    } while (dsKetQua.length == 0);
                    System.out.println("\n-------- KET QUA TIM KIEM --------");
                    TieuDeBang.inTieuDeVe();
                    for (Ve v : dsKetQua) {
                        if (v != null) {
                            v.xuat();
                        }
                    }
                    System.out.println("\n>> Nhan Enter de tiep tuc...");
                    in.nextLine();
                    break;
                case 4:
                    System.out.println("\n-------- SUA VE --------");
                    int ma1;
                    Ve temp2;
                    do {
                        System.out.print("Vui long nhap ma ve can chinh sua: ");
                        ma1 = in.nextInt();
                        in.nextLine();
                        temp2 = KhoDuLieu.getDanhSachVe().timTheoMa(ma1);
                        if (temp2 == null) {
                            System.out.println("Ma ve khong ton tai vui long nhap lai!");
                        }
                    } while (temp2 == null);
                    System.out.println("\n-------- THONG TIN BAN DAU --------");
                    TieuDeBang.inTieuDeVe();
                    temp2.xuat();
                    System.out.println("\n-------- CHINH SUA THONG TIN --------");
                    KhoDuLieu.getDanhSachVe().suaTheoMa(ma1);
                    System.out.println("\n-------- THONG TIN SAU KHI SUA --------");
                    TieuDeBang.inTieuDeVe();
                    temp2.xuat();
                    System.out.println("\n>> Nhan Enter de tiep tuc...");
                    in.nextLine();
                    break;
                case 5:
                    System.out.println("\n-------- XOA VE THEO MA --------");
                    int ma2;
                    Ve temp3;
                    do {
                        System.out.print("Vui long nhap ma ve can xoa: ");
                        ma2 = in.nextInt();
                        in.nextLine();
                        temp3 = KhoDuLieu.getDanhSachVe().timTheoMa(ma2);
                        if (temp3 == null) {
                            System.out.println("Ma ve khong ton tai vui long nhap lai!");
                        }
                    } while (temp3 == null);
                    KhoDuLieu.getDanhSachVe().xoaTheoMa(ma2);
                    System.out.println("XOA THANH CONG!");
                    System.out.println("\n>> Nhan Enter de tiep tuc...");
                    in.nextLine();
                    break;
                case 6:
                    System.out.println("\n-------- NHAP NHIEU VE --------");
                    System.out.print("Nhap so luong ve can them: ");
                    int soLuongThem = in.nextInt();
                    in.nextLine();
                    if (soLuongThem <= 0) {
                        System.out.println("So luong phai lon hon 0!");
                        break;
                    }
                    int viTriBatDau = KhoDuLieu.getDanhSachVe().getSoLuong();
                    for (int i = 0; i < soLuongThem; i++) {
                        System.out.println("\n--- Nhap ve thu " + (i + 1) + "/" + soLuongThem + " ---");
                        KhoDuLieu.getDanhSachVe().them();
                    }
                    System.out.println("\nDA THEM THANH CONG " + soLuongThem + " VE:");
                    TieuDeBang.inTieuDeVe();
                    Ve[] dsVe = KhoDuLieu.getDanhSachVe().getDanhSach();
                    for (int i = viTriBatDau; i < KhoDuLieu.getDanhSachVe().getSoLuong(); i++) {
                        if (dsVe[i] != null) {
                            dsVe[i].xuat();
                        }
                    }
                    System.out.println("\n>> Nhan Enter de tiep tuc...");
                    in.nextLine();
                    break;
                case 7:
                    System.out.println("\n--- DANH SACH CAC NHOM DAT ---");
                    int[] dsMaDon = KhoDuLieu.getDanhSachVe().layDanhSachMaDonDatTour();
                    if (dsMaDon.length == 0) {
                        System.out.println("Chua co nhom dat ve nao.");
                    } else {
                        TieuDeBang.inTieuDeNhomDat();
                        for (int i = 0; i < dsMaDon.length; i++) {
                            int maDon = dsMaDon[i];
                            Ve[] nhomVe = KhoDuLieu.getDanhSachVe().timTheoMaDonDatTour(maDon);
                            if (nhomVe.length == 0) {
                                continue;
                            }
                            Ve veDau = nhomVe[0];
                            HanhKhach hk = KhoDuLieu.getDanhSachHanhKhach().timTheoMa(veDau.getMaNguoiDat());
                            String tenNguoiDat = (hk != null) ? hk.getHoTen() : ("KH #" + veDau.getMaNguoiDat());

                            KeHoachTour kh = KhoDuLieu.getDanhSachKeHoachTour().timTheoMa(veDau.getMaKeHoachTour());
                            String tenTour = "KHT #" + veDau.getMaKeHoachTour();
                            if (kh != null) {
                                Tour t = KhoDuLieu.getDanhSachTour().timTheoMa(kh.getMaTour());
                                if (t != null) {
                                    tenTour = t.getTenTour();
                                }
                            }

                            long tongTien = 0;
                            for (int j = 0; j < nhomVe.length; j++) {
                                if (nhomVe[j].chiemCho()) {
                                    tongTien += nhomVe[j].getDonGia();
                                }
                            }

                            String trangThaiNhom = KhoDuLieu.getDanhSachVe().layTrangThaiNhom(maDon);
                            System.out.printf("%-10d | %-24s | %-26s | %-12s | %-8d | %-15d | %-25s%n",
                                    maDon,
                                    tenNguoiDat,
                                    tenTour,
                                    veDau.getNgayDat(),
                                    nhomVe.length,
                                    tongTien,
                                    trangThaiNhom);
                        }
                        System.out.println("Tong so nhom dat: " + dsMaDon.length);
                    }
                    System.out.println("\n>> Nhan Enter de tiep tuc...");
                    in.nextLine();
                    break;
                case 8:
                    System.out.println("\n--- LOC VE THEO TRANG THAI ---");
                    int ttLoc;
                    while (true) {
                        System.out.println("Chon trang thai (0: Da dat | 1: Da thanh toan | 2: Da huy): ");
                        System.out.print("Nhap lua chon: ");
                        if (in.hasNextInt()) {
                            ttLoc = in.nextInt();
                            in.nextLine();
                            if (ttLoc == Ve.DA_DAT || ttLoc == Ve.DA_THANH_TOAN || ttLoc == Ve.DA_HUY) {
                                break;
                            }
                            System.out.println("Lua chon khong hop le! Vui long nhap 0, 1 hoac 2.");
                        } else {
                            System.out.println("Vui long nhap so nguyen!");
                            in.nextLine();
                        }
                    }
                    Ve[] dsLoc = KhoDuLieu.getDanhSachVe().timTheoTrangThai(ttLoc);
                    if (dsLoc.length == 0) {
                        System.out.println("Khong co ve nao o trang thai nay.");
                    } else {
                        TieuDeBang.inTieuDeVe();
                        for (int i = 0; i < dsLoc.length; i++) {
                            dsLoc[i].xuat();
                        }
                        System.out.println("Tong so ve: " + dsLoc.length);
                    }
                    System.out.println("\n>> Nhan Enter de tiep tuc...");
                    in.nextLine();
                    break;
                case 9:
                    System.out.println("\n--- HUY NHOM VE ---");
                    int maNhomHuy;
                    Ve[] nhomCanHuy;
                    do {
                        System.out.print("Nhap ma nhom ve can huy: ");
                        maNhomHuy = in.nextInt();
                        in.nextLine();
                        nhomCanHuy = KhoDuLieu.getDanhSachVe().timTheoMaDonDatTour(maNhomHuy);
                        if (nhomCanHuy.length == 0) {
                            System.out.println("Khong tim thay nhom ve voi ma " + maNhomHuy + "! Vui long nhap lai.");
                        }
                    } while (nhomCanHuy.length == 0);
                    System.out.println("Trang thai hien tai cua nhom: " + KhoDuLieu.getDanhSachVe().layTrangThaiNhom(maNhomHuy));
                    TieuDeBang.inTieuDeVe();
                    boolean coVeHuyDuoc = false;
                    for (int i = 0; i < nhomCanHuy.length; i++) {
                        nhomCanHuy[i].xuat();
                        if (nhomCanHuy[i].duocHuy()) {
                            coVeHuyDuoc = true;
                        }
                    }
                    if (!coVeHuyDuoc) {
                        System.out.println("Nhom ve nay khong co ve nao co the huy (da thanh toan hoac da huy toan bo).");
                    } else {
                        System.out.print("Xac nhan huy cac ve co the huy trong nhom nay? (y/n): ");
                        String xacNhan = in.nextLine().trim();
                        if (xacNhan.equalsIgnoreCase("y")) {
                            int soVeDaHuy = KhoDuLieu.getDanhSachVe().huyNhomVe(maNhomHuy);
                            System.out.println("Da huy thanh cong " + soVeDaHuy + " ve trong nhom " + maNhomHuy + ".");
                        } else {
                            System.out.println("Da huy thao tac.");
                        }
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
