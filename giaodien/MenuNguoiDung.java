package giaodien;
import nguoidung.*;
import tochuctour.KhachSan;
import kho.*;
import java.util.Scanner;

import kho.*;
public class MenuNguoiDung {
    static Scanner in = kho.KhoDuLieu.getScanner();
    static nguoidung.HanhKhach temp;

    public static  nguoidung.HanhKhach case1(){
        nguoidung.HanhKhach temp1 = new HanhKhach();
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

        if(chose == 1){
            int chose1;
            System.out.print("Vui lòng nhập mã khách hàng: ");
            chose1 = in.nextInt();
            temp1 = kho.KhoDuLieu.getDanhSachHanhKhach().timTheoMa(chose1);
            if(temp1 != null){
                System.out.println("Xin chào "+ temp.getHoTen());
                return temp1;
            }else{
                System.out.println(">> Không tìm thấy khách hàng có mã: " + chose1);
                case1();
            }
        }else if(chose == 2){
            temp1.nhap();
            kho.KhoDuLieu.getDanhSachHanhKhach().them(temp1);
            System.out.println(">> Đăng ký thành công! Xin chào " + temp1.getHoTen());
            return temp1;
        }else if(chose == 3){
            int chose1;
            System.out.print("Vui lòng nhập mã khách hàng: ");
            chose1 = in.nextInt();
            kho.KhoDuLieu.getDanhSachHanhKhach().suaTheoMa(chose1);
            temp1 = kho.KhoDuLieu.getDanhSachHanhKhach().timTheoMa(chose1);
            return temp1;
        }else{
            System.out.println(">> Sửa thất bại hoặc không tìm thấy mã!");
        }
        return null;
    }



    public static void menunguoidung(){
        int chose;
        do{
        System.out.println("════════ KHÁCH HÀNG ════════");
        if(temp == null){
            System.out.println("Tài khoản: [Chưa đăng nhập]");
        }else{
            System.out.println("Tài khoản: " + temp.getHoTen());
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
            case 1:
                HanhKhach kh = case1();
                if(kh != null){
                    temp = kh;
                }
                break;
            case 2:

            default:
            System.out.println("Lựa chọn không hợp lệ!");
        }
        }while(chose != 0);
    }
}
