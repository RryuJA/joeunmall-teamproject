CREATE TABLE USER_TBL (
	USER_INDEX	CHAR(7) PRIMARY KEY,
	USER_ID	VARCHAR2(50)	NOT NULL UNIQUE,
	USER_NAME	VARCHAR2(50 CHAR)	NOT NULL,
	USER_PW	VARCHAR2(60)	NOT NULL,
	USER_DATE	DATE	DEFAULT SYSDATE,
	USER_MAIL	VARCHAR2(50 CHAR)   NOT	NULL UNIQUE,
	USER_BIRTH	DATE	NULL,
	USER_GENDER	CHAR(1)	NOT NULL,
	USER_LANDLINE	VARCHAR2(13)	NULL,
	USER_MOBILE	VARCHAR2(13)	NOT	NULL UNIQUE,
	USER_ADDRESS_POST	CHAR(5)	NOT	NULL,
	USER_ADDRESS	VARCHAR2(200 CHAR)	NOT	NULL,
	USER_ADDRESS_DETAIL	VARCHAR2(200 CHAR)	NOT	NULL
);

--논리적 다이어그램 TBL
COMMENT ON COLUMN USER_TBL.USER_INDEX IS '회원번호';
COMMENT ON COLUMN USER_TBL.USER_ID IS '아이디';
COMMENT ON COLUMN USER_TBL.USER_NAME IS '이름';
COMMENT ON COLUMN USER_TBL.USER_PW IS '비밀번호';
COMMENT ON COLUMN USER_TBL.USER_DATE IS '가입일';
COMMENT ON COLUMN USER_TBL.USER_EMAIL IS '이메일';
COMMENT ON COLUMN USER_TBL.USER_BIRTH IS '생년월일';
COMMENT ON COLUMN USER_TBL.USER_GENDER IS '성별';
COMMENT ON COLUMN USER_TBL.USER_LANDLINE IS '일반전화';
COMMENT ON COLUMN USER_TBL.USER_MOBILE IS '휴대전화';
COMMENT ON COLUMN USER_TBL.USER_POST IS '우편번호';
COMMENT ON COLUMN USER_TBL.USER_ADDRESS IS '주소';
COMMENT ON COLUMN USER_TBL.USER_ADDRESS_DETAIL IS '상세주소';

--테이블관의 관계를 추후 작성(ALTER)
--작성 예시
--ALTER TABLE member_roles_tbl
--ADD CONSTRAINT member_roles_fk FOREIGN KEY (id) 
     --REFERENCES member_auth_tbl (id);