package com.bruce.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bruce.common.ErrorCode;
import com.bruce.core.schema.TableSchema;
import com.bruce.exception.BusinessException;
import com.bruce.mapper.FieldInfoMapper;
import com.bruce.model.entity.FieldInfo;
import com.bruce.service.FieldInfoService;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;

public class FieldInfoServiceImpl extends ServiceImpl<FieldInfoMapper, FieldInfo> implements FieldInfoService {

    private final static Gson gson = new Gson();

    @Override
    public void validAndHandleFieldInfo(FieldInfo fieldInfo, boolean add) {
        if (fieldInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String content = fieldInfo.getContent();
        String name = fieldInfo.getName();
        Integer reviewStatus = fieldInfo.getReviewStatus();

        // 创建时, 所有参数必须非空
        if (add && StringUtils.isAnyBlank(name, content)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (StringUtils.isNotBlank(name) && name.length() > 30) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "名称过长");
        }
        if  (StringUtils.isNotBlank(content)) {
            if (content.length() > 20000) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "内容过长");
            }
            // 校验字段内容
            try {
                TableSchema.Field tableSchema = gson.fromJson(content, TableSchema.Field.class);
            }
        }
    }
}
