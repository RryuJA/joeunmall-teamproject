<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!--title 페이지에 맞게 수정-->
    <title>JoEunMall 로그인</title>

    <!--javascript-->
    <script src="<c:url value='/jquery/jquery.min.js'/>"></script>
	<script>
	$(function(){
		
		//로그아웃 버튼 눌렀을때
		$("#logout_btn").click(function(){
			location.href = "<%=request.getContextPath()%>/user/user-logout.do"
		});
		
		//로그인 버튼 눌렀을때
		$("#login_btn").click(function(){
			location.href = "<%=request.getContextPath()%>/user/user-login.do"
		})
	});
	</script>

    <!--css-->
    <!--header와 footer는 고정-->
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/user-header-login.css' />">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/user-footer.css' />">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/user-login.css' />">

</head>

<body>
    <div id="wrap">
        <header>
            <a href="user-productlistCarousel.do"><img id="logo" src="<c:url value='/images/logo/logo_transparent.png' />" alt="JoEunMall"></a>
            <div id="header-icon">
                
                <!-- 로그인 상태 -->
	            <c:if test="${not empty SESS_LOGIN_ID}">
	           		<input type="button" id="logout_btn" value="로그아웃">
	            </c:if>
	            <!-- 로그아웃 상태 -->
	            <c:if test="${empty SESS_LOGIN_ID}">
	            	<input type="button" id="login_btn" value="로그인">
	            </c:if>
                
                <a href="<c:url value='/user/user-mypageOrder.do' />"><img src="<c:url value='/images/icon/icon_user.png' />"></a>
                <a href="<c:url value='/user/user-shoppingBasket.do' />"><img src="<c:url value='/images/icon/icon_shopping_bag.png' />"></a>
                <a href="#"><img src="<c:url value='/images/icon/icon_search.png' />"></a>
            </div>
        </header>
        
        <section>
            <article id="title">
                <p>로그인</p>
            </article>
            <article id="login">
                <form action="<c:url value ='/user/user-loginProc.do'/>" method="post">
                    <input type="text" name="userId" required pattern="\w{8,16}" title="아이디는 8자~16자 이내로 영문/숫자로 입력하세요" placeholder="아이디">
                    <input type="text" name="userPw" required pattern="\w{8,16}" 
                    	title="패쓰워드는 영문 대소문자, 숫자를 포함하여 8~16자 이내로 입력하세요" placeholder="비밀번호">
                    <input type="submit" value="로그인">
                </form>
            </article>
            <article id="etc-button">
                <ul>
                    <li><a href="<c:url value='/user/user-join.do' />">회원가입</a></li>
                    <div class="vertical-line"></div>
                    <li><a href="#">아이디 찾기</a></li>
                    <div class="vertical-line"></div>
                    <li><a href="#">비밀번호 찾기</a></li>
                </ul>
            </article>
        </section>

        <footer>
            <ul>
                <li>이용약관</li>
                <li>개인정보취급방침</li>
                <a href="#"><li>1:1문의</li></a>
            </ul>
            <div id="horizontal-line"></div>
            <div id="footer-info">
                <p>법인명(상호) : 조은몰</p>
                <p>사업자 등록번호 안내 : 000-00-00000</p>
                <p>주소 : 서울특별시 강남구 강남대로78길 8 (한국빌딩 8층) 804호</p>
                <p>팀원 : 류정아, 이승은, 조규성</p>
            </div>
        </footer>
    </div>
</body>
</html>