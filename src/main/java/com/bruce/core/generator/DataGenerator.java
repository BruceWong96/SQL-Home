package com.bruce.core.generator;

import com.bruce.core.schema.TableSchema;

import java.util.List;

/**
 * 数据生成器
 *
 * @Author Bruce Wong
 */
public interface DataGenerator {

    /**
     * 生成数据接口
     *
     * @Param field 字段信息
     * @Param rowNum 生成数据条数
     * @Return
     *
     */
    List<String> doGenerate(TableSchema.Field field, int rowNum);
}
