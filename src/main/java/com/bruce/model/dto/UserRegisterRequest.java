package com.bruce.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册请求
 *
 * @author Bruce Wong
 * @date 2023年04月19日 00:01
 */
@Data
public class UserRegisterRequest implements Serializable {
    private static final long serialVersionUID = -2071676050393837506L;

    private String userName;

    private String userAccount;

    private String userPassword;

    private String checkPassword;
}
