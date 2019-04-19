// JavaScript Document

//取ID-------------------------------------------------------------------------
function getEbyId(objectId){
    if (document.getElementById && document.getElementById(objectId)) {
        return document.getElementById(objectId)
    }
    else 
        if (document.all && document.all(objectId)) {
            return document.all(objectId)
        }
        else 
            if (document.layers && document.layers[objectId]) {
                return document.layers[objectId]
            }
            else {
                return false
            }
}

//通过样式取对象-------------------------------------------------------------
function getElementsByClassName(className, tagName){
    var ele = [], all = document.getElementsByTagName(tagName || "*");
    for (var i = 0; i < all.length; i++) {
        if (all[i].className == className) {
            ele[ele.length] = all[i];
        }
    }
    return ele;
}
	
$(function(){
    //首页选择分类
    //$("#sortbox").hide();
    $('#sort-all').click(function(e) {
        e.stopPropagation();
        $('#sortbox').slideToggle();
        $(this).toggleClass('sort-all');
    });

    $("#sortbox").click(function(e) {
        e.stopPropagation();
    });

    $(document).click(function() {
        $("#sortbox").slideUp();
        $('#sort-all').removeClass('sort-all');
    });

});


$(function(){
    //首页登录
   // $("#loginbox").hide();
    $('#loginbtn').click(function(e) {
		
        e.stopPropagation();
		$('#overlay ').fadeIn(200);
        $('#loginbox').slideToggle();
        $(this).toggleClass('login_on');
		
    });

    $("#loginbox").click(function(e) {
        e.stopPropagation();
    });

    // $(document).click(function() {
    //     $("#loginbox").slideUp();
    //     $('#login_on').removeClass('login_on');
		// $('#overlay' ).fadeOut(600);
    // });

});


/*第一种形式 第二种形式 更换显示样式*/
function setTab(name,cursel,n){
	for(i=1;i<=n;i++){
		var menu=document.getElementById(name+i);
		var con=document.getElementById("con_"+name+"_"+i);
		menu.className=i==cursel?"hover":"";
		con.style.display=i==cursel?"block":"none";
	}

	//证书非证书状态设置
	if(cursel==2) {
		$("#userType").val("COMMON");
	} else {
		$("#userType").val("CERTIFICATE");
	}
}

