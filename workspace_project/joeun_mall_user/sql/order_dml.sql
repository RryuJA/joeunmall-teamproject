-- 마이페이지 - 주문내역(UserOrder)
-- order_tbl paging 쿼리. 

-- order_product_tbl 주문별 총 주문수량.
SELECT A.order_index, SUM(B.product_count) product_count_sum
    FROM order_tbl A, order_product_tbl B
    WHERE A.order_index = SUBSTR(B.order_product_index, 1, 16)
    GROUP BY A.order_index ORDER BY A.order_index ASC;
  
-- order_product_tbl 주문별 총 주문수량과 order_tbl JOIN하기.
SELECT * 
from ORDER_TBL 
JOIN (SELECT A.order_index, SUM(B.product_count) product_count_sum
    FROM order_tbl A, order_product_tbl B
    WHERE A.order_index = SUBSTR(B.order_product_index, 1, 16)
    GROUP BY A.order_index ORDER BY A.order_index ASC) C ON order_tbl.ORDER_INDEX = C.order_index;
    
-- order_product_tbl 주문별 총 주문수량과 order_tbl를 JOIN한 테이블을 이클립스 페이징 메소드에 대입.
SELECT * 
FROM (SELECT ROWNUM, m.*, FLOOR(ROWNUM -1) / 8 + 1 PAGE 
	FROM ( SELECT * FROM 
		(SELECT * from ORDER_TBL JOIN (SELECT A.order_index, SUM(B.product_count) product_count_sum
		    FROM order_tbl A, order_product_tbl B
		    WHERE A.order_index = SUBSTR(B.order_product_index, 1, 16)
		    GROUP BY A.order_index ORDER BY A.order_index ASC) C ON order_tbl.ORDER_INDEX = C.order_index)
		    ORDER BY ORDER_DATE DESC
				) m
			)
WHERE page = 1;

--product_tbl 전체 레코드 수
SELECT count(*) FROM order_tbl;
