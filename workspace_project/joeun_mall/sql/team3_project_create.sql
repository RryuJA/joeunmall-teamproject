-- 고객
CREATE TABLE user_tbl (
	user_index	            CHAR(7) NOT NULL PRIMARY KEY,
	user_id	                VARCHAR2(50) NOT NULL,
	user_name	            VARCHAR2(10 CHAR) NOT NULL,
	user_pw 	            VARCHAR2(60) NOT NULL,
	user_date  	            DATE DEFAULT SYSDATE NOT NULL,
	user_mail	            VARCHAR2(50 CHAR) NOT NULL,
	user_birth	            DATE NOT NULL,
	user_gender	            NCHAR(1) NOT NULL,
	user_landline	        VARCHAR2(13) NULL,
	user_mobile	            VARCHAR2(13) NOT NULL,
	user_post	            CHAR(5) NULL,
	user_address	        VARCHAR2(200 CHAR) NULL,
	user_address_detail 	VARCHAR2(200 CHAR) NULL
);

---------------------------------------------------------------------------------------------------------------------------------------------------------------

-- 판매상태정보 (1) 
CREATE TABLE product_state_tbl (
    product_state_index     CHAR(1) NOT NULL PRIMARY KEY,
    product_state_info      VARCHAR2(10 CHAR) NOT NULL
);

-- 판매상태 데이터 삽입 (2)
INSERT ALL 
    INTO product_state_tbl VALUES (0, '판매중')
    INTO product_state_tbl VALUES (1, '판매종료')
SELECT * FROM DUAL;

---------------------------------------------------------------------------------------------------------------------------------------------------------------

-- 상품 카테고리 (1)
CREATE TABLE product_category_tbl (
    product_category_index	CHAR(2) NOT NULL PRIMARY KEY,
    product_category_name	NVARCHAR2(10) NOT NULL
);

-- 상품 카테고리 데이터 삽입 (2)
INSERT ALL 
    INTO product_category_tbl VALUES ('01', '티셔츠')
    INTO product_category_tbl VALUES ('02', '팬츠/스커트')
    INTO product_category_tbl VALUES ('03', '원피스')
    INTO product_category_tbl VALUES ('04', '니트/가디건')
    INTO product_category_tbl VALUES ('05', '자켓')
SELECT * FROM DUAL;

---------------------------------------------------------------------------------------------------------------------------------------------------------------

-- 상품
CREATE TABLE product_tbl (
    product_index           CHAR(9) NOT NULL PRIMARY KEY,
	product_category_index 	CHAR(2) NOT NULL,
	product_state_index 	CHAR(1) NOT NULL,
    product_name 			NVARCHAR2(50) NOT NULL,
    product_price			NUMBER NOT NULL,
    product_image			VARCHAR2(1000) NOT NULL,
    product_date 			DATE DEFAULT SYSDATE NOT NULL,
    product_info 			VARCHAR2(1000 CHAR) NULL,

    -- 외래키(FK) 등록 코드
    CONSTRAINTS fk_product_tbl_1 FOREIGN KEY(product_category_index) REFERENCES product_category_tbl (product_category_index),
    CONSTRAINTS fk_product_tbl_2 FOREIGN KEY(product_state_index) REFERENCES product_state_tbl (product_state_index)
);

---------------------------------------------------------------------------------------------------------------------------------------------------------------


-- 상품 이미지
CREATE TABLE product_image_tbl(
    product_image_index         CHAR(11) NOT NULL PRIMARY KEY,
    product_index 				CHAR(9) NOT NULL,
    product_detail_image     	VARCHAR2(1000) NOT NULL,
    
    -- 외래키(FK) 등록 코드
    CONSTRAINTS fk_product_image_tbl_1 FOREIGN KEY(product_index) REFERENCES product_tbl (product_index)
);

---------------------------------------------------------------------------------------------------------------------------------------------------------------


-- 상품 옵션
CREATE TABLE product_option_tbl (
    product_option_index   		NCHAR(13) NOT NULL PRIMARY KEY,
    product_index 				CHAR(9) NOT NULL,
    product_option_value    	VARCHAR2(15 CHAR) NOT NULL,

    -- 외래키(FK) 등록 코드
    CONSTRAINTS fk_product_option_tbl_1 FOREIGN KEY(product_index) REFERENCES product_tbl (product_index)
);

---------------------------------------------------------------------------------------------------------------------------------------------------------------

-- 장바구니
CREATE TABLE cart_tbl (
    cart_index    				NCHAR(11) NOT NULL PRIMARY KEY,
    user_index 					CHAR(7) NOT NULL,
    product_index 				CHAR(9) NOT NULL,
    product_option_index		NCHAR(13) NOT NULL,
    product_count 		        NUMBER NOT NULL,

    -- 외래키(FK) 등록 코드
    CONSTRAINTS fk_cart_tbl_1 FOREIGN KEY(user_index) REFERENCES user_tbl (user_index),
    CONSTRAINTS fk_cart_tbl_2 FOREIGN KEY(product_index) REFERENCES product_tbl (product_index),
    CONSTRAINTS fk_cart_tbl_3 FOREIGN KEY(product_option_index) REFERENCES product_option_tbl (product_option_index)
);

---------------------------------------------------------------------------------------------------------------------------------------------------------------

-- 결제유형 (1) 
CREATE TABLE payment_tbl (
    payment_index 				NCHAR(4) NOT NULL PRIMARY KEY,
    payment_method 				VARCHAR2(10 CHAR) NOT NULL
);

