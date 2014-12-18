use breakout;
DROP TABLE gametype;
DROP TABLE blocksoorten;
DROP TABLE highscores;
DROP TABLE players;

create table `highscores` (
  `PlayerName` char(32) not null,
  `highscore` int not null,
  primary key(PlayerName)
);

create table `powerups` (
	`Id` int not null unique AUTO_INCREMENT,
  `PowerUpMessage` char(128),
  `ExtraLive` int not NULL,
  `ExtraWith` int not NULL,
  `ExtraBallspeed` int NOT NULL,
  `ExtraPadSpeed` int not NULL,
  `BlockColor` char(6) not NULL,
  `ticks` int not NULL,
  PRIMARY KEY(Id)
);

UPDATE level_map set BlockCat=1 where id=1;

INSERT INTO powerups (PowerUpMessage, ExtraLive, ExtraWith, ExtraBallspeed, ExtraPadSpeed, BlockColor ,ticks)
    VALUES (NULL ,0, 0,0,0, '0099FF', 0), ("Extra Live!" ,1, 0,0,0,'E51616', 0),  ("Lost Live" ,-1, 0,0,0,'000000' ,0);