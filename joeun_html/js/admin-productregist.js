   //상품
$(function() { 
    
    var cnt=1;//파일박스 개수
    
    //console.log('아무거나');
    //상품 이미지 추가 버튼 핸들러
    $('#image_upload_btn').click(function(){
        
            console.log('상품이미지 추가');

            console.log("cnt:" + cnt);//현황

            console.log("image_upload_td: " +$('td[id=image_upload_td]').height());
            var td_height=$('td[id=image_upload_td]').height();

            var fileIndex = $('[id^=image_upload_box]').length;

            console.log("fileIndex :" + fileIndex);//현황

            if (cnt<5){
		
                console.log("fileIndex : "+fileIndex);
                //새로생기는 창
                $('#image_upload_boxes').append(
                    '<div id="image_upload_box'+fileIndex+'">'+
                    '<input type="file"  name="T5_'+fileIndex+'" id="upload_file' +fileIndex+'"  placeholder="이미지 파일을 첨부하세요." size="20">&nbsp;'+
                    '<img id="upload_image_btn'+fileIndex+'" src="images/icon/icon_minus.png">'+
                    '</div>');

                $('td[id=image_upload_td]').height(td_height + 50);
                cnt++;
            } else {
                alert('파일은 5개까지만 추가가 됩니다.');
            }
        }//function
    ); 

    //상품 업로드 패널 삭제
    $("#image_upload_td").on('click','img[id^=upload_image_btn]', function(e){
        var id = e.currentTarget.id;
        var num = id.substring('upload_image_btn'.length);
        console.log("클릭:"+ num);
        var box_id= "image_upload_box" + num;
        console.log('box_id=' + box_id);

        console.log('upload_file값=' + $('#upload_file'+ num).val());
        //css변경
       //$('[name="'+ name +'"]').css('background','red');
        if (cnt > 1) { 
            $('#'+box_id).remove(); //파일첨부 필드 삭제
            cnt--;//파일박스 개수 감소
        }else { //파일 업로드 필드가 1개 남았을때 초기화
            $('#upload_file'+ num).val("");
        }
        console.log("cnt:" + cnt);//현황
        console.log("image_upload_td: " +$('td[id=image_upload_td]').height());
        
        var td_height=$('td[id=image_upload_td]').height();
        $('td[id=image_upload_td]').height(td_height - 50);
    });


});

//색상
$(function() { 
    
    var color_cnt=1;//색상 파일박스 개수
    
    //console.log('아무거나');
    //컬러 이미지 추가 버튼 핸들러
    $('#color_upload_btn').click(function(){
        
            console.log('컬러이미지 추가');

            console.log("color_cnt:" + color_cnt);//현황

            console.log("color_upload_td: " +$('td[id=color_upload_td]').height());
            var td_height=$('td[id=color_upload_td]').height();

            var fileIndex = $('[id^=color_upload_box]').length;

            console.log("fileIndex :" + fileIndex);//현황

            if (color_cnt<5){
		
                console.log("fileIndex : "+fileIndex);
                //새로생기는 창
                $('#color_upload_boxes').append(
                    '<div id="color_upload_box' +fileIndex +'">' +
                        '<div style="float:left; height: 50px;  padding:0;">'+
                            '<input type="text"  name="T8_'+fileIndex+'" id="color_text' +fileIndex+'"  placeholder="옵션을 입력하세요." size="20">'+
                        '</div>'+
                        '<div style="padding-left:5px">' +
                            '<img id="color_image_btn'+fileIndex+'" src="images/icon/icon_minus.png">'+
                        '</div>' +
                    '</div>');

                $('td[id=color_upload_td]').height(td_height + 40);
                color_cnt++;
            } else {
                alert('옵션은 5개까지만 추가가 됩니다.');
            }
        }//function
    ); 


    //상품 업로드 패널 삭제
    $("#color_upload_td").on('click','img[id^=color_image_btn]', function(e){
        var id = e.currentTarget.id;
        var num = id.substring('color_image_btn'.length);
        console.log("클릭:"+ num);
        var box_id= "color_upload_box" + num;
        console.log('box_id=' + box_id);

        console.log('--color_cnt=' + color_cnt);
        console.log('color_file값=' + $('#color_text'+ num).val());
        //css변경
       //$('[name="'+ name +'"]').css('background','red');
        if (color_cnt > 1) { 
            $('#'+box_id).remove(); //파일첨부 필드 삭제
            color_cnt--;//파일박스 개수 감소


            // var td_height=$('td[id=color_upload_td]').height();
            // $('td[id=color_upload_td]').height(td_height - 30);
        }else { //파일 업로드 필드가 1개 남았을때 초기화
            $('#color_text'+ num).val("");
            //var td_height=$('td[id=color_upload_td]').height();
            //$('td[id=color_upload_td]').height(td_height - 30);
            // color_cnt=1;
        }
        console.log("color_cnt:" + color_cnt);//현황
        console.log("color_upload_td: " +$('td[id=color_upload_td]').height());
        
        var td_height=$('td[id=color_upload_td]').height();
            $('td[id=color_upload_td]').height(td_height - 40);
        
    });

    
}); 