-- 결제 유형 데이터 삽입(2) 
INSERT ALL 
    INTO payment_tbl VALUES ('PAY1', '무통장입금')
    INTO payment_tbl VALUES ('PAY2', '신용카드')
    INTO payment_tbl VALUES ('PAY3', '휴대폰결제')
SELECT * FROM DUAL;

---------------------------------------------------------------------------------------------------------------------------------------------------------------

-- 주문진행상태 (1)
CREATE TABLE order_state_tbl (
    order_state_index 			NCHAR(4) NOT NULL PRIMARY KEY,
    order_state_info 			VARCHAR2(10 CHAR) NOT NULL
);

-- 주문진행상태 데이터 삽입 (2)
INSERT ALL 
    INTO order_state_tbl VALUES ('STA1', '판매자 확인중')
    INTO order_state_tbl VALUES ('STA2', '상품준비')
    INTO order_state_tbl VALUES ('STA3', '배송처리')
    INTO order_state_tbl VALUES ('STA4', '배송완료')
    INTO order_state_tbl VALUES ('STA5', '주문취소')
    INTO order_state_tbl VALUES ('STA6', '반품 처리중')
    INTO order_state_tbl VALUES ('STA7', '환불완료')
    INTO order_state_tbl VALUES ('STA8', '교환 처리중')
    INTO order_state_tbl VALUES ('STA9', '교환완료')
SELECT * FROM DUAL;

---------------------------------------------------------------------------------------------------------------------------------------------------------------

-- 주문
CREATE TABLE order_tbl (
    order_index  			 CHAR(10) NOT NULL PRIMARY KEY,
    user_index 				 CHAR(7) NOT NULL,
    order_state_index		 NCHAR(4) NOT NULL,
    payment_index			 NCHAR(4) NOT NULL,
    order_date 				 DATE DEFAULT SYSDATE NOT NULL,
    order_price				 NUMBER	NOT NULL,
    order_name 				 VARCHAR2(10 CHAR) NOT NULL,
    order_post				 CHAR(5) NOT NULL,
    order_address			 VARCHAR2(200 CHAR) NOT NULL,
    order_address_detail	 VARCHAR2(200 CHAR)	NOT NULL,
    order_mobile 			 VARCHAR2(13) NOT NULL,
    order_message			 VARCHAR2(200 CHAR)	NULL,

    -- 외래키(FK) 등록 코드
    CONSTRAINTS fk_order_tbl_1 FOREIGN KEY(user_index) REFERENCES user_tbl (user_index),
    CONSTRAINTS fk_order_tbl_2 FOREIGN KEY(order_state_index) REFERENCES order_state_tbl (order_state_index),
    CONSTRAINTS fk_order_tbl_3 FOREIGN KEY(payment_index) REFERENCES payment_tbl (payment_index)
);

---------------------------------------------------------------------------------------------------------------------------------------------------------------

-- 주문 상품
CREATE TABLE order_product_tbl (
    order_product_index 	CHAR(12) NOT NULL PRIMARY KEY,
    order_index 			CHAR(10) NOT NULL,
    product_index 			CHAR(9) NOT NULL,
    product_option_index 	NCHAR(13) NOT NULL,
    product_count			NUMBER NOT NULL,
    
    -- 외래키(FK) 등록 코드
    CONSTRAINTS fk_order_product_tbl_1 FOREIGN KEY(order_index) REFERENCES order_tbl (order_index),
    CONSTRAINTS fk_order_product_tbl_2 FOREIGN KEY(product_index) REFERENCES product_tbl (product_index),
    CONSTRAINTS fk_order_product_tbl_3 FOREIGN KEY(product_option_index) REFERENCES product_option_tbl (product_option_index)
);

---------------------------------------------------------------------------------------------------------------------------------------------------------------

-- 1:1 문의
CREATE TABLE inquiry_tbl (
    inquiry_index   		CHAR(9)	NOT NULL PRIMARY KEY,
    user_index 				CHAR(7) NOT NULL,
    inquiry_date   			DATE DEFAULT SYSDATE NOT NULL,
    inquiry_category  		VARCHAR2(10 CHAR) NOT NULL,
    inquiry_state   		VARCHAR2(10 CHAR) NOT NULL,
    inquiry_title   		VARCHAR2(200 CHAR) NOT NULL,
    inquiry_content 		VARCHAR2(1000 CHAR) NOT NULL,
    inquiry_answer  		VARCHAR2(1000 CHAR) NULL,
    
    -- 외래키(FK) 등록 코드
    CONSTRAINTS fk_inquiry_tbl_1 FOREIGN KEY(user_index) REFERENCES user_tbl (user_index)
);


---------------------------------------------------------------------------------------------------------------------------------------------------------------

-- 리뷰
CREATE TABLE review_tbl (
    review_index    		NCHAR(16) NOT NULL PRIMARY KEY,
    product_index 			CHAR(9) NOT NULL,
    order_index 			CHAR(10) NOT NULL,
    review_date   			DATE DEFAULT SYSDATE NOT NULL,
    review_content 			VARCHAR2(100 CHAR) NOT NULL,
    
    -- 외래키(FK) 등록 코드
    CONSTRAINTS fk_review_tbl_1 FOREIGN KEY(product_index) REFERENCES product_tbl (product_index),
    CONSTRAINTS fk_review_tbl_2 FOREIGN KEY(order_index) REFERENCES order_tbl (order_index)
);
