//명시적 함수 만들기
//graphType이 나올 수 있는 애들 : quantity price select3(기간) select4(의류 종류)
function chartAJAX(graphType, sellPeriod, clothType){
	
	alert(graphType);
	//ajax
	$.ajax({
		 url : "/joeunmall_pjm/graphJsonVO/priceQuantity",
		 type : 'get',
		 dataType:'text',
		 data : {
			 graphType : $("#" + graphType).val(),
			 sellPeriod: $('#select3').val(),
			 clothType: $('#select4').val()
		 }, // data 
		 success : function(msg) {
//			alert(msg)
			console.log("msg: " + msg);	
			//msg에 배열이 넘어온것 -> 배열에서 key와 값을 구분해줘야 함
			
			//chart data 유형 
			var json = JSON.parse(msg);
			console.log(json);
			
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
			console.log(dict_Chart_Data_ct1);
			console.log(dict_Chart_Data_ct2);
			console.log(dict_Chart_Data_ct3);
			console.log(dict_Chart_Data_ct4);
			console.log(dict_Chart_Data_ct5);
		
			var chartData;
			var chartCategories;
			var price_quantity;
			var unit;
			
			//y축 결정 -> 정렬해서 가장 많이 팔린 상위 5개 항목 y축 이름으로 넣어야 함
			if($('#select4').val() == "ct-all"){
				chartCategories = ["티셔츠", "팬츠/스커트", "원피스", "니트/가디건", "자켓"];
			} else if($('#select4').val() == "ct-1"){
				chartCategories = ["t1", "t2", "t3", "t4", "t5"];
			} else if($('#select4').val() == "ct-2"){
				chartCategories = ["p1", "p2", "p3", "p4", "p5"];
			} else if($('#select4').val() == "ct-3"){
				chartCategories = ["o1", "o2", "o3", "o4", "o5"];
			} else if($('#select4').val() == "ct-4"){
				chartCategories = ["n1", "n2", "n3", "n4", "n5"];
			} else if($('#select4').val() == "ct-5"){
				chartCategories = ["j1", "j2", "j3", "j4", "j5"];
			}
			
			
			
			
			if(graphType == "selectQuantity"){
				$('#selectQuantity_stat').val('선택');
				$('#selectPrice_stat').val('미선택');
				price_quantity = "판매 수량";
				unit = "벌";
				
				//각 dictionary의 key(sellPeriod)들의 개수(= 해당 dictionary의 인자 개수 = 차트의 quantity data)
				chartData = [Object.keys(dict_Chart_Data_ct1).length, Object.keys(dict_Chart_Data_ct2).length, Object.keys(dict_Chart_Data_ct3).length, Object.keys(dict_Chart_Data_ct4).length, Object.keys(dict_Chart_Data_ct5).length];
				
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
				
				chartData = [ct_1_price, ct_2_price, ct_3_price, ct_4_price, ct_5_price];
				
			}//if문 끝
				
			console.log("차트 데이터: " + chartData);	
			console.log("선택된 기간: " + $('#select3').val());
		    console.log("선택된 의류: " + $('#select4').val());
			console.log("판매금액/판매수량: " + price_quantity);
			console.log(unit);
			console.log("판매수량 선택여부: " + $('#selectQuantity_stat').val());
			console.log("판매금액 선택여부: " + $('#selectPrice_stat').val());
			
			//차트 그리기
			chartCreator(chartData, chartCategories, price_quantity, unit);
			
//			//차트 그리기
//			Highcharts.chart('container', {
//	            chart: {
//	                type: 'bar'
//	            },
//	                title: {
//	                text:'&nbsp'
//	            },
//	            
//	            xAxis: {
//	            	categories: chart_categories,
//	                title: {
//	                    text: null
//	                }
//	            },
//	            yAxis: {
//	                min: 0,
//	                title: {
//	                    text: unit,
//	                    align: 'high'
//	                },
//	                labels: {
//	                    overflow: 'justify'
//	                }
//	            },
//	            tooltip: {
//	                valueSuffix: unit
//	            },
//	            plotOptions: {
//	                bar: {
//	                    dataLabels: {
//	                        enabled: true
//	                    }
//	                }
//	            },
//	            credits: { //이건 그냥 쓸데없는거 --> 하이차트 워터마크 없애는 부분임
//	                enabled: false
//	            },
//	            series: [{ //-------------------- 나중에 서버에서 데이터 불러오는 값 여기에 넣어야 됨----------------------------
//	                name: price_quantity,
//	                data: chartData
//	            }]
//	        });
	        
	 	} // success
	 	
	 }); // ajax
	
}//function charAJAX() 끝
