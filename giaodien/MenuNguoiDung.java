package giaodien;

import java.util.*;
import tour.*;
import tochuctour.*;
import nguoidung.*;
import datvevathanhtoan.*;
import kho.*;

public class MenuNguoiDung {
    private static Scanner in = KhoDuLieu.getScanner();
    private static HanhKhach nguoidung;
    private static Tour tourDaChon;
    private static KeHoachTour keHoachDaChon;

    public static HanhKhach case1(){
        HanhKhach temp1 = null;
        int chose;
        do{
            System.out.println("\n════════ TÀI KHOẢN HÀNH KHÁCH ════════");
            System.out.println("1. Đăng nhập bằng mã hành khách");
            System.out.println("2. Đăng ký mới");
            System.out.println("3. Cập nhật thông tin cá nhân");
            System.out.println("0. Quay lại");
            System.out.println("========================================");
            System.out.print("Chọn: ");
            chose = in.nextInt();
            in.nextLine();
            if(chose < 0 || chose > 3){
                System.out.println("\n>> Vui lòng chọn lại!");
            }
        }while(chose < 0 || chose > 3);

        switch (chose) {
            case 1:
                int chose1;
                System.out.println("\n-------- ĐĂNG NHẬP --------");
                System.out.print("Vui lòng nhập mã khách hàng: ");
                chose1 = in.nextInt();
                in.nextLine();
                temp1 = KhoDuLieu.getDanhSachHanhKhach().timTheoMa(chose1);
                if(temp1 != null){
                    System.out.println(">> Đăng nhập thành công! Xin chào " + temp1.getHoTen());
                    System.out.printf("%-6s | %-24s | %-12s | %-10s | %-14s%n",
                            "Ma HK", "Ho Ten", "Ngay Sinh", "Gioi Tinh", "So Dien Thoai");
                    System.out.println("--------------------------------------------------------------------------------");
                    System.out.printf("%-6d | %-24s | %-12s | %-10s | %-14s%n",
                            temp1.getMaHanhKhach(), temp1.getHoTen(), temp1.getNgaySinh(),
                            (temp1.getGioiTinh() == 1 ? "Nam" : "Nu"), temp1.getSoDienThoai());
                    System.out.println("================================================================================");
                    return temp1;
                }else{
                    System.out.println(">> Không tìm thấy khách hàng có mã: " + chose1);
                    return case1();
                }

            case 2:
                System.out.println("\n-------- ĐĂNG KÝ TÀI KHOẢN MỚI --------");
                temp1 = new HanhKhach();
                temp1.nhap();
                KhoDuLieu.getDanhSachHanhKhach().them(temp1);
                System.out.println(">> Đăng ký thành công! Xin chào " + temp1.getHoTen());
                System.out.printf("%-6s | %-24s | %-12s | %-10s | %-14s%n",
                        "Ma HK", "Ho Ten", "Ngay Sinh", "Gioi Tinh", "So Dien Thoai");
                System.out.println("--------------------------------------------------------------------------------");
                System.out.printf("%-6d | %-24s | %-12s | %-10s | %-14s%n",
                        temp1.getMaHanhKhach(), temp1.getHoTen(), temp1.getNgaySinh(),
                        (temp1.getGioiTinh() == 1 ? "Nam" : "Nu"), temp1.getSoDienThoai());
                System.out.println("================================================================================");
                return temp1;

            case 3:
                int chose3;
                if(nguoidung == null){
                    System.out.println(">> Bạn chưa đăng nhập! Vui lòng chọn 1 để đăng nhập trước khi cập nhật.");
                    return null;
                }
                System.out.println("\n-------- CẬP NHẬT THÔNG TIN CÁ NHÂN --------");
                chose3 = nguoidung.getMaHanhKhach();
                KhoDuLieu.getDanhSachHanhKhach().suaTheoMa(chose3);
                temp1 = KhoDuLieu.getDanhSachHanhKhach().timTheoMa(chose3);
                if(temp1 != null){
                    System.out.println("\n>> Thông tin cá nhân hiện tại:");
                    System.out.printf("%-6s | %-24s | %-12s | %-10s | %-14s%n",
                            "Ma HK", "Ho Ten", "Ngay Sinh", "Gioi Tinh", "So Dien Thoai");
                    System.out.println("--------------------------------------------------------------------------------");
                    System.out.printf("%-6d | %-24s | %-12s | %-10s | %-14s%n",
                            temp1.getMaHanhKhach(), temp1.getHoTen(), temp1.getNgaySinh(),
                            (temp1.getGioiTinh() == 1 ? "Nam" : "Nu"), temp1.getSoDienThoai());
                    System.out.println("================================================================================");
                }
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
        System.out.println("\n════════ TÌM VÀ XEM TOUR ════════");
        System.out.println("1. Xem tất cả tour");
        System.out.println("2. Tìm theo mã");
        System.out.println("3. Tìm theo tên");
        System.out.println("0. Quay lại");
        System.out.println("=================================");
        System.out.print("Chọn: ");
        chose = in.nextInt();
        in.nextLine();
        if(chose < 0 || chose > 3){
            System.out.println("\n>> Vui lòng chọn lại!");
        }
        switch (chose) {
            case 1:
                System.out.println("\n-------- DANH SÁCH TẤT CẢ TOUR --------");
                System.out.printf("%-7s | %-26s | %-14s | %-12s | %-7s | %-14s | %-12s | %-12s | %-10s | %-10s | %-10s%n",
                        "Ma Tour", "Ten Tour", "Diem Khoi Hanh", "Diem Den", "So Ngay", "Don Gia Co Ban",
                        "Loai Tour", "Vung/QuocGia", "Phu Phi", "Can Visa", "Phi Visa");
                System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------");
                KhoDuLieu.getDanhSachTour().xuat();
                System.out.println("===========================================================================================================================================================");
                System.out.println("\n>> Nhấn Enter để tiếp tục...");
                in.nextLine();
                break;
            case 2:
                int ma;
                System.out.println("\n-------- TÌM TOUR THEO MÃ --------");
                System.out.print("Vui long nhập mã tour: ");
                ma = in.nextInt();
                in.nextLine();
                Tour temp = KhoDuLieu.getDanhSachTour().timTheoMa(ma);
                System.out.println();
                if(temp != null){
                    System.out.printf("%-7s | %-26s | %-14s | %-12s | %-7s | %-14s | %-12s | %-12s | %-10s | %-10s | %-10s%n",
                            "Ma Tour", "Ten Tour", "Diem Khoi Hanh", "Diem Den", "So Ngay", "Don Gia Co Ban",
                            "Loai Tour", "Vung/QuocGia", "Phu Phi", "Can Visa", "Phi Visa");
                    System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------");
                    temp.xuat();
                    System.out.println("===========================================================================================================================================================");
                }else{
                    System.out.println(">> Không tìm thấy tour!");
                }
                break;
            case 3:
                String ten;
                System.out.println("\n-------- TÌM TOUR THEO TÊN --------");
                System.out.print("Vui lòng nhập tên: ");
                ten = in.nextLine();
                Tour[] danhsach = KhoDuLieu.getDanhSachTour().timTheoTen(ten);
                if(danhsach != null && danhsach.length > 0 && danhsach[0] != null){
                    System.out.printf("%-7s | %-26s | %-14s | %-12s | %-7s | %-14s | %-12s | %-12s | %-10s | %-10s | %-10s%n",
                            "Ma Tour", "Ten Tour", "Diem Khoi Hanh", "Diem Den", "So Ngay", "Don Gia Co Ban",
                            "Loai Tour", "Vung/QuocGia", "Phu Phi", "Can Visa", "Phi Visa");
                    System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------");
                    for(Tour temptour : danhsach){
                        if(temptour != null){
                            temptour.xuat();
                        }
                    }
                    System.out.println("===========================================================================================================================================================");
                }else{
                    System.out.println(">> Không tồn tại!");
                }
                break;
            case 0:
                System.out.println(">> Đang quay lại menu chính...");
                return;
            default:
                break;
        }
        }while(chose != 0);
    }

