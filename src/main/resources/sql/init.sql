/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/4/24 13:31:18                           */
/*==============================================================*/


drop table if exists sys_auth;

drop table if exists sys_auth_module;

drop table if exists sys_dept;

drop table if exists sys_log;

drop table if exists sys_role;

drop table if exists sys_role_auth;

drop table if exists sys_role_user;

drop table if exists sys_user;

/*==============================================================*/
/* Table: sys_auth                                              */
/*==============================================================*/
create table sys_auth
(
   id                   int(11) not null auto_increment comment '系统权限ID',
   code                 varchar(20) not null comment '权限码',
   name                 varchar(20) not null comment '权限名称',
   url                  varchar(100) not null comment '请求的url, 可以填正则表达式',
   type                 int(11) not null default 3 comment '类型，1：菜单，2：按钮，3：其他',
   status               int(11) not null default 1 comment '状态，1：正常，0：冻结',
   seq                  int(11) not null comment '权限在当前模块下的顺序，由小到大',
   remark               varchar(200) comment '备注',
   operator             varchar(20) not null comment '操作者',
   operate_time         datetime not null comment '最后一次更新时间',
   operate_ip           varchar(20) not null comment '最后一个更新者的ip地址',
   auth_module_id       varchar(20) not null comment '权限模块ID',
   primary key (id)
)
auto_increment = 100
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

alter table sys_auth comment '系统权限';

INSERT INTO `sys_auth` VALUES ('1', '20171015095130_26', '进入产品管理界面', '/sys/product/product.page', '1', '1', '1', '', 'Admin', '2017-10-15 09:51:30', '0:0:0:0:0:0:0:1', '1'), ('2', '20171015095322_14', '查询产品列表', '/sys/product/page.json', '2', '1', '2', '', 'Admin', '2017-10-15 09:53:22', '0:0:0:0:0:0:0:1', '1'), ('3', '20171015095350_69', '产品上架', '/sys/product/online.json', '2', '1', '3', '', 'Admin', '2017-10-15 09:53:51', '0:0:0:0:0:0:0:1', '1'), ('4', '20171015095420_7', '产品下架', '/sys/product/offline.json', '2', '1', '4', '', 'Admin', '2017-10-15 10:11:28', '0:0:0:0:0:0:0:1', '1'), ('5', '20171015212626_63', '进入订单页', '/sys/order/order.page', '1', '1', '1', '', 'Admin', '2017-10-15 21:26:27', '0:0:0:0:0:0:0:1', '2'), ('6', '20171015212657_12', '查询订单列表', '/sys/order/list.json', '2', '1', '2', '', 'Admin', '2017-10-15 21:26:57', '0:0:0:0:0:0:0:1', '2'), ('7', '20171015212907_36', '进入权限管理页', '/sys/authModule/auth.page', '1', '1', '1', '', 'Admin', '2017-10-15 21:29:07', '0:0:0:0:0:0:0:1', '7'), ('8', '20171015212938_27', '进入角色管理页', '/sys/role/role.page', '1', '1', '1', '', 'Admin', '2017-10-16 17:49:38', '0:0:0:0:0:0:0:1', '8'), ('9', '20171015213009_0', '进入用户管理页', '/sys/dept/dept.page', '1', '1', '1', '', 'Admin', '2017-10-15 21:30:09', '0:0:0:0:0:0:0:1', '9'), ('10', '20171016230429_8', '进入权限更新记录页面', '/sys/log/log.page', '1', '1', '1', '', 'Admin', '2017-10-16 23:04:49', '0:0:0:0:0:0:0:1', '11');

/*==============================================================*/
/* Table: sys_auth_module                                       */
/*==============================================================*/
create table sys_auth_module
(
   id                   int(11) not null auto_increment comment '权限模块id',
   name                 varchar(20) not null comment '权限模块名称',
   parent_id            int(11) not null comment '上级权限模块id',
   level                varchar(200) not null comment '权限模块层级',
   seq                  int(11) not null comment '权限模块在当前层级下的顺序，由小到大',
   status               int(11) not null default 1 comment '状态，1：正常，0：冻结',
   remark               varchar(200) not null comment '备注',
   operator             varchar(20) not null comment '操作者',
   operate_time         datetime not null comment '最后一次操作时间',
   operate_ip           varchar(20) not null comment '最后一次更新操作者的ip地址',
   primary key (id)
)
auto_increment = 100
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

