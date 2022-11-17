<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!--title 페이지에 맞게 수정-->
    <title>JoEun-admin 주문통계표</title>

    <!-- javascript -->
    <script src="<c:url value ='/js/jquery-3.6.1.min.js' />"></script>
    <script src="<c:url value ='/js/admin-orderStats.js' />"></script>
    <script src="<c:url value ='/js/admin-orderTable.js' />"></script>

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
			<hr id="logohr" />
            <ul>
                <!--html 페이지에 해당하는 li태그에 class="thispage" 지정-->
                <!--class가 "li-lower"에 해당하는 경우, 상위 li에도 class="thispage" 지정-->
                <!--ex) <li class="li-lower thispage"> -->
                <li><a href="<c:url value='/admin/admin-orderManage.do' />">주문관리</a></li>
                <li class="thispage"><a href="<c:url value='/admin/admin-orderStats.do' />">주문통계</a></li>
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

        <section>
            <!-- 주문 통계 표 페이지 헤더(?) -->
            <!-- 기간 선택 드랍박스 -->
            <div id="top-menu-manageStats">
	            <div>  <!-- 기간 선택 select -->
	                <%
                	Date now = new Date();
               		Calendar cal1 = Calendar.getInstance();
               		Calendar cal2 = Calendar.getInstance();
               		Calendar cal3 = Calendar.getInstance();
               		Calendar cal4 = Calendar.getInstance();
               		Calendar cal5 = Calendar.getInstance();
               		Calendar cal6 = Calendar.getInstance();
                	SimpleDateFormat sf = new SimpleDateFormat("yyyy년 MM월");
                	SimpleDateFormat sf2 = new SimpleDateFormat("yyMM");
                	cal1.add(Calendar.MONTH, -1);
                    cal2.add(Calendar.MONTH, -2);
                    cal3.add(Calendar.MONTH, -3);
                    cal4.add(Calendar.MONTH, -4);
                    cal5.add(Calendar.MONTH, -5);
                    cal6.add(Calendar.MONTH, -6);
                    %>
                    <select name="selectPeriod" id="select3">
                        <option selected value="allPeriod">전체 기간</option>
                        <option value=<%=sf2.format(cal1.getTime())%>><%=sf.format(cal1.getTime())%></option>
                        <option value=<%=sf2.format(cal2.getTime())%>><%=sf.format(cal2.getTime())%></option>
                        <option value=<%=sf2.format(cal3.getTime())%>><%=sf.format(cal3.getTime())%></option>
                        <option value=<%=sf2.format(cal4.getTime())%>><%=sf.format(cal4.getTime())%></option>
                        <option value=<%=sf2.format(cal5.getTime())%>><%=sf.format(cal5.getTime())%></option>
                        <option value=<%=sf2.format(cal6.getTime())%>><%=sf.format(cal6.getTime())%></option>
	            </div>
	            <div>
	                <!-- 그래프 보기 버튼 -->
	                <input type="button" value="그래프 보기" id="graph-btn" style="cursor:pointer" onclick="location.href='admin-orderStatsGraph.do'">
	            </div>
        	</div>
            <section id="section">
            <!-- 테이블 -->
            <article>
                <div id="bottom-menu-manage">
                    <table>
                        <thead>
                            <tr>
                                <th>No </th>
                                <th>날짜</th>
                                <th class="th2-width">상품명</th> <!-- 너비 조절 -->
                                <th>
                                    <select class="drop-table" name="order-state" id="select4">
                                        <option value="ct-all">전체 의류</option>
                                        <option value="01">티셔츠</option>
                                        <option value="02">팬츠/스커트</option>
                                        <option value="03">원피스</option>
                                        <option value="04">니트/가디건</option>
                                        <option value="05">자켓</option>
                                    </select>
                                </th>
                                <th style="cursor: pointer;" onclick="sortTable(0)">판매수량&nbsp<img src="<c:url value ='/images/icon/icon_sort.png' />" width=20 height=15></th>
                                <th style="cursor: pointer;" onclick="sortTable(1)">개별 가격&nbsp<img src="<c:url value ='/images/icon/icon_sort.png' />" width=20 height=15></th>
                                <th style="cursor: pointer;" onclick="sortTable(2)">총 판매 금액<img src="<c:url value ='/images/icon/icon_sort.png' />" width=20 height=15></th>
                            </tr>
                        </thead>
                        <!-- 레이아웃 페이지보다 웹페이지가 커서 행 2개 추가 (7개 > 9개) -->
                        <tbody>
                        	<c:forEach var="orderStatsVO" items="${orderStatsList}" varStatus="st">
	                            <tr>
	                                <td>${orderStatsVO.orderIndex}</td>
	                                <td>${orderStatsVO.sellPeriod}</td>
	                                <td>${orderStatsVO.productName}</td>
	                                <td>
	                                	<c:choose>
	                                		<c:when test="${orderStatsVO.clothType eq'01'}">
	                                		티셔츠
	                                		</c:when>
	                                		<c:when test="${orderStatsVO.clothType eq'02'}">
	                                		팬츠/스커트
	                                		</c:when>
	                                		<c:when test="${orderStatsVO.clothType eq'03'}">
	                                		원피스
	                                		</c:when>
	                                		<c:when test="${orderStatsVO.clothType eq'04'}">
	                                		니트/가디건
	                                		</c:when>
	                                		<c:when test="${orderStatsVO.clothType eq'05'}">
	                                		자켓
	                                		</c:when>
	                                	</c:choose>
	                                </td>
	                                <td>${orderStatsVO.amount}벌</td>
	                                <td>${orderStatsVO.price}원</td>
	                                <td>${orderStatsVO.total}원</td>
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
	                    <a class="arrow pprev" href="<%=request.getContextPath()%>/admin/admin-orderStats.do?currentPage=1"></a>
	                    <a class="arrow prev" href="<%=request.getContextPath()%>/admin/admin-orderStats.do?currentPage=
	                    ${pageMaker.pageDTO.currentPage-1 < 1 ? '1' : pageMaker.pageDTO.currentPage-1}"></a>
	          			
	                    
	                    
	                    <a href="<%=request.getContextPath()%>/admin/admin-orderStats.do?currentPage=${pageNum}"
	                    ${pageMaker.pageDTO.currentPage == pageMaker.startPage ? "class='active'" : ""}>${pageMaker.startPage}</a>
	                    
	                    <a href="<%=request.getContextPath()%>/admin/admin-orderStats.do?currentPage=${pageNum+1}"
	                    ${pageMaker.pageDTO.currentPage == pageMaker.startPage+1 ? "class='active'" : ""}>${pageMaker.startPage +1}</a>
	                    
	                    <a href="<%=request.getContextPath()%>/admin/admin-orderStats.do?currentPage=${pageNum+2}"
	                    ${pageMaker.pageDTO.currentPage == pageMaker.startPage+2 ? "class='active'" : ""}>${pageMaker.startPage +2}</a>
	                    
	                    <a href="<%=request.getContextPath()%>/admin/admin-orderStats.do?currentPage=${pageNum+3}"
	                    ${pageMaker.pageDTO.currentPage == pageMaker.startPage+3 ? "class='active'" : ""}>${pageMaker.startPage +3}</a>
	                    
	                    <a href="<%=request.getContextPath()%>/admin/admin-orderStats.do?currentPage=${pageNum+4}"
	                    ${pageMaker.pageDTO.currentPage == pageMaker.startPage+4 ? "class='active'" : ""}>${pageMaker.startPage +4}</a>
	                    
	                    <a class="arrow next" href="<%=request.getContextPath()%>/admin/admin-orderStats.do?currentPage=${pageMaker.pageDTO.currentPage+1 < pageMaker.pageDTO.maxPage ? pageMaker.pageDTO.currentPage + 1 : pageMaker.pageDTO.maxPage}"></a>
	                    <a class="arrow nnext" href="<%=request.getContextPath()%>/admin/admin-orderStats.do?currentPage=${pageMaker.pageDTO.maxPage}"></a>
	                </div>
	            </div>

        </article>
            
        </section>
    </div>

</body>

</html>