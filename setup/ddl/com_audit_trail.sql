SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for com_audit_trail
-- ----------------------------
DROP TABLE IF EXISTS `com_audit_trail`;
CREATE TABLE `com_audit_trail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `AUD_ACTION` varchar(255) DEFAULT NULL,
  `APPLIC_CD` varchar(255) DEFAULT NULL,
  `AUD_CLIENT_IP` varchar(255) DEFAULT NULL,
  `AUD_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `AUD_RESOURCE` varchar(255) DEFAULT NULL,
  `AUD_SERVER_IP` varchar(255) DEFAULT NULL,
  `AUD_USER` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
