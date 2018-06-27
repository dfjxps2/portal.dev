//var colo = ['rgb(255,0,0)','rgb(0,128,0)','rgb(255,140,0)','rgb(0,0,255)','rgb(128,0,128)','rgb(255,0,255)','rgb(0,255,255)'];
var colo =['rgb(19,182,179)','rgb(99,187,241)','rgb(242,173,57)','rgb(250,133,100)','rgb(71,200,241)','rgb(108,226,224)','rgb(252,150,38)','rgb(95,159,242)','rgb(168,217,72)','rgb(233,70,124)','rgb(255,193,0)','rgb(126,221,151)','rgb(245,76,96)','rgb(218,98,104)','rgb(109,219,200)'];
//标题和图例字体颜色
var titleColor = '#333333';
//图表中字体颜色
var textColor = '#999999';
//表格边框颜色
var tableLine = '#d3d3d3';

//添加柱状图
function bar(data,settingData,name,num,dimension,stateTime,endTime){
	data = setData(data,dimension,settingData.time_dim,stateTime,endTime);
	//添加数据
	var datas = [];
	for (var i = 0; i < data.length; i++) {
		datas.push(data[i].value);
	}
	var bars = {
        name:name,
          type: 'bar',
          barGap :'0',
          z:2,
          barWidth:'50%',
          itemStyle: {
				normal: {
				color: '',	
				},
				emphasis: {
					color: '#FFFF33'
				}
			},
           data: datas
       };
	return bars;
}

//给rgb颜色添加透明度
function changeColor(color,num){
color = color.substr(0,3)+'a'+color.substr(3);
color = color.substr(0,color.length-1)+','+num+color.substr(color.length-1);
return color;
}

//添加折线图
function line(data,settingData,typeData,name,num,dimension,stateTime,endTime){
	data = setData(data,dimension,settingData.time_dim,stateTime,endTime);
	var datas = [];
	for (var i = 0; i < data.length; i++) {
		datas.push(data[i].value);
	}
		var lines ={
		         name:name,
		           type: 'line',
		           smooth: true,//值为true折线平滑    值为false折线曲折
		           symbol:'circle',//circle--拐点实心圆形 rect--实心方形   symbol没有为默认空心圆形
		           showSymbol: true,//true 为拐点处有点  false 为没有
		           symbolSize:7,//拐点  点的大小  
		           z:3,//折线图的层级
		           yAxisIndex: 0,
		          areaStyle:{
		            },
		            itemStyle: {
			        },
		            data: datas
		        };
	return lines;
}

//添加饼图
function pie(data,settingData,name,dimension,stateTime,endTime){
	data = setData(data,dimension,settingData.time_dim,stateTime,endTime);
	var type = settingData.charts;
	var datas = [];
	var tmp = {};
	var tmp1 = {};
	var sum = 0;
	//添加数据
	for (var i = 0; i < data.length; i++) {
		tmp = {};
		tmp.value=data[i].value;
		tmp.name=data[i].object_name;
		datas.push(tmp);
	}
//	对数据进行排序
	datas = JsonDown(datas,'value');
	//控制生成饼图还是环图
	var radius1 = '55%';
	if (type=="ringPie"||type=="ringRose") {
		radius1 = ['30%', '55%'];
	}
	var pies =  [{
			name:name,
            type:'pie',
            radius : radius1,
            center : ['50%', '60%'],
            //roseType : 'area',
            data:datas,
            itemStyle: {
         	   emphasis: {
                  color: '#FFFF33'
              }        
            },	
            label: {
                normal: {
                	/*formatter:function(v) {
                		var name=wrap(v.name,8);
                		return name;
                	},*/
                	 textStyle: {			   
	  		  		      color: textColor,
	  		  		      fontSize:8
	  		                }
                },
                emphasis: {
                    show: true
                }
            },
            labelLine: {
                normal: {
                    show: true,
                    length:2,
                    length2:6
                }
            }
        }];
	if (type=="rose"||type=="ringRose") {
		pies =  [{
				name:name,
	            type:'pie',
	            radius : radius1,
	            center : ['50%', '60%'],
	            roseType : 'area',
	            data:datas,
	            itemStyle: {
	         	   emphasis: {
	                  color: '#FFFF33'
	              }        
	            },	
	            label: {
	                normal: {
	                	/*formatter:function(v) {
	                		var name=wrap(v.name,8);
	                		return name;
	                	},*/
	                	 textStyle: {			   
		  		  		      color: textColor,
		  		  		      fontSize:16
		  		                }
	                },
	                emphasis: {
	                    show: true
	                }
	            },
	            labelLine: {
	                normal: {
	                    show: true,
	                    length:2,
	                    length2:6
	                }
	            }
	        }];
	}
	return pies;
}

//获取图形数据
function getTime(data,timeType,stateTime,endTime){
	if (stateTime.length>4&&endTime.length>4) {
		if (timeType=='year') {
			stateTime = stateTime.substring(0,4);
			endTime = endTime.substring(0,4);
		}
	}

	var t = [];
	var time = [];
	for (var i = 0; i < data.length; i++) {
		t.push(data[i].month_id);
	}
	if (Math.min.apply(null, t)<stateTime) {
		time.push(stateTime);
	}else {
		time.push(Math.min.apply(null, t));
	}
	if (Math.max.apply(null, t)>=endTime) {
		time.push(endTime);
	}else{
		time.push(Math.max.apply(null, t));
	}
	return time;
}

