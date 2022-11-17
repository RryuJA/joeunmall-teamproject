function monthlyChartCreator(chartData, price_quantity, unit){
	//하이차트
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
                text: unit
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y}</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            
        },
        plotOptions: {
            column: {
//                pointPadding: 0.2,
//                borderWidth: 0
            	dataLabels: {
                    enabled: true
                }
            }
        },
        series: [{
            name: price_quantity,
            data: chartData
        }]
    });
}