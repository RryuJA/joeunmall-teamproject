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
    <title>JoEunMall 주문내역</title>

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
		});
		
		//회원탈퇴 버튼 눌렀을때
		$("#deleteMember_btn").click(function(){
			if(confirm("정말 탈되하시겠습니까?")){
				location.href="<%=request.getContextPath()%>/user/user-delete.do?userId=${sessionScope.SESS_LOGIN_ID}"	
			}
		});
		
	});
	</script>

    <!--css-->
    <!--header와 footer는 고정-->
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/user-header-main.css' />">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/user-mypage-nav.css' />">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/user-footer.css' />">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/user-mypage-list.css' />">

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
        
        <div id="content-wrap">
            <nav>
                <h3 id="mypage-title">마이페이지</h3>

                <div class="mypage-list">
                    <p class="sub-title">나의 쇼핑정보</p>
                    <ul class="mypage-sublist">
                        <li id="this-page"><a href="<c:url value='/user/user-mypageOrder.do' />">주문내역</a></li>
                        <li><a href="<c:url value='/user/user-mypageHistory.do' />">최근본상품</a></li>
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
                        <li><a href="<c:url value='/user/user-mypageModify.do'/>">회원정보수정</a></li>
                        <li><a href="#" id="deleteMember_btn">회원탈퇴</a></li>
                    </ul>
                </div>
            </nav>  

            <section id="list">
                <div id="sub-title">
                    <h3>주문내역</h3>
                </div>
                <article id="list-table">
                    <table>
                        <tr>
                            <th class="width2">주문일자</th>
                            <th class="width3">상품명</th>
                            <th class="width2">결제금액</th>
                            <th class="width2">주문상세</th>
                        </tr>
                        <tr>
                            <td>2022.09.20</td>
                            <td>오버롤포켓OPS 외 3건</td>
                            <td>79,000원</td>
                            <td><button>상세보기</button></td>
                        </tr>
                        <tr>
                            <td>2022.08.16</td>
                            <td>위드밴딩팬츠 외 2건</td>
                            <td>110,980원</td>
                            <td><button>상세보기</button></td>
                        </tr>
                        <tr>
                            <td>2022.07.15</td>
                            <td>ST가디건 외 5건</td>
                            <td>214,100원</td>
                            <td><button>상세보기</button></td>
                        </tr>
                        <tr>
                            <td>2022.05.05</td>
                            <td>935스키니 외 3건</td>
                            <td>34,300원</td>
                            <td><button>상세보기</button></td>
                        </tr>
                        <tr>
                            <td>2022.03.21</td>
                            <td>1973슬랙스</td>
                            <td>20,000원</td>
                            <td><button>상세보기</button></td>
                        </tr>
                    </table>
                </article>

                <!--페이지 이동 버튼-->
                <article>
                    <div class="page-wrap">
                        <div class="page-nation">
                            <a class="arrow pprev" href="#"></a>
                            <a class="arrow prev" href="#"></a>
                            <a href="#" class="active">1</a>
                            <a href="#">2</a>
                            <a href="#">3</a>
                            <a href="#">4</a>
                            <a href="#">5</a>
                            <a class="arrow next" href="#"></a>
                            <a class="arrow nnext" href="#"></a>
                        </div>
                    </div>
                </article>
            </section>
        </div>

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
                <p>팀원 : 류정아, 이승은, 조규성, 박재민</p>
            </div>
        </footer>
    </div>
</body>
</html>''