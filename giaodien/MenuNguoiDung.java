package giaodien;

import java.util.*;
import danhsach.*;
import tour.*;
import tochuctour.*;
import nguoidung.*;
import datvevathanhtoan.*;
import kho.*;
import baocao.*;
import dulieu.*;

public class MenuNguoiDung {
    private static Scanner in = KhoDuLieu.getScanner();
    private static HanhKhach nguoidung;

    public static HanhKhach case1(){
        HanhKhach temp1 = new HanhKhach();
        int chose;
        do{
            System.out.println("1. Đăng nhập bằng mã hành khách");
            System.out.println("2. Đăng ký mới");
            System.out.println("3. Cập nhật thông tin cá nhân");
            System.out.println("0. Quay lại");
            chose = in.nextInt();
            if(chose < 0 || chose > 3){
                System.out.print("\n Vui long chọn lại!\n");
            }
        }while(chose < 0 || chose > 3);

        switch (chose) {
            case 1:
                int chose1;
                System.out.print("Vui lòng nhập mã khách hàng: ");
                chose1 = in.nextInt();
                temp1 = kho.KhoDuLieu.getDanhSachHanhKhach().timTheoMa(chose1);
                if(temp1 != null){
                    System.out.println("Xin chào "+ temp1.getHoTen());
                    return temp1;
                }else{
                    System.out.println(">> Không tìm thấy khách hàng có mã: " + chose1);
                    return case1();
                }

            case 2:
                in.nextLine();
                temp1.nhap();
                kho.KhoDuLieu.getDanhSachHanhKhach().them(temp1);
                System.out.println(">> Đăng ký thành công! Xin chào " + temp1.getHoTen());
                return temp1;

            case 3:
                int chose3;
                if(nguoidung == null){
                    System.out.println(">> Bạn chưa đăng nhập! Vui lòng chọn 1 để đăng nhập trước khi cập nhật.");
                    in.nextLine();
                    return null;
                }
                chose3 = nguoidung.getMaHanhKhach();
                kho.KhoDuLieu.getDanhSachHanhKhach().suaTheoMa(chose3);
                temp1 = kho.KhoDuLieu.getDanhSachHanhKhach().timTheoMa(chose3);
                return temp1;

            case 0:
                System.out.println(">> Đang quay lại menu chính...");
                return null;

            default:
                return null;
        }
    }

    public static void case2(){
        int chose;
        do{
        System.out.println("1. Xem tất cả tour");
        System.out.println("2. Tìm theo mã");
        System.out.println("3. Tìm theo tên");
        System.out.println("0. Quay lại");
        chose = in.nextInt();
        if(chose < 0 || chose > 3){
            System.out.print("\n Vui long chọn lại!\n");
        }
        switch (chose) {
            case 1:
                System.out.printf("%-7s | %-26s | %-14s | %-12s | %-7s | %-14s | %-12s | %-12s | %-10s | %-10s | %-10s%n",
                        "Ma Tour", "Ten Tour", "Diem Khoi Hanh", "Diem Den", "So Ngay", "Don Gia Co Ban",
                        "Loai Tour", "Vung/QuocGia", "Phu Phi", "Can Visa", "Phi Visa");
                kho.KhoDuLieu.getDanhSachTour().xuat();
                System.out.println("\n>> Nhấn Enter để tiếp tục...");
                in.nextLine();
                in.nextLine();
                break;
            case 2:
                int ma;
                System.out.print("Vui long nhập mã tour: ");
                ma = in.nextInt();
                Tour temp = kho.KhoDuLieu.getDanhSachTour().timTheoMa(ma);
                System.out.println();
                if(temp != null){
                    System.out.printf("%-7s | %-26s | %-14s | %-12s | %-7s | %-14s | %-12s | %-12s | %-10s | %-10s | %-10s%n",
                            "Ma Tour", "Ten Tour", "Diem Khoi Hanh", "Diem Den", "So Ngay", "Don Gia Co Ban",
                            "Loai Tour", "Vung/QuocGia", "Phu Phi", "Can Visa", "Phi Visa");
                    temp.xuat();
                }else{
                    System.out.println("Không tìm thấy tour!");
                }
                break;
            case 3:
                in.nextLine();
                String ten;
                System.out.print("Vui lòng nhập tên: ");
                ten = in.nextLine();
                Tour[] danhsach = kho.KhoDuLieu.getDanhSachTour().timTheoTen(ten);
                if(danhsach != null && danhsach.length > 0 && danhsach[0] != null){
                    System.out.printf("%-7s | %-26s | %-14s | %-12s | %-7s | %-14s | %-12s | %-12s | %-10s | %-10s | %-10s%n",
                            "Ma Tour", "Ten Tour", "Diem Khoi Hanh", "Diem Den", "So Ngay", "Don Gia Co Ban",
                            "Loai Tour", "Vung/QuocGia", "Phu Phi", "Can Visa", "Phi Visa");
                    for(Tour temptour : danhsach){
                        temptour.xuat();
                    }
                }else{
                    System.out.println("Không tồn tại!");
                }
                break;
            case 0:
                System.out.println(">> Đang quay lại menu chính...");
                return;
            default:
                break;
        }
        }while(chose < 0 || chose > 3);
    }

