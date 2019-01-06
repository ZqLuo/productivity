package com.productivity.web.vo;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import java.util.List;

/**
 * 列表数据返回封装成easyui格式
 */
public class DataGridReturn<T> {

    private boolean isPageQuery = false;

    public DataGridReturn() {
    }

    public DataGridReturn(Pagination pagination) {
        if(pagination != null){
            isPageQuery = true;
            PageHelper.startPage(pagination.getPage(),pagination.getSize());
        }
    }

    public DataGridReturn(int total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    private int total;
    private List<T> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public void setPageHelperList(List<T> list) {
        if(isPageQuery){
            if(list != null && list.size() > 0){
                Page<T> page = (Page<T>) list;
                this.total = (int) page.getTotal();
            }else{
                this.total = 0;
            }
        }else{
            this.total = list==null?0:list.size();
        }
        this.rows = list;
    }
}
