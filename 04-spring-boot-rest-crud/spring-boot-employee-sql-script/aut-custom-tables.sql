CREATE DATABASE  IF NOT EXISTS `employee_directory`;
USE `employee_directory`;

DROP TABLE IF EXISTS `roles`;
DROP TABLE IF EXISTS `members`;

-- password should have a length of 68 because of BCRYPT will generate a string of 60 characters and the {bcrypt} is 8 characters long
CREATE TABLE `members` (
	`user_id` varchar(50) NOT NULL,
    `pw` varchar(68) NOT NULL,
    `active` tinyint NOT NULL,
    
    PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Data for table `users`
--

INSERT INTO `members` VALUES
	('john', '{bcrypt}$2a$10$T5x7dLCMYggsBMH24RvF0ef5lRDkPld882ohjlUrK1ycQphwtHtkK', 1),
	('marry', '{bcrypt}$2a$10$xosrOvreL41Cbol6.dmILOfttlAW8ipCi1pgTswNesTVicJmWUEx.', 1),
	('susan', '{bcrypt}$2a$10$jI4jcC6wl/G49lwUtn2Yq.J7JTXMaPol24XKs28HRdWY3cN3lRy1G', 1);
    

CREATE TABLE `roles` (
	`user_id` VARCHAR(50) NOT NULL,
    `role` VARCHAR(50) NOT NULL,
    
    UNIQUE KEY `roles_idx_1` (`user_id`, `role`),
    CONSTRAINT `roles_ibfk_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `members` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Data for table `authorities`
--

INSERT INTO `roles` VALUES
	('john', 'ROLE_EMPLOYEE'),
	('marry', 'ROLE_EMPLOYEE'),
	('marry', 'ROLE_MANAGER'),
	('susan', 'ROLE_EMPLOYEE'),
	('susan', 'ROLE_MANAGER'),
	('susan', 'ROLE_ADMIN');