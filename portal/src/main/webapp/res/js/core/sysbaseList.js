if (window["_dataStore"] == null)   //本地数据源,考虑使用html5Store
	window["_dataStore"] = {};
var quick = {
    serverUrl: '/dologin.do',
    editUrl: '',
    openUrl:'',
    idField: 'sysid',
    urlField: '',         /*URL传参主键*/
    urlParm: '',
    queryParm:'',
    urlMap:{},
    btns:[], //权限
    debug:false,
    grid:{},
    gridId: "list", //当前表格ID
    gridType: 'datagrid',  /*控件类型*/
    isSearch: false,  /*是否自动查询*/
    selectSingle: true,  /*是否选择单选*/
    editMask: false,
    fit: false,
    border: true,
    toolbar: '',
    opt :{},
    editOptions: { maximized: true },
    editWidth: $(window).width() - 10, /*编辑窗体宽度*/
    editHeight: $(window).height() - 5 /*编辑窗体高度*/

};

quick.baseUrl = window["G.host"] || '/';
quick.objName = "记录";   //对象中文名称
//quick.pageSize = parseInt($.cookie("myPageSize")) || 15; /*每页面行数*/
quick.log = function(msg) {
    if(quick.debug)
        console.log(msg);
}
quick.getSearch = function(div){
    var s = tourlpar(div);
    var q = quick.getParm();
    var f = "";
    if(s != "" && q != "")
        f = "&";
    return q + f + s;
}
quick.getParm = function(){
    var str = quick.urlParm;
	for(var n in quick.urlMap) {
		str += "&" + n + "=" + encodeURI(quick.urlMap[n]);
	}
    return str;
}
quick.setParm = function(name, val){
    quick.urlMap[name] = val;
}
//quick.listAction();
$(document).ready(function () {
    config();   //配置模型初始化
    quick.init();
});

quick.init = function(){
    //导航更新
    navGoto(2);
    //回车查询
    document.onkeydown=function(event){
        var e = event || window.event || arguments.callee.caller.arguments[0];
        if(e && e.keyCode==13){ // enter 键
            query();
        }
    };

}

quick.initForm = function(){
    //导航条
    if (this.parent && this.parent.getTab) {
        var ifrm = parent.getTab();
        if (ifrm != null)
            document.title = ifrm.attr("title");
    }
    quick.initOption();     //控件初始化
    $(window).resize(resizeWindow);
    if (quick.isSearch) {
        try {
            search();    //表格初始化 getlist
        } catch (e) {
            $qbox.error("查询列表时发生错误" + e.message, '异常');
        }
    }
}
quick.initToolbar = function () {}
quick.initButton = function() {
    var b = false;
    if(quick.btns.length == 0){
        b = true;
    }
    var div = $(".toolbar");
    div.find(".easyui-linkbutton").each(function (i) {
        var fn = '';
        if (this.id == "a_search") {
            $(this).click(function (e) { search(e); });
        }else if (this.id == "a_print") {
            $(this).click(printLoop);
        }else if (this.id == "a_import") {
            $(this).click(importXsl);
        }else if (this.id == "a_export") {
            $(this).click(exportXsl);
        }else if (this.id == "a_delete") {
            $(this).click(del);
        }else {
            if (!this.id || this.id.indexOf('_') == -1) return;
            var fn = this.id.substr(2);
            if (window[fn])
                $(this).click(window[fn]);
        }
        if(b && fn.length > 0){
            quick.btns.push({'PerCode':fn});
        }
    });
}

quick.isRight = function (btn) {
    for (var i = 0; i < quick.btns.length; i++) {
        if (quick.btns[i].PerCode == btn)
            return true;
    }
    return false;
}
quick.initOption = function () {
    quick.urlField = quick.urlField || quick.idField; //
    if(quick.objName.indexOf('表') > -1 ) quick.objName = quick.objName.replace("表", "");
	var toolbarHeight = 0;
	var toolbarNum=0;
     $(".toolbar").each(function () { 
	 toolbarHeight += $(this)[0].clientHeight;
	 toolbarNum++;
	  });
	 var qbuttonHeight=0;
	  $(".qbutton").each(function () { 
	     qbuttonHeight+=$(this)[0].clientHeight;
	  });
	  var winSize = { width: $(window).width() - 1, height: $(window).height() };
	 if($(quick.gridId).parent()[0])
     winSize = { width: $(quick.gridId).parent().width() - 1, height: $(quick.gridId).parent()[0].clientHeight };
   
}

quick.extendParm="";

quick.add = function(){
    var sURL = urlJoin(quick.editUrl, 'Flag=1',  quick.getParm());
    var _options = $.extend(true, {}, { title: document.title + '-添加', iconCls: 'icon-add', handler: addAfter, url: sURL }, quick.editOptions);
    openWindow(_options);
}

//事件-配置模块信息
function config() {}
//事件-查询表单
function initForm(){}

//事件-查询
function search() {
    _gridPool[quick.gridId].isSearch = true;
    _gridPool[quick.gridId].refresh();
}

//打开添加页面前-验证,有需要的重写此方法
function addBefore() {return true;}

//事件-打开添加页面
function add() {
	if (!addBefore()) return false;
    if (quick.editUrl.length <= 0 || quick.editUrl == "#") return false;
	quick.add();
}
//打开添加页面-回调
function addAfter(tp) {
    quick.openUrl = '';
    if (tp) {
        search();
    }
}

