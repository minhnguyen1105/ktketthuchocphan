import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;

public class RunMain {
    //danh sách toàn bộ hàng hóa của bài toán
    public static List<HangHoa> hangHoas;
    public static Scanner sc = new Scanner(System.in);
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    //pattern định dạng ngày theo chuẩn
    public static Pattern pattern = Pattern.compile("^\\d{1,2}[/]\\d{1,2}[/]\\d{4}$");

    public static void main(String[] args) {
        hangHoas = new ArrayList<>();

        //thêm sẵn 20 hàng hóa không cần nhập từ bàn phím
        themDuLieu();

        int choice;
        do {
            System.out.println("======HÃY CHỌN CHỨC NĂNG=======");
            System.out.println("1. Thêm, xóa, sửa hàng hóa");
            System.out.println("2. Tìm kiếm");
            System.out.println("3. Sắp xếp");
            System.out.println("4. Thống kê");
            System.out.println("0. Thoát ");
            System.out.print("Chọn chức năng: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1: //Thêm, sửa, xóa hàng hóa
                    int future1;
                    do {
                        System.out.println("====BẠN CHỌN===");
                        System.out.println("1. Thêm hàng hóa");
                        System.out.println("2. Sửa hàng hóa");
                        System.out.println("3. Xóa hàng hóa");
                        System.out.println("0. Hủy");
                        System.out.print("Bạn chọn: ");
                        future1 = sc.nextInt();
                        switch (future1) {
                            case 1: //thêm hàng hóa
                                themHangHoa();
                                break;
                            case 2: //sửa hàng hóa
                                sc.nextLine();
                                System.out.println("Nhập mã hàng cần sửa: ");
                                String mH = sc.nextLine();
                                suaHangHoa(mH);
                                break;
                            case 3: //xóa hàng hóa
                                sc.nextLine();
                                System.out.println("Nhập mã hàng cần xóa: ");
                                String maHang = sc.nextLine();
                                xoaHangHoa(maHang);
                                break;
                            case 0: //thoát và quay lại màn hình trước đó
                                break;
                            default:
                                System.out.println("Lựa chọn không hợp lệ");
                                break;
                        }
                    }while (future1!=0);
                    break;
                case 2: //tìm kiếm
                    int f;
                    do {
                        System.out.println("======Bạn chọn======");
                        System.out.println("1. Tìm kiếm theo loại");
                        System.out.println("2. Tìm kiếm theo khoảng giá");
                        System.out.println("3. Tìm kiếm theo khoảng ngày nhập");
                        System.out.println("0. Thoát");
                        f = sc.nextInt();
                        switch (f) {
                            case 1:
                                int c;
                                do {
                                    System.out.println("Chọn loại hàng: ");
                                    System.out.println("1. THỰC PHẨM");
                                    System.out.println("2. SÀNH SỨ");
                                    System.out.println("3. ĐIỆN MÁY");
                                    System.out.println("0. Thoát");
                                    System.out.print("Chọn: ");
                                    c = sc.nextInt();
                                    switch (c) {
                                        case 1:
                                            timKiemTheoLoai(Loai.THUCPHAM);
                                            break;
                                        case 2:
                                            timKiemTheoLoai(Loai.SANHSU);
                                            break;
                                        case 3:
                                            timKiemTheoLoai(Loai.DIENMAY);
                                            break;
                                        case 0:
                                            break;
                                        default:
                                            System.out.println("Lựa chọn không hợp lệ");
                                            break;
                                    }
                                }while (c!=0);
                                break;
                            case 2:
                                System.out.println("===== NHẬP KHOẢNG GIÁ ======");
                                System.out.print("Giá từ: ");
                                double giaTu = sc.nextDouble();
                                System.out.print("Đến giá: ");
                                double giaDen = sc.nextDouble();
                                sc.nextLine();
                                timKiemTheoKhoangGia(giaTu, giaDen);
                                break;
                            case 3:
                                sc.nextLine();
                                System.out.println("===== NHẬP KHOẢNG NGÀY ======");
                                System.out.println("=====  Từ ngày: ");
                                String tuNgay;
                                while (true) {
                                    System.out.println("Nhập ngày nhập theo đúng định dạng dd/MM/yyyy: ");
                                    tuNgay = sc.nextLine();
                                    if (pattern.matcher(tuNgay).matches()) {
                                        break;
                                    } else {
                                        System.out.println("Chuỗi ngày tháng chưa đúng định dạng. Hãy nhập lại");
                                    }
                                }
                                LocalDate tu = LocalDate.parse(tuNgay, formatter);
                                System.out.println("===== Đến ngày: ");
                                String denNgay;
                                while (true) {
                                    System.out.println("Nhập ngày nhập theo đúng định dạng dd/MM/yyyy: ");
                                    denNgay = sc.nextLine();
                                    if (pattern.matcher(denNgay).matches()) {
                                        break;
                                    } else {
                                        System.out.println("Chuỗi ngày tháng chưa đúng định dạng. Hãy nhập lại");
                                    }
                                }
                                LocalDate den = LocalDate.parse(denNgay, formatter);
                                timKiemTheoKhoangNgay(tu, den);
                                break;
                            case 0:
                                break;
                            default:
                                System.out.println("Hãy nhập lựa chọn phù hợp");
                                break;
                        }
                    } while (f != 0);
                    break;
                case 3: //sắp xếp
                    int f2;
                    int tg = 0; //biến check xem tăng hay giảm
                    do {
                        System.out.println("=== BẠN CHỌN ===");
                        System.out.println("1. Sắp xếp tăng đần");
                        System.out.println("2. Sắp xếp giảm dần");
                        System.out.println("0. Quay lại");
                        System.out.print("Bạn chọn: ");
                        f2 = sc.nextInt();
                        switch (f2) {
                            case 1:
                                tg = 1;
                                break;
                            case 2:
                                tg = -1;
                                break;
                            case 0:
                                break;
                            default:
                                System.out.println("Lựa chọn không hợp lệ. Hãy nhập lại!");
                                break;
                        }

                        if (tg != 0) { // khi đã chọn sắp xếp tăng hoặc giảm thì cho chọn sắp xếp theo giá và ngày
                            int f3;
                            do {
                                System.out.println("=== BẠN CHỌN ===");
                                System.out.println("1. Sắp xếp theo giá nhập");
                                System.out.println("2. Sắp xếp theo ngày nhập");
                                System.out.println("3. Sắp xếp theo loại và ngày nhập");
                                System.out.println("4. Sắp xếp theo loại và giá nhập");
                                System.out.println("0. Quay lại");
                                System.out.print("Bạn chọn: ");
                                f3 = sc.nextInt();
                                sc.nextLine();
                                switch (f3) {
                                    case 1:
                                        sapXepTheoGia(tg);
                                        break;
                                    case 2:
                                        sapXepTheoNgay(tg);
                                        break;
                                    case 3:
                                        sapXepTheoLoaiVaNgayNhap(tg);
                                        break;
                                    case 4:
                                        sapXepTheoLoaiVaGia(tg);
                                        break;
                                    case 0:
                                        break;
                                    default:
                                        System.out.println("Lựa chọn không phù hợp. Hãy nhập lại!");
                                        break;
                                }
                            } while (f3 < 0 || f3 > 4);
                        }
                    } while (f2 > 2 || f2 < 0);


                    break;
                case 4: //thống kê
                    int future4;
                    do {
                        System.out.println("=== BẠN CHỌN ===");
                        System.out.println("1. Thống kê tổng số lượng hàng hóa");
                        System.out.println("2. Thống kê tổng giá trị nhập kho");
                        System.out.println("3. Thống kê số lượng từng loại hàng");
                        System.out.println("0. Quay lại");
                        System.out.print("Chọn: ");
                        future4 = sc.nextInt();
                        sc.nextLine();
                        switch (future4) {
                            case 1:
                                thongKeTongSLHang();
                                break;
                            case 2:
                                thongKeTongGiaTriKho();
                                break;
                            case 3:
                                thongKeSLTungLoai();
                                break;
                            case 0:
                                break;
                            default:
                                System.out.println("Lựa chọn không đúng. Hãy nhập lại!!");
                                break;
                        }
                    } while (future4<0 || future4>3);
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Lựa chọn chưa chính xác");
                    break;
            }
        } while (choice != 0);
    }

