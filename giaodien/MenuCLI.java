package giaodien;
import java.util.Scanner;
import dulieu.DocGhiFile;
import giaodien.*;
import kho.KhoDuLieu;
public class MenuCLI {
    public static void main(String[] args) {
        DocGhiFile docGhi = new DocGhiFile();
        docGhi.docTatCa();

        Scanner in = kho.KhoDuLieu.getScanner();
        int chose;
        do{
            System.out.println("HE THONG QUAN LY TOUR DU LICH");
            System.out.println("1. Nguoi dung (khach hang) ");
            System.out.println("2. Quan ly (nhan vien)");
            System.out.println("0. Thoat  ");
            System.out.print("Chon: ");
            chose = in.nextInt();
            in.nextLine();
            if(chose < 0 || chose > 2 ){
                System.out.print("\n Vui long chon lai!\n");
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
                System.out.println("Da luu du lieu. Dang thoat chuong trinh...");
                KhoDuLieu.dongScanner();
                System.exit(0);
            }
        }while(chose != 0);
    }
}

