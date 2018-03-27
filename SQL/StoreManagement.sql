/*All User's gets stored in APP_USER table*/
create table APP_USER (
   id NUMBER generated by default on null as IDENTITY ,
   sso_id VARCHAR(30) NOT NULL,
   password VARCHAR(100) NOT NULL,
   first_name VARCHAR(30) NOT NULL,
   last_name  VARCHAR(30) NOT NULL,
   email VARCHAR(30) NOT NULL,
   PRIMARY KEY (id),
   UNIQUE (sso_id)
);
  
/* USER_PROFILE table contains all possible roles */ 
create table USER_PROFILE(
   id NUMBER generated by default on null as IDENTITY,
   type VARCHAR(30) NOT NULL,
   PRIMARY KEY (id),
   UNIQUE (type)
);
  
/* JOIN TABLE for MANY-TO-MANY relationship*/  
CREATE TABLE APP_USER_USER_PROFILE (
    user_id NUMBER NOT NULL,
    user_profile_id NUMBER NOT NULL,
    PRIMARY KEY (user_id, user_profile_id),
    CONSTRAINT FK_APP_USER FOREIGN KEY (user_id) REFERENCES APP_USER (id),
    CONSTRAINT FK_USER_PROFILE FOREIGN KEY (user_profile_id) REFERENCES USER_PROFILE (id)
);

create table CUSTOMER (
   CUST_ID int IDENTITY ,
   first_name VARCHAR(30) NOT NULL,
   last_name  VARCHAR(30) NOT NULL,
   email VARCHAR(50),
   phone VARCHAR(30),
   LOC VARCHAR(50),
   ADDRESS VARCHAR(256),
   DUE_AMOUNT VARCHAR(30),
   PRIMARY KEY (CUST_ID)
);

create table ACTIVITY (

   id int IDENTITY ,
   CUST_ID int NOT NULL,
   CREATE_DATE VARCHAR(30) NOT NULL,
   ACT_TYP  VARCHAR(30) NOT NULL,
   INT_RT VARCHAR(4) NOT NULL DEFAULT 0,
   STATUS VARCHAR(10) DEFAULT 'OPEN',
   MEMO VARCHAR(50),
   AMOUNT VARCHAR(30),
   CLOSE_DATE VARCHAR(30) NOT NULL,
   TS_CRT VARCHAR(30) DEFAULT getdate(),
   TS_UPD VARCHAR(30) DEFAULT getdate(),
   PRIMARY KEY (id,CUST_ID),
   CONSTRAINT FK_CUSTOMER FOREIGN KEY (CUST_ID) REFERENCES CUSTOMER (CUST_ID)
);

create table LOCATION (

   id int IDENTITY ,
   LOCATION VARCHAR(50) NOT NULL,
   TS_CRT VARCHAR(30) DEFAULT getdate(),
   TS_UPD VARCHAR(30) DEFAULT getdate(),
   PRIMARY KEY (id),
);

 create table STORE(
   id NUMBER generated by default on null as IDENTITY,
   STORE_ID VARCHAR(5) NOT NULL,
   STORE_NAME VARCHAR(100),
   STORE_ADDRESS VARCHAR(256),
   STORE_COUNTRY VARCHAR(30),
   PRIMARY KEY (id,STORE_ID),
   UNIQUE (STORE_ID)
);
 
insert into STORE
(
STORE_ID ,
STORE_NAME ,
STORE_ADDRESS ,
STORE_COUNTRY 

)
values ('08502','Blloming Dales','Dubai Mall Dubai','UAE')
/* Populate USER_PROFILE Table */
INSERT INTO USER_PROFILE(type)
VALUES ('USER');
 
INSERT INTO USER_PROFILE(type)
VALUES ('ADMIN');
 
INSERT INTO USER_PROFILE(type)
VALUES ('DBA');
 
 
/* Populate one Admin User which will further create other users for the application using GUI */
INSERT INTO APP_USER(sso_id, password, first_name, last_name, email)
VALUES ('jai','$2a$10$4eqIF5s/ewJwHK1p8lqlFOEm2QIA0S8g6./Lok.pQxqcxaBZYChRm', 'jai','kumar','samy@xyz.com');
 
 
/* Populate JOIN Table */
INSERT INTO APP_USER_USER_PROFILE (user_id, user_profile_id)
   (SELECT usr.id,pro.id from app_user usr,user_profile pro
  where usr.sso_id='jai' and pro.type='ADMIN');

  
/* Create persistent_logins Table used to store rememberme related stuff*/
CREATE TABLE persistent_logins (
    username VARCHAR(64) NOT NULL,
    series VARCHAR(64) NOT NULL,
    token VARCHAR(64) NOT NULL,
    last_used TIMESTAMP NOT NULL,
    PRIMARY KEY (series)
);