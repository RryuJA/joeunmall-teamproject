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
    <script src="<c:url value='/js/bootstrap-5.0.2-js/bootstrap.bundle.js' />"></script>
    
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
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/user-header-main.css' />">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/user-footer.css' />">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/user-product-list.css' />">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/bootstrap-5.0.2-css/bootstrap.css' />">

</head>

<body>
    <div id="wrap">
        <header>
            <a href="user-productlistCarousel.do"><img id="logo" src="<c:url value='/images/logo/logo_transparent.png' />" alt="JoEunMall"></a>
            <ul id="header">
                <li><a href="<c:url value='/user/user-productlistCarousel.do?currentPage=1'/>">전상품</a></li>
                <li><a href="<c:url value='/user/user-productlistCarouselCategory.do?currentPage=1&productCategoryIndex=01'/>">티셔츠</a></li>
                <li><a href="<c:url value='/user/user-productlistCarouselCategory.do?currentPage=1&productCategoryIndex=02'/>">팬츠/스커트</a></li>
                <li><a href="<c:url value='/user/user-productlistCarouselCategory.do?currentPage=1&productCategoryIndex=03'/>">원피스</a></li>
                <li><a href="<c:url value='/user/user-productlistCarouselCategory.do?currentPage=1&productCategoryIndex=04'/>">니트/가디건</a></li>
                <li><a href="<c:url value='/user/user-productlistCarouselCategory.do?currentPage=1&productCategoryIndex=05'/>">자켓</a></li>
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
        
        <section>
            <!--슬라이드 이미지(캐러셀)-->
            <article>
                <!-- <img id="carousel" src="" alt="carousel-image"> -->
                <div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            <img src="<c:url value='/images/carousel/image-carousel-1.jpeg' />" class="carousel-image" alt="carousel-image1">
                        </div>
                        <div class="carousel-item">
                            <img src="<c:url value='/images/carousel/image-carousel-2.jpeg' />" class="carousel-image" alt="carousel-image2">
                        </div>
                        <div class="carousel-item">
                            <img src="<c:url value='/images/carousel/image-carousel-3.jpeg' />" class="carousel-image" alt="carousel-image3">
                        </div>
                    </div>
                    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Previous</span>
                    </button>
                    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Next</span>
                    </button>
                </div>
            </article>

			<a name="product-list"></a>
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
                <c:forEach items="${products}" var="product" varStatus="st" begin="0" end="3">
                    <li>
                        <div class="thumbnail-box">
                            <div class="thumbnail-image">
                            	<img src="<c:url value='/thumbnail/${product.productImage}'/>" alt="product-image">
                           	</div>
                            <div class="thumbnail-title">${product.productName}
                            	<br><fmt:formatNumber value="${product.productPrice}" pattern="#,###"/></div>
                        </div>
                    </li>
                </c:forEach>
                </ul>
                
                <ul class="list">
                <c:forEach items="${products}" var="product" varStatus="st" begin="4" end="7">
                    <li>
                        <div class="thumbnail-box">
                            <div class="thumbnail-image">
                            	<img src="<c:url value='/thumbnail/${product.productImage}'/>" alt="product-image">
                           	</div>
                            <div class="thumbnail-title">${product.productName}
                            	<br><fmt:formatNumber value="${product.productPrice}" pattern="#,###"/></div>
                        </div>
                    </li>
                </c:forEach>
                </ul>
            </article>

