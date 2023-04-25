package com.bruce.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bruce.model.entity.FieldInfo;

/**
 * @Description 针对表 field_info 的 Service 接口
 *
 * @author Bruce Wong
 */
public interface FieldInfoService extends IService<FieldInfo> {
    /**
     * 校验并处理
     *
     * @param fieldInfo
     * @param add 是否为创建校验
     */
    void validAndHandleFieldInfo(FieldInfo fieldInfo, boolean add);
}
