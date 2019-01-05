package com.productivity.web.vo;

/**
 * 分页结构
 */
public class Pagination {

    public Pagination(int page, int size) {
        this.page = page;
        this.size = size;
    }

    /**
     * 页码
     */
    private int page;
    /**
     * 分页大小
     */
    private int size;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
