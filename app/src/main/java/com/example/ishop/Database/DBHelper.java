package com.example.ishop.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "DBISHOP", null, 12);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //table KHÁCH HÀNG
        String tKHACHHANG = "CREATE TABLE KHACHHANG(maKH text primary key, " +
                "anhKH text," +
                "tenKH text," +
                "sdtKH text," +
                "emailKH text," +
                "matkhauKH text," +
                "diachiKH text)";
        db.execSQL(tKHACHHANG);
        db.execSQL("INSERT INTO KHACHHANG VALUES('IC1001', 'ce_thanh_phong', 'Nghiêu Thanh Phong','0192805205','thanhphong47@gmail.com','1234','Hồ Chí Minh')," +
                "('IC1002','ce_tan_sang','Nguyễn Tấn Sang','0251303999','tansang99@gmail.com','1234','Tiền Giang')," +
                "('IC1003','ce_giang_sinh','Ngô Thị Giáng Sinh','0212512202','giangsinh12@gmail.com','1234','Tây Ninh')," +
                "('IC1004','ce_huy_hoang','Nguyễn Văn Huy Hoàng','0201234999','hoang2000@gmail.com','1234','Hồ Chí Minh')," +
                "('IC1005','c_khanh_vy','Trần Khánh Vy','0242108999','khanhvy99@gmail.com','1234','Nghệ An')," +
                "('IC1006','c_thach_trang','Thạch Nguyễn Phương Trang','0262002998','thachtrang98@gmail.com','1234','Hà Nội')," +
                "('IC1007','c_giang_oi','Đào Lê Thu Giang','0322708991','giangoi91@gmail.com','1234','Hà Nội')," +
                "('IC1008','c_duy_thanh','Nguyễn Duy Thành','0310101993','duythanh93@gmail.com','1234','Hà Nội')," +
                "('IC1009','c_chi_nguyen','Nguyễn Phương Chi','0352305989','chinguyen89@gmail.com','1234','Hà Nội')," +
                "('IC1010','c_sunhuyn','Trần Thị Thanh Huyền','0290106995','sunhuyn95@gmail.com','1234','Hà Nội')," +
                "('IC1011','c_meichan','Nguyễn Hà Trang','0230909200','meichan20@gmail.com','1234','Hà Nội')," +
                "('IC1012','c_kira','Nguyễn Như Nam Anh','0302512994','kira94@gmail.com','1234','Hà Nội')," +
                "('IC1013','c_huynl','Nguyễn Lạc Huy','0321301992','huynl92@gmail.com','1234','Phú Yên')," +
                "('IC1014','c_duytham','Trần Đức Duy','0281511996','duytham96@gmail.com','1234','Phú Yên')");

        //table NHÂN VIÊN
        String tNHANVIEN = "CREATE TABLE NHANVIEN(maNV text primary key, " +
                "anhNV text," +
                "tenNV text," +
                "SDTNV text," +
                "emailNV text," +
                "matkhauNV text,"+
                "diachiNV text," +
                "tuoiNV text," +
                "gioitinhNV text)";
        db.execSQL(tNHANVIEN);
        db.execSQL("INSERT INTO NHANVIEN VALUES('IE101','ce_thanh_phong','Nghiêu Thanh Phong','0192805205','thanhphong47@ishop.vn','1234','Hồ Chí Minh','19','Nam')," +
                "('IE102','ce_tan_sang','Nguyễn Tấn Sang','0251303999','tansang99@ishop.vn','1234','Tiền Giang','25','Nữ')," +
                "('IE103','ce_giang_sinh','Ngô Thị Giáng Sinh','0212512202','giangsinh12@ishop.vn','1234','Tây Ninh','21','Nam')," +
                "('IE104','ce_huy_hoang','Nguyễn Văn Huy Hoàng','0201234999','hoang2000@ishop.vn','1234','Hồ Chí Minh','20','Nam')," +
                "('IE105','ce_huy_hoang','Quang Liem','0323613201','quangliem01@ishop.vn','1234','Hồ Chí Minh','23','Nam')");

        //table QUẢN LÝ
        String tQUANLY = "CREATE TABLE QUANLY(maQL text primary key, " +
                "anhQL text," +
                "tenQL text," +
                "SDTQL text," +
                "emailQL text," +
                "matkhauQL text,"+
                "diachiQL text," +
                "tuoiQL text," +
                "gioitinhQL text)" ;

        db.execSQL(tQUANLY);
        db.execSQL("INSERT INTO QUANLY VALUES('IM9250','cme_quan_ly','Nghiêu Tấn Hoàng','0212519292','tanhoang925@ishop.vn','1234','Hồ Chí Minh','24','Nam')");

        //table LOẠI SẢN PHẨM
        String tLOAISANPHAM = "CREATE TABLE LOAISANPHAM(maLSP text primary key," +
                "anhLSP text," +
                "tenLSP text)";
        db.execSQL(tLOAISANPHAM);
        db.execSQL("INSERT INTO LOAISANPHAM VALUES('IP27','type_iphone','iPhone')," +
                "('IA10','type_ipad','iPad')," +
                "('IM84','type_mac','Mac')," +
                "('IW15','type_watch','Apple Watch')");

        //table CHI TIẾT ĐƠN HÀNG
        String tCTDH = "CREATE TABLE CTDH(maDH text references DONHANG(maDH)," +
                "maSP text references SANPHAM(maSP)," +
                "soluong integer," +
                "dongia integer)";
        db.execSQL(tCTDH);
        db.execSQL("INSERT INTO CTDH VALUES('IO101','IPE2734',1,28499000)," +
                "('IO101','IPA1018',1,34999000)," +
                "('IO101','IPM8426',1,84990000)," +
                "('IO101','IPW1511',1,19990000)," +
                "('IO102','IPE2732',1,19299000)," +
                "('IO102','IPA1017',1,18999000)," +
                "('IO102','IPM8422',1,32990000)," +
                "('IO102','IPW1510',1,99900000)," +
                "('IO103','IPE2730',1,26499000)," +
                "('IO103','IPA1012',1,14990000)," +
                "('IO103','IPM8404',1,27990000)," +
                "('IO103','IPW1508',1,6490000)," +
                "('IO104','IPE2725',1,14789000)," +
                "('IO104','IPA1013',1,25990000)," +
                "('IO104','IPM8412',1,69990000)," +
                "('IO104','IPW1506',1,5990000)");

        //table HÓA ĐƠN
        String tHOADON = "CREATE TABLE HOADON(maHD text primary key," +
                "maDH text references DONHANG(maDH)," +
                "ngayHD date," +
                "maNV text references NHANVIEN(maNV)," +
                "thanhtien integer)";
        db.execSQL(tHOADON);
        db.execSQL("INSERT INTO HOADON VALUES('IB1','IO101','14/07/2024','IE101', 168478000)");

        //table ĐƠN HÀNG
        String tDONHANG = "CREATE TABLE DONHANG(maDH text primary key," +
                "maKH text references KHACHHANG(maKH)," +
                "ngayDH date NOT NULL," +
                "trangthaiDH text," +
                "maNV text references NHANVIEN(maNV)," +
                "thanhtien integer NOT NULL)";
        db.execSQL(tDONHANG);
        db.execSQL("INSERT INTO DONHANG VALUES('IO101','IC1005','14/07/2024','Đã xử lý','IE101', 0)," +
                "('IO102','IC1006','15/07/2024','Chưa xử lý','IE102', 0)," +
                "('IO103','IC1007','16/07/2024','Chưa xử lý','IE101', 0)," +
                "('IO104','IC1008','17/07/2024','Chưa xử lý','IE102', 0)");

        //table GIO HANG
        String tGIOHANG = "CREATE TABLE GIOHANG(maKH text references KHACHHANG(maKH)," +
                "anhSP text," +
                "tenSP text," +
                "tenLSP text," +
                "gia int," +
                "soluong int)";
        db.execSQL(tGIOHANG);

        //table SẢN PHẨM
        String tSANPHAM = "CREATE TABLE SANPHAM(maSP text primary key, " +
                "anhSP text," +
                "tenSP text," +
                "giaSP integer," +
                "motaSP text," +
                "soluong integer," +
                "maLSP text references LOAISANPHAM(maLSP))";
        db.execSQL(tSANPHAM);

        //Iphone
        db.execSQL("INSERT INTO SANPHAM VALUES('IPE2701','iphone_6','iPhone 6',1590000,'Màn hình Retina HD 4.7 inch sắc nét, hiển thị rõ ràng Chip A8 mạnh mẽ, camera iSight 8MP chụp ảnh đẹp. Quay video Full HD," +
                "Touch ID tiện lợi và an toàn. Thiết kế nhôm nguyên khối mỏng nhẹ, nhiều tùy chọn màu sắc." +
                "',60000,'IP27')," +
                "('IPE2702','iphone_6_plus','iPhone 6 Plus',1990000,'Màn hình Retina HD 5.5 inch lớn, hiển thị sắc nét. Chip A8 mạnh mẽ, camera iSight 8MP chụp ảnh ổn định. Quay video Full HD," +
                "Touch ID tiện lợi và an toàn. Thiết kế nhôm nguyên khối mỏng nhẹ, nhiều tùy chọn màu sắc." +
                "',63000,'IP27')," +
                "('IPE2703','iphone_6s','iPhone 6s',2190000,'Màn hình Retina HD 4.7 inch, công nghệ 3D Touch cảm ứng lực. Chip A9 mạnh mẽ, camera iSight 12MP chụp ảnh Live Photos." +
                "Quay video 4K, Touch ID thế hệ 2 nhanh hơn. Thiết kế nhôm series 7000 cứng cáp, nhiều tùy chọn màu sắc." +
                "',66000,'IP27')," +
                "('IPE2704','iphone_6s_plus','iPhone 6s Plus',2590000,'Màn hình Retina HD 5.5 inch, công nghệ 3D Touch cảm ứng lực. Chip A9 mạnh mẽ, camera iSight 12MP chụp ảnh Live Photos." +
                "Quay video 4K, Touch ID thế hệ 2 nhanh hơn. Thiết kế nhôm series 7000 cứng cáp, nhiều tùy chọn màu sắc." +
                "',69000,'IP27')," +
                "('IPE2705','iphone_se_1','iPhone SE 2016',990000,'Thiết kế nhỏ gọn, thừa hưởng ngoại hình iPhone 5s. Chip A9 mạnh mẽ, tương đương iPhone 6s. Camera sau 12MP chụp ảnh" +
                "sắc nét, quay video 4K. Touch ID tiện lợi, bảo mật vân tay. Giá bán hấp dẫn, phù hợp người dùng yêu thích thiết kế nhỏ gọn." +
                "',10000,'IP27')," +
                "('IPE2706','iphone_7','iPhone 7',2990000,'Màn hình Retina HD 4.7 inch, thiết kế nhôm nguyên khối cứng cáp. Chip A10 Fusion mạnh mẽ, camera 12MP chụp ảnh đẹp." +
                "Khả năng quay video 4K chất lượng cao, nút Home cảm ứng lực Force Touch. Chống nước, chống bụi chuẩn IP67." +
                "',70000,'IP27')," +
                "('IPE2707','iphone_7_plus','iPhone 7 Plus',3290000,'Màn hình Retina HD 5.5 inch, thiết kế nhôm nguyên khối cứng cáp. Chip A10 Fusion mạnh mẽ, camera kép 12MP chụp ảnh" +
                "xóa phông. Khả năng quay video 4K chất lượng cao, nút Home cảm ứng lực Force Touch. Chống nước, chống bụi chuẩn IP67." +
                "',75000,'IP27')," +
                "('IPE2708','iphone_8','iPhone 8',3590000,'Màn hình Retina HD 4.7 inch, thiết kế mặt kính sang trọng. Chip A11 Bionic mạnh mẽ, camera 12MP chụp ảnh đẹp." +
                "Khả năng quay video 4K chất lượng cao, hỗ trợ sạc không dây Qi. Nút Home Touch ID quen thuộc, cảm biến vân tay nhạy bén." +
                "',80000,'IP27')," +
                "('IPE2709','iphone_8_plus','iPhone 8 Plus',3990000,'Màn hình Retina HD 5.5 inch, thiết kế mặt kính sang trọng. Chip A11 Bionic mạnh mẽ, camera kép 12MP chụp ảnh xóa phông." +
                "Khả năng quay video 4K chất lượng cao, hỗ trợ sạc không dây Qi. Nút Home Touch ID quen thuộc, cảm biến vân tay nhạy bén." +
                "',85000,'IP27')," +
                "('IPE2710','iphone_x','iPhone X',4190000,'Thiết kế đột phá với màn hình OLED tràn viền, loại bỏ nút Home. Face ID thay thế Touch ID, mở khóa bằng khuôn mặt." +
                "Chip A11 Bionic mạnh mẽ, camera kép 12MP chụp ảnh xóa phông. Khả năng quay video 4K chất lượng cao, sạc không dây tiện lợi." +
                "Mở ra kỷ nguyên mới cho thiết kế iPhone." +
                "',90000,'IP27')," +
                "('IPE2711','iphone_xr','iPhone XR',4290000,'Màn hình Liquid Retina LCD 6.1 inch, hiển thị sắc nét và tươi sáng. Chip A12 Bionic mạnh mẽ, camera đơn 12MP chụp ảnh đẹp." +
                "Khả năng quay video 4K chất lượng cao, Face ID nhanh chóng và an toàn. Nhiều tùy chọn màu sắc trẻ trung, giá bán hấp dẫn." +
                "',100000,'IP27')," +
                "('IPE2712','iphone_xs','iPhone XS',4590000,'Màn hình OLED 5.8 inch sắc nét, hiển thị sống động và chân thực. Chip A12 Bionic mạnh mẽ, hệ thống camera kép 12MP" +
                "chụp ảnh đẹp. Khả năng quay video 4K chất lượng cao, Face ID nhanh chóng và an toàn. Mặt lưng kính sang trọng," +
                "khung thép không gỉ bền bỉ." +
                "',102000,'IP27')," +
                "('IPE2713','iphone_xs_max','iPhone XS Max',4990000,'Màn hình OLED 6.5 inch lớn nhất, hiển thị sống động và sắc nét. Chip A12 Bionic mạnh mẽ, hệ thống camera kép 12MP chụp" +
                "ảnh đẹp. Khả năng quay video 4K chất lượng cao, Face ID nhanh chóng và an toàn. Mặt lưng kính sang trọng, khung thép" +
                "không gỉ bền bỉ." +
                "',105000,'IP27')," +
                "('IPE2714','iphone_11','iPhone 11',5189000,'Màn hình Liquid Retina HD 6.1 inch LCD, hiển thị sắc nét và tươi sáng. Chip A13 Bionic mạnh mẽ, hệ thống camera kép 12MP" +
                "chụp ảnh đẹp. Khả năng quay video 4K chất lượng cao, thời lượng pin tốt. Mặt lưng kính bóng trẻ trung, khung nhôm bền bỉ." +
                "',110000,'IP27')," +
                "('IPE2715','iphone_11_pro','iPhone 11 Pro',6789000,'Màn hình Super Retina XDR 5.8 inch OLED, hiển thị sắc nét và sống động. Chip A13 Bionic mạnh mẽ, hệ thống 3 camera sau" +
                "12MP chụp ảnh siêu nét. Khả năng quay video 4K chất lượng cao, hiệu năng ấn tượng. Mặt lưng kính mờ sang trọng," +
                "khung thép không gỉ bền bỉ." +
                "',112000,'IP27')," +
                "('IPE2716','iphone_11_pro_max','iPhone 11 Pro Max',8289000,'Màn hình Super Retina XDR 6.5 inch OLED, hiển thị sắc nét và sống động. Chip A13 Bionic mạnh mẽ, hệ thống 3 camera sau" +
                "12MP chụp ảnh siêu nét. Khả năng quay video 4K chất lượng cao, thời lượng pin ấn tượng. Mặt lưng kính mờ sang trọng," +
                "khung thép không gỉ bền bỉ." +
                "',115000,'IP27')," +
                "('IPE2717','iphone_se_2','iPhone SE 2020',4990000,'Thiết kế tương tự iPhone 8, màn hình Retina HD 4.7 inch. Chip A13 Bionic mạnh mẽ, tương đương iPhone 11. Camera đơn 12MP" +
                "chụp ảnh đẹp, quay video 4K. Touch ID quen thuộc, hỗ trợ sạc nhanh. Giá bán hợp lý, hiệu năng mạnh mẽ trong tầm giá." +
                "',20000,'IP27')," +
                "('IPE2718','iphone_12_mini','iPhone 12 mini',5289000,'Màn hình Super Retina XDR 5.4 inch nhỏ gọn, thiết kế cạnh phẳng trẻ trung. Chip A14 Bionic mạnh mẽ, hệ thống camera kép" +
                "12MP chụp ảnh đẹp. Khả năng quay video Dolby Vision HDR chất lượng cao. Khung nhôm bền bỉ, nhiều tùy chọn màu sắc." +
                "',120000,'IP27')," +
                "('IPE2719','iphone_12','iPhone 12',6789000,'Màn hình Super Retina XDR 6.1 inch sắc nét, thiết kế cạnh phẳng trẻ trung. Chip A14 Bionic mạnh mẽ, hệ thống camera kép" +
                "12MP chụp ảnh đẹp. Khả năng quay video Dolby Vision HDR chất lượng cao. Khung nhôm bền bỉ, nhiều tùy chọn màu sắc." +
                "',122000,'IP27')," +
                "('IPE2720','iphone_12_pro','iPhone 12 Pro',9289000,'Màn hình Super Retina XDR 6.1 inch sắc nét, thiết kế cạnh phẳng sang trọng. Chip A14 Bionic mạnh mẽ, hệ thống 3 camera" +
                "sau 12MP chụp ảnh đẹp. Khả năng quay video Dolby Vision HDR chất lượng cao. Khung thép không gỉ bền bỉ, hỗ trợ" +
                "MagSafe tiện lợi." +
                "',125000,'IP27')," +
                "('IPE2721','iphone_12_pro_max','iPhone 12 Pro Max',11589000,'Màn hình Super Retina XDR 6.7 inch lớn nhất, thiết kế cạnh phẳng sang trọng. Chip A14 Bionic mạnh mẽ, hệ thống 3" +
                "camera sau 12MP chụp ảnh đẹp. Khả năng quay video Dolby Vision HDR chất lượng cao. Khung thép không gỉ bền bỉ," +
                "hỗ trợ MagSafe tiện lợi." +
                "',127000,'IP27')," +
                "('IPE2722','iphone_13_mini','iPhone 13 mini',10589000,'Màn hình Super Retina XDR 5.4 inch nhỏ gọn, dễ cầm nắm. Chip A15 Bionic mạnh mẽ, camera kép 12MP chụp ảnh đẹp." +
                "Thiết kế tai thỏ nhỏ gọn hơn, hiệu năng ổn định. Nhiều tùy chọn màu sắc trẻ trung, giá bán dễ tiếp cận." +
                "',130000,'IP27')," +
                "('IPE2723','iphone_13','iPhone 13',13789000,'Màn hình Super Retina XDR 6.1 inch sắc nét, hiển thị sống động. Chip A15 Bionic mạnh mẽ, camera kép 12MP chụp ảnh đẹp." +
                "Thiết kế tai thỏ nhỏ gọn hơn, thời lượng pin được cải thiện. Nhiều tùy chọn màu sắc trẻ trung, giá bán hấp dẫn." +
                "',132000,'IP27')," +
                "('IPE2724','iphone_13_pro','iPhone 13 Pro',12489000,'Màn hình Super Retina XDR 6.1 inch, tần số quét 120Hz mượt mà. Chip A15 Bionic mạnh mẽ, camera chính 12MP cải tiến" +
                "chụp thiếu sáng tốt hơn. Hệ thống 3 camera sau đa năng, quay video Cinematic mode chuyên nghiệp. Thiết kế tai thỏ nhỏ gọn" +
                "hơn, hiệu năng ấn tượng." +
                "',135000,'IP27')," +
                "('IPE2725','iphone_13_pro_max','iPhone 13 Pro Max',14789000,'Màn hình Super Retina XDR 6.7 inch, tần số quét 120Hz mượt mà. Chip A15 Bionic mạnh mẽ, camera chính 12MP cải tiến" +
                "chụp thiếu sáng tốt hơn. Hệ thống 3 camera sau đa năng, quay video Cinematic mode chuyên nghiệp. Thiết kế tai thỏ nhỏ gọn" +
                "hơn, thời lượng pin ấn tượng." +
                "',137000,'IP27')," +
                "('IPE2726','iphone_se_3','iPhone SE 2021',11990000,'Thiết kế tương tự iPhone 8, màn hình Retina HD 4.7 inch. Chip A15 Bionic mạnh mẽ, tương đương iPhone 13. Camera đơn 12MP" +
                "chụp ảnh đẹp, quay video 4K. Touch ID quen thuộc, hỗ trợ 5G. Hiệu năng vượt trội, giá bán cạnh tranh." +
                "',30000,'IP27')," +
                "('IPE2727','iphone_14','iPhone 14',16399000,'Màn hình Super Retina XDR 6.1 inch sắc nét, hiển thị sống động. Chip A15 Bionic mạnh mẽ, camera kép 12MP chụp ảnh đẹp." +
                "Thiết kế tai thỏ quen thuộc, hiệu năng ổn định. Nhiều tùy chọn màu sắc trẻ trung, giá bán dễ tiếp cận." +
                "',140000,'IP27')," +
                "('IPE2728','iphone_14_plus','iPhone 14 Plus',19199000,'Màn hình Super Retina XDR 6.7 inch lớn, hiển thị sống động. Chip A15 Bionic mạnh mẽ, camera kép 12MP chụp ảnh đẹp." +
                "Thiết kế tai thỏ quen thuộc, thời lượng pin ấn tượng. Nhiều tùy chọn màu sắc trẻ trung, giá bán hấp dẫn" +
                "',142000,'IP27')," +
                "('IPE2729','iphone_14_pro','iPhone 14 Pro',23399000,'Màn hình Super Retina XDR 6.1 inch siêu sáng, Always-On display tiện lợi. Chip A16 Bionic mạnh mẽ, camera chính 48MP" +
                "chụp ảnh siêu nét. Dynamic Island thay thế tai thỏ, tương tác trực quan. Khung thép không gỉ bền bỉ, hiệu năng ấn tượng." +
                "',145000,'IP27')," +
                "('IPE2730','iphone_14_pro_max','iPhone 14 Pro Max',26499000,'Màn hình Super Retina XDR 6.7 inch siêu sáng, Always-On display tiện lợi. Chip A16 Bionic mạnh mẽ, camera chính 48MP" +
                "chụp ảnh siêu nét. Dynamic Island thay thế tai thỏ, tương tác trực quan. Khung thép không gỉ bền bỉ, thời lượng pin ấn tượng." +
                "',147000,'IP27')," +
                "('IPE2731','iphone_15','iPhone 15',18599000,'iPhone 15 là mẫu iPhone tiêu chuẩn mới nhất của Apple, sở hữu thiết kế Dynamic Island tương tác trực quan." +
                "Máy được trang bị chip A16 Bionic mạnh mẽ, hệ thống camera kép 48MP chụp ảnh sắc nét." +
                "Màn hình Super Retina XDR 6.1 inch hiển thị sống động, cổng USB-C thay thế cổng Lightning truyền thống." +
                "Thời lượng pin được cải thiện đáng kể so với thế hệ trước." +
                "',150000,'IP27')," +
                "('IPE2732','iphone_15_plus','iPhone 15 Plus',19299000,'iPhone 15 Plus là phiên bản màn hình lớn của iPhone 15, sở hữu màn hình Super Retina XDR 6.7 inch sắc nét." +
                "Máy được trang bị chip A16 Bionic mạnh mẽ, hệ thống camera kép 48MP chụp ảnh chất lượng cao." +
                "Thiết kế Dynamic Island tương tác trực quan, thời lượng pin được cải thiện đáng kể." +
                "Cổng USB-C thay thế cổng Lightning truyền thống, hỗ trợ sạc nhanh và truyền dữ liệu tốc độ cao." +
                "',152000,'IP27')," +
                "('IPE2733','iphone_15_pro','iPhone 15 Pro',24399000,'iPhone 15 Pro là điện thoại cao cấp của Apple với thiết kế khung titan bền bỉ, viền màn hình siêu mỏng." +
                "Máy sở hữu chip A17 Pro mạnh mẽ, camera tele 3x chụp ảnh chân dung ấn tượng, camera chính 48MP chụp ảnh sắc nét." +
                "Nút Action đa năng thay thế nút gạt rung, cổng USB-C hỗ trợ sạc và truyền dữ liệu nhanh chóng." +
                "',155000,'IP27')," +
                "('IPE2734','iphone_15_pro_max','iPhone 15 Pro Max',28499000,'iPhone 15 Pro Max là chiếc điện thoại cao cấp nhất của Apple với thiết kế khung titan siêu bền, viền màn hình siêu mỏng." +
                "Máy được trang bị chip A17 Pro mạnh mẽ, camera tele 5x, camera chính 48MP chụp ảnh siêu nét." +
                "Nút Action mới thay thế nút gạt rung, cổng USB-C hỗ trợ sạc nhanh và truyền dữ liệu tốc độ cao.',157000,'IP27')");

        //Ipad
        db.execSQL("INSERT INTO SANPHAM VALUES('IPA1001','ipad_gen_5','iPad Gen 5', 6990000,'Màn hình Retina 9.7 inch, chip A9 mạnh mẽ, cảm biến vân tay Touch ID. Hỗ trợ Apple Pencil thế hệ đầu, thiết kế mỏng nhẹ" +
                "dễ mang theo. Giá cả phải chăng, phù hợp học tập và giải trí cơ bản." +
                "',50000,'IA10')," +
                "('IPA1002','ipad_pro_2','iPad Pro 2', 14490000, 'Màn hình Retina lớn nhất, chip A9X mạnh mẽ, 4 loa ngoài chất lượng cao. Hỗ trợ Apple Pencil và Smart Keyboard" +
                "cảm biến vân tay Touch ID. Phù hợp làm việc, sáng tạo nội dung và giải trí đa phương tiện." +
                "',20000,'IA10')," +
                "('IPA1003','ipad_gen_6','iPad Gen 6',7990000,'Màn hình Retina 9.7 inch, chip A10 Fusion mạnh hơn, hỗ trợ Apple Pencil. Thiết kế không đổi so với Gen 5, hiệu năng được cải thiện." +
                "Phù hợp học tập, làm việc và giải trí nhẹ nhàng." +
                "',60000,'IA10')," +
                "('IPA1004','ipad_pro_3','iPad Pro 3',18990000,'Màn hình ProMotion 120Hz mượt mà, chip A10X Fusion mạnh mẽ hơn. Camera được cải thiện, hỗ trợ Apple Pencil và Smart Keyboard." +
                "Thiết kế mỏng nhẹ, cảm biến vân tay Touch ID. Phù hợp làm việc, sáng tạo nội dung và giải trí chuyên nghiệp." +
                "',30000,'IA10')," +
                "('IPA1005','ipad_gen_7','iPad Gen 7',8990000,'Màn hình Retina 10.2 inch lớn hơn, chip A10 Fusion mạnh mẽ. Hỗ trợ Smart Keyboard, Apple Pencil, đa nhiệm tốt hơn." +
                "Thiết kế mỏng nhẹ, thời lượng pin dài. Phù hợp học tập, làm việc và giải trí đa phương tiện." +
                "',70000,'IA10')," +
                "('IPA1006','ipad_air_3','iPad Air 3',11490000,'Màn hình Retina 10.5 inch, chip A12 Bionic mạnh mẽ. Hỗ trợ Apple Pencil thế hệ 1 và Smart Keyboard." +
                "Thiết kế mỏng nhẹ, cảm biến vân tay Touch ID. Phù hợp học tập, làm việc và giải trí đa phương tiện." +
                "',30000,'IA10')," +
                "('IPA1007','ipad_mini_5','iPad mini 5',11990000,'Màn hình Retina 7.9 inch, chip A12 Bionic mạnh mẽ. Hỗ trợ Apple Pencil thế hệ 1, cảm biến vân tay Touch ID." +
                "Thiết kế nhỏ gọn, dễ dàng mang theo. Phù hợp đọc sách, ghi chú và giải trí nhẹ nhàng." +
                "',50000,'IA10')," +
                "('IPA1008','ipad_gen_8','iPad Gen 8',9990000,'Màn hình Retina 10.2 inch, chip A12 Bionic mạnh mẽ hơn. Hiệu năng vượt trội so với thế hệ trước, hỗ trợ Apple Pencil và" +
                "Smart Keyboard. Thiết kế không đổi, giá cả hợp lý. Phù hợp học tập, làm việc và giải trí nâng cao." +
                "',80000,'IA10')," +
                "('IPA1009','ipad_air_4','iPad Air 4',13990000,'Màn hình Liquid Retina 10.9 inch, chip A14 Bionic mạnh mẽ hơn. Hỗ trợ Apple Pencil thế hệ 2 và Magic Keyboard." +
                "Thiết kế hiện đại với Touch ID tích hợp trên nút nguồn. Phù hợp làm việc, sáng tạo nội dung và giải trí." +
                "',40000,'IA10')," +
                "('IPA1010','ipad_pro_4','iPad Pro 4',21990000,'Thiết kế mới với viền màn hình mỏng, Face ID thay thế Touch ID. Chip A12X Bionic mạnh mẽ, hỗ trợ Apple Pencil thế hệ 2." +
                "Cổng USB-C thay thế Lightning, đa nhiệm tốt hơn. Phù hợp làm việc, sáng tạo nội dung và giải trí cao cấp." +
                "',40000,'IA10')," +
                "('IPA1011','ipad_gen_9','iPad Gen 9',9990000,'Màn hình Retina 10.2 inch, chip A13 Bionic mạnh mẽ. Camera trước siêu rộng 12MP, hỗ trợ Center Stage. Thiết kế không đổi" +
                "thời lượng pin tốt. Phù hợp học tập, làm việc và giải trí trực tuyến." +
                "',90000,'IA10')," +
                "('IPA1012','ipad_mini_6','iPad mini 6',14990000,'Màn hình Liquid Retina 8.3 inch lớn hơn, viền mỏng hơn. Chip A15 Bionic mạnh mẽ, hỗ trợ Apple Pencil thế hệ 2." +
                "Thiết kế hiện đại với Touch ID tích hợp trên nút nguồn. Camera trước 12MP góc siêu rộng, hỗ trợ Center Stage." +
                "Phù hợp làm việc, sáng tạo nội dung và giải trí." +
                "',60000,'IA10')," +
                "('IPA1013','ipad_pro_5','iPad Pro 5',25990000,'Chip A12Z Bionic mạnh mẽ, hệ thống camera kép với cảm biến LiDAR. Hỗ trợ Magic Keyboard với bàn phím trackpad." +
                "Màn hình Liquid Retina sắc nét, hiệu năng đồ họa ấn tượng. Phù hợp làm việc chuyên nghiệp, sáng tạo nội dung và chơi game." +
                "',50000,'IA10')," +
                "('IPA1014','ipad_gen_10','iPad Gen 10',9990000,'Màn hình Liquid Retina 10.9 inch lớn hơn, viền mỏng hơn. Chip A14 Bionic mạnh mẽ, camera sau 12MP góc rộng." +
                "Thiết kế mới hiện đại, nhiều màu sắc trẻ trung. Hỗ trợ Magic Keyboard Folio, Apple Pencil thế hệ 1." +
                "',100000,'IA10')," +
                "('IPA1015','ipad_air_5','iPad Air 5',14090000,'Màn hình Liquid Retina 10.9 inch, chip M1 siêu mạnh. Hỗ trợ Apple Pencil thế hệ 2 và Magic Keyboard." +
                "Camera trước 12MP góc siêu rộng, hỗ trợ Center Stage. Thiết kế không đổi so với Air 4, hiệu năng vượt trội." +
                "',50000,'IA10')," +
                "('IPA1016','ipad_pro_6','iPad Pro 6',28999000,'Chip M1 mạnh mẽ như MacBook, màn hình Liquid Retina XDR trên bản 12.9 inch. Hỗ trợ 5G, Thunderbolt, camera trước siêu rộng" +
                "với Center Stage. Hiệu năng vượt trội, phù hợp làm việc chuyên nghiệp và sáng tạo nội dung." +
                "',60000,'IA10')," +
                "('IPA1017','ipad_air_6','iPad Air 6',18999000,'iPad Air 6 2024 trang bị chip A16 Bionic mạnh mẽ, màn hình Liquid Retina 10.9 inch với độ phân giải cao." +
                "Thiết kế mỏng nhẹ bằng nhôm tái chế, hỗ trợ Apple Pencil thế hệ 2 và Magic Keyboard. Kết nối 5G giúp truy cập" +
                "internet nhanh chóng và mượt mà." +
                "',60000,'IA10')," +
                "('IPA1018','ipad_pro_m4','iPad Pro M4',34999000,'Chip M2 mạnh mẽ hơn M1, hỗ trợ Wi-Fi 6E và Bluetooth 5.3. Apple Pencil hover cho phép tương tác trước khi chạm màn hình." +
                "Tính năng Stage Manager cải thiện đa nhiệm. Hiệu năng vượt trội, phù hợp làm việc chuyên nghiệp và sáng tạo nội dung." +
                "',140000,'IA10')");

        //Mac
        db.execSQL("INSERT INTO SANPHAM VALUES('IPM8401','macbook_air_m1','MacBook Air M1',18990000,'Thiết kế mỏng nhẹ, không quạt tản nhiệt, hoạt động êm ái. Chip M1 mạnh mẽ, hiệu năng vượt trội so với thế hệ trước. Màn hình Retina" +
                "sắc nét, bàn phím Magic Keyboard thoải mái. Thời lượng pin ấn tượng, lên đến 18 tiếng. Phù hợp học tập, làm việc văn phòng và giải trí" +
                "cơ bản." +
                "',100000,'IM84')," +
                "('IPM8402','macbook_pro_m1','MacBook Pro M1',24990000,'Thiết kế quen thuộc, hiệu năng mạnh mẽ nhờ chip M1. Màn hình Retina sắc nét, bàn phím Magic Keyboard cải tiến. Touch Bar và" +
                "Touch ID tiện lợi, hệ thống âm thanh chất lượng cao. Thời lượng pin lên đến 20 tiếng, phù hợp làm việc chuyên nghiệp và" +
                "sáng tạo nội dung." +
                "',10000,'IM84')," +
                "('IPM8403','mac_mini_m1_m2_m2pro','Mac mini M1',14490000,'Thiết kế nhỏ gọn, đa năng, dễ dàng kết nối với màn hình ngoài. Chip M1 mạnh mẽ, hiệu năng vượt trội so với thế hệ trước." +
                "Hỗ trợ Wi-Fi 6, nhiều cổng kết nối đa dạng. Giá bán hợp lý, phù hợp làm việc văn phòng, giải trí và lập trình." +
                "',100000,'IM84')," +
                "('IPM8404','imac_m1','iMac 24-inch M1',27990000,'Thiết kế mới mỏng nhẹ, 7 màu sắc trẻ trung. Màn hình Retina 4.5K 24 inch sắc nét, hệ thống 6 loa chất lượng cao. Chip M1 mạnh mẽ" +
                "camera FaceTime HD 1080p. Bàn phím Magic Keyboard với Touch ID, phù hợp làm việc, học tập và giải trí tại nhà." +
                "',10000,'IM84')," +
                "('IPM8405','macbook_pro_14_m1_2_pro_max','MacBook Pro 14-inch M1 Pro',44990000,'Màn hình Liquid Retina XDR mini-LED 120Hz sắc nét, chip M1 Pro mạnh mẽ. Thiết kế mới với cổng MagSafe 3, HDMI và khe cắm thẻ SD." +
                "Bàn phím Magic Keyboard cải tiến, không còn Touch Bar. Hiệu năng vượt trội, phù hợp làm việc chuyên nghiệp và sáng tạo nội dung." +
                "',1000,'IM84')," +
                "('IPM8406','macbook_pro_14_m1_2_pro_max','MacBook Pro 14-inch M1 Max',54490000,'Tương tự bản M1 Pro, nhưng mạnh mẽ hơn với chip M1 Max. Hiệu năng đồ họa vượt trội, xử lý video 8K mượt mà." +
                "Thời lượng pin ấn tượng lên đến 17 tiếng. Phù hợp làm việc chuyên sâu về đồ họa, dựng phim và xử lý âm thanh." +
                "',1000,'IM84')," +
                "('IPM8407','macbook_pro_16_m1_2_pro_max','MacBook Pro 16-inch M1 Pro',59990000,'Màn hình Liquid Retina XDR mini-LED 120Hz lớn hơn, chip M1 Pro mạnh mẽ. Thiết kế mới với cổng MagSafe 3, HDMI và khe cắm thẻ SD." +
                "Bàn phím Magic Keyboard cải tiến, không còn Touch Bar. Hiệu năng vượt trội, phù hợp làm việc chuyên nghiệp và sáng tạo nội dung." +
                "',1000,'IM84')," +
                "('IPM8408','macbook_pro_16_m1_2_pro_max','MacBook Pro 16-inch M1 Max',64990000,'Tương tự bản M1 Pro, nhưng mạnh mẽ hơn với chip M1 Max. Hiệu năng đồ họa vượt trội, xử lý video 8K mượt mà." +
                "Màn hình lớn hơn cho trải nghiệm làm việc tốt hơn. Thời lượng pin ấn tượng, lên đến 21 tiếng. Phù hợp làm việc chuyên sâu về đồ họa" +
                "dựng phim và xử lý âm thanh." +
                "',500,'IM84')," +
                "('IPM8409','macbook_air_2','MacBook Air M2',26990000,'Thiết kế mới mỏng nhẹ, nhiều màu sắc trẻ trung. Màn hình Liquid Retina 13.6 inch lớn hơn, sáng hơn. Chip M2 mạnh mẽ" +
                "hiệu năng vượt trội so với M1. Thời lượng pin lên đến 18 tiếng, sạc MagSafe tiện lợi. Phù hợp học tập, làm việc văn phòng và giải trí cơ bản." +
                "',200000,'IM84')," +
                "('IPM8410','macbook_pro_2','MacBook Pro M2',32990000,'Thiết kế không đổi so với bản M1, hiệu năng mạnh mẽ hơn nhờ chip M2. Màn hình Retina sắc nét, Touch Bar và Touch ID tiện lợi." +
                "Thời lượng pin lên đến 20 tiếng, phù hợp làm việc chuyên nghiệp và sáng tạo nội dung." +
                "',20000,'IM84')," +
                "('IPM8411','mac_studio_m1_2_max_ultra','Mac Studio M1 Max',49990000,'Thiết kế nhỏ gọn, mạnh mẽ như một trạm làm việc chuyên nghiệp. Chip M1 Max mạnh mẽ, xử lý đồ họa và video 8K mượt mà." +
                "Nhiều cổng kết nối đa dạng, hỗ trợ tối đa 4 màn hình. Phù hợp làm việc chuyên sâu về đồ họa, dựng phim và xử lý âm thanh." +
                "',1000,'IM84')," +
                "('IPM8412','mac_studio_m1_2_max_ultra','Mac Studio M1 Ultra',69990000,'Phiên bản mạnh mẽ nhất của Mac Studio, chip M1 Ultra vượt trội. Hiệu năng gấp đôi M1 Max, xử lý công việc nặng một cách dễ dàng." +
                "Nhiều cổng kết nối đa dạng, hỗ trợ tối đa 5 màn hình. Phù hợp làm việc chuyên sâu về đồ họa, dựng phim, phát triển phần mềm và" +
                "khoa học dữ liệu." +
                "',1000,'IM84')," +
                "('IPM8413','mac_mini_m1_m2_m2pro','Mac mini M2',15990000,'Thiết kế nhỏ gọn, đa năng, dễ dàng kết nối với màn hình ngoài. Chip M2 mạnh mẽ, hiệu năng vượt trội so với thế hệ trước. Hỗ trợ Wi-Fi 6E" +
                "nhiều cổng kết nối đa dạng. Giá bán hợp lý, phù hợp làm việc văn phòng, giải trí và lập trình." +
                "',200000,'IM84')," +
                "('IPM8414','mac_mini_m1_m2_m2pro','Mac mini M2 Pro',30990000,'Thiết kế nhỏ gọn nhưng mạnh mẽ với chip M2 Pro. Hiệu năng vượt trội so với Mac mini M2, xử lý công việc nặng mượt mà." +
                "Nhiều cổng kết nối đa dạng, hỗ trợ tối đa 2 màn hình. Phù hợp làm việc chuyên nghiệp, sáng tạo nội dung và lập trình." +
                "',20000,'IM84')," +
                "('IPM8415','macbook_pro_14_m1_2_pro_max','MacBook Pro 14-inch M2 Pro',49990000,'Màn hình Liquid Retina XDR mini-LED 120Hz sắc nét, chip M2 Pro mạnh mẽ. Thiết kế không đổi so với thế hệ trước, vẫn có MagSafe 3" +
                "HDMI và khe cắm thẻ SD. Hiệu năng vượt trội, phù hợp làm việc chuyên nghiệp và sáng tạo nội dung." +
                "',2000,'IM84')," +
                "('IPM8416','macbook_pro_14_m1_2_pro_max','MacBook Pro 14-inch M2 Max',62990000,'Tương tự bản M2 Pro, nhưng mạnh mẽ hơn với chip M2 Max. Hiệu năng đồ họa vượt trội, xử lý video 8K mượt mà. Thời lượng pin ấn tượng" +
                "lên đến 18 tiếng. Phù hợp làm việc chuyên sâu về đồ họa, dựng phim và xử lý âm thanh." +
                "',2000,'IM84')," +
                "('IPM8417','macbook_pro_16_m1_2_pro_max','MacBook Pro 16-inch M2 Pro',59990000,'Màn hình Liquid Retina XDR mini-LED 120Hz sắc nét, chip M2 Pro mạnh mẽ. Thiết kế không đổi so với thế hệ trước, vẫn có MagSafe 3" +
                "HDMI và khe cắm thẻ SD. Thời lượng pin lên đến 22 tiếng, hiệu năng vượt trội. Phù hợp làm việc chuyên nghiệp và sáng tạo nội dung." +
                "',2000,'IM84')," +
                "('IPM8418','macbook_pro_16_m1_2_pro_max','MacBook Pro 16-inch M2 Max',74990000,'Tương tự bản M2 Pro, nhưng mạnh mẽ hơn với chip M2 Max. Hiệu năng đồ họa vượt trội, xử lý video 8K mượt mà." +
                "Màn hình lớn cho trải nghiệm làm việc tốt hơn. Thời lượng pin lên đến 22 tiếng. Phù hợp làm việc chuyên sâu về đồ họa, dựng phim và" +
                "xử lý âm thanh." +
                "',200,'IM84')," +
                "('IPM8419','mac_studio_m1_2_max_ultra','Mac Studio M2 Max',59990000,'Thiết kế nhỏ gọn, mạnh mẽ như một trạm làm việc chuyên nghiệp. Chip M2 Max mạnh mẽ, xử lý đồ họa và video 8K mượt mà." +
                "Nhiều cổng kết nối đa dạng, hỗ trợ tối đa 5 màn hình. Phù hợp làm việc chuyên sâu về đồ họa, dựng phim và xử lý âm thanh." +
                "',200,'IM84')," +
                "('IPM8420','mac_studio_m1_2_max_ultra','Mac Studio M2 Ultra',99990000,'Phiên bản mạnh mẽ nhất của Mac Studio, chip M2 Ultra vượt trội. Hiệu năng gấp đôi M2 Max, xử lý công việc nặng một cách dễ dàng." +
                "Nhiều cổng kết nối đa dạng, hỗ trợ tối đa 6 màn hình. Phù hợp làm việc chuyên sâu về đồ họa, dựng phim, phát triển phần mềm và" +
                "khoa học dữ liệu." +
                "',20,'IM84')," +
                "('IPM8421','macbook_air_m3','MacBook Air M3',28990000,'Thiết kế siêu mỏng nhẹ, hai tùy chọn màn hình 13.6 inch và 15.3 inch. Chip M3 mạnh mẽ, hiệu năng vượt trội so với M2." +
                "Màn hình Liquid Retina sắc nét, thời lượng pin lên đến 18 tiếng. Phù hợp học tập, làm việc văn phòng và giải trí cơ bản." +
                "',300000,'IM84')," +
                "('IPM8422','macbook_pro_m3','MacBook Pro M3',32990000,'Thiết kế không đổi so với bản M2, hiệu năng mạnh mẽ hơn nhờ chip M3. Màn hình Retina sắc nét, Touch Bar và Touch ID tiện lợi." +
                "Thời lượng pin lên đến 20 tiếng, phù hợp làm việc chuyên nghiệp và sáng tạo nội dung." +
                "',30000,'IM84')," +
                "('IPM8423','macbook_pro_14_m3_pro_max','MacBook Pro 14-inch M3 Pro',49990000,'Màn hình Liquid Retina XDR mini-LED 120Hz sắc nét, chip M3 Pro mạnh mẽ. Thiết kế không đổi so với thế hệ trước" +
                "vẫn có MagSafe 3, HDMI và khe cắm thẻ SD. Hiệu năng vượt trội, phù hợp làm việc chuyên nghiệp và sáng tạo nội dung." +
                "',3000,'IM84')," +
                "('IPM8424','macbook_pro_14_m3_pro_max','MacBook Pro 14-inch M3 Max',74990000,'Tương tự bản M3 Pro, nhưng mạnh mẽ hơn với chip M3 Max. Hiệu năng đồ họa vượt trội, xử lý video 8K mượt mà." +
                "Thời lượng pin ấn tượng, lên đến 18 tiếng. Phù hợp làm việc chuyên sâu về đồ họa, dựng phim và xử lý âm thanh." +
                "',300,'IM84')," +
                "('IPM8425','macbook_pro_16_m3_pro_max','MacBook Pro 16-inch M3 Pro',59990000,'Màn hình Liquid Retina XDR mini-LED 120Hz lớn hơn, chip M3 Pro mạnh mẽ. Thiết kế không đổi so với thế hệ trước, vẫn có MagSafe 3" +
                "HDMI và khe cắm thẻ SD. Thời lượng pin lên đến 22 tiếng, hiệu năng vượt trội. Phù hợp làm việc chuyên nghiệp và sáng tạo nội dung." +
                "',3000,'IM84')," +
                "('IPM8426','macbook_pro_16_m3_pro_max','MacBook Pro 16-inch M3 Max',84990000,'Tương tự bản M3 Pro, nhưng mạnh mẽ hơn với chip M3 Max. Hiệu năng đồ họa vượt trội, xử lý video 8K mượt mà." +
                "Màn hình lớn cho trải nghiệm làm việc tốt hơn. Thời lượng pin lên đến 22 tiếng. Phù hợp làm việc chuyên sâu về đồ họa" +
                "dựng phim và xử lý âm thanh.',300,'IM84')");

        //Apple watch
        db.execSQL("INSERT INTO SANPHAM VALUES('IPW1501','apple_watch_series_3','Apple Watch Series 3',2490000,'Thiết kế vuông vức, màn hình OLED sắc nét. Chip S3 lõi kép, hiệu năng mượt mà. GPS tích hợp, theo dõi vận động hiệu quả." +
                "Chống nước 50 mét, nghe gọi trực tiếp. Giá rẻ, phù hợp người dùng mới." +
                "',30000,'IW15')," +
                "('IPW1502','apple_watch_series_4','Apple Watch Series 4',3990000,'Thiết kế mỏng hơn, màn hình lớn hơn, viền mỏng. Chip S4 lõi kép 64-bit, hiệu năng vượt trội. Cảm biến điện tâm đồ ECG, phát hiện té ngã." +
                "Mặt lưng ceramic, tăng khả năng kết nối. Phù hợp người dùng quan tâm sức khỏe." +
                "',40000,'IW15')," +
                "('IPW1503','apple_watch_series_5','Apple Watch Series 5',4590000,'Màn hình luôn bật Always-On Retina, xem giờ tiện lợi. Chip S5 lõi kép 64-bit, hiệu năng ổn định. La bàn tích hợp, định hướng chính xác." +
                "Chất liệu titan hoặc ceramic cao cấp. Phù hợp người dùng năng động, ưa khám phá." +
                "',50000,'IW15')," +
                "('IPW1504','apple_watch_series_6','Apple Watch Series 6',5490000,'Màn hình luôn bật sáng hơn, đo nồng độ oxy trong máu. Chip S6 SiP nhanh hơn 20%, theo dõi giấc ngủ chi tiết. Cảm biến đo độ cao luôn bật" +
                "nhiều tính năng sức khỏe. Tính năng Family Setup, sử dụng độc lập không cần iPhone." +
                "',60000,'IW15')," +
                "('IPW1505','apple_watch_se','Apple Watch SE 1',3990000,'Thiết kế giống Series 6, không có màn hình luôn bật và đo oxy máu. Chip S5 mạnh mẽ, cảm biến nhịp tim quang học." +
                "Theo dõi vận động, chống nước 50 mét. Giá cả phải chăng, nhiều tính năng hữu ích." +
                "',100000,'IW15')," +
                "('IPW1506','apple_watch_series_7','Apple Watch Series 7',5990000,'Màn hình lớn hơn, viền mỏng hơn, hiển thị nhiều nội dung hơn. Bàn phím QWERTY đầy đủ, nhập liệu dễ dàng. Sạc nhanh hơn" +
                "thời lượng pin vẫn ấn tượng. Chống bụi IP6X, chống nước 50 mét. Nhiều mặt đồng hồ mới." +
                "',70000,'IW15')," +
                "('IPW1507','apple_watch_series_8','Apple Watch Series 8',9990000,'Tính năng theo dõi nhiệt độ cơ thể, hỗ trợ sức khỏe phụ nữ. Phát hiện va chạm xe hơi, tự động gọi cứu hộ. Chip S8 nhanh hơn" +
                "hiệu năng mượt mà. Màn hình luôn bật sáng hơn, dễ nhìn ngoài trời. Nhiều mặt đồng hồ và dây đeo mới." +
                "',80000,'IW15')," +
                "('IPW1508','apple_watch_se_2','Apple Watch SE 2',6490000,'Thiết kế giống Series 8, không có màn hình luôn bật và đo nhiệt độ. Chip S8 mạnh mẽ, theo dõi vận động và giấc ngủ. Phát hiện té ngã" +
                "SOS khẩn cấp. Giá cả phải chăng, nhiều tính năng hữu ích." +
                "',200000,'IW15')," +
                "('IPW1509','apple_watch_ultra','Apple Watch Ultra',12990000,'Thiết kế hầm hố, độ bền vượt trội với vỏ titan. Màn hình lớn, sáng nhất, dễ nhìn dưới ánh nắng. Nút Action tùy chỉnh, còi báo động 86 decibel." +
                "Pin lên đến 36 tiếng, lý tưởng cho hoạt động ngoài trời." +
                "',10000,'IW15')," +
                "('IPW1510','apple_watch_series_9','Apple Watch Series 9',9990000,'Chip S9 mạnh mẽ hơn, phản hồi nhanh chóng. Màn hình luôn bật sáng hơn, dễ đọc ngoài trời. Cảm biến mới, theo dõi sức khỏe chính xác hơn." +
                "Nhiều tính năng watchOS 10 mới, trải nghiệm mượt mà." +
                "',90000,'IW15')," +
                "('IPW1511','apple_watch_ultra_2','Apple Watch Ultra 2',19990000,'Sáng hơn với độ sáng tối đa 3000 nit, dễ nhìn dưới nắng. Chip S9 SiP mạnh mẽ, hiệu suất vượt trội. Vỏ titan mới, nhẹ và bền hơn." +
                "Tính năng Wayfinder mới, dẫn đường bằng la bàn chính xác hơn." +
                "',20000,'IW15')");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS KHACHHANG");
            db.execSQL("DROP TABLE IF EXISTS NHANVIEN");
            db.execSQL("DROP TABLE IF EXISTS DONHANG");
            db.execSQL("DROP TABLE IF EXISTS HOADON");
            db.execSQL("DROP TABLE IF EXISTS SANPHAM");
            db.execSQL("DROP TABLE IF EXISTS CTDH");
            db.execSQL("DROP TABLE IF EXISTS LOAISANPHAM");
            db.execSQL("DROP TABLE IF EXISTS QUANLY");
            db.execSQL("DROP TABLE IF EXISTS GIOHANG");
            onCreate(db);
        }
    }
}
