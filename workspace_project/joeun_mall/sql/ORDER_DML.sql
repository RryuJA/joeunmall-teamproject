-- LSE 관리자-주문관리 Paging
SELECT *
FROM (
		SELECT m.*, FLOOR((ROWNUM -1) / 8 + 1) PAGE 
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

-- LSE 관리자-주문관리 Paging records 수량 계산
SELECT count(*) FROM order_tbl;

-- LSE 관리자-주문관리-검색기능 paging
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

-- LSE 관리자-주문관리-검색기능 paging records 수량 계산
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

-- LSE 관리자-주문관리-상품명-주문에 포함되는 모든 제품명 출력 ('제품명 외 N개' 출력용)
SELECT product_name FROM PRODUCT_TBL
WHERE product_index IN (
						SELECT product_index FROM ORDER_PRODUCT_TBL 
						WHERE order_product_index like '221007_2022039_1%'
					   );	

-- LSE 관리자-주문관리-selectBox- 주문상태번호수정(selectBox 제어)
UPDATE ORDER_TBL SET
	ORDER_STATE_INDEX = 'STA1'
	WHERE order_index = '221007_2022039_1';

-- LSE 관리자-주문관리-selectBox-주문번호 존재확인 (selectBox 제어)(log 출력용)
SELECT COUNT(*) 
	FROM ORDER_TBL 
	WHERE ORDER_INDEX = '221007_2022039_1';	

	