//获取图形数据
function dataType(data,typeData,stateTime,endTime){
	var series = [];
	var series1 = [];
	var series2 = [];
	var series3 = '';
	var xAxis = [];
	var tmp = {};
	
	var tt = [];
	var tmm = {};
	for (var i = 0; i < data.length; i++) {
		for (var j = 0; j < typeData.length; j++) {
			if (data[i].measure_id==typeData[j].metric_id) {
				if (typeData[j].charts=="bar"||typeData[j].charts=="line") {
					tmm = {};
					tmm.value = getxAxis(data[i].measures,typeData[j].dimension,typeData[j].time_dim,stateTime,endTime);
					tmm.data = chartType(data[i].measures,typeData[j],typeData,data[i].measure_name,i,typeData[j].dimension,stateTime,endTime);
					tmm.category_id = typeData[j].category_id;
					tmm.dimension = typeData[j].dimension;
					tmm.unit = typeData[j].unit;
					tmm.time = getTime(data[i].measures,typeData[j].time_dim,stateTime,endTime);
					tmm.type = typeData[j].charts;
					tt.push(tmm);
				} else if (typeData[j].charts!="line"&&typeData[j].charts!="bar") {
					if (typeData[j].charts=="table") {
						tmp = {};
						tmp.value = data[i].measures;
							//add_table(data[i].measures,data[i].measure_name,typeData[j].dimension,stateTime,endTime);
						tmp.name = data[i].measure_name;
						tmp.type = 'table';
						tmp.dimension = typeData[j].dimension;
						tmp.time = getTime(data[i].measures,typeData[j].time_dim,stateTime,endTime);
						tmp.unit = typeData[j].unit;
						series2.push(tmp);
					}else if (typeData[j].charts=="gauge") {
						tmp = {};
						tmp.value = data[i].measures
						tmp.name = data[i].measure_name;
						tmp.type = 'gauge';
						tmp.dimension = typeData[j].dimension;
						tmp.time_dim = typeData[j].time_dim;
						tmp.time = getTime(data[i].measures,typeData[j].time_dim,stateTime,endTime);
						tmp.unit = typeData[j].unit;
						series2.push(tmp);
					}else {
						tmp = {};
						tmp.value = chartType(data[i].measures,typeData[j],typeData,data[i].measure_name,i,typeData[j].dimension,stateTime,endTime);
						tmp.name = data[i].measure_name;
						tmp.type = 'pie';
						tmp.dimension = typeData[j].dimension;
						tmp.time = getTime(data[i].measures,typeData[j].time_dim,stateTime,endTime);
						tmp.unit = typeData[j].unit;
						series2.push(tmp);
					}
				}
			}
		}
	}
	var cat = [];
	var dim = [];
	var un = [];
	for (var a = 0; a < tt.length; a++) {
		cat.push(tt[a].category_id);
		dim.push(tt[a].dimension);
		un.push(tt[a].unit);
	}
	cat = onlyData(cat);
	dim = onlyData(dim);
	un = onlyData(un);
	var all = [];
	var tmpp = {};
	//同纬度同类别指标的归类
		for (var b = 0; b < cat.length; b++) {
			for (var c = 0; c < dim.length; c++) {
				for (var e = 0; e < un.length; e++) {
					series1 = [];
					tmpp = {};
					var uni = [];
					var time = [];
					for (var d = 0; d < tt.length; d++) {
						if (tt[d].category_id ==cat[b]&&tt[d].dimension==dim[c]&&tt[d].unit == un[e]) {
							var tk = {};
							xAxis = tt[d].value;
							series1.push(tt[d].data);
							time = tt[d].time;
							tk.type = tt[d].type;
							tk.unit = tt[d].unit;
							uni.push(tk);
						}
					}
				
					if (series1.length>0) {
						tmpp.xAxis = xAxis;
						tmpp.yAxis = getyAxis(series1);
						tmpp.series = series1;
						tmpp.dimension = dim[c];
						tmpp.units =uni;
						tmpp.time =time;
						all.push(tmpp);
					}
				}
			}
		}
	//将整个栏目的图表数据分类
	var em1 = {};
	if (all.length>0) {
		em1.value =all;
		em1.name = "bar";
		series.push(em1);
	}
	var em2 = {};
	if (series2.length>0) {
		em2.value =series2;
		em2.name = "pie";
		series.push(em2);
	}
	return series;
}


//判断图形类型
function chartType(data,settingData,typeData,name,i,dimension,stateTime,endTime){
	var type = settingData.charts;
	var str = {};
		if (type=="bar") {
			str =bar(data,settingData,name,i,dimension,stateTime,endTime);
		}else if (type=="line") {
			str =line(data,settingData,typeData,name,i,dimension,stateTime,endTime);
		}else{
			str =pie(data,settingData,name,dimension,stateTime,endTime);
		}
		return str;
}

//获取x轴数据
function getXData(data){
	var xData = [];
	for (var i = 0; i < data.length; i++) {
		xData.push(data[i].object_name);
	}
	return xData;
}

//获取图例颜色
function getColor(data){
	var color = [];
	for (var i = 0; i < data.length; i++) {
		color.push(data[i].color);
	}
	return color;
}

//获取图例值
function getLegend(data){
	var legendData = [];
	for (var i = 0; i < data.length; i++) {
		legendData.push(data[i].measure_name);
	}
	return legendData;
}


//获取生成x轴
function getxAxis(data,dimension,timeType,stateTime,endTime){
	//将数据按维度提取
	data = setData(data,dimension,timeType,stateTime,endTime);
//	获取x轴数据
	var xData = getXData(data);
	//生成x轴
	var xAxis = [ {
		type: 'category',
		margin: 12,
		data: xData,
		axisLine:{
			lineStyle:{
				color:'#ebebeb',
				width:2//这里是为了突出显示加上的，可以去掉
			}
		},
		axisLabel : {
			nterval: 0,//标签设置为全部显示
			margin: 12,
			interval:0 ,
			formatter:function(value){
          	 return value;
				},
			textStyle: {
				color: textColor,
				fontSize:10 // 让字体变大
			}
		}
	}];
	return xAxis;
}

//获取生成y轴
function getyAxis(data){
	var typs = [];
//	判断图标中柱状图和折线图的个数
	for (var j = 0; j < data.length; j++) {
		typs.push(data[j].type);
	}
	typs = onlyData(typs);
	//只有一种生成一个y轴
	var yAxis = [{
		name:'',
		nameLocation: 'end',
	    nameGap: 8,
		nameTextStyle: {
             color: textColor,
             fontSize: 10
         },
    	splitLine:{
    		show: true,
    		lineStyle:{
                color:'rgba(160,160,160,0.3)',
            }},//去除网格线
        type: 'value',
         boundaryGap: [0, 0.01],
         splitLine:{
     		show: true,
     		lineStyle:{
                 color:'rgba(160,160,160,0.3)',
             }},
	      axisLine:{
              lineStyle:{
                  color:'#ebebeb',
                  width:2//这里是为了突出显示加上的，可以去掉
              }
          },
	      axisLabel: {
              show: true,
              textStyle: {
                  color: textColor,
                  fontSize:8
              },
              formatter:'{value}'
          }
    }];
	//两种都有生成两个y轴
	if (typs.length>1) {
		yAxis = [{
			name:'',
			nameLocation: 'end',
		    nameGap: 8,
			nameTextStyle: {
	             color: textColor,
	             fontSize: 10
	         },
	    	splitLine:{show: false},//去除网格线
	        type: 'value',
	         boundaryGap: [0, 0.01],
	         splitLine:{
	     		show: false,
	     		lineStyle:{
	                 color:'rgba(160,160,160,0.3)',
	             }},
		      axisLine:{
	              lineStyle:{
	                  color:'#ebebeb',
	                  width:2,//这里是为了突出显示加上的，可以去掉
	              }
	          },
		      axisLabel: {
	              show: true,
	              textStyle: {
	                  color: textColor,
	                  fontSize:8
	              },
	              formatter:'{value}'
	          }
	    },{
	    	name:'',
			nameLocation: 'end',
		    nameGap: 8,
			nameTextStyle: {
	             color: textColor,
	             fontSize: 10
	         },
	    	splitLine:{show: false},//去除网格线
	        type: 'value',
	         boundaryGap: [0, 0.01],
	         splitLine:{
	     		show: false,
	     		lineStyle:{
	                 color:'rgba(160,160,160,0.3)',
	             }},
		      axisLine:{
	              lineStyle:{
	                  color:'#ebebeb',
	                  width:2,//这里是为了突出显示加上的，可以去掉
	              }
	          },
		      axisLabel: {
	              show: true,
	              textStyle: {
	                  color: textColor,
	                  fontSize:8
	              },
	              formatter:'{value}'
	          }
	    }];
	}
	return yAxis;
}


