$(function(){

    
    $(document).ready(function(){
        console.log("선택된 이미지" + $("#sub-img1").attr("src"));
        $("#img1").show();
        $("#img2").hide();
        $("#img3").hide();
        $("#img4").hide();
        $("#img5").hide();
    

        $("#sub-img1").click(function(){
            $("#img1").show();
            $("#img2").hide();
            $("#img3").hide();
            $("#img4").hide();
            $("#img5").hide();
        });

        $("#sub-img2").click(function(){
            console.log("선택된 이미지" + $("#sub-img2").attr("src"));
            $("#img1").hide();
            $("#img2").show();
            $("#img3").hide();
            $("#img4").hide();
            $("#img5").hide();
        });

        $("#sub-img3").click(function(){
            console.log("선택된 이미지" + $("#sub-img3").attr("src"));
            $("#img1").hide();
            $("#img2").hide();
            $("#img3").show();
            $("#img4").hide();
            $("#img5").hide();
        });

        $("#sub-img4").click(function(){
            console.log("선택된 이미지" + $("#sub-img4").attr("src"));
            $("#img1").hide();
            $("#img2").hide();
            $("#img3").hide();
            $("#img4").show();
            $("#img5").hide();
        });

        $("#sub-img5").click(function(){
            console.log("선택된 이미지" + $("#sub-img5").attr("src"));
            $("#img1").hide();
            $("#img2").hide();
            $("#img3").hide();
            $("#img4").hide();
            $("#img5").show();
        });
    });

    
})