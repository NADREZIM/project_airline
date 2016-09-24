CREATE DATABASE IF NOT EXISTS `airline` DEFAULT CHARACTER SET = UTF8;

USE `airline`;

CREATE TABLE IF NOT EXISTS `crew_team` (
  `crew_team_id` int(11) DEFAULT NULL,
  `employee_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `crew_team_number` (
  `number` int(11) NOT NULL AUTO_INCREMENT,
  `ordinal_number` int(11) DEFAULT NULL,
  PRIMARY KEY (`number`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `employee` (
  `employee_id` int(11) NOT NULL AUTO_INCREMENT,
  `specialty` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `ordinal_number` int(11) DEFAULT NULL,
  `status` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`employee_id`),
  UNIQUE KEY `ordinal_number_UNIQUE` (`ordinal_number`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `flights` (
  `flight_number` int(11) NOT NULL AUTO_INCREMENT,
  `flight_name` varchar(45) DEFAULT NULL,
  `departure` varchar(45) DEFAULT NULL,
  `destination` varchar(45) DEFAULT NULL,
  `departure_date` date DEFAULT NULL,
  `crew` int(11) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`flight_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `request` (
  `request_id` int(11) NOT NULL AUTO_INCREMENT,
  `flight_id` int(11) DEFAULT NULL,
  `body` varchar(45) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `status` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`request_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

insert into employee(specialty,name,ordinal_number,status)values('pilot','Semen Semenov',100,1);
insert into employee(specialty,name,ordinal_number,status)values('pilot','Ivan Ivnov',200,1);
insert into employee(specialty,name,ordinal_number,status)values('stewardess','Alla Semenova',300,1);
insert into employee(specialty,name,ordinal_number,status)values('stewardess','Elena Ivanova',400,1);
insert into employee(specialty,name,ordinal_number,status)values('navigator','Kiril Ririlov',500,1);
insert into employee(specialty,name,ordinal_number,status)values('radioman','Denis Denisov',600,1);
insert into employee(specialty,name,ordinal_number,status)values('pilot','Vasilev Vasiliy',700,1);
insert into employee(specialty,name,ordinal_number,status)values('pilot','Egorov Egor',800,1);
insert into employee(specialty,name,ordinal_number,status)values('stewardess','Kira Kirova',900,1);
insert into employee(specialty,name,ordinal_number,status)values('stewardess','Sveta Svetlova',1000,1);
insert into employee(specialty,name,ordinal_number,status)values('navigator','Rick Rikov',1100,1);
insert into employee(specialty,name,ordinal_number,status)values('radioman','Roman Romanov',1200,1);

insert into users(login,password,role)values('admin','admin','admin');
insert into users(login,password,role)values('disp','disp','disp');