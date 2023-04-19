package com.bruce.common;

import com.bruce.constant.CommonConstant;
import lombok.Data;

/**
 * 分页请求
 */
@Data
public class PageRequest {
    /**
     * 当前页号
     */
    private long current = 1;

    /**
     * 页面大小
     */
    private long size = 10;

    /**
     * 排序字段
     */
    private String sortField;

    /**
     * 排序方式, 默认升序
     */
    private String sortOrder = CommonConstant.SORT_ORDER_ASC;

}
