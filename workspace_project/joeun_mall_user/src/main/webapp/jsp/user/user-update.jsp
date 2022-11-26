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
    <title>JoEunMall 회원수정</title>

    <!--javascript-->

	<script src="<c:url value='/jquery/jquery.min.js'/>"></script>
	<script>
	$(function(){
		
		//폼 점검
		$("#update_form").submit(function(){
			
			var submit_success = false;
			alert("폼점검");
			
			//비밀번호, 비밀번호 확인 비교
			if ($("#userPw").val()!=$("#userPw2").val()){
				alert("비밀번호가 맞지 않습니다.");
				$("#userPw").focus();//패스워드 재입력
			} else {
					 submit_success = 'true';
			}
			
			if(submit_success == "true"){
				alert("전송");
				return true;
			} 
		
			return false;
		});
	});
	</script>
	
	<!-- 1단계 : daum 우편번호 서비스 외장 JS(Javascript) 파일 링크 -->
	<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
	
	<!-- 2단계 : 소스 코드 복사 -->
	<script>
	// 도로명 주소 검색
	    function getPostcodeAddress() {
	        new daum.Postcode({
	            oncomplete: function(data) {
	                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
	
	                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
	                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	                var fullAddr = ''; // 최종 주소 변수
	                var extraAddr = ''; // 조합형 주소 변수
	
	                // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
	                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
	                    fullAddr = data.roadAddress;
	
	                } else { // 사용자가 지번 주소를 선택했을 경우(J)
	                    fullAddr = data.jibunAddress;
	                }
	
	                // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
	                if(data.userSelectedType === 'R'){
	                    //법정동명이 있을 경우 추가한다.
	                    if(data.bname !== ''){
	                        extraAddr += data.bname;
	                    }
	                    // 건물명이 있을 경우 추가한다.
	                    if(data.buildingName !== ''){
	                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                    }
	                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
	                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
	                }
	                
	                // 3단계 : 해당 필드들에 정보 입력
	                // 우편번호와 주소 정보를 해당 필드에 넣는다.
	                document.getElementById('userPost').value = data.zonecode; //5자리 새우편번호 사용
	                document.getElementById('userAddress').value = fullAddr;
	
	                // 커서를 상세주소 필드로 이동한다.
	                document.getElementById('userAddressDetail').focus();
	            }
	        }).open();
	    }
</script>
    <!--css-->
    <!--header와 footer는 고정-->
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/user-header-login.css' />">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/user-footer.css' />">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/user-join.css' />">

</head>

<body>
    <div id="wrap">
        <header>
            <a href="user-productlistCarousel.do"><img id="logo" src="<c:url value='/images/logo/logo_transparent.png' />" alt="JoEunMall"></a>
            <div id="header-icon">
                <input type="button" value="로그인">
                <a href="user-mypageOrder.do"><img src="<c:url value='/images/icon/icon_user.png' />"></a>
                <a href="user/user-shoppingBasket.do"><img src="<c:url value='/images/icon/icon_shopping_bag.png' />"></a>
                <a href="#"><img src="<c:url value='/images/icon/icon_search.png' />"></a>
            </div>
        </header>
        
        <section>
            <article id="title">
                <p>회원정보수정</p>
            </article>
            <article id="join">
                <form id="update_form" method="post" action="<c:url value='/user/user-updateProc.do'/>">
                    <table> 
                        <tr>
                            <td class="icon-width"><a class="checked-icon" /></td>
                            <th>아이디</th>
                            <td><input type="text" id="userId" name="userId" value="${defaultUser.userId}" readonly></td>
                        </tr>
                        <tr>
                            <td class="icon-width"><a class="checked-icon" /></td>
                            <th>비밀번호</th>
                            <td><input type="text" id="userPw" name="userPw" required pattern="\w{8,16}" placeholder="(영문 대소문자/숫자, 8~16자)"></td>
                        </tr>
                        <tr>
                            <td class="icon-width"><a class="checked-icon" /></td>
                            <th>비밀번호 확인</th>
                            <td><input type="text" id="userPw2" name="userPw2" required pattern="\w{8,16}" placeholder="(영문 대소문자/숫자, 8~16자)"></td>
                        </tr>
                        <tr>
                            <td class="icon-width"><a class="checked-icon" /></td>
                            <th>이름</th>
                            <td><input type="text" id="userName" name="userName" value="${defaultUser.userName}" readonly></td>
                        </tr>
                        <tr>
                            <td class="icon-width"></td>
                            <th></th>
                            <td><input type="text"  id="userPost" name="userPost" placeholder="(우편번호)" required readonly value="${defaultUser.userPost}">
                            <button type="button"  id="post_search" onclick="getPostcodeAddress()" style="width: 200px; height: 40px; background: #FBFBFB">주소검색</button>
                            
                            </td>
                        </tr>
                        <tr>
                            <td class="icon-width"></td>
                            <th>주소</th>
                            <td><input type="text" id="userAddress" name="userAddress" required readonly placeholder="(기본주소)" value="${defaultUser.userAddress}"></td>
                        </tr>
                        <tr>
                            <td class="icon-width"></td>
                            <th></th>
                            <td><input type="text" id="userAddressDetail" name="userAddressDetail" required placeholder="(상세주소)" value="${defaultUser.userAddressDetail}"></td>
                        </tr>
                        <tr>
                            <td class="icon-width"><a class="checked-icon" /></td>
                            <th>이메일</th>
                            <td><input type="email" id="userMail" name="userMail" pattern="[a-zA-Z0-9_+.-]+@([a-zA-Z0-9-]+\.)+[a-zA-Z0-9]{2,4}" required value="${defaultUser.userMail}"></td>
                        </tr>
                        <tr>
                            <td class="icon-width"></td>
                            <th>일반전화</th>
                            <td><input type="tel" id="userLandline" name="userLandline" pattern="0\d{1,2}-\d{3,4}-\d{4}" value="${defaultUser.userLandline}"></td>
                        </tr>
                        <tr>
                            <td class="icon-width"><a class="checked-icon" /></td>
                            <th>휴대전화</th>
                            <td><input type="tel" id="userMobile" name="userMobile" required pattern="010-\d{4}-\d{4}" value="${defaultUser.userMobile}"></td>
                        </tr>
                        <tr>
                            <td class="icon-width"><a class="checked-icon" /></td>
                            <th>성별</th>
                            <td>
								${defaultUser.userGender == '남' ? '남자':'여자'}
								<input type="hidden" name="userGender" value="${defaultUser.userGender}"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="icon-width"><a class="checked-icon" /></td>
                            <th>생년월일</th>
                            <td>
                            	<fmt:formatDate value="${defaultUser.userBirth}" pattern="yyyy년 MM월 dd일"/>
                            </td>
                        </tr>
                    </table>
                    <div id="update-button">
                        <input type="submit" value="회원수정">&nbsp;  
                        <input type="reset" value="취소">  
                    </div>
                </form>
            </article>

        </section>

        <footer>
            <ul>
                <li>이용약관</li>
                <li>개인정보취급방침</li>
                <a href="#"><li>1:1문의</li></a>
            </ul>
            <div id="horizontal-line"></div>
            <div id="footer-info">
                <p>법인명(상호) : 조은몰</p>
                <p>사업자 등록번호 안내 : 000-00-00000</p>
                <p>주소 : 서울특별시 강남구 강남대로78길 8 (한국빌딩 8층) 804호</p>
                <p>팀원 : 류정아, 이승은, 조규성, 박재민</p>
            </div>
        </footer>
    </div>
</body>
</html>