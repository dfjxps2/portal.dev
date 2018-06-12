var SIZE = 6;
function loadapp(id, dt){
	var x = 1, html = [];
	if(dt == null || dt.length == 0)
		return;
	for(var i = 0; i < dt.length; i++){
		if(i % SIZE == 0){
			if(x > 1)
				html.push('<div style="clear:both;"></div></div>');
			html.push('<div id="box', x++, '" class="box sortable">');
		}
		var _id = "a_"+dt[i].app_id+"_"+dt[i].app_sort;
		html.push('<div id="',_id, '" data-id="', dt[i].app_id,'" data-title="', dt[i].app_name,'" data-url="', dt[i].app_url, '" aid="',dt[i].config_id, '" class="cell ss32">', '<div class="cell-ctx">', loadctx(dt[i]), '</div>', '<i class="cell-remove">×</i>', '</div>');
	}
	html.push('<div style="clear:both;"></div></div>');
	$(id).html(html.join(''));
	pageMax = Math.ceil(dt.length / SIZE);
	if(pageMax == 0) pageMax = 1;

	loadpg(pageMax);
	play(1);

	$(".cell .cell-remove").on("click", function(){
		del(this);
	});
	$(".cell").on("dblclick", function(){
		var u = this.dataset.url, t = this.dataset.title;
		if(u && u != "#"){
			window.open(u);
		}else{
			window.open(_host + "/monitor/index?t="+this.dataset.id);
		}
	});
}
function loadctx(n){
	var u = n.app_preview_url;
	if(!u){
		u = _host + "/res/script/home/images/preview.png";
		return '<div class="cell-bar ss21">'+ n.app_name +drawMenu(n)+'</div><img class="ss21" src="'+ u + '" />';
	}
	var ar = u.split('.'), fix = ar[ar.length - 1].toLowerCase();
	if(/jpg|jpeg|gif|png/.test(fix))
		return '<img class="ss21" src="'+ u + '" />';
	return loadpancel(n, u);

	function drawMenu(n){
		return '<span class="cell-barico dropdown">'
			   +  '<a href="javascript:void(0);" class="dropdown-toggle white" data-toggle="dropdown"><i class="fa fa-cog"></i></a>'
			   +  '<ul class="dropdown-menu"><li><a href="javascript:void(0);" onclick="usr_setting(\''+ n.app_id+'\');"><i class="fa fa-desktop"></i>&nbsp;&nbsp;个性化配置</a></li></ul>'
			   +'</span>';
	}
}
function usr_setting(aid){
	window.open(_host + "/monitor/setting?t="+aid);
}
function loadpancel(n, u){
	return '<div class="ss21" style="margin:auto 0px;"><iframe class="ss21" src="'+u+'"></iframe></div>';
}
function loadpg(n){
	var str = [];
	str.push('<li>',
		'<a href="javascript:void(0);" aria-label="Previous" onclick="playpre();">',
			'<span aria-hidden="true">&laquo;</span>',
		'</a>',
		'</li>');
	for(var i = 1; i <= n; i++)
		str.push('<li><a href="javascript:void(0);" onclick="play(',i,');">',i, '</a></li>');
	str.push('<li>',
		'<a href="javascript:void(0);" aria-label="Next" onclick="playnext();">',
			'<span aria-hidden="true">&raquo;</span>',
		'</a>',
		'</li>');
	$("#pager").html(str.join(''));
}

