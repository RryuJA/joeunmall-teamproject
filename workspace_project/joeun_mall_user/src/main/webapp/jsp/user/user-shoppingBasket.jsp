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
    <title>JoEunMall 장바구니</title>


    <!-- js-->
    <script src="js/user-shopping-basket.js" charset="UTF-8"></script> 
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
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/user-footer.css' />">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/user-shopping-basket.css' />">
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
            
            <a href="<c:url value='/user/user-mypageOrder.do' />"><img src="<c:url value='/images/icon/icon_user.png' />"></a>
            <a href="<c:url value='/user/user-shoppingBasket.do' />"><img src="<c:url value='/images/icon/icon_shopping_bag.png' />"></a>
            <a href="#"><img src="<c:url value='/images/icon/icon_search.png' />"></a>
        </header>
        <h1 class="h1-move">장바구니<hr></h1>
        <hr>
        <!--테이블-->
        <article>
            <div>
                <table>
                    <thead>
                            <tr class="tr1">
                                <th><input type="checkbox" id="all_select"></th>
                                <th colspan="2">상품정보</th>
                                <th>수량</th>
                                <th>상품금액</th>
                                <th>선택</th>
                            </tr>
                    </thead>
                    <tbody>
                            <tr>
                                <td><input type="checkbox"></td>
                                <td class="pd-1"><img src="<c:url value='/images/icon/icon_preloader.png' />" width="100" height="100"></td>
                                <td>여기에 글추가!!</td>
                                <td>
                                    <input type='button' onclick='count1("plus")' value='+'/>
                                    <div id='result1' class="result">1</div>
                                    <input type='button' onclick='count1("minus")' value='-'/>
                                    <button type="button" class="button-center">수정</button>
                                </td>
                                <td></td>
                                <td><button type="button">삭제</button></td>
                            </tr>
                            <tr>
                                <td><input type="checkbox"></td>
                                <td class="pd-1"><img src="<c:url value='/images/icon/icon_preloader.png' />" width="100" height="100"></td>
                                <td></td>
                                <td>
                                    <input type='button' onclick='count2("plus")' value='+'/>
                                    <div id='result2' class="result">1</div>
                                    <input type='button' onclick='count2("minus")' value='-'/>
                                    <button type="button" class="button-center">수정</button>
                                </td>
                                <td></td>
                                <td><button type="button">삭제</button></td>
                            </tr>
                            <tr>
                                <td><input type="checkbox"></td>
                                <td class="pd-1"><img src="<c:url value='/images/icon/icon_preloader.png' />" width="100" height="100"></td>
                                <td></td>
                                <td>
                                    <input type='button' onclick='count3("plus")' value='+'/>
                                    <div id='result3' class="result">1</div>
                                    <input type='button' onclick='count3("minus")' value='-'/>
                                    <button type="button" class="button-center">수정</button>
                                </td>
                                <td></td>
                                <td><button type="button">삭제</button></td>
                            </tr>
                            <tr>
                                <td><input type="checkbox"></td>
                                <td class="pd-1"><img src="<c:url value='/images/icon/icon_preloader.png' />" width="100" height="100"></td>
                                <td></td>
                                <td>
                                    <input type='button' onclick='count4("plus")' value='+'/>
                                    <div id='result4' class="result">1</div>
                                    <input type='button' onclick='count4("minus")' value='-'/>
                                    <button type="button" class="button-center">수정</button>
                                </td>
                                <td></td>
                                <td><button type="button">삭제</button></td>
                            </tr>
                            <tr>
                                <td><input type="checkbox"></td>
                                <td class="pd-1"><img src="<c:url value='/images/icon/icon_preloader.png' />" width="100" height="100"></td>
                                <td></td>
                                <td>
                                    <input type='button' onclick='count5("plus")' value='+'/>
                                    <div id='result5' class="result">1</div>
                                    <input type='button' onclick='count5("minus")' value='-'/>
                                    <button type="button" class="button-center">수정</button>
                                </td>
                                <td></td>
                                <td><button type="button">삭제</button></td>
                            </tr>
                    </tbody>
                </table>
            </div>
        </article>
        <table>
            <tr class="tr2">
                <td rowspan="2"></td>
                <td rowspan="2">*쇼핑백에 담긴 상품은 7일간 보관됩니다.<br>
                    5만원 이상 구매 시 배송비가 무료입니다.</td>
                <td></td>
                <td class="fs-1">총 상품금액</td>
                <td></td>
                <td class="fs-1">배송비</td>
                <td></td>
                <td class="fs-1">총 주문금액</td>
            </tr>
            <tr class="tr3">
                <td></td>
                <td class="fs-2">175,000</td>
                <td class="fs-2">+</td>
                <td class="fs-2">0</td>
                <td class="fs-2">=</td>
                <td class="fs-2">175,000원</td>
            </tr>
        </table>
        <!-- 선택 버튼 -->
        <article>
            <div id="pd-1">
            <button id="bt1" type="button">선택상품 삭제</button>
            <button id="bt1" type="button">전체상품 삭제</button>
            <button id="bt2" type="button">선택상품 주문</button>
            <button id="bt2" type="button">전체상품 주문</button>
            </div>
        </article>
        <!-- 하단 -->
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
</body>
</html>