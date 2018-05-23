function loadapp(id, dt){
	var size = 6, x = 1, html = [];
	if(dt == null || dt.length == 0)
		return;
	for(var i = 0; i < dt.length; i++){
		if(i % size == 0){
			if(x > 1)
				html.push('</div>');
			html.push('<div id="box', x++, '" class="box sortable grid-stack">');
		}
		html.push('<div data-title="', dt[i].app_name,'" data-url="', dt[i].app_url, '" aid="',dt[i].pri_id, '" class="cell grid-stack-item ss21">', '<div class="cell-ctx">', '<img class="ss21" src="', dt[i].app_preview_url, '" />', '</div>', '<i class="cell-remove">×</i>', '</div>');
	}
	html.push('</div>');
	$(id).html(html.join(''));
	pageMax = Math.ceil(dt.length / size);
	if(pageMax == 0) pageMax = 1;

	layout(pageMax);
	loadpg(pageMax);
	play(1);

	$(".cell .cell-remove").on("click", function(){
		del(this);
	});
	$(".cell").on("dblclick", function(){
		var u = this.dataset.url, t = this.dataset.title;
		if(u && u != "#"){
			layer.open({
				type: 2
				,title: t
				,area: ['800px', '600px']
				,shade: 0
				,maxmin: true
				,content: u
				,btn: []
				,zIndex: layer.zIndex
				,success: function(layero){
					layer.setTop(layero);
				}
			});
		}
	});
}
function ini_portlet(){
	ko.components.register('dashboard-grid', {
		viewModel: {
			createViewModel: function (controller, componentInfo) {
				var ViewModel = function (controller, componentInfo) {
					var grid = null;

					this.widgets = controller.widgets;

					this.afterAddWidget = function (items) {
						if (grid == null) {
							grid = $(componentInfo.element).find('.grid-stack').gridstack({
								auto: false
							}).data('gridstack');
						}

						var item = _.find(items, function (i) { return i.nodeType == 1 });
						grid.add_widget(item);
						ko.utils.domNodeDisposal.addDisposeCallback(item, function () {
							grid.remove_widget(item);
						});
					};
				};

				return new ViewModel(controller, componentInfo);
			}
		},
		template:
			[
				'<div class="grid-stack" data-bind="foreach: {data: widgets, afterRender: afterAddWidget}">',
				'   <div class="grid-stack-item" data-bind="attr: {\'data-gs-x\': $data.x, \'data-gs-y\': $data.y, \'data-gs-width\': $data.width, \'data-gs-height\': $data.height, \'data-gs-auto-position\': $data.auto_position}">',
				'       <div class="grid-stack-item-content"><div data-title="$data.app_name" data-url="$data.app_url" aid="$data.pri_id" class="cell ss32">', '<div class="cell-ctx">', '<img class="ss21" src="$data.app_preview_url" />', '</div>', '<i class="cell-remove">×</i>', '</div>','</div>',
				'   </div>',
				'</div> '
			].join('')
	});
	window.DG_Controller = function (widgets) {
		var self = this;

		this.widgets = ko.observableArray(widgets);

		this.add_new_widget = function () {
			this.widgets.push({
				x: 0,
				y: 0,
				width: Math.floor(1 + 3 * Math.random()),
				height: Math.floor(1 + 3 * Math.random()),
				auto_position: true
			});
		};

		this.delete_widget = function (item) {
			self.widgets.remove(item);
		};
	};
}
function ini_layout(){
	var widgets = [
		{x: 0, y: 0, width: 2, height: 2},
		{x: 2, y: 0, width: 4, height: 2},
		{x: 6, y: 0, width: 2, height: 4},
		{x: 0, y: 2, width: 4, height: 2}
	];

	var controller = new DG_Controller(widgets);
	ko.applyBindings(controller);
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

}

function play(i){
	page = i;
	$(".apps .box").hide();
	$(".apps .box").eq(i-1).show();
	$(".pagination li").removeClass("active").eq(i).addClass("active");
	if(isdel)
		adddel();
	$(window).trigger("resize");
}
function playpre(){
	var i = page == 1 ? 1 : page - 1;
	play(i);
}
function playnext(){
	var i = page == pageMax ? 1 : page + 1;
	play(i);
}
function query(){
	var t = $("#txt").val();
	$.post('getUserApp',{'t':t}, function(d){
		loadapp('#apps',d);
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
function loadmenu(db){
	var str  = ['<li class="sidebar-brand"><a href="#">应用菜单</a></li>'];
	var apps = [], app_frt = '';
	for(var i = 0; i < db.length; i++){
		if(db[i].app_url){
			apps.push(db[i]);
			continue;
		}
		if(!app_frt)
			app_frt = db[i].app_id;
		str.push('<li>','<a href="#"><i class="fa fa-fw fa-file-o"></i>', db[i].app_name , '</a>','</li>');
	}
	str.push('<li class="dropdown">','<a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-fw fa-folder"></i> 集成应用 <span class="caret"></span></a>','<ul class="dropdown-menu" role="menu">');
	for(var j = 0; j< apps.length;j++){
		str.push('<li>','<a href="#">&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-fw fa-file-o"></i>', apps[j].app_name , '</a>','</li>');
	}
	str.push('</ul> </li>');
	$("#appmenu").html(str.join(''));
	loadmnt(app_frt);
}
function loadmnt(aid){

}
function loadnav(){
	var trigger = $('.hamburger'),
		overlay = $('.overlay'),
		isClosed = false;

	trigger.click(function () {
		hamburger_cross();
	});

	function hamburger_cross() {

		if (isClosed == true) {
			overlay.hide();
			trigger.removeClass('is-open');
			trigger.addClass('is-closed');
			isClosed = false;
		} else {
			overlay.show();
			trigger.removeClass('is-closed');
			trigger.addClass('is-open');
			isClosed = true;
		}
	}

	$('[data-toggle="offcanvas"]').click(function () {
		$('#wrapper').toggleClass('toggled');
	});
}
function savecomm(){
	var txt = $("#ppcomm").val();
	if(cn == null){
		layer.alert('没有批示图片,请使用现代浏览器打开系统');
		return;
	}
	if(txt==''){
		layer.alert('请先填写批示内容');
		return;
	}
	var imgdata = cn.toDataURL("image/png");
	imgdata = imgdata.replace(/^data:image\/(png|jpg|jpeg);base64,/,"");
	$("#ppimg").val(imgdata);
	$('#pfm').attr("action",_host+"/comments/save").ajaxSubmit(function(ds) {
		if(typeof(ds) == "string")
			ds = eval("("+ds+")");
		layer.alert(ds.msg, function(i){
			if(ds.code > 0)
				layer.closeAll();
			else
				layer.close(i);
		});
	});
}

function capture(){
	if (typeof(Worker) == "undefined"){
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
var size = 5;
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
			var x = e.clientX - canvasLeft;
			var y = e.clientY - canvasTop;
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
		var x = e.clientX - canvasLeft;
		var y = e.clientY - canvasTop;
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