function play(n){
	page = n;
	$(".apps .box").hide();
	$(".apps .box").eq(n-1).show();
	$(".pagination li").removeClass("active").eq(n).addClass("active");
	if(isdel)
		adddel();
	$(window).trigger("resize");
	var bb = $(".apps .box").eq(n-1);
	bb.sortable({
		stop:function(){
			var ids = bb.sortable('toArray'), dt = [], did = apps[0].dashboard_id;
			if(ids.length<2 || !did)
				return;
			console.log(ids);
			for(var i = 0; i < ids.length; i++){
				if(!ids[i])
					continue;
				var ss = ids[i].split('_');
				var j = (n-1) * SIZE + i + 1;
				if(j != ss[2]){
					dt.push({app_id:ss[1], app_sort:j, dashboard_id:did});
				}
			}
			var dd = {"s": JSON.stringify(dt)};
			$.post(_host+'/home/saveSort',dd, function(ds){
				if(ds.code <1){
					layer.msg("无法保存排序,"+ds.msg);
				}
			});
		}
	});
}
function playpre(){
	var i = page == 1 ? 1 : page - 1;
	play(i);
}
function playnext(){
	var i = page == pageMax ? 1 : page + 1;
	play(i);
}
function bindsearch(){
	var $ui = $('#div_append');
	$("#txtico").after(txtdata);
	var w = $("#div_append").width();
	$("#div_data ul").width(w);
	$("#div_data ul li.tit_filter").width(w - 10);
	$ui.bind('mouseleave',function(){
		//$ui.find('.arrowUp').addClass('arrowDown').removeClass('arrowUp').andSelf().find('.dropdown').slideUp(500);
	});
	$('#txt').click(function(){
		document.getElementById("div_data").style.display ='block';
		$ui.find('.arrowDown').addClass('arrowUp').removeClass('arrowDown').andSelf().find('.dropdown').slideDown(500);
	});
	$('#btn_span').click(function(){
		gosearch();
	});
}
function click_event(str){
	var $ui = $('#div_append');
	var str = $("input[name='radio']:checked").val();
	$("#txt").val(str);
	$ui.find('.arrowUp').addClass('arrowDown').removeClass('arrowUp').andSelf().find('.dropdown').slideUp(10);
}
function query(){
	var t = $("#txt").val();
	$.post('getUserApp',{'t':t}, function(d){
		if(d == null || d.length == 0){
			$("#apps").html('');
			loadpg(1);
			play(1);
			return;
		}
		loadapp('#apps',d);
	});
}
function gosearch(){
	var $ui = $('#div_append');
	$ui.find('.arrowUp').addClass('arrowDown').removeClass('arrowUp').andSelf().find('.dropdown').slideUp(10);
	layer.open({
		type: 2
		,title: false //不显示标题栏
		,area: ['680px', '500px']
		,shade: 0
		,skin:'addwin'
		,maxmin: true
		,content: _host+'/home/listinfo'
		,btn: ['添加', '取消']
		,yes: function(index, layero){
			layer.closeAll();
		}
		,btn2: function(){
			layer.closeAll();
		}
		,zIndex: layer.zIndex
		,success: function(layero){
			layer.setTop(layero);
		}
	});
}

function adddel(){
	var o = $(".apps:visible").find(".cell");
	if(!o.hasClass("shake")) {
		o.addClass('shakecontainer').addClass('shake');
		$("#del").hide();
		$("#delend").removeClass("hide");
	}
	isdel = true;
}
function del(o){
	//confirm
	$(o).parent(".cell").remove();
	ids.push($(o).parent(".cell").attr("aid"));
}
function delend(){
	var o = $(".apps:visible").find(".cell");
	o.removeClass('shakecontainer').removeClass('shake');
	$("#del").show();
	$("#delend").addClass("hide");
	isdel = false;
	if(ids.length >0){
		$.post('dodel?id='+ids.join(','), function(dt){
			loadapp('#apps',dt);
		});
		ids = [];
	}
}
function addnew(){
	layer.open({
		type: 2
		//,title: '添加应用'
		,title: false //不显示标题栏
		,area: ['680px', '500px']
		,shade: 0
		,skin:'addwin'
		,maxmin: true
		,content: _host+'/home/addapp'
		,btn: ['添加', '取消'] //只是为了演示
		,yes: function(index, layero){
			var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
			var appids = iframeWin.ids;
			if(!appids){
				layer.msg('请您点击要添加的应用', {icon: 1, time: 1000, skin: 'layer-ext-moon'});
				return;
			}

			$.post('doadd?id=' + appids.join(','), function(dt){
				layer.closeAll();
				loadapp('#apps',dt);
			});
		}
		,btn2: function(){
			layer.closeAll();
		}
		,zIndex: layer.zIndex
		,success: function(layero){
			layer.setTop(layero);
		}
	});
}
function savecomm(){
	var txt = $("#ppcomm").val();
	if(cn == null){
		alert('没有批示图片,请使用现代浏览器打开系统');
		return;
	}
	if(txt==''){
		alert('请先填写批示内容');
		return;
	}
	var imgdata = cn.toDataURL("image/png");
	imgdata = imgdata.replace(/^data:image\/(png|jpg|jpeg);base64,/,"");
	$("#ppimg").val(imgdata);
	$('#pfm').attr("action",_host+"/comments/save").ajaxSubmit(function(ds) {
		if(typeof(ds) == "string")
			ds = eval("("+ds+")");
		alert(ds.msg);
		if(ds.code > 0)
			layer.closeAll();
		/*layer.alert(ds.msg, function(i){
			if(ds.code > 0)
				layer.closeAll();
			else
				layer.close(i);
		});*/
	});
}

