package com.quick.portal.mesManage;

import java.io.Serializable;

/**
 * Created by GaoPh on 2018/5/7.
 */
public class TagManageDo implements Serializable {

    private static  final long serialVersionUID = 1L;

    private Integer tagId; //标签ID
    private String tagText; //标签名称
    private  Integer tagTypeId; //标签类型ID
    private Integer superTypeId; //上级标签ID
    private String  tagTypeName; //标签类型名
    private String superTypeName; //上级标签名称

    public String getSuperTypeName() {
        return superTypeName;
    }

    public void setSuperTypeName(String superTypeName) {
        this.superTypeName = superTypeName;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getTagText() {
        return tagText;
    }

    public void setTagText(String tagText) {
        this.tagText = tagText;
    }

    public Integer getTagTypeId() {
        return tagTypeId;
    }

    public void setTagTypeId(Integer tagTypeId) {
        this.tagTypeId = tagTypeId;
    }

    public Integer getSuperTypeId() {
        return superTypeId;
    }

    public void setSuperTypeId(Integer superTypeId) {
        this.superTypeId = superTypeId;
    }

    public String getTagTypeName() {
        return tagTypeName;
    }

    public void setTagTypeName(String tagTypeName) {
        this.tagTypeName = tagTypeName;
    }
}