//生成仪表盘
function gauge(data,name,id,dimension,timeType,time,unit){
	//将数据按维度提取
	data = setData(data,dimension,timeType,time[0],time[1]);
	var divwid= getPX(id,'width'); //宽度
	var divhei= getPX(id,'height'); //高度
	//根据容器的高度调整仪表盘中文字的大小
	var txtSize = Math.floor((divhei-80)/20+6);
	if (txtSize>22) {
		txtSize = 22;
	}
	if (txtSize<6) {
		txtSize = 6;
	}
	//调整标题的文字大小
	var titleSize =txtSize*1+2;
	//调整标题的上下位置
	var top = '-5%';
	var radius = '80%';
	var show = true;
	if (divhei<228) {
		radius = '70%'
			if (divhei<120) {
				radius = '65%'	
			}
		 top =-5+Math.floor((divhei-220)/5)+'%';
	}else if (divhei>400) {
		top =Math.floor((divhei-420)/9)+'%';
		if (Math.floor((divhei-420)/9)>5) {
			top = '4%';
		}
	}
	if (divhei<90) {
		show = false;
	}
	var ifNumber = 0;
	//判断对象为年份，按年份降序排序
	var reg = /^[0-9]+.?[0-9]*$/;
	//判断是否位数字
	for (var m = 0; m < data.length; m++) {
		if (reg.test(data[m].object_name)) {
			ifNumber = ifNumber +1;
		}
	}
	if (ifNumber == data.length) {
		data = JsonDown(data,'object_name');
	}
	//将数据按图表要求生成
	var dx = [];
	var xAxisData = [];
	for (var i = 0; i < data.length; i++) {
		//时间轴数据
		xAxisData.push(data[i].object_name);
		//仪表盘值数据
		dx.push(data[i].value);
	}
//	获取值数据的最大值调整仪表盘的最大值
	var max = Math.ceil(Math.max.apply(null, dx)/10)*10;
	if (unit == '1') {
		unit = '';
	}else if (unit != '%') {
		unit = '('+unit+')';
	}
	var formatter = '{value}'+unit;
	if (Math.max.apply(null, dx)<100) {
		max = 100;
		//formatter = '{value}%';
	}
	var myChart = echarts.init(document.getElementById(id));
	//仪表盘数据的动态加载
	var os = [];
	for (var i = 0; i < dx.length; i++) {
    var series = [];
    series.push({
        data: [{
            value: dx[i],
            name: xAxisData[i]
        }]
    });
    
    os.push({
        series: series
    });
}

var option = {
    baseOption: {
        timeline: {
        	show:false,
            axisType: 'category',
            autoPlay: true,
            bottom: 10,
            left:3,
            right:3,
            data: xAxisData
        },
        //color: ['#019aba'],
        title: {
        	show:show,
            subtext: name+'\n'+time[0]+'-'+time[1],
            top:top,
	        left:'center',
            subtextStyle: {
                fontSize: titleSize,
               color:titleColor
            }
        },
        tooltip : {
        	z:20,
	        trigger: 'item',
	        formatter:name+ "<br/>{b}: {c} "+unit
	    },
        series: [{
            type: 'gauge',
            center: ['50%', '60%'],
            radius :radius,
            max:max,
            label: {
                normal: {
                    position: 'center'
                }
            },
            axisLine: {            // 坐标轴线
                show: true,        // 默认显示，属性show控制显示与否
                lineStyle: {       // 属性lineStyle控制线条样式
                    color: [[0.5, '#94e9f5'],[1, '#5f9ff2']], 
                    width: 10
                }
            },
            axisLabel: {
                show: true,
                textStyle: {
                    fontSize: txtSize-4
                }
            },
            axisTick: {            // 坐标轴小标记
                show: true,        // 属性show控制显示与否，默认不显示
                splitNumber: 10,    // 每份split细分多少段
                length:3,         // 属性length控制线长
                lineStyle: {       // 属性lineStyle控制线条样式
                    color: textColor,
                    width: 1,
                    type: 'solid'
                }
            },
            splitLine: {           // 分隔线
                show: true,        // 默认显示，属性show控制显示与否
                length:15,         // 属性length控制线长
                lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                    color: textColor,
                    width: 1,
                    type: 'solid'
                }
            },
            pointer: {
                width:5
            },
            title : {
            	offsetCenter: [0, '80%'],
                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                    fontSize:txtSize,
                    color: textColor,
                    shadowColor : textColor, //默认透明
                    shadowBlur: 10
                }
            },
            detail: {
                formatter: formatter,
                textStyle: {
                    fontSize: txtSize,
                    fontWeight: "",
                    color: textColor, 
                }
            },
           
        }]
    	},
    	options: os
	};
	 myChart.setOption (option);
}


//生成饼图方法
function pie_echart(series,name,id,time,unit){
	//label.normal.textStyle.fontSize/labelLine.normal.show/label.normal.show
	var wid= getPX(id,'width'); //宽度
	var hei= getPX(id,'height'); //高度
	var top = '-5%';
	//根据容器宽高控制饼图提示的现实和隐藏当容器过于小时 提示信息隐藏
	if ((hei<wid&&hei<100)||(wid<hei)&&wid<110) {
		series[0].labelLine.normal.show = false;
		series[0].label.normal.show = false;
	}
	var wh = wid;
	if (wid>hei) {
		wh = hei;
	}
	var show = true;
	//标题位置调整
	if (hei<200&&hei>120) {
		top = '-14%';
	}else if (hei<100) {
		top = '-35%';
	}else if (hei>300) {
		top = '0%';	
	}
	if (hei<80) {
		show = false;
	}
	if (unit == '1') {
		unit = '';
	}else if (unit != '%') {
		unit = '('+unit+')';
	}
	//修改饼图提示信息的字体大小
	var size = Math.floor((wh-180)/26+10);
	 series[0].label.normal.textStyle.fontSize = size;
	var myChart = echarts.init(document.getElementById(id));	
	option = {
			title: [{
				show:show,
		        subtext:name+'\n'+time[0]+'-'+time[1],
		        top:top,
		        left:'center',
		        subtextStyle: {			   
		  		      color: titleColor,
		  		      fontSize:size+2
		        }
		  	 }],
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a}<br/>{b}: {c} "+unit+" <br/>占比：{d}%"
		    },
		    color:colo,
		    calculable : true,
		    series : series
		};
	 myChart.setOption (option);
}

//修改y轴的元素
function set_yAxis(yAxis,data,txtSize,divhei){
	//修改y轴字体大小
	for (var y = 0; y < yAxis.length; y++) {
		yAxis[y].axisLabel.textStyle.fontSize = txtSize;
	}
	if (divhei<120) {
		return yAxis;
	}
	for (var yy = 0; yy < data.units.length; yy++) {
		if (yAxis.length>1) {
			if (data.units[yy].type == 'bar') {
				if (data.units[yy].unit == '%') {
					yAxis[0].axisLabel.formatter = '{value} %';
				}else if (data.units[yy].unit != '1') {
					yAxis[0].name =data.units[yy].unit;
					yAxis[0].nameTextStyle.fontSize =txtSize;
				}
			}else{
				if (data.units[yy].unit == '%') {
					yAxis[0].axisLabel.formatter = '{value} %';
				}else if (data.units[yy].unit != '1') {
					yAxis[1].name =data.units[yy].unit;
					yAxis[1].nameTextStyle.fontSize =txtSize;
				}
			}
		}else{
			if (data.units[yy].unit == '%') {
				yAxis[0].axisLabel.formatter = '{value} %';
			}else if (data.units[yy].unit != '1') {
				yAxis[0].name =data.units[yy].unit;
				yAxis[0].nameTextStyle.fontSize =txtSize;
			}
		}
	}
	return yAxis;
}

