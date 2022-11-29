-- 마이페이지 - 내 문의글(UserMypageInquiry)

-- UserMypageInquiry paging 쿼리
SELECT * 
FROM (SELECT ROWNUM, m.*, FLOOR((ROWNUM -1) / 8 + 1) page
		FROM (SELECT * FROM INQUIRY_TBL
			 ) m
		)
WHERE page = 2;

-- UserMypageInquiry 전체 레코드 수
SELECT count(*) FROM product_tbl;