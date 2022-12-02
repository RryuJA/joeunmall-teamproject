-- product_tbl paging 쿼리. 
-- product_tbl 전체 데이터 가져오기 (paging)
-- ASC는 오름차순
-- DESC는 내림차순
SELECT * 
FROM (SELECT ROWNUM, m.*, FLOOR(ROWNUM -1) / 6 + 1 page
		FROM (
				SELECT * FROM (
					SELECT * from PRODUCT_TBL JOIN (
						SELECT PRODUCT_INDEX, LISTAGG(PRODUCT_OPTION_VALUE , ',') WITHIN GROUP (ORDER BY PRODUCT_OPTION_VALUE) AS PRODUCT_OPTION_VALUE
	 			FROM PRODUCT_OPTION_TBL
	 			GROUP BY PRODUCT_INDEX) B ON PRODUCT_TBL.PRODUCT_INDEX = B.PRODUCT_INDEX)
				ORDER BY product_date DESC
			 ) m
		)
WHERE page = 1;

--product_tbl 전체 레코드 수
SELECT count(*) FROM product_tbl;


--상품 정보 수정

--INSERT INTO PRODUCT_TBL VALUES(#{productIndex},#{productCategoryIndex},#{productStateInfo},#{productName},#{productPrice},#{updateFile1Filename},sysdate,#{productInfo})
UPDATE product_tbl SET
product_name = '',
product_price = '',
product_image ='',
product_date = sysdate,
product_info = ''
WHERE product_index = '';

--상품 이미지 수정
UPDATE product_image_tbl SET
product_index = '',
product_detail_image = ''
WHERE product_image_index = '';

--상품 이미지 조회 
SELECT COUNT()

--상품 이미지 삭제 
DELETE product_image_tbl 
WHERE product_image_index ='';
		