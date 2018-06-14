if (window["_gridPool"] == null)
    window["_gridPool"] = {};
window["_gd"] = window["_gridPool"];
/*表格相关定义*/
var GridOptions={
    idField:'',
    method:'POST',
    contentType:'application/x-www-form-urlencoded',
    classes:'table table-hover', //表格样式
    height:$(window).height()-15, //表格高度
    undefinedText:'-',//undefined时显示文本
    striped:true,//隔行变色
    iconsPrefix:'glyphicon', //字体库glyphicon/fa
    sortClass:'sortClass',
    sortName:'',
    sortOrder:'asc', //排序方式
    sortStable:true,
    columns:[],
    url:'',
    smartDisplay:false,//固定每页显示条数
    cache:'false', //是否缓存
    queryParams:function(params){
        var temp={
                pageSize : params.pageSize,
                page : params.pageNumber
        }
        return temp;
    },
    queryParamsType:'',
    responseHandler:function(data){
        return data;
    },
    pagination:true, //是否分页
    sidePagination:'server',
    pageNumber:1, //初始化加载第一页，默认第一页
    pageSize: 10, //每页的记录行数（*）
    pageList:[10, 30, 50, 100],
    clickToSelect:true,//点击时自动选择
    singleSelect:true,//单选或多选
    toolbar:'#formSearch',
    paginationLoop:false
}

var BGrid=function(_selector) {
    // 选择器
    this.selector = _selector || '#list';
    this.options=GridOptions;
    //配置
    this.config=function(options){
        this.options=$.extend({},this.options,options)
    };
    //初始化
    this.init=function(){
        this.options.queryParams=this.createQuery;
        $(this.selector).bootstrapTable(this.options);
    };
    this.refresh=function(){
        $(this.selector).bootstrapTable('destroy');
        this.init();
    };
    //创建查询
    this.createQuery=function(params){
        var temp={
            pageSize : params.pageSize,
            page : params.pageNumber
        };
        $(this.toolbar).find(":text,input:hidden,:input,:password,:radio,:checkbox,textarea,select").each(function (i) {
            var key = this.name ? this.name : (this.id ? this.id.substr(3) : '');
            if (key == '') return;
            var nodeType = this.nodeName;
            if (nodeType == "input" || nodeType == "INPUT") {
                var etype = $(this).attr("type") || "text";
                if (etype == "checkbox" || etype == "radio") {
                    if (!this.checked)
                        return;
                }
            }
            //if(!this.value&&typeof(this.value)!="undefined"&&this.value!="")
                temp[key]=encodeURI(this.value);
        });
        return temp;
    };
    this.getSelect=function(){
        return $(this.selector).bootstrapTable('getAllSelections');
    };
}

BGrid.filter = function(data){
    if (!data) {
        BDialog.error('服务器发生异常，无法连接！');
        return { total: 0, rows: [] };
    }
    else if (data.code) {
        BDialog.error(decodeURI(data.msg));
        return { total: 0, rows: [] };
    }
    //自定义转换
    /*if (data.rows) {
        return data;
    } else {
        return { total: data.length, rows: data };
    }*/
};
BGrid.extend = function(id, opt){
    BGrid.formatOption(id, opt);
    return opt;
};

//自定义函数解析2
BGrid.formatOption = function(id, opt){
    for (var i = 0; i < opt.columns[0].length; i++) {
        var key = opt.columns[0][i].field;
        var store = opt.columns[0][i].decoder;
        if (store) {
            var storeStr = store.toString();
            if (typeof (store) == "string") {
                storeStr = "_dataStore[\""+store+"\"]";
            }

            if(opt.columns[0][i].formatter == null) {
                opt.columns[0][i].formatter =function(value,row,index){
                    return BGrid.decode(value,_dataStore[this.decoder]);
                };
            }
            else{
                var formatter=opt.columns[0][i].formatter;
                opt.columns[0][i].formatter =function(value,row,index){
                    var val= BGrid.decode(value,_dataStore[this.decoder]);
                    return formatter();
                };

            }
        }
    }
}

BGrid.decode = function(val,store){
    //customer
    if (val == "" || val == null) {
        return "-";
    }
    if(store == null) return val;
    for (var a = 0; a < store.length; a++) {
        if (val == store[a].value) return store[a].text;
    }
    return val;
}

var ComboboxOptions = {textField:"text",valueField:"value", emptyObj:null, defaultVal:""};
/*下拉框相关定义*/
var BCombobox=function(_selector){
    var ths = this;
    this.load=function(url,opt){
        opt = opt || {};
        var cfg = $.extend({},ComboboxOptions, opt);
        $.ajax({
            url:url,
            type:'post',
            dataType:'json',
            success:function(data){
                if(typeof(data) == "string")
                    data=eval("("+data+")");
                var rows= data.length > 0 ? data : data.rows;
                var v = $(_selector).val();
                $(_selector).empty();
                var str = ths.appendOption(rows, cfg, v);
                $(_selector).append(str);
                if(opt.success)
                    opt.success(data);
            }
        });
    };
    this.loadData=function(store,opt){
        opt = opt || {};
        var cfg = $.extend({},ComboboxOptions, opt);
        var v = $(_selector).val();
        $(_selector).empty();
        var str = ths.appendOption(store, cfg, v);
        $(_selector).append(str);
    };
};
BCombobox.prototype.appendOption = function(rows, cfg, val){

    var option = [];
    var s = "", c = " selected='selected'";
    if(cfg.emptyObj){
        if(val == cfg.emptyObj[cfg.valueField])
            s = c;
        option.push("<option class='disabled' value='",cfg.emptyObj[cfg.valueField], "'",s," >",cfg.emptyObj[cfg.textField], "</option>");
    }
    if(rows == null || rows.length == 0)
        return option.join('');
    for(var i = 0; i < rows.length; i++){
        var item = rows[i];
        s = "";
        if(val == item[cfg.valueField])
            s = c;
        option.push("<option value='",item[cfg.valueField], "'",s," >",item[cfg.textField], "</option>");
    }
    return option.join('');
}
/**
 * 提示框
 * @param content 内容
 * @param title 标题
 * @param icon 图标
 */
