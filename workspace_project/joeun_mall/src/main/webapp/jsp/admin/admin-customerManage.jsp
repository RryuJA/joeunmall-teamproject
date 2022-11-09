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
        <a href = "<%=request.getContextPath()%>/orderManage.do?admin=team3">
			<img id="logo" src="<c:url value ='/images/logo/logo_transparent.png'/>" alt="JoEunMall">
		</a>
        <hr id="logohr"/>
        <ul>
            <!--html 페이지에 해당하는 li태그에 class="thispage" 지정-->
            <!--class가 "li-lower"에 해당하는 경우, 상위 li에도 class="thispage" 지정-->
            <!--ex) <li class="li-lower thispage"> -->
            <li><a href="admin-orderManage.html">주문관리</a></li>
            <li><a href="admin-orderStats.html">주문통계</a></li>
            <li><a href="admin-productManage.html">상품관리</a></li>
            <ul class="ul-lower">
                <li class="li-lower"><a href="admin-productManage.html">상품조회</a></li>
                <li class="li-lower"><a href="admin-productRegistration.html">상품등록</a></li>
            </ul>
            <li class="thispage"><a href="admin-customerManage.html">고객관리</a></li>
            <li><a href="admin-inquiryManage.html">1:1문의</a></li>
        </ul>
        <input type="button" value="로그아웃" id="logout-btn">
    </nav>
    <!--admin-nav.css 끝-->
    
    <!--section, article css 제작해야 함-->
    <section id="section">
            <div id="top-menu-manage">
                    <select class="drop-box">
                        <option value="">정렬 선택</option>
                        <option value="order-sort">최근가입순 정렬</option>
                        <option value="state-sort">고객명 정렬</option>
                    </select>
                    <input type="search" id="search" placeholder="고객번호 또는 고객명" />
                <a href="123"><img id="icon_search" src="<c:url value ='/images/icon/icon_search.png' />" alt="돋보기"></a>
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
                        <th>나이</th>
                        <th>성별</th>
                        <th class= "th-width">연락처</th>
                        <th class="th6-width">관련 내역 확인</th>
                    </tr>
                </thead>
                <!-- 레이아웃 페이지보다 웹페이지가 커서 행 2개 추가 (7개 > 9개) -->
                <tbody>
					<c:forEach var="customerManageVO" items="${customerManageList}" varStatus="st" >
                    <tr>
                        <!-- <td><a onclick="window.open('./admin-customerInfo.html', 'window_name', 'width=710, height=510, location=no, status=no, scrollbars=yes');" style="cursor:pointer">주문번호</a></td> -->
                        <td>${customerManageVO.userIndex}</td>
                        <td>${customerManageVO.userName}</td>
                        <td><fmt:formatDate value="${customerManageVO.userDate}" pattern="yyyy년MM월dd일" /></td>
                        <td>${customerManageVO.userAge}</td>
                        <td>${customerManageVO.userGender}</td>
                        <td>${customerManageVO.userMoblie}</td>
                        <td>
                            <a href="#"></a><input type="button" value="주문내역">
                            <a href="#"></a><input type="button" value="문의내역">
                        </td>
                    </tr>
                    </c:forEach>
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
<!--관리자 페이지 footer 생략-->
</body>
</html>
