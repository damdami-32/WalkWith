
DROP SEQUENCE sequence_;

DROP TABLE LikeList CASCADE CONSTRAINTS PURGE;

DROP TABLE StoreCategory CASCADE CONSTRAINTS PURGE;

DROP TABLE Category CASCADE CONSTRAINTS PURGE;

DROP TABLE Reservation CASCADE CONSTRAINTS PURGE;

DROP TABLE Pet CASCADE CONSTRAINTS PURGE;

DROP TABLE Menu CASCADE CONSTRAINTS PURGE;

DROP TABLE Review CASCADE CONSTRAINTS PURGE;

DROP TABLE Store CASCADE CONSTRAINTS PURGE;

DROP TABLE Seller CASCADE CONSTRAINTS PURGE;

DROP TABLE Customer CASCADE CONSTRAINTS PURGE;

CREATE TABLE Category
(
	categoryId           INTEGER  NOT NULL ,
	caName               VARCHAR2(200)  NULL ,
	storeCount           INTEGER  NULL 
);

CREATE UNIQUE INDEX XPKCategory ON Category
(categoryId   ASC);

ALTER TABLE Category
	ADD CONSTRAINT  XPKCategory PRIMARY KEY (categoryId);

CREATE TABLE Customer
(
	userId               VARCHAR2(20)  NOT NULL ,
	uName                VARCHAR2(200)  NOT NULL ,
	uPassword            VARCHAR2(200)  NOT NULL ,
	uPhone               VARCHAR2(20)  NOT NULL ,
	uMail                VARCHAR2(300)  NOT NULL 
);

CREATE UNIQUE INDEX XPKUser ON Customer
(userId   ASC);

ALTER TABLE Customer
	ADD CONSTRAINT  XPKUser PRIMARY KEY (userId);

CREATE TABLE Pet
(
	petId                INTEGER  NOT NULL ,
	pName                VARCHAR2(200)  NULL ,
	pAge                 INTEGER  NULL ,
	pCategory            VARCHAR2(200)  NULL ,
	pDetailCategory      VARCHAR2(200)  NULL ,
	userId               VARCHAR2(20)  NOT NULL ,
	pNeureting           INTEGER  NULL ,
	pImage               VARCHAR2(500)  NULL 
);

CREATE UNIQUE INDEX XPKPet ON Pet
(petId   ASC,userId   ASC);

ALTER TABLE Pet
	ADD CONSTRAINT  XPKPet PRIMARY KEY (petId,userId);

CREATE TABLE Seller
(
	sellerId             VARCHAR2(20)  NOT NULL ,
	seName               VARCHAR2(200)  NOT NULL ,
	sePassword           VARCHAR2(200)  NOT NULL ,
	sePhone              VARCHAR2(20)  NOT NULL ,
	seMail               VARCHAR2(300)  NOT NULL 
);

CREATE UNIQUE INDEX XPKSeller ON Seller
(sellerId   ASC);

ALTER TABLE Seller
	ADD CONSTRAINT  XPKSeller PRIMARY KEY (sellerId);

CREATE TABLE Store
(
	storeId              INTEGER  NOT NULL ,
	sName                VARCHAR2(200)  NOT NULL ,
	sPhone               VARCHAR2(20)  NOT NULL ,
	sTime                DATE  NOT NULL ,
	sStarScore           FLOAT  NULL ,
	sDetailDescription   VARCHAR2(1000)  NULL ,
	sellerId             VARCHAR2(20)  NOT NULL ,
	openDate             TIMESTAMP  NOT NULL ,
	likeCount            CHAR(18)  NULL 
);

CREATE UNIQUE INDEX XPKStore ON Store
(storeId   ASC);

ALTER TABLE Store
	ADD CONSTRAINT  XPKStore PRIMARY KEY (storeId);

CREATE TABLE LikeList
(
	userId               VARCHAR2(20)  NOT NULL ,
	storeId              INTEGER  NOT NULL 
);

CREATE UNIQUE INDEX XPKLikeList ON LikeList
(userId   ASC,storeId   ASC);

ALTER TABLE LikeList
	ADD CONSTRAINT  XPKLikeList PRIMARY KEY (userId,storeId);

CREATE TABLE StoreCategory
(
	categoryId           INTEGER  NOT NULL ,
	storeId              INTEGER  NOT NULL 
);

CREATE UNIQUE INDEX XPKStoreCategory ON StoreCategory
(categoryId   ASC,storeId   ASC);

ALTER TABLE StoreCategory
	ADD CONSTRAINT  XPKStoreCategory PRIMARY KEY (categoryId,storeId);

CREATE TABLE Reservation
(
	reservationId        INTEGER  NOT NULL ,
	resDaTi              DATE  NOT NULL ,
	userId               VARCHAR2(20)  NOT NULL ,
	storeId              INTEGER  NOT NULL 
);

CREATE UNIQUE INDEX XPKReservation ON Reservation
(reservationId   ASC);

