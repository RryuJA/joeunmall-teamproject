$(function() {
    // 보이기 | 숨기기
    $(window).scroll(function() {
       if ($(this).scrollTop() > 200) { //300 넘으면 버튼이 보여짐
             $('#topButton').fadeIn();
             } else {
             $('#topButton').fadeOut();
       }
    });

    // 버튼 클릭시
    $("#topButton").click(function() {   
    $('html, body').animate({scrollTop : 0/*0 까지 animation 이동*/}, 100 /*속도 100*/);          
     return false;
     });
   });