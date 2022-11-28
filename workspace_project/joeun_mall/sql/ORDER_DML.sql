-- 주문관리 paging 쿼리

SELECT *
FROM (
		SELECT m.*, FLOOR(ROWNUM -1) / 8 + 1 PAGE 
		FROM ( 
				SELECT * FROM 
				(
					SELECT * FROM order_tbl
                    JOIN 
					(
						SELECT A.order_index, 
                              SUM(B.product_count) product_count_sum
						FROM order_tbl A, order_product_tbl B
						WHERE A.order_index = SUBSTR(B.order_product_index, 1, 16)
						GROUP BY A.order_index 
                        ORDER BY A.order_index ASC
                    ) C 
					ON order_tbl.order_index = C.order_index
                )
				ORDER BY order_date DESC
		) m
);

-- 주문관리 전체 레코드 수
SELECT count(*) FROM order_tbl;

-- 주문번호에 포함되는 모든 제품명을 뽑는 쿼리(주문관리 selectBox 제어)
SELECT product_name FROM PRODUCT_TBL
WHERE product_index IN (
						SELECT product_index FROM ORDER_PRODUCT_TBL 
						WHERE order_product_index like '221007_2022039_1%'
					  );	

-- 주문상태번호 수정 Order_product_state_tbl(주문관리 selectBox 제어)
UPDATE ORDER_TBL SET
	ORDER_STATE_INDEX = 'STA1'
	WHERE order_index = '221007_2022039_1';
	
-- 주문관리 검색기능
SELECT *
FROM (
      SELECT m.*, FLOOR((ROWNUM -1) / 8 + 1) PAGE  
      FROM ( 
            SELECT *
                FROM 
            (
               SELECT * FROM order_tbl
                    JOIN 
               (
                  SELECT A.order_index, 
                              SUM(B.product_count) product_count_sum
                  FROM order_tbl A, order_product_tbl B
                  WHERE A.order_index = SUBSTR(B.order_product_index, 1, 16)
                  GROUP BY A.order_index 
                        ORDER BY A.order_index ASC
                    ) C 
               ON order_tbl.order_index = C.order_index
                ) D            
                WHERE order_name like '%영민%'
            ORDER BY order_date DESC
      ) m
) WHERE PAGE = 1;

-- 주문관리 검색 후 paging 전체 레코드 수
SELECT count(*) 
FROM (
		SELECT * from ORDER_TBL 
			JOIN 
		(
			SELECT A.order_index,
						SUM(B.product_count) product_count_sum
			    FROM order_tbl A, order_product_tbl B
			    WHERE A.order_index = SUBSTR(B.order_product_index, 1, 16)
			    GROUP BY A.order_index 
			    ORDER BY A.order_index ASC
			    ) C 
		    ON order_tbl.ORDER_INDEX = C.order_index
		    )
			WHERE order_name LIKE '%민%';
		
	