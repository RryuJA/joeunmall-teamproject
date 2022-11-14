-- user_tbl paging 쿼리. 
-- user_tbl 전체 데이터 가져오기 (paging)
-- ASC는 오름차순
SELECT *  
FROM (SELECT ROWNUM,  
             m.*,  
             FLOOR((ROWNUM - 1) / 8 + 1) page  
      FROM (
             SELECT * FROM user_tbl  
             ORDER BY user_index ASC
           ) m  
      )  
WHERE page = 1;

-- user_tbl 전체 레코드 수
SELECT count(*) FROM user_tbl;