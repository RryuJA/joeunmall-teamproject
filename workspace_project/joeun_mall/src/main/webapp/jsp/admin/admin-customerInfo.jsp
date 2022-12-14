<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!--title 페이지에 맞게 수정-->
<title>JoEun-admin 고객상세정보팝업</title>

<!--javascript-->

<!--css-->
<!--admin-nav.css는 고정-->
<!--페이지별로 추가해야하는 css 파일은 admin-nav.css 아래에 추가-->
<link rel="stylesheet" type="text/css" href="<c:url value ='/css/admin-nav.css' />">
<link rel="stylesheet" type="text/css" href="<c:url value ='/css/admin-section-manage.css' />">
</head>
<body>
    <section id="customer-info-wrap">
        <div class="customer-info-header">
            <div>
            <p><a href="#">DB에서 입력받은 고객상세정보: ex)고객정보-(2022001)이승은</a></p>
            </div>
            <div class="close-button">
                <input type="image" src="<c:url value ='/joeun_html/images/icon/icon_close.png' />" onclick='window.close()'>
            </div>
        </div>
        <div id="custom-info-button">
            <a href="#"></a><input type="button" value="주문내역"  >
            <a href="#"></a><input type="button" value="문의내역"  >
        </div>
<!-- 테이블 -->
            <div>
                <table>
                    <tbody>
                        <td class="th7-width">이름</td>
                        <td class="th8-width"><input type="text" placeholder="이름을 입력해주세요"></td>
                    <tr>
                        <td class="th7-width">가입일</td>
                        <td class="th8-width"><input type="date"></td>
                    </tr>
                    <tr>
                        <td class="th7-width">아이디</td>
                        <td class="th8-width "><input type="text" placeholder="아이디를 입력해주세요"></td>
                    </tr>
                    <tr>
                        <td class="th7-width">이메일</td>
                        <td class="th8-width "><input type="text" placeholder="이메일을 입력해주세요"></td>
                    </tr>
                    <tr>
                        <td class="th7-width">생년월일</td>
                        <td class="th8-width "><input type="date"></td>
                    </tr>
                    <tr>
                        <td class="th7-width">성별</td>
                        <td class="th8-width "><input type="text" placeholder="성별을 입력해주세요"></td>
                    </tr>
                    <tr>
                        <td class="th7-width">일반전화</td>
                        <td class="th8-width "><input type="text" placeholder="전화번호를 입력해주세요"></td>
                    </tr>
                    <tr>
                        <td class="th7-width">휴대전화</td>
                        <td class="th8-width "><input type="text" placeholder="휴대전화번호를 입력해주세요"></td>
                    </tr>
                    <tr>
                        <td class="th7-width">주소</td>
                        <td class="th8-width "><input type="text" placeholder="주소를 입력해주세요"></td>
                    </tr>
                    </tbody>
                </table>
            </div>
<!--관리자 페이지 footer 생략-->
    </section>
</body>
</html>
