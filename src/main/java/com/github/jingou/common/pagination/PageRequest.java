package com.github.jingou.common.pagination;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Map;

/**
 * @author Caedmon
 */
@ApiModel(value = "分页对象")
public class PageRequest<C> {
    /**
     * 当前页码
     */
    @Min(value = 1, message = "不能小于1")
    @ApiModelProperty(value = "当前页码")
    public Integer pageNumber = 1;

    /**
     * 每页的数据条数
     */
    @ApiModelProperty(value = "每页的数据条数")
    @Min(value = 1, message = "不能小于1")
    @Max(value = 50, message = "不能大于50")
    public Integer pageSize = 10;

    /**
     * 排序字段及其排序方式
     */
    @ApiModelProperty(value = "排序字段及其排序方式")
    public Map<String, String> orderBy;

    /**
     * 查询条件
     */
    @ApiModelProperty(value = "params")
    public C params;

    public String generateOrderByStr() {
        if (null != orderBy && orderBy.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> entry : orderBy.entrySet()) {
                sb.append(entry.getKey()).append(" ");
                sb.append(entry.getValue()).append(",");
            }
            String orderSql = sb.toString();
            orderSql = orderSql.substring(0, orderSql.length() - 1);
            return orderSql;
        } else {
            return null;
        }
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Map<String, String> getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Map<String, String> orderBy) {
        this.orderBy = orderBy;
    }

    public C getParams() {
        return params;
    }

    public void setParams(C params) {
        this.params = params;
    }
}
