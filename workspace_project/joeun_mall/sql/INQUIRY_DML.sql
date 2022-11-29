-- 1:1문의 Paging 쿼리(INQUIRY_TBL에 USER_TBL을 JOIN해서 USER_NAME을 추가하는 쿼리)
	SELECT *
FROM (
		SELECT m.*, FLOOR((ROWNUM -1) / 8 + 1) PAGE 
		FROM ( 
				SELECT * FROM 
				(
				SELECT *
					FROM inquiry_tbl
					JOIN user_tbl
					ON inquiry_tbl.user_index = user_tbl.user_index
                )				
				ORDER BY inquiry_date DESC
		) m
);

-- 1:1문의 전체 레코드 수
SELECT count(*) FROM inquiry_tbl;

-- 1:1문의 검색기능.
SELECT *  
		FROM (SELECT ROWNUM,  
	             m.*,  
	             FLOOR((ROWNUM - 1) / 8 + 1) page  
	      FROM (
	             SELECT * FROM (
				SELECT *
					FROM inquiry_tbl
					JOIN user_tbl
					ON inquiry_tbl.user_index = user_tbl.user_index
                )
	             WHERE user_name LIKE '%남궁%'
	             ORDER BY inquiry_date DESC
	           ) m  
	      )  
	WHERE page = 1;
	
-- 1:1문의 검색 후 Paging
SELECT count(*) FROM (
	SELECT *
		FROM inquiry_tbl
		JOIN user_tbl
		ON inquiry_tbl.user_index = user_tbl.user_index
    )
     WHERE user_name LIKE '%남궁%';
     
-- 주문번호에 포함되는 모든 제품명을 뽑는 쿼리.
SELECT product_name FROM PRODUCT_TBL
WHERE product_index IN (
						SELECT product_index FROM ORDER_PRODUCT_TBL 
						WHERE order_product_index like '221007_2022039_1%'
					  );
