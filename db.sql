-- MySQL dump 10.13  Distrib 8.0.22, for macos10.15 (x86_64)
--
-- Host: localhost    Database: heroku_844f026e121e684
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cart_detail`
--

DROP TABLE IF EXISTS `cart_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_detail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_id` int DEFAULT NULL,
  `session_id` int DEFAULT NULL,
  `creation_time` date DEFAULT NULL,
  `repair_time` date DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `selected` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cart_prod` (`product_id`),
  KEY `cart_session` (`session_id`),
  CONSTRAINT `cart_prod` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `cart_session` FOREIGN KEY (`session_id`) REFERENCES `shopping_session` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_detail`
--

LOCK TABLES `cart_detail` WRITE;
/*!40000 ALTER TABLE `cart_detail` DISABLE KEYS */;
INSERT INTO `cart_detail` VALUES (38,14,8,'2022-10-17','2022-10-17',1,NULL),(40,14,7,'2022-10-17','2022-10-17',1,NULL),(41,15,7,'2022-10-17','2022-10-17',1,NULL);
/*!40000 ALTER TABLE `cart_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Men\'s Clothing'),(2,'Women\'s Clothing'),(3,'Toys Hobbies & Robots'),(4,'Mobiles & Tablets'),(5,'Consumer Electronics'),(6,'Books & Audible'),(7,'Beauty & Health'),(8,'Furniture Home & Office');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `colors`
--

DROP TABLE IF EXISTS `colors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `colors` (
  `id` int NOT NULL AUTO_INCREMENT,
  `color_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `colors`
--