//修改y轴的元素
function set_series(series){
	for (var i = 0; i < series.length; i++) {
		if (series[i].type == 'bar') {
			series[i].itemStyle.normal.color = colo[i];
		}else if (series[i].type == 'line'){
			series[i].areaStyle = {
                normal:{
                	color: {
                        type: 'linear',
                        x: 0,
                        y: 0,
                        x2: 0,
                        y2: 1,
                        colorStops: [{
                            offset: 0, color: changeColor(colo[i],0.7) // 0% 处的颜色
                        },  {
                            offset: 1, color: changeColor(colo[i],0.1) // 100% 处的颜色
                        }],
                        globalCoord: false // 缺省为 false
                    }
                }
            };
			series[i].itemStyle =	{ //折线拐点标志的样式
	            normal: {
	                color: colo[i],
	                //borderColor:color, //changeColor(color,0.3), //图形的描边颜色。支持的格式同 color
	               borderWidth: 17//描边线宽。为 0 时无描边。[ default: 0 ] 

	            }
			};
		}
	}
	return series;
}

//生成柱状图和折线图方法
function bar_echart(data,name,id){
	//alert(JSON.stringify(data));
//	每次生成图标后将颜色初始化
	ba = 0;
	var wid= getPX(id,'width'); //宽度
	var divhei= getPX(id,'height'); //高度
	//根据容器高度自动调整x轴和y轴字体大小
	var txtSize = Math.floor(divhei*0.25/4);
	if (txtSize>15) {
		txtSize = 15;
	}
	if (txtSize<9) {
		txtSize = 9;
	}
	//根据容器大小自动调整标注的位置和显示隐藏
	var show = true;
	var sh = true;
	var top = '20%';
	//根据容器高度调整时间标题的位置
	var subtop = '0%';
	if (divhei<410&&divhei>200) {
		subtop = -3+3/100*(divhei-300)+'%';
	}else if (divhei<200&&divhei>150) {
		subtop = -3-3/100*(divhei-100)+'%';
	}else if (divhei<150) {
		sh = false;
	}
	if (wid<100) {
		top = '15%';
		show = false;
	}
	
	if (divhei<100) {
		subtop = '-38%';
		top = '15%';
		show = false;
		sh = false;
	}
	var legendData = [];
	var series = set_series(data.series);
	var xAxis = data.xAxis;
	//修改x轴字体大小
	xAxis[0].axisLabel.textStyle.fontSize = txtSize;
	//获取调整y轴
	var yAxis = set_yAxis(data.yAxis,data,txtSize,divhei);
	var colList = ['#FFFF00','#00FFFF','#00FF00','#8A2BE2','#FF0000'];
	var list = [];
	var lt = [];
	var tnp = [];
	//将数据转变为可排序的数据格式
	for (var j = 0; j < (xAxis[0].data).length; j++) {
		tnp = [];
		tnp.push((xAxis[0].data)[j]);
		for (var l = 0; l < series.length; l++) {
			tnp.push((series[l].data)[j]);	
		}
		list.push(tnp);
	}
	var ih = 1;
	if (data.dimension == 'time') {
		ih = 0;
		sh = false;
	}
	//对数据进行不同维度排序
	var newData = changeData(list,ih);
	for (var c = 0; c < (series.length)+1; c++) {
		var sd = [];
		for (var d = 0; d < newData.length; d++) {
			sd.push(newData[d][c]);
		}
		if (c<1) {
			xAxis[0].data = sd;
		}else{
			series[c-1].data = sd;
		}
	}
	var k = 0;
	//控制柱状图各自对应的y轴
	for (var i = 0; i < series.length; i++) {
		legendData.push(series[i].name);
		if (series[i].type=="bar") {
			series[i].itemStyle.emphasis.color=colList[k];
			k++;
		}
	}
	if (yAxis.length>1) {
		for (var j = 0; j < series.length; j++) {
			if (series[j].type == "line") {
				series[j].yAxisIndex = 1;
			}
		}
	}
	//控制柱状图的主体的宽度
	if (series.length>1) {
		var w = parseInt(70/series.length)+'%';
		for (var s = 0; s < series.length; s++) {
			series[s].barWidth = w;
		}
	}
	var bottom = '4%';
	var dataZoom = [];
	var end = 0;
	//控制是否显示公洞条  以及初始显示的对象个数
	if (wid/series[0].data.length<18) {
		end =  parseInt((wid/30)/series[0].data.length*90);
		bottom = '10%';
		dataZoom = [{
	    	show:'true',
	        type:'slider',
	        backgroundColor:'#383838',//背景色
	        borderColor:'#87CEFF',//边框色
	        fillerColor:'#00cc66',//选中区颜色
	        dataBackground:{
	        	lineStyle:{
	        		color:'#383838'
	        	}
	        },
	        xAxisIndex: 0,
	        width:'90%',
	        height:'5%',
	        bottom:'3%',
	        start: 0,
	        end: end,
	        handleSize: '50%',
	        handleStyle:{
	            color:'#87CEFF',
	            borderWidth:15
	            
	        },
	           textStyle:{
	            color:textColor
	            },
	           borderColor:"#eee"
	    },
	    {
	        type: 'inside',
	        start: 94,
	        end: 100
	    }
	    ];
	}
	//根据x轴的文字长度以及容器宽度判断x轴文字是横向显示还是纵向缩略显示
	var le = 0;
	//获取x轴位子的最大长度
	for (var z = 0; z < xAxis[0].data.length; z++) {
		if (xAxis[0].data[z].length>le) {
			le = xAxis[0].data[z].length;
		}
	}
	//当x轴字段组的个数小于可显示最大个数end是end等于x轴字段个数
	if (end == 0) {
		end = xAxis[0].data.length;
	}
	//当x轴的分割宽度大于字段的占用宽度时 地段横向全部展示
	//if (wid/(end+3)<txtSize*1*le) {
		var lz = Math.floor(divhei*0.2/txtSize);

			if (txtSize*1*le<wid/(end+3)) {
				xAxis[0].axisLabel.formatter = function(value){
					return value;	
				}
			}else {
				xAxis[0].axisLabel.formatter = function(value){
					if (lz == 0) {
						return '';
					}
					if (value.length<=lz+1) {
						return value.split("").join("\n");	
					}else{
						 return (value.substring(0,lz)).split("").join("\n")+'\n'+'…';
					}
				}
			}
	//}
	var myChart = echarts.init(document.getElementById(id));
	option = {
		    title: [{
		  	   show:sh,
			   subtext:'时间：'+data.time[0]+'-'+data.time[1],
			   top:subtop,
			   right:'6%',
			   subtextStyle: {			   
			  		color: titleColor,
			  		fontSize:txtSize
			        }
			  }],
		  	tooltip: {
		  		 trigger: 'axis',
		         axisPointer: {
		             type: 'shadow'
		         },
		         formatter: function (params) {
		        	 var str = params[0].name+'<br/>';
		        	 for (var i = 0; i < series.length; i++) {
		        		 var ui = data.units[i].unit;
		        		 if (ui == '1') {
							ui = '';
						}else if (ui != '%') {
							ui = '('+ui+')';
						}
		        		 str =str+series[i].name+':'+params[i].value+' '+ui+'<br/>';
					}
						return str;
					}
		    },
		    legend: {
		    	  show:show,
		    	  data:legendData,
		    	  right:'3%',
		    	  textStyle: {			   
		  		      color: titleColor,
		  		      fontSize:txtSize
		        }
		   },
		   grid: {
		        left:'2%',
		        right:'2%',
		        top:top,
		        bottom: bottom,
		        containLabel: true
		    },
		    xAxis: xAxis,
		    yAxis: yAxis ,
		    dataZoom:dataZoom,
		    series: series
		};
	 myChart.setOption (option);
}

