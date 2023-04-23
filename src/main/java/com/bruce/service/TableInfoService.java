package com.bruce.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bruce.model.entity.TableInfo;

/**
 * @Author Bruce Wong
 */
public interface TableInfoService extends IService<TableInfo> {
    /**
     * 校验并处理
     *
     * @param tableInfo
     * @param add 是否为创建校验
     */
    void validAndHandleTableInfo(TableInfo tableInfo, boolean add);
}
