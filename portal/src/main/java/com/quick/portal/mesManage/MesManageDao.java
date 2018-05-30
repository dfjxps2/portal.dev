package com.quick.portal.mesManage;

import com.quick.core.base.ISysBaseDao;
import com.quick.core.base.model.PageBounds;

import java.util.List;
import java.util.Map;

/**
 * Created by GaoPh on 2018/5/3.
 */
public interface MesManageDao<MesManageDO> extends ISysBaseDao<MesManageDO> {
    //内容管理初始化
    List<Map<String,Object>> selectMes(Map<String,Object> tag, PageBounds page);
    //内容管理初始化
    List<Map<String,Object>> selectMes(Map<String,Object> tag);
    //内容管理-删除内容
    int deleteMsg(String msgId);
    //内容管理初始化--获取内容标签信息
    List<String> selectMesTag( String mesId);
    //内容发布,信息标签
    int insertMesTag( Map<String,Object> map);
    //内容管理修改信息标签,删除原标签
    int deleteMesTag(Integer msgId);
    //内容查询--密级下拉框
    List<Map<String,Object>> selectMsgCs();
    //自动审核规则数据查询
    List<Map<String,Object>> selectRules(Map<String,Object> rules);
    //增加参数值，选择规则下拉框数据
    List<Map<String,Object>> selectRule();
    //增加参数值-保存
    int insertParamValue(Map<String,Object> map);
    //查找目前规则参数表中最大ID
    int selectMaxParam();
    //查找目前规则参数表中数据量
    int countParam();
    //将数据库中数据导出为指定格式的excel
    List<Map<String,Object>> exportExcel();
    //自动审核管理，参数值删除
    int deleteParamValue(Map<String,Object> map);
    //内容管理初始化&#45;&#45;内容标签，标签父级类型分类
    List<Map<String,Object>> selectTagTree();
    //内容管理初始化&#45;&#45;内容标签，标签类型分类
    List<Map<String,Object>> selectTypeCla();
    //内容管理初始化,内容修改
    int msgedit(Map<String,Object> map);
    //内容管理初始化,审核状态修改
    int mesappr(Map<String,Object> map);
    //根据标签树进行查询
    List<Map<String,Object>> mesByTag(Integer tagId);



    //标签管理-初始化/查询
    List<Map<String,Object>> selectTagMes(Map<String,Object> tag, PageBounds page);
    List<Map<String,Object>> selectTagMes(Map<String,Object> tag);
    //查询标签的总数
    Integer selectTagCount(Map<String,Object> tag);
    //内容标签数据
    List<Map<String,Object>> selectTagsTree();
    //删除选择的内容标签
    int deleteTags(Map<String,Object> tag);
    //删除选择的内容标签类型
    int deleteTagsType(Map<String,Object> tag);
    //编辑标签
     int updateTagType(Map<String,Object> tagType);
     //查询上级标签
    List<Map<String,Object>> selectSuperTag(Map<String,Object> tag, PageBounds page);
    //上级标签的数量
    int countSuperTag(Map<String,Object> tag);
    //查询标签类型--分页
    List<Map<String,Object>> selectTagType(Map<String,Object> tag, PageBounds page);
    //查询标签类型--不分页
    List<Map<String,Object>> selectTagType(Map<String,Object> tag);
    //上级标签的数量
    int countTagType(Map<String,Object> tag);
    //获得当前标签库中最大ID值
    int selectMaxTagId();
    //获得当前标签类型库中最大ID值
    int selectMaxTypeTagId();
    //增加新标签
    int insertTag(Map<String,Object> tag);
    //增加新标签类型
    int insertTagType(Map<String,Object> tagType);
    //根据消息ID查对应的标签id
    List<Map<String,Object>> selectMesByTag(List<Integer> tag);

}
