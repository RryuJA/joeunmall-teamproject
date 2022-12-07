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
    <title>JoEunMall 회원정보</title>

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
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/user-header-main.css' />">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/user-mypage-nav.css' />">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/user-header-login.css' />">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/user-footer.css' />">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/user-join.css' />">

</head>

<body>
    <div id="wrap">
        <header>
            <a href="user-productlistCarousel.do"><img id="logo" src="<c:url value='/images/logo/logo_transparent.png' />" alt="JoEunMall"></a>
            <ul id="header">
                <li><a href="#">전상품</a></li>
                <li><a href="#">티셔츠</a></li>
                <li><a href="#">팬츠/스커트</a></li>
                <li><a href="#">원피스</a></li>
                <li><a href="#">니트/가디건</a></li>
                <li><a href="#">자켓</a></li>
            </ul>
                
                <!-- 로그인 상태 -->
	            <c:if test="${not empty SESS_LOGIN_ID}">
	           		<input type="button" id="logout_btn" value="로그아웃">
	            </c:if>
	            <!-- 로그아웃 상태 -->
	            <c:if test="${empty SESS_LOGIN_ID}">
	            	<input type="button" id="login_btn" value="로그인">
	            </c:if>
	            
                <a href="user-mypageOrder.do"><img src="<c:url value='/images/icon/icon_user.png' />"></a>
                <a href="user/user-shoppingBasket.do"><img src="<c:url value='/images/icon/icon_shopping_bag.png' />"></a>
                <a href="#"><img src="<c:url value='/images/icon/icon_search.png' />"></a>
            </div>
        </header>
        
        <div id="content-wrap">
            <nav>
                <h3 id="mypage-title">마이페이지</h3>

                <div class="mypage-list">
                    <p class="sub-title">나의 쇼핑정보</p>
                    <ul class="mypage-sublist">
                        <li><a href="<c:url value='/user/user-mypageOrder.do' />">주문내역</a></li>
                        <li id="this-page"><a href="<c:url value='/user/user-mypageHistory.do' />">최근본상품</a></li>
                    </ul>
                </div>
                <div class="mypage-list">
                    <p class="sub-title">나의 쇼핑문의</p>
                    <ul class="mypage-sublist">
                        <li><a href="<c:url value='/user/user-mypageInquiry.do' />">내 문의글</a></li>
                    </ul>
                </div>
                <div class="mypage-list">
                    <p class="sub-title">회원정보</p>
                    <ul class="mypage-sublist">
                        <li><a href="<c:url value='/user/user-view.do'/>">회원정보 보기</a></li>
                        <li><a href="<c:url value='/user/user-mypageModify.do' />">회원정보수정</a></li>
                        <li><a href="#">회원탈퇴</a></li>
                    </ul>
                </div>
            </nav>  
        
        <section>
            <article id="title">
                <p>회원 정보 </p>
            </article>
            <article id="join">
                  <table> 
                      <tr>
                          <td class="icon-width"><a class="checked-icon" /></td>
                          <th>아이디</th>
                          <td>${user.userId}</td>
                      </tr>
                      <tr>
                          <td class="icon-width"><a class="checked-icon" /></td>
                          <th>이름</th>
                          <td>${user.userName}</td>
                      </tr>
                      <tr>
                          <td class="icon-width"></td>
                          <th>우편번호</th>
                          <td>${user.userPost}</td>
                      </tr>
                      <tr>
                          <td class="icon-width"></td>
                          <th>주소</th>
                          <td>${user.userAddress}</td>
                      </tr>
                      <tr>
                          <td class="icon-width"></td>
                          <th>상세주소</th>
                          <td>${user.userAddressDetail}</td>
                      </tr>
                      <tr>
                          <td class="icon-width"><a class="checked-icon" /></td>
                          <th>이메일</th>
                          <td>${user.userMail}</td>
                      </tr>
                      <tr>
                          <td class="icon-width"></td>
                          <th>일반전화</th>
                          <td>${user.userLandline}</td>
                      </tr>
                      <tr>
                          <td class="icon-width"><a class="checked-icon" /></td>
                          <th>휴대전화</th>
                          <td>${user.userMobile}</td>
                      </tr>
                      <tr>
                          <td class="icon-width"><a class="checked-icon" /></td>
                          <th>성별</th>
                          <td>
							${user.userGender == '남' ? '남자':'여자'}
                          </td>
                      </tr>
                      <tr>
                          <td class="icon-width"><a class="checked-icon" /></td>
                          <th>생년월일</th>
                          <td>
                          	<fmt:formatDate value="${user.userBirth}" pattern="yyyy년 MM월 dd일"/> 
					</td>
                      </tr>
                  </table>
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