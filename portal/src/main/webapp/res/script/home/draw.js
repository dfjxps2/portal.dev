function loadapp(id, dt){
	var size = 6, x = 1, html = [];
	if(dt == null || dt.length == 0)
		return;
	for(var i = 0; i < dt.length; i++){
		if(i % size == 0){
			if(x > 1)
				html.push('</div>');
			html.push('<div id="box', x++, '" class="box sortable">');
		}
		html.push('<div aid="',dt[i].pri_id, '" class="cell ss32">', '<div class="cell-ctx">', '<img class="ss21" src="', dt[i].app_url, '" />', '</div>', '<i class="cell-remove">×</i>', '</div>');
	}
	html.push('</div>');
	$(id).html(html.join(''));
	pageMax = Math.ceil(dt.length / size);
	if(pageMax == 0) pageMax = 1;

	layout(pageMax);
	loadpg(pageMax);
	play(1);
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
function layout(n){
	for(var i = 1; i <= n; i++){
		var ww = new freewall("#box"+i);
		ww.reset({
			draggable: true,
			selector: '.cell',
			animate: true,
			fixSize: 0,
			cellW: function(width) {
				var cellWidth = width / 3;
				return cellWidth - 20;
			},
			cellH: 150,
			onResize: function() {
				ww.refresh();
			}
		});
		ww.fitWidth();
	}
	$(window).trigger("resize");
}

function play(i){
	page = i;
	$(".apps .box").hide();
	$(".apps .box").eq(i-1).show();
	$(".pagination li").removeClass("active").eq(i).addClass("active");
	if(isdel)
		adddel();
}
function playpre(){
	var i = page == 1 ? 1 : page - 1;
	play(i);
}
function playnext(){
	var i = page == pageMax ? 1 : page + 1;
	play(i);
}
function adddel(){
	var o = $(".apps .box:visible").find(".cell");
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
	var o = $(".apps .box:visible").find(".cell");
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
	location.href = 'addapp';
}
function capture(){
	html2canvas(document.body).then(canvas => {
		inipnl(canvas, $(window).width(),  600);
		layer.open({
			type: 1,
			title:'批示中心',
			area: ['800px', '600px'],
			content: $('#pnl')
			,zIndex: layer.zIndex
			,success: function(layero){
				layer.setTop(layero);
			}
		});
	});
}

//画布,蒙版
var cn, ctx, cn_bak, ctx, canvasWidth, canvasHeight;
var canvasTop;
var canvasLeft;

//画笔大小
var size = 1;
var color  = '#000000';
function inipnl(cn,w, h){
	$("#pnl").remove();
	var str = [];
	str.push('<div id="pnl" class="container" style="display:none;width:790px;height:120%;">',
		'<div class="row">',
			'<div class="col-xs-12 col-md-8">',
				'<div class="col-xs-12 col-md-12"><ul class="toolbar">', initbl(), '</ul>','</div>',
				'<div id="pcn" class="board col-xs-12 col-md-12"></div>',
			'</div>',
			'<div class="col-xs-12 col-md-4">',
				'<div class="col-xs-12 col-md-12">',
					'<div style="height:30px;line-height:30px;padding-top:5px;"><button class="btn btn-success">批示发送</button></div>',
					'<textarea style="width:90%;height:90%;margin-top:20px;"></textarea>',
				'</div>',
			'</div>',
		'</div>',
	'</div>');
	$(document.body).append(str.join(''));
	canvasWidth = cn.width;
	canvasHeight = cn.height;
	canvasTop = $(cn).offset().top;
	canvasLeft = $(cn).offset().left;
	$("#pcn").append(cn);
	ctx = cn.getContext('2d');
	$("#pcn").append('<canvas id="canvas_bak" style="z-index: 1;position:absolute;top:0;left:0;"></canvas>');
	cn_bak = $("#canvas_bak")[0];//cn;
	cn_bak.width = canvasWidth;
	cn_bak.height = canvasHeight;
	ctx_bak = cn_bak.getContext('2d');//cn.getContext('2d');
}
function initbl(){
	var dt = [{n:'pencil',t:'铅笔'},{n:'circle',t:'圆'},{n:'square',t:'方形'}];
	var ss = [];
	var u = _host + "/res/script/home/images/";
	for(var i =0; i < dt.length; i++){
		ss.push('<li><img src="',u, dt[i].n,'.png" onclick="draw_graph(&#39;',dt[i].n, '&#39;,this)" title="',dt[i].t,'"></li>')
	}
	return ss.join('');
}

//画图形
var draw_graph = function(graphType,obj){
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
		e=e||window.event;
		startX = e.clientX - canvasLeft;
		startY = e.clientY - canvasTop;
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
			ctx.clearRect(startX - size * 10 ,  startY - size * 10 , size * 20 , size * 20);
		}
	};

	//鼠标离开 把蒙版canvas的图片生成到canvas中
	var mouseup = function(e){
		e=e||window.event;
		canDraw = false;

		var image = new Image();
		if(graphType!='rubber'){
			image.src = cn_bak.toDataURL();
			image.onload = function(){
				ctx.drawImage(image , 0 ,0 , image.width , image.height , 0 ,0 , canvasWidth , canvasHeight);
				clearContext();
				//saveImageToAry();
			}
			var x = e.clientX   - canvasLeft;
			var y = e.clientY  - canvasTop;
			ctx.beginPath();
			ctx.moveTo(x ,y );
			ctx.lineTo(x +2 ,y+2);
			ctx.stroke();
		}
	};

	//选择功能按钮 修改样式
	function chooseImg(obj){
		$(".toolbar li img").removeClass("active");
		$(obj).addClass("active");
	}

	// 鼠标移动
	var  mousemove = function(e){
		e=e||window.event;
		var x = e.clientX   - canvasLeft;
		var y = e.clientY  - canvasTop;
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
				ctx_bak.lineTo(e.clientX   - canvasLeft ,e.clientY  - canvasTop);
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
			ctx_bak.moveTo(x - size * 10 ,  y - size * 10 );
			ctx_bak.lineTo(x + size * 10  , y - size * 10 );
			ctx_bak.lineTo(x + size * 10  , y + size * 10 );
			ctx_bak.lineTo(x - size * 10  , y + size * 10 );
			ctx_bak.lineTo(x - size * 10  , y - size * 10 );
			ctx_bak.stroke();
			if(canDraw){
				context.clearRect(x - size * 10 ,  y - size * 10 , size * 20 , size * 20);

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