//打开编辑页面钱-验证,有需要的重写此方法
function editBefore(node) {return true;}

//事件-编辑页面
function edit(e, tbid, node) {
    if (quick.editUrl.length <= 0 || quick.editUrl == "#") return false;
    tbid = tbid || quick.gridId;
	var selections=	$(tbid).datagrid("getSelections");
	if(selections.length>1)
	{
		$qbox.alert("很抱歉，您选中了多条记录，请选择其中的一条记录进行编辑");
		return;
	}
    node = node || quick.grid.getSelectData(tbid);
    if (node) {
        if (!editBefore(node)) return false;
        quick.openUrl = urlJoin(quick.editUrl, 'Flag=2&' + quick.urlField + '=' + node[quick.idField], quick.getParm()); 
        var _options = $.extend(true, {}, { title: document.title + '-编辑', iconCls: 'icon-edit', handler: editAfter, url: quick.openUrl }, quick.editOptions);
        openWindow(_options);
    } else {
        $qbox.alert('请选择要编辑的' + quick.objName + '！', '编辑');
    }
}

//打开编辑页面-回调
function editAfter(tp) {
    quick.openUrl = '';
    if (tp) {
        search();
    }
}

//打开浏览页面前-验证,有需要的重写此方法
function browseBefore(node) {return true;}
//事件-浏览页面
function browse() {
	var selections=	$(quick.gridId).datagrid("getSelections");
	if(selections.length>1)
	{
		$qbox.alert("很抱歉，您选中了多条记录，请选择其中的一条记录进行浏览");
		return;
	}
    var node = quick.grid.getSelectData(quick.gridId);
    if (node) {
        if (!browseBefore(node)) return false;
        quick.openUrl = urlJoin(quick.editUrl, 'Flag=3&' + quick.idField + '=' + node[quick.idField], quick.getParm());
        var _options = $.extend(true, {}, { title: document.title + '-浏览', iconCls: 'icon-folder_explore', handler: browseAfter, url: quick.openUrl }, quick.editOptions);
        openWindow(_options);
    } else {
        $qbox.alert('请选择要浏览的' + quick.objName + '！', '浏览');
    }
}
function browseAfter() {quick.openUrl = '';}

// 获取删除编号集合
// items: 选中项集合
function getDeleteIds(items) {
    var pids = [];
    $(items).each(function () {
        pids.push(this[quick.idField]);
    });
    return pids;
}
//删除信息前检验
function delBefore(node) {return true;}
//事件-删除信息
function del() {
	var selections=	$(quick.gridId).datagrid("getSelections");
	if(selections.length>1)
	{
		$qbox.alert("很抱歉，您选中了多条记录，请选择其中的一条记录进行删除");
		return;
	}
    var nodes = quick.grid.getCheckedData(quick.gridId);
    if (nodes && nodes.length > 0) {
        if (delBefore(nodes)) {
            var pids = getDeleteIds(nodes);
            if (pids && pids.length > 0) {
                $qbox.confirm('确定要删除选中的' + quick.objName + '吗？', '删除', function () {
                    $.post(quick.serverUrl + '/delete?' + quick.urlField + '=' + pids.join(','), function (data) {
                        delAfter(data);
                    });
                });
            }
        }
    }
    else {
        $qbox.alert('请选择要删除的' + quick.objName + '！', '删除');
    }
}
function delAfter(data) {
    var num = parseInt(data.code);
    if (num >= 1) {
        $qbox.alert(quick.objName + '删除成功！', '删除');
        search();
        // 此处需要清除所有选择的行，如果不执行此操作在选中对象已经成功删除之后该条数据仍然处于“缓存”中，将影响再次的编辑或删除操作。
        $(quick.gridId).datagrid('clearSelections');
    } else if (num == 0) {
        $qbox.error(quick.objName + data.msg, '删除');
    } else {
        checkResult(data, true);
    }
}

//事件-撤消
function refresh() {
    refreshForm();
    initForm(); //重新初始化
}
//事件-窗口改变
function resizeWindow() {
    //var winSize = { w: $(window).width() - 10, h: $(window).height() - 20 };
}
//事件-返回确认
function ok() {
    var options = $(quick.gridId).datagrid('options');
    var list = $(quick.gridId).datagrid('getSelections');
    var returnVal = list;
    if (list != null && list.length > 0) {
        if (options.singleSelect) {
            returnVal = list[0];
        }
    }
    // 关闭弹出层
    closeWin(returnVal);
}
//事件-关闭窗口
function cancel() {
    // 关闭弹出层
    closeWin();
}

function getUnUseHeight(tb) {
    var h = $(window).height();
    var nodes = $(tb).siblings();
    nodes.each(function () {
        h = h - $(this).height();
    });
    return h - 5;
}


//start datagrid封装
//加载通用配置

//双击打开窗口
quick.grid.showDetail = function (index, row) {
    if (!quick.editUrl) {
        return false;
    }
    if (quick.isRight("edit")) {//判断是否有编辑权限
        row = row || index;
        edit(null, null, row);
    }
    else if (quick.isRight("browse")) {//有浏览权限
        browse();
    }
    else {
        // $qbox.warning('你没有浏览权限，请联系管理员！', '提示');
    }
}

//end   datagrid封装

quick.formValid = function(form, fn) {
    var b = $(form).form('validate');
    if (!b) return b;
    if(fn)
    	return fn();
    else
    	return true;
}