    public static void case3(){
        System.out.println("\n════════ XEM KẾ HOẠCH VÀ LỊCH TRÌNH ════════");
        System.out.print("Vui long nhap ma tour: ");
        int matour = in.nextInt();
        in.nextLine();
        Tour t = KhoDuLieu.getDanhSachTour().timTheoMa(matour);
        if (t == null) {
            System.out.println(">> Khong tim thay tour co ma: " + matour);
            return;
        }
        System.out.println("\n-------- THÔNG TIN TOUR --------");
        System.out.printf("%-7s | %-26s | %-14s | %-12s | %-7s | %-14s | %-12s | %-12s | %-10s | %-10s | %-10s%n",
                "Ma Tour", "Ten Tour", "Diem Khoi Hanh", "Diem Den", "So Ngay", "Don Gia Co Ban",
                "Loai Tour", "Vung/QuocGia", "Phu Phi", "Can Visa", "Phi Visa");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------");
        t.xuat();
        System.out.println();
        KeHoachTour[] listkehoachtour = KhoDuLieu.getDanhSachKeHoachTour().timTheoMaTour(matour);
        if (listkehoachtour.length == 0) {
            System.out.println(">> Tour nay hien chua co ke hoach tour nao!");
            return;
        }
        System.out.println("-------- DANH SÁCH KẾ HOẠCH TOUR --------");
        for(KeHoachTour temp : listkehoachtour){
            if(temp == null){
                continue;
            }
            System.out.printf("%-18s | %-26s | %-18s | %-12s | %-12s | %-18s%n", "Ma Ke Hoach Tour", "Ten Tour", "Ten Nhan Vien", "Ngay Di", "Ngay Ve", "So Luong Ve Toi Da");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------");
            temp.xuat();
            if(temp.getChiTietLichTrinh() != null && temp.getSoLuongChiTiet() > 0){
                System.out.printf("%-12s | %-22s | %-28s | %-12s | %-12s | %-12s%n", "Ngay", "Phuong Tien", "Khach San", "Tien An", "Tien O", "Tien Di Lai");
                System.out.println("-----------------------------------------------------------------------------------------------------------------");
                for(ChiTietKeHoachTour temp2 : temp.getChiTietLichTrinh()){
                    if(temp2 != null){
                        temp2.xuat();
                    }
                }
            }else{
                System.out.println(">> Chua co chi tiet lich trinh cho ke hoach nay.");
            }
            System.out.println("=======================================================================================================================");
        }
    }

