var Model = {};
var quick = {
    serverUrl: '/dologin.do',   /*数据请求地址：quick.serverUrl+='/user'; */
    idField: 'sysid',         /*列表主键*/
    uid:0,                  /*唯一编号*/
    groupid:0,
    flag: "",               /*设置Action标识 1-添加 2-修改 3-查看*/
    urlParm: '',             /*URL附带查询参数字符串*/
    urlMap:[],
	grid:{},
	gridId: "#list", //当前表格ID
	opt :{},
    upTable: '',
    upColumn: '',
	decode:function(val,store){},
    configUpload: function (table, column) {
        this.upColumn = column;
        this.upTable = table;
    }
};
quick.baseUrl = window["G.host"] || '/';
quick.log = function(msg) {
    if(quick.debug)
        console.log(msg);
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

quick.formValid = function(form, fn) {
    var b = $(form).form('validate');
    if (!b) return b;
    if(fn)
    	return fn();
    else
    	return true;
}
quick.openWinAfter = function (data, isClose, title) {
    var json = checkResult(data);
    if (!json) return;
    var message = title+'失败！';
    if (json.msg == null) {
        if (json.code == "1" || json.code == 1) {
            message = title+'成功！';
        }
    }
    else {
        message = decodeURI(json.msg);
    }
    $qbox.alert(message, title, function () {
        if (isClose) {
            closeWin(json.code);
        }
    });
}
//quick.edit.action
$(document).ready(function () {
    quick.flag = request.get("Flag") || 1;
    config();
    quick.uid = request.get(quick.idField);
    quick.saveUrl = quick.serverUrl + "/save";
})

quick.initForm = function () {


    //quick.initUE();
}
//新增Model
quick.initAdd = function (url) {
	Model["CZRBH"]=__loginer.userId;
	Model["CZRXM"]=__loginer.userName;
	Model["CZSJ"]=new Date().format("yyyy-MM-dd");
	bindForm(Model);
	initAddAfter();
}
//事件-新增-回调
function initAddAfter(){}
//编辑
quick.initEdit = function (m, fn, url) {
    if(m) {
        Model = m;
        bindForm(m);
        if(fn)
        	fn(m);
    }else{
        url = url || quick.serverUrl + '/getObj?' + quick.idField + '=' + quick.uid + quick.getParm();
        $.post(url, function (data) {
            if (data != null && data != "null") {
                if (typeof (data) == "string") {
                    Model = eval("(" + data + ")");
                }else{
                    Model = data;
                }
                bindForm(Model);
                if(fn)
                	fn(Model);
            }
        });
    }
}

//用户操作&体验
quick.initUE = function () {
    var obj = $("form");
    var $inp = obj.find(":input");
    enter2Tab($inp, obj);    //回车切换
    firstFocus($inp);
}
function config() {}
//事件-初始化界面：initForm加载{initAdd:新增,initEdit编辑}， initRead查看
function initForm(formData) {
    if (quick.flag == "1") {
    	initAdd();
    }
    else {
    	initEdit(formData, initFormAfter);
        if (quick.flag == 3 || quick.flag == "3") {
            initRead();
            return;
        }
    }

}

//事件-编辑-回调(新增没有回调)
function initFormAfter() { }
function initRead() {
    viewRead();
}
//事件-新增
function initAdd() {
	quick.initAdd();//model初始化和绑定
}
//事件-编辑
function initEdit(formData, fn) {
	quick.initEdit(formData, fn);//model初始化和绑定
}
// 保存
quick.submit = function(fn){
    $('#form').ajaxSubmit(function(data) {
        if(typeof(data) == "string")
            data = eval("("+data+")");
        fn(data);
    });
}
// 验证规则
// options: 配置
// 例如：{ url: 'xxx', data: 数据源, handler: 回调处理方法 }
function checkRule(options) {
    var paras = $("form").serialize();
    $.ajax({
        type: 'POST',
        url: options.url,
        data: paras,
        success: function (result) {
            result = showResult(result);
            var dataSoure = [];
            if (result.code == 1) {
                if (options.data) {
                    if (options.data.length) {
                        dataSoure = options.data;
                    }
                    else {
                        dataSoure.push(options.data);
                    }
                }
            }
            else {
                if (result.value) {
                    showRule(result.value, result.msg, (options.data.XMMC || ''));
                }
            }
            if (options.handler) {
                options.handler(dataSoure);
            }
        }
    });
}

// 显示规则验证结果窗体
// data: 数据源
// title: 标题
// xmmc: 项目名称
function showRule(data, title, xmmc) {
    $('#dlgRule').remove();
    $('body').append("<div id=\"dlgRule\" class=\"easyui-dialog\" closed=\"true\"><table id=\"RULELIST\"></table></div>");
    var ruleOptions = {
        fit: true,
        title: String.format('<span style="color: orangered;">{0}：</span>', decodeURI(title)),
        border: false,
        rownumbers: true,
        pagination: false,
        singleSelect: true,
        columns: [[
                { field: "RULEDESC", title: '要求', width: '20%' },
                {
                    field: "RULEPARM", title: '您的情况', width: '40%',
                    formatter: function (value, row, index) {
                        return value == '0' ? '' : value;
                    }
                },
                {
                    field: "RESULTCODE", title: '是否满足', align: 'center',
                    formatter: function (value, row, index) {
                        return value == 1 ? '<img src="/Style/icon/accept.png" alt="符合" title="符合" />' : '<img src="/Style/icon/stop.png" alt="不符合" title="不符合" />';
                    }
                },
                { field: "RESULTTEXT", title: '结果', width: '20%' }
        ]],
        data: data
    };
    $('#RULELIST').datagrid(ruleOptions);
    $('#dlgRule').dialog({
        width: 800,
        height: 350,
        title: String.format('条件检查结果 - 未满足{0}的申请要求', xmmc.length > 0 ? String.format('「<span style="color: blue;">{0}</span>」', xmmc) : '所选项目'),
        modal: true,
        cache: false,
        collapsible: false,
        iconCls: 'icon-exclamation',
        loadingMessage: '正在加载，请稍候...',
        buttons: [{
            text: '关闭',
            iconCls: 'icon-cancel',
            handler: function () {
                $('#dlgRule').dialog('close');
            }
        }]
    });
    $('#dlgRule').dialog('open');
}

//在指定位置插入行
function addRow(id,index,row)
{
	$('#'+id).edatagrid('addRow',{index:index,row:row});
}
//删除选中行
function delRow(id)
{
	 var row = $("#"+id).edatagrid('getSelected');
     if (row)  $('#'+id).edatagrid('deleteRow', $('#'+id).datagrid('getRowIndex', row));
}
//编辑指定行
function editRow(id)
{
	 var row = $("#"+id).edatagrid('getSelected');
     if (row)  $('#'+id).edatagrid('editRow', $('#'+id).datagrid('getRowIndex', row));
}
//获取改变行
function getChangesRow(id)
{
	 var effectRow = new Object();
	var row = $("#"+id).edatagrid('getSelected');
	 $("#"+id).datagrid('endEdit',$('#'+id).datagrid('getRowIndex', row));
	 var addRow=$("#"+id).datagrid('getChanges','inserted');
	 var editRow=$("#"+id).datagrid('getChanges','updated');
	 var saveRow=addRow.concat(editRow);
	 var delRow=$("#"+id).datagrid('getChanges','deleted');
	 var aRow=$("#"+id).datagrid('getChanges');
	 effectRow["saveRow"]=JSON.stringify(saveRow);
	 effectRow["delRow"]=JSON.stringify(delRow);
	 return effectRow;
}
//创建导航
function createDH(id,dh)
{
	var dhStr="<ul>";
	if(dh)
	 for(var i=0;i<dh.length;i++)
	 dhStr+="<li><a id='go_"+i+"'>"+dh[i]+"</a></li>";
	 dhStr+="</ul>"
	 $("#"+id).html(dhStr);
	 $("#"+id+" li a").unbind("click").bind("click", function () {
               var to_id = this.id.replace("go_", "to_");
                mScroll(to_id, 65);
            });
}
quick.decode = function(val,store){
	if(_dataStore[store] == null) return val;
	for (var a = 0; a < store.length; a++) {
        if (val == _dataStore[store][a].value) return _dataStore[store][a].text;
    }
    return val;
}
function addValid(opt,id){
    id = id || '#form';
    opt = $.extend(true, {
        errorPlacement: function(error, element) {
            var pp = element.parent();
            if(pp.hasClass("input-group"))
                pp = pp.parent();
            pp.append(error);
        },
        ignore : ":disabled",
        rules: {
        }
    },opt);
    $(id).validate(opt);
}
