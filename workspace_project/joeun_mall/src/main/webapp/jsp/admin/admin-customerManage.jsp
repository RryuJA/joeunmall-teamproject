<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko-kr">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!--title 페이지에 맞게 수정-->
    <title>JoEun-admin 고객관리</title>

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
            <li><a href="<c:url value ='/admin/admin-orderManage.do' />">주문관리</a></li>
            <li><a href="<c:url value ='/admin/admin-orderStats.do' />">주문통계</a></li>
            <li><a href="<c:url value ='/admin/admin-productManage.do' />">상품관리</a></li>
            <ul class="ul-lower">
                <li class="li-lower"><a href="<c:url value ='/admin/admin-productManage.do' />">상품조회</a></li>
                <li class="li-lower"><a href="<c:url value ='/admin/admin-productRegistration.do' />">상품등록</a></li>
            </ul>
            <li class="thispage"><a href="<c:url value ='/admin/admin-customerManage.do' />">고객관리</a></li>
            <li><a href="<c:url value ='/admin/admin-inquiryManage.do' />">1:1문의</a></li>
        </ul>
        <input type="button" value="로그아웃" id="logout-btn" onclick="location.href='/joeunmall/logout.do'">
    </nav>
    <!--admin-nav.css 끝-->
    
    <!--section, article css 제작해야 함-->
    <section id="section">
            <div id="top-menu-manage">
            	<form method="get" action="<c:url value ='/admin/admin-customerManageSearch.do' />">
                    <input type="text" id="search" name="searchWord" placeholder="고객명" />
                </form>
            </div>
        <!-- 테이블 -->
        <article>
            <div id="bottom-menu-manage">
            <table>
                <thead>
                    <tr>
                        <th>고객번호</th>
                        <th>고객명</th>
                        <th class="th2_width">가입일</th> <!-- 너비 조절 -->
                        <th>생년월일</th>
                        <th>성별</th>
                        <th class= "th-width">연락처</th>
                        <th class="th6-width">이메일</th>
                    </tr>
                </thead>
                <!-- 레이아웃 페이지보다 웹페이지가 커서 행 2개 추가 (7개 > 9개) -->
                <tbody>
					<c:forEach var="UserVO" items="${customerManageList}" varStatus="st" >
	                    <tr>
	                        <!-- <td><a onclick="window.open('./admin-customerInfo.html', 'window_name', 'width=710, height=510, location=no, status=no, scrollbars=yes');" style="cursor:pointer">주문번호</a></td> -->
	                        <td>${UserVO.userIndex}</td>
	                        <td>${UserVO.userName}</td>
	                        <td><fmt:formatDate value="${UserVO.userDate}" pattern="yyyy-MM-dd" /></td>
	                        <td><fmt:formatDate value="${UserVO.userBirth}" pattern="yyyy-MM-dd" /></td>
	                        <td>${UserVO.userGender}</td>
	                        <td>${UserVO.userMobile}</td>
	                        <td>${UserVO.userMail}</td>
	                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>        
        <!-- //paging -->
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
		                    <a class="arrow pprev" href="<%=request.getContextPath()%>/admin/admin-customerManage.do?currentPage=1"></a>
		                    <a class="arrow prev" href="<%=request.getContextPath()%>/admin/admin-customerManage.do?currentPage=
		                    ${pageMaker.pageDTO.currentPage-1 < 1 ? '1' : pageMaker.pageDTO.currentPage-1}"></a>
		                    
		                    <a href="<%=request.getContextPath()%>/admin/admin-customerManage.do?currentPage=${pageNum}"
		                    ${pageMaker.pageDTO.currentPage == pageMaker.startPage ? "class='active'" : ""}>${pageMaker.startPage}</a>
		                    
		                    <a href="<%=request.getContextPath()%>/admin/admin-customerManage.do?currentPage=${pageNum+1}"
		                    ${pageMaker.pageDTO.currentPage == pageMaker.startPage+1 ? "class='active'" : ""}>${pageMaker.startPage +1}</a>
		                    
		                    <a href="<%=request.getContextPath()%>/admin/admin-customerManage.do?currentPage=${pageNum+2}"
		                    ${pageMaker.pageDTO.currentPage == pageMaker.startPage+2 ? "class='active'" : ""}>${pageMaker.startPage +2}</a>
		                    
		                    <a href="<%=request.getContextPath()%>/admin/admin-customerManage.do?currentPage=${pageNum+3}"
		                    ${pageMaker.pageDTO.currentPage == pageMaker.startPage+3 ? "class='active'" : ""}>${pageMaker.startPage +3}</a>
		                    
		                    <a href="<%=request.getContextPath()%>/admin/admin-customerManage.do?currentPage=${pageNum+4}"
		                    ${pageMaker.pageDTO.currentPage == pageMaker.startPage+4 ? "class='active'" : ""}>${pageMaker.startPage +4}</a>
		                    
		                    <a class="arrow next" href="<%=request.getContextPath()%>/admin/admin-customerManage.do?currentPage=${pageMaker.pageDTO.currentPage+1 < pageMaker.pageDTO.maxPage ? pageMaker.pageDTO.currentPage + 1 : pageMaker.pageDTO.maxPage}"></a>
		                    <a class="arrow nnext" href="<%=request.getContextPath()%>/admin/admin-customerManage.do?currentPage=${pageMaker.pageDTO.maxPage}"></a>
		                </div>
		            </div>
		            </c:if>
		        <!-- //기본 페이징 -->
		        
		        <!-- 검색페이징 -->
				<c:if test="${not empty searchWord}">
					<div>
				<c:set var="pageNum" value="${pageMaker.startPage < pageMaker.pageDTO.maxPage ? pageMaker.startPage : pageMaker.pageDTO.maxPage}" />
				</div>
		            <div class="page-wrap">
		                <div class="page-nation">
		                    <a class="arrow pprev" href="<%=request.getContextPath()%>/admin/admin-customerManageSearch.do?currentPage=1&searchWord=${searchWord}"></a>
		                    <a class="arrow prev" href="<%=request.getContextPath()%>/admin/admin-customerManageSearch.do?currentPage=
		                    ${pageMaker.pageDTO.currentPage-1 < 1 ? '1' : pageMaker.pageDTO.currentPage-1}&searchWord=${searchWord}"></a>
		                    
		                    <a href="<%=request.getContextPath()%>/admin/admin-customerManageSearch.do?currentPage=${pageNum}&searchWord=${searchWord}"
		                    ${pageMaker.pageDTO.currentPage == pageMaker.startPage ? "class='active'" : ""}>${pageMaker.startPage}</a>
		                    
		                    <a href="<%=request.getContextPath()%>/admin/admin-customerManageSearch.do?currentPage=${pageNum+1}&searchWord=${searchWord}"
		                    ${pageMaker.pageDTO.currentPage == pageMaker.startPage+1 ? "class='active'" : ""}>${pageMaker.startPage +1}</a>
		                    
		                    <a href="<%=request.getContextPath()%>/admin/admin-customerManageSearch.do?currentPage=${pageNum+2}&searchWord=${searchWord}"
		                    ${pageMaker.pageDTO.currentPage == pageMaker.startPage+2 ? "class='active'" : ""}>${pageMaker.startPage +2}</a>
		                    
		                    <a href="<%=request.getContextPath()%>/admin/admin-customerManageSearch.do?currentPage=${pageNum+3}&searchWord=${searchWord}"
		                    ${pageMaker.pageDTO.currentPage == pageMaker.startPage+3 ? "class='active'" : ""}>${pageMaker.startPage +3}</a>
		                    
		                    <a href="<%=request.getContextPath()%>/admin/admin-customerManageSearch.do?currentPage=${pageNum+4}&searchWord=${searchWord}"
		                    ${pageMaker.pageDTO.currentPage == pageMaker.startPage+4 ? "class='active'" : ""}>${pageMaker.startPage +4}</a>
		                    
		                    <a class="arrow next" href="<%=request.getContextPath()%>/admin/admin-customerManageSearch.do?currentPage=${pageMaker.pageDTO.currentPage+1 < pageMaker.pageDTO.maxPage ? pageMaker.pageDTO.currentPage + 1 : pageMaker.pageDTO.maxPage}&searchWord=${searchWord}"></a>
		                    <a class="arrow nnext" href="<%=request.getContextPath()%>/admin/admin-customerManageSearch.do?currentPage=${pageMaker.pageDTO.maxPage}&searchWord=${searchWord}"></a>
		                </div>
		            </div>
				</c:if>
			<!-- //검색페이징 -->
	        </article>
	    </section>
	</div>
<!--관리자 페이지 footer 생략-->
</body>
</html>