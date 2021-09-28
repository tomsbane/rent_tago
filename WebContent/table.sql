drop table customer;
create table customer (
customer_no int(5) not null primary key,
customer_name nvarchar (20) not null,
customer_pw nvarchar (20) not null,
gender char(1) not null,
birth datetime not null,
tel int (9) not null,
address nvarchar(200),
grade char(1),
bonuspoint int(10),
joindate datetime not null
);



drop table rentcar;
create table rentcar (
car_no int (7) auto_increment not null primary key,
car_name nvarchar(20),
car_group char(1),
car_year int(4),
car_reserve char(1),
car_price int(10),
car_brand nvarchar(15),
car_img1 varchar(20),
car_img2 varchar(20),
car_readCount int
);

alter table rentcar
add foreign key (customer_no) references customer(customer_no);

alter table rentcar
add foreign key (car_no) references car(car_no);


drop table car;



drop table review;
create table review (
review_no int(10),
car_no int(7),
review_name nvarchar(45),
review_contents nvarchar(150),
review_level char(1)
);

alter table review
add foreign key (car_no) references rentcar(car_no);


drop table cust_service;
create table cust_service (
customer_no int(5),
board_no int(5),
board_group char(1),
review_name nvarchar(45),
board_contents nvarchar(500),
anwser nvarchar(500)
);

alter table cust_service
add foreign key (customer_no) references customer(customer_no);


drop table coupon_create;
create table coupon_create (
coupon_no int(3),
coupon_name nvarchar(20),
customer_no int(5),
coupon_expiry datetime,
coupon_used char(1)
);

alter table coupon_create
add foreign key (customer_no) references customer(customer_no);
alter table coupon_create
add foreign key (coupon_name) references coupon_info(coupon_name);

drop table coupon_info;
create table coupon_info (
coupon_name nvarchar(20),
coupon_discount int(5),
coupon_discount_per int(5)
);