package com.example.ishop.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "DBISHOP", null, 1);
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
        db.execSQL("INSERT INTO KHACHHANG VALUES('IC1001', 'tcme_thanh_phong', 'Nghiêu Thanh Phong','0192805205','thanhphong47@gmail.com','1234','Hồ Chí Minh')," +
                "('IC1002','cme_tan_sang','Nguyễn Tấn Sang','0251303999','tansang99@gmail.com','1234','Tiền Giang')," +
                "('IC1003','cme_giang_sinh','Ngô Thị Giáng Sinh','0212512202','giangsinh12@gmail.com','1234','Tây Ninh')," +
                "('IC1004','cme_huy_hoang','Nguyễn Văn Huy Hoàng','0201234999','hoang2000@gmail.com','1234','Hồ Chí Minh')," +
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
                "tuoiNV text," +
                "gioitinhNV text," +
                "diachiNV text," +
                "SDTNV text," +
                "emailNV text," +
                "matkhauNV text)";
        db.execSQL(tNHANVIEN);
        db.execSQL("INSERT INTO NHANVIEN VALUES('IE101','cme_thanh_phong','Nghiêu Thanh Phong','19','Nam','Hồ Chí Minh','0192805205','thanhphong47@gmail.com','1234')," +
                "('IE102','cme_tan_sang','Nguyễn Tấn Sang','25','Nữ','Tiền Giang','0251303999','tansang99@gmail.com','1234')," +
                "('IE103','cme_giang_sinh','Ngô Thị Giáng Sinh','21','Nam','Tây Ninh','0212512202','giangsinh12@gmail.com','1234')," +
                "('IE104','cme_huy_hoang','Nguyễn Văn Huy Hoàng','20','Nam','Hồ Chí Minh','0201234999','hoang2000@gmail.com','1234')," +
                "('IE105','cme_guang_liem','Ngô Thị Giáng Sinh','23','Nam','Hồ Chí Minh','0323613201','quangliem01@gmail.com','1234')");

        //table QUẢN LÝ
        String tQUANLY = "CREATE TABLE QUANLY(maQL text primary key, " +
                "anhQL text," +
                "tenQL text," +
                "tuoiQL text," +
                "gioitinhQL text," +
                "diachiQL text," +
                "SDTQL text," +
                "emailQL text," +
                "matkhauQL text)";
        db.execSQL(tQUANLY);
        db.execSQL("INSERT INTO QUANLY VALUES('IM9250','cme_quan_ly','Nghiêu Tấn Hoàng','24','Nam','Hồ Chí Minh','0212519292','tanhoang925@ishop.vn','1234')");

        //table LOẠI SẢN PHẨM
        String tLOAISANPHAM = "CREATE TABLE LOAISANPHAM(maLSP text primary key," +
                "anhLSP text references KHACHHANG(maKH)," +
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
                "('IO102','IPW1510',1,9990000)," +
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
                "ngayHD text," +
                "maNV text references NHANVIEN(maNV)," +
                "thanhtien integer)";
        db.execSQL(tHOADON);
        db.execSQL("INSERT INTO HOADON VALUES('IB','IO','14/07/2024','IE101', 1400000000)");

        //table ĐƠN HÀNG
        String tDONHANG = "CREATE TABLE DONHANG(maDH text primary key," +
                "maKH text references KHACHHANG(maKH)," +
                "ngayDH text," +
                "trangthaiDH text," +
                "maNV text references NHANVIEN(maNV)," +
                "thanhtien integer)";
        db.execSQL(tDONHANG);
        db.execSQL("INSERT INTO DONHANG VALUES('IO101','IC1005','14/07/2024','Đã xử lý','IE101', 0)," +
                "('IO102','IC1006','15/07/2024','Chưa xử lý','IE102', 0)," +
                "('IO103','IC1007','16/07/2024','Chưa xử lý','IE101', 0)," +
                "('IO104','IC1008','17/07/2024','Chưa xử lý','IE102', 0)");

        //table SẢN PHẨM
        String tSANPHAM = "CREATE TABLE SANPHAM(maSP text primary key, " +
                "anhSP text," +
                "tenSP text," +
                "giaSP integer," +
                "motaSP text," +
                "soluong integer," +
                "maLSP text references LOAISANPHAM(maLSP))";
        db.execSQL(tSANPHAM);
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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS KHACHHANG");
            db.execSQL("DROP TABLE IF EXISTS NHANVIEN");
            db.execSQL("DROP TABLE IF EXISTS DONHANG");
            db.execSQL("DROP TABLE IF EXISTS HOADON");
            db.execSQL("DROP TABLE IF EXISTS SANPHAM");
            db.execSQL("DROP TABLE IF EXISTS CTSP");
            db.execSQL("DROP TABLE IF EXISTS LOAISANPHAM");
            db.execSQL("DROP TABLE IF EXISTS QUANLY");
            onCreate(db);
        }
    }
}