function getT(data){
	var times = [];
	for (var i = 0; i < data.length; i++) {
		times.push(data[i].month_id);
	}
	times = onlyData(times);
	var compare = function (x, y) {//降序排序
		 if (x < y) {
		        return 1;
		    } else if (x > y) {
		        return -1;
		    } else {
		        return 0;
		    }
	}
	times.sort(compare);
	return times;
}

//生成表格方法
function add_table(data,name,dimension,id){
	var op = document.getElementById(id);
	var wid= op.offsetWidth; //宽度
	var times = getT(data);
	var names = [];
	for (var i = 0; i < data.length; i++) {
		if (data[0].month_id == data[i].month_id) {
			names.push(data[i].object_name);	
		}
	}
	var leng = 2;
	for (var l = 0; l < times.length; l++) {
		leng = leng+times[l].length;
	}
	var siz = parseInt(wid/times.length/17);
	var width = 90/(times.length+1);
	var str = '<table id = "f_table" style="border:1px solid '+tableLine+';margin-left:5%;width:92%;margin-top:0px;margin-bottom:7%;">';
	str =  str + '</tr>';
	//吧数据解析成  需要的格式
	var tableData = [];
	for (var a = 0; a < times.length; a++) {
		var ta = [];
		for (var b = 0; b < data.length; b++) {
			if (times[a]==data[b].month_id) {
				ta.push(data[b].value);
			}
		}
		tableData.push(ta);
	}
	//吧数据  解析成排序需要的格式
	var ss = [];
	for (var s = 0; s < names.length; s++) {
		var mm = [];
		mm.push(names[s]);
		for (var y = 0; y < tableData.length; y++) {
			mm.push(tableData[y][s]);
		}
		ss.push(mm);
	}
	//数据根据第一列数据降序排序
	var tData = changeData(ss,1);
	for (var j = 0; j < tData.length; j++) {
		str = str + '<tr style = "height:25px">'+
		'<td style="width:'+width+'%;border:1px solid '+tableLine+';font-size:12px;text-align:center;color:'+titleColor+'">'+tData[j][0]+'</td>';
		for (var k = 0; k < tableData.length; k++) {
			var tValue = tData[j][k+1];
			if (leng*17>wid) {
				str = str + '<td style="width:'+width+'%;border:1px solid '+tableLine+';text-align:center;font-size:12px;color:'+titleColor+'">'+wrap(tValue,siz)+'</td>';
			}else{
				str = str + '<td style="width:'+width+'%;border:1px solid '+tableLine+';text-align:center;font-size:12px;color:'+titleColor+'">'+tValue+'</td>';
			}
		}
		str =  str + '</tr>';
	}
	str =  str +'</table>';
	return str;
}

function nName(value){
	var name = value+"";
	var str= '';
	if (name.length>2) {
		for (var i = 0; i < name.length; i++) {
			str +=name[i]+'\n';
		}
	}else {
		return name;
	}
	return str;
}


//向页面添加图表方法  data--指标数据   name--栏目名称       typeData--栏目图表信息   id--栏目的div的id
function add(data,name,typeData,sectionData,stateTime,endTime,ids){
	var id = "";
	if (ids == '') {
		for (var i = 0; i < sectionData.length; i++) {
			var dat = [];
			for (var j = 0; j < typeData.length; j++) {
				if (sectionData[i].no == typeData[j].section_id) {
					id = sectionData[i].sid;
					dat.push(typeData[j]);
				}
			}
			if (dat.length>0) {
				addEchart(data,name,dat,id,stateTime,endTime);	
			}
		}
	}else {
		var dats = [];
		for (var k = 0; k < typeData.length; k++) {
			if (ids == 's'+typeData[k].section_id) {
				dats.push(typeData[k]);
			}
		}
		if (dats.length>0) {
			addEchart(data,name,dats,ids,stateTime,endTime);	
		}
	}
	
}