    public static void case4(){
        int matour;
        int chosekh;
        //xác nhận đang nhập hay chưa nếu chưa thì đăng nhập
        System.out.println("\n════════ ĐẶT VÉ TOUR ════════");
        if(nguoidung == null){
            System.out.println(">> Bạn chưa đăng nhập! Vui lòng đăng nhập hoặc đăng ký.");
            nguoidung = case1();
            if (nguoidung == null) {
                return;
            }
        }
        // xuất các danh sách tour và chọn tour muôn đặt
        System.out.println("\n-------- DANH SÁCH TOUR --------");
        System.out.printf("%-7s | %-26s | %-14s | %-12s | %-7s | %-14s | %-12s | %-12s | %-10s | %-10s | %-10s%n",
                "Ma Tour", "Ten Tour", "Diem Khoi Hanh", "Diem Den", "So Ngay", "Don Gia Co Ban",
                "Loai Tour", "Vung/QuocGia", "Phu Phi", "Can Visa", "Phi Visa");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------");
        KhoDuLieu.getDanhSachTour().xuat();
        System.out.println("===========================================================================================================================================================");

        do{
            System.out.print("Chọn tour (nhập mã tour): ");
            matour = in.nextInt();
            in.nextLine();
            tourDaChon = KhoDuLieu.getDanhSachTour().timTheoMa(matour);
            if(tourDaChon == null){
                System.out.println(">> Mã tour không tồn tại, vui lòng chọn lại!");
            }
        }while(tourDaChon == null);

        KeHoachTour[] dskh = KhoDuLieu.getDanhSachKeHoachTour().timTheoMaTour(matour);
        if(dskh.length == 0){
            System.out.println(">> Tour này chưa có kế hoạch, vui lòng chọn tour khác!");
            return;
        }
        // xuất tất cả các kế hoạch tour và chọn kế hoạc muốn đi
        System.out.println("\n-------- DANH SÁCH KẾ HOẠCH TOUR --------");
        for(KeHoachTour temp : dskh){
            if(temp == null){
                continue;
            }
            System.out.printf("%-18s | %-26s | %-18s | %-12s | %-12s | %-18s%n",
                    "Ma Ke Hoach Tour", "Ten Tour", "Ten Nhan Vien", "Ngay Di", "Ngay Ve", "So Luong Ve Toi Da");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------");
            temp.xuat();
            if(temp.getChiTietLichTrinh() != null && temp.getSoLuongChiTiet() > 0){
                System.out.printf("%-12s | %-22s | %-28s | %-12s | %-12s | %-12s%n",
                        "Ngay", "Phuong Tien", "Khach San", "Tien An", "Tien O", "Tien Di Lai");
                System.out.println("-----------------------------------------------------------------------------------------------------------------");
                for(ChiTietKeHoachTour temp2 : temp.getChiTietLichTrinh()){
                    if(temp2 != null){
                        temp2.xuat();
                    }
                }
            }
            System.out.println("=======================================================================================================================");
        }

        do{
            System.out.print("Chọn kế hoạch tour (nhập mã kế hoạch): ");
            chosekh = in.nextInt();
            in.nextLine();
            keHoachDaChon = KhoDuLieu.getDanhSachKeHoachTour().timTheoMaVaTour(chosekh, matour);
            if(keHoachDaChon == null){
                System.out.println(">> Không tồn tại kế hoạch tour này thuộc tour đã chọn!");
            }
        }while(keHoachDaChon == null);
        //kiểm tra xem coi người dùng đã đặt vé với các lựa chọn tr6nb hay chưa
        DanhSachVe dsVe = KhoDuLieu.getDanhSachVe();
        if(dsVe.daCoVeHopLe(keHoachDaChon.getMaKeHoachTour(), nguoidung.getMaHanhKhach())){
            System.out.println("\n>> Bạn đã đặt vé cho kế hoạch tour này rồi!");
            return;
        }
        // kiem tra xem coi có đủ chỗ hay không
        int soVeDaDat = dsVe.demVeChiemChoTheoKeHoach(keHoachDaChon.getMaKeHoachTour());
        int soluongvecon = keHoachDaChon.soChoConLai(soVeDaDat);
        if(soluongvecon <= 0){
            System.out.println("\n>> Kế hoạch tour này đã hết chỗ, vui lòng chọn kế hoạch hoặc tour khác!");
            return;
        }
        // nếu còn thi cho đạt và nhập số lượng vé muốn đặt 
        int chosesoluongve;
        System.out.println();
        do{
            System.out.print("Chọn số lượng vé (còn " + soluongvecon + " chỗ): ");
            chosesoluongve = in.nextInt();
            in.nextLine();
            if(!keHoachDaChon.conDuCho(soVeDaDat, chosesoluongve)){
                System.out.println(">> Số lượng không hợp lệ! Chỉ còn " + soluongvecon + " chỗ.");
            }
        }while(!keHoachDaChon.conDuCho(soVeDaDat, chosesoluongve));
        // nhập thông tin hành kach đi cùng 
        System.out.println("\n-------- NHẬP HÀNH KHÁCH ĐI CÙNG --------");
        int[] dsmakh = new int[chosesoluongve];
        dsmakh[0] = nguoidung.getMaHanhKhach();
        System.out.println(">> Đã lưu mã hành khách của bạn (" + nguoidung.getMaHanhKhach() + " - " + nguoidung.getHoTen() + ").");

        DanhSachHanhKhach dsHK = KhoDuLieu.getDanhSachHanhKhach();
        for(int i = 1; i < chosesoluongve; i++){
            int makh;
            int kq;
            do{
                System.out.print("Nhập mã hành khách thứ " + (i + 1) + ": ");
                makh = in.nextInt();
                in.nextLine();
                kq = dsVe.kiemTraHanhKhachHopLe(keHoachDaChon.getMaKeHoachTour(), makh, dsmakh, i, dsHK);
                if(kq == 1){
                    System.out.println(">> Mã hành khách không tồn tại, vui lòng nhập lại!");
                }else if(kq == 2){
                    System.out.println(">> Mã hành khách bị trùng trong đơn, vui lòng nhập lại!");
                }else if(kq == 3){
                    System.out.println(">> Hành khách này đã có vé cho kế hoạch tour này!");
                }
            }while(kq != 0);
            dsmakh[i] = makh;
        }
        // tình giá vé 
        long giaTour = tourDaChon.tinhGiaVe();
        long chiphikehoac = keHoachDaChon.tinhTongChiPhi(1);
        long dongia = keHoachDaChon.tinhDonGiaVe(tourDaChon);
        long tongTien = dongia * chosesoluongve;

        System.out.println("\n================ TÓM TẮT ĐẶT VÉ ================");
        System.out.printf("%-7s | %-26s | %-14s | %-12s | %-7s | %-14s | %-12s | %-12s | %-10s | %-10s | %-10s%n",
                "Ma Tour", "Ten Tour", "Diem Khoi Hanh", "Diem Den", "So Ngay", "Don Gia Co Ban",
                "Loai Tour", "Vung/QuocGia", "Phu Phi", "Can Visa", "Phi Visa");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------");
        tourDaChon.xuat();
        System.out.println();

        System.out.printf("%-18s | %-26s | %-18s | %-12s | %-12s | %-18s%n",
                "Ma Ke Hoach Tour", "Ten Tour", "Ten Nhan Vien", "Ngay Di", "Ngay Ve", "So Luong Ve Toi Da");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        keHoachDaChon.xuat();
        if(keHoachDaChon.getChiTietLichTrinh() != null && keHoachDaChon.getSoLuongChiTiet() > 0){
            System.out.println();
            System.out.printf("%-12s | %-22s | %-28s | %-12s | %-12s | %-12s%n",
                    "Ngay", "Phuong Tien", "Khach San", "Tien An", "Tien O", "Tien Di Lai");
            System.out.println("-----------------------------------------------------------------------------------------------------------------");
            for(ChiTietKeHoachTour temp : keHoachDaChon.getChiTietLichTrinh()){
                if(temp != null){
                    temp.xuat();
                }
            }
        }
        System.out.println("------------------------------------------------");
        System.out.println("Giá tour: " + giaTour);
        System.out.println("Chi phí lịch trình (1 khách): " + chiphikehoac);
        System.out.println("Đơn giá 1 vé: " + dongia);
        System.out.println("Số lượng vé: " + chosesoluongve);
        System.out.println("Tổng tiền: " + tongTien);
        System.out.println("================================================");

        int chose;
        System.out.println("Xác nhận đặt vé (1. Đồng ý / 0. Hủy)");
        System.out.print("Vui lòng chọn: ");
        chose = in.nextInt();
        in.nextLine();
        if(chose != 1){
            System.out.println(">> Đã hủy đặt vé.");
            return;
        }

        DanhSachHoaDon dsHD = KhoDuLieu.getDanhSachHoaDon();
        int maDonDatTourMoi = dsVe.getmadondattourmoi();
        System.out.print("Nhập ngày hôm nay (vd: 2026-11-13): ");
        String ngayHienTai = in.nextLine().trim();

        int[] dsMaVeMoi = dsVe.taoNhieuVe(
                maDonDatTourMoi,
                nguoidung.getMaHanhKhach(),
                keHoachDaChon.getMaKeHoachTour(),
                dsmakh,
                ngayHienTai,
                dongia);

        HoaDon hdMoi = dsHD.taoHoaDonChoDon(
                maDonDatTourMoi,
                keHoachDaChon.getMaNhanVien(),
                ngayHienTai,
                dsMaVeMoi,
                dsVe);

        if(dsMaVeMoi.length == 0 || hdMoi == null){
            System.out.println(">> Có lỗi xảy ra khi tạo vé hoặc hóa đơn!");
            return;
        }

        int chonThanhToan;
        do{
            System.out.println("\n-------- THANH TOÁN --------");
            System.out.println("1. Thanh toán hết (" + hdMoi.tinhTongTien() + ")");
            System.out.println("2. Đặt cọc một phần");
            System.out.println("0. Thanh toán sau");
            System.out.print("Chọn: ");
            chonThanhToan = in.nextInt();
            in.nextLine();
            if(chonThanhToan < 0 || chonThanhToan > 2){
                System.out.println(">> Vui lòng chọn lại!");
            }
        }while(chonThanhToan < 0 || chonThanhToan > 2);

        if(chonThanhToan == 1){
            hdMoi.ghiNhanThanhToan(hdMoi.tinhTongTien(), dsVe);
        }else if(chonThanhToan == 2){
            boolean ok;
            do{
                System.out.print("Nhập số tiền muốn đặt cọc (tối đa " + hdMoi.tinhTongTien() + "): ");
                long soTien = in.nextLong();
                in.nextLine();
                ok = hdMoi.ghiNhanThanhToan(soTien, dsVe);
                if(!ok){
                    System.out.println(">> Số tiền không hợp lệ (phải > 0 và <= " + hdMoi.tinhTongTien() + ")!");
                }
            }while(!ok);
        }

        System.out.println("\n════════ ĐẶT VÉ THÀNH CÔNG ════════");
        System.out.println("Mã đơn đặt tour: " + maDonDatTourMoi);
        System.out.println("Mã hóa đơn: " + hdMoi.getMaHoaDon());
        System.out.print("Danh sách mã vé: ");
        for(int i = 0; i < dsMaVeMoi.length; i++){
            System.out.print(dsMaVeMoi[i] + (i < dsMaVeMoi.length - 1 ? ", " : ""));
        }
        System.out.println();
        System.out.println("Tổng tiền: " + hdMoi.tinhTongTien());
        System.out.println("Số tiền đã thanh toán: " + hdMoi.getSoTienDaThanhToan());
        System.out.println("Số tiền còn lại: " + hdMoi.tinhSoTienConLai());
        System.out.println("====================================");
    }