    private static void thongKeSLTungLoai() {
        int sDienMay=0, sSanhSu=0, sThucPham=0;
        for (HangHoa h: hangHoas
             ) {
            if (h.getLoai().compareTo(Loai.SANHSU)==0){
                sSanhSu+=h.getSlTonKho();
            }else if(h.getLoai().compareTo(Loai.THUCPHAM)==0){
                sThucPham+=h.getSlTonKho();
            }else {
                sDienMay+=h.getSlTonKho();
            }
        }
        System.out.println("==== THỐNG KÊ SỐ LƯỢNG TỪNG LOẠI ====");
        System.out.printf("%20s%20s%20s%20s\n", "Loại", "ĐIỆN MÁY", "SÀNH SỨ", "THỰC PHẨM");
        System.out.printf("%20s%20s%20s%20s\n", "Số lượng", sDienMay+"", sSanhSu+"", sThucPham+"");
    }

    private static void thongKeTongGiaTriKho() {
        long s=0;
        for (HangHoa  h: hangHoas
             ) {
            s+=h.getGiaNhap();
        }
        System.out.println("Tổng giá trị trong kho: "+ s);
    }

    private static void thongKeTongSLHang() {
        int s=0;
        for (HangHoa h: hangHoas
             ) {
            s+=h.getSlTonKho();
        }
        System.out.println("Tổng số hàng trong kho là: "+ s);
    }

