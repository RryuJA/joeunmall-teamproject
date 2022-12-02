/**
 * 상품 상세 보기 
 */

   //상품
$(function() { 
    
    var cnt=1;//파일박스 개수
    
    //console.log('아무거나');
    //상품 이미지 추가 버튼 핸들러
    $('#image_upload_btn').click(function(){
        
            console.log('상품이미지 추가');

            console.log("기존 상품이미지 수="+$("[name^=uploadFiles]").length);
            
            cnt=$("[name^=uploadFiles]").length;
            
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
	                        '<div class="filebox" style="float: left">'+
	                            '<div style="float: left">'+ 
									'<input class="upload-name" disabled>'+ 
									'<label for="upload_file'+fileIndex+'">선택</label>'+
									'<input type="file" name="uploadFiles'+fileIndex+'" id="upload_file'+fileIndex+'" class="upload-hidden">'+ 
								 '</div>'+
								 '<div style="padding-top: 5px">'+
								  	'<img id="upload_image_btn'+fileIndex+'" src="/joeunmall/images/icon/icon_minus.png">'+ 
								  '</div>'+
							  '</div>'+
					    '</div>');
                
                
                //업로드 파일 삭제 여부 필드 추가
                //(파일 삭제 여부 저장)
                $("#image_title_box").append(
                		'<div style="float: left">'+
					  		'<input type="hidden" id="upload_image_delete_yn'+fileIndex+'" name="uploadImageDeleteYn'+fileIndex+'" value="N" size="1">'+
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
    	alert("삭제");

    	if  (confirm("기존 이미지가 있으면 삭제 됩니다. 진행하시겠습니까?")){
    		
    		var id = e.currentTarget.id;
            var num = id.substring('upload_image_btn'.length);
            console.log("클릭:"+ num);
            var box_id= "image_upload_box" + num;
            console.log('box_id=' + box_id);

            console.log('upload_file값=' + $('#upload_file'+ num).val());
            //css변경
           //$('[name="'+ name +'"]').css('background','red');
            if (cnt > 0) { //버그패치 1 >> 0으로 변경 - 1번쨰 이미지 삭제가 안돼서 
                $('#'+box_id).remove(); //파일첨부 필드 삭제
                
               //업로드 파일 삭제 여부 필드값 변경
                $("#upload_image_delete_yn"+ num).val("Y");// Y >> 삭제를 의미
                
                cnt--;//파일박스 개수 감소
            }else { //파일 업로드 필드가 1개 남았을때 초기화
                $('#upload_file'+ num).val("");
            }
            console.log("cnt:" + cnt);//현황
            console.log("image_upload_td: " +$('td[id=image_upload_td]').height());
            
            var td_height=$('td[id=image_upload_td]').height();
            $('td[id=image_upload_td]').height(td_height - 50);
    	} //confirm 
    	
    });


});

//색상
$(function() { 
    
    var color_cnt=1;//색상 파일박스 개수
    
    //console.log('아무거나');
    //컬러 이미지 추가 버튼 핸들러
    $('#color_upload_btn').click(function(){
        
            console.log('컬러이미지 추가');

            console.log("기존 옵션수 ="+$('[name^=productOption]').length);
            
            color_cnt=$('[name^=productOption]').length;
            
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
