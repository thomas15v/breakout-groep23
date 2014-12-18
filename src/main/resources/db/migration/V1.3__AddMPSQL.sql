create table MPhighscores (
  Id int not NULL AUTO_INCREMENT,
  PlayerName char(32) not null,
  Score int not null,
  primary key(Id)
);

create table MPgames (
  Id int not NULL AUTO_INCREMENT,
  Player1 int not null,
  Player2 int not null,
  primary key(Id)
);