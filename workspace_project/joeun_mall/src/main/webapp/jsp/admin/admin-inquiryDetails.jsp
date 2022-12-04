<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <!--title 페이지에 맞게 수정-->
    <title>JoEun-admin 1:1문의(상세)</title>
    
    <!--javascript-->

    <!--css-->
    <!--admin-nav.css는 고정-->
    <!--페이지별로 추가해야하는 css 파일은 admin-nav.css 아래에 추가-->
    <link rel="stylesheet" type="text/css" href="<c:url value ='/css/admin-nav.css' />">
    <link rel="stylesheet" type="text/css" href="<c:url value ='/css/admin-section-details.css' />">
</head>
<body>
    <div id="wrap">
        <!--관리자 페이지 header 생략-->
        <nav>
            <!--a 태그 href는 추후 페이지 완성 후 수정-->
            <a href = "<c:url value='/admin/admin-orderManage.do' />"><img id="logo" src="<c:url value ='/images/logo/logo_transparent.png' />" alt="JoEunMall"></a>
            <hr id="logohr"/>
            <ul>
                <!--html 페이지에 해당하는 li태그에 class="thispage" 지정-->
                <!--class가 "li-lower"에 해당하는 경우, 상위 li에도 class="thispage" 지정-->
                <!--ex) <li class="li-lower thispage"> -->
                <li><a href="<c:url value='/admin/admin-orderManage.do' />">주문관리</a></li>
                <li><a href="<c:url value='/admin/admin-orderStats.do' />">주문통계</a></li>
                <li><a href="<c:url value='/admin/admin-productManage.do' />">상품관리</a></li>
                <ul class="ul-lower">
                    <li class="li-lower"><a href="<c:url value='/admin/admin-productManage.do' />">상품조회</a></li>
                    <li class="li-lower"><a href="<c:url value ='/admin/admin-productRegistration.do' />">상품등록</a></li>
                </ul>
                <li><a href="<c:url value ='/admin/admin-customerManage.do' />">고객관리</a></li>
                <li class="thispage"><a href="<c:url value ='/admin/admin-inquiryManage.do' />">1:1문의</a></li>
            </ul> 
            <input type="button" value="로그아웃" id="logout-btn" onclick="location.href='/joeunmall/logout.do'">
        </nav>
        <!--admin-nav.css 끝-->
        
        <!--section, article css 제작해야 함-->
        <section>
            <article>
                <!--table border css로 수정-->
                <!--td부분 DB에서 데이터 가져와서 출력해야함-->
                
                <form name="inquiryForm" action="<c:url value ='/admin/admin-inquiryProc.do' />" method="post">
                
                <table>
                    <tr>
                        <th class="table-col1">고객명</th>
                        <td class="table-col3">
                        	<div class="text-square">
                        		<a onclick="window.open('./admin-customerInfo.html', 'window_name', 'width=710, height=510, location=no, status=no, scrollbars=yes');" style="cursor:pointer">
                        			${inquiryVO.userName}
                        		</a>
                        	</div>
                        </td>
                        <th class="table-col1">문의번호</th>
                        <td class="table-col2">${inquiryVO.inquiryIndex}
                        <input type="hidden" id="inquiryIndex" name="inquiryIndex" value="${inquiryVO.inquiryIndex}"/></td>
                    </tr>
                    <tr>
                        <th>처리현황</th>
                        <!--"box-red" 또는 "box-blue"-->
                        <td>
                        	<c:if test="${inquiryVO.inquiryState == '문의접수'}">
                        	<div class="box-red">${inquiryVO.inquiryState}</div>
                        	</c:if>
                        	<c:if test="${inquiryVO.inquiryState != '문의접수'}">
                        	<div class="box-blue">${inquiryVO.inquiryState}</div>
                        	</c:if>
                        </td> 
                        <th>문의일자</th>
                        <td>${inquiryVO.inquiryDate}</td>
                    </tr>
                    <tr>
                        <th>문의항목</th>
                        <td colspan="3">${inquiryVO.inquiryCategory}</td>
                    </tr>
                    <tr>
                        <th>문의제목</th>
                        <td colspan="3"><div class="text-square">${inquiryVO.inquiryTitle}</div></td>
                    </tr>
                    <tr>
                        <th>문의내용</th>
                        <td colspan="3" class="table-row2"><div id="text-square2">${inquiryVO.inquiryContent}</div></td>
                    </tr>
                    <tr>
                        <th>문의답변</th>
                        <td colspan="3" class="table-row2"><textarea id="inquiryAnswer" name="inquiryAnswer" placeholder="문의답변을 입력하세요.">${inquiryVO.inquiryAnswer}</textarea></td>
                    </tr>
                </table>
                    <!--onclick : 임시코드-->
                    <input type="submit" id="inquirySubmit_btn" value="확인" /><!-- <onclick="location.href='admin-inquiryManage.html'; return false;"> -->
                </form>
            </article>
        </section>
    </div>
    <!--관리자 페이지 footer 생략-->
</body>
</html>
