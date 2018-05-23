/***原型扩展 Start***/
//检测字符串是否为空  
String.prototype.isEmpty = function () { return !(/.?[^\s]+/.test(this)); }  

String.format = function () {
    if (arguments.length == 0) {
        return null;
    }
    var str = arguments[0];
    for (var i = 1; i < arguments.length; i++) {
        var re = new RegExp('\\{' + (i - 1) + '\\}', 'gm');
        str = str.replace(re, arguments[i]);
    }
    return str;
}

Number.format = function (value, scale) {
    if (value == undefined || value == null) {
        return '';
    }
    value = value.toString().replace(/\$|\,/g, '');
    if (isNaN(value))
        value = "0";
    sign = (value == (value = Math.abs(value)));
    value = Math.floor(value * 100 + 0.50000000001);
    cents = value % 100;
    value = Math.floor(value / 100).toString();
    if (cents < 10)
        cents = "0" + cents;
    for (var i = 0; i < Math.floor((value.length - (1 + i)) / 3) ; i++)
        value = value.substring(0, value.length - (4 * i + 3)) + ',' +
        value.substring(value.length - (4 * i + 3));
    return (((sign) ? '' : '-') + value + '.' + cents);
}

Array.prototype.contains = function (obj) {
    var i = this.length;
    while (i--) {
        if (this[i] === obj) {
            return true;
        }
    }
    return false;
}


