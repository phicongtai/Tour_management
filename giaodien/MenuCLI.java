package giaodien;
import java.util.Scanner;
import dulieu.DocGhiFile;
import giaodien.*;
import kho.KhoDuLieu;
public class MenuCLI {
    public MenuCLI() {
    }

    public static void main(String[] args) {
        DocGhiFile docGhi = new DocGhiFile();
        docGhi.docTatCa();

        Scanner in = kho.KhoDuLieu.getScanner();
        int chose;
        do{
            System.out.println("HỆ THỐNG QUẢN LÝ TOUR DU LỊCH");
            System.out.println("1. Người dùng (khách hàng) ");
            System.out.println("2. Quản lý (nhân viên)");
            System.out.println("0. Thoát  ");
            System.out.print("Chọn: ");
            chose = in.nextInt();
            in.nextLine();
            if(chose < 0 || chose > 2 ){
                System.out.print("\n Vui long chọn lại!\n");
                continue;
            }

            if(chose == 1){
                System.out.print("\033[H\033[2J");
                System.out.flush();
                giaodien.MenuNguoiDung.menunguoidung();
            }else if(chose == 2){
                System.out.print("\033[H\033[2J");
                System.out.flush();
                giaodien.MenuQuanLy.menuquanly();
            }else{
                System.out.print("\033[H\033[2J");
                System.out.flush();
                docGhi.ghiTatCa();
                System.out.println("Đã lưu dữ liệu. Đang thoát chương trình...");
                System.exit(0);
            }
        }while(chose != 0);
    }
}