function capture(){
	if (typeof(Promise) == "undefined"){
		alert("您的浏览器不支持截屏,请使用现代浏览器");
		return;
	}
	html2canvas(document.body).then(
		function(canvas) {
			inipnl(canvas, $(window).width(),  600);
			layer.open({
				type: 1,
				title:'批示中心',
				area: ['800px', '600px'],
				content: $('#pnl')
				,zIndex: layer.zIndex
				,success: function(layero){
					/*layer.setTop(layero);*/
				}
			});
	});
}

//画布,蒙版
var cn, ctx, cn_bak, ctx, canvasWidth, canvasHeight;
var canvasTop = 0;
var canvasLeft = 0;

//画笔大小
var size = 5, rsize = 2;
var color  = '#ff1200';
function inipnl(can,w, h){
	$("#pnl").remove();
	var str = [];
	str.push('<div id="pnl" class="container panl" style="display:none;">',
		'<div class="row">',
			'<div class="col-xs-12 col-md-8">',
				'<div class="col-xs-12 col-md-12"><ul class="toolbar">', initbl(), '</ul>','</div>',
				'<div id="pcn" class="board col-xs-12 col-md-12" style="height:450px;overflow: auto;"></div>',
			'</div>',
			'<div class="col-xs-12 col-md-4">',
				'<div class="col-xs-12 col-md-12">',
					'<div style="height:30px;line-height:30px;padding-top:5px;"><button onclick="savecomm();" class="btn btn-success">批示发送</button></div>',
					'<form id="pfm" name="pfm" method="post"><textarea id="ppcomm" name="ppcomm" style="width:90%;height:450px;margin-top:20px;"></textarea><input type="hidden" name="ppimg" id="ppimg" /></form>',
				'</div>',
			'</div>',
		'</div>',
	'</div>');
	$(document.body).append(str.join(''));
	canvasWidth = can.width;
	canvasHeight = can.height;
	$("#pcn").append(can);
	cn = can;
	ctx = cn.getContext('2d');
	$("#pcn").append('<canvas id="canvas_bak" style="z-index: 1;position:absolute;top:0;left:0;"></canvas>');
	cn_bak = $("#canvas_bak")[0];
	cn_bak.width = canvasWidth;
	cn_bak.height = canvasHeight;
	ctx_bak = cn_bak.getContext('2d');
}
function initbl(){
	var dt = [{n:'pencil',t:'铅笔'},{n:'circle',t:'圆'},{n:'square',t:'方形'},{n:'rubber',t:'橡皮擦'}];
	var ss = [];
	var u = _host + "/res/script/home/images/";
	for(var i =0; i < dt.length; i++){
		ss.push('<li><img src="',u, dt[i].n,'.png" onclick="draw_graph(&#39;',dt[i].n, '&#39;,this)" title="',dt[i].t,'"></li>')
	}
	return ss.join('');
}
//选择功能按钮 修改样式
function chooseImg(obj){
	$(".toolbar li img").removeClass("active");
	$(obj).addClass("active");
}
function getLocation(e) {
	e=e||window.event;
	var bbox = cn.getBoundingClientRect();
	return {
		x: e.clientX,
		y: e.clientY
		/*x: (e.clientX - bbox.left) * (cn.width / bbox.width),
		y: (e.clientY - bbox.top) * (cn.height / bbox.height)*/
	};
}
//画图形
var draw_graph = function(graphType,obj){
	canvasTop = $(cn).offset().top;
	canvasLeft = $(cn).offset().left;
	//把蒙版放于画板上面
	$(cn_bak).css("z-index",1);
	//先画在蒙版上 再复制到画布上
	chooseImg(obj);
	var canDraw = false;

	var startX;
	var startY;

	//鼠标按下获取 开始xy开始画图
	var mousedown = function(e){
		ctx.strokeStyle= color;
		ctx_bak.strokeStyle= color;
		ctx_bak.lineWidth = size;
		var loc = getLocation(e);
		startX = loc.x - canvasLeft;
		startY = loc.y - canvasTop;
		ctx_bak.moveTo(startX ,startY );
		canDraw = true;

		if(graphType == 'pencil'){
			ctx_bak.beginPath();
		}else if(graphType == 'circle'){
			ctx.beginPath();
			ctx.moveTo(startX ,startY );
			ctx.lineTo(startX +2 ,startY+2);
			ctx.stroke();
		}else if(graphType == 'rubber'){
			ctx.clearRect(startX - size * rsize ,  startY - size * rsize , size * rsize * 2 , size * rsize * 2);
		}
	};

	//鼠标离开 把蒙版canvas的图片生成到canvas中
	var mouseup = function(e){
		canDraw = false;

		var image = new Image();
		if(graphType!='rubber'){
			image.src = cn_bak.toDataURL();
			image.onload = function(){
				ctx.drawImage(image , 0 ,0 , image.width , image.height , 0 ,0 , canvasWidth , canvasHeight);
				clearContext();
				//saveImageToAry();
			}
			var loc = getLocation(e);
			var x = loc.x - canvasLeft;
			var y = loc.y - canvasTop;
			ctx.beginPath();
			ctx.moveTo(x ,y );
			ctx.lineTo(x +2 ,y+2);
			ctx.stroke();
		}
	};

	// 鼠标移动
	var  mousemove = function(e){
		var loc = getLocation(e);
		var x = loc.x - canvasLeft;
		var y = loc.y - canvasTop;
		//方块  4条直线搞定
		if(graphType == 'square'){
			if(canDraw){
				ctx_bak.beginPath();
				clearContext();
				ctx_bak.moveTo(startX , startY);
				ctx_bak.lineTo(x  ,startY );
				ctx_bak.lineTo(x  ,y );
				ctx_bak.lineTo(startX  ,y );
				ctx_bak.lineTo(startX  ,startY );
				ctx_bak.stroke();
			}
			//直线
		}else if(graphType =='line'){
			if(canDraw){
				ctx_bak.beginPath();
				clearContext();
				ctx_bak.moveTo(startX , startY);
				ctx_bak.lineTo(x  ,y );
				ctx_bak.stroke();
			}
			//画笔
		}else if(graphType == 'pencil'){
			if(canDraw){
				ctx_bak.lineTo(x ,y);
				ctx_bak.stroke();
			}
			//圆 未画得时候 出现一个小圆
		}else if(graphType == 'circle'){
			clearContext();
			if(canDraw){
				ctx_bak.beginPath();
				var radii = Math.sqrt((startX - x) *  (startX - x)  + (startY - y) * (startY - y));
				ctx_bak.arc(startX,startY,radii,0,Math.PI * 2,false);
				ctx_bak.stroke();
			}else{
				ctx_bak.beginPath();
				ctx_bak.arc(x,y,20,0,Math.PI * 2,false);
				ctx_bak.stroke();
			}
			//涂鸦 未画得时候 出现一个小圆
		}else if(graphType == 'handwriting'){
			if(canDraw){
				ctx_bak.beginPath();
				ctx_bak.strokeStyle = color;
				ctx_bak.fillStyle  = color;
				ctx_bak.arc(x,y,size*10,0,Math.PI * 2,false);
				ctx_bak.fill();
				ctx_bak.stroke();
				ctx_bak.restore();
			}else{
				clearContext();
				ctx_bak.beginPath();
				ctx_bak.fillStyle  = color;
				ctx_bak.arc(x,y,size*10,0,Math.PI * 2,false);
				ctx_bak.fill();
				ctx_bak.stroke();
			}
			//橡皮擦 不管有没有在画都出现小方块 按下鼠标 开始清空区域
		}else if(graphType == 'rubber'){
			ctx_bak.lineWidth = 1;
			clearContext();
			ctx_bak.beginPath();
			ctx_bak.strokeStyle =  '#000000';
			ctx_bak.moveTo(x - size * rsize ,  y - size * rsize );
			ctx_bak.lineTo(x + size * rsize  , y - size * rsize );
			ctx_bak.lineTo(x + size * rsize  , y + size * rsize );
			ctx_bak.lineTo(x - size * rsize  , y + size * rsize );
			ctx_bak.lineTo(x - size * rsize  , y - size * rsize );
			ctx_bak.stroke();
			if(canDraw){
				context.clearRect(x - size * rsize ,  y - size * rsize , size * rsize * 2 , size * rsize * 2);
			}
		}
	};

	//鼠标离开区域以外 除了涂鸦 都清空
	var mouseout = function(){
		if(graphType != 'handwriting'){
			clearContext();
		}
	}

	$(cn_bak).unbind();
	$(cn_bak).bind('mousedown',mousedown);
	$(cn_bak).bind('mousemove',mousemove);
	$(cn_bak).bind('mouseup',mouseup);
	$(cn_bak).bind('mouseout',mouseout);
	$("#pcn").scroll(function(){
		canvasTop = $(cn).offset().top;
		canvasLeft = $(cn).offset().left;
	});
}

//清空层
var clearContext = function(type){
	if(!type){
		ctx_bak.clearRect(0,0,canvasWidth,canvasHeight);
	}else{
		context.clearRect(0,0,canvasWidth,canvasHeight);
		ctx_bak.clearRect(0,0,canvasWidth,canvasHeight);
	}
}
