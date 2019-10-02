/*==============================================================*/
/* Table: t_book                                                */
/*==============================================================*/
create table t_book
(
   ID                   int(11) not null auto_increment comment '主键',
   BOOK_NAME            varchar(50) comment '图书名称',
   BOOK_INTRO           varchar(200) comment '简介',
   BOOK_PRICE           double comment '图书单价',
   PUB_ID               int(11) not null comment '出版社外键',
   TYPE_ID              int(11) not null comment '图书种类外键',
   IMAGE_URL            varchar(200) comment '缩略图url',
   AUTHOR               varchar(200) comment '作者',
   REPERTORY_SIZE       bigint(10) comment '库存量',
   primary key (ID)
);

alter table t_book comment '图书';

/*==============================================================*/
/* Index: PUB_ID_FK                                             */
/*==============================================================*/
create index PUB_ID_FK on t_book
(
   PUB_ID
);

/*==============================================================*/
/* Index: TYPE_ID_FK                                            */
/*==============================================================*/
create index TYPE_ID_FK on t_book
(
   TYPE_ID
);

/*==============================================================*/
/* Table: t_book_in_record                                      */
/*==============================================================*/
create table t_book_in_record
(
   ID                   int(11) not null auto_increment comment '主键',
   BOOK_ID              int(11) comment '图书',
   IN_RECORD_ID         int(11) comment '销售记录',
   IN_SUM               int(10) comment '入库数量',
   primary key (ID)
);

alter table t_book_in_record comment '图书入库记录';

/*==============================================================*/
/* Index: BOOK_ID_FK                                            */
/*==============================================================*/
create index BOOK_ID_FK on t_book_in_record
(
   BOOK_ID
);

/*==============================================================*/
/* Index: T_IN_RECORD_ID_FK                                     */
/*==============================================================*/
create index T_IN_RECORD_ID_FK on t_book_in_record
(
   IN_RECORD_ID
);

/*==============================================================*/
/* Table: t_book_sale_record                                    */
/*==============================================================*/
create table t_book_sale_record
(
   ID                   int(11) not null auto_increment comment '主键',
   BOOK_ID              int(11) comment '图书',
   SALE_RECORD_ID       int(11) comment '图书销售记录',
   TRADE_SUM            int(10) comment '销售数量',
   primary key (ID)
);

alter table t_book_sale_record comment '图书销售记录';

/*==============================================================*/
/* Index: BOOK_ID_FK                                            */
/*==============================================================*/
create index BOOK_ID_FK on t_book_sale_record
(
   BOOK_ID
);

/*==============================================================*/
/* Index: T_SALE_RECORD_ID_FK                                   */
/*==============================================================*/
create index T_SALE_RECORD_ID_FK on t_book_sale_record
(
   SALE_RECORD_ID
);

/*==============================================================*/
/* Table: t_book_type                                           */
/*==============================================================*/
create table t_book_type
(
   ID                   int(11) not null auto_increment comment '主键',
   TYPE_NAME            varchar(50) comment '名称',
   TYPE_INTRO           varchar(200) comment '简介',
   primary key (ID)
);

alter table t_book_type comment '图书种类';

/*==============================================================*/
/* Table: t_in_record                                           */
/*==============================================================*/
create table t_in_record
(
   ID                   int(11) not null auto_increment comment '主键',
   RECORD_DATE          datetime comment '入库日期',
   primary key (ID)
);

alter table t_in_record comment '入库记录';

/*==============================================================*/
/* Table: t_publisher                                           */
/*==============================================================*/
create table t_publisher
(
   ID                   int(11) not null auto_increment comment '主键',
   PUB_NAME             varchar(50) comment '出版社名称',
   PUB_TEL              varchar(50) comment '出版社电话',
   PUB_LINK_MAN         varchar(50) comment '联系人',
   PUB_INTRO            varchar(200) comment '简介',
   primary key (ID)
);

alter table t_publisher comment '出版社';

/*==============================================================*/
/* Table: t_sale_record                                         */
/*==============================================================*/
create table t_sale_record
(
   ID                   int(11) not null auto_increment comment '主键',
   RECORD_DATE          datetime comment '销售日期',
   primary key (ID)
);

alter table t_sale_record comment '销售记录';

/*==============================================================*/
/* Table: t_user                                                */
/*==============================================================*/
create table t_user
(
   ID                   int(11) not null auto_increment comment '主键',
   USER_NAME            varchar(20) comment '用户名',
   USER_PASSWORD        varchar(20) comment '密码',
   primary key (ID)
);

alter table t_user comment '用户';

alter table t_book add constraint FK_fk_book_2_publisher foreign key (PUB_ID)
      references t_publisher (ID) on delete restrict on update restrict;

alter table t_book add constraint FK_fk_book_2_type foreign key (TYPE_ID)
      references t_book_type (ID) on delete restrict on update restrict;

alter table t_book_in_record add constraint FK_fk_book_in_record_2_book foreign key (BOOK_ID)
      references t_book (ID) on delete restrict on update restrict;

alter table t_book_in_record add constraint FK_fk_book_in_record_2_in_record foreign key (IN_RECORD_ID)
      references t_in_record (ID) on delete restrict on update restrict;

alter table t_book_sale_record add constraint FK_fk_book_sale_record_2_book foreign key (BOOK_ID)
      references t_book (ID) on delete restrict on update restrict;

alter table t_book_sale_record add constraint FK_fk_book_sale_record_2_sale_record foreign key (SALE_RECORD_ID)
      references t_sale_record (ID) on delete restrict on update restrict;