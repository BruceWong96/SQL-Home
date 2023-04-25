package com.bruce.core;

import com.alibaba.excel.util.StringUtils;
import com.bruce.core.builder.SQLBuilder;
import com.bruce.core.model.vo.GenerateVO;
import com.bruce.core.schema.SchemaException;
import com.bruce.core.schema.TableSchema;
import com.bruce.core.schema.TableSchema.Field;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 集中数据生成器
 * 门面模式，统一生成器
 *
 * @author Bruce Wong
 */
@Component
@Slf4j
public class GeneratorFacade implements Serializable {
    /**
     * 生成所有内容
     */
    public static GenerateVO generateAll(TableSchema tableSchema) {
        // 校验
        validSchema(tableSchema);
        SQLBuilder sqlBuilder = new SQLBuilder();
        return null;
    }

    /**
     *
     * 验证 schema
     *
     * @param tableSchema
     */
    private static void validSchema(TableSchema tableSchema) {
        if (tableSchema == null) {
            throw new SchemaException("数据为空");
        }

        String tableName = tableSchema.getTableName();
        if (StringUtils.isBlank(tableName)) {
            throw new SchemaException("表名为空");
        }
        Integer mockNum = tableSchema.getMockNum();
        // 默认生成 20 条
        if (tableSchema.getMockNum() == null) {
            tableSchema.setMockNum(20);
            mockNum = 20;
        }

        if (mockNum > 100 || mockNum < 10) {
            throw new SchemaException("生成条数应设置在 10 — 100 之间。");
        }
        List<Field> fieldList = tableSchema.getFieldList();
        if (CollectionUtils.isEmpty(fieldList)) {
            throw new SchemaException("字段列表为空");
        }
        for (Field field : fieldList) {
            validField(field);
        }
    }

    /**
     * 验证字段
     *
     * @param field
     */
    private static void validField(Field field) {
        String fieldName = field.getFieldName();
        String fieldType = field.getFieldType();
        if (StringUtils.isBlank(fieldName)) {
            throw new SchemaException("字段名不能为空");
        }
        if (StringUtils.isBlank(fieldType)) {
            throw new SchemaException("字段类型不能为空");
        }
    }
}
