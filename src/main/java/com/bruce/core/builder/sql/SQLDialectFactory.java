package com.bruce.core.builder.sql;

import com.bruce.common.ErrorCode;
import com.bruce.exception.BusinessException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * SQL 方言工厂
 *
 * 工厂 + 单例模式，降低开销
 *
 * @author Bruce Wong
 */
public class SQLDialectFactory {

    /**
     * className => 方言实例映射
     *
     */
    private static final Map<String, SQLDialect> DIALECT_POOL = new ConcurrentHashMap<>();

    private SQLDialectFactory() {
    }

    /**
     * 获取方言实例
     *
     * 线程安全
     *
     * 如果 className 存在，则直接返回
     * 如果不存在则创建一个新的实例，并放到 DIALECT_POOL 中
     *
     * @param className 方言类
     * @return 方言实例
     */
    public static SQLDialect getDialect(String className) {
        SQLDialect dialect = DIALECT_POOL.get(className);
        if (null == dialect) {
            synchronized (className.intern()) {
                dialect = DIALECT_POOL.computeIfAbsent(className,
                        key -> {
                            try {
                                return (SQLDialect) Class.forName(key).newInstance();
                            } catch (Exception e) {
                                throw new BusinessException(ErrorCode.SYSTEM_ERROR);
                            }
                        });
            }
        }
        return dialect;
    }

}
