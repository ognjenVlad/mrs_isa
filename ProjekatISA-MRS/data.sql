ALTER TABLE user MODIFY COLUMN picture longblob;
ALTER TABLE ad MODIFY COLUMN picture longblob;
ALTER TABLE prop MODIFY COLUMN picture longblob;

INSERT INTO user (id,activated,city,email,member_level,name,no_of_visits,password,phone,picture,surname,user_type)
VALUES (1,1,"Grad","sys",0,"Sistem",0,"pass","Tel","","Admin","sys");

INSERT INTO user (id,activated,city,email,member_level,name,no_of_visits,password,phone,picture,surname,user_type)
VALUES (2,1,"Grad","fan",0,"FanZone",0,"pass","Tel","","Admin","fan");

INSERT INTO user (id,activated,city,email,member_level,name,no_of_visits,password,phone,picture,surname,user_type)
VALUES (3,1,"Grad","ct",0,"CinemaTheatre",0,"pass","Tel","","Admin","ct");