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
        	<a href = "<%=request.getContextPath()%>/orderManage.do?admin=team3">
				<img id="logo" src="<c:url value ='/images/logo/logo_transparent.png'/>" alt="JoEunMall">
			</a>                    
			<hr id="logohr" />
            <ul>
                <!--html 페이지에 해당하는 li태그에 class="thispage" 지정-->
                <!--class가 "li-lower"에 해당하는 경우, 상위 li에도 class="thispage" 지정-->
                <!--ex) <li class="li-lower thispage"> -->
                <li><a href="admin-orderManage.do">주문관리</a></li>
                <li class="thispage"><a href="admin-orderStats.do">주문통계</a></li>
                <li><a href="admin-productManage.do">상품관리</a></li>
                <ul class="ul-lower">
                    <li class="li-lower"><a href="admin-productManage.do">상품조회</a></li>
                    <li class="li-lower"><a href="admin-productRegistration.do">상품등록</a></li>
                </ul>
                <li><a href="admin-customerManage.do">고객관리</a></li>
                <li><a href="admin-inquiryManage.do">1:1문의</a></li>
            </ul>
            <input type="button" value="로그아웃" id="logout-btn" onclick="location.href='/joeunmall/logout.do'">
        </nav>
        <!--admin-nav.css 끝-->

        <section>
            <!-- 주문 통계 표 페이지 헤더(?) -->
            <!-- 기간 선택 드랍박스 -->
            <div id="top-menu-manageStats">
	            <div>  <!-- 기간 선택 select -->
	                <select name="selectPeriod" id="select3">
	                    <option selected>전체 기간</option>
	                    <option value="2209">2022년 9월</option>
	                    <option value="2208">2022년 8월</option>
	                    <option value="2207">2022년 7월</option>
	                    <option value="2206">2022년 6월</option>
	                </select>
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
                                    <select name="order-state" class="drop-table">
                                        <option value="os-1">전체 의류</option>
                                        <option value="os-2">티셔츠</option>
                                        <option value="os-3">팬츠/스커트</option>
                                        <option value="os-4">원피스</option>
                                        <option value="os-5">니트/가디건</option>
                                        <option value="os-6">자켓</option>
                                    </select>
                                </th>
                                <th style="cursor: pointer;" onclick="sortTable(0)">판매수량&nbsp<img src="<c:url value ='/images/icon/icon_sort.png' />" width=20 height=15></th>
                                <th style="cursor: pointer;" onclick="sortTable(1)">개별 가격&nbsp<img src="<c:url value ='/images/icon/icon_sort.png' />" width=20 height=15></th>
                                <th style="cursor: pointer;" onclick="sortTable(2)">총 판매 금액<img src="<c:url value ='/images/icon/icon_sort.png' />" width=20 height=15></th>
                            </tr>
                        </thead>
                        <!-- 레이아웃 페이지보다 웹페이지가 커서 행 2개 추가 (7개 > 9개) -->
                        <tbody>
                            <tr>
                                <td>Cell Data</td>
                                <td>Cell Data</td>
                                <td>Cell Data</td>
                                <td>Cell Data</td>
                                <td>Cell Data</td>
                                <td>Cell Data</td>
                                <td>Cell Data</td>
                            </tr>
                            <tr>
                                <td>Cell Data</td>
                                <td>Cell Data</td>
                                <td>Cell Data</td>
                                <td>Cell Data</td>
                                <td>Cell Data</td>
                                <td>Cell Data</td>
                                <td>Cell Data</td>
                            </tr>
                            <tr>
                                <td>Cell Data</td>
                                <td>Cell Data</td>
                                <td>Cell Data</td>
                                <td>Cell Data</td>
                                <td>Cell Data</td>
                                <td>Cell Data</td>
                                <td>Cell Data</td>
                            </tr>
                            <tr>
                                <td>Cell Data</td>
                                <td>Cell Data</td>
                                <td>Cell Data</td>
                                <td>Cell Data</td>
                                <td>Cell Data</td>
                                <td>Cell Data</td>
                                <td>Cell Data</td>
                            </tr>
                            <tr>
                                <td>Cell Data</td>
                                <td>Cell Data</td>
                                <td>Cell Data</td>
                                <td>Cell Data</td>
                                <td>Cell Data</td>
                                <td>Cell Data</td>
                                <td>Cell Data</td>
                            </tr>
                            <tr>
                                <td>Cell Data</td>
                                <td>Cell Data</td>
                                <td>Cell Data</td>
                                <td>Cell Data</td>
                                <td>Cell Data</td>
                                <td>Cell Data</td>
                                <td>Cell Data</td>
                            </tr>
                            <tr>
                                <td>Cell Data</td>
                                <td>Cell Data</td>
                                <td>Cell Data</td>
                                <td>Cell Data</td>
                                <td>Cell Data</td>
                                <td>Cell Data</td>
                                <td>Cell Data</td>
                            </tr>
                            <tr>
                                <td>Cell Data</td>
                                <td>Cell Data</td>
                                <td>Cell Data</td>
                                <td>Cell Data</td>
                                <td>Cell Data</td>
                                <td>Cell Data</td>
                                <td>Cell Data</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </article>
    
    <!-- paging -->
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

</body>

</html>