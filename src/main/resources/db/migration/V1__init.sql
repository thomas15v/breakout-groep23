-- V1_init.sql
create table `blocksoorten` (
  `Id` int not null AUTO_INCREMENT unique,
  `Kleur` char(6) not null unique,
  `Lengte` int not null unique,
  `poweruptype` int not null,
  primary key(Id)
);

create table `players` (
  `Id` int not null AUTO_INCREMENT unique,
  `playernaam` char(16) not null unique,
  `highscore` int not null,
  primary key(Id)
);

create table `highscores` (
  `PlayerId` int not null,
  `highscore` int not null,
  primary key(PlayerId),
  foreign key(PlayerId) references players(Id)
);

create table `gametype` (
  `Id` int not null unique,
  `Naam` char(32) not null unique ,
  `SnelheidBal` int not null,
  `SnelheidPallet` int not null,
  `LengtePallet` int not null,
  `Levens` int not null,
  `ScoreMultiplier` int not null,
  `AantalRijen` int not null,
  primary key (Id)
);

create table `levels` (
  `Id` int not NULL UNIQUE AUTO_INCREMENT,
  `Name` char(32) NOT NULL,
  `width` int not NULL,
  `lenght` int not NULL,
  PRIMARY KEY (Id)
)

create table `level_map` (
  `Level_Id` int NOT NULL,
  `blockcat` int not null,
  `width` int not NULL,
  `lenght` int not NULL,
  `x` int not NULL,
  `y` int not NULL
)

