-- LSE 관리자-1:1문의 Paging
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

-- LSE 관리자-1:1문의 Paging records 수량 계산
SELECT count(*) FROM inquiry_tbl;

-- LSE 관리자-1:1문의-검색기능 paging
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
	
-- LSE 관리자-1:1문의-검색기능 paging records 수량 계산
SELECT count(*) FROM (
	SELECT *
		FROM inquiry_tbl
		JOIN user_tbl
		ON inquiry_tbl.user_index = user_tbl.user_index
    )
     WHERE user_name LIKE '%남궁%';
     
-- LSE 관리자-1:1문의-문의제목-문의상세정보(문의번호로 문의상세성보 조회)
SELECT *
	FROM inquiry_tbl
	JOIN user_tbl
	ON inquiry_tbl.user_index = user_tbl.user_index
	WHERE inquiry_index = '221005001';
