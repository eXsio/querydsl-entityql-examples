create table BOOKS (BOOK_ID bigint not null, DESC CLOB, NAME varchar(255), PRICE decimal(19,2), primary key (BOOK_ID))
create table GROUP_ADMINS (GA_ID bigint not null, NAME varchar(255), primary key (GA_ID))
create table GROUPS (GROUP_ID bigint not null, NAME varchar(255), ADMIN_NAME varchar(255) not null, primary key (GROUP_ID))
create table ORDER_ITEMS (ORDER_ITEM_ID bigint not null, QTY bigint not null, BOOK_ID bigint not null, ITEM_ORDER_ID bigint not null, primary key (ORDER_ITEM_ID))
create table ORDERS (ORDER_ID bigint not null, USER_ID bigint not null, primary key (ORDER_ID))
create table UPLOADED_FILES (FILE_ID UUID not null, DATA blob not null, primary key (FILE_ID))
create table USERS (USER_ID bigint not null, CREATED_AT date, CREATED_BY varchar(15), NAME varchar(255), TYPE_ORD integer not null, TYPE_STR varchar(255) not null, primary key (USER_ID))
alter table BOOKS add constraint UK_7gooj308e6bk65w0b0xm23n unique (NAME)
alter table GROUP_ADMINS add constraint UK_5007lbbghjt3gq4dat9eu9dm3 unique (NAME)
alter table ORDER_ITEMS add constraint unique_book_for_order unique (BOOK_ID, ITEM_ORDER_ID)
alter table GROUPS add constraint FKes4adobuhul819m845r1d9cks foreign key (ADMIN_NAME) references GROUP_ADMINS (NAME)
alter table ORDER_ITEMS add constraint FKk620hy0ooxhn0vi9xxwmbr4eb foreign key (BOOK_ID) references BOOKS
alter table ORDER_ITEMS add constraint FKoh2ubdnrh7rf2u1q12euo8txn foreign key (ITEM_ORDER_ID) references ORDERS
alter table ORDERS add constraint FK6jhqv9srg8s7x7ycrce7oxuur foreign key (USER_ID) references USERS


INSERT INTO GROUP_ADMINS (GA_ID, NAME) VALUES (1, 'GA1')
INSERT INTO GROUP_ADMINS (GA_ID, NAME) VALUES (2, 'GA2')
INSERT INTO GROUP_ADMINS (GA_ID, NAME) VALUES (3, 'GA3')

INSERT INTO GROUPS (GROUP_ID, NAME, ADMIN_NAME) VALUES (1, 'G1', 'GA1')
INSERT INTO GROUPS (GROUP_ID, NAME, ADMIN_NAME) VALUES (2, 'G2', 'GA2')
INSERT INTO GROUPS (GROUP_ID, NAME, ADMIN_NAME) VALUES (3, 'G3', 'GA3')

INSERT INTO USERS (USER_ID, NAME, TYPE_STR, TYPE_ORD, CREATED_BY, CREATED_AT) VALUES (1, 'U1', 'ADMIN', 0, 'ADMIN', now())
INSERT INTO USERS (USER_ID, NAME, TYPE_STR, TYPE_ORD, CREATED_BY, CREATED_AT) VALUES (2, 'U2', 'CLIENT', 1, 'ADMIN', now())
INSERT INTO USERS (USER_ID, NAME, TYPE_STR, TYPE_ORD, CREATED_BY, CREATED_AT) VALUES (3, 'U3', 'CLIENT', 1, 'ADMIN', now())

INSERT INTO ORDERS (ORDER_ID, USER_ID) VALUES (1, 1)
INSERT INTO ORDERS (ORDER_ID, USER_ID) VALUES (2, 2)
INSERT INTO ORDERS (ORDER_ID, USER_ID) VALUES (3, 3)

INSERT INTO BOOKS (BOOK_ID, NAME, DESC, PRICE) VALUES (1, 'B1', 'Very long Book description 1', 11.11)
INSERT INTO BOOKS (BOOK_ID, NAME, DESC, PRICE) VALUES (2, 'B2', 'Very long Book description 2', 22.22)
INSERT INTO BOOKS (BOOK_ID, NAME, DESC, PRICE) VALUES (3, 'B3', 'Very long Book description 3', 33.33)
INSERT INTO BOOKS (BOOK_ID, NAME, DESC, PRICE) VALUES (4, 'B4', 'Very long Book description 4', 44.44)
INSERT INTO BOOKS (BOOK_ID, NAME, DESC, PRICE) VALUES (5, 'B5', 'Very long Book description 5', 55.55)
INSERT INTO BOOKS (BOOK_ID, NAME, DESC, PRICE) VALUES (6, 'B6', 'Very long Book description 6', 66.66)
INSERT INTO BOOKS (BOOK_ID, NAME, DESC, PRICE) VALUES (7, 'B7', 'Very long Book description 7', 77.77)
INSERT INTO BOOKS (BOOK_ID, NAME, DESC, PRICE) VALUES (8, 'B8', 'Very long Book description 8', 88.88)
INSERT INTO BOOKS (BOOK_ID, NAME, DESC, PRICE) VALUES (9, 'B9', 'Very long Book description 9', 99.99)

INSERT INTO ORDER_ITEMS (ORDER_ITEM_ID, BOOK_ID, ITEM_ORDER_ID, QTY) VALUES (1, 1, 1, 1)
INSERT INTO ORDER_ITEMS (ORDER_ITEM_ID, BOOK_ID, ITEM_ORDER_ID, QTY) VALUES (2, 2, 1, 2)
INSERT INTO ORDER_ITEMS (ORDER_ITEM_ID, BOOK_ID, ITEM_ORDER_ID, QTY) VALUES (3, 5, 1, 5)

INSERT INTO ORDER_ITEMS (ORDER_ITEM_ID, BOOK_ID, ITEM_ORDER_ID, QTY) VALUES (4, 1, 2, 1)
INSERT INTO ORDER_ITEMS (ORDER_ITEM_ID, BOOK_ID, ITEM_ORDER_ID, QTY) VALUES (5, 3, 2, 3)
INSERT INTO ORDER_ITEMS (ORDER_ITEM_ID, BOOK_ID, ITEM_ORDER_ID, QTY) VALUES (6, 7, 2, 7)
INSERT INTO ORDER_ITEMS (ORDER_ITEM_ID, BOOK_ID, ITEM_ORDER_ID, QTY) VALUES (7, 9, 2, 9)
INSERT INTO ORDER_ITEMS (ORDER_ITEM_ID, BOOK_ID, ITEM_ORDER_ID, QTY) VALUES (8, 5, 2, 5)

INSERT INTO ORDER_ITEMS (ORDER_ITEM_ID, BOOK_ID, ITEM_ORDER_ID, QTY) VALUES (9, 8, 3, 8)
INSERT INTO ORDER_ITEMS (ORDER_ITEM_ID, BOOK_ID, ITEM_ORDER_ID, QTY) VALUES (10, 5, 3, 5)