<!-- paging -->
			<article>
				<div>
					<c:set var="pageNum" value="${pageMaker.startPage < pageMaker.pageDTO.maxPage ? pageMaker.startPage : pageMaker.pageDTO.maxPage}" />
	<%--  인자확인용	${pageMaker.pageDTO}, ${pageMaker}, ${pageMaker.pageDTO.currentPage == pageMaker.startPage ? "class='active'" : ""}<br>
					${pageNum}, ${pageNum +1}, ${pageNum +2}, ${pageNum +3}, ${pageNum +4}  --%>
				</div>
				
	            <div class="page-wrap">
	            
	            	<!-- 전상품 -->
	            	<c:if test="${empty productCategoryIndex}">
	                <div class="page-nation">
	                    <a class="arrow pprev" href="<%=request.getContextPath()%>/user/user-productlistCarousel.do?currentPage=1"></a>
	                    <a class="arrow prev" href="<%=request.getContextPath()%>/user/user-productlistCarousel.do?currentPage=
	                    	${pageMaker.pageDTO.currentPage-1 < 1 ? '1' : pageMaker.pageDTO.currentPage-1}#product-list"></a>
	          			
	                    
	                    
	                    <a href="<%=request.getContextPath()%>/user/user-productlistCarousel.do?currentPage=${pageNum}#product-list"
	                    	${pageMaker.pageDTO.currentPage == pageMaker.startPage ? "class='active'" : ""}>${pageMaker.startPage}</a>
	                    
	                    <a href="<%=request.getContextPath()%>/user/user-productlistCarousel.do?currentPage=${pageNum+1}#product-list"
	                    	${pageMaker.pageDTO.currentPage == pageMaker.startPage+1 ? "class='active'" : ""}>${pageMaker.startPage +1}</a>
	                    
	                    <a href="<%=request.getContextPath()%>/user/user-productlistCarousel.do?currentPage=${pageNum+2}#product-list"
	                    	${pageMaker.pageDTO.currentPage == pageMaker.startPage+2 ? "class='active'" : ""}>${pageMaker.startPage +2}</a>
	                    
	                    <a href="<%=request.getContextPath()%>/user/user-productlistCarousel.do?currentPage=${pageNum+3}#product-list"
	                    	${pageMaker.pageDTO.currentPage == pageMaker.startPage+3 ? "class='active'" : ""}>${pageMaker.startPage +3}</a>
	                    
	                    <a href="<%=request.getContextPath()%>/user/user-productlistCarousel.do?currentPage=${pageNum+4}#product-list"
	                    	${pageMaker.pageDTO.currentPage == pageMaker.startPage+4 ? "class='active'" : ""}>${pageMaker.startPage +4}</a>
	                    
	                    <a class="arrow next" href="<%=request.getContextPath()%>/user/user-productlistCarousel.do?currentPage=${pageMaker.pageDTO.currentPage+1 < pageMaker.pageDTO.maxPage ? pageMaker.pageDTO.currentPage + 1 : pageMaker.pageDTO.maxPage}#product-list"></a>
	                    <a class="arrow nnext" href="<%=request.getContextPath()%>/user/user-productlistCarousel.do?currentPage=${pageMaker.pageDTO.maxPage}#product-list"></a>
	                </div>
	                </c:if>
	                <!--// 전상품 -->
	                
	                <!-- 카테고리별 상품 -->
	            	<c:if test="${not empty productCategoryIndex}">
	                <div class="page-nation">
	                    <a class="arrow pprev" href="<%=request.getContextPath()%>/user/user-productlistCarouselCategory.do?currentPage=1&productCategoryIndex=${productCategoryIndex}"></a>
	                    <a class="arrow prev" href="<%=request.getContextPath()%>/user/user-productlistCarouselCategory.do?currentPage=
	                    	${pageMaker.pageDTO.currentPage-1 < 1 ? '1' : pageMaker.pageDTO.currentPage-1}&productCategoryIndex=${productCategoryIndex}#product-list"></a>
	          			
	                    
	                    
	                    <a href="<%=request.getContextPath()%>/user/user-productlistCarouselCategory.do?currentPage=${pageNum}&productCategoryIndex=${productCategoryIndex}#product-list"
	                    	${pageMaker.pageDTO.currentPage == pageMaker.startPage ? "class='active'" : ""}>${pageMaker.startPage}</a>
	                    
	                    <a href="<%=request.getContextPath()%>/user/user-productlistCarouselCategory.do?currentPage=${pageNum+1}&productCategoryIndex=${productCategoryIndex}#product-list"
	                    	${pageMaker.pageDTO.currentPage == pageMaker.startPage+1 ? "class='active'" : ""}>${pageMaker.startPage +1}</a>
	                    
	                    <a href="<%=request.getContextPath()%>/user/user-productlistCarouselCategory.do?currentPage=${pageNum+2}&productCategoryIndex=${productCategoryIndex}#product-list"
	                    	${pageMaker.pageDTO.currentPage == pageMaker.startPage+2 ? "class='active'" : ""}>${pageMaker.startPage +2}</a>
	                    
	                    <a href="<%=request.getContextPath()%>/user/user-productlistCarouselCategory.do?currentPage=${pageNum+3}&productCategoryIndex=${productCategoryIndex}#product-list"
	                    	${pageMaker.pageDTO.currentPage == pageMaker.startPage+3 ? "class='active'" : ""}>${pageMaker.startPage +3}</a>
	                    
	                    <a href="<%=request.getContextPath()%>/user/user-productlistCarouselCategory.do?currentPage=${pageNum+4}&productCategoryIndex=${productCategoryIndex}#product-list"
	                    	${pageMaker.pageDTO.currentPage == pageMaker.startPage+4 ? "class='active'" : ""}>${pageMaker.startPage +4}</a>
	                    
	                    <a class="arrow next" href="<%=request.getContextPath()%>/user/user-productlistCarouselCategory.do?currentPage=${pageMaker.pageDTO.currentPage+1 < pageMaker.pageDTO.maxPage ? pageMaker.pageDTO.currentPage + 1 : pageMaker.pageDTO.maxPage}&productCategoryIndex=${productCategoryIndex}#product-list"></a>
	                    <a class="arrow nnext" href="<%=request.getContextPath()%>/user/user-productlistCarouselCategory.do?currentPage=${pageMaker.pageDTO.maxPage}&productCategoryIndex=${productCategoryIndex}#product-list"></a>
	                </div>
	                </c:if>
	                <!--// 카테고리별 상품 -->
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
                <p>팀원 : 류정아, 이승은, 조규성, 박재민</p>
            </div>
        </footer>
    </div>
</body>
</html>