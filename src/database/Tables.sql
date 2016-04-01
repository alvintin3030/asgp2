CREATE TABLE Toys
(
	Tid int,
	Name varchar(255),
	Description varchar(1023),
	Category varchar(255),
	Image varchar(255),
	Price numeric(10,2),
	Isrecycle bit,
)

CREATE TABLE Users
(
	Uid int IDENTITY(1,1) PRIMARY KEY,
	Username varchar(255) NOT NULL,
	Password varchar(255) NOT NULL,
	Email varchar(255) NOT NULL,
	Credit int,
	UsergroupID int
)