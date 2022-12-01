-- 개별 회원정보 조회 
SELECT * FROM user_tbl
WHERE user_id = '2022001abc123';

-- 개별 회원 최근 고객번호
-- 데이터 없을때 '0', 데이터 있을때 최댓값
SELECT SUBSTR(decode((SELECT MAX(user_index) FROM user_tbl), NULL, '0',
	(SELECT MAX(user_index) FROM user_tbl)),5) FROM dual; 

	
-- 회원 정보 저장
INSERT INTO user_tbl (user_index,user_id,user_name,user_pw,user_date,user_mail,user_birth,user_gender,user_landline,user_mobile,user_post,user_address,user_address_detail) VALUES ('2022001','2022001abc123','유운주','$2a$10$UTWZ0EQbhXCVFUpjiu42quBdTdkSYJBNU0yaGwejgDsa4PwCyqbUu',TO_DATE('22/01/04','RR/MM/DD'),'email2022001@daum.net',TO_DATE('86/06/06','RR/MM/DD'),'남','031-562-1519','010-4481-5738','94577','서울시 마포구','현대아파트 101호');

--회원 정보 수정
 UPDATE user_tbl set
 user_pw='$2a$10$PdP6.CsUr2OA2Ntvj1GHreOzjlYimO6l/5criE/JNLfshxc5Mb0FC',
 user_mail='joeun1234@naver.com',
 user_landline='041-9999-0000',
 user_mobile='010-9999-8888',
 user_post='89987',
 user_address='서울 강남구 역삼동',
 user_address_detail='더조은 '
 WHERE user_id = '2022001abc123';

-- -- 마이페이지-주문내역(MyOrder)
-- LSE 사용자-마이페이지-주문내역 Paging(user_index로 주문 조회)
SELECT * FROM order_tbl
WHERE user_index LIKE '2022001';

-- LSE 사용자-마이페이지-주문내역 Paging records records 수량 계산(user_index로 주문 조회)
SELECT count(*) FROM order_tbl
WHERE user_index LIKE  '2022001';

-- LSE 사용자-마이페이지-주문내역-상품명 (모든 상품명 조회 ('파란색티셔츠 외 N개' 형식으로 출력용))
SELECT product_name FROM PRODUCT_TBL
	WHERE product_index IN 
		(
		SELECT product_index FROM ORDER_PRODUCT_TBL 
		WHERE order_product_index like '221007_2022039_1%'
	    );