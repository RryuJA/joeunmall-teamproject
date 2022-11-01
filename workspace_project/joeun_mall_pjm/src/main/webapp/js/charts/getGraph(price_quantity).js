// // 220923 PJM custom
// $("select[name='selectPeriod']").change(function(){
//     console.log("기간 선택");        /*selectPeriod select4 selectCloth*/
//     console.log('선택된 기간(값):' + $('#select3').val());
//     console.log('선택된 기간(타이틀):' + $('#select3 option').index($('#select3 option:selected')));

//     console.log("의류 선택");        /*selectPeriod select4 selectCloth*/   
//     console.log('선택된 기간(값):' + $('#select4').val());
//     console.log('선택된 기간(타이틀):' + $('#select4 option').index($('#select4 option:selected')));
    
// });

// $("select#select4").change(function(){

// })

//$("input[name='selectGraph']").change(function(){ /*라디오 박스 선택 항목에 따라 바뀌게 설정*/
$("input[name='selectGraph']").click(function(e){ /*라디오 박스 선택 항목에 따라 바뀌게 설정*/

	var id = e.currentTarget.id;	//특정 이벤트(e = click)의 대상 태그의 id를 가져옴.
	
//	console.log("가져온 id: " + id);
	
    console.log("선택된 기간: " + $('#select3').val());
    console.log("선택된 의류: " + $('#select4').val());

    $('#select3_stat').val($('#select3').val());
    $('#select4_stat').val($('#select4').val());
    
    //인자 전송
    console.log("판매금액/판매수량: " + $("#" + id).val());
    alert("전송");
    
    //ajax
	$.ajax({
		 url : "/joeunmall_pjm/graphJson/priceQuantity",
		 type : 'get',
		 dataType:'text',
		 data : {
			 selectGraph : $("#" + id).val()
		 }, // data 
		 success : function(msg) {
			 alert(msg)
	 	} // success
	 }); // ajax
	 

/*    
    if($("input[name='selectGraph']:checked").val() == 'quantity'){ //판매 수량(quantity) 선택시 만들어지는 그래프
        
        //상태변수 대시보드에 저장
        if($('#selectQuantity_stat').val() == '미선택'){
            $('#selectQuantity_stat').val('선택');
            $('#selectPrice_stat').val('미선택');
        };

        Highcharts.chart('container', {
            chart: {
                type: 'bar'
            },
                title: {
                text:'&nbsp'
            },
            
            xAxis: {
                categories: ['티셔츠', '팬츠/스커트', '원피스', '니트/가디건', '자켓'],
                title: {
                    text: null
                }
            },
            yAxis: {
                min: 0,
                title: {
                    text: '벌',
                    align: 'high'
                },
                labels: {
                    overflow: 'justify'
                }
            },
            tooltip: {
                valueSuffix: ' 벌'
            },
            plotOptions: {
                bar: {
                    dataLabels: {
                        enabled: true
                    }
                }
            },
            credits: { //이건 그냥 쓸데없는거 --> 하이차트 워터마크 없애는 부분임
                enabled: false
            },
            series: [{ //-------------------- 나중에 서버에서 데이터 불러오는 값 여기에 넣어야 됨----------------------------
                    name: '판매 수량',
                data: [231, 227, 132, 121, 80]
            }]
        });}

        else//판매 금액(price) 선택시 만들어지는 그래프
        {
            //상태변수 대시보드에 저장
            if($('#selectPrice_stat').val() == '미선택'){
                $('#selectPrice_stat').val('선택');
                $('#selectQuantity_stat').val('미선택');
            };

            Highcharts.chart('container', {
                chart: {
                    type: 'bar'
                },
                    title: {
                    text:'&nbsp'
                },
                
                xAxis: {
                    categories: ['티셔츠', '팬츠/스커트', '원피스', '니트/가디건', '자켓'],
                    title: {
                        text: null
                    }
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: '원',
                        align: 'high'
                    },
                    labels: {
                        overflow: 'justify'
                    }
                },
                tooltip: {
                    valueSuffix: ' 원'
                },
                plotOptions: {
                    bar: {
                        dataLabels: {
                            enabled: true
                        }
                    }
                },
                credits: {
                    enabled: false
                },
                series: [{ 
                        name: '판매 금액',//-------------------- 나중에 서버에서 데이터 불러오는 값 여기에 넣어야 됨----------------------------
                    data: [1211000, 3211000, 1412000, 1821000, 5483000]
                }]
            });
        }
*/
    
});