    public static void case3(){
        System.out.print("Vui long nhap ma tour: ");
        int matour = in.nextInt();
        Tour t = kho.KhoDuLieu.getDanhSachTour().timTheoMa(matour);
        if (t == null) {
            System.out.println(">> Khong tim thay tour co ma: " + matour);
            return;
        }
        System.out.printf("%-7s | %-26s | %-14s | %-12s | %-7s | %-14s | %-12s | %-12s | %-10s | %-10s | %-10s%n",
                "Ma Tour", "Ten Tour", "Diem Khoi Hanh", "Diem Den", "So Ngay", "Don Gia Co Ban",
                "Loai Tour", "Vung/QuocGia", "Phu Phi", "Can Visa", "Phi Visa");
        t.xuat();
        System.out.println();
        KeHoachTour[] listkehoachtour = kho.KhoDuLieu.getDanhSachKeHoachTour().timTheoMaTour(matour);
        if (listkehoachtour.length == 0) {
            System.out.println(">> Tour nay hien chua co ke hoach tour nao!");
            return;
        }
        for(KeHoachTour temp : listkehoachtour){
            System.out.printf("%-18s | %-26s | %-18s | %-12s | %-12s | %-18s%n", "Ma Ke Hoach Tour", "Ten Tour", "Ten Nhan Vien", "Ngay Di", "Ngay Ve", "So Luong Ve Toi Da");
            temp.xuat();
            if(temp.getChiTietLichTrinh() != null && temp.getSoLuongChiTiet() > 0){
                System.out.printf("%-12s | %-22s | %-28s | %-12s | %-12s | %-12s%n", "Ngay", "Phuong Tien", "Khach San", "Tien An", "Tien O", "Tien Di Lai");
                for(ChiTietKeHoachTour temp2 : temp.getChiTietLichTrinh()){
                    if(temp2 != null){
                        temp2.xuat();
                    }
                }
            }else{
                System.out.println(">> Chua co chi tiet lich trinh cho ke hoach nay.");
            }
            System.out.println();
        }
    }

