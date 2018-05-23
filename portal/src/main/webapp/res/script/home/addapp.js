function addend(){
	$(".cell .cell-ctx.selected").on("click", function(){
		$(this).toggleClass("selected");
	});
	$.post('doadd?id=' + ids.join(','), function(d){
		if(d && d.code === 0){
			alert(d.msg);
			return;
		}
		location.href = 'main';
	});

}
function addlist(id, dt){
	var uk = "其他", m = {}, arr = [];
	if(dt == null || dt.length == 0)
		return;
	for(var i = 0; i < dt.length; i++){
		var ss = dt[i].app_class_name;
		var b = false;
		var tt = ss;
		if(ss == null){
			if(m[uk] == null){
				arr.push([]);
				m[uk] = arr.length - 1;
				b=true;
			}
			tt = uk;
		}
		else if(m[ss] == null){
			arr.push([]);
			m[ss] = arr.length - 1;
			b=true;
		}
		var str = arr[m[tt]];
		if(b){
			str.push('<div class="title">', tt ,'</div>');
			str.push('<div class="value">');
		}
		var icon = dt[i].menu_icon_url || _host + "/res/script/home/images/default.png";
		str.push('<div class="cell ss11">', '<div id="a', dt[i].app_id, '" class="cell-ctx">', '<img src="', icon, '" />', '</div>', '<div class="cell-ttl"><span>', dt[i].app_name, '</span></div>', '</div>');
	}
	var res = '';
	for(var j = 0; j < arr.length; j++){
		res += '<div class="apps">' + arr[j].join('') + '</div><div class="clear"></div></div>';//<div class="box">
	}
	$(id).html(res);
}