    private static void sapXepTheoLoaiVaGia(int flag) {
        Collections.sort(hangHoas, new Comparator<HangHoa>() {
            @Override
            public int compare(HangHoa o1, HangHoa o2) {
                //đầu tiên sẽ sắp xếp theo loại, nếu loại giống nhau thì sắp xếp theo giá nhập
                if (o1.getLoai().compareTo(o2.getLoai()) != 0) {
                    return flag * o1.getLoai().compareTo(o2.getLoai());
                } else {
                    double result = flag * (o1.getGiaNhap() - o2.getGiaNhap());
                    if (result > 0) return 1;
                    else if (result < 0) return -1;
                    else return 0;
                }
            }
        });
        xemHang(hangHoas);
    }

    private static void sapXepTheoLoaiVaNgayNhap(int flag) {
        Collections.sort(hangHoas, new Comparator<HangHoa>() {
            @Override
            public int compare(HangHoa o1, HangHoa o2) {
                //đầu tiên sẽ sắp xếp theo loại, nếu loại giống nhau thì sắp xếp theo ngày nhập
                if (o1.getLoai().compareTo(o2.getLoai()) != 0) {
                    return flag * o1.getLoai().compareTo(o2.getLoai());
                } else {
                    if (flag == 1) {
                        if (o1.getNgayNhapKho().isBefore(o2.getNgayNhapKho())) {
                            return -1;
                        } else {
                            return 1;
                        }
                    } else {
                        if (o1.getNgayNhapKho().isBefore(o2.getNgayNhapKho())) {
                            return 1;
                        } else {
                            return -1;
                        }
                    }
                }
            }
        });
        xemHang(hangHoas);
    }

