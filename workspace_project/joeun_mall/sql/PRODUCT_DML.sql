-- product_tbl paging 쿼리. 
-- product_tbl 전체 데이터 가져오기 (paging)
-- ASC는 오름차순
-- DESC는 내림차순
SELECT * 
FROM (SELECT ROWNUM, m.*, FLOOR((ROWNUM -1) / 8 + 1) page
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
		
-- 상품관리 검색기능
SELECT * 
FROM (SELECT m.*, FLOOR((ROWNUM -1) / 8 + 1) PAGE 
		FROM (
				SELECT * FROM (
					SELECT * from PRODUCT_TBL JOIN (
						SELECT PRODUCT_INDEX, LISTAGG(PRODUCT_OPTION_VALUE , ',') 
						WITHIN GROUP (ORDER BY PRODUCT_OPTION_VALUE) AS PRODUCT_OPTION_VALUE
	 			FROM PRODUCT_OPTION_TBL
	 			GROUP BY PRODUCT_INDEX) B ON PRODUCT_TBL.PRODUCT_INDEX = B.PRODUCT_INDEX
	 			)
	 			WHERE product_name LIKE '%팬츠%'
	 			ORDER BY product_date DESC
			 ) m
		)
WHERE page = 1;
	
-- 상품관리 검색 후 총 레코드 갯수
SELECT COUNT(*)   
		FROM (SELECT * FROM product_tbl )
	             WHERE product_name LIKE '%팬츠%'