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
                    '<input type="file"  name="uploadFiles'+fileIndex+'" id="upload_file' +fileIndex+'"  placeholder="이미지 파일을 첨부하세요." size="20">&nbsp;'+
                    '<img id="upload_image_btn'+fileIndex+'" src="/joeunmall/images/icon/icon_minus.png">'+
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
                            '<input type="text"  required name="productOption'+fileIndex+'" id="color_text' +fileIndex+'"  placeholder="옵션을 입력하세요." size="20">'+
                        '</div>'+
                        '<div style="padding-left:5px">' +
                            '<img id="color_image_btn'+fileIndex+'" src="/joeunmall/images/icon/icon_minus.png">'+
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



$(function() { 

	/*  //사이즈  
    var size_cnt=1;//사이즈 파일박스 개수

    $("#size_upload_td").on('click','[id^=size_upload_btn]', function(e){
        
    //console.log('아무거나');
    //사이즈 이미지 추가 버튼 핸들러
            // var len="size_upload_btn".length;
            // var target_id = e.currentTarget.id;
            // var btn_id = target_id.substring(len);

            console.log('사이즈 추가');
            // console.log('btn_id='+ btn_id);

            console.log("size_cnt:" + size_cnt);//현황

            console.log("size_upload_td: " +$('td[id=size_upload_td]').height());
            var td_height=$('td[id=size_upload_td]').height();

            var fileIndex = $('[id^=size_upload_box]').length;

            console.log("fileIndex :" + fileIndex);//현황

            if (size_cnt<5){
		
                console.log("fileIndex : "+fileIndex);

                $('#size_upload_boxes').append(
                    '<div id="size_upload_box'+ fileIndex +'" style="background-color: pink;">' +                           
                    '<div style="float:left">' +
                        '&nbsp;&nbsp;' +
                        '<a href="#" id="size_upload_btn'+ fileIndex +'">' +
                        '<img id="size'+ fileIndex +'" src="/joeunmall/images/icon/icon_plus.png">' + 
                        '</a>' +
                    '</div>' +
                    '<div id="size_upload_content'+ fileIndex +'" style="float: left;">' +
                        '&nbsp;&nbsp;' +
                        '<select id="color_choose_btn'+ fileIndex +'" style="width: 100px;">' +
                            '<option value="blk"></option>' +
                            '<option value="rd"></option>' +
                        '</select>' +
                        '&nbsp;' +
                        '<input type="text" name="T11_1" id="size_text'+ fileIndex +'" placeholder="사이즈를 입력하세요" size="20" >' +
                        '&nbsp;' +
                        '<input type="radio" name="selle'+ fileIndex +'" value="ing">판매중' +
                        '&nbsp;' +
                        '<input type="radio" name="selle'+ fileIndex +'" value="end">품절' +
                    '</div>' +
                    '<div id="size_upload_del_menu'+ fileIndex +'" style="float:left">' +
                        '&nbsp;&nbsp;' +
                        '<a href="#" id="size_upload_del_btn">' +
                            '<img id="size_image_btn'+ fileIndex +'" src="/joeunmall/images/icon/icon_minus.png">' + 
                        '</a>' +
                    '</div>' +
                '</div>' 
                );
                
                
                $('td[id=size_upload_td]').height(td_height + 30);
                size_cnt++;
            } else {
                alert('사이즈는 5개까지만 추가가 됩니다.');
            }
        //function
    }); 
   


    //상품 업로드 패널    삭제
    $("#size_upload_td").on('click','img[id^=size_image_btn]', function(e){
        var id = e.currentTarget.id;
        var num = id.substring('size_image_btn'.length);
        console.log("클릭:"+ num);
        var box_id= "size_upload_box" + num;
        console.log('box_id=' + box_id);
        console.log('--size_cnt=' + size_cnt);
        console.log('size_file값=' + $('#size_text'+ num).val());
        //css변경
        
        if (size_cnt > 1) { 
            $('#'+box_id).remove(); //파일첨부 필드 삭제
            size_cnt--;//파일박스 개수 감소
            var td_height=$('td[id=size_upload_td]').height();
            $('td[id=size_upload_td]').height(td_height - 30);
        }else { //파일 업로드 필드가 1개 남았을때 초기화
            
            size_cnt=1;
        }
        console.log("size_cnt:" + size_cnt);//현황
        console.log("size_upload_td: " +$('td[id=size_upload_td]').height());
        
        
        console.log("td_height:"+td_height);
        
        
    });
    */

  //카테고리 선택시에 상품번호 자동선정
  //상품번호 = ''상품등록연도 2자리'_'상품 카테고리 번호 2자리'_'상품 등록 순서 3자리'
   $("#category").change(function(e){
	  console.log("카테고리 선택");
	  var cat_num =  $("#category").val();
	  console.log("선택된 값 =" + cat_num);
	  
	  var today = new Date();
	  var year = today.getUTCFullYear();
	  console.log("year =" + year.toString().substring(2));
	  
	  var product_index = year.toString().substring(2) + "_" + cat_num + "_";
	  
	//상품테이블에서 해당 카테고리 중 마지막 상품번호+1 >> 신규 상품 번호
	  $.ajax({
		  url:'/joeunmall/admin/admin-productIndex.do',
		  type:'get',
		  data:{ yearCate:product_index },
		  success:function(data){
			  console.log( data.split("_")[2]);
			  var maxNum = data.split("_")[2];
			  maxNum = parseInt(maxNum);
			 // console.log("maxNum =" + ( maxNum +1));
			  
			  maxNum = maxNum + 1; 
			  console.log("1.maxNum =" +  maxNum);
			  console.log("2.product_index =" + product_index);
			  //100미만이면 숫자 앞에 '0' 붙이기 ex)050
			  var lastNum = maxNum < 100 ? '0'+ Number(maxNum).toString() :  Number(maxNum).toString();
			  
			  product_index = product_index + lastNum;
			  console.log("3.product_index =" + product_index);
			  
			  $("#productIndex").val(product_index);
			  //상품 번호 필드를 읽기 전용으로 변환시킨다
			  $("#productIndex").attr("readonly","readonly");
		  }
	  });

	  
   });//카테고리 선택시에 상품번호 자동선정
   
    
});
