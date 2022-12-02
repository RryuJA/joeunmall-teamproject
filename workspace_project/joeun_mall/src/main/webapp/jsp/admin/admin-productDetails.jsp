<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html lang="ko">

<head>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!--title 페이지에 맞게 수정-->
    <title>JoEun-admin 상품상세</title>

    <!--javascript-->
    <script src="<c:url value='/jquery/jquery.min.js' />"></script>
    
    <!-- 상품 상세보기  -->
 	<script src="<c:url value = '/js/admin-productdetail.js' />" charset="UTF-8"></script> 
    
    <!-- 커스텀 파일 필드  -->
    <script>
    	$(function(){
   				
   			  $('#image_upload_td').on('change','.upload-hidden', function(e){ // 값이 변경되면
   				
   					if(window.FileReader){ // modern browser 

   						var filename = $(this)[0].files[0].name; 

   					} else { // old IE(예전 IE)

   						var filename = $(this).val()
   											  .split('/')
   											  .pop()
   											  .split('\\')
   											  .pop(); // 파일명만 추출 
   					} // 추출한 파일명 삽입 
   					
   					$(this).siblings('.upload-name').val(filename); 
					
   			   });  // 값이 변경되면(끝)
    		}); 
    </script>

    <!--css-->

    <!--admin-nav.css는 고정-->

    <!--페이지별로 추가해야하는 css 파일은 admin-nav.css 아래에 추가-->

    <link rel="stylesheet" type="text/css" href="<c:url value ='/css/admin-nav.css' />">
    <link rel="stylesheet" type="text/css" href="<c:url value ='/css/admin-registration.css' />">
    <link rel="stylesheet" type="text/css" href="<c:url value ='/css/admin-section-details.css' />">

	<!-- 커스텀 파일 필드 -->
	<style>
	.filebox{
		width: 400px;
	}
	
	.filebox input[type="file"] 
	{ 
		position: absolute; 
		width: 1px; 
		height: 1px; 
		padding: 0; 
		margin: -1px; 
		overflow: hidden; 
		clip:rect(0,0,0,0); 
		border: 0; 
	} 

	.filebox label 
	{ 
		display: inline-block; 
		padding: .5em .5em; 
		color: #000; 
		font-size: inherit; 
		line-height: normal; 
		vertical-align: middle; 
		background-color: #fff; 
		cursor: pointer; 
		border: 1px solid #646464; 
		border-bottom-color: #646464; 
		border-radius: .25em; 
	} 

	/* named upload */ 
	.filebox .upload-name 
	{ 
		display: inline-block; 
		padding: .5em .75em; /* label의 패딩값과 일치 */ 
		font-size: inherit; 
		font-family: inherit; 
		line-height: normal; 
		vertical-align: middle; 
		background-color: #fff; 
		border: 1px solid #646464; 
		border-bottom-color: #646464; 
		border-radius: .25em; 
		width: 250px; 

		/* 네이티브 외형(원래 모양) 감추기 */		
		appearance: none; 
	}

  </style>