    public static void case4(){
        //các dữ liệu chọn 
        int matour;
        int chosekh;
        // kiểm tra xem coi người dùng đăng nhập hay chưa
        if(nguoidung == null){
        System.out.println("Chua dang nhap!");
        nguoidung = case1();
        if (nguoidung == null) {
                return;
        }
    }

        System.out.println("Danh sách tour");
        System.out.printf("%-7s | %-26s | %-14s | %-12s | %-7s | %-14s | %-12s | %-12s | %-10s | %-10s | %-10s%n",
                "Ma Tour", "Ten Tour", "Diem Khoi Hanh", "Diem Den", "So Ngay", "Don Gia Co Ban",
                "Loai Tour", "Vung/QuocGia", "Phu Phi", "Can Visa", "Phi Visa");
        kho.KhoDuLieu.getDanhSachTour().xuat();
        do{
        System.out.print("Chon tour(nhap ma tour vao): ");
        matour = in.nextInt();
        in.nextLine();
        if(kho.KhoDuLieu.getDanhSachTour().timTheoMa(matour) == null){
            System.out.println("Ma tour khong ton tai vui long chon lai!");
        }
        }while(kho.KhoDuLieu.getDanhSachTour().timTheoMa(matour) == null);
        KeHoachTour[] dskh = kho.KhoDuLieu.getDanhSachKeHoachTour().timTheoMaTour(matour);
        if(dskh.length == 0){
            System.out.println("Tour Chưa có kế hoạch vui lòng chọn tour khac!");
            case4();
            return;
        }
        for(KeHoachTour temp : dskh){
            System.out.printf("%-18s | %-26s | %-18s | %-12s | %-12s | %-18s%n", "Ma Ke Hoach Tour", "Ten Tour", "Ten Nhan Vien", "Ngay Di", "Ngay Ve", "So Luong Ve Toi Da");
            temp.xuat();
            if(temp.getChiTietLichTrinh() != null && temp.getSoLuongChiTiet() > 0){
                System.out.printf("%-12s | %-22s | %-28s | %-12s | %-12s | %-12s%n", "Ngay", "Phuong Tien", "Khach San", "Tien An", "Tien O", "Tien Di Lai");
                for(ChiTietKeHoachTour temp2 : temp.getChiTietLichTrinh()){
                    if(temp2 != null){
                        temp2.xuat();
                    }
                }
            }
        }
            System.out.print("Chon ke hoach tour: ");
            do{
                chosekh = in.nextInt();
                if(kho.KhoDuLieu.getDanhSachKeHoachTour().timTheoMa(chosekh) == null || kho.KhoDuLieu.getDanhSachKeHoachTour().timTheoMa(chosekh).getMaTour() != matour  ){
                    System.out.println("khong ton tai ke hoach tour nay!");
                }
            }while(kho.KhoDuLieu.getDanhSachKeHoachTour().timTheoMa(chosekh) == null || kho.KhoDuLieu.getDanhSachKeHoachTour().timTheoMa(chosekh).getMaTour() != matour  );
            
            if(KhoDuLieu.getDanhSachVe().daCoVeHopLe(chosekh,nguoidung.getMaHanhKhach())){
                System.out.print("Ban da dat vé rồi!");
                return;
            }
            // lấy số lượng vé tối đa - số lượng vé đã đặt
            int soluongvecon = kho.KhoDuLieu.getDanhSachKeHoachTour().timTheoMa(chosekh).getSoLuongVeToiDa() - kho.KhoDuLieu.getDanhSachVe().demVeChiemChoTheoKeHoach(chosekh);
            if(soluongvecon <= 0){
                System.out.println("Kế hoạch tour này đã hết chỗ vui lọng chọn tour khoac kế haoch tour khác!");
                case4();
                return;
            }
            int chosesoluongve;
            do{
            System.out.println("chọn số luong vé: ");

            chosesoluongve = in.nextInt();
            if(chosesoluongve > soluongvecon || chosesoluongve <= 0){
                System.out.println("Không đủ chỗ! Chỉ còn" + soluongvecon + "chỗ.");
            }
            }while(chosesoluongve > soluongvecon || chosesoluongve <= 0);

            
        }


    public static void menunguoidung(){
        int chose;
        do{
        System.out.println("════════ KHÁCH HÀNG ════════");
        if(nguoidung == null){
            System.out.println("Tài khoản: [Chưa đăng nhập]");
        }else{
            System.out.println("Tài khoản: " + nguoidung.getHoTen());
        }
        System.out.println("1. Tài khoản (đăng nhập / đăng ký / cập nhật)");
        System.out.println("2. Tìm và xem tour");
        System.out.println("3. Xem kế hoạch và lịch trình");
        System.out.println("4. Đặt vé");
        System.out.println("5. Tra cứu nhóm vé");
        System.out.println("6. Hủy nhóm vé");
        System.out.println(" 0. Quay lại");
        System.out.print("Chọn: ");
        chose = in.nextInt();

        switch (chose) {
            case 0: break;
            case 1:
                HanhKhach kh = case1();
                if(kh != null){
                    nguoidung = kh;
                }
                break;
            case 2:
                case2();
                break;
            case 3:
                case3();
                break;

            default:
            System.out.println("Lựa chọn không hợp lệ!");
        }
        }while(chose != 0);
    }
}
