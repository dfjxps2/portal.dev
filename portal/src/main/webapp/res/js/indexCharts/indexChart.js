var colo = ['rgb(255,0,0)','rgb(0,128,0)','rgb(255,140,0)','rgb(0,0,255)','rgb(128,0,128)','rgb(255,0,255)','rgb(0,255,255)'];
var ba = 0;
//添加柱状图
function bar(data,settingData,name,num){
	var color = colo[ba];//settingData.color;
	ba++;
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
				color: color,	
				},
				emphasis: {
					color: '#FFFF33'
				}
			},
           data: datas
       };
	return bars;
}

function changeColor(color,num){
color = color.substr(0,3)+'a'+color.substr(3);
color = color.substr(0,color.length-1)+','+num+color.substr(color.length-1);
return color;
}

//添加折线图
function line(data,settingData,typeData,name,num){
	var color = colo[ba];//settingData.color;
	ba++;
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
		                normal:{
		                	color: {
		                        type: 'linear',
		                        x: 0,
		                        y: 0,
		                        x2: 0,
		                        y2: 1,
		                        colorStops: [{
		                            offset: 0, color: changeColor(color,0.7) // 0% 处的颜色
		                        },  {
		                            offset: 1, color: changeColor(color,0.1) // 100% 处的颜色
		                        }],
		                        globalCoord: false // 缺省为 false
		                    }
		                }
		            },
		            itemStyle: { //折线拐点标志的样式
			            normal: {
			                color: color,
			                //borderColor:color, //changeColor(color,0.3), //图形的描边颜色。支持的格式同 color
			               borderWidth: 17//描边线宽。为 0 时无描边。[ default: 0 ] 

			            }
			        },
		            data: datas
		        };
	return lines;
}

