$("input[name='selectGraph']").click(function(){ /*라디오 박스 선택 항목에 따라 바뀌게 설정*/
    if($("input[name='selectGraph']:checked").val() == 'quantity'){ /*판매 수량(quantity) 선택시 만들어지는 그래프*/
    Highcharts.chart('container', {
        chart: {
            type: 'column'
        },
        title: {
            text: '&nbsp'
        },
        subtitle: {
            text: '&nbsp'
        },
        credits:{
            enabled: false
        },
        xAxis: {
            categories: [
                '1월',
                '2월',
                '3월',
                '4월',
                '5월',
                '6월',
                '7월',
                '8월',
                '9월',
                '10월',
                '11월',
                '12월'
            ],
            crosshair: true
        },
        yAxis: {
            title: {
                text: '벌'
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y:.1f}</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [{
            name: '판매 수량',
            data: [401, 520, 853, 627, 953, 416, 850, 782, 351, 854, 219, 647]
        }]
    });}

    else/*판매 금액(price) 선택시 만들어지는 그래프*/
    {
        Highcharts.chart('container', {
            chart: {
                type: 'column'
            },
            title: {
                text: '&nbsp'
            },
            subtitle: {
                text: '&nbsp'
            },
            credits:{
                enabled: false
            },
            xAxis: {
                categories: [
                    '1월',
                    '2월',
                    '3월',
                    '4월',
                    '5월',
                    '6월',
                    '7월',
                    '8월',
                    '9월',
                    '10월',
                    '11월',
                    '12월'
                ],
                crosshair: true
            },
            yAxis: {
                title: {
                    text: '원'
                }
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                    '<td style="padding:0"><b>{point.y:.1f}</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                }
            },
            series: [{
                name: '판매 금액',
                data: [401000, 520000, 428000, 1320000, 827000, 721000, 1240000, 998000, 874200, 1009000, 1234000, 647000]
            }]
        });
    }


});


