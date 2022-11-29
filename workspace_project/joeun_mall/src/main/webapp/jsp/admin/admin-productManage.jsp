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
    <title>JoEun-admin 상품조회</title>

    <!--javascript-->

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
            <li><a href="<c:url value='/admin/admin-orderManage.do' />">주문관리</a></li>
            <li><a href="<c:url value='/admin/admin-orderStats.do' />">주문통계</a></li>
            <li class="thispage"><a href="<c:url value='/admin/admin-productManage.do' />">상품관리</a></li>
            <ul class="ul-lower">
                <li class="li-lower thispage"><a href="<c:url value='/admin/admin-productManage.do' />">상품조회</a></li>
                <li class="li-lower"><a href="<c:url value='/admin/admin-productRegistration.do' />">상품등록</a></li>
            </ul>
            <li><a href="<c:url value='/admin/admin-customerManage.do' />">고객관리</a></li>
            <li><a href="<c:url value='/admin/admin-inquiryManage.do' />">1:1문의</a></li>
        </ul>
        <input type="button" value="로그아웃" id="logout-btn" onclick="location.href='/joeunmall/logout.do'">
    </nav>
    <!--admin-nav.css 끝-->
    
    <!--section, article css 제작해야 함-->
    <section id="section">
        <article>
            <div id="top-menu-manage">
                <select class="drop-box">
                    <option hidden>카테고리</option>
                    <option value="cat-00">전상품</option>
                    <option value="cat-01">티셔츠</option>
                    <option value="cat-02">팬츠/스커트</option>
                    <option value="cat-03">원피스</option>
                    <option value="cat-04">니트/가디건</option>
                    <option value="cat-05">자켓</option>
                </select>
                <select class="drop-box">
                    <option hidden>정렬 선택</option>
                    <option value="order-sort">최근주문순 정렬</option>
                    <option value="state-sort">상품명 정렬</option>
                </select>
                <input type="search" id="search" placeholder="상품번호 또는 상품명" />
                <a href="123"><img id="icon_search" src="<c:url value ='/images/icon/icon_search.png' />" alt="돋보기"></a>
            </div>
        </article>
        
        <!-- 
        <div id="modify-button">
            <a href="#"></a><input type="button" value="전체 삭제">
            <a href="#"></a><input type="button" value="선택항목 삭제">
        </div>
         -->
         
        <!-- 테이블 -->
        <article>
            <div id="bottom-menu-productmanage">
                <table>
                    <thead>
                        <tr>
                            <!-- <th class= "th4-width"></th> -->
						    <th>등록일자</th>
                            <th>상품번호</th>
                            <th>카테고리 </th>
                            <!-- <th>상품썸네일</th> -->
                            <th class="th-width">상품명</th> <!-- 너비 조절 -->
                            <th class="th2-width">옵션</th>
                            <th>상품가격</th>
                        </tr>
                    </thead>
                    <!-- 레이아웃 페이지보다 웹페이지가 커서 행 2개 추가 (7개 > 9개) -->
                    <tbody>
					<c:forEach var="productVO" items="${productManageList}" varStatus="st" >
                        <tr>
                            <!-- <td class="th4-width">
                            	<input type="checkbox" id="chk_${st.index}" />
                       		</td> -->
                            <td><fmt:formatDate value="${productVO.productDate}" pattern="yyyy-MM-dd"/> </td> 
                            <td>${productVO.productIndex}</td>
                            <td>${productVO.productCategoryIndex}</td>
							<!-- <td><img src="<c:url value='/thumbnail/${productVO.productImage}' />" /></td> -->
                            <td><a href="<%=request.getContextPath()%>/admin/admin-productDetails.do?productIndex=${productVO.productIndex}">${productVO.productName}</a></td>
                            <td>${productVO.productOptionValue}</td>
                            <td><fmt:formatNumber value="${productVO.productPrice}" pattern="###,###" /></td>
                      	</tr>
                     </c:forEach>
                    </tbody>
                </table>
            </div>
        </article>

<!-- paging -->
			<article>
			<div>
				<c:set var="pageNum" value="${pageMaker.startPage < pageMaker.pageDTO.maxPage ? pageMaker.startPage : pageMaker.pageDTO.maxPage}" />
<%-- 인자확인용	${pageMaker.pageDTO}, ${pageMaker}, ${pageMaker.pageDTO.currentPage == pageMaker.startPage ? "class='active'" : ""}<br>
				${pageNum}, ${pageNum +1}, ${pageNum +2}, ${pageNum +3}, ${pageNum +4} --%>
			</div>
	            <div class="page-wrap">
	                <div class="page-nation">
	                    <a class="arrow pprev" href="<%=request.getContextPath()%>/admin/admin-productManage.do?currentPage=1"></a>
	                    <a class="arrow prev" href="<%=request.getContextPath()%>/admin/admin-productManage.do?currentPage=
	                    ${pageMaker.pageDTO.currentPage-1 < 1 ? '1' : pageMaker.pageDTO.currentPage-1}"></a>
	          			
	                    
	                    
	                    <a href="<%=request.getContextPath()%>/admin/admin-productManage.do?currentPage=${pageNum}"
	                    ${pageMaker.pageDTO.currentPage == pageMaker.startPage ? "class='active'" : ""}>${pageMaker.startPage}</a>
	                    
	                    <a href="<%=request.getContextPath()%>/admin/admin-productManage.do?currentPage=${pageNum+1}"
	                    ${pageMaker.pageDTO.currentPage == pageMaker.startPage+1 ? "class='active'" : ""}>${pageMaker.startPage +1}</a>
	                    
	                    <a href="<%=request.getContextPath()%>/admin/admin-productManage.do?currentPage=${pageNum+2}"
	                    ${pageMaker.pageDTO.currentPage == pageMaker.startPage+2 ? "class='active'" : ""}>${pageMaker.startPage +2}</a>
	                    
	                    <a href="<%=request.getContextPath()%>/admin/admin-productManage.do?currentPage=${pageNum+3}"
	                    ${pageMaker.pageDTO.currentPage == pageMaker.startPage+3 ? "class='active'" : ""}>${pageMaker.startPage +3}</a>
	                    
	                    <a href="<%=request.getContextPath()%>/admin/admin-productManage.do?currentPage=${pageNum+4}"
	                    ${pageMaker.pageDTO.currentPage == pageMaker.startPage+4 ? "class='active'" : ""}>${pageMaker.startPage +4}</a>
	                    
	                    <a class="arrow next" href="<%=request.getContextPath()%>/admin/admin-productManage.do?currentPage=${pageMaker.pageDTO.currentPage+1 < pageMaker.pageDTO.maxPage ? pageMaker.pageDTO.currentPage + 1 : pageMaker.pageDTO.maxPage}"></a>
	                    <a class="arrow nnext" href="<%=request.getContextPath()%>/admin/admin-productManage.do?currentPage=${pageMaker.pageDTO.maxPage}"></a>
	                </div>
	            </div>

        </article>
    </section>
</div>
<!--관리자 페이지 footer 생략-->
</body>
</html>
