package com.bruce.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录请求
 *
 * @author Bruce Wong
 * @date 2023年04月19日 00:01
 */
@Data
public class UserLoginRequest implements Serializable {
    private static final long serialVersionUID = -2071676050393837506L;

    private String userAccount;

    private String userPassword;

}