INSERT INTO `sys_auth_module` VALUES ('1', '产品管理', '0', '0', '1', '1', 'product', 'Admin', '2017-10-14 21:13:15', '0:0:0:0:0:0:0:1'), ('2', '订单管理', '0', '0', '2', '1', '', 'Admin', '2017-10-14 20:17:11', '0:0:0:0:0:0:0:1'), ('3', '公告管理', '0', '0', '3', '1', '', 'Admin', '2017-10-14 20:17:21', '0:0:0:0:0:0:0:1'), ('4', '出售中产品管理', '1', '0.1', '1', '1', '', 'Admin', '2017-10-14 21:13:39', '0:0:0:0:0:0:0:1'), ('5', '下架产品管理', '1', '0.1', '2', '1', '', 'Admin', '2017-10-14 20:18:02', '0:0:0:0:0:0:0:1'), ('6', '权限管理', '0', '0', '4', '1', '', 'Admin', '2017-10-15 21:27:37', '0:0:0:0:0:0:0:1'), ('7', '权限管理', '6', '0.6', '1', '1', '', 'Admin', '2017-10-15 21:27:57', '0:0:0:0:0:0:0:1'), ('8', '角色管理', '6', '0.6', '2', '1', '', 'Admin', '2017-10-15 21:28:22', '0:0:0:0:0:0:0:1'), ('9', '用户管理', '6', '0.6', '2', '1', '', 'Admin', '2017-10-15 21:28:36', '0:0:0:0:0:0:0:1'), ('10', '运维管理', '0', '0', '6', '1', '', 'Admin', '2017-10-16 23:03:37', '0:0:0:0:0:0:0:1'), ('11', '权限更新记录管理', '6', '0.6', '4', '1', '', 'Admin', '2017-10-16 23:04:07', '0:0:0:0:0:0:0:1');

/*==============================================================*/
/* Table: sys_dept                                              */
/*==============================================================*/
create table sys_dept
(
   id                   int(11) not null auto_increment comment '部门id',
   name                 varchar(20) not null comment '部门名称',
   parent_id            int(11) not null comment '上级部门id',
   level                varchar(200) not null comment '部门层级',
   seq                  int(11) not null comment '部门在当前层级下的顺序，由小到大',
   remark               varchar(200) comment '备注',
   operator             varchar(20) not null comment '操作者',
   operate_time         datetime not null comment '最后一次操作时间',
   operate_ip           varchar(20) not null comment '最后一次更新操作者的ip地址',
   primary key (id)
)
auto_increment = 100
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

alter table sys_dept comment '系统部门';

INSERT INTO `sys_dept` VALUES ('1', '技术部', '0', '0', '1', '技术部', 'system', '2017-10-11 07:21:40', '127.0.0.1'), ('2', '后端开发', '1', '0.1', '1', '后端', 'system-update', '2017-10-12 07:56:16', '127.0.0.1'), ('3', '前端开发', '1', '0.1', '2', '', 'system-update', '2017-10-14 11:29:45', '127.0.0.1'), ('4', 'UI设计', '1', '0.1', '3', '', 'system', '2017-10-12 07:55:43', '127.0.0.1'), ('11', '产品部', '0', '0', '2', '', 'Admin', '2017-10-16 22:52:29', '0:0:0:0:0:0:0:1'), ('12', '客服部', '0', '0', '4', '', 'Admin', '2017-10-17 00:22:55', '0:0:0:0:0:0:0:1');

/*==============================================================*/
/* Table: sys_log                                               */
/*==============================================================*/
create table sys_log
(
   id                   int(11) not null auto_increment comment '日志ID',
   type                 int(11) comment '权限更新的类型，1：部门，2：用户，3：权限模块，4：权限，5：角色，6：角色用户关系，7：角色权限关系',
   target_id            varchar(20) comment '基于type后指定的对象id，比如用户、权限、角色等表的主键',
   old_value            text comment '旧值',
   new_value            text comment '新值',
   status               int(11) not null default 0 comment '当前是否复原过，0：没有，1：复原过',
   remark               varchar(200) not null comment '备注',
   operator             varchar(20) not null comment '操作者',
   operate_time         datetime not null comment '最后一次更新的时间',
   operate_ip           varchar(20) not null comment '最后一次更新者的ip地址',
   Column_10            char(10),
   primary key (id)
)
auto_increment = 100
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

alter table sys_log comment '系统日志';

/*==============================================================*/
/* Table: sys_role                                              */
/*==============================================================*/
create table sys_role
(
   id                   int(11) not null auto_increment comment '角色ID',
   name                 varchar(20) not null comment '角色名称',
   type                 int(11) not null default 1 comment '角色的类型，1：管理员角色，2：其他',
   status               int(11) not null comment '状态，1：可用，0：冻结',
   remark               varchar(200) comment '备注',
   operator             varchar(20) not null comment '操作者',
   operate_time         datetime not null comment '最后一次更新的时间',
   operate_ip           varchar(20) not null comment '最后一次更新者的ip地址',
   primary key (id)
)
auto_increment = 100
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

alter table sys_role comment '系统角色';

