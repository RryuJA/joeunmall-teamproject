<%@ page isErrorPage="true" 
		 contentType="text/html; charset=UTF-8"
    	 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>403 patch</title>
<script>
	alert("접근권한이 없습니다. 로그인 페이지로 이동하겠습니다.");
	location.href= "${pageContext.request.contextPath}/"; // 페이지 이동
</script>
</head>
<body>
</body>
</html>