// 对Date的扩展，将 Date 转化为指定格式的String   
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，   
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)   
// 例子：   
// (new Date()).format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423   
// (new Date()).format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18   
Date.prototype.format = function (fmt) { //author: meizz   
    var o = {
        "M+": this.getMonth() + 1,                 //月份   
        "d+": this.getDate(),                    //日   
        "h+": this.getHours(),                   //小时   
        "m+": this.getMinutes(),                 //分   
        "s+": this.getSeconds(),                 //秒   
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度   
        "S": this.getMilliseconds()             //毫秒   
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
function isArray(value) {
    if (typeof Array.isArray === "function") {
        return Array.isArray(value);
    } else {
        return Object.prototype.toString.call(value) === "[object Array]";
    }
}


//全能时间格式（带参数），兼容IE、火狐浏览器
function NewDate(str) {

    var Sys = {};
    var ua = navigator.userAgent.toLowerCase();
    var s;
    var newdate;

    (s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] :
    (s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] :
    (s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] :
    (s = ua.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] :
    (s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;

    if (Sys.ie) {
        //如果是IE浏览器,解决 IE浏览器下 new Date（）带参数格式化问题。
        //IE 时间格式化
        str = str.toString().split('T'); //2014-06-03T11:43:49
        var strDate = str[0]; //2014-06-03
        var strTime = str[1]; //11:43:49

        strDate = strDate.toString().split('-');
        strTime = strTime.toString().split(':');

        var year = strDate[0];
        var month = strDate[1];
        var day = strDate[2];

        var hour = strTime[0];
        var minute = strTime[1];
        var second = strTime[2];

        newdate = new Date(year, month - 1, day, hour, minute, second); //IE 下 月份=（月份-1）
    }
    else {
        newdate = new Date(str);
    }
    return newdate;
}


// 格式化日期
// dateString: 转换日期
// dataFormat: 转换格式
function dateTimeFormatter(dateString, dateFormat) {
    if (dateString == undefined || dateString == null || dateString == "null" || dateString.length <= 0) {
        return "";
    }
    var odate = new Date(dateString);
    return odate.Format(dateFormat);
}

function getDateStr(val) {
    if (val) {
        return val.split(' ')[0];
    }
}
 
//from转json
$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

/***原型扩展 End***/

/***DOM操作扩展 End***/
//复选框操作
function CheckAll(father, chks) {
    chks = chks || 'chks';
    var c = document.getElementsByName(chks);
    for (var i = 0; i < c.length; i++)
        c[i].checked = father.checked;
}
function CheckTr(obj) {
    var c = $("input:checkbox", obj)[0];
    if (c) c.click();
}

function getSelectText(obj, v) {
    v = v || $("#" + obj).val();
    $("#" + obj).find("option").each(function () {
        if ($(this).attr("value") == v) {
            v = $(this).text();
            return true;
        }
    });
    return v;
}

/***DOM操作扩展 End***/

/***赋值取值转换 Start***/
var request = {
    get:function(name){
        var url = decodeURIComponent(window.location.href);
        var regexS = "[\\?&]" + name + "=([^&#]*)";
        var regex = new RegExp(regexS,'i');
        var results = regex.exec(url);
        if (results == null) {
            return "";
        } else {
            return results[1];
        }
    },
    getAll:function(){
        var obj = {};
        var arr = window.location.href.split('?');
        if (arr.length < 2) return obj;
        var kvp = arr[1].replace('?', '&').split('&');
        for (var i = 0; i < kvp.length; i++) {
            var k = kvp[i].split('=');
            if (!k || k.length != 2)
                continue;
            if (k[1].length == 0)
                continue;
            obj[k[0]] = k[1];
        }
        return obj;
    }
};
function options2Json(optionString) {
    var reg = /([^\s\:\{\,\d]+|[a-z][a-z\d]*)\s*\:/gi;
    var jsonString = optionString.replace(reg, "\'$1\'\:");
    var options = eval("( {" + jsonString + "} )");
    return options;
}
//url连接
function urlJoin(str) {
    var fix = str.indexOf('?') > -1 ? '&' : '?';
    for(var i = 1; i < arguments.length; i++){
        if(arguments[i] != '' && str.indexOf(arguments[i]) > -1){
            continue;
        }
        str += fix + arguments[i];
        fix = "&";
    }
    return str;
}
function urlDel(url){
    for(var i = 1; i < arguments.length; i++){
        //var reg=new RegExp("(^\?|&)" + arguments[i] + "=[^&]*(&)?","gi");
        var reg=new RegExp("(\\\?|&)" + arguments[i] + "=([^&#]*)(&|&)","i");
        url = url.replace(reg, function(p0, p1, p2) {
            return p1 === '?' || p2 ? p1 : '';
        });
    }
    var x = url.indexOf('?');
    if(x == -1)
        url += "?i=" + Math.random();
    else if(x == url.length - 1)
        url = url + "i=" + Math.random();
    return url;
}

function tourlpar(j) {
    var prm = [];
    j = j || ".query,.toolbar";
    $(j).find(":text,input:hidden,:password,:radio,:checkbox,textarea,select").each(function (i) {
        var key = this.name ? this.name : (this.id ? this.id.substr(3) : '');
        if (key == '') return;
        var nodeType = this.nodeName;
        if (nodeType == "input") {
            var etype = $(this).attr("type") || "text";
            if (etype == "checkbox" || etype == "radio") {
                if (!this.checked)
                    return;
            }
        }
        if ((this.className.indexOf("easyui-combotree") > -1 || this.className.indexOf("easyui-combobox") > -1) && $(this).combotree) {
            //$qbox.alert(this.name);
            //prm.push(this.name + "=" + escape(this.value));
            try {
                prm.push(key + "=" + $(this).combobox('getValue'));
            } catch (e) {
                if (this.name)
                    prm.push(key + "=");
            }
        } else {
            prm.push(key + "=" +encodeURI(encodeURI(this.value)));
        }
    });
    return "&" + prm.join("&");
}

//从URL,JSON中取值赋给表单元素
function setForm(obj) {
    if (!obj) {
        obj = request.getAll();
    } else if (typeof (obj) == "string") {
        obj = eval("(" + obj + ")");
    }
    for (var name in obj) {
        //var val = obj[name] || '';
        var val = obj[name] === null ? '' : obj[name];
        var nodes = document.getElementsByName(name);
        if (nodes == null || nodes.length == 0) {
            _selector = $("#" + name);
        } else {  
            _selector = $(nodes);
        }
        if (_selector == null || _selector.length == 0)
            continue;
        var nodeType = _selector[0].nodeName.toLowerCase();
        if (nodeType == "input") {
            var etype = _selector.attr("type") || "text";
            if (etype == "checkbox") {
                CheckBoxInit(name, val);
            } else if (etype == "radio") {
                _selector.each(function () {
                    if ($(this).val() == decodeURI(val)) {
                        $(this).attr("checked", "checked");
                        if (typeof ($(this).parent().parent().attr("data-toggle")) != "undefined") {
                            $(this).parent().parent().find('label').removeClass("active");
                            $(this).parent().addClass("active");
                        }
                    }
                });
            } else {
                _selector.val(val);
            }

        } else if (nodeType == "select") {
            ListInit(name, decodeURI(val));
        } else if (nodeType == "div") {

        } else if (nodeType == "button") {
            _selector.val(val);
        } else {
            _selector.text(decodeURI(val));
        }
    }
    //选中多选下拉框的值
    function ListInit(obj, v) {
        if (!obj) return false;
        var x = v.split(',');
        $("#" + obj).find("option").each(function () {
            for (var i = 0; i < x.length; i++) {
                if (x[i] == this.value)
                    this.selected = "selected";
            }
        });
    }
    function CheckBoxInit(obj, v) {
        if (null == obj || v == '') return false;
        var chks = document.getElementsByName(obj);
        var vs = v.split(',');
        for (var i = 0; i < chks.length; i++) {
            chks[i].checked = false;
            for (var j = 0; j < vs.length; j++) {
                if (vs[j] == chks[i].value) {
                    chks[i].checked = true;
                    break;
                }
            }
        }
    }
}

function newForm(obj){
    var f = $("form").serializeObject();
    for(var n in f)
        f[n] = '';
    var o = $.extend(f, obj);
    setForm(o);
}

/***赋值取值转换 End***/


/***弹窗跳转 Start***/

// 返回
function goBack() {
    history.go(-1);
}


//提交表单
function formSubmit(url, map) {
    var form = $("<form method='post' target='' />");//定义一个form表单
    form.attr("action", url);
    for (var n in map) {
        var input1 = $("<input type='hidden' >");
        input1.attr("name", n);
        input1.attr("value", map[n]);
        form.append(input1);
    }
    
    $("body").append(form);//将表单放置在web中
    form.submit();//表单提交 
}

/***弹窗跳转 End***/


/***通用 Start***/
function refreshForm() {
    var j = ".toolbar";
    $(j).find("input:text,select").each(function (i) {
        if (this.className.indexOf("easyui-combotree") > -1) {
            $(this).combotree('setValue', '');
        } else if (this.className.indexOf("easyui-combobox") > -1) {
            $(this).combobox('setValue', '');
        } else if (this.className.indexOf("easyui-datebox") > -1) {
            $(this).combo('setValue', '');
        } else {
            this.value = '';
        }
    });
}
//只读视图
function viewRead(dom) {
    $(":text,:password,textarea").attr("readOnly", "readOnly").addClass("read");
    $(":checkbox,:radio,:submit,:file").attr("disabled", "disabled").addClass("read");
    $("select").each(function(i){
       var id = this.id + "0";
        $(this).hide();
        $(this).after("<input type='text' id='"+id+"' class='"+this.className+"' readOnly='readOnly' />");
        $("#"+id).val(getSelectText(this.id));
    });
    $("#btnSave").attr("disabled", "true");
    $("#a_save").hide();
    $(".save").hide();
    $(".close").attr("disabled", false).removeClass("read");
}
/*获取父页面元素并动态赋值父页面title*/
var _navStr = ['#title_list_first','#title_list_two','#title_list_thr'];
function navSet(txt){
    $(parent.document).find("#title_list_two").html(txt);
}
function navAppend(txt, i){
    i = i || 3;
    $(parent.document).find(".icon-right").addClass("glyphicon glyphicon-chevron-right title_list");
    $(parent.document).find("#title_list_two").addClass("title_list");
    $(parent.document).find("#title_list_thr").html(txt);
}
function navGoto(lv){
    for(var i = lv; i < _navStr.length; i++){
        $(parent.document).find(_navStr[i]).removeClass("title_list").html('');
        $(parent.document).find(_navStr[i]).prev().removeClass("glyphicon glyphicon-chevron-right title_list");
    }
    $(parent.document).find(_navStr[lv-1]).removeClass("title_list");
}
function menuGoto(url){
    window.location.href = url;
}

function checkResult(o, showOK, fn) {
    fn = fn || function(){};
    if (o == undefined) {
        $qbox.alert("服务器未知响应,请联系系统开发商。");
        return false;
    }
    try {
        if (typeof (o) == "string") {   //转不成功直接输出
            o = eval("(" + o + ")");
        }
        if (typeof (o) == "object" && o.code && o.code < 0) {
            layer.alert(decodeURI(o.msg));
            return false;
        }
        if (showOK) {
            layer.alert(decodeURI(o.msg), fn);
            return true;
        }
    } catch (e) {
        layer.alert("系统异常," + decodeURI(o));
        return false;
    }
    return o;
}

//回车调用函数
function enterFn(obj, fn) {
    $(obj).bind('keydown', function (e) {
        var key = event.keyCode || e.which;
        if (key == 13) {
            e.preventDefault();
            if (fn)
                fn($(obj));
            else
                event.keyCode = 9;
        }
    });
}

function enter2Tab(obj, bnId) {
    obj = obj || $("form");
    var ins = obj.find(":text,:password,:radio,:checkbox,select,textarea");
	var l = ins.length;
    if(ins.length > 0)
    	ins[0].focus();
    obj.keydown(function (e) {
        var key = event.keyCode || e.which;
        if (key == 13) {
            var el = $(document.activeElement);
            var n = enterFocus(ins, el, l, bnId || '#btnSave');
            if (n)
                return false;
        }
    });
    function enterFocus(els, el, l, call) {
        if (el[0].nodeName.toLowerCase() == "textarea")
            return false;
        var i = els.index(el);
        var next = els[++i];
        if (next == null && call) {
            $(call).click();
            return $(call);
        }
        if (next.readOnly || next.disabled || next.style.display == "none") {
            return enterFocus(els, $(next), l, call);
        }
        if (i <= l - 1) {
            next.focus();
        } else if (call) {
        	$(call).click();
        }
        return next;
    }
}

//第一个可用元素获取焦点
function firstFocus(o) {
    var first = null;
    for (var i = 0, l = o.length; i < l; i++) {
        first = o.get(i);
        if (!first.readOnly && !first.disabled) {
            if (first.style.display == "none")
                continue;
            try { first.focus(); } catch (e) {; }
            break;
        }
    }
}


(function () {
    //创建空console对象，避免JS报错   
    if (!window.console)
        window.console = {};
    var console = window.console;

    var funcs = ['assert', 'clear', 'count', 'debug', 'dir', 'dirxml',
                 'error', 'exception', 'group', 'groupCollapsed', 'groupEnd',
                 'info', 'log', 'markTimeline', 'profile', 'profileEnd',
                 'table', 'time', 'timeEnd', 'timeStamp', 'trace', 'warn'];
    for (var i = 0, l = funcs.length; i < l; i++) {
        var func = funcs[i];
        if (!console[func])
            console[func] = function () { };
    }
    if (!console.memory)
        console.memory = {};

})();

var debug = false;
var debugType = "log";

function ShowDebug(data) {
    if (debug) {

        switch (debugType) {
            case "log": console.log(data);
                break;
            case "alert": alert(data);
                break;
            default:
                console.log(data);
        }
    }
}


//项目自定义--------------------------------------

function nvl(obj, v) {
    v = v || '';
    return obj || v;
}
function nvl2(obj, v){
    if(!obj || obj == 'undefined' || obj == 'null'){
        return v;
    }
    return obj;
}
function to_number(s) {
    var d = parseFloat(s.replace(/[^\d^\.^-]/g, ''));
    return d || 0;
}
//------------------

// 获取随机字符串
// length: 长度
// 返回指定长度的随机字符串
function random(length) {
    if (length == undefined || length == null) {
        length = 6;
    }
    var _values = "";
    var chars = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];
    for (var i = 0; i < length ; i++) {
        var index = Math.ceil(Math.random() * (chars.length - 1));
        _values += chars[index];
    }
    return _values;
}

/***通用 End***/


// jQuery 滚动到指定ID(CZ)
function mScroll(id, top) {
    top = top || 0;
    $("html,body").stop(true);
    $("html,body").animate({ scrollTop: $("#" + id).offset().top - top }, 1000);     // 滚动到指定内容
}


//保存
function saveDataPost(u, param,fn) {

    $.ajax({
        type: "POST",
        url: u,
        data: param,
        success: function (msg) {
            if (!fn) checkResult(msg);
            else fn(msg);
        },
        error: function () { 
            $.messager.show({ icon: "error", msg: "操作失败,出现网络故障" });
        }
    });
}

//若是对象数组，返回符合对应键值的对象
Array.prototype.get=function(key,value){
	for(var i=0;i<this.length;i++){
		if(typeof(this[i])=='object'&&typeof(this[i][key])!="undefined"&&this[i][key]==value)
		return this[i];
	}
	return {};
}
//获取对象数组的某一个字段的值组成的数组
Array.prototype.getCloumn=function(key){
	var objArray=[];
	for(var i=this.length-1;i>=0;i--){
		if(typeof(this[i])=='object')
			objArray.push(this[i][key]);	
	}
	return objArray;
}
 String.prototype.trim=function(){
　　    return this.replace(/(^s*)|(s*$)/g, "");
　　 }
//为空判断函数
function isNull(value)
{
 return !value  && typeof value!=="boolean"?true:false;
}
