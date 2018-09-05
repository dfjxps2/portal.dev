$(function () {
    var lineChart = echarts.init(document.getElementById("echarts-line-chart"));
    var lineoption = {
        title : {
            text: ''
        },
        tooltip : {
            trigger: 'axis'
        },
        legend: {
        data:['经济运行','城市生态','城市交通','政务服务','民生服务']
	    },
        grid:{
            x:40,
            x2:40,
            y2:24
        },
        calculable : true,
        xAxis : [
            {
                type : 'category',
                boundaryGap : false,
                data : ['2012','2013','2014','2015','2016','2017','2018']
            }
        ],
        yAxis : [
                 {
                     type : 'value',
                     axisLine:{show:true,
                     lineStyle:{color:'#ccc'}
                     },
                     axisTick:{
                     show:false,                         
                     },
                     axisLabel:{
                     show:true,
                     inside:true
                     },
                     min:0,
                     max:100,
                     splitNumber:8
                      
                 }
        ],
         series : [
        
        {
            name:'城市生态',
            type:'line',
//            stack: '总量',
            data:[0, 0, 0, 0, 0, 0, 10]
        },
        {
            name:'城市交通',
            type:'line',
//            stack: '总量',
            data:[0, 0, 0, 0, 0, 0, 20]
        },
        {
            name:'政务服务',
            type:'line',
//            stack: '总量',
            data:[0, 0, 0, 0, 0, 0, 30]
        },
        {
            name:'民生服务',
            type:'line',
//            stack: '总量',
            data:[0, 0, 0, 0, 0, 0, 40]
        },
        {
            name:'经济运行',
            type:'line',
//            stack: '总量',
            data:[0, 5, 10, 15, 20, 30, 50]
        },
    ]
    };

    lineChart.setOption(lineoption);
    $(window).resize(lineChart.resize);
    var pieChart = echarts.init(document.getElementById("echarts-pie-chart"));
    var pieoption = {
        title : {
            text: '',
            subtext: '',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient : 'vertical',
            x : 'left',
            data:['经济运行','城市生态','城市交通','政务服务','民生服务']
        },
        calculable : true,
        series : [
            {
                name:'访问来源',
                type:'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:[
                    {value:50, name:'经济运行'},
                    {value:10, name:'城市生态'},
                    {value:20, name:'城市交通'},
                    {value:30, name:'政务服务'},
                    {value:40, name:'民生服务'}
                ]
            }
        ]
    };
    pieChart.setOption(pieoption);
    $(window).resize(pieChart.resize);

  

 


});