function addEchart(data,name,typeData,id,stateTime,endTime){
	var typ = [];
	for (var i = 0; i < typeData.length; i++) {
		typ.push(typeData[i].charts);
	}
	//将图表类型唯一化
	var typs = onlyData(typ);
	var a = false;
	//var b = false;
	var c = false;
	//判断书否有柱状图和折线图
	for (var j = 0; j < typs.length; j++) {
		if (typs[j]=="bar"||typs[j]=="line") {
			a = true;
		}else{
			c = true;
		}
	}
	var  getData = dataType(data,typeData,stateTime,endTime);
	var pieData = [];
	var barData = [];
	for (var t = 0; t < getData.length; t++) {
		if (getData[t].name == "pie") {
			pieData = getData[t].value;
		}else if (getData[t].name == "bar") {
			barData = getData[t].value;
		}
	}
	//生成图表展示的容器
	var tbody=window.document.getElementById(id);
	var str = "";
	//既有柱状图和折线图  又有其他类型图形  生成两个容器
	var ma = 0;
	var height = 99;
	if (pieData.length>0) {
		ma =  Math.ceil(pieData.length/2);
		height = 99/ma;
	}
	if ((a==true&&c==true)) {
		var div1 = id+'chart1';
		var div2 = id+'chart2';
		var divo = document.getElementById(id);
		var divwid= divo.offsetWidth; //宽度
		var divhei= divo.offsetHeight; //高度
		//添加柱状和折线图
		if (barData.length>1) {
			if (divwid>divhei) {
				str='<div id = "'+div1+'" style="width: 49.5%;height:99.5%;float: left;">'+
				'</div>'+
				'<div id = "'+div2+'div" style="width: 50%;height:99.5%;float: left;">'+
				'</div>';
			}else{
				str='<div id = "'+div1+'" style="width: 99.5%;height:49.5%;float: top;">'+
				'</div>'+
				'<div id = "'+div2+'div" style="width: 99.5%;height:50%;float: top;">'+
				'</div>';
			}
			
			tbody.innerHTML = str;
			var o = document.getElementById(div1);
			var wid= o.offsetWidth; //宽度
			var hei= o.offsetHeight; //高度
			var barDiv=window.document.getElementById(div1);
			var bar1 = '<table id = "'+div1+'bar"  style="width: 99.5%;height:99.5%;">';
			var heights = 99/Math.ceil(barData.length/2);
			if ((wid<1.25*hei&&barData.length<=Math.ceil(hei/0.5*wid))||(hei<0.75*wid&&barData.length<=Math.ceil(hei/0.5*wid))) {
				if (wid<0.75*hei) {
					for (var v1 = 0; v1 < barData.length; v1++) {
						var bId1 = id+'bar'+v1;
						bar1 = bar1 + '<tr style="height:'+100/barData.length+'%;">'+
						'<td id = "'+bId1+'"  style="width:100%;"></td>'+
					'</tr>';
					}
				}else{
					bar1 = bar1 + '<tr style="height:99.5%;">';
					for (var v2 = 0; v2 < barData.length; v2++) {
						var bId2 = id+'bar'+v2;
						bar1 = bar1 + '<td id = "'+bId2+'"  style="width:'+100/barData.length+'%;"></td>';
					}
					bar1 = bar1 + '</tr>';
				}
			}else{
				for (var v = 0; v < Math.ceil(barData.length/2); v++) {
					var barId1 = id+'bar'+(2*v);
					var barId2 = id+'bar'+(2*v+1);
					if (v>Math.ceil(barData.length/2)-2) {
						if (Math.ceil(barData.length/2)*2>barData.length) {
							bar1 = bar1 + '<tr style="height:'+heights+'%;">'+
							'<td id = "'+barId1+'"  colspan="2" style="width:100%;"></td>'+
						'</tr>';
						}else{
							bar1 = bar1 + '<tr style="height:'+heights+'%;">'+
							'<td id = "'+barId1+'"  style="width:50%;"></td>'+
							'<td id = "'+barId2+'"  style="width:50%;"></td>'+
						'</tr>';
						}
					}else{
						bar1 = bar1 + '<tr style="height:'+heights+'%;">'+
						'<td id = "'+barId1+'"  style="width:50%;"></td>'+
						'<td id = "'+barId2+'"  style="width:50%;"></td>'+
					'</tr>';
					}
				}
			}
			bar1 = bar1 +'</table>';
			barDiv.innerHTML = bar1;
			for (var p = 0; p < barData.length; p++) {
				var barId3 = id+'bar'+p;
				bar_echart(barData[p],name,barId3);
			}
		}else{
			if (divwid>divhei) {
				str='<div id = "'+div1+'"  style="width: 50%;height:99.5%;float: left;">'+
				'</div>'+
				'<div id = "'+div2+'div" style="width: 50%;height:99.5%;float: left;">'+
				'</div>';
			}else{
				str='<div id = "'+div1+'"  style="width: 99%;height:49.5%;float: top;">'+
				'</div>'+
				'<div id = "'+div2+'div" style="width: 99%;height:50%;float: top;">'+
				'</div>';
			}
			
			tbody.innerHTML = str;
			bar_echart(barData[0],name,div1);
		}
		//添加其他类型图表
		var divs = div2+'div';
		if (pieData.length>1) {
			var tab1=window.document.getElementById(divs);
			var tabs1 = '<table id = "'+div2+'" style="width: 100%;height:99.5%;">'+
			'</table>';
			tab1.innerHTML = tabs1;

			var op = document.getElementById(div2);
			var wid1= op.offsetWidth; //宽度
			var hei1= op.offsetHeight; //高度
			var st = "";
			var tab2=window.document.getElementById(div2);
			if ((wid1/hei1<1&&pieData.length<=Math.ceil(hei1/wid1))||(hei1/wid1<=1&&pieData.length<=Math.ceil(hei1/wid1))) {
				if (wid1/hei1<1) {
					for (var m1 = 0; m1 < pieData.length; m1++) {
						var pId1 = id+m1;
						st = st + '<tr style="height:'+100/pieData.length+'%;">'+
						'<td id = "'+pId1+'"  style="width:100%;"></td>'+
					'</tr>';
					}
				}else{
					st = st + '<tr style="height:100%;">';
					for (var m2 = 0; m2 < pieData.length; m2++) {
						var pId2 = id+m2;
						st = st + '<td id = "'+pId2+'"  style="width:'+100/pieData.length+'%;"></td>';
					}
					st = st + '</tr>';
				}
			}else{
				for (var k = 0; k < ma; k++) {
					var id1 = id+(2*k);
					var id2 = id+(2*k+1);
					if (k>ma-2) {
						if (2*ma>pieData.length) {
							st = st + '<tr style="height:'+height+'%;">'+
							'<td id = "'+id1+'"  colspan="2" style="width:100%;"></td>'+
						'</tr>';
						}else{
							st = st + '<tr style="height:'+height+'%;">'+
							'<td id = "'+id1+'"  style="width:50%;"></td>'+
							'<td id = "'+id2+'"  style="width:50%;"></td>'+
						'</tr>';
						}
					}else{
						st = st + '<tr style="height:'+height+'%;">'+
						'<td id = "'+id1+'"  style="width:50%;"></td>'+
						'<td id = "'+id2+'"  style="width:50%;"></td>'+
					'</tr>';
					}
				}
			}
			
			tab2.innerHTML = st;
			for (var j = 0; j < pieData.length; j++) {
				var ids = id+j;
				if (pieData[j].type=='pie') {
					pie_echart(pieData[j].value,pieData[j].name,ids,pieData[j].time,pieData[j].unit);
				}else if (pieData[j].type=='table'){
					ps(ids,pieData[j].name,pieData[j].value,pieData[j].unit);
					var ida1 = 'd'+ids;
					var tables=window.document.getElementById(ida1);
					tables.innerHTML =add_table(pieData[j].value,pieData[j].name,pieData[j].dimension,ida1);
				}else if (pieData[j].type=='gauge'){
					gauge(pieData[j].value,pieData[j].name,ids,pieData[j].dimension,pieData[j].time_dim,pieData[j].time,pieData[j].unit);
				}
			}
		}else{
			if (pieData[0].type=='pie') {
				pie_echart(pieData[0].value,pieData[0].name,divs,pieData[0].time,pieData[0].unit);
			}else if (pieData[0].type=='table'){
				ps(divs,pieData[0].name,pieData[0].value,pieData[0].unit);
				var ida2 = 'd'+divs;
				var table1=window.document.getElementById(ida2);
				table1.innerHTML = add_table(pieData[0].value,pieData[0].name,pieData[0].dimension,ida2);
			}else if (pieData[0].type=='gauge'){
				gauge(pieData[0].value,pieData[0].name,divs,pieData[0].dimension,pieData[0].time_dim,pieData[0].time,pieData[0].unit);
			}
		}
	}else if ((a==true&&c==false)||(a==false&&c==true)) {
		//只有柱状图和折线图  或者没有柱状图和折线图时生成一个容器
		var div3 = id+'chart1';
		if (a==false&&c==true) {
			var strs = '';
			if (pieData.length>1) {
				strs='<div style="width: 99%;height:100%;">'+
				'<table id = "'+div3+'" style="width: 100%;height:99%;">'+
				'</table>'+
				'</div>';
				tbody.innerHTML = strs;
				
				var op1 = document.getElementById(div3);
				var wid2= op1.offsetWidth; //宽度
				var hei2= op1.offsetHeight; //高度
				var sts = "";
				var tabs=window.document.getElementById(div3);
				if ((wid2<hei2&&pieData.length<=Math.ceil(hei2/wid2))||(hei2<wid2&&pieData.length<=Math.ceil(hei2/wid2))) {
					if (wid2<hei2) {
						for (var m2 = 0; m2 < pieData.length; m2++) {
							var pId3 = id+m2;
							sts = sts + '<tr style="height:'+100/pieData.length+'%;">'+
							'<td id = "'+pId3+'"  style="width:100%;"></td>'+
						'</tr>';
						}
					}else{
						sts = sts + '<tr style="height:100%;">';
						for (var m3 = 0; m3 < pieData.length; m3++) {
							var pId4 = id+m3;
							sts = sts + '<td id = "'+pId4+'"  style="width:'+100/pieData.length+'%;"></td>';
						}
						sts = sts + '</tr>';
					}
				}else{
					for (var k = 0; k < ma; k++) {
						var id1 = id+(2*k);
						var id2 = id+(2*k+1);
						if (k>ma-2) {
							if (2*ma>pieData.length) {
								sts = sts + '<tr style="height:'+height+'%;">'+
								'<td id = "'+id1+'"  colspan="2" style="width:100%;"></td>'+
							'</tr>';
							}else{
								sts = sts + '<tr style="height:'+height+'%;">'+
								'<td id = "'+id1+'"  style="width:50%;"></td>'+
								'<td id = "'+id2+'"  style="width:50%;"></td>'+
							'</tr>';
							}
						}else{
							sts = sts + '<tr style="height:'+height+'%;">'+
							'<td id = "'+id1+'"  style="width:50%;"></td>'+
							'<td id = "'+id2+'"  style="width:50%;"></td>'+
						'</tr>';	
						}
					}
				}
				tabs.innerHTML = sts;
				for (var j = 0; j < pieData.length; j++) {
					var ids2 = id+j;
					if (pieData[j].type=='pie') {
						pie_echart(pieData[j].value,pieData[j].name,ids2,pieData[j].time,pieData[j].unit);
					}else if (pieData[j].type=='table'){
						ps(ids2,pieData[j].name,pieData[j].value,pieData[j].unit);
						var ida3 = 'd'+ids2;
						var tables=window.document.getElementById(ida3);
						tables.innerHTML = add_table(pieData[j].value,pieData[j].name,pieData[j].dimension,ida3);
					}else if (pieData[j].type=='gauge'){
						gauge(pieData[j].value,pieData[j].name,ids2,pieData[j].dimension,pieData[j].time_dim,pieData[j].time,pieData[j].unit);
					}
				}
			}else{
				strs='<div id = "'+div3+'"  style="width: 99%;height:100%;">'+
				'</div>';
				tbody.innerHTML = strs;
				if (pieData[0].type=='pie') {
					pie_echart(pieData[0].value,pieData[0].name,div3,pieData[0].time,pieData[0].unit);
				}else if (pieData[0].type=='table'){
					ps(div3,pieData[0].name,pieData[0].value,pieData[0].unit);
					var ida4 = 'd'+div3;
					var table1=window.document.getElementById(ida4);
					table1.innerHTML = add_table(pieData[0].value,pieData[0].name,pieData[0].dimension,ida4);
				}else if (pieData[0].type=='gauge'){
					gauge(pieData[0].value,pieData[0].name,div3,pieData[0].dimension,pieData[0].time_dim,pieData[0].time,pieData[0].unit);
				}
			}
		}else{
			if (barData.length>1) {
				str='<div id = "'+div3+'" style="width: 99%;height:100%;">'+
				'</div>';
				tbody.innerHTML = str;
				var barDiv2=window.document.getElementById(div3);
				var bar2 = '<table id = "'+div3+'bar" style="width: 100%;height:99%;">';
				var height2 = 99/Math.ceil(barData.length/2);
				var bo = document.getElementById(div3);
				var bwid= bo.offsetWidth; //宽度
				var bhei= bo.offsetHeight; //高度
				if ((bwid<1.5*bhei&&barData.length<=Math.ceil(bhei/0.5*bwid))||(bhei<0.5*bwid&&barData.length<=Math.ceil(bhei/0.5*bwid))) {
					if (bwid<1.5*bhei) {
						for (var vb1 = 0; vb1 < barData.length; vb1++) {
							var bIds1 = id+'bar'+vb1;
							bar2 = bar2 + '<tr style="height:'+100/barData.length+'%;">'+
							'<td id = "'+bIds1+'"  style="width:100%;"></td>'+
						'</tr>';
						}
					}else{
						bar2 = bar2 + '<tr style="height:100%;">';
						for (var vb2 = 0; vb2 < barData.length; vb2++) {
							var bIds2 = id+'bar'+vb2;
							bar2 = bar2 + '<td id = "'+bIds2+'"  style="width:'+100/barData.length+'%;"></td>';
						}
						bar2 = bar2 + '</tr>';
					}
				}else{
					for (var s = 0; s < Math.ceil(barData.length/2); s++) {
						var barId4 = id+'bar'+(2*s);
						var barId5 = id+'bar'+(2*s+1);
						if (s > Math.ceil(barData.length/2)-2) {
							if (Math.ceil(barData.length/2)*2>barData.length) {
								bar2 = bar2 + '<tr style="height:'+height2+'%;">'+
								'<td id = "'+barId4+'"  colspan="2" style="width:100%;"></td>'+
								
							'</tr>';	
							}else{
								bar2 = bar2 + '<tr style="height:'+height2+'%;">'+
								'<td id = "'+barId4+'"  style="width:50%;"></td>'+
								'<td id = "'+barId5+'"  style="width:50%;"></td>'+
							'</tr>';
							}	
						}else {
							bar2 = bar2 + '<tr style="height:'+height2+'%;">'+
							'<td id = "'+barId4+'"  style="width:50%;"></td>'+
							'<td id = "'+barId5+'"  style="width:50%;"></td>'+
						'</tr>';
						}
					}
				}
				bar2 = bar2 +'</table>';
				barDiv2.innerHTML = bar2;
				for (var h = 0; h < barData.length; h++) {
					var barId6 = id+'bar'+h;
					bar_echart(barData[h],name,barId6);
				}
			}else{
				str='<div id = "'+div3+'"  style="width: 99%;height:100%;">'+
				'</div>';
				tbody.innerHTML = str;
				bar_echart(barData[0],name,div3);
			}
		}
	}
}

