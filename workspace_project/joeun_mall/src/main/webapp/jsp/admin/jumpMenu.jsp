<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko-kr">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href = "<%=request.getContextPath()%>/admin/admin-productManage.do?demo=team3">
productManage
</a><br>
<a href = "<%=request.getContextPath()%>/orderManage.do?admin=team3">
orderManage
</a><br>
<a href = "<%=request.getContextPath()%>/customerManage.do?admin=team3">
customerManage
</a><br>
<a href = "<%=request.getContextPath()%>/inquiryManage.do?admin=team3">
inquiryManage
</a><br>
<a href = "<%=request.getContextPath()%>/orderStats.do?admin=team3">
orderStats
</a><br>
</body>
</html>