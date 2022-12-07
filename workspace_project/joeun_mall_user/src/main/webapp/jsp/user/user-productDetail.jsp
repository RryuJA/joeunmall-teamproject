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
    <title>JoEunMall ${productVO.productName}</title>

    <!--javascript-->
    <script src="<c:url value='/js/jquery-3.6.1.min.js' />"></script>
    <script src="<c:url value='/js/user-productDetail-topBtn.js' />"></script> <!-- topButton -->
    <script src="<c:url value='/js/user-imageChange.js' />"></script>
    <script src="<c:url value='/js/user-productDetail-optionSelect.js' />"></script>
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
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/user-product-detail.css' />">


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
            
            <a href="user-mypageOrder.do"><img src="<c:url value='/images/icon/icon_user.png' />"></a>
            <a href="user-shoppingBasket.do"><img src="<c:url value='/images/icon/icon_shopping_bag.png' />"></a>
            <a href="#"><img src="<c:url value='/images/icon/icon_search.png' />"></a>
        </header>
        
        <article>
            <!-- 사진 넣는 칸 -->
            <section>
                <div class="product-img">
                    <div class="main-img">
                        <img id="img1" class="target1" src="<c:url value='/thumbnail/${productImageList[0].productDetailImage}'/>" data-zoom="2">
                        <img id="img2" class="target2" src="<c:url value='/thumbnail/${productImageList[1].productDetailImage}' />" data-zoom="2">
                        <img id="img3" class="target3" src="<c:url value='/thumbnail/${productImageList[2].productDetailImage}' />" data-zoom="2">
                        <img id="img4" class="target4" src="<c:url value='/thumbnail/${productImageList[3].productDetailImage}' />" data-zoom="2">
                        <img id="img5" class="target5" src="<c:url value='/thumbnail/${productImageList[4].productDetailImage}' />" data-zoom="2">
                    </div>
                    <div id="sub-img">
                    	<p>
                    		<c:if test="${productImageList[0].productDetailImage != null}">
                    			<img id="sub-img1" src="<c:url value='/thumbnail/${productImageList[0].productDetailImage}' />">
                    		</c:if>
                   		</p>
                   		<p>
	                        <c:if test="${productImageList[1].productDetailImage != null}">
	                        	<img id="sub-img2" src="<c:url value='/thumbnail/${productImageList[1].productDetailImage}' />">
	                        </c:if>
                        </p>
                   		<p>
	                       	<c:if test="${productImageList[2].productDetailImage != null}">
	                        	<img id="sub-img3" src="<c:url value='/thumbnail/${productImageList[2].productDetailImage}' />">
	                        </c:if>
                        </p>
                   		<p>
	                        <c:if test="${productImageList[3].productDetailImage != null}">
	                        	<img id="sub-img4" src="<c:url value='/thumbnail/${productImageList[3].productDetailImage}' />">
	                        </c:if>
                        </p>
                   		<p>
	                        <c:if test="${productImageList[4].productDetailImage != null}">
	                        	<img id="sub-img5" src="<c:url value='/thumbnail/${productImageList[4].productDetailImage}' />">
	                        </c:if>
                        </p>
                    </div>
                </div>
            </section>

            <!-- 상품 가격 및 옵션 선택 -->
            <section class="product-select">
                <!-- 상품명 -->
                <div class="productName">
                   	 ${productVO.productName}
                </div>
                <hr width="100%" color="grey">

                <!-- 판매 가격 -->
                <div class="productPrice">판매가
               		<span class="productPriceData">
                   		<fmt:formatNumber value="${productVO.productPrice}" pattern="#,###"/>
                   	</span>
                   	<span class="won1">원</span>    <!-- 판매가격 데이터 들어가야 됨 -->
                </div>

                <!-- 배송비 -->
                <div class="deliveryFee">배송비
                	<span class="deliveryFeeDetail">
                		3,000원 (50,000원 이상 구매 시 무료)
               		</span><!-- 5만원 이상일시 배송비 계산 안되는거 까지 포함시켜야 됨-->
                </div>
                <hr width="100%" color="grey">

                <!-- 옵션 -->
                <div class="option">옵션
                    <select class="optionSelect" id="option-select-box" onchange='changeSelect(); sumTotalPrice(${productVO.productPrice})'>
                    	<option hidden>옵션을 선택하세요</option>
                    	<c:forEach var="productOption" items="${productOptionList}" varStatus="st">
                        	<option value="${productOption.productOptionIndex}">
                        		${productOption.productOptionValue}
                        	</option>
                        </c:forEach>
                    </select> 
                    
                    <script>
                    	//select 박스에서 옵션 선택 시
                    	function changeSelect() {
	                    	const productOption = document.getElementById('option-select-box');
	                    	/* alert("value: "+ productOption.options[productOption.selectedIndex].value 
	                    			+ " text: " + productOption.options[productOption.selectedIndex].text); */
	                  
	                    	document.getElementById('selected-value').innerText = productOption.options[productOption.selectedIndex].text;
	                    	document.getElementById("count").innerText = 1;
	                    			
	                    	jQuery('#hide-div').show();
                    	}
                    	
                    	//+버튼 기능
                    	function countPlus() {
                    		const countElement = document.getElementById("count");
                    		
                    		let countNum = countElement.innerText;
                    		countNum = parseInt(countNum) + 1;
                    		
                    		countElement.innerText = countNum;
                    	}
                    	
                    	//-버튼 기능
                    	function countMinus() {
							const countElement = document.getElementById("count");
                    		
                    		let countNum = countElement.innerText;
                    		if(countNum > 1) {	//이미 숫자가 1일때, 숫자가 더 내려가지 않음
                    			countNum = parseInt(countNum) - 1;
                    		}
                    		
                    		countElement.innerText = countNum;
                    	}          
                    	
                    	function sumTotalPrice(productPrice) {
                    		const totalPriceElement = document.getElementById("total-price");
                    		const countElement = document.getElementById("count");
                    		
                    		let totalPrice = productPrice * countElement.innerText;
                    		let totalPriceString = totalPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
                    		
                    		totalPriceElement.innerText = totalPriceString;                     		
                    	}
                    </script>
                    
                    <div id="hide-div"> 
                    	<!-- 선택된 상품 옵션 -->
                        <div id="selected-option">
                            <table id="option-table">
                                <tr>
                                    <td id="selected-value" class="width-1"></td>
                                    <td>
                                        <div id="option-count">
                                            <input type='button' onclick='countPlus(); sumTotalPrice(${productVO.productPrice})' value='+'/>
                                            <div id='count'>1</div>
                                            <input type='button' onclick='countMinus(); sumTotalPrice(${productVO.productPrice})' value='-'/>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        
                        <!-- 선택된 상품 총 가격 -->
                        <!-- 배송비는 장바구니 총 가격으로 확인함 -->
                        <div class="totalPrice">총 상품 가격
                        	<span class="finalPrice" id="total-price"></span>
                    		<span class="won2">원</span> 
                        </div>
                    </div>
                </div>

				<script>
	            	//콤마찍혀있는숫자 -> 콤마 제거
	            	function stringNumberToInt(stringNumber) {
	            	    return parseInt(stringNumber.replace(",", ""));
	            	}
	            	
					function postShoppingBasket() {
						const totalPriceElement = document.getElementById("total-price");
						
						if(!totalPriceElement.innerText) {
							//옵션을 선택하지 않은 경우
							alert("옵션을 선택해주세요.")
							
						} else {
							//옵션을 선택한 경우
							let confirmResult = window.confirm("장바구니에 상품을 담으시겠습니까?")
							
							if(confirmResult == true) {
								let tempForm = document.createElement('form');
															   
							    const productOption = document.getElementById('option-select-box');
							    let tempProductOptionObj;
							    tempProductOptionObj = document.createElement('input');
							    tempProductOptionObj.setAttribute('type', 'hidden');
							    tempProductOptionObj.setAttribute('name', 'productOption');
							    tempProductOptionObj.setAttribute('value', productOption.options[productOption.selectedIndex].value);
							    console.log(tempProductOptionObj);
							    
							    const productCount = document.getElementById("count").innerText;
							    let tempProductCountObj;
							    tempProductCountObj = document.createElement('input');
							    tempProductCountObj.setAttribute('type', 'hidden');
							    tempProductCountObj.setAttribute('name', 'productCount');
							    tempProductCountObj.setAttribute('value', productCount);
							    console.log(tempProductCountObj);
							    
							    let tempTotalPriceObj;
							    tempTotalPriceObj = document.createElement('input');
							    tempTotalPriceObj.setAttribute('type', 'hidden');
							    tempTotalPriceObj.setAttribute('name', 'totalPrice');
							    tempTotalPriceObj.setAttribute('value', stringNumberToInt(totalPriceElement.innerText));
							    console.log(tempTotalPriceObj);
							    
							    tempForm.appendChild(tempProductOptionObj);
							    tempForm.appendChild(tempProductCountObj);
							    tempForm.appendChild(tempTotalPriceObj);
							    
							    /* 
							    tempForm.setAttribute('method', 'post');
							    tempForm.setAttribute('action', 'view.do');
							    document.body.appendChild(tempForm);
							    tempForm.submit(); 
							    */
							} else {
								console.log("no");
							}
						}

					}
				</script>
                <!-- 장바구니 담기 버튼, 구매하기 버튼 -->
                <div>
                    <input type="button" value="장바구니 담기" id="shoppingList-btn" style="cursor:pointer" onclick="postShoppingBasket()">
                    <input type="button" value="구매하기" id="purchase-btn" style="cursor:pointer" onclick="location.href=''">
                </div>
            </section>

            <!-- 상품 상세 정보 텍스트 박스 -->
            <section class="productInfo-textBox">
                <div class="productInfo"><!-- 얘만 글시체 다르게 적용해야됨 -->
                    PRODUCT INFO
                </div>
                <div>                    
                    ${productVO.productInfo}
                </div>
            </section>

            <!-- 리뷰 칸 -->
            <section class="review-textBox">
                <div class="review">
                    REVIEW
                    
                </div>
            <hr width="100%" color="grey" id="review-line">
                <div>

                </div>
            </section>

            <!-- Top Button -->
            <%-- <a href="#"><img id="topButton" src="<c:url value='/images/icon/topbutton.png' />"></a> --%>

        </article>

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