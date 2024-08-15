CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `last_name` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `email` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `phone` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `username` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `active` bit(1) DEFAULT b'1',
  `user_role` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `avatar` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_email` (`email`),
  UNIQUE KEY `unique_phone` (`phone`),
  UNIQUE KEY `unique_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

INSERT INTO `category` (`name`, `description`) VALUES 
('Kiến thức cơ sở', 'Các kiến thức nền tảng cần thiết'),
('Lập trình cơ sở', 'Những khái niệm và kỹ năng lập trình cơ bản'),
('Lập trình nâng cao', 'Những kỹ năng và kiến thức lập trình chuyên sâu'),
('Giải quyết vấn đề', 'Kỹ năng phân tích và giải quyết các vấn đề phức tạp'),
('Kỹ năng nâng cao', 'Các kỹ năng nâng cao để phát triển bản thân và sự nghiệp');


CREATE TABLE `instructor` (
  `id` int NOT NULL AUTO_INCREMENT,
  `expertise` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `description` text CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_id` int NOT NULL UNIQUE,  -- Cột khóa ngoại đến bảng user và đảm bảo mối quan hệ 1:1
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_id`) REFERENCES `user`(`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;




CREATE TABLE onlinecourse.`course` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `description` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci,
  `time_experted` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci, -- hoặc định dạng thời gian khác nếu cần
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `price` bigint NOT NULL,
  `category_id` int NOT NULL,
  `instructor_id` int NOT NULL,  -- Thêm cột khóa ngoại instructor_id
  `status` ENUM('ACTIVE', 'INACTIVE') NOT NULL,
  `course_type` ENUM('ONLINE', 'ON_OFF') NOT NULL,
  `img` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci, -- Thêm cột img
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_title` (`title`),
  FOREIGN KEY (`category_id`) REFERENCES `category`(`id`),
  FOREIGN KEY (`instructor_id`) REFERENCES `instructor`(`id`)  -- Khóa ngoại instructor_id tham chiếu đến bảng instructor
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;


INSERT INTO `instructor` (`expertise`, `description`, `created_date`, `updated_date`, `user_id`)
VALUES
('Data Analysis', 'Chuyên gia phân tích dữ liệu với khả năng sử dụng các công cụ phân tích mạnh mẽ.', NOW(), NOW(), 4),
('Cloud Services', 'Chuyên gia về các dịch vụ đám mây, có kinh nghiệm triển khai ứng dụng trên AWS và Azure.', NOW(), NOW(), 5);



INSERT INTO `course` (`title`, `description`, `time_experted`, `created_date`, `updated_date`, `price`, `category_id`, `instructor_id`, `status`, `course_type`)
VALUES
('Nhập môn Data Analysis', 'Khóa học cơ bản về phân tích dữ liệu sử dụng các công cụ phổ biến.', '30 giờ', NOW(), NOW(), 1200000, 1, 1, 'ACTIVE', 'ONLINE'),
('Triển khai Dịch vụ Đám mây', 'Hướng dẫn cách triển khai các dịch vụ đám mây với AWS.', '40 giờ', NOW(), NOW(), 1500000, 2, 2, 'ACTIVE', 'ON_OFF'),
('Xử lý Dữ liệu lớn', 'Kỹ thuật xử lý và phân tích dữ liệu lớn cho các dự án dữ liệu phức tạp.', '50 giờ', NOW(), NOW(), 1800000, 3, 1, 'INACTIVE', 'ONLINE'),
('Kỹ năng SQL nâng cao', 'Khóa học chuyên sâu về SQL cho quản lý và phân tích cơ sở dữ liệu.', '35 giờ', NOW(), NOW(), 1300000, 4, 2, 'ACTIVE', 'ONLINE'),
('Quản lý Dự án IT', 'Học cách quản lý các dự án IT từ khâu lập kế hoạch đến thực hiện.', '45 giờ', NOW(), NOW(), 1600000, 5, 1, 'ACTIVE', 'ON_OFF');


-- DELETE FROM `instructor` WHERE user_id=4;
-- Delete from `user` where id=4;


CREATE TABLE `enrollment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `course_id` int NOT NULL,
  `invoice_id` int DEFAULT NULL, -- Liên kết đến bảng hóa đơn
  `status` ENUM('IN_PROGRESS', 'COMPLETED', 'CANCELLED') DEFAULT 'IN_PROGRESS',
  `progress` int DEFAULT 0,
  `grade` VARCHAR(10) DEFAULT NULL,
  `certificate_url` VARCHAR(255) DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_id`) REFERENCES `user`(`id`) ON DELETE CASCADE,
  FOREIGN KEY (`course_id`) REFERENCES `course`(`id`) ON DELETE CASCADE,
  FOREIGN KEY (`invoice_id`) REFERENCES `invoice`(`id`) ON DELETE SET NULL, -- Khóa ngoại liên kết với bảng invoice
  UNIQUE KEY `unique_user_course` (`user_id`, `course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;


CREATE TABLE `invoice` (
  `id` int NOT NULL AUTO_INCREMENT,
  `invoice_number` varchar(100) NOT NULL UNIQUE, -- Số hóa đơn, đảm bảo tính duy nhất
  `payer_name` varchar(255) NOT NULL, -- Tên người thanh toán
  `payer_phone` varchar(11) NOT NULL, -- Số điện thoại người thanh toán
  `payer_email` varchar(255) NOT NULL, -- Email người thanh toán
  `total_amount` bigint NOT NULL, -- Tổng số tiền của hóa đơn
  `status` bit(1) DEFAULT b'0', -- Trạng thái của hóa đơn: 0 - Chưa thanh toán, 1 - Đã thanh toán
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

