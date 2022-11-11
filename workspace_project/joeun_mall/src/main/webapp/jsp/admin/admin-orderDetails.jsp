<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <!--title 페이지에 맞게 수정-->
    <title>JoEun-admin 주문상세</title>
    
    <!--javascript-->

    <!--css-->
    <!--admin-nav.css는 고정-->
    <!--페이지별로 추가해야하는 css 파일은 admin-nav.css 아래에 추가-->
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/admin-nav.css' />">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/admin-section-details.css' />">
</head>
<body>
    <div id="wrap">
        <!--관리자 페이지 header 생략-->
        <nav>
            <!--a 태그 href는 추후 페이지 완성 후 수정-->
            <a href="#"><img id="logo" src="<c:url value ='/images/logo/logo_transparent.png' />" alt="JoEunMall"></a>
            <hr id="logohr"/>
            <ul>
                <!--html 페이지에 해당하는 li태그에 class="thispage" 지정-->
                <!--class가 "li-lower"에 해당하는 경우, 상위 li에도 class="thispage" 지정-->
                <!--ex) <li class="li-lower thispage"> -->
                <li class="thispage"><a href="admin-orderDetails.do">주문관리</a></li>
                <li><a href="admin-orderStats.do">주문통계</a></li>
                <li><a href="admin-productManage.do">상품관리</a></li>
                <ul class="ul-lower">
                    <li class="li-lower"><a href="admin-productManage.do">상품조회</a></li>
                    <li class="li-lower"><a href="#">상품등록</a></li>
                </ul>
                <li><a href="admin-customerManage.do">고객관리</a></li>
                <li><a href="admin-inquiryManage.do">1:1문의</a></li>
            </ul>
            <input type="button" value="로그아웃" id="logout-btn" onclick="location.href='/joeunmall/logout.do'">
        </nav>
        <!--admin-nav.css 끝-->
        
        <!--section, article css 제작해야 함-->
        <section>
            <article>
            <form name="orderDetails" method="post" action="<c:url value ='/admin/admin-orderProc.do' />">
                <!--table border css로 수정-->
                <!--td부분 DB에서 데이터 가져와서 출력해야함-->
                <table>
                    <tr>
                        <th class="table-col1">주문자명</th>
                        <td class="table-col3"><div class="text-square">${orderVO.orderName}</div>
                        <input type="hidden" id="orderName" name="orderName" value="${orderVO.orderName}"/></td>
                        <th class="table-col1">주문번호</th>
                        <td class="table-col2" colspan="2">${orderVO.orderIndex}
                        <input type="hidden" id="orderIndex" name="orderIndex" value="${orderVO.orderIndex}"/></td>
                    </tr>
                    <tr>
                        <th>진행상태</th>
                        <td>
                            <!--주문관리탭의 진행상태와 속성 일치시켜야함-->
                            <select name="order-state">
                                <option value="os-1">${orderVO.orderStateIndex}</option>
                                <option value="os-2">상품준비</option>
                                <option value="os-3">배송처리</option>
                                <option value="os-4">배송완료</option>
                                <option value="os-5">주문취소</option>
                                <option value="os-6">환불완료</option>
                                <option value="os-7">교환 처리중</option>
                                <option value="os-8">교환완료</option>
                            </select>
                            <input type="hidden" id="orderState" name="orderState" value="${orderVO.orderStateIndex}"/>
                        </td>
                        <th>주문일자</th>
                        <td colspan="2">${orderVO.orderDate}
                        <input type="hidden" id="orderDate" name="orderDate" value="${orderVO.orderDate}"/></td>
                    </tr>
                    <!--고객이 주문한 상품 수에 따라 칸이 달라짐-->
                    <!--수정해야 될 부분: rowspan, tr-->
                    <tr>
                        <th rowspan="3">주문한 상품</th>
                        <th>상품명</th>
                        <th>수량</th>
                        <th>상품가격</th>
                        <th>총 상품가격</th>
                    </tr>
                    <!-- 주문상품 리스트 -->
                    <c:forEach var="cart" items="${orderVO.cartList}">
                    <tr>
                        <td>${cart.productName}</td>
                        <td><div class="text-center">${cart.productCount}</div></td>
                        <td><div class="text-center"><fmt:formatNumber pattern="###,###" value="${cart.productPrice}"/> </div></td>
                        <td><div class="text-center"><fmt:formatNumber pattern="###,###" value="${cart.productCount * cart.productPrice}"/></div></td>
                    </tr>
                    </c:forEach>
                    <!-- //주문상품 리스트 -->
                    <!-- <tr>
                        <td>조직감 반넥 니트 베스트(BLACK, 95)</td>
                        <td><div class="text-center">2</div></td>
                        <td><div class="text-center">35,000</div></td>
                        <td><div class="text-center">70,000</div></td>
                    </tr> -->
                    <tr>
                        <th>결제금액</th>
                        <td><div class="text-square">${orderVO.orderPrice}</div>
                        <input type="hidden" id="orderPrice" name="orderPrice" value="${orderVO.orderPrice}"/></td>
                        <th>배송비</th>
                        <td colspan="2">
                            <div id="text-square3">0</div>
                            <div id="graytext">50,000 이상 구매 시 배송비 무료</div>
                        </td>
                    </tr>
                    <tr>
                        <th>배송지</th>
                        <td colspan="4" id="table-row1">
                            <div class="text-square">${orderVO.orderAddress}</div>
                            <div class="text-square">${orderVO.orderAddressDetail}</div>
                            <input type="hidden" id="orderAddress" name="orderAddress" value="${orderVO.orderAddress}"/>
                            <input type="hidden" id="orderAddressDetail" name="orderAddress" value="${orderVO.orderAddressDetail}"/>
                        </td>
                    </tr>
                    <!--고객이 문의한 횟수에 따라 칸이 달라짐-->
                    <!--수정해야 될 부분: rowspan, tr-->
                    <!--문의제목에 글자수 제한 필요-->
                    <tr>
                        <th rowspan="2">고객문의</th>
                        <td colspan="4">
                            <div class="box-blue">${orderVO.inquiryState}</div>
                            <div class="inquiry-title">${orderVO.inquiryTitle}</div>
                            <div class="inquiry-content">${orderVO.inquiryContent}</div>
                        <input type="hidden" id="inquiryState" name="inquiryState" value="${orderVO.inquiryState}"/>
                        <input type="hidden" id="inquiryTitle" name="inquiryTitle" value="${orderVO.inquiryTitle}"/>
                        <input type="hidden" id="inquiryContent" name="inquiryContent" value="${orderVO.inquiryContent}"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4">
                            <div class="box-red">문의접수</div>
                            <div class="inquiry-title">문의제목2</div>
                            <div class="inquiry-content">문의내용2222222222By promptly disclosing medical errors and offering earnest apologies and fair compensation, doctors hope to make it easier to learn from mistakes and relieve the patient's anger.</div>
                        </td>
                    </tr>
                </table>
                    <!--onclick : 임시코드-->
                    <input type="submit" value="확인">
                    <!-- onclick="location.href='admin-orderManage.html'; return false;" --> 
                </form>
            </article>
        </section>
    </div>
    <!--관리자 페이지 footer 생략-->
</body>
</html>
