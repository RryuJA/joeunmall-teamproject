<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html lang="ko">

<head>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

 

    <!--title 페이지에 맞게 수정-->
    <title>JoEun-admin 상품등록</title>

 

    <!--javascript-->

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="<c:url value = '/js/admin-productregist.js' />" charset="UTF-8"></script>   

 

    <!--css-->

    <!--admin-nav.css는 고정-->

    <!--페이지별로 추가해야하는 css 파일은 admin-nav.css 아래에 추가-->

    <link rel="stylesheet" type="text/css" href="<c:url value ='/css/admin-nav.css' />">
    <link rel="stylesheet" type="text/css" href="<c:url value ='/css/admin-registration.css' />">
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
                <li class="thispage"><a href="<c:url value='/admin/admin-productManage.do' />">상품관리</a></li>
                <ul class="ul-lower">
                    <li class="li-lower"><a href="<c:url value='/admin/admin-productManage.do' />">상품조회</a></li>
                    <li class="li-lower thispage"><a href="<c:url value='/admin/admin-productRegistration.do' />">상품등록</a></li>
                </ul>
                <li><a href="<c:url value='/admin/admin-customerManage.do' />">고객관리</a></li>
                <li><a href="<c:url value='/admin/admin-inquiryManage.do' />">1:1문의</a></li>
            </ul>
            <input type="button" value="로그아웃" id="logout-btn" onclick="location.href='/joeunmall/logout.do'">
        </nav>
        
        <!--admin-nav.css 끝-->
        <form id="regist_form" method="post" action="<c:url value='/admin/admin-productRegistration.do' />" enctype="multipart/form-data">
        <section id="regist-section">
            <table id="productRegistration">
                <tr>
                    <th class="table-col1">상품명</th>
                    <td class="table-col2">
                    	<input type="text" required name="productName" id="productName" placeholder="상품명을 입력하세요." size="28">
                   	</td>
                    <th class="table-col1">상품번호</th>
                    <td class="table-col3">
                    	<input type="text" required name="productIndex" id="productIndex" 
                    		pattern="\d{2}_\d{2}_\d{3}"	title="먼저 카테고리를 선택해주세요." placeholder="카테고리를 선택해주세요." size="35" readonly >
                   	</td>
                </tr>
                <tr>
                    <th>카테고리</th>
                    <td>
                        <select name="productCategoryIndex" id="category">
                            <option value="01">티셔츠</option>
                            <option value="02">팬츠/스커트</option>
                            <option value="03">원피스</option>
                            <option value="04">니트/가디건</option>
                            <option value="05">자켓</option>
                        </select>
                    </td>
                    <th>판매상태</th>
                    <td>
                        <label><input type="radio" name="productStateInfo" value="0" checked>판매중</label>
                        <label><input type="radio" name="productStateInfo" value="1">판매종료</label>
                    </td>
                </tr>
                <tr>
                    <th>상품가격</th>
                    <td colspan="3"><input type="number" required min="0" name="productPrice" id="productPrice" placeholder="상품가격을 입력하세요."></td>
                </tr>
                <tr>
                    <th>대표 이미지</th>
                    <td colspan="3">
                        <div name="uploadFile1" id="left_push">
                            <script src="<c:url value= '/js/admin-representative.js' />"></script>
                            <img id="main_image" src="<c:url value='/images/icon/icon_preloader.png' />" width="100" height="100">
                        </div> 
                        <div id="imageupload_pnl">
                        <!-- T4 >> uploadFile1 -->
                            <input type="file" onchange="readURL(this);";" required name="uploadFile1" id="uploadFile1" placeholder="이미지 파일을 첨부하세요." size="20">
                        </div>
                    </td>   
                </tr>
                <tr>
                    <th>상품 이미지</th>
                    <td id="image_upload_td" colspan="3">
                        <div id="image_upload_pnl">
                            <div id="image_upload_btn_pnl">
                                <a id="image_upload_btn">
                                    <img id="goods1" src="<c:url value ='/images/icon/icon_plus.png' />">
                                </a>
                            </div> 
                            <!-- 상품이미지 파일들 -->
                            <!-- name=T5 >> T5_1 >> uploadFiles -->
                            <div id="image_upload_boxes">
                                <div id="image_upload_box1">
                                    <input type="file"  name="uploadFiles1" id="upload_file1"  placeholder="이미지 파일을 첨부하세요." size="20">
                                    <img id="upload_image_btn1" src="<c:url value ='/images/icon/icon_minus.png' />">  
                                </div>
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th>이미지<br>상세정보</th>
                    <td colspan="3" id="table-row"><textarea required name="productInfo" placeholder="상품 상세정보를 입력하세요."></textarea></td>
                </tr>
                <tr>
                    <th>옵션</th>
                    <!-- <td id="color_choose" colspan="3"> -->
                    <td id="color_upload_td" colspan="3">
                        <div id="color_upload_pnl">
                            <div id="color_upload_btn_pnl">
                                <a href="#" id="color_upload_btn">
                                    <img id="color1" src="<c:url value ='/images/icon/icon_plus.png' />"> 
                                </a>
                            </div>
                            <!-- 색상이미지 파일들 -->
                            <div id="color_upload_boxes" >
                                <!-- <div id="color_upload_box0"  class="move_square"> -->
                                <div id="color_upload_box1">
                                <!-- T8_1 >> productOption -->
                                    <div>
                                        <input type="text" required name="productOption1" id="color_text1" placeholder="옵션을 입력하세요." size="20">
                                    </div>
                                    <div>
                                        <img id="color_image_btn1" src="<c:url value ='/images/icon/icon_minus.png' />">
                                    </div>
                                </div>
                            </div>   
                        </div> 
                    </td>
                </tr>
            </table>
            
        </section>
        <div id="regist-btn">
        	<input type="submit" id="product_submit_btn" value="상품등록">
    	</div>
    	</form>
    </div>
    <!--관리자 페이지 footer 생략-->
</body>
</html>