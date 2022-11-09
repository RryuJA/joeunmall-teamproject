<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>JoEun-admin 로그인</title>
    <link rel="stylesheet" href="<c:url value ='/css/admin-login.css' />">
</head>
<body>
<%-- session=${SESS_LOGIN_ID}<br> --%>
	<!-- 에러메시지 출력 -->
	<c:if test="${not empty err_msg}">
		<script>
		alert("${err_msg}");
		</script>
	</c:if>
	
    <div id="wrap">
        	<img id="logo" src="<c:url value ='/images/logo/logo_transparent.png' />">
        <div class="login">
            <hr>
            <form action="<c:url value ='/admin/admin-loginProc.do'/>" method="post">
            <div  id="login-box">
	            <p><span style="letter-spacing:6px">ID:</span><input type="text" name="id"/></p>
	            <p>PW: <input type="password" name="pw"/></p>
	            <p><button type="submit" id="first-login">로그인</button></p>
        	</div>
        	</form>
        </div>
    </div>
</body>
</html>