LOCK TABLES `colors` WRITE;
/*!40000 ALTER TABLE `colors` DISABLE KEYS */;
INSERT INTO `colors` VALUES (1,'Red'),(2,'Yellow'),(3,'Blue'),(4,'Green'),(5,'White'),(6,'Black'),(7,'Gray'),(8,'Brown');
/*!40000 ALTER TABLE `colors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `creation_time` date DEFAULT NULL,
  `status_inv` tinyint(1) DEFAULT NULL,
  `delivery_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `voucher_user_id` int DEFAULT NULL,
  `payment_method_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `invoice_vu` (`voucher_user_id`),
  KEY `invoice_user` (`user_id`),
  KEY `invoice_payment` (`payment_method_id`),
  CONSTRAINT `invoice_payment` FOREIGN KEY (`payment_method_id`) REFERENCES `payment_method` (`id`),
  CONSTRAINT `invoice_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `invoice_vu` FOREIGN KEY (`voucher_user_id`) REFERENCES `voucher_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` VALUES (1,7,NULL,NULL,NULL,NULL,NULL),(2,7,NULL,NULL,NULL,NULL,NULL),(3,7,NULL,NULL,NULL,NULL,NULL),(4,7,NULL,NULL,NULL,NULL,NULL),(5,7,NULL,NULL,NULL,NULL,NULL),(6,7,NULL,NULL,NULL,NULL,NULL),(7,7,NULL,NULL,NULL,NULL,NULL),(8,7,'2022-10-17',NULL,NULL,NULL,NULL),(9,6,'2022-10-17',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `after_invoice_insert` AFTER INSERT ON `invoice` FOR EACH ROW BEGIN
    CALL cartItemToInvoice(NEW.USER_ID, NEW.ID);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `invoice_detail`
--

DROP TABLE IF EXISTS `invoice_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice_detail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `invoice_id` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idetail_invoice` (`invoice_id`),
  KEY `idetail_prod` (`product_id`),
  CONSTRAINT `idetail_invoice` FOREIGN KEY (`invoice_id`) REFERENCES `invoice` (`id`),
  CONSTRAINT `idetail_prod` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice_detail`
--

LOCK TABLES `invoice_detail` WRITE;
/*!40000 ALTER TABLE `invoice_detail` DISABLE KEYS */;
INSERT INTO `invoice_detail` VALUES (9,6,15,1),(10,6,16,1),(11,6,14,1),(12,6,13,1),(13,7,14,1),(14,8,15,3),(15,8,13,1),(16,9,14,4);
/*!40000 ALTER TABLE `invoice_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_method`
--

DROP TABLE IF EXISTS `payment_method`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_method` (
  `id` int NOT NULL AUTO_INCREMENT,
  `method_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_method`
--

LOCK TABLES `payment_method` WRITE;
/*!40000 ALTER TABLE `payment_method` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment_method` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `subcategory_id` int DEFAULT NULL,
  `discount` int DEFAULT NULL,
  `size_id` int DEFAULT NULL,
  `color_id` int DEFAULT NULL,
  `price` int DEFAULT NULL,
  `amount` int DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `supplier_id` int DEFAULT NULL,
  `description` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `deleted` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `subcate_prod` (`subcategory_id`),
  KEY `size_prod` (`size_id`),
  KEY `color_prod` (`color_id`),
  KEY `supplier_prod` (`supplier_id`),
  CONSTRAINT `color_prod` FOREIGN KEY (`color_id`) REFERENCES `colors` (`id`),
  CONSTRAINT `size_prod` FOREIGN KEY (`size_id`) REFERENCES `sizes` (`id`),
  CONSTRAINT `subcate_prod` FOREIGN KEY (`subcategory_id`) REFERENCES `subcategory` (`id`),
  CONSTRAINT `supplier_prod` FOREIGN KEY (`supplier_id`) REFERENCES `suppliers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Áo khoác Cardigan',10,20,3,6,229000,100,'1.jpg',1,'Áo khoác cardigan nam chất nỉ CD03 M.RO giữ ấm mùa thu đông dáng ngắn phong cách Hàn Quốc Mrhero màu nâu đen dễ phối đồ',1),(2,'Áo khoác Cardigan xám',10,20,3,7,219000,100,'man_cardigan2.jpg',1,'Thời Trang Cổ Chữ V Phối Cúc Cực Basic, dày dặn, mềm mịn, co giãn 4 chiều, không xù, không nhăn, không hút bụi bẩn',0),(3,'Áo khoác Cardigan nâu',10,NULL,3,8,239000,100,'man_cardigan3.jpg',1,'Áo khoác cadigan nam chất cotton tổ ong cao cấp, dễ mặc dễ phối đồ, hợp mọi thời đại',0),(4,'Áo khoác Cardigan trắng',10,NULL,3,5,239000,100,'man_cardigan4.jpg',1,'Áo Khoác Cardigan NECNSEZRY Vải Nỉ Bông màu KEM Form Rộng Phong Cách Ulzzang Unisex',0),(5,'Áo Khoác Classic Tối Giản M4 (Nâu vàng)',6,NULL,4,2,199000,100,'outerwear05.jpg',1,'Chất liệu: Vải dù. Thành phần: 100% Polyester. May logo inox. Bên trong áo có túi xếp gọn lại tiện lợi mang đi du lịch. Túi trong tiện dụng. Reverse Coil Zipper (Những chiếc khoá túi này có bề mặt ngoài dẹp, phẳng trong khi răng khoá thì ở trong)',0),(6,'Áo Khoác Classic Tối Giản M4 (Xanh rêu)',6,NULL,4,4,199000,100,'outerwear18.jpg',1,'Chất liệu: Vải dù. Thành phần: 100% Polyester. May logo inox. Bên trong áo có túi xếp gọn lại tiện lợi mang đi du lịch. Túi trong tiện dụng. Reverse Coil Zipper (Những chiếc khoá túi này có bề mặt ngoài dẹp, phẳng trong khi răng khoá thì ở trong)',0),(7,'Áo Phao Nam Có Mũ Siêu Nhẹ Siêu Ấm Đen',6,NULL,4,6,389000,100,'outerwear04.jpg',1,'Áo có cấu trúc 3 lớp chắc chắn: Lớp ngoài và lớp lót được làm từ 100% Nylon, Lớp giữa bông nhẹ 100% polyester',0),(8,'Áo Phao Nam Có Mũ Siêu Nhẹ Siêu Ấm Vàng',6,NULL,4,2,389000,100,'outerwear17.jpg',1,'Áo có cấu trúc 3 lớp chắc chắn: Lớp ngoài và lớp lót được làm từ 100% Nylon, Lớp giữa bông nhẹ 100% polyester',0),(9,'Áo Sơ Mi Nam Trắng',5,NULL,4,5,219000,100,'shirt3.jpg',1,'Đứng dáng , Chất vải mềm mịn, khô thoáng, đứng dáng. , Lados cam kết: , + 100% hình ảnh sản phẩm do Lados tự chụp, hoàn tiền nếu ...',0),(10,'Áo Sơ Mi Xanh Khó chịu',5,NULL,4,3,219000,100,'shirt4.jpg',1,'Đứng dáng , Chất vải mềm mịn, khô thoáng, đứng dáng. , Lados cam kết: , + 100% hình ảnh sản phẩm do Lados tự chụp, hoàn tiền nếu ...',0),(11,'Áo Sơ Mi Đen Xám Ngắn Tay',5,NULL,4,6,219000,100,'shirt6.jpg',1,'Áo sơ mi nam ngắn tay phong cách Anh QUốc ZH480 thiết kế trẻ trung, lịch lãm; chất liệu bông pha cotton mát mẻ, thoáng khí; là ưu tiên hàng đầu cho các nam giới trong mùa hè, áo dễ phối đồ với quần jean, kaki, thích hợp mặc nhiều địa điểm',0),(12,'Áo Sơ Mi Xám Ngắn Tay',5,NULL,4,7,219000,100,'shirt7.jpg',1,'Áo sơ mi nam ngắn tay phong cách Anh QUốc ZH480 thiết kế trẻ trung, lịch lãm; chất liệu bông pha cotton mát mẻ, thoáng khí; là ưu tiên hàng đầu cho các nam giới trong mùa hè, áo dễ phối đồ với quần jean, kaki, thích hợp mặc nhiều địa điểm',0),(13,'Áo Thun Đen Xanh Dài Tay',1,50,4,6,169000,100,'tshirt02.jpg',1,'Mua sắm áo thun nam bằng chất liệu vải an toàn cho da với rất nhiều kiểu dáng và màu sắc khác nhau, từ các công nghệ tiên tiến nhất như DRY-EX, AIRism.',0),(14,'Áo Thun Trắng Xanh Dài Tay',1,50,4,5,169000,100,'tshirt03.jpg',1,'Mua sắm áo thun nam bằng chất liệu vải an toàn cho da với rất nhiều kiểu dáng và màu sắc khác nhau, từ các công nghệ tiên tiến nhất như DRY-EX, AIRism.',0),(15,'Áo Thun Xanh Trắng Ngắn Tay',1,20,4,3,99000,100,'tshirt08.jpg',1,'Mua sắm áo thun nam bằng chất liệu vải an toàn cho da với rất nhiều kiểu dáng và màu sắc khác nhau, từ các công nghệ tiên tiến nhất như DRY-EX, AIRism.',0),(16,'Áo Thun Vàng Nhạt Ngắn Tay',1,25,4,2,99000,100,'tshirt08.jpg',1,'Mua sắm áo thun nam bằng chất liệu vải an toàn cho da với rất nhiều kiểu dáng và màu sắc khác nhau, từ các công nghệ tiên tiến nhất như DRY-EX, AIRism.',0),(17,'Áo Hoodie Đen',2,NULL,4,6,235000,100,'hoodie01.jpg',1,'Sở thích của con người luôn thay đổi không ngừng từng ngày. Do đó các nhà thiết kế đã biến tấu áo hoodie theo nhiều cách khác nhau để bắt kịp với xu hướng của thời trang. Ngoài sự lên ngôi của hoodie oversize, các mẫu hoodie croptop, hoodie tay lỡ, tay siêu dài… cũng xuất hiện và chiếm được tình yêu của các tín đồ yêu thích dòng trang phục này.',0),(18,'Áo Hoodie Xanh Anime',2,NULL,4,3,235000,100,'hoodie02.jpg',1,'Sở thích của con người luôn thay đổi không ngừng từng ngày. Do đó các nhà thiết kế đã biến tấu áo hoodie theo nhiều cách khác nhau để bắt kịp với xu hướng của thời trang. Ngoài sự lên ngôi của hoodie oversize, các mẫu hoodie croptop, hoodie tay lỡ, tay siêu dài… cũng xuất hiện và chiếm được tình yêu của các tín đồ yêu thích dòng trang phục này.',0),(19,'Áo Hoodie Trắng Anime',2,NULL,4,5,235000,100,'hoodie03.jpg',1,'Sở thích của con người luôn thay đổi không ngừng từng ngày. Do đó các nhà thiết kế đã biến tấu áo hoodie theo nhiều cách khác nhau để bắt kịp với xu hướng của thời trang. Ngoài sự lên ngôi của hoodie oversize, các mẫu hoodie croptop, hoodie tay lỡ, tay siêu dài… cũng xuất hiện và chiếm được tình yêu của các tín đồ yêu thích dòng trang phục này.',0),(20,'Áo Hoodie Xanh Sky',2,NULL,4,3,235000,100,'hoodie03.jpg',1,'Sở thích của con người luôn thay đổi không ngừng từng ngày. Do đó các nhà thiết kế đã biến tấu áo hoodie theo nhiều cách khác nhau để bắt kịp với xu hướng của thời trang. Ngoài sự lên ngôi của hoodie oversize, các mẫu hoodie croptop, hoodie tay lỡ, tay siêu dài… cũng xuất hiện và chiếm được tình yêu của các tín đồ yêu thích dòng trang phục này.',0),(21,'Quần Jeans Xanh Sky',25,NULL,4,3,219000,100,'jean01.jpg',1,'Thiết kế form ôm. Hai túi trước và hai túi sau vừa tiện dụng. Đường may đẹp mắt, cẩn thận tỉ mỉ, tạo sự thanh lịch, lịch lãm, trông bạn như một người thành đạt đầy thu hút. Chất liệu vải mềm mại, co giản tốt tạo nên sự thoái mái cho người mặc, có nhiều size phù hợp theo dáng người.',0),(22,'Quần Jeans Xám Ngầu',25,NULL,4,7,219000,100,'jean02.jpg',1,'Thiết kế form ôm. Hai túi trước và hai túi sau vừa tiện dụng. Đường may đẹp mắt, cẩn thận tỉ mỉ, tạo sự thanh lịch, lịch lãm, trông bạn như một người thành đạt đầy thu hút. Chất liệu vải mềm mại, co giản tốt tạo nên sự thoái mái cho người mặc, có nhiều size phù hợp theo dáng người.',0),(23,'Quần Jeans Xanh Ôm',25,NULL,4,3,219000,100,'jean03.jpg',1,'Thiết kế form ôm. Hai túi trước và hai túi sau vừa tiện dụng. Đường may đẹp mắt, cẩn thận tỉ mỉ, tạo sự thanh lịch, lịch lãm, trông bạn như một người thành đạt đầy thu hút. Chất liệu vải mềm mại, co giản tốt tạo nên sự thoái mái cho người mặc, có nhiều size phù hợp theo dáng người.',0),(24,'Quần Jeans Xanh Baggy',25,NULL,4,3,219000,100,'jean04.jpg',1,'Thiết kế form ôm. Hai túi trước và hai túi sau vừa tiện dụng. Đường may đẹp mắt, cẩn thận tỉ mỉ, tạo sự thanh lịch, lịch lãm, trông bạn như một người thành đạt đầy thu hút. Chất liệu vải mềm mại, co giản tốt tạo nên sự thoái mái cho người mặc, có nhiều size phù hợp theo dáng người.',0),(25,'Áo Thun Nữ Ngắn(Vàng đất) ',29,NULL,2,2,88000,100,'ao_pic03.jpg',1,'Chất liệu cotton 100% cho độ thấm hút mồ hôi cao, luôn thoáng mát bất chấp ngày nóng , Áo siêu nhẹ, chỉ 150g cho bạn cảm giác thoải ...',0),(26,'Áo Thun Nữ (Xanh lơ)',29,NULL,2,4,129000,100,'ao_pic07.jpg',1,'Chất liệu cotton 100% cho độ thấm hút mồ hôi cao, luôn thoáng mát bất chấp ngày nóng , Áo siêu nhẹ, chỉ 150g cho bạn cảm giác thoải ...',0),(27,'Áo Thun Nữ Cổ Trái Tim(Xanh)',29,NULL,2,3,98000,100,'ao_pic20.jpg',1,'Chất liệu cotton 100% cho độ thấm hút mồ hôi cao, luôn thoáng mát bất chấp ngày nóng , Áo siêu nhẹ, chỉ 150g cho bạn cảm giác thoải ...',0),(28,'Áo Thun Nữ Ngắn(Xám)',29,NULL,2,7,88000,100,'ao_pic23.jpg',1,'Chất liệu cotton 100% cho độ thấm hút mồ hôi cao, luôn thoáng mát bất chấp ngày nóng , Áo siêu nhẹ, chỉ 150g cho bạn cảm giác thoải ...',0),(29,'Áo Sơ Mi Nữ Sọc Trắng',31,NULL,2,2,165000,100,'shirts2.jpg',1,'Áo sơ mi nữ sọc tay dài mới đẹp được thiết kế mềm mại, thấm hút mồ hôi tốt, cho bạn gái sự nhẹ nhàng thoải mái, cũng như tự tin và nổi bật hơn mỗi khi xuất hiện trước đám đông. Áo sơ mi nữ sọc tay dài dễ dàng kết hợp với Jeans, chân váy, hoặc đơn gian cùng với Short là bạn đã có 1 set đồ thoai mái.',0),(30,'Áo Sơ Mi Nữ Sọc Xanh Đen',31,NULL,2,1,165000,100,'shirts3.jpg',1,'Áo sơ mi nữ sọc tay dài mới đẹp được thiết kế mềm mại, thấm hút mồ hôi tốt, cho bạn gái sự nhẹ nhàng thoải mái, cũng như tự tin và nổi bật hơn mỗi khi xuất hiện trước đám đông. Áo sơ mi nữ sọc tay dài dễ dàng kết hợp với Jeans, chân váy, hoặc đơn gian cùng với Short là bạn đã có 1 set đồ thoai mái.',0),(31,'Áo Sơ Mi Nữ Bi Trắng',31,NULL,2,6,165000,100,'shirts6.jpg',1,'Áo sơ mi nữ sọc tay dài mới đẹp được thiết kế mềm mại, thấm hút mồ hôi tốt, cho bạn gái sự nhẹ nhàng thoải mái, cũng như tự tin và nổi bật hơn mỗi khi xuất hiện trước đám đông. Áo sơ mi nữ sọc tay dài dễ dàng kết hợp với Jeans, chân váy, hoặc đơn gian cùng với Short là bạn đã có 1 set đồ thoai mái.',0),(32,'Áo Sơ Mi Nữ Ngắn Tay Bi Đen',31,NULL,2,5,165000,100,'shirts7.jpg',1,'Áo sơ mi nữ sọc tay dài mới đẹp được thiết kế mềm mại, thấm hút mồ hôi tốt, cho bạn gái sự nhẹ nhàng thoải mái, cũng như tự tin và nổi bật hơn mỗi khi xuất hiện trước đám đông. Áo sơ mi nữ sọc tay dài dễ dàng kết hợp với Jeans, chân váy, hoặc đơn gian cùng với Short là bạn đã có 1 set đồ thoai mái.',0),(33,'Trouser Kem Cá Tính',36,NULL,2,7,225000,100,'trousers1.jpg',1,'Đặc tính vải: Đứng form; Hạn chế nhăn ủi; Vải mềm, mịn, thoáng mát; Không xù lông; Vải dày tương đối (không mỏng dính hoặc dày cộm).',0),(34,'Trouser Nâu Đất',36,NULL,2,8,225000,100,'trousers3.jpg',1,'Đặc tính vải: Đứng form; Hạn chế nhăn ủi; Vải mềm, mịn, thoáng mát; Không xù lông; Vải dày tương đối (không mỏng dính hoặc dày cộm).',0),(35,'Trouser Xanh Đen',36,NULL,2,3,225000,100,'trousers6.jpg',1,'Đặc tính vải: Đứng form; Hạn chế nhăn ủi; Vải mềm, mịn, thoáng mát; Không xù lông; Vải dày tương đối (không mỏng dính hoặc dày cộm).',0),(36,'Trouser Đen',36,NULL,2,6,225000,100,'trousers3.jpg',1,'Đặc tính vải: Đứng form; Hạn chế nhăn ủi; Vải mềm, mịn, thoáng mát; Không xù lông; Vải dày tương đối (không mỏng dính hoặc dày cộm).',0),(37,'Đồ Ngủ Xanh Lá Cây Hoa Văn',41,NULL,2,4,205000,100,'pajamas03.jpg',1,'Giữ ấm cho cơ thể trong mùa đông giá lạnh, tạo sự thoải mái, thoáng mát cho các bạn nữ cả vào mùa hè oi ả,… không ngại bất kì diễn biến thời tiết nào.',0),(38,'Đồ Ngủ Xanh Dương Hoa Văn',41,NULL,2,3,205000,100,'pajamas04.jpg',1,'Giữ ấm cho cơ thể trong mùa đông giá lạnh, tạo sự thoải mái, thoáng mát cho các bạn nữ cả vào mùa hè oi ả,… không ngại bất kì diễn biến thời tiết nào.',0),(39,'Đồ Ngủ Xanh Nhạt Họa Tiết',41,NULL,2,3,205000,100,'pajamas06.jpg',1,'Giữ ấm cho cơ thể trong mùa đông giá lạnh, tạo sự thoải mái, thoáng mát cho các bạn nữ cả vào mùa hè oi ả,… không ngại bất kì diễn biến thời tiết nào.',0),(40,'Đồ Ngủ Nâu Nhạt Họa Tiết',41,NULL,2,8,205000,100,'pajamas05.jpg',1,'Giữ ấm cho cơ thể trong mùa đông giá lạnh, tạo sự thoải mái, thoáng mát cho các bạn nữ cả vào mùa hè oi ả,… không ngại bất kì diễn biến thời tiết nào.',0),(41,'Đầm Xòe Cổ V Tay Lửng 3/4',27,NULL,2,6,384000,100,'skirt01.jpg',1,'Chất liệu vải mềm mịn tạo ra một hình dáng xòe tuyệt đẹp. Cũng có thể tạo kiểu như một lớp áo khoác bên ngoài nhẹ.',0),(42,'Đầm Xòe Cổ V Tay Lửng In Họa Tiết 3/4',27,NULL,2,4,384000,100,'skirt02.jpg',1,'Chất liệu vải mềm mịn tạo ra một hình dáng xòe tuyệt đẹp. Cũng có thể tạo kiểu như một lớp áo khoác bên ngoài nhẹ.',0),(43,'Đầm Sơ Mi Denim Dáng Chữ A Dài Tay',27,NULL,2,3,334000,100,'skirt04.jpg',1,'Đầm sơ mi chất liệu denim mềm mại, thoải mái. Cũng có thể tạo kiểu như một lớp áo khoác nhẹ bên ngoài.',0),(44,'Đầm Xòe Vải Dạ Mềm',27,NULL,2,3,685000,100,'skirt05.jpg',1,'Đã cập nhật cổ áo. Có thể tạo kiểu như một lớp áo khoác bên ngoài nếu không gài nút.',0),(45,'Đầm Kiểu Sơ Mi Dáng Chữ A Dài Tay',27,NULL,2,5,560000,100,'skirt08.jpg',1,'Một chiếc đầm cotton cao cấp mềm mại và thoải mái. Có thể tạo kiểu như một lớp áo khoác bên ngoài nhẹ',0),(46,'Đầm Mini Xòe Cổ V Dài Tay',27,NULL,2,6,494000,100,'skirt10.jpg',1,'Chất liệu vải mềm mịn tạo ra một hình dáng loe tuyệt đẹp. Cũng có thể tạo kiểu như một lớp áo nhẹ bên ngoài.',0),(47,'Đầm Kiểu Sơ Mi Kẻ Sọc Dài Tay',27,NULL,2,8,554000,100,'skirt13.jpg',1,'Một chiếc đầm thoáng mát bằng chất liệu cotton nhẹ. Đầm có thể mặc riêng lẻ hoặc như một lớp bên ngoài nhẹ.',0),(48,'Đầm Vải Lambswool Cao Cấp',27,NULL,2,8,554000,100,'skirt14.jpg',1,'Kiểu đan chunky đặc biệt với các chi tiết thời trang. Sản phẩm tuyệt vời khi kết hợp với quần.',0),(49,'Kính Mát Gập Wellington',51,NULL,2,8,222000,100,'glasses03.jpg',2,'Kính râm thân thiện với mắt, ngăn chặn ánh sáng xanh và tia UV. Thiết kế có thể gập lại để dễ dàng mang theo.',0),(50,'Kính Mát Boston',51,NULL,2,6,190000,100,'glasses01.jpg',2,'Gọng kính khác biệt tạo nên phong cách riêng. Có thể dùng như kính râm hoặc kính thời trang.',0),(51,'Kính Mát Boston Kết Hợp',51,NULL,2,8,190000,100,'glasses02.jpg',2,'Kính râm thân thiện với mắt, ngăn ánh sáng xanh và tia UV. Gọng kính tròn.',0),(52,'Kính Mát Gọng Kim Loại Tròn',51,NULL,2,5,200000,100,'glasses04.jpg',2,'Gọng kính khác biệt tạo nên phong cách riêng. Có thể dùng như kính râm hoặc kính thời trang.',0),(53,'Kính Mát Hình Giọt Nước',51,NULL,2,8,200000,100,'glasses07.jpg',2,'Kính râm thân thiện với mắt, ngăn chặn ánh sáng xanh và tia UV.',0),(54,'JW Anderson Tote Bag',55,NULL,2,6,640000,50,'bag1.jpg',2,'Kiểu dáng kết hợp vải thời trang. Một tote linh hoạt với nhiều dung lượng lưu trữ.',0),(55,'Túi Đeo Vai Mini Tròn',55,NULL,2,1,244000,50,'bag2.jpg',2,'Hình dạng tròn cho cảm giác vừa vặn thoải mái. Kích thước hoàn hảo, với nhiều túi thiết thực.',0),(56,'Túi Đeo Vai Rút Dây',55,NULL,2,5,599000,50,'bag3.jpg',2,'Túi tiện lợi và có thể dễ dàng mang theo. Ngoài ra còn có một túi bên trong có khóa kéo.',0),(57,'Túi Đeo Vai Giả Da',55,NULL,2,6,619000,50,'bag4.jpg',2,'Một chiếc túi đa năng với vẻ ngoài cao cấp. Thiết kế thực tế với nhiều không gian chứa đồ.',0),(58,'HEATTCH Mũ',52,NULL,2,8,145000,50,'access1.jpg',2,'Xua tan thời tiết lạnh với HEATTECH ấm áp. Kiểu đan cao cấp đa năng rất dễ để phối đồ.',0),(59,'Mũ Nhung Chống Tia UV',52,NULL,2,6,164000,50,'access2.jpg',2,'Tạo sự ấm áp, vải nhung dày dặn. Với công nghệ chống tia UV. UPF50 +.',0),(60,'Mũ Melton Metro Chống UV',52,NULL,2,8,194000,50,'access3.jpg',2,'Một chiếc túi đa năng với vẻ ngoài cao cấp. Kích thước vừa phải cho những chuyến đi chơi nhanh chóng.',0),(61,'Mũ BLOCKTECH',52,NULL,2,6,175000,50,'access4.jpg',2,'Một chiếc túi đa năng với vẻ ngoài cao cấp. Kích thước vừa phải cho những chuyến đi chơi nhanh chóng.',0),(62,'Mũ Bucket',52,NULL,2,6,240000,50,'access5.jpg',2,'Cảm giác cao cấp và ấm áp của 100% vải len. Chiếc mũ ấm áp này là sự lựa chọn phù hợp hoàn hảo.',0),(63,'Thắt Lưng Sát Thủ',54,NULL,3,6,310000,50,'belt1.jpg',2,'Sản phẩm hoàn thiện với cảm giác cổ điển. Khóa có hình dạng giống như một chiếc yên ngựa để có cách nhìn giản dị.',0),(64,'Thắt Lưng Trắng Bông Bưởi',54,NULL,3,5,311000,50,'belt2.jpg',2,'Một chiếc thắt lưng bản mỏng thanh lịch. Rất thích hợp dùng trong trường hợp bỏ áo vào quần giúp tôn lên vòng eo .',0),(65,'Áo Cardigan Chống UV Cổ Tròn',45,NULL,2,8,489000,100,'cardigan1.jpg',1,'Đường cắt rộng rãi vừa phải hoàn hảo để trở thành một lớp áo khoác bên ngoài tuyệt vời. Chức năng linh hoạt, phù hợp cho mọi dịp.',0),(66,'Áo Cardigan Cổ Tròn Chống UV',45,NULL,2,4,391000,100,'cardigan2.jpg',1,'Một chiếc áo cardigan giúp chống tia UV khi hoạt động ngoài trời. Thiết kế được cải tiến rộng rãi hơn.',0),(67,'Extra Fine Merino Áo Cardigan Cổ Tròn Dài Tay',45,NULL,2,3,784000,100,'cardigan3.jpg',1,'Kết cấu tinh tế của 100% vải len cao cấp. Các chi tiết thiết kế được cải tiến tạo cảm giác thoải mái hơn.',0),(68,'Áo Cardigan Sợi Souffle Dáng Ngắn Dài Tay',45,NULL,2,3,784000,100,'cardigan4.jpg',1,'Khi chạm vào cảm giác mượt mà và không bị ngứa. Gài nút và tạo kiểu như một chiếc áo riêng lẻ.',0),(69,'Áo Cardigan Vải Lambswool Cao Cấp',45,NULL,2,8,1275000,100,'cardigan5.jpg',1,'Một chiếc áo len cao cấp với kiểu đan chunky đơn giản. Sản phẩm hoàn hảo để kết hợp nhiều lớp.',0),(70,'Áo Cardigan Sợi Souffle Dệt 3D',45,NULL,2,5,784000,100,'cardigan6.jpg',1,'Kết cấu bông mịn đảm bảo mang lại cảm giác mềm mại không bao giờ bị ngứa. Hình bóng tròn cho một phong cách đáng yêu.',0),(71,'Extra Fine Merino Áo Cardigan Gân Dáng Ngắn',45,NULL,2,4,784000,100,'cardigan7.jpg',1,'Dựa trên phản hồi của khách hàng, áo cardigan gân mới của chúng tôi được thiết kế để mặc trong mọi điều kiện thời tiết.',0),(72,'Áo Cardigan Mắt Lưới Dáng Ngắn',45,NULL,2,5,489000,100,'cardigan8.jpg',1,'Kiểu đan chunky mát mẻ. Một chiếc áo cardigan đa năng cho mọi phong cách.',0),(73,'Quần Jeans Ống Cong',35,NULL,2,6,980000,100,'jeans01.jpg',1,'Vải mềm mại, cảm giác thoải mái. Hình dáng đẹp mắt giúp tôn dáng cho đôi chân.',0),(74,'Quần Jean Siêu Co Giãn Dáng Skinny',35,NULL,2,3,980000,100,'jeans02.jpg',1,'Độ giãn đáng kinh ngạc cho cảm giác vừa vặn thoải mái. Thắt lưng cao tạo hiệu ứng tôn dáng.',0),(75,'Quần Jean Dáng Skinny Cạp Cao Siêu Co Giãn',35,NULL,2,6,980000,100,'jeans03.jpg',1,'Sự co giãn tuyệt vời kết hợp một sự vừa vặn và thoải mái tuyệt vời. Cạp cao tạo hiệu ứng tôn lên đôi chân thanh lịch.',0),(76,'Quần Jeans Skinny Siêu Co Giãn Cạp Cao',35,NULL,2,3,980000,100,'jeans04.jpg',1,'Đã cập nhật thành thiết kế cạp cao để vừa vặn hơn.',0),(77,'Quần Jeans Skinny Siêu Co Giãn Cạp Cao',35,NULL,2,6,980000,100,'jeans05.jpg',1,'Đã cập nhật thành thiết kế cạp cao để vừa vặn hơn.',0),(78,'Quần Jean Dáng Skinny Fit Siêu Co Giãn',35,NULL,2,8,686000,100,'jeans06.jpg',1,'Thiết kế bó, nhưng có tính co giãn cực kỳ tốt và dễ mặc. Màu sắc theo xu hướng sẽ tạo nét cho trang phục của bạn.',0),(79,'Quần Boyfriend Ống Ôm Dần',35,NULL,2,3,980000,100,'jeans07.jpg',1,'Quần jean ống ôm dần với kiểu dáng denim cổ điển. Hoàn hảo cho một phong cách thoải mái.',0),(80,'Quần Boyfriend Ống Ôm Dần',35,NULL,2,3,980000,100,'jeans08.jpg',1,'Quần jean ống ôm dần với kiểu dáng denim cổ điển. Hoàn hảo cho một phong cách thoải mái.',0),(81,'Quần Jean Lửng Dáng Rộng',35,NULL,2,3,980000,100,'jeans09.jpg',1,'Quần jean dài đến mắt cá chân với kiểu dáng rộng.',0),(82,'Quần Jean Bầu Siêu Co Giãn',35,NULL,2,3,980000,100,'jeans10.jpg',1,'Nhẹ nhàng ôm bụng và tôn lên đôi chân. Chiếc quần jean hoàn hảo để bạn mặc trong suốt thai kỳ.',0),(83,'Quần Jeans Ống Loe',35,NULL,2,3,784000,100,'jeans11.jpg',1,'Đường cắt ống loe phong cách. Phần eo rộng và các chi tiết thiết kế thời trang.',0),(84,'Quần Jean Ống Loe Dáng Slim Fit',35,NULL,2,6,980000,100,'jeans12.jpg',1,'Kiểu dáng đẹp, dáng ôm với đường viền loe tinh tế. Quần jean thoải mái với tác dụng tôn lên đôi chân.',0),(85,'Quần Jean Baggy',35,NULL,2,3,980000,100,'jeans13.jpg',1,'100% cotton denim và một đường cắt rộng thoải mái.',0),(86,'NỮ QUẦN JEANS',35,NULL,2,6,2453000,100,'jeans14.jpg',1,'Kéo dài tuyệt vời cho một phù hợp tâng bốc. Thiết kế thời trang với đường khâu tinh tế.',0),(87,'Quần Jean Lửng Dáng Rộng (Damaged)',35,NULL,2,3,980000,100,'jeans15.jpg',1,'Quần jean lửng ống rộng vừa vặn. Thiết kế cá tính nhưng vẫn thoải mái.',0),(88,'Quần Denim Jersey',35,NULL,2,3,784000,100,'jeans16.jpg',1,'Quần short đa năng màu indigo.',0),(89,'Quần Short In Họa Tiết Dáng Slim Fit Co Giãn',26,NULL,3,8,489000,100,'short01.jpg',1,'Vẻ ngoài tinh tế với kiểu dáng và chất liệu vải đẹp mắt.',0),(90,'Quần Short Co Giãn Dáng Slim Fit',26,NULL,3,6,489000,100,'short02.jpg',1,'Tận hưởng vẻ ngoài tinh tế với kiểu cắt, thiết kế và chất liệu vải đẹp mắt.',0),(91,'Quần Short Chino',26,NULL,3,5,391000,100,'short03.jpg',1,'Quần với thiết kế rộng rãi, gọn gàng và thoải mái.',0),(92,'Quần Short Chino 2',26,NULL,3,8,391000,100,'short04.jpg',1,'Thiết kế được cập nhật thoải mái hơn. Mang lại cảm giác giản dị trong khi vẫn hợp thời trang.',0),(93,'Quần Short Siêu Nhẹ',26,NULL,3,5,784000,100,'short05.jpg',1,'Dáng ngắn theo kiểu quần Kando tiện dụng của chúng tôi. Vải chất lượng cao tuyệt vời cho hoạt động chơi gôn và các môn thể thao khác.',0),(94,'Quần Short Siêu Nhẹ',26,NULL,3,8,784000,100,'short06.jpg',1,'Quần short nhẹ, co giãn và khô ráo hiệu suất đáng ngạc nhiên. Từ kinh doanh đến bình thường đến thể thao.',0),(95,'Quần Short Thể Thao',26,NULL,3,6,293000,100,'short07.jpg',1,'Những chiếc quần short đa năng này có kiểu dáng như quần bơi hoặc quần short mặc hằng ngày.',0),(96,'Quần Short Bơi Thể Thao',26,NULL,3,3,489000,100,'short08.jpg',1,'Những chiếc quần short đa năng này có kiểu dáng như quần bơi hoặc quần short mặc hằng ngày.',0),(97,'Quần Short Vải Nylon',26,NULL,3,6,489000,100,'short09.jpg',1,'Quần short bền và đa năng. Hoàn hảo cho các hoạt động ngoài trời.',0),(98,'Quần Short Vải Nylon',26,NULL,3,8,293000,100,'short10.jpg',1,'Hiệu suất và khả năng bảo quản cao. Quần hiệu suất cao rất thiết thực và hoàn hảo cho trang phục activewear.',0),(99,'Quần Short Vải Linen Pha',26,NULL,3,7,489000,100,'short11.jpg',1,'Quần short vải linen pha thanh lịch, mát mẻ. Thích hợp cho mọi phong cách.',0),(100,'Quần Short Dry-EX',26,NULL,3,7,489000,100,'short12.jpg',1,'Quần short được thiết kế biểu diễn sự thoải mái mát mẻ. Một go-to đa năng.',0),(101,'Quần Short Thể Thao Siêu Co Giãn',26,NULL,3,3,489000,100,'short13.jpg',1,'Co giãn đáng kinh ngạc và một cảm giác luôn tươi mới. Tự do đi lại trong các môn thể thao và trang phục bình thường.',0),(102,'Quần Short Thể Thao Siêu Co Giãn',26,NULL,3,6,391000,100,'short14.jpg',1,'Vải siêu co giãn, hoàn hảo cho các hoạt động thể thao hoặc thoải mái hàng ngày.',0),(103,'NK Quần Short Dry',26,NULL,3,6,980000,100,'short15.jpg',1,'Bản sao của trang phục thi đấu của Roger Federer. Quần short kết hợp phong cách và hiệu suất.',0),(104,'Quần Dry Easy Short Co Giãn',26,NULL,3,8,391000,100,'short16.jpg',1,'Nhanh khô và dễ dàng di chuyển. Quần short siêu thoải mái của chúng tôi.',0),(105,'Quần Easy Short Vải Jersey',26,NULL,3,8,391000,100,'short17.jpg',1,'Quần short với chất liệu vải đặc biệt. Vải nhẹ tạo sự thoải mái và dễ dàng vận động.',0),(106,'Quần Easy Short Co Giãn',26,NULL,3,8,391000,100,'short18.jpg',1,'Co giãn để dễ dàng di chuyển. Quần short mang đến cảm giác tươi mới, bạn có thể mặc ở bất cứ đâu.',0),(107,'Quần Easy Short Co Giãn',26,NULL,3,5,391000,100,'short19.jpg',1,'Co giãn để dễ dàng di chuyển. Quần short mát mẻ để thư giãn ở nhà hoặc mặc đi chơi.',0),(108,'Quần Easy Short Vải Jersey',26,NULL,3,4,391000,100,'short20.jpg',1,'Quần short được xử lí giặt để có kết cấu thô.',0),(109,'Quần Short Vải Jersey',26,NULL,3,7,391000,100,'short21.jpg',1,'Những chiếc quần short mát mẻ này chính là sự lựa chọn hoàn hảo để mặc trong nhà dài hoặc mặc hàng ngày.',0),(110,'Quần Easy Short',26,NULL,3,3,391000,100,'short22.jpg',1,'Quần short thường ngày thoải mái có túi quần.',0),(111,'Quần Easy Short',26,NULL,3,7,391000,100,'short23.jpg',1,'Quần dáng rộng kiểu dáng đẹp và thoáng mát với kết cấu vải tự nhiên tương tự như vải cotton.',0),(112,'Quần Easy Short Dry Co Giãn',26,NULL,3,4,391000,100,'short24.jpg',1,'Quần short với hiệu suất giúp làm khô nhanh và cho phép di chuyển dễ dàng. Thiết kế thắt lưng thoải mái.',0),(113,'IPhone X Case',71,10,3,4,200000,100,NULL,1,'Ốp điện thoại cho Iphone, chống nước, va đập',0),(114,'HairCare kjaskd',32,21,2,5,1001231,23,'access5.jpg',5,'dsx',0);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviews`
--

DROP TABLE IF EXISTS `reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reviews` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idetail_id` int DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `rate` float DEFAULT NULL,
  `review_date` date DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `review_prod` (`product_id`),
  KEY `review_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviews`
--

LOCK TABLES `reviews` WRITE;
/*!40000 ALTER TABLE `reviews` DISABLE KEYS */;
/*!40000 ALTER TABLE `reviews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shopping_session`
--

DROP TABLE IF EXISTS `shopping_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shopping_session` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `total` int DEFAULT NULL,
  `extra_time` date DEFAULT NULL,
  `repair_time` date DEFAULT NULL,
  `cur_session` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`),
  UNIQUE KEY `user_id_2` (`user_id`),
  CONSTRAINT `shop_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shopping_session`
--

LOCK TABLES `shopping_session` WRITE;
/*!40000 ALTER TABLE `shopping_session` DISABLE KEYS */;
INSERT INTO `shopping_session` VALUES (1,6,19,'2022-10-11','2022-10-11','5A2F3C6A274E00579D94C79C29E17144'),(2,5,1,'2022-10-11','2022-10-11',NULL),(3,4,1,'2022-10-11','2022-10-11','abdce'),(4,3,1,'2022-10-11','2022-10-11',NULL),(5,2,1,'2022-10-11','2022-10-11',NULL),(6,1,1,'2022-10-11','2022-10-11',NULL),(7,7,111,'2022-10-11','2022-10-11','54BC6F9A4616CCC0507A10734DE9A488'),(8,8,2,'2022-10-11','2022-10-11','2FFCDB153F0DE1C7071D8626386D05AE'),(9,35,1,'2022-10-17','2022-10-17',NULL);
/*!40000 ALTER TABLE `shopping_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sizes`
--

DROP TABLE IF EXISTS `sizes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sizes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `size_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sizes`
--

LOCK TABLES `sizes` WRITE;
/*!40000 ALTER TABLE `sizes` DISABLE KEYS */;
INSERT INTO `sizes` VALUES (1,'S'),(2,'M'),(3,'L'),(4,'XL'),(5,'XXL');
/*!40000 ALTER TABLE `sizes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subcategory`
--

DROP TABLE IF EXISTS `subcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subcategory` (
  `id` int NOT NULL AUTO_INCREMENT,
  `category_id` int DEFAULT NULL,
  `positions` varchar(50) DEFAULT NULL,
  `subcategory_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `category_sub` (`category_id`),
  CONSTRAINT `category_sub` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subcategory`
--

LOCK TABLES `subcategory` WRITE;
/*!40000 ALTER TABLE `subcategory` DISABLE KEYS */;
INSERT INTO `subcategory` VALUES (1,1,'Tops','T-Shirts'),(2,1,'Tops','Hoodies'),(3,1,'Tops','Sults'),(4,1,'Tops','Black Bean T-Shirt'),(5,1,'Tops','Shirt'),(6,1,'Outwear','Jackets'),(7,1,'Outwear','Trench'),(8,1,'Outwear','Parkas'),(9,1,'Outwear','Sweaters'),(10,1,'Outwear','Cardigan'),(11,1,'Accessories','Watches'),(12,1,'Accessories','Ties'),(13,1,'Accessories','Scarves'),(14,1,'Accessories','Belts'),(15,1,'Sunglasses','Pilot'),(16,1,'Sunglasses','Wayfarer'),(17,1,'Sunglasses','Square'),(18,1,'Sunglasses','Round'),(19,1,'Underwear','Boxers'),(20,1,'Underwear','Briefs'),(21,1,'Underwear','Robes'),(22,1,'Underwear','Socks'),(23,1,'Bottoms','Underwear'),(24,1,'Bottoms','Shoes'),(25,1,'Bottoms','Jeans'),(26,1,'Bottoms','Shorts'),(27,2,'Tops','Dresses'),(28,2,'Tops','Blouses & Shirts'),(29,2,'Tops','T-shirts'),(30,2,'Tops','Sweater'),(31,2,'Tops','Shirts'),(32,2,'Bottoms','Skirts'),(33,2,'Bottoms','Shoes'),(34,2,'Bottoms','Leggings'),(35,2,'Bottoms','Jeans'),(36,2,'Bottoms','Trousers'),(37,2,'Intimates','Bras'),(38,2,'Intimates','Brief Sets'),(39,2,'Intimates','Bustiers & Corsets'),(40,2,'Intimates','Panties'),(41,2,'Intimates','Pajama'),(42,2,'Outwear & Jackets','Blazers'),(43,2,'Outwear & Jackets','Basics Jackets'),(44,2,'Outwear & Jackets','Trench'),(45,2,'Outwear & Jackets','Cardigan'),(46,2,'Outwear & Jackets','Leather & Suede'),(47,2,'Wedding & Events','Wedding Dresses'),(48,2,'Wedding & Events','Evening Dresses'),(49,2,'Wedding & Events','Prom Dresses'),(50,2,'Wedding & Events','Flower Dresses'),(51,2,'Accessories','Sunglasses'),(52,2,'Accessories','Headwear'),(53,2,'Accessories','Baseball Caps'),(54,2,'Accessories','Belts'),(55,2,'Accessories','Bags'),(56,3,'Educational','Board Games'),(57,3,'Educational','Memory Games'),(58,3,'Educational','Matching'),(59,3,'Educational','Jigsaws'),(60,3,'Dolls','Barbie'),(61,3,'Dolls','Bisque'),(62,3,'Dolls','Voodoo'),(63,3,'Plush','Stuffed Animals'),(64,3,'Plush','Pillows'),(65,3,'Plush','Teddy Bears'),(66,3,'Drone','Belts'),(67,3,'Robot','Quadcopters'),(68,3,'Robot','Vehicles'),(69,4,'Mobile','CellPhone'),(70,4,'Mobile','Cases'),(71,4,'Mobile','Accessories'),(72,4,'Tablet','Computer Tablets'),(73,5,'Audio','Headphones'),(74,5,'Audio','Speakers'),(75,5,'Camera','Lenses'),(76,5,'Camera','Digital Camera'),(77,6,'Novel','Romance'),(78,6,'Novel','Comedy'),(79,6,'Novel','Fantasy'),(80,6,'Comic','Romance'),(81,6,'Comic','Mystery'),(82,6,'Comic','Fantasy'),(83,6,'Travel','Food'),(84,6,'Travel','Reference'),(85,7,'Makeup','Eyeliners'),(86,7,'Makeup','Brush'),(87,7,'Makeup','Foundation'),(88,7,'Oral Care','Denture Care'),(89,7,'Oral Care','Mouthwash'),(90,7,'Hair Care','Wigs'),(91,7,'Hair Care','Styling'),(92,7,'Hair Care','Oils'),(93,7,'Fragrance','Men'),(94,7,'Fragrance','Women'),(95,8,'Home','Bedroom'),(96,8,'Home','Kitchen'),(97,8,'Home','Livingroom'),(98,8,'Home','Entry'),(99,8,'Office','Chair'),(100,8,'Office','Desk');
/*!40000 ALTER TABLE `subcategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suppliers`
--

DROP TABLE IF EXISTS `suppliers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `suppliers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `supplier_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `address` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `phone_number` varchar(10) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `note` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `cooperated` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone_number` (`phone_number`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suppliers`
--

LOCK TABLES `suppliers` WRITE;
/*!40000 ALTER TABLE `suppliers` DISABLE KEYS */;
INSERT INTO `suppliers` VALUES (1,'Xưởng Sỉ Quần Áo ANN','68 Đường C12, Phường 13, Quận Tân Bình, TP. HCM','0913268406','ann2707@gmail.com.vn',NULL,1),(2,'Phụ Kiện AHA24H.VN','129/1 Nguyễn Trãi, Phường 2, Quận 5, TP. HCM','0965482185','aha24h@gmail.com',NULL,1),(3,'Xưởng Giày Tamy','C7D/27, Phạm Hùng, Quận 8, Tp. Hồ Chí Minh','0946712804','tamyshoes@gmail.com',NULL,1),(5,'gag999','','090423423','gagcom@mail.com','',0);
/*!40000 ALTER TABLE `suppliers` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `after_supplier_insert` BEFORE INSERT ON `suppliers` FOR EACH ROW BEGIN
   SET NEW.cooperated=1;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  `reset_password_token` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (4,'test','123',1,'ROLE_ADMIN',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fullname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `role` varchar(50) DEFAULT NULL,
  `passwords` varchar(20) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `gender` tinyint(1) DEFAULT NULL,
  `phone_number` varchar(10) DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `actived` tinyint(1) DEFAULT NULL,
  `reset_password_token` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `phone_number` (`phone_number`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Jack Chris','hgiraldo0@bluehost.com','ROLE_ADMIN','123','1995-04-11',0,'0141533129','0920 Marcy Parkway, HN',1,NULL),(2,'Rosy Lutas','rlutas1@last.fm','ROLE_USER','123','1997-12-04',0,'0701748279','78564 Steensland Place, CT',1,NULL),(3,'Welby Newcombe','wnewcombe2@seattletimes.com','1','oUwrFJsRJ8BE','1988-08-01',1,'0969721311','9 Prentice Place, DN',1,NULL),(4,'Barney Crinidge','bcrinidge3@infoseek.co.jp','0','6AM6qb51','1986-09-11',0,'0446194006','55456 Namekagon Place, HCM',1,NULL),(5,'Bernadina Pittwood','bpittwood4@ucoz.com','0','YXnIG05LHh','1998-09-04',0,'0713932721','5 Bunker Hill Park, CT',1,NULL),(6,'van','van@gmail.com','ROLE_ADMIN','123','1998-01-01',1,'0713932781','',1,NULL),(7,'ha','ha@gmail.com','ROLE_USER','123','1998-01-01',1,'0713332781','',1,NULL),(8,'yue','vanthtps19229@fpt.edu.vn','ROLE_USER','123','1995-04-12',1,'0143333129','0920 Marcy Parkway, HN',1,NULL),(35,'Van','rmail@mail.com','ROLE_ADMIN','123','2022-10-01',NULL,'','132',0,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `after_user_insert` AFTER INSERT ON `users` FOR EACH ROW BEGIN
   INSERT INTO SHOPPING_SESSION(USER_ID, TOTAL, EXTRA_TIME, REPAIR_TIME, CUR_SESSION)
VALUEs (NEW.id, 1, CURDATE(), CURDATE(), null);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `voucher`
--

DROP TABLE IF EXISTS `voucher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `voucher` (
  `id` int NOT NULL AUTO_INCREMENT,
  `start_day` date DEFAULT NULL,
  `end_day` date DEFAULT NULL,
  `reduce` int DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voucher`
--

LOCK TABLES `voucher` WRITE;
/*!40000 ALTER TABLE `voucher` DISABLE KEYS */;
/*!40000 ALTER TABLE `voucher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `voucher_user`
--

DROP TABLE IF EXISTS `voucher_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `voucher_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `voucher_id` int DEFAULT NULL,
  `used` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `vu_user` (`user_id`),
  KEY `vu_voucher` (`voucher_id`),
  CONSTRAINT `vu_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `vu_voucher` FOREIGN KEY (`voucher_id`) REFERENCES `voucher` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voucher_user`
--

LOCK TABLES `voucher_user` WRITE;
/*!40000 ALTER TABLE `voucher_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `voucher_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `WISHLIST`
--

DROP TABLE IF EXISTS `WISHLIST`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `WISHLIST` (
  `USER_ID` int NOT NULL,
  `PRO_ID` int NOT NULL,
  PRIMARY KEY (`USER_ID`,`PRO_ID`),
  KEY `PRO_ID` (`PRO_ID`),
  CONSTRAINT `wishlist_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `USERS` (`id`),
  CONSTRAINT `wishlist_ibfk_2` FOREIGN KEY (`PRO_ID`) REFERENCES `PRODUCTS` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `WISHLIST`
--

LOCK TABLES `WISHLIST` WRITE;
/*!40000 ALTER TABLE `WISHLIST` DISABLE KEYS */;
/*!40000 ALTER TABLE `WISHLIST` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'heroku_844f026e121e684'
--

--
-- Dumping routines for database 'heroku_844f026e121e684'
--
/*!50003 DROP FUNCTION IF EXISTS `countCartTotal` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `countCartTotal`( userId INT ) RETURNS int
    READS SQL DATA
BEGIN

   DECLARE num INT;
   SELECT count(cd.id) into num FROM heroku_844f026e121e684.cart_detail CD JOIN shopping_session SS
	ON CD.session_id = SS.ID JOIN users U ON U.ID = SS.USER_ID	WHERE U.ID = userId;
   
   RETURN num;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `cartItemToInvoice` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `cartItemToInvoice`(
IN userId INT, IN invoicId int)
BEGIN    
DECLARE length INT DEFAULT 0;
DECLARE counter INT DEFAULT 0;
DECLARE ssid int default -1;
DECLARE qty  INT DEFAULT -1;
declare pid int DEFAULT -1;

SELECT countCartTotal(userId) INTO length;
select id from shopping_session where user_id = userId into ssid;
SET counter=0;
 WHILE counter<length DO
	select  quantity into qty from (SELECT *, ROW_NUMBER() OVER(PARTITION BY session_id) AS row_num  
    FROM cart_detail where session_id = ssid) as temp where row_num = counter + 1 ;
	
    select product_id into pid from (SELECT *, ROW_NUMBER() OVER(PARTITION BY session_id) AS row_num  
    FROM cart_detail where session_id = ssid) as temp where row_num = counter + 1 ;
    
    INSERT INTO INVOICE_DETAIL(INVOICE_ID, PRODUCT_ID, QUANTITY)
    VALUES(invoicId, pid, qty);
    
    set counter = counter + 1;
	END WHILE;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `removeCartItemAfterCheckOut` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `removeCartItemAfterCheckOut`(
IN userId INT)
BEGIN
	DECLARE ssid int default -1;
	select id from shopping_session where user_id = userId into ssid;
    DELETE FROM cart_detail WHERE session_id = ssid;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-17 20:33:46
