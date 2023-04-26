package com.bruce.core.builder;

import com.alibaba.excel.util.StringUtils;
import com.bruce.common.ErrorCode;
import com.bruce.core.builder.sql.MySQLDialect;
import com.bruce.core.builder.sql.SQLDialect;
import com.bruce.core.builder.sql.SQLDialectFactory;
import com.bruce.core.model.enums.FieldTypeEnum;
import com.bruce.core.schema.TableSchema;
import com.bruce.core.schema.TableSchema.Field;
import com.bruce.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

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
        String onUpdate = field.getOnUpdate();
        boolean primaryKey = field.isPrimaryKey();
        boolean autoIncrement = field.isAutoIncrement();
        // e.g. column_name int default 0 not null auto_increment comment '注释' primary key,
        StringBuilder fieldStrBuilder = new StringBuilder();
        // 字段名
        fieldStrBuilder.append(fieldName);
        // 字段类型
        fieldStrBuilder.append(" ").append(fieldType);
        // 默认值
        if (StringUtils.isNotBlank(defaultValue)) {
            fieldStrBuilder.append(" default ").append(getValueStr(field, defaultValue));
        }
        // 是否非空
        fieldStrBuilder.append(" ").append(notNull ? "not null" : "null");
        // 是否自增
        if (autoIncrement) {
            fieldStrBuilder.append(" auto_increment");
        }
        // 附加条件
        if (StringUtils.isNotBlank(onUpdate)) {
            fieldStrBuilder.append(" on update ").append(onUpdate);
        }
        // 注释
        if (StringUtils.isNotBlank(comment)) {
            fieldStrBuilder.append(" comment ").append(comment);
        }
        // 是否为主键
        if (primaryKey) {
            fieldStrBuilder.append(" primary key");
        }
        return fieldStrBuilder.toString();
    }

    /**
     * 构造插入数据 SQL
     *
     * @param field
     * @param value
     * @return
     */


    public static String getValueStr(Field field, Object value) {
        if (field == null || value == null) {
            return "''";
        }
        FieldTypeEnum fieldTypeEnum = Optional.ofNullable(FieldTypeEnum.getEnumByValue(field.getFieldType())).orElse(FieldTypeEnum.TEXT);

        String result = String.valueOf(value);

        switch (fieldTypeEnum) {
            case DATETIME:
            case TIMESTAMP:
                return result.equalsIgnoreCase("CURRENT_TIMESTAMP") ? result : String.format("'%s'", result);
            case DATE:
            case TIME:
            case CHAR:
            case VARCHAR:
            case TINYTEXT:
            case TEXT:
            case MEDIUMTEXT:
            case LONGTEXT:
            case TINYBLOB:
            case BLOB:
            case MEDIUMBLOB:
            case LONGBLOB:
            case BINARY:
            case VARBINARY:
                return String.format("'%s'", value);
            default:
                return result;
        }
    }
}
