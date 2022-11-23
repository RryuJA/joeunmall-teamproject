-- order_tbl paging 쿼리. 

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

--product_tbl 전체 레코드 수
SELECT count(*) FROM order_tbl;
