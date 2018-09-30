CREATE TABLE users (
  id int(9) unsigned NOT NULL AUTO_INCREMENT,
  username varchar(100) NOT NULL,
  hashedpass varchar(255) NOT NULL,
  highscore int(9) unsigned NOT NULL,
  PRIMARY KEY (id),
  KEY username (username)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;


