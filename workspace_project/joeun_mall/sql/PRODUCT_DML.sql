-- LSE 관리자-상품관리 Paging
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

-- LSE 관리자-상품관리 Paging records 수량 계산
SELECT count(*) FROM product_tbl;
		
-- LSE 관리자-상품관리-검색기능 paging
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
	
-- LSE 관리자-상품관리-검색기능 paging records 수량 계산
SELECT COUNT(*)   
		FROM (SELECT * FROM product_tbl )
	             WHERE product_name LIKE '%팬츠%'

-- LSE 관리자-상품관리-카테고리별 상품조회 paging
SELECT *  
FROM (SELECT  m.*,  
             FLOOR((ROWNUM - 1) / 8 + 1) page  
      FROM (
			SELECT * FROM (SELECT * from PRODUCT_TBL JOIN (SELECT product_index, LISTAGG(product_option_value, ', ') WITHIN GROUP (ORDER BY product_option_value) AS product_option_value
			FROM product_option_tbl
			GROUP BY product_index) B ON product_tbl.product_index = B.product_index)  
			WHERE product_category_index = '01'
			ORDER BY product_date DESC;
           ) m  
      )  
WHERE page = 1;

-- LSE 관리자-상품관리-카테고리별 상품조회 paging records 수량 계산
SELECT count(*) FROM product_tbl 
WHERE product_category_index = '01'
ORDER BY product_index DESC;	             
	             
-- RJA 상품번호로 상품정보 조회
SELECT * FROM product_tbl WHERE product_index = '22_05_050';

