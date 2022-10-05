$(function(){
    $("#select3").change(function(e){
        console.log("선택된 기간: " + $('#select3').val());
        console.log("선택된 의류: " + $('#select4').val());
    });
    $("#select4").change(function(e){
        console.log("선택된 기간: " + $('#select3').val());
        console.log("선택된 의류: " + $('#select4').val());
    });
});