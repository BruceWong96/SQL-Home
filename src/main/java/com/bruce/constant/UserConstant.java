package com.bruce.constant;

/**
 * 用户常量
 */
public interface UserConstant {
    /**
     * 用户登录状态键
     */
    String USER_LOGIN_STATUS = "userLoginStatus";

    /**
     * 系统用户 id （虚拟用户）
     */
    long SYSTEM_USER_ID = 0L;

    /**
     * 权限
     */

    /**
     * 默认权限
     */
    String DEFAULT_ROLE = "user";

    /**
     * 管理员权限
     */
    String ADMIN_ROLE = "admin";
}