function ps(id,name,data,unit) {
	var idf = '#qsection_'+id;
	$(idf).css('overflow','hidden');
	
	var op1 = document.getElementById(id);
	var op2 = document.getElementById('f_table');
	var wid1= op1.offsetWidth; //宽度
	var hei1= op1.offsetHeight; //高度
	var wid2= op1.offsetWidth; //宽度
	var str = "";
	var tables=window.document.getElementById(id);
	var idd = 'd'+id;
	var idds = 'dd'+id;
	var times = getT(data);
	var leng = 2;
	for (var l = 0; l < times.length; l++) {
		leng = leng+times[l].length;
	}
	var siz = parseInt(wid1/times.length/17);
	var width = 90/(times.length+1);
	str = '<div id = "'+idds+'" style = "width:'+wid1+'px;height:100%;margin-top:2%">'+
	'<div id = "s_dv"><p style = "text-align:center;color:#333333;font-size:16px;">'+name+'</p>'+
	'<table id = "f_table" style="height:30px;border:1px solid #00FFFF;margin-left:5%;width:92%;margin-bottom:0px;">'+
	'<tr style = "border:1px solid '+tableLine+';">'+
	'<td style="width:'+width+'%;border:1px solid '+tableLine+';text-align:center;color:'+titleColor+';font-size:16px;">对象</td>';
	if (unit == '1') {
		unit = '';
	}else {
		unit = '('+unit+')';
	}
for (var a = 0; a < times.length; a++) {
	var t_name = times[a]+unit;
	/*if (leng*17>wid1) {
		 str =  str + '<td style="width:'+width+'%;border:1px solid '+tableLine+';text-align:center;color:'+titleColor+';font-size:16px;">'+wrap(t_name,siz)+'</td>';	
	}else{*/
		 str =  str + '<td style="width:'+width+'%;border:1px solid '+tableLine+';text-align:center;color:'+titleColor+';font-size:16px;">'+t_name+'</td>';
	//}
}
//var hei = hei1*0.85;
str =  str + '</tr></table>'+
	'</div>'+
	'<div id = "'+idd+'" class = "innerbox" style = "width:'+wid1+'px;height:80%;position: absolute;">'+
	'</div></div>';
	tables.innerHTML = str;
	var op3 = document.getElementById("s_dv");
	var hei2= op3.offsetHeight; //宽度
	var hei = hei1-hei2-3+'px';
	var ids = "#"+idd;
	$(ids).css('height',hei);
	
	new PerfectScrollbar(ids);
	var idss = ids+' .ps__rail-x';
	$(idss).css('display','none');
	if (wid1<wid2) {
		$(idss).css('display','');	
	}
}

