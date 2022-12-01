-- LSE 관리자-고객관리 paging
SELECT *  
FROM (SELECT ROWNUM,  
             m.*,  
             FLOOR((ROWNUM - 1) / 8 + 1) page  
      FROM (
             SELECT * FROM user_tbl  
             ORDER BY user_index DESC
           ) m  
      )  
WHERE page = 1;

-- LSE 관리자-고객관리 paging records 수량 계산
SELECT count(*) FROM user_tbl;

-- LSE 관리자-고객관리-검색기능 paging
SELECT *  
		FROM (SELECT ROWNUM,  
	             m.*,  
	             FLOOR((ROWNUM - 1) / 8 + 1) page  
	      FROM (
	             SELECT * FROM user_tbl 
	             WHERE user_name LIKE '%숙자%'
	             ORDER BY product_index DESC
	           ) m  
	      )  
	WHERE page = 1;
	
-- LSE 관리자-고객관리-검색기능 paging records 수량 계산
SELECT count(*) FROM user_tbl WHERE user_name LIKE '%숙자%'