CREATE SCHEMA `elearning` DEFAULT CHARACTER SET utf8;
USE elearning;

CREATE TABLE IF NOT EXISTS roles (
   id INT(11) NOT NULL AUTO_INCREMENT,
   name VARCHAR(30) NOT NULL UNIQUE,
   description VARCHAR(255),
   CONSTRAINT pk_role PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS users (
    id INT(11) NOT NULL AUTO_INCREMENT,
    email VARCHAR(100) NOT NULL UNIQUE,
    fullname VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    avatar VARCHAR(255),
    phone VARCHAR(20),
    address VARCHAR(255),
    role_id INT(11) NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY(id),
    CONSTRAINT fk_user_role FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS categories (
    id INT(11) NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL UNIQUE,
    icon VARCHAR(20) NOT NULL,
    CONSTRAINT pk_category PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS courses (
    id INT(11) NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    image VARCHAR(255) NOT NULL,
    letures_count INT(11) NOT NULL,
    hour_count INT(11) NOT NULL,
    view_count INT(11) DEFAULT '0',
    price DECIMAL DEFAULT '0',
    discount INT(11) DEFAULT '0',
    promotion_price DECIMAL DEFAULT '0',
    description VARCHAR (255) NOT NULL,
    content TEXT,
    category_id INT(11) NOT NULL,
    last_update TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_course PRIMARY KEY(id),
    CONSTRAINT fk_course_category FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS targets(
    id INT(11) NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    order_index INT(11) NOT NULL,
    course_id INT(11) NOT NULL,
    CONSTRAINT pk_target PRIMARY KEY(id),
    CONSTRAINT fk_target_course FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS videos (
    id INT(11) NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    url VARCHAR(255) NOT NULL,
	image VARCHAR(255) NOT NULL,
    time_count INT(11) NOT NULL,
    course_id INT(11) NOT NULL,
    CONSTRAINT pk_video PRIMARY KEY(id),
    CONSTRAINT fk_video_course FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS user_course(
    user_id INT(11) NOT NULL,
    course_id INT(11) NOT NULL,
    CONSTRAINT pk_account_course PRIMARY KEY (user_id, course_id),
    CONSTRAINT fk_user_course_user FOREIGN KEY (user_id) REFERENCES users(id)  ON DELETE CASCADE,
    CONSTRAINT fk_user_course_course FOREIGN KEY (course_id) REFERENCES courses(id)  ON DELETE CASCADE
);

INSERT INTO `elearning`.`roles` (`name`, `description`) VALUES ('ROLE_ADMIN', 'admin');

/* admin account:  email: admin@gmail.com, password: 0 */

INSERT INTO `elearning`.`users` (`email`, `fullname`, `password`, `avatar`,`phone` ,`address`, `role_id`) VALUES ('admin@gmail.com', 'admin', '$2a$10$QneDpUjT6NIPlon4kGCqYeeJ3p2OfxauF14rV19S2kx5jqIdXhY0.', 'avatar-1.jpg','090909' ,'Tp. Hồ Chí Minh', '1');