    private static void sapXepTheoNgay(int flag) {
        Collections.sort(hangHoas, new Comparator<HangHoa>() {
            @Override
            public int compare(HangHoa o1, HangHoa o2) {
                if (flag == 1) {
                    if (o1.getNgayNhapKho().isBefore(o2.getNgayNhapKho())) {
                        return -1;
                    } else {
                        return 1;
                    }
                } else {
                    if (o1.getNgayNhapKho().isBefore(o2.getNgayNhapKho())) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            }
        });
        xemHang(hangHoas);
    }

    private static void sapXepTheoGia(int flag) {
        Collections.sort(hangHoas, new Comparator<HangHoa>() {
            @Override
            public int compare(HangHoa o1, HangHoa o2) {
                double result = flag * (o1.getGiaNhap() - o2.getGiaNhap());
                if (result > 0) return 1;
                else if (result < 0) return -1;
                else return 0;
            }
        });
        System.out.println("===== DANH SÁCH HÀNG HÓA SẮP XẾP THEO GIÁ =====");
        xemHang(hangHoas);
    }

    //các phương thức tĩnh=================================

    private static void timKiemTheoKhoangNgay(LocalDate tuNgay, LocalDate denNgay) {
        List<HangHoa> hangs = new ArrayList<>();
        for (int i = 0; i < hangHoas.size(); i++) {
            if (hangHoas.get(i).getNgayNhapKho().isAfter(tuNgay) && hangHoas.get(i).getNgayNhapKho().isBefore(denNgay)) {
                hangs.add(hangHoas.get(i));
            }
        }
        if (hangs.size() == 0) {
            System.out.println("Không có sản phẩm nào");
        } else {
            xemHang(hangs);
        }
    }

    private static void timKiemTheoKhoangGia(double giaTu, double giaDen) {
        List<HangHoa> hangs = new ArrayList<>();
        for (int i = 0; i < hangHoas.size(); i++) {
            if (hangHoas.get(i).getGiaNhap() > giaTu && hangHoas.get(i).getGiaNhap() < giaDen) {
                hangs.add(hangHoas.get(i));
            }
        }
        if (hangs.size() == 0) {
            System.out.println("Không có sản phẩm nào");
        } else {
            xemHang(hangs);
        }
    }

    private static void timKiemTheoLoai(Loai thucpham) {
        List<HangHoa> hangs = new ArrayList<>();
        for (int i = 0; i < hangHoas.size(); i++) {
            if (hangHoas.get(i).getLoai() == thucpham) {
                hangs.add(hangHoas.get(i));
            }
        }
        if (hangs.size() == 0) {
            System.out.println("Không có sản phẩm nào");
        } else {
            xemHang(hangs);
        }
    }

    private static void xemHang(List<HangHoa> hangs) {
        System.out.printf("%20s%20s%20s%20s%20s%20s\n", "Loại Hàng", "Mã Hàng", "Tên Hàng", "Giá Nhập", "SL Tồn Kho", "Ngày Nhập Kho");
        for (HangHoa ha :
                hangs) {
            System.out.printf("%20s%20s%20s%20s%20s%20s\n", ha.getLoai(), ha.getMaHang(), ha.getTenHang(), ha.getGiaNhap() + "", ha.getSlTonKho() + "", ha.getNgayNhapKho());
        }
    }

    private static void xoaHangHoa(String maHang) {
        int i = kiemTraMaHangTonTai(0, hangHoas.size()-1, maHang);
        if (i != -1) {
            hangHoas.remove(i);
            System.out.println("Xóa hàng "+ maHang+" thành công");
        } else {
            System.out.println("Mã hàng không tồn tại");
        }
    }

    private static void suaHangHoa(String maHang) {
        int i = kiemTraMaHangTonTai(0, hangHoas.size()-1, maHang);
        if (i != -1) {
            System.out.println("===========NHẬP LẠI THÔNG TIN HÀNG: " + maHang + " ==============");

            System.out.println("Nhập tên hàng: ");
            hangHoas.get(i).setTenHang(sc.nextLine());

            System.out.println("Nhập giá nhập: ");
            hangHoas.get(i).setGiaNhap(sc.nextDouble());

            System.out.println("Nhập số lượng tồn kho: ");
            hangHoas.get(i).setSlTonKho(sc.nextInt());
            sc.nextLine();
            String date;
            while (true) {
                System.out.println("Nhập ngày nhập theo đúng định dạng dd/MM/yyyy: ");
                date = sc.nextLine();
                if (pattern.matcher(date).matches()) {
                    break;
                } else {
                    System.out.println("Chuỗi ngày tháng chưa đúng định dạng. Hãy nhập lại");
                }
            }
            LocalDate ngayNhap = LocalDate.parse(date, formatter);
            hangHoas.get(i).setNgayNhapKho(ngayNhap);
            System.out.println("=== SỬA HÀNG THÀNH CÔNG ===");
        } else {
            System.out.println("Mã hàng không tồn tại");
        }
    }

    private static void themHangHoa() {
        sc.nextLine();
        Loai loai = Loai.THUCPHAM;
        int ch;
        do {
            System.out.println("Chọn loại hàng: ");
            System.out.println("1. THỰC PHẨM");
            System.out.println("2. SÀNH SỨ");
            System.out.println("3. ĐIỆN MÁY");
            System.out.print("Chọn: ");
            ch = sc.nextInt();
            sc.nextLine();
            switch (ch) {
                case 1:
                    loai = Loai.THUCPHAM;
                    break;
                case 2:
                    loai = Loai.SANHSU;
                    break;
                case 3:
                    loai = Loai.DIENMAY;
                    break;
                default:
                    System.out.println("Loại hàng không tồn tại. Hãy chọn lại");
            }
        } while (ch > 3 || ch < 1);

        String maHang;
        int check = 0;
        do {
            System.out.println("Nhập mã hàng: ");
            maHang = sc.nextLine();
            if (maHang.compareTo("") == 0) {
                System.out.println("Mã hàng không được để trống. Hãy nhập lại!");
            } else {
                maHang = loai + maHang;
                check = kiemTraMaHangTonTai(0, hangHoas.size()-1, maHang);
                if (check != -1) {
                    System.out.println("Mã hàng đã tồn tại. Hãy nhập lại");
                }
            }

        } while (maHang.compareTo("") == 0 || check != -1);

        System.out.println("Nhập tên hàng: ");
        String tenHang = sc.nextLine();

        System.out.println("Nhập giá nhập: ");
        double giaNhap = sc.nextDouble();

        System.out.println("Nhập số lượng tồn kho: ");
        int slTonKho = sc.nextInt();
        sc.nextLine();
        String date;
        while (true) {
            System.out.println("Nhập ngày nhập theo đúng định dạng dd/MM/yyyy: ");
            date = sc.nextLine();
            if (pattern.matcher(date).matches()) {
                break;
            } else {
                System.out.println("Chuỗi ngày tháng chưa đúng định dạng. Hãy nhập lại");
            }
        }
        LocalDate ngayNhap = LocalDate.parse(date, formatter);
        HangHoa hangHoa = new HangHoa(loai, maHang, tenHang, giaNhap, slTonKho, ngayNhap);
        hangHoas.add(hangHoa);
        System.out.println("===============Thêm hàng hóa thành công==================");
    }

    //nếu hàng tồn tại thì trả về vị trí của hàng đó trong danh sách, nếu không tìm thấy trả về -1
//    private static int kiemTraMaHangTonTai(String maHang) {
//        int size = hangHoas.size();
//        int index = -1;
//        for (int i = 0; i < size; i++) {
//            if (hangHoas.get(i).getMaHang().compareTo(maHang) == 0) {
//                index = i;
//                break;
//            }
//        }
//        return index;
//    }

    //tìm kiếm nhị phân
    private static  int kiemTraMaHangTonTai(int l, int r, String maHang) {
        Collections.sort(hangHoas, new Comparator<HangHoa>() {
            @Override
            public int compare(HangHoa o1, HangHoa o2) {
                return o1.getMaHang().compareTo(o2.getMaHang());
            }
        });
        if (r >= l) {
            int mid = l + (r - l) / 2;

            // Nếu phần tử có ở chính giữa
            if (hangHoas.get(mid).getMaHang().compareTo(maHang)==0)
                return mid;

            // Nếu phần tử nhỏ hơn giữa, thì nó chỉ có thể
            // hiện diện trong mảng con bên trái
            if (hangHoas.get(mid).getMaHang().compareTo(maHang)>0)
                return kiemTraMaHangTonTai(l, mid - 1, maHang);

            // Ngược lại, phần tử chỉ có thể có mặt
            // trong mảng con bên phải
            return kiemTraMaHangTonTai(mid + 1, r, maHang);
        }

        // Nếu phầnt tử không có trong mảng
        return -1;
    }

    private static void themDuLieu() {
        hangHoas.add(new HangHoa(Loai.THUCPHAM, "THUCPHAM1", "KẸO", 5000, 100, LocalDate.parse("10/11/2019", formatter)));
        hangHoas.add(new HangHoa(Loai.THUCPHAM, "THUCPHAM2", "BÁNH", 3000, 10, LocalDate.parse("13/09/2017", formatter)));
        hangHoas.add(new HangHoa(Loai.THUCPHAM, "THUCPHAM3", "TRỨNG", 200000, 120, LocalDate.parse("14/08/2018", formatter)));
        hangHoas.add(new HangHoa(Loai.THUCPHAM, "THUCPHAM4", "THỊT LỢN", 17000, 110, LocalDate.parse("21/05/2019", formatter)));
        hangHoas.add(new HangHoa(Loai.THUCPHAM, "THUCPHAM5", "THỊT GÀ", 4000, 40, LocalDate.parse("11/03/2020", formatter)));
        hangHoas.add(new HangHoa(Loai.THUCPHAM, "THUCPHAM6", "CÀ RỐT", 5000, 80, LocalDate.parse("28/01/2021", formatter)));

        hangHoas.add(new HangHoa(Loai.SANHSU, "SANHSU1", "BÁT SỨ CON", 15000, 30, LocalDate.parse("11/12/2019", formatter)));
        hangHoas.add(new HangHoa(Loai.SANHSU, "SANHSU2", "BÁT SỨ TO", 33000, 101, LocalDate.parse("21/04/2017", formatter)));
        hangHoas.add(new HangHoa(Loai.SANHSU, "SANHSU3", "CHÉN SỨ", 21000, 21, LocalDate.parse("19/01/2018", formatter)));
        hangHoas.add(new HangHoa(Loai.SANHSU, "SANHSU4", "ẤM TRÀ", 290000, 10, LocalDate.parse("22/02/2019", formatter)));
        hangHoas.add(new HangHoa(Loai.SANHSU, "SANHSU5", "NỒI NINH SỨ", 230000, 20, LocalDate.parse("13/07/2020", formatter)));
        hangHoas.add(new HangHoa(Loai.SANHSU, "SANHSU6", "VÒ RƯỢU", 500000, 60, LocalDate.parse("15/01/2021", formatter)));

        hangHoas.add(new HangHoa(Loai.DIENMAY, "DIENMAY1", "MÁY GIẶT", 6200000, 100, LocalDate.parse("11/11/2019", formatter)));
        hangHoas.add(new HangHoa(Loai.DIENMAY, "DIENMAY2", "MÁY LỌC NƯỚC", 3000000, 10, LocalDate.parse("11/09/2017", formatter)));
        hangHoas.add(new HangHoa(Loai.DIENMAY, "DIENMAY3", "NỒI CƠM ĐIỆN", 1200000, 120, LocalDate.parse("11/08/2018", formatter)));
        hangHoas.add(new HangHoa(Loai.DIENMAY, "DIENMAY4", "BẾP ĐIỆN", 1700000, 110, LocalDate.parse("11/05/2019", formatter)));
        hangHoas.add(new HangHoa(Loai.DIENMAY, "DIENMAY5", "MÁY SẤY TÓC", 100000, 40, LocalDate.parse("11/03/2020", formatter)));
        hangHoas.add(new HangHoa(Loai.DIENMAY, "DIENMAY7", "MÁY BƠM", 2000000, 80, LocalDate.parse("11/01/2021", formatter)));
        hangHoas.add(new HangHoa(Loai.DIENMAY, "DIENMAY8", "MÁY HÚT BỤI", 1500000, 80, LocalDate.parse("11/01/2021", formatter)));
        hangHoas.add(new HangHoa(Loai.DIENMAY, "DIENMAY9", "TV MÀN HÌNH CON", 5000000, 80, LocalDate.parse("11/01/2021", formatter)));
    }
}
