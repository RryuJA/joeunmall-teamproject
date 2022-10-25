-- 회원정보 테이블
-- 규정) 소문자, snake-case, 접미어(table -> tbl, view -> view ..)

CREATE TABLE member_tbl (
id VARCHAR2(20),
name VARCHAR2(100),
gender CHAR,
email VARCHAR2(50),
mobile VARCHAR2(13),
zip CHAR(5),
address1 VARCHAR2(300 char),
address2 VARCHAR2(300 char),
birthday DATE,
joindate DATE DEFAULT sysdate
);

ALTER TABLE member_tbl
ADD CONSTRAINT member_tbl_id_pk PRIMARY KEY(id);
 
ALTER TABLE member_tbl
ADD CONSTRAINT member_tbl_email_u UNIQUE(email);
 
ALTER TABLE member_tbl
ADD CONSTRAINT member_tbl_mobile_u UNIQUE(mobile);
 
ALTER TABLE member_tbl
MODIFY (name CONSTRAINT member_tbl_name_nn NOT NULL);
 
ALTER TABLE member_tbl
MODIFY (gender CONSTRAINT member_tbl_gender_nn NOT NULL);
 
ALTER TABLE member_tbl
MODIFY (email CONSTRAINT member_tbl_email_nn NOT NULL);
 
ALTER TABLE member_tbl
MODIFY (mobile CONSTRAINT member_tbl_mobile_nn NOT NULL);
 
-- 회원 인증(auth) 정보 테이블
CREATE TABLE member_auth_tbl (
  id VARCHAR2(20),
  password VARCHAR2(60),
  enabled NUMBER(1) DEFAULT 1
 );
 
ALTER TABLE member_auth_tbl
ADD CONSTRAINT member_auth_id_pk PRIMARY KEY(id);
 
ALTER TABLE member_auth_tbl
MODIFY (password CONSTRAINT member_auth_pw_nn NOT NULL);

-- 회원 롤(roles) 정보 테이블 
CREATE TABLE member_roles_tbl (
  member_role_id NUMBER(11),
  id VARCHAR2(20),
  role VARCHAR2(45)
);

ALTER TABLE member_roles_tbl
ADD	CONSTRAINT member_roles_role_id_pk PRIMARY KEY(member_role_id);

ALTER TABLE member_roles_tbl
MODIFY (id CONSTRAINT member_roles_id_nn NOT NULL);

ALTER TABLE member_roles_tbl
ADD CONSTRAINT member_roles_fk FOREIGN KEY (id) 
     REFERENCES member_auth_tbl (id);
 
-- 회원 롤 정보 시퀀스
-- member_roles_seq.nextval
CREATE SEQUENCE member_roles_seq
	start with 1
	increment by 1
	maxvalue 99999
    nocycle; 