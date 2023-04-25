package com.bruce.core.builder;

import com.alibaba.excel.util.StringUtils;
import com.bruce.common.ErrorCode;
import com.bruce.core.builder.sql.MySQLDialect;
import com.bruce.core.builder.sql.SQLDialect;
import com.bruce.core.builder.sql.SQLDialectFactory;
import com.bruce.core.schema.TableSchema;
import com.bruce.core.schema.TableSchema.Field;
import com.bruce.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * SQL 生成器
 *
 */
@Slf4j
public class SQLBuilder {
    /**
     * 方言
     */
    private SQLDialect sqlDialect;

    public SQLBuilder(){
        this.sqlDialect = SQLDialectFactory.getDialect(MySQLDialect.class.getName());
    }

    public SQLBuilder(SQLDialect sqlDialect){
        this.sqlDialect = sqlDialect;
    }

    /**
     * 设置方言
     */
    public String buildCreateTableSQL(TableSchema tableSchema){
        StringBuilder template = new StringBuilder();
        template.append("%s\n")
                .append("CREATE TABLE IF NOT EXISTS %s\n")
                .append("(\n")
                .append("%s\n")
                .append(") %s;");

        // 构造表名
        String tableName = sqlDialect.wrapTableName(tableSchema.getTableName());
        String dbName = tableSchema.getDbName();
        if (StringUtils.isNotBlank(dbName)) {
            tableName = String.format("%s.%s", dbName, tableName);
        }

        // 构造表前缀注释
        String tableComment = tableSchema.getTableComment();
        if (StringUtils.isBlank(tableComment)) {
            tableComment = tableName;
        }
        String tablePrefixComment = String.format("-- %s", tableComment);

        // 构造表后缀注释
        String tableSuffixComment = String.format("comment %s", tableComment);

        // 构造表字段
        List<Field> fieldList = tableSchema.getFieldList();
        StringBuilder fieldStrBuilder = new StringBuilder();
        int filedSize = fieldList.size();
        for (int i = 0; i < filedSize; i++) {
            Field field = fieldList.get(i);
            fieldStrBuilder.append(buildCreateFieldSQL(field));
            // 最后一个字段不需要逗号和换行
        }

        return null;
    }

    /**
     * 生成创建字段的 SQL
     *
     * @param field
     * @return
     */
    private String buildCreateFieldSQL(Field field) {
        if (field == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String fieldName = sqlDialect.wrapFieldName(field.getFieldName());
        String fieldType = field.getFieldType();
        String defaultValue = field.getDefaultValue();
        boolean notNull = field.isNotNull();
        String comment = field.getComment();





        return null;
    }


}
