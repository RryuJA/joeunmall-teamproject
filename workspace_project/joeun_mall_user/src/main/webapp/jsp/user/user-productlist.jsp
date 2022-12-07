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
    <title>JoEunMall</title>

    <!--javascript-->
    <script src="bootstrap-5.0.2/js/bootstrap.bundle.js"></script>

	<script src="<c:url value='/jquery/jquery.min.js'/>"></script>
	<script>
	$(function(){
		
		//로그아웃 버튼 눌렀을때
		$("#logout_btn").click(function(){
			location.href = "<%=request.getContextPath()%>/user/user-logout.do";
		});
		
		//로그인 버튼 눌렀을때
		$("#login_btn").click(function(){
			location.href = "<%=request.getContextPath()%>/user/user-login.do";
		})
	});
	</script>
    <!--css-->
    <!--header와 footer는 고정-->
    <link rel="stylesheet" type="text/css" href="css/user-header-main.css">
    <link rel="stylesheet" type="text/css" href="css/user-footer.css">
    <link rel="stylesheet" type="text/css" href="css/user-product-list.css">
    
    <link rel="stylesheet" type="text/css" href="bootstrap-5.0.2/css/bootstrap.css">

</head>

<body>
    <div id="wrap">
        <header>
            <a href="user-productlistCarousel.do"><img id="logo" src="images/logo/logo_transparent.png" alt="JoEunMall"></a>
            <ul id="header">
                <li><a href="#">전상품</a></li>
                <li><a href="#">티셔츠</a></li>
                <li><a href="#">팬츠/스커트</a></li>
                <li><a href="#">원피스</a></li>
                <li><a href="#">니트/가디건</a></li>
                <li><a href="#">자켓</a></li>
            </ul>
            <input type="button" value="로그인">
            <a href="user-mypageOrder.html"><img src="images/icon/icon_user.png"></a>
            <a href="user-shoppingBasket.html"><img src="images/icon/icon_shopping_bag.png"></a>
            <a href="#"><img src="images/icon/icon_search.png"></a>
        </header>
        
        <section>
            <article>
                <!--상품 정렬 버튼-->
                <ul class="sort">
                    <li><a href="#">신상품</a></li>
                    <div class="vertical-line"></div>
                    <li><a href="#">낮은가격순</a></li>
                    <div class="vertical-line"></div>
                    <li><a href="#">높은가격순</a></li>
                    <div class="vertical-line"></div>
                    <li><a href="#">인기순</a></li>
                </ul>

                <!--상품 목록-->
                <ul class="list">
                    <li>
                        <div class="thumbnail-box">
                            <div class="thumbnail-image"><img src="" alt="product-image"></div>
                            <div class="thumbnail-title">레이스 블라우스 티셔츠<br>35,000</div>
                        </div>
                    </li>
                    <li>
                        <div class="thumbnail-box">
                            <div class="thumbnail-image"><img src="" alt="product-image"></div>
                            <div class="thumbnail-title">소매 턱 티셔츠<br>15,000</div>
                        </div>
                    </li>
                    <li>
                        <div class="thumbnail-box">
                            <div class="thumbnail-image"><img src="" alt="product-image"></div>
                            <div class="thumbnail-title">RN 후드<br>20,000</div>
                        </div>
                    </li>
                    <li>
                        <div class="thumbnail-box">
                            <div class="thumbnail-image"><img src="" alt="product-image"></div>
                            <div class="thumbnail-title">시스루 캐주얼 점퍼<br>50,000</div>
                        </div>
                    </li>
                </ul>
                <ul class="list">
                    <li>
                        <div class="thumbnail-box">
                            <div class="thumbnail-image"><img src="" alt="product-image"></div>
                            <div class="thumbnail-title">레이스 블라우스 티셔츠<br>35,000</div>
                        </div>
                    </li>
                    <li>
                        <div class="thumbnail-box">
                            <div class="thumbnail-image"><img src="" alt="product-image"></div>
                            <div class="thumbnail-title">소매 턱 티셔츠<br>15,000</div>
                        </div>
                    </li>
                    <li>
                        <div class="thumbnail-box">
                            <div class="thumbnail-image"><img src="" alt="product-image"></div>
                            <div class="thumbnail-title">RN 후드<br>20,000</div>
                        </div>
                    </li>
                    <li>
                        <div class="thumbnail-box">
                            <div class="thumbnail-image"><img src="" alt="product-image"></div>
                            <div class="thumbnail-title">시스루 캐주얼 점퍼<br>50,000</div>
                        </div>
                    </li>
                </ul>

                <ul class="list">
                    <li>
                        <div class="thumbnail-box">
                            <div class="thumbnail-image"><img src="" alt="product-image"></div>
                            <div class="thumbnail-title">레이스 블라우스 티셔츠<br>35,000</div>
                        </div>
                    </li>
                    <li>
                        <div class="thumbnail-box">
                            <div class="thumbnail-image"><img src="" alt="product-image"></div>
                            <div class="thumbnail-title">소매 턱 티셔츠<br>15,000</div>
                        </div>
                    </li>
                    <li>
                        <div class="thumbnail-box">
                            <div class="thumbnail-image"><img src="" alt="product-image"></div>
                            <div class="thumbnail-title">RN 후드<br>20,000</div>
                        </div>
                    </li>
                    <li>
                        <div class="thumbnail-box">
                            <div class="thumbnail-image"><img src="" alt="product-image"></div>
                            <div class="thumbnail-title">시스루 캐주얼 점퍼<br>50,000</div>
                        </div>
                    </li>
                </ul>

                <ul class="list">
                    <li>
                        <div class="thumbnail-box">
                            <div class="thumbnail-image"><img src="" alt="product-image"></div>
                            <div class="thumbnail-title">레이스 블라우스 티셔츠<br>35,000</div>
                        </div>
                    </li>
                    <li>
                        <div class="thumbnail-box">
                            <div class="thumbnail-image"><img src="" alt="product-image"></div>
                            <div class="thumbnail-title">소매 턱 티셔츠<br>15,000</div>
                        </div>
                    </li>
                    <li>
                        <div class="thumbnail-box">
                            <div class="thumbnail-image"><img src="" alt="product-image"></div>
                            <div class="thumbnail-title">RN 후드<br>20,000</div>
                        </div>
                    </li>
                    <li>
                        <div class="thumbnail-box">
                            <div class="thumbnail-image"><img src="" alt="product-image"></div>
                            <div class="thumbnail-title">시스루 캐주얼 점퍼<br>50,000</div>
                        </div>
                    </li>
                </ul>
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