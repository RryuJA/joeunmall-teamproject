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
    <title>JoEun_admin 주문관리</title>

    <!--javascript-->
	<script src="<c:url value='/jquery/jquery.min.js' />" ></script>
	<script>
	$(function(){
		//id="orderState${st.index}"
		//주문관리-진행상태-selectBOX제어(인자 전송_ajax)
		$("select[id^=orderState]").change(function(e){
			var targetId = e.currentTarget.id;
			
			//선택상자의 id를 확인하는 코드
			console.log("targetID: " + targetId);
			//선택된 값을 확인
			console.log("값 : " + $("#"+targetId).val());
			
			//주문번호를 추출해서 확인
			var num = targetId.substring(10);
			var orderIndex = "orderIndex" + num; //주문번호 
			 console.log("주문번호: " + $("#"+orderIndex).text());
			 
			//전송
			 $.ajax({
				url: "${pageContext.request.contextPath}/admin/admin-orderStateUpdate.do",
				type: 'GET',
				data: {
					orderIndex : $("#"+orderIndex).text() , 
					orderStateIndex : $("#"+targetId).val()
				}, 
				success(result){
					console.log("result: " + result);
					alert(result);
				}
				 
			 })

			 
		});
		
 	
	
	});
	</script>
	
    <!--css-->
    <!--admin-nav.css는 고정-->
    <!--페이지별로 추가해야하는 css 파일은 admin-nav.css 아래에 추가-->
    <link rel="stylesheet" type="text/css" href="<c:url value ='/css/admin-nav.css' />">
    <link rel="stylesheet" type="text/css" href="<c:url value ='/css/admin-section-manage.css' />">
</head>
<body>
<div id="wrap">
    <!--관리자 페이지 header 생략-->
    <nav>
        <!--a 태그 href는 추후 페이지 완성 후 수정-->
        <a href = "<c:url value='/admin/admin-orderManage.do' />">
			<img id="logo" src="<c:url value ='/images/logo/logo_transparent.png'/>" alt="JoEunMall">
		</a>        
		<hr id="logohr"/>
        <ul>
            <!--html 페이지에 해당하는 li태그에 class="thispage" 지정-->
            <!--class가 "li-lower"에 해당하는 경우, 상위 li에도 class="thispage" 지정-->
            <!--ex) <li class="li-lower thispage"> -->
