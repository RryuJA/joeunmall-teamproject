<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko-kr">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
이미지:<img src="http://localhost:8282/joeunmall/images/icon/icon_plus.png"><br>
이미지2:<img src="/joeunmall/images/icon/icon_plus.png"><br>
이미지3:<img src="<c:url value='/images/icon/icon_plus.png' />"><br>
이미지4:<img src="<c:url value='/images/carousel/image-carousel-1.jpeg' />">
</body>
</html>