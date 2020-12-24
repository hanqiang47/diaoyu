package com.github.jingou.common.pagination;

import java.util.List;

/**
 * @author caedmon
 */
public class PageResponse<T> {

    private List<T> rows;

    private Long totalRow;

    public PageResponse() {
    }

    public PageResponse(Long totalRow, List<T> rows) {
        this.rows = rows;
        this.totalRow = totalRow;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public Long getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(Long totalRow) {
        this.totalRow = totalRow;
    }
}
