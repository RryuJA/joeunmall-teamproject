--고객번호 2022051
--고객 ID AabcdeF1234
--고객 PW joeun5678   $2a$10$M/g2nBqmBrPYUyWP/crqMO2R3zyOEeDO9gXGy14gggQY/zFnTvv/6
--문의 번호 221005002
--주문 상품 번호 ﻿: '주문일자 6자리'_'고객번호 7자리'_'고객의 당일 주문 순서 1자리'_OR (+) '주문상품번호'  (주문상품번호는1~5까지만 가능)  000000_0000000_0_OR0

--카트 테이블 CART_INDEX 자료형 크기 변경
ALTER TABLE CART_TBL MODIFY ( CART_INDEX NCHAR(12));


INSERT INTO inquiry_tbl VALUES ('221005002','2022051',TO_DATE('22/08/13','RR/MM/DD'),'교환/환불','답변완료','221005002 문의제목 dummy','220813001문의내용 dummy','220813001문의답변 dummy');

INSERT INTO order_product_tbl VALUES ('221124_2022051_1_OR1','2022051','22_04_025','22_04_025_OP1',3);

--CART_INDEX = ﻿'고객번호' (+) _CA (+) '장바구니 번호 1자리'  (장바구니 번호는 1~5까지만 가능)
INSERT INTO cart_tbl VALUES 
('﻿2022051_CA0', '2022051', '22_01_001', '22_01_001_OP1', 3);

INSERT INTO user_tbl (user_index,user_id,user_name,user_pw,user_date,user_mail,user_birth,user_gender,user_landline,user_mobile,user_post,user_address,user_address_detail) VALUES ('2022051','AabcdeF1234','조규성','$2a$10$M/g2nBqmBrPYUyWP/crqMO2R3zyOEeDO9gXGy14gggQY/zFnTvv/6',sysdate,'GSC123@naver.com',TO_DATE('96/11/01','RR/MM/DD'),'남','041-1234-4321','010-9876-6789','03900','서울 천국구 하늘공원로 86 (상암동, 마포자원회수시설)','현대아파트 101호3333');

--삭제
DELETE INQUIRY_TBL WHERE USER_INDEX = (SELECT USER_INDEX FROM USER_TBL WHERE USER_ID = 'AabcdeF1234'); 

DELETE CART_TBL WHERE USER_INDEX = (SELECT USER_INDEX FROM USER_TBL WHERE USER_ID = 'AabcdeF1234'); 

DELETE Order_Product_Tbl WHERE USER_INDEX = (SELECT USER_INDEX FROM USER_TBL WHERE USER_ID = 'AabcdeF1234'); 

DELETE USER_TBL WHERE USER_ID = 'AabcdeF1234';