    public static void case5(){
        if(nguoidung == null){
            System.out.println(">> Bạn chưa đăng nhập! Vui lòng đăng nhập hoặc đăng ký.");
            nguoidung = case1();
            if (nguoidung == null) {
                return;
            }
        }

        DanhSachVe dsVe = KhoDuLieu.getDanhSachVe();
        DanhSachHoaDon dsHD = KhoDuLieu.getDanhSachHoaDon();
        int chose;
        do {
            System.out.println("\n════════ TRA CỨU NHÓM VÉ ════════");
            System.out.println("1. Tra theo mã nhóm vé");
            System.out.println("2. Tra theo mã vé");
            System.out.println("3. Xem các nhóm vé của tôi");
            System.out.println("0. Quay lại");
            System.out.println("=================================");
            do {
                System.out.print("Vui lòng chọn: ");
                chose = in.nextInt();
                in.nextLine();
                if(chose < 0 || chose > 3){
                    System.out.println(">> Lựa chọn không hợp lệ, vui lòng chọn lại!");
                }
            } while(chose < 0 || chose > 3);

            switch(chose){
                case 1:
                    System.out.println("\n-------- TRA THEO MÃ NHÓM VÉ --------");
                    System.out.print("Vui lòng nhập mã nhóm vé: ");
                    int maDon = in.nextInt();
                    in.nextLine();
                    Ve[] dsNhom = dsVe.timTheoMaDonDatTour(maDon);
                    if (dsNhom.length == 0) {
                        System.out.println(">> Không tìm thấy nhóm vé có mã: " + maDon);
                        break;
                    }
                    System.out.println("\n-------- NHÓM VÉ #" + maDon + " (" + dsVe.layTrangThaiNhom(maDon) + ") --------");
                    System.out.printf("%-6s | %-8s | %-8s | %-22s | %-12s | %-12s | %-15s%n",
                            "Ma Ve", "Ma KH", "Ma HK", "Ten Hanh Khach", "Ngay Dat", "Don Gia", "Trang Thai");
                    System.out.println("---------------------------------------------------------------------------------------------------");
                    for (Ve v : dsNhom) {
                        if (v != null) {
                            HanhKhach hk = KhoDuLieu.getDanhSachHanhKhach().timTheoMa(v.getMaHanhKhach());
                            String tenHK = (hk != null) ? hk.getHoTen() : "Khong ro";
                            System.out.printf("%-6d | %-8d | %-8d | %-22s | %-12s | %-12d | %-15s%n",
                                    v.getMaVe(),
                                    v.getMaKeHoachTour(),
                                    v.getMaHanhKhach(),
                                    tenHK,
                                    v.getNgayDat(),
                                    v.getDonGia(),
                                    v.layTenTrangThai());
                        }
                    }
                    HoaDon[] dsHD1 = dsHD.timTheoMaDonDatTour(maDon);
                    if (dsHD1.length == 0) {
                        System.out.println(">> Nhóm vé này chưa có hóa đơn.");
                    } else {
                        System.out.println("\n-------- HÓA ĐƠN ĐI KÈM --------");
                        System.out.printf("%-6s | %-12s | %-12s | %-14s | %-14s | %-10s%n",
                                "Ma HD", "Ngay Lap", "Tong Tien", "Da Thanh Toan", "Con Lai", "Trang Thai");
                        System.out.println("----------------------------------------------------------------------------------");
                        for (HoaDon hd : dsHD1) {
                            if (hd != null) {
                                System.out.printf("%-6d | %-12s | %-12d | %-14d | %-14d | %-10s%n",
                                        hd.getMaHoaDon(),
                                        hd.getNgayLap(),
                                        hd.tinhTongTien(),
                                        hd.getSoTienDaThanhToan(),
                                        hd.tinhSoTienConLai(),
                                        hd.daThanhToanDu() ? "Du" : "Chua du");
                            }
                        }
                    }
                    System.out.println("===================================================================================================");
                    break;

                case 2:
                    System.out.println("\n-------- TRA THEO MÃ VÉ --------");
                    System.out.print("Vui lòng nhập mã vé: ");
                    int maVe = in.nextInt();
                    in.nextLine();
                    Ve ve = dsVe.timTheoMa(maVe);
                    if (ve == null) {
                        System.out.println(">> Không tìm thấy vé có mã: " + maVe);
                        break;
                    }
                    System.out.printf("%-6s | %-8s | %-8s | %-22s | %-12s | %-12s | %-15s%n",
                            "Ma Ve", "Ma KH", "Ma HK", "Ten Hanh Khach", "Ngay Dat", "Don Gia", "Trang Thai");
                    System.out.println("---------------------------------------------------------------------------------------------------");
                    HanhKhach hkVe = KhoDuLieu.getDanhSachHanhKhach().timTheoMa(ve.getMaHanhKhach());
                    String tenHKVe = (hkVe != null) ? hkVe.getHoTen() : "Khong ro";
                    System.out.printf("%-6d | %-8d | %-8d | %-22s | %-12s | %-12d | %-15s%n",
                            ve.getMaVe(),
                            ve.getMaKeHoachTour(),
                            ve.getMaHanhKhach(),
                            tenHKVe,
                            ve.getNgayDat(),
                            ve.getDonGia(),
                            ve.layTenTrangThai());
                    System.out.println("===================================================================================================");
                    break;

                case 3:
                    System.out.println("\n-------- CÁC NHÓM VÉ CỦA TÔI --------");
                    int[] dsMaDon = dsVe.layDanhSachMaDonTheoNguoiDat(nguoidung.getMaHanhKhach());
                    if (dsMaDon.length == 0) {
                        System.out.println(">> Bạn chưa có nhóm vé nào!");
                        break;
                    }
                    for (int maDonItem : dsMaDon) {
                        Ve[] nhom = dsVe.timTheoMaDonDatTour(maDonItem);
                        System.out.println("\n-------- NHÓM VÉ #" + maDonItem + " (" + dsVe.layTrangThaiNhom(maDonItem) + ") --------");
                        System.out.printf("%-6s | %-8s | %-8s | %-22s | %-12s | %-12s | %-15s%n",
                                "Ma Ve", "Ma KH", "Ma HK", "Ten Hanh Khach", "Ngay Dat", "Don Gia", "Trang Thai");
                        System.out.println("---------------------------------------------------------------------------------------------------");
                        for (Ve v : nhom) {
                            if (v != null) {
                                HanhKhach hk = KhoDuLieu.getDanhSachHanhKhach().timTheoMa(v.getMaHanhKhach());
                                String tenHK = (hk != null) ? hk.getHoTen() : "Khong ro";
                                System.out.printf("%-6d | %-8d | %-8d | %-22s | %-12s | %-12d | %-15s%n",
                                        v.getMaVe(),
                                        v.getMaKeHoachTour(),
                                        v.getMaHanhKhach(),
                                        tenHK,
                                        v.getNgayDat(),
                                        v.getDonGia(),
                                        v.layTenTrangThai());
                            }
                        }
                        HoaDon[] dsHD3 = dsHD.timTheoMaDonDatTour(maDonItem);
                        if (dsHD3.length == 0) {
                            System.out.println(">> Nhóm vé này chưa có hóa đơn.");
                        } else {
                            System.out.println("\n-------- HÓA ĐƠN ĐI KÈM --------");
                            System.out.printf("%-6s | %-12s | %-12s | %-14s | %-14s | %-10s%n",
                                    "Ma HD", "Ngay Lap", "Tong Tien", "Da Thanh Toan", "Con Lai", "Trang Thai");
                            System.out.println("----------------------------------------------------------------------------------");
                            for (HoaDon hd : dsHD3) {
                                if (hd != null) {
                                    System.out.printf("%-6d | %-12s | %-12d | %-14d | %-14d | %-10s%n",
                                            hd.getMaHoaDon(),
                                            hd.getNgayLap(),
                                            hd.tinhTongTien(),
                                            hd.getSoTienDaThanhToan(),
                                            hd.tinhSoTienConLai(),
                                            hd.daThanhToanDu() ? "Du" : "Chua du");
                                }
                            }
                        }
                        System.out.println("===================================================================================================");
                    }
                    break;

                case 0:
                    System.out.println(">> Đang quay lại menu khách hàng...");
                    return;
            }
        } while (chose != 0);
    }

