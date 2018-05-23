/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.quick.core.base.model;

import org.apache.ibatis.session.RowBounds;

/**
 * 通用分页类
 * @author cong
 */
public class PageBounds extends RowBounds {

    protected int total;

    protected int pageNo = 1;

    protected int pageSize = defaultLimit;

    protected int pageCount;

    protected Boolean autoCount = true;

    public final static PageBounds DEFAULT = new PageBounds();
    
    public PageBounds(){}
    
    public PageBounds(int pageNo, int pageSize){
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public boolean isFirst() {
        return pageNo == 1;
    }

    public boolean isLast() {
        return pageNo == getPageCount();
    }

    public int getOffset() {
        return (pageNo - 1) * pageSize;
    }
    public int getLimit(){
        return pageSize;
    }
    public int getStartRow(){
        return (pageNo - 1) * pageSize + 1;
    }
    public int getEndRow(){
        return pageNo * pageSize;
    }
    /**
    * 返回总页数
    * @return
    */
    public int getPageCount() {
        int pageCount = total / pageSize;
        return (total % pageSize == 0) ? pageCount : pageCount + 1;
    }
    //计算总页数
    public void count(){
        this.pageCount = this.total / this.pageSize;
        if(this.total % this.pageSize != 0){
            this.pageCount = this.pageCount + 1;
        }
        if (this.pageNo > this.pageCount){
            this.pageNo = this.pageCount;
        }
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public Boolean isAutoCount() {
        return autoCount;
    }

    public void setAutoCount(Boolean autoCount) {
        this.autoCount = autoCount;
    }

    public RowBounds toRowBounds(){
        int offset = (pageNo - 1) * pageSize;
        int limit = pageSize;
        RowBounds page = new RowBounds(offset, limit);
        return page;
    }

    private static final long serialVersionUID = 1L;
    private static final int defaultLimit = 15;
}
