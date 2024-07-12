CREATE DATABASE  IF NOT EXISTS `employee_directory`;
USE `employee_directory`;

DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `users`;

-- password should have a length of 68 because of BCRYPT will generate a string of 60 characters and the {bcrypt} is 8 characters long
CREATE TABLE `users` (
	`username` varchar(50) NOT NULL,
    `password` varchar(68) NOT NULL,
    `enabled` tinyint NOT NULL,
    
    PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Data for table `users`
--

INSERT INTO `users` VALUES
	('john', '{bcrypt}$2a$10$T5x7dLCMYggsBMH24RvF0ef5lRDkPld882ohjlUrK1ycQphwtHtkK', 1),
	('marry', '{bcrypt}$2a$10$xosrOvreL41Cbol6.dmILOfttlAW8ipCi1pgTswNesTVicJmWUEx.', 1),
	('susan', '{bcrypt}$2a$10$jI4jcC6wl/G49lwUtn2Yq.J7JTXMaPol24XKs28HRdWY3cN3lRy1G', 1);
    

CREATE TABLE `authorities` (
	`username` VARCHAR(50) NOT NULL,
    `authority` VARCHAR(50) NOT NULL,
    
    UNIQUE KEY `authorities_idx_1` (`username`, `authority`),
    CONSTRAINT `authorities_ibfk_1`
    FOREIGN KEY (`username`)
    REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Data for table `authorities`
--

INSERT INTO `authorities` VALUES
	('john', 'ROLE_EMPLOYEE'),
	('marry', 'ROLE_EMPLOYEE'),
	('marry', 'ROLE_MANAGER'),
	('susan', 'ROLE_EMPLOYEE'),
	('susan', 'ROLE_MANAGER'),
	('susan', 'ROLE_ADMIN');