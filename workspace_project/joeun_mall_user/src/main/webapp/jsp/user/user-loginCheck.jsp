<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<!DOCTYPE html>
<html lang="ko-kr">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
request.setCharacterEncoding("UTF-8");
String userId = request.getParameter("userID");
String userPw = request.getParameter("userPw");

String dbType = "com.oracle.database.jdbc";
String connectUrl = "project@//localhost:1521/xe";
String connectId = "project";
String connectPw = "1111";
</body>
</html>