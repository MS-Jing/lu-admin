-- MySQL dump 10.13  Distrib 5.7.21, for Win64 (x86_64)
--
-- Host: localhost    Database: lu
-- ------------------------------------------------------
-- Server version	5.7.21-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `gen_column_config`
--

DROP TABLE IF EXISTS `gen_column_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gen_column_config` (
  `id` bigint(20) NOT NULL,
  `table_id` bigint(20) NOT NULL COMMENT '关联表配置id',
  `column_pk` bit(1) DEFAULT NULL COMMENT '主键列',
  `column_name` varchar(255) DEFAULT NULL COMMENT '列名',
  `column_type_name` varchar(20) DEFAULT NULL COMMENT '列类型名称',
  `column_size` int(11) DEFAULT NULL COMMENT '列大小',
  `column_digit` int(11) DEFAULT NULL COMMENT '列位数',
  `field_name` varchar(255) DEFAULT NULL COMMENT 'java字段名',
  `field_type` varchar(255) DEFAULT NULL COMMENT 'java字段类型的全路径(基本数据类型可以不填全)',
  `enum_dict_type` varchar(255) DEFAULT NULL COMMENT '枚举字典类型',
  `field_sort` int(11) DEFAULT NULL COMMENT '字段排序',
  `comment` varchar(255) DEFAULT NULL COMMENT '备注',
  `required` bit(1) DEFAULT b'0' COMMENT '是否必须',
  `show_in_list` bit(1) DEFAULT NULL COMMENT '是否出现在查询列表中',
  `show_in_query` bit(1) DEFAULT NULL COMMENT '是否出现在列表查询条件中',
  `query_type` int(11) DEFAULT NULL COMMENT '查询方式',
  `show_in_info` bit(1) DEFAULT NULL COMMENT '是否出现在信息接口',
  `show_in_save` bit(1) DEFAULT NULL COMMENT '是否出现在保存接口',
  `show_in_update` bit(1) DEFAULT NULL COMMENT '是否出现在更新接口',
  `form_type` int(11) DEFAULT NULL COMMENT '新增和更新接口的表单类型',
  `show_in_import` bit(1) DEFAULT NULL COMMENT '字段是否导入',
  `show_in_export` bit(1) DEFAULT NULL COMMENT '字段是否导出',
  `create_user` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='生成器列配置';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gen_column_config`
--

LOCK TABLES `gen_column_config` WRITE;
/*!40000 ALTER TABLE `gen_column_config` DISABLE KEYS */;
INSERT INTO `gen_column_config` VALUES (1859602332415967233,1859602235108114433,'','id','BIGINT',19,0,'id','Long','',1,'','\0','','\0',0,'','\0','',0,'\0','\0',0,'2024-11-21 22:18:40',0,'2024-12-10 16:30:09','\0',0),(1859602332436938753,1859602235108114433,'\0','nick_name','VARCHAR',100,0,'nickName','String','',2,'用户昵称','','','',8,'','','',0,'','',0,'2024-11-21 22:18:40',0,'2024-12-10 16:30:09','\0',0),(1859602332436938754,1859602235108114433,'\0','user_name','VARCHAR',100,0,'userName','String',NULL,3,'用户名','','\0','\0',0,'\0','','',0,'','',0,'2024-11-21 22:18:40',0,'2024-12-10 16:30:09','\0',0),(1859602332436938755,1859602235108114433,'\0','password','VARCHAR',100,0,'password','String',NULL,4,'密码','\0','\0','\0',0,'\0','','\0',0,'','',0,'2024-11-21 22:18:40',0,'2024-12-10 16:30:09','\0',0),(1859602332436938756,1859602235108114433,'\0','email','VARCHAR',100,0,'email','String',NULL,5,'电子邮箱','\0','\0','\0',0,'','','',0,'','',0,'2024-11-21 22:18:40',0,'2024-12-10 16:30:09','\0',0),(1859602332436938757,1859602235108114433,'\0','mobile','VARCHAR',11,0,'mobile','String',NULL,6,'移动电话','\0','','',1,'','','',0,'','',0,'2024-11-21 22:18:40',0,'2024-12-10 16:30:09','\0',0),(1859602332436938758,1859602235108114433,'\0','user_status','INT',10,0,'userStatus','Integer','SysUserStatus',7,'用户状态','','','',1,'','','',0,'','',0,'2024-11-21 22:18:40',0,'2024-12-10 16:30:09','\0',0),(1859602332436938759,1859602235108114433,'\0','create_user','BIGINT',19,0,'createUser','Long',NULL,8,'','\0','\0','\0',0,'\0','\0','\0',0,'\0','\0',0,'2024-11-21 22:18:40',0,'2024-12-10 16:30:09','\0',0),(1859602332436938760,1859602235108114433,'\0','create_time','DATETIME',19,0,'createTime','java.time.LocalDateTime',NULL,9,'','\0','\0','\0',0,'\0','\0','\0',0,'\0','\0',0,'2024-11-21 22:18:40',0,'2024-12-10 16:30:09','\0',0),(1859602332436938761,1859602235108114433,'\0','update_user','BIGINT',19,0,'updateUser','Long',NULL,10,'','\0','\0','\0',0,'\0','\0','\0',0,'\0','\0',0,'2024-11-21 22:18:40',0,'2024-12-10 16:30:09','\0',0),(1859602332499853313,1859602235108114433,'\0','update_time','DATETIME',19,0,'updateTime','java.time.LocalDateTime',NULL,11,'','\0','\0','\0',0,'\0','\0','\0',0,'\0','\0',0,'2024-11-21 22:18:40',0,'2024-12-10 16:30:09','\0',0),(1859602332499853314,1859602235108114433,'\0','deleted','BIT',1,0,'deleted','Boolean',NULL,12,'','\0','\0','\0',0,'\0','\0','\0',0,'\0','\0',0,'2024-11-21 22:18:40',0,'2024-12-10 16:30:09','\0',0),(1859602332499853315,1859602235108114433,'\0','version','INT',10,0,'version','Integer',NULL,13,'','\0','\0','\0',0,'\0','\0','\0',0,'\0','\0',0,'2024-11-21 22:18:40',0,'2024-12-10 16:30:09','\0',0),(1864584448073621507,1864584448073621506,'','id','BIGINT',19,0,'id','Long',NULL,1,'','\0','\0','\0',NULL,'\0','\0','\0',NULL,'\0','\0',0,'2024-12-05 16:15:49',0,'2024-12-05 16:15:49','',0),(1864584448073621508,1864584448073621506,'\0','user_id','BIGINT',19,0,'userId','Long',NULL,2,'用户id','\0','','',1,'','','',NULL,'','',0,'2024-12-05 16:15:49',0,'2024-12-05 16:15:49','',0),(1864584448140730369,1864584448073621506,'\0','role_id','BIGINT',19,0,'roleId','Long',NULL,3,'角色id','\0','','',1,'','','',NULL,'','',0,'2024-12-05 16:15:49',0,'2024-12-05 16:15:49','',0),(1864585508800520194,1864585508800520193,'','id','BIGINT',19,0,'id','Long',NULL,1,'','\0','\0','\0',NULL,'\0','\0','\0',NULL,'\0','\0',0,'2024-12-05 16:20:02',0,'2024-12-05 16:20:02','',0),(1864585508800520195,1864585508800520193,'\0','user_id','BIGINT',19,0,'userId','Long',NULL,2,'用户id','\0','','',1,'','','',NULL,'','',0,'2024-12-05 16:20:02',0,'2024-12-05 16:20:02','',0),(1864585508800520196,1864585508800520193,'\0','role_id','BIGINT',19,0,'roleId','Long',NULL,3,'角色id','\0','','',1,'','','',NULL,'','',0,'2024-12-05 16:20:02',0,'2024-12-05 16:20:02','',0),(1864586421632065538,1864586421632065537,'','id','BIGINT',19,0,'id','Long',NULL,1,'','\0','\0','\0',NULL,'\0','\0','\0',NULL,'\0','\0',0,'2024-12-05 16:23:40',0,'2024-12-05 16:23:40','',0),(1864586421694980097,1864586421632065537,'\0','user_id','BIGINT',19,0,'userId','Long',NULL,2,'用户id','\0','','',2,'','','\0',NULL,'\0','\0',0,'2024-12-05 16:23:40',0,'2024-12-05 16:23:40','',0),(1864586421694980098,1864586421632065537,'\0','role_id','BIGINT',19,0,'roleId','Long',NULL,3,'角色id','\0','','',3,'','','\0',NULL,'\0','\0',0,'2024-12-05 16:23:40',0,'2024-12-05 16:23:40','',0),(1864603629120741378,1864603629120741377,'','id','BIGINT',19,0,'id','Long',NULL,1,'','\0','\0','\0',NULL,'\0','\0','\0',NULL,'\0','\0',0,'2024-12-05 17:32:03',0,'2024-12-10 17:08:35','\0',0),(1864603629120741379,1864603629120741377,'\0','user_id','BIGINT',19,0,'userId','Long',NULL,2,'用户id','\0','\0','\0',NULL,'\0','\0','\0',NULL,'\0','\0',0,'2024-12-05 17:32:03',0,'2024-12-10 17:08:35','\0',0),(1864603629120741380,1864603629120741377,'\0','role_id','BIGINT',19,0,'roleId','Long',NULL,3,'角色id','\0','\0','\0',NULL,'\0','\0','\0',NULL,'\0','\0',0,'2024-12-05 17:32:03',0,'2024-12-10 17:08:35','\0',0),(1866411603334078466,1866411603334078465,'','id','BIGINT',19,0,'id','Long',NULL,1,'','\0','','\0',NULL,'\0','\0','',NULL,'\0','\0',0,'2024-12-10 17:16:17',0,'2024-12-10 17:21:22','\0',0),(1866411603396993026,1866411603334078465,'\0','role_name','VARCHAR',100,0,'roleName','String',NULL,2,'角色名称','\0','','',1,'','','',NULL,'\0','\0',0,'2024-12-10 17:16:17',0,'2024-12-10 17:21:22','\0',0),(1866411603396993027,1866411603334078465,'\0','remark','VARCHAR',100,0,'remark','String',NULL,3,'备注','\0','','\0',NULL,'','','',NULL,'\0','\0',0,'2024-12-10 17:16:17',0,'2024-12-10 17:21:22','\0',0),(1866411603396993028,1866411603334078465,'\0','create_user','BIGINT',19,0,'createUser','Long',NULL,4,'','\0','\0','\0',NULL,'\0','\0','\0',NULL,'\0','\0',0,'2024-12-10 17:16:17',0,'2024-12-10 17:21:22','\0',0),(1866411603396993029,1866411603334078465,'\0','create_time','DATETIME',19,0,'createTime','java.time.LocalDateTime',NULL,5,'','\0','','\0',NULL,'\0','\0','\0',NULL,'\0','\0',0,'2024-12-10 17:16:17',0,'2024-12-10 17:21:22','\0',0),(1866411603396993030,1866411603334078465,'\0','update_user','BIGINT',19,0,'updateUser','Long',NULL,6,'','\0','\0','\0',NULL,'\0','\0','\0',NULL,'\0','\0',0,'2024-12-10 17:16:17',0,'2024-12-10 17:21:22','\0',0),(1866411603396993031,1866411603334078465,'\0','update_time','DATETIME',19,0,'updateTime','java.time.LocalDateTime',NULL,7,'','\0','','\0',NULL,'\0','\0','\0',NULL,'\0','\0',0,'2024-12-10 17:16:17',0,'2024-12-10 17:21:22','\0',0),(1866411603396993032,1866411603334078465,'\0','deleted','BIT',1,0,'deleted','Boolean',NULL,8,'','\0','\0','\0',NULL,'\0','\0','\0',NULL,'\0','\0',0,'2024-12-10 17:16:17',0,'2024-12-10 17:21:22','\0',0),(1866411603396993033,1866411603334078465,'\0','version','INT',10,0,'version','Integer',NULL,9,'','\0','\0','\0',NULL,'\0','\0','\0',NULL,'\0','\0',0,'2024-12-10 17:16:17',0,'2024-12-10 17:21:22','\0',0),(1866414513275076609,1866414513170219009,'','id','BIGINT',19,0,'id','Long',NULL,1,'','\0','\0','\0',NULL,'\0','\0','\0',NULL,'\0','\0',0,'2024-12-10 17:27:51',0,'2024-12-10 17:27:51','\0',0),(1866414513275076610,1866414513170219009,'\0','role_id','BIGINT',19,0,'roleId','Long',NULL,2,'角色id','\0','\0','\0',NULL,'\0','\0','\0',NULL,'\0','\0',0,'2024-12-10 17:27:51',0,'2024-12-10 17:27:51','\0',0),(1866414513275076611,1866414513170219009,'\0','menu_id','BIGINT',19,0,'menuId','Long',NULL,3,'菜单id','\0','\0','\0',NULL,'\0','\0','\0',NULL,'\0','\0',0,'2024-12-10 17:27:51',0,'2024-12-10 17:27:51','\0',0),(1866417899424776195,1866417899424776194,'','id','BIGINT',19,0,'id','Long',NULL,1,'','\0','','\0',NULL,'\0','\0','',NULL,'\0','\0',0,'2024-12-10 17:41:18',0,'2024-12-10 17:41:18','',0),(1866417899424776196,1866417899424776194,'\0','parent_id','BIGINT',19,0,'parentId','Long',NULL,2,'父菜单id 顶级菜单为0','\0','','\0',NULL,'','','',NULL,'\0','\0',0,'2024-12-10 17:41:18',0,'2024-12-10 17:41:18','',0),(1866417899588354050,1866417899424776194,'\0','title','VARCHAR',100,0,'title','String',NULL,3,'菜单标题','\0','','\0',NULL,'','','',NULL,'\0','\0',0,'2024-12-10 17:41:18',0,'2024-12-10 17:41:18','',0),(1866417899617714177,1866417899424776194,'\0','name','VARCHAR',100,0,'name','String',NULL,4,'菜单名称','\0','','',1,'','','',NULL,'\0','\0',0,'2024-12-10 17:41:18',0,'2024-12-10 17:41:18','',0),(1866417899684823042,1866417899424776194,'\0','menu_type','INT',10,0,'menuType','Integer',NULL,5,'菜单类型','','','\0',NULL,'','','',NULL,'\0','\0',0,'2024-12-10 17:41:18',0,'2024-12-10 17:41:18','',0),(1866417899684823043,1866417899424776194,'\0','path','VARCHAR',255,0,'path','String',NULL,6,'菜单路由','\0','','\0',NULL,'','','',NULL,'\0','\0',0,'2024-12-10 17:41:18',0,'2024-12-10 17:41:18','',0),(1866417899751931905,1866417899424776194,'\0','component','VARCHAR',255,0,'component','String',NULL,7,'菜单组件','\0','','\0',NULL,'','','',NULL,'\0','\0',0,'2024-12-10 17:41:18',0,'2024-12-10 17:41:18','',0),(1866417899751931906,1866417899424776194,'\0','icon','VARCHAR',255,0,'icon','String',NULL,8,'','\0','','\0',NULL,'','','',NULL,'\0','\0',0,'2024-12-10 17:41:18',0,'2024-12-10 17:41:18','',0),(1866417899751931907,1866417899424776194,'\0','sort_code','INT',10,0,'sortCode','Integer',NULL,9,'菜单排序','\0','\0','\0',NULL,'','','',NULL,'\0','\0',0,'2024-12-10 17:41:18',0,'2024-12-10 17:41:18','',0),(1866417899819040770,1866417899424776194,'\0','module_name','VARCHAR',100,0,'moduleName','String',NULL,10,'模块名(只加载当前有的模块cai\'dan)','\0','','\0',NULL,'','','',NULL,'\0','\0',0,'2024-12-10 17:41:18',0,'2024-12-10 17:41:18','',0),(1866417899819040771,1866417899424776194,'\0','deleted','BIT',1,0,'deleted','Boolean',NULL,11,'','\0','\0','\0',NULL,'\0','\0','\0',NULL,'\0','\0',0,'2024-12-10 17:41:18',0,'2024-12-10 17:41:18','',0),(1866417899819040772,1866417899424776194,'\0','version','INT',10,0,'version','Integer',NULL,12,'','\0','\0','\0',NULL,'\0','\0','\0',NULL,'\0','\0',0,'2024-12-10 17:41:18',0,'2024-12-10 17:41:18','',0),(1866417899819040773,1866417899424776194,'\0','create_user','BIGINT',19,0,'createUser','Long',NULL,13,'','\0','\0','\0',NULL,'\0','\0','\0',NULL,'\0','\0',0,'2024-12-10 17:41:18',0,'2024-12-10 17:41:18','',0),(1866417899819040774,1866417899424776194,'\0','create_time','DATETIME',19,0,'createTime','java.time.LocalDateTime',NULL,14,'','\0','\0','\0',NULL,'','\0','\0',NULL,'\0','\0',0,'2024-12-10 17:41:18',0,'2024-12-10 17:41:18','',0),(1866417899819040775,1866417899424776194,'\0','update_user','BIGINT',19,0,'updateUser','Long',NULL,15,'','\0','\0','\0',NULL,'\0','\0','\0',NULL,'\0','\0',0,'2024-12-10 17:41:18',0,'2024-12-10 17:41:18','',0),(1866417899890343937,1866417899424776194,'\0','update_time','DATETIME',19,0,'updateTime','java.time.LocalDateTime',NULL,16,'','\0','\0','\0',NULL,'\0','\0','\0',NULL,'\0','\0',0,'2024-12-10 17:41:18',0,'2024-12-10 17:41:18','',0),(1866733344698671105,1866733344593813505,'','id','BIGINT',19,0,'id','Long',NULL,1,'','\0','','\0',NULL,'','\0','',NULL,'\0','\0',0,'2024-12-11 14:34:46',0,'2024-12-11 14:34:46','\0',0),(1866733344765779970,1866733344593813505,'\0','parent_id','BIGINT',19,0,'parentId','Long',NULL,2,'父菜单id 顶级菜单为0','\0','','\0',NULL,'','','',NULL,'\0','\0',0,'2024-12-11 14:34:46',0,'2024-12-11 14:34:46','\0',0),(1866733344765779971,1866733344593813505,'\0','menu_type','INT',10,0,'menuType','Integer','SysMenuType',3,'菜单类型','','','',1,'','','',NULL,'\0','\0',0,'2024-12-11 14:34:46',0,'2024-12-11 14:34:46','\0',0),(1866733344828694530,1866733344593813505,'\0','path','VARCHAR',255,0,'path','String',NULL,4,'菜单路由','\0','','',1,'','','',NULL,'\0','\0',0,'2024-12-11 14:34:46',0,'2024-12-11 14:34:46','\0',0),(1866733344828694531,1866733344593813505,'\0','name','VARCHAR',100,0,'name','String',NULL,5,'菜单名称','\0','','',1,'','','',NULL,'\0','\0',0,'2024-12-11 14:34:46',0,'2024-12-11 14:34:46','\0',0),(1866733344828694532,1866733344593813505,'\0','component','VARCHAR',255,0,'component','String',NULL,6,'菜单组件','\0','','\0',NULL,'','','',NULL,'\0','\0',0,'2024-12-11 14:34:46',0,'2024-12-11 14:34:46','\0',0),(1866733344891609089,1866733344593813505,'\0','redirect','VARCHAR',255,0,'redirect','String',NULL,7,'重定向','\0','\0','\0',NULL,'','','',NULL,'\0','\0',0,'2024-12-11 14:34:46',0,'2024-12-11 14:34:46','\0',0),(1866733344891609090,1866733344593813505,'\0','icon','VARCHAR',255,0,'icon','String',NULL,8,'菜单图标','\0','\0','\0',NULL,'','','',NULL,'\0','\0',0,'2024-12-11 14:34:46',0,'2024-12-11 14:34:46','\0',0),(1866733344891609091,1866733344593813505,'\0','title','VARCHAR',100,0,'title','String',NULL,9,'菜单标题','\0','','',1,'','','',NULL,'\0','\0',0,'2024-12-11 14:34:46',0,'2024-12-11 14:34:46','\0',0),(1866733344958717954,1866733344593813505,'\0','active_menu','VARCHAR',255,0,'activeMenu','String',NULL,10,'当前菜单隐藏时才需要设置','\0','\0','\0',NULL,'','','',NULL,'\0','\0',0,'2024-12-11 14:34:46',0,'2024-12-11 14:34:46','\0',0),(1866733344958717955,1866733344593813505,'\0','link','VARCHAR',255,0,'link','String',NULL,11,'链接','\0','\0','\0',NULL,'','','',NULL,'\0','\0',0,'2024-12-11 14:34:46',0,'2024-12-11 14:34:46','\0',0),(1866733344958717956,1866733344593813505,'\0','hide','BIT',1,0,'hide','Boolean',NULL,12,'在菜单上隐藏','\0','\0','\0',NULL,'','','',NULL,'\0','\0',0,'2024-12-11 14:34:46',0,'2024-12-11 14:34:46','\0',0),(1866733344958717957,1866733344593813505,'\0','full','BIT',1,0,'full','Boolean',NULL,13,'充满整个屏幕','\0','\0','\0',NULL,'','','',NULL,'\0','\0',0,'2024-12-11 14:34:46',0,'2024-12-11 14:34:46','\0',0),(1866733344958717958,1866733344593813505,'\0','affix','BIT',1,0,'affix','Boolean',NULL,14,'是否始终依附在tab上','\0','\0','\0',NULL,'','','',NULL,'\0','\0',0,'2024-12-11 14:34:46',0,'2024-12-11 14:34:46','\0',0),(1866733344958717959,1866733344593813505,'\0','keep_alive','BIT',1,0,'keepAlive','Boolean',NULL,15,'保持活跃','\0','\0','\0',NULL,'','','',NULL,'\0','\0',0,'2024-12-11 14:34:46',0,'2024-12-11 14:34:46','\0',0),(1866733344958717960,1866733344593813505,'\0','sort_code','INT',10,0,'sortCode','Integer',NULL,16,'菜单排序','\0','\0','\0',NULL,'','','',NULL,'\0','\0',0,'2024-12-11 14:34:46',0,'2024-12-11 14:34:46','\0',0),(1866733345025826818,1866733344593813505,'\0','module_name','VARCHAR',100,0,'moduleName','String',NULL,17,'模块名(只加载当前有的模块菜单)','\0','','\0',NULL,'','','',NULL,'\0','\0',0,'2024-12-11 14:34:46',0,'2024-12-11 14:34:46','\0',0),(1866733345025826819,1866733344593813505,'\0','deleted','BIT',1,0,'deleted','Boolean',NULL,18,'','\0','\0','\0',NULL,'\0','\0','\0',NULL,'\0','\0',0,'2024-12-11 14:34:46',0,'2024-12-11 14:34:46','\0',0),(1866733345025826820,1866733344593813505,'\0','version','INT',10,0,'version','Integer',NULL,19,'','\0','\0','\0',NULL,'\0','\0','\0',NULL,'\0','\0',0,'2024-12-11 14:34:46',0,'2024-12-11 14:34:46','\0',0),(1866733345025826821,1866733344593813505,'\0','create_user','BIGINT',19,0,'createUser','Long',NULL,20,'','\0','\0','\0',NULL,'\0','\0','\0',NULL,'\0','\0',0,'2024-12-11 14:34:46',0,'2024-12-11 14:34:46','\0',0),(1866733345025826822,1866733344593813505,'\0','create_time','DATETIME',19,0,'createTime','java.time.LocalDateTime',NULL,21,'','\0','','\0',NULL,'\0','\0','\0',NULL,'\0','\0',0,'2024-12-11 14:34:46',0,'2024-12-11 14:34:46','\0',0),(1866733345025826823,1866733344593813505,'\0','update_user','BIGINT',19,0,'updateUser','Long',NULL,22,'','\0','\0','\0',NULL,'\0','\0','\0',NULL,'\0','\0',0,'2024-12-11 14:34:46',0,'2024-12-11 14:34:46','\0',0),(1866733345092935681,1866733344593813505,'\0','update_time','DATETIME',19,0,'updateTime','java.time.LocalDateTime',NULL,23,'','\0','\0','\0',NULL,'\0','\0','\0',NULL,'\0','\0',0,'2024-12-11 14:34:46',0,'2024-12-11 14:34:46','\0',0);
/*!40000 ALTER TABLE `gen_column_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gen_table_config`
--

DROP TABLE IF EXISTS `gen_table_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gen_table_config` (
  `id` bigint(20) NOT NULL,
  `table_name` varchar(255) DEFAULT NULL COMMENT '表名',
  `comment` varchar(255) DEFAULT NULL COMMENT '表备注',
  `author` varchar(255) DEFAULT NULL COMMENT '作者',
  `module_name` varchar(100) DEFAULT NULL COMMENT '模块名',
  `package_name` varchar(100) DEFAULT NULL COMMENT '包名',
  `super_class` varchar(255) DEFAULT NULL COMMENT '父类',
  `table_prefix` varchar(50) DEFAULT NULL COMMENT '表前缀',
  `unprefix` bit(1) DEFAULT b'0' COMMENT '是否去掉前缀',
  `gen_page` bit(1) DEFAULT b'0' COMMENT '是否生成分页接口',
  `gen_info` bit(1) DEFAULT b'0' COMMENT '是否生成详情接口',
  `gen_save` bit(1) DEFAULT b'0' COMMENT '是否生成新增接口',
  `gen_update` bit(1) DEFAULT b'0' COMMENT '是否生成更新接口',
  `gen_deleted` bit(1) DEFAULT b'0' COMMENT '是否生成删除接口',
  `gen_import` bit(1) DEFAULT b'0' COMMENT '是否生成导入接口',
  `gen_export` bit(1) DEFAULT b'0' COMMENT '是否生成导出接口',
  `create_user` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `gen_table_config_table_name_index` (`table_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='生成器表配置';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gen_table_config`
--

LOCK TABLES `gen_table_config` WRITE;
/*!40000 ALTER TABLE `gen_table_config` DISABLE KEYS */;
INSERT INTO `gen_table_config` VALUES (1859602235108114433,'sys_user','系统用户','luojing','sys','com.lj','com.lj.sys.standard.SysStandardEntity','sys','\0','','','','','','\0','\0',0,'2024-11-21 22:18:17',0,'2024-12-10 16:30:09','\0',0),(1864584448073621506,'sys_user_role','用户角色关联表','lj','sys','com.lj','com.lj.mp.standard.entity.IdLongStandardEntity','sys','','','','','','','','',0,'2024-12-05 16:15:49',0,'2024-12-05 16:17:30','',0),(1864585508800520193,'sys_user_role','用户角色关联表','lj','sys','com.lj','com.lj.mp.standard.entity.IdLongStandardEntity','sys','','','','','','','','',0,'2024-12-05 16:20:02',0,'2024-12-05 16:20:21','',0),(1864586421632065537,'sys_user_role','用户角色关联表','lj','sys','com.lj','com.lj.mp.standard.StandardEntity','sys','','','','','','','','',0,'2024-12-05 16:23:40',0,'2024-12-05 17:31:23','',0),(1864603629120741377,'sys_user_role','用户角色关联表','lj','sys','com.lj','com.lj.mp.standard.entity.IdLongStandardEntity','sys','\0','\0','\0','\0','\0','\0','\0','\0',0,'2024-12-05 17:32:03',0,'2024-12-10 17:08:35','\0',0),(1866411603334078465,'sys_role','系统角色表','lj','sys','com.lj','com.lj.sys.standard.SysStandardEntity','sys','\0','','','','','','\0','\0',0,'2024-12-10 17:16:17',0,'2024-12-10 17:21:22','\0',0),(1866414513170219009,'sys_role_menu','角色菜单关联表','lj','sys','com.lj','com.lj.mp.standard.entity.IdLongStandardEntity','sys','\0','\0','\0','\0','\0','\0','\0','\0',0,'2024-12-10 17:27:51',0,'2024-12-10 17:27:51','\0',0),(1866417899424776194,'sys_menu','菜单表','lj','sys','com.lj','com.lj.sys.standard.SysStandardEntity','sys','\0','','','','','','\0','\0',0,'2024-12-10 17:41:18',0,'2024-12-11 14:20:25','',0),(1866733344593813505,'sys_menu','菜单表','lj','sys','com.lj','com.lj.sys.standard.SysStandardEntity','sys','\0','','','','','','\0','\0',0,'2024-12-11 14:34:46',0,'2024-12-11 14:34:46','\0',0);
/*!40000 ALTER TABLE `gen_table_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单id 顶级菜单为0',
  `menu_type` int(11) NOT NULL COMMENT '菜单类型',
  `path` varchar(255) DEFAULT NULL COMMENT '菜单路由',
  `name` varchar(100) DEFAULT NULL COMMENT '菜单名称',
  `component` varchar(255) DEFAULT NULL COMMENT '菜单组件',
  `redirect` varchar(255) DEFAULT NULL COMMENT '重定向',
  `icon` varchar(255) DEFAULT NULL COMMENT '菜单图标',
  `title` varchar(100) DEFAULT NULL COMMENT '菜单标题',
  `active_menu` varchar(255) DEFAULT NULL COMMENT '当前菜单隐藏时才需要设置',
  `link` varchar(255) DEFAULT NULL COMMENT '链接',
  `hide` bit(1) DEFAULT b'0' COMMENT '在菜单上隐藏',
  `full` bit(1) DEFAULT b'0' COMMENT '充满整个屏幕',
  `affix` bit(1) DEFAULT b'0' COMMENT '是否始终依附在tab上',
  `keep_alive` bit(1) DEFAULT b'1' COMMENT '保持活跃',
  `sort_code` int(11) DEFAULT NULL COMMENT '菜单排序',
  `module_name` varchar(100) DEFAULT NULL COMMENT '模块名(只加载当前有的模块菜单)',
  `permission` varchar(255) DEFAULT NULL COMMENT '权限(例如: sys:menu:list)',
  `deleted` bit(1) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `create_user` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES (1,0,2,'/home/index','home','/home/index',NULL,'HomeFilled','首页',NULL,NULL,'\0','\0','','',1,'sys',NULL,'\0',0,0,'2024-12-16 15:09:27',0,'2024-12-16 15:09:27'),(2,0,1,'/system','system',NULL,'/system/accountManage','Tools','系统管理',NULL,NULL,'\0','\0','\0','',2,'sys',NULL,'\0',0,0,'2024-12-16 15:10:26',0,'2024-12-16 15:10:26'),(3,2,2,'/system/accountManage','accountManage','/system/accountManage/index',NULL,'Menu','账号管理',NULL,NULL,'\0','\0','\0','',1,'sys',NULL,'\0',0,0,'2024-12-16 15:10:45',0,'2024-12-16 15:10:45'),(4,2,2,'/system/roleManage','roleManage','/system/roleManage/index',NULL,'Menu','角色管理',NULL,NULL,'\0','\0','\0','',2,'sys',NULL,'\0',0,0,'2024-12-16 15:12:32',0,'2024-12-16 15:12:32'),(5,2,2,'/system/menuMange','menuMange','/system/menuMange/index',NULL,'Menu','菜单管理',NULL,NULL,'\0','\0','\0','',3,'sys',NULL,'\0',0,0,'2024-12-16 15:11:00',0,'2024-12-16 15:11:00'),(6,2,2,'/system/dictManage','dictManage','/system/dictManage/index',NULL,'Menu','字典管理',NULL,NULL,'\0','\0','\0','',4,'sys',NULL,'\0',0,0,'2024-12-16 15:11:06',0,'2024-12-16 15:11:06'),(7,0,2,'/generator/index','generator','/generator/index',NULL,'InfoFilled','代码生成器',NULL,NULL,'\0','\0','\0','',3,'generator',NULL,'\0',0,0,'2024-12-16 15:15:03',0,'2024-12-16 15:15:03'),(8,7,3,NULL,'generator',NULL,NULL,NULL,'新增',NULL,NULL,'\0','\0','\0','',1,'generator','gen:table:save','\0',0,0,'2024-12-16 15:15:38',0,'2024-12-16 15:15:38'),(9,7,3,NULL,'generator',NULL,NULL,NULL,'更新',NULL,NULL,'\0','\0','\0','',2,'generator','gen:table:update','\0',0,0,'2024-12-16 15:15:44',0,'2024-12-16 15:15:44'),(1868559488158908417,0,2,'/dataScreen','dataScreen','/dataScreen/index',NULL,'Histogram','数据大屏',NULL,NULL,'\0','','\0','',10000,'common',NULL,'\0',0,0,'2024-12-16 15:31:13',0,'2024-12-16 15:31:13'),(1868570236528422913,0,1,'/auth','auth',NULL,'/auth/menu','Lock','权限管理',NULL,NULL,'\0','\0','\0','',10004,'common',NULL,'\0',0,0,'2024-12-16 16:13:55',0,'2024-12-16 16:13:55'),(1868570809881391106,1868570236528422913,2,'/auth/menu','authMenu','/auth/menu/index',NULL,'Menu','菜单权限',NULL,NULL,'\0','\0','\0','',10005,'common',NULL,'\0',0,0,'2024-12-16 16:16:12',0,'2024-12-16 16:16:12'),(1868571605586997249,1868570236528422913,2,'/auth/button','authButton','/auth/button/index',NULL,'Menu','按钮权限',NULL,NULL,'\0','\0','\0','',10006,'common',NULL,'\0',0,0,'2024-12-16 16:19:22',0,'2024-12-16 16:19:22'),(1868572170610077698,0,1,'/assembly','assembly',NULL,'/assembly/guide','Briefcase','常用组件',NULL,NULL,'\0','\0','\0','',10007,'common',NULL,'\0',0,0,'2024-12-16 16:21:37',0,'2024-12-16 16:21:37'),(1868572709506838529,1868572170610077698,2,'/assembly/guide','guide','/assembly/guide/index',NULL,'Menu','引导页',NULL,NULL,'\0','\0','\0','',10008,'common',NULL,'\0',0,0,'2024-12-16 16:23:45',0,'2024-12-16 16:23:45'),(1868573265117900801,1868572170610077698,2,'/assembly/tabs','tabs','/assembly/tabs/index',NULL,'Menu','标签页操作',NULL,NULL,'\0','\0','\0','',10009,'common',NULL,'\0',0,0,'2024-12-16 16:25:57',0,'2024-12-16 16:25:57'),(1868573766567915522,1868573265117900801,2,'/assembly/tabs/detail/:id','tabsDetail','/assembly/tabs/detail',NULL,'Menu','Tab 详情','/assembly/tabs',NULL,'','\0','\0','',10010,'common',NULL,'\0',0,0,'2024-12-16 16:27:57',0,'2024-12-16 16:27:57'),(1868575580986077185,1868572170610077698,2,'/assembly/selectIcon','selectIcon','/assembly/selectIcon/index',NULL,'Menu','图标选择器',NULL,NULL,'\0','\0','\0','',10011,'common',NULL,'\0',0,0,'2024-12-16 16:35:10',0,'2024-12-16 16:35:25'),(1868577113425375233,1868572170610077698,2,'/assembly/selectFilter','selectFilter','/assembly/selectFilter/index',NULL,'Menu','分类筛选器',NULL,NULL,'\0','\0','\0','',10012,'common',NULL,'\0',0,0,'2024-12-16 16:41:15',0,'2024-12-16 16:41:15'),(1868577460378202114,1868572170610077698,2,'/assembly/treeFilter','treeFilter','/assembly/treeFilter/index',NULL,'Menu','树形筛选器',NULL,NULL,'\0','\0','\0','',10013,'common',NULL,'\0',0,0,'2024-12-16 16:42:38',0,'2024-12-16 16:42:38'),(1868577684375007234,1868572170610077698,2,'/assembly/svgIcon','svgIcon','/assembly/svgIcon/index',NULL,'Menu','SVG 图标',NULL,NULL,'\0','\0','\0','',10014,'common',NULL,'\0',0,0,'2024-12-16 16:43:31',0,'2024-12-16 16:43:45'),(1868578124462338049,1868572170610077698,2,'/assembly/uploadFile','uploadFile','/assembly/uploadFile/index',NULL,'Menu','文件上传',NULL,NULL,'\0','\0','\0','',10015,'common',NULL,'\0',0,0,'2024-12-16 16:45:16',0,'2024-12-16 16:45:16'),(1868578445959933953,1868572170610077698,2,'/assembly/batchImport','batchImport','/assembly/batchImport/index',NULL,'Menu','批量添加数据',NULL,NULL,'\0','\0','\0','',10016,'common',NULL,'\0',0,0,'2024-12-16 16:46:33',0,'2024-12-16 16:46:33'),(1868578687283408897,1868572170610077698,2,'/assembly/wangEditor','wangEditor','/assembly/wangEditor/index',NULL,'Menu','富文本编辑器',NULL,NULL,'\0','\0','\0','',10017,'common',NULL,'\0',0,0,'2024-12-16 16:47:30',0,'2024-12-16 16:47:30'),(1868579109314277377,1868572170610077698,2,'/assembly/draggable','draggable','/assembly/draggable/index',NULL,'Menu','拖拽组件',NULL,NULL,'\0','\0','\0','',10018,'common',NULL,'\0',0,0,'2024-12-16 16:49:11',0,'2024-12-16 16:49:11'),(1868579472708775937,0,1,'/dashboard','dashboard',NULL,'/dashboard/dataVisualize','Odometer','Dashboard',NULL,NULL,'\0','\0','\0','',10019,'common',NULL,'\0',0,0,'2024-12-16 16:50:37',0,'2024-12-16 18:06:55'),(1868579791022895105,1868579472708775937,2,'/dashboard/dataVisualize','dataVisualize','/dashboard/dataVisualize/index',NULL,'Menu','数据可视化',NULL,NULL,'\0','\0','\0','',10020,'common',NULL,'\0',0,0,'2024-12-16 16:51:53',0,'2024-12-16 16:51:53'),(1868580125921292290,0,1,'/form','form',NULL,'/form/proForm','Tickets','表单 Form',NULL,NULL,'\0','\0','\0','',10021,'common',NULL,'\0',0,0,'2024-12-16 16:53:13',0,'2024-12-16 16:53:13'),(1868580413923176449,1868580125921292290,2,'/form/proForm','proForm','/form/proForm/index',NULL,'Menu','超级 Form',NULL,NULL,'\0','\0','\0','',10022,'common',NULL,'\0',0,0,'2024-12-16 16:54:22',0,'2024-12-16 16:54:22'),(1868580627107065857,1868580125921292290,2,'/form/basicForm','basicForm','/form/basicForm/index',NULL,'Menu','基础 Form',NULL,NULL,'\0','\0','\0','',10023,'common',NULL,'\0',0,0,'2024-12-16 16:55:13',0,'2024-12-16 16:55:36'),(1868580934234976258,1868580125921292290,2,'/form/validateForm','validateForm','/form/validateForm/index',NULL,'Menu','校验 Form',NULL,NULL,'\0','\0','\0','',10024,'common',NULL,'\0',0,0,'2024-12-16 16:56:26',0,'2024-12-16 16:56:26'),(1868581133942566913,1868580125921292290,2,'/form/dynamicForm','dynamicForm','/form/dynamicForm/index',NULL,'Menu','动态 Form',NULL,NULL,'\0','\0','\0','',10025,'common',NULL,'\0',0,0,'2024-12-16 16:57:14',0,'2024-12-16 16:57:14'),(1868581475899977729,0,1,'/echarts','echarts',NULL,'/echarts/waterChart','TrendCharts','ECharts',NULL,NULL,'\0','\0','\0','',10026,'common',NULL,'\0',0,0,'2024-12-16 16:58:35',0,'2024-12-16 16:58:35'),(1868581666199744514,1868581475899977729,2,'/echarts/waterChart','waterChart','/echarts/waterChart/index',NULL,'Menu','水型图',NULL,NULL,'\0','\0','\0','',10027,'common',NULL,'\0',0,0,'2024-12-16 16:59:20',0,'2024-12-16 16:59:20'),(1868581870802087937,1868581475899977729,2,'/echarts/columnChart','columnChart','/echarts/columnChart/index',NULL,'Menu','柱状图',NULL,NULL,'\0','\0','\0','',10028,'common',NULL,'\0',0,0,'2024-12-16 17:00:09',0,'2024-12-16 17:00:09'),(1868582066080493570,1868581475899977729,2,'/echarts/lineChart','lineChart','/echarts/lineChart/index',NULL,'Menu','折线图',NULL,NULL,'\0','\0','\0','',10029,'common',NULL,'\0',0,0,'2024-12-16 17:00:56',0,'2024-12-16 17:00:56'),(1868582250629869569,1868581475899977729,2,'/echarts/pieChart','pieChart','/echarts/pieChart/index',NULL,'Menu','饼图',NULL,NULL,'\0','\0','\0','',10030,'common',NULL,'\0',0,0,'2024-12-16 17:01:40',0,'2024-12-16 17:01:40'),(1868582437716799490,1868581475899977729,2,'/echarts/radarChart','radarChart','/echarts/radarChart/index',NULL,'Menu','雷达图',NULL,NULL,'\0','\0','\0','',10031,'common',NULL,'\0',0,0,'2024-12-16 17:02:24',0,'2024-12-16 17:02:24'),(1868582683914055682,1868581475899977729,2,'/echarts/nestedChart','nestedChart','/echarts/nestedChart/index',NULL,'Menu','嵌套环形图',NULL,NULL,'\0','\0','\0','',10032,'common',NULL,'\0',0,0,'2024-12-16 17:03:23',0,'2024-12-16 17:03:23'),(1868583038110445569,0,1,'/directives','directives',NULL,'/directives/copyDirect','Stamp','自定义指令',NULL,NULL,'\0','\0','\0','',10033,'common',NULL,'\0',0,0,'2024-12-16 17:04:48',0,'2024-12-16 17:04:48'),(1868583215470784514,1868583038110445569,2,'/directives/copyDirect','copyDirect','/directives/copyDirect/index',NULL,'Menu','复制指令',NULL,NULL,'\0','\0','\0','',10034,'common',NULL,'\0',0,0,'2024-12-16 17:05:30',0,'2024-12-16 17:05:30'),(1868583401731436545,1868583038110445569,2,'/directives/watermarkDirect','watermarkDirect','/directives/watermarkDirect/index',NULL,'Menu','水印指令',NULL,NULL,'\0','\0','\0','',10035,'common',NULL,'\0',0,0,'2024-12-16 17:06:14',0,'2024-12-16 17:06:14'),(1868583648549449729,1868583038110445569,2,'/directives/dragDirect','dragDirect','/directives/dragDirect/index',NULL,'Menu','拖拽指令',NULL,NULL,'\0','\0','\0','',10036,'common',NULL,'\0',0,0,'2024-12-16 17:07:13',0,'2024-12-16 17:07:13'),(1868583835036594178,1868583038110445569,2,'/directives/debounceDirect','debounceDirect','/directives/debounceDirect/index',NULL,'Menu','防抖指令',NULL,NULL,'\0','\0','\0','',10037,'common',NULL,'\0',0,0,'2024-12-16 17:07:58',0,'2024-12-16 17:07:58'),(1868584017174245377,1868583038110445569,2,'/directives/throttleDirect','throttleDirect','/directives/throttleDirect/index',NULL,'Menu','节流指令',NULL,NULL,'\0','\0','\0','',10038,'common',NULL,'\0',0,0,'2024-12-16 17:08:41',0,'2024-12-16 17:08:41'),(1868584210057703426,1868583038110445569,2,'/directives/longpressDirect','longpressDirect','/directives/longpressDirect/index',NULL,'Menu','长按指令',NULL,NULL,'\0','\0','\0','',10039,'common',NULL,'\0',0,0,'2024-12-16 17:09:27',0,'2024-12-16 17:09:27'),(1868584793527332865,0,1,'/menu','menu',NULL,'/menu/menu1','List','菜单嵌套',NULL,NULL,'\0','\0','\0','',10040,'common',NULL,'\0',0,0,'2024-12-16 17:11:46',0,'2024-12-16 17:11:46'),(1868584969906204673,1868584793527332865,2,'/menu/menu1','menu1','/menu/menu1/index',NULL,'Menu','菜单1',NULL,NULL,'\0','\0','\0','',10041,'common',NULL,'\0',0,0,'2024-12-16 17:12:28',0,'2024-12-16 17:12:28'),(1868585285754073089,1868584793527332865,1,'/menu/menu2','menu2',NULL,'/menu/menu2/menu21','Menu','菜单2',NULL,NULL,'\0','\0','\0','',10042,'common',NULL,'\0',0,0,'2024-12-16 17:13:43',0,'2024-12-16 17:13:43'),(1868585552679579650,1868585285754073089,2,'/menu/menu2/menu21','menu21','/menu/menu2/menu21/index',NULL,'Menu','菜单2-1',NULL,NULL,'\0','\0','\0','',10043,'common',NULL,'\0',0,0,'2024-12-16 17:14:47',0,'2024-12-16 17:14:47'),(1868585823069581313,1868585285754073089,1,'/menu/menu2/menu22','menu22',NULL,'/menu/menu2/menu22/menu221','Menu','菜单2-2',NULL,NULL,'\0','\0','\0','',10044,'common',NULL,'\0',0,0,'2024-12-16 17:15:52',0,'2024-12-16 17:15:52'),(1868586117505527810,1868585823069581313,2,'/menu/menu2/menu22/menu221','menu221','/menu/menu2/menu22/menu221/index',NULL,'Menu','菜单2-2-1',NULL,NULL,'\0','\0','\0','',10045,'common',NULL,'\0',0,0,'2024-12-16 17:17:02',0,'2024-12-16 17:17:02'),(1868586306337288193,1868585823069581313,2,'/menu/menu2/menu22/menu222','menu222','/menu/menu2/menu22/menu222/index',NULL,'Menu','菜单2-2-2',NULL,NULL,'\0','\0','\0','',10046,'common',NULL,'\0',0,0,'2024-12-16 17:17:47',0,'2024-12-16 17:17:47'),(1868589485321924610,1868585285754073089,2,'/menu/menu2/menu23','menu23','/menu/menu2/menu23/index',NULL,'Menu','菜单2-3',NULL,NULL,'\0','\0','\0','',10047,'common',NULL,'\0',0,0,'2024-12-16 17:30:25',0,'2024-12-16 17:30:25'),(1868589739228311554,1868584793527332865,2,'/menu/menu3','menu3','/menu/menu3/index',NULL,'Menu','菜单3',NULL,NULL,'\0','\0','\0','',10048,'common',NULL,'\0',0,0,'2024-12-16 17:31:25',0,'2024-12-16 17:31:25'),(1868590215151792130,0,1,'/link','link',NULL,'/link/bing','Paperclip','外部链接',NULL,NULL,'\0','\0','\0','',10049,'common',NULL,'\0',0,0,'2024-12-16 17:33:19',0,'2024-12-16 17:33:19'),(1868590648545030145,1868590215151792130,2,'/link/bing','bing','/link/bing/index',NULL,'Menu','Bing 内嵌',NULL,NULL,'\0','\0','\0','',10050,'common',NULL,'\0',0,0,'2024-12-16 17:35:02',0,'2024-12-16 17:35:02'),(1868595671781912578,1868590215151792130,2,'/link/gitee','gitee','/link/gitee/index',NULL,'Menu','Gitee 仓库',NULL,'https://gitee.com/HalseySpicy/Geeker-Admin','\0','\0','\0','',10051,'common',NULL,'\0',0,0,'2024-12-16 17:55:00',0,'2024-12-16 17:55:00'),(1868596254236520449,1868590215151792130,2,'/link/docs','docs','/link/docs/index',NULL,'Menu','项目文档',NULL,'https://docs.spicyboy.cn','\0','\0','\0','',10052,'common',NULL,'\0',0,0,'2024-12-16 17:57:19',0,'2024-12-16 17:57:19'),(1868597578093080577,1868590215151792130,2,'/link/juejin','juejin','/link/juejin/index',NULL,'Menu','掘金主页',NULL,'https://juejin.cn/user/3263814531551816/posts','\0','\0','\0','',10053,'common',NULL,'\0',0,0,'2024-12-16 18:02:34',0,'2024-12-16 18:02:34'),(1868608489193254914,0,2,'/about/index','about','/about/index',NULL,'InfoFilled','关于项目',NULL,NULL,'\0','\0','\0','',10054,'common',NULL,'\0',0,0,'2024-12-16 18:45:56',0,'2024-12-16 18:45:56');
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_user` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_menu`
--

DROP TABLE IF EXISTS `sys_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_menu`
--

LOCK TABLES `sys_role_menu` WRITE;
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
INSERT INTO `sys_role_menu` VALUES (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,1,5),(6,1,6),(7,1,7),(8,1,8),(9,1,9);
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL,
  `nick_name` varchar(100) DEFAULT NULL COMMENT '用户昵称',
  `user_name` varchar(100) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `email` varchar(100) DEFAULT NULL COMMENT '电子邮箱',
  `mobile` varchar(11) DEFAULT NULL COMMENT '移动电话',
  `user_status` int(11) DEFAULT NULL COMMENT '用户状态',
  `create_user` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (0,'超管','super-admin','f6e0a1e2ac41945a9aa7ff8a8aaa0cebc12a3bcc981a929ad5cf810a090e11ae','super-admin@lu.com','18711111111',1,-1,'2024-08-09 16:10:32',-1,'2024-08-09 16:10:41','\0',0),(1867033801878994945,'测试账号','test','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','xxx@qq.com','123',1,0,'2024-12-12 10:28:41',0,'2024-12-12 10:28:41','\0',0);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` VALUES (1,1867033801878994945,1),(2,1867033801878994945,2);
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-16 18:46:35
