<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko-kr">
<head>
<meta charset="UTF-8">
<title>회원전용 마이 페이지</title>
</head>
<body>
회원 아이디: ${SESS_LOGIN_ID}
<hr>
<a href="/joeunmall/admin/admin-orderManage.do">관리자 페이지
</a><br>
<a href="/joeunmall/logout.do">로그아웃</a>
</body>
</html>