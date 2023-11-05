
CREATE SEQUENCE [sequence_store] AS bigint
START WITH 100
INCREMENT BY 1
go

CREATE SEQUENCE [sequence_] AS bigint
INCREMENT BY 1
go

CREATE TABLE [Category]
( 
	[categoryId]         numeric()  NOT NULL ,
	[caName]             varchar()  NULL ,
	[storeCount]         numeric  NULL 
)
go

ALTER TABLE [Category]
	ADD CONSTRAINT [XPKCategory] PRIMARY KEY  CLUSTERED ([categoryId] ASC)
go

CREATE TABLE [LikeList]
( 
	[userId]             varchar()  NOT NULL ,
	[storeId]            numeric  NOT NULL 
)
go

ALTER TABLE [LikeList]
	ADD CONSTRAINT [XPKLikeList] PRIMARY KEY  CLUSTERED ([userId] ASC,[storeId] ASC)
go

CREATE TABLE [Menu]
( 
	[menuId]             numeric  NOT NULL ,
	[menuName]           varchar()  NULL ,
	[menuDescrip]        varchar()  NULL ,
	[mePrice]            numeric()  NULL ,
	[storeId]            numeric  NOT NULL 
)
go

ALTER TABLE [Menu]
	ADD CONSTRAINT [XPKMenu] PRIMARY KEY  CLUSTERED ([menuId] ASC)
go

CREATE TABLE [Pet]
( 
	[petId]              numeric()  NOT NULL ,
	[pName]              varchar()  NULL ,
	[pAge]               numeric  NULL ,
	[pCategory]          varchar()  NULL ,
	[pDetailCategory]    varchar()  NULL ,
	[userId]             varchar()  NOT NULL ,
	[pNeureting]         bit  NULL ,
	[pImage]             varchar()  NULL 
)
go

ALTER TABLE [Pet]
	ADD CONSTRAINT [XPKPet] PRIMARY KEY  CLUSTERED ([petId] ASC,[userId] ASC)
go

CREATE TABLE [PopularStore]
( 
	[likeCount]          numeric  NULL ,
	[updateTime]         datetime  NULL ,
	[storeId]            numeric  NOT NULL ,
	[starScore]          float  NULL 
)
go

ALTER TABLE [PopularStore]
	ADD CONSTRAINT [XPKPopularStore] PRIMARY KEY  CLUSTERED ([storeId] ASC)
go

CREATE TABLE [RecommendStore]
( 
	[userId]             varchar()  NOT NULL ,
	[storeId]            numeric  NOT NULL ,
	[recomStore]         varchar()  NULL ,
	[recomDate]          datetime  NULL 
)
go

ALTER TABLE [RecommendStore]
	ADD CONSTRAINT [XPKRecommendStore] PRIMARY KEY  CLUSTERED ([userId] ASC,[storeId] ASC)
go

CREATE TABLE [Reservation]
( 
	[reservationId]      numeric  NOT NULL ,
	[resDate]            datetime  NOT NULL ,
	[resTime]            datetime  NOT NULL ,
	[userId]             varchar()  NOT NULL ,
	[storeId]            numeric  NOT NULL 
)
go

ALTER TABLE [Reservation]
	ADD CONSTRAINT [XPKReservation] PRIMARY KEY  CLUSTERED ([reservationId] ASC,[userId] ASC)
go

CREATE TABLE [Review]
( 
	[reviewId]           numeric  NOT NULL ,
	[reContent]          varchar()  NULL ,
	[starScore]          numeric()  NULL ,
	[userId]             varchar()  NOT NULL ,
	[storeId]            numeric  NOT NULL 
)
go

ALTER TABLE [Review]
	ADD CONSTRAINT [XPKReview] PRIMARY KEY  CLUSTERED ([reviewId] ASC)
go

CREATE TABLE [SearchCategory]
( 
	[searchcId]          numeric  NOT NULL ,
	[categoryId]         numeric()  NOT NULL ,
	[userId]             varchar()  NOT NULL 
)
go

ALTER TABLE [SearchCategory]
	ADD CONSTRAINT [XPKSearchCategory] PRIMARY KEY  CLUSTERED ([searchcId] ASC)
go

CREATE TABLE [SearchStore]
( 
	[searchId]           numeric  NOT NULL ,
	[searchQuery]        varchar()  NOT NULL ,
	[storeId]            numeric  NOT NULL ,
	[userId]             varchar()  NOT NULL 
)
go

ALTER TABLE [SearchStore]
	ADD CONSTRAINT [XPKSearchStore] PRIMARY KEY  CLUSTERED ([searchId] ASC)
go

CREATE TABLE [Seller]
( 
	[sellerId]           varchar()  NOT NULL ,
	[seName]             varchar()  NOT NULL ,
	[sePassword]         varchar()  NOT NULL ,
	[sePhone]            varchar()  NOT NULL ,
	[seMail]             varchar()  NOT NULL 
)
go

