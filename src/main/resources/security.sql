/*Table structure for table `security_permission` */

CREATE TABLE `security_permission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `resource` varchar(32) NOT NULL,
  `action` varchar(12) NOT NULL,
  `value` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
);

/*Table structure for table `security_role_permission` */

CREATE TABLE `security_role_permission` (
  `role_id` varchar(32) NOT NULL,
  `resource` varchar(32) NOT NULL,
  `actions` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`role_id`,`resource`)
);

/*Table structure for table `security_user_permission` */

CREATE TABLE `security_user_permission` (
  `user_id` varchar(32) NOT NULL,
  `resource` varchar(32) NOT NULL,
  `actions` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`,`resource`)
);