function BAlert(content,title){
    content=content||"";
    title=title||"提示";
    $("#bAlert").remove();
    var htmlStr="<div class=\"modal fade\" id=\"bAlert\">"+
        "<div class=\"modal-dialog\"> <div class=\"modal-content message_align\">"+
        "<div class=\"modal-header\">"+
        "<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\"><span aria-hidden=\"true\">×</span></button>"+
        "<h4 class=\"modal-title\">"+title+"</h4>"+
        "</div> <div class=\"modal-body\"><p>"+
        content+"</p> </div></div> </div></div>";
    $("body").append(htmlStr);
    $("#bAlert").modal();
}
function BConfirm(content,confirmFn,cancelFn,title){
    title=title||"提示信息";
    content=content||"";
    confirmFn=confirmFn||confirm
    cancelFn=cancelFn||cancel
    $("#bConfirm").remove();
    var htmlStr=    "<div class=\"modal fade\" id=\"bConfirm\"><div class=\"modal-dialog\">"+
        "<div class=\"modal-content message_align\"> <div class=\"modal-header\">"+
        "<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\"><span aria-hidden=\"true\">×</span></button>"+
        "<h4 class=\"modal-title\">"+title+"</h4></div><div class=\"modal-body\">"+
        content+"</div> <div class=\"modal-footer\">"+
       " <button  type=\"button\"  id=\"bConfirm_ok\"  class=\"btn btn-success\" data-dismiss=\"modal\">确定</button>"+
        "<button   type=\"button\" id=\"bConfirm_cancel\"  class=\"btn btn-default\" data-dismiss=\"modal\" >取消</button>"+
        "</div> </div></div></div>";
    $("body").append(htmlStr);
    $("#bConfirm").modal();
    $("#bConfirm_ok").click(function(){
        confirmFn()
    });
    $("#bConfirm_cancel").click(function(){
        cancelFn();
    });

}
function confirm(){
    $("#bConfirm").modal("hide");
}
function cancel(){
    $("#bConfirm").modal("hide");
}
//获取页面url的参数
$.request = (function(){
    var apiMap = {};
    function request(queryStr){
        var api = {};
        if (apiMap[queryStr]) {
            return apiMap[queryStr];
        }
        api.queryString = (function(){
            var urlParams = {};
            var e, d = function(s){
                return decodeURIComponent(s.replace(/\+/g, " "));
            }, q = queryStr.substring(queryStr.indexOf('?') + 1), r = /([^&=]+)=?([^&]*)/g;
            while (e = r.exec(q))
                urlParams[d(e[1])] = d(e[2]);
            return urlParams;
        })();
        api.getUrl = function(){
            var url = queryStr.substring(0, queryStr.indexOf('?') + 1);
            for (var p in api.queryString) {
                url += p + '=' + api.queryString[p] + "&";
            }
            if (url.lastIndexOf('&') == url.length - 1) {
                return url.substring(0, url.lastIndexOf('&'));
            }
            return url;
        }
        apiMap[queryStr] = api;
        return api;
    }
    $.extend(request, request(window.location.href));
    return request;
})();
function clearPlaceHodler(fid){
    fid = fid || "form";
    $(fid).find("input,textarea").each(function(i){
        if($(this).val() == $(this).attr("placeholder")){
            $(this).val("");
        }
        //$(this).attr("placeholder", "");
    });
}
function layerDateTime(id){
    laydate({
        elem: '#'+ id
    });
    $("#"+id).focus();
}
function chosebox(nid, url,id, fn, opt){
    if(!layer.values)
        layer.values = {};
    var val = $("#"+id).val();
    opt = opt || {};
    var d = {
        type: 2
        ,title: '选择记录'
        ,area: ['680px', '500px']
        ,shade: 0
        ,maxmin: true
        ,content: url + "?rid=" + val
        ,btn: ['确认', '关闭'] //只是为了演示
        ,yes: function(index, layero){
            var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
            if(!iframeWin._chosenId){
                alert('您还未选择记录，请点击要选择的行');
                return;
            }
            if(id){
                $("#"+nid).val(iframeWin._chosenName);
                $("#"+id).val(iframeWin._chosenId);
            }
            if(fn){
                var gd = iframeWin._gd["list"];
                var dt = gd.getSelect();
                fn(dt || [], iframeWin);
            }
            layer.closeAll();
        }
        ,btn2: function(){
            layer.closeAll();
        }
    };
    var o = $.extend(d, opt);
    layer.open(o);
}