ALTER TABLE [Seller]
	ADD CONSTRAINT [XPKSeller] PRIMARY KEY  CLUSTERED ([sellerId] ASC)
go

CREATE TABLE [Store]
( 
	[storeId]            numeric  NOT NULL ,
	[sName]              varchar()  NOT NULL ,
	[sPhone]             varchar()  NOT NULL ,
	[sTime]              datetime  NOT NULL ,
	[sStarScore]         float  NULL ,
	[sDetailDescription] varchar()  NULL ,
	[sellerId]           varchar()  NOT NULL ,
	[openDate]           char(18)  NOT NULL 
)
go

ALTER TABLE [Store]
	ADD CONSTRAINT [XPKStore] PRIMARY KEY  CLUSTERED ([storeId] ASC)
go

CREATE TABLE [StoreCategory]
( 
	[categoryId]         numeric()  NOT NULL ,
	[storeId]            numeric  NOT NULL 
)
go

ALTER TABLE [StoreCategory]
	ADD CONSTRAINT [XPKStoreCategory] PRIMARY KEY  CLUSTERED ([categoryId] ASC,[storeId] ASC)
go

CREATE TABLE [User]
( 
	[userId]             varchar()  NOT NULL ,
	[uName]              varchar()  NOT NULL ,
	[uPassword]          varchar()  NOT NULL ,
	[uPhone]             varchar()  NOT NULL ,
	[uMail]              varchar()  NOT NULL 
)
go

ALTER TABLE [User]
	ADD CONSTRAINT [XPKUser] PRIMARY KEY  CLUSTERED ([userId] ASC)
go


ALTER TABLE [LikeList]
	ADD CONSTRAINT [R_15] FOREIGN KEY ([userId]) REFERENCES [User]([userId])
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go

ALTER TABLE [LikeList]
	ADD CONSTRAINT [R_16] FOREIGN KEY ([storeId]) REFERENCES [Store]([storeId])
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go


ALTER TABLE [Menu]
	ADD CONSTRAINT [R_6] FOREIGN KEY ([storeId]) REFERENCES [Store]([storeId])
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go


ALTER TABLE [Pet]
	ADD CONSTRAINT [R_1] FOREIGN KEY ([userId]) REFERENCES [User]([userId])
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go


ALTER TABLE [PopularStore]
	ADD CONSTRAINT [R_18] FOREIGN KEY ([storeId]) REFERENCES [Store]([storeId])
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go


ALTER TABLE [RecommendStore]
	ADD CONSTRAINT [R_19] FOREIGN KEY ([userId]) REFERENCES [User]([userId])
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go

ALTER TABLE [RecommendStore]
	ADD CONSTRAINT [R_20] FOREIGN KEY ([storeId]) REFERENCES [Store]([storeId])
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go


ALTER TABLE [Reservation]
	ADD CONSTRAINT [R_2] FOREIGN KEY ([userId]) REFERENCES [User]([userId])
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go

ALTER TABLE [Reservation]
	ADD CONSTRAINT [R_11] FOREIGN KEY ([storeId]) REFERENCES [Store]([storeId])
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go


ALTER TABLE [Review]
	ADD CONSTRAINT [R_10] FOREIGN KEY ([userId]) REFERENCES [User]([userId])
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go

ALTER TABLE [Review]
	ADD CONSTRAINT [R_14] FOREIGN KEY ([storeId]) REFERENCES [Store]([storeId])
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go


ALTER TABLE [SearchCategory]
	ADD CONSTRAINT [R_22] FOREIGN KEY ([categoryId]) REFERENCES [Category]([categoryId])
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go

ALTER TABLE [SearchCategory]
	ADD CONSTRAINT [R_23] FOREIGN KEY ([userId]) REFERENCES [User]([userId])
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go


ALTER TABLE [SearchStore]
	ADD CONSTRAINT [R_24] FOREIGN KEY ([storeId]) REFERENCES [Store]([storeId])
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go

ALTER TABLE [SearchStore]
	ADD CONSTRAINT [R_25] FOREIGN KEY ([userId]) REFERENCES [User]([userId])
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go


ALTER TABLE [Store]
	ADD CONSTRAINT [R_3] FOREIGN KEY ([sellerId]) REFERENCES [Seller]([sellerId])
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go


ALTER TABLE [StoreCategory]
	ADD CONSTRAINT [R_8] FOREIGN KEY ([categoryId]) REFERENCES [Category]([categoryId])
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go

ALTER TABLE [StoreCategory]
	ADD CONSTRAINT [R_9] FOREIGN KEY ([storeId]) REFERENCES [Store]([storeId])
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go
