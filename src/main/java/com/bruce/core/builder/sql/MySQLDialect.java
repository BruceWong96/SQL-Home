package com.bruce.core.builder.sql;

/**
 * MySQL 方言
 *
 * @author Bruce Wong
 */
public class MySQLDialect implements SQLDialect {

    private final static String QUOTE_FLAG= "`";

    /**
     * 封装字段名
     *
     * @param name
     * @return
     */
    @Override
    public String wrapFieldName(String name) {
        return String.format(QUOTE_FLAG + "%s" + QUOTE_FLAG, name);
    }

    /**
     * 解析字段名
     *
     * @param fieldName
     * @return
     */
    @Override
    public String parseFieldName(String fieldName) {
        if (fieldName.startsWith(QUOTE_FLAG) && fieldName.endsWith(QUOTE_FLAG)) {
            return fieldName.substring(1, fieldName.length() - 1);
        }
        return fieldName;
    }

    /**
     * 封装表名
     *
     * @param name
     * @return
     */
    @Override
    public String wrapTableName(String name) {
        return String.format( QUOTE_FLAG + "%s" + QUOTE_FLAG, name);
    }

    /**
     * 解析表名
     *
     * @param tableName
     * @return
     */
    @Override
    public String parseTableName(String tableName) {
        if (tableName.startsWith(QUOTE_FLAG) && tableName.endsWith(QUOTE_FLAG)) {
            return tableName.substring(1, tableName.length() - 1);
        }
        return tableName;
    }
}
