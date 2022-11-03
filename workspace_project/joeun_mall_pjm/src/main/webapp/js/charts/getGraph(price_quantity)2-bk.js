//명시적 함수 만들기
function chartAJAX(chart_categories){
	
	//ajax
	$.ajax({
		 url : "/joeunmall_pjm/graphJsonVO/priceQuantity",
		 type : 'get',
		 dataType:'text',
		 data : {
			 selectGraph : $("#" + id).val(),	// selectePrice/selctQuantity
			 selectPeriod : $('#select3').val(),
			 selectCloth : $('#select4').val()
		 }, // data 
		 success : function(msg) {
//			alert(msg)
			console.log("msg: " + msg);	
			//로그 뜨는 형식: msg: 
			//json 배열 형식임 -> [{"id":"ct-1","name":"티셔츠","quantity":1231,"price":23213},{"id":"ct-2","name":"팬츠/스커트","quantity":2312,"price":13213},{"id":"ct-3","name":"원피스","quantity":2311,"price":23113},{"id":"ct-4","name":"니트/가디건","quantity":2131,"price":31213},{"id":"ct-5","name":"자켓","quantity":2231,"price":13213}]
			//msg에 배열이 넘어온것 -> 배열에서 key와 값을 구분해줘야 함
			
			//chart data 유형 
			//categories: ['티셔츠', '팬츠/스커트', '원피스', '니트/가디건', '자켓']
			var json = JSON.parse(msg);
			console.log(json);
			console.log(json[1].price);
			console.log("json길이: " + json.length);
			console.log("json 개별요소-상품명: " + json[0].name);
			console.log("json 개별요소-판매개수: " + json[0].length);
			console.log("json 개별요소-판매금액: " + json[0].price);
			
			//js 배열 객체 api doc(라이브러리 덩어리): https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Array
			
			
			console.log("판매금액/판매수량: " + id);
			
			
			//else if select4 == 특정 항목일 경우, y축 항목이 바뀌도록 설정하는 부분
			var chart_categories = [];
			
//			$('#select4').click(function(){
//				
//				var chart_categories = [];
//				
//				if($('#select4').val() == "ct-all"){
//					chart_categories = ["티셔츠", "팬츠/스커트", "원피스", "니트/가디건", "자켓"];
//				} else if ($('#select4').val() == "ct-1"){
//					chart_categories = [];
//				} else if ($('#select4').val() == "ct-2"){
//					chart_categories = [];
//				} else if ($('#select4').val() == "ct-3"){
//					chart_categories = [];
//				} else if ($('#select4').val() == "ct-4"){
//					chart_categories = [];
//				} else if ($('#select4').val() == "ct-5"){
//					chart_categories = [];
//				};
//			});
			
			
			//ct-n(clothType) 포함하는 항목의 Dictionary
			var dict_Chart_Data_ct1 = {};
			var dict_Chart_Data_ct2 = {};
			var dict_Chart_Data_ct3 = {};
			var dict_Chart_Data_ct4 = {};
			var dict_Chart_Data_ct5 = {};
			
			for(var i=0; i<json.length; i++){
				
				//ct-n을 포함하는 항목 키(판매일자), 값(판매 가격) 쌍으로 dictionary에 넣음
				if(json[i].ct == "ct-1"){
					dict_Chart_Data_ct1[json[i].period] = json[i].price;
				} else if(json[i].ct == "ct-2"){
					dict_Chart_Data_ct2[json[i].period] = json[i].price;
				} else if(json[i].ct == "ct-3"){
					dict_Chart_Data_ct3[json[i].period] = json[i].price;
				} else if(json[i].ct == "ct-4"){
					dict_Chart_Data_ct4[json[i].period] = json[i].price;
				} else if(json[i].ct == "ct-5"){
					dict_Chart_Data_ct5[json[i].period] = json[i].price;
				};
			} //for문 종료
			
			
				var chart_Data;
				var price_quantity;
				var unit;
				
				if(id == "selectQuantity"){
					$('#selectQuantity_stat').val('선택');
					$('#selectPrice_stat').val('미선택');
					price_quantity = "판매 수량";
					unit = "벌";
					
					//각 dictionary의 key(sellPeriod)들의 개수(= 해당 dictionary의 인자 개수 = 차트의 quantity data)
					chart_Data = [Object.keys(dict_Chart_Data_ct1).length, Object.keys(dict_Chart_Data_ct2).length, Object.keys(dict_Chart_Data_ct3).length, Object.keys(dict_Chart_Data_ct4).length, Object.keys(dict_Chart_Data_ct5).length];
					
				} else{
					$('#selectQuantity_stat').val('미선택');
					$('#selectPrice_stat').val('선택');
					price_quantity = "판매 금액";
					unit = "원";
					
					let ct_1_price = Object.values(dict_Chart_Data_ct1).reduce(function add(sum, currValue){
						return sum + currValue;
					}, 0);
					
					let ct_2_price = Object.values(dict_Chart_Data_ct2).reduce(function add(sum, currValue){
						return sum + currValue;
					}, 0);
					
					let ct_3_price = Object.values(dict_Chart_Data_ct3).reduce(function add(sum, currValue){
						return sum + currValue;
					}, 0);
					
					let ct_4_price = Object.values(dict_Chart_Data_ct4).reduce(function add(sum, currValue){
						return sum + currValue;
					}, 0);
					
					let ct_5_price = Object.values(dict_Chart_Data_ct5).reduce(function add(sum, currValue){
						return sum + currValue;
					}, 0);
					
					chart_Data = [ct_1_price, ct_2_price, ct_3_price, ct_4_price, ct_5_price];
					
				}//if문 끝
				
			console.log(dict_Chart_Data_ct1);
			console.log(dict_Chart_Data_ct2);
			console.log(dict_Chart_Data_ct3);
			console.log(dict_Chart_Data_ct4);
			console.log(dict_Chart_Data_ct5);
			
			console.log("차트 데이터: " + chart_Data);	
			console.log("선택된 기간: " + $('#select3').val());
		    console.log("선택된 의류: " + $('#select4').val());
			console.log("판매금액/판매수량: " + price_quantity);
			console.log(unit);
			console.log("판매수량 선택여부: " + $('#selectQuantity_stat').val());
			console.log("판매금액 선택여부: " + $('#selectPrice_stat').val());
			
			//차트 그리기
			Highcharts.chart('container', {
	            chart: {
	                type: 'bar'
	            },
	                title: {
	                text:'&nbsp'
	            },
	            
	            xAxis: {
	            	categories: chart_categories,
	                title: {
	                    text: null
	                }
	            },
	            yAxis: {
	                min: 0,
	                title: {
	                    text: unit,
	                    align: 'high'
	                },
	                labels: {
	                    overflow: 'justify'
	                }
	            },
	            tooltip: {
	                valueSuffix: unit
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
	                name: price_quantity,
	                data: chart_Data
	            }]
	        });
	        
	 	} // success
	 	
	 }); // ajax
	
}//function charAJAX() 끝