ALTER TABLE Reservation
	ADD CONSTRAINT  XPKReservation PRIMARY KEY (reservationId);

CREATE TABLE Menu
(
	menuId               INTEGER  NOT NULL ,
	menuName             VARCHAR2(200)  NULL ,
	menuDescrip          VARCHAR2(500)  NULL ,
	mePrice              INTEGER  NULL ,
	storeId              INTEGER  NOT NULL 
);

CREATE UNIQUE INDEX XPKMenu ON Menu
(menuId   ASC);

ALTER TABLE Menu
	ADD CONSTRAINT  XPKMenu PRIMARY KEY (menuId);

CREATE TABLE Review
(
	reviewId             INTEGER  NOT NULL ,
	reContent            VARCHAR2(1000)  NULL ,
	starScore            INTEGER  NULL ,
	userId               VARCHAR2(20)  NOT NULL ,
	storeId              INTEGER  NOT NULL 
);

CREATE UNIQUE INDEX XPKReview ON Review
(reviewId   ASC);

ALTER TABLE Review
	ADD CONSTRAINT  XPKReview PRIMARY KEY (reviewId);

ALTER TABLE Pet
	ADD (
CONSTRAINT R_1 FOREIGN KEY (userId) REFERENCES Customer (userId));

ALTER TABLE Store
	ADD (
CONSTRAINT R_3 FOREIGN KEY (sellerId) REFERENCES Seller (sellerId));

ALTER TABLE LikeList
	ADD (
CONSTRAINT R_15 FOREIGN KEY (userId) REFERENCES Customer (userId));

ALTER TABLE LikeList
	ADD (
CONSTRAINT R_16 FOREIGN KEY (storeId) REFERENCES Store (storeId));

ALTER TABLE StoreCategory
	ADD (
CONSTRAINT R_8 FOREIGN KEY (categoryId) REFERENCES Category (categoryId));

ALTER TABLE StoreCategory
	ADD (
CONSTRAINT R_9 FOREIGN KEY (storeId) REFERENCES Store (storeId));

ALTER TABLE Reservation
	ADD (
CONSTRAINT R_2 FOREIGN KEY (userId) REFERENCES Customer (userId));

ALTER TABLE Reservation
	ADD (
CONSTRAINT R_11 FOREIGN KEY (storeId) REFERENCES Store (storeId));

ALTER TABLE Menu
	ADD (
CONSTRAINT R_6 FOREIGN KEY (storeId) REFERENCES Store (storeId));

ALTER TABLE Review
	ADD (
CONSTRAINT R_10 FOREIGN KEY (userId) REFERENCES Customer (userId));

ALTER TABLE Review
	ADD (
CONSTRAINT R_14 FOREIGN KEY (storeId) REFERENCES Store (storeId));





-- Category 테이블에 데이터 삽입
INSERT INTO Category VALUES(1, '병원', 10);
INSERT INTO Category VALUES(2, '음식점', 15);
INSERT INTO Category VALUES(3, '카페', 5);

-- Customer 테이블에 데이터 삽입
INSERT INTO Customer VALUES('user1', '홍길동', 'password1', '010-1234-5678', 'user1@example.com');
INSERT INTO Customer VALUES('user2', '이순신', 'password2', '010-2345-6789', 'user2@example.com');
INSERT INTO Customer VALUES('user3', '유관순', 'password3', '010-3456-7890', 'user3@example.com');

-- Pet 테이블에 데이터 삽입
INSERT INTO Pet VALUES(1, 'Max', 3, 'Dog', 'Poodle', 'user1', 1, NULL);
INSERT INTO Pet VALUES(2, 'Bella', 2, 'Cat', 'Persian', 'user2', 0, NULL);
INSERT INTO Pet VALUES(3, 'Charlie', 1, 'Bird', 'Parrot', 'user3', 1, NULL);

-- Seller 테이블에 데이터 삽입
INSERT INTO Seller VALUES('seller1', '사장님1', 'password1', '010-4567-8901', 'seller1@example.com');
INSERT INTO Seller VALUES('seller2', '사장님2', 'password2', '010-5678-9012', 'seller2@example.com');
INSERT INTO Seller VALUES('seller3', '사장님3', 'password3', '010-6789-0123', 'seller3@example.com');

-- Store 테이블에 데이터 삽입
INSERT INTO Store VALUES(1, 'Store1', '02-123-4567', TO_DATE('09:00:00', 'HH24:MI:SS'), 4.5, '최고의 가게1', 'seller1', CURRENT_TIMESTAMP, '100');
INSERT INTO Store VALUES(2, 'Store2', '02-234-5678', TO_DATE('10:00:00', 'HH24:MI:SS'), 4.3, '최고의 가게2', 'seller2', CURRENT_TIMESTAMP, '200');
INSERT INTO Store VALUES(3, 'Store3', '02-345-6789', TO_DATE('11:00:00', 'HH24:MI:SS'), 4.7, '최고의 가게3', 'seller3', CURRENT_TIMESTAMP, '300');

