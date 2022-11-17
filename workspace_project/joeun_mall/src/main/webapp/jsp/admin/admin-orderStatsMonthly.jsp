<%@ page language = "java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <title>JoEun-admin 주문통계 월별그래프</title>

        <!--javascript-->
        <!-- 그래프에 필요한 스크립트 -->
        <script src="<c:url value='/js/jquery-3.6.1.min.js' />"></script>
        <script src="<c:url value='/js/charts/highcharts.js' />"></script>
        <script src="<c:url value='/js/charts/exporting.js' />"></script>
        <script src="<c:url value='/js/charts/export-data.js' />"></script>
        <script src="<c:url value='/js/charts/accessibility.js' />"></script>

        <!-- PJM 220923 -->
        <!-- <script src = 'js/charts/PJM_getGraph(Monthly).js'></script> -->
       

        <!--css-->
        <!--admin-nav.css는 고정-->
        <!--페이지별로 추가해야하는 css 파일은 admin-nav.css 아래에 추가-->
        <link rel="stylesheet" type="text/css" href="<c:url value ='/css/admin-nav.css' />">
        <link rel="stylesheet" type="text/css" href="<c:url value ='/css/admin-stats-monthlygraph.css' />">
    </head>

    <body>
        <div id="wrap">
            <!--관리자 페이지 header 생략-->
            <nav>
                <!--a 태그 href는 추후 페이지 완성 후 수정-->
               <a href = "<c:url value='/admin/admin-orderManage.do' />"><img id="logo" src="<c:url value ='/images/logo/logo_transparent.png' />" alt="JoEunMall"></a>
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
                <!-- 상태 변수 대쉬보드 (나중에 visibility: hidden; 이용해서 투명으로 만들것)-->
                <div hidden id="PJM_boxStats" style=" height: 40px; text-align: center; padding-top: 20px; background-color: yellow;">
                    <label for="select_stat">판매 금액:</label><input type="text" id="selectPrice_stat" size="10" value="미선택" readonly>&nbsp;
                    <label for="select_stat">판매 수량:</label><input type="text" id="selectQuantity_stat" size="10" value="미선택" readonly>&nbsp;
                    <label for="select_stat">기간 선택:</label><input type="text" id="select3_stat" size="10" value="미선택" readonly>&nbsp;
                    <label for="select_stat">의류 선택:</label><input type="text" id="select4_stat" size="10" value="미선택" readonly>&nbsp;
                </div>
                
                <div class="select">
                    <input type="radio" id="selectPrice" name="selectGraph" value="price" checked="checked"><label for="selectPrice">판매 금액</label>
                    <input type="radio" id="selectQuantity" name="selectGraph" value="quantity"><label for="selectQuantity">판매 수량</label>
                    
                    <!-- ---------------- 그래프 ---------------------->
                    <script src="<c:url value='/js/charts/PJM_getGraph(Monthly).js' />"></script><!-- 이 js 파일 헤드로 올리면 그래프 작동 안함 -->
                    <script src="<c:url value='/js/charts/PJM_getGraph(Monthly)_onLoad.js' />"></script>
                    <script src="<c:url value='/js/charts/PJM_getGraph(Monthly)(select3).js' />"></script>
                    <script src="<c:url value='/js/charts/PJM_getGraph(Monthly)(select4).js' />"></script>
                    <script src="<c:url value='/js/charts/monthlyChartAJAX.js' />"></script>
                    <script src="<c:url value='/js/charts/monthlyChartCreator.js' />"></script>
                    
                    <figure class="highcharts-figure">
                        <div id="container"></div>
                    </figure>
                </div>

                <div class="select3">  <!-- 기간 선택 select -->
                	<%
                	Date now = new Date();
                	SimpleDateFormat sf = new SimpleDateFormat("yyyy");
                    String today = sf.format(now);
                    %>
                    <select name="selectYear" id="select3">
                        <option selected value="allPeriod">전체 기간</option>
                        <option value=<%=today%>><%=today%></option>
                        <option value=<%=Integer.toString(Integer.parseInt(today)-1)%>><%=Integer.parseInt(today)-1%></option>
                        <option value=<%=Integer.toString(Integer.parseInt(today)-2)%>><%=Integer.parseInt(today)-2%></option>
                        <option value=<%=Integer.toString(Integer.parseInt(today)-3)%>><%=Integer.parseInt(today)-3%></option>
                    </select>
                </div>

                <div class="select4">  <!-- 종류 선택 select -->
                    <select name="clothType" id="select4" class="clothType-graph">
                        <option selected value="ct-all">전체 의류</option>
                        <option value="01">티셔츠</option>
                        <option value="02">팬츠/스커트</option>
                        <option value="03">원피스</option>
                        <option value="04">니트/가디건</option>
                        <option value="05">자켓</option>                        
                    </select>
                </div>

                <div><!-- 전체 항목 보기 버튼 -->
                    <input type="button" value="전체 항목 보기" id="monthCompare-btn" style="cursor:pointer" onclick="location.href='admin-orderStatsGraph.do'">
                </div>

                <div><!-- 표 보기 버튼 -->
                    <input type="button" value="표 보기" id="graph-btn" style="cursor:pointer" onclick="location.href='admin-orderStats.jsp'">
                </div>
            </section>

            
        </div>
    </body>
</html>