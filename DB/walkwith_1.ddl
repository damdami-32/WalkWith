
DROP SEQUENCE sequence_;

CREATE SEQUENCE sequence_
	INCREMENT BY 1
	START WITH 100;

DROP TABLE RecommendStore CASCADE CONSTRAINTS PURGE;

DROP TABLE SearchCategory CASCADE CONSTRAINTS PURGE;

DROP TABLE SearchStore CASCADE CONSTRAINTS PURGE;

DROP TABLE PopularStore CASCADE CONSTRAINTS PURGE;

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

CREATE TABLE SearchCategory
(
	searchcId            INTEGER  NOT NULL ,
	categoryId           INTEGER  NOT NULL ,
	userId               VARCHAR2(20)  NOT NULL 
);

CREATE UNIQUE INDEX XPKSearchCategory ON SearchCategory
(searchcId   ASC);

ALTER TABLE SearchCategory
	ADD CONSTRAINT  XPKSearchCategory PRIMARY KEY (searchcId);

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
	openDate             TIMESTAMP  NOT NULL 
);

CREATE UNIQUE INDEX XPKStore ON Store
(storeId   ASC);

ALTER TABLE Store
	ADD CONSTRAINT  XPKStore PRIMARY KEY (storeId);

CREATE TABLE RecommendStore
(
	userId               VARCHAR2(20)  NOT NULL ,
	storeId              INTEGER  NOT NULL ,
	recomStore           VARCHAR2(500)  NULL ,
	recomDate            NCLOB  NULL 
);

CREATE UNIQUE INDEX XPKRecommendStore ON RecommendStore
(userId   ASC,storeId   ASC);

ALTER TABLE RecommendStore
	ADD CONSTRAINT  XPKRecommendStore PRIMARY KEY (userId,storeId);

CREATE TABLE SearchStore
(
	searchId             INTEGER  NOT NULL ,
	searchQuery          VARCHAR2(200)  NOT NULL ,
	storeId              INTEGER  NOT NULL ,
	userId               VARCHAR2(20)  NOT NULL 
);

CREATE UNIQUE INDEX XPKSearchStore ON SearchStore
(searchId   ASC);

ALTER TABLE SearchStore
	ADD CONSTRAINT  XPKSearchStore PRIMARY KEY (searchId);

CREATE TABLE PopularStore
(
	likeCount            INTEGER  NULL ,
	updateTime           NCLOB  NULL ,
	storeId              INTEGER  NOT NULL ,
	starScore            FLOAT  NULL 
);

CREATE UNIQUE INDEX XPKPopularStore ON PopularStore
(storeId   ASC);

ALTER TABLE PopularStore
	ADD CONSTRAINT  XPKPopularStore PRIMARY KEY (storeId);

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
	resDate              NCLOB  NOT NULL ,
	resTime              DATE  NOT NULL ,
	userId               VARCHAR2(20)  NOT NULL ,
	storeId              INTEGER  NOT NULL 
);

CREATE UNIQUE INDEX XPKReservation ON Reservation
(reservationId   ASC,userId   ASC);

ALTER TABLE Reservation
	ADD CONSTRAINT  XPKReservation PRIMARY KEY (reservationId,userId);

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

ALTER TABLE SearchCategory
	ADD (
CONSTRAINT R_22 FOREIGN KEY (categoryId) REFERENCES Category (categoryId));

ALTER TABLE SearchCategory
	ADD (
CONSTRAINT R_23 FOREIGN KEY (userId) REFERENCES Customer (userId));

ALTER TABLE Pet
	ADD (
CONSTRAINT R_1 FOREIGN KEY (userId) REFERENCES Customer (userId));

ALTER TABLE Store
	ADD (
CONSTRAINT R_3 FOREIGN KEY (sellerId) REFERENCES Seller (sellerId));

ALTER TABLE RecommendStore
	ADD (
CONSTRAINT R_19 FOREIGN KEY (userId) REFERENCES Customer (userId));

ALTER TABLE RecommendStore
	ADD (
CONSTRAINT R_20 FOREIGN KEY (storeId) REFERENCES Store (storeId));

ALTER TABLE SearchStore
	ADD (
CONSTRAINT R_24 FOREIGN KEY (storeId) REFERENCES Store (storeId) ON DELETE SET NULL);

ALTER TABLE SearchStore
	ADD (
CONSTRAINT R_25 FOREIGN KEY (userId) REFERENCES Customer (userId));

ALTER TABLE PopularStore
	ADD (
CONSTRAINT R_18 FOREIGN KEY (storeId) REFERENCES Store (storeId));

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
