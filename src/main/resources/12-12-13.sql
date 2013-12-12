/*
SQLyog Ultimate - MySQL GUI v8.22 
MySQL - 5.0.41-community-nt : Database - generalwebservice
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `booking` */

DROP TABLE IF EXISTS `booking`;

CREATE TABLE `booking` (
  `bookingID` int(11) NOT NULL auto_increment,
  `bookingDate` datetime default NULL,
  `bookingTime` varchar(100) default NULL,
  `employeeID` int(11) default NULL,
  `productID` int(11) default NULL,
  `emailID` varchar(255) default NULL,
  PRIMARY KEY  (`bookingID`),
  KEY `FK6713A039D5115D3E` (`employeeID`),
  KEY `FK6713A03916364C12` (`productID`),
  KEY `FK6713A039244B7F49` (`emailID`),
  CONSTRAINT `FK6713A039244B7F49` FOREIGN KEY (`emailID`) REFERENCES `user` (`emailID`),
  CONSTRAINT `FK6713A03916364C12` FOREIGN KEY (`productID`) REFERENCES `product` (`productID`),
  CONSTRAINT `FK6713A039D5115D3E` FOREIGN KEY (`employeeID`) REFERENCES `employee` (`employeeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `booking` */

insert  into `booking`(`bookingID`,`bookingDate`,`bookingTime`,`employeeID`,`productID`,`emailID`) values (1,NULL,'9am',2,6,'proshad@gmail.com'),(2,NULL,'9am',3,4,'proshad@gmail.com');

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `categoryID` int(11) NOT NULL auto_increment,
  `categoryDescription` varchar(1000) default NULL,
  `categoryName` varchar(255) default NULL,
  `categoryNote` varchar(255) default NULL,
  `parentCatID` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`categoryID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `category` */

insert  into `category`(`categoryID`,`categoryDescription`,`categoryName`,`categoryNote`,`parentCatID`,`status`) values (1,'description 1 description 1 description 1 description 1 description 1 description 1 description 1 description 1 description 1 description 1','cat1','note of category one ..... note of category one....note of category one',0,0),(2,'description 1 description 1 description 1 description 1 description 1 description 1 description 1 description 2 description 2 description 2.....description 1 description 1 description 1 description 1 description 1 description 1 description 1 description 2 description 2 description 2.....description 1 description 1 description 1 description 1 description 1 description 1 description 1 description 2 description 2 description 2.....description 1 description 1 description 1 description 1 description 1 description 1 description 1 description 2 description 2 description 2.....description 1 description 1 description 1 description 1 description 1 description 1 description 1 description 2 description 2 description 2.....','cat2','note of category ..... note of category....',0,0),(3,'The Supreme Court today started the hearing on the review petition filed by condemned Jamaat-e-Islami leader Abdul Quader Mollah against its verdict awarding him death penalty for his wartime. After holding hearing on the acceptability of the review petition in the morning, a five-member bench of the Appellate Division head by Chief Justice Md Muzammel Hossain decided to hear the main petition. As the court','cat3','The Supreme Court today started',0,0),(4,'The Supreme Court today started the hearing on the review petition filed by condemned Jamaat-e-Islami leader Abdul Quader Mollah against its verdict awarding him death penalty for his wartime. After holding hearing on the acceptability of the review petition in the morning, a five-member bench of the Appellate Division head by Chief Justice Md Muzammel Hossain decided to hear the main petition. As the court','cat4','The Supreme Court today started',0,1),(5,'The Supreme Court today started the hearing on the review petition filed by condemned Jamaat-e-Islami leader Abdul Quader Mollah against its verdict awarding him death penalty for his wartime. After holding hearing on the acceptability of the review petition in the morning, a five-member bench of the Appellate Division head by Chief Justice Md Muzammel Hossain decided to hear the main petition. As the court','cat5','The Supreme Court today started',2,1),(6,'The Supreme Court today started the hearing on the review petition filed by condemned Jamaat-e-Islami leader Abdul Quader Mollah against its verdict awarding him death penalty for his wartime. After holding hearing on the acceptability of the review petition in the morning, a five-member bench of the Appellate Division head by Chief Justice Md Muzammel Hossain decided to hear the main petition. As the court','cat6','The Supreme Court today started',1,1);

/*Table structure for table `department` */

DROP TABLE IF EXISTS `department`;

CREATE TABLE `department` (
  `departmentID` int(11) NOT NULL auto_increment,
  `departmentName` varchar(500) default NULL,
  PRIMARY KEY  (`departmentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `department` */

/*Table structure for table `employee` */

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `employeeID` int(11) NOT NULL auto_increment,
  `address` varchar(1000) default NULL,
  `contactNo` varchar(20) default NULL,
  `designation` varchar(300) default NULL,
  `firstName` varchar(500) default NULL,
  `imageUrl` varchar(300) default NULL,
  `joiningDate` datetime default NULL,
  `lastName` varchar(500) default NULL,
  PRIMARY KEY  (`employeeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `employee` */

insert  into `employee`(`employeeID`,`address`,`contactNo`,`designation`,`firstName`,`imageUrl`,`joiningDate`,`lastName`) values (1,'dhanmondi, dhaka, bangladesh','01911287205','sales ','sanjoy','http://www.goodlightscraps.com/content/funny-baby/funny-baby-6.jpg',NULL,'biswas'),(2,'dhanmondi, dhaka, bangladesh','01911287205','sales ','mithu','http://www.goodlightscraps.com/content/funny-baby/funny-baby-5.jpg',NULL,'biswas'),(3,'dhanmondi, dhaka, bangladesh','01911287205','sales ','rajib','http://www.goodlightscraps.com/content/funny-baby/funny-baby-4.jpg',NULL,'biswas'),(4,'dhanmondi, dhaka, bangladesh','01911287205','sales ','proshad','http://www.goodlightscraps.com/content/funny-baby/funny-baby-3.jpg',NULL,'biswas'),(5,'dhanmondi, dhaka, bangladesh','01911287205','sales ','chandan','http://www.goodlightscraps.com/content/funny-baby/funny-baby-2.jpg',NULL,'biswas'),(6,'dhanmondi, dhaka, bangladesh','01911287205','sales ','mrinmoy','http://www.goodlightscraps.com/content/funny-baby/funny-baby-1.jpg',NULL,'biswas');

/*Table structure for table `employeeproduct` */

DROP TABLE IF EXISTS `employeeproduct`;

CREATE TABLE `employeeproduct` (
  `empProductID` int(11) NOT NULL auto_increment,
  `employeeID` int(11) default NULL,
  `productID` int(11) default NULL,
  PRIMARY KEY  (`empProductID`),
  KEY `FKCD53F641D5115D3E` (`employeeID`),
  KEY `FKCD53F64116364C12` (`productID`),
  CONSTRAINT `FKCD53F64116364C12` FOREIGN KEY (`productID`) REFERENCES `product` (`productID`),
  CONSTRAINT `FKCD53F641D5115D3E` FOREIGN KEY (`employeeID`) REFERENCES `employee` (`employeeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `employeeproduct` */

insert  into `employeeproduct`(`empProductID`,`employeeID`,`productID`) values (1,6,6),(2,5,6),(3,4,4),(4,1,4),(5,2,1),(6,3,3),(7,4,2),(8,5,1),(9,6,4),(10,6,1);

/*Table structure for table `employeeroster` */

DROP TABLE IF EXISTS `employeeroster`;

CREATE TABLE `employeeroster` (
  `employeeRosterID` int(11) NOT NULL auto_increment,
  `employeeID` int(11) default NULL,
  `rosterID` int(11) default NULL,
  PRIMARY KEY  (`employeeRosterID`),
  KEY `FK9E112F9D5115D3E` (`employeeID`),
  KEY `FK9E112F9B8954F78` (`rosterID`),
  CONSTRAINT `FK9E112F9B8954F78` FOREIGN KEY (`rosterID`) REFERENCES `roster` (`rosterID`),
  CONSTRAINT `FK9E112F9D5115D3E` FOREIGN KEY (`employeeID`) REFERENCES `employee` (`employeeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `employeeroster` */

insert  into `employeeroster`(`employeeRosterID`,`employeeID`,`rosterID`) values (1,1,1),(2,2,2),(3,3,3),(4,4,3),(5,5,5),(6,6,6);

/*Table structure for table `organization` */

DROP TABLE IF EXISTS `organization`;

CREATE TABLE `organization` (
  `organizationID` int(11) NOT NULL auto_increment,
  `contactDetails` varchar(1000) default NULL,
  `description` varchar(5000) default NULL,
  `location` varchar(500) default NULL,
  `name` varchar(500) default NULL,
  `timeSlotDuration` varchar(100) default NULL,
  `tradeLicense` varchar(500) default NULL,
  `tradingHour` varchar(200) default NULL,
  PRIMARY KEY  (`organizationID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `organization` */

insert  into `organization`(`organizationID`,`contactDetails`,`description`,`location`,`name`,`timeSlotDuration`,`tradeLicense`,`tradingHour`) values (1,'Mob: 01911287205, email:proshad@gmail.com','mobile company','dhanmodi, dhaka, bangladesh','gramenphone','30m','123456789','8am to 5 pm'),(2,'Mob: 01911287205, email:proshad@gmail.com','mobile company','dhanmodi, dhaka, bangladesh','banglalink','30m','123456789','8am to 5 pm'),(3,'Mob: 01911287205, email:proshad@gmail.com','mobile company','dhanmodi, dhaka, bangladesh','airtel','30m','123456789','8am to 5 pm'),(4,'Mob: 01911287205, email:proshad@gmail.com','mobile company','dhanmodi, dhaka, bangladesh','teletalk','30m','123456789','8am to 5 pm');

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `productID` int(11) NOT NULL auto_increment,
  `noOfTimeSlot` int(11) NOT NULL,
  `productDescription` varchar(1000) default NULL,
  `productName` varchar(255) default NULL,
  `productNote` varchar(255) default NULL,
  `status` tinyint(1) NOT NULL default '1',
  `categoryID` int(11) default NULL,
  PRIMARY KEY  (`productID`),
  KEY `FK50C664CFD434B5DE` (`categoryID`),
  CONSTRAINT `FK50C664CFD434B5DE` FOREIGN KEY (`categoryID`) REFERENCES `category` (`categoryID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `product` */

insert  into `product`(`productID`,`noOfTimeSlot`,`productDescription`,`productName`,`productNote`,`status`,`categoryID`) values (1,1,'The United Nations\' hectic efforts brought the Awami League and the BNP leaders to the discussion table twice in the last two days, but could not make any breakthrough in the stalemate over the polls-time government as the rival','product 1','They talk twice, agree to nothing ',1,1),(2,1,'The United Nations\' hectic efforts brought the Awami League and the BNP leaders to the discussion table twice in the last two days, but could not make any breakthrough in the stalemate over the polls-time government as the rival','product 2','They talk twice, agree to nothing ',1,2),(3,1,'The United Nations\' hectic efforts brought the Awami League and the BNP leaders to the discussion table twice in the last two days, but could not make any breakthrough in the stalemate over the polls-time government as the rival','product 3','They talk twice, agree to nothing ',1,3),(4,1,'The United Nations\' hectic efforts brought the Awami League and the BNP leaders to the discussion table twice in the last two days, but could not make any breakthrough in the stalemate over the polls-time government as the rival','product 4','They talk twice, agree to nothing ',1,4),(5,1,'The United Nations\' hectic efforts brought the Awami League and the BNP leaders to the discussion table twice in the last two days, but could not make any breakthrough in the stalemate over the polls-time government as the rival','product 5','They talk twice, agree to nothing ',1,5),(6,1,'The United Nations\' hectic efforts brought the Awami League and the BNP leaders to the discussion table twice in the last two days, but could not make any breakthrough in the stalemate over the polls-time government as the rival','product 6','They talk twice, agree to nothing ',1,6);

/*Table structure for table `productimage` */

DROP TABLE IF EXISTS `productimage`;

CREATE TABLE `productimage` (
  `productImageID` int(11) NOT NULL auto_increment,
  `imageUrl` varchar(500) default NULL,
  `productID` int(11) default NULL,
  PRIMARY KEY  (`productImageID`),
  KEY `FK450AFDCC16364C12` (`productID`),
  CONSTRAINT `FK450AFDCC16364C12` FOREIGN KEY (`productID`) REFERENCES `product` (`productID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `productimage` */

insert  into `productimage`(`productImageID`,`imageUrl`,`productID`) values (16,'http://www.assoftech.com/script/compensation/administrator/uploads/5-image5.jpg',1),(17,'http://www.assoftech.com/script/compensation/administrator/uploads/2-image2.jpg',1),(18,'http://www.aldanoub.com/files/8513/4790/9522/desktop.jpg',2),(19,'http://www.aldanoub.com/files/1813/4790/9536/laptops.jpg',2),(20,'http://www.aldanoub.com/files/1213/4790/9495/Computer-accessories.jpg',3),(21,'http://www.mastickevolution.com/wp-content/gallery/security-labels/95161_consumer_products.jpg',3),(22,'http://static.oprah.com/omagazine/200707/images/omag_200707_hair_220x312.jpg',4),(23,'http://im.rediff.com/money/2010/mar/08product1.jpg',5),(24,'http://im.rediff.com/money/2010/mar/08product2.jpg',5),(25,'http://im.rediff.com/money/2010/mar/08product4.jpg',6),(26,'http://im.rediff.com/money/2010/mar/08product7.jpg',6);

/*Table structure for table `productrate` */

DROP TABLE IF EXISTS `productrate`;

CREATE TABLE `productrate` (
  `productRateID` int(11) NOT NULL auto_increment,
  `productID` int(11) default NULL,
  `rateID` int(11) default NULL,
  PRIMARY KEY  (`productRateID`),
  KEY `FKA80264F4E01A0A2` (`rateID`),
  KEY `FKA80264F16364C12` (`productID`),
  CONSTRAINT `FKA80264F16364C12` FOREIGN KEY (`productID`) REFERENCES `product` (`productID`),
  CONSTRAINT `FKA80264F4E01A0A2` FOREIGN KEY (`rateID`) REFERENCES `rate` (`rateID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `productrate` */

insert  into `productrate`(`productRateID`,`productID`,`rateID`) values (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,2,1),(6,3,1),(8,5,1),(9,6,1),(10,6,2),(11,5,2),(13,2,2),(14,1,2),(15,1,3),(16,3,3),(17,5,3),(18,6,3),(19,6,4);

/*Table structure for table `rate` */

DROP TABLE IF EXISTS `rate`;

CREATE TABLE `rate` (
  `rateID` int(11) NOT NULL auto_increment,
  `price` double NOT NULL,
  `rateDescription` varchar(1000) default NULL,
  `rateName` varchar(255) default NULL,
  `status` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`rateID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `rate` */

insert  into `rate`(`rateID`,`price`,`rateDescription`,`rateName`,`status`) values (1,34.5,'student package','student',1),(2,30.5,'child package','child',1),(3,38.5,'adult package','adult',1),(4,38.5,'default package','Default',1);

/*Table structure for table `roster` */

DROP TABLE IF EXISTS `roster`;

CREATE TABLE `roster` (
  `rosterID` int(11) NOT NULL auto_increment,
  `day` varchar(100) default NULL,
  `endTime` varchar(50) default NULL,
  `name` varchar(500) default NULL,
  `startTime` varchar(50) default NULL,
  PRIMARY KEY  (`rosterID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `roster` */

insert  into `roster`(`rosterID`,`day`,`endTime`,`name`,`startTime`) values (1,'sat','5pm','roster 1','8 am'),(2,'sat','5pm','roster 2','10 am'),(3,'sun','5pm','roster 3','10 am'),(4,'mon','5pm','roster 4','10 am'),(5,'tuesday','5pm','roster 5','10 am'),(6,'wednesday','5pm','roster 6','10 am'),(7,'thursday','5pm','roster 7','10 am');

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `studentID` int(11) NOT NULL auto_increment,
  `studentName` varchar(500) default NULL,
  `departmentID` int(11) default NULL,
  PRIMARY KEY  (`studentID`),
  KEY `FKF3371A1B2DBF07C6` (`departmentID`),
  CONSTRAINT `FKF3371A1B2DBF07C6` FOREIGN KEY (`departmentID`) REFERENCES `department` (`departmentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `student` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `emailID` varchar(255) NOT NULL,
  `firstName` varchar(500) default NULL,
  `imageUrl` varchar(1000) default NULL,
  `lastName` varchar(500) default NULL,
  `password` varchar(500) default NULL,
  PRIMARY KEY  (`emailID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`emailID`,`firstName`,`imageUrl`,`lastName`,`password`) values ('chandan@gmail.com','chandan','http://www.goodlightscraps.com/content/funny-baby/funny-baby-1.jpg','biswas','1234'),('mithu@gmail.com','mithu','http://www.baconwrappedmedia.com/wp-content/uploads/2013/01/funny-kids-bacon-wrapped-media-21.jpg','biswas','1234'),('mrinmoy@gmail.com','mrinmoy','http://www.goodlightscraps.com/content/funny-baby/funny-baby-4.jpg','biswas','1234'),('proshad@gmail.com','proshad','http://www.goodlightscraps.com/content/funny-baby/funny-baby-2.jpg','biswas','1234'),('rajib@gmail.com','rajib','http://www.goodlightscraps.com/content/funny-baby/funny-baby-3.jpg','biswas','1234'),('sanjoy@gmail.com','sanjoy','http://www.goodlightscraps.com/content/funny-baby/funny-baby-6.jpg','biswas','1234');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
