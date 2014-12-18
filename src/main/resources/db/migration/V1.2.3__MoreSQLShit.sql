drop table highscores;

create table highscores (
  Id int not NULL AUTO_INCREMENT,
  PlayerName char(32) not null,
  Score int not null,
  primary key(Id)
);
