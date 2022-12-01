-- 마이페이지-내 문의글(UserMypageInquiry)
-- 사용자-마이페이지-내문의글 Paging
SELECT * 
FROM (SELECT ROWNUM, m.*, FLOOR((ROWNUM -1) / 8 + 1) page
		FROM (SELECT * FROM INQUIRY_TBL
			 ) m
		)
WHERE page = 2;

-- 사용자-마이페이지-내문의글 Paging records 수량 계산
SELECT count(*) FROM product_tbl;