//添加饼图
function pie(data,settingData,name){
	var type = settingData.charts;
	var datas = [];
	var tmp = {};
	var tmp1 = {};
	var sum = 0;
	for (var i = 0; i < data.length; i++) {
		tmp = {};
		//if (i<8) {
			tmp.value=data[i].value;
			tmp.name=data[i].object_name;
			datas.push(tmp);	
		/*}else {
			sum = sum +data[i].value;
		}*/
	}
	/*tmp1.value=sum;
	tmp1.name='其它';
	if (sum>0) {
		datas.push(tmp1);
	}*/
	datas = JsonDown(datas,'value');
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
	  		  		      color: '#fff'
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
	            center : ['50%', '55%'],
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
		  		  		      color: '#fff'
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
function dataType(data,typeData){
	var series = [];
	var series1 = [];
	var series2 = [];
	var series3 = '';
	var xAxis = [];
	var tmp = {};
	for (var i = 0; i < data.length; i++) {
		for (var j = 0; j < typeData.length; j++) {
			if (data[i].measure_id==typeData[j].metric_id) {
				if (typeData[j].charts=="bar"||typeData[j].charts=="line") {
						xAxis = getxAxis(data[i].measures);
					series1.push(chartType(data[i].measures,typeData[j],typeData,data[i].measure_name,i));	
				}else if (typeData[j].charts!="line"&&typeData[j].charts!="bar") {
					if (typeData[j].charts=="table") {
						tmp = {};
						tmp.value = add_table(data[i].measures,data[i].measure_name);
						tmp.name = data[i].measure_name;
						tmp.type = 'table';
						series2.push(tmp);
					}else if (typeData[j].charts=="gauge") {
						tmp = {};
						tmp.value = data[i].measures
						tmp.name = data[i].measure_name;
						tmp.type = 'gauge';
						series2.push(tmp);
					}else {
						tmp = {};
						tmp.value = chartType(data[i].measures,typeData[j],typeData,data[i].measure_name,i);
						tmp.name = data[i].measure_name;
						tmp.type = 'pie';
						series2.push(tmp);
					}
				}
			}
		}
	}
	var em1 = {};
	if (series1.length>0) {
		var tmps = {};
		tmps.series = series1;
		tmps.xAxis = xAxis;
		tmps.yAxis = getyAxis(series1);
		em1.value =tmps;
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
function chartType(data,settingData,typeData,name,i){
	var type = settingData.charts;
	var str = {};
		if (type=="bar") {
			str =bar(data,settingData,name,i);
		}else if (type=="line") {
			str =line(data,settingData,typeData,name,i);
		}else{
			str =pie(data,settingData,name);
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
function getxAxis(data){
	var xData = getXData(data);
	var xAxis = [ {
		type: 'category',
		margin: 12,
		data: xData,
		axisLine:{
			lineStyle:{
				color:'#87CEFF'
				//width:8,//这里是为了突出显示加上的，可以去掉
			}
		},
		axisLabel : {
			nterval: 0,//标签设置为全部显示
			margin: 12,
			interval:0 ,
			formatter:function(value){
          	  if (value.length>4) {
          		  return (value.substring(0,3)).split("").join("\n")+'\n'+'…';
				}else{
					return value.split("").join("\n");	
				}
				},
			textStyle: {
				color: '#fff',
				fontSize:10 // 让字体变大
			}
		}
	}];
	return xAxis;
}

//获取生成y轴
function getyAxis(data){
	var typs = [];
	for (var j = 0; j < data.length; j++) {
		typs.push(data[j].type);
	}
	typs = onlyData(typs);
	var yAxis = [{
    	splitLine:{show: false},//去除网格线
        type: 'value',
         boundaryGap: [0, 0.01],
          splitLine :
	      {
		      show : false
	      },
	      axisLine:{
              lineStyle:{
                  color:'#87CEFF'
                  //width:8,//这里是为了突出显示加上的，可以去掉
              }
          },
	      axisLabel: {
              show: true,
              textStyle: {
                  color: '#fff',
                  fontSize:8
              }
          }
    }];
	if (typs.length>1) {
		yAxis = [{
	    	splitLine:{show: false},//去除网格线
	        type: 'value',
	         boundaryGap: [0, 0.01],
	          splitLine :
		      {
			      show : false
		      },
		      axisLine:{
	              lineStyle:{
	                  color:'#87CEFF'
	                  //width:8,//这里是为了突出显示加上的，可以去掉
	              }
	          },
		      axisLabel: {
	              show: true,
	              textStyle: {
	                  color: '#fff',
	                  fontSize:8
	              }
	          }
	    },{
	    	splitLine:{show: false},//去除网格线
	        type: 'value',
	         boundaryGap: [0, 0.01],
	          splitLine :
		      {
			      show : false
		      },
		      axisLine:{
	              lineStyle:{
	                  color:'#87CEFF'
	                  //width:8,//这里是为了突出显示加上的，可以去掉
	              }
	          },
		      axisLabel: {
	              show: true,
	              textStyle: {
	                  color: '#fff',
	                  fontSize:8
	              }
	          }
	    }];
	}
	return yAxis;
}

//生成仪表盘
function gauge(data,name,id){
	//alert(JSON.stringify(data));
	var dat = [];
	var tmp = {};
	var timeData = [];
	for (var i = 0; i < data.length; i++) {
		tmp = {};
		tmp.value = data[i].value;
		tmp.name = data[i].object_name;
		timeData.push(data[i].object_name);
		dat.push(tmp);
	}
	var myChart = echarts.init(document.getElementById(id));
	
	option = {
		    timeline: {
		        axisType: 'category',
		        show: true,
		        autoPlay: false,
		        playInterval: 1000,
		        data: timeData
		    },
		    options: [{
			    color: ["#37A2DA", "#32C5E9", "#67E0E3"],
			    series: [{
			        name: timeData[0],
			        type: 'gauge',
			        detail: {
			            formatter: '{value}%'
			        },
			        axisLine:{
		                lineStyle:{
		                  width:1  
		                }
		                },
		            splitLine:{
		                show:true,
		                length:5,
		                lineStyle:{
		                  width:1
		                }
		            },
		            axisTick:{
		                splitNumber:5,
		                length:2
		             },
		             axisLabel: {
		                 show: true,
		                 textStyle: {
		                     fontSize: 6
		                 }
		             },
		             pointer:{
		                 width:2
		             },
		             title:{
		            	 textStyle: {
		            		 color:'red',
		                     fontSize: 8
		                 }
		             },
			        axisLine: {
			            show: true,
			            lineStyle: {
			                width: 3,
			                shadowBlur: 0,
			                color: [
			                    [0.3, '#67e0e3'],
			                    [0.7, '#37a2da'],
			                    [1, '#fd666d']
			                ]
			            }
			        },
			        detail: {
			        	formatter:'{value}%',
		                textStyle: {
			                     fontSize: 8
			                 }
		            },
			        data: dat[0]
			    }]
			}]
		};
	/*for (var n = 0; n < timeData.length; n++) {
	    option.timeline.data.push(timeData[n]);
	    option.options.push({
		    color: ["#37A2DA", "#32C5E9", "#67E0E3"],
		    series: [{
		        name: timeData[n],
		        type: 'gauge',
		        detail: {
		            formatter: '{value}%'
		        },
		        axisLine:{
	                lineStyle:{
	                  width:1  
	                }
	                },
	            splitLine:{
	                show:true,
	                length:5,
	                lineStyle:{
	                  width:1
	                }
	            },
	            axisTick:{
	                splitNumber:5,
	                length:2
	             },
	             axisLabel: {
	                 show: true,
	                 textStyle: {
	                     fontSize: 6
	                 }
	             },
	             pointer:{
	                 width:2
	             },
	             title:{
	            	 textStyle: {
	            		 color:'red',
	                     fontSize: 8
	                 }
	             },
		        axisLine: {
		            show: true,
		            lineStyle: {
		                width: 3,
		                shadowBlur: 0,
		                color: [
		                    [0.3, '#67e0e3'],
		                    [0.7, '#37a2da'],
		                    [1, '#fd666d']
		                ]
		            }
		        },
		        detail: {
		        	formatter:'{value}%',
	                textStyle: {
		                     fontSize: 8
		                 }
	            },
		        data: dat[n]
		    }]
		});
	}*/

	
	 myChart.setOption (option);
}


//生成饼图方法
function pie_echart(series,name,id){
	var myChart = echarts.init(document.getElementById(id));	
	option = {
			title: [{
		        text:name,
		        top:'5%',
		        left:'center',
		        textStyle: {			   
		  		      color: '#fff',
		  		      fontSize:12
		        }
		  	 }],
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a}<br/>{b}: {c} <br/>占比：{d}%"
		    },
		    color:['#1d33b5','#4169E1','#0f9aff','#48D1CC','#3CB371','#369e3b','#95c20f','#DAA520','#fcbc31','#6A5ACD','#9370DB','#9932CC'],
		    calculable : true,
		    series : series
		};
	 myChart.setOption (option);
}

//生成柱状图和折线图方法
function bar_echart(data,name,id){
	ba = 0;
	var o = document.getElementById(id);
	var wid= o.offsetWidth; //宽度
	var legendData = [];
	var series = data.series;
	var xAxis = data.xAxis;
	var yAxis = data.yAxis;
	//alert(JSON.stringify(xAxis[0].data));
	var colList = ['#FFFF00','#00FFFF','#00FF00','#8A2BE2','#FF0000'];
	var k = 0;
	var list = [];
	var lt = [];
	var tnp = [];
	var ty = series[0].name;
	for (var j = 0; j < (xAxis[0].data).length; j++) {
		tnp = [];
		tnp.push((xAxis[0].data)[j]);
		for (var l = 0; l < series.length; l++) {
			tnp.push((series[l].data)[j]);	
		}
		list.push(tnp);
	}
	var newData = changeData(list,1);
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
	if (series.length>1) {
		var w = parseInt(70/series.length)+'%';
		for (var s = 0; s < series.length; s++) {
			series[s].barWidth = w;
		}
	}
	var bottom = '4%';
	var dataZoom = [];
	if (wid/series[0].data.length<13) {
		var end =  parseInt((wid/15)/series[0].data.length*90);
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
	            color:"#fff"},
	           borderColor:"#eee"
	    },
	    {
	        type: 'inside',
	        start: 94,
	        end: 100
	    }
	    ];
	}
	
	var myChart = echarts.init(document.getElementById(id));
	option = {
		    title: [{
		        text:name,
		        top:'1%',
		        left:'5%',
		        textStyle: {			   
		  		      color: '#fff',
		  		      fontSize:12
		        }
		  	 }],
		  	tooltip: {
		  		 trigger: 'axis',
		         axisPointer: {
		             type: 'shadow'
		         }
		    },
		    legend: {
		    	  data:legendData,
		    	  top:'0%',
		    	  right:'4%',
		    	  textStyle: {			   
		  		      color: '#fff',
		  		      fontSize:8
		        }
		   },
		   grid: {
		        left:'2%',
		        right:'4%',
		        top:'17%',
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


//向页面添加图表方法  data--指标数据   name--栏目名称       typeData--栏目图表信息   id--栏目的div的id
function add(data,name,typeData,sectionData){
	//alert(JSON.stringify(typeData));
	var id = "";
	for (var i = 0; i < sectionData.length; i++) {
		var dat = [];
		for (var j = 0; j < typeData.length; j++) {
			if (sectionData[i].no == typeData[j].section_id) {
				id = sectionData[i].sid;
				dat.push(typeData[j]);
			}
		}
		if (dat.length>0) {
			addEchart(data,name,dat,id);	
		}
	}
}

//生成表格方法
function add_table(data,name){
	var times = [];
	var names = [];
	for (var i = 0; i < data.length; i++) {
		if (data[0].month_id == data[i].month_id) {
			names.push(data[i].object_name);	
		}
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
	var width = 90/(times.length+1);
	var str = '<p style="width:100%;text-align:center;font-size:15px;color:#fff;margin-top:2%;">'+name+'</p>'+
		'<table style="border:1px solid #00FFFF;margin-left:5%;width:90%;margin-top:1%;margin-bottom:4%;">'+
		'<tr style = "height:33px;border:2px solid #00FFFF;">'+
		'<td style="width:'+width+'%;border:1px solid #00FFFF;text-align:center;color:#00FFFF;font-size:16px;">对象</td>';
	for (var a = 0; a < times.length; a++) {
		 str =  str + '<td style="width:'+width+'%;border:1px solid #00FFFF;text-align:center;color:#00FFFF;font-size:16px;">'+times[a]+'</td>';
	}
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
	str =  str + '</tr>';
	for (var j = 0; j < tData.length; j++) {
		str = str + '<tr style = "height:25px">'+
		'<td style="width:'+width+'%;border:1px solid #00FFFF;font-size:12px;text-align:center;color:#fff">'+tData[j][0]+'</td>';
		for (var k = 0; k < tableData.length; k++) {
			str = str + '<td style="width:'+width+'%;border:1px solid #00FFFF;text-align:center;font-size:12px;color:#fff">'+tData[j][k+1]+'</td>';
		}
		str =  str + '</tr>';
	}
	str =  str +'</table>';
	return str;
}

function addEchart(data,name,typeData,id){
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
	var  getData = dataType(data,typeData);
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
		str='<div id = "'+div1+'" style="width: 49%;height:100%;float: left;">'+
		'</div>'+
		'<div id = "'+div2+'div" style="width: 50%;height:100%;float: left;">'+
		'</div>';
		tbody.innerHTML = str;
		//添加柱状和折线图
		bar_echart(barData,name,div1);
		//添加其他类型图表
		var divs = div2+'div';
		if (pieData.length>1) {
			var tab1=window.document.getElementById(divs);
			var tabs1 = '<table id = "'+div2+'" style="width: 100%;height:99%;">'+
			'</table>';
			tab1.innerHTML = tabs1;
			
			var st = "";
			var tab2=window.document.getElementById(div2);
			for (var k = 0; k < ma; k++) {
				var id1 = id+(2*k);
				var id2 = id+(2*k+1);
				st = st + '<tr style="height:'+height+'%;">'+
				'<td id = "'+id1+'" style="width:50%;"></td>'+
				'<td id = "'+id2+'" style="width:50%;"></td>'+
			'</tr>';
			}
			tab2.innerHTML = st;
			for (var j = 0; j < pieData.length; j++) {
				var ids = id+j;
				if (pieData[j].type=='pie') {
					pie_echart(pieData[j].value,pieData[j].name,ids);
				}else if (pieData[j].type=='table'){
					var tables=window.document.getElementById(ids);
					tables.innerHTML = pieData[j].value;
				}else if (pieData[j].type=='gauge'){
					gauge(pieData[j].value,pieData[j].name,ids);
				}
			}
		}else{
			if (pieData[0].type=='pie') {
				pie_echart(pieData[0].value,pieData[0].name,divs);
			}else if (pieData[0].type=='table'){
				var table1=window.document.getElementById(divs);
				table1.innerHTML = pieData[0].value;
			}else if (pieData[0].type=='gauge'){
				gauge(pieData[0].value,pieData[0].name,divs);
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
				
				var sts = "";
				var tabs=window.document.getElementById(div3);
				for (var k = 0; k < ma; k++) {
					var id1 = id+(2*k);
					var id2 = id+(2*k+1);
					sts = sts + '<tr style="height:'+height+'%;">'+
					'<td id = "'+id1+'" style="width:50%;"></td>'+
					'<td id = "'+id2+'" style="width:50%;"></td>'+
				'</tr>';
				}
				tabs.innerHTML = sts;
				for (var j = 0; j < pieData.length; j++) {
					var ids2 = id+j;
					if (pieData[j].type=='pie') {
						pie_echart(pieData[j].value,pieData[j].name,ids2);
					}else if (pieData[j].type=='table'){
						var tables=window.document.getElementById(ids2);
						tables.innerHTML = pieData[j].value;
					}else if (pieData[j].type=='gauge'){
						gauge(pieData[j].value,pieData[j].name,ids2);
					}
				}
			}else{
				strs='<div id = "'+div3+'" style="width: 99%;height:100%;">'+
				'</div>';
				tbody.innerHTML = strs;
				if (pieData[0].type=='pie') {
					pie_echart(pieData[0].value,pieData[0].name,div3);
				}else if (pieData[0].type=='table'){
					var table1=window.document.getElementById(div3);
					table1.innerHTML = pieData[0].value;
				}else if (pieData[0].type=='gauge'){
					gauge(pieData[0].value,pieData[0].name,div3);
				}
			}
		}else{
			str='<div id = "'+div3+'" style="width: 99%;height:100%;">'+
			'</div>';
			tbody.innerHTML = str;
			bar_echart(barData,name,div3);
		}
	}
}

//文字换行方法
function wrap(name,size) {
	var s3= name;
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
            val  = temp[key],
            i    = j-1;
        while(i >=0 && json[i][key]>val){
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
            val  = temp[key],
            i    = j-1;
        while(i >=0 && json[i][key]<val){
            json[i+1] = json[i];
            i = i-1;   
        }
        json[i+1] = temp;
       
    }
    //console.log(json);
    return json;
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