</head>
<body>
<%-- <div>
productVO:${productVO}<br><hr>
productImageList:${productImageList}<br><hr>
productOptionList:${productOptionList}<br><hr>
productImageList.크기:${productImageList.size()}
</div> --%>
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
                    <li class="li-lower thispage"><a href="<c:url value='/admin/admin-productManage.do' />">상품조회</a></li>
                    <li class="li-lower"><a href="<c:url value='/admin/admin-productRegistration.do' />">상품등록</a></li>
                </ul>
                <li><a href="<c:url value='/admin/admin-customerManage.do' />">고객관리</a></li>
                <li><a href="<c:url value='/admin/admin-inquiryManage.do' />">1:1문의</a></li>
            </ul>
            <input type="button" value="로그아웃" id="logout-btn" onclick="location.href='/joeunmall/logout.do'">
        </nav>
        
        <!--admin-nav.css 끝-->
        <form id="update_form" method="post" action="<c:url value='/admin/admin-productUpdate.do' />" enctype="multipart/form-data">
        <section id="regist-section">
            <table id="productRegistration">
                <tr>
                    <th class="table-col1">상품명</th>
                    <td class="table-col2">
                    	<input type="text" required name="productName" id="productName" placeholder="상품명을 입력하세요." size="28" value="${productVO.productName}">
                   	</td>
                    <th class="table-col1">상품번호</th>
                    <td class="table-col3">
                    	<input type="text" required name="productIndex" id="productIndex" 
                    		pattern="\d{2}_\d{2}_\d{3}"	title="먼저 카테고리를 선택해주세요." placeholder="카테고리를 선택해주세요." size="35" value="${productVO.productIndex}">
                   	</td>
                </tr>
                <tr>
                    <th>카테고리</th>
                    <td>
                        <select name="productCategoryIndex" id="category">
                            <option value="01" ${productVO.productCategoryIndex == '01' ? "selected" : ""}>티셔츠</option>
                            <option value="02" ${productVO.productCategoryIndex == '02' ? "selected" : ""}>팬츠/스커트</option>
                            <option value="03" ${productVO.productCategoryIndex == '03' ? "selected" : ""}>원피스</option>
                            <option value="04" ${productVO.productCategoryIndex == '04' ? "selected" : ""}>니트/가디건</option>
                            <option value="05" ${productVO.productCategoryIndex == '05' ? "selected" : ""}>자켓</option>
                        </select>
                    </td>
                    <th>판매상태</th>
                    <td>
                        <label><input type="radio" name="productStateInfo" value="0" ${productVO.productStateIndex == '0' ? "checked" : ""}>판매중</label>
                        <label><input type="radio" name="productStateInfo" value="1" ${productVO.productStateIndex == '1' ? "checked" : ""}>판매종료</label>
                    </td>
                </tr>
                <tr>
                    <th>상품가격</th>
                    <td colspan="3"><input type="number" required min="0" name="productPrice" id="productPrice" placeholder="상품가격을 입력하세요." value="${productVO.productPrice}"></td>
                </tr>
                <tr>
                    <th>대표 이미지</th>
                    <td colspan="3">
                        <div name="uploadFile1" id="left_push">
                            <script src="<c:url value= '/js/admin-representative.js' />"></script>
                            <!-- 상품 이미지가 존재할때  -->
                            <c:if test ="${not empty productVO.productImage}">
                            	<img id="main_image" src="<c:url value='/thumbnail/${productVO.productImage}' />" width="100" height="100" style="margin: 10px 0 5px 5px">
                            </c:if>
                            <!-- 상품 이미지가 존재X  -->
                            <c:if  test ="${empty productVO.productImage}">
                            <img id="main_image" src="<c:url value='/images/icon/icon_preloader.png' />" width="100" height="100">	
                            </c:if>
                            
                        </div> 
                        <div id="imageupload_pnl">
                        	<!-- T4 >> uploadFile1 -->
                            <!-- <input type="file" onchange="readURL(this);";" required name="uploadFile1" id="uploadFile1" placeholder="이미지 파일을 첨부하세요." size="20"> -->
                         	<div class="filebox">
                                    <div style="float: left"> 
									<input class="upload-name" value="${productVO.productImage}" disabled> 
									<label for="uploadFile1">선택</label> 
									<input type="file" name="uploadFile1" id="uploadFile1" class="upload-hidden"> 
								 </div>
						    </div>
                        </div>
                    </td>   
                </tr>
                <tr>
                    <th id="image_title_box">상품 이미지
                    	
                    	<!-- 업로드 파일 삭제 여부 체크 -->
                    	<c:forEach items="${productImageList}" var="productImage" varStatus="st" >
		                   <div id="upload_image_delete_menu" style="float: left">
		                        <div style="float: left">
						  			<input type="hidden" id="upload_image_delete_yn${st.count}" name="uploadImageDeleteYn${st.count}" value="N" size="1">
		                        </div>
		                    </div>
	                    </c:forEach>
	                    
                    </th>
                    <td id="image_upload_td" colspan="3">
                        <div id="image_upload_pnl">
                            <div id="image_upload_btn_pnl"  style="padding-top: 5px">
                                <a id="image_upload_btn" >
                                    <img id="goods1" src="<c:url value ='/images/icon/icon_plus.png' />">
                                </a>
                            </div> 
                            <!-- 상품이미지 파일들 -->
                            <!-- name=T5 >> T5_1 >> uploadFiles -->
                            <div id="image_upload_boxes">
                            	
                            <c:forEach items="${productImageList}" var="productImage" varStatus="st" >
                            	
                            	<div id="image_upload_box${st.count}">
                                     <div class="filebox" style="float: left">
	                                     <div style="float: left"> 
											<input class="upload-name" value="${productImage.productDetailImage}" disabled> 
											<label for="upload_file${st.count}">선택</label> 
											<input type="file" name="uploadFiles${st.count}" id="upload_file${st.count}" class="upload-hidden"> 
										 </div>
										 <div style="padding-top: 5px">
										  	<img id="upload_image_btn${st.count}" src="<c:url value ='/images/icon/icon_minus.png' />"> 
										  </div>
									  </div>
									  
                                </div>
                                
                                <script> 
                                	$(function(){
                                		var imgPnlheight=$("#image_upload_boxes").height();
                                		console.log("imgPnlheight ="+imgPnlheight);
                                		$("#image_upload_boxes").height(imgPnlheight+50);
                                		imgPnlheight=$("#image_upload_boxes").height()
                                		console.log("imgPnlheight ="+imgPnlheight);
                                	});
                                </script>
                            </c:forEach>
                            
                                <script> /* 상품 이미지 패널 높이 조정 */
                                	$(function(){
                                		var imgPnlheight=$("#image_upload_boxes").height();
                                		console.log("imgPnlheight ="+imgPnlheight);
                                		$("#image_upload_boxes").height(imgPnlheight-50);
                                		imgPnlheight=$("#image_upload_boxes").height()
                                		console.log("imgPnlheight ="+imgPnlheight);
                                	});
                                </script>
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th>이미지<br>상세정보</th>
                    <td colspan="3" id="table-row"><textarea required name="productInfo" placeholder="상품 상세정보를 입력하세요.">${productVO.productInfo}</textarea></td>
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
                            <div id="color_upload_boxes">
                                
                                <c:forEach items="${productOptionList}" var="productOptionVO" varStatus="st">
                                <div id="color_upload_box${st.count}">
                                <!-- T8_1 >> productOption -->
                                    <div>
                                        <input type="text" required name="productOption${st.count}" id="color_text${st.count}" placeholder="옵션을 입력하세요." size="20" value="${productOptionVO.productOptionValue}">
                                    </div>
                                    <div>
                                        <img id="color_image_btn${st.count}" src="<c:url value ='/images/icon/icon_minus.png' />">
                                    </div>
                                </div>
                                <script>
                                	$(function(){
                                		var colorPnlheight=$("#color_upload_boxes").height();
                                		console.log("colorPnlheight ="+colorPnlheight);
                                		$("#color_upload_boxes").height(colorPnlheight+50);
                                		colorPnlheight=$("#color_upload_boxes").height()
                                		console.log("colorPnlheight ="+colorPnlheight);
                                	});
                                </script>
                                </c:forEach>
                                
                                <script> /* 상품 이미지 패널 높이 조정 */
                                	$(function(){
                                		var colorPnlheight=$("#color_upload_boxes").height();
                                		console.log("colorPnlheight ="+colorPnlheight);
                                		$("#color_upload_boxes").height(colorPnlheight-50);
                                		colorPnlheight=$("#color_upload_boxes").height()
                                		console.log("colorPnlheight ="+colorPnlheight);
                                	});
                                </script>
                                
                            </div>   
                        </div> 
                    </td>
                </tr>
            </table>
            
        </section> 
        <br>
        <div  style="text-align:right; height: 50px;width: 100%">
	        	<button type="button" id="product_delete_btn">상품삭제</button>&nbsp;
	        	<button type="submit" id="product_update_btn">상품수정</button>
    	</div>
    	</form>
    </div>
    <!--관리자 페이지 footer 생략-->
    
</body>
</html>