<!--             <li class="thispage"><a href="admin-orderManage.html">주문관리</a></li> -->
            <li class="thispage"><a href="<c:url value='/admin/admin-orderManage.do' />">주문관리</a></li>
            <li><a href="<c:url value='/admin/admin-orderStats.do' />">주문통계</a></li>
            <li><a href="<c:url value='/admin/admin-productManage.do' />">상품관리</a></li>
            <ul class="ul-lower">
                <li class="li-lower"><a href="<c:url value='/admin/admin-productManage.do' />">상품조회</a></li>
                <li class="li-lower"><a href="<c:url value='/admin/admin-productRegistration.do' />">상품등록</a></li>
            </ul>
            <li><a href="<c:url value='/admin/admin-customerManage.do' />">고객관리</a></li>
            <li><a href="<c:url value='/admin/admin-inquiryManage.do' />">1:1문의</a></li>
        </ul>
        <input type="button" value="로그아웃" id="logout-btn" onclick="location.href='/joeunmall/logout.do'">
    </nav>
    <!--admin-nav.css 끝-->
    
    <section id="section">
        <div id="top-menu-manage">
            <form method="get" action="<c:url value = '/admin/admin-orderManageSearch.do' />" >
                <input type="search" id="search" name="searchWord" placeholder="고객명" />
                <a href="123"><img id="icon_search" src="<c:url value ='/images/icon/icon_search.png' />" alt="돋보기"></a>
           	</form>
        </div>
        <!-- 테이블 -->
        <article>
            <div id="bottom-menu-manage">
                <table>
                    <thead>
                        <tr>
                            <th>주문번호 </th>
                            <th>주문자명</th>
                            <th class="th2-width">상품명</th> <!-- 너비 조절 -->
                            <th>총 주문수량</th>
                            <th>총 상품가격</th>
                            <th>주문일자</th>
                            <th class="th1-width">진행상태</th>
                        </tr>
                    </thead>
                    <!-- 레이아웃 페이지보다 웹페이지가 커서 행 2개 추가 (7개 > 9개) -->
                    <tbody>
                    	<c:forEach var="orderManageVO" items="${orderManageList}" varStatus="st">
                    		
	                    	<tr>                            
		                        <td><div id="orderIndex${st.index}">${orderManageVO.orderIndex}</div></td>
		                        <td>${orderManageVO.orderName}</td>
								<td>${orderManageVO.productNames.get(0)} 외 ${orderManageVO.productNames.size()-1}개</td>
		                        <td>${orderManageVO.productCountSum}</td>
		                        <td><fmt:formatNumber value="${orderManageVO.orderPrice}" pattern="###,###" /></td>
		                        <td><fmt:formatDate value= "${orderManageVO.orderDate}" pattern="yyyy-MM-dd"/></td>
		                        <td>	
		                         	<select name="order-state" id="orderState${st.index}" class="drop-table">
	                                    <option value="STA1">판매자 확인중</option>
	                                    <option value="STA2">상품준비</option>
	                                    <option value="STA3">배송처리</option>
	                                    <option value="STA4">배송완료</option>
	                                    <option value="STA5">주문취소</option>
	                                    <option value="STA6">반품 처리중</option>
	                                    <option value="STA7">환불완료</option>
	                                    <option value="STA8">교환 처리중</option>
	                                    <option value="STA9">교환완료</option>
                                	</select>     
                                	
                                	<script>
                                	function setOrderStateIndex(idx, stateIndex){
                                		
                                		console.log("-------------------------------------");
                                		
                                		var selectBox = document.getElementById(idx);
                                		
                                		console.log("stateIndex : " + stateIndex);
                                		var selectedLabelIndex;
                                		
                                		for (var i=0; i<selectBox.length; i++) {
                                			
                                			if (selectBox[i].value == stateIndex) {
                                				
                                				console.log("인자와 일치하는 옵션 index : " + i);
                                				console.log("인자와 일치하는 옵션 라벨 : " + selectBox[i].label);
                                				console.log("인자와 일치하는 옵션 값 : " + selectBox[i].value);
                                				
                                				selectedLabelIndex = i;
                                				break;
                                			}
                                			
                                		} // for
                                		
                                		console.log("선택된 상자의 옵션 index : " + selectedLabelIndex);
                                		selectBox.selectedIndex = selectedLabelIndex;
                                    }
		                    		
                                	setOrderStateIndex("orderState${st.index}", "${orderManageVO.orderStateIndex}");
				                    </script>                        
                           		</td>
			               </tr>
                   		</c:forEach>
                    </tbody>
                </table>
            </div>
        </article>

		<!-- paging -->
			<article>
			<!-- 기본 페이징 -->
			<c:if test="${empty searchWord}">
				<div>
					<c:set var="pageNum" value="${pageMaker.startPage < pageMaker.pageDTO.maxPage ? pageMaker.startPage : pageMaker.pageDTO.maxPage}" />
	<%-- 인자확인용	${pageMaker.pageDTO}, ${pageMaker}, ${pageMaker.pageDTO.currentPage == pageMaker.startPage ? "class='active'" : ""}<br>
					${pageNum}, ${pageNum +1}, ${pageNum +2}, ${pageNum +3}, ${pageNum +4} --%>
				</div>
		            <div class="page-wrap">
		                <div class="page-nation">
		                    <a class="arrow pprev" href="<%=request.getContextPath()%>/admin/admin-orderManage.do?currentPage=1"></a>
		                    <a class="arrow prev" href="<%=request.getContextPath()%>/admin/admin-orderManage.do?currentPage=
		                    ${pageMaker.pageDTO.currentPage-1 < 1 ? '1' : pageMaker.pageDTO.currentPage-1}"></a>
		                    
		                    <a href="<%=request.getContextPath()%>/admin/admin-orderManage.do?currentPage=${pageNum}"
		                    ${pageMaker.pageDTO.currentPage == pageMaker.startPage ? "class='active'" : ""}>${pageMaker.startPage}</a>
		                    
		                    <a href="<%=request.getContextPath()%>/admin/admin-orderManage.do?currentPage=${pageNum+1}"
		                    ${pageMaker.pageDTO.currentPage == pageMaker.startPage+1 ? "class='active'" : ""}>${pageMaker.startPage +1}</a>
		                    
		                    <a href="<%=request.getContextPath()%>/admin/admin-orderManage.do?currentPage=${pageNum+2}"
		                    ${pageMaker.pageDTO.currentPage == pageMaker.startPage+2 ? "class='active'" : ""}>${pageMaker.startPage +2}</a>
		                    
		                    <a href="<%=request.getContextPath()%>/admin/admin-orderManage.do?currentPage=${pageNum+3}"
		                    ${pageMaker.pageDTO.currentPage == pageMaker.startPage+3 ? "class='active'" : ""}>${pageMaker.startPage +3}</a>
		                    
		                    <a href="<%=request.getContextPath()%>/admin/admin-orderManage.do?currentPage=${pageNum+4}"
		                    ${pageMaker.pageDTO.currentPage == pageMaker.startPage+4 ? "class='active'" : ""}>${pageMaker.startPage +4}</a>
		                    
		                    <a class="arrow next" href="<%=request.getContextPath()%>/admin/admin-orderManage.do?currentPage=${pageMaker.pageDTO.currentPage+1 < pageMaker.pageDTO.maxPage ? pageMaker.pageDTO.currentPage + 1 : pageMaker.pageDTO.maxPage}"></a>
		                    <a class="arrow nnext" href="<%=request.getContextPath()%>/admin/admin-orderManage.do?currentPage=${pageMaker.pageDTO.maxPage}"></a>
		                </div>
		            </div>
	            </c:if>
	        <!-- 검색페이징 -->
			<c:if test="${not empty searchWord}">
				<div>
					<c:set var="pageNum" value="${pageMaker.startPage < pageMaker.pageDTO.maxPage ? pageMaker.startPage : pageMaker.pageDTO.maxPage}" />
				</div>
            	<div class="page-wrap">
                	<div class="page-nation">
	                    <a class="arrow pprev" href="<%=request.getContextPath()%>/admin/admin-orderManageSearch.do?currentPage=1&searchWord=${searchWord}"></a>
	                    <a class="arrow prev" href="<%=request.getContextPath()%>/admin/admin-orderManageSearch.do?currentPage=
	                    ${pageMaker.pageDTO.currentPage-1 < 1 ? '1' : pageMaker.pageDTO.currentPage-1}&searchWord=${searchWord}"></a>
	                    
	                    <a href="<%=request.getContextPath()%>/admin/admin-orderManageSearch.do?currentPage=${pageNum}&searchWord=${searchWord}"
	                    ${pageMaker.pageDTO.currentPage == pageMaker.startPage ? "class='active'" : ""}>${pageMaker.startPage}</a>
	                    
	                    <a href="<%=request.getContextPath()%>/admin/admin-orderManageSearch.do?currentPage=${pageNum+1}&searchWord=${searchWord}"
	                    ${pageMaker.pageDTO.currentPage == pageMaker.startPage+1 ? "class='active'" : ""}>${pageMaker.startPage +1}</a>
	                    
	                    <a href="<%=request.getContextPath()%>/admin/admin-orderManageSearch.do?currentPage=${pageNum+2}&searchWord=${searchWord}"
	                    ${pageMaker.pageDTO.currentPage == pageMaker.startPage+2 ? "class='active'" : ""}>${pageMaker.startPage +2}</a>
	                    
	                    <a href="<%=request.getContextPath()%>/admin/admin-orderManageSearch.do?currentPage=${pageNum+3}&searchWord=${searchWord}"
	                    ${pageMaker.pageDTO.currentPage == pageMaker.startPage+3 ? "class='active'" : ""}>${pageMaker.startPage +3}</a>
	                    
	                    <a href="<%=request.getContextPath()%>/admin/admin-orderManageSearch.do?currentPage=${pageNum+4}&searchWord=${searchWord}"
	                    ${pageMaker.pageDTO.currentPage == pageMaker.startPage+4 ? "class='active'" : ""}>${pageMaker.startPage +4}</a>
	                    
	                    <a class="arrow next" href="<%=request.getContextPath()%>/admin/admin-orderManageSearch.do?currentPage=${pageMaker.pageDTO.currentPage+1 < pageMaker.pageDTO.maxPage ? pageMaker.pageDTO.currentPage + 1 : pageMaker.pageDTO.maxPage}&searchWord=${searchWord}"></a>
	                    <a class="arrow nnext" href="<%=request.getContextPath()%>/admin/admin-orderManageSearch.do?currentPage=${pageMaker.pageDTO.maxPage}&searchWord=${searchWord}"></a>
               		 </div>
           		 </div>
			</c:if>
			<!-- //검색페이징 --> 	          
        </article>
</div>
<!--관리자 페이지 footer 생략-->
</body>
</html>
