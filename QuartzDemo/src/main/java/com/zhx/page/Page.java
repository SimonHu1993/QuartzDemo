package com.zhx.page;

/**
 * Created by mulder on 15/5/14.
 */
public class Page {

    private int page;       //当前页码
    private int rows;       //每页分页数

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
