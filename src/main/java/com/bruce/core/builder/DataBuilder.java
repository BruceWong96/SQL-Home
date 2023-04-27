package com.bruce.core.builder;

import com.bruce.core.schema.TableSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 数据生成器
 */
public class DataBuilder {
    /**
     * 生成数据
     */
    public static List<Map<String, Object>> generateData(TableSchema tableSchema, int rowNum) {
        List<TableSchema.Field> fieldList = tableSchema.getFieldList();
        // 初始化结果数据
        List<Map<String, Object>> resultList = new ArrayList<>(rowNum);


        return null;
    }
}
