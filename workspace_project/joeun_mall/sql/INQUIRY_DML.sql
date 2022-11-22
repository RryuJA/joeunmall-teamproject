-- INQUIRY_TBL에 USER_TBL을 JOIN해서 USER_NAME을 추가하는 쿼리
	SELECT *
FROM (
		SELECT m.*, FLOOR(ROWNUM -1) / 8 + 1 PAGE 
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


--inquiry_tbl 전체 레코드 수
SELECT count(*) FROM inquiry_tbl;