//文字换行方法
function wrap(name,size) {
	var s3= name+"";
	 var newParamsName = "";// 最终拼接成的字符串
	var paramsNameNumber = s3.length;// 实际标签的个数
	var provideNumber =size;// 每行能显示的字的个数
	var rowNumber = Math.ceil(paramsNameNumber / provideNumber);// 换行的话，需要显示几行，向上取整
	/**
	 * 判断标签的个数是否大于规定的个数， 如果大于，则进行换行处理 如果不大于，即等于或小于，就返回原标签
	 */
	// 条件等同于rowNumber>1
	 if (paramsNameNumber > provideNumber) {
		 /** 循环每一行,p表示行 */
	for (var p = 0; p < rowNumber; p++) {
	 var tempStr = "";// 表示每一次截取的字符串
	var start = p * provideNumber;// 开始截取的位置
	 var end = start + provideNumber;// 结束截取的位置
	 // 此处特殊处理最后一行的索引值
	 if (p == rowNumber - 1) {
		   // 最后一次不换行
		 tempStr = s3.substring(start, paramsNameNumber);
	 } else {
// 每一次拼接字符串并换行
		 tempStr = s3.substring(start, end) + "\n";
	 }
	newParamsName += tempStr;// 最终拼成的字符串
}

} else {
// 将旧标签的值赋给新标签
 newParamsName = s3;
}
//将最终的字符串返回
	 if (s3=='') {
		 return;
		}else{
    return newParamsName;
		}
}

//对json数据进行升序排序
function JsonUp(json,key){
    //console.log(json);
    for(var j=1,jl=json.length;j < jl;j++){
        var temp = json[j],
            val  = temp[key]*1,
            i    = j-1;
        while(i >=0 && json[i][key]*1>val){
            json[i+1] = json[i];
            i = i-1;   
        }
        json[i+1] = temp;
       
    }
    //console.log(json);
    return json;
}


//对json数据进行降序排序
function JsonDown(json,key){
    //console.log(json);
    for(var j=1,jl=json.length;j < jl;j++){
        var temp = json[j],
            val  = temp[key]*1,
            i    = j-1;
        while(i >=0 && json[i][key]*1<val){
            json[i+1] = json[i];
            i = i-1;   
        }
        json[i+1] = temp;
       
    }
    //console.log(json);
    return json;
}

//获取容器的高度和宽度
function getPX(id,type){
	var divo = document.getElementById(id);
	var divwid= divo.offsetWidth; //宽度
	var divhei= divo.offsetHeight; //高度
	if (type=='height') {
		return divhei;
	}else if(type=='width'){
		return divwid;
	}
}

//将数据按维度进行提取
function setData(data,dimension,timeType,stateTime,endTime){
	if (stateTime.length>4&&endTime.length>4) {
		if (timeType=='year') {
			stateTime = stateTime.substring(0,4);
			endTime = endTime.substring(0,4);
		}
	}
	var list = [];
	if (dimension == 'obj') {
		for (var i = 0; i < data.length; i++) {
			if (data[i].month_id*1>=stateTime*1&&data[i].month_id*1<=endTime*1) {
				list.push(data[i].object_name);
			}
		}
	}else if(dimension == 'time'){
		for (var j = 0; j < data.length; j++) {
			if (data[j].month_id*1>=stateTime*1&&data[j].month_id*1<=endTime*1) {
				list.push(data[j].month_id);
			}
		}
	}else{
		return data;
	}
	list = onlyData(list);
	var ls = [];
	var tmp = {};
	for (var k = 0; k < list.length; k++) {
		tmp = {};
		var sum = 0;
		for (var a = 0; a < data.length; a++) {
			if (dimension == 'obj') {
				if (data[a].month_id*1>=stateTime*1&&data[a].month_id*1<=endTime*1) {
					if (list[k] == data[a].object_name) {
						sum = (sum*1 + data[a].value*1).toFixed(2);
					}
				}
			}else {
				if (data[a].month_id*1>=stateTime*1&&data[a].month_id*1<=endTime*1) {
					if (list[k] == data[a].month_id) {
						sum = (sum*1 + data[a].value*1).toFixed(2);
					}
				}
			}
		}
		tmp.value = sum;
		tmp.object_name = list[k];
		ls.push(tmp);
	}
	return ls;
}

//数组去重方法
function onlyData(ary){
	for(var i=0;i<ary.length;i++){
		  for(var j=i+1;j<ary.length;j++){
		    if(ary[i]===ary[j]){
		      ary.splice(i,1);
		      i--;
		    }
		  }
		}
	return ary;
}

//柱状折线图排序方法
function changeData(data,num){
	var dd = [];
	var tmp = {};
	for (var i = 0; i < data.length; i++) {
		tmp = {};
		tmp.value = data[i][num];
		tmp.name = data[i][0];
		dd.push(tmp);
	}
	var dds = JsonDown(dd,'value');
	var ret = [];
	for (var j = 0; j < dds.length; j++) {
		for (var k = 0; k < data.length; k++) {
			if (dds[j].name == data[k][0]&&dds[j].value == data[k][num]) {
				ret.push(data[k]);
			}
		}
	}
	
	return ret;
}

//对json数据进行降序排序
function listDown(data,j){
    //console.log(json);
	for (var i = 1; i < data.length; i++) {
		var temp = data[i],
		 val  = temp[j],
		 k    = i-1;
		while(i >=0 && data[i][k]<val){
			data[k+1] = data[k];
            k = k-1;   
        }
		data[k+1] = temp;
	}
    return data;
}
