/* 
처음 창이 떴을때 그래프는 없고 위에 선택 항목은 다 떠있음
판매 금액을 누르
*/

$(function(){

    var selectedPeriod = '';    //선택된 기간
    var selectedCloth = '';     //선택된 의류

    /* 기간 선택 값 불러오기 */
    $('#select3').change(function(e){

        console.log("기간 선택");        /*selectPeriod select4 selectCloth*/
        console.log('선택된 기간(값):' + $('#select3').val());
        console.log('선택된 기간(타이틀):' + $('#select3 option').index($('#select3 option:selected')));

        console.log("의류 선택");        /*selectPeriod select4 selectCloth*/
        console.log('선택된 기간(값):' + $('#select4').val());
        console.log('선택된 기간(타이틀):' + $('#select4 option').index($('#select4 option:selected')));

        //대시보드 상태변수
        console.log("판매 금액 선택 여부: " + $('#selectPrice_stat').val());
        console.log("판매 수량 선택 여부: " + $('#selectQuantity_stat').val());

        //차트요소 변수: DB에서 전송받아야 함 AJAX!
        var pjm_xAxisName = '판매 변수';
        var pjm_data = [212, 28, 45, 2, 3]; 
        
        //기간별 data: DB에서 전송받아야 함(이하는 임시 데이터)
        selectedPeriod = $('#select3').val();
        if(selectedPeriod == '2209'){
            pjm_data = [12, 128, 145, 12, 13]; 
        } else if(selectedPeriod == '2208'){
            pjm_data = [12, 18, 15, 2, 14]; 
        } else if(selectedPeriod == '2207'){
            pjm_data = [112, 8, 5, 2, 1]; 
        } else if(selectedPeriod == '2206'){
            pjm_data = [1211, 11118, 111115, 112, 1114]; 
        };


        //하이차트
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
            credits: { /*이건 그냥 쓸데없는거 --> 하이차트 워터마크 없애는 부분임*/
                enabled: false
            },
            series: [{ /*-------------------- 나중에 서버에서 데이터 불러오는 값 여기에 넣어야 됨----------------------------*/
                // name: '판매 금액',
                name: pjm_xAxisName,
                // data: [231, 227, 132, 121, 80]
                data: pjm_data
            }]
        })
        
    }); 

    /* 의류 선택 값 불러오기*/
    $('#select4').change(function(e){
        console.log("의류 선택");        /*selectPeriod select4 selectCloth*/
        console.log('선택된 기간(값):' + $('#select4').val());
        console.log('선택된 기간(타이틀):' + $('#select4 option').index($('#select4 option:selected')));                    
    }); 
    
    
    
});