$(function(){
//		var id = $("input[name='selectGraph']").val();
//		console.log(id);
	
	var chart_categories = [];
		
	$("input[name='selectGraph']").change(function(e){ /*라디오 박스 선택 항목에 따라 바뀌게 설정*/
	
		id = e.currentTarget.id;	//특정 이벤트(e = click)의 대상 태그(name이 selectPraph)의 id를 가져옴.
		
		
	    console.log("선택된 기간: " + $('#select3').val());
	    console.log("선택된 의류: " + $('#select4').val());
	
	    $('#select3_stat').val($('#select3').val());
	    $('#select4_stat').val($('#select4').val());
	    
	    /*
	    //ajax
		$.ajax({
			 url : "/joeunmall_pjm/graphJson/priceQuantity",
			 type : 'get',
			 dataType:'text',
			 data : {
				 selectGraph : $("#" + id).val()
			 }, // data 
			 success : function(msg) {
				//alert(msg)
				console.log("msg: " + msg);	//로그 뜨는 형식: msg: {"팬츠/스커트":227,"티셔츠":231}
				//Json 해석(parse): 문자열을 json으로 변환/해석해주는 것
				//js JSON api doc: https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/JSON
				var json = JSON.parse(msg);
				console.log("json data: " + json['팬츠/스커트']);
				console.log("Json 요소 개수: " + Object.keys(json).length);
				 
				//chart data 유형 
				//categories: ['티셔츠', '팬츠/스커트', '원피스', '니트/가디건', '자켓']
				//data: [231, 227, 132, 121, 80]
				
				//js 배열 객체 api doc(라이브러리 덩어리): https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Array
				
				var chart_categories = [];
				var chart_quantityData = [];
				 
				//json을 배열로 치환
				for(var i=0; i<Object.keys(json).length; i++){
					
					var key = Object.keys(json)[i];
					console.log("Json 요소의" + (i+1) + "번째 key: " + key);
					chart_categories.push(key);
					
					var value = json[key];
					console.log("Json 요소의" + (i+1) + "번째 value: " + value);
					chart_quantityData.push(value);
				}
				
				//차트 그리기
				Highcharts.chart('container', {
		            chart: {
		                type: 'bar'
		            },
		                title: {
		                text:'&nbsp'
		            },
		            
		            xAxis: {
		                //categories: ['티셔츠', '팬츠/스커트', '원피스', '니트/가디건', '자켓'],
		            	categories: chart_categories,
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
		                //data: [231, 227, 132, 121, 80]
		                data: chart_quantityData
		            }]
		        });
		 	} // success
		 }); // ajax
		 */
	    
	    /*
	  //ajax
		$.ajax({
			 url : "/joeunmall_pjm/graphJsonList/priceQuantity",
			 type : 'get',
			 dataType:'text',
			 data : {
				 selectGraph : $("#" + id).val()
			 }, // data 
			 success : function(msg) {
				//alert(msg)
				console.log("msg: " + msg);	//로그 뜨는 형식: msg: ["티셔츠,23321","팬츠/스커트,232127","원피스,132312","니트/가디건,121212","자켓,82110"] ("키, 값", "키, 값",) 이런 구조
				//msg에 배열이 넘어온것 -> 배열에서 key와 값을 구분해줘야 함
				
				//chart data 유형 
				//categories: ['티셔츠', '팬츠/스커트', '원피스', '니트/가디건', '자켓']
				//data: [231, 227, 132, 121, 80]
				
				//문자열을 배열로 치환
				var arr = Array.from(["티셔츠,23321","팬츠/스커트,232127","원피스,132312","니트/가디건,121212","자켓,82110"], x => x);
				
				console.log("msg: " + arr[0]);
				console.log("msg 길이: " + arr.length);
				
				//js 배열 객체 api doc(라이브러리 덩어리): https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Array
				
				
				var chart_categories = [];
				var chart_quantityData = [];
				 
				//arr 원본 데이터 사용
				for(var i=0; i<arr.length; i++){
					
					var element = arr[i].split(",");
					var key = element[0];
					
					console.log("arr 요소의" + (i+1) + "번째 key: " + key);
					chart_categories.push(key);
					
					var value = Number.parseInt(element[1]);
					console.log("arr 요소의" + (i+1) + "번째 value: " + value);
					chart_quantityData.push(value);
				}
				
				//차트 그리기
				Highcharts.chart('container', {
		            chart: {
		                type: 'bar'
		            },
		                title: {
		                text:'&nbsp'
		            },
		            
		            xAxis: {
		                //categories: ['티셔츠', '팬츠/스커트', '원피스', '니트/가디건', '자켓'],
		            	categories: chart_categories,
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
		                //data: [231, 227, 132, 121, 80]
		                data: chart_quantityData
		            }]
		        });
		        
		 	} // success
		 	
		 }); // ajax
		*/
		
		
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
	    
	    chartAJAX(chart_categories);
		
	}); //price/quantity function 끝
	
	$('#select4').click(function(){
		
		if($('#select4').val() == "ct-all"){
			chart_categories = ["티셔츠", "팬츠/스커트", "원피스", "니트/가디건", "자켓"];
		} else if ($('#select4').val() == "ct-1"){
			chart_categories = [ ];
		} else if ($('#select4').val() == "ct-2"){
			chart_categories = [ ];
		} else if ($('#select4').val() == "ct-3"){
			chart_categories = [ ];
		} else if ($('#select4').val() == "ct-4"){
			chart_categories = [ ];
		} else if ($('#select4').val() == "ct-5"){
			chart_categories = [ ];
		};
		
	});
	
	
})//$(function) 끝