INSERT INTO `sys_role` VALUES ('1', '产品管理员', '1', '1', '', 'Admin', '2017-10-15 12:42:47', '0:0:0:0:0:0:0:1'), ('2', '订单管理员', '1', '1', '', 'Admin', '2017-10-15 12:18:59', '0:0:0:0:0:0:0:1'), ('3', '公告管理员', '1', '1', '', 'Admin', '2017-10-15 12:19:10', '0:0:0:0:0:0:0:1'), ('4', '权限管理员', '1', '1', '', 'Admin', '2017-10-15 21:30:36', '0:0:0:0:0:0:0:1'), ('5', '运维管理员', '1', '1', '运维', 'Admin', '2017-10-17 00:23:28', '0:0:0:0:0:0:0:1');

/*==============================================================*/
/* Table: sys_role_auth                                         */
/*==============================================================*/
create table sys_role_auth
(
   id                   int(11) not null auto_increment comment '系统角色权限ID',
   role_id              int(11) not null comment '角色id',
   auth_id              int(11) not null comment '权限id',
   remark               varchar(200) comment '备注',
   operator             varchar(20) not null comment '操作者',
   operate_time         datetime not null comment '最后一次更新的时间',
   operate_ip           varchar(20) not null comment '最后一次更新者的ip',
   primary key (id)
)
auto_increment = 100
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

alter table sys_role_auth comment '角色权限映射';

INSERT INTO `sys_role_auth` VALUES ('9', '4', '7','', 'Admin', '2017-10-16 23:34:39', '0:0:0:0:0:0:0:1'), ('10', '4', '8','', 'Admin', '2017-10-16 23:34:39', '0:0:0:0:0:0:0:1'), ('11', '4', '9','', 'Admin', '2017-10-16 23:34:39', '0:0:0:0:0:0:0:1'), ('12', '4', '10','', 'Admin', '2017-10-16 23:34:39', '0:0:0:0:0:0:0:1');

/*==============================================================*/
/* Table: sys_role_user                                         */
/*==============================================================*/
create table sys_role_user
(
   id                   int(11) not null auto_increment comment '系统角色用户关联ID',
   role_id              int(11) not null comment '角色id',
   user_id              int(11) not null comment '用户id',
   remark               varchar(200) comment '备注',
   operator             varchar(20) not null comment '操作者',
   operate_time         datetime not null comment '最后一次更新的时间',
   operate_ip           varchar(20) not null comment '最后一次更新者的ip地址',
   primary key (id)
)
auto_increment = 100
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

alter table sys_role_user comment '系统角色用户关联表';

INSERT INTO `sys_role_user` VALUES ('16', '4', '1','', 'Admin', '2017-10-17 00:24:04', '0:0:0:0:0:0:0:1'), ('17', '4', '4','', 'Admin', '2017-10-17 00:24:04', '0:0:0:0:0:0:0:1');

/*==============================================================*/
/* Table: sys_user                                              */
/*==============================================================*/
create table sys_user
(
   id                   int(11) not null auto_increment comment '用户id',
   username             varchar(20) not null comment '用户名称',
   telephone            varchar(13) not null comment '手机号',
   mail                 varchar(20) not null comment '邮箱',
   password             varchar(40) not null comment '加密后的密码',
   status               int(11) not null default 1 comment '状态，1：正常，0：冻结状态，2：删除',
   remark               varchar(200) comment '备注',
   operator             varchar(20) not null comment '操作者',
   operate_time         datetime not null comment '最后一次更新时间',
   operate_ip           varchar(20) not null comment '最后一次更新者的ip地址',
   dept_id              int(11) not null comment '用户所在部门的id',
   primary key (id)
)
auto_increment = 6
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

alter table sys_user comment '系统用户信息';

INSERT INTO `sys_user` VALUES ('1', 'Admin', '18612344321', 'admin@qq.com', '25D55AD283AA400AF464C76D713C07AD', '1', 'admin', 'system', '2017-10-13 08:46:16', '127.0.0.1', '1'), ('2', 'Jimin', '13188889999', 'jimin@qq.com', '25D55AD283AA400AF464C76D713C07AD', '1', 'jimin.zheng', 'Admin', '2017-10-14 14:45:19', '127.0.0.1', '1'), ('3', 'Jimmy', '13812344311', 'jimmy@qq.com', '25D55AD283AA400AF464C76D713C07AD', '1', '', 'Admin', '2017-10-16 12:57:35', '0:0:0:0:0:0:0:1', '2'), ('4', 'Kate', '13144445555', 'kate@qq.com', '25D55AD283AA400AF464C76D713C07AD', '1', 'sss', 'Admin', '2017-10-16 23:02:51', '0:0:0:0:0:0:0:1', '1'), ('5', '服务员A', '18677778888', 'service@qq.com', '25D55AD283AA400AF464C76D713C07AD', '1', '', 'Admin', '2017-10-17 00:22:15', '0:0:0:0:0:0:0:1', '12');