    public static void case6(){
        System.out.println("\n════════ HỦY NHÓM VÉ ════════");
        if(nguoidung == null){
            System.out.println(">> Bạn chưa đăng nhập! Vui lòng đăng nhập hoặc đăng ký.");
            nguoidung = case1();
            if (nguoidung == null) {
                return;
            }
        }

        DanhSachVe dsVe = KhoDuLieu.getDanhSachVe();
        DanhSachHoaDon dsHD = KhoDuLieu.getDanhSachHoaDon();

        System.out.print("Nhập mã nhóm vé: ");
        int maDon = in.nextInt();
        in.nextLine();

        Ve[] dsNhom = dsVe.timTheoMaDonDatTour(maDon);
        if (dsNhom.length == 0) {
            System.out.println(">> Không tìm thấy nhóm vé có mã: " + maDon);
            return;
        }

        boolean thuocNguoiDung = false;
        for (Ve v : dsNhom) {
            if (v != null && v.getMaNguoiDat() == nguoidung.getMaHanhKhach()) {
                thuocNguoiDung = true;
                break;
            }
        }
        if (!thuocNguoiDung) {
            System.out.println(">> Bạn không có quyền hủy nhóm vé này (nhóm vé thuộc về tài khoản khác)!");
            return;
        }

        System.out.println("\n-------- NHÓM VÉ #" + maDon + " (" + dsVe.layTrangThaiNhom(maDon) + ") --------");
        System.out.printf("%-6s | %-8s | %-8s | %-22s | %-12s | %-12s | %-15s%n",
                "Ma Ve", "Ma KH", "Ma HK", "Ten Hanh Khach", "Ngay Dat", "Don Gia", "Trang Thai");
        System.out.println("---------------------------------------------------------------------------------------------------");
        for (Ve v : dsNhom) {
            if (v != null) {
                HanhKhach hk = KhoDuLieu.getDanhSachHanhKhach().timTheoMa(v.getMaHanhKhach());
                String tenHK = (hk != null) ? hk.getHoTen() : "Khong ro";
                System.out.printf("%-6d | %-8d | %-8d | %-22s | %-12s | %-12d | %-15s%n",
                        v.getMaVe(),
                        v.getMaKeHoachTour(),
                        v.getMaHanhKhach(),
                        tenHK,
                        v.getNgayDat(),
                        v.getDonGia(),
                        v.layTenTrangThai());
            }
        }
        System.out.println("===================================================================================================");

        if (dsHD.donDaThuTien(maDon)) {
            System.out.println(">> Không thể hủy: hóa đơn đã thu tiền!");
            return;
        }

        boolean coVeDuocHuy = false;
        for (Ve v : dsNhom) {
            if (v != null && v.duocHuy()) {
                coVeDuocHuy = true;
                break;
            }
        }
        if (!coVeDuocHuy) {
            System.out.println(">> Nhóm vé này không có vé nào có thể hủy!");
            return;
        }

        System.out.print("Hủy cả nhóm? (Y/N): ");
        String xacNhan = in.nextLine().trim();
        if (!xacNhan.equalsIgnoreCase("Y")) {
            System.out.println(">> Đã giữ nguyên nhóm vé.");
            return;
        }

        int soVeDaHuy = dsVe.huyNhomVe(maDon);
        System.out.println(">> Đã hủy " + soVeDaHuy + " vé.");

        System.out.println("\n-------- NHÓM VÉ #" + maDon + " SAU KHI HỦY (" + dsVe.layTrangThaiNhom(maDon) + ") --------");
        System.out.printf("%-6s | %-8s | %-8s | %-22s | %-12s | %-12s | %-15s%n",
                "Ma Ve", "Ma KH", "Ma HK", "Ten Hanh Khach", "Ngay Dat", "Don Gia", "Trang Thai");
        System.out.println("---------------------------------------------------------------------------------------------------");
        for (Ve v : dsNhom) {
            if (v != null) {
                HanhKhach hk = KhoDuLieu.getDanhSachHanhKhach().timTheoMa(v.getMaHanhKhach());
                String tenHK = (hk != null) ? hk.getHoTen() : "Khong ro";
                System.out.printf("%-6d | %-8d | %-8d | %-22s | %-12s | %-12d | %-15s%n",
                        v.getMaVe(),
                        v.getMaKeHoachTour(),
                        v.getMaHanhKhach(),
                        tenHK,
                        v.getNgayDat(),
                        v.getDonGia(),
                        v.layTenTrangThai());
            }
        }
        System.out.println("===================================================================================================");
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
        System.out.println("0. Quay lại");
        System.out.print("Chọn: ");
        chose = in.nextInt();
        in.nextLine();

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
            case 4:
                case4();
                break;
            case 5:
                case5();
                break;
            case 6:
                case6();
                break;

            default:
            System.out.println("Lựa chọn không hợp lệ!");
        }
        }while(chose != 0);
    }
}
